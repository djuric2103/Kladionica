/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author not-sure
 */
public class Tip implements GeneralEntity{
    private String name;

    public Tip() {
    }

    public Tip(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
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
        final Tip other = (Tip) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "tip";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        List<GeneralEntity> list = new LinkedList<>();
        while(rs.next()){
            list.add(new Tip(rs.getString("name")));
        }
        return list;
    }

    @Override
    public String getColumnsForInsert() {
        return "name";
    }

    @Override
    public String getValuesForInsert() {
        return "\'"+name+"\'";
    }

    @Override
    public String returnWhere() {
        return "name = \'"+name+"\'";
    }

    @Override
    public String returnSet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralEntity getOne(ResultSet rs) throws SQLException {
        Tip tip = null;
        while(rs.next()){
            tip = new Tip(rs.getString("name"));
        }
        return tip;
    }
}
