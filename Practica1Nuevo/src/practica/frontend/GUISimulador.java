package practica.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import practica.backend.Celda;
import practica.backend.Simulador;

@SuppressWarnings("serial")
public class GUISimulador extends GUIWindow{
	
	private Simulador simulador;
	
	public GUISimulador() {
		setTitle("Simulador IA practica 1");
		setSimulador(new Simulador());
		construirVentana();
	}
	
	public void construirVentana() {
		BorderLayout layout = new BorderLayout();
		obtenerContenedor().setLayout(layout);
		
		GUIPanelEscenario panelEscenario = new GUIPanelEscenario(simulador.getEscenario().getMatrizCeldas());
		panelEscenario.setBackground(cPExt);
		obtenerContenedor().add(panelEscenario, BorderLayout.CENTER);
		
		GUIPanelMenu panelMenu = new GUIPanelMenu(simulador.getEscenario().getMatrizCeldas(), panelEscenario);
		panelMenu.setPreferredSize(new Dimension(200, 38));
		panelMenu.setBackground(cPInt);
		
		JButton botonEjecutar = new JButton("Ejecutar");
		botonEjecutar.addActionListener(new EventoEjecutar(simulador, panelEscenario));
		panelMenu.add(botonEjecutar);
		
		JButton botonCrear = new JButton("Crear");
		EventoCrear eCrear = new EventoCrear(panelEscenario, panelMenu);
		botonCrear.addActionListener(eCrear);
		panelMenu.add(botonCrear);
		getContentPane().add(panelMenu, BorderLayout.NORTH);
		
		panelEscenario.addMouseListener(panelMenu.monitorInteraccionRaton());
		
		pack();
	}

	public Simulador getSimulador() {
		return simulador;
	}

	public void setSimulador(Simulador simulador) {
		this.simulador = simulador;
	}

class EventoEjecutar implements ActionListener{

	private GUIPanelEscenario panel;
	
	private Simulador simulador;
	
	public EventoEjecutar(Simulador simulador, GUIPanelEscenario panel) {
		this.simulador = simulador;
		this.panel = panel;
	}
	
	public EventoEjecutar(GUIPanelEscenario panel) {
		this.panel = panel;
	}
	 
	@Override
	public void actionPerformed(ActionEvent arg0) {
		simulador.ejecutarBusqueda(panel.getMatrizGUICeldasEscenario());
		panel.ejecutarListaAcciones(simulador.getListaAcciones());
	}
	
}

class EventoCrear implements ActionListener{

	private GUIPanelEscenario panel;
	private GUIPanelMenu menu;
	
	private int numFilas, numColumnas;
	private int posXInicio, posYInicio;
	private int posXMeta, posYMeta;
	
	private JLabel etiquetaNumFilas, etiquetaNumColumnas;
	private JLabel etiquetaPosXInicio, etiquetaPosYInicio;
	private JLabel etiquetaPosXMeta, etiquetaPosYMeta;
	
	private JTextField tNumFilas, tNumColumnas;
	private JTextField tEntradaPosXInicio, tEntradaPosYInicio;
	private JTextField tEntradaPosXMeta, tEntradaPosYMeta;
	
	
	public EventoCrear(GUIPanelEscenario panel, GUIPanelMenu menu) {
		this.menu = menu;
		this.panel = panel;
	}
	
	private JPanel crearPanelEntradas() {
		JPanel panelSalida = new JPanel();
		panelSalida.setPreferredSize(new Dimension(300, 400));
		GridLayout gl = new GridLayout(6,2);
		panelSalida.setLayout(gl);
		
		etiquetaNumFilas = new JLabel("Numero de filas");
		panelSalida.add(etiquetaNumFilas);
		
		tNumFilas = new JTextField(3);
		panelSalida.add(tNumFilas);
		
		etiquetaNumColumnas = new JLabel("Numero de columnas");
		panelSalida.add(etiquetaNumColumnas);
		
		tNumColumnas = new JTextField(3);
		panelSalida.add(tNumColumnas);
		
		etiquetaPosXInicio = new JLabel("Fila celda inicial");
		panelSalida.add(etiquetaPosXInicio);
		
		tEntradaPosXInicio = new JTextField(3);
		panelSalida.add(tEntradaPosXInicio);
				
		etiquetaPosYInicio = new JLabel("Columna celda inicial");
		panelSalida.add(etiquetaPosYInicio);
		
		tEntradaPosYInicio = new JTextField(3);
		panelSalida.add(tEntradaPosYInicio);
				
		etiquetaPosXMeta = new JLabel("Fila celda meta");
		panelSalida.add(etiquetaPosXMeta);
		
		tEntradaPosXMeta = new JTextField(3);
		panelSalida.add(tEntradaPosXMeta);
				
		etiquetaPosYMeta = new JLabel("Columna celda meta");
		panelSalida.add(etiquetaPosYMeta);
		
		tEntradaPosYMeta = new JTextField(3);
		panelSalida.add(tEntradaPosYMeta);
		
		return panelSalida;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JPanel dialogoCrear = crearPanelEntradas();
		int i = JOptionPane.showConfirmDialog(null, dialogoCrear, 
	               "Introducir la configuracion del escenario", JOptionPane.OK_CANCEL_OPTION);
		if(i==0) { //aceptar es 0
			int numFilas = Integer.parseInt(tNumFilas.getText());
			setNumFilas(numFilas);
			
			int numColumnas = Integer.parseInt(tNumColumnas.getText());
			setNumColumnas(numColumnas);
			
			int posXInicio = Integer.parseInt(tEntradaPosXInicio.getText());
			setPosXInicio(posXInicio);
			
			int posYInicio = Integer.parseInt(tEntradaPosYInicio.getText());
			setPosXInicio(posYInicio);

			int posXMeta = Integer.parseInt(tEntradaPosXMeta.getText());
			setPosXInicio(posXMeta);

			int posYMeta = Integer.parseInt(tEntradaPosXMeta.getText());
			setPosXInicio(posYMeta);
			
			Celda[][] c = new Celda[numFilas][numColumnas];
			for(int u = 0; u < numFilas; u++) {
				for(int v = 0; v < numColumnas; v++) {
					c[u][v] = new Celda();
				}
			}
			c[posXInicio][posYInicio].setInicio(true);
			c[posXMeta][posYMeta].setMeta(true);
			
			panel.setMatrizCeldas(c);
			menu.setCeldasEscenario(c);
		}
	}

	public int getPosXInicio() {
		return posXInicio;
	}

	public void setPosXInicio(int posXInicio) {
		this.posXInicio = posXInicio;
	}

	public int getNumColumnas() {
		return numColumnas;
	}

	public void setNumColumnas(int numColumnas) {
		this.numColumnas = numColumnas;
	}
	

	public int getNumFilas() {
		return numFilas;
	}

	public void setNumFilas(int numFilas) {
		this.numFilas = numFilas;
	}
	

	public int getPosYInicio() {
		return posYInicio;
	}

	public void setPosYInicio(int posYInicio) {
		this.posYInicio = posYInicio;
	}

	public int getPosXMeta() {
		return posXMeta;
	}

	public void setPosXMeta(int posXMeta) {
		this.posXMeta = posXMeta;
	}

	public int getPosYMeta() {
		return posYMeta;
	}

	public void setPosYMeta(int posYMeta) {
		this.posYMeta = posYMeta;
	}

}

}

