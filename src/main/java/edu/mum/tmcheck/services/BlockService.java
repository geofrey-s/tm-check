package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Block;

public interface BlockService {
    public void create();

    //    public void update();
    public Block get();
    public Block save(Block block);
    public Block getFirst();
}
