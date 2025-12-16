/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rafii
 */
public class Model_Reportincoming {
    private String id_incoming;
    private String id_distributor;
    private String distributor_name;
    private String transaction_date;
    private String id_items;
    private String items_name;
    private String subtotal;
    private String quantity;
    private String total;
    private String nip;
    private String pic;
    private String type_transaction;
    
     public String getType_transaction() {
        return type_transaction;
    }

    public void setType_transaction(String type_transaction) {
        this.type_transaction = type_transaction;
    }
    
      public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    
       public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
    
        public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
         public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
     public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
    
    public String getItems_name() {
        return items_name;
    }

    public void setItems_name(String items_name) {
        this.items_name = items_name;
    }
    
    public String getId_items() {
        return id_items;
    }

    public void setId_items(String id_items) {
        this.id_items = id_items;
    }
    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }
    
    public String getDistributor_name() {
        return distributor_name;
    }

    public void setDistributor_name(String distributor_name) {
        this.distributor_name = distributor_name;
    }
    
    public String getId_distributor() {
        return id_distributor;
    }

    public void setId_distributor(String id_distributor) {
        this.id_distributor = id_distributor;
    }
    
    public String getId_incoming() {
        return id_incoming;
    }

    public void setId_incoming(String id_incoming) {
        this.id_incoming = id_incoming;
    }


   

} 
