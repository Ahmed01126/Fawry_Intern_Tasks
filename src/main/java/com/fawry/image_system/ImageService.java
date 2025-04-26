package com.fawry.image_system;

import com.fawry.image_system.exceptions.DuplicateImageNameException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for managing and searching images by name, tags, and date/time
 */
public class ImageService {
    private final Set<Image> images;
    private final Map<String, Image> nameMap;
    private final Map<String, Set<Image>> textMap;
    private final NavigableMap<LocalDateTime, Set<Image>> dateMap;

    public ImageService() {
        this.images = new HashSet<>();
        this.nameMap = new HashMap<>();
        this.textMap = new HashMap<>();
        this.dateMap = new TreeMap<>();
    }

    /**
     * Uploads an image to the service and indexes it for searching
     * @param image The image to upload
     * @throws IllegalArgumentException if an image with the same name already exists
     */
    public void uploadImage(Image image) {
        if (nameMap.containsKey(image.getName())) {
            throw new DuplicateImageNameException("Image name must be unique: " + image.getName());        }

        nameMap.put(image.getName(), image);
        dateMap.computeIfAbsent(image.getDateTime(), k -> new HashSet<>()).add(image);
        image.getTags().forEach(tag ->
                textMap.computeIfAbsent(tag.toLowerCase(), k -> new HashSet<>()).add(image));
        images.add(image);
    }

    /**
     * Searches for images matching the given text (name, tag, or date)
     * @param text Search query (can contain multiple space-separated terms)
     * @return Set of matching images (empty set if no matches found)
     */
    public Set<Image> generalSearch(String text) {
        // deal with null or empty input
        text = text.trim();
        if (text.isEmpty()) {
            return Collections.emptySet();
        }

        // Try exact name match first
        Image exactMatch = nameMap.get(text);
        if (exactMatch != null) {
            return Collections.singleton(exactMatch); // more efficient شوية ^_^
        }

        Set<Image> results = null;
        String[] terms = text.toLowerCase().split("\\s+");

        for (String term : terms) {
            if (term.isEmpty()) continue;

            Set<Image> termMatches = new HashSet<>();

            // Try date parse
            try {
                termMatches.addAll(
                        dateMap.getOrDefault(LocalDateTime.parse(term), Collections.emptySet())
                );
            } catch (Exception ignored) {}

            termMatches.addAll(textMap.getOrDefault(term, Collections.emptySet()));

            // Intersect with previous results
            if (results == null) {
                results = new HashSet<>(termMatches);
            } else {
                results.retainAll(termMatches);
                if (results.isEmpty()) {
                    break;
                }
            }

            /* if I want to add all results to the final set
                try {
                    Set<Image> dateMatches = dateMap.getOrDefault(LocalDateTime.parse(term), Collections.emptySet());
                    results.addAll(dateMatches);
                } catch (Exception ignored) {}

                // Search in tags
                results.addAll(textMap.getOrDefault(term, Collections.emptySet()));
             */
        }

        return results;
    }

    /**
     * Searches for images within a date/time range
     * @param start Start of date range (inclusive)
     * @param end End of date range (inclusive)
     * @return Set of images captured within the specified range
     */
    public Set<Image> searchDateTimeRange(LocalDateTime start, LocalDateTime end) {
        return dateMap.subMap(start, true, end, true).values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    /**
     * Gets all images in the service
     * @return all images
     */
    public Set<Image> getImages() {
        return images;
    }
}