package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Entry;
import edu.mum.tmcheck.serviceimp.EntryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class EntryFixture extends BaseFixture {
    @Autowired
    EntryServiceImp entryServiceImp;

    @Override
    public void generate(int size) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Entry entry = new Entry();
        LocalDate start = LocalDate.parse("2019-02-01");
        entry.setStartDate(start);

        entry.setEndDate(futureDateByMonth(8, start));
        entry.setName("FEB19");
        entryServiceImp.save(entry);

        entry = new Entry();
        start = LocalDate.parse("2019-04-01");
        entry.setStartDate(start);
        entry.setEndDate(futureDateByMonth(8, start));
        entry.setName("APL19");
        entryServiceImp.save(entry);
    }
}
