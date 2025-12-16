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
public class Model_Cart_Incoming {
    private Model_Detail_Incoming modi;
    private Model_Item moit;
    private Model_Distributor modt;
    private Model_Type moty;
    private String quantity;
    private String total_price;
    private String status;
    private String sts_active;
    private String created_by;
    private String created_date;
    private String updated_date;
    private String updated_by; 

    public Model_Detail_Incoming getModi() {
        return modi;
    }

    public void setModi(Model_Detail_Incoming modi) {
        this.modi = modi;
    }

    public Model_Item getMoit() {
        return moit;
    }

    public void setMoit(Model_Item moit) {
        this.moit = moit;
    }

    public Model_Distributor getModt() {
        return modt;
    }

    public void setModt(Model_Distributor modt) {
        this.modt = modt;
    }
    
    public Model_Type getMoty() {
        return moty;
    }

    public void setMoty(Model_Type moty) {
        this.moty = moty;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSts_active() {
        return sts_active;
    }

    public void setSts_active(String sts_active) {
        this.sts_active = sts_active;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(String updated_date) {
        this.updated_date = updated_date;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }
    
}
