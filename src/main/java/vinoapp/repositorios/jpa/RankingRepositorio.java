package vinoapp.repositorios.jpa;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Result;
import org.jooq.sources.enums.VinosCategoria;
import org.jooq.sources.enums.VinosColor;
import org.jooq.sources.tables.Bodegas;
import org.jooq.sources.tables.Usuarios;
import org.jooq.sources.tables.Vinos;
import org.jooq.types.UInteger;
import vinoapp.entidades.Ranking;
import vinoapp.repositorios.interfaces.I_RankingRepositorio;
public class RankingRepositorio implements I_RankingRepositorio{
    private final EntityManager em;
    DSLContext ctx;
    public RankingRepositorio(EntityManager em) {this.em = em;}

    @Override
    public void save(Ranking ranking) {
    EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(ranking);
            et.commit();
        } catch (Exception e) {
           et.rollback();
        }
    }

    @Override
    public void remove(Ranking ranking) {
    EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.remove(ranking);
            et.commit();
        } catch (Exception e) {
        et.rollback();
        }
    }

    @Override
    public void update(Ranking ranking) {
    EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(ranking);
            et.commit();
        } catch (Exception e) {
           et.rollback();
        }
    }

    @Override
    public List<Ranking> getAll() {
        List<Ranking> resultado=new ArrayList<>();
        em.getTransaction().begin();
        em.getTransaction().commit();
        resultado=em.createNamedQuery("Ranking.findAll", Ranking.class).getResultList();
        em.close();
    return resultado;
    }

    @Override
    public int getById(int rankingId) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Ranking.findByRankingId", Ranking.class).setParameter("rankinId", rankingId);
        int resultado=q.getFirstResult();
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public String getByRanking(Ranking ranking) {
        em.getTransaction().begin();
        Query q=em.createNamedQuery("Ranking.findByRanking", Ranking.class).setParameter("ranking", ranking);
        String resultado=q.getSingleResult().toString();
        em.getTransaction().commit();
        em.close();
    return resultado; 
    }

    @Override
    public String getLikeUsuario(String cuentausuario) {
        em.getTransaction().begin();
        Result<Record1<String>> record= ctx.select(Usuarios.USUARIOS.CUENTA_USUARIO)
                .from(Usuarios.USUARIOS)
                .where(Usuarios.USUARIOS.CUENTA_USUARIO.like(cuentausuario))
                .fetch();
        String resultado=record.stream().findFirst().get().component1(); //Atento a esta modalidad
        em.getTransaction().commit();
        em.close();
    return resultado;
    }

    @Override
    public List<Object> getByVino(String nombre) {
        List<Object>resultado=new ArrayList();
        em.getTransaction().begin();
        Result<Record7<String, VinosColor, UInteger, VinosCategoria, String, String, String>> record =
                ctx.select(Vinos.VINOS.CEPAS, Vinos.VINOS.COLOR, Vinos.VINOS.COSECHA,
                Vinos.VINOS.CATEGORIA, Vinos.VINOS.ENOLOGO, Bodegas.BODEGAS.NOMBRE_BODEGA,
                Vinos.VINOS.TERRUNO)
                .from(Vinos.VINOS)
                .join(Bodegas.BODEGAS).on(Vinos.VINOS.BODEGA_ID.eq(Bodegas.BODEGAS.BODEGA_ID))
                .groupBy(Vinos.VINOS.CEPAS, Vinos.VINOS.COLOR, Vinos.VINOS.COSECHA,
                Vinos.VINOS.CATEGORIA, Vinos.VINOS.ENOLOGO, Bodegas.BODEGAS.NOMBRE_BODEGA,
                Vinos.VINOS.TERRUNO)
                .fetch();
        resultado=record.stream().collect(Collectors.toList());
        em.getTransaction().commit();
        em.close();
        return resultado;
    }
    
}
