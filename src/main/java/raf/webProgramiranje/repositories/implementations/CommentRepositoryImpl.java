package raf.webProgramiranje.repositories.implementations;

import raf.webProgramiranje.entities.Comment;
import raf.webProgramiranje.entities.News;
import raf.webProgramiranje.repositories.AbstractMariaDBRepository;
import raf.webProgramiranje.repositories.specifications.CommentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl extends AbstractMariaDBRepository implements CommentRepository {

    @Override
    public Comment addComment(Comment comment){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id","time_created"};

            preparedStatement = connection.prepareStatement("INSERT INTO comment (username,comment,fk_news_id) VALUES( ? , ?,?)", generatedColumns);
            preparedStatement.setString(1, comment.getUsername());
            preparedStatement.setString(2, comment.getComment());
            preparedStatement.setInt(3,comment.getNews().getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;

    }
    @Override
    public Comment deleteComment(Comment comment){

            Connection connection = null;
            PreparedStatement preparedStatement = null;

            try {
                connection = this.newConnection();

                preparedStatement = connection.prepareStatement("DELETE FROM comment WHERE id = ?");
                preparedStatement.setInt(1, comment.getId());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    comment=new Comment();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.closeStatement(preparedStatement);
                this.closeConnection(connection);
            }
            return comment;
        }

        @Override
        public List<Comment> getAllCommentsForNews(News news){
            List<Comment> comments = new ArrayList<>();

            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            try {
                connection = this.newConnection();

                statement = connection.createStatement();
                resultSet = statement.executeQuery("select * from comment where fk_news_id="+news.getId());
                while (resultSet!=null&&resultSet.next()) {
                    comments.add(new Comment(resultSet.getInt("id"),
                                resultSet.getString("username"),
                            resultSet.getString("comment"),
                            resultSet.getDate("time_created"),
                            news
                            ));

                         }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.closeStatement(statement);
                this.closeResultSet(resultSet);
                this.closeConnection(connection);
            }

            return comments;
        }

    }



