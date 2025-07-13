package org.exemple.dao;

import org.exemple.model.User;

import java.util.Collection;

public interface UserDao {
    User findByLogin(String login);
    User create(String login, String password, String mail, String name, Integer age, String bio);
    User findByMail(String mail);
    boolean existsByLogin(String login);
    User findById(int id);
    Collection<Integer> findAllIds();
}