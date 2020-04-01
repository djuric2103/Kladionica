/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.businessresults.model;

import convert.Convert;
import domain.Ticket;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author not-sure
 */
public class TableModelTickets extends AbstractTableModel{
    String[] header = {"ID", "Paid", "Total odds", "Possible win", "Time of payment", "Status", "User"};
    List<Ticket> list;

    public TableModelTickets() {
        list = new LinkedList<>();
    }

    public void setList(List<Ticket> list) {
        this.list = list;
        list.sort((a, b) -> ((Ticket)a).getId() - ((Ticket)b).getId());
        fireTableDataChanged();
    }
    
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ticket ticket = list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return ticket.getId();
            case 1:
                return ticket.getPaid();
            case 2:
                return ticket.getTotalodds();
            case 3:
                return ticket.getWin();
            case 4:
                return Convert.date2StringNice(ticket.getTimeOfPayment());
            case 5:
                return ticket.getStatus();
            case 6:
                return ticket.getUser();
        }
        return "N/A";
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
    
    
}
