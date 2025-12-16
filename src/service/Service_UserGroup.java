/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import model.Model_UserGroup;

/**
 *
 * @author rafii
 */
public interface Service_UserGroup {
    void addData(Model_UserGroup mdl_usergroup);
    void updateData(Model_UserGroup mdl_usergroup);
    void deleteData(Model_UserGroup mdl_usergroup);
    
    List<Model_UserGroup>getData();
    List<Model_UserGroup>getData2();
}
