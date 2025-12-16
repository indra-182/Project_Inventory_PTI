/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Cart_Incoming;
import model.Model_Detail_Incoming;

/**
 *
 * @author rafii
 */
public interface Service_Cart_Incoming {
    void addData(Model_Cart_Incoming mdl_cart_in);
    void sumTotal(Model_Cart_Incoming mdl_cart_in);
    void delCart();
    void updCart(Model_Cart_Incoming mdl_cart_in);

    
    List<Model_Cart_Incoming>getData();
    List<Model_Detail_Incoming>getData2();
    
    String nomor();
    String nomor2();

    public void deleteData(String id);
}
