package practica.frontend;

import java.awt.Color;
import java.awt.Graphics;

public class GUICoche {
	
	private int posBaseX, posBaseY;
	private int longitud;
	
	public GUICoche() {}
	
	public GUICoche(int posX, int posY, int longitud) {
		this.setPosBaseX(posX);
		this.setPosBaseY(posY);
		this.setLongitud(longitud);
	}
	
	/**
	 * Dibujas la carroceria, las ruedas y el parabrisas. Invocado cada
	 * vez que se dibuja el escenario constantemente.
	 * @param graficos encargados de la ventana del escenario
	 */
	public void dibujarCoche(Graphics graficos) {
		dibujarCarroceria(graficos);
		dibujarRuedas(graficos);
		dibujarParabrisas(graficos);
	}
	
	private void dibujarParabrisas(Graphics graficos) {
		graficos.setColor(Color.white);
		graficos.fillRect(getPosBaseX()+(getLongitud()/2)-2,			//posX
				getPosBaseY()+(getLongitud()/2), 						//posY
				getLongitud()/3, 														//largo
				getLongitud()/3); 														//alto
	}

	private void dibujarCarroceria(Graphics graficos) {
		graficos.setColor(Color.RED);
		graficos.fillOval(getPosBaseX(), getPosBaseY(), getLongitud(), getLongitud());
	}
	
	private void dibujarRuedas(Graphics graficos) {
		graficos.setColor(Color.BLACK);
		graficos.fillRect(getPosBaseX(),												//posX
				getPosBaseY()+(getLongitud()/2)-getLongitud()/3, 						//posY
				getLongitud()/3, 														//largo
				getLongitud()/3); 														//alto
		
		graficos.fillRect(getPosBaseX()+getLongitud()-(getLongitud()/3),				//posX
				getPosBaseY()+(getLongitud()/2)-getLongitud()/3, 						//posY
				getLongitud()/3, 														//largo
				getLongitud()/3); 														//alto
		
		graficos.fillRect(getPosBaseX()+getLongitud()-(getLongitud()/3),				//posX
				getPosBaseY()+(getLongitud()/2)+getLongitud()/3, 						//posY
				getLongitud()/3, 														//largo
				getLongitud()/3); 														//alto
	
		graficos.fillRect(getPosBaseX(),												//posX
				getPosBaseY()+(getLongitud()/2)+getLongitud()/3, 						//posY
				getLongitud()/3, 														//largo
				getLongitud()/3); 														//alto
	}
	
	/**
	 * @return the posBaseX
	 */
	public int getPosBaseX() {
		return posBaseX;
	}

	/**
	 * @param posBaseX the posX to set
	 */
	public void setPosBaseX(int posBaseX) {
		this.posBaseX = posBaseX;
	}

	/**
	 * @return the posBaseY
	 */
	public int getPosBaseY() {
		return posBaseY;
	}

	/**
	 * @param posBaseY the posY to set
	 */
	public void setPosBaseY(int posBaseY) {
		this.posBaseY = posBaseY;
	}

	/**
	 * @return the longitud
	 */
	public int getLongitud() {
		return longitud;
	}

	/**
	 * @param longitud the longitud to set
	 */
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

}
