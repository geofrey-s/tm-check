package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.TMType;
import edu.mum.tmcheck.serviceimp.TMtypeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TMTypeFixture extends BaseFixture {
    @Autowired
    TMtypeServiceImp tMtypeServiceImp;

    @Override
    public void generate(int size) {
        TMType tmType = new TMType();

        tmType.setName("standard");
        tMtypeServiceImp.save(tmType);

        tmType = new TMType();

        tmType.setName("retreat");
        tMtypeServiceImp.save(tmType);

        tmType = new TMType();

        tmType.setName("check");
        tMtypeServiceImp.save(tmType);
    }
}
