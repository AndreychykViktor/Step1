package org.exemple.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class HistoryDbRow {

    final Integer id;
    final LocalDateTime date;
    final int src;
    final int dst;
    final int choice;
    final String mail;

    public HistoryDbRow(Integer id, LocalDateTime date, int src, int dst, int choice, String mail) {
        this.id = id;
        this.date = date;
        this.src = src;
        this.dst = dst;
        this.choice = choice;
        this.mail = mail;
    }

    public static HistoryDbRow fromResultSet(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        LocalDateTime date = rs.getTimestamp("dt").toLocalDateTime();
        int src = rs.getInt("src");
        int dst = rs.getInt("dst");
        int choice = rs.getInt("choice");
        String mail = rs.getString("mail");

        return new HistoryDbRow(id, date, src, dst, choice, mail);
    }

    @Override
    public String toString() {
        return "HistoryDbRow{id=%d, date=%s, a=%d, b=%d, choice=%d, mail=%s}".formatted(id, date, src, dst, choice, mail);
    }
}