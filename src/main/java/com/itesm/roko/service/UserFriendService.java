package com.itesm.roko.service;


import com.itesm.roko.dao.UserDAO;
import com.itesm.roko.domain.User;
import com.itesm.roko.domain.User_friend;
import com.itesm.roko.dao.user_friend.SqlUserFriendDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserFriendService {

    @Autowired
    private SqlUserFriendDAOImpl userFriendDAO;
    @Autowired
    private UserDAO userDAO;



    public Optional<User_friend> getByUuid(String uuid) {
        return userFriendDAO.getByUuid(uuid);
    }


    public Optional<User_friend> insertUserFriend(User user, String username) {
        User_friend user_friend = new User_friend();
        User aux_user = userDAO.getByUsername(username).get();
        user_friend.setUser_id(aux_user.getId());
        user_friend.setUser_id1(user.getId());
        return userFriendDAO.insertUserFriend(user_friend);
    }


    public Optional<Boolean> deleteUserFriendAll (String username, String uuid) {
        User user = userDAO.getByUuid(uuid).get();
        List<User_friend> amistad = getFriendShip(username,user.getUsername()).get();
        for (User_friend user_friend : amistad) {
            userFriendDAO.deleteUserFriend(user_friend);
        }
        return Optional.of(new Boolean(true));
    }

    public Optional<User_friend> borrarAmigo (String username, String uuid) {
        User user = userDAO.getByUsername(username).get();
        User user1 = userDAO.getByUuid(uuid).get();
        int user_id = user.getId();
        int user_id1 = user1.getId();
        User_friend user_friend = userFriendDAO.getFriendship(user_id,user_id1).get();
        return userFriendDAO.delete(user_friend);
    }


    public Optional<User_friend> updateUserFriend(User_friend user_friend, String uuid) {
        return userFriendDAO.updateUserFriend(user_friend,uuid);
    }


    public Optional<List<User_friend>> list(int user_id) {
        return userFriendDAO.list(user_id);
    }


    /*Devuelve la lista de usuarios que tienen amistan con username*/

    public Optional<List<User>> getFriendsList(String username) {
        return userFriendDAO.getFriendsList(username);
    }

    public Optional<List<User_friend>> getFriendShip(String username1, String username2) {
        return userFriendDAO.checkFriendship(username1,username2);
    }
}
