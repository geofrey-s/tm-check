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
        LocalDate DataGeneratingStartDate = LocalDate.of(2018, 01, 01);
        size = 24;

        while(size-- > 0){
            Block block = new Block();
            block.setStartDate(DataGeneratingStartDate);
            Integer n = random.nextInt(noofdays.size());
            block.setEndDate(block.getStartDate().plusDays(noofdays.get(n)));
            DataGeneratingStartDate = DataGeneratingStartDate.plusDays(noofdays.get(n)).plusDays(4);
            blockServiceImp.save(block);
        }
    }
}
