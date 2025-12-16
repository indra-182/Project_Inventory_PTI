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
import model.Model_Reportincoming;

/**
 *
 * @author rafii
 */
public class TabMod_Reportincoming extends AbstractTableModel {
private List<Model_Reportincoming> list = new ArrayList();

        public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    public void setData(List<Model_Reportincoming>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Reportincoming mv){
        list.set(index,mv);
        fireTableRowsUpdated(index,index);
    }
    
    
    public Model_Reportincoming getData(int index){
        return list.get(index);
    }
    @Override
    public int getRowCount() {
        return list.size();
    }
    private final String[] colName={"  Id Incoming","  Distributor","  Transaction Date", "Items","Price","Quantity","Total","Pic"};
    @Override
    public int getColumnCount() {
        return colName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return list.get(rowIndex).getId_incoming();
//            case 1: return list.get(rowIndex).getId_distributor();
            case 1: return list.get(rowIndex).getDistributor_name();
            case 2: return list.get(rowIndex).getTransaction_date();
//            case 4: return list.get(rowIndex).getId_items();
            case 3: return list.get(rowIndex).getItems_name();
            case 4: return list.get(rowIndex).getSubtotal();
            case 5: return list.get(rowIndex).getQuantity();
            case 6: return list.get(rowIndex).getTotal();
                        case 7: return list.get(rowIndex).getPic();
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
