package practica.frontend;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import practica.backend.Celda;

@SuppressWarnings("serial")
public class GUIPanelEscenario extends JPanel{
	
	private GUICelda[][] guiCeldasEscenario;
	
	private GUICoche coche;
	
	private GUICelda celdaConCoche;
	
	private GUIMovementHandler hiloMovimiento;
	
	private int longitudCelda;
	
	private int posCocheCentroCeldaX, posCocheCentroCeldaY;
	
	private boolean moviendose, pendienteCalcularCeldaConCoche;
	
	public GUIPanelEscenario(Celda[][] matriz) {
		super();
		
		int posInicial = 25;
		int longitudInicial = 8;
		coche = new GUICoche(posInicial, posInicial, longitudInicial);
		hiloMovimiento = new GUIMovementHandler(this, coche);
		this.setMatrizCeldas(matriz);
	}
	
	public void limpiarRecorrido() {
		for(int i = 0; i < getMatrizGUICeldasEscenario().length; i++) {
			for(int j = 0; j < getMatrizGUICeldasEscenario()[0].length; j++) {
				getMatrizGUICeldasEscenario()[i][j].setTuvoCoche(false);
			}
		}
	}
	
	/**
	 * Dibuja una matriz de celdas y un coche. Se adapta al tamanyo de ventana.
	 * Este metodo se llama cada vez que se actualiza la ventana, incluyendo
	 *  redimensionamientos.
	 * Pensado para que el coche navegue por escenario.
	 */
	public void paintComponent(Graphics graficos) {
		super.paintComponent(graficos);
		calcularEscenario(graficos);
		dibujarEscenario(graficos);
		calcularCoche(graficos);
		coche.dibujarCoche(graficos);
	}

	/**
	 * Devuelve las celdas del escenario directamente sin la parte GUI.
	 * @return
	 */
	public Celda[][] obtenerEscenario(){
		Celda[][] escenarioSalida = new Celda[getMatrizGUICeldasEscenario().length][getMatrizGUICeldasEscenario()[0].length];
		for(int i = 0; i < getMatrizGUICeldasEscenario().length; i++) {
			for(int j = 0; j < getMatrizGUICeldasEscenario()[0].length; j++) {
				escenarioSalida[i][j] = getMatrizGUICeldasEscenario()[i][j].getCelda();
			}
		}
		return escenarioSalida;
	}
	
	/**
	 * Devuelve la celda equivalente en la pos dada
	 * @param posX
	 * @param posY
	 * @return celda correspondiente a la posicion dada
	 */
	public GUICelda obtenerCeldaGrafica(int posX, int posY) {
		GUICelda celdaSalida = null;
		for(int i = 0; i < guiCeldasEscenario.length; i++) {
			for(int j = 0; j < guiCeldasEscenario[0].length; j++) {
				GUICelda celda = guiCeldasEscenario[i][j];
//				System.out.println(posX + " > " + celda.getPosX() + " | " + 
//						posY + " > " + celda.getPosY() + " | " + 
//						posX + " < " + (celda.getPosX()+celda.getLongitud()) + " | " + 
//						posY + " < " + (celda.getPosY()+celda.getLongitud()));
				if(posX >= celda.getPosX() && posY >= celda.getPosY() &&
						posX <= celda.getPosX()+celda.getLongitud() &&
						posY <= celda.getPosY()+celda.getLongitud()) {
//					System.err.println("GUIPanelEscenario.obtenerCeldaGrafica(...) encontro coincidencia con celda");
					return celda;
				}
			}
		}
			
		return celdaSalida; 
	}
	
	/**
	 * Se asegura que no haya mas de una celda inicial
	 * @param celdaInicial unica del escenario
	 */
	public void establecerCeldaInicial(GUICelda celdaInicial) {
		if(!celdaInicial.getCelda().esMeta()) {
			for(int i = 0; i < guiCeldasEscenario.length; i++) {
				for(int j = 0; j < guiCeldasEscenario[0].length; j++) {
					guiCeldasEscenario[i][j].getCelda().setInicio(false);
				}
			}
			celdaInicial.getCelda().setInicio(true);
		}
	}
	
	/**
	 * Se asegura que no haya mas de una celda meta
	 * @param celdaMeta unica del escenario
	 */
	public void establecerCeldaMeta(GUICelda celdaMeta) {
		if(!celdaMeta.getCelda().esInicio()) {
			for(int i = 0; i < guiCeldasEscenario.length; i++) {
				for(int j = 0; j < guiCeldasEscenario[0].length; j++) {
					guiCeldasEscenario[i][j].getCelda().setMeta(false);
				}
			}
			celdaMeta.getCelda().setMeta(true);
		}
	}

	/**
	 * Dibuja una matriz de celdas con distintos colores segun sean
	 * celdas finales, iniciales o ocupadas.
	 * @param graficos del panel. Actualizados constantemente.
	 */
	private void dibujarEscenario(Graphics graficos) {
		int numFilas = guiCeldasEscenario.length;
		int numColumnas = guiCeldasEscenario[0].length;
		
		for(int i = 0; i < numFilas; i++) {
			for(int j = 0; j < numColumnas; j++) {
				guiCeldasEscenario[i][j].dibujarCelda(graficos);
			}
		}
	}
	
	/**
	 * Calcula las posiciones y las longitudes de las celdas segun el 
	 * tamanyo de la ventana.
	 * @param graficos del panel. Actualizados constantemente
	 */
	private void calcularEscenario(Graphics graficos) {
		calcularLongitudCelda();
		int numFilas = guiCeldasEscenario.length;
		int numColumnas = guiCeldasEscenario[0].length;
		int longitudMitadVentana = this.getWidth()/2;
		int longitudMitadEscenario = (longitudCelda*numColumnas)/2;
		int margenDeCentrado = longitudMitadVentana-longitudMitadEscenario;				//Sirve para centrar el escenario
		
		for(int i = 0; i < numFilas; i++) {
			for(int j = 0; j < numColumnas; j++) {
				int nuevaPosX = j*this.getLongitudCelda();
				int nuevaPosY = i*this.getLongitudCelda();
				GUICelda celda = guiCeldasEscenario[i][j];
				
				celda.setPosX(nuevaPosX+margenDeCentrado);
				celda.setPosY(nuevaPosY);
				celda.setLongitud(this.getLongitudCelda());
			}
		}
	}
	
	/**
	 * Permite que el escenario se adapte al tamanyo de ventana. Si es demasiado
	 * ancho ajusto la anchura. Si es demasiado alto ajusto la altura. 
	 * 
	 * @return longitud de celda acorde con el tamanyo de ventana
	 */
	private void calcularLongitudCelda() {
		int numFilas = guiCeldasEscenario.length;
		int numColumnas = guiCeldasEscenario[0].length;
		
		if(numFilas>=numColumnas) {
			longitudCelda = this.getHeight()/numFilas;
		}else {
			longitudCelda = this.getWidth()/numColumnas;
		}
	}
	
	/**
	 * Calculas la posicion y la longitud del coche segun el tamanyo
	 * de la ventana
	 * @param graficos del panel. Actualizados constantemente
	 */
	private void calcularCoche(Graphics graficos) {
		actualizarVariablesCentroCelda();
		coche.setLongitud(longitudCelda/2);
		if(!moviendose) {
			if(isPendienteCalcularCeldaConCoche()) {
				calcularNuevaCeldaConCoche();
				hiloMovimiento.procesarMovimiento();
				setPendienteCalcularCeldaConCoche(false);
			}
			centrarCocheEnSuCelda();
		}else {
//			System.out.println("GUIPanelEscenario.calcularCoche(...) i: " + celdaConCoche.getIndiceMatrizI() + " j:" + celdaConCoche.getIndiceMatrizJ());
			ajustarCocheANuevaEscala();
//			System.out.println("GUIPanelEscenario: en calcularCoche(...): coche ajustado, posX=" + (coche.getPosBaseX()+coche.getMargenX()));
		}
//		System.out.println("GUIPanelEscenario: en calcularCoche(...): posCoche= " + coche.getPosBaseX());
	}
	
	private void calcularNuevaCeldaConCoche() {
		GUICelda nuevaCeldaConCoche = obtenerCeldaGrafica(coche.getPosBaseX()+coche.getMargenX(), 
				coche.getPosBaseY()+coche.getMargenY());
//		System.err.println("GUIPanelEscenario.calcularCoche().coche.X: " + (nuevaCeldaConCoche.getIndiceMatrizI()) + "coche.Y: " + (nuevaCeldaConCoche.getIndiceMatrizJ()));
		ponerCocheEnCelda(nuevaCeldaConCoche.getIndiceMatrizI(), nuevaCeldaConCoche.getIndiceMatrizJ());
		coche.setMargenX(0);
		coche.setMargenY(0);
	}
	
	/**
	 * Actualiza las variables que indican donde debe estar el coche para estar
	 * centrado en la celda.
	 */
	private void actualizarVariablesCentroCelda() {
		int posMitadCeldaX = celdaConCoche.getPosX()+(this.getLongitudCelda()/2);
		int posMitadCeldaY = celdaConCoche.getPosY()+(this.getLongitudCelda()/2);
		this.setPosCocheCentroCeldaX(posMitadCeldaX - (coche.getLongitud()/2));
		this.setPosCocheCentroCeldaY(posMitadCeldaY - (coche.getLongitud()/2));
	} 
	
	/**
	 * Centra la posicion a la celda. Es necesario para cuando no 
	 * se esta moviendo el coche.
	 */
	private void centrarCocheEnSuCelda() {
		coche.setPosBaseX(this.getPosCocheCentroCeldaX());
		coche.setPosBaseY(this.getPosCocheCentroCeldaY());
		repaint();
	}
	
	/**
	 * Ajusta la posicion y longitud al nuevo tamanyo de ventana manteniendo
	 * los margenes que simulaban movimiento de una celda a otra.
	 */
	private void ajustarCocheANuevaEscala() {
		coche.setPosBaseX(this.getPosCocheCentroCeldaX()+coche.getMargenX());
		coche.setPosBaseY(this.getPosCocheCentroCeldaY()+coche.getMargenY());
	}
	
	/**
	 * Envia una lista de peticiones al GUIMovementHandler para que las vaya ejecutando
	 */
	public void ejecutarListaAcciones(ArrayList<String> acciones) {
		if(acciones.size()>0) {
			if(acciones.get(0).equals("noEncontrada")) {
				JOptionPane.showMessageDialog(null, "Algoritmo fallido, no se ha encontrado el camino");
			}else {
				hiloMovimiento.agregarListaAcciones(acciones);
			}
		}else {
			JOptionPane.showMessageDialog(null, "El agente no hace nada, no se detectaron acciones");
		}
	}
	
	public void moverADerecha() {
//		System.err.println("GUIPanelEscenario.moverADerecha(): viejoI: " + celdaConCoche.getIndiceMatrizI() + " viejoJ:" + celdaConCoche.getIndiceMatrizJ());
		moviendose = true;
		hiloMovimiento.setCeldaObjetivo(guiCeldasEscenario[getCeldaConCoche().getIndiceMatrizI()][getCeldaConCoche().getIndiceMatrizJ()+1]);
		hiloMovimiento.moverADerecha();
	}
	
	public void moverAIzquierda() {
//		System.out.println("GUIPanelEscenario.moverADerecha(): viejoI: " + celdaConCoche.getIndiceMatrizI() + " viejoJ:" + celdaConCoche.getIndiceMatrizJ());
		moviendose = true;
		hiloMovimiento.setCeldaObjetivo(guiCeldasEscenario[getCeldaConCoche().getIndiceMatrizI()][getCeldaConCoche().getIndiceMatrizJ()-1]);
		hiloMovimiento.moverAIzquierda();
	}
	
	public void moverAbajo() {
//		System.out.println("GUIPanelEscenario.moverADerecha(): viejoI: " + celdaConCoche.getIndiceMatrizI() + " viejoJ:" + celdaConCoche.getIndiceMatrizJ());
		moviendose = true;
		hiloMovimiento.setCeldaObjetivo(guiCeldasEscenario[getCeldaConCoche().getIndiceMatrizI()+1][getCeldaConCoche().getIndiceMatrizJ()]);
		hiloMovimiento.moverAIzquierda();
	}
	
	public void moverArriba() {
//		System.out.println("GUIPanelEscenario.moverADerecha(): viejoI: " + celdaConCoche.getIndiceMatrizI() + " viejoJ:" + celdaConCoche.getIndiceMatrizJ());
		moviendose = true;
		hiloMovimiento.setCeldaObjetivo(guiCeldasEscenario[getCeldaConCoche().getIndiceMatrizI()-1][getCeldaConCoche().getIndiceMatrizJ()]);
		hiloMovimiento.moverAIzquierda();
	}
	
	private int getLongitudCelda() {
		return this.longitudCelda;
	}
	
	/**
	 * @return matrizCeldas que forma el escenario
	 */
	public GUICelda[][] getMatrizGUICeldasEscenario() {
		return guiCeldasEscenario;
	}

	/**
	 * Inicializa la matriz de guiCeldasEscenario
	 * @param celdasEscenario de celdas. Forma el escenario sobre el que se movera el agente de IA.
	 */
	public void setMatrizCeldas(Celda[][] celdasEscenario) {
		guiCeldasEscenario = new GUICelda[celdasEscenario.length][celdasEscenario[0].length];
		
		for(int i = 0; i < celdasEscenario.length; i++) {			
			for(int j = 0; j < celdasEscenario[0].length; j++) {
				Celda celda = celdasEscenario[i][j];
				if(celda!=null) {
					guiCeldasEscenario[i][j] = new GUICelda(celda);
					guiCeldasEscenario[i][j].setIndiceMatrizI(i);
					guiCeldasEscenario[i][j].setIndiceMatrizJ(j);
					if(celdasEscenario[i][j].esInicio()) {
						ponerCocheEnCelda(i,j);
					}
				}else {
					guiCeldasEscenario[i][j] = new GUICelda(new Celda());				//valores predeterminados
					guiCeldasEscenario[i][j].setIndiceMatrizI(i);
					guiCeldasEscenario[i][j].setIndiceMatrizJ(j);
				}
			}
		}
	}

	/**
	 * @return coche que navega por el escenario
	 */
	public GUICoche getCoche() {
		return coche;
	}

	/**
	 * @param coche que navega por el escenario
	 */
	public void setCoche(GUICoche coche) {
		this.coche = coche;
	}

	/**
	 * @return the celdaConCoche
	 */
	public GUICelda getCeldaConCoche() {
		return celdaConCoche;
	}

	/**
	 * @param i es la fila de la celda
	 * @param j es la columna de la celda
	 */
	public void ponerCocheEnCelda(int i, int j) {
//		System.out.println("GUIPanelEscenario.ponerCocheEnCelda(...): celda actualizada" + i + " " + j);
		this.celdaConCoche = guiCeldasEscenario[i][j];
		celdaConCoche.setTuvoCoche(true);
	}

	public boolean isMoviendose() {
		return moviendose;
	}

	public void setMoviendose(boolean moviendose) {
		this.moviendose = moviendose;
	}

	/**
	 * @return the posCocheCentroCeldaX
	 */
	public int getPosCocheCentroCeldaX() {
		return posCocheCentroCeldaX;
	}

	/**
	 * @param posCocheCentroCeldaX the posCocheCentroCeldaX to set
	 */
	public void setPosCocheCentroCeldaX(int posCocheCentroCeldaX) {
		this.posCocheCentroCeldaX = posCocheCentroCeldaX;
	}

	/**
	 * @return the posCocheCentroCeldaY
	 */
	public int getPosCocheCentroCeldaY() {
		return posCocheCentroCeldaY;
	}

	/**
	 * @param posCocheCentroCeldaY the posCocheCentroCeldaY to set
	 */
	public void setPosCocheCentroCeldaY(int posCocheCentroCeldaY) {
		this.posCocheCentroCeldaY = posCocheCentroCeldaY;
	}

	public boolean isPendienteCalcularCeldaConCoche() {
		return pendienteCalcularCeldaConCoche;
	}

	public void setPendienteCalcularCeldaConCoche(boolean pendienteCalcularCeldaConCoche) {
		this.pendienteCalcularCeldaConCoche = pendienteCalcularCeldaConCoche;
	}
	
}
