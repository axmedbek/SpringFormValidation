package mvc.dao;

import mvc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by axmedbek on 9/4/17.
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void saveUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.save(user);
        tr.commit();

    }

    public User findUserById(int id) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        User user = session.load(User.class,id);
        tr.commit();
        return user;
    }

    public User findUserByName(String name) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        User user = (User) session.createQuery("from User where name=:name").setParameter("name",name).uniqueResult();
        tr.commit();
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> userList() {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        List<User> userList = session.createQuery("from User").list();
        tr.commit();
        return userList;
    }

    public void deleteUser(String name) {

        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.delete(findUserByName(name));
        tr.commit();

    }

    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(user);
        tr.commit();
    }
}
