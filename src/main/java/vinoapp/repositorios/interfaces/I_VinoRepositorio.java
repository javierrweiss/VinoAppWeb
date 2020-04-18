package vinoapp.repositorios.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import vinoapp.entidades.Bodega;
import vinoapp.entidades.Vino;
import vinoapp.enumerados.Categoria;
import vinoapp.enumerados.Color;

public interface I_VinoRepositorio {
    void save(Vino vino);
    void remove(Vino vino);
    void update(Vino vino);
    List<Vino>getAll();
    List<Object>getById(int vinoId);
    List<Object> getByNombre(String nombre);
    List<Object> getByCepas(String cepas);
    List<Object> getByColor(Color color);
    List<Object> getByCosecha(int cosecha);
    List<Object> getByCategoria(Categoria categoria);
    List<Object> getLikeEnologo(String enologo);
    List<Object> getLikeBodega(String bodega);     
    List<Object> getLikeTerruno(String terruno);
}
