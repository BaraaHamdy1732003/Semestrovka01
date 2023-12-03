package repository;

import models.User;

import java.sql.SQLException;

public interface AccountsRepository {
    void save(User user) throws SQLException;
}
