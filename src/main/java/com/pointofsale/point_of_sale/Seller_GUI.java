package com.pointofsale.point_of_sale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * This is main class which cooperates with <code>DBConnection</code> and <code>Article</code> classes. For now
 * it is set to get data from mocked method <code>ResultSet</code> but is also ready to work with real PostgreSQL.
 * As for input it is necessary to enter codes by hand in the right place while application is running.
 * <code>PrintReceipt</code> method is responsible for simulation the output.
 * Some access specifiers are package-private or protected instead of private because of test class!
 * <p>
 * IMPORTANT!
 * If database is not connected even though exception will be thrown the application will work correctly.
 * <p>
 * EXAMPLE BAR-CODES:
 * 123
 * 456
 * 789
 * 01234567890123
 *
 * @author Bartlomiej Karbownik
 */
public class Seller_GUI extends javax.swing.JFrame {

    public Seller_GUI() {
        initComponents();
        DBConnection.getDBConncetion();
        
        this.setTitle("Point of sale");
        this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 250, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 250, 250, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }
    
    private List<Article> listOfArticles = new ArrayList<>();
    private DefaultListModel model = new DefaultListModel() {
        @Override
        public Article get(int index) {
            return listOfArticles.get(index);
        }
    };
    private double totalSum = 0;
    private DecimalFormat df = new DecimalFormat("0.00");
    private boolean testProductNotFound = false;
    private boolean testInvalidBarCode = false;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        workspaceJPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        idEnter = new javax.swing.JTextField();
        scanButton = new javax.swing.JButton();
        exitPrintReceiptButton = new javax.swing.JButton();
        yourArticlesJPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listArticlesJList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        sumTextArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Type in a code: ");

        idEnter.setPreferredSize(new java.awt.Dimension(150, 40));
        idEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idEnterActionPerformed(evt);
            }
        });

        scanButton.setText("Scan");
        scanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanButtonActionPerformed(evt);
            }
        });

        exitPrintReceiptButton.setText("Exit/Print receipt");
        exitPrintReceiptButton.setPreferredSize(new java.awt.Dimension(150, 150));
        exitPrintReceiptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitPrintReceiptButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout workspaceJPanelLayout = new javax.swing.GroupLayout(workspaceJPanel);
        workspaceJPanel.setLayout(workspaceJPanelLayout);
        workspaceJPanelLayout.setHorizontalGroup(
            workspaceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workspaceJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(workspaceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitPrintReceiptButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(workspaceJPanelLayout.createSequentialGroup()
                        .addGroup(workspaceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(workspaceJPanelLayout.createSequentialGroup()
                                .addComponent(idEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scanButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        workspaceJPanelLayout.setVerticalGroup(
            workspaceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(workspaceJPanelLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(workspaceJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idEnter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scanButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exitPrintReceiptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setText("List of scanned articles: ");

        listArticlesJList.setPreferredSize(new java.awt.Dimension(200, 250));
        jScrollPane1.setViewportView(listArticlesJList);

        sumTextArea.setColumns(20);
        sumTextArea.setRows(5);
        sumTextArea.setPreferredSize(new java.awt.Dimension(150, 50));
        jScrollPane3.setViewportView(sumTextArea);

        jLabel2.setText("Total sum:");

        javax.swing.GroupLayout yourArticlesJPanelLayout = new javax.swing.GroupLayout(yourArticlesJPanel);
        yourArticlesJPanel.setLayout(yourArticlesJPanelLayout);
        yourArticlesJPanelLayout.setHorizontalGroup(
            yourArticlesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, yourArticlesJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(yourArticlesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, yourArticlesJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, yourArticlesJPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        yourArticlesJPanelLayout.setVerticalGroup(
            yourArticlesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yourArticlesJPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(yourArticlesJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(yourArticlesJPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jLabel2))
                    .addGroup(yourArticlesJPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(yourArticlesJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(workspaceJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(workspaceJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(yourArticlesJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idEnterActionPerformed

    }//GEN-LAST:event_idEnterActionPerformed

    private void scanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanButtonActionPerformed
        String id = idEnter.getText();
        try {
            if (id.equals(""))
                throw new InvalidBarCodeException();
            else {
                long idArticle = Long.parseLong(id);
                articleChecker(idArticle);
            }
        } catch (InvalidBarCodeException | NumberFormatException ex) {
            System.out.println("Invalid bar-code!");
            testInvalidBarCode = true;
            JOptionPane.showMessageDialog(rootPane, "Invalid bar-code", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_scanButtonActionPerformed

    private void exitPrintReceiptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitPrintReceiptButtonActionPerformed
        printReceipt();
        JOptionPane.showMessageDialog(rootPane, df.format(totalSum) + " zł", "Amount to pay", JOptionPane.INFORMATION_MESSAGE);
        model.clear();
        listOfArticles.clear();
        totalSum = 0;
        sumTextArea.setText("0");
    }//GEN-LAST:event_exitPrintReceiptButtonActionPerformed

        void articleChecker(long id) {
        ResultSet rs = null;
        long idArticle = 0L;
        try {
            /*
            *
            * You can use this in case of real database. Otherwise you will use mocked data from <code>DBConnection</code>.
            *
            rs = DBConnection.executeQuery("SELECT * FROM article WHERE id = " + id);
             */
            rs = DBConnection.executeQuery(String.valueOf(id));
            while (rs.next()) {
                idArticle = rs.getLong("id");
                String nameArticle = rs.getString("name");
                double costArticle = rs.getDouble("cost");

                System.out.println("Product found!");
                addToArticleList(idArticle, nameArticle, costArticle);
            }
            if (id != idArticle)
                throw new ProductNotFoundException();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ProductNotFoundException e) {
            System.out.println("Product not found!");
            testProductNotFound = true;
            JOptionPane.showMessageDialog(rootPane, "Product not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void addToArticleList(long id, String articleName, double cost) {
        model.addElement(articleName + " " + cost + "zł");
        listArticlesJList.setModel(model);
        System.out.println("Product added to list!");
        totalSum += cost;
        sumTextArea.setText(df.format(totalSum));
        listOfArticles.add(new Article(id, articleName, cost));
    }

    protected void printReceipt() {
        System.out.println("RECEIPT: ");
        for (Article a : listOfArticles)
            System.out.println(a.getName() + " " + a.getCost());
        System.out.println("Total cost: " + df.format(totalSum) + " zł");
    }
    
    public void enterCode(String text) {
        idEnter.setText(text);
        scanButton.doClick();
    }
    
    public String getSumTextArea() {
        return sumTextArea.getText();
    }
    
    public Article getAddedArticle() {
        return (Article) model.get(0);
    }

    public boolean getPNFFlag() {
        return testProductNotFound;
    }
    
    public boolean getIBFlag() {
        return testInvalidBarCode;
    }
        
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Seller_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Seller_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Seller_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Seller_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Seller_GUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitPrintReceiptButton;
    private javax.swing.JTextField idEnter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList listArticlesJList;
    private javax.swing.JButton scanButton;
    private javax.swing.JTextArea sumTextArea;
    private javax.swing.JPanel workspaceJPanel;
    private javax.swing.JPanel yourArticlesJPanel;
    // End of variables declaration//GEN-END:variables
}
