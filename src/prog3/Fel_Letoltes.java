/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Alex
 */
public class Fel_Letoltes extends javax.swing.JFrame {

        MainForm main=new MainForm();
      Dolgozat vegrehajt=new Dolgozat();
    public String osztaly;
    public String name=null;
    
    /**
     * Creates new form Fel_Letoltes
     */
    public Fel_Letoltes() {
        initComponents();
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        palette = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setText("Feladatok Generálása");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        palette.setEditable(false);
        palette.setColumns(20);
        palette.setRows(5);
        palette.setAutoscrolls(false);
        palette.setBorder(null);
        jScrollPane1.setViewportView(palette);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("PDF-be mentés");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel2.setText("Fájl név");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jLabel1)
                .addContainerGap(333, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(44, 44, 44))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        diagrammok dm=new diagrammok();
        if(!(jTextField1.getText().equals("")) && !(jTextField1.getText().equals("Adjon meg egy nevet!")))
        {
            dm.Dog_save_Img(jPanel1,"Img/"+jTextField1.getText());
        }
        else
        {
            jTextField1.setText("Adjon meg egy nevet!");
        }
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       try {
              String query="Select DO_KERDES,DO_FELADAT from dolgozat where DO_OSZTALY="+main.Osz;
             
              ResultSet visszateres=vegrehajt.lekerdez(query);
                  int row=0;
                  while(visszateres.next()){
                  ++row;
               }
                visszateres=vegrehajt.lekerdez(query);
               String[][] kerdesek=new String[row][2];
              int i=0;
              while(visszateres.next()){
                  String feladat=visszateres.getString("DO_KERDES");
                  String kerdes=visszateres.getString("DO_FELADAT");
                  kerdesek[i][0]=feladat;
                  kerdesek[i][1]=kerdes;
                  i++;
              }
              String[] kerdes=new String[5];

              int y=0;
              ArrayList<JLabel> labels = new ArrayList<>();
              kerdes[0]=kerdesek[0][1];
              Random rnd=new Random();
              int szamlalo=0;
              boolean van=false;
           JTextArea newLabel2 = new JTextArea(100,30);
              for(int j=0;j<5;j++)
            {
                if(j==0)
                {
                  int Rnumber=rnd.nextInt(row)+0;
                   JLabel newLabel = new JLabel((szamlalo+1)+" :"+kerdesek[Rnumber][0]);
                   
                   palette.append((szamlalo+1)+" :"+kerdesek[Rnumber][0]+"\n"+kerdesek[Rnumber][1]);
//                   newLabel.setBounds(10, y, 380, 200);
//                     y = y + 10;
//                   newLabel2.setBounds(255, y, 380, 200);
//                   y = y + 55;
//                   jPanel1.add(newLabel);
                   jPanel1.add(newLabel2);
                   newLabel2.repaint();
                   jPanel1.repaint();
                   szamlalo++;
                }
                else
                {
             int Rnumber=rnd.nextInt(row)+0;
             for(int k=0;k<szamlalo;k++)
             {
                 if(kerdes[k].equals(kerdesek[Rnumber][1]))
                 {
                     van=true;
                 }
             }
             if(van==true)
             {
                 van=false;
                 j--;
                
             }
             else
             {
                 kerdes[j]=kerdesek[Rnumber][1];
                 palette.append("\n\n");
                 palette.append((szamlalo+1)+" :"+kerdesek[Rnumber][0]+"\n"+kerdesek[Rnumber][1]+"\n");
//                  JLabel newLabel = new JLabel((szamlalo+1)+" :"+kerdesek[Rnumber][0]);
//                   JTextArea newLabel2 = new JTextArea(kerdesek[Rnumber][1],100,30);
                   
//                   newLabel.setBounds(10, y, 380, 20);
//                   y = y + 10;
//                   newLabel2.setBounds(255, y, 380, 20);
//                   y = y + 75;
//                   jPanel1.add(newLabel);
                   
                   palette.repaint();
                   jPanel1.repaint();
                   szamlalo++;
             }
             
            }
            }
              
          } catch (SQLException ex) {
              Logger.getLogger(Fel_Letoltes.class.getName()).log(Level.SEVERE, null, ex);
          }
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Fel_Letoltes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fel_Letoltes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fel_Letoltes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fel_Letoltes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Fel_Letoltes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextArea palette;
    // End of variables declaration//GEN-END:variables
}