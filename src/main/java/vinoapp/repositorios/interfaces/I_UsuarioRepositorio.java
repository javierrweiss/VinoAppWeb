package vinoapp.repositorios.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import vinoapp.entidades.Usuario;

public interface I_UsuarioRepositorio {
    
    void save(Usuario usuario);
    void remove(Usuario usuario);
    void update(Usuario usuario);
    List<Usuario>getAll();
    List<Object> getLikeNombre(String nombre);
    List<Object> getLikeApellido(String apellido);
    List<Object> getLikeCuentaUsuario(String cuenta);
    List<Object> getById(int id);
    String getByPais(String pais);
    String getByRegion(String region);
    String getByCiudad(String ciudad);
}
