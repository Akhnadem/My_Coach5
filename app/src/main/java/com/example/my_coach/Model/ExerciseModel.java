package com.example.my_coach.Model;

public class ExerciseModel {
    private String exercise_name;
    private String id;
    private String image;
    private String sport_id;

    public ExerciseModel(String exercise_name, String id, String image, String sport_id) {
        this.exercise_name = exercise_name;
        this.id = id;
        this.image = image;
        this.sport_id = sport_id;
    }

    public ExerciseModel() {
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
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
