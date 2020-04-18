package vinoapp.repositorios.interfaces;
import java.util.List;
import vinoapp.entidades.Notadecata;
public interface I_NotadecataRepositorio {
    void save(Notadecata notasdecata);
    void remove(Notadecata notasdecata);
    void update(Notadecata notasdecata);
    List<Notadecata> getAll();  
    int getById(int notaDeCataId);
    int getByUserId(String cuentaUsuario);
    String getByVino(String nombre);
    
}
