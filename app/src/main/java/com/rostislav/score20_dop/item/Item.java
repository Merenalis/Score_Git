package com.rostislav.score20_dop.item;


public class Item {

    private String team1,team2,score,date,tour; // название
    private int logoResource1,logoResource2; // ресурс флага
    public Item(String team1, String team2, String score, String date, String tour) {
        this.team1 = team1;
        this.team2 = team2;
        this.score = score;
        this.date = date;
        this.tour = tour;

    }

public Item(){}



    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }


}

