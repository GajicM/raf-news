package raf.webProgramiranje.repositories.implementations;


import raf.webProgramiranje.exceptions.ResourceNotChangeableException;
import raf.webProgramiranje.entities.Category;
import raf.webProgramiranje.repositories.AbstractMariaDBRepository;
import raf.webProgramiranje.repositories.specifications.CategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl extends AbstractMariaDBRepository implements CategoryRepository {
    @Override
    public Category addCategory(Category category){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};


            preparedStatement = connection.prepareStatement("INSERT INTO category (name, description) VALUES (?, ?) ON DUPLICATE KEY UPDATE name = name;", generatedColumns);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());

         int rowsAffected=   preparedStatement.executeUpdate();
            System.out.println(rowsAffected);

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                category.setId(resultSet.getInt(1));
            }else   if(rowsAffected==1 ){
                System.out.println(rowsAffected);
                throw new ResourceNotChangeableException("cannot add same category");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResourceNotChangeableException("cannot add same category");
        }
        finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return category;

    }
    @Override
    public boolean deleteCategory(Integer category){

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM category WHERE id = ?");
            preparedStatement.setInt(1, category);

          int rowsAffected=  preparedStatement.executeUpdate();
            if(rowsAffected>0)return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResourceNotChangeableException("Category id="+category+" cannot be deleted while it has news");
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

        return false;
    }
    @Override
    public List<Category> getAllCategories(){
        List<Category> categories = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from category");
            while (resultSet!=null&&resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                ));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return categories;
    }
@Override
public Category changeCategory(Category category) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
        connection = this.newConnection();
       preparedStatement = connection.prepareStatement("UPDATE category SET name = ?, description = ? WHERE id = ?");
        preparedStatement.setString(1, category.getName());
        preparedStatement.setString(2, category.getDescription());
        preparedStatement.setInt(3, category.getId());

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            return category;
        }

    } catch (SQLException e) {
        e.printStackTrace();
        throw new ResourceNotChangeableException("Cannot change category name:"+category.getName()+" bc it already exists");
    } finally {
        this.closeStatement(preparedStatement);
        this.closeConnection(connection);
    }

    return null; // Update failed
}

    @Override
    public List<Category> getAllCategories(int offset, int limit) {
        List<Category> categories = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM category  LIMIT ? OFFSET ?");
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                categories.add(new Category(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description")));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ResourceNotChangeableException("Something went wrong ");
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return categories;
    }



    }


