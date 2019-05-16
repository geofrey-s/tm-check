package edu.mum.tmcheck.fixtures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApplicationFixture {
    final int DEFAULT_NUMBER_OF_RECORDS = 10;

    @Autowired
    CardFixture cardFixture;

    @Autowired
    EntryFixture entryFixture;

    @Autowired
    CourseFixture courseFixture;

    @PostConstruct
    public void initialize() {
        entryFixture.generate(2);
        courseFixture.generate(DEFAULT_NUMBER_OF_RECORDS);
        cardFixture.generate(DEFAULT_NUMBER_OF_RECORDS);
    }
}
