/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointofsale.point_of_sale;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Simple tests for <code>Seller_GUI</code>.
 */

public class Seller_GUITest{

    private Seller_GUI seller;
    private ResultSet rs;
    
    @Before
    public void setUp() throws SQLException {
        seller = new Seller_GUI();
        /*
        seller.listOfArticles.add(new Article(123L, "Some", 0.12));
        seller.listOfArticles.add(new Article(456L, "Thing", 3.45));
        seller.listOfArticles.add(new Article(789L, "Here", 6.78));

        */
    }
    
    @Test
    public void testAddToArticleList() {
        seller.enterCode("123");
        Article ar = seller.getAddedArticle();
        assertEquals("Kajzerka", ar.getName());
        assertEquals(0.20, ar.getCost(), 0.01);
    }
    
    @Test
    public void testTotalSum() {
        seller.enterCode("123");
        seller.enterCode("789");
        assertEquals("0,79", seller.getSumTextArea());
    }
    
    @Test 
    public void testProductNotFound() {
        seller.enterCode("4545454545");
        assertEquals(true, seller.getPNFFlag());
    }
    
    @Test 
    public void testInvalidBarCode() {
        seller.enterCode("");
        assertEquals(true, seller.getIBFlag());
    }

/*
    @Test
    public void testExit() {
        assertEquals(3, seller.listOfArticles.size());
        seller.exitActionPerformed(null);
        assertEquals(0, seller.listOfArticles.size());
    }
    */
}