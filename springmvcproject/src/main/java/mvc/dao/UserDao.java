package mvc.dao;

import mvc.model.User;

import java.util.List;

/**
 * Created by axmedbek on 9/4/17.
 */
public interface UserDao {

    void saveUser(User user);
    User findUserById(int id);
    User findUserByName(String name);
    List<User> userList();
    void deleteUser(String name);
    void updateUser(User user);


}
