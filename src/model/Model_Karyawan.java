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
public class Model_Karyawan {
    private String id_karyawan;
    private String nama_lengkap;
    private String nik;
    private String gender;
    private String no_telp;
    private String pic_distributor;
    private String status_active;
    private String created_by;
    private String created_date;
    private String updated_date;
    private String updated_by; 
    
    public String getId_karyawan() {
        return id_karyawan;
    }

    public void setId_karyawan(String id_karyawan) {
        this.id_karyawan = id_karyawan;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getPic_distributor() {
        return pic_distributor;
    }

    public void setPic_distributor(String pic_distributor) {
        this.pic_distributor = pic_distributor;
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

}
