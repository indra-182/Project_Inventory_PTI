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
import model.Model_Distributor;

/**
 *
 * @author rafii
 */
public class TabMod_Distributor extends AbstractTableModel {
    private List<Model_Distributor> list = new ArrayList();
public void addData(Model_Distributor md){
        list.add(md);
        fireTableRowsInserted(list.size()-1,list.size()-1);
        JOptionPane.showMessageDialog(null,"Data has been Added");
    }
    public void updateData(int row, Model_Distributor md){
        list.add(row ,md);
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
    
    public void setData(List<Model_Distributor>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Distributor md){
        list.set(index,md);
        fireTableRowsUpdated(index,index);
    }
    
    public Model_Distributor getData(int index){
        return list.get(index);
    }
    @Override
    public int getRowCount() {
        return list.size();
    }
    private final String[] colName={" No","  Distributor Id","  Distributor Name","  Location","  No Telp","  Pic Distributor"};
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
            case 1: return list.get(rowIndex).getId_distributor();
            case 2: return list.get(rowIndex).getDistributor_name();
            case 3: return list.get(rowIndex).getLocation();
            case 4: return list.get(rowIndex).getNo_telp();
            case 5: return list.get(rowIndex).getPic_distributor();
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
