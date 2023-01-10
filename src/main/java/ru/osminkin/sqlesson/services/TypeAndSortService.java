package ru.osminkin.sqlesson.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.Objects;

@Service
public class TypeAndSortService {
    private final PhotoService photoService;

    public TypeAndSortService(PhotoService photoService) {
        this.photoService = photoService;
    }

    public void getPhotosPage(String search, String sort, Model model, Long userId){
        if (userId == null){
            if (Objects.equals(sort, "new")){
                if (search != null){
                    model.addAttribute("photos", photoService.findAllPhotosOrderByDate(search.toLowerCase()));
                } else model.addAttribute("photos", photoService.findAllPhotosOrderByDate());
            }

            else if (Objects.equals(sort, "old")) {
                if (search != null){
                    model.addAttribute("photos", photoService.findAllPhotosOrderByDateDesc(search.toLowerCase()));
                } else model.addAttribute("photos", photoService.findAllPhotosOrderByDateDesc());
            }
            else {
                if (search != null){
                    model.addAttribute("photos", photoService.findAllPhotosOrderByDateDesc(search.toLowerCase()));
                } else model.addAttribute("photos", photoService.findAllPhotos());
            }
        }
        else {
            if (Objects.equals(sort, "new")){
                if (search != null){
                    model.addAttribute("photos", photoService.findAllUserPhotosOrderByDateDesc(userId, search.toLowerCase()));
                } else model.addAttribute("photos", photoService.findAllUserPhotosOrderByDateDesc(userId));
            }
            else if (Objects.equals(sort, "old")){
                if (search != null){
                    model.addAttribute("photos", photoService.findAllUserPhotosOrderByDate(userId, search.toLowerCase()));
                } else model.addAttribute("photos", photoService.findAllUserPhotosOrderByDate(userId));
            } else {
                if (search != null){
                    model.addAttribute("photos", photoService.findAllPhotosByUserId(userId, search.toLowerCase()));
                } else model.addAttribute("photos", photoService.findAllPhotosByUserId(userId));
            }
        }
    }
}
