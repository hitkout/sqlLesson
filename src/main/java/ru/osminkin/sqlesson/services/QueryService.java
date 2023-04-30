package ru.osminkin.sqlesson.services;

import org.springframework.stereotype.Service;
import ru.osminkin.sqlesson.constants.SqlConstants;
import ru.osminkin.sqlesson.repository.basicDb.RecordRepository;

import java.sql.*;
import java.util.*;

@Service
public class QueryService {
    private RecordRepository repository;
    private final String urlDb = "jdbc:postgresql://localhost:5432/testDb";
    private final String usr = "postgres";
    private final String pas = "geronymo1337";

    QueryService(RecordRepository repository){
        this.repository = repository;
    }

    public List<Map<String, Object>> getRecordByRoll(String sqlQuery, boolean isChangeQuery) {
        String upperCaseQuery = sqlQuery.toUpperCase();
        if (isChangeQuery
                || (!upperCaseQuery.contains(SqlConstants.DELETE)
                && !upperCaseQuery.contains(SqlConstants.INSERT))) {
            try (Connection con = DriverManager.getConnection(urlDb, usr, pas)) {
                ResultSet rs = con.createStatement().executeQuery(sqlQuery);
                ResultSetMetaData md = rs.getMetaData();
                int columns = md.getColumnCount();
                List<Map<String, Object>> results = new ArrayList<>();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columns; i++) {
                        row.put(md.getColumnLabel(i).toUpperCase(), rs.getObject(i));
                    }
                    results.add(row);
                }
                return results;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();

//        try {
//            System.out.println("start");
//            Query query = entityManager.createNativeQuery(sqlQuery);
//            NativeQuery nativeQuery = (NativeQuery) query;
//            nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
//            return (List<Map<String, Object>>) nativeQuery.getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
    }
}
