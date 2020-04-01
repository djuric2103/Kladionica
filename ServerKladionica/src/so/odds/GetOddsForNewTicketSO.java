/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.odds;

import domain.GeneralEntity;
import domain.Match;
import domain.Odds;
import domain.Tip;
import java.util.Date;
import so.AbstractGenericOperation;
import storage.StorageMatch;
import storage.StorageOdds;
import storage.database.DataBaseStorageMatch;
import storage.database.DatabaseStorageOdds;

/**
 *
 * @author not-sure
 */
public class GetOddsForNewTicketSO extends AbstractGenericOperation{
    private Odds odd;
    private StorageMatch storageMatch;

    public GetOddsForNewTicketSO() {
        super();
        this.storageMatch = new DataBaseStorageMatch();
    }
    
    
    @Override
    protected void validate(Object entity) throws Exception {
        Object[] arr = (Object[])entity;
        if (!(arr[1] instanceof Tip)) {
            throw new Exception("Invalid object!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        Object[] arr = (Object[])entity;
        int id = (int)arr[0];
        Tip tip = (Tip)arr[1];
        Match match = storageMatch.getMatch(id);
        
        if(match.getStartTime().before(new Date())){
            throw new Exception("Match "+match.getId()+" is already start or finished");
        }
        StorageOdds storageOdds = new DatabaseStorageOdds();
        
        double value = storageOdds.getValue(id, tip.getName());
        
        odd = new Odds(match, tip, value);
    }

    public GeneralEntity get() {
        return odd;
    }
}
