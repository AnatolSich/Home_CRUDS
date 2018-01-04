package controller;

import dao.DaoBook;
import model.Book;
import util.ActionValuesEnum;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static util.ActionValuesEnum.CREATE;
import static util.ActionValuesEnum.DELETE;
import static util.ActionValuesEnum.EDIT;
import static util.ActionValuesEnum.LIST;

public class BookController extends HttpServlet {
    private static final String INSERT_EDIT = "editBook.jsp";
    private static final String BOOK_LIST = "listBook.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String view = "";
        String actionValue = req.getParameter("action");

        if (CREATE.name().equalsIgnoreCase(actionValue)) {
            view = INSERT_EDIT;
        } else if (DELETE.name().equalsIgnoreCase(actionValue)) {
            int deleteBookId = Integer.parseInt(req.getParameter("bookId"));
            DaoBook.deleteBookById(deleteBookId);
            req.setAttribute("books", DaoBook.getALLBooks());
            view = BOOK_LIST;
        } else if (EDIT.name().equalsIgnoreCase(actionValue)) {
            int deleteBookId = Integer.parseInt(req.getParameter("bookId"));
            Book book = DaoBook.getBookById(deleteBookId);
            req.setAttribute("book", book);
            view = INSERT_EDIT;
        } else if (LIST.name().equalsIgnoreCase(actionValue)) {
            req.setAttribute("books", DaoBook.getALLBooks());
            view = BOOK_LIST;
        } else {
            throw new RuntimeException("Bad action");
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");

        Book newBook = new Book();
        newBook.setName(req.getParameter("name"));
        newBook.setAuthor(req.getParameter("author"));
        try {
            newBook.setRelease(new SimpleDateFormat("dd.MM.yyyy").parse(req.getParameter("release")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newBook.setQuantity(Integer.parseInt(req.getParameter("quantity")));


        if(bookId==null || bookId.isEmpty()) {
            DaoBook.addBook(newBook);
        } else {
            newBook.setId(Integer.parseInt(bookId));
            DaoBook.updateBook(newBook);
        }

        req.setAttribute("books", DaoBook.getALLBooks());
        RequestDispatcher dispatcher = req.getRequestDispatcher(BOOK_LIST);
        dispatcher.forward(req, resp);
    }
}
