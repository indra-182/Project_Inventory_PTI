/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import model.Model_Distributor;
import model.Model_Item;
import model.Model_Project;
import model.Model_Reportoutgoing;
import model.Model_Type;
import model.Model_Vendor;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import service.Service_Reportitems;
import service.Service_Reportoutgoing;
import service.Service_Reportproject;

/**
 *
 * @author rafii
 */
public class DAO_Reportitems implements Service_Reportitems{
    private Connection conn;

    public DAO_Reportitems() {
        conn = Koneksi.getConnection();
    }
    @Override
    public List<Model_Item> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "select * from v_items";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Item mi = new Model_Item();
                Model_Distributor md = new Model_Distributor();
                Model_Type mt = new Model_Type();
                mi.setId_items(rs.getString("id_items"));
                mt.setType_name(rs.getString("type_name"));
                mi.setItems_name(rs.getString("item_name"));
                md.setDistributor_name(rs.getString("distributor_name"));
                                              mi.setUnit(rs.getString("unit"));
                                              mi.setBrand(rs.getString("brand"));
                                              mi.setStock(rs.getString("stock"));
                                              mi.setPrice(rs.getString("price"));
                              mi.setMoty(mt);
                              mi.setModt(md);
                
                
                list.add(mi);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Reportincoming.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Reportincoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Reportincoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }
    @Override
    public List<Model_Item> getData(String str) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "select * from v_items where type_name='"+str+"'";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
               Model_Item mi = new Model_Item();
                Model_Distributor md = new Model_Distributor();
                Model_Type mt = new Model_Type();
                mi.setId_items(rs.getString("id_items"));
                mt.setType_name(rs.getString("type_name"));
                mi.setItems_name(rs.getString("item_name"));
                md.setDistributor_name(rs.getString("distributor_name"));
                                              mi.setUnit(rs.getString("unit"));
                                              mi.setBrand(rs.getString("brand"));
                                              mi.setStock(rs.getString("stock"));
                                              mi.setPrice(rs.getString("price"));
                              mi.setMoty(mt);
                              mi.setModt(md);
                
                
                list.add(mi);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Reportincoming.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Reportincoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Reportincoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }
    @Override
    public String nomor() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String nomor2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void Report(String user,String type) {
       try{
//            System.out.println(status);
            String file = "src/report/Report_Item.jasper";
            Map<String, Object> param = new HashMap<>();
            param.put("user", user); // Ini nama param di Jasper
            param.put("type", type); // Ini nama param di Jasper
            JasperPrint print = JasperFillManager.fillReport(file, param,conn);
            JasperViewer.viewReport(print,false);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }
public void Report(String user) {
       try{
           
            String file = "src/report/Report_Item.jasper";
            Map<String, Object> param = new HashMap<>();
            param.put("user", user); // Ini nama param di Jasper
            JasperPrint print = JasperFillManager.fillReport(file, param,conn);
            JasperViewer.viewReport(print,false);
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }


}






