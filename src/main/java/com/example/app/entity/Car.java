package com.example.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue
    private int id;
    private String carNumber;
    private CarLocation carLocation;
    private CarStatus carStatus;
    private ParkingMeterStatus parkingMeterStatus;


    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", car Number='" + carNumber + '\'' +
                ", car location='" + carLocation + '\'' +
                ", car status='" + carStatus + '\'' +
                ", parking meter status='" + parkingMeterStatus + '\'' +
                '}';
    }

}
