package com.example.app.controller;

import com.example.app.entity.Car;
import com.example.app.entity.CarLocation;
import com.example.app.entity.CarSatus;
import com.example.app.entity.ParkingMeterStatus;

import java.util.ArrayList;
import java.util.List;

public class CarMockedData {

    //list of cars
    private List<Car> cars;

    private static CarMockedData instance = null;
    public static CarMockedData getInstance(){
        if(instance == null){
            instance = new CarMockedData();
        }
        return instance;
    }


    public CarMockedData(){
        cars = new ArrayList<Car>();
        cars.add(new Car(1, "AAAAAB222",
                CarLocation.OUTSIDE, CarSatus.VIP, ParkingMeterStatus.OFF));
        cars.add(new Car(2, "BBBBBSSS",
                CarLocation.OUTSIDE, CarSatus.REGULAR, ParkingMeterStatus.OFF));
        cars.add(new Car(3, "WWWWW",
                CarLocation.INSIDE, CarSatus.REGULAR, ParkingMeterStatus.OFF));
        cars.add(new Car(4, "WERWE2",
                CarLocation.OUTSIDE, CarSatus.REGULAR, ParkingMeterStatus.OFF));
        cars.add(new Car(5, "LUBRBR",
                CarLocation.OUTSIDE, CarSatus.VIP, ParkingMeterStatus.OFF));
        cars.add(new Car(6, "RRRR",
                CarLocation.INSIDE, CarSatus.REGULAR, ParkingMeterStatus.OFF));
        cars.add(new Car(7, "RRRRSSSW",
                CarLocation.OUTSIDE, CarSatus.REGULAR, ParkingMeterStatus.OFF));
        cars.add(new Car(8, "WERTVFR",
                CarLocation.OUTSIDE, CarSatus.VIP, ParkingMeterStatus.OFF));
        cars.add(new Car(9, "WDERF",
                CarLocation.INSIDE, CarSatus.REGULAR, ParkingMeterStatus.OFF));
        cars.add(new Car(10, "TRTRTRT",
                CarLocation.OUTSIDE, CarSatus.VIP, ParkingMeterStatus.OFF));
    }


    public List<Car> fetchCars() {
        return cars;
    }


    public Car getCarById(int id) {
        for(Car car: cars) {
            if(car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    public Car getCarByNumber(String carNumber) {
        for(Car car: cars) {
            if(car.getCarNumber().toLowerCase().equals(carNumber.toLowerCase())) {
                return car;
            }
        }
        return null;
    }

    public List<Car> getCarsByLocation(CarLocation carLocation) {
        List<Car> searchedCars = new ArrayList<>();
        for(Car car: cars) {
            if(car.getCarLocation().equals(carLocation)) {
                searchedCars.add(car);
            }
        }

        return searchedCars;
    }

    public List<Car> searchCars(String searchTerm) {
        List<Car> searchedCars = new ArrayList<>();
        for(Car car: cars) {
            if(car.getCarNumber().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    car.getCarLocation().toString().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    car.getCarSatus().toString().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchedCars.add(car);
            }
        }

        return searchedCars;
    }


    public Car updateCarLocation(String carNumber, CarLocation carLocation) {
        for(Car car: cars) {
            if(car.getCarNumber().toLowerCase().equals(carNumber.toLowerCase())) {
                int carIndex = cars.indexOf(car);
                car.setCarLocation(carLocation);
                cars.set(carIndex, car);
                return car;
            }
        }

        return null;
    }

    public Car updateParkingMeterStatus(String carNumber, ParkingMeterStatus parkingMeterStatus) {
        for(Car car: cars) {
            if(car.getCarNumber().toLowerCase().equals(carNumber.toLowerCase())) {
                int carIndex = cars.indexOf(car);
                car.setParkingMeterStatus(parkingMeterStatus);
                cars.set(carIndex, car);
                return car;
            }
        }

        return null;
    }
}
