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
public class Model_Outgoing {
    private String id_outgoing;
    private Model_Project mopr;
    private String transaction_date;
    private Model_Karyawan mokr;
    private String sts_active;
    private String created_by;
    private String created_date;
    private String updated_date;
    private String updated_by; 

    public String getId_outgoing() {
        return id_outgoing;
    }

    public void setId_outgoing(String id_outgoing) {
        this.id_outgoing = id_outgoing;
    }

    public Model_Project getMopr() {
        return mopr;
    }

    public void setMopr(Model_Project mopr) {
        this.mopr = mopr;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public Model_Karyawan getMokr() {
        return mokr;
    }

    public void setMokr(Model_Karyawan mokr) {
        this.mokr = mokr;
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