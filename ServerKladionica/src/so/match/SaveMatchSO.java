/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.match;

import domain.GeneralEntity;
import domain.Match;
import domain.Odds;
import domain.Team;
import java.util.Date;
import java.util.List;
import so.AbstractGenericOperation;
import storage.StorageMatch;
import storage.database.DataBaseStorageMatch;

/**
 *
 * @author not-sure
 */
public class SaveMatchSO extends AbstractGenericOperation{
    private int id;
    private StorageMatch storageMatch;

    public SaveMatchSO() {
        super();
        storageMatch = new DataBaseStorageMatch();
    }
    
    
    
    @Override
    protected void validate(Object entity) throws Exception {
        Object[] arr = (Object[])entity;
        if (!(arr[0] instanceof Match)) {
            throw new Exception("Invalid object!");
        }
        List<Object> list = (List<Object>) arr[1];
        for (Object object : list) {
            if (!(object instanceof Odds)) {
                throw new Exception("Invalid entity parameter!");
            }
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        Object[] arr = (Object[])entity;
        Match match = (Match)arr[0];
        List<Odds> list = (List<Odds>)arr[1];
        alreadyPlaying(match.getHome(), match.getStartTime());
        alreadyPlaying(match.getAway(), match.getStartTime());
        
        id = databaseBroker.saveWithId(match);
        match.setId(id);
        for(Odds o : list){
            o.setMatch(match);
            databaseBroker.save(o);
        }
    }

    public int getID() {
        return id;
    }
    
    public void alreadyPlaying(Team tema, Date date) throws Exception {
        if (storageMatch.alreadyPlaying(tema, date)) {
            throw new Exception("Team " + tema.getName() + " already playing in that time");
        }
    }
}
