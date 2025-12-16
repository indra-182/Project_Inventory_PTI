/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Model_Login;
import view.Form_Regist;
import view.Form_login;

/**
 *
 * @author rafii
 */
public interface Service_Login {
    void proccesLogin(Model_Login login);
    void registLogin(Model_Login login,Form_login fl,Form_Regist fr);
}
