package raf.webProgramiranje.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class News {

    /*
    * Vest - Entitet koji sadrži naslov, tekst, vreme kreiranja, broj poseta, autora
    * (korisnika koji je kreirao vest),
    * komentare čitalaca, kao i tagove na osnovu kojih će se
    * raditi pretraga srodnih vesti. Vest pripada jednoj kategoriji.*/
    private Integer id;
    @NotEmpty (message = "title field is required")
    @NotNull(message = "title field is required")
    private String title;
    @NotEmpty
    private String text;
    private Date date;
    private int visits;
    @NotNull(message = "author field is required")
    private User author;
    private List<Comment> comment; //lista
    private List<Tag> tags;
    @NotNull(message = "category field is required")
    private Category category;
    public News(){}

    public News(Integer id, String title, String text, Date date, int visits, User author, List<Comment> comment, List<Tag> tags, Category category) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.visits = visits;
        this.author = author;
        this.comment = comment;
        this.tags = tags;
        this.category = category;
    }
    public News(Integer id, String title, String text, Date date, int visits, User author,  List<Tag> tags, Category category) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
        this.visits = visits;
        this.author = author;
        this.comment=new ArrayList<>();
        this.tags = tags;
        this.category = category;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public int getVisits() {
        return visits;
    }

    public User getAuthor() {
        return author;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Category getCategory() {
        return category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", visits=" + visits +
                ", author=" + author +
                ", comment=" + comment +
                ", tags=" + tags +
                ", category=" + category +
                '}';
    }
}
