package practica.frontend;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GUIWindow extends GUIObject{

	public GUIWindow() {
		setVisible(true);
		setSize(new Dimension(800, 700));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setMinimumSize(new Dimension(700, 400));
	}
	
}
