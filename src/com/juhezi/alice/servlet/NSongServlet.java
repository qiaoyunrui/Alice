package com.juhezi.alice.servlet;

import com.juhezi.alice.dao.NSongDao;
import com.juhezi.alice.dao.impl.NSongDaoImpl;
import com.juhezi.alice.domain.NSong;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.sun.javafx.fxml.expression.Expression.add;

/**
 * Created by root on 17-4-26.
 */
public class NSongServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        String method = req.getParameter("method");
        switch (method) {
            case "findAll":
                findAll(req, resp);
                break;
            case "add":
                add(req, resp);
                break;
            case "delete":
                delete(req, resp);
                break;
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        NSongDao dao = new NSongDaoImpl();
        try {
            List<NSong> nsongs = dao.findAll(userid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
