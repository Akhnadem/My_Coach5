package com.example.my_coach.Model;

public class ExerciseModel {
    private String name;
    private String id;
    private String image;
    private String sport_id;


    public ExerciseModel() {
    }

    public ExerciseModel(String name, String id, String image, String sport_id) {
        this.name = name;
        this.id = id;
        this.image = image;
        this.sport_id = sport_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSport_id() {
        return sport_id;
    }

    public void setSport_id(String sport_id) {
        this.sport_id = sport_id;
    }
}
