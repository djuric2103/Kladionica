/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author not-sure
 */
public interface GeneralEntity extends Serializable{
    public String getTableName();
    
    public List<GeneralEntity> getList(ResultSet rs)  throws SQLException;
    
    public GeneralEntity getOne(ResultSet rs) throws SQLException;
    
    public String getColumnsForInsert();
    
    public String getValuesForInsert();
    
    public String returnWhere();
    
    public String returnSet();
}
