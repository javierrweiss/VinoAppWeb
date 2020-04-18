package vinoapp.repositorios.jpa;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.jooq.DSLContext;
import org.jooq.Record8;
import org.jooq.Result;
import org.jooq.sources.enums.VinosCategoria;
import org.jooq.sources.enums.VinosColor;
import org.jooq.sources.tables.Bodegas;
import org.jooq.sources.tables.Vinos;
import org.jooq.types.UInteger;
import vinoapp.entidades.Vino;
import vinoapp.enumerados.Categoria;
import vinoapp.enumerados.Color;
import vinoapp.repositorios.interfaces.I_VinoRepositorio;
public class VinoRepositorio implements I_VinoRepositorio {
    private final EntityManager em;
    DSLContext ctx;
    public VinoRepositorio(EntityManager em) {this.em = em;}

    @Override
    public void save(Vino vino) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(vino);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void remove(Vino vino) {
    EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.remove(vino);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void update(Vino vino) {
    EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(vino);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public List<Vino> getAll() {
    return em.createNamedQuery("Vino.findAll", Vino.class).getResultList();
    }

    @Override
    public List<Object> getById(int vinoId) {
    List<Object> resultado = new ArrayList();
    em.getTransaction().begin();
    Query q= em.createNamedQuery("Vino.findByVinoId", Vino.class).setParameter("vinoId", vinoId);
    resultado=q.getResultList();
    em.getTransaction().commit();
    em.close();
    return resultado;
    }

    @Override
    public List<Object> getByNombre(String nombre) {
    List<Object> resultado = new ArrayList();
    em.getTransaction().begin();
    Query q =em.createNamedQuery("Vino.findByNombre", Vino.class).setParameter("nombre", nombre);
    resultado=q.getResultList();
    em.getTransaction().commit();
    em.close();
    return resultado;
    }

    @Override
    public List<Object> getByCepas(String cepas) {
    List<Object> resultado = new ArrayList();
    em.getTransaction().begin();
    Query q=em.createNamedQuery("Vino.findByCepas", Vino.class).setParameter("cepas", cepas);
    resultado=q.getResultList();
    em.getTransaction().commit();
    em.close();
    return resultado;
    }

    @Override
    public List<Object> getByColor(Color color) {
    List<Object> resultado = new ArrayList();
    em.getTransaction().begin();
    Query q=em.createNamedQuery("Vino.findByColor").setParameter("color", color);
    resultado=q.getResultList();
    em.getTransaction().commit();
    em.close();
    return resultado;
    }

    @Override
    public List<Object> getByCosecha(int cosecha) {
    List<Object> resultado = new ArrayList();
    em.getTransaction().begin();
    Query q=em.createNamedQuery("Vino.findByCosecha").setParameter("cosecha", cosecha);
    resultado=q.getResultList();
    em.getTransaction().commit();
    em.close();
    return resultado;
    }

    @Override
    public List<Object> getByCategoria(Categoria categoria) {
    List<Object> resultado = new ArrayList();
    em.getTransaction().begin();
    Query q=em.createNamedQuery("Vino.findByCategoria").setParameter("categoria", categoria);
    resultado=q.getResultList();
    em.getTransaction().commit();
    em.close();
    return resultado;
    }

    @Override
    public List<Object> getLikeEnologo(String enologo) {
    List<Object> resultado = new ArrayList();
    em.getTransaction().begin();
    Result<Record8< String, String,VinosColor, VinosCategoria, UInteger, String, String, String>> record=
                ctx.select(Vinos.VINOS.NOMBRE, Vinos.VINOS.CEPAS, Vinos.VINOS.COLOR, 
                 Vinos.VINOS.CATEGORIA, Vinos.VINOS.COSECHA, Bodegas.BODEGAS.NOMBRE_BODEGA,
                 Vinos.VINOS.TERRUNO, Vinos.VINOS.ENOLOGO)
                .from(Vinos.VINOS)
                .join(Bodegas.BODEGAS).on(Bodegas.BODEGAS.BODEGA_ID.eq(Vinos.VINOS.BODEGA_ID))
                .where(Vinos.VINOS.ENOLOGO.like(enologo))
                .fetch();
    resultado=record.stream().collect(Collectors.toList());
    em.getTransaction().commit();
    em.close();
    return resultado;
    }

    @Override
    public List<Object> getLikeBodega(String bodega) {
    List<Object> resultado = new ArrayList();
    em.getTransaction().begin();
    Result<Record8<String, String, VinosCategoria, VinosColor, String, UInteger, String, String>> record=
            ctx.select(Vinos.VINOS.NOMBRE, Vinos.VINOS.CEPAS, Vinos.VINOS.CATEGORIA, Vinos.VINOS.COLOR,
             Vinos.VINOS.TERRUNO, Vinos.VINOS.COSECHA, Vinos.VINOS.ENOLOGO, Bodegas.BODEGAS.NOMBRE_BODEGA)
            .from(Vinos.VINOS)
            .join(Bodegas.BODEGAS).on(Bodegas.BODEGAS.BODEGA_ID.eq(Vinos.VINOS.BODEGA_ID))
            .where(Bodegas.BODEGAS.NOMBRE_BODEGA.like(bodega))
            .fetch();
    resultado=record.stream().collect(Collectors.toList());
    em.getTransaction().commit();
    em.close();
    return resultado;
    }

    @Override
    public List<Object> getLikeTerruno(String terruno) {
    List<Object> resultado=new ArrayList();
    em.getTransaction().begin();
    Result<Record8<String, String,UInteger,VinosColor,VinosCategoria, String, String, String>> record=
            ctx.select(Vinos.VINOS.NOMBRE, Vinos.VINOS.CEPAS, Vinos.VINOS.COSECHA, Vinos.VINOS.COLOR,
                Vinos.VINOS.CATEGORIA, Vinos.VINOS.TERRUNO, Vinos.VINOS.ENOLOGO, Bodegas.BODEGAS.NOMBRE_BODEGA)
            .from(Vinos.VINOS)
            .join(Bodegas.BODEGAS).on(Bodegas.BODEGAS.BODEGA_ID.eq(Vinos.VINOS.BODEGA_ID))
            .where(Vinos.VINOS.TERRUNO.like(terruno))
            .fetch();
    resultado=record.stream().collect(Collectors.toList());
    em.getTransaction().commit();
    em.close();
    return resultado;
    }
    
    
}
