package com.fawry.image_system;

import com.github.f4b6a3.ulid.Ulid;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;

public class Image {
    private final String name;
    private HashSet<String> tags;
    private final LocalDateTime dateTime;

    public Image(String name, HashSet<String> tags, LocalDateTime dateTime) {
        this.name = name;
        this.tags = tags;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(name, image.name) && Objects.equals(tags, image.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tags);
    }

    @Override
    public String toString() {
        return "\nImage{" +
                "\nname='" + name + '\'' +
                "\ntags=" + tags +
                "\ndateTime=" + dateTime +
                "\n}";
    }
}
