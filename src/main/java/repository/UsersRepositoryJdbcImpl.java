package repository;


import models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
   private final static String SQL_SELECT_ALL = "select * from rep_users";
    private final static String SQL_INSERT = "INSERT INTO rep_users(first_name, uname, age,  password ) VALUES ";
    private static final String SQL_SELECT_BY_AGE = "SELECT * FROM rep_users WHERE age = ?";
    private static final String SQL_INSERT_COOKIE_USER = "INSERT INTO cookie_users(uuid, id) VALUES (?, ?)";
    private static final String SQL_SELECT_USER_BY_UUID = "SELECT * FROM rep_users WHERE id IN (SELECT id FROM cookie_users WHERE uuid = ?)";
    private final static String SQL_UPDATE_USER = "UPDATE rep_users SET first_name=?,age=? WHERE id=?";

    private final static String SQL_SELECT_BY_ID="SELECT * FROM rep_users WHERE id = ? ";

    private final static String SELECT_USER_BY_LOGIN = "SELECT * FROM rep_users WHERE uname=?";
//    private final Connection connection;
//    private final Statement statement;

    private  final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
//        this.connection = connection;
//        this.statement = statement;
        this.dataSource = dataSource;
    }

    @Override
    public void save(User model) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT+"(?,?,?,?)");
            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getUname());
            preparedStatement.setInt(3, model.getAge());
            preparedStatement.setString(5, model.getPassword());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Cannot insert user");
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User findByEmail(String login) {
        User user = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .uname(resultSet.getString("uname"))
                        .password(resultSet.getString("password"))
                        .age(resultSet.getInt("age"))
                        .build();
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findAllByAge(int age) {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_AGE);
            preparedStatement.setInt(1, age);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = User.builder()
                            .id(resultSet.getInt("id"))
                            .firstName(resultSet.getString("first_name"))
                            .uname(resultSet.getString("uname"))
                            .age(resultSet.getInt("age"))
                            .password(resultSet.getString("password"))
                            .build();

                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
                ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .uname(resultSet.getString("uname"))
                        .age(resultSet.getInt("age"))
                        .password(resultSet.getString("password"))
                        .build();

                users.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findByLogin(User login) {
        return Optional.empty();
    }

    @Override
    public void saveCookieUser(String uuid, int userId) {
        try (Connection connection = dataSource.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_COOKIE_USER);
            preparedStatement.setString(1, uuid);
            preparedStatement.setInt(2, userId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows != 1) {
                throw new SQLException("Cannot insert cookie user");
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public User findUserByUuid(String uuid) {
        try (Connection connection = dataSource.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_UUID);
            preparedStatement.setString(1, uuid);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return User.builder()
                            .id(resultSet.getInt("id"))
                            .firstName(resultSet.getString("first_name"))
                            .uname(resultSet.getString("uname"))
                            .age(resultSet.getInt("age"))
                            .password(resultSet.getString("password"))
                            .build();
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }
    @Override
    public void updateUserProfile(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setInt(2, user.getAge());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public User findById(int id) {
        User user = null;
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = User.builder()
                            .id(resultSet.getInt("id"))
                            .firstName(resultSet.getString("first_name"))
                            .uname(resultSet.getString("uname"))
                            .age(resultSet.getInt("age"))
                            .password(resultSet.getString("password"))
                            .build();
                }
                return user;
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean authenticateUser(String uname, String password) {
        User user = findByEmail(uname);

        System.out.println(user.getUname()+"->"+uname+"\n"+user.getPassword()+"->"+password);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findByLogin(String email) {
        return null;
    }
}



