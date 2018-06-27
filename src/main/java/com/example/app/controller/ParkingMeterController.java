package com.example.app.controller;

import com.example.app.entity.ParkingMeter;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ParkingMeterController {


    ParkingMeterData parkingMeterData = ParkingMeterData.getInstance();

    public ParkingMeterController() throws ParseException {
    }

    @GetMapping("/owner/meters")
    public List<ParkingMeter> index() {
        return parkingMeterData.fetchMeters();
    }

    @GetMapping("/owner/{carNumber}")
    public List<ParkingMeter> showOwnerMetersByCarNumber(@PathVariable String carNumber) {
        String carId = carNumber;
        return parkingMeterData.getParkingMeterByCar(carId);
    }

    @GetMapping("/owner/{day}/{month}/{year}")
    public List<ParkingMeter> showMetersByDay(@PathVariable String day, @PathVariable String month, @PathVariable String year) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = day+"/"+month+"/"+year;
        Date date = formatter.parse(dateInString);
        return parkingMeterData.getParkingMeterByDate(date);
    }

    @GetMapping("/owner/{carNumber}/sum")
    public double showCostByCarNumber(@PathVariable String carNumber) {
        String carId = carNumber;
        return parkingMeterData.sumCosts(parkingMeterData.getParkingMeterByCar(carId));
    }

    @GetMapping("/owner/{day}/{month}/{year}/sum")
    public double showCostByDay(@PathVariable String day, @PathVariable String month, @PathVariable String year) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = day+"/"+month+"/"+year;
        Date date = formatter.parse(dateInString);
        return parkingMeterData.sumCosts(parkingMeterData.getParkingMeterByDate(date));
    }

    @GetMapping("/driver/{carNumber}")
    public List<ParkingMeter> showMetersByCarNumber(@PathVariable String carNumber) {
        String car = carNumber;
        return parkingMeterData.getParkingMeterByCar(car);
    }


}