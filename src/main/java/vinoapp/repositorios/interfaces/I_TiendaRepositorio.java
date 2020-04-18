package vinoapp.repositorios.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import vinoapp.entidades.Tienda;

public interface I_TiendaRepositorio {
  void save(Tienda tienda);
  void remove(Tienda tienda);
  void update(Tienda tienda);
  List<Tienda> getAll();
  List<Object> getLikeDireccion(String direccion);  
  List<Object> getLikeTienda(String nombre);
  String getById(int id);  
}
