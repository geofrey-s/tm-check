package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.domain.entities.Faculty;
import edu.mum.tmcheck.services.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import edu.mum.tmcheck.domain.repository.BlockRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BlockServiceImp implements BlockService {
    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private OfferedCourseServiceImp offeredCourseServiceImp;

    @Override
    public void create() {

    }

    public List<Block> getfacultyteachingblocks(Long userid){
//        HashMap<Long, Block> blocks = new HashMap<>();
//        offeredCourseServiceImp.getfacultytaughtblock(userid).forEach(blk -> {
//            blocks.put(blk.getId(), blk);
//        });
        return offeredCourseServiceImp.getfacultytaughtblock(userid);
    }

    @Override
    public Block get() {
        return null;
    }

    @Override
    public Block save(Block block) {
        return blockRepository.save(block);
    }

    @Override
    public Block getFirst() {
        return blockRepository.getFirst();
    }

    @Override
    public List<Block> findAll() {
        List<Block> records = new ArrayList<>();

        blockRepository.findAll().forEach(records::add);
        return records;
    }
    public Block findById(long id) {
        return blockRepository.findById(id).get();
    }
}
