package edu.mum.tmcheck.fixtures;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public abstract class BaseFixture {
    @Autowired
    protected Faker faker;

    @Autowired
    protected FakeValuesService fakeValuesService;

    protected Random random = new Random();

    public abstract void generate(int size);

    /**
     * Generate a future date given a number of years in the future
     * @param numYears
     * @return
     */
    public LocalDate futureDateByYear(Integer numYears) {
        numYears = numYears != null ? numYears : 1;
        Date futureDate = faker.date().future(numYears * 365, TimeUnit.DAYS);
        return toLocalDate(futureDate);
    }

    /**
     * Generate a future date given a number of months from the reference date
     * @param numMonths
     * @param refDate
     * @return
     */
    public LocalDate futureDateByMonth(Integer numMonths, LocalDate refDate) {
        Date _refDate =  java.sql.Date.valueOf(refDate);
        numMonths = numMonths != null ? numMonths : 1;
        Date futureDate = faker.date().future(numMonths * 30, TimeUnit.DAYS, _refDate);
        return toLocalDate(futureDate);
    }

    /**
     * Generate a past date given a number of months in the past
     * @param numMonths
     * @return
     */
    public LocalDate pastDateByMonth(Integer numMonths) {
        numMonths = numMonths != null ? numMonths : 1;
        Date futureDate = faker.date().past(numMonths * 30, TimeUnit.DAYS);
        return toLocalDate(futureDate);
    }

    /**
     * Conver a Date object to the new advanced LocalDate object
     * @param date
     * @return
     */
    public LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
