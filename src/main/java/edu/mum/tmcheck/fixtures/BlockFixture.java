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
        System.out.println("Generating Block fixture data ...");

        Random random = new Random();
        List<Integer> noofdays = new ArrayList<>();
        noofdays.add(14);
        noofdays.add(28);
        LocalDate DataGeneratingStartDate = LocalDate.of(2019, 02, 01);
        size = 12;

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
