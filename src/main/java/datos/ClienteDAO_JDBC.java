package datos;

// Importando librerías
import dominio.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO_JDBC {

    // Variables con las consultas de tipo SQL
    private static final String SQL_SELECT = "SELECT * FROM cliente";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM cliente WHERE id_cliente = ?";
    private static final String SQL_INSERT = "INSERT INTO cliente (nombre, apellido, email, telefono, saldo) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE cliente SET nombre = ?, apellido = ?, email = ?, telefono = ?, saldo = ? WHERE id_cliente = ?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente = ?";

    // Método para listar los clientes
    public List<Cliente> listar() {

        // Declarando los objetos
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            // Ejecutando la sentencia SQL de tipo SELECT
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            // Iterando los clientes de la base de datos
            while (rs.next()) {
                // Recuperando los campos
                int id_cliente = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");

                // Creando un nuevo cliente
                cliente = new Cliente(id_cliente, nombre, apellido, email, telefono, saldo);
                // Añadiendo cliente a la lista de clientes
                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar clientes " + ex.getMessage());
        } finally {
            // Cerrando los objetos
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return clientes;
    }
}
