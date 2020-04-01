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
public class User implements GeneralEntity{
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    public User() {
    }

    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    
    @Override
    public String getTableName() {
        return "users";
    }

    @Override
    public List<GeneralEntity> getList(ResultSet rs) throws SQLException{
        List<GeneralEntity> list = new LinkedList<>();
        while(rs.next()){
            list.add(new User(rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname")));
        }
        
        return list;
    }
    
    @Override
    public String getColumnsForInsert() {
        return "username, password, firstname, lastname";
    }

    @Override
    public String getValuesForInsert() {
        return "\'"+getUsername()+"\', " + "\'"+getPassword()+"\'" +"\'"+getFirstname()+"\', " +"\'"+getLastname()+"\'";
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.username);
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
        final User other = (User) obj;
        return true;
    }

    @Override
    public String returnWhere() {
        return "username = \'"+username+"\'";
    }

    @Override
    public String returnSet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GeneralEntity getOne(ResultSet rs) throws SQLException {
        User user = null;
        while(rs.next()){
            user = new User(rs.getString("username"), rs.getString("password"), rs.getString("firstname"), rs.getString("lastname"));
        }
        return user;
    }
}
