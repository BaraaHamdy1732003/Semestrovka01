package repository;

import models.User;

import java.util.List;

public interface UsersRepository extends CrudRepository<User> {
    User findByEmail(String login);

    List <User> findAllByAge(int age);

    void saveCookieUser(String uuid, int userId);

    User findUserByUuid(String uuid);
//void updateUserProfile(int id,String firstName, String lastName, Integer age ,String email, String password, Integer phone);

    void updateUserProfile(User user);
    User findById(int id);
    boolean authenticateUser(String uname, String password);
    //TODO update methoed for profile

    User findByLogin(String uname);
}