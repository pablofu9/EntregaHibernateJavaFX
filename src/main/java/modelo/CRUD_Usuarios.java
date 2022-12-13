package modelo;

import entity.Usuarios;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.List;

public class CRUD_Usuarios {

    public static void insertarUser(Usuarios u){ //Metodo enargado de añadir un usuario
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(u);

        session.getTransaction().commit();
        session.close();
    }
    //Metodo para recorrer la tabla y meter todas los usuarios en una lista
    public static List<Usuarios> llenarTabla(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();
        List<Usuarios> lista = session.createQuery("from Usuarios ").getResultList();
        return lista;
    }
    //Metodo para modificar el user selcted
    public static void modificarUser(int id,String nombre, String email){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();
        Usuarios userModified= (Usuarios) session.createQuery("FROM Usuarios  WHERE id_user ="+id).uniqueResult();
        userModified.setNombre_usuario(nombre);
        userModified.setEmail_usuario(email);
        session.beginTransaction();
        session.update(userModified);
        session.getTransaction().commit();
        session.close();
    }
    //Metodo para borrar usuarios
    public static void eliminarUser(Usuarios u){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();
        Usuarios usuarioDelete = (Usuarios) session.createQuery("FROM Usuarios WHERE id_user ="+u.getId_user()).uniqueResult();
        session.beginTransaction();
        session.delete(usuarioDelete);
        session.getTransaction().commit();
        session.close();
    }

    public static Usuarios buscarUser(int id){
        //Nos buscara el usuario segun el id
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();
        return session.get(Usuarios.class, id);


    }

}
