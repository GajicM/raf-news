package raf.webProgramiranje.entities;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class User {

        private Integer id;
/*
* Korisnik - Entitet koji sadrži jedinstveni email, ime i prezime, tip korisnika
* (content creator ili admin), status (aktivan/neaktivan) i lozinku. Entitet uz pomoć kog se pristupa
* CMS-u i koji može biti autor vesti.
* Na osnovu statusa je moguće zabraniti pristup CMS-u. Lozinke se čuvaju isključivo kao heš vrednosti.*/

        @NotNull(message = "email field is required")
        @NotEmpty(message = "email field is required")
        private String email;
    @NotNull(message = "name field is required")
    @NotEmpty(message = "name field is required")
        private String firstName,lastName;

        private int userType;

        private boolean userStatus;

        @NotNull(message = "password is required")
        @NotEmpty(message = "password is required")
        private String password; //cuva hes


        public User() {
        }

        public User(String firstName, String lastName,String email,String password) {
            this();
            this.firstName = firstName;
            this.lastName = lastName;
            this.email=email;
            this.password=password;
        }

        public User(Integer id, String firstName, String lastName,String email,String password) {
            this(firstName,lastName,email,password);
            this.id = id;
        }
        public User(Integer id, String firstName, String lastName){
            this.id=id;
            this.firstName=firstName;
            this.lastName=lastName;
        }

    public Integer getId() {
        return id;
    }

    public int getUserType() {
        return userType;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public String getPassword() {
            return this.password;
    }


    public void setId(Integer id) {
        this.id = id;
    }
}


