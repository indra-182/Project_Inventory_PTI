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
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import model.Model_Cart_Incoming;
import model.Model_Cart_Outgoing;
import model.Model_Detail_Incoming;
import model.Model_Detail_Outgoing;
import model.Model_Distributor;
import model.Model_Incoming;
import model.Model_Item;
import model.Model_Outgoing;
import model.Model_Project;
import model.Model_Type;
import service.Service_Detail_Incoming;
import service.Service_Detail_Outgoing;
import service.Service_Incoming;

/**
 *
 * @author rafii
 */
public class DAO_Detail_Outgoing implements Service_Detail_Outgoing{
    private Connection conn;

    public DAO_Detail_Outgoing() {
        conn = Koneksi.getConnection();
    }

    @Override
    public void addData(Model_Cart_Outgoing mdl_in) {
        {
        PreparedStatement st = null;
        String sql = "INSERT INTO tx_detail_outgoing (id_outgoing,id_project,id_items,quantity,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?,?,?) ";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_in.getModo().getMoou().getId_outgoing());
            st.setString(2,mdl_in.getMopr().getId_project());
            st.setString(3,mdl_in.getMoit().getId_items());
            st.setString(4,mdl_in.getModo().getQuantity());
            st.setString(5,"1");
            st.setString(6,mdl_in.getModo().getCreated_by());
            st.setString(7,mdl_in.getModo().getCreated_date());
            st.setString(8,mdl_in.getModo().getUpdated_by());
            st.setString(9,mdl_in.getModo().getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Add data");
            java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Project.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }
    }

    @Override
    public void sumTotal(Model_Detail_Outgoing mdl_det_in) {
        PreparedStatement st = null;
        ResultSet rs= null;
        String sql = "SELECT SUM(total_price) from tx_cart_incoming";
        try{
            st=conn.prepareStatement(sql);
            rs=st.executeQuery();
            if(rs.next()){
//                mdl_det_in.setTotal_price(rs.getString(1));
            }
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Incoming.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Incoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Incoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public void delCart(Model_Detail_Outgoing mdl_det_in) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Model_Detail_Outgoing> getData(String id) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT i.id_outgoing,di.id_items,d.project_name,t.type_name,it.items_name,it.unit,it.brand,di.quantity FROM tx_detail_outgoing di JOIN tx_outgoing i ON di.id_outgoing = i.id_outgoing JOIN mst_item it ON it.id_items = di.id_items JOIN mst_type t ON t.id_type = it.id_type JOIN mst_project d ON i.id_project=d.id_project where i.sts_active='1' AND di.id_outgoing='"+id+"';";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Detail_Outgoing mdi = new Model_Detail_Outgoing();
                Model_Outgoing min = new Model_Outgoing();
                Model_Project md = new Model_Project();
                Model_Item mi = new Model_Item();
                Model_Type mt = new Model_Type();
                min.setId_outgoing(rs.getString("id_outgoing"));
                mi.setId_items(rs.getString("id_items"));
                md.setProject_name(rs.getString("project_name"));
                mi.setItems_name(rs.getString("items_name"));
                mi.setBrand(rs.getString("brand"));
                mt.setType_name(rs.getString("type_name"));
                mi.setUnit(rs.getString("unit"));
                mdi.setQuantity(rs.getString("quantity"));
                mdi.setMoou(min);
                mdi.setMoit(mi);
                mdi.setMopr(md);
                mdi.setMoty(mt);
                
                list.add(mdi);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Outgoing.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Outgoing.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Outgoing.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }

    @Override
    public List<Model_Detail_Outgoing> getData2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String nomor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String nomor2() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void useStock(Model_Detail_Outgoing mdl_det) {
        PreparedStatement st = null;            
        String sql = "Update mst_item set stock=stock-? where id_items='"+mdl_det.getMoit().getId_items()+"'";
        try{
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_det.getQuantity());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Update data");
            java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }
}
