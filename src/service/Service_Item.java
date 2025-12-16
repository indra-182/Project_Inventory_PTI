/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Distributor;
import model.Model_Item;

/**
 *
 * @author rafii
 */
public interface Service_Item {
    void addData(Model_Item mdl_item);
    void updateData(Model_Item mdl_item);
    void deleteData(Model_Item mdl_item);

    List<Model_Item>getData();
    List<Model_Item>getData3();
    List<Model_Item>getData2(String idDis);
    
    String nomor();
    String nomor2();

    public List<Model_Item> search(String id,String str);
    public List<Model_Item> search(String str);

    public List<Model_Item> getDataDistributor();

    public List<Model_Item> getDataType();
}
