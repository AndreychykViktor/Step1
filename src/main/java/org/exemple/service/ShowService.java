package org.exemple.service;


import org.exemple.dao.UserDaoImpl;
import org.exemple.model.User;

public class ShowService {
    private final UserDaoImpl userDao;
    public Integer clientId;

    public ShowService(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public User getUser() {

    }


}
