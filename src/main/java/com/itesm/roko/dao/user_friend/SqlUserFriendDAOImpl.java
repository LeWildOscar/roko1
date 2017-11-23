package com.itesm.roko.dao.user_friend;


import com.itesm.roko.dao.UserDAO;
import com.itesm.roko.dao.user_friend.SqlUserFriendDAO;
import com.itesm.roko.domain.User;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.itesm.roko.domain.User_friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SqlUserFriendDAOImpl implements SqlUserFriendDAO {

    @Autowired
    protected JdbcTemplate jdbcTemplate;
    @Autowired
    UserDAO daoUsuario;

    private static final Logger logger = LoggerFactory.getLogger(User_friend.class);


    @Override
    public Optional<User_friend> getByUuid (String uuid) {
        String sql = "SELECT * FROM user_friend WHERE uuid = ?";
        try {
            BeanPropertyRowMapper<User_friend> rowMapper = new BeanPropertyRowMapper<>(User_friend.class);
            User_friend user_friend = jdbcTemplate.queryForObject(sql,rowMapper,uuid);
            logger.debug("Getting user_friend with uuid:" + uuid);
            return Optional.of(user_friend);
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("No se encuetro el user_friend con uuid: "+uuid);
        }
        return Optional.empty();
    }


    @Override
    public Optional<User_friend> insertUserFriend (User_friend user_friend) {
        String newUuid = UUID.randomUUID().toString();
        try {
            jdbcTemplate.update("INSERT INTO user_friend (uuid,user_id,user_id1) VALUES (?,?,?)",newUuid,user_friend.getUser_id(),user_friend.getUser_id1());
            logger.debug("Inserting user friend");
            return getByUuid(newUuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public Optional<Boolean> deleteUserFriend (User_friend user_friend) {
        try {
            jdbcTemplate.update("DELETE FROM user_friend WHERE uuid = ?",user_friend.getUuid());
            return Optional.of(new Boolean(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User_friend> delete(User_friend user_friend) {
        String sql = "DELETE FROM user_friend WHERE uuid = ?";
        try {
            jdbcTemplate.update(sql,user_friend.getUuid());
            return Optional.of(user_friend);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User_friend> updateUserFriend (User_friend user_friend, String uuid) {
        Date on_update = new Date();
        int user_id = user_friend.getUser_id();
        int user_id1 = user_friend.getUser_id1();
        try {
            BeanPropertyRowMapper<User_friend> rowMapper = new BeanPropertyRowMapper<>(User_friend.class);
            jdbcTemplate.update("UPDATE user SET on_updated = ?,user_id = ?,user_id1 = ? WHERE uuid = ?",rowMapper,on_update,user_id,user_id1,uuid);
            logger.debug("User_friend con uuid: "+uuid +" modificado");
            User_friend aux_user_friend = getByUuid(uuid).get();
            return Optional.of(aux_user_friend);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public Optional<List<User_friend>> list (int user_id) {
        String sql = "SELECT * FROM user_friend WHERE user_id = ?";
        try {
            List<User_friend> user_friends = jdbcTemplate.query(sql,new BeanPropertyRowMapper<User_friend>(),user_id);
            logger.debug("Obteniendo los amigos del usuario con id: "+user_id);
            return Optional.of(user_friends);

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("No se encontraron user_friend para el user : "+user_id);
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<User_friend>> getByUserUsername(String username) {
        User user = daoUsuario.getByUsername(username).get();
        try {
            int user_id = user.getId();
            return list(user_id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    @Override
    public Optional<List<User>> getFriendsList(String username) {

        try {
            User user = daoUsuario.getByUsername(username).get();
            int user_id = user.getId();
            String sql = "SELECT user.id, user.uuid,user.username,user.email," +
                    "user.password,user.on_create,user.on_update,user.total_points,user.losts," +
                    "user.played,user.country,user.total_money,user.money_in,user.money_out," +
                    "user.wins FROM user join user_friend on user.id = user_friend.user_id1 where" +
                    " user_friend.user_id = ? order by total_points desc";

            List<User> user_friends = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class),user_id);
            return Optional.of(user_friends);
        } catch (Exception e) {
            System.out.println(e.getMessage() +" getFriendList");

            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<User_friend>> checkFriendship(String username1, String username2) {
        User usuario1 = daoUsuario.getByUsername(username1).get();
        User usuario2 = daoUsuario.getByUsername(username1).get();
        String sql = "SELECT * FROM user_friend WHERE (user_id = ? AND user_id1 = ?) OR (user_id1 = ? AND user_id = ?)";

        try {
            List<User_friend> user_friends = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User_friend.class),usuario1.getId(),usuario2.getId(),usuario2.getId(),usuario1.getId());
            return Optional.of(user_friends);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getNumberFriendsByUsername (String username) {
        User user = daoUsuario.getByUsername(username).get();
        String sql = "SELECT count(user_id1) FROM user_friends WHERE user_id = ? GROUP BY user_id";
        try {
            Integer numAmigos = jdbcTemplate.queryForObject(sql, new Object[]{user.getId()},Integer.class);
            return Optional.of(numAmigos);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getNumberFriendsByUuid(String uuid) {
    User user = daoUsuario.getByUuid(uuid).get();
    String sql = "SELECT count(user_id1) FROM user_friends WHERE user_id = ? GROUP BY user_id";
        try {
        Integer numAmigos = jdbcTemplate.queryForObject(sql, new Object[]{user.getId()},Integer.class);
        return Optional.of(numAmigos);
    } catch (Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
        return Optional.empty();
}
    /**
     * Obtiene el objeto user_friend en caso de que haya una entrada en la
     * tabla de amigos de 'user_id' con user_id1 = 'user_id1'
     *
     * user_id sigue a user_id1 -> user_id deja de seguir a user_id1
     *
     * */
    @Override
    public Optional<User_friend> getFriendship(int user_id, int user_id1) {
        String sql = "SELECT * FROM user_friend WHERE user_id = ? AND user_id1 = ?";
        try {
            User_friend user_friend = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User_friend.class),user_id,user_id1);
            return Optional.of(user_friend);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
