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
import model.Model_Vendor;

/**
 *
 * @author rafii
 */
public class TabMod_Vendor extends AbstractTableModel {
    private List<Model_Vendor> list = new ArrayList();
public void addData(Model_Vendor mv){
        list.add(mv);
        fireTableRowsInserted(list.size()-1,list.size()-1);
        JOptionPane.showMessageDialog(null,"Data has been Added");
    }
    public void updateData(int row, Model_Vendor mv){
        list.add(row ,mv);
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
    
    public void setData(List<Model_Vendor>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Vendor mv){
        list.set(index,mv);
        fireTableRowsUpdated(index,index);
    }
    
    public Model_Vendor getData(int index){
        return list.get(index);
    }
    @Override
    public int getRowCount() {
        return list.size();
    }
    private final String[] colName={" No","  Vendor Id","  Vendor Name","  Location","  No Telp","  Pic Vendor"};
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
            case 1: return list.get(rowIndex).getId_vendor();
            case 2: return list.get(rowIndex).getVendor_name();
            case 3: return list.get(rowIndex).getLocation();
            case 4: return list.get(rowIndex).getNo_telp();
            case 5: return list.get(rowIndex).getPic_vendor();
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
