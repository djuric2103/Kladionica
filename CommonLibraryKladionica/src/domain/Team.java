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
public class Team implements GeneralEntity{
    private String id;
    private String name;

    public Team() {
    }

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getTableName() {
        return "team";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException {
        List<GeneralEntity> list = new LinkedList<>();
        while(rs.next()){
            list.add(new Team(rs.getString("id"), rs.getString("name")));
        }
        
        return list;
    }

    @Override
    public String getColumnsForInsert() {
        return "id, name"; 
    }

    @Override
    public String getValuesForInsert() {
        return "\'"+id+"\', \'"+name+"\'";
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        return id.equals(((Team)obj).getId());
    }

    @Override
    public String returnWhere() {
        return "id = \'" + id + "\'";
    }

    @Override
    public String returnSet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralEntity getOne(ResultSet rs) throws SQLException {
        Team t = new Team();
        while(rs.next()){
            t = new Team(rs.getString("id"), rs.getString("name"));
        }
        return t;
    }
}
