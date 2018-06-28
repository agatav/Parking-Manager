package com.example.app.controller;

import com.example.app.entity.ParkingMeter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ParkingMeterRepository extends JpaRepository<ParkingMeter, Integer> {
    List<ParkingMeter> findAllByCarNumber(String carNumber);
    ParkingMeter findParkingMeterById(int id);
    List<ParkingMeter> findParkingMetersByStoppedAt(Date date);

}