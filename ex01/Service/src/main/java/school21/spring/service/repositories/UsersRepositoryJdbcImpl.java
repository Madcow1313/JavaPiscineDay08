package school21.spring.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Override
    public User findById(Long id) {
        User user;
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try{
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM shop.product WHERE identifier = " + id);
            if (resultSet.next()){
                user = new User(resultSet.getLong(1),
                        resultSet.getString(2));
                return user;
            }
        }
        catch (SQLException e){
            System.out.println("Something went wrong");
            System.out.println("Exception caught: " + e);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> allProducts = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM models.user");
            while (resultSet.next()){
                allProducts.add(new User(resultSet.getLong(1), resultSet.getString(2)));
            }
        }
        catch (SQLException e){
            System.out.println("Something went wrong");
            System.out.println("Exception caught: " + e);
        }

        return allProducts;
    }

    @Override
    public void save(User entity) {
        Connection connection;
        Statement statement;

        try{
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            statement.executeQuery("INSERT INTO models.user (identifier, email) VALUES ('"
                    + entity.getIdentifier() + "', '" + entity.getEmail() + "')");
        }
        catch (SQLException e){
            System.out.println("Something went wrong");
            System.out.println("Exception caught: " + e);
        }
    }

    @Override
    public void update(User entity) {
        Connection connection;
        PreparedStatement statement;

        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("UPDATE shop.product SET name = ?, price = ? WHERE identifier = ?");
            statement.setString(1, entity.getEmail());
            statement.setLong(2, entity.getIdentifier());
            statement.execute();

        }
        catch (SQLException e){
            System.out.println("Something went wrong");
            System.out.println("Exception caught: " + e);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection;
        PreparedStatement statement;

        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement("DELETE FROM models.user WHERE identifier = " + id);
            statement.execute();
        }
        catch (SQLException e){
            System.out.println("Something went wrong");
            System.out.println("Exception caught: " + e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM models.user WHERE email = ?");
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            if (resultSet.next()){
                return Optional.of(new User(resultSet.getLong(1), resultSet.getString(2)));
            }
        }
        catch (SQLException e){
            System.out.println("Something went wrong");
            System.out.println("Exception caught: " + e);
        }

        return Optional.empty();
    }
}
