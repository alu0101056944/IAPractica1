package practica.backend;

public class Celda {

	private Celda celdaIzq, celdaDer, celdaArriba, celdaAbajo;		//posicion relativa
	
	private boolean ocupada;
	private boolean inicial;
	private boolean meta;

	public Celda() {}
	
	/**
	 * 
	 * @return verdadero si hay un obstaculo
	 */
	public boolean estaOcupada() {
		return ocupada;
	}

	/**
	 * 
	 * @param ocupada verdadero significa que esta obstaculizada
	 */
	public void setOcupada(boolean ocupada) {
		if(esMeta()==false && esInicio()==false) this.ocupada = ocupada;
	}

	/**
	 * @return verdadero si es la celda inicial 
	 */
	public boolean esInicio() {
		return inicial;
	}

	/**
	 * @param inicial verdadero si quiero que sea una celda inicial
	 */
	public void setInicio(boolean inicial) {
		if(esMeta()==false) this.inicial = inicial;
	}

	/**
	 * @return meta, verdadero si es una celda final
	 */
	public boolean esMeta() {
		return meta;
	}

	/**
	 * @param meta final
	 */
	public void setMeta(boolean meta) {
		if(esInicio()==false)this.meta = meta;
	}

	public Celda getCeldaIzquierda() {
		return celdaIzq;
	}

	public void setCeldaIzquierda(Celda celdaIzq) {
		this.celdaIzq = celdaIzq;
	}

	public Celda getCeldaDerecha() {
		return celdaDer;
	}

	public void setCeldaDerecha(Celda celdaDer) {
		this.celdaDer = celdaDer;
	}

	public Celda getCeldaArriba() {
		return celdaArriba;
	}

	public void setCeldaArriba(Celda celdaArriba) {
		this.celdaArriba = celdaArriba;
	}

	public Celda getCeldaAbajo() {
		return celdaAbajo;
	}

	public void setCeldaAbajo(Celda celdaAbajo) {
		this.celdaAbajo = celdaAbajo;
	}
	
	
}
