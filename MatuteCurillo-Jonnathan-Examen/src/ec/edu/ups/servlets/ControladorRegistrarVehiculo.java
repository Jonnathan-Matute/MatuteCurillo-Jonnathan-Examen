package ec.edu.ups.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.edu.ups.dao.VehiculoDAO;
import ec.edu.ups.dao.ClienteDAO;
import ec.edu.ups.dao.DAOGuia;
import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.Vehiculo;

/**
 * Servlet implementation class Registrar
 */

@WebServlet("/ControladorRegistrar")
public class ControladorRegistrarVehiculo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControladorValidacionParametros control;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControladorRegistrarVehiculo() {
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
		control = new ControladorValidacionParametros();
		String opcion = request.getParameter("amodeloion");

		if(opcion.equals("crear_Vehiculo")) {
			ClienteDAO ClienteDAO = DAOGuia.getGuia().getClienteDAO();
			VehiculoDAO VehiculoDAO = DAOGuia.getGuia().getVehiculoDAO();
			Vehiculo ve = null;
			String Vehiculo11 = "";
			String placa = request.getParameter("placa");
			String marca = request.getParameter("marca");
			String modelo = request.getParameter("modelo");

			Vehiculo11 = request.getParameter("Vehiculo");

			String validacionVehiculo = "";
			Cliente Cliente = ClienteDAO.read(modelo);
			if(Vehiculo11 == "") {
				validacionVehiculo += "Por favor ingresar datos!";

			}
			
			Cliente cli = ClienteDAO.read(modelo);
			List<Vehiculo> Vehiculos = VehiculoDAO.findByCliente(cli); 
			for (Vehiculo Vehiculo : Vehiculos) {
				if(Vehiculo11.equals(Vehiculo.getPlaca())) { 

					request.setAttribute("er", Vehiculo11);
					validacionVehiculo += "Placa Existente";				
				}
			}

			if(validacionVehiculo=="") {

				ve = new Vehiculo(Vehiculo11, marca, modelo, Cliente);
				VehiculoDAO.create(ve);
				Cliente = ClienteDAO.read(modelo);

				request.setAttribute("bien", "Se ha registrado exitosamente el Marca ");

				request.setAttribute("num", ve.getPlaca());
			}

			Vehiculos.add(ve);
			
			Cliente.setVehiculos(Vehiculos);

			request.getSession().setAttribute("Cliente", Cliente);

			request.setAttribute("validacionVehiculo", validacionVehiculo);

			request.getRequestDispatcher("/JSPs/CuentaPrincipal.jsp").forward(request, response);
		}

		if(opcion.equals("buscar_Vehiculo")) {
			String Vehiculo = request.getParameter("Vehiculo");
			String validaciones = "";
			boolean ban = true;


			ClienteDAO ClienteDAO = DAOGuia.getGuia().getClienteDAO();
			VehiculoDAO VehiculoDAO = DAOGuia.getGuia().getVehiculoDAO();
			if(Vehiculo == "") {
				ban=false;
				validaciones += "campo vacio";
				request.setAttribute("validacionVehiculo", validaciones);
			}else {
				if(!control.validarMarca(Vehiculo)) {
					validaciones += "Ingrese solamente Marcas";
					request.setAttribute("validacionVehiculo", validaciones);
					ban=false;
				}else {
					if(Vehiculo.length() !=7 && Vehiculo.length() !=10) {
						ban=false;
						validaciones += "Ingrese placa correcta";
						request.setAttribute("validacionVehiculo", validaciones);

					}
				}
			}


			if(ban) {

				Vehiculo veFInal = null;
				if(VehiculoDAO.read(Vehiculo)!=null){
					veFInal = VehiculoDAO.read(Vehiculo);
					request.setAttribute("datosVehiculo", "Datos Del Vehiculo");
					request.setAttribute("infoVehiculo", "Vehiculo: ");
					request.setAttribute("infoMarca", "Marca: ");
					request.setAttribute("infoModelo", "Modelo: ");
					request.setAttribute("Vehiculo", veFInal);

				}else {
					validaciones="No se ha encontrado Marca";
					request.setAttribute("validacionVehiculo", validaciones);
				}
			}


			request.getRequestDispatcher("/JSPs/ActualizarVehiculo.jsp").forward(request, response);
		}

		if(opcion.equals("modificar_Vehiculo")) {
			ClienteDAO ClienteDAO = DAOGuia.getGuia().getClienteDAO();
			VehiculoDAO VehiculoDAO = DAOGuia.getGuia().getVehiculoDAO();
			Vehiculo ve = null;
			String ttt = request.getParameter("vePar");
			HttpSession misession= (HttpSession) request.getSession();
			Cliente us= (Cliente) misession.getAttribute("Cliente");
			String actualizar = request.getParameter("buscarVehiculo");
			String elimina = request.getParameter("eliminar");
			String nuevaMarca = request.getParameter("veActualizar");
			String nuevoModelo = request.getParameter("marca");
			String validaciones = "";
			boolean ban = true;

			if(nuevaMarca == "") {
				ban=false;
				validaciones += "campo vacio";
				request.setAttribute("validacionVehiculoActualizar", validaciones);
			}else {
				if(!control.validarMarca(nuevaMarca)) {
					validaciones += "Ingrese solamente Marcas";
					request.setAttribute("validacionVehiculoActualizar", validaciones);
					ban=false;
				}else {
					if(nuevaMarca.length() !=7 && nuevaMarca.length() !=10) {
						ban=false;
						validaciones += "Ingrese Marca correcto";
						request.setAttribute("validacionVehiculoActualizar", validaciones);

					}
				}
			}



			if(actualizar != null && ban == true) {
				Vehiculo veFInal = null;
				if(nuevaMarca.equals(ttt)) {
					Vehiculo vee = VehiculoDAO.read(nuevaMarca);
					vee.setMarca(nuevaMarca);
					vee.setModelo(nuevoModelo);
					vee.setCliente(us);
					VehiculoDAO.update(vee);
					request.setAttribute("valOk", "Actualizado el Marca " + vee.getMarca());
				}else {
					Vehiculo vee = VehiculoDAO.read(ttt);
					vee.setMarca(nuevaMarca);
					vee.setModelo(nuevoModelo);
					vee.setCliente(us);
					VehiculoDAO.update(vee);
					request.setAttribute("valOk", "Actualizado el Marca " +nuevaMarca);
				}
			}

			if(elimina != null && ban == true) {
				Vehiculo veFInal = null;
				if(VehiculoDAO.read(nuevaMarca)!=null) {
					veFInal = VehiculoDAO.read(nuevaMarca);
					VehiculoDAO.delete(veFInal);
					request.setAttribute("valOk", "Eliminado el Marca " + nuevaMarca);
				}else {
					validaciones += "No existe el Marca ingresado";
					request.setAttribute("validacionVehiculoActualizar", validaciones);
				}
			}

			List<Vehiculo> Vehiculos = VehiculoDAO.findByCliente(us);
			us.setVehiculos(Vehiculos);
			request.getSession().setAttribute("Cliente", us);
			request.getRequestDispatcher("/JSPs/ActualizarVehiculo.jsp").forward(request, response);

		}


	}
}
