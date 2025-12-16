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
import model.Model_Distributor;
import model.Model_Type;
import model.Model_Item;
import service.Service_Item;

/**
 *
 * @author rafii
 */
public class DAO_Item implements Service_Item{
    private Connection conn;
    public DAO_Item(){
        conn = (Connection) Koneksi.getConnection();
    }

    @Override
    public void addData(Model_Item mdl_Item) {
        PreparedStatement st = null;
        String sql = "INSERT INTO mst_item (id_items,id_distributor,id_type,items_name,brand,unit,stock,price,sts_active,created_by,created_date,updated_by,updated_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        try{    
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_Item.getId_items());
            st.setString(2,mdl_Item.getModt().getId_distributor());
            st.setString(3,mdl_Item.getMoty().getId_type());
            st.setString(4,mdl_Item.getItems_name());
            st.setString(5,mdl_Item.getBrand());
            st.setString(6,mdl_Item.getUnit());
            st.setString(7,mdl_Item.getStock());
            st.setString(8,mdl_Item.getPrice());
            st.setString(9,"1");
            st.setString(10,mdl_Item.getCreated_by());
            st.setString(11,mdl_Item.getCreated_date());
            st.setString(12,mdl_Item.getUpdated_by());
            st.setString(13,mdl_Item.getUpdated_date());
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Add data");
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

    @Override
    public void updateData(Model_Item mdl_Item) {
        PreparedStatement st = null;
        String sql = "Update mst_item set items_name=?,brand=?,unit=?,stock=?,price=?,updated_by=?,updated_date=? where id_items='"+mdl_Item.getId_items()+"'";
        try{
            st = conn.prepareStatement(sql);
            st.setString(1,mdl_Item.getItems_name());
            st.setString(2,mdl_Item.getBrand());
            st.setString(3,mdl_Item.getUnit());
            st.setString(4,mdl_Item.getStock());
            st.setString(5,mdl_Item.getPrice());
            st.setString(6,mdl_Item.getUpdated_by());
            st.setString(7,mdl_Item.getUpdated_date());
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

    @Override
    public void deleteData(Model_Item mdl_item) {
        PreparedStatement st= null;
        String sql="Update mst_item set sts_active=? where id_items='"+mdl_item.getId_items()+"'";
        try{
            st = conn.prepareStatement(sql);
            st.setString(1,"0");
            
            st.executeUpdate(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Failed to Delete data");
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

    @Override
    public List<Model_Item> getData() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT i.id_items,t.type_name,i.items_name,d.distributor_name,i.brand,i.unit,i.stock,i.price "
                + "FROM mst_item i JOIN mst_type t ON i.id_type = t.id_type JOIN mst_distributor d ON i.id_distributor = d.id_distributor where i.sts_active='1';";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Item mi = new Model_Item();
                Model_Type mt = new Model_Type();
                Model_Distributor md = new Model_Distributor();
                mt.setType_name(rs.getString("type_name"));
                mi.setMoty(mt);
                mi.setItems_name(rs.getString("items_name"));
                mi.setId_items(rs.getString("id_items"));
                md.setDistributor_name(rs.getString("distributor_name"));
                mi.setModt(md);
                mi.setPrice(rs.getString("price"));
                mi.setBrand(rs.getString("brand"));
                mi.setUnit(rs.getString("unit"));
                mi.setStock(rs.getString("stock"));
                list.add(mi);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }

    @Override
    public List<Model_Item> getData2(String idDist) {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT i.id_items, t.type_name,i.items_name,d.distributor_name,i.brand,i.unit,i.stock,i.price FROM mst_item i JOIN mst_type t ON i.id_type = t.id_type JOIN mst_distributor d ON i.id_distributor = d.id_distributor where i.id_distributor='"+idDist+"' AND i.sts_active='1';";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Item mi = new Model_Item();
                Model_Type mt = new Model_Type();
                Model_Distributor md = new Model_Distributor();
                mi.setId_items(rs.getString("id_items"));
                mt.setType_name(rs.getString("type_name"));
                mi.setMoty(mt);
                mi.setItems_name(rs.getString("items_name"));
                md.setDistributor_name(rs.getString("distributor_name"));
                mi.setModt(md);
                mi.setPrice(rs.getString("price"));
                mi.setBrand(rs.getString("brand"));
                mi.setUnit(rs.getString("unit"));
                mi.setStock(rs.getString("stock"));
                list.add(mi);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }

    @Override
    public List<Model_Item> getData3() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT i.id_items, t.type_name,i.items_name,d.distributor_name,i.brand,i.unit,i.stock,i.price FROM mst_item i JOIN mst_type t ON i.id_type = t.id_type JOIN mst_distributor d ON i.id_distributor = d.id_distributor where i.sts_active='1';";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Item mi = new Model_Item();
                Model_Type mt = new Model_Type();
                Model_Distributor md = new Model_Distributor();
                mi.setId_items(rs.getString("id_items"));
                mt.setType_name(rs.getString("type_name"));
                mi.setMoty(mt);
                mi.setItems_name(rs.getString("items_name"));
                md.setDistributor_name(rs.getString("distributor_name"));
                mi.setModt(md);
                mi.setPrice(rs.getString("price"));
                mi.setBrand(rs.getString("brand"));
                mi.setUnit(rs.getString("unit"));
                mi.setStock(rs.getString("stock"));
                list.add(mi);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
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
    public List<Model_Item> search(String id,String str) {
        
     {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;     
        String sql = "SELECT i.id_items, t.type_name,i.items_name,i.brand,i.unit,i.stock,i.price FROM mst_item i JOIN mst_type t ON i.id_type = t.id_type JOIN mst_distributor d ON i.id_distributor = d.id_distributor where i.id_distributor='"+id+"' AND i.sts_active='1' AND (i.id_items LIKE '%"+str+"%' OR i.items_name LIKE '%"+str+"%')";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            System.out.println(str);
            while(rs.next()){
                Model_Item mi = new Model_Item();
                Model_Type mt = new Model_Type();
                mi.setId_items(rs.getString("id_items"));
                mt.setType_name(rs.getString("type_name"));
                mi.setMoty(mt);
                mi.setItems_name(rs.getString("items_name"));
                mi.setPrice(rs.getString("price"));
                mi.setBrand(rs.getString("brand"));
                mi.setUnit(rs.getString("unit"));
                mi.setStock(rs.getString("stock"));
                list.add(mi);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }
    }

    @Override
    public List<Model_Item> getDataDistributor() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT id_distributor,distributor_name FROM mst_distributor where sts_active='1';";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Item mi = new Model_Item();
                Model_Distributor md = new Model_Distributor();
                md.setId_distributor(rs.getString("id_distributor"));
                md.setDistributor_name(rs.getString("distributor_name"));
                mi.setModt(md);
                list.add(mi);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }


    @Override
    public List<Model_Item> getDataType() {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;
        String sql = "SELECT id_type,type_name FROM mst_type where sts_active='1';";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            while(rs.next()){
                Model_Item mi = new Model_Item();
                Model_Type mt = new Model_Type();
                mt.setId_type(rs.getString("id_type"));
                mt.setType_name(rs.getString("type_name"));
                mi.setMoty(mt);
                list.add(mi);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Item.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
        
    }

    @Override
    public List<Model_Item> search(String str){
        
     {
        PreparedStatement st = null;
        List list = new ArrayList();
        ResultSet rs=null;     
        String sql = "SELECT i.id_items, t.type_name,i.items_name,i.brand,i.unit,i.stock,i.price FROM mst_item i JOIN mst_type t ON i.id_type = t.id_type JOIN mst_distributor d ON i.id_distributor = d.id_distributor where i.sts_active='1' AND (i.id_items LIKE '%"+str+"%' OR i.items_name LIKE '%"+str+"%')";
        try{
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            System.out.println(str);
            while(rs.next()){
                Model_Item mi = new Model_Item();
                Model_Type mt = new Model_Type();
                mi.setId_items(rs.getString("id_items"));
                mt.setType_name(rs.getString("type_name"));
                mi.setMoty(mt);
                mi.setItems_name(rs.getString("items_name"));
                mi.setPrice(rs.getString("price"));
                mi.setBrand(rs.getString("brand"));
                mi.setUnit(rs.getString("unit"));
                mi.setStock(rs.getString("stock"));
                list.add(mi);
            }
            return list;
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
            return null;
        }finally{
            if(st!=null){
                try{
                    st.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
            if(rs!=null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(DAO_Distributor.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        }
    }
    }


}
