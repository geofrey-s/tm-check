package edu.mum.tmcheck.serviceimp;

import edu.mum.tmcheck.domain.entities.Block;
import edu.mum.tmcheck.services.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import edu.mum.tmcheck.domain.Repository.BlockRepository;

class BlockServiceImp implements BlockService {
    @Autowired
    private BlockRepository blockRepository;
    @Override
    public void create() {

    }

    @Override
    public Block get() {
        return null;
    }
}
