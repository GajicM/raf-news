package raf.webProgramiranje.entities;

import java.util.Date;
import javax.validation.constraints.*;
public class News {

    /*
    * Vest - Entitet koji sadrži naslov, tekst, vreme kreiranja, broj poseta, autora
    * (korisnika koji je kreirao vest),
    * komentare čitalaca, kao i tagove na osnovu kojih će se
    * raditi pretraga srodnih vesti. Vest pripada jednoj kategoriji.*/


    private String title;
    private String text;
    private Date date;
    private int visits;

    private User author;
    private Comment comment; //lista
    private Tag tag;
    private Category category;

}
