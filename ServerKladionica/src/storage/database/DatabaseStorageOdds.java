/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.Match;
import domain.Odds;
import domain.Team;
import domain.Tip;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import storage.StorageMatch;
import storage.StorageOdds;
import storage.database.connection.DatabaseConnection;

/**
 *
 * @author not-sure
 */
public class DatabaseStorageOdds implements StorageOdds{
    StorageMatch storageMatch;

    public DatabaseStorageOdds() {
        storageMatch = new DataBaseStorageMatch();
    }
    
    
    
    
    @Override
    public double getValue(int id, String name) throws Exception {
        String query = "SELECT value FROM odds WHERE id = "+id+" and name = \'"+name+"\'";
        System.out.println(query);
        Connection con = DatabaseConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs =  st.executeQuery(query);
        
        double val = 0;
        int i = 0;
        
        while(rs.next()){
            i++;
            val = rs.getDouble("value");
        }
        
        if(i == 0){
            throw new Exception("Tip + \""+name+"\" does not exist for match "+id);
        }
        
        return val;
    }

//    @Override
//    public ResultSet getAllOddsOnTicket(int ticketID) throws Exception {
//        String query = "select * from odds where (id, name) in (select matchid, name from has where ticketid = "+ticketID+")";
//        System.out.println(query);
//        Connection con = DatabaseConnection.getInstance().getConnection();
//        Statement st = con.createStatement();
//        return  st.executeQuery(query);
//    }
    
    @Override
    public List<Odds> getAllOddsForTicket(int ticketID) throws Exception {
        String query = "select * from odds where (id, name) in (select matchid, name from has where ticketid = "+ticketID+")";
        System.out.println(query);
        Connection con = DatabaseConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        List<Odds> list = new LinkedList<>();
        
        while(rs.next()){
            Odds o = new Odds();
            o.setMatch(storageMatch.getMatch(rs.getInt("id")));
            o.setValue(rs.getDouble("value"));
            o.setTip(new Tip(rs.getString("name")));
            list.add(o);
        }
        
        return list;
    }
}
