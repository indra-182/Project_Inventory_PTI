/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Model_Reportoutgoing;

/**
 *
 * @author rafii
 */
public class TabMod_Reportoutgoing extends AbstractTableModel {
private List<Model_Reportoutgoing> list = new ArrayList();

        public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    public void setData(List<Model_Reportoutgoing>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Reportoutgoing mv){
        list.set(index,mv);
        fireTableRowsUpdated(index,index);
    }
    
    
    public Model_Reportoutgoing getData(int index){
        return list.get(index);
    }
    @Override
    public int getRowCount() {
        return list.size();
    }
    private final String[] colName={"  Id Outgoing","  Project","  Transaction Date", "Items","Quantity","Pic"};
    @Override
    public int getColumnCount() {
        return colName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return list.get(rowIndex).getId_outgoing();
            case 1: return list.get(rowIndex).getProject_name();
            case 2: return list.get(rowIndex).getTransaction_date();

            case 3: return list.get(rowIndex).getItems_name();
            
            case 4: return list.get(rowIndex).getQuantity();
            
                        case 5: return list.get(rowIndex).getPic();
            default:return null;
        }
    }
     public String getColumnName(int column){
        if(column ==0){
            return "  "+colName[column];
        }else{
            return colName[column];
        } 
    }
}
