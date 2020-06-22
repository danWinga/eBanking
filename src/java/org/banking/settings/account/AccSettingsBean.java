/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banking.settings.account;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.LazyDataModel;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author dan
 */
@ManagedBean(name = "accSettingsBean")

@ViewScoped
public class AccSettingsBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private AccountOpenType newAccOpenType = new AccountOpenType();
    private AccountOpenType selectedAccType = new AccountOpenType();
    private AccountProduct selectedProduct = new AccountProduct();
    private ProductAccSetting selectedSettings  = new ProductAccSetting();
    private ProductAccSetting newSettings  = new ProductAccSetting();
    private AccountProduct newProduct = new AccountProduct ();
    private AccSettings newAccSetting =new  AccSettings();
    private AccSettings selectedAccSetting =new  AccSettings();
    private ArrayList<AccountOpenType> accOpenTypeList;
    private ArrayList<AccountProduct> accProductList;
    private ArrayList<AccSettings> accSettingList;
    private List<AccSettings> selectedProdAccSet;
    private ArrayList<AccSettings> productSettingList ;
    private ArrayList<AccSettings> accSetList;
    
    
    
    public SelectItem[] accTypes;// personal account , Bussiness account etc
    public SelectItem[] accSettingTypes;
    public SelectItem[] productType; // Personal -: individual, joint 
    private LazyDataModel<AccountProduct> accountProductModel;
    
       // FacesContext facesContext = FacesContext.getCurrentInstance();
        //HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
        FacesMessage msg = null;
        
        FacesContext facesContext;
        HttpSession httpSession;
        
        public  AccSettingsBean(){
                facesContext = FacesContext.getCurrentInstance();
                httpSession = (HttpSession)facesContext.getExternalContext().getSession(true);
                //selectedAccSetting = new AccSettings();
                //selectedProduct = new AccountProduct();
                
        }
        
        @PostConstruct
        public void init(){
            
            accSetList = service.getAccSList();

        }
        public ArrayList<AccSettings> getAccSetList() {
                //accSetList = new ArrayList<AccSettings>();
                if(( selectedProduct.getProductCode()!=null)){
                        service.setProdCode(selectedProduct.getProductCode());
                        accSetList = service.getAccSList();
                }
                
                return accSetList;
        }
        public void setAccSetList(ArrayList<AccSettings> accSetList) {
                this.accSetList = accSetList;
        }
    
    
  /**
     * @return 
    @PostConstruct
    public void init(){
        newAccOpenType = new AccountOpenType();
        selectedAccType = new AccountOpenType();
        selectedProduct = new AccountProduct();
        selectedSettings = new ProductAccSetting();
        newSettings = new ProductAccSetting();
        newProduct = new AccountProduct();
        newAccSetting = new AccSettings();
        selectedAccSetting = new AccSettings();
        newSettings.setUid(0);
        
    }**/
        @ManagedProperty("#{accSettingService}")
        private AccSettingService service;
        
        public void setService(AccSettingService service) {
        this.service = service;
    }
    
        public AccountOpenType getNewAccOPenType(){
                return newAccOpenType;
        }
        public void setNewAccountOpenType(AccountOpenType open){
                this.newAccOpenType = open;
        }
    
        public AccountOpenType getSelectedAccType(){
                return selectedAccType;
        }
        public void setSelectedAccType(AccountOpenType selected){
                this.selectedAccType = selected;
        }
    
        public AccountProduct getSelectedProduct(){
                return selectedProduct;
        }
        public void setSelectedProduct(AccountProduct selected){
                this.selectedProduct = selected;
        }
    
        public AccountProduct getNewProduct(){
                return newProduct;
        }
        public void setNewProduct(AccountProduct newProd){
                this.newProduct = newProd;
        }
    
        public ProductAccSetting getSelectedSettings(){
                return selectedSettings;
        }
        public void setSelectedSettings(ProductAccSetting setProd){
                this.selectedSettings = setProd;
        }
    
        public ProductAccSetting getNewSettings(){
                return newSettings;
        }
        public void setNewSettings(ProductAccSetting newSet){
                this.newSettings = newSet;
        }
        public LazyDataModel<AccountProduct>getAccountProductModel(){
                return accountProductModel;
        }
        public AccSettings getNewAccSetting(){
                return newAccSetting;
        }
        public void setNewAccSetting(AccSettings newAccSetting){
                this.newAccSetting = newAccSetting;
        }
    
        public AccSettings getSelectedAccSetting(){
                return selectedAccSetting;
        }
        public void setSelectedAccSetting(AccSettings selectedAccSetting){
                this.selectedAccSetting = selectedAccSetting;
        }
        public List<AccSettings> getSelectedProdAccSet() {
                return selectedProdAccSet;
        }

        public void setSelectedProdAccSet(List<AccSettings> selectedProdAccSet) {
                this.selectedProdAccSet = selectedProdAccSet;
        }
        public ArrayList<AccountProduct>getAccProductList() {
                if(accProductList == null){
                        accProdList();
                }
                return accProductList;
        }
    
    /***** Product List
     * selectedAccSetting
     * @return accProductList
     */
    
    public void accProdList(){
        System.out.println("reading data.........");
        accProductList = new ArrayList<AccountProduct>();
        String query = "SELECT * FROM vw_accProducts ;";// '"+selectedMember.getMemberNo()+"'
        try{
                         DatabaseBean db = new DatabaseBean();
                         Connection con = db.DBconnect();
                         PreparedStatement stmt = con.prepareStatement(query);
                         stmt.setFetchSize(100);
                         ResultSet rs  = db.preparedState(stmt);
                             while(rs.next()){
                                 
                           
                                accProductList.add(new AccountProduct(rs.getInt("id"),rs.getString("productCode"),rs.getString("productName"),
                                rs.getString("accName"), rs.getString("accDesc")));
                                 
                             }
                             rs.close();
                         db.cleanup();
                         
        }catch(SQLException e){
                            msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sql Error"," Please contact admin!");
                            System.out.println("Product Account list  error in SQLE !"+e);
        }catch(Exception e ){
                            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                            System.out.println(" error in EXception !"+e);
                    }
                            
        
        
    }
    public void refresh(){
                accSettingList = null;
        }
    
    public ArrayList<AccSettings>getAccSettingList(){
            
            System.out.println("reading data.........");
            String query = "SELECT * FROM vw_accSettingsGroup ";
            if( ( selectedProduct.getProductCode()!=null) ){
                            query += " WHERE productcode = '"+ selectedProduct.getProductCode().trim() +"'";
            }
                    query += ";";
            
              if(accSettingList == null){
                        AccSetList(query);
                }
          
        
            return accSettingList;
    }
    public ArrayList<AccSettings>getProductSettingList(){
            
            System.out.println("reading data.........");
            String query = "SELECT * FROM vw_accSettingsGroup ";
            if( ( selectedProduct.getProductCode()!=null) ){
                            query += " WHERE productcode = '"+ selectedProduct.getProductCode().trim() +"'";
                            query += ";";
            }
               
                        AccSetList(query);
                        
            
            return productSettingList;
    }
    
            public void AccSetList(String query){
                        productSettingList = new ArrayList<AccSettings>();
                        try {
                                DatabaseBean db = new DatabaseBean();
                                Connection con = db.DBconnect();
                                PreparedStatement stmt = con.prepareStatement(query);
                                stmt.setFetchSize(100);
                                ResultSet rs=db.preparedState(stmt);

                                while(rs.next()){
                                       productSettingList.add(new AccSettings(rs.getString("accgroupCode"),
                                               rs.getString("productCode"),rs.getString("productName"), 
                                               rs.getString("accSettingCode"),rs.getString("settingName"),
                                               rs.getInt("amount")));
                                }
                                rs.close();
                                db.cleanup();                    
                        }catch(SQLException e){
                                System.out.println("Error :"+ e);
                                msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sql Error"," Please contact admin!");
                                System.out.println("Product Account list  error in SQLE !"+e);
                        }catch(Exception e){
                                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Saving Error"," Fill in the missing fields!");
                                System.out.println(" error in EXception !"+e);

                        }
            }
    
    public void addNewSettingUi(){
        newSettings.setUid(1);
    }
    public void editSettingUi(){
        newSettings.setUid(2);
    }
    public void saveSettingUi(){
        newSettings.setUid(0);
    }
    
    public void testSave(){
        System.out.println("*****Adding new accSettings*****");
         String sql = "INSERT INTO accSettings (accSettingCode, productCode, counterWithD, ATMwithdrawal,accService,chequeProcessing,minimumBalance, auditTime)"
                        + " VALUES ( '"+newSettings.getAccSettingCode().trim() +"', '"+ newSettings.getProductCode().trim() +"', "
                + "'"+ newSettings.getCounterWithD() +"', '"+ newSettings.getAtmwithdrawal() +"','"+ newSettings.getAccService() +"','"+ newSettings.getMinBalance() +"',now());";
         newSettings.addNewAccSettings(sql);
    }
    public void AddNewAccSetting(){ 
        System.out.println("*****Adding new accSettings*****");
         String sql = "INSERT INTO accSettings (accSettingCode, productCode, counterWithD, ATMwithdrawal,accService,chequeProcessing,minimumBalance, auditTime)"
                        + " VALUES ( '"+newSettings.getAccSettingCode().trim() +"', '"+ newSettings.getProductCode().trim() +"', "
                + "'"+ newSettings.getCounterWithD() +"', '"+ newSettings.getAtmwithdrawal() +"','"+ newSettings.getAccService() +"','"+ newSettings.getMinBalance() +"',now());";
         
          try{
            DatabaseBean db = new DatabaseBean();
            db.insert(sql);
            System.out.println("*****sql*****"+ sql);
        }catch (Exception e){
              System.out.println("Exception: "+ e);
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Error! ", ":Contact Admin  then try again"));
        }
       System.out.println("sql:"+ sql);
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "successfull! ", ":New File has been created"));
        
    }
    
    /****** Account Types
      * 
      * ComboBox for Account Types e.g
      * personal account , Business account etc
      * @return accType
      * 
      *******/
     
     public SelectItem[] getAccTypes() {
                String query = "SELECT acctypeCode, accName FROM accOpeningType;";

                List list = new ArrayList();
                TreeMap <String, String>tMap = new TreeMap<String, String>();
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs=db.preparedState(stmt);
                        int z = 0;
                        while(rs.next()){
                                list.add(z++);
                                tMap.put(rs.getString("acctypeCode").toString(), rs.getString("accName").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("Account Types  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("Account Types - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No Account >>".toString());
                        list.add("---".toString());
                }
                accTypes = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        accTypes[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return accTypes;
        }
     
     
     /********* Product Types *********
      * **  ComboBox for Product Types e.g
      * * Personal -: individual, joint 
      * * 
     * @param str
      * @return productType
      */
     public SelectItem[] getProductTypes() {
         
        String prod = selectedAccType.getAcctypeCode();
        if(prod == null)
            prod ="PC001";
        System.out.println("*********account product code1 :"+prod);
                String query = "SELECT  groupCode, groupName FROM vw_accProducts where acctypeCode = '"+prod.trim()+"' ;";///where acctypeCode = '"+selectedAccType.getAcctypeCode().trim()+"'
               /* String sql = "SELECT accProductsGroup.groupCode,accProductsGroup.groupName,accProducts.productCode," +
                    "accProducts.productName,accProducts.acctypeCode" +
                    "FROM accProductsGroup" +
                    "INNER JOIN accProducts ON accProducts.groupCode = accProductsGroup.groupCode;";
*/
                                    List list = new ArrayList();
                TreeMap <String, String>tMap = new TreeMap<String, String>();
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs=db.preparedState(stmt);
                        int z = 0;
                        while(rs.next()){
                                list.add(z++);
                                tMap.put(rs.getString("groupCode").toString(), rs.getString("groupName").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("category Types  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("category Types - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No category Types >>".toString());
                        list.add("---".toString());
                }
                productType = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        productType[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return productType;
        }
     
     public SelectItem[] getAccSettingTypes() {
                String query = "SELECT accSettingCode, settingName FROM vw_accSettingsGroup;";

                List list = new ArrayList();
                TreeMap <String, String>tMap = new TreeMap<String, String>();
                try{
                        DatabaseBean db = new DatabaseBean();
                        Connection con = db.DBconnect();
                        PreparedStatement stmt = con.prepareStatement(query);
                        ResultSet rs=db.preparedState(stmt);
                        int z = 0;
                        while(rs.next()){
                                list.add(z++);
                                tMap.put(rs.getString("accSettingCode").toString(), rs.getString("settingName").toString());
                        }
                        rs.close();
                        db.cleanup();
                }catch(SQLException e){
                        System.out.println("settings Types  - Combobox SQLException: "+ e);
                }catch(Exception e){
                        System.out.println("Settings Types - Combobox  Exception: "+ e);
                        System.err.println(e);
                }
                if (list.isEmpty()) {
                        tMap.put("--".toString(), "<<No Settings >>".toString());
                        list.add("---".toString());
                }
                accSettingTypes = new SelectItem[list.size()];
                int i = 0;

                Set set = tMap.entrySet();
                Iterator ite = set.iterator();
                while(ite.hasNext()) { 
                        Map.Entry me = (Map.Entry)ite.next(); 
                        accSettingTypes[i++] = new SelectItem(me.getKey().toString(), me.getValue().toString());
                } 
                return accSettingTypes;
        }
     
     
        public void viewData(){
                 System.out.println("********************** Data**" + selectedAccType.getAcctypeCode());
        }
        public void onRowSelectAccSettings(SelectEvent event) {
                FacesMessage msg = new FacesMessage("Car Selected", ((AccSettings) event.getObject()).getAccgroupCode());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println("AccSetting Selected :"+ ((AccSettings) event.getObject()).getAccgroupCode());
        }   
 
        public void onRowUnselectAccSettings(UnselectEvent event) {
                FacesMessage msg = new FacesMessage("Car Unselected", ((AccSettings) event.getObject()).getAccgroupCode());
                FacesContext.getCurrentInstance().addMessage(null, msg);
                System.out.println("AccSetting Selected :"+ ((AccSettings) event.getObject()).getAccgroupCode());
        }
        
        public void onRowSelect(SelectEvent event ){
                String srt = ((AccountProduct) event.getObject()).getProductCode();
                selectedProduct.setProductCode(srt);
                msg = new FacesMessage("account Selected", ((AccountProduct) event.getObject()).getProductCode());
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }
       
      
      public void onRowEdit(RowEditEvent event) {
          //selectedAccSetting = ((AccSettings) event.getObject());
            FacesMessage msgx = new FacesMessage("Car Edited", ((AccSettings) event.getObject()).getAccgroupCode());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            System.out.println("AccGroupCode :"+ ((AccSettings) event.getObject()).getAccgroupCode());
            int intAmount = ((AccSettings) event.getObject()).getAmount();
            editAmount(intAmount);
            System.out.println(".................................................:");
            System.out.println(".................................................:");
           
            
            System.out.println("Amount  :"+ intAmount);
            try {
                       //String mysql = "UPDATE accSettingsGroup SET productCode = '"+ selectedAccSetting.getProductCode().trim() +"', amount = "+ selectedAccSetting.getAmount() +" WHERE accgroupCode = '"+ selectedAccSetting.getAccgroupCode().trim()+"';";
                        DatabaseBean db = new DatabaseBean();
                        db.insert("UPDATE accSettingsGroup SET productCode ='"+ ((AccSettings) event.getObject()).getProductCode().trim() +"' , amount = "+ intAmount +" WHERE accgroupCode = '"+ ((AccSettings) event.getObject()).getAccgroupCode().trim() +"';");
                        db.cleanup();
                        FacesMessage msg = new FacesMessage(((AccSettings) event.getObject()).getProductName() +",  "+ "New Settings is KShs."+ ((AccSettings) event.getObject()).getAmount() );  
                        FacesContext.getCurrentInstance().addMessage(null, msg); 
                } catch(SQLException e){
                        System.out.println("SQL Error In Product account Settings: "+ e);
                        FacesMessage msg = new FacesMessage("Sorry! ", "New Settings update failed");  
                        FacesContext.getCurrentInstance().addMessage(null, msg); 
                } catch(Exception e){
                        System.out.println("Exception Error In account Product settings: "+ e);
                        FacesMessage msg = new FacesMessage("Sorry! ", "New Settings update failed");  
                        FacesContext.getCurrentInstance().addMessage(null, msg); 
                }
            

     }
     
     public void editAmount(int amount){
         selectedAccSetting.setAmount(amount);
         System.out.println("new Amount is:"+ selectedAccSetting.getAmount() );
     }
      
      public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((AccSettings) event.getObject()).getAccgroupCode());
        FacesContext.getCurrentInstance().addMessage(null, msg);
     }
      
    public void onCellEdit(CellEditEvent event) {
            Object oldValue = event.getOldValue();
            Object newValue = event.getNewValue();
        
         
            if(newValue != null && !newValue.equals(oldValue)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        
        
    }
     
     //private String processAccProduct;
              
                
               /** 
                public class AccSettings implements Serializable{
    
                   
                        private int id, amount;
                        private String accSetting, settingName,subAcc, accDesc,accgroupCode,productCode, productName;
                        private Date auditTime;

                         public AccSettings(){
                                amount = 0;
                                accgroupCode = "";
                                productCode = "";
                                accSetting = "";
                                settingName = "";
                         }


                        AccSettings(String accgroupCode, String productCode, String productName, String accSettingCode,
                                String settingName, int amount) {
                                this.accgroupCode = accgroupCode;
                                this.productCode = productCode;
                                this.productName = productName;
                                this.accSetting = accSettingCode;
                                this.settingName = settingName;
                                this.amount = amount;
                        }
                        public int getId(){
                                return id;
                        }
                        public  void setId(int id){this.id = id;}

                        public int getAmount(){return amount;}
                        public void setAmount(int amount){this.amount = amount;}

                        public String getAccSetting(){return accSetting;}
                        public void setAccSetting(String str){this.accSetting = str;}

                         public String getSettingName(){return settingName;}
                         public void setSettingName(String str){this.settingName = str;}

                         public String getSubAcc(){return subAcc;}
                         public void setSubAcc(String str){this.subAcc = str;}

                         public String getAccDesc(){return accDesc;}
                         public void setAccDesc(String str){this.accDesc = str;}

                         public String getAccgroupCode(){return accgroupCode;}
                         public void setAccgroupCode(String str){this.accgroupCode = str;}

                         public String getProductCode(){return productCode;}
                         public void setProductCode(String str){this.productCode = str;}

                         public String getProductName(){return productName;}
                         public void setProductName(String str){this.productName = str;}

                         public Date getAuditTime(){return auditTime;}
                         public void setAuditTime(Date str){this.auditTime = str;}
                         
                         
                        public void updateProductSetting(){
                                String mysql = "UPDATE accSettingsGroup SET productCode = '"+ selectedAccSetting.getProductCode().trim() +"', amount = "+ selectedAccSetting.getAmount() +" WHERE accgroupCode = '"+ selectedAccSetting.getAccgroupCode().trim()+"';";
                                System.out.println( "productCode:"+ selectedAccSetting.getProductCode().trim()+ "\t amount :"+  selectedAccSetting.getAmount() +"\t accgroupCode : " + selectedAccSetting.getAccgroupCode().trim() );
                                dbAction(mysql);
                        }
                        
                        public int dbAction(String mysql){
                                int affected = -1;
                                try{
                                        DatabaseBean db = new DatabaseBean();
                                        affected = db.insert(mysql);

                                        db.cleanup();
                                }catch(SQLException e){
                                        System.out.println("SQL Error In Product Charges Setting: "+ e);
                                }catch(Exception e){
                                        System.out.println("Exception Error In Product Charges Setting: "+ e);
                                }
                                return affected;
                        }
                
                }**/
     
      
}
