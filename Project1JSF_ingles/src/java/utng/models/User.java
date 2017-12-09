package utng.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_user")
    private Long idUser;
    @Column(name="name_user", length=10)
    private String nameUser;
    @Column(length=40)
    private String password;
    @ManyToOne()
    @JoinColumn(name="id_role")
    private Role role;

    public User(Long idUser, String nameUser, String password, Role role) {
        super();
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.password = password;
        this.role = role;
    }

    public User() {
        this.idUser=0L;
    }
    public Long getIdUser() {
        return idUser;
    }
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    public String getNameUser() {
        return nameUser;
    }
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    
    
}