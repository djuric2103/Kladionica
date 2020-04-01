/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import convert.Convert;
import domain.Ticket;
import domain.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import storage.StorageTicket;
import storage.database.connection.DatabaseConnection;

/**
 *
 * @author not-sure
 */
public class DatabaseStorageTicket implements StorageTicket{
    DatabaseBroker databaseBroker;
    
    public DatabaseStorageTicket() {
        databaseBroker = new DatabaseBroker();
    }
    
    @Override
    public Ticket getTicket(int ticketID) throws Exception {
        String query = "SELECT * FROM tickets WHERE id = "+ticketID;
        System.out.println(query);
        Connection con = DatabaseConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        Ticket ticket = null;
        while (rs.next()) {
            ticket = new Ticket();
            ticket.setId(rs.getInt("id"));
            ticket.setPaid(rs.getDouble("paid"));
            ticket.setTotalodds(rs.getDouble("totalodds"));
            ticket.setWin(rs.getDouble("win"));
            ticket.setTimeOfPayment(new Date(rs.getTimestamp("timeofpayment").getTime()));
            ticket.setStatus(rs.getString("status"));
            User u = (User)databaseBroker.getOne(new User(rs.getString("username"), null, null, null));
            ticket.setUser(u);
        }

        return ticket;
    }

    @Override
    public List<Ticket> getAllTicketsInPeriod(Date dateFrom, Date dateUntil) throws Exception {
        String query = "SELECT * FROM tickets WHERE timeofpayment >= timestamp \'"+Convert.date2String(dateFrom)+"\' and timeofpayment <= timestamp \'"+Convert.date2String(dateUntil)+"\'";
        System.out.println(query);
        Connection con = DatabaseConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        List<Ticket> list = new LinkedList<>();
        
        while(rs.next()){
            User user = (User)databaseBroker.getOne(new User(rs.getString("username"), null, null, null));
            Date dateOfPayment = new Date(rs.getTimestamp("timeofpayment").getTime());
            Ticket ticket = new Ticket(rs.getInt("id"), rs.getDouble("paid"), rs.getDouble("totalodds"), rs.getDouble("win"), dateOfPayment, rs.getString("status"), user);
            list.add(ticket);
        }
        return list;
    }
    
}
