package com.assf.bsspringboot.dao;

import com.assf.bsspringboot.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByNameAndPassword(String name, String password);
}
