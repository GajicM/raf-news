package raf.webProgramiranje.repositories.implementations;

import raf.webProgramiranje.entities.User;
import raf.webProgramiranje.exceptions.ResourceNotChangeableException;
import raf.webProgramiranje.exceptions.TestException;
import raf.webProgramiranje.repositories.AbstractMariaDBRepository;
import raf.webProgramiranje.repositories.specifications.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl extends AbstractMariaDBRepository implements UserRepository {
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from user");
            while (resultSet!=null&&resultSet.next()) {
             User u   =new User(resultSet.getInt("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),resultSet.getString("password"));
             u.setUserStatus(resultSet.getBoolean("status"));
             u.setUserType(resultSet.getInt("user_type"));
                users.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }

    public User getUser(Integer id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user=null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select * from user where id=?");
            statement.setInt(1,id);
            resultSet = statement.executeQuery();

            if (resultSet!=null && resultSet.next()) {
                user=new User(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"),resultSet.getString("password") );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return user;
    }

    public User addUser(User user){

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id","status"};
            preparedStatement = connection.prepareStatement("INSERT INTO user (first_name,last_name,email,password,user_type) VALUES(?, ?, ?, ? , ?) ON DUPLICATE KEY UPDATE email = email;", generatedColumns);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setInt(5,user.getUserType());
           int rowsAffected= preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }else   if(rowsAffected==1 ){

                throw new ResourceNotChangeableException("User email="+user.getEmail()+" cannot be added, already exists");
            }

        } catch (SQLException e) {
            e.printStackTrace();
          throw new ResourceNotChangeableException("User email="+user.getEmail()+" cannot be added, already exists");


        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return user;
    }
    public User changeUserStatus(User user){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();



            preparedStatement = connection.prepareStatement("UPDATE user SET status = ? WHERE id= ?");
            preparedStatement.setBoolean(1, !user.isUserStatus());
            preparedStatement.setInt(2, user.getId());
           int rowsAffected= preparedStatement.executeUpdate();


            if (rowsAffected>0) {

                user.setUserStatus(!user.isUserStatus());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return user;
    }

    @Override
    public User findUser(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user=null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select * from user where email=?");
            statement.setString(1,email);
            resultSet = statement.executeQuery();

            if (resultSet!=null && resultSet.next()) {
                user=new User(resultSet.getInt("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"),resultSet.getString("password") );
                user.setUserStatus(resultSet.getBoolean("status"));
                user.setUserType(resultSet.getInt("user_type"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return user;
    }

    @Override
    public User changeUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("UPDATE user SET user_type = ?, first_name=?, last_name=?, email=? WHERE id= ?");
            preparedStatement.setBoolean(1, user.getUserType()%2!=0);
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getId());
           int rowsAffected= preparedStatement.executeUpdate();


            if (rowsAffected>0) {
              return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResourceNotChangeableException("Cannot change user name:"+user.getFirstName()+" "+ user.getLastName()+ " bc email: "+user.getEmail() +" already exists");
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return null;
    }

    @Override
    public List<User> getAllUsers(int limit, int offset) {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select * from user LIMIT ?  OFFSET ?");
            statement.setInt(1,limit);
            statement.setInt(2,offset);
            resultSet = statement.executeQuery();
            while (resultSet!=null&&resultSet.next()) {
                User u   =new User(resultSet.getInt("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),resultSet.getString("password"));
                u.setUserStatus(resultSet.getBoolean("status"));
                u.setUserType(resultSet.getInt("user_type"));
                users.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }
}
