/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClient.Ejb;

import RestClient.Db.DbHelper;
import RestClient.Entity.User;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author wilzone
 */
@Stateless
public class UserRepositoryBean implements UserRepository {

    private final Map<String, User> users = new HashMap<>();

    @Override
    public User signIn(User user) {
        try {
            DbHelper.connectToDb();
            ResultSet rs = DbHelper.signInDataEntry(user);
            if (rs.next()) {
                int id = rs.getInt(1);
                user.setId(String.valueOf(id));
            }
            DbHelper.closeDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User signOut(String id, Date signOutTime) {
        if (users.containsKey(id)) {
            System.out.println("contained id " + id);
            users.get(id).setSignOutTime(signOutTime);
            return users.get(id);
        } else {
            System.out.println("not contained id " + id);
            return null;
        }
    }

    @Override
    public List<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            DbHelper.connectToDb();
            ResultSet rs = DbHelper.executeQuery("Select * From users");
            while (rs.next()) {
                User user = new User(rs.getString("firstname"), rs.getString("lastname"), rs.getDate("signInTime"), rs.getDate("signOutTime"), rs.getString("phoneNumber"));
                users.add(user);
            }
            DbHelper.closeDbConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}
