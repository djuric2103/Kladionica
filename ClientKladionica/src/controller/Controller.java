/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import communication.Communication;
import domain.Has;
import domain.Match;
import domain.Odds;
import domain.Ticket;
import domain.Tip;
import domain.User;
import java.util.Date;
import java.util.List;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;
import domain.GeneralEntity;

/**
 *
 * @author not-sure
 */
public class Controller {
    private static  Controller instance;
    
    private Controller(){
        
    }
    
    public static Controller getInstance(){
        return instance = instance == null ? new Controller() : instance;
    }

    public User login(String username, String password) throws Exception{
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Request request = new Request(transfer.util.Operation.LOGIN, user);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        System.out.println("stigao");
        if(response.getStatus() == ResponseStatus.SUCCESS){
            return (User) response.getData();
        }
        throw (Exception) response.getError();
    }

    public List<GeneralEntity> getAllTeams() throws Exception{
        Request request = new Request(Operation.GET_ALL_TEAMS, null);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.SUCCESS){
            return (List<GeneralEntity>)response.getData();
        }
        throw (Exception) response.getError();
    }
    
   
    public Match saveMatch(Match match, List<Odds> list) throws Exception{
        Request request = new Request(Operation.SAVE_MATCH, new Object[] {match, list});
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.SUCCESS){
            return (Match)response.getData();
        }
        throw (Exception) response.getError();
    }

    public Match getMatchForScoreInsert(int id) throws Exception{
        Request request = new Request(Operation.GET_MATCH_FOR_SCORE_INSERT, id);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.SUCCESS){
            return (Match)response.getData();
        }
        throw (Exception) response.getError();
    }

    public void saveScore(Match match) throws Exception{
        Request request = new Request(Operation.SAVE_SCORE, match);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.ERROR)
            throw (Exception) response.getError();
    }

    public List<GeneralEntity> getAllTips() throws Exception{
        Request request = new Request(Operation.GET_ALL_TIPS, null);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.SUCCESS){
            return (List<GeneralEntity>)response.getData();
        }
        throw (Exception) response.getError();
    }

    public Odds getOddsForNewTicket(int id, Tip tip) throws Exception{
        Request request = new Request(Operation.GET_ODDS_FOR_NEW_TICKET, new Object[] {id, tip});
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.SUCCESS){
            return (Odds)response.getData();
        }
        throw (Exception) response.getError();
    }

    public Ticket saveTicket(Ticket ticket, List<Has> list) throws Exception{
        Request request = new Request(Operation.SAVE_TICKET, new Object[] {ticket, list});
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.ERROR){
            throw (Exception) response.getError();
        }
        return (Ticket)response.getData();
    }

    public Object[] getTicket(int id) throws Exception{
        Request request = new Request(Operation.GET_TICKET, id);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.ERROR){
            throw (Exception) response.getError();
        }
        return (Object[])response.getData();
    }

    public void deleteTicket(int id) throws Exception{
        Request request = new Request(Operation.DELETE_TICKET, id);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.ERROR)
            throw (Exception) response.getError();
    }

    public Ticket payoffTicket(int id) throws Exception{
        Request request = new Request(Operation.PAYOFF_TICKET, id);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.ERROR){
            throw (Exception) response.getError();
        }
        return (Ticket)response.getData();
    }

    public Object[] getBussinessResults(Date dateFrom, Date dateUntil) throws Exception{
        Request request = new Request(Operation.GET_BUSSINESS_RESULTS, new Object[] {dateFrom, dateUntil});
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if(response.getStatus() == ResponseStatus.ERROR){
            throw (Exception) response.getError();
        }
        return (Object[])response.getData();
    }
}
