package ru.osminkin.sqlesson.services;

import org.springframework.stereotype.Service;
import ru.osminkin.sqlesson.model.Record;
import ru.osminkin.sqlesson.model.User;
import ru.osminkin.sqlesson.repository.RecordRepository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RecordService {
    private final RecordRepository recordsRepository;

    public RecordService(RecordRepository recordsRepository) {
        this.recordsRepository = recordsRepository;
    }

    public void deleteRecordById(Long id){
        recordsRepository.deleteById(id);
    }

    public void saveComment(User authUser, Record record) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //record.setAddDate(Timestamp.valueOf(dateFormat.format(GregorianCalendar.getInstance().getTime())));
        record.setUser(authUser);
        recordsRepository.save(record);
    }

    public Iterable<Record> findAllCommentsByNewVideoId(Long videoId){
        return recordsRepository.findAllCommentsByNewVideoId(videoId);
    }

    public Iterable<Record> findAllCommentsByOldVideoId(Long videoId){
        return recordsRepository.findAllCommentsByOldVideoId(videoId);
    }

    public void deleteByVideoId(Long id){
        recordsRepository.deleteByVideoId(id);
    }
}
