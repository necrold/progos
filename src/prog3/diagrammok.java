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
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.awt.*;
import com.sun.scenario.effect.ImageData;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.PopupMenu;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Kronosz
 */
public class diagrammok {

     public ChartPanel barPanel=null;
     public BufferedImage imagebuf=null;
  
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
         
         
      
         MainForm main=new MainForm();
         valami.removeAll();
         ChartPanel barPanel=new ChartPanel(barchar);
         valami.add(barPanel);
         valami.validate();
         
                 
  }
  
  public void pdfcreater(JPanel valami) throws FileNotFoundException, DocumentException, BadElementException, IOException, SQLException
  {
      Document document=new Document();
      
     
      PdfWriter.getInstance(document, new FileOutputStream("Iskola_Statisztika.pdf"));
      document.open();
      document.addTitle("Statisztika");
      String filename="Img/Diagramm.jpeg";
      saveImage(valami,filename);
      String imageFile = filename; 
      Image img = Image.getInstance(filename);
      img.setRotationDegrees(-90);
      document.add(img);
      document.close();
     
  }
  public void saveImage(JPanel valami, String filename) throws SQLException{
    
      Dimension nowsize=valami.getSize();
      
      valami.setSize(780, 530);
      DiaGramm(valami);
  
      
    try {
        imagebuf = new Robot().createScreenCapture(valami.bounds());
    } catch (AWTException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }  
     Graphics2D graphics2D = imagebuf.createGraphics();
     
     valami.paint(graphics2D);
    
       
     
     try {
        ImageIO.write(imagebuf,"jpeg", new File(filename));
       
    } catch (Exception e) {
        // TODO Auto-generated catch block
        System.out.println("error");
    }
     valami.setSize(nowsize);
      DiaGramm(valami);
}
  
}

