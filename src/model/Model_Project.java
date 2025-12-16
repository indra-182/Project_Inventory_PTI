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
public class Model_Project {
    private String id_project;
    private String project_name;
    private String project_site;
    private String project_manager;
    private Model_Vendor movr;
    private String status;
    private String sts_active;
    private String created_by;
    private String created_date;
    private String updated_date;
    private String updated_by; 

    public String getId_project() {
        return id_project;
    }
    public void setId_project(String id_project) {
        this.id_project = id_project;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_site() {
        return project_site;
    }

    public void setProject_site(String project_site) {
        this.project_site = project_site;
    }

    public String getProject_manager() {
        return project_manager;
    }

    public void setProject_manager(String project_manager) {
        this.project_manager = project_manager;
    }
    public Model_Vendor getMovr() {
        return movr;
    }

    public void setMovr(Model_Vendor movr) {
        this.movr = movr;
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
