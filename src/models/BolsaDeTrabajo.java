/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JeroJimenez
 */
public class BolsaDeTrabajo {

    private boolean avaible = false;
    private List<TicketSimplificado> ticketsSimplificados;

    public synchronized void addTicket(TicketSimplificado ts) {
        while (avaible == true) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        this.ticketsSimplificados.add(ts);
        avaible = true;
        notifyAll();

    }

    public BolsaDeTrabajo() {
        this.ticketsSimplificados = new ArrayList<>();
    }

    public synchronized TicketSimplificado removeTicket(int eleccionLocacion, TicketSimplificado ts) {
        while (avaible == false) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        if (eleccionLocacion == ts.getEleccionLocacion()) {
            this.ticketsSimplificados.remove(ts);

            avaible = false;
            notifyAll();
            return ts;
        }
        else{
            return null;
        }
    }
}