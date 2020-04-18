package vinoapp.repositorios.interfaces;

import java.util.List;
import vinoapp.entidades.Premio;
import vinoapp.enumerados.Medalla;

public interface I_PremioRepositorio {
    void save(Premio premio);
    void remove(Premio premio);
    void update(Premio premio);
    List<Premio> getAll();
    int getById(int premioId);
    String getByNombre(String nombrePremio);
    int getByPuntaje(int puntaje);
    Medalla getByMedalla(Medalla medalla);
    int getByAno(int ano);
}
