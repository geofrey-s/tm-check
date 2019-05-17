package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Block;

import java.util.List;

public interface BlockService {
    public void create();

    //    public void update();
    public Block get();
    public Block save(Block block);
    public Block getFirst();
    public List<Block> findAll();
}
