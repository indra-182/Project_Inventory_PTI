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
import model.Model_Incoming;

/**
 *
 * @author rafii
 */
public class TabMod_Incoming extends AbstractTableModel {
private List<Model_Incoming> list = new ArrayList();
public void addData(Model_Incoming mi){
        list.add(mi);
        fireTableRowsInserted(list.size()-1,list.size()-1);
        JOptionPane.showMessageDialog(null,"Data has been Added");
    }
    public void updateData(int row, Model_Incoming mi){
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
    
    public void setData(List<Model_Incoming>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Incoming mi){
        list.set(index,mi);
        fireTableRowsUpdated(index,index);
    }
    
    public Model_Incoming getData(int index){
        return list.get(index);
    }
    @Override
    public int getRowCount() {
        return list.size();
    }
    private final String[] colName={" No Incoming","  Tanggal","  Total Price","  ID Distributor","  Nama User"};
    @Override
    public int getColumnCount() {
        return colName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return list.get(rowIndex).getId_incoming();
            case 1: return list.get(rowIndex).getTransaction_date();
            case 2: return list.get(rowIndex).getTotal_price();
            case 3: return list.get(rowIndex).getModr().getId_distributor();
            case 4: return list.get(rowIndex).getMokr().getNama_lengkap();
            default:return null;
        }
    }
    public String getColumnName(int column){
        return colName[column];    
    }
}
