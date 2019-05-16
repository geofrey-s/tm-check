package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Entry;
import edu.mum.tmcheck.serviceimp.EntryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EntryFixture extends BaseFixture {
    @Autowired
    EntryServiceImp entryServiceImp;

    @Override
    public void generate(int size) {
        Entry entry = new Entry();
        entry.setStartDate(LocalDate.now());
        entry.setEndDate(futureDateByYear(1));
        entry.setName("FEB19");
        entryServiceImp.save(entry);

        entry = new Entry();
        entry.setStartDate(LocalDate.now());
        entry.setEndDate(futureDateByYear(1));
        entry.setName("APL19");
        entryServiceImp.save(entry);
    }
}
