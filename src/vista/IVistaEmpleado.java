package vista;

import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;

import modelo.Empleador;

public interface IVistaEmpleado {
	JRadioButton getRButtonActivar();
	JRadioButton getRButtonSuspender();
	JRadioButton getRButtonCancelar();
	void actualizarLista();
	void completarTicket(String ticket);
	DefaultListModel<Empleador> getModeloLista();
}
