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
public class Model_UserGroup {
    private String id_user;
    private String id_group;
    private String id_menu;
    private String username;
    private String group_name;
    private String name_menu;
    private String view_akses;
    private String add_akses;
    private String edit_akses;
    private String delete_akses;
    private String status_active;
    private String created_by;
    private String created_date;
    private String updated_date;
    private String updated_by; 
        
    public String getId_user() {
        return username;
    }

    public void setId_user(String username) {
        this.username = username;
    }
    
    public String getId_group() {
        return id_user;
    }

    public void setId_group(String id_group) {
        this.id_group = id_group;
    }
    
     public String getId_menu() {
        return id_menu;
    }

    public void setId_menu(String id_menu) {
        this.id_menu = id_menu;
    }
    
    
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
    
    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
    
    public String getName_menu() {
        return name_menu;
    }

    public void setName_menu(String name_menu) {
        this.name_menu = name_menu;
    }


    public String getStatus_active() {
        return status_active;
    }

    public void setStatus_active(String status_active) {
        this.status_active = status_active;
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
    
    
    public String getView_akses() {
        return view_akses;
    }
    
    public String getAdd_akses() {
        return add_akses;
    }
    
    public String getEdit_akses() {
        return edit_akses;
    }
    
    public String getDelete_akses() {
        return delete_akses;
    }

}
