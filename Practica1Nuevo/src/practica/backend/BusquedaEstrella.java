package practica.backend;

import java.util.ArrayList;
import java.util.Iterator;

public class BusquedaEstrella implements IAlgoritmoBusqueda{
	
	private ArrayList<NodoBusqueda> nodosExpandidos;
	private ArrayList<NodoBusqueda> nodosPorExpandir;
	
	private Coche coche;
	
	public BusquedaEstrella(Coche coche) {
		this.setCoche(coche);
		this.setCeldasExpandidas(new ArrayList<NodoBusqueda>());
		this.setCeldasPorExpandir(new ArrayList<NodoBusqueda>());
	}
	
	@Override
	public void buscar() {
		NodoBusqueda nodoInicial = new NodoBusqueda(coche.getCeldaConCoche());
		getNodosPorExpandir().add(nodoInicial);
		
		NodoBusqueda metaOptima = null;
		boolean posibleMejorMeta = true;
		while(getNodosPorExpandir().size()>0) {
			NodoBusqueda siguienteNodo = obtenerNodoSinExpandirConMenorMerito();
			if(siguienteNodo.esMeta() && siguienteNodo.mejorMetaQue(metaOptima)) {
				metaOptima = siguienteNodo;
				moverCoche(siguienteNodo.getAccion());	
			}
			
			if(metaOptima!=null && metaOptima.getF()>siguienteNodo.getF()) {
				expandirNodo(siguienteNodo);
				calcularMeritos(siguienteNodo);
				//Ordenar lista segun f (0 es menor que 1)
			}
		}
	}
	
	private NodoBusqueda obtenerNodoSinExpandirConMenorMerito() {
		return getNodosPorExpandir().get(0);
	}
	
	private void moverCoche(String accion) {
		if(accion.equals("arriba")) {
			getCoche().moverArriba();
		}else if(accion.equals("abajo")) {
			getCoche().moverAbajo();
		}else if(accion.equals("derecha")) {
			getCoche().moverDerecha();
		}else if(accion.equals("izquierda")) {
			getCoche().moverIzquierda();
		}
	}
	
	private void expandirNodo(NodoBusqueda nodoPadre) {
		nodoPadre.expandirHijos();
		getNodosPorExpandir().remove(nodoPadre);
		getNodosExpandidos().add(nodoPadre);
		Iterator<NodoBusqueda> itr = nodoPadre.iterator();
		while(itr.hasNext()) {
			NodoBusqueda nodoHijo = itr.next();
			if(!getNodosExpandidos().contains(nodoHijo)) {
				getNodosPorExpandir().add(nodoHijo);
			}
		}
	}
	
	//Calcular los meritos de los nodos en la lista de por expandir
	private void calcularMeritos(NodoBusqueda NodoCalculando) {
		
	}
	
	@Override
	public boolean encontroLaMeta() {
		// TODO Auto-generated method stub
		return true;
	}

	public ArrayList<NodoBusqueda> getNodosExpandidos() {
		return nodosExpandidos;
	}

	public void setCeldasExpandidas(ArrayList<NodoBusqueda> celdasExpandidas) {
		this.nodosExpandidos = celdasExpandidas;
	}

	public ArrayList<NodoBusqueda> getNodosPorExpandir() {
		return nodosPorExpandir;
	}

	public void setCeldasPorExpandir(ArrayList<NodoBusqueda> celdasPorExpandir) {
		this.nodosPorExpandir = celdasPorExpandir;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

}
