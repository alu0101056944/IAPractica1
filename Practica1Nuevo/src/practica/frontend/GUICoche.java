package practica.frontend;

import java.awt.Color;
import java.awt.Graphics;

public class GUICoche {
	
	private int posBaseX, posBaseY;
	private int margenX, margenY;
	private int longitud;
	
	public GUICoche() {}
	
	public GUICoche(int posX, int posY, int longitud) {
		this.setPosBaseX(posX);
		this.setPosBaseY(posY);
		this.setLongitud(longitud);
	}
	
	public void dibujarCoche(Graphics graficos) {
		graficos.setColor(Color.PINK);
		graficos.fillRect(getPosBaseX(), getPosBaseY(), getLongitud(), getLongitud());
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

	/**
	 * @return the margenX
	 */
	public int getMargenX() {
		return margenX;
	}

	/**
	 * @param margenX the margenX to set
	 */
	public void setMargenX(int margenX) {
		this.margenX = margenX;
	}

	/**
	 * @return the margenY
	 */
	public int getMargenY() {
		return margenY;
	}

	/**
	 * @param margenY the margenY to set
	 */
	public void setMargenY(int margenY) {
		this.margenY = margenY;
	}

}
