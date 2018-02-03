package controller;

import dao.BooksDao;
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

import static util.Constants.DATE_PATTERN;
import static util.Operations.*;

public class BookController extends HttpServlet {

    private final static String BOOK_CREATE = "/bookCreate.jsp";
    private final static String BOOK_EDIT = "/bookEdit.jsp";
    private final static String BOOK_LIST_BY_USER = "/booksListByUserId.jsp";

    private BooksDao booksDao;

    public BookController() {
        this.booksDao = new BooksDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String view = "";
        String actionValue = req.getParameter("action");
        if (LIST_BY_ID.name().equalsIgnoreCase(actionValue)) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            req.setAttribute("books", booksDao.getBooksByUserId(userId));
            req.setAttribute("userId", userId);

            view = BOOK_LIST_BY_USER;
        } else if (CREATE.name().equalsIgnoreCase(actionValue)) {
            String userId = req.getParameter("userId");
            req.setAttribute("userId", userId);
            view = BOOK_CREATE;

        } else if (DELETE.name().equalsIgnoreCase(actionValue)) {
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            booksDao.deleteBookById(bookId);
            String userId = req.getParameter("userId");
            req.setAttribute("books", booksDao.getBooksByUserId(Integer.parseInt(userId)));
            req.setAttribute("userId", userId);

            view =  BOOK_LIST_BY_USER;
        } else if (EDIT.name().equalsIgnoreCase(actionValue)) {
            int bookId = Integer.parseInt(req.getParameter("bookId"));
            Book book = booksDao.getBookById(bookId);
            req.setAttribute("book", book);
            view = BOOK_EDIT;
        } else {
            throw new RuntimeException("Invalid request");
        }
        req.setAttribute("date_pattern", DATE_PATTERN);
        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();
        book.setTitle(req.getParameter("title"));

        Date releaseDate = null;
        try {
            releaseDate = new SimpleDateFormat(DATE_PATTERN).parse(req.getParameter("releaseDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setReleaseDate(releaseDate);
        String userId = req.getParameter("userId");
        book.setUserId(Integer.parseInt(userId));

        String id = req.getParameter("id");
        if (id == null || id.isEmpty() || id.equalsIgnoreCase("Auto")) {
            booksDao.createBook(book);
        } else {
            book.setId(Integer.parseInt(id.trim()));
            booksDao.editBook(book);
        }
        req.setAttribute("books", booksDao.getBooksByUserId(Integer.parseInt(userId)));
        req.setAttribute("userId", userId);
        req.setAttribute("date_pattern", DATE_PATTERN);
        RequestDispatcher dispatcher = req.getRequestDispatcher(BOOK_LIST_BY_USER);
        dispatcher.forward(req, resp);
    }
}
