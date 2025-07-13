package org.exemple.dao;

import org.exemple.model.Like;

import java.util.Collection;

public interface LikeDao {
    Like create(int clientId, int userId);
    boolean exists(int clientId, int userId);
    Collection<Integer> findAllUsersIdById(int clientId);
    Collection<Integer> findAllMutualLikes(int clientId);
}
