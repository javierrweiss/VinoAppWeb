package vinoapp.repositorios.interfaces;

import java.util.List;
import vinoapp.entidades.Bodega;

public interface I_BodegaRepositorio {
    void save(Bodega bodega);
    void remove(Bodega bodega);
    void update(Bodega bodega);
    List<Bodega> getAll();
    int getById (int bodegaId);
    List<Bodega> getByNombre(String nombreBodega);
    List<Bodega> getByPais(String pais);
    List<Bodega> getByUbicacion(String ubicacion);
    int getByFundacion(int fundacion);
}
