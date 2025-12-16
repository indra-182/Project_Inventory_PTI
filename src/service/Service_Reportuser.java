/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_User;

/**
 *
 * @author rafii
 */
public interface Service_Reportuser {
    
    List<Model_User>getData();
    List<Model_User>getDataKaryawan();
    List<Model_User>getDataGroup();
    void Report(String user);
}