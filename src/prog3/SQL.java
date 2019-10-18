/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog3;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 *
 * @author Kronosz
 */
public class SQL {
   public String ip=null;
   public String felhasz=null;
   String jelszo=null;
   String Data=null;
         String con="false";
      Connection myconn=null;
      Statement statment=null;
      ResultSet myq=null;
    
 public SQL()
 {
     
 }
 public SQL(String ip,String felhasz, String jelszo,String Data) {
     
     this.ip = ip;
     this.felhasz=felhasz;
     this.jelszo=jelszo;
     this.Data=Data;
 }  
    
  public void error(String e)
  {
      final JPanel panel = new JPanel();
      JOptionPane.showMessageDialog(panel, e, "Meow", JOptionPane.ERROR_MESSAGE);
  }
  public String connect()
  { 
    

      try{
          
          myconn=DriverManager.getConnection("jdbc:mysql://"+ip+"/"+Data+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",felhasz,jelszo);
          statment=myconn.createStatement();
         
          myq=statment.executeQuery("select * from iskolák");
          
         if(myq.first()!=false)
         {
             con="true";
         }
        
      }
      catch(Exception e){
         
         error(e.getMessage());
      }
      

     
      
      return con;
  }
  
          public void query(String command)
          {

      try{
         
          myq=statment.executeQuery(command);            
            while (myq.next())
            {
                System.out.println(myq.getString("iskola_Name"));
            }
     
        
      
      }
      catch(Exception e){
         
         error(e.getMessage());
      }
  }
}
