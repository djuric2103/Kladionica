/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author not-sure
 */
public class Has implements GeneralEntity{
    private Odds odds;
    private Ticket ticket;

    public Has() {
    }

    public Has(Odds odds, Ticket ticket) {
        this.odds = odds;
        this.ticket = ticket;
    }

    /**
     * @return the odds
     */
    public Odds getOdds() {
        return odds;
    }

    /**
     * @param odds the odds to set
     */
    public void setOdds(Odds odds) {
        this.odds = odds;
    }

    /**
     * @return the ticket
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * @param ticket the ticket to set
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Has{" + "odds=" + odds + ", ticket=" + ticket + '}';
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
        final Has other = (Has) obj;
        if (!Objects.equals(this.odds, other.odds)) {
            return false;
        }
        if (!Objects.equals(this.ticket, other.ticket)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "has";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public String getColumnsForInsert() {
        return "matchid, name, ticketid";
    }

    @Override
    public String getValuesForInsert() {
        return odds.getMatch().getId() + ", \'"+odds.getTip().getName() + "\', "+ticket.getId();
    }

    @Override
    public String returnWhere() {
        return "matchid = " + odds.getMatch().getId() + " and name = \'" + odds.getTip().getName() + "\' and ticketid = " + ticket.getId();
    }

    @Override
    public String returnSet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralEntity getOne(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
