/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Vendor;

/**
 *
 * @author rafii
 */
public interface Service_Vendor {
    void addData(Model_Vendor mdl_Vendor);
    void updateData(Model_Vendor mdl_Vendor);
    void deleteData(Model_Vendor mdl_Vendor);
    
    List<Model_Vendor>getData();
    List<Model_Vendor>getData2();
}
