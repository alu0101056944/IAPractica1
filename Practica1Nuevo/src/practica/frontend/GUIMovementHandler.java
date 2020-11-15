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
	private ActionListenerMover ejecutor;
	
	private GUICelda celdaObjetivo;
	
	private ArrayList<String> listaAcciones;
	private ArrayList<GUICelda> listaObjetivos;
	
	private int indiceMovimientoActual;
	
	public GUIMovementHandler(GUIPanelEscenario panelEscenario, GUICoche coche) {
		this.setPanelEscenario(panelEscenario);
		this.setCoche(coche);
		listaAcciones = new ArrayList<String>();
		listaObjetivos = new ArrayList<GUICelda>();
		ejecutor = new ActionListenerMover(panelEscenario, coche);
		t = new Timer(7, ejecutor);
	}
	
	public void procesarMovimiento() {
		if(indiceMovimientoActual<listaAcciones.size()) {
			String sigMovimiento = listaAcciones.get(indiceMovimientoActual);
			setCeldaObjetivo(listaObjetivos.get(indiceMovimientoActual));
//			System.err.println("celdaSig:" + listaObjetivos.get(indiceMovimientoActual).getIndiceMatrizI() + " , " + 
//					listaObjetivos.get(indiceMovimientoActual).getIndiceMatrizJ());
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
//		System.err.println("GUIMovementHandler.formarListasCeldasCamino, fila:" + filaCeldaObjetivo + " , columna:" + columnaCeldaObjetivo);
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
//		System.out.println("GUIMovementHandler.moverADerecha(): A DERECHA");
		ejecutor.setCeldaObjetivo(celdaObjetivo);
		ejecutor.calcularDireccion();
		ejecutor.setNecesitaCalcularVelocidad(true);
		t.restart();
	}
	
	public void moverAIzquierda() {
//		System.out.println("GUIMovementHandler.moverAIzquierda(): A IZQUIERDA");
		ejecutor.setCeldaObjetivo(celdaObjetivo);
		ejecutor.calcularDireccion();
		ejecutor.setNecesitaCalcularVelocidad(true);
		t.restart();
	}
	
	public void moverArriba() {
//		System.out.println("GUIMovementHandler.moverArriba(): Arriba");
		ejecutor.setCeldaObjetivo(celdaObjetivo);
		ejecutor.calcularDireccion();
		ejecutor.setNecesitaCalcularVelocidad(true);
		t.restart();
	}

	public void moverAbajo() {
//		System.out.println("GUIMovementHandler.moverAbajo(): Abajo");
		ejecutor.setCeldaObjetivo(celdaObjetivo);
		ejecutor.calcularDireccion();
		ejecutor.setNecesitaCalcularVelocidad(true);
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

class ActionListenerMover implements ActionListener{

	private GUICoche coche;
	
	private GUIPanelEscenario escenario;
	
	private GUICelda celdaObjetivo;
	
	private int direccionX, direccionY;
	
	private int posXFinal, posYFinal, posXCoche, posYCoche;
		
	private boolean necesitaCalcularVelocidad = false;
	
	public ActionListenerMover(GUIPanelEscenario escenario, GUICoche coche) {
		this.coche = coche;
		this.escenario = escenario;
		if(celdaObjetivo!=null) {
			actualizarValoresPosicion();
			calcularDireccion();
		}
	}
	
	public void calcularDireccion() {
		if(celdaObjetivo.getPosX()<escenario.getCeldaConCoche().getPosX()) {
			this.direccionX = -1;
//			System.err.print("GUIMovementHandler.calcularDireccion(): direccion establecida a izquierda");
		}else if(celdaObjetivo.getPosX()>escenario.getCeldaConCoche().getPosX()) {
			this.direccionX = 1;
//			System.err.print("GUIMovementHandler.calcularDireccion(): direccion establecida a derecha");
		}else {
			direccionX = 0;
//			System.err.print("GUIMovementHandler.calcularDireccion(): la celda objetivo es la misma que la actual, asigno 0 a la dirX");
		}
		
		if(celdaObjetivo.getPosY()<escenario.getCeldaConCoche().getPosY()) {
			this.direccionY = -1;
//			System.err.print("GUIMovementHandler.calcularDireccion(): direccion establecida hacia arriba");
		}else if(celdaObjetivo.getPosY()>escenario.getCeldaConCoche().getPosY()) {
			this.direccionY = 1;
//			System.err.print("GUIMovementHandler.calcularDireccion(): direccion establecida hacia abajo");
		}else {
			direccionY = 0;
//			System.err.print("GUIMovementHandler.calcularDireccion(): la celda objetivo es la misma que la actual, asigno 0 a la dirY");
		}
	}
	
	public void setCeldaObjetivo(GUICelda nuevaCeldaObjetivo) {
		this.celdaObjetivo = nuevaCeldaObjetivo;
	}
	
	public void setNecesitaCalcularVelocidad(boolean necesitaCalcularVelocidad) {
		this.necesitaCalcularVelocidad = necesitaCalcularVelocidad;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(celdaObjetivo!=null) {
			actualizarValoresPosicion();
			if(necesitaCalcularVelocidad) {
				calcularVelocidad(arg0);
				setNecesitaCalcularVelocidad(false);
			}
//			System.out.println("GUIMovementHandler.action(): posXFinal: " + posXFinal + " |posYFinal: " + posYFinal + "|posXCoche:" + posXCoche +
//					"|posYCoche:" + posYCoche);
//			System.out.println("GUIMovementHandler.action(): comprueba posiciones objetivo");
			if(haLlegado()) {
				Timer timer = (Timer) arg0.getSource();
				timer.stop();
				escenario.setMoviendose(false);
				escenario.setPendienteCalcularCeldaConCoche(true);
				escenario.repaint();
//				System.err.println("GUIMovementHandler.action(): objetivo cumplido, movimiento parado");
				return;
			}else {
				moverSegunDireccion();
			}
			
			escenario.repaint();
		}
	}
	
	private void calcularVelocidad(ActionEvent e) {
//		System.err.println("GUIMovementHandler.timer.calcularVelocidad(...)");
		int distanciaHaciaXFinal = Math.abs(coche.getPosBaseX()-celdaObjetivo.getPosX());
		int distanciaHaciaYFinal = Math.abs(coche.getPosBaseY()-celdaObjetivo.getPosY());
		int distanciaTotal = (int)Math.sqrt( (distanciaHaciaXFinal*distanciaHaciaXFinal) + (distanciaHaciaYFinal*distanciaHaciaYFinal) );
//		System.err.println("velocidad: " + (distanciaTotal));
		Timer t = ((Timer)e.getSource());
//		t.setDelay(1/(distanciaTotal*distanciaTotal)); demasiado lento
		t.setDelay(0);
	}
	
	private void actualizarValoresPosicion() {
		posXFinal = celdaObjetivo.getPosX()+(celdaObjetivo.getLongitud()/2);
		posYFinal = celdaObjetivo.getPosY()+(celdaObjetivo.getLongitud()/2);
		posXCoche = (this.coche.getPosBaseX()+this.coche.getMargenX());
		posYCoche = (this.coche.getPosBaseY()+this.coche.getMargenY());
	}
	
	private void moverSegunDireccion() {
		if(direccionX!=0 && direccionY==0) {//horizontal
			coche.setMargenX(coche.getMargenX()+(direccionX));
//			System.err.println("GUIMovementHandler.action(): moverse en horizontal");
		}else if (direccionX==0 && direccionY!=0) {//vertical
			coche.setMargenY(coche.getMargenY()+(direccionY));
//			System.err.println("GUIMovementHandler.action(): moverse en vertical");
		}
	}
	
	private boolean haLlegado() {
		if(direccionX==1 && direccionY==0) {//derecha
			return (posXCoche>=posXFinal);
		}else if (direccionX==-1 && direccionY==0) {//izquierda
			return (posXCoche<=posXFinal);
		}else if (direccionX==0 && direccionY==1) {//abajo
			return (posYCoche>=posYFinal);
		}else if (direccionX==0 && direccionY==-1) {//arriba
			return (posYCoche<=posYFinal);
		}
		return false;
	}

}
