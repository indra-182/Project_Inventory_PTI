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
import model.Model_UserGroup;

/**
 *
 * @author adam
 */
public class TabMod_UserGroup extends AbstractTableModel {
    private List<Model_UserGroup> list = new ArrayList();
public void addData(Model_UserGroup mk){
        list.add(mk);
        fireTableRowsInserted(list.size()-1,list.size()-1);
        JOptionPane.showMessageDialog(null,"Data has been Added");
    }
    public void updateData(int row, Model_UserGroup mk){
        list.add(row ,mk);
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
    
    public void setData(List<Model_UserGroup>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_UserGroup mk){
        list.set(index,mk);
        fireTableRowsUpdated(index,index);
    }
    
    public Model_UserGroup getData(int index){
        return list.get(index);
    }
    @Override
    public int getRowCount() {
        return list.size();
    }
    private final String[] colName={" No","  Username ","  Nama Group" , " Nama Menu" , " Akses View" , " Akses Add" , " Akses Edit" , " Akses Delete"};
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
            case 1: return list.get(rowIndex).getId_user();
            case 2: return list.get(rowIndex).getGroup_name();
            case 3: return list.get(rowIndex).getName_menu();
            case 4: return list.get(rowIndex).getView_akses();
            case 5: return list.get(rowIndex).getAdd_akses();
            case 6: return list.get(rowIndex).getEdit_akses();
            case 7: return list.get(rowIndex).getDelete_akses();
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
