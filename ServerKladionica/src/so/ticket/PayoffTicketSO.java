/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ticket;

import domain.Ticket;
import so.AbstractGenericOperation;

/**
 *
 * @author not-sure
 */
public class PayoffTicketSO extends AbstractGenericOperation{
    private Ticket ticket;
    
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Ticket)) {
            throw new Exception("Invalid object!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        ticket = (Ticket)entity;
        if(ticket.getStatus().equals("Losing") || ticket.getStatus().equals("Not finished") || ticket.getStatus().equals("Losing")){
            throw new Exception("Ticket"+ticket.getId()+" is not payed, because it is: "+ticket.getStatus());
        }
        if(ticket.getStatus().equals("Payed")){
            throw new Exception("Ticket"+ticket.getId()+" is not payed, because it is already: "+ticket.getStatus());            
        }
        ticket.setStatus("Payed");
        databaseBroker.update(ticket);
    }

    public Ticket getTicket() {
        return ticket;
    }
    
}
