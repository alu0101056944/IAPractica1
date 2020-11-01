package practica.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GUIMovementHandler{
	
	private int posFinalX, posFinalY;
	
	private GUICoche coche;
	private GUIPanelEscenario panelEscenario;
	
	private int tiempoEspera = 1000;		//En milisegundos (ms)
	
	private int tipoMovimiento;
	
	private Timer t;
	private ActionListenerPrueba ejecutor;
	
	public GUIMovementHandler(GUIPanelEscenario panelEscenario, GUICoche coche) {
		this.setPanelEscenario(panelEscenario);
		this.setCoche(coche);
		ejecutor = new ActionListenerPrueba(panelEscenario, coche, 25, 1);
		t = new Timer(30, ejecutor);
	}
	
	public void moverADerecha() {
		System.out.println("GUIMovementHandler.moverADerecha(): A DERECHA");
		t.restart();
	}
	
	public void moverAIzquierda() {
		System.out.println("GUIMovementHandler.moverADerecha(): A IZQUIERDA");
		t.restart();
	}
	
	private void moverArriba() {
		System.out.println("GUIMovementHandler.moverADerecha(): A IZQUIERDA");
		t.restart();
	}

	private void moverAbajo() {
		System.out.println("GUIMovementHandler.moverADerecha(): A IZQUIERDA");
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
}

class ActionListenerPrueba implements ActionListener{

	private GUICoche coche;
	
	private int posFinalX;
	
	private GUIPanelEscenario escenario;
	
	private int inc;
	
	public ActionListenerPrueba(GUIPanelEscenario escenario, GUICoche coche, int valor, int inc) {
		this.coche = coche;
		this.escenario = escenario;
		this.inc = inc;
		posFinalX = this.coche.getPosBaseX()+valor;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("GUIMovementHandler.action(): celdaConCoche.i,j: " + 
	escenario.getCeldaConCoche().getIndiceMatrizI() + " , " + escenario.getCeldaConCoche().getIndiceMatrizJ());
		if(this.coche.getMargenX()>=posFinalX) {
			Timer timer = (Timer) arg0.getSource();
			timer.stop();
			escenario.setMoviendose(false);
			escenario.setPendienteCalcularCeldaConCoche(true);
		}
		coche.setMargenX(coche.getMargenX()+inc);
		escenario.repaint();
	}
	
}
