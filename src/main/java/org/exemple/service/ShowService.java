package org.exemple.service;


import org.exemple.dao.LikeDaoImpl;
import org.exemple.dao.UserDaoImpl;
import org.exemple.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ShowService {
    private final UserDaoImpl userDao;
    private final LikeDaoImpl likeDao;
    public Integer clientId;

    public ShowService(UserDaoImpl userDao, LikeDaoImpl likeDao, Integer clientId) {
        this.userDao = userDao;
        this.likeDao = likeDao;
        this.clientId = clientId;
    }

    public Optional<User> getNextUser(){
        var allUserIds = userDao.findAllIds();
        var likedUserIds = likeDao.findAllUsersIdById(clientId);

        List<Integer> notLikedUserIds = allUserIds.stream()
                .filter(id -> !likedUserIds.contains(id))
                .toList();

        if (notLikedUserIds.isEmpty()) return Optional.empty();

        int randomId = notLikedUserIds.get(new Random().nextInt(notLikedUserIds.size()));
        return Optional.ofNullable(userDao.findById(randomId));
    }
}
