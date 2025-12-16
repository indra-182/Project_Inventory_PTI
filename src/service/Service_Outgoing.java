/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Cart_Incoming;
import model.Model_Cart_Outgoing;
import model.Model_Incoming;
import model.Model_Outgoing;

/**
 *
 * @author rafii
 */
public interface Service_Outgoing {
    void addData(Model_Cart_Outgoing mdl_in);
    void updData(Model_Outgoing mdl_in);
    void delData(Model_Outgoing mdl_in);
    List<Model_Outgoing>getData();
    List<Model_Outgoing>getData2();
    
    String nomor();
    String nomor2();
}
