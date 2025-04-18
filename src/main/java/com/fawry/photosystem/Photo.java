package com.fawry.photosystem;

import com.github.f4b6a3.ulid.Ulid;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;

public class Photo {
    private final Ulid id;
    private final String title;
    private HashSet<String> tags;
    private final LocalDate date;
    private final LocalTime time;
    private final Location location;

    private Photo(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.tags = builder.tags;
        this.date = builder.date;
        this.time = builder.time;
        this.location = builder.location;
    }

    public Ulid getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) && Objects.equals(title, photo.title)
                && Objects.equals(tags, photo.tags) && Objects.equals(date, photo.date)
                && Objects.equals(time, photo.time) && Objects.equals(location, photo.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, tags, date, time, location);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", \ntitle='" + title + '\'' +
                ", \ntags=" + tags +
                ", \ndate=" + date +
                ", \ntime=" + time +
                ", \nlocation=" + location +
                '}';
    }

    public static class Builder {
        private Ulid id;
        private String title;
        private HashSet<String> tags;
        private LocalDate date;
        private LocalTime time;
        private Location location;

        public Builder id(Ulid id){
            this.id = id;
            return this;
        }
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder tags(HashSet<String> tags){
            this.tags = tags;
            return this;
        }

        public Builder date(LocalDate date){
            this.date = date;
            return this;
        }

        public Builder time(LocalTime time){
            this.time = time;
            return this;
        }

        public Builder location(Location location){
            this.location = location;
            return this;
        }

        public Photo create(){
            return new Photo(this);
        }
    }


}
