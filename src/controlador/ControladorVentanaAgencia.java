package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import models.Agencia;
import models.Empleado;
import models.Empleador;
import vista.IVistaAgencia;


public class ControladorVentanaAgencia implements ActionListener{
	private IVistaAgencia vista=null;
	private Agencia agencia=null;
	
	public ControladorVentanaAgencia(IVistaAgencia vista,Agencia agencia) {
		this.vista=vista;
		this.vista.addActionListener(this);
		this.agencia=agencia;
		this.actualizarListas();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		this.actualizarListas();
	}
	
	public void actualizarListas() {
		Iterator<Empleado> itEmpleado=this.agencia.getEmpleados().iterator();
		Iterator<Empleador> itEmpleador=this.agencia.getEmpleadores().iterator();
		this.vista.getModeloListaEmpleado().clear();
		this.vista.getModeloListaEmpleador().clear();
		while(itEmpleado.hasNext()) 	
			this.vista.getModeloListaEmpleado().addElement(itEmpleado.next());	
		while(itEmpleador.hasNext())
			this.vista.getModeloListaEmpleador().addElement(itEmpleador.next());
		this.vista.actualizarListas();
	}

}
