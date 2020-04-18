package vinoapp.repositorios.jpa;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.jooq.DSLContext;
import org.jooq.Record4;
import org.jooq.Record5;
import org.jooq.Result;
import org.jooq.sources.tables.Tiendas;
import vinoapp.entidades.Tienda;
import vinoapp.repositorios.interfaces.I_TiendaRepositorio;
public class TiendaRepositorio implements I_TiendaRepositorio{
    private final EntityManager em;
    DSLContext ctx;
    public TiendaRepositorio(EntityManager em) {this.em = em;}

    @Override
    public void save(Tienda tienda) {
    EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(tienda);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void remove(Tienda tienda) {
    EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.remove(tienda);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void update(Tienda tienda) {
     EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(tienda);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public List<Tienda> getAll() {
    return em.createNamedQuery("Tienda.findAll", Tienda.class).getResultList();//Acá no abrí ni cerré transacción
    }
    
    @Override
    public List<Object> getLikeTienda(String nombre){
    List<Object> resultado=new ArrayList();
    em.getTransaction().begin();
    Result<Record5<Integer, String, String, String, String>> record= 
                ctx.select(Tiendas.TIENDAS.TIENDA_ID, Tiendas.TIENDAS.CIUDAD, 
                        Tiendas.TIENDAS.DIRECCION, Tiendas.TIENDAS.PAGINA_WEB, Tiendas.TIENDAS.PAIS)
                .from(Tiendas.TIENDAS)
                .where(Tiendas.TIENDAS.NOMBRE_T.like(nombre))
                .fetch();
    resultado=record.stream().collect(Collectors.toList());
    em.getTransaction().commit();
    em.close();
    return resultado;
    }
    
    @Override
    public List<Object> getLikeDireccion(String direccion){
        List<Object> resultado=new ArrayList();
        em.getTransaction().begin();
        Result<Record4<String, String, String, String>> record= ctx.select(Tiendas.TIENDAS.NOMBRE_T,
                Tiendas.TIENDAS.CIUDAD, Tiendas.TIENDAS.PAIS, Tiendas.TIENDAS.PAGINA_WEB)
                .from(Tiendas.TIENDAS)
                .where(Tiendas.TIENDAS.DIRECCION.like(direccion))
                .fetch();
        resultado=record.stream().collect(Collectors.toList());
        em.getTransaction().commit();
        em.close();
        return resultado;
    }
    
    public String getById(int id){
        em.getTransaction().begin();
        //String resultado=em.createNamedQuery("Bodega.findByBodegaId").getParameter(id).getName();
        Query q=em.createNamedQuery("Bodega.findByBodegaId", Tienda.class).setParameter("bodegaId", id);
        String resultado=q.getSingleResult().toString();
        em.getTransaction().commit();
        em.close();
       return resultado;
    }
    
    
    
    
}
