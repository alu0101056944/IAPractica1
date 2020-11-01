package practica.frontend;

import java.awt.Color;
import java.awt.Graphics;

import practica.backend.Cell;

public class GUICelda {
	
	private Cell celda;

	private int posX, posY;
	
	private int indiceMatrizI, indiceMatrizJ;
	
	private int longitud;
	
	public GUICelda(Cell celda) {
		this.setCelda(celda);
	}
	
	/**
	 * Dibuja un cuadrado de color con bordes negros. 
	 * @param posX, de izquierda a derecha
	 * @param posY, de arriba a abajo
	 * @param longitudCelda o tamanyo de celda. Es un cuadrado. 
	 * @param graficos obtenidos del metodo paint
	 */
	public void dibujarCelda(Graphics graficos) {
		graficos.setColor(calcularColor());
		graficos.fillRect(this.posX, this.posY, getLongitud(), getLongitud());
		graficos.setColor(Color.YELLOW);
		graficos.drawRect(this.posX, this.posY, getLongitud(), getLongitud());
	}
	
	/**
	 * Llamado antes de dibujar una celda en la matriz grafica del programa
	 * @param celda a evaluar
	 * @return Amarillo si esta ocupada, azul si inicial, negra si esta libre
	 */
	private Color calcularColor() {
		if(celda.isInitial()) {
			return Color.CYAN;
		}else if(celda.isFinish()) {
			return Color.GREEN;
		}else if(celda.isOccupied()) {
			return Color.YELLOW;
		}
		return new Color(167, 184, 164);			//Variacion de verde suave para los ojos
	}

	/**
	 * @return the celda
	 */
	public Cell getCelda() {
		return celda;
	}

	/**
	 * @param celda the celda to set
	 */
	public void setCelda(Cell celda) {
		this.celda = celda;
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
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
	 * @return the indiceMatrizI
	 */
	public int getIndiceMatrizI() {
		return this.indiceMatrizI;
	}

	/**
	 * @param indiceMatrizI the indiceMatrizI to set
	 */
	public void setIndiceMatrizI(int indiceMatrizI) {
		this.indiceMatrizI = indiceMatrizI;
	}

	/**
	 * @return the indiceMatrizJ
	 */
	public int getIndiceMatrizJ() {
		return this.indiceMatrizJ;
	}

	/**
	 * @param indiceMatrizJ the indiceMatrizJ to set
	 */
	public void setIndiceMatrizJ(int indiceMatrizJ) {
		this.indiceMatrizJ = indiceMatrizJ;
	}
	
}
