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

    public VolunteerUser(@JsonProperty("UID")String UID, @JsonProperty("username")String username, @JsonProperty("password")String password, 
                        @JsonProperty("name") String name,
                        @JsonProperty("currentPoints") int currentPoints,
                        @JsonProperty("level") double level,
                        @JsonProperty("claimedDiscounts") ArrayList<Discount> claimedDiscounts,
                        @JsonProperty("eventsJoined") ArrayList<Event> eventsJoined) {
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

        System.out.println("start");

        for (Event e : eventsJoined) {
            System.out.println("start 1");
            if (e.getEventID() == event.getEventID()) {
                System.out.println("found");
                currentPoints += event.getPoints();
                level += event.getPoints()/1000;
                added = event.getPoints();
                eventsJoined.remove(e);
                break;
            }
            System.out.println("not found");
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

    public ArrayList<Event> getEventsJoined() {
        return this.eventsJoined;
    }
}
