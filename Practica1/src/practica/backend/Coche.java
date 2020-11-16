package practica.backend;

import java.util.ArrayList;

public class Coche {
	
	private SistemaSensores sensores;
	
	public SistemaSensores getSensores() {
		return sensores;
	}

	public void setSensores(SistemaSensores sensores) {
		this.sensores = sensores;
	}

	private Celda celdaConCoche, celdaAnterior;
	
	private ArrayList<String> listaAcciones;

	public Coche(Celda celdaConCoche) {
		sensores = new SistemaSensores(celdaConCoche);
		this.setCeldaConCoche(celdaConCoche);
		listaAcciones = new ArrayList<String>();
	}

	public void limpiarListaAcciones() {
		listaAcciones.clear();
	}
	
	/**
	 * Vuelve atras una vez hacia la celda previa y borra la accion del historial
	 */
	public void volverAtras() {
		listaAcciones.remove(listaAcciones.size()-1);
		if(getCeldaAnterior()!=null) setCeldaConCoche(getCeldaAnterior());;
	}
	
	public ArrayList<String> getListaAcciones() {
		return listaAcciones;
	}

	public void setListaAcciones(ArrayList<String> listaAcciones) {
		this.listaAcciones = listaAcciones;
	}
	
	public Celda getCeldaConCoche() {
		return celdaConCoche;
	}

	public void setCeldaConCoche(Celda celdaConCoche) {
		this.celdaConCoche = celdaConCoche;
	}
	
	/**
	 * Le dice al algoritmo que busque. El algoritmo utilizara los metodos
	 * de moverArriba(), etc para ir actualizando la lista de acciones.
	 */
	public void buscarCamino(Celda celdaMeta) {
		IAlgoritmoBusqueda busqueda = new AlgoritmoBusquedaEstrella(this);
		sensores.setCeldaConCoche(getCeldaConCoche());
		busqueda.buscar(celdaMeta); //Ejecuta el algoritmo
	}
	
	public void moverArriba() {
		if(sensores.comprobarSiCeldaArribaLibre()) {
			setCeldaAnterior(getCeldaConCoche());
			setCeldaConCoche(getCeldaConCoche().getCeldaArriba());
			getListaAcciones().add("arriba");
		}
	}
	
	public void moverAbajo() {
		if(sensores.comprobarSiCeldaAbajoLibre()) {
			setCeldaAnterior(getCeldaConCoche());
			setCeldaConCoche(getCeldaConCoche().getCeldaAbajo());
			getListaAcciones().add("abajo");
		}
	}
	
	public void moverDerecha() {
		if(sensores.comprobarSiCeldaDerechaLibre()) {
			setCeldaAnterior(getCeldaConCoche());
			setCeldaConCoche(getCeldaConCoche().getCeldaDerecha());
			getListaAcciones().add("derecha");
		}
	}
	
	public void moverIzquierda() {
		if(sensores.comprobarSiCeldaIzquierdaLibre()) {
			setCeldaAnterior(getCeldaConCoche());
			setCeldaConCoche(getCeldaConCoche().getCeldaIzquierda());
			getListaAcciones().add("izquierda");
		}
	}

	public Celda getCeldaAnterior() {
		return celdaAnterior;
	}

	public void setCeldaAnterior(Celda celdaAnterior) {
		this.celdaAnterior = celdaAnterior;
	}
	
}
