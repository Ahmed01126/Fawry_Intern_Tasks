package com.fawry.image_system;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ImageService imageService = new ImageService();

        LocalDateTime now = LocalDateTime.now();
        Image beachImage = new Image("beach_sunset",
                new HashSet<>(Set.of("vacation", "summer", "night", "nature")),
                now.minusMinutes(30));

        Image mountainImage = new Image("mountain_view",
                new HashSet<>(Set.of("nature", "landscape", "vacation")),
                now.plusDays(1));

        Image cityImage = new Image("city_night",
                new HashSet<>(Set.of("vacation", "night", "landscape")),
                now);

        Image egyptImage = new Image("egypt",
                new HashSet<>(Set.of("pyramids", "zoo", "nature")),
                now.minusHours(3));

        // Test 0: Upload images
        System.out.println("=== Uploading Images ===");
        imageService.uploadImage(beachImage);
        imageService.uploadImage(mountainImage);
        imageService.uploadImage(cityImage);
        imageService.uploadImage(egyptImage);
        System.out.println("Uploaded 4 images");
        System.out.println("All images: " + imageService.getImages().size());
        System.out.println();

        // Test 1: Print all images
        System.out.println("=== Print All Images ===");
        System.out.println(imageService.getImages());
        System.out.println();

        // Test 2: Exact name search
        System.out.println("=== Exact Name Search ===");
        Set<Image> nameResults = imageService.generalSearch("egypt");
        System.out.println("Name search results: \"egypt\" => " + nameResults.size());
        nameResults.forEach(img -> System.out.println(" - " + img.getName()));
        System.out.println();

        // Test 3: Tag search
        System.out.println("=== Tag Search ===");
        Set<Image> tagResults = imageService.generalSearch("vacation");
        System.out.println("Tag search results: \"vacation\" => " + tagResults.size());
        tagResults.forEach(img -> System.out.println(" - " + img.getName()));
        System.out.println();

        // Test 4: Multiple term search
        System.out.println("=== Multi-Term Search ===");
        Set<Image> multiTermResults = imageService.generalSearch("vacation night");
        System.out.println("Multi-term search results: \"vacation night\" =>" + multiTermResults.size());
        multiTermResults.forEach(img -> System.out.println(" - " + img.getName()));
        System.out.println();

        // Test 5: Date search
        System.out.println("=== Date Search ===");
        String dateString = now.minusDays(1).toString();
        Set<Image> dateResults = imageService.generalSearch(dateString);
        System.out.println("Date search results: " + dateString + " => " + dateResults.size());
        dateResults.forEach(img -> System.out.println(" - " + img.getName()));
        System.out.println();

        // Test 6: Date range search
        System.out.println("=== Date Range Search ===");
        Set<Image> rangeResults = imageService.searchDateTimeRange(
                now.minusDays(3),
                now.plusHours(3)
        );
        System.out.println("Date range results: from " + now.minusDays(3) + " to " + now.plusHours(3) +
                " => " + rangeResults.size());
        rangeResults.forEach(img -> System.out.println(" - " + img.getName() + " (" + img.getDateTime() + ")"));
        System.out.println();

        // Test 7: Empty search
        System.out.println("=== Empty Search ===");
        Set<Image> emptyResults = imageService.generalSearch("");
        System.out.println("Empty search results: " + emptyResults.size());
        System.out.println();

        // Test 8: Non-existent search
        System.out.println("=== Non-Existent Search ===");
        Set<Image> noResults = imageService.generalSearch("non-existent");
        System.out.println("Non-existent search results: " + noResults.size());
    }
}