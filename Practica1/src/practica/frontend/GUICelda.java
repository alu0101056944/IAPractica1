package practica.frontend;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import practica.backend.Celda;

public class GUICelda {
	
	private final Random random = new Random();
	
	private Celda celda;
	
	private boolean tuvoCoche;

	private int posX, posY;
	
	private int indiceMatrizI, indiceMatrizJ;
	
	private int longitud;
	
	private int posXGris, posYGris, posXGrisSuave, posYGrisSuave, posXGrisOscuro, posYGrisOscuro;
	
	public GUICelda(Celda celda, int longitud) {
		this.setCelda(celda);
		this.setLongitud(longitud);
		calcularPosicionesDetallesAsfalto();
	}
	
	private void calcularPosicionesDetallesAsfalto() {
		posXGris = random.nextInt(getLongitud()-3);
		posYGris = random.nextInt(getLongitud()-3);
		posXGrisSuave = random.nextInt(getLongitud()-4);
		posXGrisSuave = random.nextInt(getLongitud()-2);
		posXGrisOscuro = random.nextInt(getLongitud()-4);
		posYGrisOscuro = random.nextInt(getLongitud()-2);
	}
	
	/**
	 * Dibuja un cuadrado de color con bordes negros. 
	 * @param posX, de izquierda a derecha
	 * @param posY, de arriba a abajo
	 * @param longitudCelda o tamanyo de celda. Es un cuadrado. 
	 * @param graficos obtenidos del metodo paint
	 */
	public void dibujarCelda(Graphics graficos) {
		graficos.setColor(new Color(100, 100, 100));
		graficos.drawRect(getPosX(), getPosY(), getLongitud(), getLongitud());
		
		if(celda.esInicio()) {
			dibujarDetalleInicio(graficos);
			return;
		}else if(celda.esMeta()) {
			dibujarDetalleMeta(graficos);
			return;
		}else if(celda.estaOcupada()) {
			dibujarObstaculo(graficos);
			return;
		}else if (isTuvoCoche()&&!celda.estaOcupada()) {
			graficos.setColor(Color.DARK_GRAY);
			graficos.fillRect(getPosX(), getPosY(), getLongitud(), getLongitud());
			graficos.setColor(new Color(100, 100, 100));
			graficos.drawRect(getPosX(), getPosY(), getLongitud(), getLongitud());
			dibujarDetalleAsfalto(graficos);
			return;
		}else {
			graficos.setColor(new Color(100, 100, 100));
			graficos.drawRect(getPosX(), getPosY(), getLongitud(), getLongitud());
			dibujarDetalleAsfalto(graficos);
		}
		
	}
	
	private void dibujarObstaculo(Graphics graficos) {
		for(int i = 0; i < getLongitud(); i++) {
			if(i%2==0) {
				graficos.setColor(new Color(255, 133, 51));
				graficos.fillRect(getPosX(), getPosY()+i, getLongitud(), 1);
			}else {
				graficos.setColor(new Color(179, 71, 0));
				graficos.fillRect(getPosX(), getPosY()+i, getLongitud(), 1);
			}
		}
		graficos.setColor(new Color(255, 133, 51));
		graficos.fillOval(getPosX(), getPosY(), getLongitud(), getLongitud());
	}
	
	private void dibujarDetalleMeta(Graphics graficos) {
		int numCuadrados = getLongitud()/5;
		for(int i = 0; i < numCuadrados-1; i++) {
			for(int j = 0; j < numCuadrados-1; j++) {
				if((i+j)%2==0) {
					graficos.setColor(Color.BLACK);
				}else {
					graficos.setColor(Color.WHITE);
				}
				graficos.fillRect(getPosX()+(numCuadrados*j), getPosY()+(numCuadrados*i), numCuadrados, numCuadrados);
			}
		}
	}

	private void dibujarDetalleInicio(Graphics graficos) {
		graficos.setColor(Color.PINK);
		graficos.fillOval(getPosX(), getPosY(), getLongitud(), getLongitud());
	}

	private void dibujarDetalleAsfalto(Graphics graficos) {
		graficos.setColor(new Color(150,150,150));
		graficos.fillRect(getPosX()+posXGris, getPosY()+posYGris, 3, 3);
		graficos.setColor(new Color(120,120,120));
		graficos.fillRect(getPosX()+posXGrisSuave, getPosY()+posYGrisSuave, 2, 4);
		graficos.setColor(new Color(80,80,80));
		graficos.fillRect(getPosX()+posXGrisOscuro, getPosY()+posYGrisOscuro, 4, 2);
	}

	/**
	 * @return the celda
	 */
	public Celda getCelda() {
		return celda;
	}

	/**
	 * @param celda the celda to set
	 */
	public void setCelda(Celda celda) {
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

	public boolean isTuvoCoche() {
		return tuvoCoche;
	}

	public void setTuvoCoche(boolean tuvoCoche) {
		this.tuvoCoche = tuvoCoche;
	}
	
}
