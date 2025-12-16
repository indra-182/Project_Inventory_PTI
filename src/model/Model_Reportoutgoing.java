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
public class Model_Reportoutgoing {
    private String id_outgoing;
    private String id_project;
    private String transaction_date;
    private String project_name;
    private String id_items;
    private String items_name;
    
    private String quantity;
    
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
    
    
         public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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
    
    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }
   
    public String getId_outgoing() {
        return id_outgoing;
    }

    public void setId_outgoing(String id_outgoing) {
        this.id_outgoing = id_outgoing;
    }


   

} 
