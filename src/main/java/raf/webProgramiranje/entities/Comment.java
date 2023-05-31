package raf.webProgramiranje.entities;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Comment {
    /*Komentar - Entitet koji sadrži ime autora komentara
    (unosi se pri pisanju komentara), tekst komentara i datum kreiranja. Pripada jednoj vesti.
     */
    private Integer id;
    @NotNull(message = "username field is required")
    private String username;
    @NotNull(message = "comment field is required")
    private String comment;
    private Date timeCreated;

    private News news;
    private Integer newsID;

    public Comment(){}
    public Comment(Integer id, String username,String comment, Date timeCreated,News news){
        this.id=id;
        this.username=username;
        this.comment=comment;
        this.timeCreated=timeCreated;
        this.news=news;
        this.newsID=news.getId();
    }
    public Comment(Integer id, String username, String comment, Date timeCreated, Integer newsID){
        this.id=id;
        this.username=username;
        this.comment=comment;
        this.timeCreated=timeCreated;
        this.newsID=newsID;
    }

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getUsername() {
        return this.username;
    }

    public String getComment() {
        return comment;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public News getNews() {
        return news;
    }

    public Integer getId() {
        return id;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }
}
