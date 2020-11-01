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
	
	private GUICelda celdaObjetivo;
	
	public GUIMovementHandler(GUIPanelEscenario panelEscenario, GUICoche coche) {
		this.setPanelEscenario(panelEscenario);
		this.setCoche(coche);
		ejecutor = new ActionListenerPrueba(panelEscenario, coche);
		t = new Timer(30, ejecutor);
	}
	
	public void moverADerecha() {
		System.out.println("GUIMovementHandler.moverADerecha(): A DERECHA");
		ejecutor.setCeldaObjetivo(celdaObjetivo);
		ejecutor.calcularDireccion();
		t.restart();
	}
	
	public void moverAIzquierda() {
		System.out.println("GUIMovementHandler.moverADerecha(): A IZQUIERDA");
		ejecutor.setCeldaObjetivo(celdaObjetivo);
		ejecutor.calcularDireccion();
		t.restart();
	}
	
	public void moverArriba() {
		System.out.println("GUIMovementHandler.moverADerecha(): A IZQUIERDA");
		ejecutor.setCeldaObjetivo(celdaObjetivo);
		ejecutor.calcularDireccion();
		t.restart();
	}

	public void moverAbajo() {
		System.out.println("GUIMovementHandler.moverADerecha(): A IZQUIERDA");
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

class ActionListenerPrueba implements ActionListener{

	private GUICoche coche;
	
	private GUIPanelEscenario escenario;
	
	private GUICelda celdaObjetivo;
	
	private int direccionX, direccionY;
	
	public ActionListenerPrueba(GUIPanelEscenario escenario, GUICoche coche) {
		this.coche = coche;
		this.escenario = escenario;
		if(celdaObjetivo!=null) calcularDireccion();
	}
	
	public void calcularDireccion() {
		if(celdaObjetivo.getPosX()<escenario.getCeldaConCoche().getPosX()) {
			this.direccionX = -1;
			System.out.print("GUIMovementHandler.calcularDireccion(): direccion establecida a izquierda");
		}else if(celdaObjetivo.getPosX()>escenario.getCeldaConCoche().getPosX()) {
			this.direccionX = 1;
			System.out.print("GUIMovementHandler.calcularDireccion(): direccion establecida a derecha");
		}else {
			direccionX = 1;
			System.err.print("GUIMovementHandler.calcularDireccion(): la celda objetivo es la misma que la actual, dirX = 1 (derecha)");
		}
		
		if(celdaObjetivo.getPosY()<escenario.getCeldaConCoche().getPosY()) {
			this.direccionY = -1;
			System.out.print("GUIMovementHandler.calcularDireccion(): direccion establecida hacia arriba");
		}else if(celdaObjetivo.getPosY()>escenario.getCeldaConCoche().getPosY()) {
			this.direccionY = 1;
			System.out.print("GUIMovementHandler.calcularDireccion(): direccion establecida hacia abajo");
		}else {
			direccionY = 1;
			System.err.print("GUIMovementHandler.calcularDireccion(): la celda objetivo es la misma que la actual, dirY = 1 (abajo)");
		}
	}
	
	public void setCeldaObjetivo(GUICelda nuevaCeldaObjetivo) {
		this.celdaObjetivo = nuevaCeldaObjetivo;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(celdaObjetivo!=null) {
			int posXFinal = celdaObjetivo.getPosX()+(celdaObjetivo.getLongitud()/2);
			int posYFinal = celdaObjetivo.getPosY()+(celdaObjetivo.getLongitud()/2);
			int posXCoche = (this.coche.getPosBaseX()+this.coche.getMargenX());
			int posYCoche = (this.coche.getPosBaseY()+this.coche.getMargenY());
			System.err.println("GUIMovementHandler.action(): posXFinal: " + posXFinal + " |posYFinal: " + posYFinal + "|posXCoche:" + posXCoche +
					"|posYCoche:" + posYCoche);
			System.out.println("GUIMovementHandler.action(): comprueba posiciones objetivo");
			if(posXCoche==posXFinal && posYCoche==posYFinal) {
				Timer timer = (Timer) arg0.getSource();
				timer.stop();
				escenario.setMoviendose(false);
				escenario.setPendienteCalcularCeldaConCoche(true);
				System.out.println("GUIMovementHandler.action(): objetivo cumplido, movimiento parado");
			}else if(posXCoche==posXFinal && posYCoche!=posYFinal) {
				coche.setMargenY(coche.getMargenY()+(direccionY));
				System.out.println("GUIMovementHandler.action(): moverse en vertical");
			}else if(posXCoche!=posXFinal && posYCoche==posYFinal) {
				coche.setMargenX(coche.getMargenX()+(direccionX));
				System.out.println("GUIMovementHandler.action(): moverse en horizontal");
			}else { //si tanto x como y son distintos
				coche.setMargenX(coche.getMargenX()+(direccionX));
				coche.setMargenY(coche.getMargenY()+(direccionY));
				System.out.println("GUIMovementHandler.action(): moverse en diagonal");
			}
			
			escenario.repaint();
		}
	}
	
}
