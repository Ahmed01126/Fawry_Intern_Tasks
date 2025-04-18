package com.fawry.photosystem;

import com.github.f4b6a3.ulid.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GalleryService galleryService = new GalleryService();
        HashSet<String> tags = new HashSet<>();
        tags.add("sunset");
        tags.add("beach");
        tags.add("vacation");
        tags.add("holiday");
        tags.add("family");
        tags.add("friends");
        tags.add("nature");
        tags.add("adventure");


        Ulid ulid = UlidCreator.getUlid();
        Photo photo1 = new Photo.Builder()
                .id(ulid)
                .title("image1")
                .tags(tags)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .location(new Location("Beach", 34.0522, 118.2437))
                .create();

        galleryService.uploadPhoto(photo1);
        System.out.println("Photo uploaded: \n" + photo1);
        HashSet<String> newTags = new HashSet<>();
        newTags.add("sunrise");
        newTags.add("mountain");
        newTags.add("hiking");
        galleryService.addTagsToPhoto(photo1,newTags);
        System.out.println("\nTags added to photo: \n" + photo1);

        Photo photo2 = new Photo.Builder()
                .id(UlidCreator.getUlid())
                .title("image2")
                .tags(newTags)
                .date(LocalDate.now())
                .time(LocalTime.now())
                .location(new Location("Mountain", 40.7128, 74.0060))
                .create();
        galleryService.uploadPhoto(photo2);
        List<Photo> photosByTags = galleryService.searchPhotosByTags(newTags);
        System.out.println("\nPhotos found by tags: ");
        for (Photo photo : photosByTags) {
            System.out.println(photo);
        }

        List<Photo> photosByDateRange =
                galleryService.searchPhotosByDateRange(LocalDate.now().minusDays(1), LocalDate.now());
        System.out.println("\nPhotos found by date range: ");
        for (Photo photo : photosByDateRange) {
            System.out.println(photo);
        }

        List<Photo> photosByLocation =
                galleryService.searchPhotosByNameLocation("Muntain");
        System.out.println("\nPhotos found by location: ");
        for (Photo photo : photosByLocation) {
            System.out.println(photo);
        }




    }
}
