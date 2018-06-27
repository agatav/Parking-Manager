package com.example.app.controller;

import com.example.app.entity.Car;
import com.example.app.entity.ParkingMeter;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ParkingMeterData {

    private List<ParkingMeter> meters;
    private List<Car> cars;

    private static ParkingMeterData instance = null;
    public static ParkingMeterData getInstance() throws ParseException {
        if(instance == null){
            instance = new ParkingMeterData();
        }
        return instance;
    }


    public ParkingMeterData() throws ParseException {
        meters = new ArrayList<ParkingMeter>();
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        meters.add(new ParkingMeter(1, "AAAAAB222", format.parse("04/07/2001 12:08:56"),
                format.parse("04/07/2001 12:18:56"), 0 ));

        meters.add(new ParkingMeter(2, "BBBBBSSS", format.parse("05/07/2001 12:08:56"),
                format.parse("05/07/2001 13:18:56"), 2 ));

        meters.add(new ParkingMeter(3, "BBBBBSSS", format.parse("07/07/2001 12:08:56 "),
                format.parse("07/07/2001 12:48:43"), 1 ));

        meters.add(new ParkingMeter(4, "AAAAAB222", format.parse("13/07/2001 11:03:56"),
                format.parse("13/07/2001 15:03:56"), 2.4 ));

        meters.add(new ParkingMeter(5, "AAAAAB222", format.parse("13/08/2002 19:03:56 "),
                format.parse("13/08/2002 23:03:56"), 0 ));

        meters.add(new ParkingMeter(6, "AAAAAB222", format.parse("15/08/2002 11:03:56"),
                format.parse("15/08/2002 12:53:56"), 0 ));

        meters.add(new ParkingMeter(7, "AAAAAB222", format.parse("22/08/2002 07:03:56"),
                format.parse("22/08/2002 15:23:53"), 0 ));

        meters.add(new ParkingMeter(8, "AAAAAB222", format.parse("03/09/2002 11:03:56"),
                format.parse("03/09/2002 13:03:56"), 0 ));

        meters.add(new ParkingMeter(9, "AAAAAB222", format.parse("14/09/2002 05:43:56"),
                format.parse("14/09/2002 21:00:56"), 0 ));
    }

    public List<ParkingMeter> fetchMeters() {
        return meters;
    }

    public ParkingMeter fetchLargestMeterId(){
        List<ParkingMeter> meters = fetchMeters();
        meters.sort(Comparator.comparing(ParkingMeter::getId).reversed());
        return meters.get(0);
    }

    public List<ParkingMeter> getParkingMeterByCar(String carNumber) {
        List<ParkingMeter> searchedMeters = new ArrayList<ParkingMeter>();
        for(ParkingMeter meter: meters) {
            if(meter.getCarNumber().toUpperCase().equals(carNumber.toUpperCase())) {
                searchedMeters.add(meter);
            }
        }
        return searchedMeters;
    }

    public List<ParkingMeter> getParkingMeterByDate(Date date) {
        List<ParkingMeter> searchedMeters = new ArrayList<ParkingMeter>();
        for(ParkingMeter meter: meters) {
            if(meter.getStoppedAt().getDay() == date.getDay()  &&
                    meter.getStoppedAt().getMonth() == date.getMonth() &&
                    meter.getStoppedAt().getYear() == date.getYear()) {
                searchedMeters.add(meter);
            }
        }
        return searchedMeters;
    }

    public ParkingMeter getLatestParkingMeter(String carNumber){
        List<ParkingMeter> meters = getParkingMeterByCar(carNumber);
        meters.sort(Comparator.comparing(ParkingMeter::getCreatedAt).reversed());
        return meters.get(0);
    }

    public double sumCosts(List<ParkingMeter> meters){
        double sum = 0;
        for(ParkingMeter meter: meters) {
            sum+=meter.getCost();
        }
        return sum;
    }

    public ParkingMeter createMeter(int id, String carNumber, Date createdAt) {
        ParkingMeter newMeter = new ParkingMeter(id, carNumber, createdAt, createdAt, 0);
        meters.add(newMeter);
        return newMeter;
    }



    public ParkingMeter updateMeter(int id, Date stoppedAt) throws ParseException {
        for(ParkingMeter meter: meters) {
            if(meter.getId() == id) {
                int meterIndex = meters.indexOf(meter);
                meter.setStoppedAt(stoppedAt);
                double cost = countCost(id);
                meter.setCost(cost);
                meters.set(meterIndex, meter);
                return meter;
            }

        }

        return null;
    }

    private double countCost(int id) throws ParseException {
        for(ParkingMeter meter: meters) {
            if(meter.getId() == id) {
                String carNumber = meter.getCarNumber();
                List<Car> cars = CarMockedData.getInstance().fetchCars();
                for (Car car: cars){
                    if(car.getCarNumber().toLowerCase().equals(carNumber.toLowerCase())) {
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
                    }
                }
            }

        }
        return 0;
    }

}
