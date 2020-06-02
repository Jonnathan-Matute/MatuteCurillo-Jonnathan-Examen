package ec.edu.ups.servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.edu.ups.dao.TicketDAO;
import ec.edu.ups.dao.ClienteDAO;
import ec.edu.ups.dao.DAOGuia;
import ec.edu.ups.modelo.Cliente;
import ec.edu.ups.modelo.Ticket;
import ec.edu.ups.modelo.Vehiculo;

/**
 * Servlet implementation class Registrar
 */

@WebServlet("/ControladorRegistrar")
public class ControladorRegistrarTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControladorValidacionParametros control;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControladorRegistrarTicket() {
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
		String opcion = request.getParameter("anumeroion");

		if(opcion.equals("crear_Ticket")) {
			ClienteDAO ClienteDAO = DAOGuia.getGuia().getClienteDAO();
			TicketDAO TicketDAO = DAOGuia.getGuia().getTicketDAO();
			Ticket ve = null;
			String Ticket11 = "";
			String fechaHoraIngeso = request.getParameter("fechaHoraIngeso");
			String fechaHoraSalida = request.getParameter("fechaHoraSalida");
			String numero = request.getParameter("numero");

			Ticket11 = request.getParameter("Ticket");

			String validacionTicket = "";
			Cliente Cliente = ClienteDAO.read(numero);
			if(Ticket11 == "") {
				validacionTicket += "Por favor ingresar datos!";

			}
			
			Cliente cli = ClienteDAO.read(numero);
			List<Vehiculo> Tickets = TicketDAO.findByCliente(cli); 
			for (Vehiculo Ticket : Tickets) {
				if(Ticket11.equals(Ticket.getfechaHoraIngeso())) { 

					request.setAttribute("er", Ticket11);
					validacionTicket += "Ticket Existente";				
				}
			}

			if(validacionTicket=="") {

				ve = new Ticket();
				TicketDAO.create(ve);
				Cliente = ClienteDAO.read(numero);

				request.setAttribute("bien", "Se ha registrado exitosamente el fechaHoraSalida ");

				request.setAttribute("num", ve.getFechaHoraIngeso());
			}

			Tickets.addAll((Collection<? extends Vehiculo>) ve);
			
			Cliente.setTickets(Tickets);

			request.getSession().setAttribute("Cliente", Cliente);

			request.setAttribute("validacionTicket", validacionTicket);

			request.getRequestDispatcher("/JSPs/CuentaPrincipal.jsp").forward(request, response);
		}

		if(opcion.equals("buscar_Ticket")) {
			String Ticket = request.getParameter("Ticket");
			String validaciones = "";
			boolean ban = true;


			ClienteDAO ClienteDAO = DAOGuia.getGuia().getClienteDAO();
			TicketDAO TicketDAO = DAOGuia.getGuia().getTicketDAO();
			if(Ticket == "") {
				ban=false;
				validaciones += "campo vacio";
				request.setAttribute("validacionTicket", validaciones);
			}else {
				if(!control.validarfechaHoraIngeso(Ticket)) {
					validaciones += "Ingrese solamente fechaHoraSalidas";
					request.setAttribute("validacionTicket", validaciones);
					ban=false;
				}else {
					if(Ticket.length() !=7 && Ticket.length() !=10) {
						ban=false;
						validaciones += "Ingrese fechaHoraIngeso correcta";
						request.setAttribute("validacionTicket", validaciones);

					}
				}
			}


			if(ban) {

				Ticket veFInal = null;
				if(TicketDAO.readVehiculo(Ticket)!=null){
					veFInal = TicketDAO.readVehiculo(Ticket);
					request.setAttribute("datosTicket", "Datos Del Ticket");
					request.setAttribute("infoTicket", "Ticket: ");
					request.setAttribute("infofechaHoraSalida", "fechaHoraSalida: ");
					request.setAttribute("infonumero", "numero: ");
					request.setAttribute("Ticket", veFInal);

				}else {
					validaciones="No se ha encontrado fechaHoraSalida";
					request.setAttribute("validacionTicket", validaciones);
				}
			}


			request.getRequestDispatcher("/JSPs/ActualizarTicket.jsp").forward(request, response);
		}

		if(opcion.equals("modificar_Ticket")) {
			ClienteDAO ClienteDAO = DAOGuia.getGuia().getClienteDAO();
			TicketDAO TicketDAO = DAOGuia.getGuia().getTicketDAO();
			Ticket ve = null;
			String ttt = request.getParameter("vePar");
			HttpSession misession= (HttpSession) request.getSession();
			Cliente us= (Cliente) misession.getAttribute("Cliente");
			String actualizar = request.getParameter("buscarTicket");
			String elimina = request.getParameter("eliminar");
			String nuevafechaHoraSalida = request.getParameter("veActualizar");
			String nuevonumero = request.getParameter("fechaHoraSalida");
			String validaciones = "";
			boolean ban = true;

			if(nuevafechaHoraSalida == "") {
				ban=false;
				validaciones += "campo vacio";
				request.setAttribute("validacionTicketActualizar", validaciones);
			}else {
				if(!control.validarfechaHoraIngeso(nuevafechaHoraSalida)) {
					validaciones += "Ingrese solamente fechaHoraSalidas";
					request.setAttribute("validacionTicketActualizar", validaciones);
					ban=false;
				}else {
					if(nuevafechaHoraSalida.length() !=7 && nuevafechaHoraSalida.length() !=10) {
						ban=false;
						validaciones += "Ingrese fechaHoraSalida correcto";
						request.setAttribute("validacionTicketActualizar", validaciones);

					}
				}
			}



			if(actualizar != null && ban == true) {
				Ticket veFInal = null;
				if(nuevafechaHoraSalida.equals(ttt)) {
					Ticket vee = TicketDAO.readVehiculo(nuevafechaHoraSalida);
					vee.setFechaHoraSalida(nuevafechaHoraSalida);
					vee.setNumero(nuevonumero);
					vee.setClientes(us);
					TicketDAO.update(vee);
					request.setAttribute("valOk", "Actualizado el fechaHoraSalida " + vee.getFechaHoraSalida());
				}else {
					Ticket vee = TicketDAO.readVehiculo(ttt);
					vee.setFechaHoraSalida(nuevafechaHoraSalida);
					vee.setNumero(nuevonumero);
					vee.setClientes(us);
					TicketDAO.update(vee);
					request.setAttribute("valOk", "Actualizado el fechaHoraSalida " +nuevafechaHoraSalida);
				}
			}

			if(elimina != null && ban == true) {
				Ticket veFInal = null;
				if(TicketDAO.readVehiculo(nuevafechaHoraSalida)!=null) {
					veFInal = TicketDAO.readVehiculo(nuevafechaHoraSalida);
					TicketDAO.delete(veFInal);
					request.setAttribute("valOk", "Eliminado el fechaHoraSalida " + nuevafechaHoraSalida);
				}else {
					validaciones += "No existe el fechaHoraSalida ingresado";
					request.setAttribute("validacionTicketActualizar", validaciones);
				}
			}

			List<Vehiculo> Tickets = TicketDAO.findByCliente(us);
			us.setTickets(Tickets);
			request.getSession().setAttribute("Cliente", us);
			request.getRequestDispatcher("/JSPs/ActualizarTicket.jsp").forward(request, response);

		}


	}
}
