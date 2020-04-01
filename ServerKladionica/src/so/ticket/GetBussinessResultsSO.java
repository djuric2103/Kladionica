/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ticket;

import domain.Odds;
import domain.Ticket;
import evaluator.Evaluator;
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
public class GetBussinessResultsSO extends AbstractGenericOperation{
    private double income;
    private double outcome;
    private int nOfPayments;
    private int nOfPayoffs;
    List<Ticket> list;
    StorageTicket storageTicket;
    StorageOdds storageOdds;
    
    public GetBussinessResultsSO() {
        super();
        storageTicket = new DatabaseStorageTicket();
        storageOdds = new DatabaseStorageOdds();
    }
    
    
    

    @Override
    protected void validate(Object entity) throws Exception {
        Object[] arr = (Object[])entity;
        if (!(arr[0] instanceof Date && arr[1] instanceof Date)) {
            throw new Exception("Invalid object!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        Object[] arr = (Object[])entity;
        Date dateFrom = (Date)arr[0];
        Date dateUntil = (Date)arr[1];
        
        list = storageTicket.getAllTicketsInPeriod(dateFrom, dateUntil);

        income = 0;
        outcome = 0;
        nOfPayments = 0;
        nOfPayoffs = 0;
        
        for(Ticket ticket : list){
            nOfPayments++;
            income += ticket.getPaid();
            if(ticket.getStatus().equals("Not finished")){
                List<Odds> odds = storageOdds.getAllOddsForTicket(ticket.getId());
                update(ticket, odds);
            }
            if(ticket.getStatus().equals("Payed")){
                nOfPayoffs++;
                outcome += ticket.getWin();
            }
        }
    }

    public Object[] getResults() {
        return new Object[] {income, outcome, nOfPayments, nOfPayoffs, list};
    }
    
    private void update(Ticket ticket, List<Odds> list) throws Exception{
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
