/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Cart_Incoming;
import model.Model_Detail_Incoming;
import model.Model_Item;

/**
 *
 * @author rafii
 */
public interface Service_Detail_Incoming {
    void addData(Model_Cart_Incoming mdl_det_in);
    void sumTotal(Model_Detail_Incoming mdl_det_in);
    void delCart(Model_Detail_Incoming mdl_det_in);
    void addStock(Model_Detail_Incoming mdl_item);

    
    List<Model_Detail_Incoming>getData(String id);
    List<Model_Detail_Incoming>getData2();
    
    String nomor();
    String nomor2();
}
