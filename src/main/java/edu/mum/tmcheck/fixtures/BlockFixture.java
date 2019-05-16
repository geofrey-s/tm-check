package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.serviceimp.BlockServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class BlockFixture extends BaseFixture {
    @Autowired
    BlockServiceImp blockServiceImp;

    @Override
    public void generate(int size) {
        Random random = new Random();

        while (size-- > 0) {
            Block block = new Block();
            LocalDate startDate = pastDateByMonth(random.nextInt(5) + 1);
            block.setStartDate(startDate);
            block.setEndDate(futureDateByMonth(1, startDate));

            blockServiceImp.save(block);
        }
    }


}
