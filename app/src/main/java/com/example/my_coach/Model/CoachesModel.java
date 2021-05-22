package com.example.my_coach.Model;

public class CoachesModel {
    private String coach_id ;
    private String id ;
    private String coach_image ;
    private String coach_name ;
    private String  sport_id;
    private String  sport_name;
    private int  sport_price;

    public CoachesModel() {
    }

    public CoachesModel(String coach_id, String id, String coach_image, String coach_name, String sport_id, String sport_name, int sport_price) {
        this.coach_id = coach_id;
        this.id = id;
        this.coach_image = coach_image;
        this.coach_name = coach_name;
        this.sport_id = sport_id;
        this.sport_name = sport_name;
        this.sport_price = sport_price;
    }

    public String getCoach_id() {
        return coach_id;
    }

    public void setCoach_id(String coach_id) {
        this.coach_id = coach_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoach_image() {
        return coach_image;
    }

    public void setCoach_image(String coach_image) {
        this.coach_image = coach_image;
    }

    public String getCoach_name() {
        return coach_name;
    }

    public void setCoach_name(String coach_name) {
        this.coach_name = coach_name;
    }

    public String getSport_id() {
        return sport_id;
    }

    public void setSport_id(String sport_id) {
        this.sport_id = sport_id;
    }

    public String getSport_name() {
        return sport_name;
    }

    public void setSport_name(String sport_name) {
        this.sport_name = sport_name;
    }

    public int getSport_price() {
        return sport_price;
    }

    public void setSport_price(int sport_price) {
        this.sport_price = sport_price;
    }
}
