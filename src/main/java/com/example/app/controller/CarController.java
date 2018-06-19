package com.example.app.controller;

import com.example.app.entity.Car;
import com.example.app.entity.CarLocation;
import com.example.app.entity.ParkingMeterStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CarController {
    CarMockedData carMockedData = CarMockedData.getInstance();

    @GetMapping("/owner/cars")
    public List<Car> index(){
        return carMockedData.fetchCars();
    }

    @GetMapping("/operator/list")
    public List<Car> showInsideCars(){
       return carMockedData.getCarsByLocation(CarLocation.INSIDE);
    }

    @GetMapping("/operator/cars/{id}")
    public Car showCarsById(@PathVariable String id){
        int carId = Integer.parseInt(id);
        return carMockedData.getCarById(carId);
    }

    @GetMapping("/operator/cars/number/{carNumber}")
    public Car showCarsByNumber(@PathVariable String carNumber){
        String carId = carNumber;
        return carMockedData.getCarByNumber(carId);
    }

    @PostMapping("/operator/search")
    public List<Car> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return carMockedData.searchCars(searchTerm);
    }


    @PutMapping("/driver/{carNumber}/location")
    public Car updateCarLocation(@PathVariable String carNumber, @RequestBody Map<String, CarLocation> body){
        String carId = carNumber.toUpperCase();
        CarLocation location = body.get("location");
        return carMockedData.updateCarLocation(carId, location);
    }

    @PutMapping("/driver/{carNumber}/parkingMeter")
    public Car updateParkingMeterStatus(@PathVariable String carNumber, @RequestBody Map<String, ParkingMeterStatus> body){
        String carId = carNumber.toUpperCase();
        ParkingMeterStatus status = body.get("parkingMeter");
        return carMockedData.updateParkingMeterStatus(carId, status);
    }

}
