package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.services.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import edu.mum.tmcheck.domain.repository.BlockRepository;
import org.springframework.stereotype.Service;

@Service
public class BlockServiceImp implements BlockService {
    @Autowired
    private BlockRepository blockRepository;

    @Override
    public void create() {

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

    public Block findById(long id){
        return blockRepository.findById(id).get();
    }
}
