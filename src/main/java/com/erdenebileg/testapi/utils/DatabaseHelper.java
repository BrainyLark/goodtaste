package com.erdenebileg.testapi.utils;

import com.erdenebileg.testapi.Entity.User;

import java.sql.*;

public class DatabaseHelper {
    Connection conn;
    Statement stmt;

    public DatabaseHelper() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/badtaste", "postgres", "1234");
            stmt = conn.createStatement();
        } catch (SQLException | ClassNotFoundException e) {

        }
    }

    public ResultSet getMoviesByName(String title) {
        String query = "select tb1.movieid, tb1.title, tb1.genres, u.url \n" +
                "from (select m.*, l.tmdbid from movies m, links l where lower(title) like lower('%"+ title +"%') and l.movieid = m.movieid) as tb1, url u\n" +
                "where u.id = tb1.tmdbid;";
        try {
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addUser(User user) {
        String qry = "insert into users(uname, passwd, email) values('" + user.getUname() + "', '" + user.getPasswd() + "', '" + user.getEmail() + "')";
        try {
            stmt.executeUpdate(qry);
            return true;
        } catch (SQLException e) {

        }
        return false;
    }

    public int getUser(String uname, String passwd) {
        String qry = "select * from users where uname = '" + uname + "' and passwd = '" + passwd + "'";
        ResultSet rs = null;
        int row = 0;
        try {
            rs = stmt.executeQuery(qry);
            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {

        }
        return -1;
    }

    public ResultSet getMovieData(long movieid) {
        String qry = "select tb1.url, m.* from movies m, (select url from url where id = (select links.tmdbid from links where movieid = " + movieid + " limit 1)) as tb1 where m.movieid = " + movieid + " limit 1";
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(qry);
        } catch (SQLException e) {

        }
        return rs;
    }
}
