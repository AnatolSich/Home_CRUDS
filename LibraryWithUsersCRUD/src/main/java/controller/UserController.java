package controller;

import dao.UserDao;
import model.User;
import util.ActionOperations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserController extends HttpServlet {

    private final UserDao dao;

    private static final String INSERT_OR_EDIT = "/editUser.jsp";
    private static final String LIST_USER = "/listUsers.jsp";

    public UserController() {
        this.dao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (ActionOperations.DELETE.name().equalsIgnoreCase(action)) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            dao.deleteUser(userId);
            forward = LIST_USER;
            req.setAttribute("users", dao.getAllUsers());
        } else if (ActionOperations.EDIT.name().equalsIgnoreCase(action)) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            User user = dao.getUserById(userId);
            req.setAttribute("user", user);
            forward = INSERT_OR_EDIT;
        } else if (ActionOperations.LIST.name().equalsIgnoreCase(action)) {
            req.setAttribute("users", dao.getAllUsers());
            forward = LIST_USER;
        } else if (ActionOperations.CREATE.name().equalsIgnoreCase(action)) {
            forward = INSERT_OR_EDIT;
        } else {
            throw new RuntimeException("Invalid action");
        }

        RequestDispatcher rd = req.getRequestDispatcher(forward);
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setAddress(req.getParameter("address"));
        user.setTel(req.getParameter("tel"));
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(req.getParameter("dob"));
            user.setDob(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(req.getParameter("email"));
        String id = req.getParameter("userId");
        if (id == null || id.isEmpty()) {
            dao.addUser(user);
        } else {
            user.setId(Integer.parseInt(id));
            dao.editUser(user);
        }

        req.setAttribute("users", dao.getAllUsers());
        req.getRequestDispatcher(LIST_USER).forward(req, resp);
    }
}
