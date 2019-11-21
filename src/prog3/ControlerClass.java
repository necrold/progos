/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static prog3.MainForm.Data;
import static prog3.MainForm.felhasz;
import static prog3.MainForm.ip;
import static prog3.MainForm.jelszo;

/**
 *
 * @author Alex
 */
 class ControlerClass {
    
        public void feltolt_combo(JComboBox<String> megye_ComboBox,JComboBox<String> telep_ComboBox,JComboBox<String> fenntarttip_ComboBox,JComboBox<String> inttip_ComboBox,JComboBox<String> diakszulhely_ComboBox,JComboBox<String> diakintezmeny_ComboBox,JComboBox isktelep_ComboBox,JComboBox iskfenn_ComboBox,JComboBox iskfeladat_ComboBox){
        megye_ComboBox.removeAllItems();
        telep_ComboBox.removeAllItems();
        fenntarttip_ComboBox.removeAllItems();
        inttip_ComboBox.removeAllItems();
        diakszulhely_ComboBox.removeAllItems();
        diakintezmeny_ComboBox.removeAllItems();
        isktelep_ComboBox.removeAllItems();
        iskfenn_ComboBox.removeAllItems();
        iskfeladat_ComboBox.removeAllItems();
        String query1="SELECT `iskola_County` from iskolák";
        String query2="SELECT `varos_NAME` from város";
        String query3="SELECT `iskola_Task` from iskolák";
        String query4="SELECT `fenntart_Name` from fenntartok";
        String query5="SELECT `iskola_Name` from iskolák";
        String query6="SELECT `feladat_Name` from feladatok";
        try{
            Connection myconn=DriverManager.getConnection("jdbc:mysql://"+ip+"/"+Data+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",felhasz,jelszo);
          Statement statment=myconn.createStatement();
          ResultSet rs=statment.executeQuery(query1);
            while(rs.next()){
                String megye =rs.getString("iskola_County");
                megye_ComboBox.addItem(megye);
            }
            rs=statment.executeQuery(query2);
            while(rs.next()){
                String telep =rs.getString("varos_NAME");
                telep_ComboBox.addItem(telep);
                diakszulhely_ComboBox.addItem(telep);
                isktelep_ComboBox.addItem(telep);
            }
            rs=statment.executeQuery(query3);
            while(rs.next()){
                int feladat =rs.getInt("iskola_Task");
                inttip_ComboBox.addItem(String.valueOf(feladat));
            }
            rs=statment.executeQuery(query4);
            while(rs.next()){
                String fenntarto =rs.getString("fenntart_Name");
                fenntarttip_ComboBox.addItem(fenntarto);
                iskfenn_ComboBox.addItem(fenntarto);
            }
            rs=statment.executeQuery(query5);
            while(rs.next()){
                String iskola =rs.getString("iskola_Name");
                diakintezmeny_ComboBox.addItem(iskola);
            }
            rs=statment.executeQuery(query6);
            while(rs.next()){
                String felad = rs.getString("feladat_Name");
                iskfeladat_ComboBox.addItem(felad);
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
        public ArrayList<IskolakClass> iskolaLista(){
       ArrayList<IskolakClass> iskLista=new ArrayList<>();
       try{
           Connection myconn=DriverManager.getConnection("jdbc:mysql://"+ip+"/"+Data+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",felhasz,jelszo);
          String query1="SELECT `iskola_OM`,`iskola_Name`,`varos_NAME`,`iskola_County`,`fenntart_Name`,`feladat_Name`,`iskola_Enable`,`iskola_AddR` FROM `iskolák` INNER JOIN `város` ON iskola_SETL=varos_ID INNER JOIN `fenntartok` ON iskola_Fennt=fenntart_ID INNER JOIN `feladatok` ON iskola_Task=feladat_ID";
          Statement statment=myconn.createStatement();
          ResultSet rs=statment.executeQuery(query1);
          IskolakClass iskolak;
          while(rs.next()){
              iskolak=new IskolakClass(rs.getString("iskola_OM"), rs.getString("varos_NAME"), rs.getString("fenntart_Name"),rs.getString("feladat_Name"),rs.getString("iskola_Name"),rs.getString("iskola_County"),rs.getString("iskola_AddR"), rs.getBoolean("iskola_Enable"));
              iskLista.add(iskolak);
          }
       }
       catch(Exception e){       
         JOptionPane.showMessageDialog(null, e);
      }
       
        return iskLista;
    }
       
        public void feltolt_tabla(JTable iskolaTable){
        ArrayList<IskolakClass> lista=iskolaLista();
        DefaultTableModel model=(DefaultTableModel)iskolaTable.getModel();
        model.setRowCount(0);
            System.out.println(lista.size());
        Object[] sor=new Object[8];
        for(int i=0;i<lista.size();i++){
            sor[0]=lista.get(i).getiskola_OM();
            sor[1]=lista.get(i).getiskola_Name();
            sor[2]=lista.get(i).getvaros_NAME();
            sor[3]=lista.get(i).getiskola_County();
            sor[4]=lista.get(i).getfenntart_Name();
            sor[5]=lista.get(i).getiskola_AddR();
            sor[6]=lista.get(i).getfeladat_Name();
            sor[7]=lista.get(i).getiskola_Enable();
            model.addRow(sor);
        }
    }       
         
        public void aktiv_filter_Check(JCheckBox aktiv_CheckBox,JTable iskolaTable) { 
        DefaultTableModel model = (DefaultTableModel)iskolaTable.getModel();
        if (!aktiv_CheckBox.isSelected()){
            iskolaTable.setRowSorter(null);
            return;
        }
        RowFilter<Object, Object> filter =new RowFilter<Object, Object>() {
            @Override
            public boolean include(RowFilter.Entry<? extends Object, ? extends Object> entry) {
                Boolean value = (Boolean)entry.getValue(7);
                return value != null && value;
            }
        };
        TableRowSorter sorter =new TableRowSorter(model);
        sorter.setRowFilter(filter);
        sorter.isSortable(1);
        sorter.setSortable(1,true);
        iskolaTable.setRowSorter(sorter);
    }
   
        public void kereses(JComboBox<String> comboBox,JTable iskolaTable,int oszlop_szam) {
        DefaultTableModel model = (DefaultTableModel)iskolaTable.getModel();
        RowFilter<Object, Object> keresofilter;
        String kival=(String)comboBox.getSelectedItem();
        keresofilter = new RowFilter<Object, Object>() {
            @Override
            public boolean include(RowFilter.Entry<? extends Object, ? extends Object> entry) {
                 return entry.getStringValue(oszlop_szam).toLowerCase().contains(kival.toLowerCase());
            }
        };
        TableRowSorter keresosorter =new TableRowSorter(model);
        keresosorter.setRowFilter(keresofilter);
        iskolaTable.setRowSorter(keresosorter);
    }   
        
        public void kereses(JTextField TextField,JTable iskolaTable,int oszlop_szam) {
        DefaultTableModel model = (DefaultTableModel)iskolaTable.getModel();
        RowFilter<Object, Object> keresofilter;
        String kival=TextField.getText();
        keresofilter = new RowFilter<Object, Object>() {
            @Override
            public boolean include(RowFilter.Entry<? extends Object, ? extends Object> entry) {
                 return entry.getStringValue(oszlop_szam).toLowerCase().contains(kival.toLowerCase());
            }
        };
        TableRowSorter keresosorter =new TableRowSorter(model);
        keresosorter.setRowFilter(keresofilter);
        iskolaTable.setRowSorter(keresosorter);
    }
       public void diakfeltoltes(JTextField diakveznev_TextField,JTextField diakkernev_TextField,JTextField diakannya_TextField,JTextField diakszulido_TextField,JTextField diakom_TextField,JComboBox diakszulhely_ComboBox,JComboBox diakintezmeny_ComboBox) {                                                     
        String veznev=diakveznev_TextField.getText();
        String kernev=diakkernev_TextField.getText();
        String anyjanev=diakannya_TextField.getText();
        String szulhely=(String) diakszulhely_ComboBox.getSelectedItem();
        String szuletesiIdo=diakszulido_TextField.getText();  
        String diakintezmeny=String.valueOf(diakintezmeny_ComboBox.getSelectedIndex()+1);
        String diakOM=diakom_TextField.getText();
        
        String insertquery="INSERT INTO diák ( diak_VEZN, diak_KERN, diak_AYNN, diak_SZHELY, diak_SZIDO, diak_INT, diak_OM) VALUES ";
        insertquery+=("('");
      insertquery+=veznev;
      insertquery+="','";
      insertquery+=(kernev);
      insertquery+="','";
      insertquery+=(anyjanev);
      insertquery+="','";
      insertquery+=(szulhely);
      insertquery+="','";
      insertquery+=(szuletesiIdo);
      insertquery+="','";
      insertquery+=(diakintezmeny);
      insertquery+="','";
      insertquery+=(diakOM);
      insertquery+="');";
        
      if(!"".equals(veznev)&&!"".equals(anyjanev)&&!"".equals(szuletesiIdo)&&!"".equals(diakOM)){
        SQL feltolt_Sql=new SQL(ip,felhasz,jelszo,Data);
        feltolt_Sql.Insert_Query(insertquery);
        diakveznev_TextField.setText(null);
        diakkernev_TextField.setText(null);
        diakannya_TextField.setText(null);
        diakszulido_TextField.setText(null);
        diakom_TextField.setText(null);
        JOptionPane.showMessageDialog(null, "Sikeres feltöltés!", "Siker",1);
      }else{
           JOptionPane.showMessageDialog(null, "Minden mező kitöltése kötelező!", "Figyelmeztetés",2);
      }

    }
            
    public void iskfeltoltes(JTextField iskom_TextField,JTextField iskneve_TextField,JTextField iskmegye_TextField,JTextField iskcime_TextField,JComboBox isktelep_ComboBox,JComboBox iskfenn_ComboBox,JComboBox iskfeladat_ComboBox,JCheckBox iskaktiv_CheckBox) {                                                     
        String iskolaOM=iskom_TextField.getText();
        String iskolaNeve=iskneve_TextField.getText();
        String iskolaMegye=iskmegye_TextField.getText();
        String iskolaVaros=String.valueOf(isktelep_ComboBox.getSelectedIndex()+1);
        String iskolaCime=iskcime_TextField.getText();
        String iskolaFenntarto=String.valueOf(iskfenn_ComboBox.getSelectedIndex()+1);
        String iskfeladat=String.valueOf(iskfeladat_ComboBox.getSelectedIndex()+1);
        String aktiv="0";
        if(iskaktiv_CheckBox.isSelected()){
            aktiv="1";
        }else{
            aktiv="0";
        }
        String insertquery="INSERT INTO `iskolák`(`iskola_OM`, `iskola_Name`, `iskola_SETL`, `iskola_County`, `iskola_Fennt`, `iskola_Task`, `iskola_Enable`, `iskola_AddR`) VALUES ";
        insertquery+=("('");
      insertquery+=iskolaOM;
      insertquery+="','";
      insertquery+=(iskolaNeve);
      insertquery+="','";
      insertquery+=(iskolaVaros);
      insertquery+="','";
      insertquery+=(iskolaMegye);
      insertquery+="','";
      insertquery+=(iskolaFenntarto);
      insertquery+="','";
      insertquery+=(iskfeladat);
      insertquery+="','";
      insertquery+=(aktiv);
      insertquery+="','";
      insertquery+=(iskolaCime);
      insertquery+="');";
      
       if(!"".equals(iskolaOM)&&!"".equals(iskolaNeve)&&!"".equals(iskolaMegye)&&!"".equals(iskolaCime)) {
       SQL feltolt_Sql=new SQL(ip,felhasz,jelszo,Data);
       feltolt_Sql.Insert_Query(insertquery);
       iskom_TextField.setText(null);
       iskneve_TextField.setText(null);
       iskmegye_TextField.setText(null);
       iskcime_TextField.setText(null);
       JOptionPane.showMessageDialog(null, "Sikeres feltöltés!", "Siker",1);
       }else{
           JOptionPane.showMessageDialog(null, "Minden mező kitöltése kötelező!", "Figyelmeztetés",2);
       }
    }

    void fenntartFeltolt(JTextField fenntnev_TextField, JTextField fennttip_TextField) {
        String fenntartNeve=fenntnev_TextField.getText();
        String fenntartTipusa=fennttip_TextField.getText();
        
        String insertquery="INSERT INTO `fenntartok`(`fenntart_Name`, `fenntart_Type`) VALUES ";
        insertquery+=("('");
        insertquery+=fenntartNeve;
        insertquery+="','";
        insertquery+=(fenntartTipusa);
        insertquery+="');";
      
        if (!"INSERT INTO `fenntartok`(`fenntart_Name`, `fenntart_Type`) VALUES ('','');".equals(insertquery)){
                 SQL feltolt_Sql=new SQL(ip,felhasz,jelszo,Data);
                 feltolt_Sql.Insert_Query(insertquery);
                 fenntnev_TextField.setText(null);
                 fennttip_TextField.setText(null);
                 JOptionPane.showMessageDialog(null, "Sikeres feltöltés!", "Siker",1);
        }else{
           JOptionPane.showMessageDialog(null, "Minden mező kitöltése kötelező!", "Figyelmeztetés",2);
        }

    }
}
