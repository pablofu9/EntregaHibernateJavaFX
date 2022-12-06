package modelo;

import entity.Objetos;
import entity.Usuarios;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.List;

public class CRUD_Objetos {

    public static List<Objetos> llenarTabla(){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();
        List<Objetos> listaObjetos = session.createQuery("from Objetos ").getResultList();
        return listaObjetos;
    }
    public static void reservar(int id){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();
        Objetos objReserved= (Objetos) session.createQuery("FROM Objetos  WHERE id_objeto ="+id).uniqueResult();
        objReserved.setReservado(true);
        session.beginTransaction();
        session.update(objReserved);
        session.getTransaction().commit();
        session.close();
    }
    public static void insertarObjeto(Objetos obj){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(obj);

        session.getTransaction().commit();
        session.close();
    }
}
