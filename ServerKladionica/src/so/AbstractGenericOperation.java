package so;

import domain.GeneralEntity;
import storage.IDatabaseBroker;
import storage.database.DatabaseBroker;
import storage.database.connection.DatabaseConnection;

public abstract class AbstractGenericOperation {

    protected IDatabaseBroker databaseBroker;

    public AbstractGenericOperation() {
        databaseBroker = new DatabaseBroker();
    }

    public final void templateExecute(Object entity, String keyword) throws Exception {
        try {
            validate(entity);
            try {
                startTransaction();
                execute(entity, keyword);
                commitTransaction();
            } catch (Exception ex) {
                rollbackTransaction();
                throw ex;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    protected abstract void validate(Object entity) throws Exception;
    
    protected abstract void execute(Object entity, String keyword) throws Exception;
    
    private void startTransaction() throws Exception {
        DatabaseConnection.getInstance().getConnection().setAutoCommit(false);
    }

    private void commitTransaction() throws Exception {
        DatabaseConnection.getInstance().getConnection().commit();
    }

    private void rollbackTransaction() throws Exception {
        DatabaseConnection.getInstance().getConnection().rollback();
    }

}
