package com.assf.bsspringboot.init;

import com.assf.bsspringboot.dao.UserRepository;
import com.assf.bsspringboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitProject implements ApplicationRunner {

    @Autowired
    private UserRepository userDao;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Iterable<User> allUsers = userDao.findAll();
        if (!allUsers.iterator().hasNext()){
            User user = new User();
            user.setName("admin");
            user.setPassword("123");
            user.setRole("0");
            userDao.save(user);
        }
    }
}
