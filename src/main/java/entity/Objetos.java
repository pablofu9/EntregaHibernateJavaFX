package entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="objetos")
public class Objetos extends RecursiveTreeObject<Objetos> implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_objeto")
    private int id_objeto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private Usuarios usuario;

    @Column(name = "nombre_objeto")
    private String nombre_objeto;

    @Column(name = "tipo_objeto")
    private String tipo_objeto;

    @Column(name = "estado")
    private String estado;

    @Column(name = "precio")
    private int precio;

    @Column(name = "Reservado")
    private boolean reservado;

    public Objetos() {
    }

    public Objetos(int id_objeto, Usuarios usuario, String nombre_objeto, String tipo_objeto, String estado, int precio, boolean reservado) {
        this.id_objeto = id_objeto;
        this.usuario = usuario;
        this.nombre_objeto = nombre_objeto;
        this.tipo_objeto = tipo_objeto;
        this.estado = estado;
        this.precio = precio;
        this.reservado = reservado;
    }

    public int getId_objeto() {
        return id_objeto;
    }

    public void setId_objeto(int id_objeto) {
        this.id_objeto = id_objeto;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getNombre_objeto() {
        return nombre_objeto;
    }

    public void setNombre_objeto(String nombre_objeto) {
        this.nombre_objeto = nombre_objeto;
    }

    public String getTipo_objeto() {
        return tipo_objeto;
    }

    public void setTipo_objeto(String tipo_objeto) {
        this.tipo_objeto = tipo_objeto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }

    @Override
    public String toString() {
        return "Objetos{" +
                "id_objeto=" + id_objeto +
                ", usuario=" + usuario +
                ", nombre_objeto='" + nombre_objeto + '\'' +
                ", tipo_objeto='" + tipo_objeto + '\'' +
                ", estado='" + estado + '\'' +
                ", precio=" + precio +
                ", reservado=" + reservado +
                '}';
    }
}