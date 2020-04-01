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
public class Odds implements GeneralEntity{
    private Match match;
    private Tip tip;
    private double value;

    public Odds() {
    }

    public Odds(Match match, Tip tip, double value) {
        this.match = match;
        this.tip = tip;
        this.value = value;
    }

    /**
     * @return the match
     */
    public Match getMatch() {
        return match;
    }

    /**
     * @param match the match to set
     */
    public void setMatch(Match match) {
        this.match = match;
    }

    /**
     * @return the tip
     */
    public Tip getTip() {
        return tip;
    }

    /**
     * @param tip the tip to set
     */
    public void setTip(Tip tip) {
        this.tip = tip;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Odds{" + "match=" + match + ", tip=" + tip + ", value=" + value + '}';
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
        final Odds other = (Odds) obj;
        if (!Objects.equals(this.match, other.match)) {
            return false;
        }
        if (!Objects.equals(this.tip, other.tip)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "odds";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public String getColumnsForInsert() {
        return "id, name, value";
    }

    @Override
    public String getValuesForInsert() {
        return match.getId()+", \'"+tip.getName()+"\', "+value;
    }

    @Override
    public String returnWhere() {
        return "id = " + match.getId() +" and name = \'" + tip.getName() +"\'";
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
