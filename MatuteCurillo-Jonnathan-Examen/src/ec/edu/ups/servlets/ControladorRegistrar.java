package ec.edu.ups.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.edu.ups.dao.ClienteDAO;
import ec.edu.ups.dao.DAOGuia;
import ec.edu.ups.modelo.Cliente;

/**
 * Servlet implementation class Registrar
 */

@WebServlet("/ControladorRegistrar")
public class ControladorRegistrar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControladorValidacionParametros control;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControladorRegistrar() {
		super();
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = null;
		doGet(request, response);

		control = new ControladorValidacionParametros();
		String url = "";
		url = "/JSPs/Registrar.jsp";
		ClienteDAO clienteDAO = DAOGuia.getGuia().getClienteDAO();

		Cliente cli = null;
		boolean ci = false;
		boolean no = false;
		boolean ap = false;
		boolean co = false;
		boolean pa = false;
		boolean paw = false;
		boolean iguales = false;
		


		String cedula = request.getParameter("cedula");
		String nombre = request.getParameter("nombre");
		String direccion = request.getParameter("direccion");
		String telefono = request.getParameter("email");
		String pass = request.getParameter("password");
		String confirma = request.getParameter("confirma");


		String validacionesCedula = "";
		String validacionesNombre = "";
		String validacionesdireccion = "";
		String validacionestelefono = "";
		String validacionesPassw = "";
		String validacionesPasswConf = "";

		if(cedula.equals("")) 
			validacionesCedula += "La cedula esta vacia";
		else {
			if (control.validadorDeCedula(cedula)){
				ci = true;
			}else
				validacionesCedula += "La cedula es incorrecta";
		}


		if(nombre.equals("")) 
			validacionesNombre += "El nombre esta vacio";
		else {
			if(control.validarTexto(nombre)) {
				no = true;
			}else {

				validacionesNombre += "Ingrese solo letras";

			}

		}

		if(direccion.equals("")) 
			validacionesdireccion += "El direccion esta vacio";
		else {
			if(control.validarTexto(direccion)) {
				ap = true;
			}else {

				validacionesdireccion += "Ingrese solo letras";

			}


		}

		if(telefono.equals("")) 
			validacionestelefono += "El telefono esta vacio";
		else{
			co = true;
		}


		if(ci && no && ap && co && pa && paw && iguales) {

			boolean ok = true;
			List<Cliente> clientes = clienteDAO.find();

			for (Cliente cliente : clientes) {
				if(cedula.equals(cliente.getCedula())) {
					ok = false;
					validacionesCedula = "Cedula registrada actualmente!";

				}
				if(cedula.equals(cliente.getTelefono())) {
					ok = false;
					validacionestelefono = "telefono registrado actualmente!";
				}
			}
			if(ok) {
				cli = new Cliente(cedula, nombre, direccion, telefono);
				clienteDAO.create(cli);
				session = request.getSession(true); 

				session.setAttribute("accesos", 1); 
				session.setAttribute("cliente", cli);

				System.out.println(cli);
				url = "/JSPs/CuentaPrincipal.jsp";

			}

		}

		request.setAttribute("cedula", cedula);
		request.setAttribute("nombre", nombre);
		request.setAttribute("direccion", direccion);
		request.setAttribute("telefono", telefono);
		request.setAttribute("cliente", cli);
		request.getRequestDispatcher(url).forward(request, response);

	}

}
