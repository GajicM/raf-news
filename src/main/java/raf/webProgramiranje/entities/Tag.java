package raf.webProgramiranje.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Tag {
    /*
    * Tag - Ključna reč ili više reči koje usko opisuju vest.
    *  Vest može imati više tagova i jedan tag može pripadati više vesti.
     */
    private Integer id;
    @NotNull(message = "tag field is required")
    @NotEmpty(message = "name field is required")
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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Tag){
            return ((Tag) obj).tag.equalsIgnoreCase(tag) || (((Tag) obj).id!=null &&((Tag) obj).id.equals(id));
        }else return false;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                '}';
    }
}
