/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage;

import domain.GeneralEntity;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author not-sure
 */
public interface IDatabaseBroker {
    public GeneralEntity getOne(GeneralEntity entity) throws SQLException;
    public List<GeneralEntity> getAll(GeneralEntity entity) throws SQLException;
    public int saveWithId(GeneralEntity object) throws SQLException;
    public void save(GeneralEntity object) throws SQLException;
    public void update(GeneralEntity object) throws SQLException;
    public void delete(GeneralEntity object) throws SQLException;

}
