package vinoapp.repositorios.interfaces;
import java.util.List;
import java.util.stream.Collectors;
import vinoapp.entidades.Bodegapremio;
public interface I_BodegapremioRepositorio {
    void save(Bodegapremio bodegapremio);
    void remove(Bodegapremio bodegapremio);
    void update(Bodegapremio bodegapremio);
    List<Bodegapremio> getAll();
    int getById(int premiobodId);
    List<Bodegapremio> getByNombre (String nombrePremiobod);
    
    
//    default long getNumPremiosPorBodega (String nombreBodega) {
//        return getAll()
//               .stream()
//               .filter(bp -> bp.getBodegaId().getNombreBodega().equals(nombreBodega))
//               .count();
//    }
}
