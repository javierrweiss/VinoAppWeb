package vinoapp.repositorios.jpa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import vinoapp.entidades.Premio;
import vinoapp.enumerados.Medalla;
import vinoapp.repositorios.interfaces.I_PremioRepositorio;
public class PremioRepositorio implements I_PremioRepositorio{
    private EntityManager em;
    public PremioRepositorio(EntityManager em) {this.em = em;}

    @Override
    public void save(Premio premio) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(premio);
            et.commit();
        } catch (Exception e) {
           et.rollback();
        }
    }

    @Override
    public void remove(Premio premio) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.remove(premio);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void update(Premio premio) {
      EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(premio);
            et.commit();
        } catch (Exception e) {
           et.rollback();
        }
    }

    @Override
    public List<Premio> getAll() {
        List<Premio> resultado=new ArrayList<>();
        em.getTransaction().begin();
        resultado=em.createNamedQuery("Premio.findAll", Premio.class).getResultList();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public int getById(int premioId) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Premio.findByPremioId", Premio.class).setParameter("premioId", premioId);
        int resultado=q.getFirstResult();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public String getByNombre(String nombrePremio) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Premio.findByNombrePremio", Premio.class).setParameter("nombrePremio", nombrePremio);
        String resultado=q.getSingleResult().toString();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public int getByPuntaje(int puntaje) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Premio.findByPuntaje", Premio.class).setParameter("puntaje", puntaje);
        int resultado=q.getFirstResult();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public Medalla getByMedalla(Medalla medalla) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Premio.findByMedalla", Premio.class)
                .setParameter("medalla",medalla);
        Medalla resultado= (Medalla) q.getSingleResult();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public int getByAno(int ano) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Premio.findByAno").setParameter("ano", ano);
        int resultado=q.getFirstResult();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }
    
}
