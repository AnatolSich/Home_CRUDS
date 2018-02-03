package controller;

import dao.UsersDao;
import model.User;
import util.Operations;

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

public class UserController extends HttpServlet {

    private static final String USERS_LIST = "/usersList.jsp";
    private static final String USER_EDIT = "/userEdit.jsp";
    private static final String USER_CREATE = "/userCreate.jsp";

    private UsersDao usersDao;

    public UserController() {
        this.usersDao = new UsersDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String view = "";
        String actionValue = req.getParameter("action");
        if (LIST.name().equalsIgnoreCase(actionValue)) {
            req.setAttribute("date_pattern", DATE_PATTERN);
            req.setAttribute("users", usersDao.getAllUsers());
            view = USERS_LIST;
        } else if (CREATE.name().equalsIgnoreCase(actionValue)) {
            req.setAttribute("date_pattern", DATE_PATTERN);
            view = USER_CREATE;
        } else if (DELETE.name().equalsIgnoreCase(actionValue)) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            usersDao.deleteUserById(userId);
            req.setAttribute("users", usersDao.getAllUsers());
            req.setAttribute("date_pattern", DATE_PATTERN);
            view = USERS_LIST;
        } else if (EDIT.name().equalsIgnoreCase(actionValue)) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            User user = usersDao.getUserById(userId);
            req.setAttribute("user", user);
            req.setAttribute("date_pattern", DATE_PATTERN);
            view = USER_EDIT;
        } else {
            throw new RuntimeException("Invalid request");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));

        Date date = null;
        try {
            date = new SimpleDateFormat(DATE_PATTERN).parse(req.getParameter("dob"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setDob(date);

        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            usersDao.createUser(user);
        } else {
            user.setId(Integer.parseInt(id.trim()));
            usersDao.editUser(user);
        }
        req.setAttribute("users", usersDao.getAllUsers());
        req.setAttribute("date_pattern", DATE_PATTERN);
        RequestDispatcher dispatcher = req.getRequestDispatcher(USERS_LIST);
        dispatcher.forward(req, resp);
    }
}
