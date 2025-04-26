package com.fawry.image_system;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ImageService {
    private final Set<Image> images;
    private final Map<String , Image> nameMap;
    private final Map<String , Set<Image>> textMap;
    private final NavigableMap<LocalDateTime, Set<Image>> dateMap;

    public ImageService() {
        this.nameMap = new HashMap<>();
        this.images = new HashSet<>();
        this.textMap = new HashMap<>();
        this.dateMap = new TreeMap<>();
    }

    public void uploadImage(Image image) {
        nameMap.put(image.getName(), image);
        dateMap.computeIfAbsent(image.getDateTime(), k -> new HashSet<>()).add(image);

        image.getTags()
                .forEach(tag -> textMap.computeIfAbsent(tag, k -> new HashSet<>()).add(image));

        images.add(image);
    }

    public List<Image> generalSearch(String text){ // name or tag or date
        text = text.trim();
        if (text.isEmpty()) {
            return Collections.emptyList();
        }
        // Try exact name match
        Image exactMatchToName = nameMap.getOrDefault(text, null);
        if (exactMatchToName != null) {
            return Collections.singletonList(exactMatchToName);
            // Creates an immutable list containing exactly one element and more efficient
        }

        // Try exact date or tag match
        List<Image> result = new ArrayList<>();
        String[] textParts = text.toLowerCase().split("\\s+");
        for (String part : textParts) {;
            Set<Image> dateMatches = dateMap.getOrDefault(LocalDateTime.parse(part), Collections.emptySet());
            Set<Image> matches = textMap.getOrDefault(part, Collections.emptySet());
            if(!dateMatches.isEmpty())
                result.addAll(dateMatches);
            if (!matches.isEmpty()) {
                result.addAll(matches);
            }
        }
        return result;
    }

    public List<Image> searchDateTimeRange(LocalDateTime start, LocalDateTime end) {
        return dateMap.subMap(start, true, end, true).values().stream()
                .flatMap(Set::stream)
                .collect(Collectors.toList());
    }

    public Set<Image> getImages() {
        return images;
    }
}
