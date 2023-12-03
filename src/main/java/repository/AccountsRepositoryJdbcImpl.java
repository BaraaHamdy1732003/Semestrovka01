package repository;

import models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountsRepositoryJdbcImpl implements AccountsRepository{
    private DataSource dataSource;
    //todo make a database tables okay
    private static final String SQL_INSERT="insert into rep_users(first_name,uname,age,password) values";

public AccountsRepositoryJdbcImpl(DataSource dataSource){
    this.dataSource= dataSource;
}
public void save(User user)throws SQLException{
    String sql= SQL_INSERT  +"(?,?,?,?)";
    Connection connection = dataSource.getConnection();
    PreparedStatement preparedStatement =connection.prepareStatement(sql);
    preparedStatement.setString(1, user.getFirstName());
    preparedStatement.setString(2, user.getUname());
    preparedStatement.setInt(3, user.getAge());
    preparedStatement.setString(5, user.getPassword());
    preparedStatement.executeUpdate();
    System.out.println("done!!");
}
}
