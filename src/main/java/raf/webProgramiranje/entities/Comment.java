package raf.webProgramiranje.entities;

import java.util.Date;

public class Comment {
    /*Komentar - Entitet koji sadrži ime autora komentara
    (unosi se pri pisanju komentara), tekst komentara i datum kreiranja. Pripada jednoj vesti.
     */
    private Integer id;
    private String username;
    private String comment;
    private Date timeCreated;

    private News news;

    public Comment(){}
    public Comment(Integer id, String username,String comment, Date timeCreated,News news){
        this.id=id;
        this.username=username;
        this.comment=comment;
        this.timeCreated=timeCreated;
        this.news=news;
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
