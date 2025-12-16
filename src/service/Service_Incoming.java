/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Cart_Incoming;
import model.Model_Incoming;

/**
 *
 * @author rafii
 */
public interface Service_Incoming {
    void addData(Model_Cart_Incoming mdl_in);
    void updData(Model_Incoming mdl_in);
    void delData(Model_Incoming mdl_in);
    List<Model_Incoming>getData();
    List<Model_Incoming>getData2();
    
    String nomor();
    String nomor2();
}
