/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Menu;

/**
 *
 * @author rafii
 */
public interface Service_Menu {
    void addData(Model_Menu mdl_Menu);
    void updateData(Model_Menu mdl_Menu);
    void deleteData(Model_Menu mdl_Menu);
    
    List<Model_Menu>getData();
    List<Model_Menu>getData2();
}
