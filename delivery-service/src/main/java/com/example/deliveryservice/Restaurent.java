package com.example.deliveryservice;

public class Restaurent {

    private int id;
    private String name;

    // No-argument constructor
    public Restaurent() {
    }

    // Parameterized constructor
    public Restaurent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
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
}
