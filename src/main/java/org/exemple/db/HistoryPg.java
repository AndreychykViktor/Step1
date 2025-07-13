package org.exemple.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryPg implements HistoryDAO {
    private final Connection conn;

    public HistoryPg(Connection conn) {
        this.conn = conn;
    }

    public List<HistoryDbRow> getAll() {
        List<HistoryDbRow> result = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement("SELECT id, coice, src, dst, date, mail FROM history")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new HistoryDbRow(
                        rs.getInt("id"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getInt("src"),
                        rs.getInt("dst"),
                        rs.getInt("coice"),
                        rs.getString("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void put(HistoryDbRow r) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("insert into history (src, dstv, choice, date, user_id) VALUES (?,?,?,?,?)");
            st.setInt(1, r.src);
            st.setInt(2, r.dst);
            st.setInt(3, r.choice);
            st.setTimestamp(4, Timestamp.valueOf(r.date));
            st.setString(5, r.mail);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HistoryDbRow> get(String mail) {
        List<HistoryDbRow> result = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement("SELECT id, choice, date, mail FROM history WHERE mail = ? order by date desc")) {
            st.setString(1, mail);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(new HistoryDbRow(
                        rs.getInt("id"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getInt("src"),
                        rs.getInt("dst"),
                        rs.getInt("coice"),
                        rs.getString("mail")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}