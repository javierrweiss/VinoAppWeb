package vinoapp.repositorios.jpa;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.jooq.DSLContext;
import org.jooq.Record8;
import org.jooq.Result;
import org.jooq.sources.tables.Usuarios;
import vinoapp.entidades.Usuario;
import vinoapp.repositorios.interfaces.I_UsuarioRepositorio;
public class UsuarioRepositorio implements I_UsuarioRepositorio{
    private final EntityManager em;
    private EntityManagerFactory emf;
    DSLContext ctx;
    public UsuarioRepositorio(EntityManager em) {this.em = em;}

    @Override
    public void save(Usuario usuario) {
        EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(usuario);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void remove(Usuario usuario) {
    EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.remove(usuario);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public void update(Usuario usuario) {
    EntityTransaction et=em.getTransaction();
        try {
            et.begin();
            em.persist(usuario);
            et.commit();
        } catch (Exception e) {
            et.rollback();
        }
    }

    @Override
    public List<Usuario> getAll() {
    return em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
    }
    
    /*
    En este método estoy empleando una native query para probar las alternativas que JPA ofrece
    ante JOOQ
    :id => Esto es un named parameter
    */
    public List<Object> getById(int id){
    List <Object>resultado=new ArrayList<>();
    Query q = em.createNativeQuery("SELECT u.cuentaUsuario, u.nombre, u.apellido, u.pais, u.region,"
            + "u.ciudad, u.email, u.codPostal FROM Usuario WHERE u.usuarioId = ?");
    q.setParameter(1, id); 
        try {
    em.getTransaction().begin();
    resultado=q.getResultList();
    em.getTransaction().commit();
    em.close();  
        } catch (Exception e) {
        em.getTransaction().rollback();
        }
    return resultado;
    }   

    @Override
    public List<Object> getLikeNombre(String nombre) {
    List<Object> resultado=new ArrayList<>();
    em.getTransaction().begin();
    Result<Record8<String,String, String, String, String, String, String, String>> record=
         ctx.select(Usuarios.USUARIOS.NOMBRE, Usuarios.USUARIOS.APELLIDO, Usuarios.USUARIOS.CUENTA_USUARIO, 
            Usuarios.USUARIOS.EMAIL, Usuarios.USUARIOS.PAIS, Usuarios.USUARIOS.REGION,
            Usuarios.USUARIOS.CIUDAD, Usuarios.USUARIOS.COD_POSTAL)
            .from(Usuarios.USUARIOS)
            .where(Usuarios.USUARIOS.NOMBRE.like(nombre))
            .fetch();
    resultado=record.stream().collect(Collectors.toList());
    em.getTransaction().commit();
    em.close();
    return resultado;
    }

    @Override
    public List<Object> getLikeApellido(String apellido) {
    List<Object> resultado=new ArrayList<>();
    em.getTransaction().begin();
    Result<Record8<String, String, String, String, String, String, String, String>> record=
            ctx.select(Usuarios.USUARIOS.NOMBRE, Usuarios.USUARIOS.APELLIDO, Usuarios.USUARIOS.CUENTA_USUARIO, 
            Usuarios.USUARIOS.EMAIL, Usuarios.USUARIOS.PAIS, Usuarios.USUARIOS.REGION,
            Usuarios.USUARIOS.CIUDAD, Usuarios.USUARIOS.COD_POSTAL)
            .from(Usuarios.USUARIOS)
            .where(Usuarios.USUARIOS.APELLIDO.like(apellido))
            .fetch();
    resultado=record.stream().collect(Collectors.toList());
    em.getTransaction().commit();
    em.close();
    return resultado;
    }

    @Override
    public List<Object> getLikeCuentaUsuario(String cuenta) {
    List<Object> resultado=new ArrayList();
    em.getTransaction().begin();
    Result<Record8<String, String, String, String, String, String, String, String>> record=
            ctx.select(Usuarios.USUARIOS.NOMBRE, Usuarios.USUARIOS.APELLIDO, Usuarios.USUARIOS.CUENTA_USUARIO, 
            Usuarios.USUARIOS.EMAIL, Usuarios.USUARIOS.PAIS, Usuarios.USUARIOS.REGION,
            Usuarios.USUARIOS.CIUDAD, Usuarios.USUARIOS.COD_POSTAL)
            .from(Usuarios.USUARIOS)
            .where(Usuarios.USUARIOS.CUENTA_USUARIO.like(cuenta))
            .fetch();
    resultado=record.stream().collect(Collectors.toList());
    em.getTransaction().commit();
    em.close();
    return resultado;
    }

    /*
    Las implementaciones de los métodos de las interfaces no están siendo parejas en todas las clases, 
    debido a la fase experimental en que nos encontramos. Aquí estamos probando otra forma de hacerlo.
    Resta verificar el comportamiento en los tests.
    */
    @Override
    public String getByPais(String pais) {
        em.getTransaction().begin();
        Query q= em.createNamedQuery("Usuario.findByPais", Usuario.class).setParameter("pais", pais); //Así lo vimos en el tutorial de Kevin Bowersox JPA/JPQL
        String resultado=q.getSingleResult().toString();
        em.getTransaction().commit();
        em.close();
        return resultado;
    }

    @Override
    public String getByRegion(String region) {
        em.getTransaction().begin();
        em.getTransaction().commit();
        Query q= em.createNamedQuery("Usuario.findByRegion", Usuario.class).setParameter("region", region);
        em.close();
        return q.getSingleResult().toString();
    }

    @Override
    public String getByCiudad(String ciudad) {
        em.getTransaction().begin();
        Query q= em.createNamedQuery("Usuario.findByCiudad", Usuario.class).setParameter("ciudad", ciudad);
        String resultado =q.getSingleResult().toString();//Me parece que no puede ser un SingleResult
        em.getTransaction().commit();
        em.close();
        return  resultado;
    }
}
