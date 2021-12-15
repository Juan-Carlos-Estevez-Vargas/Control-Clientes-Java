package web;

// Librerías
import datos.ClienteDAO_JDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServerControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.accionDefalult(request, response);
    }

    private void accionDefalult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Listando los clientes
        List<Cliente> clientes = new ClienteDAO_JDBC().listar();
        System.out.println(clientes);

        // Compartiendo la información con el frontend
        request.setAttribute("clientes", clientes);
        request.setAttribute("totalClientes", clientes.size());
        request.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }

    // Método para calcular el saldo total de todos los clientes
    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;

        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }

        return saldoTotal;
    }

    // Método para procesar las peticiones de tipo POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recuperando el parámetro de la acción a realizar
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                default:
                    this.accionDefalult(request, response);
            }
        } else {
            this.accionDefalult(request, response);
        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Recuperando los valores del formulario agregarCliente.jsp
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");

        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }

        // Creando el objeto cliente (modelo)
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);

        // Insertando el cliente en la base de datos
        int registrosModificados = new ClienteDAO_JDBC().insertar(cliente);
        System.out.println("Registros Modificados = " + registrosModificados);

        // Redirigiendo a la accion por default (listado actualizado de clientes)
        this.accionDefalult(request, response);
    }

}
