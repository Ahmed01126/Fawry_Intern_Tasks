package com.fawry.image_system;

import com.github.f4b6a3.ulid.Ulid;

import java.util.HashSet;
import java.util.Objects;

public class Image {
    private final String name;
    private HashSet<String> tags;

    public Image(String name, HashSet<String> tags) {
        this.name = name;
        this.tags = tags;
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
        return "Image{ \n" +
                "name = " + name + ", \n" +
                "tags = " + tags +
                "\n}";
    }
}
