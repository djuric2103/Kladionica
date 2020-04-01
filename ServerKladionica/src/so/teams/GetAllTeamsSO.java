/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.teams;

import domain.GeneralEntity;
import domain.Odds;
import domain.Team;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author not-sure
 */
public class GetAllTeamsSO extends AbstractGenericOperation{
    List<GeneralEntity> list;
    @Override
    protected void validate(Object entity) throws Exception {
        if(!(entity instanceof Team)){
            throw new Exception("Invalid object!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        list = databaseBroker.getAll((GeneralEntity)entity);
    }
    
    public List<GeneralEntity> getAll(){
        return list;
    }
    
}
