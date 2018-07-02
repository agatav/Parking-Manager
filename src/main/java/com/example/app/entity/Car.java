package com.example.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Car {

    @Id
    @GeneratedValue
    private int id;
    @NonNull private String carNumber;
    @NonNull private CarLocation carLocation;
    @NonNull private CarStatus carStatus;
    @NonNull private ParkingMeterStatus parkingMeterStatus;


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
