package practica.frontend;


import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import practica.backend.Celda;

@SuppressWarnings("serial")
public class GUIPanelMenu extends JPanel{

	private Celda[][] celdasEscenario;
	
	private JLabel etiquetaVelocidad;
	private JTextField campoVelocidad;
	private JButton botonVelocidad;
	
	private JButton botonAleatorizarObstaculos;
	private JLabel etiquetaAleatorizarObstaculos;
	private JTextField valorAleatorizacionObstaculos;
	
	private JRadioButton botonSeleccionarObstaculo, botonSeleccionarInicio, botonSeleccionarFinal;
	private ButtonGroup botonesSeleccion;
	
	private GUIPanelEscenario escenario;
	
	private MouseListener monitorRaton;
	
	public GUIPanelMenu(Celda[][] celdasEscenario, GUIPanelEscenario escenario) {
		super();
		this.escenario = escenario;
		this.setCeldasEscenario(celdasEscenario);
		construirGUI();
	}
	
	private void construirGUI() {
		parteCambiarVelocidad();
		parteAleatorizarObstaculos();
		parteFuncionClickCambiarEscenario();
	}
	
	private void parteCambiarVelocidad() {
		etiquetaVelocidad = new JLabel ("velocidad");
		add(etiquetaVelocidad);
		
		campoVelocidad = new JTextField("2");
		campoVelocidad.setPreferredSize(new Dimension(40, 25));
		add(campoVelocidad);
		
		botonVelocidad = new JButton("aplicar");
		EventoCambiarVelocidad eventoVelocidad = new EventoCambiarVelocidad(escenario, campoVelocidad);
		botonVelocidad.addActionListener(eventoVelocidad);
		add(botonVelocidad);
	}
	
	private void parteAleatorizarObstaculos(){
		etiquetaAleatorizarObstaculos = new JLabel("% Obs.");
		add(etiquetaAleatorizarObstaculos);
		
		valorAleatorizacionObstaculos = new JTextField("20");
		valorAleatorizacionObstaculos.setPreferredSize(new Dimension(40, 25));
		add(valorAleatorizacionObstaculos);
		
		botonAleatorizarObstaculos = new JButton(">");
		EventoAleatorizarObstaculos alAleatorizar = new EventoAleatorizarObstaculos(celdasEscenario, escenario,valorAleatorizacionObstaculos, this);
		botonAleatorizarObstaculos.addActionListener(alAleatorizar);
		add(botonAleatorizarObstaculos);
	}
	
	private void parteFuncionClickCambiarEscenario() {
		botonSeleccionarObstaculo = new JRadioButton("Obstaculo");
		botonSeleccionarObstaculo.setSelected(true);
		
		botonSeleccionarInicio = new JRadioButton("Inicio");
		
		botonSeleccionarFinal = new JRadioButton("Meta");
		
		botonesSeleccion = new ButtonGroup();
		botonesSeleccion.add(botonSeleccionarObstaculo);
		botonesSeleccion.add(botonSeleccionarInicio);
		botonesSeleccion.add(botonSeleccionarFinal);
		
		add(botonSeleccionarObstaculo);
		add(botonSeleccionarInicio);
		add(botonSeleccionarFinal);
		
		monitorRaton = new MouseListenerPosicion(escenario, botonSeleccionarObstaculo,
				botonSeleccionarInicio, botonSeleccionarFinal);
	}
	
	/**
	 * Devuelve el monitor de raton que se encarga de cambiar el escenario en base a 
	 * los botones del menu
	 * @return monitorRaton para la posicion del raton
	 */
	public MouseListener monitorInteraccionRaton() {
		return monitorRaton;
	}
	
	/**
	 * @return the celdasEscenario
	 */
	public Celda[][] getCeldasEscenario() {
		return celdasEscenario;
	}

	/**
	 * @param celdasEscenario the celdasEscenario to set
	 */
	public void setCeldasEscenario(Celda[][] celdasEscenario) {
		this.celdasEscenario = celdasEscenario;
	}
	
	
}

class EventoCambiarVelocidad implements ActionListener{

	
	private JTextField campoVelocidad;
	
	private GUIPanelEscenario escenario;
	
	public EventoCambiarVelocidad(GUIPanelEscenario escenario, JTextField campoVelocidad) {
		this.escenario = escenario;
		this.campoVelocidad = campoVelocidad;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String aux = campoVelocidad.getText();
		int nuevaVelocidad = Integer.parseInt(aux);
		escenario.getHiloMovimiento().cambiarVelocidad(nuevaVelocidad);
	}
	
}

class EventoAleatorizarObstaculos implements ActionListener{

	private Celda[][] celdasEscenario;
	
	private JTextField valorAleatorio;
	
	private GUIPanelEscenario escenario;
	
	private final Random random = new Random();
	
	private GUIPanelMenu menu;
	
	public EventoAleatorizarObstaculos(Celda[][] celdasEscenario, GUIPanelEscenario escenario, 
			JTextField valorAleatorio, GUIPanelMenu menu) {
		this.escenario = escenario;
		this.valorAleatorio = valorAleatorio;
		this.celdasEscenario = celdasEscenario;
		this.menu = menu;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		celdasEscenario = menu.getCeldasEscenario();
		aleatorizarObstaculos();
		escenario.repaint();
	}
	
	private void aleatorizarObstaculos() {
		String aux = valorAleatorio.getText();
		for(int i = 0; i < celdasEscenario.length; i++) {
			for(int j = 0; j < celdasEscenario[0].length; j++) {
				
				int valorAleatorio = random.nextInt(100);
				if(valorAleatorio < Integer.parseInt(aux)) {
					celdasEscenario[i][j].setOcupada(true);
				}else {
					celdasEscenario[i][j].setOcupada(false);
				}
			}
		}
	}
	
}

class MouseListenerPosicion implements MouseListener{

	private GUIPanelEscenario escenario;
	private JRadioButton obstaculos, inicio, meta;
	
	
	public MouseListenerPosicion(GUIPanelEscenario escenario, JRadioButton obstaculos,
			JRadioButton inicio, JRadioButton meta) {
		this.inicio = inicio;
		this.obstaculos = obstaculos;
		this.meta = meta;
		this.escenario = escenario;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		GUICelda celda = escenario.obtenerCeldaGrafica(arg0.getX(), arg0.getY());
		if(celda!=null) {
			if(obstaculos.isSelected()) {
				celda.getCelda().setOcupada(!celda.getCelda().estaOcupada());
			}else if(inicio.isSelected()) {
				if(!celda.getCelda().esMeta()) {
					escenario.establecerCeldaInicial(celda);
					escenario.ponerCocheEnCelda(celda.getIndiceMatrizI(), celda.getIndiceMatrizJ());
				}
			}else if(meta.isSelected()) {
				if(!celda.getCelda().esInicio()) {
					escenario.establecerCeldaMeta(celda);
				}
			}
		}
		escenario.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
}
