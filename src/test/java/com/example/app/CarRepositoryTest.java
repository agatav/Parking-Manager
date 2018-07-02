package com.example.app;

import com.example.app.controller.CarRepository;
import com.example.app.entity.Car;
import com.example.app.entity.CarLocation;
import com.example.app.entity.CarStatus;
import com.example.app.entity.ParkingMeterStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    private Car carItem1 = new Car("BBBBB", CarLocation.OUTSIDE, CarStatus.REGULAR, ParkingMeterStatus.OFF);
    private Car carItem2 = new Car("CCCCC", CarLocation.OUTSIDE, CarStatus.REGULAR, ParkingMeterStatus.OFF);

    @Test
    public void whenFindCarByCarNumber_thenReturnCar() {
        // given
        entityManager.persist(carItem1);
        entityManager.flush();

        // when
        Car found = carRepository.findCarByCarNumber(carItem1.getCarNumber());

        // then
        assertThat(found.getCarNumber())
                .isEqualTo(carItem1.getCarNumber());
    }

    @Test
    public void whenFindCarById_thenReturnCar() {
        // given
        entityManager.persist(carItem1);
        entityManager.flush();

        // when
        Car found = carRepository.findCarById(carItem1.getId());

        // then
        assertThat(found.getId())
                .isEqualTo(carItem1.getId());
    }

    @Test
    public void whenFindByCarLocation_thenReturnCars() {
        // given
        List <Car> testingList = new ArrayList<>();
        testingList.add(carItem1);
        testingList.add(carItem2);

        entityManager.persist(carItem1);
        entityManager.persist(carItem2);
        entityManager.flush();

        // when
        List<Car> found = carRepository.findByCarLocation(carItem1.getCarLocation());

        // then
        assertThat(found.equals(testingList)).isTrue();
    }

    @Test
    public void whenFindByCarNumberContaining_thenReturnCars() {
        // given
        List <Car> testingList = new ArrayList<>();
        testingList.add(carItem1);

        entityManager.persist(carItem1);
        entityManager.persist(carItem2);
        entityManager.flush();

        // when
        List<Car> found = carRepository.findByCarNumberContaining("BBB");

        // then
        assertThat(found.equals(testingList)).isTrue();
    }
}
