package mvc.model;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by axmedbek on 9/4/17.
 */

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "{user.empty}")
    @Length(min = 5,max = 25,message = "{user.size}")
    private String name;
    @NotEmpty(message = "{user.empty}")
    @Length(min = 5,max = 25,message = "{user.size}")
    private String surname;

    @Email(message = "{user.email}")
    @NotEmpty(message = "{user.empty}")
    @Transient
    @Pattern(regexp = "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$",message = "")
    private String email;



    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Transient
    private MultipartFile file;

    public User() {
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public User(String name, String surname, String imagePath, MultipartFile file) {
        this.name = name;
        this.surname = surname;
        this.imagePath = imagePath;
        this.file = file;
    }

    public User(String name, String surname, MultipartFile file) {
        this.name = name;
        this.surname = surname;
        this.file = file;
    }

    public User(String name, String surname, String email, String imagePath, MultipartFile file) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.imagePath = imagePath;
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
