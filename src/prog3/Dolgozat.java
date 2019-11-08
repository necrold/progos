/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog3;

import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Kronosz
 */

public class Dolgozat {
    
    public void error()
{
  final JPanel panel = new JPanel();
                  JOptionPane.showMessageDialog(panel,
                  "Minden mezőt ki kell tőlteni!!!",
                    "HIBA",
                     JOptionPane.PLAIN_MESSAGE);
}
    
    MainForm My=new MainForm();
      String ip=My.ip;
      String felhasz=My.felhasz;
      String jelszo=My.jelszo;
      String data=My.Data;
   
    public void feldolgoz(String kerdes, String feladat, String osztaly)
    {  
      if(kerdes.equals("")|| feladat.equals("")|| osztaly=="")
      {
          error();
      }
      else
      {
      SQL Dog_Sql=new SQL(ip,felhasz,jelszo,data);
      String query="INSERT INTO dolgozat(DO_KERDES, DO_FELADAT, DO_OSZTALY) VALUES ";
      query+=("('");
      query+=kerdes;
      query+="','";
      query+=(feladat);
      query+="','";
      query+=(osztaly);
      query+="');";
        System.out.println(query);
        try {
            Dog_Sql.Insert_Query(query);
               final JPanel panel = new JPanel();
                  JOptionPane.showMessageDialog(panel,
                  "Feldolgozás",
                    "Sikeres Feltöltés",
                     JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            
        }
      }
     
    }
    
}
