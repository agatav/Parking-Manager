package com.example.app.controller;

import com.example.app.entity.ParkingMeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ParkingMeterController {

    @Autowired
    ParkingMeterRepository parkingMeterRepository;

    public ParkingMeterController() throws ParseException {
    }

    @GetMapping("/owner/meters")
    public List<ParkingMeter> index() {
        return parkingMeterRepository.findAll();
    }

    @GetMapping("/owner/{carNumber}")
    public List<ParkingMeter> showOwnerMetersByCarNumber(@PathVariable String carNumber) {
        String carId = carNumber;
        return parkingMeterRepository.findAllByCarNumber(carId);
    }

    @GetMapping("/owner/{day}/{month}/{year}")
    public List<ParkingMeter> showMetersByDay(@PathVariable String day, @PathVariable String month, @PathVariable String year) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String dateInString = year+"-"+month+"-"+day;
        Date date = formatter.parse(dateInString);
        return parkingMeterRepository.findParkingMetersByStoppedAt(date);
    }

    @GetMapping("/owner/{carNumber}/sum")
    public double showCostByCarNumber(@PathVariable String carNumber) {
        String carId = carNumber;
        List<ParkingMeter> meters = parkingMeterRepository.findAllByCarNumber(carId);
        return sumCosts(meters);
    }

    @GetMapping("/owner/{day}/{month}/{year}/sum")
    public double showCostByDay(@PathVariable String day, @PathVariable String month, @PathVariable String year) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        String dateInString = year+"-"+month+"-"+day;
        Date date = formatter.parse(dateInString);
        List<ParkingMeter> meters = parkingMeterRepository.findParkingMetersByStoppedAt(date);
        return sumCosts(meters);

    }

    @GetMapping("/driver/{carNumber}")
    public List<ParkingMeter> showMetersByCarNumber(@PathVariable String carNumber) {
        String car = carNumber;
        return parkingMeterRepository.findAllByCarNumber(car);
    }

    private double sumCosts(List<ParkingMeter> meters){
        double sum = 0;
        for(ParkingMeter meter: meters) {
            sum+=meter.getCost();
        }
        return sum;
    }

}

