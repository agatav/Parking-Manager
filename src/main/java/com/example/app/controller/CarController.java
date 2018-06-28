package com.example.app.controller;

import com.example.app.entity.Car;
import com.example.app.entity.CarLocation;
import com.example.app.entity.ParkingMeter;
import com.example.app.entity.ParkingMeterStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class CarController {
    @Autowired
    CarRepository carRepository;
    ParkingMeterRepository parkingMeterRepository;

    @GetMapping("/owner/cars")
    public List<Car> index(){
        return carRepository.findAll();
    }

    @GetMapping("/operator/list")
    public List<Car> showInsideCars(){
       return carRepository.findByCarLocation(CarLocation.INSIDE);
    }

    @GetMapping("/operator/cars/{id}")
    public Car showCarsById(@PathVariable String id){
        int carId = Integer.parseInt(id);
        return carRepository.findCarById(carId);
    }

    @GetMapping("/operator/cars/number/{carNumber}")
    public Car showCarsByNumber(@PathVariable String carNumber){
        String carId = carNumber.toUpperCase();
        return carRepository.findCarByCarNumber(carId);
    }

    @PostMapping("/operator/search")
    public List<Car> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return carRepository.findByCarNumberContainingOrCarLocationContainingOrCarStatusContaining(searchTerm,
                searchTerm, searchTerm);
    }


    @PutMapping("/driver/{carNumber}/location")
    public Car updateCarLocation(@PathVariable String carNumber, @RequestBody Map<String, CarLocation> body){
        String carId = carNumber.toUpperCase();
        Car car = carRepository.findCarByCarNumber(carId);
        car.setCarLocation(body.get("location"));
        return carRepository.save(car);
    }

    @PostMapping("/driver/{carNumber}/parkingMeter")
    public Car updateParkingMeterStatus(@PathVariable String carNum, @RequestBody Map<String, ParkingMeterStatus> body) throws ParseException {
        String carNumber = carNum.toUpperCase();
        Car car = carRepository.findCarByCarNumber(carNumber);
        ParkingMeterStatus status = body.get("parkingMeter");

        if (status == ParkingMeterStatus.ON) {
            Date date = java.util.Calendar.getInstance().getTime();
            parkingMeterRepository.save(new ParkingMeter(carNumber, date, date,0));
            car.setParkingMeterStatus(status);
        }
        else {
            List<ParkingMeter> parkingMeters = parkingMeterRepository.findAllByCarNumber(carNumber);
            parkingMeters.sort(Comparator.comparing(ParkingMeter::getCreatedAt).reversed());
            ParkingMeter parkingMeter = parkingMeters.get(0);
            Date date = java.util.Calendar.getInstance().getTime();
            parkingMeter.setStoppedAt(date);
            double cost = countCost(parkingMeter.getId());
            parkingMeter.setCost(cost);
            parkingMeterRepository.save(parkingMeter);
            car.setParkingMeterStatus(ParkingMeterStatus.OFF);
        }
        return carRepository.save(car);
    }

    private double countCost(int id) throws ParseException {
        ParkingMeter meter = parkingMeterRepository.findParkingMeterById(id);
        String carNumber = meter.getCarNumber();
        Car car = carRepository.findCarByCarNumber(carNumber);

        int diff = meter.getCreatedAt().getMinutes() - meter.getStoppedAt().getMinutes();
        switch (car.getCarStatus()) {
            case VIP:
                if (diff<60){ return 0; }
                else if (diff<120){ return 2; }
                else return 1.5*(diff%60 + 1); //TODO recursion
            case REGULAR:
                if (diff<60){ return 1; }
                else if (diff<120){ return 2; }
                else return 1.2*(diff%60 + 1); //TODO recursion

                        }
        return 0;
    }

}
