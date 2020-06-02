package ec.edu.ups.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ec.edu.ups.dao.DAOGuia;
import ec.edu.ups.dao.ClienteDAO;
import ec.edu.ups.modelo.Cliente;

/**
 * Servlet implementation class LlenarTabla
 */
@WebServlet("/ControladorBuscarContacto")
public class ControladorBuscar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControladorValidacionParametros control;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControladorBuscar() {
		ControladorValidacionParametros control = null;
		
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
		
		control = new ControladorValidacionParametros();
		String name = request.getParameter("opcion");
		String parametro = request.getParameter("parametro");

		ClienteDAO ClienteDAO = DAOGuia.getGuia().getClienteDAO();
		boolean bandera = true;
		Cliente Cliente = null;
		if(parametro == "") {
			String error = "Por favor, ingrese un dato";
			request.setAttribute("error", error);
			bandera = false;
		}

		if(name.equals("cedula") && bandera) {	
			if(control.validadorDeCedula(parametro)) {
				System.out.println("Correcto");
				
				
				if(ClienteDAO.read(parametro)==null) {
					String error = "Cliente no encontrado";

					request.setAttribute("error", error);

					request.setAttribute("parInval", parametro);
				}else {
					Cliente = ClienteDAO.read(parametro);
					request.setAttribute("Cliente", Cliente);

					request.setAttribute("titulo","Informacion del Cliente a buscar:");
					request.setAttribute("nn", "Nombre: ");
					request.setAttribute("cc", "Cedula: ");
					request.setAttribute("crr", "Correo: ");
				}
			}else{
				String error = "Cedula incorrecta";
				request.setAttribute("error", error);

				request.setAttribute("parInval", parametro);
			}

		}else if(name.equals("correo") && bandera){


			if(control.validadorDeCedula(parametro)) {
				
				request.setAttribute("Cliente", Cliente);

				if(ClienteDAO.readByCedula(parametro)== null) {
					String error = "Cliente no encontrado";

					request.setAttribute("error", error);
					request.setAttribute("parInval", parametro);
				}
				else {

					Cliente = ClienteDAO.readByCedula(parametro);
					request.setAttribute("Cliente", Cliente);

					request.setAttribute("titulo","Informacion del Cliente a buscar:");
					request.setAttribute("nn", "Nombre: ");
					request.setAttribute("cc", "Cedula: ");
					request.setAttribute("crr", "Correo: ");
				}
			}else {
				String error = "Correo incorrecto";
				request.setAttribute("error", error);

				request.setAttribute("parInval", parametro);
			}
		}

		request.getRequestDispatcher("JSPs/BuscarContacto.jsp").forward(request, response);
	}

}
