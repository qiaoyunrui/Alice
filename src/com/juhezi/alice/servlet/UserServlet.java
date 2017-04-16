package com.juhezi.alice.servlet;

import com.juhezi.alice.base.PageRoll;
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
            case "list":
                list(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) {
        String currPage = req.getParameter("currPage");
        PageRoll pageRoll = new PageRoll();
        if (currPage != null) {
            pageRoll.setCurrPage(Integer.parseInt(currPage));
        }
        UserDao dao = new UserDaoImpl();
        try {
            List<User> list = dao.list(pageRoll);
            req.setAttribute("list", list);
            req.setAttribute("pageRoll", pageRoll);
            req.getRequestDispatcher("users/list.jsp").forward(req, resp);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        UserDao userDao = new UserDaoImpl();
        try {
            userDao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/UserServlet?method=list");
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
                resp.sendRedirect(request.getContextPath() + "/UserServlet?method=list");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String pickname = req.getParameter("pickname");
        User user = new User();
        user.setId(id).setUsername(username)
                .setPassword(password).setPickname(pickname);
        UserDao userDao = new UserDaoImpl();
        try {
            userDao.update(user);
            resp.sendRedirect(req.getContextPath() + "/UserServlet?method=list");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void find(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        UserDao userDao = new UserDaoImpl();
        try {
            User user = userDao.findById(id);
            req.setAttribute("user", user);
            req.getRequestDispatcher("users/edit.jsp").forward(req, resp);
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
