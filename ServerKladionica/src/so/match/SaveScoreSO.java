/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.match;

import domain.GeneralEntity;
import domain.Match;
import so.AbstractGenericOperation;

/**
 *
 * @author not-sure
 */
public class SaveScoreSO extends AbstractGenericOperation{

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Match)) {
            throw new Exception("Invalid object!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        databaseBroker.update((GeneralEntity)entity);
    }
    
}
