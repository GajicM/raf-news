package raf.webProgramiranje.repositories.implementations;

import raf.webProgramiranje.entities.Tag;
import raf.webProgramiranje.repositories.AbstractMariaDBRepository;
import raf.webProgramiranje.repositories.specifications.TagRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TagRepositoryImpl extends AbstractMariaDBRepository implements TagRepository {
    @Override
    public Tag addTag(Tag tag){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO tag (tag) VALUES(?)", generatedColumns);
            preparedStatement.setString(1, tag.getTag());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                tag.setId(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tag;

    }
    @Override
    public Tag deleteTag(Tag tag){

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM tag WHERE id = ?");
            preparedStatement.setInt(1, tag.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                tag=new Tag();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
        return tag;
    }
    @Override
    public List<Tag> getAllTags(){
        List<Tag> tags = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tag");
            while (resultSet!=null&&resultSet.next()) {
                tags.add(new Tag(resultSet.getInt("id"),
                        resultSet.getString("tag")

                ));

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return tags;
    }
}
