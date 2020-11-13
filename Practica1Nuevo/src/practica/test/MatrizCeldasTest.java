package practica.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import practica.backend.Celda;
import practica.backend.MatrizCeldas;

class MatrizCeldasTest {

	@Test
	void testCeldaEnEsquina() {
		MatrizCeldas mc = new MatrizCeldas(10, 25, 0, 0, 4, 24);
		assertEquals(true, mc.celdaEnEsquina(mc.getMatrizCeldas(), 0, 0));
		assertEquals(true, mc.celdaEnEsquina(mc.getMatrizCeldas(), 0, 0));
		assertEquals(true, mc.celdaEnEsquina(mc.getMatrizCeldas(), 0, 0));
		assertEquals(true, mc.celdaEnEsquina(mc.getMatrizCeldas(), 0, 0));
	}
	
	@Test
	void testCeldaEnBorde() {
		MatrizCeldas mc = new MatrizCeldas(10, 25, 0, 0, 4, 24);
		assertEquals(true, mc.celdaEnBorde(mc.getMatrizCeldas(), 0, 1));
		assertEquals(true, mc.celdaEnBorde(mc.getMatrizCeldas(), mc.getNumFilas()-1, 1));
		assertEquals(true, mc.celdaEnBorde(mc.getMatrizCeldas(), 1, mc.getNumColumnas()-1));
		assertEquals(true, mc.celdaEnBorde(mc.getMatrizCeldas(), 1, 0));
	}
	
	@Test
	void testAsignarVecinosEnBorde() {
		MatrizCeldas mc = new MatrizCeldas(10, 25, 0, 0, 4, 24);
		mc.asignarVecinosEnBorde(mc.getMatrizCeldas(), 1, 0);
		
		Celda vecinoDerecho = mc.getMatrizCeldas()[1][1];									//comprobamos borde izquierdo
		assertEquals(vecinoDerecho, mc.getMatrizCeldas()[1][0].getCeldaDerecha());
		
		Celda vecinoArriba = mc.getMatrizCeldas()[0][0];
		assertEquals(vecinoArriba, mc.getMatrizCeldas()[1][0].getCeldaArriba());
		
		Celda vecinoInferior = mc.getMatrizCeldas()[2][0];
		assertEquals(vecinoInferior, mc.getMatrizCeldas()[1][0].getCeldaAbajo());
		
	}
	
	@Test
	void testAsignarVecinosEnEsquina() {
		MatrizCeldas mc = new MatrizCeldas(10, 25, 0, 0, 4, 24);
		mc.asignarVecinosEnEsquina(mc.getMatrizCeldas(), 0, 0);
		
		Celda vecinoDerecho = mc.getMatrizCeldas()[0][1];
		assertEquals(vecinoDerecho, mc.getMatrizCeldas()[0][0].getCeldaDerecha());
		
		Celda vecinoInferior = mc.getMatrizCeldas()[1][0];
		assertEquals(vecinoInferior, mc.getMatrizCeldas()[0][0].getCeldaAbajo());
	}
}
