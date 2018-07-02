package com.example.app;

import com.example.app.controller.ParkingMeterRepository;
import com.example.app.entity.ParkingMeter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ParkingMeterRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ParkingMeterRepository parkingMeterRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ParkingMeter meterItem1 = new ParkingMeter("BBBBBSSS", LocalTime.of(13, 15),
            LocalTime.of(15,18), LocalDate.parse("2001-07-05", formatter),
            LocalDate.parse("2001-07-05", formatter),new BigDecimal("2"));
    private ParkingMeter meterItem2 = new ParkingMeter("BBBBBSSS", LocalTime.of(13, 15),
            LocalTime.of(15,18), LocalDate.parse("2001-10-05", formatter),
            LocalDate.parse("2001-10-05", formatter),new BigDecimal("2"));
    private ParkingMeter meterItem3 = new ParkingMeter("CCCCC", LocalTime.of(13, 15),
            LocalTime.of(15,18), LocalDate.parse("2001-10-05", formatter),
            LocalDate.parse("2001-10-05", formatter),new BigDecimal("2"));

    @Test
    public void whenFindCarById_thenReturnMeter() {
        // given
        entityManager.persist(meterItem1);
        entityManager.flush();

        // when
        ParkingMeter found = parkingMeterRepository.findParkingMeterById(meterItem1.getId());

        // then
        assertThat(found.getId())
                .isEqualTo(meterItem1.getId());
    }

    @Test
    public void whenFindAllByCarNumber_thenReturnMeters() {
        // given
        List <ParkingMeter> testingList = new ArrayList<>();
        testingList.add(meterItem1);
        testingList.add(meterItem2);

        entityManager.persist(meterItem1);
        entityManager.persist(meterItem2);
        entityManager.persist(meterItem3);
        entityManager.flush();

        // when
        List<ParkingMeter> found = parkingMeterRepository.findAllByCarNumber("BBBBBSSS");

        // then
        assertThat(found.equals(testingList)).isTrue();
    }

    @Test
    public void whenFindAllByStoppedAtDay_thenReturnMeters() {
        // given
        List <ParkingMeter> testingList = new ArrayList<>();
        testingList.add(meterItem2);
        testingList.add(meterItem3);

        entityManager.persist(meterItem1);
        entityManager.persist(meterItem2);
        entityManager.persist(meterItem3);
        entityManager.flush();

        // when
        List<ParkingMeter> found = parkingMeterRepository.findAllByStoppedAtDay(LocalDate.parse("2001-10-05", formatter));

        // then
        assertThat(found.equals(testingList)).isTrue();
    }

}
