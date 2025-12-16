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
import model.Model_Project;

/**
 *
 * @author rafii
 */
public class TabMod_Project extends AbstractTableModel {
    private List<Model_Project> list = new ArrayList();
public void addData(Model_Project mp){
        list.add(mp);
        fireTableRowsInserted(list.size()-1,list.size()-1);
        JOptionPane.showMessageDialog(null,"Data has been Added");
    }
    public void updateData(int row, Model_Project mp){
        list.add(row ,mp);
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
    
    public void setData(List<Model_Project>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Project mp){
        list.set(index,mp);
        fireTableRowsUpdated(index,index);
    }
    
    public Model_Project getData(int index){
        return list.get(index);
    }
    @Override
    public int getRowCount() {
        return list.size();
    }
    private final String[] colName={" No","  Project Id","  Project Manager","  Project Name ","  Project Site","  Vendor Name","  Status"};
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
            case 1: return list.get(rowIndex).getId_project();
            case 2: return list.get(rowIndex).getProject_manager();
            case 3: return list.get(rowIndex).getProject_name();
            case 4: return list.get(rowIndex).getProject_site();
            case 5: return list.get(rowIndex).getMovr().getVendor_name();
            case 6: return list.get(rowIndex).getStatus();
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
