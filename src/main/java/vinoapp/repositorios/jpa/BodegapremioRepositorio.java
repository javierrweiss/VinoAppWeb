package vinoapp.repositorios.jpa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import vinoapp.entidades.Bodegapremio;
import vinoapp.repositorios.interfaces.I_BodegapremioRepositorio;
public class BodegapremioRepositorio implements I_BodegapremioRepositorio{
private final EntityManager em;

    public BodegapremioRepositorio(EntityManager em) { this.em = em;}

    @Override
    public void save(Bodegapremio bodegapremio) {
        EntityTransaction et= em.getTransaction();
        try {
            et.begin();
            em.persist(bodegapremio);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void remove(Bodegapremio bodegapremio) {
        EntityTransaction et= em.getTransaction();
        try {
            et.begin();
            em.remove(bodegapremio);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void update(Bodegapremio bodegapremio) {
        EntityTransaction et= em.getTransaction();
        try {
            et.begin();
            em.persist(bodegapremio);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public List<Bodegapremio> getAll() {
        List<Bodegapremio> resultado=new ArrayList<>();
        em.getTransaction().begin();
        resultado=em.createNamedQuery("Bodegapremio.findAll", Bodegapremio.class).getResultList();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public int getById(int premiobodId) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Bodegapremio.findByPremiobodId", Bodegapremio.class).setParameter("premiobodId", premiobodId);
        int resultado=q.getFirstResult();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public List<Bodegapremio> getByNombre(String nombrePremiobod) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Bodegapremio.findByNombrePremiobod", Bodegapremio.class)
                .setParameter("nombrePremiobod", nombrePremiobod);
        List<Bodegapremio> resultado=q.getResultList();
        em.getTransaction().commit();
        em.close();
    return resultado; 
    }
    
}
