package vinoapp.tests;
import java.sql.Connection;
import java.sql.Statement;
import vinoapp.connectors.Connector;
public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn=Connector.getConnection()){
            Statement st=conn.createStatement();
            String query="insert into bodegas (bodega_id,nombre_bodega,pais,ubicacion,fundacion) "
                    + "values(null,'Viña Casas del Bosque','Chile','Valle de Casablanca',1993)";
            st.execute(query);
            
            conn.createStatement().execute(
            "insert into vinos (vino_id,nombre,cepas,color,cosecha,categoria,"
                    + "enologo,bodega_id,terruno)"
                    + "values(null,'Casas del Bosque Gran Reserva','Syrah','tinto',2015,"
                    + "'gran_reserva',null,null,'Valle de Casablanca')"
            );
            
            conn.createStatement().execute(
            "insert into premios(premio_id,nombre_premio,puntaje,medalla,ano,vino_id)" 
                    + "values(null,'Decanter World Wine Awards',88,null,2015,null)"
            );
        } catch (Exception e) {e.printStackTrace();}
    }
}
