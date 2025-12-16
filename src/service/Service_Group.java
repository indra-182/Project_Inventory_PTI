/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Group;

/**
 *
 * @author rafii
 */
public interface Service_Group {
    void addData(Model_Group mdl_group);
    void updateData(Model_Group mdl_group);
    void deleteData(Model_Group mdl_group);
    
    List<Model_Group>getData();
    List<Model_Group>getData2();
}
