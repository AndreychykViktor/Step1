package org.exemple.dao;

import org.exemple.model.User;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;

public class UserDaoImpl implements UserDao {
    private static final String URL = "jdbc:postgresql://localhost:5434/postgres";
    private static final String USER = "postgres777";
    private static final String PASS = "pg123456";

    @Override
    public User findByLogin(String login) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_auth WHERE login = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("mail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User create(String login, String password, String mail, String name, Integer age, String bio) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO user_auth (login, age, bio, name, password, mail) VALUES (?, ?, ?, ?, ?, ?) RETURNING id");
            ps.setString(1, login);
            ps.setInt(2, age);
            ps.setString(3, bio);
            ps.setString(4, name);
            ps.setString(5, password);
            ps.setString(6, mail);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                return new User(id, name, age, bio, login, password, mail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public User findByMail(String mail) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_auth WHERE mail = ?");
            ps.setString(1, mail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("mail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean existsByLogin(String login) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM user_auth WHERE login = ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findById(int id) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM user_auth WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("login"), rs.getString("password"), rs.getString("mail"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Integer> findAllIds() {
        var result = new HashSet<Integer>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement("SELECT id FROM user_auth");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               result.add(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}