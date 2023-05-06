package ru.osminkin.sqlesson.services;

import org.springframework.stereotype.Service;
import ru.osminkin.sqlesson.model.basicDb.Points;
import ru.osminkin.sqlesson.repository.basicDb.PointsRepository;

import java.util.List;

@Service
public class PointsService {
    private final PointsRepository pointsRepository;

    public PointsService(PointsRepository pointsRepository) {
        this.pointsRepository = pointsRepository;
    }

    public void savePoints(Long userId, Long points){
        pointsRepository.savePoints(userId, points);
    }

    public void updatePoints(Long userId, Long points){
        pointsRepository.updatePoints(userId, points);
    }

    public boolean isUserHasPoints(Long userId){
        return pointsRepository.isUserHasPoints(userId).isPresent();
    }

    public List<Points> findAll(){
        return pointsRepository.findAll();
    }
}
