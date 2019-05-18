package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Admin;
import edu.mum.tmcheck.serviceimp.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminFixture extends BaseFixture {
    @Autowired
    AdminServiceImp adminServiceImp;

    @Override
    public void generate(int size) {
        Admin admin = new Admin();
        admin.setRole("admin");
        admin.setName(faker.name().fullName());

        String username = faker.name().username();
        admin.setUsername(username);
        admin.setPassword(username);

        adminServiceImp.save(admin);
    }
}
