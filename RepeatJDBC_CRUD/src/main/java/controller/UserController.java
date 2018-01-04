package controller;

import dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static util.ActionEnum.*;

public class UserController extends HttpServlet {

    private static final String INSERT_OR_EDIT = "/editUser.jsp";
    private static final String USER_LIST = "/listUser.jsp";
    private UserDao userDao;


    public UserController() {
        this.userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String view = "";

        if (DELETE.name().equalsIgnoreCase(action)) {
            int userId = Integer.valueOf(req.getParameter("userID"));
            userDao.deleteUser(userId);
            view = USER_LIST;
            req.setAttribute("users", userDao.getAllUsers());

        } else if (EDIT.name().equalsIgnoreCase(action)) {
            int userID = Integer.valueOf(req.getParameter("userID"));
            req.setAttribute("user", userDao.getUserById(userID));
            view = INSERT_OR_EDIT;

        } else if (LIST.name().equalsIgnoreCase(action)) {
            req.setAttribute("users", userDao.getAllUsers());
            view = USER_LIST;

        } else if (CREATE.name().equalsIgnoreCase(action)) {
            view = INSERT_OR_EDIT;
        } else {
            throw new RuntimeException("Invalid action");
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(view);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
