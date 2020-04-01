/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import convert.Convert;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author not-sure
 */
public class Match implements GeneralEntity{
    private int id;
    private Date startTime;
    private boolean scoreInserted;
    private int goalsHome;
    private int goalsAway;
    private User user;
    private Team home;
    private Team away;

    public Match() {
    }
    
    public Match(Date startTime, User user, Team home, Team away) {
        this.startTime = startTime;
        this.user = user;
        this.home = home;
        this.away = away;
    }

    public Match(int id, Date startTime, User user, Team home, Team away) {
        this.id = id;
        this.startTime = startTime;
        this.user = user;
        this.home = home;
        this.away = away;
    }

    public Match(int id, Date startTime, boolean scoreInserted, int goalsHome, int goalsAway, User user, Team home, Team away) {
        this.id = id;
        this.startTime = startTime;
        this.scoreInserted = scoreInserted;
        this.goalsHome = goalsHome;
        this.goalsAway = goalsAway;
        this.user = user;
        this.home = home;
        this.away = away;
    }
    
    
    
    
    
    @Override
    public String getTableName() {
        return "match";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public String getColumnsForInsert() {
        return "starttime, scoreinserted, goalshome, goalsaway, username, home, away";
    }

    @Override
    public String getValuesForInsert() {
        return "timestamp \'"+Convert.date2String(startTime)+"\', "+scoreInserted+", "+goalsHome+", "+goalsAway+", \'"
                +user.getUsername()+"\', " + "\'"+home.getId()+"\', " + "\'"+away.getId()+"\'";
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the home
     */
    public Team getHome() {
        return home;
    }

    /**
     * @param home the home to set
     */
    public void setHome(Team home) {
        this.home = home;
    }

    /**
     * @return the away
     */
    public Team getAway() {
        return away;
    }

    /**
     * @param away the away to set
     */
    public void setAway(Team away) {
        this.away = away;
    }

    public boolean isScoreInserted() {
        return scoreInserted;
    }

    public boolean getScoreInserted(){
        return scoreInserted;
    }
    
    public void setScoreInserted(boolean scoreInserted) {
        this.scoreInserted = scoreInserted;
    }

    public int getGoalsHome() {
        return goalsHome;
    }

    public void setGoalsHome(int goalsHome) {
        this.goalsHome = goalsHome;
    }

    public int getGoalsAway() {
        return goalsAway;
    }

    public void setGoalsAway(int goalsAway) {
        this.goalsAway = goalsAway;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", startTime=" + startTime + ", scoreInserted=" + scoreInserted + ", goalsHome=" + goalsHome + ", goalsAway=" + goalsAway + ", user=" + user + ", home=" + home + ", away=" + away + '}';
    }



    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Match other = (Match) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String returnWhere() {
        return "id = " +id;
    }
    
    public String returnSet(){
        return "scoreinserted = "+ scoreInserted + ", goalshome = " + goalsHome + ", goalsaway = " + goalsAway;
    }

    @Override
    public GeneralEntity getOne(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
