package com.example.app.controller;

import com.example.app.entity.Car;
import com.example.app.entity.CarLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByCarNumberContainingOrCarLocationContainingOrCarStatusContaining(String textNumber,
                                                                                    String textLocation, String textStatus);
    List<Car> findByCarLocation(CarLocation location);
    Car findCarById(int id);
    Car findCarByCarNumber(String carNumber);
}