package ru.osminkin.sqlesson.services;

import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import ru.osminkin.sqlesson.repository.RecordRepository;

import javax.persistence.*;
import java.util.*;
import java.util.logging.Logger;

@Service
public class QueryService {

    @PersistenceContext
    private EntityManager entityManager;
    RecordRepository repository;

    QueryService(RecordRepository repository){
        this.repository = repository;
    }

    public List<Map<String, Object>> getRecordByRoll(String sqlQuery) {
        try {
            System.out.println("start");
            Query query = entityManager.createNativeQuery(sqlQuery);
            NativeQuery nativeQuery = (NativeQuery) query;
            nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            return (List<Map<String, Object>>) nativeQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
