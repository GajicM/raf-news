package raf.webProgramiranje.entities;

import java.util.List;

public class Tag {
    /*
    * Tag - Ključna reč ili više reči koje usko opisuju vest.
    *  Vest može imati više tagova i jedan tag može pripadati više vesti.
     */
    private Integer id;
    private String tag; //moze vise
    private List<News> newsList;
}
