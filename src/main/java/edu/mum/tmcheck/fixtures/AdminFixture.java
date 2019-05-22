package edu.mum.tmcheck.fixtures;

import edu.mum.tmcheck.domain.entities.Admin;
import edu.mum.tmcheck.serviceimp.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminFixture extends BaseFixture {
    private final String DEFAULT_USERNAME = "admin";

    @Autowired
    AdminServiceImp adminServiceImp;

    @Override
    public void generate(int size) {
        Admin admin = new Admin();
        admin.setRole("admin");
        admin.setName(faker.name().fullName());

        admin.setUsername(DEFAULT_USERNAME);
        admin.setPassword(DEFAULT_USERNAME);

        adminServiceImp.save(admin);
    }
}
