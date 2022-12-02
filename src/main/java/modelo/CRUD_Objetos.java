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
}
