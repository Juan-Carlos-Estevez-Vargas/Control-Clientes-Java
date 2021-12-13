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

    // Método para buscar un cliente
    public Cliente encontrar(Cliente cliente) {

        // Declarando los objetos
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Ejecutando la sentencia SQL de tipo SELECT_BY_ID
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getIdCliente()); // Seteando el parámetro de la consulta preparada
            rs = stmt.executeQuery();
            rs.absolute(1); // Posicionandonos en el primer registro encontrado

            // Recuperando los campos
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String telefono = rs.getString("telefono");
            double saldo = rs.getDouble("saldo");

            // Setenado los campos al cliente (Creando un nuevo cliente)
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setSaldo(saldo);

        } catch (SQLException ex) {
            System.err.println("Error al encontrar cliente " + ex.getMessage());
        } finally {
            // Cerrando los objetos
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return cliente;
    }

    // Método para insertar un cliente a la base de datos
    public int insertar(Cliente cliente) {

        // Declarando los objetos
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            // Ejecutando la sentencia SQL de tipo INSERT
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);

            // Seteando los parámetros a la consulta preparada
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());

            // Ejecutando la sentencia SQL_INSERT
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al insertar cliente " + ex.getMessage());
        } finally {
            // Cerrando los objetos
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    // Método pata actualizar un cliente en la base de datos
    public int actualizar(Cliente cliente) {

        // Declarando los objetos
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            // Ejecutando la sentencia SQL de tipo UPDATE
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);

            // Seteando los parámetros a la consulta preparada
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6, cliente.getIdCliente());

            // Ejecutando la sentencia SQL_UPDATE
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al actualizar cliente " + ex.getMessage());
        } finally {
            // Cerrando los objetos
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }

    // Método para eliminar un cliente
    public int eliminar(Cliente cliente) {
        // Declarando los objetos
        Connection con = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            // Ejecutando la sentencia SQL de tipo DELETE
            con = Conexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);

            // Seteando los parámetros a la consulta preparada
            stmt.setInt(1, cliente.getIdCliente());

            // Ejecutando la sentencia SQL_DELETE
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Error al eliminar cliente " + ex.getMessage());
        } finally {
            // Cerrando los objetos
            Conexion.close(stmt);
            Conexion.close(con);
        }
        return rows;
    }
}
