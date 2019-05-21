package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.serviceimp.BlockServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class BlockFixture extends BaseFixture {
    @Autowired
    BlockServiceImp blockServiceImp;

    @Override
    public void generate(int size) {
        Random random = new Random();
        List<Integer> noofdays = new ArrayList<>();
        noofdays.add(14);
        noofdays.add(22);
        while (size-- > 0) {
            Block block = new Block();
            LocalDate startDate = pastDateByMonth(random.nextInt(5) + 1);
            block.setStartDate(startDate);
            Integer n = random.nextInt(noofdays.size());
            block.setEndDate(block.getStartDate().plusDays(noofdays.get(n)));
            blockServiceImp.save(block);
        }
    }


}
