package com.example.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;


//@Entity
public class Car {

    private int id;
    private String carNumber;
    private CarLocation carLocation;
    private CarSatus carSatus;


    private ParkingMeterStatus parkingMeterStatus;

    private Car() { } // JPA only


    public Car(int id, String carNumber, CarLocation carLocation, CarSatus carSatus, ParkingMeterStatus parkingMeterStatus) {
        this.setId(id);
        this.setCarNumber(carNumber);
        this.setCarLocation(carLocation);
        this.setCarSatus(carSatus);
        this.setParkingMeterStatus(parkingMeterStatus);

    }
//
//    @OneToMany(mappedBy = "car")
//    private Set<ParkingMeter> parkingMeterSet = new HashSet<>();
//
//    public Set<ParkingMeter> parkingMeterSet() {
//        return parkingMeterSet;
//    }

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

    public CarLocation getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(CarLocation carLocation) {
        this.carLocation = carLocation;
    }

    public CarSatus getCarSatus() {
        return carSatus;
    }

    public void setCarSatus(CarSatus carSatus) {
        this.carSatus = carSatus;
    }

    public ParkingMeterStatus getParkingMeterStatus() {
        return parkingMeterStatus;
    }

    public void setParkingMeterStatus(ParkingMeterStatus parkingMeterStatus) {
        this.parkingMeterStatus = parkingMeterStatus;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", car Number='" + carNumber + '\'' +
                ", car location='" + carLocation + '\'' +
                ", car status='" + carSatus + '\'' +
                ", parking meter status='" + parkingMeterStatus + '\'' +
                '}';
    }

}
