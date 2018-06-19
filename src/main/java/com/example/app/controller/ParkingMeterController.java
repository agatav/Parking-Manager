package com.example.app.controller;

import com.example.app.entity.ParkingMeter;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    public List<ParkingMeter> showMetersByCarNumber(@PathVariable String carNumber) {
        String carId = carNumber;
        return parkingMeterData.getParkingMeterByCar(carId);
    }

    @GetMapping("/owner/{day}")
    public List<ParkingMeter> showMetersByDay(@PathVariable String day) throws ParseException {
        DateFormat format = new SimpleDateFormat("d MMM yyyy HH:mm:ss", Locale.ENGLISH);
        Date date = format.parse(day);
        return parkingMeterData.getParkingMeterByDate(date);
    }

    @GetMapping("/owner/{carNumber}/sum")
    public double showCostByCarNumber(@PathVariable String carNumber) {
        String carId = carNumber;
        return parkingMeterData.sumCosts(parkingMeterData.getParkingMeterByCar(carId));
    }

    @GetMapping("/owner/{day}/sum")
    public double showCostByDay(@PathVariable String day) throws ParseException {
        String date = day;
        return parkingMeterData.sumCosts(showMetersByDay(date));
    }

    @GetMapping("/driver/{carNumber}")
    public List<ParkingMeter> showMetersByNumber(@PathVariable String carNumber) {
        String carId = carNumber;
        return parkingMeterData.getParkingMeterByCar(carId);
    }
}