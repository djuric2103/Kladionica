/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ticket;

import domain.Odds;
import domain.Ticket;
import evaluator.Evaluator;
import java.util.List;
import so.AbstractGenericOperation;
import storage.StorageOdds;
import storage.database.DatabaseStorageOdds;
import storage.database.DatabaseStorageTicket;

/**
 *
 * @author not-sure
 */
public class GetTicketSO extends AbstractGenericOperation{
    private Ticket ticket;
    private List<Odds> list;
    private StorageOdds storageOdds;

    public GetTicketSO() {
        super();
        this.storageOdds = new DatabaseStorageOdds();
    }
    
    
    
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Ticket)) {
            throw new Exception("Invalid object!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        DatabaseStorageTicket databaseStorageTicket = new DatabaseStorageTicket();
        ticket = databaseStorageTicket.getTicket(((Ticket)entity).getId());
        if(ticket == null)
            throw new Exception("Ticket "+(((Ticket)entity).getId())+" does not exist");
        
        list = storageOdds.getAllOddsForTicket(ticket.getId());
        
        if(ticket.getStatus().equals("Not finished")){
            update();
        }
    }

    public Object[] get() {
        return new Object[]{ticket, list};
    }
    
    
    private void update() throws Exception{
        String newStatus = null;
        boolean allInserted = true;
        
        for(Odds o : list){
            if(o.getMatch().getScoreInserted()){
                if(!Evaluator.evaluate(o)){
                    newStatus = "Losing";
                    break;
                }
            }else{
                allInserted = false;
            }
        }
        if(allInserted && newStatus == null){
            newStatus = "Winning";
        }
        
        if(newStatus != null){
            ticket.setStatus(newStatus);
            databaseBroker.update(ticket);
        }
    }
}
