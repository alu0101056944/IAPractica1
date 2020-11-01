package practica.frontend;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;

import practica.backend.Cell;

@SuppressWarnings("serial")
public class GUISimulador extends GUIWindow{
	
	private static final Random r = new Random();
	
	public GUISimulador() {
		setTitle("prueba");
		construirVentana();
	}
	
	public void construirVentana() {
		BorderLayout layout = new BorderLayout();
		obtenerContenedor().setLayout(layout);
		
		Cell[][] celdasEscenario = new Cell[2][25];
		for(int a = 0; a < celdasEscenario.length; a++) {
			for (int b = 0; b < celdasEscenario[0].length; b++) {
				int rnd = r.nextInt(3);
				celdasEscenario[a][b] = new Cell();
				if(rnd == 1) celdasEscenario[a][b].setOccupied(true);
			}
		}
		celdasEscenario[0][0].setInitial(true);
		celdasEscenario[celdasEscenario.length-1][celdasEscenario[0].length-1].setFinish(true);
		
		
		GUIPanelEscenario panelEscenario = new GUIPanelEscenario(celdasEscenario);
		panelEscenario.setBackground(cPExt);
		obtenerContenedor().add(panelEscenario, BorderLayout.CENTER);
		
		GUIPanelMenu panelMenu = new GUIPanelMenu(celdasEscenario, panelEscenario);
		panelMenu.setPreferredSize(new Dimension(200, 38));
		panelMenu.setBackground(cPInt);
		JButton boton = new JButton("pruebaaa");
		boton.addActionListener(new Evento(panelEscenario));
		panelMenu.add(boton);
		JButton boton2 = new JButton("izq");
		boton2.addActionListener(new Eventoo(panelEscenario));
		panelMenu.add(boton2);
		obtenerContenedor().add(panelMenu, BorderLayout.NORTH);
		
		panelEscenario.addMouseListener(panelMenu.monitorInteraccionRaton());
		
		pack();
	}
	
}

class Evento implements ActionListener{

	private GUIPanelEscenario panel;
	
	public Evento(GUIPanelEscenario panel) {
		this.panel = panel;
	}
	 
	@Override
	public void actionPerformed(ActionEvent arg0) {
		panel.moverADerecha();
	}
	
}

class Eventoo implements ActionListener{

	private GUIPanelEscenario panel;
	
	public Eventoo(GUIPanelEscenario panel) {
		this.panel = panel;
	}
	 
	@Override
	public void actionPerformed(ActionEvent arg0) {
	}
	
}

