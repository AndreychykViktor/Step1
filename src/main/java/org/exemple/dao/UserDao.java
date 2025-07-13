package org.exemple.dao;

import org.exemple.model.User;

public interface UserDao {
    User findByLogin(String login);
    User create(String login, String password, String mail, String name, Integer age, String bio);
    User findByMail(String mail);
    boolean existsByLogin(String login);
}