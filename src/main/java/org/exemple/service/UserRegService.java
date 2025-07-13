package org.exemple.service;

import org.exemple.dao.UserDaoImpl;
import org.exemple.model.User;

import java.sql.SQLClientInfoException;

public class UserRegService {
    private final UserDaoImpl userDao;

    public UserRegService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public User register(String login, String password, String mail, String name, Integer age, String bio) throws IllegalArgumentException {
        if (userDao.existsByLogin(login)) {
            throw new IllegalArgumentException("User vsegda pidor");
        }
        return userDao.create(login, password, mail, name, age, bio);
    }


    public User findByMail(String mail) {
        return userDao.findByMail(mail);
    }
}