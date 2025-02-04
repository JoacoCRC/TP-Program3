package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Aspectos.DecoratorAspecto;
import models.Aspectos.IAspectos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Empleado extends Person {
	private String surname;
	private String phone;
	private String dni;
	private LocalDate bornDate;
	private TicketEmpleado ticket;


	public Empleado(String username, String password, String name, String surname, String phone, String dni,
			LocalDate bornDate) {
		super(username, password, name, PERSONA_FISICA);
		this.surname = surname;
		this.phone = phone;
		this.dni = dni;
		this.bornDate = bornDate;
		this.ticket = new TicketEmpleado();
	}

	@Override
	public boolean isEmpleado() {
		return true;
	}
	public String getSurname() {
		return surname;
	}

	public String getPhone() {
		return phone;
	}

	public String getDNI() {
		return dni;
	}

	public LocalDate getBornDate() {
		return bornDate;
	}

	public TicketEmpleado getTicket() {
		return ticket;
	}

	public void setTicket(TicketEmpleado ticket) {
		this.ticket = ticket;
	}
	public void cancelarTicket() {
		if(this.getTicket().getEstado()!=Ticket.ESTADO_CANCELADO && this.getTicket().getEstado()!=Ticket.ESTADO_FINALIZADO) {
			this.getTicket().setEstadoCancelado();		
			this.modificarPuntaje(-1);
		}
	}
	public void suspenderTicket() {
		if(this.getTicket().getEstado()!=Ticket.ESTADO_CANCELADO && this.getTicket().getEstado()!=Ticket.ESTADO_FINALIZADO) {
			this.getTicket().setEstadoSuspendido();
		}
	}
	public void activarTicket() {
		if(this.getTicket().getEstado()!=Ticket.ESTADO_CANCELADO && this.getTicket().getEstado()!=Ticket.ESTADO_FINALIZADO) {
			this.getTicket().setEstadoActivo();
		}
	}

	@Override
	public boolean isAdmin() {
		return false;
	}
	
	public void setFormulario( FormularioBusqueda fb) {
		ticket.setFormulario(fb);
	
	}


	@Override
	public String toString() {
		return super.getName() + " " + getSurname() + "\nTelefono: " + getPhone() + "\nDNI: " + getDNI()
				+ "\nFecha Nacimiento: " + getBornDate();// + "\nTicket generado: " + getTicket().toString();
	}

}
