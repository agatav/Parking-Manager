package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
public class ParkingMeter {

    @Id
    @GeneratedValue
    private int id;

    private String carNumber;
    private LocalTime createdAtTime;
    private LocalTime stoppedAtTime;
    private LocalDate createdAtDay;
    private LocalDate stoppedAtDay;
    private BigDecimal cost;


    private ParkingMeter() { } // JPA only

    public ParkingMeter(String carNumber, LocalTime createdAtTime, LocalTime stoppedAtTime, LocalDate createdAtDay,
                        LocalDate stoppedAtDay, BigDecimal cost) {
        this.carNumber = carNumber;
        this.createdAtTime = createdAtTime;
        this.stoppedAtTime = stoppedAtTime;
        this.createdAtDay = createdAtDay;
        this.stoppedAtDay = stoppedAtDay;
        this.cost = cost;
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

    public LocalTime getCreatedAtTime() {
        return createdAtTime;
    }

    public void setCreatedAtTime(LocalTime createdAtTime) {
        this.createdAtTime = createdAtTime;
    }

    public LocalTime getStoppedAtTime() {
        return stoppedAtTime;
    }

    public void setStoppedAtTime(LocalTime stoppedAtTime) {
        this.stoppedAtTime = stoppedAtTime;
    }

    public LocalDate getCreatedAtDay() {
        return createdAtDay;
    }

    public void setCreatedAtDay(LocalDate createdAtDay) {
        this.createdAtDay = createdAtDay;
    }

    public LocalDate getStoppedAtDay() {
        return stoppedAtDay;
    }

    public void setStoppedAtDay(LocalDate stoppedAtDay) {
        this.stoppedAtDay = stoppedAtDay;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "ParkingMeter{" +
                "id=" + id +
                ", car Number='" + carNumber + '\'' +
                ", createdAtDay='" + createdAtDay.toString() + '\'' +
                ", createdAtTime='" + createdAtTime.toString() + '\'' +
                ", stoppedAtDay='" + stoppedAtTime.toString() + '\'' +
                ", stoppedAtTime='" + stoppedAtDay.toString() + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }

}
