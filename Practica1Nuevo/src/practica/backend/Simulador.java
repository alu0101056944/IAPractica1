package practica.backend;

import java.util.ArrayList;

import practica.frontend.GUICelda;

public class Simulador {
	
	private MatrizCeldas escenario;
	private Coche coche;
	
	public Simulador() {
		setEscenario(new MatrizCeldas(10, 25, 0, 0, 4, 24));
		setCoche(new Coche(escenario.getCeldaInicial()));
	}
	
	public void ejecutarBusqueda(GUICelda[][] escenario) {
		actualizarEscenario(escenario);
		coche.limpiarListaAcciones();
		coche.setCeldaConCoche(getEscenario().getCeldaInicial());
		coche.buscarCamino(getEscenario().getCeldaMeta());
	}
	
	/**
	 * Obtengo el escenario grafico y saco la parte logica de las celdas
	 * sobre el que el algoritmo de busqueda trabaje
	 * @param escenario Escenario creado graficamente
	 */
	private void actualizarEscenario(GUICelda[][] escenario) {
		Celda[][] nuevoEscenario = new Celda[escenario.length][escenario[0].length];
		for(int i = 0; i < escenario.length; i++) {
			for(int j = 0; j < escenario[0].length; j++) {
				nuevoEscenario[i][j] = escenario[i][j].getCelda();
			}
		}
		getEscenario().setMatrizCeldas(nuevoEscenario);
	}

	public MatrizCeldas getEscenario() {
		return escenario;
	}

	public void setEscenario(MatrizCeldas escenario) {
		this.escenario = escenario;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	public ArrayList<String> getListaAcciones() {
		return coche.getListaAcciones();
	}
	
}
