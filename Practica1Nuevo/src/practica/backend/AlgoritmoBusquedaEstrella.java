package practica.backend;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

public class AlgoritmoBusquedaEstrella implements IAlgoritmoBusqueda{
	
	private ArrayList<NodoBusqueda> nodosExpandidos;
	private ArrayList<NodoBusqueda> nodosPorExpandir;
	
	private Coche coche;
	
	public AlgoritmoBusquedaEstrella(Coche coche) {
		this.setCoche(coche);
		this.setCeldasExpandidas(new ArrayList<NodoBusqueda>());
		this.setCeldasPorExpandir(new ArrayList<NodoBusqueda>());
	}
	
	@Override
	public void buscar(Celda celdaMeta) {
		NodoBusqueda nodoInicial = new NodoBusqueda(coche.getCeldaConCoche(), null);
		getNodosPorExpandir().add(nodoInicial);
		
		NodoBusqueda metaOptima = null;
		while(getNodosPorExpandir().size()>0) {
			NodoBusqueda siguienteNodo = obtenerNodoSinExpandirConMenorMerito();

			if(siguienteNodo.esMeta() && (siguienteNodo.mejorMetaQue(metaOptima) || metaOptima==null)){
				metaOptima = siguienteNodo;
				recalcularCaminoCoche(siguienteNodo);
				
			}
			
			getNodosPorExpandir().remove(siguienteNodo);
			getNodosExpandidos().add(siguienteNodo);
			if(metaOptima!=null) {
				if(siguienteNodo.getF() < metaOptima.getF()) {
					expandirNodo(siguienteNodo);
					calcularMeritos(siguienteNodo, celdaMeta);
					ordenarListaNodosPorExpandir();
				}
			}else {
				expandirNodo(siguienteNodo);
				calcularMeritos(siguienteNodo, celdaMeta);
				ordenarListaNodosPorExpandir();
			}
		}
	}
	
	/**
	 * Quiero obtener la lista de acciones que nos llevo al nodo meta actual para luego
	 * aplicarlas de manera inversa al coche, es decir, de inicio a meta. Para ello
	 * uso una pila.
	 * 
	 * @param nodoMeta Cuyo padre indica el camino hacia el inicio
	 */
	private void recalcularCaminoCoche(NodoBusqueda nodoMeta) {
		coche.limpiarListaAcciones();
		ArrayDeque<String> pilaAcciones = new ArrayDeque<String>();
		pilaAcciones.push(nodoMeta.getAccion());
		NodoBusqueda nodoPadre = nodoMeta.getNodoPadre();
		while(nodoPadre!=null) {
			pilaAcciones.push(nodoPadre.getAccion());
			nodoPadre = nodoPadre.getNodoPadre();
		}
		
		Iterator<String> itr = pilaAcciones.iterator(); //Va desde cima de pila a primero insertado
		while(itr.hasNext()) {
			String a = itr.next();
			moverCoche(a);
		}
	}
	
	//Algoritmo de burbuja O(n^2)tiempo, O(1)espacio
	private void ordenarListaNodosPorExpandir() {
        for (int i = 0; i < getNodosPorExpandir().size()-1; i++) { 
            for (int j = 0; j < getNodosPorExpandir().size()-i-1; j++) {
            	if (getNodosPorExpandir().get(j).getF() > getNodosPorExpandir().get(j+1).getF()) { 
                    NodoBusqueda temp = getNodosPorExpandir().get(j); 
                    getNodosPorExpandir().set(j, getNodosPorExpandir().get(j+1));
                    getNodosPorExpandir().set(j+1, temp); 
                }else if(getNodosPorExpandir().get(j).getF() == getNodosPorExpandir().get(j+1).getF() &&
                		getNodosPorExpandir().get(j).getH() > getNodosPorExpandir().get(j+1).getH()) {
                	NodoBusqueda temp = getNodosPorExpandir().get(j); 
                    getNodosPorExpandir().set(j, getNodosPorExpandir().get(j+1));
                    getNodosPorExpandir().set(j+1, temp); 
                }
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
		Iterator<NodoBusqueda> itr = nodoPadre.iterator();
		while(itr.hasNext()) {
			NodoBusqueda nodoHijo = itr.next();
			if(!getNodosExpandidos().contains(nodoHijo) &&
					!getNodosPorExpandir().contains(nodoHijo)) {
				getNodosPorExpandir().add(nodoHijo);
			}
		}
	}
	
	//Calcular los meritos de los nodos en la lista de por expandir
	private void calcularMeritos(NodoBusqueda nodoCalculando, Celda celdaMeta) {
		Iterator<NodoBusqueda> itr = nodoCalculando.iterator();
		while(itr.hasNext()) {
			NodoBusqueda nodoHijoActual = itr.next();
			nodoHijoActual.setG(nodoHijoActual.getNodoPadre().getG()+1);
			nodoHijoActual.setH(distanciaEuclidea(nodoHijoActual.getCelda(), celdaMeta));
//			nodoHijoActual.setH(distanciaManhattan(nodoHijoActual.getCelda(), celdaMeta));
			nodoHijoActual.setF(nodoHijoActual.getG()+nodoHijoActual.getH());
		}
	}
	
	private int distanciaEuclidea(Celda celdaOrigen, Celda celdaDestino) {
		int distanciaFilas = Math.abs(celdaOrigen.getFila()-celdaDestino.getFila());
		int distanciaColumnas = Math.abs(celdaOrigen.getColumna()-celdaDestino.getColumna());
		return (int) Math.sqrt(((distanciaFilas*distanciaFilas) + (distanciaColumnas*distanciaColumnas)));
	}
	
	private int distanciaManhattan(Celda celdaOrigen, Celda celdaDestino) {
		int distanciaFilas = Math.abs(celdaOrigen.getFila())-celdaDestino.getFila();
		int distanciaColumnas = Math.abs(celdaOrigen.getColumna()-celdaDestino.getColumna());
		return distanciaFilas+distanciaColumnas;
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
