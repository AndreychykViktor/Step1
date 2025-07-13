package org.exemple.db;

import java.util.List;

public interface HistoryDAO {

    void put(HistoryDbRow r);
    List<HistoryDbRow> get(String user_id);
    List<HistoryDbRow> getAll();

}
