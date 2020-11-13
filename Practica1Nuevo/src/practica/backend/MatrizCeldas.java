package practica.backend;

public class MatrizCeldas {

	private Celda[][] matrizCeldas;			
	
	private Celda celdaInicial, celdaMeta;
	
	private int numFilas, numColumnas;
	
	/**
	 * Crea una matriz inicial de 5x5
	 */
	public MatrizCeldas(int numFilas, int numColumnas, int filaInicial, int columnaInicial,
			int filaMeta, int columnaMeta) {
		setMatrizCeldas(numFilas, numColumnas, filaInicial, columnaInicial, filaMeta, columnaMeta);
	}
	
	public void setMatrizCeldas(int numFilas, int numColumnas, int filaInicial, int columnaInicial,
			int filaMeta, int columnaMeta) {
		setNumColumnas(numColumnas);
		setNumFilas(numFilas);
		this.matrizCeldas = new Celda[numFilas][numColumnas];
		for(int i = 0; i < numFilas; i++) {			//inicializamos la matriz
			for(int j = 0; j < numColumnas; j++){
				matrizCeldas[i][j] = new Celda();
			}
		}
		matrizCeldas[filaInicial][columnaInicial].setInicio(true);
		matrizCeldas[filaMeta][columnaMeta].setMeta(true);
		setCeldaInicial(matrizCeldas[filaInicial][columnaInicial]);
		setCeldaMeta(matrizCeldas[filaMeta][columnaMeta]);
		establecerVecinos();
	}
	
	public void setMatrizCeldas(Celda[][] nuevaMatriz) {
		this.matrizCeldas = nuevaMatriz;
	}
	
	private void establecerVecinos() {
		for(int i = 0; i < getNumFilas(); i++) {
			for(int j = 0; j < getNumColumnas(); j++) {
				asignarVecinosCelda(getMatrizCeldas(), i, j);
			}
		}
	}
	
	/**
	 * Asigna las celdas vecinas de la celda en la fila i, columna j
	 * 
	 * Hay cuatro categorias: esquina, borde, interna. Cada posicion
	 * tiene un algoritmo de asignacion de celda vecina distinta.
	 * 
	 * @param matrizCeldas para comprobar ancho y alto
	 * @param i fila de la celda a asignar
	 * @param j columna de la celda a asignar
	 * @return 
	 */
	private void asignarVecinosCelda(Celda[][] matrizCeldas, int i, int j) {
		if(celdaEnEsquina(matrizCeldas, i, j)) {
			asignarVecinosEnEsquina(matrizCeldas, i, j);
		}else if(celdaEnBorde(matrizCeldas, i, j)) {
			asignarVecinosEnBorde(matrizCeldas, i, j);
		}else {																//Esta dentro, posicion interior
			asignarVecinosCeldaInterior(matrizCeldas, i, j);
		}
	}
	
	//Para las celdas que tienen un vecino en arriba,abajo,derecha y izquierda. Las de dentro
	//de la matriz, las que no estan en el borde
	private void asignarVecinosCeldaInterior(Celda[][] matrizCeldas, int i, int j) {
		getMatrizCeldas()[i][j].setCeldaAbajo(getMatrizCeldas()[i+1][j]);
		getMatrizCeldas()[i][j].setCeldaArriba(getMatrizCeldas()[i-1][j]);
		getMatrizCeldas()[i][j].setCeldaDerecha(getMatrizCeldas()[i][j+1]);
		getMatrizCeldas()[i][j].setCeldaIzquierda(getMatrizCeldas()[i][j-1]);
	}
	
	/**
	 * Usado en asignarVecinos(...) para poder saber que vecinos asignar a la celda
	 * 	en la fila i, columna j
	 * @param matrizCeldas para comprobar ancho y alto
	 * @param i fila de la celda a asignar
	 * @param j columna de la celda a asignar
	 * @return true si la celda en la fila i, columna j esta en una de las cuatro esquinas
	 */
	public boolean celdaEnEsquina(Celda[][] matrizCeldas, int i, int j) {
		return (celdaEnEsquinaInferiorDerecha(matrizCeldas, i, j) || celdaEnEsquinaInferiorIzquierda(matrizCeldas, i, j) || 
				celdaEnEsquinaSuperiorDerecha(matrizCeldas, i, j) || celdaEnEsquinaSuperiorIzquierda(matrizCeldas, i, j));
	}
	
	public void asignarVecinosEnEsquina(Celda[][] matrizCeldas, int i, int j) {
		if(celdaEnEsquinaSuperiorIzquierda(matrizCeldas, i, j)) {
			asignarCeldasVecinasEsquinaSuperiorIzquierda(matrizCeldas, i, j);
		}else if(celdaEnEsquinaSuperiorDerecha(matrizCeldas, i, j)) {
			asignarCeldasVecinasEsquinaSuperiorDerecha(matrizCeldas, i, j);
		}else if(celdaEnEsquinaInferiorIzquierda(matrizCeldas, i, j)) {
			asignarCeldasVecinasEsquinaInferiorIzquierda(matrizCeldas, i, j);
		}else if(celdaEnEsquinaInferiorDerecha(matrizCeldas, i, j)) {
			asignarCeldasVecinasEsquinaInferiorDerecha(matrizCeldas, i, j);
		}
	}
	
	private boolean celdaEnEsquinaSuperiorIzquierda(Celda[][] matrizCeldas, int i, int j) {
		return (i==0						&& j==0);
	}
	
	private boolean celdaEnEsquinaSuperiorDerecha(Celda[][] matrizCeldas, int i, int j) {
		return  (i==0 					&& j==(getNumColumnas()-1));
	}
	
	private boolean celdaEnEsquinaInferiorIzquierda(Celda[][] matrizCeldas, int i, int j) {
		return  (i==(getNumFilas()-1)	&& j==0);
	}
	
	
	private boolean celdaEnEsquinaInferiorDerecha(Celda[][] matrizCeldas, int i, int j) {
		return  (i==(getNumFilas()-1) 	&& j==(getNumColumnas()-1));
	}
	
	private void asignarCeldasVecinasEsquinaSuperiorIzquierda(Celda[][] matrizCeldas, int i, int j) {
		getMatrizCeldas()[i][j].setCeldaAbajo(getMatrizCeldas()[i+1][j]);
		getMatrizCeldas()[i][j].setCeldaDerecha(getMatrizCeldas()[i][j+1]);
	}
	
	private void asignarCeldasVecinasEsquinaSuperiorDerecha(Celda[][] matrizCeldas, int i, int j) {
		getMatrizCeldas()[i][j].setCeldaAbajo(getMatrizCeldas()[i+1][j]);
		getMatrizCeldas()[i][j].setCeldaIzquierda(getMatrizCeldas()[i][j-1]);
	}
	
	private void asignarCeldasVecinasEsquinaInferiorIzquierda(Celda[][] matrizCeldas, int i, int j) {
		getMatrizCeldas()[i][j].setCeldaArriba(getMatrizCeldas()[i-1][j]);
		getMatrizCeldas()[i][j].setCeldaDerecha(getMatrizCeldas()[i][j+1]);
	}
	
	private void asignarCeldasVecinasEsquinaInferiorDerecha(Celda[][] matrizCeldas, int i, int j) {
		getMatrizCeldas()[i][j].setCeldaArriba(getMatrizCeldas()[i-1][j]);
		getMatrizCeldas()[i][j].setCeldaIzquierda(getMatrizCeldas()[i][j-1]);
	}
	
	/**
	 * Usado en asignarVecinos(...) para poder saber que vecinos asignar a la celda
	 * 	en la fila i, columna j
	 * @param matrizCeldas para comprobar ancho y alto
	 * @param i fila de la celda a asignar
	 * @param j columna de la celda a asignar
	 * @return true si la celda en la fila i, columna j esta en una de las cuatro esquinas
	 */
	public boolean celdaEnBorde(Celda[][] matrizCeldas, int i, int j) {
		return (celdaEnBordeDerecha(matrizCeldas, i, j) || celdaEnBordeIzquierda(matrizCeldas, i, j)
				|| celdaEnBordeInferior(matrizCeldas, i, j) || celdaEnBordeSuperior(matrizCeldas, i, j));
	}
	
	public void asignarVecinosEnBorde(Celda[][] matrizCeldas, int i, int j) {
		if(celdaEnBordeDerecha(matrizCeldas, i, j)) {
			asignarCeldasVecinasBordeDerecho(matrizCeldas, i, j);
		}else if(celdaEnBordeIzquierda(matrizCeldas, i, j)) {
			asignarCeldasVecinasBordeIzquierda(matrizCeldas, i, j);
		}else if(celdaEnBordeInferior(matrizCeldas, i, j)) {
			asignarCeldasVecinasBordeInferior(matrizCeldas, i, j);
		}else if(celdaEnBordeSuperior(matrizCeldas, i, j)) {
			asignarCeldasVecinasBordeSuperior(matrizCeldas, i, j);
		}
	}
	
	private boolean celdaEnBordeIzquierda(Celda[][] matrizCeldas, int i, int j) {
		return (i>0 && i<(getNumFilas()-1)) 		&& j==0;
	}
	
	private boolean celdaEnBordeDerecha(Celda[][] matrizCeldas, int i, int j) {
		return (i>0 && i<(getNumFilas()-1)) 		&& j==(getNumColumnas()-1);
	}
	
	private boolean celdaEnBordeSuperior(Celda[][] matrizCeldas, int i, int j) {
		return (i==0 								&& (j>0 && j<(getNumColumnas()-1)));
	}
	
	private boolean celdaEnBordeInferior(Celda[][] matrizCeldas, int i, int j) {
		return (i==(getNumFilas()-1)				&& (j>0 && j<(getNumColumnas()-1)));
	}
	
	private void asignarCeldasVecinasBordeDerecho(Celda[][] matrizCeldas, int i, int j) {
		getMatrizCeldas()[i][j].setCeldaArriba(getMatrizCeldas()[i-1][j]);
		getMatrizCeldas()[i][j].setCeldaIzquierda(getMatrizCeldas()[i][j-1]);
		getMatrizCeldas()[i][j].setCeldaAbajo(getMatrizCeldas()[i+1][j]);
	}
	
	private void asignarCeldasVecinasBordeIzquierda(Celda[][] matrizCeldas, int i, int j) {
		getMatrizCeldas()[i][j].setCeldaArriba(getMatrizCeldas()[i-1][j]);
		getMatrizCeldas()[i][j].setCeldaDerecha(getMatrizCeldas()[i][j+1]);
		getMatrizCeldas()[i][j].setCeldaAbajo(getMatrizCeldas()[i+1][j]);
	}
	
	private void asignarCeldasVecinasBordeSuperior(Celda[][] matrizCeldas, int i, int j) {
		getMatrizCeldas()[i][j].setCeldaAbajo(getMatrizCeldas()[i+1][j]);
		getMatrizCeldas()[i][j].setCeldaDerecha(getMatrizCeldas()[i][j+1]);
		getMatrizCeldas()[i][j].setCeldaIzquierda(getMatrizCeldas()[i][j-1]);
	}
	
	private void asignarCeldasVecinasBordeInferior(Celda[][] matrizCeldas, int i, int j) {
		getMatrizCeldas()[i][j].setCeldaArriba(getMatrizCeldas()[i-1][j]);
		getMatrizCeldas()[i][j].setCeldaDerecha(getMatrizCeldas()[i][j+1]);
		getMatrizCeldas()[i][j].setCeldaIzquierda(getMatrizCeldas()[i][j-1]);
	}
	
	public Celda[][] getMatrizCeldas() {
		return matrizCeldas;
	}

	/**
	 * Si la celda esta ocupada un agente IA no deberia ser capaz de entrar
	 * en ella
	 * 
	 * @Precondition 1 <= x <= this.getNumColumnas()
	 * @Precondition 1 <= y <= this.getNumFilas()
	 * @param x fila
	 * @param y columna
	 */
	public void ocuparCelda(int x, int y) {
		if(1 <= x && x <= this.getNumColumnas() && 
				1 <= y && y <= this.getNumFilas()) {
			matrizCeldas[x][y].setOcupada(true);
		}
	}
	
	/**
	 * Una celda libre deberia permitir entrar a un agente IA 
	 * 
	 * @Precondition 1 <= x <= this.getNumColumnas()
	 * @Precondition 1 <= y <= this.getNumFilas()
	 * @param x fila
	 * @param y columna
	 */
	public void liberarCelda(int x, int y) {
		if(1 <= x && x <= this.getNumColumnas() && 
				1 <= y && y <= this.getNumFilas()) {
			matrizCeldas[x][y].setOcupada(false);
		}
	}
	
	/**
	 * Parte de la especificacion del tamanyo del escenario. Usado
	 * en la clase MatrizCeldas
	 * 
	 * 
	 * @return numFilasMatrizCeldas filas en la matriz escenario
	 */
	public int getNumFilas() {
		return numFilas;
	}
	
	/**
	 * Parte de la especificacion del tamanyo del escenario. Usado
	 * en la clase MatrizCeldas
	 * 
	 * @Precondition numFilas>0
	 * @param numFilas, nuevo numero de filas
	 */
	public void setNumFilas(int numFilas) {
		this.matrizCeldas = new Celda[numFilas][this.numColumnas];
		this.numFilas = numFilas;
	}

	/**
	 * Part of stage size specification. Used in the cell matrix
	 * 
	 * 
	 * @return numColumns Columns in the matrix of cells in the stage
	 */
	public int getNumColumnas() {
		return numColumnas;
	}

	/**
	 * Part of stage size specification. Used in cell matrix.
	 * Should be called after instantiation.
	 * 
	 * @return numColumns Columns in the matrix of cells in the stage
	 */
	public void setNumColumnas(int numColumns) {
		this.matrizCeldas = new Celda[this.numFilas][numColumns];
		this.numColumnas = numColumns;
	}

	public Celda getCeldaInicial() {
		return celdaInicial;
	}

	public void setCeldaInicial(Celda celdaInicial) {
		this.celdaInicial = celdaInicial;
	}

	public Celda getCeldaMeta() {
		return celdaMeta;
	}

	public void setCeldaMeta(Celda celdaMeta) {
		this.celdaMeta = celdaMeta;
	}
	
}
