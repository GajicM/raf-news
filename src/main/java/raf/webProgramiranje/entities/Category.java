package raf.webProgramiranje.entities;


public class Category {

    /*
    * Kategorija - Sistem se sastoji od vesti koje pripadaju određenoj kategoriji
    * (vesti su grupisane u kategorije). Kategorija ima jedinstveno ime i opis.
     */
    private Integer id;
    private String name;
    private String description;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Category(){}
    public Category(String name, String description){
        this.name=name;
        this.description=description;
    }
    public Category(int id,String name, String description){
        this.id=id;
        this.name=name;
        this.description=description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }
}
