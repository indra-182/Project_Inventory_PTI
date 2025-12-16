/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Distributor;

/**
 *
 * @author rafii
 */
public interface Service_Distributor {
    void addData(Model_Distributor mdl_distributor);
    void updateData(Model_Distributor mdl_distributor);
    void deleteData(Model_Distributor mdl_distributor);
    
    List<Model_Distributor>search(String str);
    List<Model_Distributor>getData();
    List<Model_Distributor>getData2();
}
