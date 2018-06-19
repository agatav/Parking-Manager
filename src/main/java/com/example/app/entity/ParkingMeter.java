package com.example.app.entity;

import java.util.Date;


public class ParkingMeter {

    private int id;
    private String carNumber;
    private Date createdAt;
    private Date stoppedAt;
    private double cost;


    private ParkingMeter() { } // JPA only

    public ParkingMeter(int id, String carNumber, Date createdAt, Date stoppedAt, double cost) {
        this.setId(id);
        this.setCarNumber(carNumber);
        this.setCreatedAt(createdAt);
        this.setStoppedAt(stoppedAt);
        this.setCost(cost);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getStoppedAt() {
        return stoppedAt;
    }

    public void setStoppedAt(Date stoppedAt) {
        this.stoppedAt = stoppedAt;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "ParkingMeter{" +
                "id=" + id +
                ", car Number='" + carNumber + '\'' +
                ", createdAt='" + createdAt.toString() + '\'' +
                ", stoppedAt='" + stoppedAt.toString() + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }

}
