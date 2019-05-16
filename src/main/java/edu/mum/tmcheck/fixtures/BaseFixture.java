package edu.mum.tmcheck.fixtures;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public abstract class BaseFixture {
    @Autowired
    protected Faker faker;

    @Autowired
    protected FakeValuesService fakeValuesService;

    public abstract void generate(int size);

    /**
     * Generate a future date a given number of years in the future
     * @param numYears
     * @return
     */
    public LocalDate futureYearDate(Integer numYears) {
        numYears = numYears != null ? numYears : 1;
        Date futureDate = faker.date().future(numYears * 365, TimeUnit.DAYS);
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
