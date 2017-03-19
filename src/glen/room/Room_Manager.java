/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glen.room;


import glen.DBconnect;
import glen.System_Login;
import java.awt.Color;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import javax.swing.DefaultListModel;
import java.util.*;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Ashani Dikowita
 */
public class Room_Manager extends javax.swing.JFrame {

    /**
     * Creates new form Room_Interface
     */
    Connection con=DBconnect.ConnectDB();
    PreparedStatement pstatement=null;
    ResultSet Results =null;
    
    String ResArray[];
    String RoomSerArray[];
    String RoomDetails[]=new String[2];
    String RoomNoArray[];
    int ResId;
    
    public Room_Manager() {
        initComponents();
        groupButton();
        this.setLocationRelativeTo(null); //Setting to display in the center of screen
        RoomTogglePanel.setVisible(true);
        MainViewDetailsPanel.setVisible(true);
        CustomerDetailsPanel.setVisible(false);
        RoomDetailsPanel.setVisible(false);
        PackageDetailsPanel.setVisible(false);
        ReservationDetailsPanel.setVisible(false);
        RoomServicePanel.setVisible(false);
        PaymentsDetailsPanel.setVisible(false);
        RoomReportsPanel.setVisible(false);
        MaintainanceDetailsPanel.setVisible(false);
        StockDetailsPanel.setVisible(false);
        RoomLaundaryPanel.setVisible(false);
        
         ViewcustomerTableLoad();//calling of table load functions
         EditcustomerTableLoad();
         ViewRoomTableLoad();
         EditRoomTableLoad();
         ViewPackageTableLoad();
         EditReservationTableLoad();
         EditLaundaryTableLoad();
         updatePaymentTableLoad();
         RoomServiceTableLoad();
         RoomMaintainanceTableLoad();
         RoomDetailsTableLoad();
         date();

           //----------------------------------------------------------Date-----------------------------------------//       
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH); 
        int date = now.get(Calendar.DATE); 
        int year = now.get(Calendar.YEAR);
        String Date = date + " - " + month + " - " + year;
        RoomDatejLabel.setText(Date);
        
         //----------------------------------------------------------Time-----------------------------------------//

        int second=now.get(Calendar.SECOND);
        int min=now.get(Calendar.MINUTE);
        int hr=now.get(Calendar.HOUR_OF_DAY);
        String t=(hr+":"+min+":"+second);
        RoomTimejLabel.setText(t);
         
    }
    
     void date(){
        
                Thread clock = new Thread(){
                    public void run(){
                        for(;;){
                            Calendar now = Calendar.getInstance();
                int second=now.get(Calendar.SECOND);
                int min=now.get(Calendar.MINUTE);
                int hr=now.get(Calendar.HOUR);
                String t=(hr+":"+min+":"+second);
                RoomTimejLabel.setText(t);
                            try {
                                sleep(1000);
                            } catch (InterruptedException ex) {
                                java.util.logging.Logger.getLogger(Room_Manager.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                };
               clock.start(); 
    }
 
     //function to shift panels
    public void Roompanelshift(String buttonName){
        switch (buttonName) {
            case "CUSTOMER":
                CustomerDetailsPanel.setVisible(true);
                RoomDetailsPanel.setVisible(false);
                PackageDetailsPanel.setVisible(false);
                ReservationDetailsPanel.setVisible(false);
                RoomServicePanel.setVisible(false);
                PaymentsDetailsPanel.setVisible(false);
                RoomReportsPanel.setVisible(false);
                MaintainanceDetailsPanel.setVisible(false);
                StockDetailsPanel.setVisible(false);
                RoomLaundaryPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(false); 
                break;
            case "ROOM":
                CustomerDetailsPanel.setVisible(false);
                RoomDetailsPanel.setVisible(true);
                PackageDetailsPanel.setVisible(false);
                ReservationDetailsPanel.setVisible(false);
                RoomServicePanel.setVisible(false);
                PaymentsDetailsPanel.setVisible(false);
                RoomReportsPanel.setVisible(false);
                MaintainanceDetailsPanel.setVisible(false);
                StockDetailsPanel.setVisible(false);
                RoomLaundaryPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(false);
                break;
            case "PACKAGES":
                CustomerDetailsPanel.setVisible(false);
                RoomDetailsPanel.setVisible(false);
                PackageDetailsPanel.setVisible(true);
                ReservationDetailsPanel.setVisible(false);
                RoomServicePanel.setVisible(false);
                PaymentsDetailsPanel.setVisible(false);
                RoomReportsPanel.setVisible(false);
                MaintainanceDetailsPanel.setVisible(false);
                StockDetailsPanel.setVisible(false);
                RoomLaundaryPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(false);
                break;
            case "RESERVATIONS":
                CustomerDetailsPanel.setVisible(false);
                RoomDetailsPanel.setVisible(false);
                PackageDetailsPanel.setVisible(false);
                ReservationDetailsPanel.setVisible(true);
                RoomServicePanel.setVisible(false);
                PaymentsDetailsPanel.setVisible(false);
                RoomReportsPanel.setVisible(false);
                MaintainanceDetailsPanel.setVisible(false);
                StockDetailsPanel.setVisible(false);
                RoomLaundaryPanel.setVisible(false); 
                MainViewDetailsPanel.setVisible(false);
                break;
            case "ROOM SERVICE":
                CustomerDetailsPanel.setVisible(false);
                RoomDetailsPanel.setVisible(false);
                PackageDetailsPanel.setVisible(false);
                ReservationDetailsPanel.setVisible(false);
                RoomServicePanel.setVisible(true);
                PaymentsDetailsPanel.setVisible(false);
                RoomReportsPanel.setVisible(false);
                MaintainanceDetailsPanel.setVisible(false);
                StockDetailsPanel.setVisible(false);
                RoomLaundaryPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(false);
                break;               
            case "PAYMENTS":
                CustomerDetailsPanel.setVisible(false);
                RoomDetailsPanel.setVisible(false);
                PackageDetailsPanel.setVisible(false);
                ReservationDetailsPanel.setVisible(false);
                RoomServicePanel.setVisible(false);
                PaymentsDetailsPanel.setVisible(true);
                RoomReportsPanel.setVisible(false);
                MaintainanceDetailsPanel.setVisible(false);
                StockDetailsPanel.setVisible(false);
                RoomLaundaryPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(false);
                break;
             case "MAINTAINANCE":
                CustomerDetailsPanel.setVisible(false);
                RoomDetailsPanel.setVisible(false);
                PackageDetailsPanel.setVisible(false);
                ReservationDetailsPanel.setVisible(false);
                RoomServicePanel.setVisible(false);
                PaymentsDetailsPanel.setVisible(false);
                RoomReportsPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(false);
                MaintainanceDetailsPanel.setVisible(true);
                StockDetailsPanel.setVisible(false);
                RoomLaundaryPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(false);
                break;
            case "STOCK":
                CustomerDetailsPanel.setVisible(false);
                RoomDetailsPanel.setVisible(false);
                PackageDetailsPanel.setVisible(false);
                ReservationDetailsPanel.setVisible(false);
                RoomServicePanel.setVisible(false);
                PaymentsDetailsPanel.setVisible(false);
                RoomReportsPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(false);
                MaintainanceDetailsPanel.setVisible(false);
                StockDetailsPanel.setVisible(true);
                RoomLaundaryPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(false);
                break;
            case "LAUNDRY":
                CustomerDetailsPanel.setVisible(false);
                RoomDetailsPanel.setVisible(false);
                PackageDetailsPanel.setVisible(false);
                ReservationDetailsPanel.setVisible(false);
                RoomServicePanel.setVisible(false);
                PaymentsDetailsPanel.setVisible(false);
                RoomReportsPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(false);
                MaintainanceDetailsPanel.setVisible(false);
                StockDetailsPanel.setVisible(false);
                RoomLaundaryPanel.setVisible(true);
                MainViewDetailsPanel.setVisible(false);
                break;
            case "REPORTS":
                CustomerDetailsPanel.setVisible(false);
                RoomDetailsPanel.setVisible(false);
                PackageDetailsPanel.setVisible(false);
                ReservationDetailsPanel.setVisible(false);
                RoomServicePanel.setVisible(false);
                PaymentsDetailsPanel.setVisible(false);
                RoomReportsPanel.setVisible(true);
                MainViewDetailsPanel.setVisible(false);
                MaintainanceDetailsPanel.setVisible(false);
                StockDetailsPanel.setVisible(false);
                RoomLaundaryPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(false);
                break;
                   case "HOME":
                CustomerDetailsPanel.setVisible(false);
                RoomDetailsPanel.setVisible(false);
                PackageDetailsPanel.setVisible(false);
                ReservationDetailsPanel.setVisible(false);
                RoomServicePanel.setVisible(false);
                PaymentsDetailsPanel.setVisible(false);
                RoomReportsPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(false);
                MaintainanceDetailsPanel.setVisible(false);
                StockDetailsPanel.setVisible(false);
                RoomLaundaryPanel.setVisible(false);
                MainViewDetailsPanel.setVisible(true);
                break;
            default:
                break;   
        }
            
    }
    
   
       
    //---------------loading the customer table on view panel of the customer interface   
    public void ViewcustomerTableLoad(){
        try{
        String q ="Select room_customer_id as 'Customer ID',room_customer_name as 'Customer Name',room_customer_nic as 'Customer NIC',room_customer_address as Address,room_customer_telno as 'Telephone' from room_customer";
        pstatement=con.prepareStatement(q);
        Results=pstatement.executeQuery();
        ViewCustomerDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));  
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }
        
    }
//---------------loading the customer table on edit panel of the customer interface    
    public void EditcustomerTableLoad(){
        try{
        String q ="Select room_customer_id as 'Customer ID',room_customer_name as 'Customer Name',room_customer_nic as 'Customer NIC',room_customer_address as Address,room_customer_telno as 'Telephone No' from room_customer";
        pstatement=con.prepareStatement(q);
        Results=pstatement.executeQuery();
        EditCustomerDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));  
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }        
    }
//---------------loading the room table on edit panel of the room interface     
     public void EditRoomTableLoad(){
        try{
        String q ="Select room_roomdetails_roomno as 'Room No',room_roomdetails_status as Status,room_roomdetails_amount as Amount,room_roomdetails_NoofAdults as 'No of Adults',room_roomdetails_NoofChildren as 'No of Children',room_roomdetails_roomType as Type,room_roomdetails_headcount as 'head count',room_roomdetails_Description as Description from room_roomdetails";
        pstatement=con.prepareStatement(q);
        Results=pstatement.executeQuery();
        EditRoomDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));
     }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }        
    }
    //---------------loading the room table on View panel of the room interface 
     public void ViewRoomTableLoad(){
        try{
        String q ="Select room_roomdetails_roomno as 'Room No',room_roomdetails_NoofAdults as 'No of Adults',room_roomdetails_NoofChildren as 'No of Children',room_roomdetails_roomType as Type,room_roomdetails_amount as Amount from room_roomdetails where room_roomdetails_status ='Available'"+"";
        pstatement=con.prepareStatement(q);
        Results=pstatement.executeQuery();
        ViewRoomDetailsjTable.setModel(DbUtils.resultSetToTableModel(Results));
    
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }        
    }
     
     public void RoomDetailsTableLoad(){
        try{
        String q ="Select room_roomdetails_roomno as 'Room No',room_roomdetails_NoofAdults as 'No of Adults',room_roomdetails_NoofChildren as 'No of Children',room_roomdetails_amount as Amount,room_roomdetails_Description as Description from room_roomdetails";
        pstatement=con.prepareStatement(q);
        Results=pstatement.executeQuery();
        RoomDescriptionTable.setModel(DbUtils.resultSetToTableModel(Results));
    
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }        
    }
     //---------------------loading the package table on view panel of the package interface
     public void ViewPackageTableLoad(){
        try{
        String q ="Select room_package_packageid as 'Package ID',room_package_description as 'Description',room_package_status as 'Status',room_package_amount as 'Amount',room_package_NoofAdults as 'No of Adults',room_package_NoofChildren as 'No of Children',room_package_roomno as 'Room No' from room_package";
        pstatement=con.prepareStatement(q);
        Results=pstatement.executeQuery();
              EditPackageDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));
        String r ="Select room_package_packageid as 'Package ID',room_package_description as 'Description',room_package_status as 'Status',room_package_amount as 'Amount',room_package_NoofAdults as 'No of Adults',room_package_NoofChildren as 'No of Children',room_package_roomno as 'Room No' from room_package";
        pstatement=con.prepareStatement(r);
        Results=pstatement.executeQuery();
              ViewPackageDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }        
    }
    
    public void EditReservationTableLoad(){
        try{
        String q ="Select room_roomreservation_resid as 'Reservation ID',room_roomreservation_cusnic as 'Customer NIC',room_roomreservation_cusname as 'Customer Name' ,room_roomreservation_date as 'Date',room_roomreservation_amount as 'Amount',room_roomreservation_type as 'Package/Room',room_roomreservation_checkin as 'Check In',room_roomreservation_checkout 'Check Out',room_roomreservation_meals as Meals from room_roomreservation";
        pstatement=con.prepareStatement(q);
        Results=pstatement.executeQuery();
              ViewReservationDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));
    
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }        
    }
    
     public void EditLaundaryTableLoad(){
        try{
        String q ="Select room_roomlaundary_lid as 'Laundary ID',room_roomlaundary_cusnic as 'Customer NIC',room_roomlaundary_cusName as 'Customer Name',room_roomlaundary_Desc as 'Description',room_roomlaundary_amount as 'Amount',room_roomlaundary_date as 'Date',room_roomlaundary_Weight as Weight from room_roomlaundary";
        pstatement=con.prepareStatement(q);
        Results=pstatement.executeQuery();
              AddLaundaryDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));
    
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }        
    }
     public void updatePaymentTableLoad(){
        try{
        String q ="Select room_payments_cusnic as 'Customer NIC',room_payments_method as 'Payment Method',room_payment_amount as 'Amount',room_payments_date as 'Date',room_payments_desc as 'Description' from room_payments";
        pstatement=con.prepareStatement(q);
        Results=pstatement.executeQuery();
              UpdateRoompaymentDetailsjTable.setModel(DbUtils.resultSetToTableModel(Results));
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }        
    }
     
    public void RoomServiceTableLoad(){
        try{
         String q ="Select room_roomserviceorders_cusnic as 'Customer NIC',room_roomserviceorders_roomno as 'Room No',room_roomserviceorders_noofplates as 'No of plates',room_roomserviceorders_date as 'Date',room_roomserviceorders_price as 'Price',room_roomserviceorder_status as 'Status',room_roomserviceorders_package as 'Package',room_roomserviceorders_Description as Description from room_roomserviceorders";
        pstatement=con.prepareStatement(q);
        Results=pstatement.executeQuery();
              RoomServiceOrderrDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));    
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }        
    }
    public void RoomMaintainanceTableLoad(){
        try{
        String q="Select room_roommaintainance_product as 'Item Name',room_roommaintainance_Quantity as 'Available Quantity',room_roommaintainance_Used 'Quantity Used'from room_roommaintainance";
        pstatement=con.prepareStatement(q);
        Results=pstatement.executeQuery();
               RoomMaintainancejTable.setModel(DbUtils.resultSetToTableModel(Results));    
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }        
    }
   
    
    public double getSum()
     {
         int rowcount=jTableToReserveRoom.getRowCount();
         double sum=0;
         for(int i=0;i<rowcount;i++){
             String val=jTableToReserveRoom.getValueAt(i,1).toString();
              sum=sum+Double.parseDouble(val);
         }
         return sum;
     }
    public double getLaundrySum()
     {
         int rowcount=LaundryOrdersjTable.getRowCount();
         double sum=0;
         for(int i=0;i<rowcount;i++){
             String val=LaundryOrdersjTable.getValueAt(i,1).toString();
              sum=sum+Double.parseDouble(val);
         }
         return sum;
     }
    public double getPackSum()
     {
         int rowcount=jTableToReservePackage.getRowCount();
         System.out.println(rowcount);
         double sum=0;
         for(int i=0;i<rowcount;i++){
             String val=jTableToReservePackage.getValueAt(i,1).toString();
              sum=sum+Double.parseDouble(val);
              System.out.println(val);
         }
         return sum;
     }
    
      public double getRoomServiceSum()
     {
         int rowcount=RoomServicejTable.getRowCount();
         System.out.println(rowcount);
         double sum=0;
         for(int i=0;i<rowcount;i++){
             String val=RoomServicejTable.getValueAt(i,1).toString();
              sum=sum+Double.parseDouble(val);
              System.out.println(val);
         }
         return sum;
     }
      
       public double getRoomServiceCustomizedSum()
     {
         int rowcount=CustomizeRequestedjTable.getRowCount();
         System.out.println(rowcount);
         double sum=0;
         for(int i=0;i<rowcount;i++){
             String val=CustomizeRequestedjTable.getValueAt(i,1).toString();
              sum=sum+(Double.parseDouble(val));
              System.out.println(sum);
         }
         return sum;
     }
    
    
    public double getTotal(){
        Double RP=Double.parseDouble(AddRoomPaymentTextFields.getText());
        Double RSamount=Double.parseDouble(AddRoomPaymentRSamountValueTextFields.getText());
        Double Lamount=Double.parseDouble(AddRoomPaymentLaValueTextFields.getText());
        Double Totalamount=RP+RSamount+Lamount;
        return Totalamount;
    }
    public String Selectmeal(){
    if(WithMealsjRadioButton.isSelected())
        {
            String meals="With Meals";
            return meals;
        }
        if(WithoutMealsjRadioButton.isSelected())
        {
            String meals="Without Meals";
            return meals;
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Select Whether you need meals!");
            return null;
        }
    }
    private void groupButton() {

    ButtonGroup bg1 = new ButtonGroup( );

    bg1.add(WithMealsjRadioButton);
    bg1.add(WithoutMealsjRadioButton);
    }
    /**
     * This 
     * method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPaneRoom = new javax.swing.JDesktopPane();
        RoomManagerPanel = new javax.swing.JPanel();
        RoomHeaderPanel = new javax.swing.JPanel();
        heasderLogoutButton = new javax.swing.JButton();
        HeaderLogoLabel = new javax.swing.JLabel();
        RoomTimejLabel = new javax.swing.JLabel();
        RoomDatejLabel = new javax.swing.JLabel();
        HomeRoomjButton = new javax.swing.JButton();
        RoomButtonPanel = new javax.swing.JPanel();
        CustomerButton = new javax.swing.JButton();
        RoomButton = new javax.swing.JButton();
        PackagesButton = new javax.swing.JButton();
        ReservationButton = new javax.swing.JButton();
        RoomServiceButton = new javax.swing.JButton();
        RoomLaundaryButton = new javax.swing.JButton();
        MaintainanceButton = new javax.swing.JButton();
        RoomStockButton = new javax.swing.JButton();
        RoomPaymentsButton = new javax.swing.JButton();
        RoomReportsButton = new javax.swing.JButton();
        RoomTogglePanel = new javax.swing.JPanel();
        MainViewDetailsPanel = new javax.swing.JPanel();
        ReserveRoomjButton = new javax.swing.JButton();
        PackageReservationjButton = new javax.swing.JButton();
        WelcomejLabel = new javax.swing.JLabel();
        RoomPaymentsjButton = new javax.swing.JButton();
        laundryOrdersjButton = new javax.swing.JButton();
        RoomSErviceOrdersjButton = new javax.swing.JButton();
        CustomerDetailsPanel = new javax.swing.JPanel();
        CustomerDetailsTabbedPane = new javax.swing.JTabbedPane();
        viewCustomerpanel = new javax.swing.JPanel();
        ViewCustomerDetailsScrollPane = new javax.swing.JScrollPane();
        ViewCustomerDetailsTable = new javax.swing.JTable();
        ViewCustomerDetailsSearchButton = new javax.swing.JButton();
        viewcustomerDetailssearchTextField = new javax.swing.JTextField();
        viewcustomerDetailsSearchLabel = new javax.swing.JLabel();
        ViewRoomCustomerNameLabel = new javax.swing.JLabel();
        ViewRoomCustomerNICLabel = new javax.swing.JLabel();
        ViewRoomCustomerNICTextField = new javax.swing.JTextField();
        ViewRoomCustomerNameTextField = new javax.swing.JTextField();
        ViewRoomCustomerAddressLabel = new javax.swing.JLabel();
        ViewRoomCustomerTelNoLabel = new javax.swing.JLabel();
        ViewRoomCustomerTelNoTextField = new javax.swing.JTextField();
        ViewRoomCustomerAddressTextField = new javax.swing.JTextField();
        ViewCustomerDetailsOrderRoomButton = new javax.swing.JButton();
        ViewCustomerDetailsLaundaryButton = new javax.swing.JButton();
        ViewCustomerDetailsOrderRoomServiceButton = new javax.swing.JButton();
        ViewCustomerDetailsOrderPackageButton = new javax.swing.JButton();
        ViewCustomerDetailsResetButton = new javax.swing.JButton();
        ViewCustomerDetailsSearchButton2 = new javax.swing.JButton();
        EditCustomerPanel = new javax.swing.JPanel();
        EditRoomCustomerNameLabel = new javax.swing.JLabel();
        EditRoomCustomerNICLabell = new javax.swing.JLabel();
        EditRoomCustomerAddressLabel = new javax.swing.JLabel();
        EditRoomCustomerTelNoLabel = new javax.swing.JLabel();
        EditRoomCustomerNameTextField = new javax.swing.JTextField();
        EditRoomCustomerNICTextField = new javax.swing.JTextField();
        EditRoomCustomerAddressTextField = new javax.swing.JTextField();
        EditRoomCustomerTElNoTextField = new javax.swing.JTextField();
        EditCustomerDetailsUpdateButton = new javax.swing.JButton();
        EditCustomerDetailsScrollPane = new javax.swing.JScrollPane();
        EditCustomerDetailsTable = new javax.swing.JTable();
        EditCustomerDetailsIDvalueLabel = new javax.swing.JLabel();
        EditCustomerDetailsLabel = new javax.swing.JLabel();
        EditCustomerDetailsDeleteButton = new javax.swing.JButton();
        EditCustomerDetailsSearchButton = new javax.swing.JButton();
        EditcustomerDetailsSearchLabel = new javax.swing.JLabel();
        EditcustomerDetailssearchTextField = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        EditCustomerDetailsResetButton = new javax.swing.JButton();
        RoomDetailsPanel = new javax.swing.JPanel();
        RoomDetailsTabbedPane = new javax.swing.JTabbedPane();
        ViewRoomDetailsPanel = new javax.swing.JPanel();
        ViewRoomDetailsScrollPane = new javax.swing.JScrollPane();
        ViewRoomDetailsjTable = new javax.swing.JTable();
        ViewRoomDetailsCheckAvailabiltyButton = new javax.swing.JButton();
        ViewRoomDetailsNoofAdultsTextField = new javax.swing.JTextField();
        ViewRoomDetailsNoofAdultsjLabel = new javax.swing.JLabel();
        ViewRoomDetailsNoofChildrenjLabel = new javax.swing.JLabel();
        ViewRoomDetailsNoChildrenTextField = new javax.swing.JTextField();
        ViewRoomDetailsCheckInjLabel = new javax.swing.JLabel();
        RoomCheckInjDateChooser = new com.toedter.calendar.JDateChooser();
        RoomCheckoutjDateChooser = new com.toedter.calendar.JDateChooser();
        CheckoutjLabel = new javax.swing.JLabel();
        ViewRoomDetailsReserveRoomButton = new javax.swing.JButton();
        ViewRoomDetailsRoomTypejLabel = new javax.swing.JLabel();
        ViewRoomDetailsjComboBox = new javax.swing.JComboBox<>();
        ViewRoomDetailsCusNICLabel = new javax.swing.JLabel();
        ViewRoomDetailsCusnicValuejLabel = new javax.swing.JLabel();
        ViewRoomDetailsNextButton1 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        ViewCustomerDetailsResetButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableToReserveRoom = new javax.swing.JTable();
        jButtonAddRoomDetailsToReserve = new javax.swing.JButton();
        jButtonRemoveRoomReserves = new javax.swing.JButton();
        jLabelTotalAmount = new javax.swing.JLabel();
        jButtonTotalAmount = new javax.swing.JButton();
        jTextFieldTotalAmountToPAy = new javax.swing.JTextField();
        ViewCustomerDetailsResetButton4 = new javax.swing.JButton();
        EditRoomDetailsPanel = new javax.swing.JPanel();
        EditRoomDetailsRoomNoLabel = new javax.swing.JLabel();
        EditRoomDetailsAmountLabel = new javax.swing.JLabel();
        EditRoomDetailsAmountTextField = new javax.swing.JTextField();
        EditRoomDetailsUpdateButton = new javax.swing.JButton();
        ViewRoomDetailsScrollPane1 = new javax.swing.JScrollPane();
        EditRoomDetailsTable = new javax.swing.JTable();
        EditRoomDetailsRoomNovalueLabel = new javax.swing.JLabel();
        EditRoomDetailsjLabel = new javax.swing.JLabel();
        EditRoomDetailsNoofAdultsTextField = new javax.swing.JTextField();
        EditRoomDetailsNoofchildrenjLabel = new javax.swing.JLabel();
        EditRoomDetailsNoofChildrenTextField = new javax.swing.JTextField();
        viewRoomDetailsSearchLabel1 = new javax.swing.JLabel();
        EditRoomDetailsSearchValueTextField = new javax.swing.JTextField();
        EditRoomDetailsSearchButton = new javax.swing.JButton();
        EditRoomDetailsRoomTypejLabel = new javax.swing.JLabel();
        EditRoomDetailsSubmitButton = new javax.swing.JButton();
        EditRoomDetailsjComboBox = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JSeparator();
        ViewCustomerDetailsResetButton3 = new javax.swing.JButton();
        RoomDescriptionjPanel = new javax.swing.JPanel();
        ViewRoomDetailsScrollPane2 = new javax.swing.JScrollPane();
        RoomDescriptionTable = new javax.swing.JTable();
        PackageDetailsPanel = new javax.swing.JPanel();
        PackageRoomTabbedPane = new javax.swing.JTabbedPane();
        ViewPackageDetailsPanel = new javax.swing.JPanel();
        ViewPackageDetailsScrollPane = new javax.swing.JScrollPane();
        ViewPackageDetailsTable = new javax.swing.JTable();
        ViewPackageDetailsSubmitButton = new javax.swing.JButton();
        ViewPackagesDetailsNextButton = new javax.swing.JButton();
        EditPAckageDetailsNoofDetailsjLabel1 = new javax.swing.JLabel();
        ViewPackageDetailsNoofAdultsTextField = new javax.swing.JTextField();
        EditPackageDetailsNoofchildrenjLabel1 = new javax.swing.JLabel();
        ViewPackageDetailsNoofChildrenTextField = new javax.swing.JTextField();
        ViewPackagesDetailsNextButton1 = new javax.swing.JButton();
        ViewPackageCustomerNICTextField = new javax.swing.JTextField();
        ViewRoomCustomerNICLabel1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        ViewPackageDetailsReservePackageButton = new javax.swing.JButton();
        ViewPackageDetailsResetButton = new javax.swing.JButton();
        jButtonRemovePackageReserves1 = new javax.swing.JButton();
        jButtonAddPackageDetailsToReserve = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableToReservePackage = new javax.swing.JTable();
        jTextFieldTotalAmountToPAypackage = new javax.swing.JTextField();
        jLabelTotalAmountRoom = new javax.swing.JLabel();
        jButtonTotalAmountPackage = new javax.swing.JButton();
        ViewPackDetailsCheckInjLabel3 = new javax.swing.JLabel();
        PackCheckInjDateChooser = new com.toedter.calendar.JDateChooser();
        PackCheckoutjLabel3 = new javax.swing.JLabel();
        PackCheckoutjDateChooser = new com.toedter.calendar.JDateChooser();
        jButtonTotalPackageReserves = new javax.swing.JButton();
        ViewRoomDetailsNextButton2 = new javax.swing.JButton();
        EditPackageDetailsPanel = new javax.swing.JPanel();
        EditPackagePackageIDLabel = new javax.swing.JLabel();
        EditPackagePackageDetailsLabel = new javax.swing.JLabel();
        EditPackageDetailsPackagestatusLabel = new javax.swing.JLabel();
        EditPackagePackageAmountLabel = new javax.swing.JLabel();
        EditPackageDetailsPackDetailsTextField = new javax.swing.JTextField();
        EditPackageDetailsAmountTextField = new javax.swing.JTextField();
        EditPackageDetailsSubmitButton = new javax.swing.JButton();
        EditPackageDetailsUpdateButton = new javax.swing.JButton();
        EditPackageDetailsDeleteButton = new javax.swing.JButton();
        EditpackagespackidValueLabel = new javax.swing.JLabel();
        ViewRoomPackageDetailsjComboBox = new javax.swing.JComboBox<>();
        EditPackageDetailsNoofchildrenjLabel = new javax.swing.JLabel();
        EditPAckageDetailsNoofDetailsjLabel = new javax.swing.JLabel();
        EditPackageDetailsNoofAdultsTextField = new javax.swing.JTextField();
        EditPackageDetailsNoofChildrenTextField = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        EditPackageDetailsRoomNoLabel = new javax.swing.JLabel();
        EditPackageDetailsRoomNoValueTextField = new javax.swing.JTextField();
        ViewPackageDetailsScrollPane1 = new javax.swing.JScrollPane();
        EditPackageDetailsTable = new javax.swing.JTable();
        ViewPackageDetailsResetButton1 = new javax.swing.JButton();
        ReservationDetailsPanel = new javax.swing.JPanel();
        ReservationDetailsTabbedPane = new javax.swing.JTabbedPane();
        makeReservationPanel = new javax.swing.JPanel();
        AddReservationDetailsSubmitButton = new javax.swing.JButton();
        AddReservationDetailsBackButton = new javax.swing.JButton();
        AddReservationDetailsCustomerIDLabel = new javax.swing.JLabel();
        ViewReservationDetailsCancelButton = new javax.swing.JButton();
        AddReservationDatejLabel = new javax.swing.JLabel();
        AddReservationCusNamejLabel = new javax.swing.JLabel();
        AddReservationCusNICTextField = new javax.swing.JTextField();
        AddReservationCusNameTextField = new javax.swing.JTextField();
        CheckoutjLabel1 = new javax.swing.JLabel();
        ViewRoomDetailsCheckInjLabel1 = new javax.swing.JLabel();
        AddReservationCheckInTextField = new javax.swing.JTextField();
        AddReservationCheckOutTextField = new javax.swing.JTextField();
        MakeReservationjDateChooser = new com.toedter.calendar.JDateChooser();
        jLabelRoomReseravtionAmount = new javax.swing.JLabel();
        MakeReservationFinalAmountjlabel = new javax.swing.JLabel();
        jLabelMakeReservationRoomCount = new javax.swing.JLabel();
        RoomCountjTextField = new javax.swing.JTextField();
        WithMealsjRadioButton = new javax.swing.JRadioButton();
        WithoutMealsjRadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        RoomReservationHeadCountjLabel = new javax.swing.JLabel();
        RoomReservationHeadCountTextField = new javax.swing.JTextField();
        jLabelRoomReseravtionTotalAmount = new javax.swing.JLabel();
        MakeReservationFinaTotallAmountjlabel = new javax.swing.JLabel();
        makePackageReservationPanel = new javax.swing.JPanel();
        AddPAckageReservationDetailsSubmitButton = new javax.swing.JButton();
        AddPAckageReservationDetailsBackButton = new javax.swing.JButton();
        AddPAckageReservationDetailsCustomerIDLabel = new javax.swing.JLabel();
        ViewPAckageReservationDetailsCancelButton = new javax.swing.JButton();
        AddPAckageReservationDatejLabel1 = new javax.swing.JLabel();
        AddPAckageReservationCusNamejLabel = new javax.swing.JLabel();
        AddPAckageReservationCusNICTextField = new javax.swing.JTextField();
        AddPAckageReservationCusNameTextField = new javax.swing.JTextField();
        CheckoutjLabel2 = new javax.swing.JLabel();
        ViewRoomDetailsCheckInjLabel2 = new javax.swing.JLabel();
        AddPAckageReservationCheckInTextField = new javax.swing.JTextField();
        AddPAckageReservationCheckOutTextField = new javax.swing.JTextField();
        MakePAckageReservationjDateChooser = new com.toedter.calendar.JDateChooser();
        jLabelPAckageRoomCount = new javax.swing.JLabel();
        PackageRoomCountjTextField = new javax.swing.JTextField();
        AddReservePackageAmountjLabel1 = new javax.swing.JLabel();
        jLabelPackAmount = new javax.swing.JLabel();
        ViewReservationDetailsjPanel = new javax.swing.JPanel();
        viewReservationDetailsScrollPane = new javax.swing.JScrollPane();
        ViewReservationDetailsTable = new javax.swing.JTable();
        ViewReservatiomDetailsSearchvaljLabel = new javax.swing.JLabel();
        viewReservationDetailssearchTextField = new javax.swing.JTextField();
        ViewReservationDetailsSearchButton = new javax.swing.JButton();
        ViewReservationDetailsPaymentsButton = new javax.swing.JButton();
        RoomServicePanel = new javax.swing.JPanel();
        RoomServiceTabbedPane = new javax.swing.JTabbedPane();
        AddCustomizedPackagesPanel = new javax.swing.JPanel();
        CustomizedroomServiceNiCjLabel = new javax.swing.JLabel();
        AddCustomizedRoomServiceAddjButton = new javax.swing.JButton();
        AddCustomizeCanceljButton = new javax.swing.JButton();
        CustomizedroomServicevalueNiC = new javax.swing.JLabel();
        AddCustomizeRoomServiceNoofPlatesjLabel = new javax.swing.JLabel();
        AddCustomizeRoomServiceNoofPlatesTextField = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        CustomizeSelectjTable = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        CustomizeRequestedjTable = new javax.swing.JTable();
        jButtonAddRoomServiceToReserve = new javax.swing.JButton();
        jButtonRemoveRoomToReserve = new javax.swing.JButton();
        jTextFieldRoomServiceTotalAmountToPAy = new javax.swing.JTextField();
        jLabelTotalAmountRoomService = new javax.swing.JLabel();
        jButtonTotalAmountRoomService = new javax.swing.JButton();
        RoomServiceDatejLabel1 = new javax.swing.JLabel();
        AddRoomServiceCustomizedPackagejDateChooser = new com.toedter.calendar.JDateChooser();
        AddRooomServiceRoomNojLabel1 = new javax.swing.JLabel();
        AddRoomServiceCustomizedRoomNoTextField = new javax.swing.JTextField();
        RoomServiceOrdersjPanel = new javax.swing.JPanel();
        RoomServiceOrderDetailsScrollPane = new javax.swing.JScrollPane();
        RoomServiceOrderrDetailsTable = new javax.swing.JTable();
        RoomServiceOrdersjLabel = new javax.swing.JLabel();
        RoomServiceSearchTextField = new javax.swing.JTextField();
        RoomServiceOrdersSearchjButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        RoomServicejTable = new javax.swing.JTable();
        AddRoomServicejButton = new javax.swing.JButton();
        AddRoomServiceDeletejButton = new javax.swing.JButton();
        RoomServiceTotalAmountjTextField = new javax.swing.JTextField();
        CAlculateRoomServiceTotaljButton = new javax.swing.JButton();
        RoomServiceTotaljLabel = new javax.swing.JLabel();
        AddRoomServiceDetailsBackToPAymentsButton = new javax.swing.JButton();
        PaymentsDetailsPanel = new javax.swing.JPanel();
        PaymentDetailsjTabbedPane = new javax.swing.JTabbedPane();
        RoomAddPaymentsPanel = new javax.swing.JPanel();
        AddRoomPaymentMethodjlabel = new javax.swing.JLabel();
        AddRoomPaymentAmountjlabel = new javax.swing.JLabel();
        AddRoomPaymentTextFields = new javax.swing.JTextField();
        AddRoomPaymentDescjlabel = new javax.swing.JLabel();
        AddRoomPaymentjComboBox = new javax.swing.JComboBox();
        AddPaymentsDescjScrollPane = new javax.swing.JScrollPane();
        AddRoomPaymentdescDesc = new javax.swing.JTextArea();
        AddRoomPaymentDueDatejLabel = new javax.swing.JLabel();
        AddRoomPaymentcodjlabel = new javax.swing.JLabel();
        AddPaymentsCODjComboBox = new javax.swing.JComboBox();
        AddRoomPaymentcusNICjLabel = new javax.swing.JLabel();
        AddRoomPaymentCusNICvaluejlabel = new javax.swing.JLabel();
        AddRoomPaymentDueDatejDateChooser = new com.toedter.calendar.JDateChooser();
        AddPaymentsDetailsSubmitButton = new javax.swing.JButton();
        AddPaymentRsIDjLabel = new javax.swing.JLabel();
        AddPaymentsRIDValuejLabel = new javax.swing.JLabel();
        AddPaymentsDetailsBackButton = new javax.swing.JButton();
        AddRoomPaymentRSAmountjlabel = new javax.swing.JLabel();
        AddRoomPaymentRSamountValueTextFields = new javax.swing.JTextField();
        AddPaymentsDetailsCheckRSButton = new javax.swing.JButton();
        AddPaymentsDetailsLaundryRSButton = new javax.swing.JButton();
        MakepaymentsLaundryAmountjLabel = new javax.swing.JLabel();
        AddRoomPaymentLaValueTextFields = new javax.swing.JTextField();
        AddPaymentCheckInjLabel = new javax.swing.JLabel();
        AddPaymentsCheckOutjLabel = new javax.swing.JLabel();
        AddRoomPaymentCIjLabel = new javax.swing.JLabel();
        AddRoomPaymentCOjlabel = new javax.swing.JLabel();
        MakepaymentsTotalAmountjLabel = new javax.swing.JLabel();
        AddRoomPaymentTotalValueTextFields = new javax.swing.JTextField();
        AddPaymentsDetailsLaundryRSButton1 = new javax.swing.JButton();
        AddPrintPaymentDetailsBackButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableToPrintPaymentDetails = new javax.swing.JTable();
        AddPaymentsDetailsAddToTableButton = new javax.swing.JButton();
        AddPaymentsDetailsCalculatorButton = new javax.swing.JButton();
        RoomupdatePaymentsPanel = new javax.swing.JPanel();
        RoomUpdatePaymentsNICjLabel = new javax.swing.JLabel();
        RoomUpdatePaymentsNICvalueTextfield = new javax.swing.JTextField();
        UpdateValuesgetValuejPanel = new javax.swing.JPanel();
        UpdateRoomPaymentPaymentMethodjLabel = new javax.swing.JLabel();
        UpdateRoomPaymentPaymentMethodjComboBox = new javax.swing.JComboBox();
        UpdateRoomPaymentAmountjLabel = new javax.swing.JLabel();
        UpdateRoomPaymentAmountTextFields = new javax.swing.JTextField();
        UpdateRoomPaymentCODjLabel = new javax.swing.JLabel();
        UpdateRoomPaymentCODjComboBox = new javax.swing.JComboBox();
        UpdateRoomPaymentDescLabel = new javax.swing.JLabel();
        UpdatePaneDescjScrollPane = new javax.swing.JScrollPane();
        UpdateRoomPaymenttDescription = new javax.swing.JTextArea();
        UpdateRoomPaymentDueDatejlabel = new javax.swing.JLabel();
        UpdateRoomPaymentDueDateTextFeilds = new javax.swing.JTextField();
        UpdatePaymentsDetailsSearchButton = new javax.swing.JButton();
        UpdatePaymentDetailsUpdateButton = new javax.swing.JButton();
        AddPaymentsdetailsjScrollPane1 = new javax.swing.JScrollPane();
        UpdateRoompaymentDetailsjTable = new javax.swing.JTable();
        RoomReportsPanel = new javax.swing.JPanel();
        StockDetailsPanel = new javax.swing.JPanel();
        RoomStockItemIDjLabel = new javax.swing.JLabel();
        RoomStockItemNamejLabel = new javax.swing.JLabel();
        RoomStockItemCategoryjLabel = new javax.swing.JLabel();
        RoomStockDepartmentjLabel = new javax.swing.JLabel();
        RoomStockOrderqtyjLabel = new javax.swing.JLabel();
        RoomStockUnitsjLabel = new javax.swing.JLabel();
        RoomStockunitsjTextField = new javax.swing.JTextField();
        RoomStockqtyjTextField = new javax.swing.JTextField();
        RoomStockDepartmentjTextField = new javax.swing.JTextField();
        RoomStockItemCategoryjComboBox = new javax.swing.JComboBox<>();
        RoomStockItemNamejComboBox = new javax.swing.JComboBox<>();
        RoomStockjComboBox = new javax.swing.JComboBox<>();
        RoomStockRequestOrderButton = new javax.swing.JButton();
        RoomstockClearButton = new javax.swing.JButton();
        MaintainanceDetailsPanel = new javax.swing.JPanel();
        MaintainanceScrollPane = new javax.swing.JScrollPane();
        RoomMaintainancejTable = new javax.swing.JTable();
        AddRoomMainatainanceItemNameTextField = new javax.swing.JTextField();
        AddRoomMainatinanceItemNameLabel = new javax.swing.JLabel();
        AddRoomMainatinanceAvailabelQtyLabel = new javax.swing.JLabel();
        AddRoomMainatianaceAvailableQtyTextField = new javax.swing.JTextField();
        AddRoomMainatinanceQtyUsedLabel = new javax.swing.JLabel();
        AddRoomMainatianaceQtyUsedTextField = new javax.swing.JTextField();
        MaintainanceDetailsUpdateButton = new javax.swing.JButton();
        MaintainanceDetailsSubmitButton = new javax.swing.JButton();
        RoomLaundaryPanel = new javax.swing.JPanel();
        AddLaundaryDetailsNICjLabel = new javax.swing.JLabel();
        AddLaundaryDetailsLaundaryCustomerNamejLabel = new javax.swing.JLabel();
        AddLaundaryDetailsLaundaryDescjLabel = new javax.swing.JLabel();
        AddLaundaryDetailsLaundaryamountjLabel = new javax.swing.JLabel();
        AddLaundaryDetailsLaundarycusnameTextField = new javax.swing.JTextField();
        AddLaundaryDetailsLaundaryDescTextField = new javax.swing.JTextField();
        AddLaundaryDetailsLaundaryAmountTextField = new javax.swing.JTextField();
        AddLaundaryDetailsNICValuejLabel = new javax.swing.JLabel();
        EditCustomerDetailsScrollPane1 = new javax.swing.JScrollPane();
        AddLaundaryDetailsTable = new javax.swing.JTable();
        AddLaundaryDetailsSubmitButton = new javax.swing.JButton();
        AddLaundaryDatejLabel = new javax.swing.JLabel();
        AddLaundaryjDateChooser = new com.toedter.calendar.JDateChooser();
        AddLaundaryDetailstelnojLabel = new javax.swing.JLabel();
        AddLaundaryDetailsLaundarytelnoTextField = new javax.swing.JTextField();
        AddLaundaryDetailsBackutton = new javax.swing.JButton();
        AddLaundaryDetailsSearchButton = new javax.swing.JButton();
        AddLaundaryDetailsBackToPAymentsButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        LaundryOrdersjTable = new javax.swing.JTable();
        LaundryDeletejButton = new javax.swing.JButton();
        LaundryAddjButton = new javax.swing.JButton();
        TotalLAundryjTextField = new javax.swing.JTextField();
        LaundryCalculateTotjButton = new javax.swing.JButton();
        AddLaundaryDetailsLaundaryWeightjLabel = new javax.swing.JLabel();
        RoomLaundryjComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPaneRoom.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPaneRoom.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RoomManagerPanel.setBackground(new java.awt.Color(255, 255, 255));
        RoomManagerPanel.setMinimumSize(new java.awt.Dimension(1024, 700));
        RoomManagerPanel.setPreferredSize(new java.awt.Dimension(1024, 700));
        RoomManagerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RoomHeaderPanel.setBackground(new java.awt.Color(255, 255, 255));
        RoomHeaderPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        RoomHeaderPanel.setPreferredSize(new java.awt.Dimension(1005, 100));
        RoomHeaderPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        heasderLogoutButton.setBackground(new java.awt.Color(255, 255, 255));
        heasderLogoutButton.setForeground(new java.awt.Color(51, 153, 255));
        heasderLogoutButton.setText("Log out");
        heasderLogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heasderLogoutButtonActionPerformed(evt);
            }
        });
        RoomHeaderPanel.add(heasderLogoutButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 13, 98, 30));
        RoomHeaderPanel.add(HeaderLogoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 43, -1, -1));

        RoomTimejLabel.setBackground(new java.awt.Color(255, 255, 255));
        RoomTimejLabel.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 12)); // NOI18N
        RoomTimejLabel.setForeground(new java.awt.Color(102, 153, 255));
        RoomTimejLabel.setText("Time");
        RoomHeaderPanel.add(RoomTimejLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 74, 98, -1));

        RoomDatejLabel.setBackground(new java.awt.Color(255, 255, 255));
        RoomDatejLabel.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 12)); // NOI18N
        RoomDatejLabel.setForeground(new java.awt.Color(102, 153, 255));
        RoomDatejLabel.setText("Date");
        RoomHeaderPanel.add(RoomDatejLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 49, 98, -1));

        HomeRoomjButton.setBackground(new java.awt.Color(255, 255, 255));
        HomeRoomjButton.setText("HOME");
        HomeRoomjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeRoomjButtonActionPerformed(evt);
            }
        });
        RoomHeaderPanel.add(HomeRoomjButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 43, 70, 40));

        RoomManagerPanel.add(RoomHeaderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1005, 100));

        RoomButtonPanel.setBackground(new java.awt.Color(255, 255, 255));
        RoomButtonPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        RoomButtonPanel.setMinimumSize(new java.awt.Dimension(180, 559));
        RoomButtonPanel.setPreferredSize(new java.awt.Dimension(198, 63));
        RoomButtonPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CustomerButton.setBackground(new java.awt.Color(51, 153, 255));
        CustomerButton.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        CustomerButton.setForeground(new java.awt.Color(255, 255, 255));
        CustomerButton.setText("CUSTOMER");
        CustomerButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CustomerButton.setMargin(new java.awt.Insets(2, 4, 2, 0));
        CustomerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CustomerButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CustomerButtonMouseExited(evt);
            }
        });
        CustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerButtonActionPerformed(evt);
            }
        });
        RoomButtonPanel.add(CustomerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 174, 42));

        RoomButton.setBackground(new java.awt.Color(51, 153, 255));
        RoomButton.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        RoomButton.setForeground(new java.awt.Color(255, 255, 255));
        RoomButton.setText("ROOM");
        RoomButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RoomButton.setMargin(new java.awt.Insets(2, 4, 2, 0));
        RoomButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RoomButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RoomButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RoomButtonMouseExited(evt);
            }
        });
        RoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomButtonActionPerformed(evt);
            }
        });
        RoomButtonPanel.add(RoomButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 66, 174, 42));

        PackagesButton.setBackground(new java.awt.Color(51, 153, 255));
        PackagesButton.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        PackagesButton.setForeground(new java.awt.Color(255, 255, 255));
        PackagesButton.setText("PACKAGES");
        PackagesButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        PackagesButton.setMargin(new java.awt.Insets(2, 4, 2, 0));
        PackagesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PackagesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PackagesButtonMouseExited(evt);
            }
        });
        PackagesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PackagesButtonActionPerformed(evt);
            }
        });
        RoomButtonPanel.add(PackagesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 119, 174, 42));

        ReservationButton.setBackground(new java.awt.Color(51, 153, 255));
        ReservationButton.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        ReservationButton.setForeground(new java.awt.Color(255, 255, 255));
        ReservationButton.setText("RESERVATIONS");
        ReservationButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ReservationButton.setMargin(new java.awt.Insets(2, 4, 2, 0));
        ReservationButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReservationButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReservationButtonMouseExited(evt);
            }
        });
        ReservationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReservationButtonActionPerformed(evt);
            }
        });
        RoomButtonPanel.add(ReservationButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 172, 174, 42));

        RoomServiceButton.setBackground(new java.awt.Color(51, 153, 255));
        RoomServiceButton.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        RoomServiceButton.setForeground(new java.awt.Color(255, 255, 255));
        RoomServiceButton.setText("ROOM SERVICE");
        RoomServiceButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RoomServiceButton.setMargin(new java.awt.Insets(2, 4, 2, 0));
        RoomServiceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RoomServiceButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RoomServiceButtonMouseExited(evt);
            }
        });
        RoomServiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomServiceButtonActionPerformed(evt);
            }
        });
        RoomButtonPanel.add(RoomServiceButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 227, 174, 42));

        RoomLaundaryButton.setBackground(new java.awt.Color(51, 153, 255));
        RoomLaundaryButton.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        RoomLaundaryButton.setForeground(new java.awt.Color(255, 255, 255));
        RoomLaundaryButton.setText("LAUNDRY");
        RoomLaundaryButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RoomLaundaryButton.setMargin(new java.awt.Insets(2, 4, 2, 0));
        RoomLaundaryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RoomLaundaryButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RoomLaundaryButtonMouseExited(evt);
            }
        });
        RoomLaundaryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomLaundaryButtonActionPerformed(evt);
            }
        });
        RoomButtonPanel.add(RoomLaundaryButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 280, 174, 42));

        MaintainanceButton.setBackground(new java.awt.Color(51, 153, 255));
        MaintainanceButton.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        MaintainanceButton.setForeground(new java.awt.Color(255, 255, 255));
        MaintainanceButton.setText("MAINTAINANCE");
        MaintainanceButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MaintainanceButton.setMargin(new java.awt.Insets(2, 4, 2, 0));
        MaintainanceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MaintainanceButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MaintainanceButtonMouseExited(evt);
            }
        });
        MaintainanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaintainanceButtonActionPerformed(evt);
            }
        });
        RoomButtonPanel.add(MaintainanceButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 333, 174, 42));

        RoomStockButton.setBackground(new java.awt.Color(51, 153, 255));
        RoomStockButton.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        RoomStockButton.setForeground(new java.awt.Color(255, 255, 255));
        RoomStockButton.setText("STOCK");
        RoomStockButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RoomStockButton.setMargin(new java.awt.Insets(2, 4, 2, 0));
        RoomStockButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RoomStockButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RoomStockButtonMouseExited(evt);
            }
        });
        RoomStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomStockButtonActionPerformed(evt);
            }
        });
        RoomButtonPanel.add(RoomStockButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 386, 174, 42));

        RoomPaymentsButton.setBackground(new java.awt.Color(51, 153, 255));
        RoomPaymentsButton.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        RoomPaymentsButton.setForeground(new java.awt.Color(255, 255, 255));
        RoomPaymentsButton.setText("PAYMENTS");
        RoomPaymentsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RoomPaymentsButton.setMargin(new java.awt.Insets(2, 4, 2, 0));
        RoomPaymentsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RoomPaymentsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RoomPaymentsButtonMouseExited(evt);
            }
        });
        RoomPaymentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomPaymentsButtonActionPerformed(evt);
            }
        });
        RoomButtonPanel.add(RoomPaymentsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 439, 174, 42));

        RoomReportsButton.setBackground(new java.awt.Color(51, 153, 255));
        RoomReportsButton.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        RoomReportsButton.setForeground(new java.awt.Color(255, 255, 255));
        RoomReportsButton.setText("REPORTS");
        RoomReportsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        RoomReportsButton.setMargin(new java.awt.Insets(2, 4, 2, 0));
        RoomReportsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RoomReportsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RoomReportsButtonMouseExited(evt);
            }
        });
        RoomReportsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomReportsButtonActionPerformed(evt);
            }
        });
        RoomButtonPanel.add(RoomReportsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 492, 174, 42));

        RoomManagerPanel.add(RoomButtonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 570));

        RoomTogglePanel.setBackground(new java.awt.Color(255, 255, 255));
        RoomTogglePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        RoomTogglePanel.setMaximumSize(new java.awt.Dimension(908, 801));
        RoomTogglePanel.setMinimumSize(new java.awt.Dimension(908, 801));
        RoomTogglePanel.setPreferredSize(new java.awt.Dimension(790, 580));
        RoomTogglePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MainViewDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        MainViewDetailsPanel.setMinimumSize(new java.awt.Dimension(790, 580));
        MainViewDetailsPanel.setPreferredSize(new java.awt.Dimension(790, 565));
        MainViewDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ReserveRoomjButton.setBackground(new java.awt.Color(255, 255, 255));
        ReserveRoomjButton.setForeground(new java.awt.Color(102, 153, 255));
        ReserveRoomjButton.setText("Reserve Room");
        ReserveRoomjButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ReserveRoomjButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReserveRoomjButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ReserveRoomjButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ReserveRoomjButtonMouseExited(evt);
            }
        });
        ReserveRoomjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReserveRoomjButtonActionPerformed(evt);
            }
        });
        MainViewDetailsPanel.add(ReserveRoomjButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 150, 40));

        PackageReservationjButton.setBackground(new java.awt.Color(255, 255, 255));
        PackageReservationjButton.setForeground(new java.awt.Color(102, 153, 255));
        PackageReservationjButton.setText("Reserve Package");
        PackageReservationjButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        PackageReservationjButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PackageReservationjButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                PackageReservationjButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                PackageReservationjButtonMouseExited(evt);
            }
        });
        PackageReservationjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PackageReservationjButtonActionPerformed(evt);
            }
        });
        MainViewDetailsPanel.add(PackageReservationjButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 150, 40));

        WelcomejLabel.setBackground(new java.awt.Color(255, 255, 255));
        WelcomejLabel.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 36)); // NOI18N
        WelcomejLabel.setForeground(new java.awt.Color(51, 153, 255));
        WelcomejLabel.setText("WELCOME TO ROOM RESERVATION!");
        MainViewDetailsPanel.add(WelcomejLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 0, -1, 100));

        RoomPaymentsjButton.setBackground(new java.awt.Color(255, 255, 255));
        RoomPaymentsjButton.setForeground(new java.awt.Color(102, 153, 255));
        RoomPaymentsjButton.setText("Payments");
        RoomPaymentsjButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        RoomPaymentsjButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RoomPaymentsjButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RoomPaymentsjButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RoomPaymentsjButtonMouseExited(evt);
            }
        });
        RoomPaymentsjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomPaymentsjButtonActionPerformed(evt);
            }
        });
        MainViewDetailsPanel.add(RoomPaymentsjButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, 150, 40));

        laundryOrdersjButton.setBackground(new java.awt.Color(255, 255, 255));
        laundryOrdersjButton.setForeground(new java.awt.Color(102, 153, 255));
        laundryOrdersjButton.setText("Laundry");
        laundryOrdersjButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        laundryOrdersjButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laundryOrdersjButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                laundryOrdersjButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                laundryOrdersjButtonMouseExited(evt);
            }
        });
        laundryOrdersjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laundryOrdersjButtonActionPerformed(evt);
            }
        });
        MainViewDetailsPanel.add(laundryOrdersjButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 150, 40));

        RoomSErviceOrdersjButton.setBackground(new java.awt.Color(255, 255, 255));
        RoomSErviceOrdersjButton.setForeground(new java.awt.Color(102, 153, 255));
        RoomSErviceOrdersjButton.setText("Room Service");
        RoomSErviceOrdersjButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        RoomSErviceOrdersjButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RoomSErviceOrdersjButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RoomSErviceOrdersjButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RoomSErviceOrdersjButtonMouseExited(evt);
            }
        });
        RoomSErviceOrdersjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomSErviceOrdersjButtonActionPerformed(evt);
            }
        });
        MainViewDetailsPanel.add(RoomSErviceOrdersjButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 150, 40));

        RoomTogglePanel.add(MainViewDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, 566));

        CustomerDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        CustomerDetailsPanel.setMaximumSize(new java.awt.Dimension(780, 500));
        CustomerDetailsPanel.setMinimumSize(new java.awt.Dimension(790, 580));

        CustomerDetailsTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        CustomerDetailsTabbedPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        CustomerDetailsTabbedPane.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        CustomerDetailsTabbedPane.setMaximumSize(new java.awt.Dimension(790, 510));
        CustomerDetailsTabbedPane.setMinimumSize(new java.awt.Dimension(790, 510));
        CustomerDetailsTabbedPane.setPreferredSize(new java.awt.Dimension(780, 510));

        viewCustomerpanel.setBackground(new java.awt.Color(255, 255, 255));
        viewCustomerpanel.setMaximumSize(new java.awt.Dimension(908, 801));
        viewCustomerpanel.setMinimumSize(new java.awt.Dimension(908, 801));

        ViewCustomerDetailsScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        ViewCustomerDetailsScrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewCustomerDetailsScrollPane.setForeground(new java.awt.Color(255, 255, 255));
        ViewCustomerDetailsScrollPane.setMinimumSize(new java.awt.Dimension(780, 500));
        ViewCustomerDetailsScrollPane.setPreferredSize(new java.awt.Dimension(780, 500));

        ViewCustomerDetailsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewCustomerDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "Customer Name", "Customer NIC", "Customer Address", "Customer Telephone No"
            }
        ));
        ViewCustomerDetailsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ViewCustomerDetailsTable.setGridColor(new java.awt.Color(102, 153, 255));
        ViewCustomerDetailsTable.setMinimumSize(new java.awt.Dimension(375, 480));
        ViewCustomerDetailsTable.getTableHeader().setResizingAllowed(false);
        ViewCustomerDetailsTable.getTableHeader().setReorderingAllowed(false);
        ViewCustomerDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewCustomerDetailsTableMouseClicked(evt);
            }
        });
        ViewCustomerDetailsScrollPane.setViewportView(ViewCustomerDetailsTable);

        ViewCustomerDetailsSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewCustomerDetailsSearchButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewCustomerDetailsSearchButton.setText("Search");
        ViewCustomerDetailsSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewCustomerDetailsSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewCustomerDetailsSearchButtonActionPerformed(evt);
            }
        });

        viewcustomerDetailssearchTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        viewcustomerDetailssearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewcustomerDetailssearchTextFieldActionPerformed(evt);
            }
        });

        viewcustomerDetailsSearchLabel.setText("Search by NIC");

        ViewRoomCustomerNameLabel.setBackground(new java.awt.Color(255, 255, 255));
        ViewRoomCustomerNameLabel.setText("Customer Name");

        ViewRoomCustomerNICLabel.setBackground(new java.awt.Color(255, 255, 255));
        ViewRoomCustomerNICLabel.setText("Customer NIC");

        ViewRoomCustomerNICTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewRoomCustomerNICTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewRoomCustomerNICTextFieldActionPerformed(evt);
            }
        });
        ViewRoomCustomerNICTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ViewRoomCustomerNICTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ViewRoomCustomerNICTextFieldKeyTyped(evt);
            }
        });

        ViewRoomCustomerNameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewRoomCustomerNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ViewRoomCustomerNameTextFieldKeyTyped(evt);
            }
        });

        ViewRoomCustomerAddressLabel.setBackground(new java.awt.Color(255, 255, 255));
        ViewRoomCustomerAddressLabel.setText("Customer Address");

        ViewRoomCustomerTelNoLabel.setBackground(new java.awt.Color(255, 255, 255));
        ViewRoomCustomerTelNoLabel.setText("Customer Telephone Number");

        ViewRoomCustomerTelNoTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewRoomCustomerTelNoTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ViewRoomCustomerTelNoTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ViewRoomCustomerTelNoTextFieldKeyTyped(evt);
            }
        });

        ViewRoomCustomerAddressTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        ViewCustomerDetailsOrderRoomButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewCustomerDetailsOrderRoomButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewCustomerDetailsOrderRoomButton.setText("Order Room");
        ViewCustomerDetailsOrderRoomButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewCustomerDetailsOrderRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewCustomerDetailsOrderRoomButtonActionPerformed(evt);
            }
        });

        ViewCustomerDetailsLaundaryButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewCustomerDetailsLaundaryButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewCustomerDetailsLaundaryButton.setText("Laundary");
        ViewCustomerDetailsLaundaryButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewCustomerDetailsLaundaryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewCustomerDetailsLaundaryButtonActionPerformed(evt);
            }
        });

        ViewCustomerDetailsOrderRoomServiceButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewCustomerDetailsOrderRoomServiceButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewCustomerDetailsOrderRoomServiceButton.setText("Room Service");
        ViewCustomerDetailsOrderRoomServiceButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewCustomerDetailsOrderRoomServiceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewCustomerDetailsOrderRoomServiceButtonActionPerformed(evt);
            }
        });

        ViewCustomerDetailsOrderPackageButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewCustomerDetailsOrderPackageButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewCustomerDetailsOrderPackageButton.setText("Order Package");
        ViewCustomerDetailsOrderPackageButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewCustomerDetailsOrderPackageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewCustomerDetailsOrderPackageButtonActionPerformed(evt);
            }
        });

        ViewCustomerDetailsResetButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewCustomerDetailsResetButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewCustomerDetailsResetButton.setText("Reset");
        ViewCustomerDetailsResetButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewCustomerDetailsResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewCustomerDetailsResetButtonActionPerformed(evt);
            }
        });

        ViewCustomerDetailsSearchButton2.setBackground(new java.awt.Color(255, 255, 255));
        ViewCustomerDetailsSearchButton2.setForeground(new java.awt.Color(102, 153, 255));
        ViewCustomerDetailsSearchButton2.setText("Submit");
        ViewCustomerDetailsSearchButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewCustomerDetailsSearchButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewCustomerDetailsSearchButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewCustomerpanelLayout = new javax.swing.GroupLayout(viewCustomerpanel);
        viewCustomerpanel.setLayout(viewCustomerpanelLayout);
        viewCustomerpanelLayout.setHorizontalGroup(
            viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewCustomerpanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewCustomerpanelLayout.createSequentialGroup()
                        .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewCustomerpanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ViewRoomCustomerAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ViewRoomCustomerTelNoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(viewCustomerpanelLayout.createSequentialGroup()
                                .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(viewCustomerpanelLayout.createSequentialGroup()
                                        .addComponent(ViewRoomCustomerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ViewRoomCustomerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(viewCustomerpanelLayout.createSequentialGroup()
                                        .addComponent(ViewRoomCustomerNICLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ViewRoomCustomerNICTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(257, 257, 257)))
                        .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ViewRoomCustomerAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewRoomCustomerTelNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(167, 167, 167))
                    .addGroup(viewCustomerpanelLayout.createSequentialGroup()
                        .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ViewCustomerDetailsOrderPackageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewCustomerDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(viewCustomerpanelLayout.createSequentialGroup()
                                .addComponent(viewcustomerDetailsSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(viewcustomerDetailssearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(ViewCustomerDetailsSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(168, 168, 168)
                                .addComponent(ViewCustomerDetailsResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(viewCustomerpanelLayout.createSequentialGroup()
                                .addComponent(ViewCustomerDetailsSearchButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(129, 129, 129)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(viewCustomerpanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(ViewCustomerDetailsOrderRoomServiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(ViewCustomerDetailsLaundaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(ViewCustomerDetailsOrderRoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        viewCustomerpanelLayout.setVerticalGroup(
            viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewCustomerpanelLayout.createSequentialGroup()
                .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewCustomerpanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ViewRoomCustomerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewRoomCustomerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewRoomCustomerAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewCustomerpanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ViewRoomCustomerAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ViewRoomCustomerTelNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ViewRoomCustomerTelNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ViewRoomCustomerNICLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ViewRoomCustomerNICTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ViewCustomerDetailsSearchButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(viewcustomerDetailssearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(viewcustomerDetailsSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ViewCustomerDetailsSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ViewCustomerDetailsResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(ViewCustomerDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(viewCustomerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ViewCustomerDetailsOrderRoomServiceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewCustomerDetailsLaundaryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewCustomerDetailsOrderRoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewCustomerDetailsOrderPackageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(342, Short.MAX_VALUE))
        );

        CustomerDetailsTabbedPane.addTab("View Customer Details", viewCustomerpanel);

        EditCustomerPanel.setBackground(new java.awt.Color(255, 255, 255));
        EditCustomerPanel.setMaximumSize(new java.awt.Dimension(908, 801));
        EditCustomerPanel.setMinimumSize(new java.awt.Dimension(908, 801));
        EditCustomerPanel.setPreferredSize(new java.awt.Dimension(780, 500));

        EditRoomCustomerNameLabel.setBackground(new java.awt.Color(255, 255, 255));
        EditRoomCustomerNameLabel.setText("Customer Name");

        EditRoomCustomerNICLabell.setBackground(new java.awt.Color(255, 255, 255));
        EditRoomCustomerNICLabell.setText("Customer NIC");

        EditRoomCustomerAddressLabel.setBackground(new java.awt.Color(255, 255, 255));
        EditRoomCustomerAddressLabel.setText("Customer Address");

        EditRoomCustomerTelNoLabel.setBackground(new java.awt.Color(255, 255, 255));
        EditRoomCustomerTelNoLabel.setText("Customer Telephone Number");

        EditRoomCustomerNameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditRoomCustomerNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EditRoomCustomerNameTextFieldKeyTyped(evt);
            }
        });

        EditRoomCustomerNICTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditRoomCustomerNICTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditRoomCustomerNICTextFieldActionPerformed(evt);
            }
        });
        EditRoomCustomerNICTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EditRoomCustomerNICTextFieldKeyReleased(evt);
            }
        });

        EditRoomCustomerAddressTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditRoomCustomerAddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditRoomCustomerAddressTextFieldActionPerformed(evt);
            }
        });

        EditRoomCustomerTElNoTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditRoomCustomerTElNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditRoomCustomerTElNoTextFieldActionPerformed(evt);
            }
        });
        EditRoomCustomerTElNoTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EditRoomCustomerTElNoTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EditRoomCustomerTElNoTextFieldKeyTyped(evt);
            }
        });

        EditCustomerDetailsUpdateButton.setBackground(new java.awt.Color(255, 255, 255));
        EditCustomerDetailsUpdateButton.setForeground(new java.awt.Color(102, 153, 255));
        EditCustomerDetailsUpdateButton.setText("Update");
        EditCustomerDetailsUpdateButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditCustomerDetailsUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditCustomerDetailsUpdateButtonActionPerformed(evt);
            }
        });

        EditCustomerDetailsScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        EditCustomerDetailsScrollPane.setMinimumSize(new java.awt.Dimension(780, 500));
        EditCustomerDetailsScrollPane.setPreferredSize(new java.awt.Dimension(780, 500));

        EditCustomerDetailsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditCustomerDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Customer ID", "Customer Name", "Customer NIC", "Customer Address", "Customer Telephone No"
            }
        ));
        EditCustomerDetailsTable.setGridColor(new java.awt.Color(102, 153, 255));
        EditCustomerDetailsTable.setMinimumSize(new java.awt.Dimension(375, 480));
        EditCustomerDetailsTable.getTableHeader().setResizingAllowed(false);
        EditCustomerDetailsTable.getTableHeader().setReorderingAllowed(false);
        EditCustomerDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditCustomerDetailsTableMouseClicked(evt);
            }
        });
        EditCustomerDetailsScrollPane.setViewportView(EditCustomerDetailsTable);

        EditCustomerDetailsIDvalueLabel.setText("ID");

        EditCustomerDetailsLabel.setText("ID");

        EditCustomerDetailsDeleteButton.setBackground(new java.awt.Color(255, 255, 255));
        EditCustomerDetailsDeleteButton.setForeground(new java.awt.Color(102, 153, 255));
        EditCustomerDetailsDeleteButton.setText("Delete");
        EditCustomerDetailsDeleteButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditCustomerDetailsDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditCustomerDetailsDeleteButtonActionPerformed(evt);
            }
        });

        EditCustomerDetailsSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        EditCustomerDetailsSearchButton.setForeground(new java.awt.Color(102, 153, 255));
        EditCustomerDetailsSearchButton.setText("Search");
        EditCustomerDetailsSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditCustomerDetailsSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditCustomerDetailsSearchButtonActionPerformed(evt);
            }
        });

        EditcustomerDetailsSearchLabel.setText("Search by NIC");

        EditcustomerDetailssearchTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditcustomerDetailssearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditcustomerDetailssearchTextFieldActionPerformed(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(102, 153, 255));

        EditCustomerDetailsResetButton.setBackground(new java.awt.Color(255, 255, 255));
        EditCustomerDetailsResetButton.setForeground(new java.awt.Color(102, 153, 255));
        EditCustomerDetailsResetButton.setText("Reset");
        EditCustomerDetailsResetButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditCustomerDetailsResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditCustomerDetailsResetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EditCustomerPanelLayout = new javax.swing.GroupLayout(EditCustomerPanel);
        EditCustomerPanel.setLayout(EditCustomerPanelLayout);
        EditCustomerPanelLayout.setHorizontalGroup(
            EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditCustomerPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EditRoomCustomerNICLabell, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditRoomCustomerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditCustomerDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EditCustomerDetailsIDvalueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(EditRoomCustomerNICTextField)
                    .addComponent(EditRoomCustomerNameTextField))
                .addGap(54, 54, 54)
                .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EditRoomCustomerAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EditRoomCustomerTelNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(EditRoomCustomerAddressTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(EditRoomCustomerTElNoTextField))
                .addGap(67, 67, 67))
            .addComponent(jSeparator4)
            .addGroup(EditCustomerPanelLayout.createSequentialGroup()
                .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditCustomerPanelLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(EditCustomerDetailsUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124)
                        .addComponent(EditCustomerDetailsDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EditCustomerPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EditCustomerDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(EditCustomerPanelLayout.createSequentialGroup()
                                .addComponent(EditcustomerDetailsSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(EditcustomerDetailssearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(EditCustomerDetailsSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(EditCustomerDetailsResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        EditCustomerPanelLayout.setVerticalGroup(
            EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditCustomerPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditRoomCustomerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditRoomCustomerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditRoomCustomerAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditRoomCustomerAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EditRoomCustomerTelNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EditRoomCustomerNICLabell, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EditCustomerPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(EditRoomCustomerNICTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditRoomCustomerTElNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditCustomerDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditCustomerDetailsIDvalueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EditcustomerDetailsSearchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EditcustomerDetailssearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EditCustomerDetailsSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EditCustomerDetailsResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(EditCustomerDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(EditCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditCustomerDetailsUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditCustomerDetailsDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(322, Short.MAX_VALUE))
        );

        CustomerDetailsTabbedPane.addTab("Edit Customer Details", EditCustomerPanel);

        javax.swing.GroupLayout CustomerDetailsPanelLayout = new javax.swing.GroupLayout(CustomerDetailsPanel);
        CustomerDetailsPanel.setLayout(CustomerDetailsPanelLayout);
        CustomerDetailsPanelLayout.setHorizontalGroup(
            CustomerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CustomerDetailsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        CustomerDetailsPanelLayout.setVerticalGroup(
            CustomerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerDetailsPanelLayout.createSequentialGroup()
                .addComponent(CustomerDetailsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        RoomTogglePanel.add(CustomerDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        RoomDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        RoomDetailsPanel.setMinimumSize(new java.awt.Dimension(790, 580));

        RoomDetailsTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        RoomDetailsTabbedPane.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        RoomDetailsTabbedPane.setMinimumSize(new java.awt.Dimension(780, 510));
        RoomDetailsTabbedPane.setPreferredSize(new java.awt.Dimension(780, 510));

        ViewRoomDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        ViewRoomDetailsPanel.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N

        ViewRoomDetailsScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        ViewRoomDetailsScrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewRoomDetailsScrollPane.setForeground(new java.awt.Color(255, 255, 255));

        ViewRoomDetailsjTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewRoomDetailsjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Room No", "No Of Adults", "No of Children", "Room Type", "Amount"
            }
        ));
        ViewRoomDetailsjTable.setGridColor(new java.awt.Color(102, 153, 255));
        ViewRoomDetailsjTable.setMinimumSize(new java.awt.Dimension(375, 480));
        ViewRoomDetailsjTable.getTableHeader().setResizingAllowed(false);
        ViewRoomDetailsjTable.getTableHeader().setReorderingAllowed(false);
        ViewRoomDetailsjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewRoomDetailsjTableMouseClicked(evt);
            }
        });
        ViewRoomDetailsScrollPane.setViewportView(ViewRoomDetailsjTable);

        ViewRoomDetailsCheckAvailabiltyButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewRoomDetailsCheckAvailabiltyButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewRoomDetailsCheckAvailabiltyButton.setText("Check Availability");
        ViewRoomDetailsCheckAvailabiltyButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewRoomDetailsCheckAvailabiltyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewRoomDetailsCheckAvailabiltyButtonActionPerformed(evt);
            }
        });

        ViewRoomDetailsNoofAdultsTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewRoomDetailsNoofAdultsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewRoomDetailsNoofAdultsTextFieldActionPerformed(evt);
            }
        });
        ViewRoomDetailsNoofAdultsTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ViewRoomDetailsNoofAdultsTextFieldKeyTyped(evt);
            }
        });

        ViewRoomDetailsNoofAdultsjLabel.setText("No of Adults");

        ViewRoomDetailsNoofChildrenjLabel.setText("No of Children");

        ViewRoomDetailsNoChildrenTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewRoomDetailsNoChildrenTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewRoomDetailsNoChildrenTextFieldActionPerformed(evt);
            }
        });
        ViewRoomDetailsNoChildrenTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ViewRoomDetailsNoChildrenTextFieldKeyTyped(evt);
            }
        });

        ViewRoomDetailsCheckInjLabel.setText("Check In");

        RoomCheckInjDateChooser.setBackground(new java.awt.Color(255, 255, 255));
        RoomCheckInjDateChooser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 0));

        RoomCheckoutjDateChooser.setBackground(new java.awt.Color(255, 255, 255));
        RoomCheckoutjDateChooser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 0));

        CheckoutjLabel.setText("Check Out");

        ViewRoomDetailsReserveRoomButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewRoomDetailsReserveRoomButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewRoomDetailsReserveRoomButton.setText("Reserve Room");
        ViewRoomDetailsReserveRoomButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewRoomDetailsReserveRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewRoomDetailsReserveRoomButtonActionPerformed(evt);
            }
        });

        ViewRoomDetailsRoomTypejLabel.setText("Room Type");

        ViewRoomDetailsjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a type", "Single bed A/C", "Single bed Non A/C", "Double bed A/C", "Double bed Non A/C" }));
        ViewRoomDetailsjComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 0));

        ViewRoomDetailsCusNICLabel.setText("Customer NIC");

        ViewRoomDetailsCusnicValuejLabel.setText("CusNIC");

        ViewRoomDetailsNextButton1.setBackground(new java.awt.Color(255, 255, 255));
        ViewRoomDetailsNextButton1.setForeground(new java.awt.Color(102, 153, 255));
        ViewRoomDetailsNextButton1.setText("Back");
        ViewRoomDetailsNextButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewRoomDetailsNextButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewRoomDetailsNextButton1ActionPerformed(evt);
            }
        });

        jSeparator3.setForeground(new java.awt.Color(102, 153, 255));

        ViewCustomerDetailsResetButton1.setBackground(new java.awt.Color(255, 255, 255));
        ViewCustomerDetailsResetButton1.setForeground(new java.awt.Color(102, 153, 255));
        ViewCustomerDetailsResetButton1.setText("Reset");
        ViewCustomerDetailsResetButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewCustomerDetailsResetButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewCustomerDetailsResetButton1ActionPerformed(evt);
            }
        });

        jTableToReserveRoom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableToReserveRoom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room No", "Amount"
            }
        ));
        jTableToReserveRoom.setGridColor(new java.awt.Color(102, 153, 255));
        jScrollPane1.setViewportView(jTableToReserveRoom);

        jButtonAddRoomDetailsToReserve.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAddRoomDetailsToReserve.setForeground(new java.awt.Color(102, 153, 255));
        jButtonAddRoomDetailsToReserve.setText("ADD");
        jButtonAddRoomDetailsToReserve.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jButtonAddRoomDetailsToReserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddRoomDetailsToReserveActionPerformed(evt);
            }
        });

        jButtonRemoveRoomReserves.setBackground(new java.awt.Color(255, 255, 255));
        jButtonRemoveRoomReserves.setForeground(new java.awt.Color(102, 153, 255));
        jButtonRemoveRoomReserves.setText("REMOVE");
        jButtonRemoveRoomReserves.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jButtonRemoveRoomReserves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveRoomReservesActionPerformed(evt);
            }
        });

        jLabelTotalAmount.setText("Total Amount");

        jButtonTotalAmount.setBackground(new java.awt.Color(255, 255, 255));
        jButtonTotalAmount.setForeground(new java.awt.Color(102, 153, 255));
        jButtonTotalAmount.setText("Total");
        jButtonTotalAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jButtonTotalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTotalAmountActionPerformed(evt);
            }
        });

        ViewCustomerDetailsResetButton4.setBackground(new java.awt.Color(255, 255, 255));
        ViewCustomerDetailsResetButton4.setForeground(new java.awt.Color(102, 153, 255));
        ViewCustomerDetailsResetButton4.setText("Reset");
        ViewCustomerDetailsResetButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewCustomerDetailsResetButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewCustomerDetailsResetButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ViewRoomDetailsPanelLayout = new javax.swing.GroupLayout(ViewRoomDetailsPanel);
        ViewRoomDetailsPanel.setLayout(ViewRoomDetailsPanelLayout);
        ViewRoomDetailsPanelLayout.setHorizontalGroup(
            ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewRoomDetailsPanelLayout.createSequentialGroup()
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewRoomDetailsPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                                .addComponent(ViewRoomDetailsCusNICLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ViewRoomDetailsCusnicValuejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                                .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(ViewRoomDetailsRoomTypejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ViewRoomDetailsjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(ViewRoomDetailsCheckInjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(RoomCheckInjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(ViewRoomDetailsNoofAdultsjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ViewRoomDetailsNoofAdultsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(68, 68, 68)
                                .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(ViewRoomDetailsCheckAvailabiltyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(ViewCustomerDetailsResetButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(ViewRoomDetailsNoofChildrenjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(CheckoutjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(ViewRoomDetailsNoChildrenTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(RoomCheckoutjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ViewRoomDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                                .addComponent(ViewCustomerDetailsResetButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(ViewRoomDetailsReserveRoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButtonAddRoomDetailsToReserve, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButtonRemoveRoomReserves, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabelTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldTotalAmountToPAy, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(ViewRoomDetailsNextButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewRoomDetailsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        ViewRoomDetailsPanelLayout.setVerticalGroup(
            ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ViewRoomDetailsCusNICLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewRoomDetailsCusnicValuejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ViewRoomDetailsNoofAdultsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewRoomDetailsNoofAdultsjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RoomCheckInjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewRoomDetailsCheckInjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ViewRoomDetailsRoomTypejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewRoomDetailsjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                        .addContainerGap(70, Short.MAX_VALUE)
                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ViewRoomDetailsNoofChildrenjLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewRoomDetailsNoChildrenTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CheckoutjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RoomCheckoutjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ViewRoomDetailsCheckAvailabiltyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewCustomerDetailsResetButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewRoomDetailsPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTotalAmountToPAy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButtonAddRoomDetailsToReserve, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonRemoveRoomReserves, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ViewRoomDetailsPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ViewRoomDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ViewRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ViewRoomDetailsReserveRoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewRoomDetailsNextButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewCustomerDetailsResetButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        RoomDetailsTabbedPane.addTab("View Room details", ViewRoomDetailsPanel);

        EditRoomDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        EditRoomDetailsPanel.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N

        EditRoomDetailsRoomNoLabel.setBackground(new java.awt.Color(255, 255, 255));
        EditRoomDetailsRoomNoLabel.setText("Room Number");

        EditRoomDetailsAmountLabel.setBackground(new java.awt.Color(255, 255, 255));
        EditRoomDetailsAmountLabel.setText("Amount");

        EditRoomDetailsAmountTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditRoomDetailsAmountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditRoomDetailsAmountTextFieldActionPerformed(evt);
            }
        });

        EditRoomDetailsUpdateButton.setBackground(new java.awt.Color(255, 255, 255));
        EditRoomDetailsUpdateButton.setForeground(new java.awt.Color(102, 153, 255));
        EditRoomDetailsUpdateButton.setText("Update");
        EditRoomDetailsUpdateButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditRoomDetailsUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditRoomDetailsUpdateButtonActionPerformed(evt);
            }
        });

        ViewRoomDetailsScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        EditRoomDetailsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditRoomDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Room No", "Status", "Amount", "No of Adults", "No oF children", "Room Type", "Head Count", "Description"
            }
        ));
        EditRoomDetailsTable.setGridColor(new java.awt.Color(102, 153, 255));
        EditRoomDetailsTable.setMinimumSize(new java.awt.Dimension(375, 480));
        EditRoomDetailsTable.setPreferredSize(new java.awt.Dimension(375, 480));
        EditRoomDetailsTable.getTableHeader().setResizingAllowed(false);
        EditRoomDetailsTable.getTableHeader().setReorderingAllowed(false);
        EditRoomDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditRoomDetailsTableMouseClicked(evt);
            }
        });
        ViewRoomDetailsScrollPane1.setViewportView(EditRoomDetailsTable);

        EditRoomDetailsRoomNovalueLabel.setText("Room No");

        EditRoomDetailsjLabel.setText("No of Adults");

        EditRoomDetailsNoofAdultsTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditRoomDetailsNoofAdultsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditRoomDetailsNoofAdultsTextFieldActionPerformed(evt);
            }
        });

        EditRoomDetailsNoofchildrenjLabel.setText("No of Children");

        EditRoomDetailsNoofChildrenTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditRoomDetailsNoofChildrenTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditRoomDetailsNoofChildrenTextFieldActionPerformed(evt);
            }
        });

        viewRoomDetailsSearchLabel1.setText("Search by Room No");

        EditRoomDetailsSearchValueTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditRoomDetailsSearchValueTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditRoomDetailsSearchValueTextFieldActionPerformed(evt);
            }
        });

        EditRoomDetailsSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        EditRoomDetailsSearchButton.setForeground(new java.awt.Color(102, 153, 255));
        EditRoomDetailsSearchButton.setText("Search");
        EditRoomDetailsSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditRoomDetailsSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditRoomDetailsSearchButtonActionPerformed(evt);
            }
        });

        EditRoomDetailsRoomTypejLabel.setText("Room Type");

        EditRoomDetailsSubmitButton.setBackground(new java.awt.Color(255, 255, 255));
        EditRoomDetailsSubmitButton.setForeground(new java.awt.Color(102, 153, 255));
        EditRoomDetailsSubmitButton.setText("Submit");
        EditRoomDetailsSubmitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditRoomDetailsSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditRoomDetailsSubmitButtonActionPerformed(evt);
            }
        });

        EditRoomDetailsjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a type", "Single bed A/C", "Single bed Non A/C", "Double bed A/C", "Double bed Non A/C" }));
        EditRoomDetailsjComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        jSeparator5.setForeground(new java.awt.Color(102, 153, 255));

        ViewCustomerDetailsResetButton3.setBackground(new java.awt.Color(255, 255, 255));
        ViewCustomerDetailsResetButton3.setForeground(new java.awt.Color(102, 153, 255));
        ViewCustomerDetailsResetButton3.setText("Reset");
        ViewCustomerDetailsResetButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewCustomerDetailsResetButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewCustomerDetailsResetButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EditRoomDetailsPanelLayout = new javax.swing.GroupLayout(EditRoomDetailsPanel);
        EditRoomDetailsPanel.setLayout(EditRoomDetailsPanelLayout);
        EditRoomDetailsPanelLayout.setHorizontalGroup(
            EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(EditRoomDetailsPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ViewRoomDetailsScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(EditRoomDetailsPanelLayout.createSequentialGroup()
                            .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(EditRoomDetailsjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EditRoomDetailsRoomNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EditRoomDetailsNoofchildrenjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(EditRoomDetailsRoomNovalueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EditRoomDetailsNoofChildrenTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(EditRoomDetailsNoofAdultsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(95, 95, 95)
                            .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(EditRoomDetailsPanelLayout.createSequentialGroup()
                                    .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(EditRoomDetailsRoomTypejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(EditRoomDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(EditRoomDetailsPanelLayout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(ViewCustomerDetailsResetButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(EditRoomDetailsjComboBox, 0, 1, Short.MAX_VALUE)))
                                .addGroup(EditRoomDetailsPanelLayout.createSequentialGroup()
                                    .addComponent(EditRoomDetailsAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(EditRoomDetailsAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(EditRoomDetailsPanelLayout.createSequentialGroup()
                            .addComponent(viewRoomDetailsSearchLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(EditRoomDetailsSearchValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(70, 70, 70)
                            .addComponent(EditRoomDetailsSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditRoomDetailsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EditRoomDetailsUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184))
        );
        EditRoomDetailsPanelLayout.setVerticalGroup(
            EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditRoomDetailsPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditRoomDetailsPanelLayout.createSequentialGroup()
                        .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditRoomDetailsRoomNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditRoomDetailsRoomNovalueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditRoomDetailsjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditRoomDetailsNoofAdultsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditRoomDetailsNoofchildrenjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditRoomDetailsNoofChildrenTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(EditRoomDetailsPanelLayout.createSequentialGroup()
                        .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditRoomDetailsAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditRoomDetailsAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditRoomDetailsRoomTypejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditRoomDetailsjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EditRoomDetailsPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(ViewCustomerDetailsResetButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(EditRoomDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EditRoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewRoomDetailsSearchLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditRoomDetailsSearchValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditRoomDetailsSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ViewRoomDetailsScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(EditRoomDetailsUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        RoomDetailsTabbedPane.addTab("Edit Room Details", EditRoomDetailsPanel);

        RoomDescriptionjPanel.setBackground(new java.awt.Color(255, 255, 255));

        ViewRoomDetailsScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        RoomDescriptionTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        RoomDescriptionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Room No", "Amount", "No of Adults", "No oF children", "Head Count", "Description"
            }
        ));
        RoomDescriptionTable.setEnabled(false);
        RoomDescriptionTable.setGridColor(new java.awt.Color(102, 153, 255));
        RoomDescriptionTable.setMinimumSize(new java.awt.Dimension(375, 480));
        RoomDescriptionTable.setPreferredSize(new java.awt.Dimension(375, 480));
        RoomDescriptionTable.getTableHeader().setResizingAllowed(false);
        RoomDescriptionTable.getTableHeader().setReorderingAllowed(false);
        RoomDescriptionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RoomDescriptionTableMouseClicked(evt);
            }
        });
        ViewRoomDetailsScrollPane2.setViewportView(RoomDescriptionTable);

        javax.swing.GroupLayout RoomDescriptionjPanelLayout = new javax.swing.GroupLayout(RoomDescriptionjPanel);
        RoomDescriptionjPanel.setLayout(RoomDescriptionjPanelLayout);
        RoomDescriptionjPanelLayout.setHorizontalGroup(
            RoomDescriptionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoomDescriptionjPanelLayout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(ViewRoomDetailsScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        RoomDescriptionjPanelLayout.setVerticalGroup(
            RoomDescriptionjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoomDescriptionjPanelLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(ViewRoomDetailsScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(188, Short.MAX_VALUE))
        );

        RoomDetailsTabbedPane.addTab("Room Description", RoomDescriptionjPanel);

        javax.swing.GroupLayout RoomDetailsPanelLayout = new javax.swing.GroupLayout(RoomDetailsPanel);
        RoomDetailsPanel.setLayout(RoomDetailsPanelLayout);
        RoomDetailsPanelLayout.setHorizontalGroup(
            RoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RoomDetailsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        RoomDetailsPanelLayout.setVerticalGroup(
            RoomDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RoomDetailsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        RoomTogglePanel.add(RoomDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        PackageDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        PackageDetailsPanel.setMinimumSize(new java.awt.Dimension(790, 580));

        PackageRoomTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        PackageRoomTabbedPane.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        PackageRoomTabbedPane.setMinimumSize(new java.awt.Dimension(780, 510));
        PackageRoomTabbedPane.setPreferredSize(new java.awt.Dimension(780, 510));

        ViewPackageDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));

        ViewPackageDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Package ID", "Description", "Status", "Amount", "No of Adults", "No of Children", "Room No"
            }
        ));
        ViewPackageDetailsTable.setGridColor(new java.awt.Color(102, 153, 255));
        ViewPackageDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewPackageDetailsTableMouseClicked(evt);
            }
        });
        ViewPackageDetailsScrollPane.setViewportView(ViewPackageDetailsTable);

        ViewPackageDetailsSubmitButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewPackageDetailsSubmitButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewPackageDetailsSubmitButton.setText("Check Availabilty");
        ViewPackageDetailsSubmitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewPackageDetailsSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPackageDetailsSubmitButtonActionPerformed(evt);
            }
        });

        ViewPackagesDetailsNextButton.setBackground(new java.awt.Color(42, 135, 235));
        ViewPackagesDetailsNextButton.setForeground(new java.awt.Color(255, 255, 255));
        ViewPackagesDetailsNextButton.setText("Reserve Package");
        ViewPackagesDetailsNextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPackagesDetailsNextButtonActionPerformed(evt);
            }
        });

        EditPAckageDetailsNoofDetailsjLabel1.setText("No of Adults");

        ViewPackageDetailsNoofAdultsTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewPackageDetailsNoofAdultsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPackageDetailsNoofAdultsTextFieldActionPerformed(evt);
            }
        });

        EditPackageDetailsNoofchildrenjLabel1.setText("No of Children");

        ViewPackageDetailsNoofChildrenTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewPackageDetailsNoofChildrenTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPackageDetailsNoofChildrenTextFieldActionPerformed(evt);
            }
        });

        ViewPackagesDetailsNextButton1.setBackground(new java.awt.Color(42, 135, 235));
        ViewPackagesDetailsNextButton1.setForeground(new java.awt.Color(255, 255, 255));
        ViewPackagesDetailsNextButton1.setText("Back");
        ViewPackagesDetailsNextButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPackagesDetailsNextButton1ActionPerformed(evt);
            }
        });

        ViewPackageCustomerNICTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewPackageCustomerNICTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPackageCustomerNICTextFieldActionPerformed(evt);
            }
        });
        ViewPackageCustomerNICTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ViewPackageCustomerNICTextFieldKeyReleased(evt);
            }
        });

        ViewRoomCustomerNICLabel1.setBackground(new java.awt.Color(255, 255, 255));
        ViewRoomCustomerNICLabel1.setText("Customer NIC");

        jSeparator7.setForeground(new java.awt.Color(102, 153, 255));

        ViewPackageDetailsReservePackageButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewPackageDetailsReservePackageButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewPackageDetailsReservePackageButton.setText("Reserve package");
        ViewPackageDetailsReservePackageButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewPackageDetailsReservePackageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPackageDetailsReservePackageButtonActionPerformed(evt);
            }
        });

        ViewPackageDetailsResetButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewPackageDetailsResetButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewPackageDetailsResetButton.setText("Reset");
        ViewPackageDetailsResetButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewPackageDetailsResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPackageDetailsResetButtonActionPerformed(evt);
            }
        });

        jButtonRemovePackageReserves1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonRemovePackageReserves1.setForeground(new java.awt.Color(102, 153, 255));
        jButtonRemovePackageReserves1.setText("REMOVE");
        jButtonRemovePackageReserves1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jButtonRemovePackageReserves1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemovePackageReserves1ActionPerformed(evt);
            }
        });

        jButtonAddPackageDetailsToReserve.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAddPackageDetailsToReserve.setForeground(new java.awt.Color(102, 153, 255));
        jButtonAddPackageDetailsToReserve.setText("ADD");
        jButtonAddPackageDetailsToReserve.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jButtonAddPackageDetailsToReserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPackageDetailsToReserveActionPerformed(evt);
            }
        });

        jTableToReservePackage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableToReservePackage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room No", "Amount"
            }
        ));
        jTableToReservePackage.setGridColor(new java.awt.Color(102, 153, 255));
        jScrollPane2.setViewportView(jTableToReservePackage);

        jLabelTotalAmountRoom.setText("Total Amount");

        jButtonTotalAmountPackage.setBackground(new java.awt.Color(255, 255, 255));
        jButtonTotalAmountPackage.setForeground(new java.awt.Color(102, 153, 255));
        jButtonTotalAmountPackage.setText("Total");
        jButtonTotalAmountPackage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jButtonTotalAmountPackage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTotalAmountPackageActionPerformed(evt);
            }
        });

        ViewPackDetailsCheckInjLabel3.setText("Check In");

        PackCheckInjDateChooser.setBackground(new java.awt.Color(255, 255, 255));
        PackCheckInjDateChooser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 0));

        PackCheckoutjLabel3.setText("Check Out");

        PackCheckoutjDateChooser.setBackground(new java.awt.Color(255, 255, 255));
        PackCheckoutjDateChooser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 0));

        jButtonTotalPackageReserves.setBackground(new java.awt.Color(255, 255, 255));
        jButtonTotalPackageReserves.setForeground(new java.awt.Color(102, 153, 255));
        jButtonTotalPackageReserves.setText("Total");
        jButtonTotalPackageReserves.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jButtonTotalPackageReserves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTotalPackageReservesActionPerformed(evt);
            }
        });

        ViewRoomDetailsNextButton2.setBackground(new java.awt.Color(255, 255, 255));
        ViewRoomDetailsNextButton2.setForeground(new java.awt.Color(102, 153, 255));
        ViewRoomDetailsNextButton2.setText("Back");
        ViewRoomDetailsNextButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewRoomDetailsNextButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewRoomDetailsNextButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ViewPackageDetailsPanelLayout = new javax.swing.GroupLayout(ViewPackageDetailsPanel);
        ViewPackageDetailsPanel.setLayout(ViewPackageDetailsPanelLayout);
        ViewPackageDetailsPanelLayout.setHorizontalGroup(
            ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                        .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(EditPAckageDetailsNoofDetailsjLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(EditPackageDetailsNoofchildrenjLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewPackageDetailsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(ViewRoomCustomerNICLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ViewPackageDetailsSubmitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                                .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ViewPackageDetailsNoofAdultsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ViewPackageDetailsNoofChildrenTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ViewPackageCustomerNICTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(PackCheckoutjLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(PackCheckoutjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(ViewPackDetailsCheckInjLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(PackCheckInjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(20, 20, 20))))
                    .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(ViewPackageDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                                    .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonAddPackageDetailsToReserve, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonRemovePackageReserves1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(37, 37, 37)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                                    .addComponent(jLabelTotalAmountRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldTotalAmountToPAypackage, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButtonTotalPackageReserves, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonTotalAmountPackage, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ViewPackagesDetailsNextButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(ViewPackagesDetailsNextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(ViewPackageDetailsResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(ViewPackageDetailsReservePackageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addComponent(ViewRoomDetailsNextButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ViewPackageDetailsPanelLayout.setVerticalGroup(
            ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewPackageDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ViewRoomCustomerNICLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewPackageCustomerNICTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EditPAckageDetailsNoofDetailsjLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ViewPackageDetailsNoofAdultsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ViewPackDetailsCheckInjLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PackCheckInjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EditPackageDetailsNoofchildrenjLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ViewPackageDetailsNoofChildrenTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(PackCheckoutjLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(PackCheckoutjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ViewPackageDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                        .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ViewPackagesDetailsNextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewPackagesDetailsNextButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86))
                    .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ViewPackageDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                                .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                                        .addComponent(jButtonAddPackageDetailsToReserve, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonRemovePackageReserves1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldTotalAmountToPAypackage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelTotalAmountRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButtonTotalAmountPackage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ViewPackageDetailsPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonTotalPackageReserves, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addGroup(ViewPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ViewPackageDetailsReservePackageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewPackageDetailsResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewRoomDetailsNextButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66))))
        );

        EditPAckageDetailsNoofDetailsjLabel1.getAccessibleContext().setAccessibleName("");
        EditPackageDetailsNoofchildrenjLabel1.getAccessibleContext().setAccessibleName("");
        ViewRoomCustomerNICLabel1.getAccessibleContext().setAccessibleName("");

        PackageRoomTabbedPane.addTab("View Package Details", ViewPackageDetailsPanel);

        EditPackageDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));

        EditPackagePackageIDLabel.setBackground(new java.awt.Color(255, 255, 255));
        EditPackagePackageIDLabel.setText("Package ID");

        EditPackagePackageDetailsLabel.setBackground(new java.awt.Color(255, 255, 255));
        EditPackagePackageDetailsLabel.setText("Package Details");

        EditPackageDetailsPackagestatusLabel.setBackground(new java.awt.Color(255, 255, 255));
        EditPackageDetailsPackagestatusLabel.setText("Package Status");

        EditPackagePackageAmountLabel.setBackground(new java.awt.Color(255, 255, 255));
        EditPackagePackageAmountLabel.setText("Amount");

        EditPackageDetailsPackDetailsTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        EditPackageDetailsAmountTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        EditPackageDetailsSubmitButton.setBackground(new java.awt.Color(255, 255, 255));
        EditPackageDetailsSubmitButton.setForeground(new java.awt.Color(102, 153, 255));
        EditPackageDetailsSubmitButton.setText("Submit");
        EditPackageDetailsSubmitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditPackageDetailsSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditPackageDetailsSubmitButtonActionPerformed(evt);
            }
        });

        EditPackageDetailsUpdateButton.setBackground(new java.awt.Color(255, 255, 255));
        EditPackageDetailsUpdateButton.setForeground(new java.awt.Color(102, 153, 255));
        EditPackageDetailsUpdateButton.setText("Update");
        EditPackageDetailsUpdateButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditPackageDetailsUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditPackageDetailsUpdateButtonActionPerformed(evt);
            }
        });

        EditPackageDetailsDeleteButton.setBackground(new java.awt.Color(255, 255, 255));
        EditPackageDetailsDeleteButton.setForeground(new java.awt.Color(102, 153, 255));
        EditPackageDetailsDeleteButton.setText("Delete");
        EditPackageDetailsDeleteButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditPackageDetailsDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditPackageDetailsDeleteButtonActionPerformed(evt);
            }
        });

        EditpackagespackidValueLabel.setText("PackageID");

        ViewRoomPackageDetailsjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select a Status", "Available", "Not Available" }));
        ViewRoomPackageDetailsjComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        EditPackageDetailsNoofchildrenjLabel.setText("No of Children");

        EditPAckageDetailsNoofDetailsjLabel.setText("No of Adults");

        EditPackageDetailsNoofAdultsTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditPackageDetailsNoofAdultsTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditPackageDetailsNoofAdultsTextFieldActionPerformed(evt);
            }
        });

        EditPackageDetailsNoofChildrenTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditPackageDetailsNoofChildrenTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditPackageDetailsNoofChildrenTextFieldActionPerformed(evt);
            }
        });

        jSeparator6.setForeground(new java.awt.Color(102, 153, 255));

        EditPackageDetailsRoomNoLabel.setText(" Room No");

        EditPackageDetailsRoomNoValueTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        EditPackageDetailsRoomNoValueTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditPackageDetailsRoomNoValueTextFieldActionPerformed(evt);
            }
        });

        EditPackageDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Package ID", "Description", "Status", "Amount", "No of Adults", "No of Children", "Room No"
            }
        ));
        EditPackageDetailsTable.setGridColor(new java.awt.Color(102, 153, 255));
        EditPackageDetailsTable.getTableHeader().setResizingAllowed(false);
        EditPackageDetailsTable.getTableHeader().setReorderingAllowed(false);
        EditPackageDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EditPackageDetailsTableMouseClicked(evt);
            }
        });
        ViewPackageDetailsScrollPane1.setViewportView(EditPackageDetailsTable);

        ViewPackageDetailsResetButton1.setBackground(new java.awt.Color(255, 255, 255));
        ViewPackageDetailsResetButton1.setForeground(new java.awt.Color(102, 153, 255));
        ViewPackageDetailsResetButton1.setText("Reset");
        ViewPackageDetailsResetButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewPackageDetailsResetButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPackageDetailsResetButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EditPackageDetailsPanelLayout = new javax.swing.GroupLayout(EditPackageDetailsPanel);
        EditPackageDetailsPanel.setLayout(EditPackageDetailsPanelLayout);
        EditPackageDetailsPanelLayout.setHorizontalGroup(
            EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditPackageDetailsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditPackageDetailsPanelLayout.createSequentialGroup()
                        .addComponent(EditPackageDetailsUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)
                        .addComponent(EditPackageDetailsDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EditPackageDetailsPanelLayout.createSequentialGroup()
                        .addComponent(ViewPackageDetailsScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))))
            .addGroup(EditPackageDetailsPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditPackageDetailsPanelLayout.createSequentialGroup()
                        .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(EditPackagePackageDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditPackagePackageIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EditpackagespackidValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditPackageDetailsPackDetailsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(EditPackageDetailsPanelLayout.createSequentialGroup()
                        .addComponent(EditPackageDetailsPackagestatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ViewRoomPackageDetailsjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EditPackageDetailsPanelLayout.createSequentialGroup()
                        .addComponent(EditPackageDetailsRoomNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EditPackageDetailsRoomNoValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68)
                .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditPackageDetailsPanelLayout.createSequentialGroup()
                        .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EditPackagePackageAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditPackageDetailsNoofchildrenjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditPAckageDetailsNoofDetailsjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EditPackageDetailsNoofAdultsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditPackageDetailsAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditPackageDetailsNoofChildrenTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(EditPackageDetailsPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(ViewPackageDetailsResetButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(97, 97, 97)
                        .addComponent(EditPackageDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        EditPackageDetailsPanelLayout.setVerticalGroup(
            EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EditPackageDetailsPanelLayout.createSequentialGroup()
                .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EditPackageDetailsPanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditPackageDetailsAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditPackagePackageAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EditPackageDetailsNoofAdultsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditPAckageDetailsNoofDetailsjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditPackageDetailsNoofchildrenjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditPackageDetailsNoofChildrenTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditPackageDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewPackageDetailsResetButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(EditPackageDetailsPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditPackagePackageIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditpackagespackidValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EditPackageDetailsPackDetailsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditPackagePackageDetailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditPackageDetailsPackagestatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewRoomPackageDetailsjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EditPackageDetailsRoomNoValueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(EditPackageDetailsRoomNoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(ViewPackageDetailsScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(EditPackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EditPackageDetailsDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditPackageDetailsUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        PackageRoomTabbedPane.addTab("Edit Package Details", EditPackageDetailsPanel);

        javax.swing.GroupLayout PackageDetailsPanelLayout = new javax.swing.GroupLayout(PackageDetailsPanel);
        PackageDetailsPanel.setLayout(PackageDetailsPanelLayout);
        PackageDetailsPanelLayout.setHorizontalGroup(
            PackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PackageRoomTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        PackageDetailsPanelLayout.setVerticalGroup(
            PackageDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PackageRoomTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        RoomTogglePanel.add(PackageDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));
        PackageDetailsPanel.getAccessibleContext().setAccessibleName("");

        ReservationDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        ReservationDetailsPanel.setMinimumSize(new java.awt.Dimension(790, 580));

        ReservationDetailsTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        ReservationDetailsTabbedPane.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        ReservationDetailsTabbedPane.setMinimumSize(new java.awt.Dimension(780, 510));
        ReservationDetailsTabbedPane.setPreferredSize(new java.awt.Dimension(780, 510));

        makeReservationPanel.setBackground(new java.awt.Color(255, 255, 255));

        AddReservationDetailsSubmitButton.setBackground(new java.awt.Color(255, 255, 255));
        AddReservationDetailsSubmitButton.setForeground(new java.awt.Color(102, 153, 255));
        AddReservationDetailsSubmitButton.setText("Submit");
        AddReservationDetailsSubmitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddReservationDetailsSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddReservationDetailsSubmitButtonActionPerformed(evt);
            }
        });

        AddReservationDetailsBackButton.setBackground(new java.awt.Color(255, 255, 255));
        AddReservationDetailsBackButton.setForeground(new java.awt.Color(102, 153, 255));
        AddReservationDetailsBackButton.setText("Back");
        AddReservationDetailsBackButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddReservationDetailsBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddReservationDetailsBackButtonActionPerformed(evt);
            }
        });

        AddReservationDetailsCustomerIDLabel.setBackground(new java.awt.Color(255, 255, 255));
        AddReservationDetailsCustomerIDLabel.setText("Customer NIC");

        ViewReservationDetailsCancelButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewReservationDetailsCancelButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewReservationDetailsCancelButton.setText("Reset");
        ViewReservationDetailsCancelButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewReservationDetailsCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewReservationDetailsCancelButtonActionPerformed(evt);
            }
        });

        AddReservationDatejLabel.setText("Date");

        AddReservationCusNamejLabel.setText("Customer Name");

        AddReservationCusNICTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddReservationCusNICTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddReservationCusNICTextFieldActionPerformed(evt);
            }
        });
        AddReservationCusNICTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddReservationCusNICTextFieldKeyTyped(evt);
            }
        });

        AddReservationCusNameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddReservationCusNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddReservationCusNameTextFieldKeyTyped(evt);
            }
        });

        CheckoutjLabel1.setText("Check Out");

        ViewRoomDetailsCheckInjLabel1.setText("Check In");

        AddReservationCheckInTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddReservationCheckInTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddReservationCheckInTextFieldKeyTyped(evt);
            }
        });

        AddReservationCheckOutTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddReservationCheckOutTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddReservationCheckOutTextFieldKeyTyped(evt);
            }
        });

        jLabelRoomReseravtionAmount.setText("Amount");

        MakeReservationFinalAmountjlabel.setText("Amount");

        jLabelMakeReservationRoomCount.setText("Room Count");

        WithMealsjRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        WithMealsjRadioButton.setText("With Meals");
        WithMealsjRadioButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 0));
        WithMealsjRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WithMealsjRadioButtonActionPerformed(evt);
            }
        });

        WithoutMealsjRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        WithoutMealsjRadioButton.setText("Without Meals");
        WithoutMealsjRadioButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255), 0));
        WithoutMealsjRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WithoutMealsjRadioButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Meals");

        RoomReservationHeadCountjLabel.setText("Head Count");

        RoomReservationHeadCountTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        RoomReservationHeadCountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomReservationHeadCountTextFieldActionPerformed(evt);
            }
        });
        RoomReservationHeadCountTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RoomReservationHeadCountTextFieldKeyTyped(evt);
            }
        });

        jLabelRoomReseravtionTotalAmount.setText("Total Amount");

        MakeReservationFinaTotallAmountjlabel.setText("Total Amount");

        javax.swing.GroupLayout makeReservationPanelLayout = new javax.swing.GroupLayout(makeReservationPanel);
        makeReservationPanel.setLayout(makeReservationPanelLayout);
        makeReservationPanelLayout.setHorizontalGroup(
            makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(makeReservationPanelLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, makeReservationPanelLayout.createSequentialGroup()
                        .addComponent(AddReservationDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(ViewReservationDetailsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(AddReservationDetailsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, makeReservationPanelLayout.createSequentialGroup()
                        .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(WithoutMealsjRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(makeReservationPanelLayout.createSequentialGroup()
                                .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(makeReservationPanelLayout.createSequentialGroup()
                                        .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(AddReservationCusNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(AddReservationDetailsCustomerIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(AddReservationCusNICTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                            .addComponent(AddReservationCusNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                                    .addGroup(makeReservationPanelLayout.createSequentialGroup()
                                        .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(AddReservationDatejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelRoomReseravtionAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelMakeReservationRoomCount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelRoomReseravtionTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(MakeReservationjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(MakeReservationFinalAmountjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(RoomCountjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(MakeReservationFinaTotallAmountjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(77, 77, 77)
                                .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(makeReservationPanelLayout.createSequentialGroup()
                                        .addComponent(ViewRoomDetailsCheckInjLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(AddReservationCheckInTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(makeReservationPanelLayout.createSequentialGroup()
                                        .addComponent(CheckoutjLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(AddReservationCheckOutTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(makeReservationPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(WithMealsjRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(makeReservationPanelLayout.createSequentialGroup()
                                        .addComponent(RoomReservationHeadCountjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(RoomReservationHeadCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(43, 43, 43))))
        );
        makeReservationPanelLayout.setVerticalGroup(
            makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(makeReservationPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddReservationDetailsCustomerIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddReservationCusNICTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewRoomDetailsCheckInjLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddReservationCheckInTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddReservationCusNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddReservationCusNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckoutjLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddReservationCheckOutTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddReservationDatejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MakeReservationjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RoomReservationHeadCountjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RoomReservationHeadCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRoomReseravtionAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MakeReservationFinalAmountjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WithMealsjRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelMakeReservationRoomCount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RoomCountjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(WithoutMealsjRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRoomReseravtionTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MakeReservationFinaTotallAmountjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(makeReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddReservationDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewReservationDetailsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddReservationDetailsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        ReservationDetailsTabbedPane.addTab("Make Room Reservation ", makeReservationPanel);

        makePackageReservationPanel.setBackground(new java.awt.Color(255, 255, 255));

        AddPAckageReservationDetailsSubmitButton.setBackground(new java.awt.Color(255, 255, 255));
        AddPAckageReservationDetailsSubmitButton.setForeground(new java.awt.Color(102, 153, 255));
        AddPAckageReservationDetailsSubmitButton.setText("Submit");
        AddPAckageReservationDetailsSubmitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPAckageReservationDetailsSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPAckageReservationDetailsSubmitButtonActionPerformed(evt);
            }
        });

        AddPAckageReservationDetailsBackButton.setBackground(new java.awt.Color(255, 255, 255));
        AddPAckageReservationDetailsBackButton.setForeground(new java.awt.Color(102, 153, 255));
        AddPAckageReservationDetailsBackButton.setText("Back");
        AddPAckageReservationDetailsBackButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPAckageReservationDetailsBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPAckageReservationDetailsBackButtonActionPerformed(evt);
            }
        });

        AddPAckageReservationDetailsCustomerIDLabel.setBackground(new java.awt.Color(255, 255, 255));
        AddPAckageReservationDetailsCustomerIDLabel.setText("Customer NIC");

        ViewPAckageReservationDetailsCancelButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewPAckageReservationDetailsCancelButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewPAckageReservationDetailsCancelButton.setText("Reset");
        ViewPAckageReservationDetailsCancelButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewPAckageReservationDetailsCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPAckageReservationDetailsCancelButtonActionPerformed(evt);
            }
        });

        AddPAckageReservationDatejLabel1.setText("Date");

        AddPAckageReservationCusNamejLabel.setText("Customer Name");

        AddPAckageReservationCusNICTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPAckageReservationCusNICTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddPAckageReservationCusNICTextFieldKeyTyped(evt);
            }
        });

        AddPAckageReservationCusNameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPAckageReservationCusNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddPAckageReservationCusNameTextFieldKeyTyped(evt);
            }
        });

        CheckoutjLabel2.setText("Check Out");

        ViewRoomDetailsCheckInjLabel2.setText("Check In");

        AddPAckageReservationCheckInTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPAckageReservationCheckInTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddPAckageReservationCheckInTextFieldKeyTyped(evt);
            }
        });

        AddPAckageReservationCheckOutTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPAckageReservationCheckOutTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddPAckageReservationCheckOutTextFieldKeyTyped(evt);
            }
        });

        jLabelPAckageRoomCount.setText("Room Count");

        AddReservePackageAmountjLabel1.setText("Package Amount");

        jLabelPackAmount.setText("Amt");

        javax.swing.GroupLayout makePackageReservationPanelLayout = new javax.swing.GroupLayout(makePackageReservationPanel);
        makePackageReservationPanel.setLayout(makePackageReservationPanelLayout);
        makePackageReservationPanelLayout.setHorizontalGroup(
            makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, makePackageReservationPanelLayout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(makePackageReservationPanelLayout.createSequentialGroup()
                        .addGroup(makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, makePackageReservationPanelLayout.createSequentialGroup()
                                .addGroup(makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(AddPAckageReservationCusNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddPAckageReservationDetailsCustomerIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AddPAckageReservationCusNICTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(AddPAckageReservationCusNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(makePackageReservationPanelLayout.createSequentialGroup()
                                    .addComponent(jLabelPAckageRoomCount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(PackageRoomCountjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(makePackageReservationPanelLayout.createSequentialGroup()
                                    .addComponent(AddPAckageReservationDatejLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(MakePAckageReservationjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(79, 79, 79)
                        .addGroup(makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(makePackageReservationPanelLayout.createSequentialGroup()
                                .addComponent(ViewRoomDetailsCheckInjLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddPAckageReservationCheckInTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(makePackageReservationPanelLayout.createSequentialGroup()
                                .addComponent(CheckoutjLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddPAckageReservationCheckOutTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(makePackageReservationPanelLayout.createSequentialGroup()
                                .addComponent(AddReservePackageAmountjLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelPackAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(makePackageReservationPanelLayout.createSequentialGroup()
                        .addComponent(AddPAckageReservationDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(ViewPAckageReservationDetailsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(AddPAckageReservationDetailsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129)))
                .addGap(49, 49, 49))
        );
        makePackageReservationPanelLayout.setVerticalGroup(
            makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(makePackageReservationPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddPAckageReservationDetailsCustomerIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPAckageReservationCusNICTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewRoomDetailsCheckInjLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPAckageReservationCheckInTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddPAckageReservationCusNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPAckageReservationCusNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckoutjLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPAckageReservationCheckOutTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(AddReservePackageAmountjLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelPackAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(AddPAckageReservationDatejLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MakePAckageReservationjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPAckageRoomCount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PackageRoomCountjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106)
                .addGroup(makePackageReservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddPAckageReservationDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewPAckageReservationDetailsCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPAckageReservationDetailsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        ReservationDetailsTabbedPane.addTab("Make Package Reservation ", makePackageReservationPanel);

        ViewReservationDetailsjPanel.setBackground(new java.awt.Color(255, 255, 255));

        viewReservationDetailsScrollPane.setBackground(new java.awt.Color(255, 255, 255));

        ViewReservationDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Reservation ID", "Customer NIC", "CustomerName", "Date", "Reservation Amount", "Type", "Meals"
            }
        ));
        ViewReservationDetailsTable.setGridColor(new java.awt.Color(102, 153, 255));
        ViewReservationDetailsTable.getTableHeader().setResizingAllowed(false);
        ViewReservationDetailsTable.getTableHeader().setReorderingAllowed(false);
        ViewReservationDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewReservationDetailsTableMouseClicked(evt);
            }
        });
        viewReservationDetailsScrollPane.setViewportView(ViewReservationDetailsTable);

        ViewReservatiomDetailsSearchvaljLabel.setText("Search by NIC");

        viewReservationDetailssearchTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        viewReservationDetailssearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReservationDetailssearchTextFieldActionPerformed(evt);
            }
        });

        ViewReservationDetailsSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewReservationDetailsSearchButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewReservationDetailsSearchButton.setText("Search");
        ViewReservationDetailsSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewReservationDetailsSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewReservationDetailsSearchButtonActionPerformed(evt);
            }
        });

        ViewReservationDetailsPaymentsButton.setBackground(new java.awt.Color(255, 255, 255));
        ViewReservationDetailsPaymentsButton.setForeground(new java.awt.Color(102, 153, 255));
        ViewReservationDetailsPaymentsButton.setText("Payments");
        ViewReservationDetailsPaymentsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        ViewReservationDetailsPaymentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewReservationDetailsPaymentsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ViewReservationDetailsjPanelLayout = new javax.swing.GroupLayout(ViewReservationDetailsjPanel);
        ViewReservationDetailsjPanel.setLayout(ViewReservationDetailsjPanelLayout);
        ViewReservationDetailsjPanelLayout.setHorizontalGroup(
            ViewReservationDetailsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewReservationDetailsjPanelLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(ViewReservationDetailsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ViewReservationDetailsjPanelLayout.createSequentialGroup()
                        .addComponent(ViewReservatiomDetailsSearchvaljLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewReservationDetailssearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(ViewReservationDetailsSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ViewReservationDetailsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewReservationDetailsjPanelLayout.createSequentialGroup()
                            .addComponent(viewReservationDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(52, 52, 52))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewReservationDetailsjPanelLayout.createSequentialGroup()
                            .addComponent(ViewReservationDetailsPaymentsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(78, 78, 78)))))
        );
        ViewReservationDetailsjPanelLayout.setVerticalGroup(
            ViewReservationDetailsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewReservationDetailsjPanelLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(ViewReservationDetailsjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ViewReservatiomDetailsSearchvaljLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewReservationDetailssearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewReservationDetailsSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(viewReservationDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(ViewReservationDetailsPaymentsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        ReservationDetailsTabbedPane.addTab("Reservation Details", ViewReservationDetailsjPanel);

        javax.swing.GroupLayout ReservationDetailsPanelLayout = new javax.swing.GroupLayout(ReservationDetailsPanel);
        ReservationDetailsPanel.setLayout(ReservationDetailsPanelLayout);
        ReservationDetailsPanelLayout.setHorizontalGroup(
            ReservationDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReservationDetailsTabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        ReservationDetailsPanelLayout.setVerticalGroup(
            ReservationDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReservationDetailsTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        RoomTogglePanel.add(ReservationDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        RoomServicePanel.setBackground(new java.awt.Color(255, 255, 255));
        RoomServicePanel.setMinimumSize(new java.awt.Dimension(790, 580));

        RoomServiceTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        RoomServiceTabbedPane.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 14)); // NOI18N
        RoomServiceTabbedPane.setMinimumSize(new java.awt.Dimension(780, 510));
        RoomServiceTabbedPane.setPreferredSize(new java.awt.Dimension(780, 510));

        AddCustomizedPackagesPanel.setBackground(new java.awt.Color(255, 255, 255));

        CustomizedroomServiceNiCjLabel.setText("Customer NIC  ");

        AddCustomizedRoomServiceAddjButton.setBackground(new java.awt.Color(255, 255, 255));
        AddCustomizedRoomServiceAddjButton.setForeground(new java.awt.Color(102, 153, 255));
        AddCustomizedRoomServiceAddjButton.setText("Submit");
        AddCustomizedRoomServiceAddjButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddCustomizedRoomServiceAddjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCustomizedRoomServiceAddjButtonActionPerformed(evt);
            }
        });

        AddCustomizeCanceljButton.setBackground(new java.awt.Color(255, 255, 255));
        AddCustomizeCanceljButton.setForeground(new java.awt.Color(102, 153, 255));
        AddCustomizeCanceljButton.setText("Back");
        AddCustomizeCanceljButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddCustomizeCanceljButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCustomizeCanceljButtonActionPerformed(evt);
            }
        });

        CustomizedroomServicevalueNiC.setText("NIC");

        AddCustomizeRoomServiceNoofPlatesjLabel.setText("No of Plates  ");

        AddCustomizeRoomServiceNoofPlatesTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddCustomizeRoomServiceNoofPlatesTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCustomizeRoomServiceNoofPlatesTextFieldActionPerformed(evt);
            }
        });

        CustomizeSelectjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Mixed Fried Rice (Basmathi)-Full", "280.00"},
                {"Mixed Fried Rice (Basmathi)-Half", "180.00"},
                {"Pasta with Cheese Sauce-Full", "220.00"},
                {"Pasta with Cheese Sauce-Half", "200.00"},
                {"Deviled Chicken-Full", "320.00"},
                {"Deviled Chicken-Half", "200.00"},
                {"Pork Black Curry-Full", "250.00"},
                {"Pork Black Curry-Half", "210.00"},
                {"Battered Mushrooms-Full", "250.00"},
                {"Battered Mushrooms-Half", "210.00"},
                {"Mix Vegetable Curry-Full", "150.00"},
                {"Mix Vegetable Curry-Half", "110.00"},
                {"Potato Tempered-Full", "150.00"},
                {"Potato Tempered-Half", "110.00"},
                {"Vegetable Noodles-Full", "230.00"},
                {"Vegetable Noodles-Half", "210.00"},
                {"Papadam-Full", "150.00"},
                {"Papadam-Half", "110.00"},
                {"Fish Ambulthiyal-Full", "260.00"},
                {"Fish Ambulthiyal-Half", "210.00"},
                {"Ice Cream (Vanilla)-Full", "150.00"},
                {"Ice Cream (Vanilla)-Half", "110.00"},
                {"Ice Cream (Chocolate)-Full", "170.00"},
                {"Ice Cream (Chocolate)-Half", "140.00"},
                {"Watalappan-Full ", "150.00"},
                {"Watalappan-Half", "110.00"},
                {"White Rice-Full", "200.00"},
                {"White Rice-Half", "180.00"},
                {"Noodles-Full", "180.00"},
                {"Noodles-Half", null},
                {"String Hoppers-Full", "250.00"},
                {"String Hoppers-Half", "210.00"},
                {"Sambol-Full", "150.00"},
                {"Sambol-Half", "110.00"},
                {"Prawns Curry-Full", "450.00"},
                {"Prawns Curry-Half", "410.00"},
                {"Cashew Greenpeace Curry-Full", "150.00"},
                {"Cashew Greenpeace Curry-Half", "120.00"},
                {"Maldives Fish Sambol-Full", "250.00"},
                {"Maldives Fish Sambol-Half", "210.00"},
                {"Chutney-Full", "350.00"},
                {"Chutney-Half", "310.00"},
                {"Fish Cutlets-Full", "150.00"},
                {"Fish Cutlets-Half", "110.00"},
                {"Potatoes White Curry-Full", "260.00"},
                {"Potatoes White Curry-Half", "210.00"},
                {"Gotukola Curry-Full", "250.00"},
                {"Gotukola Curry-Half", "210.00"},
                {"Brinjal Tempered-Full", "150.00"},
                {"Brinjal Tempered-Half", "110.00"},
                {"Jelly-Full", "200.00"},
                {"Jelly-Half", "190.00"},
                {"Ice Coffee-Full", "150.00"},
                {"Ice Coffee-Half", "110.00"},
                {"Dhal Curry-Full", "280.00"},
                {"Dhal Curry-Half", "230.00"},
                {"Brinjal Moju-Full", "150.00"},
                {"Brinjal Moju-Half", "110.00"},
                {"Egg Salad-Full", "150.00"},
                {"Egg Salad-Half", "110.00"},
                {"Malay Pickle-Full", "150.00"},
                {"Malay Pickle-Half", "110.00"},
                {"Fried Rice (Keeri Samba)-Full", "240.00"},
                {"Fried Rice (Keeri Samba)-Half", "200.00"},
                {"Mushrooms with Garlic Sauce-Full", "260.00"},
                {"Mushrooms with Garlic Sauce-Half", "210.00"},
                {"Russian Salad-Full", "150.00"},
                {"Russian Salad-Half", "110.00"},
                {"Devilled Prawns-Full", "350.00"},
                {"Devilled Prawns-Half", "310.00"},
                {"Welcome Drink-Full", "80.00"},
                {"Fried Rice (Basmathi)-Full", "200.00"},
                {"Steam Rice-Full", "100.00"},
                {"Fish Stew/Fish Sweet Sauce-Full", "120.00"},
                {"Brinjal Pahi-Full", "110.00"},
                {"Pineapple Red Curry-Full", "90.00"},
                {"Chicken Salad-Full", "120.00"},
                {"Raita Salad-Full", "100.00"},
                {"Chilli Paste-Full", "70.00"},
                {"Fruit Trifle-Full", "80.00"},
                {"Chocolate Biscuit Pudding-Full", "110.00"},
                {"Cut Fruits-Full", "150.00"},
                {"Plain Rice (Basmathi)-Full", "110.00"},
                {"Macaroni with Chili Sauce-Full", "140.00"},
                {"Chicken Black Curry-Full", "150.00"},
                {"Fish Stew/Ambulthiyal-Full", "120.00"}
            },
            new String [] {
                "Food Item", "Price per plate(Rs)"
            }
        ));
        CustomizeSelectjTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(CustomizeSelectjTable);
        if (CustomizeSelectjTable.getColumnModel().getColumnCount() > 0) {
            CustomizeSelectjTable.getColumnModel().getColumn(0).setHeaderValue("Food Item");
            CustomizeSelectjTable.getColumnModel().getColumn(1).setHeaderValue("Price per plate(Rs)");
        }

        CustomizeRequestedjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Food Item", "Price per plate(Rs)"
            }
        ));
        jScrollPane7.setViewportView(CustomizeRequestedjTable);

        jButtonAddRoomServiceToReserve.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAddRoomServiceToReserve.setForeground(new java.awt.Color(102, 153, 255));
        jButtonAddRoomServiceToReserve.setText("ADD");
        jButtonAddRoomServiceToReserve.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jButtonAddRoomServiceToReserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddRoomServiceToReserveActionPerformed(evt);
            }
        });

        jButtonRemoveRoomToReserve.setBackground(new java.awt.Color(255, 255, 255));
        jButtonRemoveRoomToReserve.setForeground(new java.awt.Color(102, 153, 255));
        jButtonRemoveRoomToReserve.setText("REMOVE");
        jButtonRemoveRoomToReserve.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jButtonRemoveRoomToReserve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveRoomToReserveActionPerformed(evt);
            }
        });

        jLabelTotalAmountRoomService.setText("Total Amount");

        jButtonTotalAmountRoomService.setBackground(new java.awt.Color(255, 255, 255));
        jButtonTotalAmountRoomService.setForeground(new java.awt.Color(102, 153, 255));
        jButtonTotalAmountRoomService.setText("Total");
        jButtonTotalAmountRoomService.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jButtonTotalAmountRoomService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTotalAmountRoomServiceActionPerformed(evt);
            }
        });

        RoomServiceDatejLabel1.setText("Date");

        AddRoomServiceCustomizedPackagejDateChooser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        AddRooomServiceRoomNojLabel1.setText("Room No");

        AddRoomServiceCustomizedRoomNoTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddRoomServiceCustomizedRoomNoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRoomServiceCustomizedRoomNoTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddCustomizedPackagesPanelLayout = new javax.swing.GroupLayout(AddCustomizedPackagesPanel);
        AddCustomizedPackagesPanel.setLayout(AddCustomizedPackagesPanelLayout);
        AddCustomizedPackagesPanelLayout.setHorizontalGroup(
            AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddCustomizedPackagesPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(CustomizedroomServiceNiCjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CustomizedroomServicevalueNiC, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AddRooomServiceRoomNojLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddCustomizeRoomServiceNoofPlatesjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddCustomizeRoomServiceNoofPlatesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddRoomServiceCustomizedRoomNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(AddCustomizedPackagesPanelLayout.createSequentialGroup()
                .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddCustomizedPackagesPanelLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(RoomServiceDatejLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddRoomServiceCustomizedPackagejDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE))
                    .addGroup(AddCustomizedPackagesPanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddCustomizedPackagesPanelLayout.createSequentialGroup()
                        .addComponent(AddCustomizedRoomServiceAddjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(AddCustomizeCanceljButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddCustomizedPackagesPanelLayout.createSequentialGroup()
                        .addComponent(jLabelTotalAmountRoomService, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRoomServiceTotalAmountToPAy, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonTotalAmountRoomService, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AddCustomizedPackagesPanelLayout.createSequentialGroup()
                        .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonRemoveRoomToReserve, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAddRoomServiceToReserve, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
        );
        AddCustomizedPackagesPanelLayout.setVerticalGroup(
            AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCustomizedPackagesPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomizedroomServiceNiCjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomizedroomServicevalueNiC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddCustomizeRoomServiceNoofPlatesjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddCustomizeRoomServiceNoofPlatesTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(RoomServiceDatejLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AddRoomServiceCustomizedPackagejDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(AddRooomServiceRoomNojLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AddRoomServiceCustomizedRoomNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddCustomizedPackagesPanelLayout.createSequentialGroup()
                        .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AddCustomizedPackagesPanelLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(AddCustomizedPackagesPanelLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jButtonAddRoomServiceToReserve, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jButtonRemoveRoomToReserve, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTotalAmountRoomService, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldRoomServiceTotalAmountToPAy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonTotalAmountRoomService, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addGroup(AddCustomizedPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddCustomizedRoomServiceAddjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddCustomizeCanceljButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AddCustomizedPackagesPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        RoomServiceTabbedPane.addTab("Add customize Food Package", AddCustomizedPackagesPanel);

        RoomServiceOrdersjPanel.setBackground(new java.awt.Color(255, 255, 255));

        RoomServiceOrderDetailsScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        RoomServiceOrderDetailsScrollPane.setMinimumSize(new java.awt.Dimension(780, 500));
        RoomServiceOrderDetailsScrollPane.setPreferredSize(new java.awt.Dimension(780, 500));

        RoomServiceOrderrDetailsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        RoomServiceOrderrDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer NIC", "Room NO", "No of Plates", "Regular", "Customized", "Date", "Price", "Package", "Status", "Description"
            }
        ));
        RoomServiceOrderrDetailsTable.setGridColor(new java.awt.Color(102, 153, 255));
        RoomServiceOrderrDetailsTable.setMinimumSize(new java.awt.Dimension(375, 480));
        RoomServiceOrderrDetailsTable.getTableHeader().setResizingAllowed(false);
        RoomServiceOrderrDetailsTable.getTableHeader().setReorderingAllowed(false);
        RoomServiceOrderrDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RoomServiceOrderrDetailsTableMouseClicked(evt);
            }
        });
        RoomServiceOrderDetailsScrollPane.setViewportView(RoomServiceOrderrDetailsTable);

        RoomServiceOrdersjLabel.setText("Customer NIC");

        RoomServiceSearchTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        RoomServiceSearchTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomServiceSearchTextFieldActionPerformed(evt);
            }
        });

        RoomServiceOrdersSearchjButton.setBackground(new java.awt.Color(255, 255, 255));
        RoomServiceOrdersSearchjButton.setForeground(new java.awt.Color(102, 153, 255));
        RoomServiceOrdersSearchjButton.setText("Search");
        RoomServiceOrdersSearchjButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        RoomServiceOrdersSearchjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomServiceOrdersSearchjButtonActionPerformed(evt);
            }
        });

        RoomServicejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cus NIC", "Amount"
            }
        ));
        jScrollPane4.setViewportView(RoomServicejTable);

        AddRoomServicejButton.setBackground(new java.awt.Color(255, 255, 255));
        AddRoomServicejButton.setForeground(new java.awt.Color(102, 153, 255));
        AddRoomServicejButton.setText("Add");
        AddRoomServicejButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddRoomServicejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRoomServicejButtonActionPerformed(evt);
            }
        });

        AddRoomServiceDeletejButton.setBackground(new java.awt.Color(255, 255, 255));
        AddRoomServiceDeletejButton.setForeground(new java.awt.Color(102, 153, 255));
        AddRoomServiceDeletejButton.setText("Delete");
        AddRoomServiceDeletejButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddRoomServiceDeletejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRoomServiceDeletejButtonActionPerformed(evt);
            }
        });

        CAlculateRoomServiceTotaljButton.setBackground(new java.awt.Color(255, 255, 255));
        CAlculateRoomServiceTotaljButton.setForeground(new java.awt.Color(102, 153, 255));
        CAlculateRoomServiceTotaljButton.setText("Total");
        CAlculateRoomServiceTotaljButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        CAlculateRoomServiceTotaljButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CAlculateRoomServiceTotaljButtonActionPerformed(evt);
            }
        });

        RoomServiceTotaljLabel.setText("Total");

        AddRoomServiceDetailsBackToPAymentsButton.setBackground(new java.awt.Color(255, 255, 255));
        AddRoomServiceDetailsBackToPAymentsButton.setForeground(new java.awt.Color(102, 153, 255));
        AddRoomServiceDetailsBackToPAymentsButton.setText("Payments");
        AddRoomServiceDetailsBackToPAymentsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddRoomServiceDetailsBackToPAymentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRoomServiceDetailsBackToPAymentsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RoomServiceOrdersjPanelLayout = new javax.swing.GroupLayout(RoomServiceOrdersjPanel);
        RoomServiceOrdersjPanel.setLayout(RoomServiceOrdersjPanelLayout);
        RoomServiceOrdersjPanelLayout.setHorizontalGroup(
            RoomServiceOrdersjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoomServiceOrdersjPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(RoomServiceOrdersjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoomServiceOrdersjPanelLayout.createSequentialGroup()
                        .addComponent(RoomServiceOrderDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoomServiceOrdersjPanelLayout.createSequentialGroup()
                        .addGroup(RoomServiceOrdersjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(AddRoomServicejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddRoomServiceDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(423, 423, 423))))
            .addGroup(RoomServiceOrdersjPanelLayout.createSequentialGroup()
                .addGroup(RoomServiceOrdersjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RoomServiceOrdersjPanelLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(RoomServiceOrdersjLabel)
                        .addGap(18, 18, 18)
                        .addComponent(RoomServiceSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(RoomServiceOrdersSearchjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RoomServiceOrdersjPanelLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addGroup(RoomServiceOrdersjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(RoomServiceOrdersjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(RoomServiceTotaljLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(RoomServiceTotalAmountjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CAlculateRoomServiceTotaljButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddRoomServiceDetailsBackToPAymentsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        RoomServiceOrdersjPanelLayout.setVerticalGroup(
            RoomServiceOrdersjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoomServiceOrdersjPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(RoomServiceOrdersjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RoomServiceOrdersjLabel)
                    .addComponent(RoomServiceSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RoomServiceOrdersSearchjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(RoomServiceOrderDetailsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(RoomServiceOrdersjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RoomServiceOrdersjPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(AddRoomServicejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(AddRoomServiceDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(57, Short.MAX_VALUE))
                    .addGroup(RoomServiceOrdersjPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RoomServiceTotaljLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RoomServiceTotalAmountjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CAlculateRoomServiceTotaljButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AddRoomServiceDetailsBackToPAymentsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );

        RoomServiceTabbedPane.addTab("Room Service Orders", RoomServiceOrdersjPanel);

        javax.swing.GroupLayout RoomServicePanelLayout = new javax.swing.GroupLayout(RoomServicePanel);
        RoomServicePanel.setLayout(RoomServicePanelLayout);
        RoomServicePanelLayout.setHorizontalGroup(
            RoomServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RoomServiceTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        RoomServicePanelLayout.setVerticalGroup(
            RoomServicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RoomServiceTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        RoomTogglePanel.add(RoomServicePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        PaymentsDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        PaymentsDetailsPanel.setMinimumSize(new java.awt.Dimension(790, 580));
        PaymentsDetailsPanel.setPreferredSize(new java.awt.Dimension(790, 565));

        RoomAddPaymentsPanel.setBackground(new java.awt.Color(255, 255, 255));

        AddRoomPaymentMethodjlabel.setText("Payment Method");

        AddRoomPaymentAmountjlabel.setText("Reservation Amount");

        AddRoomPaymentDescjlabel.setText("Description");

        AddRoomPaymentjComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Payment Methods", "Bank", "Money" }));
        AddRoomPaymentjComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRoomPaymentjComboBoxActionPerformed(evt);
            }
        });

        AddRoomPaymentdescDesc.setColumns(20);
        AddRoomPaymentdescDesc.setRows(5);
        AddPaymentsDescjScrollPane.setViewportView(AddRoomPaymentdescDesc);

        AddRoomPaymentDueDatejLabel.setText(" Date");

        AddRoomPaymentcodjlabel.setText("Credit/Debit");

        AddPaymentsCODjComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Debit", "Credit" }));

        AddRoomPaymentcusNICjLabel.setText("Customer NIC  ");

        AddRoomPaymentCusNICvaluejlabel.setText("NIC");

        AddPaymentsDetailsSubmitButton.setBackground(new java.awt.Color(255, 255, 255));
        AddPaymentsDetailsSubmitButton.setForeground(new java.awt.Color(102, 153, 255));
        AddPaymentsDetailsSubmitButton.setText("Submit");
        AddPaymentsDetailsSubmitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPaymentsDetailsSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPaymentsDetailsSubmitButtonActionPerformed(evt);
            }
        });

        AddPaymentRsIDjLabel.setText("Reservation ID");

        AddPaymentsRIDValuejLabel.setText("RID");

        AddPaymentsDetailsBackButton.setBackground(new java.awt.Color(255, 255, 255));
        AddPaymentsDetailsBackButton.setForeground(new java.awt.Color(102, 153, 255));
        AddPaymentsDetailsBackButton.setText("Back");
        AddPaymentsDetailsBackButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPaymentsDetailsBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPaymentsDetailsBackButtonActionPerformed(evt);
            }
        });

        AddRoomPaymentRSAmountjlabel.setText("Room Service Amount");

        AddPaymentsDetailsCheckRSButton.setBackground(new java.awt.Color(255, 255, 255));
        AddPaymentsDetailsCheckRSButton.setForeground(new java.awt.Color(102, 153, 255));
        AddPaymentsDetailsCheckRSButton.setText(" Room Service");
        AddPaymentsDetailsCheckRSButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPaymentsDetailsCheckRSButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPaymentsDetailsCheckRSButtonActionPerformed(evt);
            }
        });

        AddPaymentsDetailsLaundryRSButton.setBackground(new java.awt.Color(255, 255, 255));
        AddPaymentsDetailsLaundryRSButton.setForeground(new java.awt.Color(102, 153, 255));
        AddPaymentsDetailsLaundryRSButton.setText("Laundry");
        AddPaymentsDetailsLaundryRSButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPaymentsDetailsLaundryRSButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPaymentsDetailsLaundryRSButtonActionPerformed(evt);
            }
        });

        MakepaymentsLaundryAmountjLabel.setText("Laundry Amount");

        AddPaymentCheckInjLabel.setText("Check In");

        AddPaymentsCheckOutjLabel.setText("Check Out");

        AddRoomPaymentCIjLabel.setText("CI");

        AddRoomPaymentCOjlabel.setText("CO");

        MakepaymentsTotalAmountjLabel.setText("Total Amount");

        AddPaymentsDetailsLaundryRSButton1.setBackground(new java.awt.Color(255, 255, 255));
        AddPaymentsDetailsLaundryRSButton1.setForeground(new java.awt.Color(102, 153, 255));
        AddPaymentsDetailsLaundryRSButton1.setText("Total");
        AddPaymentsDetailsLaundryRSButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPaymentsDetailsLaundryRSButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPaymentsDetailsLaundryRSButton1ActionPerformed(evt);
            }
        });

        AddPrintPaymentDetailsBackButton.setBackground(new java.awt.Color(255, 255, 255));
        AddPrintPaymentDetailsBackButton.setForeground(new java.awt.Color(102, 153, 255));
        AddPrintPaymentDetailsBackButton.setText("Print");
        AddPrintPaymentDetailsBackButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPrintPaymentDetailsBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPrintPaymentDetailsBackButtonActionPerformed(evt);
            }
        });

        jTableToPrintPaymentDetails.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableToPrintPaymentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CusNIC", "Cusname", "Room Amount", "Room Service Amount", "Laundary amount"
            }
        ));
        jTableToPrintPaymentDetails.setGridColor(new java.awt.Color(102, 153, 255));
        jScrollPane3.setViewportView(jTableToPrintPaymentDetails);

        AddPaymentsDetailsAddToTableButton.setBackground(new java.awt.Color(255, 255, 255));
        AddPaymentsDetailsAddToTableButton.setForeground(new java.awt.Color(102, 153, 255));
        AddPaymentsDetailsAddToTableButton.setText("Add");
        AddPaymentsDetailsAddToTableButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPaymentsDetailsAddToTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPaymentsDetailsAddToTableButtonActionPerformed(evt);
            }
        });

        AddPaymentsDetailsCalculatorButton.setBackground(new java.awt.Color(255, 255, 255));
        AddPaymentsDetailsCalculatorButton.setForeground(new java.awt.Color(102, 153, 255));
        AddPaymentsDetailsCalculatorButton.setText("Calculator");
        AddPaymentsDetailsCalculatorButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddPaymentsDetailsCalculatorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPaymentsDetailsCalculatorButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RoomAddPaymentsPanelLayout = new javax.swing.GroupLayout(RoomAddPaymentsPanel);
        RoomAddPaymentsPanel.setLayout(RoomAddPaymentsPanelLayout);
        RoomAddPaymentsPanelLayout.setHorizontalGroup(
            RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoomAddPaymentsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(AddRoomPaymentcusNICjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                        .addComponent(AddRoomPaymentAmountjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(AddRoomPaymentTextFields, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                        .addComponent(AddRoomPaymentRSAmountjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(AddRoomPaymentRSamountValueTextFields, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddPaymentsCheckOutjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(AddPaymentCheckInjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(AddPaymentRsIDjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(2, 2, 2)
                                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(AddPaymentsRIDValuejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(AddRoomPaymentCIjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(AddRoomPaymentCOjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(AddRoomPaymentCusNICvaluejlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(MakepaymentsTotalAmountjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                        .addComponent(MakepaymentsLaundryAmountjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(AddRoomPaymentTotalValueTextFields, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(AddRoomPaymentLaValueTextFields, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(73, 73, 73)
                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddRoomPaymentDueDatejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddRoomPaymentDescjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddRoomPaymentcodjlabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AddPaymentsCODjComboBox, 0, 150, Short.MAX_VALUE)
                                    .addComponent(AddRoomPaymentDueDatejDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(AddPaymentsDescjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(AddPaymentsDetailsAddToTableButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                .addComponent(AddRoomPaymentMethodjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddRoomPaymentjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(AddPaymentsDetailsLaundryRSButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(AddPaymentsDetailsLaundryRSButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(AddPaymentsDetailsCheckRSButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(AddPaymentsDetailsCalculatorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(AddPrintPaymentDetailsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(AddPaymentsDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(129, 129, 129)
                        .addComponent(AddPaymentsDetailsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        RoomAddPaymentsPanelLayout.setVerticalGroup(
            RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddRoomPaymentcusNICjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddRoomPaymentCusNICvaluejlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddRoomPaymentMethodjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddRoomPaymentjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddPaymentRsIDjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPaymentsRIDValuejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddRoomPaymentcodjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPaymentsCODjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddPaymentCheckInjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddRoomPaymentCIjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddPaymentsCheckOutjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddRoomPaymentCOjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddRoomPaymentAmountjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddRoomPaymentTextFields, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddRoomPaymentRSAmountjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddRoomPaymentRSamountValueTextFields, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddRoomPaymentLaValueTextFields, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MakepaymentsLaundryAmountjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddPaymentsDetailsLaundryRSButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddRoomPaymentDueDatejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddRoomPaymentDueDatejDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddRoomPaymentDescjlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoomAddPaymentsPanelLayout.createSequentialGroup()
                                .addComponent(AddPaymentsDescjScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)))
                        .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddPaymentsDetailsCheckRSButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(RoomAddPaymentsPanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(AddPaymentsDetailsCalculatorButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20)
                .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddRoomPaymentTotalValueTextFields, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MakepaymentsTotalAmountjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPaymentsDetailsLaundryRSButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPaymentsDetailsAddToTableButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(RoomAddPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddPrintPaymentDetailsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPaymentsDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddPaymentsDetailsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        PaymentDetailsjTabbedPane.addTab("Add Payment", RoomAddPaymentsPanel);

        RoomupdatePaymentsPanel.setBackground(new java.awt.Color(255, 255, 255));

        RoomUpdatePaymentsNICjLabel.setText("Enter Customer NIC");

        RoomUpdatePaymentsNICvalueTextfield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        UpdateValuesgetValuejPanel.setBackground(new java.awt.Color(255, 255, 255));
        UpdateValuesgetValuejPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        UpdateRoomPaymentPaymentMethodjLabel.setText("Payment Method");
        UpdateValuesgetValuejPanel.add(UpdateRoomPaymentPaymentMethodjLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 150, 30));

        UpdateRoomPaymentPaymentMethodjComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Method", "Bank", "Money", " " }));
        UpdateRoomPaymentPaymentMethodjComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        UpdateValuesgetValuejPanel.add(UpdateRoomPaymentPaymentMethodjComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 150, 30));

        UpdateRoomPaymentAmountjLabel.setText("Amount");
        UpdateValuesgetValuejPanel.add(UpdateRoomPaymentAmountjLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 150, 30));

        UpdateRoomPaymentAmountTextFields.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        UpdateValuesgetValuejPanel.add(UpdateRoomPaymentAmountTextFields, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 150, 30));

        UpdateRoomPaymentCODjLabel.setText("Credit/Debit");
        UpdateValuesgetValuejPanel.add(UpdateRoomPaymentCODjLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 150, 30));

        UpdateRoomPaymentCODjComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        UpdateRoomPaymentCODjComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        UpdateValuesgetValuejPanel.add(UpdateRoomPaymentCODjComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 150, 30));

        UpdateRoomPaymentDescLabel.setText("Description");
        UpdateValuesgetValuejPanel.add(UpdateRoomPaymentDescLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 150, 30));

        UpdateRoomPaymenttDescription.setColumns(20);
        UpdateRoomPaymenttDescription.setRows(5);
        UpdateRoomPaymenttDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        UpdatePaneDescjScrollPane.setViewportView(UpdateRoomPaymenttDescription);

        UpdateValuesgetValuejPanel.add(UpdatePaneDescjScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 160, -1));

        UpdateRoomPaymentDueDatejlabel.setText(" Date");
        UpdateValuesgetValuejPanel.add(UpdateRoomPaymentDueDatejlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 150, 30));

        UpdateRoomPaymentDueDateTextFeilds.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        UpdateRoomPaymentDueDateTextFeilds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateRoomPaymentDueDateTextFeildsActionPerformed(evt);
            }
        });
        UpdateValuesgetValuejPanel.add(UpdateRoomPaymentDueDateTextFeilds, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 160, 30));

        UpdatePaymentsDetailsSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        UpdatePaymentsDetailsSearchButton.setForeground(new java.awt.Color(102, 153, 255));
        UpdatePaymentsDetailsSearchButton.setText("Search");
        UpdatePaymentsDetailsSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        UpdatePaymentsDetailsSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatePaymentsDetailsSearchButtonActionPerformed(evt);
            }
        });

        UpdatePaymentDetailsUpdateButton.setBackground(new java.awt.Color(255, 255, 255));
        UpdatePaymentDetailsUpdateButton.setForeground(new java.awt.Color(102, 153, 255));
        UpdatePaymentDetailsUpdateButton.setText("Update");
        UpdatePaymentDetailsUpdateButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        UpdatePaymentDetailsUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdatePaymentDetailsUpdateButtonActionPerformed(evt);
            }
        });

        UpdateRoompaymentDetailsjTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        UpdateRoompaymentDetailsjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Customer NIC", "Room No", "Payment Method", "Amount", "Credit/Debit", "Date", "Description"
            }
        ));
        UpdateRoompaymentDetailsjTable.setGridColor(new java.awt.Color(102, 153, 255));
        AddPaymentsdetailsjScrollPane1.setViewportView(UpdateRoompaymentDetailsjTable);

        javax.swing.GroupLayout RoomupdatePaymentsPanelLayout = new javax.swing.GroupLayout(RoomupdatePaymentsPanel);
        RoomupdatePaymentsPanel.setLayout(RoomupdatePaymentsPanelLayout);
        RoomupdatePaymentsPanelLayout.setHorizontalGroup(
            RoomupdatePaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoomupdatePaymentsPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(UpdatePaymentDetailsUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
            .addGroup(RoomupdatePaymentsPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(RoomupdatePaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RoomupdatePaymentsPanelLayout.createSequentialGroup()
                        .addComponent(RoomUpdatePaymentsNICjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RoomUpdatePaymentsNICvalueTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(UpdatePaymentsDetailsSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(168, 168, 168))
                    .addGroup(RoomupdatePaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(UpdateValuesgetValuejPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(AddPaymentsdetailsjScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        RoomupdatePaymentsPanelLayout.setVerticalGroup(
            RoomupdatePaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoomupdatePaymentsPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(RoomupdatePaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RoomUpdatePaymentsNICjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RoomUpdatePaymentsNICvalueTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdatePaymentsDetailsSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(UpdateValuesgetValuejPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(AddPaymentsdetailsjScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(UpdatePaymentDetailsUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        PaymentDetailsjTabbedPane.addTab("Update Payment", RoomupdatePaymentsPanel);

        javax.swing.GroupLayout PaymentsDetailsPanelLayout = new javax.swing.GroupLayout(PaymentsDetailsPanel);
        PaymentsDetailsPanel.setLayout(PaymentsDetailsPanelLayout);
        PaymentsDetailsPanelLayout.setHorizontalGroup(
            PaymentsDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaymentDetailsjTabbedPane)
        );
        PaymentsDetailsPanelLayout.setVerticalGroup(
            PaymentsDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PaymentDetailsjTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        RoomTogglePanel.add(PaymentsDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        RoomReportsPanel.setBackground(new java.awt.Color(255, 255, 255));
        RoomReportsPanel.setMinimumSize(new java.awt.Dimension(790, 580));
        RoomReportsPanel.setPreferredSize(new java.awt.Dimension(790, 565));

        javax.swing.GroupLayout RoomReportsPanelLayout = new javax.swing.GroupLayout(RoomReportsPanel);
        RoomReportsPanel.setLayout(RoomReportsPanelLayout);
        RoomReportsPanelLayout.setHorizontalGroup(
            RoomReportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 790, Short.MAX_VALUE)
        );
        RoomReportsPanelLayout.setVerticalGroup(
            RoomReportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        RoomTogglePanel.add(RoomReportsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        StockDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        StockDetailsPanel.setMinimumSize(new java.awt.Dimension(790, 580));
        StockDetailsPanel.setPreferredSize(new java.awt.Dimension(790, 565));

        RoomStockItemIDjLabel.setText("Item ID");

        RoomStockItemNamejLabel.setText("Item Name");

        RoomStockItemCategoryjLabel.setText("Item Category");

        RoomStockDepartmentjLabel.setText("Department");

        RoomStockOrderqtyjLabel.setText("Order Qty");

        RoomStockUnitsjLabel.setText("Units");

        RoomStockunitsjTextField.setEditable(false);
        RoomStockunitsjTextField.setText("uneditable, select units from item table");
        RoomStockunitsjTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        RoomStockunitsjTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomStockunitsjTextFieldActionPerformed(evt);
            }
        });

        RoomStockqtyjTextField.setText("Enter Qty");
        RoomStockqtyjTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        RoomStockDepartmentjTextField.setEditable(false);
        RoomStockDepartmentjTextField.setText("uneditable, autoselect dept");
        RoomStockDepartmentjTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        RoomStockItemCategoryjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "items table" }));
        RoomStockItemCategoryjComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        RoomStockItemNamejComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "items table" }));
        RoomStockItemNamejComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        RoomStockjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "retrieve from items table" }));
        RoomStockjComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        RoomStockRequestOrderButton.setBackground(new java.awt.Color(255, 255, 255));
        RoomStockRequestOrderButton.setForeground(new java.awt.Color(102, 153, 255));
        RoomStockRequestOrderButton.setText("Request Order");
        RoomStockRequestOrderButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        RoomStockRequestOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomStockRequestOrderButtonActionPerformed(evt);
            }
        });

        RoomstockClearButton.setBackground(new java.awt.Color(255, 255, 255));
        RoomstockClearButton.setForeground(new java.awt.Color(102, 153, 255));
        RoomstockClearButton.setText("Clear");
        RoomstockClearButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        RoomstockClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomstockClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout StockDetailsPanelLayout = new javax.swing.GroupLayout(StockDetailsPanel);
        StockDetailsPanel.setLayout(StockDetailsPanelLayout);
        StockDetailsPanelLayout.setHorizontalGroup(
            StockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StockDetailsPanelLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addGroup(StockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(StockDetailsPanelLayout.createSequentialGroup()
                        .addComponent(RoomStockItemIDjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(RoomStockjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StockDetailsPanelLayout.createSequentialGroup()
                        .addComponent(RoomStockUnitsjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RoomStockunitsjTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StockDetailsPanelLayout.createSequentialGroup()
                        .addComponent(RoomStockOrderqtyjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RoomStockqtyjTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StockDetailsPanelLayout.createSequentialGroup()
                        .addComponent(RoomStockDepartmentjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RoomStockDepartmentjTextField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StockDetailsPanelLayout.createSequentialGroup()
                        .addComponent(RoomStockItemCategoryjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RoomStockItemCategoryjComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(StockDetailsPanelLayout.createSequentialGroup()
                        .addComponent(RoomStockItemNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(RoomStockItemNamejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(StockDetailsPanelLayout.createSequentialGroup()
                        .addComponent(RoomstockClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RoomStockRequestOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(222, Short.MAX_VALUE))
        );
        StockDetailsPanelLayout.setVerticalGroup(
            StockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, StockDetailsPanelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(StockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RoomStockItemIDjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RoomStockjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(StockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RoomStockItemNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RoomStockItemNamejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(StockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RoomStockItemCategoryjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RoomStockItemCategoryjComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(StockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RoomStockDepartmentjTextField)
                    .addComponent(RoomStockDepartmentjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(StockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RoomStockOrderqtyjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RoomStockqtyjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(StockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(RoomStockUnitsjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RoomStockunitsjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(StockDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RoomstockClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RoomStockRequestOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(116, 116, 116))
        );

        RoomTogglePanel.add(StockDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        MaintainanceDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        MaintainanceDetailsPanel.setMinimumSize(new java.awt.Dimension(790, 580));
        MaintainanceDetailsPanel.setPreferredSize(new java.awt.Dimension(790, 565));

        MaintainanceScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        MaintainanceScrollPane.setBorder(null);

        RoomMaintainancejTable.setAutoCreateRowSorter(true);
        RoomMaintainancejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Item Name", "Available Quantity", "Quantity Used"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        RoomMaintainancejTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        RoomMaintainancejTable.setFocusable(false);
        RoomMaintainancejTable.setGridColor(new java.awt.Color(42, 135, 235));
        RoomMaintainancejTable.getTableHeader().setResizingAllowed(false);
        RoomMaintainancejTable.getTableHeader().setReorderingAllowed(false);
        RoomMaintainancejTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RoomMaintainancejTableMouseClicked(evt);
            }
        });
        MaintainanceScrollPane.setViewportView(RoomMaintainancejTable);

        AddRoomMainatainanceItemNameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddRoomMainatainanceItemNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRoomMainatainanceItemNameTextFieldActionPerformed(evt);
            }
        });
        AddRoomMainatainanceItemNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddRoomMainatainanceItemNameTextFieldKeyTyped(evt);
            }
        });

        AddRoomMainatinanceItemNameLabel.setBackground(new java.awt.Color(255, 255, 255));
        AddRoomMainatinanceItemNameLabel.setText("Item Name");

        AddRoomMainatinanceAvailabelQtyLabel.setBackground(new java.awt.Color(255, 255, 255));
        AddRoomMainatinanceAvailabelQtyLabel.setText("Available Quantity");

        AddRoomMainatianaceAvailableQtyTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddRoomMainatianaceAvailableQtyTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRoomMainatianaceAvailableQtyTextFieldActionPerformed(evt);
            }
        });
        AddRoomMainatianaceAvailableQtyTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddRoomMainatianaceAvailableQtyTextFieldKeyTyped(evt);
            }
        });

        AddRoomMainatinanceQtyUsedLabel.setBackground(new java.awt.Color(255, 255, 255));
        AddRoomMainatinanceQtyUsedLabel.setText("Quantity Used");

        AddRoomMainatianaceQtyUsedTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddRoomMainatianaceQtyUsedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRoomMainatianaceQtyUsedTextFieldActionPerformed(evt);
            }
        });
        AddRoomMainatianaceQtyUsedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddRoomMainatianaceQtyUsedTextFieldKeyTyped(evt);
            }
        });

        MaintainanceDetailsUpdateButton.setBackground(new java.awt.Color(255, 255, 255));
        MaintainanceDetailsUpdateButton.setForeground(new java.awt.Color(102, 153, 255));
        MaintainanceDetailsUpdateButton.setText("Update");
        MaintainanceDetailsUpdateButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        MaintainanceDetailsUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaintainanceDetailsUpdateButtonActionPerformed(evt);
            }
        });

        MaintainanceDetailsSubmitButton.setBackground(new java.awt.Color(255, 255, 255));
        MaintainanceDetailsSubmitButton.setForeground(new java.awt.Color(102, 153, 255));
        MaintainanceDetailsSubmitButton.setText("Submit");
        MaintainanceDetailsSubmitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        MaintainanceDetailsSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaintainanceDetailsSubmitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MaintainanceDetailsPanelLayout = new javax.swing.GroupLayout(MaintainanceDetailsPanel);
        MaintainanceDetailsPanel.setLayout(MaintainanceDetailsPanelLayout);
        MaintainanceDetailsPanelLayout.setHorizontalGroup(
            MaintainanceDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MaintainanceDetailsPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(MaintainanceDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(MaintainanceScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(MaintainanceDetailsPanelLayout.createSequentialGroup()
                        .addGroup(MaintainanceDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(MaintainanceDetailsPanelLayout.createSequentialGroup()
                                .addComponent(AddRoomMainatinanceQtyUsedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddRoomMainatianaceQtyUsedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(MaintainanceDetailsPanelLayout.createSequentialGroup()
                                .addComponent(AddRoomMainatinanceItemNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddRoomMainatainanceItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(66, 66, 66)
                        .addComponent(AddRoomMainatinanceAvailabelQtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddRoomMainatianaceAvailableQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MaintainanceDetailsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MaintainanceDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189)
                .addComponent(MaintainanceDetailsUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
        );
        MaintainanceDetailsPanelLayout.setVerticalGroup(
            MaintainanceDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MaintainanceDetailsPanelLayout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(MaintainanceDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddRoomMainatinanceItemNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddRoomMainatainanceItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddRoomMainatinanceAvailabelQtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddRoomMainatianaceAvailableQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(MaintainanceDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddRoomMainatinanceQtyUsedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddRoomMainatianaceQtyUsedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(MaintainanceScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(MaintainanceDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaintainanceDetailsUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaintainanceDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107))
        );

        RoomTogglePanel.add(MaintainanceDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, 566));

        RoomLaundaryPanel.setBackground(new java.awt.Color(255, 255, 255));
        RoomLaundaryPanel.setMinimumSize(new java.awt.Dimension(790, 580));
        RoomLaundaryPanel.setPreferredSize(new java.awt.Dimension(790, 565));

        AddLaundaryDetailsNICjLabel.setText("Customer NIC");

        AddLaundaryDetailsLaundaryCustomerNamejLabel.setText("CustomerName");

        AddLaundaryDetailsLaundaryDescjLabel.setText("Description");

        AddLaundaryDetailsLaundaryamountjLabel.setText("Amount");

        AddLaundaryDetailsLaundarycusnameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddLaundaryDetailsLaundarycusnameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddLaundaryDetailsLaundarycusnameTextFieldActionPerformed(evt);
            }
        });
        AddLaundaryDetailsLaundarycusnameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddLaundaryDetailsLaundarycusnameTextFieldKeyTyped(evt);
            }
        });

        AddLaundaryDetailsLaundaryDescTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddLaundaryDetailsLaundaryDescTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddLaundaryDetailsLaundaryDescTextFieldActionPerformed(evt);
            }
        });
        AddLaundaryDetailsLaundaryDescTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddLaundaryDetailsLaundaryDescTextFieldKeyTyped(evt);
            }
        });

        AddLaundaryDetailsLaundaryAmountTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddLaundaryDetailsLaundaryAmountTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddLaundaryDetailsLaundaryAmountTextFieldActionPerformed(evt);
            }
        });
        AddLaundaryDetailsLaundaryAmountTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddLaundaryDetailsLaundaryAmountTextFieldKeyTyped(evt);
            }
        });

        AddLaundaryDetailsNICValuejLabel.setText("NIC");

        EditCustomerDetailsScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        EditCustomerDetailsScrollPane1.setMinimumSize(new java.awt.Dimension(780, 500));
        EditCustomerDetailsScrollPane1.setPreferredSize(new java.awt.Dimension(780, 500));

        AddLaundaryDetailsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddLaundaryDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Laundary ID", "Customer Name", "Customer NIC", "Telephone No", "Date", "Description", "Amount", "Weight"
            }
        ));
        AddLaundaryDetailsTable.setGridColor(new java.awt.Color(102, 153, 255));
        AddLaundaryDetailsTable.setMinimumSize(new java.awt.Dimension(375, 480));
        AddLaundaryDetailsTable.getTableHeader().setResizingAllowed(false);
        AddLaundaryDetailsTable.getTableHeader().setReorderingAllowed(false);
        AddLaundaryDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddLaundaryDetailsTableMouseClicked(evt);
            }
        });
        EditCustomerDetailsScrollPane1.setViewportView(AddLaundaryDetailsTable);

        AddLaundaryDetailsSubmitButton.setBackground(new java.awt.Color(255, 255, 255));
        AddLaundaryDetailsSubmitButton.setForeground(new java.awt.Color(102, 153, 255));
        AddLaundaryDetailsSubmitButton.setText("Submit");
        AddLaundaryDetailsSubmitButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddLaundaryDetailsSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddLaundaryDetailsSubmitButtonActionPerformed(evt);
            }
        });

        AddLaundaryDatejLabel.setText("Date");

        AddLaundaryjDateChooser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        AddLaundaryDetailstelnojLabel.setText("Telephone Number");

        AddLaundaryDetailsLaundarytelnoTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddLaundaryDetailsLaundarytelnoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddLaundaryDetailsLaundarytelnoTextFieldActionPerformed(evt);
            }
        });
        AddLaundaryDetailsLaundarytelnoTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                AddLaundaryDetailsLaundarytelnoTextFieldKeyTyped(evt);
            }
        });

        AddLaundaryDetailsBackutton.setBackground(new java.awt.Color(255, 255, 255));
        AddLaundaryDetailsBackutton.setForeground(new java.awt.Color(102, 153, 255));
        AddLaundaryDetailsBackutton.setText("Back");
        AddLaundaryDetailsBackutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddLaundaryDetailsBackutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddLaundaryDetailsBackuttonActionPerformed(evt);
            }
        });

        AddLaundaryDetailsSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        AddLaundaryDetailsSearchButton.setForeground(new java.awt.Color(102, 153, 255));
        AddLaundaryDetailsSearchButton.setText("Search");
        AddLaundaryDetailsSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddLaundaryDetailsSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddLaundaryDetailsSearchButtonActionPerformed(evt);
            }
        });

        AddLaundaryDetailsBackToPAymentsButton.setBackground(new java.awt.Color(255, 255, 255));
        AddLaundaryDetailsBackToPAymentsButton.setForeground(new java.awt.Color(102, 153, 255));
        AddLaundaryDetailsBackToPAymentsButton.setText("Payments");
        AddLaundaryDetailsBackToPAymentsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        AddLaundaryDetailsBackToPAymentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddLaundaryDetailsBackToPAymentsButtonActionPerformed(evt);
            }
        });

        LaundryOrdersjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cus NIC", "Amount"
            }
        ));
        jScrollPane5.setViewportView(LaundryOrdersjTable);

        LaundryDeletejButton.setBackground(new java.awt.Color(255, 255, 255));
        LaundryDeletejButton.setForeground(new java.awt.Color(102, 153, 255));
        LaundryDeletejButton.setText("REMOVE");
        LaundryDeletejButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        LaundryDeletejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaundryDeletejButtonActionPerformed(evt);
            }
        });

        LaundryAddjButton.setBackground(new java.awt.Color(255, 255, 255));
        LaundryAddjButton.setForeground(new java.awt.Color(102, 153, 255));
        LaundryAddjButton.setText("ADD");
        LaundryAddjButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        LaundryAddjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaundryAddjButtonActionPerformed(evt);
            }
        });

        LaundryCalculateTotjButton.setBackground(new java.awt.Color(255, 255, 255));
        LaundryCalculateTotjButton.setForeground(new java.awt.Color(102, 153, 255));
        LaundryCalculateTotjButton.setText("Total");
        LaundryCalculateTotjButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        LaundryCalculateTotjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaundryCalculateTotjButtonActionPerformed(evt);
            }
        });

        AddLaundaryDetailsLaundaryWeightjLabel.setText("Weight");

        RoomLaundryjComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Weight", "0.5 Kg", "1 Kg", "1.5 kg", "2 kg", "2.5 kg", "3 kg", "3.5 kg", "4 kg" }));
        RoomLaundryjComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        RoomLaundryjComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RoomLaundryjComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RoomLaundaryPanelLayout = new javax.swing.GroupLayout(RoomLaundaryPanel);
        RoomLaundaryPanel.setLayout(RoomLaundaryPanelLayout);
        RoomLaundaryPanelLayout.setHorizontalGroup(
            RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoomLaundaryPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RoomLaundaryPanelLayout.createSequentialGroup()
                        .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EditCustomerDetailsScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoomLaundaryPanelLayout.createSequentialGroup()
                                .addComponent(AddLaundaryDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(AddLaundaryDetailsBackutton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(AddLaundaryDetailsBackToPAymentsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)))
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addGroup(RoomLaundaryPanelLayout.createSequentialGroup()
                        .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RoomLaundaryPanelLayout.createSequentialGroup()
                                .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(AddLaundaryDatejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddLaundaryDetailstelnojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddLaundaryDetailsLaundaryamountjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddLaundaryDetailsLaundaryDescjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddLaundaryDetailsLaundaryWeightjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AddLaundaryDetailsLaundaryDescTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(AddLaundaryDetailsLaundaryAmountTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(AddLaundaryjDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(AddLaundaryDetailsLaundarytelnoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(RoomLaundryjComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(RoomLaundaryPanelLayout.createSequentialGroup()
                                        .addComponent(LaundryAddjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TotalLAundryjTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(LaundryCalculateTotjButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(LaundryDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(RoomLaundaryPanelLayout.createSequentialGroup()
                                .addComponent(AddLaundaryDetailsLaundaryCustomerNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddLaundaryDetailsLaundarycusnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RoomLaundaryPanelLayout.createSequentialGroup()
                                .addComponent(AddLaundaryDetailsNICjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddLaundaryDetailsNICValuejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(AddLaundaryDetailsSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47))))
        );
        RoomLaundaryPanelLayout.setVerticalGroup(
            RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RoomLaundaryPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddLaundaryDetailsNICjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddLaundaryDetailsNICValuejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddLaundaryDetailsSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddLaundaryDetailsLaundaryCustomerNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddLaundaryDetailsLaundarycusnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(RoomLaundaryPanelLayout.createSequentialGroup()
                        .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddLaundaryDetailsLaundaryDescjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddLaundaryDetailsLaundaryDescTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddLaundaryDetailsLaundaryamountjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AddLaundaryDetailsLaundaryAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(RoomLaundaryPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(TotalLAundryjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LaundryCalculateTotjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoomLaundaryPanelLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoomLaundaryPanelLayout.createSequentialGroup()
                                .addComponent(LaundryAddjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(LaundryDeletejButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RoomLaundaryPanelLayout.createSequentialGroup()
                                .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(AddLaundaryDetailsLaundaryWeightjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                    .addComponent(RoomLaundryjComboBox))
                                .addGap(20, 20, 20)
                                .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddLaundaryDatejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddLaundaryjDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(AddLaundaryDetailsLaundarytelnoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(AddLaundaryDetailstelnojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20)))
                .addComponent(EditCustomerDetailsScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(RoomLaundaryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddLaundaryDetailsBackutton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddLaundaryDetailsSubmitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddLaundaryDetailsBackToPAymentsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41))
        );

        RoomTogglePanel.add(RoomLaundaryPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        RoomManagerPanel.add(RoomTogglePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 120, 796, 570));

        jDesktopPaneRoom.add(RoomManagerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1024, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jDesktopPaneRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jDesktopPaneRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CustomerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerButtonMouseEntered
        CustomerButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_CustomerButtonMouseEntered

    private void CustomerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerButtonMouseExited
        CustomerButton.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_CustomerButtonMouseExited

    private void CustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerButtonActionPerformed
        Roompanelshift(CustomerButton.getText());
        CustomerDetailsTabbedPane.setEnabledAt(1,true);
        CustomerDetailsTabbedPane.setEnabledAt(0,false);
        CustomerDetailsTabbedPane.setSelectedIndex(1);
        EditcustomerTableLoad();
        ViewcustomerTableLoad();
    }//GEN-LAST:event_CustomerButtonActionPerformed

    private void RoomButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomButtonMouseClicked

    private void RoomButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomButtonMouseEntered
        RoomButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_RoomButtonMouseEntered

    private void RoomButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomButtonMouseExited
        RoomButton.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_RoomButtonMouseExited

    private void RoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomButtonActionPerformed
        Roompanelshift(RoomButton.getText());
        RoomDetailsTabbedPane.setEnabledAt(0,false);
        RoomDetailsTabbedPane.setEnabledAt(1,false);
        RoomDetailsTabbedPane.setEnabledAt(2,true);
        RoomDetailsTabbedPane.setEnabledAt(3,true);
        RoomDetailsTabbedPane.setSelectedIndex(1);
        EditRoomTableLoad();
        ViewRoomTableLoad();

    }//GEN-LAST:event_RoomButtonActionPerformed

    private void PackagesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PackagesButtonMouseEntered
        PackagesButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_PackagesButtonMouseEntered

    private void PackagesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PackagesButtonMouseExited
        PackagesButton.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_PackagesButtonMouseExited

    private void PackagesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PackagesButtonActionPerformed
        Roompanelshift(PackagesButton.getText());
        PackageRoomTabbedPane.setEnabledAt(0,false);
        PackageRoomTabbedPane.setEnabledAt(1,true);
        PackageRoomTabbedPane.setSelectedIndex(1);
        ViewPackageTableLoad();
    }//GEN-LAST:event_PackagesButtonActionPerformed

    private void ReservationButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReservationButtonMouseEntered
        ReservationButton.setBackground(new Color(35,171,69));
        EditReservationTableLoad();
    }//GEN-LAST:event_ReservationButtonMouseEntered

    private void ReservationButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReservationButtonMouseExited
        ReservationButton.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_ReservationButtonMouseExited

    private void ReservationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReservationButtonActionPerformed
        Roompanelshift(ReservationButton.getText());
        ReservationDetailsTabbedPane.setSelectedIndex(2);
        ReservationDetailsTabbedPane.setEnabledAt(0,false);
        ReservationDetailsTabbedPane.setEnabledAt(2,true);
        ReservationDetailsTabbedPane.setEnabledAt(1,false);
    }//GEN-LAST:event_ReservationButtonActionPerformed

    private void RoomServiceButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomServiceButtonMouseEntered
        RoomServiceButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_RoomServiceButtonMouseEntered

    private void RoomServiceButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomServiceButtonMouseExited
        RoomServiceButton.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_RoomServiceButtonMouseExited

    private void RoomServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomServiceButtonActionPerformed
        Roompanelshift(RoomServiceButton.getText());
        RoomServiceTabbedPane.setEnabledAt(1,true);
       RoomServiceTabbedPane.setEnabledAt(0,false);
       RoomServiceTabbedPane.setSelectedIndex(1);
    }//GEN-LAST:event_RoomServiceButtonActionPerformed

    private void RoomLaundaryButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomLaundaryButtonMouseEntered
        RoomLaundaryButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_RoomLaundaryButtonMouseEntered

    private void RoomLaundaryButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomLaundaryButtonMouseExited
        RoomLaundaryButton.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_RoomLaundaryButtonMouseExited

    private void RoomLaundaryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomLaundaryButtonActionPerformed
        Roompanelshift(RoomLaundaryButton.getText());
    }//GEN-LAST:event_RoomLaundaryButtonActionPerformed

    private void MaintainanceButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaintainanceButtonMouseEntered
        MaintainanceButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_MaintainanceButtonMouseEntered

    private void MaintainanceButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MaintainanceButtonMouseExited
        MaintainanceButton.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_MaintainanceButtonMouseExited

    private void MaintainanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaintainanceButtonActionPerformed
        Roompanelshift(MaintainanceButton.getText());
    }//GEN-LAST:event_MaintainanceButtonActionPerformed

    private void RoomStockButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomStockButtonMouseEntered
        RoomStockButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_RoomStockButtonMouseEntered

    private void RoomStockButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomStockButtonMouseExited
        RoomStockButton.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_RoomStockButtonMouseExited

    private void RoomStockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomStockButtonActionPerformed
        Roompanelshift(RoomStockButton.getText());
    }//GEN-LAST:event_RoomStockButtonActionPerformed

    private void RoomPaymentsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomPaymentsButtonMouseEntered
        RoomPaymentsButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_RoomPaymentsButtonMouseEntered

    private void RoomPaymentsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomPaymentsButtonMouseExited
        RoomPaymentsButton.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_RoomPaymentsButtonMouseExited

    private void RoomPaymentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomPaymentsButtonActionPerformed
        Roompanelshift(RoomPaymentsButton.getText());
        PaymentDetailsjTabbedPane.setSelectedIndex(1);
        PaymentDetailsjTabbedPane.setEnabledAt(0,false);
        PaymentDetailsjTabbedPane.setEnabledAt(1,true);
    }//GEN-LAST:event_RoomPaymentsButtonActionPerformed

    private void RoomReportsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomReportsButtonMouseEntered
        RoomReportsButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_RoomReportsButtonMouseEntered

    private void RoomReportsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomReportsButtonMouseExited
        RoomReportsButton.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_RoomReportsButtonMouseExited

    private void RoomReportsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomReportsButtonActionPerformed
        Roompanelshift(RoomReportsButton.getText());
    }//GEN-LAST:event_RoomReportsButtonActionPerformed

    private void ViewCustomerDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewCustomerDetailsTableMouseClicked
         ViewRoomCustomerNICTextField.setBackground(new Color(255,255,255));
        int r = ViewCustomerDetailsTable.getSelectedRow();
        String Cusid=ViewCustomerDetailsTable.getValueAt(r, 0).toString();
        String CusName=ViewCustomerDetailsTable.getValueAt(r, 1).toString();
        String CusNIC=ViewCustomerDetailsTable.getValueAt(r, 2).toString();
        String CusAdd=ViewCustomerDetailsTable.getValueAt(r, 3).toString();
        String CusTel=ViewCustomerDetailsTable.getValueAt(r, 4).toString();

        ViewRoomCustomerNameTextField.setText(CusName);
        ViewRoomCustomerNICTextField.setText(CusNIC);
        ViewRoomCustomerAddressTextField.setText(CusAdd);
        ViewRoomCustomerTelNoTextField.setText(CusTel);
    }//GEN-LAST:event_ViewCustomerDetailsTableMouseClicked

    private void ViewCustomerDetailsSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewCustomerDetailsSearchButtonActionPerformed
        ViewRoomCustomerNICTextField.setText(viewcustomerDetailssearchTextField.getText());
        String searchVal=viewcustomerDetailssearchTextField.getText();
        String q="Select room_customer_id as 'Customer ID',room_customer_name as 'Customer Name',room_customer_nic as 'Customer NIC',room_customer_address as 'Address',room_customer_telno as 'Telephone' from room_customer where room_customer_nic LIKE '%"+searchVal+"%'";
        try {
            pstatement=con.prepareStatement(q);
            Results=pstatement.executeQuery();
            ViewCustomerDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_ViewCustomerDetailsSearchButtonActionPerformed

    private void viewcustomerDetailssearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewcustomerDetailssearchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewcustomerDetailssearchTextFieldActionPerformed

    private void ViewRoomCustomerNICTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewRoomCustomerNICTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewRoomCustomerNICTextFieldActionPerformed

    private void ViewRoomCustomerNICTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ViewRoomCustomerNICTextFieldKeyReleased
         String x=ViewRoomCustomerNICTextField.getText();
         int num=x.length();
         if(num>10){
        
            JOptionPane.showMessageDialog(null,"Phone Number has only 10 digits!!","Invalid Data", JOptionPane.ERROR_MESSAGE);
            ViewRoomCustomerNICTextField.setText("");
         }
    }//GEN-LAST:event_ViewRoomCustomerNICTextFieldKeyReleased

    private void ViewRoomCustomerNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ViewRoomCustomerNameTextFieldKeyTyped
         char c= evt.getKeyChar();
        if((Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_ViewRoomCustomerNameTextFieldKeyTyped

    private void ViewRoomCustomerTelNoTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ViewRoomCustomerTelNoTextFieldKeyReleased
          String x=ViewRoomCustomerTelNoTextField.getText();
         int num=x.length();
         if(num>10){
        
            JOptionPane.showMessageDialog(null,"Phone Number has only 10 digits!!","Invalid Data", JOptionPane.ERROR_MESSAGE);
            ViewRoomCustomerTelNoTextField.setText("");
         }
    }//GEN-LAST:event_ViewRoomCustomerTelNoTextFieldKeyReleased

    private void ViewRoomCustomerTelNoTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ViewRoomCustomerTelNoTextFieldKeyTyped
        char c= evt.getKeyChar();
        if((Character.isLetter(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }// TODO add your handling code here:
    }//GEN-LAST:event_ViewRoomCustomerTelNoTextFieldKeyTyped

    private void ViewCustomerDetailsOrderRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewCustomerDetailsOrderRoomButtonActionPerformed
        RoomDetailsTabbedPane.setEnabledAt(0,true);
        RoomDetailsTabbedPane.setEnabledAt(1,false);
        RoomDetailsTabbedPane.setEnabledAt(2,true);
        String cusNIC=ViewRoomCustomerNICTextField.getText();
        
        if(cusNIC.isEmpty()){
            ViewRoomCustomerNICTextField.setBackground(new Color(255,150,150));
            JOptionPane.showMessageDialog(null,"Please fill Customer NIC!","Form Incomplete!",0);      
        }
        else
        {
        Roompanelshift(RoomButton.getText());
        RoomDetailsTabbedPane.setSelectedIndex(0);
        ViewRoomDetailsCusnicValuejLabel.setText(cusNIC);
        }
        //disable edit room panel
    }//GEN-LAST:event_ViewCustomerDetailsOrderRoomButtonActionPerformed

    private void ViewCustomerDetailsLaundaryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewCustomerDetailsLaundaryButtonActionPerformed
        Roompanelshift(RoomLaundaryButton.getText());
        AddLaundaryDetailsNICValuejLabel.setText(ViewRoomCustomerNICTextField.getText());
        AddLaundaryDetailsLaundarycusnameTextField.setText(ViewRoomCustomerNameTextField.getText());
        AddLaundaryDetailsLaundarytelnoTextField.setText(ViewRoomCustomerTelNoTextField.getText());
    }//GEN-LAST:event_ViewCustomerDetailsLaundaryButtonActionPerformed

    private void ViewCustomerDetailsOrderRoomServiceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewCustomerDetailsOrderRoomServiceButtonActionPerformed
        Roompanelshift(RoomServiceButton.getText());
       CustomizedroomServicevalueNiC.setText(ViewRoomCustomerNICTextField.getText());
    }//GEN-LAST:event_ViewCustomerDetailsOrderRoomServiceButtonActionPerformed

    private void ViewCustomerDetailsOrderPackageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewCustomerDetailsOrderPackageButtonActionPerformed
        Roompanelshift(PackagesButton.getText());
        PackageRoomTabbedPane.setSelectedIndex(0);
        PackageRoomTabbedPane.setEnabledAt(1,false);
        ViewPackageCustomerNICTextField.setText(ViewRoomCustomerNICTextField.getText());
    }//GEN-LAST:event_ViewCustomerDetailsOrderPackageButtonActionPerformed

    private void EditRoomCustomerNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EditRoomCustomerNameTextFieldKeyTyped
        char c= evt.getKeyChar();
        if((Character.isDigit(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_EditRoomCustomerNameTextFieldKeyTyped

    private void EditRoomCustomerNICTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditRoomCustomerNICTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditRoomCustomerNICTextFieldActionPerformed

    private void EditRoomCustomerNICTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EditRoomCustomerNICTextFieldKeyReleased
        String x=EditRoomCustomerNICTextField.getText();
        int num=x.length();
        if(num>10){

            JOptionPane.showMessageDialog(null,"NIC has only 10 digits!!","Invalid NIC", JOptionPane.ERROR_MESSAGE);
            EditRoomCustomerNICTextField.setText("");
        } // TODO add your handling code here:
    }//GEN-LAST:event_EditRoomCustomerNICTextFieldKeyReleased

    private void EditRoomCustomerTElNoTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EditRoomCustomerTElNoTextFieldKeyReleased
        String x=EditRoomCustomerTElNoTextField.getText();
        int num=x.length();
        if(num>10){

            JOptionPane.showMessageDialog(null,"Phone Number has only 10 digits!!","Invalid Telephone number", JOptionPane.ERROR_MESSAGE);
            EditRoomCustomerTElNoTextField.setText("");
        }       // TODO add your handling code here:
    }//GEN-LAST:event_EditRoomCustomerTElNoTextFieldKeyReleased

    private void EditRoomCustomerTElNoTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EditRoomCustomerTElNoTextFieldKeyTyped
        char c =evt.getKeyChar();
        if((Character.isLetter(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_EditRoomCustomerTElNoTextFieldKeyTyped

    private void EditCustomerDetailsUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditCustomerDetailsUpdateButtonActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Are You sure you want to update?");
        if(x==0){
            try{
                String Cusid=EditCustomerDetailsIDvalueLabel.getText();
                String CusName=EditRoomCustomerNameTextField.getText();
                String CusNIC=EditRoomCustomerNICTextField.getText();
                String CusAdd=EditRoomCustomerAddressTextField.getText();
                String CusTel=EditRoomCustomerTElNoTextField.getText();

                String q ="update room_customer set room_customer_name='"+CusName+"',room_customer_nic='"+CusNIC+"',room_customer_address='"+CusAdd+"',room_customer_telno='"+CusTel+"' where room_customer_id='"+Cusid+"'";
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                EditcustomerTableLoad();
                ViewcustomerTableLoad();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x==1){}
        else if(x==2){}
    }//GEN-LAST:event_EditCustomerDetailsUpdateButtonActionPerformed

    private void EditCustomerDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditCustomerDetailsTableMouseClicked
        int r = EditCustomerDetailsTable.getSelectedRow();
        String Cusid=EditCustomerDetailsTable.getValueAt(r, 0).toString();
        String CusName=EditCustomerDetailsTable.getValueAt(r, 1).toString();
        String CusNIC=EditCustomerDetailsTable.getValueAt(r, 2).toString();
        String CusAdd=EditCustomerDetailsTable.getValueAt(r, 3).toString();
        String CusTel=EditCustomerDetailsTable.getValueAt(r, 4).toString();

        EditRoomCustomerNameTextField.setText(CusName);
        EditRoomCustomerNICTextField.setText(CusNIC);
        EditRoomCustomerAddressTextField.setText(CusAdd);
        EditRoomCustomerTElNoTextField.setText(CusTel);
        EditCustomerDetailsIDvalueLabel.setText(Cusid);
    }//GEN-LAST:event_EditCustomerDetailsTableMouseClicked

    private void EditCustomerDetailsDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditCustomerDetailsDeleteButtonActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Are You sure you want to Delete this?");
        if(x==0){
            try{
                String Cusid=EditCustomerDetailsIDvalueLabel.getText();

                String q ="Delete from room_customer where room_customer_id='"+Cusid+"'";
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                EditcustomerTableLoad();
                ViewcustomerTableLoad();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }

        }
        else if(x==1){}
        else if(x==2){}
    }//GEN-LAST:event_EditCustomerDetailsDeleteButtonActionPerformed

    private void EditCustomerDetailsSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditCustomerDetailsSearchButtonActionPerformed
        String searchVal=EditcustomerDetailssearchTextField.getText();
        String q="Select room_customer_id as 'Customer Id',room_customer_name as 'Customer Name',room_customer_nic as 'Customer NIC',room_customer_address as 'Customer Address',room_customer_telno as 'Telephone Number' from room_customer where room_customer_nic LIKE '%"+searchVal+"%'";
        try {
            pstatement=con.prepareStatement(q);
            Results=pstatement.executeQuery();
            EditCustomerDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_EditCustomerDetailsSearchButtonActionPerformed

    private void EditcustomerDetailssearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditcustomerDetailssearchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditcustomerDetailssearchTextFieldActionPerformed

    private void ViewRoomDetailsjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewRoomDetailsjTableMouseClicked
        int r = ViewRoomDetailsjTable.getSelectedRow();
        RoomDetails[0]=ViewRoomDetailsjTable.getValueAt(r, 0).toString();
        RoomDetails[1]=ViewRoomDetailsjTable.getValueAt(r, 4).toString();
      
    }//GEN-LAST:event_ViewRoomDetailsjTableMouseClicked

    private void ViewRoomDetailsCheckAvailabiltyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewRoomDetailsCheckAvailabiltyButtonActionPerformed
        try{
            SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd");
            String NoAdults=ViewRoomDetailsNoofAdultsTextField.getText();
            Integer NoChild=Integer.parseInt(ViewRoomDetailsNoChildrenTextField.getText());
            String Type=ViewRoomDetailsjComboBox.getSelectedItem().toString();
           // String DateCheckin=formatDate.format(RoomCheckInjDateChooser.getDate());
            //String DateCheckOut=formatDate.format(RoomCheckoutjDateChooser.getDate());
            String q ="Select room_roomdetails_roomno as 'Room No',room_roomdetails_NoofAdults as 'No of Adults',room_roomdetails_NoofChildren as 'No of Children',room_roomdetails_roomType as Type,room_roomdetails_amount as Amount from room_roomdetails where room_roomdetails_NoofAdults='"+NoAdults+"' AND room_roomdetails_NoofChildren ='"+NoChild+"' AND room_roomdetails_roomType ='"+Type+"' AND room_roomdetails_status ='Available'"+"";
                    //"Select room_roomdetails_roomno as 'Room No',room_roomdetails_NoofAdults as 'No of Adults',room_roomdetails_NoofChildren as 'No of Children',room_roomdetails_roomType as Type,room_roomdetails_amount as Amount from room_roomdetails where  room_roomdetails_status='Available'"+" AND room_roomdetails_NoofAdults='"+NoAdults+"' AND  ='"+NoChild+"'AND ro AND r.room_roomdetails_roomno=rs.room_reservedRoom_RoomNors.room_reservedroom_checkIn='"+DateCheckin+"' rs.room_reservedroom_checkOut='"+DateCheckOut+"'
            pstatement=con.prepareStatement(q);
            Results=pstatement.executeQuery();
            ViewRoomDetailsjTable.setModel(DbUtils.resultSetToTableModel(Results));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
    }//GEN-LAST:event_ViewRoomDetailsCheckAvailabiltyButtonActionPerformed

    private void ViewRoomDetailsNoofAdultsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewRoomDetailsNoofAdultsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewRoomDetailsNoofAdultsTextFieldActionPerformed

    private void ViewRoomDetailsNoChildrenTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewRoomDetailsNoChildrenTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewRoomDetailsNoChildrenTextFieldActionPerformed

    private void ViewRoomDetailsReserveRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewRoomDetailsReserveRoomButtonActionPerformed
        Roompanelshift(ReservationButton.getText());
        ReservationDetailsTabbedPane.setSelectedIndex(0);
        ReservationDetailsTabbedPane.setEnabledAt(1, false);
        ReservationDetailsTabbedPane.setEnabledAt(2, false);
        AddReservationCusNICTextField.setText(ViewRoomDetailsCusnicValuejLabel.getText());
        AddReservationCusNameTextField.setText(ViewRoomCustomerNameTextField.getText());
        //jTableToReserveRoom
        int rowcount=jTableToReserveRoom.getRowCount();
        RoomCountjTextField.setText(String.valueOf(rowcount));
        String values[]=new String[rowcount];
        for(int i=0;i<rowcount;i++)
        {
           values[i]=jTableToReserveRoom.getValueAt(i, 0).toString();
        }
        try{
        SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd");
        String cusNIC=ViewRoomCustomerNICTextField.getText();
        String checkin=formatDate.format(RoomCheckInjDateChooser.getDate());
        String checkout=formatDate.format(RoomCheckoutjDateChooser.getDate());
        MakeReservationFinalAmountjlabel.setText(jTextFieldTotalAmountToPAy.getText());
        AddReservationCusNICTextField.setText(cusNIC);
        AddReservationCheckInTextField.setText(checkin);
        AddReservationCheckOutTextField.setText(checkout);
        }
        catch(Exception e)
         {JOptionPane.showMessageDialog(null, "Date should be filled");
         Roompanelshift(RoomButton.getText());
       RoomDetailsTabbedPane.setSelectedIndex(0);}
       
    }//GEN-LAST:event_ViewRoomDetailsReserveRoomButtonActionPerformed

    private void ViewRoomDetailsNextButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewRoomDetailsNextButton1ActionPerformed
        Roompanelshift(CustomerButton.getText());
    }//GEN-LAST:event_ViewRoomDetailsNextButton1ActionPerformed

    private void EditRoomDetailsAmountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditRoomDetailsAmountTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditRoomDetailsAmountTextFieldActionPerformed

    private void EditRoomDetailsUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditRoomDetailsUpdateButtonActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Are You sure you want to update?");
        if(x==0){
            try{
                String Rid=EditRoomDetailsRoomNovalueLabel.getText();
                String NoAdults=EditRoomDetailsNoofAdultsTextField.getText();
                String NoChild=EditRoomDetailsNoofChildrenTextField.getText();
                String Amount=EditRoomDetailsAmountTextField.getText();
                String Type=EditRoomDetailsjComboBox.getSelectedItem().toString();
                String q ="update room_roomdetails  set room_roomdetails_amount='"+Amount+"',room_roomdetails_NoofAdults='"+NoAdults+"',room_roomdetails_NoofChildren='"+NoChild+"' ,room_roomdetails_roomType='"+Type+"' where room_roomdetails_roomno='"+Rid+"'";
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                EditRoomTableLoad();
                ViewRoomTableLoad();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x==1){}
        else if(x==2){}

    }//GEN-LAST:event_EditRoomDetailsUpdateButtonActionPerformed

    private void EditRoomDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditRoomDetailsTableMouseClicked

        int r = EditRoomDetailsTable.getSelectedRow();

        String Rid=EditRoomDetailsTable.getValueAt(r, 0).toString();
        String Amount=EditRoomDetailsTable.getValueAt(r, 2).toString();
        String adults=EditRoomDetailsTable.getValueAt(r, 3).toString();
        String Kids=EditRoomDetailsTable.getValueAt(r, 4).toString();
        String Type=EditRoomDetailsTable.getValueAt(r, 5).toString();

        EditRoomDetailsRoomNovalueLabel.setText(Rid);
        EditRoomDetailsNoofAdultsTextField.setText(adults);
        EditRoomDetailsNoofChildrenTextField.setText(Kids);
        EditRoomDetailsAmountTextField.setText(Amount);
        EditRoomDetailsjComboBox.setSelectedItem(Type);
    }//GEN-LAST:event_EditRoomDetailsTableMouseClicked

    private void EditRoomDetailsNoofAdultsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditRoomDetailsNoofAdultsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditRoomDetailsNoofAdultsTextFieldActionPerformed

    private void EditRoomDetailsNoofChildrenTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditRoomDetailsNoofChildrenTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditRoomDetailsNoofChildrenTextFieldActionPerformed

    private void EditRoomDetailsSearchValueTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditRoomDetailsSearchValueTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditRoomDetailsSearchValueTextFieldActionPerformed

    private void EditRoomDetailsSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditRoomDetailsSearchButtonActionPerformed
        String searchVal=EditRoomDetailsSearchValueTextField.getText();
        String q="Select room_roomdetails_roomno,room_roomdetails_status,room_roomdetails_amount,room_roomdetails_NoofAdults,room_roomdetails_NoofChildren,room_roomdetails_roomType from room_roomdetails where room_roomdetails_roomno='"+searchVal+"'";
        try {
            pstatement=con.prepareStatement(q);
            Results=pstatement.executeQuery();
            EditRoomDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_EditRoomDetailsSearchButtonActionPerformed

    private void EditRoomDetailsSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditRoomDetailsSubmitButtonActionPerformed
        String NoAdults=EditRoomDetailsNoofAdultsTextField.getText();
        String NoChild=EditRoomDetailsNoofChildrenTextField.getText();
        String Amount=EditRoomDetailsAmountTextField.getText();
        String Type=EditRoomDetailsjComboBox.getSelectedItem().toString();

        if(NoAdults.isEmpty())
        EditRoomDetailsNoofAdultsTextField.setBackground(new Color(255,150,150));
        else
        EditRoomDetailsNoofAdultsTextField.setBackground(new Color(255,255,255));
        if(NoChild.isEmpty())
        EditRoomDetailsNoofChildrenTextField.setBackground(new Color(255,150,150));
        else
        EditRoomDetailsNoofChildrenTextField.setBackground(new Color(255,255,255));
        if(Amount.isEmpty())
        EditRoomDetailsAmountTextField.setBackground(new Color(255,150,150));
        else
        EditRoomDetailsAmountTextField.setBackground(new Color(255,255,255));
        if(Type.isEmpty())
        EditRoomDetailsjComboBox.setBackground(new Color(255,150,150));
        else
        EditRoomDetailsjComboBox.setBackground(new Color(255,255,255));

        if(NoAdults.isEmpty()||NoChild.isEmpty()||Amount.isEmpty()||Type.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill all the fields!","Form Incomplete!",0);
        }
        if(!NoAdults.isEmpty()&&!NoChild.isEmpty()&&!Amount.isEmpty()&&!Type.isEmpty()){
            try{

                String q = "Insert into room_roomdetails(room_roomdetails_amount,room_roomdetails_NoofAdults,room_roomdetails_NoofChildren,room_roomdetails_roomType) values ('"+Amount+"','"+NoAdults+"','"+NoChild+"','"+Type+"')";
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                JOptionPane.showMessageDialog(null,"Successfully Added To The Database!","Success Message!",1);
                EditRoomTableLoad();
                ViewRoomTableLoad();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }//GEN-LAST:event_EditRoomDetailsSubmitButtonActionPerformed

    private void ViewPackageDetailsSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPackageDetailsSubmitButtonActionPerformed
        String adults=ViewPackageDetailsNoofAdultsTextField.getText();
        String Children=ViewPackageDetailsNoofChildrenTextField.getText();
        String q="Select room_package_packageid as 'Package Id',room_package_description as 'Description',room_package_amount as 'Amount',room_package_roomno as 'Room No'  from room_package where room_package_NoofAdults='"+adults+"' AND room_package_NoofChildren='"+Children+"' AND room_package_status= 'Available'"+"";
        try {
            pstatement=con.prepareStatement(q);
            Results=pstatement.executeQuery();
            ViewPackageDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }//GEN-LAST:event_ViewPackageDetailsSubmitButtonActionPerformed

    private void ViewPackagesDetailsNextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPackagesDetailsNextButtonActionPerformed
        Roompanelshift(ReservationButton.getText());
        int r = ViewPackageDetailsTable.getSelectedRow();
        String packID=ViewPackageDetailsTable.getValueAt(r, 0).toString();
        String Des=ViewPackageDetailsTable.getValueAt(r, 1).toString();
        String Amount=ViewPackageDetailsTable.getValueAt(r, 2).toString();       
 
    }//GEN-LAST:event_ViewPackagesDetailsNextButtonActionPerformed

    private void ViewPackageDetailsNoofAdultsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPackageDetailsNoofAdultsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewPackageDetailsNoofAdultsTextFieldActionPerformed

    private void ViewPackageDetailsNoofChildrenTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPackageDetailsNoofChildrenTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewPackageDetailsNoofChildrenTextFieldActionPerformed

    private void ViewPackagesDetailsNextButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPackagesDetailsNextButton1ActionPerformed
        Roompanelshift(CustomerButton.getText());
    }//GEN-LAST:event_ViewPackagesDetailsNextButton1ActionPerformed

    private void EditPackageDetailsSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditPackageDetailsSubmitButtonActionPerformed
        String RoomNo=EditPackageDetailsRoomNoValueTextField.getText();
        String PackDetails=EditPackageDetailsPackDetailsTextField.getText();
        String PackStatus=ViewRoomPackageDetailsjComboBox.getSelectedItem().toString();
        String Packamount=EditPackageDetailsAmountTextField.getText();
        String Adults=EditPackageDetailsNoofAdultsTextField.getText();
        String children =EditPackageDetailsNoofChildrenTextField.getText();
        if(PackDetails.isEmpty())
        EditPackageDetailsPackDetailsTextField.setBackground(new Color(255,150,150));
        else
        EditPackageDetailsPackDetailsTextField.setBackground(new Color(255,255,255));
        if(Packamount.isEmpty())
        EditPackageDetailsAmountTextField.setBackground(new Color(255,150,150));
        else
        EditPackageDetailsAmountTextField.setBackground(new Color(255,255,255));
        if(Adults.isEmpty())
        EditPackageDetailsNoofAdultsTextField.setBackground(new Color(255,150,150));
        else
        EditPackageDetailsNoofAdultsTextField.setBackground(new Color(255,255,255));
        if(children.isEmpty())
        EditPackageDetailsNoofChildrenTextField.setBackground(new Color(255,150,150));
        else
        EditPackageDetailsNoofChildrenTextField.setBackground(new Color(255,255,255));
        if(RoomNo.isEmpty())
        EditPackageDetailsRoomNoValueTextField.setBackground(new Color(255,150,150));
        else
        EditPackageDetailsRoomNoValueTextField.setBackground(new Color(255,255,255));
        if(PackDetails.isEmpty()||Packamount.isEmpty()||Adults.isEmpty()||children.isEmpty()||RoomNo.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill all the fields!","Form Incomplete!",0);
        }
        if(!PackDetails.isEmpty()&&!Packamount.isEmpty()&&!Adults.isEmpty()&&!children.isEmpty()&&!RoomNo.isEmpty()){
            try{
                String q = "Insert into room_package (room_package_description,room_package_status,room_package_amount,room_package_NoofAdults,room_package_NoofChildren,room_package_roomno) values ('"+PackDetails+"','"+PackStatus+"','"+Packamount+"','"+Adults+"','"+children+"','"+RoomNo+"')";
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                JOptionPane.showMessageDialog(null,"Successfully Added To The Database!","Success Message!",1);
                ViewPackageTableLoad();
          
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_EditPackageDetailsSubmitButtonActionPerformed

    private void EditPackageDetailsUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditPackageDetailsUpdateButtonActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Are You sure you want to update?");
        if(x==0){
            try{
                String pid=EditpackagespackidValueLabel.getText();
                String Rno=EditPackageDetailsRoomNoValueTextField.getText();
                String Packamount=EditPackageDetailsAmountTextField.getText();
                String Adults=EditPackageDetailsNoofAdultsTextField.getText();
                String children =EditPackageDetailsNoofChildrenTextField.getText();
                String q ="update room_package set room_package_roomno ='"+Rno+"',room_package_amount='"+Packamount+"',room_package_NoofAdults='"+Adults+"',room_package_NoofChildren='"+children+"' where room_package_packageid ='"+pid+"'";
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                ViewPackageTableLoad();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x==1){}
        else if(x==2){}
    }//GEN-LAST:event_EditPackageDetailsUpdateButtonActionPerformed

    private void EditPackageDetailsDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditPackageDetailsDeleteButtonActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Are You sure you want to Delete this?");
        if(x==0){
            try{
                String packid=EditpackagespackidValueLabel.getText();

                String q ="Delete from room_package where room_package_packageid='"+packid+"'";
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                JOptionPane.showMessageDialog(null,"Successfully Added To The Database!","Success Message!",1);
                ViewPackageTableLoad();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else if(x==1){}
        else if(x==2){}
    }//GEN-LAST:event_EditPackageDetailsDeleteButtonActionPerformed

    private void EditPackageDetailsNoofAdultsTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditPackageDetailsNoofAdultsTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditPackageDetailsNoofAdultsTextFieldActionPerformed

    private void EditPackageDetailsNoofChildrenTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditPackageDetailsNoofChildrenTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditPackageDetailsNoofChildrenTextFieldActionPerformed

    private void AddReservationDetailsSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddReservationDetailsSubmitButtonActionPerformed
       
     //entering the values in the creservation interface 
        SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd");
        String Cusnic=AddReservationCusNICTextField.getText();
        String Cusname=AddReservationCusNameTextField.getText();
        String date=formatDate.format(MakeReservationjDateChooser.getDate());
        String checkin=AddReservationCheckInTextField.getText();
        String checkout=AddReservationCheckOutTextField.getText();
        String RoomCount=RoomCountjTextField.getText();
        String Amount=MakeReservationFinaTotallAmountjlabel.getText();
        String meals=Selectmeal();
        
        if(checkin.isEmpty())
            AddReservationCheckInTextField.setBackground(new Color(255,150,150));
        else
            AddReservationCheckInTextField.setBackground(new Color(255,255,255));
        if(checkout.isEmpty())
            AddReservationCheckOutTextField.setBackground(new Color(255,150,150));
        else
            AddReservationCheckOutTextField.setBackground(new Color(255,255,255));
        if(date.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill all the fields!","Form Incomplete!",0);
        }
        if(!date.isEmpty()){
            try {
                String q="Insert into room_roomreservation (room_roomreservation_cusnic,room_roomreservation_cusname,room_roomreservation_date,room_roomreservation_amount,room_roomreservation_type,room_roomreservation_checkin,room_roomreservation_checkout,room_roomreservation_RoomCount,room_roomreservation_meals) values ('"+Cusnic+"','"+Cusname+"','"+date+"','"+Amount+"','Room'"+",'"+checkin+"','"+checkout+"','"+RoomCount+"','"+meals+"')";  
                System.out.println(q);
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                EditReservationTableLoad();
                
                String w = "select room_roomreservation_resid from room_roomreservation order by room_roomreservation_resid desc limit 1";
                pstatement=con.prepareStatement(w);
                Results=pstatement.executeQuery();
                while(Results.next())
                    ResId=Results.getInt(1);
                EditReservationTableLoad();
                int rowcount=jTableToReserveRoom.getRowCount();
                RoomNoArray=new String[rowcount];
                for(int i=0;i<rowcount;i++)
                {
                    RoomNoArray[i]=jTableToReserveRoom.getValueAt(i, 0).toString();
                    System.out.println(RoomNoArray[i]);
                    String rs="update room_roomdetails set room_roomdetails_status ='Not Available'"+" where room_roomdetails_roomno ='"+RoomNoArray[i]+"'";
                    pstatement=con.prepareStatement(rs);
                    pstatement.execute();
                    String x="Insert into room_reservedroom (room_reservedRoom_ResId,room_reservedRoom_RoomNo,room_reservedRoom_cusnic,room_reservedroom_checkIn,room_reservedroom_checkOut) values ('"+ResId+"','"+RoomNoArray[i]+"','"+Cusnic+"','"+checkin+"','"+checkout+"')";  
                    pstatement=con.prepareStatement(x);
                    pstatement.execute();
                    EditReservationTableLoad();
                    EditReservationTableLoad();
                    EditRoomTableLoad();
                    ViewRoomTableLoad();
                }
               JOptionPane.showMessageDialog(null,"Succesfully updated","Success Messsage",1);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
            }  

    }//GEN-LAST:event_AddReservationDetailsSubmitButtonActionPerformed

    private void AddReservationDetailsBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddReservationDetailsBackButtonActionPerformed
       Roompanelshift(RoomButton.getText());
    }//GEN-LAST:event_AddReservationDetailsBackButtonActionPerformed

    private void AddReservationCusNICTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddReservationCusNICTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddReservationCusNICTextFieldKeyTyped

    private void AddReservationCusNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddReservationCusNameTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddReservationCusNameTextFieldKeyTyped

    private void viewReservationDetailssearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReservationDetailssearchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewReservationDetailssearchTextFieldActionPerformed

    private void ViewReservationDetailsSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewReservationDetailsSearchButtonActionPerformed
        String searchVal=viewReservationDetailssearchTextField.getText();
        try { 
            String q ="Select room_roomreservation_resid as 'Reservation ID',room_roomreservation_cusnic as 'Customer NIC',room_roomreservation_cusname as 'Customer Name' ,room_roomreservation_date as 'Date',room_roomreservation_amount as 'Amount',room_roomreservation_type as 'Package/Room',room_roomreservation_checkin as 'Check In',room_roomreservation_checkout 'Check Out',room_roomreservation_meals as Meals from room_roomreservation where room_roomreservation_cusnic LIkE '%"+searchVal+"%' AND room_roomreservation_status='notdone'"+"";
            pstatement=con.prepareStatement(q);
            Results=pstatement.executeQuery();
            ViewReservationDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_ViewReservationDetailsSearchButtonActionPerformed

    private void RoomStockunitsjTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomStockunitsjTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomStockunitsjTextFieldActionPerformed

    private void RoomStockRequestOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomStockRequestOrderButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomStockRequestOrderButtonActionPerformed

    private void RoomstockClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomstockClearButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomstockClearButtonActionPerformed

    private void AddLaundaryDetailsLaundarycusnameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsLaundarycusnameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddLaundaryDetailsLaundarycusnameTextFieldActionPerformed

    private void AddLaundaryDetailsLaundarycusnameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsLaundarycusnameTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddLaundaryDetailsLaundarycusnameTextFieldKeyTyped

    private void AddLaundaryDetailsLaundaryDescTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsLaundaryDescTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddLaundaryDetailsLaundaryDescTextFieldActionPerformed

    private void AddLaundaryDetailsLaundaryDescTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsLaundaryDescTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddLaundaryDetailsLaundaryDescTextFieldKeyTyped

    private void AddLaundaryDetailsLaundaryAmountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsLaundaryAmountTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddLaundaryDetailsLaundaryAmountTextFieldActionPerformed

    private void AddLaundaryDetailsLaundaryAmountTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsLaundaryAmountTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddLaundaryDetailsLaundaryAmountTextFieldKeyTyped

    private void AddLaundaryDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsTableMouseClicked
        int r = AddLaundaryDetailsTable.getSelectedRow();
        String Amount=AddLaundaryDetailsTable.getValueAt(r, 4).toString();
        String Desc=AddLaundaryDetailsTable.getValueAt(r, 3).toString();
        
        AddLaundaryDetailsLaundaryDescTextField.setText(Desc);
        AddLaundaryDetailsLaundaryAmountTextField.setText(Amount);
        AddLaundaryDetailsLaundarytelnoTextField.setVisible(false);
        AddLaundaryDetailstelnojLabel.setVisible(false);
        AddLaundaryDatejLabel.setVisible(false);
        AddLaundaryjDateChooser.setVisible(false);
        AddLaundaryDetailsLaundaryWeightjLabel.setVisible(false);
        RoomLaundryjComboBox.setVisible(false);
    }//GEN-LAST:event_AddLaundaryDetailsTableMouseClicked

    private void AddLaundaryDetailsSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsSubmitButtonActionPerformed
        SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd");
        String lnic=AddLaundaryDetailsNICValuejLabel.getText();
        String cusName=AddLaundaryDetailsLaundarycusnameTextField.getText();
        String Desc=AddLaundaryDetailsLaundaryDescTextField.getText();
        String Amount=AddLaundaryDetailsLaundaryAmountTextField.getText();
        String Date=formatDate.format(AddLaundaryjDateChooser.getDate());
        String tel=AddLaundaryDetailsLaundarytelnoTextField.getText();
        if(lnic.isEmpty())
        AddLaundaryDetailsNICValuejLabel.setBackground(new Color(255,150,150));
        else
        AddLaundaryDetailsNICValuejLabel.setBackground(new Color(255,255,255));
        if(cusName.isEmpty())
        AddLaundaryDetailsLaundarycusnameTextField.setBackground(new Color(255,150,150));
        else
        AddLaundaryDetailsLaundarycusnameTextField.setBackground(new Color(255,255,255));
        if(Desc.isEmpty())
        AddLaundaryDetailsLaundaryDescTextField.setBackground(new Color(255,150,150));
        else
        AddLaundaryDetailsLaundaryDescTextField.setBackground(new Color(255,255,255));
        if(Amount.isEmpty())
        AddLaundaryDetailsLaundaryAmountTextField.setBackground(new Color(255,150,150));
        else
        AddLaundaryDetailsLaundaryAmountTextField.setBackground(new Color(255,255,255));
        if(tel.isEmpty())
        AddLaundaryDetailsLaundarytelnoTextField.setBackground(new Color(255,150,150));
        else
        AddLaundaryDetailsLaundarytelnoTextField.setBackground(new Color(255,255,255));
        if(lnic.isEmpty()||Desc.isEmpty()||Amount.isEmpty()||tel.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Please fill all the fields!","Form Incomplete!",0);
        }
        if(!lnic.isEmpty()&&!Desc.isEmpty()&&!Amount.isEmpty()&&!tel.isEmpty())
        {
            try {
                String q="Insert into room_roomlaundary (room_roomlaundary_cusnic,room_roomlaundary_cusName,room_roomlaundary_Desc,room_roomlaundary_amount,room_roomlaundary_date) values ('"+lnic+"','"+cusName+"','"+Desc+"','"+Amount+"','"+Date+"')";
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                EditLaundaryTableLoad();
                JOptionPane.showMessageDialog(null,"Successfully Added To The Database!","Success Message!",1);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_AddLaundaryDetailsSubmitButtonActionPerformed

    private void AddLaundaryDetailsLaundarytelnoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsLaundarytelnoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddLaundaryDetailsLaundarytelnoTextFieldActionPerformed

    private void AddLaundaryDetailsLaundarytelnoTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsLaundarytelnoTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddLaundaryDetailsLaundarytelnoTextFieldKeyTyped

    private void AddLaundaryDetailsBackuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsBackuttonActionPerformed
        Roompanelshift(CustomerButton.getText());
    }//GEN-LAST:event_AddLaundaryDetailsBackuttonActionPerformed

    private void AddCustomizeRoomServiceNoofPlatesTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCustomizeRoomServiceNoofPlatesTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddCustomizeRoomServiceNoofPlatesTextFieldActionPerformed

    private void ViewPackageCustomerNICTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPackageCustomerNICTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewPackageCustomerNICTextFieldActionPerformed

    private void ViewPackageCustomerNICTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ViewPackageCustomerNICTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewPackageCustomerNICTextFieldKeyReleased

    private void AddReservationCheckInTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddReservationCheckInTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddReservationCheckInTextFieldKeyTyped

    private void AddReservationCheckOutTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddReservationCheckOutTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddReservationCheckOutTextFieldKeyTyped

    private void UpdateRoomPaymentDueDateTextFeildsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateRoomPaymentDueDateTextFeildsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdateRoomPaymentDueDateTextFeildsActionPerformed

    private void AddPaymentsDetailsSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPaymentsDetailsSubmitButtonActionPerformed
      SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd");
      String cusNIC=AddRoomPaymentCusNICvaluejlabel.getText();
      String Date=formatDate.format(AddRoomPaymentDueDatejDateChooser.getDate());
      String Payment=AddRoomPaymentTotalValueTextFields.getText();
      String method=AddRoomPaymentjComboBox.getSelectedItem().toString();
      String COD=AddPaymentsCODjComboBox.getSelectedItem().toString();
      String Desc=AddRoomPaymentdescDesc.getText();
      String ResId=AddPaymentsRIDValuejLabel.getText();
      String Room,approved;
      Integer cashflowid,RoomNo;
    
      try {
                String findroom="Select room_reservedRoom_RoomNo from room_reservedroom where room_reservedRoom_cusnic='"+cusNIC+"' AND room_reservedRoom_ResId='"+ResId+"'";
                pstatement=con.prepareStatement(findroom);
                Results=pstatement.executeQuery();
                int Counter=0;
                int ArrayRoomNo[]=new int[10];
                while(Results.next()) {
                    ArrayRoomNo[Counter++]=Results.getInt(1);
                }
                for(int i=0;i<ArrayRoomNo.length;i++)
                {   String rs="update room_roomdetails set room_roomdetails_status ='Available'"+" where room_roomdetails_roomno ='"+ArrayRoomNo[i]+"'";
                    pstatement=con.prepareStatement(rs);
                    pstatement.execute();
                   
                    String ps="update room_package set room_package_status ='Available'"+" where room_package_roomno ='"+ArrayRoomNo[i]+"'";
                    pstatement=con.prepareStatement(ps);
                    pstatement.execute();
                    ViewRoomTableLoad();
                    EditRoomTableLoad();
                    ViewPackageTableLoad();
                    
                }
                
                String rs="update room_roomreservation set room_roomreservation_status ='done'"+" where room_roomreservation_resid ='"+ResId+"'";
                pstatement=con.prepareStatement(rs);
                pstatement.execute();
                
                String ls="update room_roomlaundary set room_roomlaundary_status ='done'"+" where room_roomlaundary_status ='notdone'"+" AND room_roomlaundary_cusnic='"+cusNIC+"'";
                pstatement=con.prepareStatement(ls);
                pstatement.execute();
                
                String rso="update room_roomserviceorders set room_roomserviceorder_status ='done'"+" where room_roomserviceorder_status ='notdone'"+" AND room_roomserviceorders_cusnic='"+cusNIC+"'";
                pstatement=con.prepareStatement(rso);
                pstatement.execute();
                
                String q="Insert into room_payments (room_payments_cusnic,room_payments_method,room_payment_amount,room_payments_debitorcredit,room_payments_date,room_payments_desc) values ('"+cusNIC+"','"+method+"','"+Payment+"','"+COD+"','"+Date+"','"+Desc+"')";
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                //another query for finanacce
                //String sq = "insert into fin_cashflow(cashflow_date, cashflow_description, cashflow_department, cashflow_method, cashflow_amount, cashflow_Payment_type, cashflow_Payment_status, cashflow_approval) values('"+Date+"','"+Desc+"',' Room'"+"','"+ method+"','"+ Payment +"','"+COD+"','"+1+"','approved"')";
                //pstatement=con.prepareStatement(sq);
                //pstatement.execute();
                ViewcustomerTableLoad();//calling of table load functions
                EditcustomerTableLoad();
                ViewRoomTableLoad();
                EditRoomTableLoad();
                ViewPackageTableLoad();
                EditReservationTableLoad();
                EditLaundaryTableLoad();
                updatePaymentTableLoad();
                RoomServiceTableLoad();
                JOptionPane.showMessageDialog(null,"Successfully Added To The Database!","Success Message!",1);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
    }//GEN-LAST:event_AddPaymentsDetailsSubmitButtonActionPerformed

    private void UpdatePaymentsDetailsSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatePaymentsDetailsSearchButtonActionPerformed
        String searchval=RoomUpdatePaymentsNICvalueTextfield.getText();
        String q ="Select room_payments_cusnic,room_payments_method,room_payment_amount,room_payments_debitorcredit,room_payments_date,room_payments_desc from room_payments where room_payments_cusnic LIkE '%"+searchval+"%'";
        try {
            pstatement=con.prepareStatement(q);
            Results=pstatement.executeQuery();
            UpdateRoompaymentDetailsjTable.setModel(DbUtils.resultSetToTableModel(Results));

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_UpdatePaymentsDetailsSearchButtonActionPerformed

    private void UpdatePaymentDetailsUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdatePaymentDetailsUpdateButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UpdatePaymentDetailsUpdateButtonActionPerformed

    
    private void ViewReservationDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewReservationDetailsTableMouseClicked
        int r = ViewReservationDetailsTable.getSelectedRow();
        String ResID=ViewReservationDetailsTable.getValueAt(r, 0).toString();
        String Cusnic=ViewReservationDetailsTable.getValueAt(r, 1).toString();
        String CusName=ViewReservationDetailsTable.getValueAt(r, 2).toString();
        String Date=ViewReservationDetailsTable.getValueAt(r, 3).toString();
        String RAmount=ViewReservationDetailsTable.getValueAt(r, 4).toString();
        String RType=ViewReservationDetailsTable.getValueAt(r, 5).toString();
        String checkin=ViewReservationDetailsTable.getValueAt(r, 6).toString();
        String checkout=ViewReservationDetailsTable.getValueAt(r, 7).toString();
        String meals=ViewReservationDetailsTable.getValueAt(r, 8).toString();
        
       ResArray=new String[]{ResID,Cusnic,CusName,Date,RAmount,RType,checkin,checkout,meals};
    }//GEN-LAST:event_ViewReservationDetailsTableMouseClicked

    private void ViewReservationDetailsPaymentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewReservationDetailsPaymentsButtonActionPerformed
       Roompanelshift(RoomPaymentsButton.getText());
       PaymentDetailsjTabbedPane.setSelectedIndex(0);
       AddRoomPaymentCusNICvaluejlabel.setText(ResArray[1]);
       AddPaymentsRIDValuejLabel.setText(ResArray[0]);
       AddRoomPaymentTextFields.setText(ResArray[4]);
       AddRoomPaymentCIjLabel.setText(ResArray[6]);
       AddRoomPaymentCOjlabel.setText(ResArray[7]);
       AddRoomPaymentRSamountValueTextFields.setText("0");
       AddRoomPaymentLaValueTextFields.setText("0");
    }//GEN-LAST:event_ViewReservationDetailsPaymentsButtonActionPerformed

    private void AddRoomPaymentjComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRoomPaymentjComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddRoomPaymentjComboBoxActionPerformed

    private void AddPaymentsDetailsBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPaymentsDetailsBackButtonActionPerformed
        Roompanelshift(ReservationButton.getText());
    }//GEN-LAST:event_AddPaymentsDetailsBackButtonActionPerformed

    private void AddPaymentsDetailsCheckRSButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPaymentsDetailsCheckRSButtonActionPerformed
       Roompanelshift(RoomServiceButton.getText());
       RoomServiceTabbedPane.setSelectedIndex(2);
       RoomServiceTableLoad();
       RoomServiceSearchTextField.setText(AddRoomPaymentCusNICvaluejlabel.getText());
    }//GEN-LAST:event_AddPaymentsDetailsCheckRSButtonActionPerformed

    private void RoomServiceOrderrDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomServiceOrderrDetailsTableMouseClicked
        /*int r = RoomServiceOrderrDetailsTable.getSelectedRow();
        String packID=RoomServiceOrderrDetailsTable.getValueAt(r, 0).toString();
        String Des=RoomServiceOrderrDetailsTable.getValueAt(r, 1).toString();
        String Adults=RoomServiceOrderrDetailsTable.getValueAt(r, 4).toString();
        String NoOfChildren=RoomServiceOrderrDetailsTable.getValueAt(r, 5).toString();
        String Amount=RoomServiceOrderrDetailsTable.getValueAt(r, 3).toString();*/
    }//GEN-LAST:event_RoomServiceOrderrDetailsTableMouseClicked

    private void RoomServiceSearchTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomServiceSearchTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomServiceSearchTextFieldActionPerformed

    private void RoomServiceOrdersSearchjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomServiceOrdersSearchjButtonActionPerformed
        String searchval=RoomServiceSearchTextField.getText();
        try {               
            String q ="Select room_roomserviceorders_cusnic as 'Customer NIC',room_roomserviceorders_roomno as 'Room No',room_roomserviceorders_noofplates as 'No of plates',room_roomserviceorders_regular as 'Regular',room_roomserviceorders_customized as 'Customized',room_roomserviceorders_date as 'Date',room_roomserviceorders_price as 'Price',room_roomserviceorder_status as 'Status',room_roomserviceorders_package as 'Package',room_roomserviceorders_Description as Description from room_roomserviceorders where room_roomserviceorders_cusnic='"+searchval+"' AND room_roomserviceorder_status ='notdone'"+"";
            pstatement=con.prepareStatement(q);
            Results=pstatement.executeQuery();
            RoomServiceOrderrDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }    }//GEN-LAST:event_RoomServiceOrdersSearchjButtonActionPerformed

    private void ViewReservationDetailsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewReservationDetailsCancelButtonActionPerformed
       ViewRoomDetailsCusnicValuejLabel.setText("");
       MakeReservationFinaTotallAmountjlabel.setText("");
       RoomCountjTextField.setText("");
       RoomReservationHeadCountTextField.setText("");
       AddReservationCheckOutTextField.setText("");
       AddReservationCheckInTextField.setText("");
       MakeReservationFinalAmountjlabel.setText("");
       WithMealsjRadioButton.setSelected(false);
       WithoutMealsjRadioButton.setSelected(false);
    }//GEN-LAST:event_ViewReservationDetailsCancelButtonActionPerformed

    private void AddPaymentsDetailsLaundryRSButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPaymentsDetailsLaundryRSButtonActionPerformed
       Roompanelshift(RoomLaundaryButton.getText());
       AddLaundaryDetailsNICValuejLabel.setText(AddRoomPaymentCusNICvaluejlabel.getText());
       AddLaundaryDetailsLaundarycusnameTextField.setText(ResArray[2]);
       AddLaundaryDetailsSearchButton.setEnabled(true);
       LaundryOrdersjTable.setVisible(true);
       TotalLAundryjTextField.setVisible(true);
       LaundryCalculateTotjButton.setEnabled(true);
       LaundryAddjButton.setEnabled(true);
       LaundryDeletejButton.setEnabled(true);
    }//GEN-LAST:event_AddPaymentsDetailsLaundryRSButtonActionPerformed

    private void AddLaundaryDetailsSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsSearchButtonActionPerformed
        String searchval=AddLaundaryDetailsNICValuejLabel.getText();
        try {       
            String q ="Select room_roomlaundary_lid as 'Laundary ID',room_roomlaundary_cusnic as 'Customer NIC',room_roomlaundary_cusName as 'Customer Name',room_roomlaundary_Desc as 'Description',room_roomlaundary_amount as 'Amount',room_roomlaundary_date  as 'Date',room_roomlaundary_Weight as Weight from room_roomlaundary where room_roomlaundary_cusnic ='"+searchval+"' AND room_roomlaundary_status='notdone'"+"";
            pstatement=con.prepareStatement(q);
            Results=pstatement.executeQuery();
            AddLaundaryDetailsTable.setModel(DbUtils.resultSetToTableModel(Results));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_AddLaundaryDetailsSearchButtonActionPerformed

    private void AddLaundaryDetailsBackToPAymentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddLaundaryDetailsBackToPAymentsButtonActionPerformed
       Roompanelshift(RoomPaymentsButton.getText());
       AddRoomPaymentLaValueTextFields.setText(AddLaundaryDetailsLaundaryAmountTextField.getText());
    }//GEN-LAST:event_AddLaundaryDetailsBackToPAymentsButtonActionPerformed

    private void ReserveRoomjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReserveRoomjButtonActionPerformed
        Roompanelshift(CustomerButton.getText());
        ViewCustomerDetailsOrderRoomButton.setEnabled(true);
        ViewCustomerDetailsOrderPackageButton.setEnabled(false);
        ViewCustomerDetailsLaundaryButton.setEnabled(false);
        ViewCustomerDetailsOrderRoomServiceButton.setEnabled(false);
        CustomerDetailsTabbedPane.setSelectedIndex(0);
        CustomerDetailsTabbedPane.setEnabledAt(1,false);
        CustomerDetailsTabbedPane.setEnabledAt(0,true);
        ViewCustomerDetailsOrderRoomButton.setForeground(new Color (255,153,204));

    }//GEN-LAST:event_ReserveRoomjButtonActionPerformed

    private void RoomPaymentsjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomPaymentsjButtonActionPerformed
        Roompanelshift(ReservationButton.getText());
        ReservationDetailsTabbedPane.setSelectedIndex(2);
        ReservationDetailsTabbedPane.setEnabledAt(1,false);
        ReservationDetailsTabbedPane.setEnabledAt(0,false);
    }//GEN-LAST:event_RoomPaymentsjButtonActionPerformed

    private void ViewCustomerDetailsResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewCustomerDetailsResetButtonActionPerformed
        ViewRoomCustomerNameTextField.setText("");
        ViewRoomCustomerNICTextField.setText("");
        ViewRoomCustomerAddressTextField.setText("");
        ViewRoomCustomerTelNoTextField.setText("");
        viewcustomerDetailssearchTextField.setText("");
        ViewRoomCustomerNameTextField.setBackground(new Color(255,255,255));
        ViewRoomCustomerNICTextField.setBackground(new Color(255,255,255));
        ViewRoomCustomerAddressTextField.setBackground(new Color(255,255,255));
        ViewRoomCustomerTelNoTextField.setBackground(new Color(255,255,255));
        viewcustomerDetailssearchTextField.setBackground(new Color(255,255,255));
        ViewcustomerTableLoad();
    }//GEN-LAST:event_ViewCustomerDetailsResetButtonActionPerformed

    private void EditCustomerDetailsResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditCustomerDetailsResetButtonActionPerformed
       EditRoomCustomerNameTextField.setText("");
       EditRoomCustomerNICTextField.setText("");
       EditRoomCustomerAddressTextField.setText("");
       EditRoomCustomerTElNoTextField.setText("");
       EditcustomerDetailssearchTextField.setText("");
       EditCustomerDetailsIDvalueLabel.setText("");
    }//GEN-LAST:event_EditCustomerDetailsResetButtonActionPerformed

    private void ViewCustomerDetailsResetButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewCustomerDetailsResetButton1ActionPerformed
       
       ViewRoomDetailsNoofAdultsTextField.setText("");
       ViewRoomDetailsjComboBox.setSelectedIndex(0);
       ViewRoomDetailsNoChildrenTextField.setText("");
       jTextFieldTotalAmountToPAy.setText("");
       DefaultTableModel model = (DefaultTableModel)jTableToReserveRoom.getModel();
       model.setRowCount(0);
    }//GEN-LAST:event_ViewCustomerDetailsResetButton1ActionPerformed

    private void ViewRoomCustomerNICTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ViewRoomCustomerNICTextFieldKeyTyped

    }//GEN-LAST:event_ViewRoomCustomerNICTextFieldKeyTyped

    private void ViewPackageDetailsReservePackageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPackageDetailsReservePackageButtonActionPerformed
        Roompanelshift(ReservationButton.getText());
        ReservationDetailsTabbedPane.setSelectedIndex(1);
        ReservationDetailsTabbedPane.setEnabledAt(0,false);
        ReservationDetailsTabbedPane.setEnabledAt(1,true);
        ReservationDetailsTabbedPane.setEnabledAt(2,false);
        jLabelPackAmount.setText(jTextFieldTotalAmountToPAypackage.getText());
        AddPAckageReservationCusNameTextField.setText(ViewRoomCustomerNameTextField.getText());
        AddPAckageReservationCusNICTextField.setText(ViewPackageCustomerNICTextField.getText());
        int rowcount=jTableToReservePackage.getRowCount();
        PackageRoomCountjTextField.setText(String.valueOf(rowcount));
        SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd");
        String CheckIn=formatDate.format(PackCheckInjDateChooser.getDate());
        String CheckOut=formatDate.format(PackCheckoutjDateChooser.getDate());
        AddPAckageReservationCheckInTextField.setText(CheckIn);
        AddPAckageReservationCheckOutTextField.setText(CheckOut);
    }//GEN-LAST:event_ViewPackageDetailsReservePackageButtonActionPerformed

    private void ViewPackageDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewPackageDetailsTableMouseClicked
        int r = ViewPackageDetailsTable.getSelectedRow();
        String packID=ViewPackageDetailsTable.getValueAt(r, 0).toString();
        String Des=ViewPackageDetailsTable.getValueAt(r, 1).toString();
        String Amount=ViewPackageDetailsTable.getValueAt(r, 3).toString();
        String RoomNo=ViewPackageDetailsTable.getValueAt(r, 6).toString();
        String Adults=ViewPackageDetailsTable.getValueAt(r, 4).toString();
        String Children=ViewPackageDetailsTable.getValueAt(r, 5).toString();
        
        ViewPackageDetailsNoofAdultsTextField.setText(Adults);  
        ViewPackageDetailsNoofChildrenTextField.setText(Children);
        
    }//GEN-LAST:event_ViewPackageDetailsTableMouseClicked

    private void ViewPackageDetailsResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPackageDetailsResetButtonActionPerformed

        ViewPackageDetailsNoofAdultsTextField.setText("");
        ViewPackageDetailsNoofChildrenTextField.setText("");
    }//GEN-LAST:event_ViewPackageDetailsResetButtonActionPerformed

    private void EditPackageDetailsRoomNoValueTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditPackageDetailsRoomNoValueTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditPackageDetailsRoomNoValueTextFieldActionPerformed

    private void EditPackageDetailsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EditPackageDetailsTableMouseClicked
        int r = EditPackageDetailsTable.getSelectedRow();
        String packID=EditPackageDetailsTable.getValueAt(r, 0).toString();
        String Des=EditPackageDetailsTable.getValueAt(r, 1).toString();
        String Status=EditPackageDetailsTable.getValueAt(r, 2).toString();
        String Amount=EditPackageDetailsTable.getValueAt(r, 3).toString();
        String RoomNo=EditPackageDetailsTable.getValueAt(r, 6).toString();
        String Adults=EditPackageDetailsTable.getValueAt(r, 4).toString();
        String Children=EditPackageDetailsTable.getValueAt(r, 5).toString();
        
        EditPackageDetailsNoofAdultsTextField.setText(Adults);  
        EditPackageDetailsNoofChildrenTextField.setText(Children);
        EditpackagespackidValueLabel.setText(packID);
        EditPackageDetailsRoomNoValueTextField.setText(RoomNo);
        EditPackageDetailsAmountTextField.setText(Amount);
        EditPackageDetailsPackDetailsTextField.setText(Des);
        ViewRoomPackageDetailsjComboBox.setSelectedItem(Status);
    }//GEN-LAST:event_EditPackageDetailsTableMouseClicked

    private void EditRoomCustomerTElNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditRoomCustomerTElNoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditRoomCustomerTElNoTextFieldActionPerformed

    private void EditRoomCustomerAddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditRoomCustomerAddressTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditRoomCustomerAddressTextFieldActionPerformed

    private void jButtonAddRoomDetailsToReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddRoomDetailsToReserveActionPerformed
        TableModel model1=ViewRoomDetailsjTable.getModel();
        int [] indexes= ViewRoomDetailsjTable.getSelectedRows(); 
        Object row[]=new Object[2];
        DefaultTableModel model2=(DefaultTableModel)jTableToReserveRoom.getModel();
        for(int i=0;i<indexes.length;i++)
        {
            row[0]=model1.getValueAt(indexes[i],0);
            row[1]=model1.getValueAt(indexes[i],4);
            model2.addRow(row);
        }
            
    }//GEN-LAST:event_jButtonAddRoomDetailsToReserveActionPerformed

    private void jButtonRemoveRoomReservesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveRoomReservesActionPerformed
        DefaultTableModel model2=(DefaultTableModel)jTableToReserveRoom.getModel();
        try{
            int SelectedRowIndex=jTableToReserveRoom.getSelectedRow();
            model2.removeRow(SelectedRowIndex);
        }
        catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonRemoveRoomReservesActionPerformed

    private void jButtonTotalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTotalAmountActionPerformed
         jTextFieldTotalAmountToPAy.setText(Double.toString(getSum()));
    }//GEN-LAST:event_jButtonTotalAmountActionPerformed

    private void ViewRoomDetailsNoofAdultsTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ViewRoomDetailsNoofAdultsTextFieldKeyTyped
        char c= evt.getKeyChar();
        if((Character.isLetter(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_ViewRoomDetailsNoofAdultsTextFieldKeyTyped

    private void ViewRoomDetailsNoChildrenTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ViewRoomDetailsNoChildrenTextFieldKeyTyped
       char c= evt.getKeyChar();
        if((Character.isLetter(c))||(c==KeyEvent.VK_BACK_SPACE)||(c==KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_ViewRoomDetailsNoChildrenTextFieldKeyTyped

    private void ViewCustomerDetailsResetButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewCustomerDetailsResetButton3ActionPerformed
       EditRoomDetailsNoofAdultsTextField.setText("");
       EditRoomDetailsNoofChildrenTextField.setText("");
       ViewRoomDetailsjComboBox.setSelectedIndex(0);
       EditRoomDetailsAmountTextField.setText("");
       EditRoomDetailsRoomNovalueLabel.setText("");
    }//GEN-LAST:event_ViewCustomerDetailsResetButton3ActionPerformed

    private void ViewCustomerDetailsResetButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewCustomerDetailsResetButton4ActionPerformed
        ViewRoomDetailsNoofAdultsTextField.setText("");
        ViewRoomDetailsNoChildrenTextField.setText("");
        EditRoomDetailsjComboBox.setSelectedIndex(0);
        
    }//GEN-LAST:event_ViewCustomerDetailsResetButton4ActionPerformed

    private void ViewPackageDetailsResetButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPackageDetailsResetButton1ActionPerformed
       EditpackagespackidValueLabel.setText("");
       EditPackageDetailsPackDetailsTextField.setText("");
       EditPackageDetailsRoomNoValueTextField.setText("");
       EditPackageDetailsAmountTextField.setText("");
       EditPackageDetailsNoofAdultsTextField.setText("");
       EditPackageDetailsNoofChildrenTextField.setText("");
       ViewRoomPackageDetailsjComboBox.setSelectedIndex(0);
    }//GEN-LAST:event_ViewPackageDetailsResetButton1ActionPerformed

    private void jButtonRemovePackageReserves1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemovePackageReserves1ActionPerformed
         DefaultTableModel model2=(DefaultTableModel)jTableToReservePackage.getModel();
        try{
            int SelectedRowIndex=jTableToReservePackage.getSelectedRow();
            model2.removeRow(SelectedRowIndex);
        }
        catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonRemovePackageReserves1ActionPerformed

    private void jButtonAddPackageDetailsToReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPackageDetailsToReserveActionPerformed
        TableModel model1=ViewPackageDetailsTable.getModel();
        int [] indexes= ViewPackageDetailsTable.getSelectedRows(); 
        Object row[]=new Object[2];
        DefaultTableModel model2=(DefaultTableModel)jTableToReservePackage.getModel();
        for(int i=0;i<indexes.length;i++)
        {
            row[0]=model1.getValueAt(indexes[i],6);
            row[1]=model1.getValueAt(indexes[i],3);
            model2.addRow(row);
        }
    }//GEN-LAST:event_jButtonAddPackageDetailsToReserveActionPerformed

    private void jButtonTotalAmountPackageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTotalAmountPackageActionPerformed
       jTextFieldTotalAmountToPAypackage.setText(Double.toString( getPackSum()));
    }//GEN-LAST:event_jButtonTotalAmountPackageActionPerformed

    private void AddPAckageReservationDetailsSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPAckageReservationDetailsSubmitButtonActionPerformed
        //entering the values in the creservation interface 
        SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd");
        String Cusnic=AddPAckageReservationCusNICTextField.getText();
        String Cusname=AddPAckageReservationCusNameTextField.getText();
        String date=formatDate.format(MakePAckageReservationjDateChooser.getDate());
        String checkin=AddPAckageReservationCheckInTextField.getText();
        String checkout=AddPAckageReservationCheckOutTextField.getText();
        String RoomCount=PackageRoomCountjTextField.getText();
        String Amount=jLabelPackAmount.getText();
        
       
        if(checkin.isEmpty())
            AddPAckageReservationCheckInTextField.setBackground(new Color(255,150,150));
        else
            AddPAckageReservationCheckInTextField.setBackground(new Color(255,255,255));
        if(checkout.isEmpty())
            AddPAckageReservationCheckOutTextField.setBackground(new Color(255,150,150));
        else
            AddPAckageReservationCheckOutTextField.setBackground(new Color(255,255,255));
        if(date.isEmpty()){
            JOptionPane.showMessageDialog(null,"Please fill all the fields!","Form Incomplete!",0);
        }
        if(!date.isEmpty()){
            try {
                String q="Insert into room_roomreservation (room_roomreservation_cusnic,room_roomreservation_cusname,room_roomreservation_date,room_roomreservation_amount,room_roomreservation_type,room_roomreservation_checkin,room_roomreservation_checkout,room_roomreservation_RoomCount) values ('"+Cusnic+"','"+Cusname+"','"+date+"','"+Amount+"','Package'"+",'"+checkin+"','"+checkout+"','"+RoomCount+"')";  
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                EditReservationTableLoad();
                
                String w = "select room_roomreservation_resid from room_roomreservation order by room_roomreservation_resid desc limit 1";
                pstatement=con.prepareStatement(w);
                Results=pstatement.executeQuery();
                while(Results.next())
                    ResId=Results.getInt(1);
                EditReservationTableLoad();
                int rowcount=jTableToReservePackage.getRowCount();
                RoomNoArray=new String[rowcount];
                for(int i=0;i<rowcount;i++)
                {
                    RoomNoArray[i]=jTableToReservePackage.getValueAt(i, 0).toString();
                    System.out.println(RoomNoArray[i]);
                    String rs="update room_package set room_package_status ='Not Available'"+" where room_package_roomno ='"+RoomNoArray[i]+"'";
                    pstatement=con.prepareStatement(rs);
                    pstatement.execute();
                    String x="Insert into room_reservedroom (room_reservedRoom_ResId,room_reservedRoom_RoomNo,room_reservedRoom_cusnic,room_reservedroom_checkIn,room_reservedroom_checkOut) values ('"+ResId+"','"+RoomNoArray[i]+"','"+Cusnic+"','"+checkin+"','"+checkout+"')";  
                    pstatement=con.prepareStatement(x);
                    pstatement.execute();
                    EditReservationTableLoad();
                    EditReservationTableLoad();
                    EditRoomTableLoad();
                    ViewRoomTableLoad();
                }
               JOptionPane.showMessageDialog(null,"Succesfully updated","Success Messsage",1);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }

            }  
    }//GEN-LAST:event_AddPAckageReservationDetailsSubmitButtonActionPerformed

    private void AddPAckageReservationDetailsBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPAckageReservationDetailsBackButtonActionPerformed
        Roompanelshift(PackagesButton.getText());
        PackageRoomTabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_AddPAckageReservationDetailsBackButtonActionPerformed

    private void ViewPAckageReservationDetailsCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPAckageReservationDetailsCancelButtonActionPerformed
        ViewRoomDetailsCusnicValuejLabel.setText("");
    }//GEN-LAST:event_ViewPAckageReservationDetailsCancelButtonActionPerformed

    private void AddPAckageReservationCusNICTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddPAckageReservationCusNICTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddPAckageReservationCusNICTextFieldKeyTyped

    private void AddPAckageReservationCusNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddPAckageReservationCusNameTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddPAckageReservationCusNameTextFieldKeyTyped

    private void ViewCustomerDetailsSearchButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewCustomerDetailsSearchButton2ActionPerformed
        String CusName=ViewRoomCustomerNameTextField.getText();
        String CusNIC=ViewRoomCustomerNICTextField.getText();
        String CusAdd=ViewRoomCustomerAddressTextField.getText();
        String CusTel=ViewRoomCustomerTelNoTextField.getText();
        String p = ViewRoomCustomerTelNoTextField.getText();
   
        //---------------------------------Validations of the customer details
        if(CusName.isEmpty())
        ViewRoomCustomerNameTextField.setBackground(new Color(255,150,150));
        else
        ViewRoomCustomerNameTextField.setBackground(new Color(255,255,255));
        if(CusNIC.isEmpty())
        ViewRoomCustomerNICTextField.setBackground(new Color(255,150,150));
        else
        {
        ViewRoomCustomerNICTextField.setBackground(new Color(255,255,255));//validating the phone number
            String Tel = "^\\+?[0-9.  ()-]{10,25}$";
            Boolean b = CusTel.matches(Tel );
            if (b == false) {
               JOptionPane.showMessageDialog(null, "Invalid Telephone", "Invaild Input", JOptionPane.ERROR_MESSAGE);
               ViewRoomCustomerTelNoTextField.setText("");
            }
        }
        if(CusAdd.isEmpty())
        ViewRoomCustomerAddressTextField.setBackground(new Color(255,150,150));
        else
        ViewRoomCustomerAddressTextField.setBackground(new Color(255,255,255));
        if(CusTel.isEmpty())
        ViewRoomCustomerTelNoTextField.setBackground(new Color(255,150,150));
        else{
        ViewRoomCustomerTelNoTextField.setBackground(new Color(255,255,255));
         String nicval = "^[0-9]{9}[vVxX]$";
            Boolean w = CusNIC.matches(nicval);
            if (w == false) {
                JOptionPane.showMessageDialog(null, "Invalid NIC", "Invaild Input", JOptionPane.ERROR_MESSAGE);
                ViewRoomCustomerNICTextField.setText("");    
            }
        }
        if(CusName.isEmpty()||CusNIC.isEmpty()||CusAdd.isEmpty()||CusTel.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Please fill all the fields!","Form Incomplete!",0);
        }
        if(!CusName.isEmpty()&&!CusNIC.isEmpty()&&!CusAdd.isEmpty()&&!CusTel.isEmpty()){//entering values to the db
           try{
                String q = "Insert into room_customer (room_customer_name,room_customer_nic,room_customer_address,room_customer_telno) values ('"+CusName+"','"+CusNIC+"','"+CusAdd+"','"+CusTel+"')";
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                JOptionPane.showMessageDialog(null,"Succesfully updated","Success Messsage",1);
                EditcustomerTableLoad();
                ViewcustomerTableLoad();
              }
                catch(Exception e){
                        JOptionPane.showMessageDialog(null, e);
              }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_ViewCustomerDetailsSearchButton2ActionPerformed

    private void AddPAckageReservationCheckOutTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddPAckageReservationCheckOutTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddPAckageReservationCheckOutTextFieldKeyTyped

    private void AddPAckageReservationCheckInTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddPAckageReservationCheckInTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddPAckageReservationCheckInTextFieldKeyTyped

    private void jButtonTotalPackageReservesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTotalPackageReservesActionPerformed
            jTextFieldTotalAmountToPAypackage.setText(Double.toString(getPackSum()));
    }//GEN-LAST:event_jButtonTotalPackageReservesActionPerformed

    private void ViewRoomDetailsNextButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewRoomDetailsNextButton2ActionPerformed
        Roompanelshift(CustomerButton.getText());
        CustomerDetailsTabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_ViewRoomDetailsNextButton2ActionPerformed

    private void AddPaymentsDetailsLaundryRSButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPaymentsDetailsLaundryRSButton1ActionPerformed
        AddRoomPaymentTotalValueTextFields.setText(Double.toString(getTotal()));
    }//GEN-LAST:event_AddPaymentsDetailsLaundryRSButton1ActionPerformed

    private void AddPrintPaymentDetailsBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPrintPaymentDetailsBackButtonActionPerformed
        MessageFormat header = new  MessageFormat("Payment Bill");
        MessageFormat footer = new  MessageFormat("page{0,number,integer}");
        
        try{
         jTableToPrintPaymentDetails.print(JTable.PrintMode.NORMAL, header, footer);
        }
        catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,e.getMessage());
               }
        
    }//GEN-LAST:event_AddPrintPaymentDetailsBackButtonActionPerformed

    private void AddPaymentsDetailsAddToTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPaymentsDetailsAddToTableButtonActionPerformed
    String data1 =AddRoomPaymentCusNICvaluejlabel.getText();
    String data2 = AddRoomPaymentTextFields.getText();
    String data3 = ViewRoomCustomerNameTextField.getText();
    String data4 = AddRoomPaymentRSamountValueTextFields.getText();
    String data5 = AddRoomPaymentLaValueTextFields.getText();
  
    Object[] row = { data1, data3, data2, data4,data5};

    DefaultTableModel model = (DefaultTableModel) jTableToPrintPaymentDetails.getModel();

    model.addRow(row);
    }//GEN-LAST:event_AddPaymentsDetailsAddToTableButtonActionPerformed

    private void AddPaymentsDetailsCalculatorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPaymentsDetailsCalculatorButtonActionPerformed

    }//GEN-LAST:event_AddPaymentsDetailsCalculatorButtonActionPerformed

    private void AddRoomServicejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRoomServicejButtonActionPerformed
        TableModel model1=RoomServiceOrderrDetailsTable.getModel();
        int [] indexes= RoomServiceOrderrDetailsTable.getSelectedRows();
        Object row[]=new Object[2];
        DefaultTableModel model2=(DefaultTableModel)RoomServicejTable.getModel();
        for(int i=0;i<indexes.length;i++)
        {
            row[0]=model1.getValueAt(indexes[i],0);
            row[1]=model1.getValueAt(indexes[i],6);
            model2.addRow(row);
        }

    }//GEN-LAST:event_AddRoomServicejButtonActionPerformed

    private void AddRoomServiceDeletejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRoomServiceDeletejButtonActionPerformed
        DefaultTableModel model2=(DefaultTableModel)RoomServicejTable.getModel();
        try{
            int SelectedRowIndex=RoomServicejTable.getSelectedRow();
            model2.removeRow(SelectedRowIndex);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_AddRoomServiceDeletejButtonActionPerformed

    private void CAlculateRoomServiceTotaljButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CAlculateRoomServiceTotaljButtonActionPerformed
        RoomServiceTotalAmountjTextField.setText(Double.toString(getRoomServiceSum()));
    }//GEN-LAST:event_CAlculateRoomServiceTotaljButtonActionPerformed

    private void LaundryDeletejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaundryDeletejButtonActionPerformed
        DefaultTableModel model2=(DefaultTableModel)LaundryOrdersjTable.getModel();
        try{
            int SelectedRowIndex=LaundryOrdersjTable.getSelectedRow();
            model2.removeRow(SelectedRowIndex);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_LaundryDeletejButtonActionPerformed

    private void LaundryAddjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaundryAddjButtonActionPerformed
        TableModel model1=AddLaundaryDetailsTable.getModel();
        int [] indexes= AddLaundaryDetailsTable.getSelectedRows();
        Object row[]=new Object[2];
        DefaultTableModel model2=(DefaultTableModel)LaundryOrdersjTable.getModel();
        for(int i=0;i<indexes.length;i++)
        {
            row[0]=model1.getValueAt(indexes[i],1);
            row[1]=model1.getValueAt(indexes[i],4);
            model2.addRow(row);
        }

    }//GEN-LAST:event_LaundryAddjButtonActionPerformed

    private void LaundryCalculateTotjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaundryCalculateTotjButtonActionPerformed
       TotalLAundryjTextField.setText(Double.toString(getLaundrySum()));
    }//GEN-LAST:event_LaundryCalculateTotjButtonActionPerformed

    private void PackageReservationjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PackageReservationjButtonActionPerformed
        Roompanelshift(CustomerButton.getText());
        CustomerDetailsTabbedPane.setEnabledAt(0,true);
        CustomerDetailsTabbedPane.setEnabledAt(1,false);
        ViewCustomerDetailsOrderRoomButton.setEnabled(false);
        ViewCustomerDetailsLaundaryButton.setEnabled(false);
        ViewCustomerDetailsOrderRoomServiceButton.setEnabled(false);
        ViewCustomerDetailsOrderPackageButton.setEnabled(true);
        ViewCustomerDetailsOrderPackageButton.setForeground(new Color (255,153,204));
    }//GEN-LAST:event_PackageReservationjButtonActionPerformed

    private void laundryOrdersjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laundryOrdersjButtonActionPerformed
        Roompanelshift(CustomerButton.getText());
        ViewCustomerDetailsOrderPackageButton.setEnabled(false);
        ViewCustomerDetailsLaundaryButton.setEnabled(true);
        ViewCustomerDetailsOrderRoomServiceButton.setEnabled(false);
        ViewCustomerDetailsOrderRoomButton.setEnabled(false);
        CustomerDetailsTabbedPane.setSelectedIndex(0);
        CustomerDetailsTabbedPane.setEnabledAt(1,false);
        CustomerDetailsTabbedPane.setEnabledAt(0,true);
        ViewCustomerDetailsLaundaryButton.setForeground(new Color (255,153,204));
        AddLaundaryDetailsSearchButton.setEnabled(false);
        LaundryOrdersjTable.setVisible(false);
        TotalLAundryjTextField.setVisible(false);
        LaundryCalculateTotjButton.setEnabled(false);
        LaundryAddjButton.setEnabled(false);
        LaundryDeletejButton.setEnabled(false);
    }//GEN-LAST:event_laundryOrdersjButtonActionPerformed

    private void RoomSErviceOrdersjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomSErviceOrdersjButtonActionPerformed
        Roompanelshift(CustomerButton.getText());
        ViewCustomerDetailsOrderPackageButton.setEnabled(false);
        ViewCustomerDetailsLaundaryButton.setEnabled(false);
        ViewCustomerDetailsOrderRoomServiceButton.setEnabled(true);
        ViewCustomerDetailsOrderRoomButton.setEnabled(false);
        CustomerDetailsTabbedPane.setSelectedIndex(0);
        CustomerDetailsTabbedPane.setEnabledAt(1,false);
        CustomerDetailsTabbedPane.setEnabledAt(0,true);
        RoomServiceTabbedPane.setEnabledAt(1,false);
        RoomServiceTabbedPane.setEnabledAt(0,true);
        ViewCustomerDetailsOrderRoomServiceButton.setForeground(new Color (255,153,204));
    }//GEN-LAST:event_RoomSErviceOrdersjButtonActionPerformed

    private void ReserveRoomjButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReserveRoomjButtonMouseEntered
        ReserveRoomjButton.setForeground(new Color(255,153,204));
    }//GEN-LAST:event_ReserveRoomjButtonMouseEntered

    private void PackageReservationjButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PackageReservationjButtonMouseEntered
       PackageReservationjButton.setForeground(new Color(255,153,204));
    }//GEN-LAST:event_PackageReservationjButtonMouseEntered

    private void ReserveRoomjButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReserveRoomjButtonMouseExited
        ReserveRoomjButton.setForeground(new Color(102,153,255));// TODO add your handling code here:
    }//GEN-LAST:event_ReserveRoomjButtonMouseExited

    private void PackageReservationjButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PackageReservationjButtonMouseExited
        PackageReservationjButton.setForeground(new Color(102,153,255));
    }//GEN-LAST:event_PackageReservationjButtonMouseExited

    private void RoomPaymentsjButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomPaymentsjButtonMouseClicked
        RoomPaymentsjButton.setForeground(new Color(255,153,204));
    }//GEN-LAST:event_RoomPaymentsjButtonMouseClicked

    private void ReserveRoomjButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReserveRoomjButtonMouseClicked
       ReserveRoomjButton.setForeground(new Color(255,153,204));
    }//GEN-LAST:event_ReserveRoomjButtonMouseClicked

    private void PackageReservationjButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PackageReservationjButtonMouseClicked
        PackageReservationjButton.setForeground(new Color(255,153,204));
    }//GEN-LAST:event_PackageReservationjButtonMouseClicked

    private void RoomPaymentsjButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomPaymentsjButtonMouseEntered
       RoomPaymentsjButton.setForeground(new Color(255,153,204));
    }//GEN-LAST:event_RoomPaymentsjButtonMouseEntered

    private void RoomPaymentsjButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomPaymentsjButtonMouseExited
        RoomPaymentsjButton.setForeground(new Color(102,153,255));
    }//GEN-LAST:event_RoomPaymentsjButtonMouseExited

    private void laundryOrdersjButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laundryOrdersjButtonMouseClicked
        laundryOrdersjButton.setForeground(new Color(255,153,204));
    }//GEN-LAST:event_laundryOrdersjButtonMouseClicked

    private void laundryOrdersjButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laundryOrdersjButtonMouseEntered
        laundryOrdersjButton.setForeground(new Color(255,153,204));
    }//GEN-LAST:event_laundryOrdersjButtonMouseEntered

    private void laundryOrdersjButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laundryOrdersjButtonMouseExited
        laundryOrdersjButton.setForeground(new Color(102,153,255));
    }//GEN-LAST:event_laundryOrdersjButtonMouseExited

    private void RoomSErviceOrdersjButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomSErviceOrdersjButtonMouseClicked
         RoomSErviceOrdersjButton.setForeground(new Color(255,153,204));
    }//GEN-LAST:event_RoomSErviceOrdersjButtonMouseClicked

    private void RoomSErviceOrdersjButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomSErviceOrdersjButtonMouseEntered
         RoomSErviceOrdersjButton.setForeground(new Color(255,153,204));
    }//GEN-LAST:event_RoomSErviceOrdersjButtonMouseEntered

    private void RoomSErviceOrdersjButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomSErviceOrdersjButtonMouseExited
         RoomSErviceOrdersjButton.setForeground(new Color(102,153,255));
    }//GEN-LAST:event_RoomSErviceOrdersjButtonMouseExited

    private void HomeRoomjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeRoomjButtonActionPerformed
          Roompanelshift(HomeRoomjButton.getText());
          ViewCustomerDetailsResetButton.doClick();
          ViewReservationDetailsCancelButton.doClick();
          ViewCustomerDetailsResetButton1.doClick();
    }//GEN-LAST:event_HomeRoomjButtonActionPerformed

    private void RoomReservationHeadCountTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RoomReservationHeadCountTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomReservationHeadCountTextFieldKeyTyped

    private void WithMealsjRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WithMealsjRadioButtonActionPerformed
        Double Amount=Double.parseDouble(MakeReservationFinalAmountjlabel.getText());
        String headCount=RoomReservationHeadCountTextField.getText();
        if(headCount.isEmpty()){
            RoomReservationHeadCountTextField.setBackground(new Color(255,150,150));
            JOptionPane.showMessageDialog(null, "Enter the head count!");
        }
        else{
            RoomReservationHeadCountTextField.setBackground(new Color(255,255,255));
            int hc=Integer.parseInt(headCount);
            Double finalAmount=(hc*2000)+Amount;
            MakeReservationFinaTotallAmountjlabel.setText(Double.toString(finalAmount));
        }
        
    }//GEN-LAST:event_WithMealsjRadioButtonActionPerformed

    private void WithoutMealsjRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WithoutMealsjRadioButtonActionPerformed
        RoomReservationHeadCountTextField.setBackground(new Color(255,255,255));
        MakeReservationFinaTotallAmountjlabel.setText(MakeReservationFinalAmountjlabel.getText());
    }//GEN-LAST:event_WithoutMealsjRadioButtonActionPerformed

    private void RoomReservationHeadCountTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomReservationHeadCountTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomReservationHeadCountTextFieldActionPerformed

    private void jButtonAddRoomServiceToReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddRoomServiceToReserveActionPerformed
         TableModel model1=CustomizeSelectjTable.getModel();
        int [] indexes= CustomizeSelectjTable.getSelectedRows(); 
        Object row[]=new Object[2];
        DefaultTableModel model2=(DefaultTableModel)CustomizeRequestedjTable.getModel();
        for(int i=0;i<indexes.length;i++)
        {
            row[0]=model1.getValueAt(indexes[i],0);
            row[1]=model1.getValueAt(indexes[i],1);
            model2.addRow(row);
        }
            
    }//GEN-LAST:event_jButtonAddRoomServiceToReserveActionPerformed

    private void jButtonRemoveRoomToReserveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveRoomToReserveActionPerformed
        DefaultTableModel model2=(DefaultTableModel)CustomizeRequestedjTable .getModel();
        try{
            int SelectedRowIndex=CustomizeRequestedjTable.getSelectedRow();
            model2.removeRow(SelectedRowIndex);
        }
        catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButtonRemoveRoomToReserveActionPerformed

    private void jButtonTotalAmountRoomServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTotalAmountRoomServiceActionPerformed
        jTextFieldRoomServiceTotalAmountToPAy.setText(Double.toString(getRoomServiceCustomizedSum()));
    }//GEN-LAST:event_jButtonTotalAmountRoomServiceActionPerformed

    private void AddRoomServiceCustomizedRoomNoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRoomServiceCustomizedRoomNoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddRoomServiceCustomizedRoomNoTextFieldActionPerformed

    private void RoomLaundryjComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RoomLaundryjComboBoxActionPerformed
        String Weight=RoomLaundryjComboBox.getSelectedItem().toString();
        if(Weight.equals("0.5 Kg")){
            AddLaundaryDetailsLaundaryAmountTextField.setText("200.00");
        }
        if(Weight.equals("1 Kg")){
            AddLaundaryDetailsLaundaryAmountTextField.setText("250.00");
        }
        if(Weight.equals("1.5 kg")){
            AddLaundaryDetailsLaundaryAmountTextField.setText("300.00");
        }
        if(Weight.equals("2 kg")){
            AddLaundaryDetailsLaundaryAmountTextField.setText("350.00");
        }
        if(Weight.equals("2.5 kg")){
            AddLaundaryDetailsLaundaryAmountTextField.setText("400.00");    
        }
         if(Weight.equals("3 kg")){
            AddLaundaryDetailsLaundaryAmountTextField.setText("450.00");    
        }
         if(Weight.equals("3.5 kg")){
            AddLaundaryDetailsLaundaryAmountTextField.setText("500.00");    
        }
        if(Weight.equals("4 kg")){
            AddLaundaryDetailsLaundaryAmountTextField.setText("550.00");    
        }
    }//GEN-LAST:event_RoomLaundryjComboBoxActionPerformed

    private void AddCustomizeCanceljButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCustomizeCanceljButtonActionPerformed
            Roompanelshift(CustomerButton.getText());
    }//GEN-LAST:event_AddCustomizeCanceljButtonActionPerformed

    private void AddCustomizedRoomServiceAddjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCustomizedRoomServiceAddjButtonActionPerformed
        SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd");  
        Integer plates = Integer.parseInt(AddCustomizeRoomServiceNoofPlatesTextField.getText());
        String CusNIC=CustomizedroomServicevalueNiC.getText();
        String Date=formatDate.format(AddRoomServiceCustomizedPackagejDateChooser.getDate());
        String roomno=AddRoomServiceCustomizedRoomNoTextField.getText();
        String Amount=jTextFieldRoomServiceTotalAmountToPAy.getText();
        String des="";
        int rowcount=CustomizeRequestedjTable.getRowCount();
        for(int i=0;i<rowcount;i++)
        {
            des=((CustomizeRequestedjTable.getValueAt(i,0).toString())+"," +des);
        }
        String price;
       
        try {
                String q="Insert into room_roomserviceorders(room_roomserviceorders_cusnic,room_roomserviceorders_roomno,room_roomserviceorders_noofplates,room_roomserviceorders_customized,room_roomserviceorders_date,room_roomserviceorders_price,room_roomserviceorders_Description) values ('"+CusNIC+"','"+roomno+"','"+plates+"','Customized'"+",'"+Date+"','"+Amount+"','"+des+"')";
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                RoomServiceTableLoad();
                JOptionPane.showMessageDialog(null,"Succesfully updated","Success Messsage",1);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
    }//GEN-LAST:event_AddCustomizedRoomServiceAddjButtonActionPerformed

    private void AddRoomServiceDetailsBackToPAymentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRoomServiceDetailsBackToPAymentsButtonActionPerformed
      Roompanelshift(RoomPaymentsButton.getText());
       AddRoomPaymentRSamountValueTextFields.setText(RoomServiceTotalAmountjTextField.getText());
    }//GEN-LAST:event_AddRoomServiceDetailsBackToPAymentsButtonActionPerformed

    private void AddReservationCusNICTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddReservationCusNICTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddReservationCusNICTextFieldActionPerformed

    private void AddRoomMainatainanceItemNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRoomMainatainanceItemNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddRoomMainatainanceItemNameTextFieldActionPerformed

    private void AddRoomMainatainanceItemNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddRoomMainatainanceItemNameTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddRoomMainatainanceItemNameTextFieldKeyTyped

    private void AddRoomMainatianaceAvailableQtyTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRoomMainatianaceAvailableQtyTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddRoomMainatianaceAvailableQtyTextFieldActionPerformed

    private void AddRoomMainatianaceAvailableQtyTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddRoomMainatianaceAvailableQtyTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddRoomMainatianaceAvailableQtyTextFieldKeyTyped

    private void AddRoomMainatianaceQtyUsedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRoomMainatianaceQtyUsedTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddRoomMainatianaceQtyUsedTextFieldActionPerformed

    private void AddRoomMainatianaceQtyUsedTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddRoomMainatianaceQtyUsedTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_AddRoomMainatianaceQtyUsedTextFieldKeyTyped

    private void MaintainanceDetailsUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaintainanceDetailsUpdateButtonActionPerformed
        String product=AddRoomMainatainanceItemNameTextField.getText();
        String availQty=AddRoomMainatianaceAvailableQtyTextField.getText();
        String QtyUsed=AddRoomMainatianaceQtyUsedTextField.getText();
        int x = JOptionPane.showConfirmDialog(null, "Are You sure you want to update?");
        if(x==0){
        try{
            String q="update room_roommaintainance set room_roommaintainance_Quantity ='"+availQty+"',room_roommaintainance_Used='"+QtyUsed+"' where room_roommaintainance_product ='"+product+"'";
            pstatement=con.prepareStatement(q);
            pstatement.execute();
            RoomMaintainanceTableLoad();
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
        }
        }
        if(x==1){}
        if(x==2){}
    }//GEN-LAST:event_MaintainanceDetailsUpdateButtonActionPerformed

    private void MaintainanceDetailsSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaintainanceDetailsSubmitButtonActionPerformed
        String product=AddRoomMainatainanceItemNameTextField.getText();
        String availQty=AddRoomMainatianaceAvailableQtyTextField.getText();
        String QtyUsed=AddRoomMainatianaceQtyUsedTextField.getText();
         try {
                String q="Insert into room_roommaintainance(room_roommaintainance_product,room_roommaintainance_Quantity,room_roommaintainance_Used) values ('"+ product+"','"+availQty+"','"+QtyUsed+"')";
                pstatement=con.prepareStatement(q);
                pstatement.execute();
                System.out.println(q);
                RoomMaintainanceTableLoad();
                JOptionPane.showMessageDialog(null,"Succesfully updated","Success Messsage",1);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
            }
    }//GEN-LAST:event_MaintainanceDetailsSubmitButtonActionPerformed

    private void RoomMaintainancejTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomMaintainancejTableMouseClicked
        int r = RoomMaintainancejTable.getSelectedRow();
        String product=RoomMaintainancejTable.getValueAt(r, 0).toString();
        String availableQty=RoomMaintainancejTable.getValueAt(r, 1).toString();
        String usedQty=RoomMaintainancejTable.getValueAt(r, 2).toString();
        
        AddRoomMainatainanceItemNameTextField.setText(product);
        AddRoomMainatianaceAvailableQtyTextField.setText(availableQty);
        AddRoomMainatianaceQtyUsedTextField.setText(usedQty);
    }//GEN-LAST:event_RoomMaintainancejTableMouseClicked

    private void RoomDescriptionTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RoomDescriptionTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_RoomDescriptionTableMouseClicked

    private void heasderLogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heasderLogoutButtonActionPerformed
        int result=JOptionPane.showConfirmDialog(RoomTogglePanel,"Do you want to Log Out from the system?","Logout Confirmation",0);
        if(result==0){
            System_Login login=new System_Login();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_heasderLogoutButtonActionPerformed

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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Room_Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Room_Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Room_Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Room_Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Room_Manager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddCustomizeCanceljButton;
    private javax.swing.JTextField AddCustomizeRoomServiceNoofPlatesTextField;
    private javax.swing.JLabel AddCustomizeRoomServiceNoofPlatesjLabel;
    private javax.swing.JPanel AddCustomizedPackagesPanel;
    private javax.swing.JButton AddCustomizedRoomServiceAddjButton;
    private javax.swing.JLabel AddLaundaryDatejLabel;
    private javax.swing.JButton AddLaundaryDetailsBackToPAymentsButton;
    private javax.swing.JButton AddLaundaryDetailsBackutton;
    private javax.swing.JTextField AddLaundaryDetailsLaundaryAmountTextField;
    private javax.swing.JLabel AddLaundaryDetailsLaundaryCustomerNamejLabel;
    private javax.swing.JTextField AddLaundaryDetailsLaundaryDescTextField;
    private javax.swing.JLabel AddLaundaryDetailsLaundaryDescjLabel;
    private javax.swing.JLabel AddLaundaryDetailsLaundaryWeightjLabel;
    private javax.swing.JLabel AddLaundaryDetailsLaundaryamountjLabel;
    private javax.swing.JTextField AddLaundaryDetailsLaundarycusnameTextField;
    private javax.swing.JTextField AddLaundaryDetailsLaundarytelnoTextField;
    private javax.swing.JLabel AddLaundaryDetailsNICValuejLabel;
    private javax.swing.JLabel AddLaundaryDetailsNICjLabel;
    private javax.swing.JButton AddLaundaryDetailsSearchButton;
    private javax.swing.JButton AddLaundaryDetailsSubmitButton;
    private javax.swing.JTable AddLaundaryDetailsTable;
    private javax.swing.JLabel AddLaundaryDetailstelnojLabel;
    private com.toedter.calendar.JDateChooser AddLaundaryjDateChooser;
    private javax.swing.JTextField AddPAckageReservationCheckInTextField;
    private javax.swing.JTextField AddPAckageReservationCheckOutTextField;
    private javax.swing.JTextField AddPAckageReservationCusNICTextField;
    private javax.swing.JTextField AddPAckageReservationCusNameTextField;
    private javax.swing.JLabel AddPAckageReservationCusNamejLabel;
    private javax.swing.JLabel AddPAckageReservationDatejLabel1;
    private javax.swing.JButton AddPAckageReservationDetailsBackButton;
    private javax.swing.JLabel AddPAckageReservationDetailsCustomerIDLabel;
    private javax.swing.JButton AddPAckageReservationDetailsSubmitButton;
    private javax.swing.JLabel AddPaymentCheckInjLabel;
    private javax.swing.JLabel AddPaymentRsIDjLabel;
    private javax.swing.JComboBox AddPaymentsCODjComboBox;
    private javax.swing.JLabel AddPaymentsCheckOutjLabel;
    private javax.swing.JScrollPane AddPaymentsDescjScrollPane;
    private javax.swing.JButton AddPaymentsDetailsAddToTableButton;
    private javax.swing.JButton AddPaymentsDetailsBackButton;
    private javax.swing.JButton AddPaymentsDetailsCalculatorButton;
    private javax.swing.JButton AddPaymentsDetailsCheckRSButton;
    private javax.swing.JButton AddPaymentsDetailsLaundryRSButton;
    private javax.swing.JButton AddPaymentsDetailsLaundryRSButton1;
    private javax.swing.JButton AddPaymentsDetailsSubmitButton;
    private javax.swing.JLabel AddPaymentsRIDValuejLabel;
    private javax.swing.JScrollPane AddPaymentsdetailsjScrollPane1;
    private javax.swing.JButton AddPrintPaymentDetailsBackButton;
    private javax.swing.JTextField AddReservationCheckInTextField;
    private javax.swing.JTextField AddReservationCheckOutTextField;
    private javax.swing.JTextField AddReservationCusNICTextField;
    private javax.swing.JTextField AddReservationCusNameTextField;
    private javax.swing.JLabel AddReservationCusNamejLabel;
    private javax.swing.JLabel AddReservationDatejLabel;
    private javax.swing.JButton AddReservationDetailsBackButton;
    private javax.swing.JLabel AddReservationDetailsCustomerIDLabel;
    private javax.swing.JButton AddReservationDetailsSubmitButton;
    private javax.swing.JLabel AddReservePackageAmountjLabel1;
    private javax.swing.JTextField AddRoomMainatainanceItemNameTextField;
    private javax.swing.JTextField AddRoomMainatianaceAvailableQtyTextField;
    private javax.swing.JTextField AddRoomMainatianaceQtyUsedTextField;
    private javax.swing.JLabel AddRoomMainatinanceAvailabelQtyLabel;
    private javax.swing.JLabel AddRoomMainatinanceItemNameLabel;
    private javax.swing.JLabel AddRoomMainatinanceQtyUsedLabel;
    private javax.swing.JLabel AddRoomPaymentAmountjlabel;
    private javax.swing.JLabel AddRoomPaymentCIjLabel;
    private javax.swing.JLabel AddRoomPaymentCOjlabel;
    private javax.swing.JLabel AddRoomPaymentCusNICvaluejlabel;
    private javax.swing.JLabel AddRoomPaymentDescjlabel;
    private com.toedter.calendar.JDateChooser AddRoomPaymentDueDatejDateChooser;
    private javax.swing.JLabel AddRoomPaymentDueDatejLabel;
    private javax.swing.JTextField AddRoomPaymentLaValueTextFields;
    private javax.swing.JLabel AddRoomPaymentMethodjlabel;
    private javax.swing.JLabel AddRoomPaymentRSAmountjlabel;
    private javax.swing.JTextField AddRoomPaymentRSamountValueTextFields;
    private javax.swing.JTextField AddRoomPaymentTextFields;
    private javax.swing.JTextField AddRoomPaymentTotalValueTextFields;
    private javax.swing.JLabel AddRoomPaymentcodjlabel;
    private javax.swing.JLabel AddRoomPaymentcusNICjLabel;
    private javax.swing.JTextArea AddRoomPaymentdescDesc;
    private javax.swing.JComboBox AddRoomPaymentjComboBox;
    private com.toedter.calendar.JDateChooser AddRoomServiceCustomizedPackagejDateChooser;
    private javax.swing.JTextField AddRoomServiceCustomizedRoomNoTextField;
    private javax.swing.JButton AddRoomServiceDeletejButton;
    private javax.swing.JButton AddRoomServiceDetailsBackToPAymentsButton;
    private javax.swing.JButton AddRoomServicejButton;
    private javax.swing.JLabel AddRooomServiceRoomNojLabel1;
    private javax.swing.JButton CAlculateRoomServiceTotaljButton;
    private javax.swing.JLabel CheckoutjLabel;
    private javax.swing.JLabel CheckoutjLabel1;
    private javax.swing.JLabel CheckoutjLabel2;
    private javax.swing.JButton CustomerButton;
    private javax.swing.JPanel CustomerDetailsPanel;
    private javax.swing.JTabbedPane CustomerDetailsTabbedPane;
    private javax.swing.JTable CustomizeRequestedjTable;
    private javax.swing.JTable CustomizeSelectjTable;
    private javax.swing.JLabel CustomizedroomServiceNiCjLabel;
    private javax.swing.JLabel CustomizedroomServicevalueNiC;
    private javax.swing.JButton EditCustomerDetailsDeleteButton;
    private javax.swing.JLabel EditCustomerDetailsIDvalueLabel;
    private javax.swing.JLabel EditCustomerDetailsLabel;
    private javax.swing.JButton EditCustomerDetailsResetButton;
    private javax.swing.JScrollPane EditCustomerDetailsScrollPane;
    private javax.swing.JScrollPane EditCustomerDetailsScrollPane1;
    private javax.swing.JButton EditCustomerDetailsSearchButton;
    private javax.swing.JTable EditCustomerDetailsTable;
    private javax.swing.JButton EditCustomerDetailsUpdateButton;
    private javax.swing.JPanel EditCustomerPanel;
    private javax.swing.JLabel EditPAckageDetailsNoofDetailsjLabel;
    private javax.swing.JLabel EditPAckageDetailsNoofDetailsjLabel1;
    private javax.swing.JTextField EditPackageDetailsAmountTextField;
    private javax.swing.JButton EditPackageDetailsDeleteButton;
    private javax.swing.JTextField EditPackageDetailsNoofAdultsTextField;
    private javax.swing.JTextField EditPackageDetailsNoofChildrenTextField;
    private javax.swing.JLabel EditPackageDetailsNoofchildrenjLabel;
    private javax.swing.JLabel EditPackageDetailsNoofchildrenjLabel1;
    private javax.swing.JTextField EditPackageDetailsPackDetailsTextField;
    private javax.swing.JLabel EditPackageDetailsPackagestatusLabel;
    private javax.swing.JPanel EditPackageDetailsPanel;
    private javax.swing.JLabel EditPackageDetailsRoomNoLabel;
    private javax.swing.JTextField EditPackageDetailsRoomNoValueTextField;
    private javax.swing.JButton EditPackageDetailsSubmitButton;
    private javax.swing.JTable EditPackageDetailsTable;
    private javax.swing.JButton EditPackageDetailsUpdateButton;
    private javax.swing.JLabel EditPackagePackageAmountLabel;
    private javax.swing.JLabel EditPackagePackageDetailsLabel;
    private javax.swing.JLabel EditPackagePackageIDLabel;
    private javax.swing.JLabel EditRoomCustomerAddressLabel;
    private javax.swing.JTextField EditRoomCustomerAddressTextField;
    private javax.swing.JLabel EditRoomCustomerNICLabell;
    private javax.swing.JTextField EditRoomCustomerNICTextField;
    private javax.swing.JLabel EditRoomCustomerNameLabel;
    private javax.swing.JTextField EditRoomCustomerNameTextField;
    private javax.swing.JTextField EditRoomCustomerTElNoTextField;
    private javax.swing.JLabel EditRoomCustomerTelNoLabel;
    private javax.swing.JLabel EditRoomDetailsAmountLabel;
    private javax.swing.JTextField EditRoomDetailsAmountTextField;
    private javax.swing.JTextField EditRoomDetailsNoofAdultsTextField;
    private javax.swing.JTextField EditRoomDetailsNoofChildrenTextField;
    private javax.swing.JLabel EditRoomDetailsNoofchildrenjLabel;
    private javax.swing.JPanel EditRoomDetailsPanel;
    private javax.swing.JLabel EditRoomDetailsRoomNoLabel;
    private javax.swing.JLabel EditRoomDetailsRoomNovalueLabel;
    private javax.swing.JLabel EditRoomDetailsRoomTypejLabel;
    private javax.swing.JButton EditRoomDetailsSearchButton;
    private javax.swing.JTextField EditRoomDetailsSearchValueTextField;
    private javax.swing.JButton EditRoomDetailsSubmitButton;
    private javax.swing.JTable EditRoomDetailsTable;
    private javax.swing.JButton EditRoomDetailsUpdateButton;
    private javax.swing.JComboBox<String> EditRoomDetailsjComboBox;
    private javax.swing.JLabel EditRoomDetailsjLabel;
    private javax.swing.JLabel EditcustomerDetailsSearchLabel;
    private javax.swing.JTextField EditcustomerDetailssearchTextField;
    private javax.swing.JLabel EditpackagespackidValueLabel;
    private javax.swing.JLabel HeaderLogoLabel;
    private javax.swing.JButton HomeRoomjButton;
    private javax.swing.JButton LaundryAddjButton;
    private javax.swing.JButton LaundryCalculateTotjButton;
    private javax.swing.JButton LaundryDeletejButton;
    private javax.swing.JTable LaundryOrdersjTable;
    private javax.swing.JPanel MainViewDetailsPanel;
    private javax.swing.JButton MaintainanceButton;
    private javax.swing.JPanel MaintainanceDetailsPanel;
    private javax.swing.JButton MaintainanceDetailsSubmitButton;
    private javax.swing.JButton MaintainanceDetailsUpdateButton;
    private javax.swing.JScrollPane MaintainanceScrollPane;
    private com.toedter.calendar.JDateChooser MakePAckageReservationjDateChooser;
    private javax.swing.JLabel MakeReservationFinaTotallAmountjlabel;
    private javax.swing.JLabel MakeReservationFinalAmountjlabel;
    private com.toedter.calendar.JDateChooser MakeReservationjDateChooser;
    private javax.swing.JLabel MakepaymentsLaundryAmountjLabel;
    private javax.swing.JLabel MakepaymentsTotalAmountjLabel;
    private com.toedter.calendar.JDateChooser PackCheckInjDateChooser;
    private com.toedter.calendar.JDateChooser PackCheckoutjDateChooser;
    private javax.swing.JLabel PackCheckoutjLabel3;
    private javax.swing.JPanel PackageDetailsPanel;
    private javax.swing.JButton PackageReservationjButton;
    private javax.swing.JTextField PackageRoomCountjTextField;
    private javax.swing.JTabbedPane PackageRoomTabbedPane;
    private javax.swing.JButton PackagesButton;
    private javax.swing.JTabbedPane PaymentDetailsjTabbedPane;
    private javax.swing.JPanel PaymentsDetailsPanel;
    private javax.swing.JButton ReservationButton;
    private javax.swing.JPanel ReservationDetailsPanel;
    private javax.swing.JTabbedPane ReservationDetailsTabbedPane;
    private javax.swing.JButton ReserveRoomjButton;
    private javax.swing.JPanel RoomAddPaymentsPanel;
    private javax.swing.JButton RoomButton;
    private javax.swing.JPanel RoomButtonPanel;
    private com.toedter.calendar.JDateChooser RoomCheckInjDateChooser;
    private com.toedter.calendar.JDateChooser RoomCheckoutjDateChooser;
    private javax.swing.JTextField RoomCountjTextField;
    private javax.swing.JLabel RoomDatejLabel;
    private javax.swing.JTable RoomDescriptionTable;
    private javax.swing.JPanel RoomDescriptionjPanel;
    private javax.swing.JPanel RoomDetailsPanel;
    private javax.swing.JTabbedPane RoomDetailsTabbedPane;
    private javax.swing.JPanel RoomHeaderPanel;
    private javax.swing.JButton RoomLaundaryButton;
    private javax.swing.JPanel RoomLaundaryPanel;
    private javax.swing.JComboBox<String> RoomLaundryjComboBox;
    private javax.swing.JTable RoomMaintainancejTable;
    private javax.swing.JPanel RoomManagerPanel;
    private javax.swing.JButton RoomPaymentsButton;
    private javax.swing.JButton RoomPaymentsjButton;
    private javax.swing.JButton RoomReportsButton;
    private javax.swing.JPanel RoomReportsPanel;
    private javax.swing.JTextField RoomReservationHeadCountTextField;
    private javax.swing.JLabel RoomReservationHeadCountjLabel;
    private javax.swing.JButton RoomSErviceOrdersjButton;
    private javax.swing.JButton RoomServiceButton;
    private javax.swing.JLabel RoomServiceDatejLabel1;
    private javax.swing.JScrollPane RoomServiceOrderDetailsScrollPane;
    private javax.swing.JTable RoomServiceOrderrDetailsTable;
    private javax.swing.JButton RoomServiceOrdersSearchjButton;
    private javax.swing.JLabel RoomServiceOrdersjLabel;
    private javax.swing.JPanel RoomServiceOrdersjPanel;
    private javax.swing.JPanel RoomServicePanel;
    private javax.swing.JTextField RoomServiceSearchTextField;
    private javax.swing.JTabbedPane RoomServiceTabbedPane;
    private javax.swing.JTextField RoomServiceTotalAmountjTextField;
    private javax.swing.JLabel RoomServiceTotaljLabel;
    private javax.swing.JTable RoomServicejTable;
    private javax.swing.JButton RoomStockButton;
    private javax.swing.JLabel RoomStockDepartmentjLabel;
    private javax.swing.JTextField RoomStockDepartmentjTextField;
    private javax.swing.JComboBox<String> RoomStockItemCategoryjComboBox;
    private javax.swing.JLabel RoomStockItemCategoryjLabel;
    private javax.swing.JLabel RoomStockItemIDjLabel;
    private javax.swing.JComboBox<String> RoomStockItemNamejComboBox;
    private javax.swing.JLabel RoomStockItemNamejLabel;
    private javax.swing.JLabel RoomStockOrderqtyjLabel;
    private javax.swing.JButton RoomStockRequestOrderButton;
    private javax.swing.JLabel RoomStockUnitsjLabel;
    private javax.swing.JComboBox<String> RoomStockjComboBox;
    private javax.swing.JTextField RoomStockqtyjTextField;
    private javax.swing.JTextField RoomStockunitsjTextField;
    private javax.swing.JLabel RoomTimejLabel;
    private javax.swing.JPanel RoomTogglePanel;
    private javax.swing.JLabel RoomUpdatePaymentsNICjLabel;
    private javax.swing.JTextField RoomUpdatePaymentsNICvalueTextfield;
    private javax.swing.JButton RoomstockClearButton;
    private javax.swing.JPanel RoomupdatePaymentsPanel;
    private javax.swing.JPanel StockDetailsPanel;
    private javax.swing.JTextField TotalLAundryjTextField;
    private javax.swing.JScrollPane UpdatePaneDescjScrollPane;
    private javax.swing.JButton UpdatePaymentDetailsUpdateButton;
    private javax.swing.JButton UpdatePaymentsDetailsSearchButton;
    private javax.swing.JTextField UpdateRoomPaymentAmountTextFields;
    private javax.swing.JLabel UpdateRoomPaymentAmountjLabel;
    private javax.swing.JComboBox UpdateRoomPaymentCODjComboBox;
    private javax.swing.JLabel UpdateRoomPaymentCODjLabel;
    private javax.swing.JLabel UpdateRoomPaymentDescLabel;
    private javax.swing.JTextField UpdateRoomPaymentDueDateTextFeilds;
    private javax.swing.JLabel UpdateRoomPaymentDueDatejlabel;
    private javax.swing.JComboBox UpdateRoomPaymentPaymentMethodjComboBox;
    private javax.swing.JLabel UpdateRoomPaymentPaymentMethodjLabel;
    private javax.swing.JTextArea UpdateRoomPaymenttDescription;
    private javax.swing.JTable UpdateRoompaymentDetailsjTable;
    private javax.swing.JPanel UpdateValuesgetValuejPanel;
    private javax.swing.JButton ViewCustomerDetailsLaundaryButton;
    private javax.swing.JButton ViewCustomerDetailsOrderPackageButton;
    private javax.swing.JButton ViewCustomerDetailsOrderRoomButton;
    private javax.swing.JButton ViewCustomerDetailsOrderRoomServiceButton;
    private javax.swing.JButton ViewCustomerDetailsResetButton;
    private javax.swing.JButton ViewCustomerDetailsResetButton1;
    private javax.swing.JButton ViewCustomerDetailsResetButton3;
    private javax.swing.JButton ViewCustomerDetailsResetButton4;
    private javax.swing.JScrollPane ViewCustomerDetailsScrollPane;
    private javax.swing.JButton ViewCustomerDetailsSearchButton;
    private javax.swing.JButton ViewCustomerDetailsSearchButton2;
    private javax.swing.JTable ViewCustomerDetailsTable;
    private javax.swing.JButton ViewPAckageReservationDetailsCancelButton;
    private javax.swing.JLabel ViewPackDetailsCheckInjLabel3;
    private javax.swing.JTextField ViewPackageCustomerNICTextField;
    private javax.swing.JTextField ViewPackageDetailsNoofAdultsTextField;
    private javax.swing.JTextField ViewPackageDetailsNoofChildrenTextField;
    private javax.swing.JPanel ViewPackageDetailsPanel;
    private javax.swing.JButton ViewPackageDetailsReservePackageButton;
    private javax.swing.JButton ViewPackageDetailsResetButton;
    private javax.swing.JButton ViewPackageDetailsResetButton1;
    private javax.swing.JScrollPane ViewPackageDetailsScrollPane;
    private javax.swing.JScrollPane ViewPackageDetailsScrollPane1;
    private javax.swing.JButton ViewPackageDetailsSubmitButton;
    private javax.swing.JTable ViewPackageDetailsTable;
    private javax.swing.JButton ViewPackagesDetailsNextButton;
    private javax.swing.JButton ViewPackagesDetailsNextButton1;
    private javax.swing.JLabel ViewReservatiomDetailsSearchvaljLabel;
    private javax.swing.JButton ViewReservationDetailsCancelButton;
    private javax.swing.JButton ViewReservationDetailsPaymentsButton;
    private javax.swing.JButton ViewReservationDetailsSearchButton;
    private javax.swing.JTable ViewReservationDetailsTable;
    private javax.swing.JPanel ViewReservationDetailsjPanel;
    private javax.swing.JLabel ViewRoomCustomerAddressLabel;
    private javax.swing.JTextField ViewRoomCustomerAddressTextField;
    private javax.swing.JLabel ViewRoomCustomerNICLabel;
    private javax.swing.JLabel ViewRoomCustomerNICLabel1;
    private javax.swing.JTextField ViewRoomCustomerNICTextField;
    private javax.swing.JLabel ViewRoomCustomerNameLabel;
    private javax.swing.JTextField ViewRoomCustomerNameTextField;
    private javax.swing.JLabel ViewRoomCustomerTelNoLabel;
    private javax.swing.JTextField ViewRoomCustomerTelNoTextField;
    private javax.swing.JButton ViewRoomDetailsCheckAvailabiltyButton;
    private javax.swing.JLabel ViewRoomDetailsCheckInjLabel;
    private javax.swing.JLabel ViewRoomDetailsCheckInjLabel1;
    private javax.swing.JLabel ViewRoomDetailsCheckInjLabel2;
    private javax.swing.JLabel ViewRoomDetailsCusNICLabel;
    private javax.swing.JLabel ViewRoomDetailsCusnicValuejLabel;
    private javax.swing.JButton ViewRoomDetailsNextButton1;
    private javax.swing.JButton ViewRoomDetailsNextButton2;
    private javax.swing.JTextField ViewRoomDetailsNoChildrenTextField;
    private javax.swing.JTextField ViewRoomDetailsNoofAdultsTextField;
    private javax.swing.JLabel ViewRoomDetailsNoofAdultsjLabel;
    private javax.swing.JLabel ViewRoomDetailsNoofChildrenjLabel;
    private javax.swing.JPanel ViewRoomDetailsPanel;
    private javax.swing.JButton ViewRoomDetailsReserveRoomButton;
    private javax.swing.JLabel ViewRoomDetailsRoomTypejLabel;
    private javax.swing.JScrollPane ViewRoomDetailsScrollPane;
    private javax.swing.JScrollPane ViewRoomDetailsScrollPane1;
    private javax.swing.JScrollPane ViewRoomDetailsScrollPane2;
    private javax.swing.JComboBox<String> ViewRoomDetailsjComboBox;
    private javax.swing.JTable ViewRoomDetailsjTable;
    private javax.swing.JComboBox<String> ViewRoomPackageDetailsjComboBox;
    private javax.swing.JLabel WelcomejLabel;
    private javax.swing.JRadioButton WithMealsjRadioButton;
    private javax.swing.JRadioButton WithoutMealsjRadioButton;
    private javax.swing.JButton heasderLogoutButton;
    private javax.swing.JButton jButtonAddPackageDetailsToReserve;
    private javax.swing.JButton jButtonAddRoomDetailsToReserve;
    private javax.swing.JButton jButtonAddRoomServiceToReserve;
    private javax.swing.JButton jButtonRemovePackageReserves1;
    private javax.swing.JButton jButtonRemoveRoomReserves;
    private javax.swing.JButton jButtonRemoveRoomToReserve;
    private javax.swing.JButton jButtonTotalAmount;
    private javax.swing.JButton jButtonTotalAmountPackage;
    private javax.swing.JButton jButtonTotalAmountRoomService;
    private javax.swing.JButton jButtonTotalPackageReserves;
    private javax.swing.JDesktopPane jDesktopPaneRoom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelMakeReservationRoomCount;
    private javax.swing.JLabel jLabelPAckageRoomCount;
    private javax.swing.JLabel jLabelPackAmount;
    private javax.swing.JLabel jLabelRoomReseravtionAmount;
    private javax.swing.JLabel jLabelRoomReseravtionTotalAmount;
    private javax.swing.JLabel jLabelTotalAmount;
    private javax.swing.JLabel jLabelTotalAmountRoom;
    private javax.swing.JLabel jLabelTotalAmountRoomService;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTableToPrintPaymentDetails;
    private javax.swing.JTable jTableToReservePackage;
    private javax.swing.JTable jTableToReserveRoom;
    private javax.swing.JTextField jTextFieldRoomServiceTotalAmountToPAy;
    private javax.swing.JTextField jTextFieldTotalAmountToPAy;
    private javax.swing.JTextField jTextFieldTotalAmountToPAypackage;
    private javax.swing.JButton laundryOrdersjButton;
    private javax.swing.JPanel makePackageReservationPanel;
    private javax.swing.JPanel makeReservationPanel;
    private javax.swing.JPanel viewCustomerpanel;
    private javax.swing.JScrollPane viewReservationDetailsScrollPane;
    private javax.swing.JTextField viewReservationDetailssearchTextField;
    private javax.swing.JLabel viewRoomDetailsSearchLabel1;
    private javax.swing.JLabel viewcustomerDetailsSearchLabel;
    private javax.swing.JTextField viewcustomerDetailssearchTextField;
    // End of variables declaration//GEN-END:variables
}
