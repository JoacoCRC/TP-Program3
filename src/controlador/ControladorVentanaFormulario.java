package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.Agencia;
import models.Ticket;
import vista.IVistaFormulario;

public class ControladorVentanaFormulario implements ActionListener{
	private IVistaFormulario vista=null;
	private Ticket ticket=null;
	private Agencia agencia=null;
	
	public ControladorVentanaFormulario(IVistaFormulario vista,Ticket ticket,Agencia agencia) {
		this.vista=vista;
		this.vista.addActionListener(this);
		this.ticket=ticket;
		this.agencia=agencia;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Aceptar")) {
			int peso1=0,peso2=0,peso3=0,peso4=0,peso5=0,peso6=0,peso7=0;
			boolean condicion=false;
			try {
				peso1=Integer.parseInt(this.vista.getPesoLocacion());
				peso2=Integer.parseInt(this.vista.getPesoRemuneracion());
				peso3=Integer.parseInt(this.vista.getPesoCargaHoraria());
				peso4=Integer.parseInt(this.vista.getPesoPuestoLaboral());
				peso5=Integer.parseInt(this.vista.getPesoRangoEtario());
				peso6=Integer.parseInt(this.vista.getPesoExperiencia());
				peso7=Integer.parseInt(this.vista.getPesoEstudios());
				if(peso1>0 && peso1<=10 && peso2>0 && peso2<=10 && peso3>0 && peso3<=10 &&
					peso4>0 && peso4<=10 && peso5>0 && peso5<=10 && peso6>0 && peso6<=10 &&
					peso7>0 && peso7<=10)
					condicion=true;
				else
					this.vista.emergentePesoIncorrecto();
			}catch(NumberFormatException exception) {
				this.vista.emergentePesoIncorrecto();
			}
			
			//FormularioBusqueda formulario=new FormularioBusqueda();
			//this.ticket.setFormulario(formulario);
			if(condicion) {
				/*System.out.println(this.vista.getRdbLocacion().getText()+" peso= "+peso1);
				System.out.println(this.vista.getRdbRemuneracion().getText()+" peso= "+peso2);
				System.out.println(this.vista.getRdbCargaHorario().getText()+" peso= "+peso3);
				System.out.println(this.vista.getRdbTipoPuesto().getText()+" peso= "+peso4);
				System.out.println(this.vista.getRdbLRangoEtario().getText()+" peso= "+peso5);
				System.out.println(this.vista.getRdbExperiencia().getText()+" peso= "+peso6);
				System.out.println(this.vista.getRdbEstudios().getText()+" peso= "+peso7);*/
			}
		}
		else if(e.getActionCommand().equals("Cancelar")) {
			//VentanaFormulario v=(VentanaFormulario)this.vista;
			//v.setVisible(false);
			
			
		}	
	}
}
