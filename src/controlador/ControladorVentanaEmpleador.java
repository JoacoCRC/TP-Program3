package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Agencia;
import models.Empleador;
import vista.IVistaEmpleador;

public class ControladorVentanaEmpleador implements ActionListener{
	private IVistaEmpleador vista=null;
	private Agencia agencia=null;
	private Empleador empleador=null;
	
	public ControladorVentanaEmpleador(IVistaEmpleador vista, Agencia agencia, Empleador empleador) {
		this.vista = vista;
		this.vista.addActionListener(this);
		this.agencia = agencia;
		this.empleador = empleador;
		if(this.empleador.getTickets().size()>0)
			this.vista.enableHayTicket();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	
}
