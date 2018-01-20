package controller;

import dao.BookDao;
import model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static util.ActionOperations.*;

public class BookController extends HttpServlet {
    private final BookDao bookDao;

    private static final String LIST_BOOK = "/listBooks.jsp";
    private static final String UPDATE_BOOK = "/editBook.jsp";

    public BookController() {
        this.bookDao = new BookDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String view = "";
        String action = req.getParameter("action");

        if(CREATE.name().equalsIgnoreCase(action)){
            int userId = Integer.parseInt(req.getParameter("userId"));
            req.setAttribute("userId", userId);
            view = UPDATE_BOOK;
        } else if(REVIEW_BOOKS_OF_USER.name().equalsIgnoreCase(action)){
            int userId = Integer.parseInt(req.getParameter("userId"));
            req.setAttribute("books", bookDao.getAllBooksByUserId(userId));
            req.setAttribute("userId", userId);
            view = LIST_BOOK;
        } else if(DELETE.name().equalsIgnoreCase(action)){
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            int userId = Integer.parseInt(req.getParameter("userId"));
            bookDao.deleteBook(bookId);
            req.setAttribute("userId", userId);
            req.setAttribute("books", bookDao.getAllBooksByUserId(userId));
            view = LIST_BOOK;
        } else if(EDIT.name().equalsIgnoreCase(action)){
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            int userId = Integer.parseInt(req.getParameter("userId"));
            req.setAttribute("userId", userId);
            req.setAttribute("book", bookDao.getBookById(bookId));
            view = UPDATE_BOOK;
        } else if(LIST.name().equalsIgnoreCase(action)){
            req.setAttribute("books", bookDao.getAllBooks());
            view = LIST_BOOK;
        } else {
            throw new RuntimeException("Bad action");
        }

        RequestDispatcher rd = req.getRequestDispatcher(view);
        rd.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();
        book.setQuantity(Integer.valueOf(req.getParameter("quantity")));
        book.setAuthor(req.getParameter("author"));
        book.setName(req.getParameter("name"));
        book.setUserId(Integer.valueOf(req.getParameter("userId")));

        try {
            Date date = new SimpleDateFormat("MM/dd/yyyy").parse(req.getParameter("release"));
            book.setRelease(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String id = req.getParameter("bookId");
        if(id == null || id.isEmpty()){
            bookDao.addBook(book);
        } else {
            book.setId(Integer.valueOf(id));
            bookDao.editBook(book);
        }

        req.setAttribute("userId", book.getUserId());
        req.setAttribute("books", bookDao.getAllBooksByUserId(book.getUserId()));
        RequestDispatcher rd = req.getRequestDispatcher(LIST_BOOK);
        rd.forward(req,resp);
    }
}
