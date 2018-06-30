package com.example.app.controller;

import com.example.app.entity.ParkingMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ParkingMeterRepository extends JpaRepository<ParkingMeter, Integer> {
    List<ParkingMeter> findAllByCarNumber(String carNumber);
    ParkingMeter findParkingMeterById(int id);
    List<ParkingMeter> findAllByStoppedAtDay(LocalDate date);

}