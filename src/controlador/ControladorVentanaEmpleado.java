package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import models.Agencia;
import models.Empleado;
import models.Entrevista;
import vista.IVistaEmpleado;

public class ControladorVentanaEmpleado implements ActionListener{
	private IVistaEmpleado vista=null;
	private Agencia agencia=null;
	private Empleado empleado=null;

	public ControladorVentanaEmpleado(IVistaEmpleado vista,Agencia agencia,Empleado empleado) {
		this.agencia=agencia;
		this.vista=vista;
		this.empleado=empleado;
		this.actualizarLista();
		if(this.empleado.getTicket().getFormulario()!=null) 
			this.vista.completarTicket(empleado.getTicket().toString());		
		//if(empleado.getTicket()!=null)this.vista.completarTicket(empleado.getTicket().toString());	
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.actualizarLista();
		
		if(e.getActionCommand().equals("NuevoTicket")) {
			//hacer funcion en agencia para verificar si se puede crear un nuevo ticket y crearlo 
			//abriendo ventana para crear formulario
		}
		else if(e.getActionCommand().equals("Modificar")) {
			//tomar radiobutton seleccionado y pasar su texto en una funcion de la agencia para modificar ticket
			//pasando empleado y text del radio button(estado)
		}
		else if(e.getActionCommand().equals("Elegir")) {
			//tomar elemento seleccionado de la lista y hacer llamado a funcion elegir
		}
	}
	
	public void actualizarLista() {
		Iterator<Entrevista> it=this.empleado.getAsignaciones().getList().iterator();
		this.vista.getModeloLista().clear();
		while(it.hasNext()) {
			this.vista.getModeloLista().addElement(it.next().getEmpleador());
		}
		
	}
}
