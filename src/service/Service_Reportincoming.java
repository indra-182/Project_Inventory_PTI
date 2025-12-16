/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Date;
import java.util.List;
import model.Model_Reportincoming;

/**
 *
 * @author rafii
 */
public interface Service_Reportincoming {
    List<Model_Reportincoming>getData();
    List<Model_Reportincoming>getData(Date fromDate, Date toDate);
    void Report(String user);
    void Report(String user,Date fromDate, Date toDate);
    String nomor();
    String nomor2();
}
