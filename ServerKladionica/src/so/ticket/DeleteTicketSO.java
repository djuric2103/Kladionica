/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ticket;

import domain.GeneralEntity;
import domain.Odds;
import domain.Ticket;
import java.util.Date;
import java.util.List;
import so.AbstractGenericOperation;
import storage.StorageOdds;
import storage.StorageTicket;
import storage.database.DatabaseStorageOdds;
import storage.database.DatabaseStorageTicket;

/**
 *
 * @author not-sure
 */
public class DeleteTicketSO extends AbstractGenericOperation{
    private StorageOdds storageOdds;
    private StorageTicket storageTicket;
    public DeleteTicketSO() {
        super();
        storageOdds = new DatabaseStorageOdds();
        storageTicket = new DatabaseStorageTicket();
    }
    
    

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Ticket)) {
            throw new Exception("Invalid object!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        Ticket ticket = storageTicket.getTicket(((Ticket)entity).getId());
        if(ticket == null)
            throw new Exception("Ticket "+ ((Ticket)entity).getId()+" does not exist");
        List<Odds> list = storageOdds.getAllOddsForTicket(ticket.getId());
        
        for(Odds o : list){
            if(o.getMatch().getStartTime().before(new Date())){
                throw new Exception("Match "+o.getMatch().getId()+" is already began");
            }
        }
        databaseBroker.delete((GeneralEntity)entity);
    }
    
}
