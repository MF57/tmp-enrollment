package org.tmp.enrollment.data;

import org.springframework.data.repository.CrudRepository;
import org.tmp.enrollment.domain.entities.User;

public interface UserRepository extends CrudRepository<String, User> {

    User findByName(String name);
}
