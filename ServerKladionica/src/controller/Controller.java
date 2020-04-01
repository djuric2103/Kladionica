/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Has;
import domain.Match;
import domain.Odds;
import domain.Team;
import domain.Ticket;
import domain.Tip;
import domain.User;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import thread.ClientThread;
import domain.GeneralEntity;
import so.match.GetMatchForScoreInsertSO;
import so.match.SaveMatchSO;
import so.match.SaveScoreSO;
import so.odds.GetOddsForNewTicketSO;
import so.teams.GetAllTeamsSO;
import so.ticket.DeleteTicketSO;
import so.ticket.GetBussinessResultsSO;
import so.ticket.GetTicketSO;
import so.ticket.PayoffTicketSO;
import so.ticket.SaveTicketSO;
import so.tip.GetAllTipsSO;
import so.user.LoginSO;

/**
 *
 * @author not-sure
 */
public class Controller {
    private static Controller instance;
    private List<ClientThread> activeClients;
    
    
    private Controller(){
        activeClients = new LinkedList<>();   
    }
    
    public static Controller getInsance(){
        return instance = instance == null ? instance = new Controller() : instance;
    }
    
    public void addClinet(ClientThread client){
        activeClients.add(client);
    }
    
    public void removeClient(ClientThread client){
        activeClients.remove(client);
    }

    public User login(User user) throws Exception{
        LoginSO loginSO = new LoginSO();
        loginSO.templateExecute(user, null);
        User newUser = (User)loginSO.getOne();
        if (newUser == null) {
            throw new Exception("User does not exist");
        }
        if (newUser.getPassword().equals(user.getPassword())) {
            return newUser;
        }
        throw new Exception("Wrog password");
    }

    public List<GeneralEntity> getAllTeams() throws Exception{
        GetAllTeamsSO allTeamsSO = new GetAllTeamsSO();
        allTeamsSO.templateExecute(new Team(), null);
        return allTeamsSO.getAll();
    }
    
    public int saveMatch(Match match, List<Odds> list) throws Exception{
        SaveMatchSO matchSO = new SaveMatchSO();
        matchSO.templateExecute((Object)new Object[]{match, list}, null);
        return matchSO.getID();
    }

    public Match getMatchForScoreInsert(int id) throws Exception{
        Match match = new Match();
        match.setId(id);
        GetMatchForScoreInsertSO gmfsiso = new GetMatchForScoreInsertSO();
        gmfsiso.templateExecute(match, null);
        return gmfsiso.getMatch();
    }

    public void saveScore(Match match) throws Exception{
        SaveScoreSO saveScoreSO = new SaveScoreSO();
        saveScoreSO.templateExecute(match, null);
    }

    public List<GeneralEntity> getAllTips() throws Exception{
        GetAllTipsSO allTipsSO = new GetAllTipsSO();
        allTipsSO.templateExecute(new Tip(), null);
        return allTipsSO.getAll();
    }

    public GeneralEntity getOddsForNewTicket(int id, Tip tip) throws Exception{
        GetOddsForNewTicketSO getOddsSO = new GetOddsForNewTicketSO();
        getOddsSO.templateExecute(new Object[] {id, tip}, null);
        return getOddsSO.get();
    }

    public int saveTicket(Ticket ticket, List<Has> list) throws Exception{
        SaveTicketSO saveTicketSO = new SaveTicketSO();
        saveTicketSO.templateExecute(new Object[] {ticket, list}, null);
        return saveTicketSO.getID();
    }

    public Object[] getTicket(int ticketID) throws Exception{
        GetTicketSO getTicketSO = new GetTicketSO();
        Ticket ticket = new Ticket();
        ticket.setId(ticketID);
        getTicketSO.templateExecute(ticket, null);
        return getTicketSO.get();
    }

    public void deleteTicket(int ticketID) throws Exception{
        DeleteTicketSO deleteTicketSO = new DeleteTicketSO();
        Ticket ticket = new Ticket();
        ticket.setId(ticketID);
        deleteTicketSO.templateExecute(ticket, null);
    }
        
    public Ticket payoffTicket(int ticketID) throws Exception{
        GetTicketSO getTicketSO = new GetTicketSO();
        Ticket ticket = new Ticket();
        ticket.setId(ticketID);
        getTicketSO.templateExecute(ticket, null);
        Object[] arr = getTicketSO.get();
        ticket = (Ticket)arr[0];
        PayoffTicketSO payoffTicketSO = new PayoffTicketSO();
        payoffTicketSO.templateExecute(ticket, null);
        return payoffTicketSO.getTicket();
    }

    public Object[] getBussinessResults(Date dateFrom, Date dateUntil) throws Exception{
        GetBussinessResultsSO bussinessResultsSO = new GetBussinessResultsSO();
        bussinessResultsSO.templateExecute(new Object[] {dateFrom, dateUntil}, null);
        return bussinessResultsSO.getResults();
        
    }
}
