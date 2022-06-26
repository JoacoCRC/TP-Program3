package models;

import java.io.IOException;
import java.time.LocalDate;

import models.Aspectos.DecoratorAspecto;
import models.Aspectos.DecoratorAspectoPeso;
import models.Aspectos.CargaHoraria.CargaHoraria;
import models.Aspectos.CargaHoraria.JornadaCompleta;
import models.Aspectos.Estudios.Estudios;
import models.Aspectos.Estudios.Terciario;
import models.Aspectos.Experiencia.Experiencia;
import models.Aspectos.Experiencia.ExperienciaMedia;
import models.Aspectos.Locacion.Locacion;
import models.Aspectos.Locacion.LocacionHomeOffice;
import models.Aspectos.RangoEtario.EdadEntreV1yV2;
import models.Aspectos.RangoEtario.EdadMenorQue;
import models.Aspectos.RangoEtario.RangoEtario;
import models.Aspectos.Remuneracion.Remuneracion;
import models.Aspectos.Remuneracion.RemuneracionEntreV1yV2;
import models.Aspectos.TipoPuesto.Senior;
import models.Aspectos.TipoPuesto.TipoPuesto;
import persistencia.PersistenciaBinariaAgencia;

public class main {

    public static void main(String[] args) {
        Agencia agencia = new Agencia("agencia@gsdg", "1423523");

        Empleador empleador1 = new Empleador("Empleador1", "1234", "empleador1", "Fisica", "Comercio Local", agencia.getBolsaDeTrabajo());

        // Crear aspectos para crear el formulario de busqueda
        // crear aspecto de locacion
        Locacion locacionHO = new LocacionHomeOffice();
        // Este empleador le pone un peso de 10 a locacion home office 
        // crear aspecto locacion con peso usando decorator
        DecoratorAspecto decoratorLocacion = new DecoratorAspectoPeso(locacionHO, 10);

        // crear aspecto de experiencia
        Experiencia experienciaMedia = new ExperienciaMedia();
        // Este empleador le pone un peso de 10 a experiencia
        // crear aspecto experiencia con peso usando decorator
        DecoratorAspecto decoratorExperiencia = new DecoratorAspectoPeso(experienciaMedia, 10);
        // crear aspecto estudios
        Estudios estudios = new Terciario();
        // Este empleador le pone un peso de 10 a estudios
        // crear aspecto estudios con peso usando decorator
        DecoratorAspecto decoratorEstudios = new DecoratorAspectoPeso(estudios, 10);

        // crear aspeto remuneracion
        Remuneracion remuneracion = new RemuneracionEntreV1yV2();

        // Este empleador le pone un peso de 12 a remuneracion
        // crear aspecto remuneracion con peso usando decorator
        DecoratorAspecto decoratorRemuneracion = new DecoratorAspectoPeso(remuneracion, 10);

        // crear aspecto tipo de puesto
        TipoPuesto tipoPuesto = new Senior();
        // Este empleador le pone un peso de 10 a tipo de puesto
        // crear aspecto tipo de puesto con peso usando decorator
        DecoratorAspecto decoratorTipoPuesto = new DecoratorAspectoPeso(tipoPuesto, 10);

        // crear aspecto eleccion edad
        RangoEtario eleccionEdad = new EdadEntreV1yV2();
        // Este empleador le pone un peso de 5 a eleccion edad
        // crear aspecto eleccion edad con peso usando decorator
        DecoratorAspecto decoratorEleccionEdad = new DecoratorAspectoPeso(eleccionEdad, 5);

        // crear aspecto carga horaria
        CargaHoraria cargaHoraria = new JornadaCompleta();
        // Este empleador le pone un peso de 15 a carga horaria
        // crear aspecto carga horaria con peso usando decorator
        DecoratorAspecto decoratorCargaHoraria = new DecoratorAspectoPeso(cargaHoraria, 15);
        // Crear formulario de busqueda

        // seteo el form al ticket
        empleador1.setFormulario(3, 0, decoratorLocacion, decoratorTipoPuesto,
                decoratorEleccionEdad, decoratorExperiencia, decoratorEstudios, decoratorRemuneracion,
                decoratorCargaHoraria);

        ///////////////////////////////////////////////////////////////////////////////
        /////////////			Creo un empleado llamado Empleado1		///////////////
        ///////////////////////////////////////////////////////////////////////////////
        Empleado empleado1 = new Empleado("Empleado1", "1234", "empleado1", "apellido1", "2224656", "4955151",
                LocalDate.of(1999, 01, 01), agencia.getBolsaDeTrabajo());

        ///////////////////////////////////////////////////////////////////////////////
        /////////////			Creo un ticket para el empleado 1		///////////////
        ///////////////////////////////////////////////////////////////////////////////
        // El ticket del empleado no tiene un peso asignado, entonces no uso el decorator
        TicketEmpleado ticketEmpleado1 = new TicketEmpleado();
        Locacion locacionEmpleado1 = new LocacionHomeOffice();
        TipoPuesto tipoPuestoEmpleado1 = new Senior();
        RangoEtario rangoEmpleado1 = new EdadMenorQue();
        Experiencia expEmpleado1 = new ExperienciaMedia();
        Estudios estudiosEmpleado1 = new Terciario();
        Remuneracion remuneracionEmpleado1 = new RemuneracionEntreV1yV2();
        CargaHoraria cargaEmpleado1 = new JornadaCompleta();

        ///////////////////////////////////////////////////////////////////////////////
        //////			Creo el formulario de busqueda para el empleado 1		///////
        ///////////////////////////////////////////////////////////////////////////////
        FormularioBusqueda fbEmpleado1 = new FormularioBusqueda(locacionEmpleado1,
                tipoPuestoEmpleado1,
                rangoEmpleado1,
                expEmpleado1,
                estudiosEmpleado1,
                remuneracionEmpleado1,
                cargaEmpleado1);
        empleado1.setFormulario(fbEmpleado1);

        ///////////////////////////////////////////////////////////////////////////////
        //////			Agrego el empleador1 y el empleado1 a la agencia		///////
        ///////////////////////////////////////////////////////////////////////////////
        agencia.addEmpleado(empleado1);
        agencia.addEmpleador(empleador1);

        ///////////////////////////////////////////////////////////////////////////////
        //////					Inicializamos la ronda de encuentros			///////
        ///////////////////////////////////////////////////////////////////////////////
        agencia.iniciarRondaEncuentros();

        ///////////////////////////////////////////////////////////////////////////////
        //////	Obtengo las asignaciones con sus puntajes para el empleado1		///////
        ///////////////////////////////////////////////////////////////////////////////
        System.out.println(agencia.getAsignaciones(empleado1));

        ///////////////////////////////////////////////////////////////////////////////
        //////	Obtengo las asignaciones con sus puntajes para el empleador1	///////
        ///////////////////////////////////////////////////////////////////////////////
        System.out.println(agencia.getAsignaciones(empleador1));
        empleado1.setAsignaciones(agencia.getAsignaciones(empleado1));
        empleador1.setAsignaciones(agencia.getAsignaciones(empleador1));
        ///////////////////////////////////////////////////////////////////////////////
        //////	Inicio ronda de eleccion para empleado y empleador	///////
        ///////////////////////////////////////////////////////////////////////////////
        empleador1.inicarRondaElecciones();
        empleado1.inicarRondaElecciones();

        agencia.IniciarRondaContratacion();
        agencia.setPuntaje(empleado1);
        agencia.setPuntaje(empleador1);

        System.out.println("///////hay tantas contrataciones: " + agencia.getContrataciones().size());

        agencia.comision();
//        System.out.println(empleador1.getTickets().toArray()[0]);
//        System.out.println(empleado1.getTicket());

        System.out.println(empleado1.getTicket().getState().toString());
        System.out.println(empleador1.getTickets().get(0).getState().toString());

        PersistenciaBinariaAgencia persistencia = new PersistenciaBinariaAgencia();
        persistir(persistencia, agencia);
        agencia = null;
        agencia = despersistir(persistencia);

        System.out.println(agencia.getUsername());
        System.out.println("**************EMPLEADOS*****************");
        for (Empleado e : agencia.getEmpleados()) {
            System.out.println(e);
        }
        System.out.println("**************EMPLEADORES*****************");
        for (Empleador e : agencia.getEmpleadores()) {
            System.out.println(e);
        }
        System.out.println("**************ENTREVISTAS*****************");
        for (Entrevista e : agencia.getEntrevistas()) {
            System.out.println(e);
        }
        System.out.println("**************CONTRATACIONES*****************");
        if (!agencia.getContrataciones().isEmpty()) {
            for (Contratacion c : agencia.getContrataciones()) {
                System.out.println(c);
            }
        }
        empleador1.generarTicketSimplificado(2, 0, empleado1);
        Thread h1 = new Thread(empleador1);
        Thread h2 = new Thread(empleado1);
        Observador_VentanaThread ob1 = new Observador_VentanaThread();
        ob1.agregarObservable(empleador1);
        ob1.agregarObservable(empleado1);

    }

    public static void persistir(PersistenciaBinariaAgencia persistencia, Agencia agencia) {
        try {
            persistencia.abrirOutput("Agencia.bin");
            persistencia.escribir(agencia);
            persistencia.cerrarOutput();
            System.out.println("Se persistio agencia");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

    public static Agencia despersistir(PersistenciaBinariaAgencia persistencia) {
        Agencia agencia = null;
        try {

            persistencia.abrirInput("Agencia.bin");
            agencia = (Agencia) persistencia.leer();
            persistencia.cerrarInput();
            System.out.println("Se despersistio agencia");

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return agencia;
    }

}
