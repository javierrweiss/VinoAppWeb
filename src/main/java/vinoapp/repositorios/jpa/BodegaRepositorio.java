package vinoapp.repositorios.jpa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import vinoapp.entidades.Bodega;
import vinoapp.repositorios.interfaces.I_BodegaRepositorio;
public class BodegaRepositorio implements I_BodegaRepositorio{
    private EntityManager em;
    private EntityManagerFactory emf;
    DSLContext ctx=DSL.using(SQLDialect.MARIADB);
    public BodegaRepositorio(EntityManager em) {this.em = em;}

    @Override
    public void save(Bodega bodega) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(bodega);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void remove(Bodega bodega) {
    EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.remove(bodega);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void update(Bodega bodega) {
    EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(bodega);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public List<Bodega> getAll() {
        List<Bodega> resultado=new ArrayList<>();
        em.getTransaction().begin();
        resultado=em.createNamedQuery("Bodega.findAll", Bodega.class).getResultList();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }
    
//    private static void setBindParameterValues(Query EclipseLinkQuery, org.jooq.Query jooqQuery){
//    List<Object> values= jooqQuery.getBindValues();
//            for (int i = 0; i < values.size(); i++) {
//                EclipseLinkQuery.setParameter(i + 1, values.get(i));
//            }
//    }

    @Override
    public int getById(int bodegaId) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Bodega.findByBodegaId", Bodega.class)
                .setParameter("bodegaId", bodegaId);
        int resultado=q.getFirstResult();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public List<Bodega> getByNombre(String nombreBodega) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Bodega.findByNombreBodega", Bodega.class)
                .setParameter("nombreBodega", nombreBodega);
        List<Bodega> resultado= q.getResultList();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public List<Bodega> getByPais(String pais) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Bodega.findByPais", Bodega.class).setParameter("pais", pais);
        List<Bodega> resultado=q.getResultList();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public List<Bodega> getByUbicacion(String ubicacion) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Bodega.findByUbicacion", Bodega.class)
                .setParameter("ubicacion", ubicacion);
        List<Bodega> resultado= q.getResultList();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public int getByFundacion(int fundacion) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Bodega.findByFundacion", Bodega.class)
                .setParameter("fundacion", fundacion);
        int resultado=q.getFirstResult();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    
}
