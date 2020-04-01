/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package storage.database;

import domain.GeneralEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import storage.IDatabaseBroker;
import storage.database.connection.DatabaseConnection;

/**
 *
 * @author not-sure
 */
public class DatabaseBroker implements IDatabaseBroker {

    @Override
    public GeneralEntity getOne(GeneralEntity entity) throws SQLException {
        String query = "SELECT * FROM " + entity.getTableName() + " WHERE " + entity.returnWhere();
        System.out.println(query);
        Connection con = DatabaseConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        return entity.getOne(rs);
    }

    @Override
    public List<GeneralEntity> getAll(GeneralEntity entity) throws SQLException {
        List<GeneralEntity> list = new LinkedList<>();
        String query = "SELECT * FROM " + entity.getTableName();
        System.out.println(query);
        Connection con = DatabaseConnection.getInstance().getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        System.out.println(query);
        list = entity.getList(rs);

        return list;
    }

    @Override
    public int saveWithId(GeneralEntity object) throws SQLException {
        String query = "INSERT INTO " + object.getTableName() + "(" + object.getColumnsForInsert() + ") VALUES (" + object.getValuesForInsert() + ") returning id";
        System.out.println(query);

        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        int id = rs.getInt(1);
        System.out.println("ID: " + id);
        return id;
    }

    @Override
    public void save(GeneralEntity object) throws SQLException {
        String query = "INSERT INTO " + object.getTableName() + "(" + object.getColumnsForInsert() + ") VALUES (" + object.getValuesForInsert() + ")";
        System.out.println(query);

        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement st = connection.createStatement();
        st.executeUpdate(query);
    }

    @Override
    public void update(GeneralEntity object) throws SQLException {
        String query = "UPDATE " + object.getTableName() + " SET " + object.returnSet() + " WHERE " + object.returnWhere();
        System.out.println(query);

        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement st = connection.createStatement();
        st.executeUpdate(query);
    }

    @Override
    public void delete(GeneralEntity object) throws SQLException {
        String query = "DELETE FROM " + object.getTableName() + " WHERE " + object.returnWhere();
        System.out.println(query);

        Connection connection = DatabaseConnection.getInstance().getConnection();
        Statement st = connection.createStatement();
        st.executeUpdate(query);
    }
}
