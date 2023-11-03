package com.example.mangagementhike;

import java.io.Serializable;

public class Hike implements Serializable {
    private int id;
    private String name;
    private String location;
    private String date_of_hike;
    private String parking_available;
    private String length_the_hike;
    private String level_of_difficulty;
    private String description;
    private String vehicle;

    public Hike() {
    }

    public Hike(int id, String name, String location, String date_of_hike, String parking_available, String length_the_hike, String level_of_difficulty, String description, String vehicle) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date_of_hike = date_of_hike;
        this.parking_available = parking_available;
        this.length_the_hike = length_the_hike;
        this.level_of_difficulty = level_of_difficulty;
        this.description = description;
        this.vehicle = vehicle;
    }

    public Hike(String name, String location, String date_of_hike, String parking_available, String length_the_hike, String level_of_difficulty, String description, String vehicle) {
        this.name = name;
        this.location = location;
        this.date_of_hike = date_of_hike;
        this.parking_available = parking_available;
        this.length_the_hike = length_the_hike;
        this.level_of_difficulty = level_of_difficulty;
        this.description = description;
        this.vehicle = vehicle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate_of_hike() {
        return date_of_hike;
    }

    public void setDate_of_hike(String date_of_hike) {
        this.date_of_hike = date_of_hike;
    }

    public String getParking_available() {
        return parking_available;
    }

    public void setParking_available(String parking_available) {
        this.parking_available = parking_available;
    }

    public String getLength_the_hike() {
        return length_the_hike;
    }

    public void setLength_the_hike(String length_the_hike) {
        this.length_the_hike = length_the_hike;
    }

    public String getLevel_of_difficulty() {
        return level_of_difficulty;
    }

    public void setLevel_of_difficulty(String level_of_difficulty) {
        this.level_of_difficulty = level_of_difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }
}
