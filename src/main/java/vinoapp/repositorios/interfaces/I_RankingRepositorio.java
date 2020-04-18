package vinoapp.repositorios.interfaces;

import java.util.List;
import vinoapp.entidades.Ranking;

public interface I_RankingRepositorio {
   void save(Ranking ranking);
    void remove(Ranking ranking);
    void update(Ranking ranking);
    List<Ranking> getAll();
    int getById(int rankingId);
    String getByRanking(Ranking ranking);
    String getLikeUsuario(String cuentausuario);
    List<Object> getByVino(String nombre);
     
}
