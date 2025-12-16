/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rafii
 */
public class Model_Cart_Outgoing {
    private Model_Detail_Outgoing modo;
    private Model_Item moit;
    private Model_Type moty;
    private Model_Project mopr;
    private String quantity;

    public Model_Project getMopr() {
        return mopr;
    }

    public void setMopr(Model_Project mopr) {
        this.mopr = mopr;
    }

    public Model_Detail_Outgoing getModo() {
        return modo;
    }

    public void setModo(Model_Detail_Outgoing modo) {
        this.modo = modo;
    }

    public Model_Item getMoit() {
        return moit;
    }

    public void setMoit(Model_Item moit) {
        this.moit = moit;
    }

    public Model_Type getMoty() {
        return moty;
    }

    public void setMoty(Model_Type moty) {
        this.moty = moty;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}