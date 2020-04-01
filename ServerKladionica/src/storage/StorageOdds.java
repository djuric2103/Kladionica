/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import domain.Odds;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author not-sure
 */
public interface StorageOdds {
    double getValue(int id, String name) throws Exception;

    //public ResultSet getAllOddsOnTicket(int ticketID) throws Exception;
    public List<Odds> getAllOddsForTicket(int ticketID) throws Exception;
}
