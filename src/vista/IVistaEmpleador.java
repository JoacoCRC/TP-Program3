package vista;

import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public interface IVistaEmpleador {
	void addActionListener(ActionListener listener);
	JRadioButton getRBSelected();
	void enableHayTicket();
}
