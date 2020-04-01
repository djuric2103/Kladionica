/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import convert.Convert;
import domain.Match;
import domain.Team;
import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import storage.StorageMatch;
import storage.database.connection.DatabaseConnection;

/**
 *
 * @author not-sure
 */
public class DataBaseStorageMatch implements StorageMatch{
    DatabaseBroker databaseBroker;

    public DataBaseStorageMatch() {
        databaseBroker = new DatabaseBroker();
    }
    
    
    
    @Override
    public boolean alreadyPlaying(Team t, Date d) throws Exception {
        String query = "select count(*) from match where (home = ? or away = ?) and (starttime >= ? and starttime <= ?)";
        
        PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
        
        ps.setString(1, t.getId());
        ps.setString(2, t.getId());
        Date beforeStart = Convert.addHours(d, -2);
        Date afterStart = Convert.addHours(d, 2);
        
        ps.setTimestamp(3, new Timestamp(beforeStart.getTime()));
        ps.setTimestamp(4, new Timestamp(afterStart.getTime()));
        ResultSet rs = ps.executeQuery();
        rs.next();
        
        int number = rs.getInt(1);
        return number != 0;
    }
    
    @Override
    public Match getMatch(int id) throws Exception {
        String query = "SELECT * FROM match WHERE id = " +id;
        System.out.println(query);
        Connection con = DatabaseConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        Match match = null;
        while (rs.next()) {
            match = new Match();
            match.setId(rs.getInt("id"));
            match.setStartTime(new Date(rs.getTimestamp("starttime").getTime()));
            match.setScoreInserted(rs.getBoolean("scoreinserted"));
            match.setGoalsHome(rs.getInt("goalshome"));
            match.setGoalsAway(rs.getInt("goalsaway"));
            User user = (User)databaseBroker.getOne(new User(rs.getString("username"), null, null, null));
            match.setUser(user);
            Team home = (Team)databaseBroker.getOne(new Team(rs.getString("home"), null));
            match.setHome(home);
            Team away = (Team)databaseBroker.getOne(new Team(rs.getString("away"), null));
            match.setAway(away);
        }

        return match;
    }
    
}
