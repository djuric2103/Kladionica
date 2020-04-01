/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import domain.Ticket;
import java.util.Date;
import java.util.List;

/**
 *
 * @author not-sure
 */
public interface StorageTicket {

    public List<Ticket> getAllTicketsInPeriod(Date dateFrom, Date dateUntil) throws Exception;
    
    public Ticket getTicket(int ticketID) throws Exception;
    
}
