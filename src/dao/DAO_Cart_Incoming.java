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
import model.Model_Detail_Incoming;
import model.Model_Distributor;
import model.Model_Incoming;
import model.Model_Item;
import model.Model_Type;
import service.Service_Cart_Incoming;
import service.Service_Detail_Incoming;
import service.Service_Incoming;

/**
 *
 * @author rafii
 */
public class DAO_Cart_Incoming implements Service_Cart_Incoming{
    private Connection conn;

    public DAO_Cart_Incoming() {
        conn = Koneksi.getConnection();
    }

    @Override
    public void addData(Model_Cart_Incoming mdl_cart_in) {
        {
        PreparedStatement st = null;
        
        String sql = "INSERT INTO `tx_cart_incoming` (`id_incoming`, `id_distributor`, `id_items`, `quantity`, `price`, `total_price`, `unit`, `type`, `brand`) VALUES (?,?,?,?,?,?,?,?,?)";
        try{    
            st = conn.prepareStatement(sql);
            
            st.setString(1,mdl_cart_in.getModi().getMoin().getId_incoming());
            st.setString(2,mdl_cart_in.getModt().getId_distributor());
            st.setString(3,mdl_cart_in.getMoit().getId_items());
            st.setString(4,mdl_cart_in.getModi().getQuantity());
            st.setString(5,mdl_cart_in.getMoit().getPrice());
            st.setString(6,mdl_cart_in.getModi().getTotal_price());
            st.setString(7,mdl_cart_in.getMoit().getUnit());
            st.setString(8,mdl_cart_in.getMoty().getType_name());
            st.setString(9,mdl_cart_in.getMoit().getBrand());
            st.executeUpdate(); 
                        JOptionPane.showMessageDialog(null, "Data has been added successfully.");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Add to Cart");
            java.util.logging.Logger.getLogger(DAO_Cart_Incoming.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Cart_Incoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }
    }

    @Override
    public void sumTotal(Model_Cart_Incoming mdl_cart_in) {
        
    }

    @Override
    public void delCart() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql ="DELETE FROM `tx_cart_incoming`";
        try{    
            st = conn.prepareStatement(sql);
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Update Cart");
            java.util.logging.Logger.getLogger(DAO_Cart_Incoming.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Cart_Incoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }   
    }

    @Override
    public List<Model_Cart_Incoming> getData()  {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT ci.id_incoming, ci.id_distributor ,d.distributor_name, ci.id_items, ci.type, it.items_name,ci.quantity,ci.unit,ci.price, ci.brand,ci.total_price FROM tx_cart_incoming ci JOIN mst_distributor d ON ci.id_distributor = d.id_distributor JOIN mst_item it ON ci.id_items=it.id_items;";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
            Model_Cart_Incoming mci = new Model_Cart_Incoming();
            Model_Incoming min = new Model_Incoming();
            Model_Detail_Incoming mdi = new Model_Detail_Incoming();
            Model_Item mi = new Model_Item();
            Model_Distributor md = new Model_Distributor();
            Model_Type mt = new Model_Type();
            min.setId_incoming(rs.getString("id_incoming"));
            mdi.setMoin(min);
            md.setId_distributor(rs.getString("id_distributor"));
            md.setDistributor_name(rs.getString("distributor_name"));
            mi.setId_items(rs.getString("id_items"));
            mi.setItems_name(rs.getString("items_name"));
            mt.setType_name(rs.getString("type"));
            mdi.setQuantity(rs.getString("quantity"));
            mi.setUnit(rs.getString("unit"));
            mi.setPrice(rs.getString("price"));
            mdi.setTotal_price(rs.getString("total_price"));
            mi.setBrand(rs.getString("brand"));
            mci.setModi(mdi);
            mci.setModt(md);
            mci.setMoit(mi);
            mci.setMoty(mt);
            list.add(mci);

            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Cart_Incoming.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Cart_Incoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Cart_Incoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }

    @Override
    public List<Model_Detail_Incoming> getData2() {
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
    public void updCart(Model_Cart_Incoming mdl_cart_in) {
            PreparedStatement st = null;
            String sql ="update tx_cart_incoming set id_incoming=?, id_distributor=?, id_items=?, quantity=?, price=?, total_price=?, unit=?, type=?, brand=? where id_items='"+mdl_cart_in.getMoit().getId_items()+"'";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_cart_in.getModi().getMoin().getId_incoming());
            st.setString(2,mdl_cart_in.getModt().getId_distributor());
            st.setString(3,mdl_cart_in.getMoit().getId_items());
            st.setString(4,mdl_cart_in.getModi().getQuantity());
            st.setString(5,mdl_cart_in.getMoit().getPrice());
            st.setString(6,mdl_cart_in.getModi().getTotal_price());
            st.setString(7,mdl_cart_in.getMoit().getUnit());
            st.setString(8,mdl_cart_in.getMoty().getType_name());
            st.setString(9,mdl_cart_in.getMoit().getBrand());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Update Cart");
            java.util.logging.Logger.getLogger(DAO_Cart_Incoming.class.getName()).log(Level.SEVERE,null,ex);
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Cart_Incoming.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }        
    }

    @Override
    public void deleteData(String id) {
        PreparedStatement st= null;
        String sql="DELETE FROM tx_cart_incoming WHERE id_items ='"+id+"'";
        try{
            st = conn.prepareStatement(sql);
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
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

