package raf.webProgramiranje.entities;

import java.util.List;

public class Tag {
    /*
    * Tag - Ključna reč ili više reči koje usko opisuju vest.
    *  Vest može imati više tagova i jedan tag može pripadati više vesti.
     */
    private Integer id;
    private String tag; //moze vise
    public Tag(){}
    public Tag(String tag){
        this.tag=tag;
    }
    public Tag(int id,String tag){
        this.tag=tag;
        this.id=id;
    }

    public Integer getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
