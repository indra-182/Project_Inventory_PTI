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
import model.Model_Item;

/**
 *
 * @author rafii
 */
public class TabMod_Item extends AbstractTableModel{
    private List<Model_Item> list = new ArrayList();
    
    public void addData(Model_Item mi){
        list.add(mi);
        fireTableRowsInserted(list.size()-1,list.size()-1);
        JOptionPane.showMessageDialog(null,"Data has been Added");
    }
    public void updateData(int row, Model_Item mi){
        list.add(row ,mi);
        fireTableDataChanged();
        JOptionPane.showMessageDialog(null,"Data has been Updated");
    }
    public void deleteData(int index){
        list.remove(index);
        fireTableRowsDeleted(index,index);
        JOptionPane.showMessageDialog(null,"Data has been Deleted ");
    }
    public void clear(){
        list.clear();
        fireTableDataChanged();
    }
    
    public void setData(List<Model_Item>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Item mi){
        list.set(index,mi);
        fireTableRowsUpdated(index,index);
    }
    
    public Model_Item getData(int index){
        return list.get(index);
    }
    @Override
    public int getRowCount() {
        return list.size();
    }
    private final String[] colName={" No","  ID Items" ,"  Type" ,"  Item Name","  Distributor Name","  Unit","  Stock","  Price","  Brand"};
    @Override
    public int getColumnCount() {
        return colName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return " "+(rowIndex+1);
        }
        switch(columnIndex){
            case 1: return list.get(rowIndex).getId_items();
            case 2: return list.get(rowIndex).getMoty().getType_name();
            case 3: return list.get(rowIndex).getItems_name();
            case 4: return list.get(rowIndex).getModt().getDistributor_name();
            case 5: return list.get(rowIndex).getUnit();
            case 6: return list.get(rowIndex).getStock();
            case 7: return list.get(rowIndex).getPrice();
            case 8: return list.get(rowIndex).getBrand();
            
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
