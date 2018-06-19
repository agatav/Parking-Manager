package com.example.app.entity;


public class Car {

    private int id;
    private String carNumber;
    private CarLocation carLocation;
    private CarStatus carStatus;


    private ParkingMeterStatus parkingMeterStatus;

    private Car() { } // JPA only


    public Car(int id, String carNumber, CarLocation carLocation, CarStatus carStatus, ParkingMeterStatus parkingMeterStatus) {
        this.setId(id);
        this.setCarNumber(carNumber);
        this.setCarLocation(carLocation);
        this.setCarStatus(carStatus);
        this.setParkingMeterStatus(parkingMeterStatus);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public CarLocation getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(CarLocation carLocation) {
        this.carLocation = carLocation;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }

    public ParkingMeterStatus getParkingMeterStatus() {
        return parkingMeterStatus;
    }

    public void setParkingMeterStatus(ParkingMeterStatus parkingMeterStatus) {
        this.parkingMeterStatus = parkingMeterStatus;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", car Number='" + carNumber + '\'' +
                ", car location='" + carLocation + '\'' +
                ", car status='" + carStatus + '\'' +
                ", parking meter status='" + parkingMeterStatus + '\'' +
                '}';
    }

}
