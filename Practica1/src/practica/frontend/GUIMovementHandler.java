package practica.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GUIMovementHandler{
	
	private int posFinalX, posFinalY;
	
	private GUICoche coche;
	private GUIPanelEscenario panelEscenario;
	
	private int tiempoEspera = 1000;		//En milisegundos (ms)
	
	private int tipoMovimiento;
	
	private Timer t;
	private EventoMover ejecutor;
	
	private GUICelda celdaObjetivo;
	
	private ArrayList<String> listaAcciones;
	private ArrayList<GUICelda> listaObjetivos;
	
	private int indiceMovimientoActual;
	
	public GUIMovementHandler(GUIPanelEscenario panelEscenario, GUICoche coche) {
		this.setPanelEscenario(panelEscenario);
		this.setCoche(coche);
		listaAcciones = new ArrayList<String>();
		listaObjetivos = new ArrayList<GUICelda>();
		ejecutor = new EventoMover(panelEscenario, coche);
		t = new Timer(0, ejecutor);
	}
	
	public void cambiarVelocidad(int nuevaVelocidad) {
		ejecutor.cambiarVelocidad(nuevaVelocidad);
	}
	
	public void procesarMovimiento() {
		if(indiceMovimientoActual<listaAcciones.size()) {
			String sigMovimiento = listaAcciones.get(indiceMovimientoActual);
			setCeldaObjetivo(listaObjetivos.get(indiceMovimientoActual));
			indiceMovimientoActual++;
			panelEscenario.setMoviendose(true);
			if(sigMovimiento.equals("derecha")) {
				this.moverADerecha();
			}else if(sigMovimiento.equals("izquierda")) {
				this.moverAIzquierda();
			}else if(sigMovimiento.equals("arriba")) {
				this.moverArriba();
			}else { //abajo
				this.moverAbajo();
			}
		}
	}
	
	public void agregarListaAcciones(ArrayList<String> acciones) {
		indiceMovimientoActual = 0;
		this.listaAcciones = acciones;
		formarListaCeldasCamino(acciones);											//Actualiza listaObjetivos
		this.procesarMovimiento();
	}
	
	/**
	 * Por cada accion calcula una lista ordenada de celdas objetivo que estan 
	 * en el camino.
	 * @param acciones Lista de acciones
	 */
	private void formarListaCeldasCamino(ArrayList<String> acciones) {
		listaObjetivos = new ArrayList<GUICelda>();
		GUICelda celdaConCoche = panelEscenario.getCeldaConCoche();
		int filaCeldaObjetivo = celdaConCoche.getIndiceMatrizI();
		int columnaCeldaObjetivo = celdaConCoche.getIndiceMatrizJ();
		Iterator<String> itr = acciones.iterator();
		while(itr.hasNext()) {
			String accionActual = itr.next();
			if(accionActual.equals("derecha")) {
				columnaCeldaObjetivo++;
			}else if(accionActual.equals("izquierda")) {
				columnaCeldaObjetivo--;
			}else if(accionActual.equals("arriba")) {
				filaCeldaObjetivo--;
			}else { //abajo
				filaCeldaObjetivo++;
			}
			GUICelda celdaObjetivoEnCamino = panelEscenario.getMatrizGUICeldasEscenario()[filaCeldaObjetivo][columnaCeldaObjetivo];
			listaObjetivos.add(celdaObjetivoEnCamino);
		}
	}
	
	public void moverADerecha() {
		ejecutor.setCeldaObjetivo(celdaObjetivo);
		ejecutor.calcularDireccion();
		t.restart();
	}
	
	public void moverAIzquierda() {
		ejecutor.setCeldaObjetivo(celdaObjetivo);
		ejecutor.calcularDireccion();
		t.restart();
	}
	
	public void moverArriba() {
		ejecutor.setCeldaObjetivo(celdaObjetivo);
		ejecutor.calcularDireccion();
		t.restart();
	}

	public void moverAbajo() {
		ejecutor.setCeldaObjetivo(celdaObjetivo);
		ejecutor.calcularDireccion();
		t.restart();
	}
	
	/**
	 * @return the posFinalX
	 */
	public int getPosFinalX() {
		return posFinalX;
	}

	/**
	 * @param posFinalX the posFinalX to set
	 */
	public void setPosFinalX(int posFinalX) {
		this.posFinalX = posFinalX;
	}

	/**
	 * @return the posFinalY
	 */
	public int getPosFinalY() {
		return posFinalY;
	}

	/**
	 * @param posFinalY the posFinalY to set
	 */
	public void setPosFinalY(int posFinalY) {
		this.posFinalY = posFinalY;
	}

	/**
	 * @return the panelEscenario
	 */
	public JPanel getPanelEscenario() {
		return panelEscenario;
	}

	/**
	 * @param panelEscenario the panelEscenario to set
	 */
	public void setPanelEscenario(GUIPanelEscenario panelEscenario) {
		this.panelEscenario = panelEscenario;
	}

	/**
	 * @return the coche
	 */
	public GUICoche getCoche() {
		return coche;
	}

	/**
	 * @param coche the coche to set
	 */
	public void setCoche(GUICoche coche) {
		this.coche = coche;
	}

	/**
	 * @return tiempoEspera en ms (milisegundos)
	 */
	public int getTiempoEspera() {
		return tiempoEspera;
	}

	/**
	 * @param tiempoEspera en ms (milisegundos)
	 */
	public void setTiempoEspera(int tiempoEspera) {
		this.tiempoEspera = tiempoEspera;
	}

	/**
	 * @return the tipoMovimiento
	 */
	public int getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * @param tipoMovimiento the tipoMovimiento to set
	 */
	public void setTipoMovimiento(int tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public GUICelda getCeldaObjetivo() {
		return celdaObjetivo;
	}

	public void setCeldaObjetivo(GUICelda celdaObjetivo) {
		this.celdaObjetivo = celdaObjetivo;
	}
}

class EventoMover implements ActionListener{

	private GUICoche coche;
	
	private GUIPanelEscenario escenario;
	
	private GUICelda celdaObjetivo;
	
	private int direccionX, direccionY;
	
	private int posXFinal, posYFinal;
	
	private int velocidad;
	
	public EventoMover(GUIPanelEscenario escenario, GUICoche coche) {
		this.coche = coche;
		this.escenario = escenario;
		velocidad = 2;
		if(celdaObjetivo!=null) {
			obtenerPosiciones();
			calcularDireccion();
		}
	}
	
	public void cambiarVelocidad(int nuevaVelocidad) {
		this.velocidad = nuevaVelocidad;
	}
	
	public void calcularDireccion() {
		if(celdaObjetivo.getPosX()<escenario.getCeldaConCoche().getPosX()) {
			this.direccionX = -1;
		}else if(celdaObjetivo.getPosX()>escenario.getCeldaConCoche().getPosX()) {
			this.direccionX = 1;
		}else {
			direccionX = 0;
		}
		
		if(celdaObjetivo.getPosY()<escenario.getCeldaConCoche().getPosY()) {
			this.direccionY = -1;
		}else if(celdaObjetivo.getPosY()>escenario.getCeldaConCoche().getPosY()) {
			this.direccionY = 1;
		}else {
			direccionY = 0;
		}
	}
	
	public void setCeldaObjetivo(GUICelda nuevaCeldaObjetivo) {
		this.celdaObjetivo = nuevaCeldaObjetivo;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(celdaObjetivo!=null) {
			obtenerPosiciones();
			if(haLlegado()) {
				escenario.setMoviendose(false);
				escenario.setPendienteCalcularCeldaConCoche(true);
				escenario.repaint();
				Timer timer = (Timer) arg0.getSource();
				timer.stop();
				return;
			}else {
				moverSegunDireccion();
			}
			
			escenario.repaint();
		}
	}
	
	private void obtenerPosiciones() {
		posXFinal = celdaObjetivo.getPosX()+((celdaObjetivo.getLongitud()/2)-coche.getLongitud()/2);
		posYFinal = celdaObjetivo.getPosY()+((celdaObjetivo.getLongitud()/2)-coche.getLongitud()/2);
	}
	
	private void moverSegunDireccion() {
		if(direccionX!=0 && direccionY==0) {//horizontal
			coche.setPosBaseX(coche.getPosBaseX()+(direccionX*velocidad));
		}else if (direccionX==0 && direccionY!=0) {//vertical
			coche.setPosBaseY(coche.getPosBaseY()+(direccionY*velocidad));
		}
	}
	
	private boolean haLlegado() {
		return (coche.getPosBaseX()==posXFinal && coche.getPosBaseY()==posYFinal);
	}

}
