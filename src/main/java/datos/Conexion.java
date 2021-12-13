package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class Conexion {

    // Variables de configuración a la conexión MySQL
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/control_clientes?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";

    // Método para manejar el pool de conexiones
    public static DataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASS);
        ds.setInitialSize(50);
        return ds;
    }

    // Método para obtener la conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    // Método para cerrar el objeto ResultSet
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar el objeto ResultSet " + ex.getMessage());
        }
    }

    // Método para cerrar el objeto PreparedStatement
    public static void close(PreparedStatement pst) {
        try {
            pst.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar el objeto PreparedStatement " + ex.getMessage());
        }
    }

    // Método para cerrar el objeto Connection
    public static void close(Connection cn) {
        try {
            cn.close();
        } catch (SQLException ex) {
            System.err.println("Error al cerrar el objeto Connection " + ex.getMessage());

        }
    }
}
