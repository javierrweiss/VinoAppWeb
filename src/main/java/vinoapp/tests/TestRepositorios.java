package vinoapp.tests;

import java.sql.Connection;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import vinoapp.connectors.Connector;
import vinoapp.entidades.Bodega;
import vinoapp.entidades.Premio;
import vinoapp.entidades.Ranking;
import vinoapp.entidades.Tienda;
import vinoapp.entidades.Usuario;
import vinoapp.entidades.Vino;
import vinoapp.repositorios.interfaces.I_BodegaRepositorio;
import vinoapp.repositorios.interfaces.I_PremioRepositorio;
import vinoapp.repositorios.interfaces.I_RankingRepositorio;
import vinoapp.repositorios.interfaces.I_TiendaRepositorio;
import vinoapp.repositorios.interfaces.I_UsuarioRepositorio;
import vinoapp.repositorios.interfaces.I_VinoRepositorio;
import vinoapp.repositorios.jpa.BodegaRepositorio;
import vinoapp.repositorios.jpa.PremioRepositorio;
import vinoapp.repositorios.jpa.RankingRepositorio;
import vinoapp.repositorios.jpa.TiendaRepositorio;
import vinoapp.repositorios.jpa.UsuarioRepositorio;
import vinoapp.repositorios.jpa.VinoRepositorio;

public class TestRepositorios {
    public static void main(String[] args) {
        try (Connection conn=Connector.getConnection()) {
          EntityManagerFactory emf=Persistence.createEntityManagerFactory("JPAPU2");
            I_UsuarioRepositorio ur=new UsuarioRepositorio(emf.createEntityManager());
            Usuario usuario=new Usuario(0, "pperozzi", "Pablo", "Perozzi", "Mexico", "Baja California", "Ensenada", "pperozi@gmail.com");
            ur.save(usuario);
            System.out.println(usuario);
            System.out.println("****************************");
//            ur.remove((Usuario) ur.getById(40)); Falla, como lo esperábamos
            ur.getById(15);
            usuario.setCuentaUsuario("tomalcantaraa");
            usuario.setNombre("Tomas");
            usuario.setCiudad("Guayaquil");
            usuario.setRegion("Guayas");
            ur.update(usuario);
            ur.getAll().forEach(System.out::println);
            ur.getByCiudad("Buenos Aires");
            ur.getByPais("España");
            ur.getByRegion("Neuquen");
            ur.getLikeApellido("Watson");
            ur.getLikeCuentaUsuario("toston");
            ur.getLikeNombre("Tomás");
            System.out.println("*****************************");
//            
//            I_BodegaRepositorio br=new BodegaRepositorio(emf.createEntityManager());
//            Bodega bodega = new Bodega(0,"Casa de Piedra");
//            br.save(bodega);
//            System.out.println(bodega);
//            System.out.println("****************************");
//            br.remove(br.getById(31));
//            bodega=br.getById(25);
//            bodega.setNombreBodega("Bodega y Viñedos Hija de Aníbal S.L.");
//            bodega.setUbicacion("C/Celeiro 3, 24516 Otero del Toral");
//            bodega.setPais("España");
//            bodega.setFundacion(2016);
//            br.update(bodega);
//            br.getAll().forEach(System.out::println);
//            System.out.println("*****************************");
//            
//            I_TiendaRepositorio tr=new TiendaRepositorio(emf.createEntityManager());
//            Tienda tienda=new Tienda(0, "La Europea", "Mexico", "Blvd. Agua Caliente No. 9640");
//            tr.save(tienda);
//            System.out.println(tienda);
//            System.out.println("****************************");
//            tr.remove(tr.getById(39));
//            tienda=tr.getById(15);
//            tienda.setNombreT("Ligier Vinoteca");
//            tienda.setPais("Argentina");
//            tienda.setCiudad("CABA");
//            tienda.setDireccion("Av. Callao 1111");
//            tienda.setPaginaWeb("www.ligiervinoteca.com.ar");
//            tr.update(tienda);
//            tr.getAll().forEach(System.out::println);
//            System.out.println("*****************************");
//            
//            I_VinoRepositorio vr=new VinoRepositorio(emf.createEntityManager());
//            Vino vino=new Vino(0, "Piedra de Sol", "blanco", 2017, "joven");
//            vr.save(vino);
//            System.out.println(vino);
//            System.out.println("****************************");
//            vr.remove(vr.getById(70));
//            vino=vr.getById(58);
//            vino.setNombre("Anibal de Otero Viña Centenaria");
//            vino.setCepas("Mencía");
//            vino.setColor("tinto");
//            vino.setCosecha(2014);
//            vino.setCategoria("reserva");
//            vino.setEnologo("Pepe Hidalgo");
//            vino.setTerruno("Bierzo");
//            vr.update(vino);
//            vr.getAll().forEach(System.out::println);
//            System.out.println("*****************************");
//            
//            I_RankingRepositorio rr=new RankingRepositorio(emf.createEntityManager()); 
//            Ranking ranking=new Ranking(0);
//            rr.save(ranking);
//            System.out.println(ranking);
//            System.out.println("****************************");
//            rr.remove(rr.getById(1));
//            ranking=rr.getById(10);
//            ranking.setRanking("r5");
//            rr.update(ranking);
//            rr.getAll().forEach(System.out::println);
//            System.out.println("*****************************");
//            
//            I_PremioRepositorio pr=new PremioRepositorio(emf.createEntityManager());
//            Premio premio = new Premio(0, "Robert Parker Wine Advocate", 2012);
//            pr.save(premio);
//            pr.remove(pr.getById(80));
//            System.out.println(premio);
//            System.out.println("****************************");
//            premio=pr.getById(81);
//            premio.setNombrePremio("jamessuckling.com");
//            premio.setPuntaje(95);
//            premio.setMedalla("plata");
//            premio.setAno(2012);
//            pr.update(premio);
//            pr.getAll();
//            System.out.println(premio);
//            System.out.println("*****************************");
        } catch (Exception e) {e.printStackTrace();}
    }
    
}
