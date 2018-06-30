package com.example.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ParkingMeter {

    @Id
    @GeneratedValue
    private int id;

    @NonNull private String carNumber;
    @NonNull private LocalTime createdAtTime;
    @NonNull private LocalTime stoppedAtTime;
    @NonNull private LocalDate createdAtDay;
    @NonNull private LocalDate stoppedAtDay;
    @NonNull private BigDecimal cost;

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
