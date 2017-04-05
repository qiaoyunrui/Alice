package com.juhezi.alice.servlet;

import com.juhezi.alice.dao.UserDao;
import com.juhezi.alice.dao.impl.UserDaoImpl;
import com.juhezi.alice.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Juhezi on 2017/4/5.
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch (method) {
            case "findAll":
                findAll(req, resp);
                break;
            case "find":
                find(req, resp);
                break;
            case "update":
                update(req, resp);
                break;
            case "add":
                add(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        UserDao userDao = new UserDaoImpl();
        try {
            userDao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/UserServlet?method=findAll");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse resp) {
        UserDao userDao = new UserDaoImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String pickname = request.getParameter("pickname");
        if (username != null && password != null && pickname != null) {
            User user = new User();
            user.setUsername(username)
                    .setPickname(pickname)
                    .setPassword(password)
                    .setId(UUID.randomUUID().toString());
            try {
                userDao.add(user);
                resp.sendRedirect(request.getContextPath() + "/UserServlet?method=findAll");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void find(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        UserDao userDao = new UserDaoImpl();
        try {
            User user = userDao.findById(id);
            req.setAttribute("user", user);
            req.getRequestDispatcher("users/update_user.jsp").forward(req, resp);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void findAll(HttpServletRequest req, HttpServletResponse resp) {

        UserDao dao = new UserDaoImpl();
        try {
            List<User> list = dao.findAll();
            req.setAttribute("list", list);
            req.getRequestDispatcher("users/list.jsp").forward(req, resp);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
