package com.csgame.api.csgameapi.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VolunteerUser extends User {

    @JsonProperty("name")
    private String name;
    @JsonProperty("currentPoints") 
    private int currentPoints;
    @JsonProperty("level") 
    private double level;
    @JsonProperty("claimedDiscounts") 
    private ArrayList<Discount> claimedDiscounts;
    @JsonProperty("eventsJoined")
    private ArrayList<Event> eventsJoined; 

<<<<<<< HEAD
    public VolunteerUser(@JsonProperty("UID")String UID, @JsonProperty("username")String username, @JsonProperty("password")String password, 
                        @JsonProperty("name") String name,
                        @JsonProperty("currentPoints") int currentPoints,
                        @JsonProperty("level") double level,
                        @JsonProperty("claimedDiscounts") Discount[] claimedDiscounts,
                        @JsonProperty("eventsJoined") Event[] eventsJoined) {
=======
    public VolunteerUser(String UID, String username, String password, 
                        @JsonProperty("name") String name) {
>>>>>>> 7d75da386a44f2703e15a41759ae5e422e04ee70
        super(UID, username, password);
        this.name = name;
        this.currentPoints = 0;
        this.level = 0;
        this.claimedDiscounts = new ArrayList<>();
        this.eventsJoined = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentPoints() {
        return currentPoints;
    }

    public boolean addEvent(Event event) {
        return eventsJoined.add(event);
    }

    //Returns -1 if not added
    public int completeEvent(Event event) {
        int added = -1;
        for (Event e : eventsJoined){
            if (e.getEventID() == event.getEventID()){
                currentPoints += event.getPoints();
                level += event.getPoints()/1000;
                added = event.getPoints();
                eventsJoined.remove(e);
                break;
            }
        }
        return added;
    }

    public double getLevel() {
        return level;
    }

    public ArrayList<Discount> getClaimedDiscounts() {
        return this.claimedDiscounts;
    }

    public void setClaimedDiscountID(ArrayList<Discount> claimedDiscounts) {
        this.claimedDiscounts = claimedDiscounts;
    }


}
