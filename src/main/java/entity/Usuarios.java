package entity;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="id_user")
    private int id_user;

    @Column(name="nombre_user")
    private String nombre_usuario;

    @Column(name="email_user")
    private String email_usuario;

    @OneToMany(mappedBy="id_objeto",cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Objetos> objetos;

    public Usuarios() {
    }

    public Usuarios(String nombre_usuario, String email_usuario) {
        this.nombre_usuario = nombre_usuario;
        this.email_usuario = email_usuario;
    }

    public Usuarios(int id_zona, String nombre_usuario, String email_usuario) {
        this.id_user = id_zona;
        this.nombre_usuario = nombre_usuario;
        this.email_usuario = email_usuario;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_zona) {
        this.id_user = id_zona;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public List<Objetos> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<Objetos> objetos) {
        this.objetos = objetos;
    }

    @Override
    public String toString() {
        return +id_user+"-"+nombre_usuario+"-"+email_usuario;
    }
}
