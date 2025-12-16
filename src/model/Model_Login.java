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
public class Model_Login {
    private String username;
    private String password;
    public Model_Group getMogr() {
        return mogr;
    }

    public void setMogr(Model_Group mogr) {
        this.mogr = mogr;
    }
//    private String id_group;
    private Model_Group mogr;
    private Model_Karyawan mokr;
//    private String id_karyawan;
    private String sts_active;
    private String created_by;
    private String created_date;
    private String updated_date;
    private String updated_by;

    public String getLast_active() {
        return last_active;
    }

    public void setLast_active(String last_active) {
        this.last_active = last_active;
    }
    private String last_active;
    
//    public String getId_group() {
//        return id_group;
//    }
//
//    public void setId_group(String id_group) {
//        this.id_group = id_group;
//    }

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

//    public String getId_karyawan() {
//        return id_karyawan;
//    }
//
//    public void setId_karyawan(String id_karyawan) {
//        this.id_karyawan = id_karyawan;
//    }


    public Model_Karyawan getMokr() {
        return mokr;
    }

    public void setMokr(Model_Karyawan mokr) {
        this.mokr = mokr;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
