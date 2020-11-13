package practica.backend;

public class SistemaSensores {

	private Sensor sArriba, sAbajo, sDerecha, sIzquierda;

	public SistemaSensores(Celda celdaConCoche) {
		sArriba = new Sensor(celdaConCoche);
		sAbajo = new Sensor(celdaConCoche);
		sDerecha = new Sensor(celdaConCoche);
		sIzquierda = new Sensor(celdaConCoche);
	}
	
	public boolean comprobarSiObstaculoEnDerecha() {
		return getsDerecha().celdaLibre();
	}
	
	public boolean comprobarSiObstaculoEnIzquierda() {
		return getsIzquierda().celdaLibre();
	}
	
	public boolean comprobarSiObstaculoAbajo() {
		return getsAbajo().celdaLibre();
	}
	
	public boolean comprobarSiObstaculoArriba() {
		return getsArriba().celdaLibre();
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

	
}
