//package com.fawry.photo_system;
//
//import java.util.Objects;
//
//public class Location {
//    private final String name;
//    private final double lat;
//    private final double lng;
//
//    public Location(String name, double lat, double lng) {
//        this.name = name;
//        this.lat = lat;
//        this.lng = lng;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public double getLat() {
//        return lat;
//    }
//
//    public double getLng() {
//        return lng;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Location location = (Location) o;
//        return Double.compare(lat, location.lat) == 0 && Double.compare(lng, location.lng) == 0 && Objects.equals(name, location.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, lat, lng);
//    }
//
//    @Override
//    public String toString() {
//        return "Location{" +
//                "name='" + name + '\'' +
//                ", \nlat=" + lat +
//                ", \nlng=" + lng +
//                '}';
//    }
//}
