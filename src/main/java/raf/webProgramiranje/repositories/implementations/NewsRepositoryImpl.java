package raf.webProgramiranje.repositories.implementations;

import raf.webProgramiranje.entities.*;
import raf.webProgramiranje.repositories.AbstractMariaDBRepository;
import raf.webProgramiranje.repositories.specifications.NewsRepository;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryImpl extends AbstractMariaDBRepository implements NewsRepository {
    @Override
    public News createNews(News news) {
        System.out.println("XXXXXXXXXXXXXXXXXXX");
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                connection = this.newConnection();

                // Insert the post into the 'post' table
                /*  id;   title;  text; date; visits;  author; comment;tags; category;*/
                String[] generatedColumns = {"id","created_at"};
                preparedStatement = connection.prepareStatement
                        ("INSERT INTO news (title, text, fk_category_id,visits,fk_user_id) VALUES (?, ?, ?,?,?)", generatedColumns);
                preparedStatement.setString(1, news.getTitle());
                preparedStatement.setString(2, news.getText());
                preparedStatement.setInt(3, news.getCategory().getId());
                preparedStatement.setInt(4, 1);
                preparedStatement.setInt(5, news.getAuthor().getId());
                preparedStatement.executeUpdate();
                resultSet = preparedStatement.getGeneratedKeys();

                // Get the generated post ID
                if (resultSet.next()) {
                    int postId = resultSet.getInt(1);
                    news.setId(postId);
                }

                // Insert tags associated with the post into the 'post_tags' table
                List<Tag> tags = news.getTags();
                if (tags != null && !tags.isEmpty()) {
                    String[] generatedColumns2 = {"id"};
                    preparedStatement = connection.prepareStatement("INSERT INTO newstag (fk_news_id, fk_tag_id) VALUES (?, ?)",generatedColumns2);
                    for (Tag tag : tags) {
                        preparedStatement.setInt(1, news.getId());
                        preparedStatement.setInt(2, tag.getId());
                        preparedStatement.executeUpdate();
                    }
                }

                System.out.println("Post created successfully with ID: " + news.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.closeStatement(preparedStatement);
                this.closeResultSet(resultSet);
                this.closeConnection(connection);
            }

            return news;


    }

    @Override
    public List<News> getAllNews() {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from news   join user u on u.id = news.fk_user_id join category c on c.id = news.fk_category_id");
            while (resultSet!=null&&resultSet.next()) {

                User u=new User(
                        resultSet.getInt("u.id"),
                        resultSet.getString("u.first_name"),
                        resultSet.getString("u.last_name")
                );
                Category c=new Category(resultSet.getInt("c.id"),
                            resultSet.getString("c.name"),
                            resultSet.getString("c.description"));
                Statement s2= connection.createStatement();
                ResultSet rsTags= s2.executeQuery("select * from newstag join tag t on newstag.fk_tag_id = t.id where fk_news_id="+resultSet.getString("news.id"));
                List<Tag> tagList=new ArrayList<>();
                while (rsTags!=null&&rsTags.next()) {
                    tagList.add(new Tag(rsTags.getInt("t.id"),rsTags.getString("t.tag")));
                }
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getDate("created_at"),
                        resultSet.getInt("visits"),
                        u,
                       tagList,
                        c
                ));



                   }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public News getNewsById(Integer id) {
      return getNewsById(id,null);
    }


    @Override
    public News changeNews(News news1) {
        Connection connection = null;
        PreparedStatement updateStatement = null;
        System.out.println(news1);
        try {
            connection = this.newConnection();

            String updateQuery = "UPDATE news SET title = ?, text = ?, visits = ? WHERE id = ?";
            updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, news1.getTitle());
            updateStatement.setString(2, news1.getText());
            updateStatement.setInt(3, news1.getVisits());
            updateStatement.setInt(4, news1.getId());
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(updateStatement);
            this.closeConnection(connection);
        }

        return news1;
    }


    public boolean deleteNews(Integer newsID) {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        try {
            connection = this.newConnection();
                        //u newstag on delete je cascade tako da ce izbrisati i taj
            String deleteQuery = "DELETE FROM news WHERE id = ?";
            deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, newsID);
          int rowsAffected=  deleteStatement.executeUpdate();
            if(rowsAffected>0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(deleteStatement);
            this.closeConnection(connection);
        }

        return false;
    }


    @Override
    public News addTagToNews(News news, Tag tag) {
        Connection connection = null;
        PreparedStatement insertStatement = null;
        try {
            connection = this.newConnection();

            String insertQuery = "INSERT INTO newstag (fk_news_id, fk_tag_id) VALUES (?, ?)";
            insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, news.getId());
            insertStatement.setInt(2, tag.getId());
            insertStatement.executeUpdate();

            news.getTags().add(tag);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(insertStatement);
            this.closeConnection(connection);
        }

        return news;
    }


    public News removeTagFromNews(News news, Tag tag) {
        Connection connection = null;
        PreparedStatement deleteStatement = null;
        try {
            connection = this.newConnection();

            String deleteQuery = "DELETE FROM newstag WHERE fk_news_id = ? AND fk_tag_id = ?";
            deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, news.getId());
            deleteStatement.setInt(2, tag.getId());
            deleteStatement.executeUpdate();

            news.getTags().remove(tag);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(deleteStatement);
            this.closeConnection(connection);
        }

        return news;
    }
    @Override
    public News changeTagInNews(News news, List<Tag> tags){
        System.out.println(tags);

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();


            Statement statementNewsTag = connection.createStatement();
          ResultSet  resultSetNewsTag=statementNewsTag.executeQuery("select * from newstag  join tag  t on t.id=newstag.fk_tag_id where fk_news_id = "+news.getId()+";");
            //izbacuje sve tagove koji vec postoje u vesti
            List<Tag> toBeRem=new ArrayList<>();
          while(resultSetNewsTag.next()){
              Tag t=new Tag(resultSetNewsTag.getInt("fk_tag_id"),resultSetNewsTag.getString("t.tag"));
             if( !tags.remove(t)){
                 toBeRem.add(t);
             }
          }

          //brise sve tagove koji vise ne postoje u vesti
          for(Tag t:toBeRem) {
              String deleteQuery = "DELETE FROM newstag  WHERE fk_news_id = ? AND fk_tag_id = ?";
             PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
              deleteStatement.setInt(1, news.getId());
              deleteStatement.setInt(2, t.getId());
              deleteStatement.executeUpdate();

          }
          //ubacuje sve tagove u vest, koji vec postoje kao tagovi+
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from tag");

            while (resultSet!=null&&resultSet.next()) {
                Tag toBeRemoved=null;
                    for(Tag tag:tags){
                        if(tag.getTag().equalsIgnoreCase(resultSet.getString("tag"))){
                            String insertQuery = "INSERT INTO newstag (fk_news_id, fk_tag_id) VALUES (?, ?)";
                           PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                            insertStatement.setInt(1, news.getId());
                            insertStatement.setInt(2, resultSet.getInt("id"));
                            insertStatement.executeUpdate();
                            toBeRemoved=tag;
                            System.out.println(tag.getTag());
                            news.getTags().add(tag);
                            break;
                        }
                    }
                tags.remove(toBeRemoved);
            }
            System.out.println(tags);
            //pravi tagove koji en postoje, i onda ih ubacuje u vest
            for(Tag tag:tags){
                if(tag.getTag()!=null && !tag.getTag().isBlank()) {
                   String[] generatedColumns = {"id"};
                    PreparedStatement  preparedStatement = connection.prepareStatement("INSERT INTO tag (tag) VALUES ( ?)", generatedColumns);
                    preparedStatement.setString(1,tag.getTag());
                    preparedStatement.executeUpdate();

                    System.out.println("inserted"+tag.getTag());
                    resultSet = preparedStatement.getGeneratedKeys();
                    if (resultSet.next()) {
                        int tagId = resultSet.getInt(1);
                        tag.setId(tagId);
                        String insertQuery = "INSERT INTO newstag (fk_news_id, fk_tag_id) VALUES (?, ?)";
                        PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                        insertStatement.setInt(1, news.getId());
                        insertStatement.setInt(2, tag.getId());
                        insertStatement.executeUpdate();
                        news.getTags().add(tag);
                    }
                }
            }




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }


    @Override
    public List<News> getNewsByTag(Integer tagID) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String query = "SELECT * FROM newstag where fk_tag_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, tagID);

            resultSet = preparedStatement.executeQuery();

            while (resultSet != null && resultSet.next()) {

               int newsid= resultSet.getInt("fk_news_id");
               News newa= getNewsById(newsid,connection);
                System.out.println(newa);
                news.add(newa);

            }
            System.out.println(news);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }


    @Override
    public List<News> getNewsByCategory(Integer category) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String query = "SELECT * FROM news " +
                    "JOIN user u ON u.id = news.fk_user_id " +
                    "JOIN category c ON c.id = news.fk_category_id " +
                    "WHERE c.id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, category);

            resultSet = preparedStatement.executeQuery();
            while (resultSet != null && resultSet.next()) {
                User u=new User(
                        resultSet.getInt("u.id"),
                        resultSet.getString("u.first_name"),
                        resultSet.getString("u.last_name")
                );
                Category c = new Category(resultSet.getInt("c.id"),
                        resultSet.getString("c.name"),
                        resultSet.getString("c.description"));
                Statement s2 = connection.createStatement();
                ResultSet rsTags = s2.executeQuery("SELECT * FROM newstag " +
                        "JOIN tag t ON newstag.fk_tag_id = t.id " +
                        "WHERE fk_news_id = " + resultSet.getString("news.id"));
                List<Tag> tagList = new ArrayList<>();
                while (rsTags != null && rsTags.next()) {
                    tagList.add(new Tag(rsTags.getInt("t.id"), rsTags.getString("t.tag")));
                }
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getDate("created_at"),
                        resultSet.getInt("visits"),
                        u,
                        tagList,
                        c
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }










    private News getNewsById(Integer id,Connection connection) {
        News news = null;
        //ako se pravi konekcija, ugasi je, ako se nne pravi vec je prosledjena onda prepusti da ta metoda ugasi
        boolean yesSir=false;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            if(connection==null) {
                connection = this.newConnection();
                yesSir=true;
            }

            String query = "SELECT * FROM news " +
                    "JOIN user u ON u.id = news.fk_user_id " +
                    "JOIN category c ON c.id = news.fk_category_id " +
                    "WHERE news.id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                User u=new User(
                        resultSet.getInt("u.id"),
                        resultSet.getString("u.first_name"),
                        resultSet.getString("u.last_name")
                );
                Category c = new Category(resultSet.getInt("c.id"),
                        resultSet.getString("c.name"),
                        resultSet.getString("c.description"));
                Statement s2 = connection.createStatement();
                ResultSet rsTags = s2.executeQuery("SELECT * FROM newstag " +
                        "JOIN tag t ON newstag.fk_tag_id = t.id " +
                        "WHERE fk_news_id = " + resultSet.getString("news.id"));
                List<Tag> tagList = new ArrayList<>();
                while (rsTags != null && rsTags.next()) {
                    tagList.add(new Tag(rsTags.getInt("t.id"), rsTags.getString("t.tag")));
                }


               String incrQ=("UPDATE news SET visits = visits+1 WHERE id=?;");
                PreparedStatement increment=connection.prepareStatement(incrQ);
                increment.setInt(1,id);
                increment.executeUpdate();
                news = new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getDate("created_at"),
                        resultSet.getInt("visits")+1,
                        u,
                        tagList,
                        c
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            if(yesSir)
                this.closeConnection(connection);

        }

        return news;
    }

    @Override
    public List<News> getMostReadNews(){
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            long epochDate= LocalDateTime.now().minusDays(30).toEpochSecond(ZoneOffset.systemDefault().getRules().getOffset(Instant.now()));

            resultSet = statement.executeQuery("select * from news   join user u on u.id = news.fk_user_id join category c on c.id = news.fk_category_id where created_at>"+epochDate+" ORDER BY visits LIMIT 10");
            while (resultSet!=null&&resultSet.next()) {

                User u=new User(
                        resultSet.getInt("u.id"),
                        resultSet.getString("u.first_name"),
                        resultSet.getString("u.last_name")
                );
                Category c=new Category(resultSet.getInt("c.id"),
                        resultSet.getString("c.name"),
                        resultSet.getString("c.description"));
                Statement s2= connection.createStatement();
                ResultSet rsTags= s2.executeQuery("select * from newstag join tag t on newstag.fk_tag_id = t.id where fk_news_id="+resultSet.getString("news.id"));
                List<Tag> tagList=new ArrayList<>();
                while (rsTags!=null&&rsTags.next()) {
                    tagList.add(new Tag(rsTags.getInt("t.id"),rsTags.getString("t.tag")));
                }
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getDate("created_at"),
                        resultSet.getInt("visits"),
                        u,
                        tagList,
                        c
                ));



            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }

    @Override
    public List<News> getRecentNews(){
        List<News> news = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from news   join user u on u.id = news.fk_user_id join category c on c.id = news.fk_category_id ORDER BY created_at DESC LIMIT 10");
            while (resultSet!=null&&resultSet.next()) {

                User u=new User(
                        resultSet.getInt("u.id"),
                        resultSet.getString("u.first_name"),
                        resultSet.getString("u.last_name")
                        );
                Category c=new Category(resultSet.getInt("c.id"),
                        resultSet.getString("c.name"),
                        resultSet.getString("c.description"));
                Statement s2= connection.createStatement();
                ResultSet rsTags= s2.executeQuery("select * from newstag join tag t on newstag.fk_tag_id = t.id where fk_news_id="+resultSet.getString("news.id"));
                List<Tag> tagList=new ArrayList<>();
                while (rsTags!=null&&rsTags.next()) {
                    tagList.add(new Tag(rsTags.getInt("t.id"),rsTags.getString("t.tag")));
                }
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getDate("created_at"),
                        resultSet.getInt("visits"),
                        u,
                        tagList,
                        c
                ));



            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }


    @Override
    public List<News> getAllNews(int offset, int limit) {
        List<News> news = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("select * from news   join user u on u.id = news.fk_user_id join category c on c.id = news.fk_category_id LIMIT ? OFFSET  ?");
            statement.setInt(1,limit);
            statement.setInt(2,offset);
            resultSet = statement.executeQuery();
            while (resultSet!=null&&resultSet.next()) {

                User u=new User(
                        resultSet.getInt("u.id"),
                        resultSet.getString("u.first_name"),
                        resultSet.getString("u.last_name")
                );
                Category c=new Category(resultSet.getInt("c.id"),
                        resultSet.getString("c.name"),
                        resultSet.getString("c.description"));
                Statement s2= connection.createStatement();
                ResultSet rsTags= s2.executeQuery("select * from newstag join tag t on newstag.fk_tag_id = t.id where fk_news_id="+resultSet.getString("news.id"));
                List<Tag> tagList=new ArrayList<>();
                while (rsTags!=null&&rsTags.next()) {
                    tagList.add(new Tag(rsTags.getInt("t.id"),rsTags.getString("t.tag")));
                }
                news.add(new News(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getDate("created_at"),
                        resultSet.getInt("visits"),
                        u,
                        tagList,
                        c
                ));



            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return news;
    }
}
