/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.ticket.model;

import convert.Convert;
import domain.Odds;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author not-sure
 */
public class TableModelTicket extends AbstractTableModel{   
    private String[] header = new String[]{"ID", "Home", "Away", "Start time", "Tip", "Odds", "Final score"};
    private List<Odds> list;

    public TableModelTicket() {
        list = new LinkedList<>();
    }

    public TableModelTicket(List<Odds> list) {
        this.list = list;
    }
    
    public void setList(List<Odds> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public List<Odds> getList(){
        return list;
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
        Odds o = list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return o.getMatch().getId();
            case 1:
                return o.getMatch().getHome().getName();
            case 2:
                return o.getMatch().getAway().getName();
            case 3:
                return Convert.date2StringNice(o.getMatch().getStartTime());
            case 4:
                return o.getTip().getName();
            case 5:
                return o.getValue();
            case 6:
                if(o.getMatch().getScoreInserted()){
                    return o.getMatch().getGoalsHome()+" - "+o.getMatch().getGoalsAway();
                }
                return "/";
        }
        return "N/A";
    }

    @Override
    public String getColumnName(int column) {
        return header[column];
    }
    
    public void deleteOdds(int row){
        list.remove(row);
        fireTableDataChanged();
    }
    
    public void addOdds(Odds o){
        list.add(o);
        fireTableDataChanged();
    }
    
    public void replaceOdds(int row, Odds o){
        list.set(row, o);
        fireTableDataChanged();
    }   
}
