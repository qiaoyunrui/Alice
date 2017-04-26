package com.juhezi.alice.servlet;

import com.juhezi.alice.dao.SongDao;
import com.juhezi.alice.dao.impl.SongDaoImpl;
import com.juhezi.alice.domain.Song;
import com.juhezi.alice.util.JSON;
import com.juhezi.alice.util.JSONable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juhezi on 2017/4/25.
 */
public class SongServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        String method = request.getParameter("method");
        switch (method) {
            case "findById":
                findById(request, response);
                break;
            case "findByName":
                findByName(request, response);
                break;
        }
    }

    private void findByName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        SongDao dao = new SongDaoImpl();
        try {
            List<Song> songs = dao.findByName(name);
            response.getWriter().print(JSON.toJSON(new ArrayList<>(songs)));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private void findById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        SongDao dao = new SongDaoImpl();
        try {
            Song song = dao.findById(id);
            response.getWriter().print(song.toJSON());
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
