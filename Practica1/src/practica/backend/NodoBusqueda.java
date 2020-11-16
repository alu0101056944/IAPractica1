package practica.backend;

import java.util.ArrayList;
import java.util.Iterator;

public class NodoBusqueda {

	private int f, g, h;
	
	private Celda celda;
	
	private ArrayList<NodoBusqueda> nodosHijo;
	
	private String accionParaAlcanzarlo;
	
	private NodoBusqueda nodoPadre;
	
	public NodoBusqueda(Celda celda, NodoBusqueda nodoPadre) {
		accionParaAlcanzarlo = "pendienteDeAsignar";
		nodosHijo = new ArrayList<NodoBusqueda>();
		this.setCelda(celda);
		this.setNodoPadre(nodoPadre);
	}
	
	private ArrayList<NodoBusqueda> getNodosHijo() {
		return nodosHijo;
	}
	
	private void agregarHijo(NodoBusqueda nodoHijo) {
		getNodosHijo().add(nodoHijo);
	}
	
	/**
	 * Asigna directamente el nodo hijo meta (unico), a la vez que le asigna
	 * todos los nodos hijos.
	 */
	public void expandirHijos() {
		Celda celdaArriba = getCelda().getCeldaArriba();
		Celda celdaAbajo = getCelda().getCeldaAbajo();
		Celda celdaDerecha = getCelda().getCeldaDerecha();
		Celda celdaIzquierda = getCelda().getCeldaIzquierda();
		if(celdaArriba!=null&&!celdaArriba.estaOcupada()) {
			NodoBusqueda hijoArriba = new NodoBusqueda(celdaArriba, this);
			hijoArriba.setAccion("arriba");
			agregarHijo(hijoArriba);
		}
		if(celdaAbajo!=null&&!celdaAbajo.estaOcupada()) {
			NodoBusqueda hijoAbajo = new NodoBusqueda(celdaAbajo, this);
			hijoAbajo.setAccion("abajo");
			agregarHijo(hijoAbajo);
		}
		if(celdaDerecha!=null&&!celdaDerecha.estaOcupada()) {
			NodoBusqueda hijoDerecha = new NodoBusqueda(celdaDerecha, this);
			hijoDerecha.setAccion("derecha");
			agregarHijo(hijoDerecha);
		}
		if(celdaIzquierda!=null&&!celdaIzquierda.estaOcupada()) {
			NodoBusqueda hijoIzquierda = new NodoBusqueda(celdaIzquierda, this);
			hijoIzquierda.setAccion("izquierda");
			agregarHijo(hijoIzquierda);
		}
	}

	
	public boolean esMeta() {
		return getCelda().esMeta();
	}
	
	public boolean mejorMetaQue(NodoBusqueda nodoMetaAnterior) {
		if(nodoMetaAnterior!=null) return this.getF()<nodoMetaAnterior.getF();
		return false;
	}
	
	public Iterator<NodoBusqueda> iterator(){
		return nodosHijo.iterator();
	}
	
	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getH() {
		return h;
	}

	public Celda getCelda() {
		return celda;
	}

	public void setCelda(Celda celda) {
		this.celda = celda;
	}

	public String getAccion() {
		return accionParaAlcanzarlo;
	}

	public void setAccion(String accion) {
		this.accionParaAlcanzarlo = accion;
	}
	
	@Override
	public boolean equals(Object o) {
		NodoBusqueda nb = (NodoBusqueda) o;
		return getCelda().equals(nb.getCelda());
	}

	public NodoBusqueda getNodoPadre() {
		return nodoPadre;
	}

	public void setNodoPadre(NodoBusqueda nodoPadre) {
		this.nodoPadre = nodoPadre;
	}

	
}
