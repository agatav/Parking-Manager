package com.example.app.controller;

import com.example.app.entity.Car;
import com.example.app.entity.CarLocation;
import com.example.app.entity.ParkingMeter;
import com.example.app.entity.ParkingMeterStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Comparator;
import java.util.List;


@RestController
public class CarController {

    private final CarRepository carRepository;
    private final ParkingMeterRepository parkingMeterRepository;

    public CarController(CarRepository carRepository, ParkingMeterRepository parkingMeterRepository) {
        this.carRepository = carRepository;
        this.parkingMeterRepository = parkingMeterRepository;
    }

    @GetMapping("/owner/cars")
    public List<Car> index(){

        return carRepository.findAll();
    }

    @GetMapping("/operator/list")
    public List<Car> showInsideCars(){
       return carRepository.findByCarLocation(CarLocation.INSIDE);
    }

    @GetMapping("/operator/cars/{id}")
    public Car showCarsById(@PathVariable int id){
        return carRepository.findCarById(id);
    }

    @GetMapping("/operator/cars/number/{carNumber}")
    public Car showCarsByNumber(@PathVariable String carNumber){
        return carRepository.findCarByCarNumber(carNumber.toUpperCase());
    }

    @PostMapping("/operator/search")
    public List<Car> search(@RequestParam("text") String searchTerm){
        return carRepository.findByCarNumberContaining(searchTerm.toUpperCase());
    }


    @PostMapping("/driver/{carNumber}/location/inside")
    public Car updateCarLocationInside(@PathVariable String carNumber){
        Car car = carRepository.findCarByCarNumber(carNumber.toUpperCase());
        car.setCarLocation(CarLocation.INSIDE);
        return carRepository.save(car);
    }

    @PostMapping("/driver/{carNumber}/location/outside")
    public Car updateCarLocationOutside(@PathVariable String carNumber){
        Car car = carRepository.findCarByCarNumber(carNumber.toUpperCase());
        car.setCarLocation(CarLocation.OUTSIDE);
        return carRepository.save(car);
    }

    @PostMapping("/driver/{carNumber}/parkingMeter/on")
    public Car updateParkingMeterStatusON(@PathVariable String carNumber) throws ParseException {
        Car car = carRepository.findCarByCarNumber(carNumber.toUpperCase());
        LocalDateTime date = LocalDateTime.now();
        parkingMeterRepository.save(new ParkingMeter(carNumber.toUpperCase(), date.toLocalTime(), date.toLocalTime(),
                date.toLocalDate(), date.toLocalDate(), new BigDecimal("0.0")));
        car.setParkingMeterStatus(ParkingMeterStatus.ON);

        return carRepository.save(car);
    }

    @PostMapping("/driver/{carNumber}/parkingMeter/off")
    public Car updateParkingMeterStatusOFF(@PathVariable String carNumber) throws ParseException{
        Car car = carRepository.findCarByCarNumber(carNumber.toUpperCase());
        LocalDateTime date = LocalDateTime.now();

        List<ParkingMeter> parkingMeters = parkingMeterRepository.findAllByCarNumber(carNumber.toUpperCase());
        parkingMeters.sort(Comparator.comparing(ParkingMeter::getCreatedAtDay).reversed());
        ParkingMeter parkingMeter = parkingMeters.get(0);

        parkingMeter.setStoppedAtDay(date.toLocalDate());
        parkingMeter.setStoppedAtTime(date.toLocalTime());
        BigDecimal cost = new BigDecimal(countCost(parkingMeter.getId()));
        parkingMeter.setCost(cost);
        parkingMeterRepository.save(parkingMeter);

        car.setParkingMeterStatus(ParkingMeterStatus.OFF);

        return carRepository.save(car);
    }

    private double countCost(int id) throws ParseException {
        ParkingMeter meter = parkingMeterRepository.findParkingMeterById(id);
        Car car = carRepository.findCarByCarNumber(meter.getCarNumber());

        Period period = Period.between(meter.getCreatedAtDay(), meter.getStoppedAtDay());
        long daysDiff = Math.abs(period.getDays())*1440;

        Duration timeDuration = Duration.between(meter.getCreatedAtTime(), meter.getStoppedAtTime());
        long minutesDiff = Math.abs(timeDuration.toMinutes());

        long diff = minutesDiff+daysDiff;
        switch (car.getCarStatus()) {
            case VIP:
                if (diff<60)
                    return 0;
                else if (diff<120)
                    return 2;
                else
                    return 1.5*(diff%60 + 1); //TODO recursion
            case REGULAR:
                if (diff<60)
                    return 1;
                else if (diff<120)
                    return 2;
                else
                    return 1.2*(diff%60 + 1); //TODO recursion
                }
        return 0;
    }

}
