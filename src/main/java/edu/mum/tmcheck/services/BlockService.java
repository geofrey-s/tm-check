package edu.mum.tmcheck.services;

import edu.mum.tmcheck.domain.entities.Block;

import java.util.HashMap;
import java.util.List;

public interface BlockService {
    public void create();

    //    public void update();
    public Block get();

    public Block save(Block block);

    public Block getFirst();

    public List<Block> findAll();
<<<<<<< HEAD

    public HashMap<Long, Block> getfacultyteachingblocks(Long userid);
=======
    public List<Block> getfacultyteachingblocks(Long userid);
>>>>>>> 8d6c0977cbbe2101af0fc1119bd3f56aea558f95
}
