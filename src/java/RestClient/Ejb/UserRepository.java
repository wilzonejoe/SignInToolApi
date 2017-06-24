/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClient.Ejb;

import RestClient.Entity.User;
import java.util.Date;
import java.util.List;

/**
 *
 * @author wilzone
 */
public interface UserRepository {
    User signIn(final User user);
    List<User> getUsers();
    User signOut(String id, Date signOutTime);
}
