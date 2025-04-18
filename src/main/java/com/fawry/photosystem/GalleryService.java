package com.fawry.photosystem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GalleryService {
    private final Set<Photo> photos = new HashSet<Photo>();

    public void uploadPhoto(Photo photo){
        photos.add(photo);
    }

    public void addTagsToPhoto(Photo photo,HashSet<String> tags){
        for(Photo p : photos){
            if(p.getId().equals(photo.getId())){
                for(String tag : tags){
                    p.getTags().add(tag);
                }
            }
        }
//******* Alternative approach using streams *******//
//        Photo updatedPhoto = photos.stream()
//                .filter(p -> p.getId().equals(photo.getId()))
//                .findFirst()
//                .orElse(null);
//
//        if (updatedPhoto != null) {
//            for(String tag : tags) {
//                updatedPhoto.getTags().add(tag);
//            }
//        }
    }

    public List<Photo> searchPhotosByTags(HashSet<String> tags){
        List<Photo> result = new ArrayList<>();
        for (Photo photo : photos) {
            for (String tag : tags) {
                if (photo.getTags().contains(tag)) { // O(1)
                    result.add(photo);
                    break;
                }
            }
        }
        return result;
    }

    public List<Photo> searchPhotosByDateRange(LocalDate from, LocalDate to){
        List<Photo> result = new ArrayList<>();
        int fromYear = from.getYear();
        int fromMonth = from.getMonthValue();
        int fromDay = from.getDayOfMonth();
        int toYear = to.getYear();
        int toMonth = to.getMonthValue();
        int toDay = to.getDayOfMonth();
        if (fromYear > toYear
                || (fromYear == toYear && fromMonth > toMonth)
                || (fromYear == toYear && fromMonth == toMonth && fromDay > toDay)) {
            throw new IllegalArgumentException("Invalid date range");
        }
        for (Photo photo : photos) {
            int photoYear = photo.getDate().getYear();
            int photoMonth = photo.getDate().getMonthValue();
            int photoDay = photo.getDate().getDayOfMonth();
            if (photoYear < fromYear || photoYear > toYear) {
                continue;
            }
            if ((photoYear == fromYear && photoMonth < fromMonth)
                    || (photoYear == toYear && photoMonth > toMonth)) {
                continue;
            }
            if ((photoYear == fromYear && photoMonth == fromMonth && photoDay < fromDay)
                    || (photoYear == toYear && photoMonth == toMonth && photoDay > toDay)) {
                continue;
            }

            result.add(photo);
        }

        return result;
    }

    public List<Photo> searchPhotosByTimeRange(LocalTime  from, LocalTime to){
        List<Photo> result = new ArrayList<>();
        int fromHour = from.getHour();
        int fromMinute = from.getMinute();
        int toHour = to.getHour();
        int toMinute = to.getMinute();
        if (fromHour > toHour
                || (fromHour == toHour && fromMinute > toMinute)) {
            throw new IllegalArgumentException("Invalid time range");
        }
        for (Photo photo : photos) {
            int photoHour = photo.getTime().getHour();
            int photoMinute = photo.getTime().getMinute();
            if (photoHour < fromHour || photoHour > toHour) {
                continue;
            }
            if ((photoHour == fromHour && photoMinute < fromMinute)
                    || (photoHour == toHour && photoMinute > toMinute)) {
                continue;
            }

            result.add(photo);
        }

        return result;
    }

    public List<Photo> searchPhotosByNameLocation(String name){
        List<Photo> result = new ArrayList<>();
        for (Photo photo : photos) {
            if (photo.getLocation().getName().equalsIgnoreCase(name)) {
                result.add(photo);
            }
        }
        return result;

    }

    public List<Photo> searchPhotosByLatLngLocation(double fromLat, double fromLng, double toLat, double toLng){
        List<Photo> result = new ArrayList<>();
        for(Photo photo : photos){
            double myLat = photo.getLocation().getLat();
            double myLng = photo.getLocation().getLng();
            if(myLat >= fromLat && myLat <= toLat && myLng >= fromLng && myLng <= toLng){
                result.add(photo);
            }
        }
        return result;

    }

    public Set<Photo> getPhotos(){
        return photos;
    }
}
