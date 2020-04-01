/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import domain.GeneralEntity;
import domain.User;
import so.AbstractGenericOperation;

/**
 *
 * @author not-sure
 */
public class LoginSO extends AbstractGenericOperation {
    private GeneralEntity user;
    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof User)) {
            throw new Exception("Invalid object!");
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        user = databaseBroker.getOne((GeneralEntity)entity);
    }
    
    public GeneralEntity getOne(){
        return user;
    }
}
