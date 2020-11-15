package practica.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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
		
		JButton botonEjecutar = new JButton("Buscar");
		botonEjecutar.addActionListener(new EventoEjecutar(simulador, panelEscenario));
		panelMenu.add(botonEjecutar);
		
		JButton botonCrear = new JButton("Crear");
		EventoCrear eCrear = new EventoCrear(panelEscenario, panelMenu);
		botonCrear.addActionListener(eCrear);
		panelMenu.add(botonCrear);
		
		JButton botonAbrirFichero = new JButton("Abrir fichero");
		botonAbrirFichero.setSize(new Dimension(80, botonCrear.getHeight()));
		Font f = new Font(botonAbrirFichero.getFont().getFontName(), Font.PLAIN, 10);
		EventoAbrirFichero eventoAbrir = new EventoAbrirFichero(this, panelMenu, panelEscenario);
		botonAbrirFichero.addActionListener(eventoAbrir);
		botonAbrirFichero.setFont(f);
		panelMenu.add(botonAbrirFichero);
		
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
		panel.limpiarRecorrido();
		panel.ponerCocheEnCelda(simulador.getEscenario().getCeldaInicial().getFila(), 
				simulador.getEscenario().getCeldaInicial().getColumna());
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

class EventoAbrirFichero implements ActionListener{

	final JFileChooser fc = new JFileChooser();
	
	private GUISimulador ventanaPadre;
	
	private GUIPanelMenu panelMenu;
	
	private GUIPanelEscenario panelEscenario;
	
	public EventoAbrirFichero(GUISimulador ventanaPadre, GUIPanelMenu panelMenu,
			GUIPanelEscenario panelEscenario) {
		this.ventanaPadre = ventanaPadre;
		this.panelEscenario = panelEscenario;
		this.panelMenu = panelMenu;
		fc.setCurrentDirectory(new File("."));
	}
	 
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int resultadoOperacion = fc.showOpenDialog(ventanaPadre);
		
		if(resultadoOperacion==JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			crearMatrizPorArchivo(f);
		}
	}
	
	private void crearMatrizPorArchivo(File f) {
		try(BufferedReader bf = Files.newBufferedReader(f.toPath())){
			
			Celda[][] matriz = crearMatrizConDatosDelArchivo(bf);
			
			bf.readLine();							//Obtenemos la fila y columna del inicio
			bf.readLine();
			String posInicio = bf.readLine();
			String[] tokensPosInicio = posInicio.split(" ");
			int filaInicio = Integer.parseInt(tokensPosInicio[0]);
			int columnaInicio = Integer.parseInt(tokensPosInicio[1]);
			matriz[filaInicio][columnaInicio].setInicio(true);
			
			bf.readLine();							//Obtenemos la fila y columna de la meta
			bf.readLine();
			String posMeta = bf.readLine();
			String[] tokensPosMeta = posMeta.split(" ");
			int filaMeta = Integer.parseInt(tokensPosMeta[0]);
			int columnaMeta = Integer.parseInt(tokensPosMeta[1]);
			matriz[filaMeta][columnaMeta].setMeta(true);
			
			bf.readLine();
			bf.readLine();
			String linea = null;
			while((linea = bf.readLine())!=null) {
				String[] tokensObstaculos = linea.split(" ");
				int filaObstaculo = Integer.parseInt(tokensObstaculos[0]);
				int columnaObstaculo = Integer.parseInt(tokensObstaculos[1]);
				matriz[filaObstaculo][columnaObstaculo].setOcupada(true);
			}
			
			panelEscenario.setMatrizCeldas(matriz);
			panelMenu.setCeldasEscenario(matriz);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Celda[][] crearMatrizConDatosDelArchivo(BufferedReader bf) throws IOException {
		bf.readLine();
		String segundaLinea = bf.readLine();
		String[] tokensCrearMatriz = segundaLinea.split(" ");
		int numFilas = Integer.parseInt(tokensCrearMatriz[0]);
		int numColumnas = Integer.parseInt(tokensCrearMatriz[1]);
		Celda[][] celdaMatriz = new Celda[numFilas][numColumnas];
		for(int i = 0; i < numFilas; i++) {
			for(int j = 0; j < numColumnas; j++) {
				celdaMatriz[i][j] = new Celda();
			}
		}
		return celdaMatriz;
	}
	
}

}

