package ec.edu.ups.servlets;

public class ControladorValidacionParametros {

	public ControladorValidacionParametros() {

	}

	public boolean validadorDeCedula(String cedula) {
		boolean cedulaCorrecta = false;

		try {

			if (cedula.length() == 10)
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9,10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					}
					else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			System.out.println("Una excepcion ocurrio en el proceso de validadcion");
			cedulaCorrecta = false;
		}

		if (cedula.equals("0000000000"))
			cedulaCorrecta = false;

		if (!cedulaCorrecta) {
			System.out.println("La Cédula ingresada es Incorrecta");
		}


		return cedulaCorrecta;
	}

	public boolean validarTexto(String texto) {
		boolean ban = true;
		for (int i = 0; i < texto.length(); i++) {
			char o = texto.charAt(i);
			if(o > 32 && (o<65 || o > 90) && (o<97 || o >122))
				ban = false;

		}	

		return ban;
	}

	public boolean validarNumero(String texto) {
		boolean ban = true;
		for (int i = 0; i < texto.length(); i++) {
			char o = texto.charAt(i);
			if(!(o >= 48 && o <= 57)) {
				ban = false;
			}


		}	

		return ban;
	}

	public boolean validarMarca(String vehiculo) {
		return false;
	}

	public boolean validarfechaHoraIngeso(String ticket) {
		// TODO Auto-generated method stub
		return false;
	}



}
