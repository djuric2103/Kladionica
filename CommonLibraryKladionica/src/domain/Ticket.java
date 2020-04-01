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

/**
 *
 * @author not-sure
 */
public class Ticket implements GeneralEntity{
    private int id;
    private double paid;
    private double totalodds;
    private double win;
    private Date timeOfPayment;
    private String status;
    private User user;

    public Ticket() {
    }
    
    public Ticket(double paid, double totalodds, double win, User user){
        this.paid = paid;
        this.totalodds = totalodds;
        this.win = win;
        this.status = "Not finished";
        this.user = user;
        timeOfPayment = new Date();
    }

    public Ticket(int id, double paid, double totalodds, double win, Date timeOfPayment, String status, User user) {
        this.id = id;
        this.paid = paid;
        this.totalodds = totalodds;
        this.win = win;
        this.timeOfPayment = timeOfPayment;
        this.status = status;
        this.user = user;
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
     * @return the paid
     */
    public double getPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
     */
    public void setPaid(double paid) {
        this.paid = paid;
    }

    /**
     * @return the totalodds
     */
    public double getTotalodds() {
        return totalodds;
    }

    /**
     * @param totalodds the totalodds to set
     */
    public void setTotalodds(double totalodds) {
        this.totalodds = totalodds;
    }

    /**
     * @return the win
     */
    public double getWin() {
        return win;
    }

    /**
     * @param win the win to set
     */
    public void setWin(double win) {
        this.win = win;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimeOfPayment() {
        return timeOfPayment;
    }

    public void setTimeOfPayment(Date timeOfPayment) {
        this.timeOfPayment = timeOfPayment;
    }

    @Override
    public String getTableName() {
        return "tickets";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnsForInsert() {
        return "paid, totalodds, win, timeofpayment, status, username";
    }

    @Override
    public String getValuesForInsert() {
        return paid + ", "+totalodds+", "+win+", timestamp \'"+Convert.date2String(timeOfPayment)+"\', \'"+status+"\', \'"+user.getUsername()+"\'";
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", paid=" + paid + ", totalodds=" + totalodds + ", win=" + win + ", timeOfPayment=" + timeOfPayment + ", status=" + status + ", user=" + user + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id;
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
        final Ticket other = (Ticket) obj;
        return true;
    }
    
    @Override
    public String returnWhere() {
        return "id = " +id;
    }

    @Override
    public String returnSet() {
        return "status = \'"+status+"\'";
    }

    @Override
    public GeneralEntity getOne(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
