/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.ticket;

import domain.GeneralEntity;
import domain.Has;
import domain.Ticket;
import java.util.Date;
import java.util.List;
import so.AbstractGenericOperation;

/**
 *
 * @author not-sure
 */
public class SaveTicketSO extends AbstractGenericOperation {

    private int id;

    @Override
    protected void validate(Object entity) throws Exception {
        Object[] arr = (Object[]) entity;
        if (!(arr[0] instanceof Ticket)) {
            throw new Exception("Invalid object!");
        }
        List<Object> list = (List<Object>) arr[1];
        for (Object object : list) {
            if (!(object instanceof Has)) {
                throw new Exception("Invalid entity parameter!");
            }
        }
    }

    @Override
    protected void execute(Object entity, String keyword) throws Exception {
        Object[] arr = (Object[]) entity;
        Ticket ticket = (Ticket) arr[0];
        List<Has> list = (List<Has>) arr[1];
        id = databaseBroker.saveWithId(ticket);
        for (Has h : list) {
            h.getTicket().setId(id);
            if (!h.getOdds().getMatch().getStartTime().after(new Date())) {
                throw new Exception("Ticket is not saved, Match " + h.getOdds().getMatch().getId() + " already begin");
            }
            databaseBroker.save(h);
        }
    }

    public int getID() {
        return id;
    }

}
