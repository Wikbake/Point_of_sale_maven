/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointofsale.point_of_sale;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import org.junit.BeforeClass;

/**
 * Simple tests for <code>Seller_GUI</code>.
 */

public class Seller_GUITest{

    private Seller_GUI seller;
    private ConsoleOutputCapturer cos = new ConsoleOutputCapturer();
    private ResultSet rs;
    private long wrongId = 1010101055656L;
    private long rightId = 1234567890123L;
    private Robot robot;
    
    @Before
    public void setUp() throws SQLException {
        seller = new Seller_GUI();
        /*
        seller.listOfArticles.add(new Article(123L, "Some", 0.12));
        seller.listOfArticles.add(new Article(456L, "Thing", 3.45));
        seller.listOfArticles.add(new Article(789L, "Here", 6.78));
        
        try {
         robot = new Robot();   
        } catch(AWTException e) {
            System.out.println("What is this robot doing?!");
            e.printStackTrace();
        }
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

/*
    @Test
    public void testProductNotFound() {
        seller.idEnter.setText("" + wrongId);
        cos.start();
        seller.scanActionPerformed(null);
        String actual = cos.stop().trim();
        assertEquals("Product not found!", actual);
    }

    @Test
    public void testInvalidBarCode() {
        seller.idEnter.setText("");
        cos.start();
        seller.scanActionPerformed(null);
        String actual = cos.stop().trim();
        assertEquals("Invalid bar-code!", actual);
    }

    @Test
    public void testCheckerFoundId() {
        seller.articleChecker(rightId);
        assertEquals(1, seller.listArticlesJList.getComponentCount());
    }

    @Test
    public void testAddToArticleList() {
        seller.addToArticleList(123L, "Some", 0.12);
        assertEquals(1, seller.listArticlesJList.getComponentCount());
    }

    @Test
    public void testExit() {
        assertEquals(3, seller.listOfArticles.size());
        seller.exitActionPerformed(null);
        assertEquals(0, seller.listOfArticles.size());
    }

    @Test
    public void testTotalSum() {
        assertEquals(0, seller.totalSum, 0.00);
        seller.addToArticleList(123L, "Some", 0.12);
        seller.addToArticleList(456L, "Thing", 3.45);
        assertEquals(3.57, seller.totalSum, 0.01);
    }
    */
}