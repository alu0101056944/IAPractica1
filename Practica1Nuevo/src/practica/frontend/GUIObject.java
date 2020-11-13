package practica.frontend;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GUIObject extends JFrame{
	
	private Container container;
	
	protected final Color cPExt = new Color(120, 120, 120); //Color paneles externos
	protected final Color cPInt = new Color(150, 150, 150); //Color panel central
	
	public GUIObject() {
		container = getContentPane();
		container.setBackground(Color.DARK_GRAY);
	}
	
	protected Container obtenerContenedor() {
		return container;
	}

}
