package raf.webProgramiranje.entities;

import java.util.Date;

public class Comment {
    /*Komentar - Entitet koji sadrži ime autora komentara
    (unosi se pri pisanju komentara), tekst komentara i datum kreiranja. Pripada jednoj vesti.
     */

    private String username;
    private String comment;
    private Date timeCreated;

    private News news;
}
