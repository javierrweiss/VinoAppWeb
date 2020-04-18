package vinoapp.repositorios.jpa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.sources.tables.Usuarios;
import vinoapp.entidades.Notadecata;
import vinoapp.repositorios.interfaces.I_NotadecataRepositorio;
public class NotadecataRepositorio implements I_NotadecataRepositorio{
private final EntityManager em;
    DSLContext ctx;

    public NotadecataRepositorio(EntityManager em) {this.em = em;}

    @Override
    public void save(Notadecata notadecata) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(notadecata);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void remove(Notadecata notadecata) {
       EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.remove(notadecata);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        } 
    }

    @Override
    public void update(Notadecata notadecata) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(notadecata);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public List<Notadecata> getAll() {
        List<Notadecata> resultado= new ArrayList<>();
        em.getTransaction().begin();
        resultado=em.createNamedQuery("Notadecata.findAll", Notadecata.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return resultado;
    }

    @Override
    public int getById(int notaDeCataId) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Notadecata.findByNotaDeCataId", Notadecata.class).setParameter("notaDeCataId", notaDeCataId);
        int resultado=q.getFirstResult();
        em.getTransaction().commit();
        em.close();
        return resultado;
    }

    @Override
    public int getByUserId(String cuentaUsuario) {
     em.getTransaction().begin();
        Result<Record1<Integer>> record=ctx.select(Usuarios.USUARIOS.USUARIO_ID)
            .from(Usuarios.USUARIOS)
            .where(Usuarios.USUARIOS.CUENTA_USUARIO.eq(cuentaUsuario))
            .fetch();
        int resultado=record.stream().findFirst().get().component1();
        em.getTransaction().commit();
        em.close();
     return resultado;
    } //Esta fue hecha con jooq

    @Override
    public String getByVino(String nombre) {
        em.getTransaction().begin();
        Query q =em.createNativeQuery("SELECT v.vino_id FROM vinos v WHERE v.nombre= ?");
        q.setParameter(1, nombre);
        String resultado=q.getSingleResult().toString();
        em.getTransaction().commit();
        em.close();
        return resultado;
       
    } //Acá estamos usando las native queries de JPA. También se pueden personalizar las namedqueries 
    //con addnamedquery
    
    
}
