package com.fawry.image_system;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImageService {
    private final Set<Image> images;

    public ImageService() {
        this.images = new HashSet<>();
    }

    public void uploadImage(Image image) {
        images.add(image);
    }

    public List<Image> search(String text){
        List<Image> result = new ArrayList<>();
        
        return result;
    }
}
