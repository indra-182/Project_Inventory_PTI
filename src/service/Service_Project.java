/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Project;

/**
 *
 * @author rafii
 */
public interface Service_Project {
    void addData(Model_Project mdl_Project);
    void updateData(Model_Project mdl_Project);
    void deleteData(Model_Project mdl_Project);
    
    List<Model_Project>getData();
    List<Model_Project>getDataVendor();
    
    List<Model_Project>search(String str);

}
