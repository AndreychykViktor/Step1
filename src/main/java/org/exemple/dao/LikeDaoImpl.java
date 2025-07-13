package org.exemple.dao;

import org.exemple.model.Like;

import java.sql.*;
import java.util.HashSet;
import java.util.Collection;

public class LikeDaoImpl implements LikeDao {
    private static final String URL = "jdbc:postgresql://localhost:5434/postgres";
    private static final String USER = "postgres777";
    private static final String PASS = "pg123456";

    @Override
    public Like create(int clientId, int userId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO \"like\" (client_id, user_id) VALUES (?, ?) RETURNING id"
            );
            ps.setInt(1, clientId);
            ps.setInt(2, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                return new Like(id, clientId, userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean exists(int clientId, int userId) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT 1 FROM \"like\" WHERE user_id = ? AND client_id= ?"
            );
            ps.setInt(1, clientId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Collection<Integer> findAllUsersIdById(int clientId) {
        var result = new HashSet<Integer>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT user_id FROM \"like\" WHERE client_id = ?"
            );
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Collection<Integer> findAllMutualLikes(int clientId) {
        var result = new HashSet<Integer>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT l1.user_id " +
                    "FROM \"like\" l1 " +
                    "JOIN \"like\" l2 ON l1.user_id = l2.client_id AND l1.client_id = l2.user_id " +
                    "WHERE l1.client_id = ?"
            );
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}