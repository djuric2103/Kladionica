/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import domain.Match;
import domain.Team;
import java.util.Date;

/**
 *
 * @author not-sure
 */
public interface StorageMatch {
    boolean alreadyPlaying(Team t, Date d) throws Exception;
    public Match getMatch(int id) throws Exception;
}
