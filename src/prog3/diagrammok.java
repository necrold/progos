/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

*/
package prog3;

import java.awt.Color;
import static java.lang.Compiler.command;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import static prog3.MainForm.Data;
import static prog3.MainForm.felhasz;
import static prog3.MainForm.ip;
import static prog3.MainForm.jelszo;

/**
 *
 * @author Kronosz
 */
public class diagrammok {
  
  public static void DiaGramm(JPanel valami) throws SQLException
  {
    SQL kapcsolat=new SQL(ip,felhasz,jelszo,Data);
         
     ResultSet iskola=kapcsolat.query("Select count(diak_INT),iskola_Name from diák INNER JOIN iskolák on diák.diak_INT=iskolák.iskola_ID GROUP by diak_INT;");
   
       DefaultCategoryDataset barcData=new DefaultCategoryDataset();
       while(iskola.next())
       {
            barcData.setValue(iskola.getInt("count(diak_INT)"),iskola.getString("iskola_Name"),iskola.getString("iskola_Name"));
       
       }
      
         JFreeChart barchar= ChartFactory.createBarChart("Tanulók létszáma", "Jelmagyarázat", "", barcData, PlotOrientation.VERTICAL, true, true, true);
         CategoryPlot bar= barchar.getCategoryPlot();
         bar.setRangeGridlinePaint(Color.ORANGE);
         
         
         ChartPanel barPanel=new ChartPanel(barchar);
         MainForm main=new MainForm();
         valami.removeAll();
        
         valami.add(barPanel);
         valami.validate();
         
                 
  }
  
}
