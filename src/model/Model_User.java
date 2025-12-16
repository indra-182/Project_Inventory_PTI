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
public class Model_User {
    private String id_user;
    private String username;
    private String password;
    private String id_karyawan;
    private String nama_lengkap;
    private String last_login;
    private Model_Karyawan movr;
    private Model_Group mogr;

    public Model_Group getMogr() {
        return mogr;
    }

    public void setMogr(Model_Group mogr) {
        this.mogr = mogr;
    }
    private String sts_active;
    private String created_by;
    private String created_date;
    private String updated_date;
    private String updated_by; 
    
    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
    
    public String getId_karyawan() {
        return id_karyawan;
    }

    public void setId_karyawan(String id_karyawan) {
        this.id_karyawan = id_karyawan;
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


    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
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

        public String getSts_active() {
        return sts_active;
    }
        
        public Model_Karyawan getMovr() {
        return movr;
    }

    public void setMovr(Model_Karyawan movr) {
        this.movr = movr;
    }

    public void setSts_active(String sts_active) {
        this.sts_active = sts_active;
    }
    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }
}
