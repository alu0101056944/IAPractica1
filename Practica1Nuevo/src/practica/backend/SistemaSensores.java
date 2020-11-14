package practica.backend;

public class SistemaSensores {

	private Sensor sArriba, sAbajo, sDerecha, sIzquierda;

	private Celda celdaConCoche;
	
	public SistemaSensores(Celda celdaConCoche) {
		sArriba = new Sensor(celdaConCoche.getCeldaArriba());
		sAbajo = new Sensor(celdaConCoche.getCeldaAbajo());
		sDerecha = new Sensor(celdaConCoche.getCeldaDerecha());
		sIzquierda = new Sensor(celdaConCoche.getCeldaIzquierda());
		setCeldaConCoche(celdaConCoche);
	}
	
	public boolean comprobarSiCeldaDerechaLibre() {
		Celda celdaDerecha = getCeldaConCoche().getCeldaDerecha();
		if(celdaDerecha!=null) {
			setCeldaConCoche(celdaDerecha);
			sDerecha.setCeldaAComprobar(celdaDerecha);
			return getsDerecha().celdaLibre();
		}
		return false;
	}
	
	public boolean comprobarSiCeldaIzquierdaLibre() {
		Celda celdaIzquierda = getCeldaConCoche().getCeldaIzquierda();
		if(celdaIzquierda!=null) {
			setCeldaConCoche(celdaIzquierda);
			sIzquierda.setCeldaAComprobar(celdaIzquierda);
			return getsIzquierda().celdaLibre();
		}
		return false;
	}
	
	public boolean comprobarSiCeldaAbajoLibre() {
		Celda celdaAbajo = getCeldaConCoche().getCeldaAbajo();
		if(celdaAbajo!=null) {
			setCeldaConCoche(celdaAbajo);
			sAbajo.setCeldaAComprobar(celdaAbajo);
			return getsAbajo().celdaLibre();
		}
		return false;
	}
	
	public boolean comprobarSiCeldaArribaLibre() {
		Celda celdaArriba = getCeldaConCoche().getCeldaArriba();
		if(celdaArriba!=null) {
			setCeldaConCoche(celdaArriba);
			sArriba.setCeldaAComprobar(celdaArriba);
			return getsArriba().celdaLibre();
		}
		return false;
	}
	
	public Sensor getsArriba() {
		return sArriba;
	}

	public void setsArriba(Sensor sArriba) {
		this.sArriba = sArriba;
	}

	public Sensor getsAbajo() {
		return sAbajo;
	}

	public void setsAbajo(Sensor sAbajo) {
		this.sAbajo = sAbajo;
	}

	public Sensor getsDerecha() {
		return sDerecha;
	}

	public void setsDerecha(Sensor sDerecha) {
		this.sDerecha = sDerecha;
	}

	public Sensor getsIzquierda() {
		return sIzquierda;
	}

	public void setsIzquierda(Sensor sIzquierda) {
		this.sIzquierda = sIzquierda;
	}

	public Celda getCeldaConCoche() {
		return celdaConCoche;
	}

	public void setCeldaConCoche(Celda celdaConCoche) {
		this.celdaConCoche = celdaConCoche;
	}

	
}
