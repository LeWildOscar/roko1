package com.itesm.roko.dao.user_friend;

import com.itesm.roko.domain.User;
import com.itesm.roko.domain.User_friend;

import java.util.List;
import java.util.Optional;

public interface SqlUserFriendDAO {



    public Optional<User_friend> getByUuid (String uuid);
    public Optional<List<User_friend>> getByUserUsername(String username);
    public Optional<User_friend> insertUserFriend (User_friend user_friend);
    public Optional<Boolean> deleteUserFriend (User_friend user_friend);
    public Optional<User_friend> delete (User_friend user_friend);
    public Optional<User_friend> updateUserFriend (User_friend user_friend, String uuid);
    public Optional<List<User_friend>> list (int user_id);
    public Optional<List<User>> getFriendsList(String username);
    public Optional<List<User_friend>> checkFriendship (String username1,String username2);
    public Optional<Integer> getNumberFriendsByUsername (String username);
    public Optional<Integer> getNumberFriendsByUuid (String username);
    public Optional<User_friend> getFriendship (int user_id, int user_id1);

}
