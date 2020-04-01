/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.match;

import convert.Convert;
import domain.Match;
import java.util.Date;
import so.AbstractGenericOperation;
import storage.StorageMatch;
import storage.database.DataBaseStorageMatch;

/**
 *
 * @author not-sure
 */
public class GetMatchForScoreInsertSO extends AbstractGenericOperation{
    private StorageMatch storageMatch;
    private Match match;
    
    public GetMatchForScoreInsertSO(){
        super();
        storageMatch = new DataBaseStorageMatch();
    }

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Match)) {
            throw new Exception("Invalid object!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        int id = ((Match)entity).getId();
        match = storageMatch.getMatch(id);
        
        if(match == null)
            throw new Exception("Match "+id+" does not exist");
        
        Date endTime = Convert.addHours(match.getStartTime(), 2);
        if (endTime.after(new Date())) {
            throw new Exception("Match is not finished");
        }

        if (match.getScoreInserted()) {
            throw new Exception("Match score is already inserted");
        }
    }

    public Match getMatch() {
        return match;
    }
    
}
