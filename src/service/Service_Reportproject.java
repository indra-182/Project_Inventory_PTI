/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Project;

/**
 *
 * @author rafii
 */
public interface Service_Reportproject {
    List<Model_Project>getData();
    List<Model_Project>getData(String str);
    void Report(String user,String status);
    void Report(String user);
    String nomor();
    String nomor2();
}