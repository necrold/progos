/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog3;

/**
 *
 * @author Alex
 */
 class IskolakClass {
    private int iskola_OM;
    private String iskola_Name, iskola_County, iskola_AddR,varos_NAME,fenntart_Name,feladat_Name;
    private Boolean iskola_Enable;
    
    public IskolakClass(int iskola_OM,String varos_NAME,String fenntart_Name,String feladat_Name, String iskola_Name,String iskola_County,String iskola_AddR,Boolean iskola_Enable){
    this.iskola_OM=iskola_OM;
    this.varos_NAME=varos_NAME;
    this.fenntart_Name=fenntart_Name;
    this.feladat_Name=feladat_Name;
    this.iskola_Name=iskola_Name;
    this.iskola_County=iskola_County;
    this.iskola_AddR=iskola_AddR;
    this.iskola_Enable=iskola_Enable;
    }
    
    public int getiskola_OM(){
        return iskola_OM;
     }   
    public String getvaros_NAME(){
        return varos_NAME;
     }   
    public String getfenntart_Name(){
        return fenntart_Name;
    }
    
    public String getfeladat_Name(){
        return feladat_Name;
    }
    
    public String getiskola_Name(){
        return iskola_Name;
    }
    
    public String getiskola_County(){
        return iskola_County;
    }
    
    public String getiskola_AddR(){
        return iskola_AddR;
    }
        public Boolean getiskola_Enable(){
        return iskola_Enable;
    }
    
}
