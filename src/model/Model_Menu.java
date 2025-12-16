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
public class Model_Menu {
    private String id_menu;
    private String name_menu;
        private String sts_active;
    private String created_by;
    private String created_date;
    private String updated_date;
    private String updated_by; 

    public String getId_menu() {
        return id_menu;
    }
    public void setId_menu(String id_menu) {
        this.id_menu = id_menu;
    }

    public String getName_menu() {
        return name_menu;
    }

    public void setName_menu(String name_menu) {
        this.name_menu = name_menu;
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
