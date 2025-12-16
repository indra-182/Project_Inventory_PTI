/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Cart_Outgoing;
import model.Model_Detail_Outgoing;
import model.Model_Item;

/**
 *
 * @author rafii
 */
public interface Service_Detail_Outgoing {
    void addData(Model_Cart_Outgoing mdl_det_in);
    void sumTotal(Model_Detail_Outgoing mdl_det_in);
    void delCart(Model_Detail_Outgoing mdl_det_in);
    void useStock(Model_Detail_Outgoing mdl_item);

    
    List<Model_Detail_Outgoing>getData(String id);
    List<Model_Detail_Outgoing>getData2();
    
    String nomor();
    String nomor2();
}
