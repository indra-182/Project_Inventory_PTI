/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_Karyawan;

/**
 *
 * @author rafii
 */
public interface Service_Karyawan {
    void addData(Model_Karyawan mdl_karyawan);
    void updateData(Model_Karyawan mdl_karyawan);
    void deleteData(Model_Karyawan mdl_karyawan);
    
    List<Model_Karyawan>getData();
    List<Model_Karyawan>getData2();
}
