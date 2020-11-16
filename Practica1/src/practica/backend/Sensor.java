package practica.backend;

public class Sensor {

	private Celda celdaAComprobar;
	
	public Sensor(Celda celdaConCoche) {
		setCeldaAComprobar(celdaConCoche);
	}
	
	public boolean celdaLibre() {
		return !getCeldaAComprobar().estaOcupada();
	}

	public Celda getCeldaAComprobar() {
		return celdaAComprobar;
	}

	public void setCeldaAComprobar(Celda celdaConCoche) {
		this.celdaAComprobar = celdaConCoche;
	}
}
