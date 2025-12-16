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
import model.Model_Detail_Incoming;

/**
 *
 * @author rafii
 */
public class TabMod_Detail_Incoming extends AbstractTableModel {
private List<Model_Detail_Incoming> list = new ArrayList();
public void addData(Model_Detail_Incoming mdi){
        list.add(mdi);
        fireTableRowsInserted(list.size()-1,list.size()-1);
        JOptionPane.showMessageDialog(null,"Data has been Added");
    }
    public void updateData(int row, Model_Detail_Incoming mdi){
        list.add(row ,mdi);
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
    
    public void setData(List<Model_Detail_Incoming>list){
        clear();
        this.list.addAll(list);
        fireTableDataChanged();
    }
    
    public void setData(int index, Model_Detail_Incoming mdi){
        list.set(index,mdi);
        fireTableRowsUpdated(index,index);
    }
    
    public Model_Detail_Incoming getData(int index){
        return list.get(index);
    }
    @Override
    public int getRowCount() {
        return list.size();
    }
    private final String[] colName={" No Incoming","  ID Items","  Distributor Name","  Type","  Item Name","  Quantity","  Unit","  Price","  Brand","  Subtotal"};
    @Override
    public int getColumnCount() {
        return colName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return list.get(rowIndex).getMoin().getId_incoming();
            case 1: return list.get(rowIndex).getMoit().getId_items();
            case 2: return list.get(rowIndex).getModt().getDistributor_name();
            case 3: return list.get(rowIndex).getMoty().getType_name();
            case 4: return list.get(rowIndex).getMoit().getItems_name();
            case 5: return list.get(rowIndex).getQuantity();
            case 6: return list.get(rowIndex).getMoit().getUnit();
            case 7: return list.get(rowIndex).getMoit().getPrice();
            case 8: return list.get(rowIndex).getMoit().getBrand();
            case 9: return list.get(rowIndex).getMoin().getTotal_price();
            default:return null;
        }
    }
    public String getColumnName(int column){
        return colName[column];    
    }
}
