package com.fawry.photo_system;

import com.github.f4b6a3.ulid.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        com.fawry.photo_system.ImageService galleryService = new com.fawry.photo_system.ImageService();
        HashSet<String> tags = new HashSet<>();
        tags.add("sunset");
        tags.add("beach");
        tags.add("vacation");
        tags.add("holiday");
        tags.add("family");
        tags.add("friends");
        tags.add("nature");
        tags.add("adventure");


        Ulid id = Ulid.fast();





    }
}
