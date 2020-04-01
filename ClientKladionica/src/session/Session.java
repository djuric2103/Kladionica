/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import domain.User;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author not-sure
 */
public class Session {
    private static Session instance;
    private User curreUser;
    private int currentUseCase;
    private final Map<String, Object> useCaseParams;
    
    private Session(){
        useCaseParams = new HashMap<>();
    }
    
    public static Session getInstance(){
        return instance = instance == null ? new Session() : instance;
    }

    public User getCurreUser() {
        return curreUser;
    }

    public void setCurreUser(User curreUser) {
        this.curreUser = curreUser;
    }

    public int getCurrentUseCase() {
        return currentUseCase;
    }

    public void setCurrentUseCase(int currentUseCase) {
        this.currentUseCase = currentUseCase;
    }
    
    public Map<String, Object> getUseCasePArams(){
        return useCaseParams;
    }
}
