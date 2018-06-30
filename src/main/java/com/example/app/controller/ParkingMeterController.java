package com.example.app.controller;

import com.example.app.entity.ParkingMeter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
public class ParkingMeterController {

    private final ParkingMeterRepository parkingMeterRepository;

    public ParkingMeterController(ParkingMeterRepository parkingMeterRepository) {
        this.parkingMeterRepository = parkingMeterRepository;
    }

    @GetMapping("/owner/meters")
    public List<ParkingMeter> index() {
        return parkingMeterRepository.findAll();
    }

    @GetMapping("/owner/{carNumber}")
    public List<ParkingMeter> showOwnerMetersByCarNumber(@PathVariable String carNumber) {
        return parkingMeterRepository.findAllByCarNumber(carNumber.toUpperCase());
    }

    @GetMapping("/owner/{day}/{month}/{year}")
    public List<ParkingMeter> showMetersByDay(@PathVariable String day, @PathVariable String month,
                                              @PathVariable String year) {
        String localDateString = year+"-"+month+"-"+day;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formatDate = LocalDate.parse(localDateString, formatter);
        return parkingMeterRepository.findAllByStoppedAtDay(formatDate);
    }

    @GetMapping("/owner/{carNumber}/sum")
    public BigDecimal showCostByCarNumber(@PathVariable String carNumber) {
        List<ParkingMeter> meters = parkingMeterRepository.findAllByCarNumber(carNumber.toUpperCase());
        return sumCosts(meters);
    }

    @GetMapping("/owner/{day}/{month}/{year}/sum")
    public BigDecimal showCostByDay(@PathVariable String day, @PathVariable String month, @PathVariable String year) {
        String localDateString = year+"-"+month+"-"+day;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formatDate = LocalDate.parse(localDateString, formatter);
        List<ParkingMeter> meters = parkingMeterRepository.findAllByStoppedAtDay(formatDate);
        return sumCosts(meters);

    }

    @GetMapping("/driver/{carNumber}")
    public List<ParkingMeter> showMetersByCarNumber(@PathVariable String carNumber) {
        return parkingMeterRepository.findAllByCarNumber(carNumber.toUpperCase());
    }

    private BigDecimal sumCosts(List<ParkingMeter> meters){
        BigDecimal sum = new BigDecimal("0.0");
        for(ParkingMeter meter: meters) {
            sum= sum.add(meter.getCost());
        }
        return sum;
    }

}

