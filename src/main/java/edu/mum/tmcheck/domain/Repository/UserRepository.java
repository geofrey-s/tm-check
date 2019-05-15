package edu.mum.tmcheck.domain.Repository;

import edu.mum.tmcheck.domain.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
