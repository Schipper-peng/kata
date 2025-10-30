package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Array.setLong;
import static java.sql.DriverManager.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    Connection connection = getConnection();

    public void createUsersTable(User user) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO USER(ID, NAME, LASTNAME, AGE) VALUES(?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setByte(4, user.getAge());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if(preparedStatement != null) {
                preparedStatement.close();
            } if (connection != null) {
                connection.close();
            }

    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
public class AdressService extends Util implements AdressDAO {


    @Override
    public List<Adress> getAll() throws SQLException {
        List<Adress> adressList = new ArrayList<>();
        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADRESS";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Adress adress = new Adress();
                adress.setId(resultSet.getLong("ID"));
                adress.setCountry(resultSet.getString("COUNTRY"));
                adress.setCity(resultSet.getString("CITY"));
                adress.setStreet(resultSet.getString("STREET"));
                adress.setPostCode(resultSet.getString("POST_CODE"));
                adressList.add(adress);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return adressList;
    }

    @Override
    public Adress getById(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADRESS WHERE ID=?";
        Adress adress = new Adress();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            adress.setId(resultSet.getLong("ID"));
            adress.setCountry(resultSet.getString("COUNTRY"));
            adress.setCity(resultSet.getString("CITY"));
            adress.setStreet(resultSet.getString("STREET"));
            adress.setPostCode(resultSet.getString("POST_CODE"));
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return adress;
    }

    @Override
    public void update(Adress adress) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE ADRESS SET COUNTRY=?, CITY=?, STREET=?, POST_CODE=?, WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, adress.getCountry());
            preparedStatement.setString(2, adress.getCity());
            preparedStatement.setString(3, adress.getStreet());
            preparedStatement.setString(4, adress.getPostCode());
            preparedStatement.setLong(5, adress.getId());
            preparedStatement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void remove(Adress adress) throws SQLException {
        PreparedStatement preparedStatement =  null;
        String sql = "DELETE FROM ADRESS WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, adress.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

    }
}