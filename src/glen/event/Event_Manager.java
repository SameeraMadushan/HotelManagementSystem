/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package glen.event;

import glen.DBconnect;
import glen.System_Login;
import java.awt.Color;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import static java.time.Instant.now;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Buddhini
 */
public class Event_Manager extends javax.swing.JFrame {

    //variable for connect database
    Connection conn = null;
    PreparedStatement pstatement = null;
    ResultSet result = null;

    /**
     * Creates new form main
     */
    public Event_Manager() {
        initComponents();
        conn = DBconnect.ConnectDB();
        jDesktopPaneEvent.setVisible(true);
        togglePanels.setVisible(true);
        eventPanel.setVisible(false);
        customerPanel.setVisible(false);
        foodPackagesPanel.setVisible(false);
        bookingsPanel.setVisible(false);
        decorationsPanel.setVisible(false);
        paymentPanel.setVisible(false);
        reportsPanel.setVisible(false);
        eventUserLogPanel.setVisible(false);
        eventSettingsPanel.setVisible(false);
        date();
        
        
 //----------------------------------------------------------Date-----------------------------------------//       
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH); 
        int date = now.get(Calendar.DATE); 
        int year = now.get(Calendar.YEAR);
        String Date = date + " - " + month + " - " + year;
        jLabel3SystemDate.setText(Date);
        
 //----------------------------------------------------------Time-----------------------------------------//

        int second=now.get(Calendar.SECOND);
        int min=now.get(Calendar.MINUTE);
        int hr=now.get(Calendar.HOUR);
        String t=hr + " : " + min + " : " + second;
        jLabel3SystemTime.setText(t);
       
        

        //call tableload function
        tableloadevent();
        tableloadcustomer();
        tableLoadDecoration();
        tableLoadEventBooking();
        tableLoadPayment();
        tableLoadHallName();
        
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
                jLabel3SystemTime.setText(t);
                            try {
                                sleep(1000);
                            } catch (InterruptedException ex) {
                                java.util.logging.Logger.getLogger(Event_Manager.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                };
               clock.start(); 
    }
    //------------------------------------------------------------Table Load methods--------------------------------------------------------------------//
    public void tableloadevent() {
        try {
            String sql = "SELECT event_id as 'Event ID',event_type as 'Event Type',event_duration as 'Event Duration',event_entertainment as 'Event Entertainment',event_date as 'Event Date',event_time as 'Event Time',no_of_guests as 'No of Guests',hall_name as 'Hall Name',hall_price as 'Hall Price' FROM event";
            pstatement = conn.prepareStatement(sql);
            result = pstatement.executeQuery();
            AddEventTable.setModel(DbUtils.resultSetToTableModel(result));
            String sql2 = "SELECT event_id as 'Event ID',event_type as 'Event Type',event_duration as 'Event Duration',event_entertainment as 'Event Entertainment',event_date as 'Event Date',event_time as 'Event Time',no_of_guests as 'No of Guests',hall_name as 'Hall Name',hall_price as 'Hall Price' FROM event WHERE event_id like '%"+txtViewEventID.getText()+"%'";
            pstatement = conn.prepareStatement(sql2);
            result = pstatement.executeQuery();
            viewEventTable.setModel(DbUtils.resultSetToTableModel(result));

        } catch (Exception e) {
           // e.printStackTrace();
            System.out.println(e);
        }
    }

    public void tableloadcustomer() {
        try {
            
            String sql = "SELECT cus_event_id as 'Event ID',customer_name as 'Customer Name',customer_NIC as 'Customer NIC',customer_address as 'Customer Address',customer_tel as 'Customer Contact No' FROM event_customer_info ";
            System.out.println(sql);
            pstatement = conn.prepareStatement(sql);
            result = pstatement.executeQuery();
            addCustomerTable.setModel(DbUtils.resultSetToTableModel(result));
            
            String sql2 = "SELECT cus_event_id as 'Event ID',customer_name as 'Customer Name',customer_NIC as 'Customer NIC',customer_address as 'Customer Address',customer_tel as 'Customer Contact No' FROM event_customer_info WHERE customer_NIC like '%"+txtViewCustomerEnterCustomerNIC.getText()+"%'";
            pstatement = conn.prepareStatement(sql2);
            result = pstatement.executeQuery();
            viewCustomerTable.setModel(DbUtils.resultSetToTableModel(result));
            
            String sql3 = "SELECT cus_event_id as 'Event ID',customer_name as 'Customer Name',customer_NIC as 'Customer NIC',customer_address as 'Customer Address',customer_tel as 'Customer Contact No' FROM event_customer_info ";
            System.out.println(sql3);
            pstatement = conn.prepareStatement(sql3);
            result = pstatement.executeQuery();
            updateCustomerTable.setModel(DbUtils.resultSetToTableModel(result));
            

        } catch (Exception e) {
                System.out.println(e);
            //e.printStackTrace();
        }
    }
    
    public void tableLoadDecoration(){
        try {
 
            String sql="SELECT decor_id as 'Decor ID',decor_event_id as 'Event ID',decor_customer_NIC as 'Customer NIC',theme_color as 'Theme Color',decor_description as 'Description',decor_special_requirements as 'Special Req',decor_price as 'Decor Price',decor_special_req_price as 'Special Req Price' FROM event_decoration ";
            pstatement = conn.prepareStatement(sql);
            result = pstatement.executeQuery();
            addDecorTable.setModel(DbUtils.resultSetToTableModel(result));
            
            String sql2="SELECT theme_color as 'Theme Color',decor_description as 'Description',table_decoration as 'Table Decor',wall_decoration as 'Wall Decor',flower_decoration as 'Flower Decor',disco_light as 'Disco Light',entrance_arch as 'Entrance arch',decor_special_requirements as 'Special Req',decor_special_req_price as 'Special Req Price',decor_price as 'Decor Price' FROM event_decoration ";
            pstatement = conn.prepareStatement(sql2);
            result = pstatement.executeQuery();
            updateDecorTable.setModel(DbUtils.resultSetToTableModel(result));
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void tableLoadEventBooking(){
        try {
            
            String sql = "SELECT booking_event_id as 'Event ID',booking_customer_NIC as 'Customer NIC',booking_date as 'Booking date',booking_status as 'Status',booking_time as 'Boooking Time'FROM event_booking";
            pstatement = conn.prepareStatement(sql);
            result = pstatement.executeQuery();
            
            eventBookingTable.setModel(DbUtils.resultSetToTableModel(result));
            
        } catch (Exception e) {
        }
    }
    
    public void tableLoadPayment(){
        try {
            String sql="SELECT event_payment_event_ID as 'Event ID',event_payment_customer_NIC as 'Customer NIC',event_payment_method as 'Payment Methods',event_payment_amount as 'Payment Amount',event_Credit/Debit as 'Credit/debit',event_payment_due_date as 'Due Date',event_payment_description as 'Description' FROM event_payment";
            pstatement = conn.prepareStatement(sql);
            result = pstatement.executeQuery();
            addPayTable.setModel(DbUtils.resultSetToTableModel(result));
            
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            String payEventId=updatePaymentEventIDlbl.getText();
            String payCusNIC=txtUpdatePaymentCusNIC.getText();
            String payMethod=jComboUpdatePayMethod.getSelectedItem().toString();
            String payAmount=txtUpdatePayAmount.getText();
            String payDC=jComboUpdateCD.getSelectedItem().toString();
            String payDueDate=formatDate.format(jDateChooser2UpdatePayDate.getDate());;
            String payDes=txtAreaUpdatePaymentDescription.getText();
            String sql2="INSERT INTO event_payment (event_payment_event_ID,event_payment_customer_NIC,event_payment_method,event_payment_amount,event_Credit/Debit,event_payment_due_date,event_payment_description) VALUES ('"+payEventId+"','"+payCusNIC+"','"+payMethod+"','"+payAmount+"','"+payDC+"','"+payDueDate+"','"+payDes+"')";
            pstatement = conn.prepareStatement(sql2);
            result = pstatement.executeQuery();
            addPayTable.setModel(DbUtils.resultSetToTableModel(result));
           
            JOptionPane.showMessageDialog(null, "Successfully added to the database");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error..Not added to the database");
        }
    }
    
    public void tableLoadHallName(){
        try {
            String sql="SELECT hall_name FROM event_hall";
            pstatement = conn.prepareStatement(sql);
            result = pstatement.executeQuery();
            addEventHallAvailability.setModel(DbUtils.resultSetToTableModel(result));
        } catch (Exception e) {
        }
    }
    

//--------------------------------------------------------------------------------Event Function------------------------------------------------------------------------------------------------------------------------------//
    // function for panel shifting
    public void eventpanelshift(String buttonName) {
        switch (buttonName) {
            case "EVENT":
                eventPanel.setVisible(true);
                customerPanel.setVisible(false);
                foodPackagesPanel.setVisible(false);
                decorationsPanel.setVisible(false);
                bookingsPanel.setVisible(false);
                paymentPanel.setVisible(false);
                reportsPanel.setVisible(false);
                eventUserLogPanel.setVisible(false);
                eventSettingsPanel.setVisible(false);
                break;

            case "CUSTOMER":
                eventPanel.setVisible(false);
                customerPanel.setVisible(true);
                foodPackagesPanel.setVisible(false);
                decorationsPanel.setVisible(false);
                bookingsPanel.setVisible(false);
                paymentPanel.setVisible(false);
                reportsPanel.setVisible(false);
                eventUserLogPanel.setVisible(false);
                eventSettingsPanel.setVisible(false);
                break;

            case "FOOD PACKAGES":
                eventPanel.setVisible(false);
                customerPanel.setVisible(false);
                foodPackagesPanel.setVisible(true);
                decorationsPanel.setVisible(false);
                bookingsPanel.setVisible(false);
                paymentPanel.setVisible(false);
                reportsPanel.setVisible(false);
                eventUserLogPanel.setVisible(false);
                eventSettingsPanel.setVisible(false);
                break;

            case "DECORATIONS":
                eventPanel.setVisible(false);
                customerPanel.setVisible(false);
                foodPackagesPanel.setVisible(false);
                decorationsPanel.setVisible(true);
                bookingsPanel.setVisible(false);
                paymentPanel.setVisible(false);
                reportsPanel.setVisible(false);
                eventUserLogPanel.setVisible(false);
                eventSettingsPanel.setVisible(false);
                break;

            case "BOOKINGS":
                eventPanel.setVisible(false);
                customerPanel.setVisible(false);
                foodPackagesPanel.setVisible(false);
                decorationsPanel.setVisible(false);
                bookingsPanel.setVisible(true);
                paymentPanel.setVisible(false);
                reportsPanel.setVisible(false);
                eventUserLogPanel.setVisible(false);
                eventSettingsPanel.setVisible(false);
                break;

            case "PAYMENTS":
                eventPanel.setVisible(false);
                customerPanel.setVisible(false);
                foodPackagesPanel.setVisible(false);
                decorationsPanel.setVisible(false);
                bookingsPanel.setVisible(false);
                paymentPanel.setVisible(true);
                reportsPanel.setVisible(false);
                eventUserLogPanel.setVisible(false);
                eventSettingsPanel.setVisible(false);
                break;

            case "REPORTS":
                eventPanel.setVisible(false);
                customerPanel.setVisible(false);
                foodPackagesPanel.setVisible(false);
                decorationsPanel.setVisible(false);
                bookingsPanel.setVisible(false);
                paymentPanel.setVisible(false);
                reportsPanel.setVisible(true);
                eventUserLogPanel.setVisible(false);
                eventSettingsPanel.setVisible(false);
                break;

            case "USER LOG":
                eventPanel.setVisible(false);
                customerPanel.setVisible(false);
                foodPackagesPanel.setVisible(false);
                decorationsPanel.setVisible(false);
                bookingsPanel.setVisible(false);
                paymentPanel.setVisible(false);
                reportsPanel.setVisible(false);
                eventUserLogPanel.setVisible(true);
                eventSettingsPanel.setVisible(false);
                break;

            case "SETTINGS":
                eventPanel.setVisible(false);
                customerPanel.setVisible(false);
                foodPackagesPanel.setVisible(false);
                decorationsPanel.setVisible(false);
                bookingsPanel.setVisible(false);
                paymentPanel.setVisible(false);
                reportsPanel.setVisible(false);
                eventUserLogPanel.setVisible(false);
                eventSettingsPanel.setVisible(true);
                break;
        }
    }
    
    public void view(Map para, String Path){
        try {
            DBconnect dbconnect = new DBconnect();
            JasperReport jar = JasperCompileManager.compileReport(Path);
            JasperPrint jprint = JasperFillManager.fillReport(jar, para, dbconnect.ConnectDB());
            
            JasperViewer.viewReport(jprint, false);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPaneEvent = new javax.swing.JDesktopPane();
        eventManagerPanel = new javax.swing.JPanel();
        headerpanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1LogOut = new javax.swing.JButton();
        jLabel3SystemDate = new javax.swing.JLabel();
        jLabel3SystemTime = new javax.swing.JLabel();
        mainButtonPanel = new javax.swing.JPanel();
        buttonEvent = new javax.swing.JButton();
        buttonCustomer = new javax.swing.JButton();
        buttonFoodPackages = new javax.swing.JButton();
        buttonDecorations = new javax.swing.JButton();
        buttonBookings = new javax.swing.JButton();
        buttonPayments = new javax.swing.JButton();
        buttonReports = new javax.swing.JButton();
        buttonUserLog = new javax.swing.JButton();
        buttonSettings = new javax.swing.JButton();
        togglePanels = new javax.swing.JPanel();
        eventPanel = new javax.swing.JPanel();
        jTabbedPaneEvent = new javax.swing.JTabbedPane();
        addEventPanel = new javax.swing.JPanel();
        jLabel2EventType = new javax.swing.JLabel();
        jLabel3EventDate = new javax.swing.JLabel();
        txtAddEventDuration = new javax.swing.JTextField();
        jLabel4EventDuration = new javax.swing.JLabel();
        jComboBox1AddEventType = new javax.swing.JComboBox();
        jLabel1Entertainment = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AddEventTable = new javax.swing.JTable();
        jButton1AddEvent = new javax.swing.JButton();
        addEventReset = new javax.swing.JButton();
        jButton3AddEventDemo = new javax.swing.JButton();
        jLabel2AddEventNoOfGuests = new javax.swing.JLabel();
        txtAddEventNoOfGuests = new javax.swing.JTextField();
        jComboBox2AddEventEntertainment = new javax.swing.JComboBox();
        jDateChooser2AddEventDate = new com.toedter.calendar.JDateChooser();
        jLabel3AddEventTime = new javax.swing.JLabel();
        jButton4EventUpdate = new javax.swing.JButton();
        addCusDetails = new javax.swing.JButton();
        jComboAddEventHours = new javax.swing.JComboBox();
        jComboAddEventMin = new javax.swing.JComboBox();
        jComboAddEventPM = new javax.swing.JComboBox();
        addEventNext = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        addEventHallPricelbl = new javax.swing.JLabel();
        addEventAvailability = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        addEventHallAvailability = new javax.swing.JTable();
        viewEventPanel = new javax.swing.JPanel();
        jLabel1EnterEventID = new javax.swing.JLabel();
        txtViewEventID = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        viewEventTable = new javax.swing.JTable();
        jButton1ViewEventDemo = new javax.swing.JButton();
        ViewEvent = new javax.swing.JButton();
        viewEventReset = new javax.swing.JButton();
        viewEventBack = new javax.swing.JButton();
        viewEventNext = new javax.swing.JButton();
        customerPanel = new javax.swing.JPanel();
        jTabbedPaneCustomer = new javax.swing.JTabbedPane();
        addCustomerPanel = new javax.swing.JPanel();
        jLabel1AddCustomerName = new javax.swing.JLabel();
        txtAddCustomerName = new javax.swing.JTextField();
        jLabel2AddCustomerNIC = new javax.swing.JLabel();
        txtAddCustomerNIC = new javax.swing.JTextField();
        jLabel4AddAddress = new javax.swing.JLabel();
        txtAddCustomerAddress = new javax.swing.JTextField();
        jLabel5AddContactNo = new javax.swing.JLabel();
        txtAddCustomerTelephoneNo = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        addCustomerTable = new javax.swing.JTable();
        jButton3AddCustomerDemo = new javax.swing.JButton();
        jButton4AddCustomerAdd = new javax.swing.JButton();
        addCusReset = new javax.swing.JButton();
        addCusEventID = new javax.swing.JLabel();
        addCusEventIDlbl = new javax.swing.JLabel();
        addCusBack = new javax.swing.JButton();
        addCusNext = new javax.swing.JButton();
        addCustAddFP = new javax.swing.JButton();
        hallPrice = new javax.swing.JLabel();
        viewCustomerPanel = new javax.swing.JPanel();
        jLabel1ViewEnterCustomerNIC = new javax.swing.JLabel();
        txtViewCustomerEnterCustomerNIC = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        viewCustomerTable = new javax.swing.JTable();
        jButton3ViewCustomerDemo = new javax.swing.JButton();
        viewCusNext = new javax.swing.JButton();
        viewCusBack = new javax.swing.JButton();
        viewCusSearch = new javax.swing.JButton();
        viewCusReset = new javax.swing.JButton();
        updateCustomerPanel = new javax.swing.JPanel();
        jLabel1UpdateEnterCustomerNIC = new javax.swing.JLabel();
        txtUpdateEnterCustomerNIC = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        updateCustomerTable = new javax.swing.JTable();
        jButton4UpdateCustomerDemo = new javax.swing.JButton();
        jButton5UpdateCustomerUpdate = new javax.swing.JButton();
        UpdateCusReset = new javax.swing.JButton();
        jLabel4UpdateCustomerName = new javax.swing.JLabel();
        txtUpdateCustomerName = new javax.swing.JTextField();
        jLabel4CustomerAddress = new javax.swing.JLabel();
        txtUpdateCustomerAdd = new javax.swing.JTextField();
        jLabel10CustomerTel = new javax.swing.JLabel();
        txtUpdateCustomerTel = new javax.swing.JTextField();
        jButton12UpdateCusSearch = new javax.swing.JButton();
        updateCusEventIDlbl = new javax.swing.JLabel();
        updateCusEventID = new javax.swing.JLabel();
        updateCusBack = new javax.swing.JButton();
        updateCusNext = new javax.swing.JButton();
        foodPackagesPanel = new javax.swing.JPanel();
        jTabbedPaneFoodPackages = new javax.swing.JTabbedPane();
        RegFoodPackagesPanel = new javax.swing.JPanel();
        selectRFPAdd = new javax.swing.JButton();
        regFPReset = new javax.swing.JButton();
        jScrollPane20 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jScrollPane21 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList();
        jScrollPane22 = new javax.swing.JScrollPane();
        jList5 = new javax.swing.JList();
        jScrollPane23 = new javax.swing.JScrollPane();
        jList6 = new javax.swing.JList();
        jScrollPane24 = new javax.swing.JScrollPane();
        jList7 = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        regFPEventID = new javax.swing.JLabel();
        regFPEnterEventIDlbl = new javax.swing.JLabel();
        regFPCustomerNIC = new javax.swing.JLabel();
        regFPEnterCustomerNIClbl = new javax.swing.JLabel();
        regFpackageNolbl = new javax.swing.JLabel();
        jcomRegFPSelecPack = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        txtFPNoOfPlates = new javax.swing.JTextField();
        regFPBack = new javax.swing.JButton();
        regFPNext = new javax.swing.JButton();
        regFPAddDecor = new javax.swing.JButton();
        regFPAddCusFP = new javax.swing.JButton();
        regFPSetTotalPrice = new javax.swing.JButton();
        regFPTotalPricelbl = new javax.swing.JLabel();
        regFPAddPay = new javax.swing.JButton();
        hallPricerfp = new javax.swing.JLabel();
        regFPDemo = new javax.swing.JButton();
        CusFoodPackagesPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addCusFPAdd = new javax.swing.JButton();
        cusFPReset = new javax.swing.JButton();
        addCusFPEventIDlbl = new javax.swing.JLabel();
        cusFPEventID = new javax.swing.JLabel();
        customizedFPAdd = new javax.swing.JButton();
        cusFPCustomerNIC = new javax.swing.JLabel();
        addCusFPBack = new javax.swing.JButton();
        addCusFPNext = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        selectedCusFoodItemTable = new javax.swing.JTable();
        jScrollPane16 = new javax.swing.JScrollPane();
        viewCusFPTable = new javax.swing.JTable();
        customizedFPRemove = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        txtCusFPNoOfPlates = new javax.swing.JTextField();
        cusFPAddDecor = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        addCusPricePerPlatelbl = new javax.swing.JLabel();
        cusFPOk = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        addCusTotalFPPricelbl = new javax.swing.JLabel();
        hallPricefp = new javax.swing.JLabel();
        cusFPAddPay = new javax.swing.JButton();
        cusFPDemo = new javax.swing.JButton();
        decorationsPanel = new javax.swing.JPanel();
        jTabbedPaneDecorations = new javax.swing.JTabbedPane();
        addDecorationsPanel = new javax.swing.JPanel();
        jLabel1AddDecorationColor = new javax.swing.JLabel();
        addDecorColor = new javax.swing.JComboBox();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtAreaDescription = new javax.swing.JTextArea();
        jLabel1AddDecorationDescription = new javax.swing.JLabel();
        jLabel1AddDecorationTypes = new javax.swing.JLabel();
        jCheckBox1AddTableDecorations = new javax.swing.JCheckBox();
        jCheckBox2AddWallDecorations = new javax.swing.JCheckBox();
        jCheckBox3AddFlowerDecoration = new javax.swing.JCheckBox();
        jCheckBox4AddDiscoLight = new javax.swing.JCheckBox();
        jCheckBox5AddEntranceArch = new javax.swing.JCheckBox();
        jLabel1AddSpecialRequirements = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtAreaSpecialRequirements = new javax.swing.JTextArea();
        jButton4AddDecorationDemo = new javax.swing.JButton();
        jButton7AddDecoration = new javax.swing.JButton();
        jButton8AddDecorReset = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        addDecorEventIDlbl = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        addDecorCusNIClbl = new javax.swing.JLabel();
        addDecorNext = new javax.swing.JButton();
        addDecorBack = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtAddDecorSRPrice = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtAddDecorPrice = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        addDecorTable = new javax.swing.JTable();
        addDecorAddPayment = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        addDecorTotal = new javax.swing.JLabel();
        hallpricedecor = new javax.swing.JLabel();
        fpPricedecor = new javax.swing.JLabel();
        updateDecorationsPanel = new javax.swing.JPanel();
        jLabel1UpdateDecorationEventID = new javax.swing.JLabel();
        txtUpdateDecorationEventID = new javax.swing.JTextField();
        jScrollPane12 = new javax.swing.JScrollPane();
        updateDecorTable = new javax.swing.JTable();
        jButton4UpdateDecorationDemo = new javax.swing.JButton();
        updateDecorReset = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        updateDecorIDlbl = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        updateDecorEventIDlb = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jComboUpdateDecorColor = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextAreaUpdatedecorDes = new javax.swing.JTextArea();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        jTextAreaUpdateDecorSR = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        jCheckBox1UpdateTable = new javax.swing.JCheckBox();
        jCheckBox2UpdateWall = new javax.swing.JCheckBox();
        jCheckBox3UpdateFlower = new javax.swing.JCheckBox();
        jCheckBox4UpdateDisco = new javax.swing.JCheckBox();
        jCheckBox5UpdateArch = new javax.swing.JCheckBox();
        updateDecorSRPricelbl = new javax.swing.JLabel();
        txtUpdateDecorSRPrice = new javax.swing.JTextField();
        updateDecorPricelbl = new javax.swing.JLabel();
        txtUpdateDecorPrice = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        updateDecorTotal = new javax.swing.JLabel();
        updateDecorSearch = new javax.swing.JButton();
        updateDecorBack = new javax.swing.JButton();
        updateDecorNext = new javax.swing.JButton();
        jButton9UpdateDecoration = new javax.swing.JButton();
        paymentPanel = new javax.swing.JPanel();
        jTabbedPanePayments = new javax.swing.JTabbedPane();
        addPaymentsPanel = new javax.swing.JPanel();
        jLabel2AddPaymentMethod = new javax.swing.JLabel();
        jLabel3AddPaymentAmount = new javax.swing.JLabel();
        txtAddPaymentAmount = new javax.swing.JTextField();
        jLabel4AddPaymentDescription = new javax.swing.JLabel();
        jComboPayMethods = new javax.swing.JComboBox();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtAreaAddPaymentDescription = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton4AddPaymentDemo = new javax.swing.JButton();
        jButton9AddPaymentAdd = new javax.swing.JButton();
        addPaymentReset = new javax.swing.JButton();
        jLabel3AddPaymentCredit = new javax.swing.JLabel();
        jComboPayCD = new javax.swing.JComboBox();
        jDatePayDueDate = new com.toedter.calendar.JDateChooser();
        jScrollPane14 = new javax.swing.JScrollPane();
        addPayTable = new javax.swing.JTable();
        addPayBack = new javax.swing.JButton();
        addPayNext = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        addPayEventIDlbl = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        addPayCusNIClbl = new javax.swing.JLabel();
        addPayViewBookings = new javax.swing.JButton();
        addPayTotalFdAmount = new javax.swing.JLabel();
        addPayTotalfoodAmount = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        addPayTotalDecorAmount = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        addPayTotalHallAmount = new javax.swing.JLabel();
        addPayTot = new javax.swing.JButton();
        addPayTotal = new javax.swing.JLabel();
        addPayBalancelbl = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        updatePaymentsPanel = new javax.swing.JPanel();
        jLabel2UpdatePaymentEventID = new javax.swing.JLabel();
        txtUpdatePaymentCusNIC = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        updatePayTable = new javax.swing.JTable();
        jButton4UpdatePaymentDemo = new javax.swing.JButton();
        updatePayReset = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        UpdatePaymentEventID = new javax.swing.JLabel();
        updatePaymentEventIDlbl = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboUpdatePayMethod = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        txtUpdatePayAmount = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jComboUpdateCD = new javax.swing.JComboBox();
        updatePayementDeslbl = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtAreaUpdatePaymentDescription = new javax.swing.JTextArea();
        updatePaymentDueDatelbl = new javax.swing.JLabel();
        jDateChooser2UpdatePayDate = new com.toedter.calendar.JDateChooser();
        jLabel28 = new javax.swing.JLabel();
        txtUpdatePayBalance = new javax.swing.JTextField();
        updatePaymentSearch = new javax.swing.JButton();
        updatePayNext = new javax.swing.JButton();
        updatePayBack = new javax.swing.JButton();
        jButton11UpdatePaymentUpdate = new javax.swing.JButton();
        bookingsPanel = new javax.swing.JPanel();
        updateBookingsPanel = new javax.swing.JPanel();
        UpdateBookingsEnterCusNIC = new javax.swing.JLabel();
        txtUpdateBookingsCusNIC = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        eventBookingTable = new javax.swing.JTable();
        jButton9UpdateBookingsDemo = new javax.swing.JButton();
        updateBookingsReset = new javax.swing.JButton();
        bookingSearch = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        bookingEventIDlbl = new javax.swing.JLabel();
        bookingDatelbl = new javax.swing.JLabel();
        jDateChooser2BookingDate = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jcomBookingStatus = new javax.swing.JComboBox();
        UpdateBookingsUpdate = new javax.swing.JButton();
        bookingNext = new javax.swing.JButton();
        bookingBack = new javax.swing.JButton();
        bookingReports = new javax.swing.JButton();
        reportsPanel = new javax.swing.JPanel();
        jTabbedPaneReports = new javax.swing.JTabbedPane();
        generateReportsPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        bookingReport = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jDateChooserReportsDate1 = new com.toedter.calendar.JDateChooser();
        jDateChooserReportDateTo = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        txtReportCusNIC = new javax.swing.JTextField();
        genarateReportEvent = new javax.swing.JButton();
        eventUserLogPanel = new javax.swing.JPanel();
        eventSettingsPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1020, 700));

        jDesktopPaneEvent.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPaneEvent.setPreferredSize(new java.awt.Dimension(1024, 700));

        eventManagerPanel.setBackground(new java.awt.Color(255, 255, 255));
        eventManagerPanel.setPreferredSize(new java.awt.Dimension(1024, 690));

        headerpanel.setBackground(new java.awt.Color(255, 255, 255));
        headerpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));
        headerpanel.setPreferredSize(new java.awt.Dimension(1024, 130));

        jButton1LogOut.setText("Log out");
        jButton1LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1LogOutActionPerformed(evt);
            }
        });

        jLabel3SystemDate.setText("jLabel3");

        jLabel3SystemTime.setText("jLabel22");

        javax.swing.GroupLayout headerpanelLayout = new javax.swing.GroupLayout(headerpanel);
        headerpanel.setLayout(headerpanelLayout);
        headerpanelLayout.setHorizontalGroup(
            headerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(headerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3SystemDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3SystemTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42))
        );
        headerpanelLayout.setVerticalGroup(
            headerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(headerpanelLayout.createSequentialGroup()
                        .addComponent(jLabel3SystemDate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3SystemTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1LogOut))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                .addContainerGap())
        );

        mainButtonPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainButtonPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));
        mainButtonPanel.setPreferredSize(new java.awt.Dimension(187, 552));

        buttonEvent.setBackground(new java.awt.Color(51, 153, 255));
        buttonEvent.setForeground(new java.awt.Color(255, 255, 255));
        buttonEvent.setText("EVENT");
        buttonEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonEventMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonEventMouseExited(evt);
            }
        });
        buttonEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEventActionPerformed(evt);
            }
        });

        buttonCustomer.setBackground(new java.awt.Color(51, 153, 255));
        buttonCustomer.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustomer.setText("CUSTOMER");
        buttonCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonCustomerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonCustomerMouseExited(evt);
            }
        });
        buttonCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustomerActionPerformed(evt);
            }
        });

        buttonFoodPackages.setBackground(new java.awt.Color(51, 153, 255));
        buttonFoodPackages.setForeground(new java.awt.Color(255, 255, 255));
        buttonFoodPackages.setText("FOOD PACKAGES");
        buttonFoodPackages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonFoodPackagesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonFoodPackagesMouseExited(evt);
            }
        });
        buttonFoodPackages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFoodPackagesActionPerformed(evt);
            }
        });

        buttonDecorations.setBackground(new java.awt.Color(51, 153, 255));
        buttonDecorations.setForeground(new java.awt.Color(255, 255, 255));
        buttonDecorations.setText("DECORATIONS");
        buttonDecorations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonDecorationsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonDecorationsMouseExited(evt);
            }
        });
        buttonDecorations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDecorationsActionPerformed(evt);
            }
        });

        buttonBookings.setBackground(new java.awt.Color(51, 153, 255));
        buttonBookings.setForeground(new java.awt.Color(255, 255, 255));
        buttonBookings.setText("BOOKINGS");
        buttonBookings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonBookingsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonBookingsMouseExited(evt);
            }
        });
        buttonBookings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBookingsActionPerformed(evt);
            }
        });

        buttonPayments.setBackground(new java.awt.Color(51, 153, 255));
        buttonPayments.setForeground(new java.awt.Color(255, 255, 255));
        buttonPayments.setText("PAYMENTS");
        buttonPayments.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonPaymentsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonPaymentsMouseExited(evt);
            }
        });
        buttonPayments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPaymentsActionPerformed(evt);
            }
        });

        buttonReports.setBackground(new java.awt.Color(51, 153, 255));
        buttonReports.setForeground(new java.awt.Color(255, 255, 255));
        buttonReports.setText("REPORTS");
        buttonReports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonReportsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonReportsMouseExited(evt);
            }
        });
        buttonReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReportsActionPerformed(evt);
            }
        });

        buttonUserLog.setBackground(new java.awt.Color(51, 153, 255));
        buttonUserLog.setForeground(new java.awt.Color(255, 255, 255));
        buttonUserLog.setText("USER LOG");
        buttonUserLog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonUserLogMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonUserLogMouseExited(evt);
            }
        });
        buttonUserLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUserLogActionPerformed(evt);
            }
        });

        buttonSettings.setBackground(new java.awt.Color(51, 153, 255));
        buttonSettings.setForeground(new java.awt.Color(255, 255, 255));
        buttonSettings.setText("SETTINGS");
        buttonSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonSettingsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonSettingsMouseExited(evt);
            }
        });
        buttonSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainButtonPanelLayout = new javax.swing.GroupLayout(mainButtonPanel);
        mainButtonPanel.setLayout(mainButtonPanelLayout);
        mainButtonPanelLayout.setHorizontalGroup(
            mainButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonBookings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPayments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDecorations, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(buttonUserLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEvent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonSettings, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonFoodPackages, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainButtonPanelLayout.setVerticalGroup(
            mainButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonFoodPackages, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonDecorations, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonPayments, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonBookings, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonReports, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonUserLog, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        togglePanels.setBackground(new java.awt.Color(255, 255, 255));
        togglePanels.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));
        togglePanels.setPreferredSize(new java.awt.Dimension(832, 552));

        eventPanel.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPaneEvent.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneEvent.setPreferredSize(new java.awt.Dimension(830, 548));

        addEventPanel.setBackground(new java.awt.Color(255, 255, 255));
        addEventPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jLabel2EventType.setText("Event Type  ");

        jLabel3EventDate.setText("Event Date ");

        txtAddEventDuration.setPreferredSize(new java.awt.Dimension(8, 25));

        jLabel4EventDuration.setText("Event Duration");

        jComboBox1AddEventType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Type", "Wedding", "Birthday Party", "Anniversary", "Get together", "Office Function", " " }));

        jLabel1Entertainment.setText("Entertainment");

        AddEventTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Event Id", "Event Type", "Event Duration", "Entertainment", "Event Date", "Event Time", "No of Guests", "Hall No", "Hall Price"
            }
        ));
        AddEventTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddEventTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(AddEventTable);

        jButton1AddEvent.setBackground(new java.awt.Color(51, 153, 255));
        jButton1AddEvent.setText("Add Event");
        jButton1AddEvent.setPreferredSize(new java.awt.Dimension(90, 25));
        jButton1AddEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1AddEventMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1AddEventMouseExited(evt);
            }
        });
        jButton1AddEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1AddEventActionPerformed(evt);
            }
        });

        addEventReset.setBackground(new java.awt.Color(51, 153, 255));
        addEventReset.setText("Reset");
        addEventReset.setPreferredSize(new java.awt.Dimension(80, 25));
        addEventReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addEventResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addEventResetMouseExited(evt);
            }
        });
        addEventReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventResetActionPerformed(evt);
            }
        });

        jButton3AddEventDemo.setBackground(new java.awt.Color(51, 153, 255));
        jButton3AddEventDemo.setText("Demo");
        jButton3AddEventDemo.setPreferredSize(new java.awt.Dimension(80, 25));
        jButton3AddEventDemo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3AddEventDemoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3AddEventDemoMouseExited(evt);
            }
        });
        jButton3AddEventDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3AddEventDemoActionPerformed(evt);
            }
        });

        jLabel2AddEventNoOfGuests.setText("No of Guests");

        txtAddEventNoOfGuests.setMinimumSize(new java.awt.Dimension(8, 25));
        txtAddEventNoOfGuests.setPreferredSize(new java.awt.Dimension(8, 25));

        jComboBox2AddEventEntertainment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Type", "Band", "DJ", "Calipso", "Disco Light", "Dancing Group", " " }));
        jComboBox2AddEventEntertainment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2AddEventEntertainmentActionPerformed(evt);
            }
        });

        jLabel3AddEventTime.setText("Event Time");

        jButton4EventUpdate.setBackground(new java.awt.Color(51, 153, 255));
        jButton4EventUpdate.setText("Update");
        jButton4EventUpdate.setPreferredSize(new java.awt.Dimension(80, 25));
        jButton4EventUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4EventUpdateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4EventUpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4EventUpdateMouseExited(evt);
            }
        });
        jButton4EventUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4EventUpdateActionPerformed(evt);
            }
        });

        addCusDetails.setBackground(new java.awt.Color(51, 153, 255));
        addCusDetails.setText("Add Customer Details");
        addCusDetails.setPreferredSize(new java.awt.Dimension(160, 25));
        addCusDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addCusDetailsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addCusDetailsMouseExited(evt);
            }
        });
        addCusDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCusDetailsActionPerformed(evt);
            }
        });

        jComboAddEventHours.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hours", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jComboAddEventMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Min", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jComboAddEventPM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));

        addEventNext.setBackground(new java.awt.Color(51, 153, 255));
        addEventNext.setText("Next");
        addEventNext.setPreferredSize(new java.awt.Dimension(80, 25));
        addEventNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addEventNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addEventNextMouseExited(evt);
            }
        });
        addEventNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventNextActionPerformed(evt);
            }
        });

        jLabel26.setText("Hall Price");

        addEventHallPricelbl.setText("\n");

        addEventAvailability.setText("Ckeck Availability");
        addEventAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addEventAvailabilityActionPerformed(evt);
            }
        });

        addEventHallAvailability.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hall Name"
            }
        ));
        addEventHallAvailability.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addEventHallAvailabilityMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(addEventHallAvailability);

        javax.swing.GroupLayout addEventPanelLayout = new javax.swing.GroupLayout(addEventPanel);
        addEventPanel.setLayout(addEventPanelLayout);
        addEventPanelLayout.setHorizontalGroup(
            addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addEventPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addEventPanelLayout.createSequentialGroup()
                        .addComponent(jButton3AddEventDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(jButton1AddEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(addCusDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4EventUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(addEventNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(addEventReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(addEventPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4EventDuration)
                    .addComponent(jLabel2EventType)
                    .addComponent(jLabel3EventDate)
                    .addComponent(jLabel3AddEventTime)
                    .addComponent(jLabel2AddEventNoOfGuests))
                .addGap(22, 22, 22)
                .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2AddEventDate, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1AddEventType, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addEventPanelLayout.createSequentialGroup()
                        .addComponent(jComboAddEventHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboAddEventMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboAddEventPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtAddEventDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddEventNoOfGuests, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addEventAvailability)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addEventPanelLayout.createSequentialGroup()
                        .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addEventPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1Entertainment)
                                .addGap(33, 33, 33))
                            .addGroup(addEventPanelLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addGap(58, 58, 58)))
                        .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addEventHallPricelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2AddEventEntertainment, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(217, Short.MAX_VALUE))
        );
        addEventPanelLayout.setVerticalGroup(
            addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addEventPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2EventType)
                    .addComponent(jComboBox1AddEventType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addEventAvailability))
                .addGap(18, 18, 18)
                .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addEventPanelLayout.createSequentialGroup()
                        .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4EventDuration)
                            .addComponent(txtAddEventDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3EventDate)
                            .addComponent(jDateChooser2AddEventDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboAddEventHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboAddEventMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboAddEventPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3AddEventTime))
                        .addGap(27, 27, 27))
                    .addGroup(addEventPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addEventHallPricelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)))
                .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1Entertainment)
                    .addComponent(jComboBox2AddEventEntertainment, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddEventNoOfGuests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2AddEventNoOfGuests))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(addEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addEventReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addEventNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4EventUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCusDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1AddEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3AddEventDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPaneEvent.addTab("Add Event", addEventPanel);

        viewEventPanel.setBackground(new java.awt.Color(255, 255, 255));
        viewEventPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jLabel1EnterEventID.setText("Enter Event ID");

        viewEventTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Event ID", "Event Type", "Event Duration", "Entertainment", "Event Date", "Event Time", "No of Guests", "Hall No", "Hall Price"
            }
        ));
        viewEventTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewEventTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(viewEventTable);

        jButton1ViewEventDemo.setBackground(new java.awt.Color(51, 153, 255));
        jButton1ViewEventDemo.setText("Demo");
        jButton1ViewEventDemo.setPreferredSize(new java.awt.Dimension(80, 25));
        jButton1ViewEventDemo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1ViewEventDemoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1ViewEventDemoMouseExited(evt);
            }
        });

        ViewEvent.setBackground(new java.awt.Color(51, 153, 255));
        ViewEvent.setText("View");
        ViewEvent.setPreferredSize(new java.awt.Dimension(80, 25));
        ViewEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ViewEventMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ViewEventMouseExited(evt);
            }
        });
        ViewEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewEventActionPerformed(evt);
            }
        });

        viewEventReset.setBackground(new java.awt.Color(51, 153, 255));
        viewEventReset.setText("Reset");
        viewEventReset.setPreferredSize(new java.awt.Dimension(80, 25));
        viewEventReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewEventResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewEventResetMouseExited(evt);
            }
        });
        viewEventReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEventResetActionPerformed(evt);
            }
        });

        viewEventBack.setBackground(new java.awt.Color(51, 153, 255));
        viewEventBack.setText("Back");
        viewEventBack.setPreferredSize(new java.awt.Dimension(80, 25));
        viewEventBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewEventBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewEventBackMouseExited(evt);
            }
        });
        viewEventBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEventBackActionPerformed(evt);
            }
        });

        viewEventNext.setBackground(new java.awt.Color(51, 153, 255));
        viewEventNext.setText("Next");
        viewEventNext.setPreferredSize(new java.awt.Dimension(80, 25));
        viewEventNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewEventNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewEventNextMouseExited(evt);
            }
        });
        viewEventNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewEventNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewEventPanelLayout = new javax.swing.GroupLayout(viewEventPanel);
        viewEventPanel.setLayout(viewEventPanelLayout);
        viewEventPanelLayout.setHorizontalGroup(
            viewEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewEventPanelLayout.createSequentialGroup()
                .addGroup(viewEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewEventPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE))
                    .addGroup(viewEventPanelLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1EnterEventID)
                        .addGap(52, 52, 52)
                        .addComponent(txtViewEventID, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(ViewEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewEventPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewEventBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(viewEventNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(viewEventReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(viewEventPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton1ViewEventDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        viewEventPanelLayout.setVerticalGroup(
            viewEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewEventPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(viewEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1EnterEventID)
                    .addComponent(txtViewEventID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jButton1ViewEventDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(viewEventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewEventBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewEventNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewEventReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jTabbedPaneEvent.addTab("View Event", viewEventPanel);

        javax.swing.GroupLayout eventPanelLayout = new javax.swing.GroupLayout(eventPanel);
        eventPanel.setLayout(eventPanelLayout);
        eventPanelLayout.setHorizontalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneEvent, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                .addContainerGap())
        );
        eventPanelLayout.setVerticalGroup(
            eventPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneEvent, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addContainerGap())
        );

        customerPanel.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPaneCustomer.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneCustomer.setPreferredSize(new java.awt.Dimension(830, 548));

        addCustomerPanel.setBackground(new java.awt.Color(255, 255, 255));
        addCustomerPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jLabel1AddCustomerName.setText("Customer Name");

        jLabel2AddCustomerNIC.setText("NIC");

        txtAddCustomerNIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAddCustomerNICActionPerformed(evt);
            }
        });

        jLabel4AddAddress.setText("Address");

        jLabel5AddContactNo.setText("Contact No");

        txtAddCustomerTelephoneNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAddCustomerTelephoneNoKeyTyped(evt);
            }
        });

        addCustomerTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        addCustomerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Event ID", "Customer Name", "NIC", "Address", "Telephone No"
            }
        ));
        addCustomerTable.setGridColor(new java.awt.Color(51, 153, 255));
        jScrollPane4.setViewportView(addCustomerTable);

        jButton3AddCustomerDemo.setBackground(new java.awt.Color(51, 153, 255));
        jButton3AddCustomerDemo.setText("Demo");
        jButton3AddCustomerDemo.setPreferredSize(new java.awt.Dimension(80, 25));
        jButton3AddCustomerDemo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3AddCustomerDemoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3AddCustomerDemoMouseExited(evt);
            }
        });
        jButton3AddCustomerDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3AddCustomerDemoActionPerformed(evt);
            }
        });

        jButton4AddCustomerAdd.setBackground(new java.awt.Color(51, 153, 255));
        jButton4AddCustomerAdd.setText("Add Customer");
        jButton4AddCustomerAdd.setPreferredSize(new java.awt.Dimension(120, 25));
        jButton4AddCustomerAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4AddCustomerAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4AddCustomerAddMouseExited(evt);
            }
        });
        jButton4AddCustomerAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4AddCustomerAddActionPerformed(evt);
            }
        });

        addCusReset.setBackground(new java.awt.Color(51, 153, 255));
        addCusReset.setText("Reset");
        addCusReset.setPreferredSize(new java.awt.Dimension(80, 25));
        addCusReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addCusResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addCusResetMouseExited(evt);
            }
        });
        addCusReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCusResetActionPerformed(evt);
            }
        });

        addCusEventID.setText("Event ID");

        addCusBack.setBackground(new java.awt.Color(51, 153, 255));
        addCusBack.setText("Back");
        addCusBack.setPreferredSize(new java.awt.Dimension(80, 25));
        addCusBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addCusBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addCusBackMouseExited(evt);
            }
        });
        addCusBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCusBackActionPerformed(evt);
            }
        });

        addCusNext.setBackground(new java.awt.Color(51, 153, 255));
        addCusNext.setText("Next");
        addCusNext.setPreferredSize(new java.awt.Dimension(80, 25));
        addCusNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addCusNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addCusNextMouseExited(evt);
            }
        });
        addCusNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCusNextActionPerformed(evt);
            }
        });

        addCustAddFP.setBackground(new java.awt.Color(51, 153, 255));
        addCustAddFP.setText("Add Food Package");
        addCustAddFP.setPreferredSize(new java.awt.Dimension(140, 25));
        addCustAddFP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addCustAddFPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addCustAddFPMouseExited(evt);
            }
        });
        addCustAddFP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustAddFPActionPerformed(evt);
            }
        });

        hallPrice.setEnabled(false);

        javax.swing.GroupLayout addCustomerPanelLayout = new javax.swing.GroupLayout(addCustomerPanel);
        addCustomerPanel.setLayout(addCustomerPanelLayout);
        addCustomerPanelLayout.setHorizontalGroup(
            addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addCustomerPanelLayout.createSequentialGroup()
                .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addCustomerPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(addCustomerPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2AddCustomerNIC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAddCustomerNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addCustomerPanelLayout.createSequentialGroup()
                                .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addCustomerPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel1AddCustomerName)
                                        .addGap(73, 73, 73))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addCustomerPanelLayout.createSequentialGroup()
                                        .addComponent(addCusEventID)
                                        .addGap(107, 107, 107)))
                                .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAddCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                    .addComponent(addCusEventIDlbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(122, 122, 122)
                        .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4AddAddress)
                            .addComponent(jLabel5AddContactNo))
                        .addGap(62, 62, 62)
                        .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAddCustomerTelephoneNo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddCustomerAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(addCustomerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3AddCustomerDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(170, 170, 170)
                        .addComponent(jButton4AddCustomerAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(addCustAddFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addCustomerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addCustomerPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addCustomerPanelLayout.createSequentialGroup()
                        .addComponent(hallPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addCusBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(addCusNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(addCusReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        addCustomerPanelLayout.setVerticalGroup(
            addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addCustomerPanelLayout.createSequentialGroup()
                .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addCustomerPanelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addCusEventID)
                            .addComponent(addCusEventIDlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(addCustomerPanelLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1AddCustomerName)
                            .addComponent(jLabel4AddAddress)
                            .addComponent(txtAddCustomerAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAddCustomerNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2AddCustomerNIC)
                            .addComponent(jLabel5AddContactNo)
                            .addComponent(txtAddCustomerTelephoneNo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3AddCustomerDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4AddCustomerAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCustAddFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(addCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCusBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCusNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCusReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addCustomerPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(hallPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneCustomer.addTab("Add Customer", addCustomerPanel);

        viewCustomerPanel.setBackground(new java.awt.Color(255, 255, 255));
        viewCustomerPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jLabel1ViewEnterCustomerNIC.setText("Enter Customer NIC");

        viewCustomerTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        viewCustomerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Event ID", "Customer Name", "NIC", "Address", "Telephone No"
            }
        ));
        viewCustomerTable.setGridColor(new java.awt.Color(51, 153, 255));
        jScrollPane5.setViewportView(viewCustomerTable);

        jButton3ViewCustomerDemo.setBackground(new java.awt.Color(51, 153, 255));
        jButton3ViewCustomerDemo.setText("Demo");
        jButton3ViewCustomerDemo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton3ViewCustomerDemoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton3ViewCustomerDemoMouseExited(evt);
            }
        });

        viewCusNext.setBackground(new java.awt.Color(51, 153, 255));
        viewCusNext.setText("Next");
        viewCusNext.setPreferredSize(new java.awt.Dimension(80, 25));
        viewCusNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewCusNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewCusNextMouseExited(evt);
            }
        });
        viewCusNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCusNextActionPerformed(evt);
            }
        });

        viewCusBack.setBackground(new java.awt.Color(51, 153, 255));
        viewCusBack.setText("Back");
        viewCusBack.setPreferredSize(new java.awt.Dimension(80, 25));
        viewCusBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewCusBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewCusBackMouseExited(evt);
            }
        });
        viewCusBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCusBackActionPerformed(evt);
            }
        });

        viewCusSearch.setBackground(new java.awt.Color(51, 153, 255));
        viewCusSearch.setText("Search");
        viewCusSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewCusSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewCusSearchMouseExited(evt);
            }
        });
        viewCusSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCusSearchActionPerformed(evt);
            }
        });

        viewCusReset.setBackground(new java.awt.Color(51, 153, 255));
        viewCusReset.setText("Reset");
        viewCusReset.setPreferredSize(new java.awt.Dimension(80, 25));
        viewCusReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewCusResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewCusResetMouseExited(evt);
            }
        });
        viewCusReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCusResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewCustomerPanelLayout = new javax.swing.GroupLayout(viewCustomerPanel);
        viewCustomerPanel.setLayout(viewCustomerPanelLayout);
        viewCustomerPanelLayout.setHorizontalGroup(
            viewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewCustomerPanelLayout.createSequentialGroup()
                .addGroup(viewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewCustomerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
                    .addGroup(viewCustomerPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1ViewEnterCustomerNIC)
                        .addGap(59, 59, 59)
                        .addComponent(txtViewCustomerEnterCustomerNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(viewCusSearch)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(viewCustomerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewCusBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(viewCusNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(viewCusReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(viewCustomerPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jButton3ViewCustomerDemo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        viewCustomerPanelLayout.setVerticalGroup(
            viewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewCustomerPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(viewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1ViewEnterCustomerNIC)
                    .addComponent(txtViewCustomerEnterCustomerNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewCusSearch))
                .addGap(77, 77, 77)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(jButton3ViewCustomerDemo)
                .addGap(30, 30, 30)
                .addGroup(viewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewCusReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewCusNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewCusBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jTabbedPaneCustomer.addTab("View Customer", viewCustomerPanel);

        updateCustomerPanel.setBackground(new java.awt.Color(255, 255, 255));
        updateCustomerPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jLabel1UpdateEnterCustomerNIC.setText("Enter Customer NIC");

        updateCustomerTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        updateCustomerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Event ID", "Customer Name", "NIC", "Address", "Telephone No"
            }
        ));
        updateCustomerTable.setGridColor(new java.awt.Color(51, 153, 255));
        updateCustomerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateCustomerTableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(updateCustomerTable);

        jButton4UpdateCustomerDemo.setBackground(new java.awt.Color(51, 153, 255));
        jButton4UpdateCustomerDemo.setText("Demo");
        jButton4UpdateCustomerDemo.setPreferredSize(new java.awt.Dimension(80, 25));
        jButton4UpdateCustomerDemo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4UpdateCustomerDemoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4UpdateCustomerDemoMouseExited(evt);
            }
        });

        jButton5UpdateCustomerUpdate.setBackground(new java.awt.Color(51, 153, 255));
        jButton5UpdateCustomerUpdate.setText("Update");
        jButton5UpdateCustomerUpdate.setPreferredSize(new java.awt.Dimension(80, 25));
        jButton5UpdateCustomerUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton5UpdateCustomerUpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton5UpdateCustomerUpdateMouseExited(evt);
            }
        });
        jButton5UpdateCustomerUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5UpdateCustomerUpdateActionPerformed(evt);
            }
        });

        UpdateCusReset.setBackground(new java.awt.Color(51, 153, 255));
        UpdateCusReset.setText("Reset");
        UpdateCusReset.setPreferredSize(new java.awt.Dimension(80, 25));
        UpdateCusReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UpdateCusResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UpdateCusResetMouseExited(evt);
            }
        });
        UpdateCusReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateCusResetActionPerformed(evt);
            }
        });

        jLabel4UpdateCustomerName.setText("Customer Name");

        jLabel4CustomerAddress.setText("Customer Address");

        jLabel10CustomerTel.setText("Customer Contact No");

        jButton12UpdateCusSearch.setBackground(new java.awt.Color(51, 153, 255));
        jButton12UpdateCusSearch.setText("Search");
        jButton12UpdateCusSearch.setPreferredSize(new java.awt.Dimension(80, 25));
        jButton12UpdateCusSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton12UpdateCusSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton12UpdateCusSearchMouseExited(evt);
            }
        });
        jButton12UpdateCusSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12UpdateCusSearchActionPerformed(evt);
            }
        });

        updateCusEventIDlbl.setText("Event ID");

        updateCusBack.setBackground(new java.awt.Color(51, 153, 255));
        updateCusBack.setText("Back");
        updateCusBack.setPreferredSize(new java.awt.Dimension(80, 25));
        updateCusBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateCusBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateCusBackMouseExited(evt);
            }
        });
        updateCusBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCusBackActionPerformed(evt);
            }
        });

        updateCusNext.setBackground(new java.awt.Color(51, 153, 255));
        updateCusNext.setText("Next");
        updateCusNext.setPreferredSize(new java.awt.Dimension(80, 25));
        updateCusNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateCusNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateCusNextMouseExited(evt);
            }
        });
        updateCusNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCusNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateCustomerPanelLayout = new javax.swing.GroupLayout(updateCustomerPanel);
        updateCustomerPanel.setLayout(updateCustomerPanelLayout);
        updateCustomerPanelLayout.setHorizontalGroup(
            updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateCustomerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updateCusBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(updateCusNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(UpdateCusReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
            .addGroup(updateCustomerPanelLayout.createSequentialGroup()
                .addGroup(updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateCustomerPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE))
                    .addGroup(updateCustomerPanelLayout.createSequentialGroup()
                        .addGroup(updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updateCustomerPanelLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1UpdateEnterCustomerNIC)
                                    .addComponent(jLabel4UpdateCustomerName)
                                    .addComponent(jLabel4CustomerAddress))
                                .addGap(73, 73, 73)
                                .addGroup(updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUpdateCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                                    .addComponent(txtUpdateCustomerAdd)
                                    .addComponent(txtUpdateEnterCustomerNIC))
                                .addGroup(updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(updateCustomerPanelLayout.createSequentialGroup()
                                        .addGap(111, 111, 111)
                                        .addGroup(updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(updateCusEventIDlbl)
                                            .addComponent(jLabel10CustomerTel))
                                        .addGap(28, 28, 28)
                                        .addGroup(updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(updateCusEventID, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtUpdateCustomerTel, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(updateCustomerPanelLayout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(jButton12UpdateCusSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(updateCustomerPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton4UpdateCustomerDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(250, 250, 250)
                                .addComponent(jButton5UpdateCustomerUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        updateCustomerPanelLayout.setVerticalGroup(
            updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateCustomerPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1UpdateEnterCustomerNIC)
                    .addComponent(txtUpdateEnterCustomerNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12UpdateCusSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4UpdateCustomerName)
                    .addComponent(txtUpdateCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCusEventIDlbl)
                    .addComponent(updateCusEventID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4CustomerAddress)
                    .addComponent(txtUpdateCustomerAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10CustomerTel)
                    .addComponent(txtUpdateCustomerTel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4UpdateCustomerDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5UpdateCustomerUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(updateCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UpdateCusReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCusNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCusBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
        );

        jTabbedPaneCustomer.addTab("Update Customer", updateCustomerPanel);

        javax.swing.GroupLayout customerPanelLayout = new javax.swing.GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        customerPanelLayout.setHorizontalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
        );
        customerPanelLayout.setVerticalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
        );

        foodPackagesPanel.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPaneFoodPackages.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneFoodPackages.setPreferredSize(new java.awt.Dimension(830, 548));

        RegFoodPackagesPanel.setBackground(new java.awt.Color(255, 255, 255));
        RegFoodPackagesPanel.setPreferredSize(new java.awt.Dimension(830, 550));
        RegFoodPackagesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectRFPAdd.setBackground(new java.awt.Color(51, 153, 255));
        selectRFPAdd.setText("Add Food Package");
        selectRFPAdd.setPreferredSize(new java.awt.Dimension(140, 25));
        selectRFPAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selectRFPAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selectRFPAddMouseExited(evt);
            }
        });
        selectRFPAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectRFPAddActionPerformed(evt);
            }
        });
        RegFoodPackagesPanel.add(selectRFPAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, -1, -1));

        regFPReset.setBackground(new java.awt.Color(51, 153, 255));
        regFPReset.setText("Reset");
        regFPReset.setPreferredSize(new java.awt.Dimension(80, 25));
        regFPReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                regFPResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                regFPResetMouseExited(evt);
            }
        });
        regFPReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regFPResetActionPerformed(evt);
            }
        });
        RegFoodPackagesPanel.add(regFPReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 460, -1, -1));

        jList3.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Welcome Drink", "Fried Rice (Keeri Samba)", "Plain Rice", "Noodles", "Vegetable Noodles", "Deviled Chicken", "Fish Ambulthiyal", "Cashew Green Peas Curry", "Potato Tempered", "Brinjal Moju", "Malay Pickle", "Egg Salad", "Fish Cutlets", "Papadam", "Watalappam/Caramel Pudding", "Ice Cream (Vanilla)", "Fruit Salad", "Jelly" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane20.setViewportView(jList3);

        RegFoodPackagesPanel.add(jScrollPane20, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 110, 150, 230));

        jList4.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Welcome Drink", "Fried Rice (Keeri Samba)", "Plain Rice", "Deviled Chicken/Curry", "Fish Ambulthiyal", "Potato Tempered", "Dhal Curry", "Brinjal Moju", "Malay Pickle", "Vegetable Salad", "Fish Cutlets", "Papadam", "Egg Salad", "Watalappam", "Ice Cream (Vanilla)", "Jelly" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane21.setViewportView(jList4);

        RegFoodPackagesPanel.add(jScrollPane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 110, 150, 230));

        jList5.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Welcome Drink (Orange/Strawberry Gava)", "Fried Rice/Mixed Fried Rice (Basmathi)", "Steam Rice/ V: Noodles", "Pasta with Cheese Sauce", "Deviled Chicken", "Pork Black Curry", "Fish Stew/Fish Sweet Sauce", "Brinjal Pahi", "Pineapple Red Curry", "Mix Vegetable Curry", "Battered Mushrooms", "Chicken Salad", "Raita Salad", "Egg Salad", "Chilli Paste", "Fruit Trifle", "Chocolate Biscuit Pudding", "Cut Fruits", "Ice Cream (Vanilla)", "Jelly" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane22.setViewportView(jList5);

        RegFoodPackagesPanel.add(jScrollPane22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 150, 230));

        jList6.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Welcome Drink", "Fried Rice (Keeri Samba)", "Plain Rice", "Red Rice", "Spicy Noodles", "Deviled Chicken/", "Chicken Black Curry", "Fish Ambulthiyal", "Potato Tempered", "Cashew Green Peas Curry", "Maldive Fish Salad", "Malay Pickle/Sinhala Pickel", "Mix Vegetable Salad", "Egg Salad", "Fish Cutlets", "Watalappam or Fruit Salad", "Ice Cream (Vanilla)", "Coffee Caramel", "Jelly" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane23.setViewportView(jList6);

        RegFoodPackagesPanel.add(jScrollPane23, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 150, 230));

        jList7.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Welcome Drink", "Fried Rice (Basmathi)", "Plain Rice (Basmathi)", "Macaroni with Chili Sauce", "Chicken Black Curry", "Devilled Prawns", "Fish Stew/Ambulthiyal", "Potato Tempered", "Cashew Green Peas Curry", "Brinjal Moju", "Malay Pickle", "Mushrooms with Garlic Sauce", "Russian Salad", "Fish Cutlets", "Egg Salad on Mirror", "Watalappam", "Coffee Caramel/Chocolate Mousse", "Cut Fruits", "Jelly", "Ice Cream (Vanilla)" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane24.setViewportView(jList7);

        RegFoodPackagesPanel.add(jScrollPane24, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 150, 230));

        jLabel5.setText("Package E - Rs.3670 per plate");
        RegFoodPackagesPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 150, 20));

        jLabel6.setText("Package A - Rs.4450 per plate");
        RegFoodPackagesPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 150, 20));

        jLabel7.setText("Package B - Rs.3460 per plate");
        RegFoodPackagesPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 150, 20));

        jLabel8.setText("Package C - Rs.4590 per plate");
        RegFoodPackagesPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 150, 20));

        jLabel9.setText("Package D - Rs.4520 per plate");
        RegFoodPackagesPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 150, 20));

        regFPEventID.setText("Event ID : ");
        RegFoodPackagesPanel.add(regFPEventID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));
        RegFoodPackagesPanel.add(regFPEnterEventIDlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 90, 20));

        regFPCustomerNIC.setText("Customer NIC : ");
        RegFoodPackagesPanel.add(regFPCustomerNIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));
        RegFoodPackagesPanel.add(regFPEnterCustomerNIClbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 100, 20));

        regFpackageNolbl.setText("Package No : ");
        RegFoodPackagesPanel.add(regFpackageNolbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        jcomRegFPSelecPack.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Package", "Package A", "Package B", "Package C", "Package D", "Package E" }));
        RegFoodPackagesPanel.add(jcomRegFPSelecPack, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 150, 30));

        jLabel17.setText("Number of plates : ");
        RegFoodPackagesPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, -1));

        txtFPNoOfPlates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFPNoOfPlatesActionPerformed(evt);
            }
        });
        RegFoodPackagesPanel.add(txtFPNoOfPlates, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 50, 20));

        regFPBack.setBackground(new java.awt.Color(51, 153, 255));
        regFPBack.setText("Back");
        regFPBack.setPreferredSize(new java.awt.Dimension(80, 25));
        regFPBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                regFPBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                regFPBackMouseExited(evt);
            }
        });
        regFPBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regFPBackActionPerformed(evt);
            }
        });
        RegFoodPackagesPanel.add(regFPBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, -1, -1));

        regFPNext.setBackground(new java.awt.Color(51, 153, 255));
        regFPNext.setText("Next");
        regFPNext.setPreferredSize(new java.awt.Dimension(80, 25));
        regFPNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                regFPNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                regFPNextMouseExited(evt);
            }
        });
        regFPNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regFPNextActionPerformed(evt);
            }
        });
        RegFoodPackagesPanel.add(regFPNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 460, -1, -1));

        regFPAddDecor.setBackground(new java.awt.Color(51, 153, 255));
        regFPAddDecor.setText("Add Decoration");
        regFPAddDecor.setPreferredSize(new java.awt.Dimension(125, 25));
        regFPAddDecor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                regFPAddDecorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                regFPAddDecorMouseExited(evt);
            }
        });
        regFPAddDecor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regFPAddDecorActionPerformed(evt);
            }
        });
        RegFoodPackagesPanel.add(regFPAddDecor, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, -1, -1));

        regFPAddCusFP.setBackground(new java.awt.Color(51, 153, 255));
        regFPAddCusFP.setText("Add Customized Food Package");
        regFPAddCusFP.setPreferredSize(new java.awt.Dimension(200, 25));
        regFPAddCusFP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                regFPAddCusFPMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                regFPAddCusFPMouseExited(evt);
            }
        });
        regFPAddCusFP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regFPAddCusFPActionPerformed(evt);
            }
        });
        RegFoodPackagesPanel.add(regFPAddCusFP, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, -1, -1));

        regFPSetTotalPrice.setBackground(new java.awt.Color(51, 153, 255));
        regFPSetTotalPrice.setText("Set Total Price");
        regFPSetTotalPrice.setPreferredSize(new java.awt.Dimension(120, 25));
        regFPSetTotalPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                regFPSetTotalPriceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                regFPSetTotalPriceMouseExited(evt);
            }
        });
        regFPSetTotalPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regFPSetTotalPriceActionPerformed(evt);
            }
        });
        RegFoodPackagesPanel.add(regFPSetTotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, -1, -1));
        RegFoodPackagesPanel.add(regFPTotalPricelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 350, 110, 30));

        regFPAddPay.setBackground(new java.awt.Color(51, 153, 255));
        regFPAddPay.setText("Add Payment");
        regFPAddPay.setPreferredSize(new java.awt.Dimension(110, 25));
        regFPAddPay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                regFPAddPayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                regFPAddPayMouseExited(evt);
            }
        });
        regFPAddPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regFPAddPayActionPerformed(evt);
            }
        });
        RegFoodPackagesPanel.add(regFPAddPay, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 410, -1, -1));

        hallPricerfp.setEnabled(false);
        RegFoodPackagesPanel.add(hallPricerfp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 40, 20));

        regFPDemo.setBackground(new java.awt.Color(51, 153, 255));
        regFPDemo.setText("Demo");
        regFPDemo.setPreferredSize(new java.awt.Dimension(80, 25));
        regFPDemo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                regFPDemoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                regFPDemoMouseExited(evt);
            }
        });
        RegFoodPackagesPanel.add(regFPDemo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jTabbedPaneFoodPackages.addTab("Select Regular Food Packages", RegFoodPackagesPanel);

        CusFoodPackagesPanel.setBackground(new java.awt.Color(255, 255, 255));
        CusFoodPackagesPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jLabel3.setText("Customer NIC : ");

        addCusFPAdd.setBackground(new java.awt.Color(51, 153, 255));
        addCusFPAdd.setText("Add Food Package");
        addCusFPAdd.setPreferredSize(new java.awt.Dimension(150, 25));
        addCusFPAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addCusFPAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addCusFPAddMouseExited(evt);
            }
        });
        addCusFPAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCusFPAddActionPerformed(evt);
            }
        });

        cusFPReset.setBackground(new java.awt.Color(51, 153, 255));
        cusFPReset.setText("Reset");
        cusFPReset.setPreferredSize(new java.awt.Dimension(80, 25));
        cusFPReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cusFPResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cusFPResetMouseExited(evt);
            }
        });
        cusFPReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusFPResetActionPerformed(evt);
            }
        });

        addCusFPEventIDlbl.setText("Event ID : ");

        customizedFPAdd.setBackground(new java.awt.Color(51, 153, 255));
        customizedFPAdd.setText("ADD");
        customizedFPAdd.setPreferredSize(new java.awt.Dimension(80, 25));
        customizedFPAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                customizedFPAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                customizedFPAddMouseExited(evt);
            }
        });
        customizedFPAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customizedFPAddActionPerformed(evt);
            }
        });

        addCusFPBack.setBackground(new java.awt.Color(51, 153, 255));
        addCusFPBack.setText("Back");
        addCusFPBack.setPreferredSize(new java.awt.Dimension(80, 25));
        addCusFPBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addCusFPBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addCusFPBackMouseExited(evt);
            }
        });
        addCusFPBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCusFPBackActionPerformed(evt);
            }
        });

        addCusFPNext.setBackground(new java.awt.Color(51, 153, 255));
        addCusFPNext.setText("Next");
        addCusFPNext.setPreferredSize(new java.awt.Dimension(80, 25));
        addCusFPNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addCusFPNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addCusFPNextMouseExited(evt);
            }
        });
        addCusFPNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCusFPNextActionPerformed(evt);
            }
        });

        selectedCusFoodItemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Food Item", "Price per plate"
            }
        ));
        jScrollPane11.setViewportView(selectedCusFoodItemTable);
        if (selectedCusFoodItemTable.getColumnModel().getColumnCount() > 0) {
            selectedCusFoodItemTable.getColumnModel().getColumn(1).setHeaderValue("Max num of guests");
        }

        viewCusFPTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Welcome Drink", "80.00"},
                {"Fried Rice (Basmathi)", "200.00"},
                {"Plain Rice (Basmathi)", "110.00"},
                {"Steam Rice", "100.00"},
                {"Macaroni with Chili Sauce", "140.00"},
                {"Chicken Black Curry", "150.00"},
                {"Fish Stew/Fish Sweet Sauce", "120.00"},
                {"Fish Stew/Ambulthiyal", "120.00"},
                {"Brinjal Pahi", "110.00"},
                {"Pineapple Red Curry", "90.00"},
                {"Chicken Salad", "120.00"},
                {"Raita Salad", "100.00"},
                {"Chilli Paste", "70.00"},
                {"Fruit Trifle", "80.00"},
                {"Chocolate Biscuit Pudding", "110.00"},
                {"Cut Fruits", "150.00"}
            },
            new String [] {
                "Food Items", "Price per plate (Rs)"
            }
        ));
        jScrollPane16.setViewportView(viewCusFPTable);

        customizedFPRemove.setBackground(new java.awt.Color(51, 153, 255));
        customizedFPRemove.setText("Remove");
        customizedFPRemove.setPreferredSize(new java.awt.Dimension(80, 25));
        customizedFPRemove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                customizedFPRemoveMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                customizedFPRemoveMouseExited(evt);
            }
        });
        customizedFPRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customizedFPRemoveActionPerformed(evt);
            }
        });

        jLabel29.setText("Number of Plates : ");

        txtCusFPNoOfPlates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCusFPNoOfPlatesActionPerformed(evt);
            }
        });

        cusFPAddDecor.setBackground(new java.awt.Color(51, 153, 255));
        cusFPAddDecor.setText("Add Decoration");
        cusFPAddDecor.setPreferredSize(new java.awt.Dimension(150, 25));
        cusFPAddDecor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cusFPAddDecorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cusFPAddDecorMouseExited(evt);
            }
        });
        cusFPAddDecor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusFPAddDecorActionPerformed(evt);
            }
        });

        jLabel31.setText("Price per Plate : ");

        cusFPOk.setBackground(new java.awt.Color(51, 153, 255));
        cusFPOk.setText("OK");
        cusFPOk.setPreferredSize(new java.awt.Dimension(80, 25));
        cusFPOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cusFPOkMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cusFPOkMouseExited(evt);
            }
        });
        cusFPOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusFPOkActionPerformed(evt);
            }
        });

        jLabel33.setText("Total Food Package Price : ");

        hallPricefp.setEnabled(false);

        cusFPAddPay.setBackground(new java.awt.Color(51, 153, 255));
        cusFPAddPay.setText("Add Payment");
        cusFPAddPay.setPreferredSize(new java.awt.Dimension(110, 25));
        cusFPAddPay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cusFPAddPayMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cusFPAddPayMouseExited(evt);
            }
        });
        cusFPAddPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusFPAddPayActionPerformed(evt);
            }
        });

        cusFPDemo.setBackground(new java.awt.Color(51, 153, 255));
        cusFPDemo.setText("Demo");
        cusFPDemo.setPreferredSize(new java.awt.Dimension(80, 25));
        cusFPDemo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cusFPDemoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cusFPDemoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout CusFoodPackagesPanelLayout = new javax.swing.GroupLayout(CusFoodPackagesPanel);
        CusFoodPackagesPanel.setLayout(CusFoodPackagesPanelLayout);
        CusFoodPackagesPanelLayout.setHorizontalGroup(
            CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CusFoodPackagesPanelLayout.createSequentialGroup()
                .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CusFoodPackagesPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel31)
                            .addGroup(CusFoodPackagesPanelLayout.createSequentialGroup()
                                .addComponent(cusFPDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addCusFPAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addCusPricePerPlatelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cusFPAddDecor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(CusFoodPackagesPanelLayout.createSequentialGroup()
                        .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(CusFoodPackagesPanelLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(addCusFPEventIDlbl)
                                .addGap(18, 18, 18)
                                .addComponent(cusFPEventID, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cusFPCustomerNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(CusFoodPackagesPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(customizedFPRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cusFPOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(customizedFPAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CusFoodPackagesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(47, 47, 47)
                        .addComponent(txtCusFPNoOfPlates, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CusFoodPackagesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addCusTotalFPPricelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cusFPAddPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CusFoodPackagesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hallPricefp, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CusFoodPackagesPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addCusFPBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(addCusFPNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(cusFPReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );
        CusFoodPackagesPanelLayout.setVerticalGroup(
            CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CusFoodPackagesPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addCusFPEventIDlbl)
                    .addComponent(jLabel3)
                    .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29)
                        .addComponent(txtCusFPNoOfPlates, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cusFPCustomerNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusFPEventID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CusFoodPackagesPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(customizedFPAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(customizedFPRemove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(cusFPOk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CusFoodPackagesPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CusFoodPackagesPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CusFoodPackagesPanelLayout.createSequentialGroup()
                                .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(addCusPricePerPlatelbl, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cusFPDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addCusFPAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cusFPAddDecor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cusFPAddPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))
                            .addComponent(jLabel33)))
                    .addGroup(CusFoodPackagesPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(addCusTotalFPPricelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(CusFoodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCusFPBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCusFPNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cusFPReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(hallPricefp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneFoodPackages.addTab("Add customize Food Packages", CusFoodPackagesPanel);

        javax.swing.GroupLayout foodPackagesPanelLayout = new javax.swing.GroupLayout(foodPackagesPanel);
        foodPackagesPanel.setLayout(foodPackagesPanelLayout);
        foodPackagesPanelLayout.setHorizontalGroup(
            foodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(foodPackagesPanelLayout.createSequentialGroup()
                .addComponent(jTabbedPaneFoodPackages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );
        foodPackagesPanelLayout.setVerticalGroup(
            foodPackagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneFoodPackages, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
        );

        decorationsPanel.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPaneDecorations.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneDecorations.setPreferredSize(new java.awt.Dimension(830, 548));

        addDecorationsPanel.setBackground(new java.awt.Color(255, 255, 255));
        addDecorationsPanel.setPreferredSize(new java.awt.Dimension(830, 550));
        addDecorationsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1AddDecorationColor.setText("Theme Color");
        addDecorationsPanel.add(jLabel1AddDecorationColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 70, -1, -1));

        addDecorColor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Color", "Red", "Blue", "Green", "Pink", "Purple", "White", "Yellow", "Orange", "Gold", "Silver", "Magenta", "Black" }));
        addDecorationsPanel.add(addDecorColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 96, 28));

        txtAreaDescription.setColumns(20);
        txtAreaDescription.setRows(5);
        jScrollPane9.setViewportView(txtAreaDescription);

        addDecorationsPanel.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 220, 119));

        jLabel1AddDecorationDescription.setText("Description");
        addDecorationsPanel.add(jLabel1AddDecorationDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 109, -1, -1));

        jLabel1AddDecorationTypes.setText("Decoration Types");
        addDecorationsPanel.add(jLabel1AddDecorationTypes, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, -1, -1));

        jCheckBox1AddTableDecorations.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1AddTableDecorations.setText("Table Decorations");
        addDecorationsPanel.add(jCheckBox1AddTableDecorations, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, -1, -1));

        jCheckBox2AddWallDecorations.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox2AddWallDecorations.setText("Wall Decorations");
        jCheckBox2AddWallDecorations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2AddWallDecorationsActionPerformed(evt);
            }
        });
        addDecorationsPanel.add(jCheckBox2AddWallDecorations, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, -1, -1));

        jCheckBox3AddFlowerDecoration.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox3AddFlowerDecoration.setText("Flower Decorations");
        addDecorationsPanel.add(jCheckBox3AddFlowerDecoration, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, -1, -1));

        jCheckBox4AddDiscoLight.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox4AddDiscoLight.setText("Disco Light");
        addDecorationsPanel.add(jCheckBox4AddDiscoLight, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, -1, -1));

        jCheckBox5AddEntranceArch.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox5AddEntranceArch.setText("Entrance Arch");
        addDecorationsPanel.add(jCheckBox5AddEntranceArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, -1, -1));

        jLabel1AddSpecialRequirements.setText("Special Requirements");
        addDecorationsPanel.add(jLabel1AddSpecialRequirements, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, -1, -1));

        txtAreaSpecialRequirements.setColumns(20);
        txtAreaSpecialRequirements.setRows(5);
        jScrollPane10.setViewportView(txtAreaSpecialRequirements);

        addDecorationsPanel.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 220, 123));

        jButton4AddDecorationDemo.setBackground(new java.awt.Color(51, 153, 255));
        jButton4AddDecorationDemo.setText("Demo");
        jButton4AddDecorationDemo.setPreferredSize(new java.awt.Dimension(80, 25));
        addDecorationsPanel.add(jButton4AddDecorationDemo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        jButton7AddDecoration.setBackground(new java.awt.Color(51, 153, 255));
        jButton7AddDecoration.setText("Add Decoration");
        jButton7AddDecoration.setPreferredSize(new java.awt.Dimension(120, 25));
        jButton7AddDecoration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7AddDecorationActionPerformed(evt);
            }
        });
        addDecorationsPanel.add(jButton7AddDecoration, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, -1, -1));

        jButton8AddDecorReset.setBackground(new java.awt.Color(51, 153, 255));
        jButton8AddDecorReset.setText("Reset");
        jButton8AddDecorReset.setPreferredSize(new java.awt.Dimension(80, 25));
        addDecorationsPanel.add(jButton8AddDecorReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, -1, -1));

        jLabel21.setText("Event ID : ");
        addDecorationsPanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 21, -1, -1));
        addDecorationsPanel.add(addDecorEventIDlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 80, 20));

        jLabel23.setText("Customer NIC : ");
        addDecorationsPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 21, -1, -1));
        addDecorationsPanel.add(addDecorCusNIClbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 80, 20));

        addDecorNext.setBackground(new java.awt.Color(51, 153, 255));
        addDecorNext.setText("Next");
        addDecorNext.setPreferredSize(new java.awt.Dimension(80, 25));
        addDecorNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDecorNextActionPerformed(evt);
            }
        });
        addDecorationsPanel.add(addDecorNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 470, -1, -1));

        addDecorBack.setBackground(new java.awt.Color(51, 153, 255));
        addDecorBack.setText("Back");
        addDecorBack.setPreferredSize(new java.awt.Dimension(80, 25));
        addDecorBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDecorBackActionPerformed(evt);
            }
        });
        addDecorationsPanel.add(addDecorBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 470, -1, -1));

        jLabel22.setText("Special Requirements Price:");
        addDecorationsPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, -1, -1));
        addDecorationsPanel.add(txtAddDecorSRPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 88, 29));

        jLabel24.setText("Decoration Price :");
        addDecorationsPanel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));
        addDecorationsPanel.add(txtAddDecorPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 101, 31));

        addDecorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Decor ID", "Event ID", "Customer NIC", "Theme Color", "Description", "Special Req", "Decor Price", "Special Req Price", "Total"
            }
        ));
        addDecorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addDecorTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(addDecorTable);

        addDecorationsPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 790, 110));

        addDecorAddPayment.setBackground(new java.awt.Color(51, 153, 255));
        addDecorAddPayment.setText("Add Payment");
        addDecorAddPayment.setPreferredSize(new java.awt.Dimension(120, 25));
        addDecorAddPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDecorAddPaymentActionPerformed(evt);
            }
        });
        addDecorationsPanel.add(addDecorAddPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 430, -1, -1));

        jButton5.setBackground(new java.awt.Color(51, 153, 255));
        jButton5.setText("Total");
        jButton5.setPreferredSize(new java.awt.Dimension(90, 25));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        addDecorationsPanel.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 250, -1, -1));
        addDecorationsPanel.add(addDecorTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 90, 30));

        hallpricedecor.setEnabled(false);
        addDecorationsPanel.add(hallpricedecor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 50, 20));

        fpPricedecor.setEnabled(false);
        addDecorationsPanel.add(fpPricedecor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 500, 50, 20));

        jTabbedPaneDecorations.addTab("Add Decorations", addDecorationsPanel);

        updateDecorationsPanel.setBackground(new java.awt.Color(255, 255, 255));
        updateDecorationsPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jLabel1UpdateDecorationEventID.setText("Enter Customer NIC");

        updateDecorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Theme Color", "Description", "Table Decoration", "Wall Decoration", "Flower Decoration", "Disco Light", "Entrance Arch", "Special Requirements", "special req price", "Decor Price"
            }
        ));
        updateDecorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateDecorTableMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(updateDecorTable);

        jButton4UpdateDecorationDemo.setBackground(new java.awt.Color(51, 153, 255));
        jButton4UpdateDecorationDemo.setText("Demo");
        jButton4UpdateDecorationDemo.setPreferredSize(new java.awt.Dimension(80, 25));
        jButton4UpdateDecorationDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4UpdateDecorationDemoActionPerformed(evt);
            }
        });

        updateDecorReset.setBackground(new java.awt.Color(51, 153, 255));
        updateDecorReset.setText("Reset");
        updateDecorReset.setPreferredSize(new java.awt.Dimension(80, 25));
        updateDecorReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDecorResetActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("Decoration ID");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));
        jPanel3.add(updateDecorIDlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, 20));

        jLabel16.setText("Decor Event ID");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));
        jPanel3.add(updateDecorEventIDlb, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 90, 20));

        jLabel18.setText("Theme Color");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jComboUpdateDecorColor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Color", "Red", "Blue", "Green", "Pink", "Purple", "White", "Yellow", "Orange", "Gold", "Silver", "Magenta", "Black" }));
        jPanel3.add(jComboUpdateDecorColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 100, 30));

        jLabel19.setText("Description");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jTextAreaUpdatedecorDes.setColumns(20);
        jTextAreaUpdatedecorDes.setRows(5);
        jScrollPane19.setViewportView(jTextAreaUpdatedecorDes);

        jPanel3.add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 180, 120));

        jLabel20.setText("Special Requirements");
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        jTextAreaUpdateDecorSR.setColumns(20);
        jTextAreaUpdateDecorSR.setRows(5);
        jScrollPane25.setViewportView(jTextAreaUpdateDecorSR);

        jPanel3.add(jScrollPane25, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, 190, 110));

        jLabel11.setText("Decoration Types");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        jCheckBox1UpdateTable.setText("Table Decoration");
        jPanel3.add(jCheckBox1UpdateTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, -1, 20));

        jCheckBox2UpdateWall.setText("Wall Decoration");
        jPanel3.add(jCheckBox2UpdateWall, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 110, 20));

        jCheckBox3UpdateFlower.setText("Flower Decoration");
        jPanel3.add(jCheckBox3UpdateFlower, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 120, 20));

        jCheckBox4UpdateDisco.setText("Disco Light");
        jPanel3.add(jCheckBox4UpdateDisco, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 110, 20));

        jCheckBox5UpdateArch.setText("Entrance Arch");
        jPanel3.add(jCheckBox5UpdateArch, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 120, 20));

        updateDecorSRPricelbl.setText("Special Requirement Price");
        jPanel3.add(updateDecorSRPricelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, -1, -1));

        txtUpdateDecorSRPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUpdateDecorSRPriceActionPerformed(evt);
            }
        });
        jPanel3.add(txtUpdateDecorSRPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 90, 30));

        updateDecorPricelbl.setText("Decoration Price");
        jPanel3.add(updateDecorPricelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));
        jPanel3.add(txtUpdateDecorPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 80, 30));

        jButton6.setBackground(new java.awt.Color(51, 153, 255));
        jButton6.setText("Total");
        jButton6.setPreferredSize(new java.awt.Dimension(80, 25));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));
        jPanel3.add(updateDecorTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 80, 30));

        updateDecorSearch.setBackground(new java.awt.Color(51, 153, 255));
        updateDecorSearch.setText("Search");
        updateDecorSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDecorSearchActionPerformed(evt);
            }
        });

        updateDecorBack.setBackground(new java.awt.Color(51, 153, 255));
        updateDecorBack.setText("Back");
        updateDecorBack.setPreferredSize(new java.awt.Dimension(80, 25));
        updateDecorBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDecorBackActionPerformed(evt);
            }
        });

        updateDecorNext.setBackground(new java.awt.Color(51, 153, 255));
        updateDecorNext.setText("Next");
        updateDecorNext.setPreferredSize(new java.awt.Dimension(80, 25));
        updateDecorNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDecorNextActionPerformed(evt);
            }
        });

        jButton9UpdateDecoration.setBackground(new java.awt.Color(51, 153, 255));
        jButton9UpdateDecoration.setText("Update Decoration");
        jButton9UpdateDecoration.setPreferredSize(new java.awt.Dimension(125, 25));
        jButton9UpdateDecoration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9UpdateDecorationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateDecorationsPanelLayout = new javax.swing.GroupLayout(updateDecorationsPanel);
        updateDecorationsPanel.setLayout(updateDecorationsPanelLayout);
        updateDecorationsPanelLayout.setHorizontalGroup(
            updateDecorationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateDecorationsPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(updateDecorationsPanelLayout.createSequentialGroup()
                .addGroup(updateDecorationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateDecorationsPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel1UpdateDecorationEventID)
                        .addGap(35, 35, 35)
                        .addComponent(txtUpdateDecorationEventID, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(updateDecorSearch))
                    .addGroup(updateDecorationsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(updateDecorationsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4UpdateDecorationDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183)
                        .addComponent(jButton9UpdateDecoration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateDecorationsPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(updateDecorBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(updateDecorNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(updateDecorReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        updateDecorationsPanelLayout.setVerticalGroup(
            updateDecorationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateDecorationsPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(updateDecorationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1UpdateDecorationEventID)
                    .addComponent(txtUpdateDecorationEventID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateDecorSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(updateDecorationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4UpdateDecorationDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9UpdateDecoration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(updateDecorationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateDecorBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateDecorNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateDecorReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jTabbedPaneDecorations.addTab("Update Decorations", updateDecorationsPanel);

        javax.swing.GroupLayout decorationsPanelLayout = new javax.swing.GroupLayout(decorationsPanel);
        decorationsPanel.setLayout(decorationsPanelLayout);
        decorationsPanelLayout.setHorizontalGroup(
            decorationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decorationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneDecorations, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                .addContainerGap())
        );
        decorationsPanelLayout.setVerticalGroup(
            decorationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(decorationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneDecorations, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        paymentPanel.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPanePayments.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPanePayments.setPreferredSize(new java.awt.Dimension(830, 548));

        addPaymentsPanel.setBackground(new java.awt.Color(255, 255, 255));
        addPaymentsPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jLabel2AddPaymentMethod.setText("Payment Method");

        jLabel3AddPaymentAmount.setText("Amount");

        jLabel4AddPaymentDescription.setText("Description");

        jComboPayMethods.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Payment Methods", "Bank", "Money" }));

        txtAreaAddPaymentDescription.setColumns(20);
        txtAreaAddPaymentDescription.setRows(5);
        jScrollPane13.setViewportView(txtAreaAddPaymentDescription);

        jLabel1.setText("Due Date");

        jButton4AddPaymentDemo.setBackground(new java.awt.Color(51, 153, 255));
        jButton4AddPaymentDemo.setText("Demo");
        jButton4AddPaymentDemo.setPreferredSize(new java.awt.Dimension(80, 25));
        jButton4AddPaymentDemo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4AddPaymentDemoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4AddPaymentDemoMouseExited(evt);
            }
        });

        jButton9AddPaymentAdd.setBackground(new java.awt.Color(51, 153, 255));
        jButton9AddPaymentAdd.setText("Add Payment");
        jButton9AddPaymentAdd.setPreferredSize(new java.awt.Dimension(120, 25));
        jButton9AddPaymentAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton9AddPaymentAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton9AddPaymentAddMouseExited(evt);
            }
        });
        jButton9AddPaymentAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9AddPaymentAddActionPerformed(evt);
            }
        });

        addPaymentReset.setBackground(new java.awt.Color(51, 153, 255));
        addPaymentReset.setText("Reset");
        addPaymentReset.setPreferredSize(new java.awt.Dimension(80, 25));
        addPaymentReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addPaymentResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addPaymentResetMouseExited(evt);
            }
        });
        addPaymentReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPaymentResetActionPerformed(evt);
            }
        });

        jLabel3AddPaymentCredit.setText("Credit/Debit");

        jComboPayCD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Debit", "Credit" }));

        addPayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Event ID", "Customer NIC", "Payment Method", "Amount", "Credit/Debit", "Due Date", "Description", "Balance"
            }
        ));
        jScrollPane14.setViewportView(addPayTable);

        addPayBack.setBackground(new java.awt.Color(51, 153, 255));
        addPayBack.setText("Back");
        addPayBack.setPreferredSize(new java.awt.Dimension(80, 25));
        addPayBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addPayBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addPayBackMouseExited(evt);
            }
        });
        addPayBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPayBackActionPerformed(evt);
            }
        });

        addPayNext.setBackground(new java.awt.Color(51, 153, 255));
        addPayNext.setText("Next");
        addPayNext.setPreferredSize(new java.awt.Dimension(80, 25));
        addPayNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addPayNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addPayNextMouseExited(evt);
            }
        });
        addPayNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPayNextActionPerformed(evt);
            }
        });

        jLabel25.setText("Event ID : ");

        jLabel27.setText("Customer NIC : ");

        addPayViewBookings.setBackground(new java.awt.Color(51, 153, 255));
        addPayViewBookings.setText("View Bookings");
        addPayViewBookings.setPreferredSize(new java.awt.Dimension(140, 25));
        addPayViewBookings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addPayViewBookingsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addPayViewBookingsMouseExited(evt);
            }
        });
        addPayViewBookings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPayViewBookingsActionPerformed(evt);
            }
        });

        addPayTotalFdAmount.setText("Total Food Package Amount : ");

        jLabel32.setText("Total Decoration Amount : ");

        jLabel34.setText("Total Hall Amount : ");

        addPayTot.setBackground(new java.awt.Color(51, 153, 255));
        addPayTot.setText("Total");
        addPayTot.setPreferredSize(new java.awt.Dimension(80, 25));
        addPayTot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addPayTotMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addPayTotMouseExited(evt);
            }
        });
        addPayTot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPayTotActionPerformed(evt);
            }
        });

        jLabel30.setText("Balance");

        javax.swing.GroupLayout addPaymentsPanelLayout = new javax.swing.GroupLayout(addPaymentsPanel);
        addPaymentsPanel.setLayout(addPaymentsPanelLayout);
        addPaymentsPanelLayout.setHorizontalGroup(
            addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14)
                .addContainerGap())
            .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addPayEventIDlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addPayTotalFdAmount)
                    .addComponent(jLabel3AddPaymentAmount)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3AddPaymentCredit)
                    .addComponent(jLabel2AddPaymentMethod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                        .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                                .addComponent(jComboPayMethods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(143, 143, 143)
                                .addComponent(jLabel4AddPaymentDescription))
                            .addComponent(jComboPayCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDatePayDueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                        .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                                .addComponent(txtAddPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addPayTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addPayTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addPayBalancelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                                    .addGap(76, 76, 76)
                                    .addComponent(jLabel27)
                                    .addGap(26, 26, 26)
                                    .addComponent(addPayCusNIClbl, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                                    .addComponent(addPayTotalfoodAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel32)
                                    .addGap(52, 52, 52)
                                    .addComponent(addPayTotalDecorAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel34)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(addPayTotalHallAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(29, Short.MAX_VALUE))))
            .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4AddPaymentDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(170, 170, 170)
                .addComponent(jButton9AddPaymentAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                        .addComponent(addPayBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(addPayNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(addPaymentReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addPayViewBookings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addPaymentsPanelLayout.setVerticalGroup(
            addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel27)))
                    .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addPayCusNIClbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPaymentsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addPayEventIDlbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addPayTotalfoodAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPayTotalFdAmount)
                    .addComponent(jLabel32)
                    .addComponent(addPayTotalDecorAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(addPayTotalHallAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3AddPaymentAmount)
                            .addComponent(txtAddPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addPayTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(addPayTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addPayBalancelbl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPaymentsPanelLayout.createSequentialGroup()
                        .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboPayMethods, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4AddPaymentDescription)
                            .addComponent(jLabel2AddPaymentMethod))
                        .addGap(18, 18, 18)
                        .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboPayCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3AddPaymentCredit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jDatePayDueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4AddPaymentDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9AddPaymentAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPayViewBookings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addPaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addPayBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPayNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPaymentReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPanePayments.addTab("Add Payment", addPaymentsPanel);

        updatePaymentsPanel.setBackground(new java.awt.Color(255, 255, 255));
        updatePaymentsPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jLabel2UpdatePaymentEventID.setText("Enter Customer NIC");

        updatePayTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Event ID", "Customer ID", "Payment Method", "Amount", "Credit/Debit", "Balance", "Due Date", "Description"
            }
        ));
        updatePayTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updatePayTableMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(updatePayTable);
        if (updatePayTable.getColumnModel().getColumnCount() > 0) {
            updatePayTable.getColumnModel().getColumn(1).setHeaderValue("Customer ID");
        }

        jButton4UpdatePaymentDemo.setBackground(new java.awt.Color(51, 153, 255));
        jButton4UpdatePaymentDemo.setText("Demo");
        jButton4UpdatePaymentDemo.setPreferredSize(new java.awt.Dimension(90, 25));
        jButton4UpdatePaymentDemo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton4UpdatePaymentDemoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton4UpdatePaymentDemoMouseExited(evt);
            }
        });

        updatePayReset.setBackground(new java.awt.Color(51, 153, 255));
        updatePayReset.setText("Reset");
        updatePayReset.setPreferredSize(new java.awt.Dimension(90, 25));
        updatePayReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updatePayResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updatePayResetMouseExited(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        UpdatePaymentEventID.setText("Event ID");
        jPanel2.add(UpdatePaymentEventID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));
        jPanel2.add(updatePaymentEventIDlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 70, 20));

        jLabel13.setText("Payment Method");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        jComboUpdatePayMethod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboUpdatePayMethod, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 120, 30));

        jLabel14.setText("Amount");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));
        jPanel2.add(txtUpdatePayAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 120, 30));

        jLabel15.setText("Credit/Debit");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, -1));

        jComboUpdateCD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Debit", "Credit" }));
        jPanel2.add(jComboUpdateCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 110, 30));

        updatePayementDeslbl.setText("Description");
        jPanel2.add(updatePayementDeslbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, -1, -1));

        txtAreaUpdatePaymentDescription.setColumns(20);
        txtAreaUpdatePaymentDescription.setRows(5);
        jScrollPane7.setViewportView(txtAreaUpdatePaymentDescription);

        jPanel2.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, -1, -1));

        updatePaymentDueDatelbl.setText("Due Date");
        jPanel2.add(updatePaymentDueDatelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, -1, -1));
        jPanel2.add(jDateChooser2UpdatePayDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 110, 30));

        jLabel28.setText("Balance");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, -1, -1));
        jPanel2.add(txtUpdatePayBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 110, 30));

        updatePaymentSearch.setBackground(new java.awt.Color(51, 153, 255));
        updatePaymentSearch.setText("Search");
        updatePaymentSearch.setPreferredSize(new java.awt.Dimension(90, 25));
        updatePaymentSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updatePaymentSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updatePaymentSearchMouseExited(evt);
            }
        });
        updatePaymentSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePaymentSearchActionPerformed(evt);
            }
        });

        updatePayNext.setBackground(new java.awt.Color(51, 153, 255));
        updatePayNext.setText("Next");
        updatePayNext.setPreferredSize(new java.awt.Dimension(90, 25));
        updatePayNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updatePayNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updatePayNextMouseExited(evt);
            }
        });
        updatePayNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePayNextActionPerformed(evt);
            }
        });

        updatePayBack.setBackground(new java.awt.Color(51, 153, 255));
        updatePayBack.setText("Back");
        updatePayBack.setPreferredSize(new java.awt.Dimension(90, 25));
        updatePayBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updatePayBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updatePayBackMouseExited(evt);
            }
        });
        updatePayBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePayBackActionPerformed(evt);
            }
        });

        jButton11UpdatePaymentUpdate.setBackground(new java.awt.Color(51, 153, 255));
        jButton11UpdatePaymentUpdate.setText("Update");
        jButton11UpdatePaymentUpdate.setPreferredSize(new java.awt.Dimension(90, 25));
        jButton11UpdatePaymentUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton11UpdatePaymentUpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton11UpdatePaymentUpdateMouseExited(evt);
            }
        });
        jButton11UpdatePaymentUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11UpdatePaymentUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updatePaymentsPanelLayout = new javax.swing.GroupLayout(updatePaymentsPanel);
        updatePaymentsPanel.setLayout(updatePaymentsPanelLayout);
        updatePaymentsPanelLayout.setHorizontalGroup(
            updatePaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatePaymentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(updatePaymentsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updatePaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
                    .addGroup(updatePaymentsPanelLayout.createSequentialGroup()
                        .addComponent(jButton4UpdatePaymentDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(262, 262, 262)
                        .addComponent(jButton11UpdatePaymentUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(updatePaymentsPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2UpdatePaymentEventID)
                .addGap(45, 45, 45)
                .addComponent(txtUpdatePaymentCusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(updatePaymentSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatePaymentsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updatePayBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(updatePayNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(updatePayReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        updatePaymentsPanelLayout.setVerticalGroup(
            updatePaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatePaymentsPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(updatePaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2UpdatePaymentEventID)
                    .addComponent(txtUpdatePaymentCusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatePaymentSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(updatePaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4UpdatePaymentDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11UpdatePaymentUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(updatePaymentsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updatePayReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatePayNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatePayBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        jTabbedPanePayments.addTab("Update Payment", updatePaymentsPanel);

        javax.swing.GroupLayout paymentPanelLayout = new javax.swing.GroupLayout(paymentPanel);
        paymentPanel.setLayout(paymentPanelLayout);
        paymentPanelLayout.setHorizontalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPanePayments, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                .addContainerGap())
        );
        paymentPanelLayout.setVerticalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPanePayments, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bookingsPanel.setBackground(new java.awt.Color(255, 255, 255));

        updateBookingsPanel.setBackground(new java.awt.Color(255, 255, 255));
        updateBookingsPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        UpdateBookingsEnterCusNIC.setText("Enter Customer NIC");

        eventBookingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Event ID", "Customer NIC", "Booking Date", "Booking Status", "Booking Time"
            }
        ));
        eventBookingTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eventBookingTableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(eventBookingTable);

        jButton9UpdateBookingsDemo.setBackground(new java.awt.Color(51, 153, 255));
        jButton9UpdateBookingsDemo.setText("Demo");
        jButton9UpdateBookingsDemo.setPreferredSize(new java.awt.Dimension(80, 25));
        jButton9UpdateBookingsDemo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton9UpdateBookingsDemoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton9UpdateBookingsDemoMouseExited(evt);
            }
        });
        jButton9UpdateBookingsDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9UpdateBookingsDemoActionPerformed(evt);
            }
        });

        updateBookingsReset.setBackground(new java.awt.Color(51, 153, 255));
        updateBookingsReset.setText("Reset");
        updateBookingsReset.setPreferredSize(new java.awt.Dimension(80, 25));
        updateBookingsReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateBookingsResetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateBookingsResetMouseExited(evt);
            }
        });
        updateBookingsReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookingsResetActionPerformed(evt);
            }
        });

        bookingSearch.setBackground(new java.awt.Color(51, 153, 255));
        bookingSearch.setText("Search");
        bookingSearch.setPreferredSize(new java.awt.Dimension(80, 25));
        bookingSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookingSearchMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookingSearchMouseExited(evt);
            }
        });
        bookingSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingSearchActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Event ID");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));
        jPanel1.add(bookingEventIDlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 110, 30));

        bookingDatelbl.setText("Booking Date");
        jPanel1.add(bookingDatelbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));
        jPanel1.add(jDateChooser2BookingDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 117, -1));

        jLabel12.setText("Status");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, -1, -1));

        jcomBookingStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pending", "Confirm" }));
        jPanel1.add(jcomBookingStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 129, 30));

        UpdateBookingsUpdate.setBackground(new java.awt.Color(51, 153, 255));
        UpdateBookingsUpdate.setText("Update");
        UpdateBookingsUpdate.setPreferredSize(new java.awt.Dimension(80, 25));
        UpdateBookingsUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                UpdateBookingsUpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                UpdateBookingsUpdateMouseExited(evt);
            }
        });
        UpdateBookingsUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateBookingsUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(UpdateBookingsUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, -1, -1));

        bookingNext.setBackground(new java.awt.Color(51, 153, 255));
        bookingNext.setText("Next");
        bookingNext.setPreferredSize(new java.awt.Dimension(80, 25));
        bookingNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookingNextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookingNextMouseExited(evt);
            }
        });
        bookingNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingNextActionPerformed(evt);
            }
        });

        bookingBack.setBackground(new java.awt.Color(51, 153, 255));
        bookingBack.setText("Back");
        bookingBack.setPreferredSize(new java.awt.Dimension(80, 25));
        bookingBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookingBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookingBackMouseExited(evt);
            }
        });
        bookingBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingBackActionPerformed(evt);
            }
        });

        bookingReports.setBackground(new java.awt.Color(51, 153, 255));
        bookingReports.setText("Reports");
        bookingReports.setPreferredSize(new java.awt.Dimension(80, 25));
        bookingReports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookingReportsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookingReportsMouseExited(evt);
            }
        });
        bookingReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingReportsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateBookingsPanelLayout = new javax.swing.GroupLayout(updateBookingsPanel);
        updateBookingsPanel.setLayout(updateBookingsPanelLayout);
        updateBookingsPanelLayout.setHorizontalGroup(
            updateBookingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateBookingsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bookingBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(bookingNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(updateBookingsReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(updateBookingsPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(UpdateBookingsEnterCusNIC)
                .addGap(60, 60, 60)
                .addComponent(txtUpdateBookingsCusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(bookingSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(updateBookingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateBookingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addGroup(updateBookingsPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(updateBookingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9UpdateBookingsDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bookingReports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(261, 261, 261))
        );
        updateBookingsPanelLayout.setVerticalGroup(
            updateBookingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateBookingsPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(updateBookingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UpdateBookingsEnterCusNIC)
                    .addComponent(txtUpdateBookingsCusNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookingSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(updateBookingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookingReports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9UpdateBookingsDemo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(updateBookingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookingBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookingNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBookingsReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout bookingsPanelLayout = new javax.swing.GroupLayout(bookingsPanel);
        bookingsPanel.setLayout(bookingsPanelLayout);
        bookingsPanelLayout.setHorizontalGroup(
            bookingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(updateBookingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                .addContainerGap())
        );
        bookingsPanelLayout.setVerticalGroup(
            bookingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(updateBookingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
        );

        reportsPanel.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPaneReports.setBackground(new java.awt.Color(255, 255, 255));

        generateReportsPanel.setBackground(new java.awt.Color(255, 255, 255));
        generateReportsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(153, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bookingReport.setText("Generate Booking Report");
        bookingReport.setPreferredSize(new java.awt.Dimension(120, 25));
        bookingReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookingReportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookingReportMouseExited(evt);
            }
        });
        bookingReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingReportActionPerformed(evt);
            }
        });
        jPanel4.add(bookingReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 160, 30));

        jLabel35.setText("Date from");
        jPanel4.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, -1, -1));

        jLabel36.setText("Date to");
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, -1, -1));
        jPanel4.add(jDateChooserReportsDate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 150, 30));
        jPanel4.add(jDateChooserReportDateTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 150, 30));

        generateReportsPanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 800, 180));

        jPanel5.setBackground(new java.awt.Color(153, 204, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setText("Enter Customer NIC :");
        jPanel5.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));
        jPanel5.add(txtReportCusNIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 140, 30));

        genarateReportEvent.setText("Generate Customer Event Report");
        genarateReportEvent.setPreferredSize(new java.awt.Dimension(195, 25));
        genarateReportEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                genarateReportEventMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                genarateReportEventMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                genarateReportEventMousePressed(evt);
            }
        });
        genarateReportEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genarateReportEventActionPerformed(evt);
            }
        });
        jPanel5.add(genarateReportEvent, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 70, -1, -1));

        generateReportsPanel.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 800, 180));

        jTabbedPaneReports.addTab("Generate Reports", generateReportsPanel);

        javax.swing.GroupLayout reportsPanelLayout = new javax.swing.GroupLayout(reportsPanel);
        reportsPanel.setLayout(reportsPanelLayout);
        reportsPanelLayout.setHorizontalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneReports)
                .addContainerGap())
        );
        reportsPanelLayout.setVerticalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPaneReports)
                .addContainerGap())
        );

        eventUserLogPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout eventUserLogPanelLayout = new javax.swing.GroupLayout(eventUserLogPanel);
        eventUserLogPanel.setLayout(eventUserLogPanelLayout);
        eventUserLogPanelLayout.setHorizontalGroup(
            eventUserLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 842, Short.MAX_VALUE)
        );
        eventUserLogPanelLayout.setVerticalGroup(
            eventUserLogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 548, Short.MAX_VALUE)
        );

        eventSettingsPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout eventSettingsPanelLayout = new javax.swing.GroupLayout(eventSettingsPanel);
        eventSettingsPanel.setLayout(eventSettingsPanelLayout);
        eventSettingsPanelLayout.setHorizontalGroup(
            eventSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        eventSettingsPanelLayout.setVerticalGroup(
            eventSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout togglePanelsLayout = new javax.swing.GroupLayout(togglePanels);
        togglePanels.setLayout(togglePanelsLayout);
        togglePanelsLayout.setHorizontalGroup(
            togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventSettingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(togglePanelsLayout.createSequentialGroup()
                    .addComponent(eventPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, togglePanelsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(customerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(foodPackagesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(decorationsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bookingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(paymentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(reportsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(eventUserLogPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        togglePanelsLayout.setVerticalGroup(
            togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(eventSettingsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(togglePanelsLayout.createSequentialGroup()
                    .addComponent(eventPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 31, Short.MAX_VALUE)))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(togglePanelsLayout.createSequentialGroup()
                    .addComponent(customerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 30, Short.MAX_VALUE)))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(togglePanelsLayout.createSequentialGroup()
                    .addComponent(foodPackagesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 30, Short.MAX_VALUE)))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(decorationsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(bookingsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(paymentPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(reportsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(eventUserLogPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout eventManagerPanelLayout = new javax.swing.GroupLayout(eventManagerPanel);
        eventManagerPanel.setLayout(eventManagerPanelLayout);
        eventManagerPanelLayout.setHorizontalGroup(
            eventManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventManagerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(eventManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(eventManagerPanelLayout.createSequentialGroup()
                        .addComponent(mainButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(togglePanels, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE))
                    .addComponent(headerpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE))
                .addContainerGap())
        );
        eventManagerPanelLayout.setVerticalGroup(
            eventManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eventManagerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(eventManagerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                    .addComponent(togglePanels, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        jDesktopPaneEvent.add(eventManagerPanel);
        eventManagerPanel.setBounds(-10, 0, 1040, 720);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPaneEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPaneEvent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDecorationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDecorationsActionPerformed
        eventpanelshift(buttonDecorations.getText());
    }//GEN-LAST:event_buttonDecorationsActionPerformed

    private void buttonEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEventActionPerformed
        eventpanelshift(buttonEvent.getText());

        String sql = "SELECT ";
    }//GEN-LAST:event_buttonEventActionPerformed

    private void buttonCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustomerActionPerformed
        eventpanelshift(buttonCustomer.getText());
    }//GEN-LAST:event_buttonCustomerActionPerformed

    private void buttonFoodPackagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodPackagesActionPerformed
        eventpanelshift(buttonFoodPackages.getText());
    }//GEN-LAST:event_buttonFoodPackagesActionPerformed

    private void buttonBookingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBookingsActionPerformed
        eventpanelshift(buttonBookings.getText());
    }//GEN-LAST:event_buttonBookingsActionPerformed

    private void buttonPaymentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPaymentsActionPerformed
        eventpanelshift(buttonPayments.getText());
    }//GEN-LAST:event_buttonPaymentsActionPerformed

    private void buttonReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReportsActionPerformed
        eventpanelshift(buttonReports.getText());
    }//GEN-LAST:event_buttonReportsActionPerformed

    private void buttonUserLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUserLogActionPerformed
        eventpanelshift(buttonUserLog.getText());
    }//GEN-LAST:event_buttonUserLogActionPerformed

    private void buttonSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSettingsActionPerformed
        eventpanelshift(buttonSettings.getText());
    }//GEN-LAST:event_buttonSettingsActionPerformed

    private void buttonEventMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEventMouseEntered
        buttonEvent.setBackground(new Color(35, 171, 69));
    }//GEN-LAST:event_buttonEventMouseEntered

    private void buttonEventMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEventMouseExited
        buttonEvent.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_buttonEventMouseExited

    private void buttonCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCustomerMouseEntered
        buttonCustomer.setBackground(new Color(35, 171, 69));
    }//GEN-LAST:event_buttonCustomerMouseEntered

    private void buttonCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCustomerMouseExited
        buttonCustomer.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_buttonCustomerMouseExited

    private void buttonFoodPackagesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonFoodPackagesMouseEntered
        buttonFoodPackages.setBackground(new Color(35, 171, 69));
    }//GEN-LAST:event_buttonFoodPackagesMouseEntered

    private void buttonFoodPackagesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonFoodPackagesMouseExited
        buttonFoodPackages.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_buttonFoodPackagesMouseExited

    private void buttonDecorationsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDecorationsMouseEntered
        buttonDecorations.setBackground(new Color(35, 171, 69));
    }//GEN-LAST:event_buttonDecorationsMouseEntered

    private void buttonDecorationsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonDecorationsMouseExited
        buttonDecorations.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_buttonDecorationsMouseExited

    private void buttonBookingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBookingsMouseEntered
        buttonBookings.setBackground(new Color(35, 171, 69));
    }//GEN-LAST:event_buttonBookingsMouseEntered

    private void buttonBookingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBookingsMouseExited
        buttonBookings.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_buttonBookingsMouseExited

    private void buttonPaymentsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPaymentsMouseEntered
        buttonPayments.setBackground(new Color(35, 171, 69));
    }//GEN-LAST:event_buttonPaymentsMouseEntered

    private void buttonPaymentsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPaymentsMouseExited
        buttonPayments.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_buttonPaymentsMouseExited

    private void buttonReportsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonReportsMouseEntered
        buttonReports.setBackground(new Color(35, 171, 69));
    }//GEN-LAST:event_buttonReportsMouseEntered

    private void buttonReportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonReportsMouseExited
        buttonReports.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_buttonReportsMouseExited

    private void buttonUserLogMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUserLogMouseEntered
        buttonUserLog.setBackground(new Color(35, 171, 69));
    }//GEN-LAST:event_buttonUserLogMouseEntered

    private void buttonUserLogMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUserLogMouseExited
        buttonUserLog.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_buttonUserLogMouseExited

    private void buttonSettingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSettingsMouseEntered
        buttonSettings.setBackground(new Color(35, 171, 69));
    }//GEN-LAST:event_buttonSettingsMouseEntered

    private void buttonSettingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonSettingsMouseExited
        buttonSettings.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_buttonSettingsMouseExited

    private void jButton1AddEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1AddEventActionPerformed
      
        if(txtAddEventDuration.getText()==""){
            txtAddEventDuration.setBackground(new Color(255,150,150));
        }
        if(txtAddEventNoOfGuests.getText()==""){
            txtAddEventNoOfGuests.setBackground(new Color(255,150,150));
        }
        //check no of guests-should be more than 150
        String eventGuests=txtAddEventNoOfGuests.getText();
        int no=Integer.parseInt(eventGuests);
        if(no<150){
            JOptionPane.showMessageDialog(null, "Number of guests should be more than 150");
        }
        else{
            int x=JOptionPane.showConfirmDialog(null, "Do you want to save?");
            if(x==0){

            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

            String eventType=jComboBox1AddEventType.getSelectedItem().toString();
            String eventDuration=txtAddEventDuration.getText();
            String eventEnter=jComboBox2AddEventEntertainment.getSelectedItem().toString();

            String eventDate=formatDate.format(jDateChooser2AddEventDate.getDate());
            String hours=jComboAddEventHours.getSelectedItem().toString();
            String min=jComboAddEventMin.getSelectedItem().toString();
            String pm=jComboAddEventPM.getSelectedItem().toString();
            String eventTime=hours+ " :"+min+" "+ pm;
            eventGuests=txtAddEventNoOfGuests.getText();
            int row=addEventHallAvailability.getSelectedRow();
            String hallName=addEventHallAvailability.getValueAt(row, 0).toString();
            String hallPrice=addEventHallPricelbl.getText();
      //----------------------------------------------------Validation---------------------------------------------------------------//   




            //check the time duration
            int duration=Integer.parseInt(eventDuration);
            if(duration>6){
                JOptionPane.showMessageDialog(null, "Time duration should be maximum 6 hours");
            }

                try {
                String sql="INSERT INTO event (event_type,event_duration,event_entertainment,event_date,event_time,no_of_guests,hall_name,hall_price) VALUES ('"+eventType+"','"+eventDuration+"','"+eventEnter+"','"+eventDate+"','"+eventTime+"','"+eventGuests+"','"+hallName+"','"+hallPrice+"')";
                System.out.println(sql);
                pstatement=conn.prepareStatement(sql);
                System.out.println("ERROR");
                pstatement.execute();

                //Load table
                tableloadevent();
                
                JOptionPane.showMessageDialog(null, "Successfully added to the database");

                } 
                catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }//GEN-LAST:event_jButton1AddEventActionPerformed

    private void txtAddCustomerNICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAddCustomerNICActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAddCustomerNICActionPerformed

    private void jButton4AddCustomerAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4AddCustomerAddActionPerformed
        
        String eventID = addCusEventIDlbl.getText();
        String cusName = txtAddCustomerName.getText();
        String cusNIC = txtAddCustomerNIC.getText();
        String cusAddress = txtAddCustomerAddress.getText();
        String cusTel = txtAddCustomerTelephoneNo.getText();
        
        String nicval = "^[0-9]{9}[vVxX]$";
        Boolean w = cusNIC.matches(nicval);
            if (w == false) {
                JOptionPane.showMessageDialog(null, "Invalid NIC", "invaild input", JOptionPane.ERROR_MESSAGE);
                txtAddCustomerNIC.setText("");
                txtAddCustomerNIC.setBackground(Color.red);
                
            }
            else{
                int x=JOptionPane.showConfirmDialog(null, "Do you want to save this record?");
                if(x==0){

                try {

                    String q = "INSERT INTO event_customer_info (cus_event_id,customer_name,customer_NIC,customer_address,customer_tel) VALUES ('"+eventID+"','"+cusName+"','"+cusNIC+"','"+cusAddress+"','"+cusTel+"')";
                    System.out.println("Sql = "+q);
                    pstatement=conn.prepareStatement(q);
                    pstatement.execute();

                    //addCustomerTable.setModel(DbUtils.resultSetToTableModel(result));
                    JOptionPane.showMessageDialog(null, "Successfully added to the database");
                    tableloadcustomer();
            
            } catch (SQLException ex) {
                Logger.getLogger(Event_Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
            }
        
    }//GEN-LAST:event_jButton4AddCustomerAddActionPerformed

    private void jButton4EventUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4EventUpdateActionPerformed
        int x=JOptionPane.showConfirmDialog(null, "Do you want to update?");
        
        if(x==0){
            
            int row=AddEventTable.getSelectedRow();
            String eventId=AddEventTable.getValueAt(row, 0).toString();
           
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            
            String eventType=jComboBox1AddEventType.getSelectedItem().toString();
            String eventDuration=txtAddEventDuration.getText();
            String eventEnter=jComboBox2AddEventEntertainment.getSelectedItem().toString();
            String eventDate=formatDate.format(jDateChooser2AddEventDate.getDate());
            
            String hours=jComboAddEventHours.getSelectedItem().toString();
            String min=jComboAddEventMin.getSelectedItem().toString();
            String pm=jComboAddEventPM.getSelectedItem().toString();
            String eventTime=hours + min + pm;
            
            String eventGuests=txtAddEventNoOfGuests.getText();
            row=addEventHallAvailability.getSelectedRow();
            String eventHall=addEventHallAvailability.getValueAt(row, 0).toString();
            
            String sql="UPDATE event SET event_type='"+ eventType +"',event_duration='"+ eventDuration +"',event_entertainment='"+eventEnter+"',event_date='"+eventDate+"',event_time='"+eventTime+"',no_of_guests='"+eventGuests+"',hall_name='"+eventHall+"' WHERE event_id='"+eventId+"'";
                System.out.println("Update event ==== "+sql);
            try {
                
                pstatement=conn.prepareStatement(sql);
                pstatement.execute();
                
                //table load
                tableloadevent();
                
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }//GEN-LAST:event_jButton4EventUpdateActionPerformed

    private void jButton4EventUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4EventUpdateMouseClicked
        // TODO add your handling code here:
        int row=AddEventTable.getSelectedRow();
    }//GEN-LAST:event_jButton4EventUpdateMouseClicked

    private void jButton3AddEventDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3AddEventDemoActionPerformed
       jComboBox1AddEventType.setSelectedItem("Birthday Party");
       txtAddEventDuration.setText("5");
       jComboAddEventHours.setSelectedItem("06");
       jComboAddEventMin.setSelectedItem("00");
       jComboAddEventPM.setSelectedItem("PM");
       jComboBox2AddEventEntertainment.setSelectedItem("DJ");
       txtAddEventNoOfGuests.setText("180");
    }//GEN-LAST:event_jButton3AddEventDemoActionPerformed

    private void AddEventTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddEventTableMouseClicked
        int row=AddEventTable.getSelectedRow();
        
        String eventId=AddEventTable.getValueAt(row, 0).toString();
        System.out.println("Event ID" + eventId);
        String eventType=AddEventTable.getValueAt(row, 1).toString();
        String eventDuration=AddEventTable.getValueAt(row, 2).toString();
        String eventEnter=AddEventTable.getValueAt(row, 3).toString();
        
        String eventTime=AddEventTable.getValueAt(row, 5).toString();
        String eventGuests=AddEventTable.getValueAt(row, 6).toString();
        String eventHall=AddEventTable.getValueAt(row, 7).toString();
        String hallPrice=AddEventTable.getValueAt(row, 8).toString();
        //String eventDate=AddEventTable.getModel().getValueAt(row, 4);
        
        
        txtAddEventDuration.setText(eventDuration);
        jComboBox2AddEventEntertainment.setSelectedItem(eventEnter);
        jComboBox1AddEventType.setSelectedItem(eventType);
        jComboAddEventHours.setSelectedItem(eventTime.substring(0, 2));
        jComboAddEventMin.setSelectedItem(eventTime.substring(2, 4));
        jComboAddEventPM.setSelectedItem(eventTime.substring(4, 6));
        txtAddEventNoOfGuests.setText(eventGuests);
        //addEventHallAvailability.setSelectedItem(eventHall);
        addEventHallPricelbl.setText(hallPrice);
        
    }//GEN-LAST:event_AddEventTableMouseClicked

    private void ViewEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewEventActionPerformed
        String eventID=txtViewEventID.getText();
        String sql="SELECT event_id as 'Event ID',event_type as 'Event Type',event_duration as 'Duration',event_entertainment as 'Entertainment',event_date as 'Event Date',event_time as 'Event time',no_of_guests as 'No of Guests',hall_name as 'Hall Name',hall_price as 'Hall Price' FROM event WHERE event_id like '%"+eventID+"%'";
        
        try {
            pstatement=conn.prepareStatement(sql);
            result = pstatement.executeQuery();
            
            viewEventTable.setModel(DbUtils.resultSetToTableModel(result));
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_ViewEventActionPerformed

    private void viewEventTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewEventTableMouseClicked
        
    }//GEN-LAST:event_viewEventTableMouseClicked

    private void txtAddCustomerTelephoneNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddCustomerTelephoneNoKeyTyped
        char charac= evt.getKeyChar();
        if((Character.isLetter(charac))||(charac==KeyEvent.VK_BACK_SPACE)||(charac==KeyEvent.VK_DELETE)){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtAddCustomerTelephoneNoKeyTyped

    private void viewCusSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCusSearchActionPerformed
        
//-----------------------------------------------------------------------------------view Customer--------------------------------------------------------------------------------------//       
        try {
            String cusNIC=txtViewCustomerEnterCustomerNIC.getText();
            String sql = "SELECT customer_id,event_id,customer_name,customer_NIC,customer_address,customer_tel FROM event,event_customer_info WHERE customer_NIC like '%"+cusNIC+"%'";
                System.out.println(sql);
                pstatement = conn.prepareStatement(sql);
                result = pstatement.executeQuery();
                
                viewCustomerTable.setModel(DbUtils.resultSetToTableModel(result));
                tableloadcustomer();
                
        } catch (Exception e) {
        }
    }//GEN-LAST:event_viewCusSearchActionPerformed

    private void updateCustomerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateCustomerTableMouseClicked
        try {
            int row=updateCustomerTable.getSelectedRow();
        
            //String customerId=updateCustomerTable.getValueAt(row, 0).toString();
            String eventID=updateCustomerTable.getValueAt(row, 0).toString();
            String customerName=updateCustomerTable.getValueAt(row, 1).toString();
            String customerNIC=updateCustomerTable.getValueAt(row, 2).toString();
            String customerAdd=updateCustomerTable.getValueAt(row, 3).toString();
            String customerTel=updateCustomerTable.getValueAt(row, 4).toString();

            txtUpdateCustomerName.setText(customerName);
            txtUpdateCustomerAdd.setText(customerAdd);
            txtUpdateCustomerTel.setText(customerTel);
            txtUpdateEnterCustomerNIC.setText(customerNIC);
            updateCusEventID.setText(eventID);
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_updateCustomerTableMouseClicked

    private void jButton12UpdateCusSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12UpdateCusSearchActionPerformed
        //Search Customer NIC for update customer details
        try {
            String cusNIC=txtUpdateEnterCustomerNIC.getText();
            Statement s=conn.createStatement();
            ResultSet rs= s.executeQuery("SELECT customer_id,event_id,customer_name,customer_NIC,customer_address,customer_tel FROM event,event_customer_info WHERE customer_NIC like '%"+cusNIC+"%'");
            
            while(rs.next()){
                txtUpdateCustomerName.setText(rs.getString("customer_name"));
                txtUpdateCustomerAdd.setText(rs.getString("customer_address"));
                txtUpdateCustomerTel.setText(rs.getString("customer_tel"));
                updateCusEventID.setText(rs.getString("event_id"));
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton12UpdateCusSearchActionPerformed

    private void jButton5UpdateCustomerUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5UpdateCustomerUpdateActionPerformed
        try {
            int x=JOptionPane.showConfirmDialog(null, "Do you want to update?");
        
        if(x==0){
            
            String cusNIC=txtUpdateEnterCustomerNIC.getText();
            String cusName=txtUpdateCustomerName.getText();
            String cusAdd=txtUpdateCustomerAdd.getName();
            String cusTel=txtUpdateCustomerTel.getText();
            
            String sql="UPDATE event_customer_info SET customer_name='"+cusName+"',customer_address='"+cusAdd+"',customer_tel='"+cusTel+"' WHERE customer_NIC = '%"+cusNIC+"%'";
            System.out.println("Update Cus "+sql);
            pstatement=conn.prepareStatement(sql);
            pstatement.execute();
            
            tableloadcustomer();
        }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton5UpdateCustomerUpdateActionPerformed

    private void addCusDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCusDetailsActionPerformed

        customerPanel.setVisible(true);
        eventPanel.setVisible(false);
        int row = AddEventTable.getSelectedRow();
        System.out.println("row "+AddEventTable.getSelectedRow());
        String eventId=AddEventTable.getValueAt(row, 0).toString();
        
        System.out.println("Event ID "+eventId);
        addCusEventIDlbl.setText(eventId);
        hallPrice.setText(addEventHallPricelbl.getText());
        
    }//GEN-LAST:event_addCusDetailsActionPerformed

    private void bookingSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingSearchActionPerformed
        try {
            String cusNIC=txtUpdateBookingsCusNIC.getText();
            Statement s=conn.createStatement();
            ResultSet rs= s.executeQuery("SELECT booking_event_id,booking_date, FROM event,event_customer_info WHERE customer_NIC like '%"+cusNIC+"%'");
            
            while(rs.next()){
                txtUpdateCustomerName.setText(rs.getString("customer_name"));
                txtUpdateCustomerAdd.setText(rs.getString("customer_address"));
                txtUpdateCustomerTel.setText(rs.getString("customer_tel"));
                updateCusEventID.setText(rs.getString("event_id"));
            }
        }
            catch (Exception e) {
        }
    }//GEN-LAST:event_bookingSearchActionPerformed

    private void jButton9UpdateDecorationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9UpdateDecorationActionPerformed
        try {
            int x=JOptionPane.showConfirmDialog(null, "Do you want to update this record?");
            if(x==0){
                String decorColor=jComboUpdateDecorColor.getSelectedItem().toString();
                String decorDes=jTextAreaUpdatedecorDes.getText();
                String decorSR=jTextAreaUpdateDecorSR.getText();
                String srPrice=txtUpdateDecorSRPrice.getText();
                String tPrice=txtUpdateDecorPrice.getText();
                
                String UpdatetableDecor=null;
                String UpdatewallDecor=null;
                String UpdateflowerDecor=null;
                String UpdatedicoLight=null;
                String UpdateentranceArch=null;
                if(jCheckBox1UpdateTable.isSelected()){
                    UpdatetableDecor=jCheckBox1UpdateTable.getText().toString();
                }
                if(jCheckBox2UpdateWall.isSelected()){
                    UpdatewallDecor=jCheckBox2UpdateWall.getText().toString();
                }
                if(jCheckBox3UpdateFlower.isSelected()){
                    UpdateflowerDecor=jCheckBox3UpdateFlower.getText().toString();
                }
                if(jCheckBox4UpdateDisco.isSelected()){
                    UpdatedicoLight=jCheckBox4UpdateDisco.getText().toString();
                }
                if(jCheckBox5UpdateArch.isSelected()){
                    UpdateentranceArch=jCheckBox5UpdateArch.getText().toString();
                }
                
                String sql="UPDATE event_decoration SET theme_color='"+decorColor+"',decor_description='"+decorDes+"',table_decoration='"+UpdatetableDecor+"',wall_decoration='"+UpdatewallDecor+"',flower_decoration='"+UpdateflowerDecor+"',disco_light='"+UpdatedicoLight+"',entrance_arch='"+UpdateentranceArch+"',decor_special_requirements='"+decorSR+"',decor_special_req_price='"+srPrice+"',decor_price='"+tPrice+"'";
                pstatement=conn.prepareStatement(sql);
                result =pstatement.executeQuery();
                
                updateDecorTable.setModel(DbUtils.resultSetToTableModel(result));
                
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton9UpdateDecorationActionPerformed

    private void updateDecorSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDecorSearchActionPerformed
        
        try {
            String cusNIC=txtUpdateDecorationEventID.getText();
            Statement s=conn.createStatement();
            ResultSet rs= s.executeQuery("SELECT decor_id,decor_event_id,theme_color,decor_description,decor_special_requirements FROM event,event_customer_info,event_decoration WHERE customer_NIC like '%"+cusNIC+"%'");
            
            while(rs.next()){
                updateDecorIDlbl.setText(rs.getString("decor_id"));
                updateDecorEventIDlb.setText(rs.getString("decor_event_id"));
                jComboUpdateDecorColor.setSelectedItem(rs.getString("theme_color"));
                jTextAreaUpdatedecorDes.setText(rs.getString("decor_description"));
                jTextAreaUpdateDecorSR.setText(rs.getString("decor_special_requirements"));
                jCheckBox1UpdateTable.setSelectedIcon(null);
                
            }
            
            tableLoadDecoration();
            
        } 
        catch (Exception e) {
        }
            
    }//GEN-LAST:event_updateDecorSearchActionPerformed

    private void addCusBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCusBackActionPerformed
        eventPanel.setVisible(true);
        customerPanel.setVisible(false);
    }//GEN-LAST:event_addCusBackActionPerformed

    private void viewCusBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCusBackActionPerformed
        jTabbedPaneCustomer.setSelectedIndex(0);
    }//GEN-LAST:event_viewCusBackActionPerformed

    private void viewCusNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCusNextActionPerformed
        jTabbedPaneCustomer.setSelectedIndex(2);
    }//GEN-LAST:event_viewCusNextActionPerformed

    private void addEventResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventResetActionPerformed
        txtAddEventDuration.setText("");
        txtAddEventNoOfGuests.setText("");
        jComboAddEventHours.setSelectedItem("Hours");
        jComboAddEventMin.setSelectedItem("Mins");
        jComboAddEventPM.setSelectedItem("AM");
        jComboBox1AddEventType.setSelectedItem("Select Type");
        jComboBox2AddEventEntertainment.setSelectedItem("");
        tableLoadHallName();
        //jDateChooser2AddEventDate.setDate();
    }//GEN-LAST:event_addEventResetActionPerformed

    private void viewEventBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEventBackActionPerformed
        jTabbedPaneEvent.setSelectedIndex(0);
    }//GEN-LAST:event_viewEventBackActionPerformed

    private void viewEventNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEventNextActionPerformed
        customerPanel.setVisible(true);
        eventPanel.setVisible(false);
    }//GEN-LAST:event_viewEventNextActionPerformed

    private void addCusNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCusNextActionPerformed
        jTabbedPaneCustomer.setSelectedIndex(1);
    }//GEN-LAST:event_addCusNextActionPerformed

    private void selectRFPAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectRFPAddActionPerformed
        try {
            int x=JOptionPane.showConfirmDialog(null, "Do you want to save this record?");
        
            if(x==0){
                String regFPEventID= regFPEnterEventIDlbl.getText();
                String regFPCusNIC= regFPEnterCustomerNIClbl.getText();
                String noOfPlates=txtFPNoOfPlates.getText().toString();
                String packName=jcomRegFPSelecPack.getSelectedItem().toString();
                String packPrice="";
                String menuId="";
                String status="0";
                
                if(packName=="Package A"){
                    packPrice="4450.00";
                    menuId="550004";
                    
                }
                else if(packName=="Package B"){
                    packPrice="3460.00";
                    menuId="550005";
                }
                else if(packName=="Package C"){
                    packPrice="4590.00";
                    menuId="550006";
                }
                else if(packName=="Package D"){
                    packPrice="4520.00";
                    menuId="550007";
                }
                else if(packName=="Package E"){
                    packPrice="3670.00";
                    menuId="550008";
                }

                String sql="INSERT INTO event_food_packages (fp_event_id,fp_customer_NIC,no_of_plates,fp_regular,fp_price,order_status,fp_menu_id) VALUES ('"+regFPEventID+"','"+regFPCusNIC+"','"+noOfPlates+"','"+packName+"','"+packPrice+"','"+status+"','"+menuId+"')";
                System.out.print("Decor insert"+sql);
                pstatement=conn.prepareStatement(sql);
                pstatement.execute();
                String sql2="INSERT INTO event_order (order_event_id,order_customer_NIC,event_order_no_of_plates,order_status,order_menu_id) VALUES ('"+regFPEventID+"','"+regFPCusNIC+"','"+noOfPlates+"','"+status+"','"+menuId+"')";
                pstatement=conn.prepareStatement(sql2);
                pstatement.execute();
                JOptionPane.showMessageDialog(null, "Successfully added to the database");
            }
        }
        catch (Exception e) {
            System.out.print(e);
        }
    }//GEN-LAST:event_selectRFPAddActionPerformed

    private void txtFPNoOfPlatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFPNoOfPlatesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFPNoOfPlatesActionPerformed

    private void customizedFPAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customizedFPAddActionPerformed
        TableModel tmodel1=viewCusFPTable.getModel();
        int[] index=viewCusFPTable.getSelectedRows();
        Object row[]=new Object[2];
        DefaultTableModel tmodel2=(DefaultTableModel)selectedCusFoodItemTable.getModel();
        
        for(int i=0;i<index.length;i++){
            row[0]=tmodel1.getValueAt(index[i], 0);
            row[1]=tmodel1.getValueAt(index[i], 1);
            tmodel2.addRow(row);
        }
    }//GEN-LAST:event_customizedFPAddActionPerformed

    private void jButton7AddDecorationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7AddDecorationActionPerformed
        
        try {
            int x = JOptionPane.showConfirmDialog(null, "Do you want to save this record?");
            if(x==0){
                String decorEventID=addDecorEventIDlbl.getText().toString();
                String decorCusNIC=addDecorCusNIClbl.getText();
                String decorColor=addDecorColor.getSelectedItem().toString();
                String decorDes=txtAreaDescription.getText();
                String decorSR=txtAreaSpecialRequirements.getText();
                String decorSRPrice=txtAddDecorSRPrice.getText();
                String decorPrice=txtAddDecorPrice.getText();
                String decorTotal=addDecorTotal.getText();
                
                String tableDecor=null;
                String wallDecor=null;
                String flowerDecor=null;
                String dicoLight=null;
                String entranceArch=null;
                if(jCheckBox1AddTableDecorations.isSelected()){
                    tableDecor=jCheckBox1AddTableDecorations.getText().toString();
                }
                if(jCheckBox2AddWallDecorations.isSelected()){
                    wallDecor=jCheckBox2AddWallDecorations.getText().toString();
                }
                if(jCheckBox3AddFlowerDecoration.isSelected()){
                    flowerDecor=jCheckBox3AddFlowerDecoration.getText().toString();
                }
                if(jCheckBox4AddDiscoLight.isSelected()){
                    dicoLight=jCheckBox4AddDiscoLight.getText().toString();
                }
                if(jCheckBox5AddEntranceArch.isSelected()){
                    entranceArch=jCheckBox5AddEntranceArch.getText().toString();
                }
                
                String sql="INSERT INTO event_decoration (decor_event_id,decor_customer_NIC,theme_color,decor_description,table_decoration,wall_decoration,flower_decoration,disco_light,entrance_arch,decor_special_requirements,decor_special_req_price,decor_price,decor_total) VALUES ('"+decorEventID+"','"+decorCusNIC+"','"+decorColor+"','"+decorDes+"','"+tableDecor+"','"+wallDecor+"','"+flowerDecor+"','"+dicoLight+"','"+entranceArch+"','"+decorSR+"','"+decorSRPrice+"','"+decorPrice+"','"+decorTotal+"')";
                
                pstatement=conn.prepareStatement(sql);
                System.out.println("Decor Insert "+sql);
                pstatement.execute();
                
                tableLoadDecoration();
                JOptionPane.showMessageDialog(null, "Successfully added to the database");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jButton7AddDecorationActionPerformed

    private void addDecorNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDecorNextActionPerformed
        jTabbedPaneDecorations.setSelectedIndex(1);
    }//GEN-LAST:event_addDecorNextActionPerformed

    private void updateDecorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateDecorTableMouseClicked
        int row=updateDecorTable.getSelectedRow();
        
        String decorId=updateDecorTable.getValueAt(row, 0).toString();
        String decorEventID=updateDecorTable.getValueAt(row, 1).toString();
        String decorColor=updateDecorTable.getValueAt(row, 2).toString();
        String decorDes=updateDecorTable.getValueAt(row, 3).toString();
        String decorSR=updateDecorTable.getValueAt(row, 9).toString();
        
        updateDecorIDlbl.setText(decorId);
        updateDecorEventIDlb.setText(decorEventID);
        jComboUpdateDecorColor.setSelectedItem(decorColor);
        jTextAreaUpdatedecorDes.setText(decorDes);
        jTextAreaUpdateDecorSR.setText(decorSR);
        
    }//GEN-LAST:event_updateDecorTableMouseClicked

    private void eventBookingTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eventBookingTableMouseClicked
        try {
            int row=eventBookingTable.getSelectedRow();
        
            String eventId=eventBookingTable.getValueAt(row, 0).toString();
            String cusNIC=eventBookingTable.getValueAt(row, 1).toString();
            String bookingDate=eventBookingTable.getValueAt(row, 2).toString();
            String bookingStatus=eventBookingTable.getValueAt(row, 3).toString();
            String bookingTime=eventBookingTable.getValueAt(row, 4).toString();
            
            bookingEventIDlbl.setText(eventId);
            jcomBookingStatus.setSelectedItem(bookingStatus);
            
 
        } catch (Exception e) {
        }
    }//GEN-LAST:event_eventBookingTableMouseClicked

    private void UpdateBookingsUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateBookingsUpdateActionPerformed
        try {
            int x=JOptionPane.showConfirmDialog(null, "Do you want to update this record?");
            
            if(x==0){
                String bookingStatus=jcomBookingStatus.getSelectedItem().toString();
                
                String sql="UPDATE event_booking SET booking_status='"+bookingStatus+"'";
                pstatement=conn.prepareStatement(sql);
                result =pstatement.executeQuery();
                
                eventBookingTable.setModel(DbUtils.resultSetToTableModel(result));
                tableLoadEventBooking();
                
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_UpdateBookingsUpdateActionPerformed

    private void addEventNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventNextActionPerformed
        jTabbedPaneEvent.setSelectedIndex(1);
    }//GEN-LAST:event_addEventNextActionPerformed

    private void addCustAddFPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustAddFPActionPerformed
        customerPanel.setVisible(false);
        foodPackagesPanel.setVisible(true);
        int row=addCustomerTable.getSelectedRow();
        String eventID=addCustomerTable.getValueAt(row, 0).toString();
        String cusNIC=addCustomerTable.getValueAt(row, 2).toString();
        regFPEnterEventIDlbl.setText(eventID);
        regFPEnterCustomerNIClbl.setText(cusNIC);
        hallPricerfp.setText(hallPrice.getText());
    }//GEN-LAST:event_addCustAddFPActionPerformed

    private void updateCusBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCusBackActionPerformed
        jTabbedPaneCustomer.setSelectedIndex(1);
    }//GEN-LAST:event_updateCusBackActionPerformed

    private void updateCusNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCusNextActionPerformed
        foodPackagesPanel.setVisible(true);
        customerPanel.setVisible(false);
    }//GEN-LAST:event_updateCusNextActionPerformed

    private void regFPBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regFPBackActionPerformed
        foodPackagesPanel.setVisible(false);
        customerPanel.setVisible(true);
    }//GEN-LAST:event_regFPBackActionPerformed

    private void regFPNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regFPNextActionPerformed
        jTabbedPaneFoodPackages.setSelectedIndex(1);
    }//GEN-LAST:event_regFPNextActionPerformed

    private void regFPAddDecorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regFPAddDecorActionPerformed
        foodPackagesPanel.setVisible(false);
        decorationsPanel.setVisible(true);
        
        addDecorEventIDlbl.setText(regFPEnterEventIDlbl.getText());
        addDecorCusNIClbl.setText(regFPEnterCustomerNIClbl.getText());
        hallpricedecor.setText(hallPricerfp.getText());
        fpPricedecor.setText(regFPTotalPricelbl.getText());
        
    }//GEN-LAST:event_regFPAddDecorActionPerformed

    private void addCusFPAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCusFPAddActionPerformed
        int x=JOptionPane.showConfirmDialog(null, "Do you want to save this record?");
        if(x==0){
            try {
                String eventId=cusFPEventID.getText();
                String CusNIC=cusFPCustomerNIC.getText();
                String noOfPlates=txtCusFPNoOfPlates.getText().toString();
                String totalPrice=addCusTotalFPPricelbl.getText().toString();
                String Des="";
                String status="0";
                
                int rowCount=selectedCusFoodItemTable.getRowCount();
                for(int i=0;i<rowCount;i++){
                    Des=((selectedCusFoodItemTable.getValueAt(i, 0).toString())+" ," + Des);                
                }
                
                System.out.println("Cus Des "+Des);
                String cus="Customized";


                String sql="INSERT INTO event_food_packages (fp_event_id,fp_customer_NIC,no_of_plates,fp_customized,fp_description,fp_price) VALUES ('"+eventId+"','"+CusNIC+"','"+noOfPlates+"','"+cus+"','"+Des+"','"+totalPrice+"')";
                pstatement=conn.prepareStatement(sql);
                pstatement.execute();
                
                JOptionPane.showMessageDialog(null, "Successfully added to the database");
                
                String sql2="INSERT INTO event_order (order_event_id,order_customer_NIC,event_order_no_of_plates,order_status,event_order_des,event_order_cus) VALUES ('"+eventId+"','"+CusNIC+"','"+noOfPlates+"','"+status+"','"+Des+"','"+cus+"')";
                pstatement=conn.prepareStatement(sql2);
                pstatement.execute();
                JOptionPane.showMessageDialog(null, "Successfully added to the database");
            } 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
    }//GEN-LAST:event_addCusFPAddActionPerformed

    private void addDecorBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDecorBackActionPerformed
        foodPackagesPanel.setVisible(true);
        decorationsPanel.setVisible(false);
    }//GEN-LAST:event_addDecorBackActionPerformed

    private void updateDecorBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDecorBackActionPerformed
        jTabbedPaneDecorations.setSelectedIndex(0);
    }//GEN-LAST:event_updateDecorBackActionPerformed

    private void updateDecorNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDecorNextActionPerformed
        decorationsPanel.setVisible(false);
        paymentPanel.setVisible(true);
    }//GEN-LAST:event_updateDecorNextActionPerformed

    private void bookingNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingNextActionPerformed
        bookingsPanel.setVisible(false);
        reportsPanel.setVisible(true);
    }//GEN-LAST:event_bookingNextActionPerformed

    private void bookingBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingBackActionPerformed
        paymentPanel.setVisible(true);
        bookingsPanel.setVisible(false);
    }//GEN-LAST:event_bookingBackActionPerformed

    private void updatePayNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePayNextActionPerformed
        bookingsPanel.setVisible(true);
        paymentPanel.setVisible(false);
    }//GEN-LAST:event_updatePayNextActionPerformed

    private void updatePayBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePayBackActionPerformed
        jTabbedPanePayments.setSelectedIndex(0);
    }//GEN-LAST:event_updatePayBackActionPerformed

    private void addPayNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPayNextActionPerformed
        jTabbedPanePayments.setSelectedIndex(1);
    }//GEN-LAST:event_addPayNextActionPerformed

    private void addPayBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPayBackActionPerformed
        decorationsPanel.setVisible(true);
        paymentPanel.setVisible(false);
    }//GEN-LAST:event_addPayBackActionPerformed

    private void addCusFPNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCusFPNextActionPerformed
        foodPackagesPanel.setVisible(false);
        decorationsPanel.setVisible(true);
    }//GEN-LAST:event_addCusFPNextActionPerformed

    private void addCusResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCusResetActionPerformed
        addCusEventIDlbl.setText("");
        txtAddCustomerName.setText("");
        txtAddCustomerAddress.setText("");
        txtAddCustomerNIC.setText("");
        txtAddCustomerTelephoneNo.setText("");
    }//GEN-LAST:event_addCusResetActionPerformed

    private void viewCusResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCusResetActionPerformed
        txtViewCustomerEnterCustomerNIC.setText("");
    }//GEN-LAST:event_viewCusResetActionPerformed

    private void UpdateCusResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateCusResetActionPerformed
        updateCusEventID.setText("");
        txtUpdateCustomerAdd.setText("");
        txtUpdateCustomerName.setText("");
        txtUpdateCustomerTel.setText("");
        txtUpdateEnterCustomerNIC.setText("");
    }//GEN-LAST:event_UpdateCusResetActionPerformed

    private void viewEventResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewEventResetActionPerformed
        txtViewEventID.setText("");
    }//GEN-LAST:event_viewEventResetActionPerformed

    private void addPaymentResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPaymentResetActionPerformed
        jComboPayMethods.setSelectedItem("Payment Methods");
        txtAddPaymentAmount.setText("");
        jComboPayCD.setSelectedItem("Debit");
        txtAreaAddPaymentDescription.setText("");
    }//GEN-LAST:event_addPaymentResetActionPerformed

    private void jButton9AddPaymentAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9AddPaymentAddActionPerformed
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            String payEventId=updatePaymentEventIDlbl.getText();
            String payCusNIC=txtUpdatePaymentCusNIC.getText();
            String payMethod=jComboUpdatePayMethod.getSelectedItem().toString();
            String payAmount=txtUpdatePayAmount.getText();
            String payDC=jComboUpdateCD.getSelectedItem().toString();
            String payDueDate=formatDate.format(jDateChooser2UpdatePayDate.getDate());;
            String payDes=txtAreaUpdatePaymentDescription.getText();
            String payTot=addPayTotal.getText();
            String payBal=addPayBalancelbl.getText();
            String sql="INSERT INTO event_payment (event_payment_event_ID,event_payment_customer_NIC,event_payment_method,event_payment_amount,event_Credit/Debit,event_payment_due_date,event_payment_description,event_payment_total,event_payment_balance) VALUES ('"+payEventId+"','"+payCusNIC+"','"+payMethod+"','"+payAmount+"','"+payDC+"','"+payDueDate+"','"+payDes+"','"+payTot+"','"+payBal+"')";
            pstatement = conn.prepareStatement(sql);
            result = pstatement.executeQuery();
            addPayTable.setModel(DbUtils.resultSetToTableModel(result));
            
            /*String sql2="INSERT INTO event_payment (event_payment_event_ID,event_payment_customer_NIC,event_payment_method,event_payment_amount,event_Credit/Debit,event_payment_due_date,event_payment_description,event_payment_total,event_payment_balance) VALUES ('"+payEventId+"','"+payCusNIC+"','"+payMethod+"','"+payAmount+"','"+payDC+"','"+payDueDate+"','"+payDes+"','"+payTot+"','"+payBal+"')";
            pstatement = conn.prepareStatement(sql2);
            pstatement.execute();*/
            
            JOptionPane.showMessageDialog(null, "Successfully added to the database");
            tableLoadPayment();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton9AddPaymentAddActionPerformed

    private void addCusFPBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCusFPBackActionPerformed
        jTabbedPaneFoodPackages.setSelectedIndex(0);
    }//GEN-LAST:event_addCusFPBackActionPerformed

    private void txtUpdateDecorSRPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUpdateDecorSRPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUpdateDecorSRPriceActionPerformed

    private void bookingReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingReportsActionPerformed
        bookingsPanel.setVisible(false);
        paymentPanel.setVisible(true);
        
        int row=eventBookingTable.getSelectedRow();
        String bookingEventId=eventBookingTable.getValueAt(row, 0).toString();
        String bookingCusNIC=eventBookingTable.getValueAt(row, 1).toString();
        addPayEventIDlbl.setText(bookingEventId);
        addPayCusNIClbl.setText(bookingCusNIC);
    }//GEN-LAST:event_bookingReportsActionPerformed

    private void updatePayTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePayTableMouseClicked
        int row=updatePayTable.getSelectedRow();
        String payMethod=updatePayTable.getValueAt(row, 2).toString();
        String amount=updatePayTable.getValueAt(row, 3).toString();
        String payDC=updatePayTable.getValueAt(row, 4).toString();
        String payDes=updatePayTable.getValueAt(row, 6).toString();
        
        jComboUpdatePayMethod.setSelectedItem(payMethod);
        txtUpdatePayAmount.setText(amount);
        jComboUpdateCD.setSelectedItem(payDC);
        txtAreaUpdatePaymentDescription.setText(payDes);
    }//GEN-LAST:event_updatePayTableMouseClicked

    private void jButton11UpdatePaymentUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11UpdatePaymentUpdateActionPerformed
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
            String payMethod=jComboUpdatePayMethod.getSelectedItem().toString();
            String payAmount=txtUpdatePayAmount.getText();
            String payDC=jComboUpdateCD.getSelectedItem().toString();
            String payDes=txtAreaUpdatePaymentDescription.getText();
            String payBalance=txtUpdatePayBalance.getText();
            String PayDueDate=formatDate.format(jDateChooser2UpdatePayDate.getDate());
            String sql="UPDATE event_payment SET event_payment_method='"+payMethod+"'event_payment_amount='"+payAmount+"',event_Credit/Debit='"+payDC+"',event_payment_description='"+payDes+"',event_payment_balance='"+payBalance+"', event_payment_due_date='"+PayDueDate+"'";
            pstatement = conn.prepareStatement(sql);
            result = pstatement.executeQuery();
            updatePayTable.setModel(DbUtils.resultSetToTableModel(result));
            
            tableLoadPayment();
            
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton11UpdatePaymentUpdateActionPerformed

    private void updatePaymentSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePaymentSearchActionPerformed
        try {
            String cusNIC=txtUpdatePaymentCusNIC.getText();
            Statement s=conn.createStatement();
            ResultSet rs= s.executeQuery("SELECT event_payment_event_ID,event_payment_method,event_payment_amount,event_Credit/Debit,event_payment_due_date,event_payment_description,event_payment_balance FROM event_payment WHERE customer_NIC like '%"+cusNIC+"%'");
            
            while(rs.next()){
                updatePaymentEventIDlbl.setText(rs.getString("event_payment_event_ID"));
                jComboUpdatePayMethod.setSelectedItem(rs.getString("event_payment_method"));
                txtUpdatePayAmount.setText(rs.getString("event_payment_amount"));
                jComboUpdateCD.setSelectedItem(rs.getString("event_Credit/Debit"));
                txtUpdatePayBalance.setText(rs.getString("event_payment_balance"));
                txtAreaUpdatePaymentDescription.setText(rs.getString("event_payment_description"));
                
            }
            
            tableLoadDecoration();
            
        } 
        catch (Exception e) {
        }
    }//GEN-LAST:event_updatePaymentSearchActionPerformed

    private void jCheckBox2AddWallDecorationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2AddWallDecorationsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2AddWallDecorationsActionPerformed

    private void addDecorTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addDecorTableMouseClicked
        int row=addDecorTable.getSelectedRow();
        
        String decorEventId=addDecorTable.getValueAt(row, 1).toString();
        String decorCusNIC=addDecorTable.getValueAt(row, 2).toString();
        addDecorEventIDlbl.setText(decorEventId);
        addDecorCusNIClbl.setText(decorCusNIC);
        
    }//GEN-LAST:event_addDecorTableMouseClicked

    private void addDecorAddPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDecorAddPaymentActionPerformed
        
        decorationsPanel.setVisible(false);
        paymentPanel.setVisible(true);
        
        int row=addDecorTable.getSelectedRow();
        String decorEventId=addDecorTable.getValueAt(row, 1).toString();
        String decorCusNIC=addDecorTable.getValueAt(row, 2).toString();
        addPayTotalDecorAmount.setText(addDecorTotal.getText());
        addPayTotalfoodAmount.setText(fpPricedecor.getText());
        addPayTotalHallAmount.setText(hallpricedecor.getText());
        /*try {
            String hallPrice="SELECT hall_price FROM event WHERE event_id='"+decorEventId+"'";
            pstatement=conn.prepareStatement(hallPrice);
            result = pstatement.executeQuery();
            String res=result.toString();
            addPayTotalHallAmount.setText(res);
            
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        try {
            String foodPrice="SELECT fp_total FROM event_food_packages WHERE event_id='"+decorEventId+"'";
            pstatement=conn.prepareStatement(foodPrice);
            result = pstatement.executeQuery();
            String res=result.toString();
            addPayTotalfoodAmount.setText(res);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }*/
        
        
        addPayEventIDlbl.setText(addDecorEventIDlbl.getText());
        addPayCusNIClbl.setText(addDecorCusNIClbl.getText());
        
    }//GEN-LAST:event_addDecorAddPaymentActionPerformed

    private void addPayViewBookingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPayViewBookingsActionPerformed
        paymentPanel.setVisible(false);
        bookingsPanel.setVisible(true);
        try {
            String eid=addPayEventIDlbl.getText();
            String cnic=addPayCusNIClbl.getText();
            Statement s=conn.createStatement();
            ResultSet rs=s.executeQuery("SELECT event_date FROM event WHERE event_id like '%"+eid+"%'");
            System.out.println("result = "+rs);
            String status="Pending";
            
            String sql="INSERT INTO event_booking (booking_event_id,booking_customer_NIC,booking_date,booking_status) VALUES ('"+eid+"','"+cnic+"','"+rs+"','"+status+"')";
            pstatement=conn.prepareStatement(sql);
            pstatement.execute();
            
            tableLoadEventBooking();
            
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_addPayViewBookingsActionPerformed

    private void jComboBox2AddEventEntertainmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2AddEventEntertainmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2AddEventEntertainmentActionPerformed

    private void customizedFPRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customizedFPRemoveActionPerformed
        DefaultTableModel tmodel2=(DefaultTableModel)selectedCusFoodItemTable.getModel();
        try {
            int SelectedRowIndex=selectedCusFoodItemTable.getSelectedRow();
            tmodel2.removeRow(SelectedRowIndex);
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_customizedFPRemoveActionPerformed

    private void txtCusFPNoOfPlatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCusFPNoOfPlatesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCusFPNoOfPlatesActionPerformed

    private void cusFPAddDecorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusFPAddDecorActionPerformed
        foodPackagesPanel.setVisible(false);
        decorationsPanel.setVisible(true);
        
        addDecorEventIDlbl.setText(cusFPEventID.getText());
        addDecorCusNIClbl.setText(cusFPCustomerNIC.getText());
        hallpricedecor.setText(hallPricefp.getText());
        fpPricedecor.setText(fpPricedecor.getText());
    }//GEN-LAST:event_cusFPAddDecorActionPerformed

    private void jButton4EventUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4EventUpdateMouseEntered
        jButton4EventUpdate.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton4EventUpdateMouseEntered

    private void jButton4EventUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4EventUpdateMouseExited
        jButton4EventUpdate.setForeground(Color.black);
    }//GEN-LAST:event_jButton4EventUpdateMouseExited

    private void jButton1AddEventMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1AddEventMouseEntered
        jButton1AddEvent.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton1AddEventMouseEntered

    private void jButton1AddEventMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1AddEventMouseExited
        jButton1AddEvent.setForeground(Color.black);
    }//GEN-LAST:event_jButton1AddEventMouseExited

    private void addCusDetailsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusDetailsMouseEntered
        addCusDetails.setForeground(Color.yellow);
    }//GEN-LAST:event_addCusDetailsMouseEntered

    private void addCusDetailsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusDetailsMouseExited
        addCusDetails.setForeground(Color.black);
    }//GEN-LAST:event_addCusDetailsMouseExited

    private void addEventNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEventNextMouseEntered
        addEventNext.setForeground(Color.yellow);
    }//GEN-LAST:event_addEventNextMouseEntered

    private void addEventNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEventNextMouseExited
        addEventNext.setForeground(Color.black);
    }//GEN-LAST:event_addEventNextMouseExited

    private void addEventResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEventResetMouseEntered
        addEventReset.setForeground(Color.yellow);
    }//GEN-LAST:event_addEventResetMouseEntered

    private void addEventResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEventResetMouseExited
        addEventReset.setForeground(Color.black);
    }//GEN-LAST:event_addEventResetMouseExited

    private void jButton3AddEventDemoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3AddEventDemoMouseEntered
        jButton3AddEventDemo.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton3AddEventDemoMouseEntered

    private void jButton3AddEventDemoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3AddEventDemoMouseExited
        jButton3AddEventDemo.setForeground(Color.black);
    }//GEN-LAST:event_jButton3AddEventDemoMouseExited

    private void ViewEventMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewEventMouseEntered
        ViewEvent.setForeground(Color.yellow);
    }//GEN-LAST:event_ViewEventMouseEntered

    private void ViewEventMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewEventMouseExited
        ViewEvent.setForeground(Color.black);
    }//GEN-LAST:event_ViewEventMouseExited

    private void jButton1ViewEventDemoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1ViewEventDemoMouseEntered
        jButton1ViewEventDemo.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton1ViewEventDemoMouseEntered

    private void jButton1ViewEventDemoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1ViewEventDemoMouseExited
        jButton1ViewEventDemo.setForeground(Color.black);
    }//GEN-LAST:event_jButton1ViewEventDemoMouseExited

    private void viewEventBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewEventBackMouseEntered
        viewEventBack.setForeground(Color.yellow);
    }//GEN-LAST:event_viewEventBackMouseEntered

    private void viewEventBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewEventBackMouseExited
        viewEventBack.setForeground(Color.black);
    }//GEN-LAST:event_viewEventBackMouseExited

    private void viewEventNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewEventNextMouseEntered
        viewEventNext.setForeground(Color.yellow);
    }//GEN-LAST:event_viewEventNextMouseEntered

    private void viewEventNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewEventNextMouseExited
        viewEventNext.setForeground(Color.black);
    }//GEN-LAST:event_viewEventNextMouseExited

    private void viewEventResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewEventResetMouseEntered
        viewEventReset.setForeground(Color.yellow);
    }//GEN-LAST:event_viewEventResetMouseEntered

    private void viewEventResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewEventResetMouseExited
        viewEventReset.setForeground(Color.black);
    }//GEN-LAST:event_viewEventResetMouseExited

    private void viewCusSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewCusSearchMouseEntered
        viewCusSearch.setForeground(Color.yellow);
    }//GEN-LAST:event_viewCusSearchMouseEntered

    private void viewCusSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewCusSearchMouseExited
        viewCusSearch.setForeground(Color.black);
    }//GEN-LAST:event_viewCusSearchMouseExited

    private void jButton3ViewCustomerDemoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3ViewCustomerDemoMouseEntered
       jButton3ViewCustomerDemo.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton3ViewCustomerDemoMouseEntered

    private void jButton3ViewCustomerDemoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3ViewCustomerDemoMouseExited
        jButton3ViewCustomerDemo.setForeground(Color.black);
    }//GEN-LAST:event_jButton3ViewCustomerDemoMouseExited

    private void jButton3AddCustomerDemoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3AddCustomerDemoMouseEntered
        jButton3AddCustomerDemo.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton3AddCustomerDemoMouseEntered

    private void jButton3AddCustomerDemoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3AddCustomerDemoMouseExited
        jButton3AddCustomerDemo.setForeground(Color.black);
    }//GEN-LAST:event_jButton3AddCustomerDemoMouseExited

    private void viewCusBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewCusBackMouseEntered
        viewCusBack.setForeground(Color.yellow);
    }//GEN-LAST:event_viewCusBackMouseEntered

    private void viewCusBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewCusBackMouseExited
        viewCusBack.setForeground(Color.black);
    }//GEN-LAST:event_viewCusBackMouseExited

    private void viewCusNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewCusNextMouseEntered
        viewCusNext.setForeground(Color.yellow);
    }//GEN-LAST:event_viewCusNextMouseEntered

    private void regFPAddCusFPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regFPAddCusFPActionPerformed
        jTabbedPaneFoodPackages.setSelectedIndex(1);
        cusFPEventID.setText(regFPEnterEventIDlbl.getText());
        cusFPCustomerNIC.setText(regFPEnterCustomerNIClbl.getText());
        hallPricefp.setText(hallPricerfp.getText());
    }//GEN-LAST:event_regFPAddCusFPActionPerformed

    private void cusFPOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusFPOkActionPerformed
        hallPricefp.setVisible(true);
        String noOfPlates=txtCusFPNoOfPlates.getText().toString();
        String price="";
        double tot=0.0;
        double totalPrice=0.0;
        int rowCount=selectedCusFoodItemTable.getRowCount();
        for(int i=0;i<rowCount;i++){
            price=(selectedCusFoodItemTable.getValueAt(i, 1).toString());
            tot=Double.parseDouble(price)+tot;
        }
        String total=Double.toString(tot);
        addCusPricePerPlatelbl.setText(total);
        totalPrice=(tot*Double.parseDouble(noOfPlates));
        String totPrice=Double.toString(totalPrice);
        addCusTotalFPPricelbl.setText(totPrice);
    }//GEN-LAST:event_cusFPOkActionPerformed

    private void regFPSetTotalPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regFPSetTotalPriceActionPerformed
        String noOfPlates=txtFPNoOfPlates.getText();
        String packName=jcomRegFPSelecPack.getSelectedItem().toString();
        double packPrice=0.0;
        double tot=0.0;
        int plates=Integer.parseInt(noOfPlates);
        
        if(plates==0){
            JOptionPane.showMessageDialog(null, "Please enter the no of Plates");
        }
        if(packName==""){
            JOptionPane.showMessageDialog(null, "Please select the package");
        }
        
        if(packName=="Package A"){
            packPrice=4450.00;
            tot=packPrice*Double.parseDouble(noOfPlates);
        }
        if(packName=="Package B"){
            packPrice=3460.00;
            tot=packPrice*Double.parseDouble(noOfPlates);
        }
        if(packName=="Package C"){
            packPrice=4590.00;
            tot=packPrice*Double.parseDouble(noOfPlates);
        }
        if(packName=="Package D"){
            packPrice=4520.00;
            tot=packPrice*Double.parseDouble(noOfPlates);
        }
        if(packName=="Package E"){
            packPrice=3670.00;
            tot=packPrice*Double.parseDouble(noOfPlates);
        }
        
        String total=Double.toString(tot);
        regFPTotalPricelbl.setText(total);
    }//GEN-LAST:event_regFPSetTotalPriceActionPerformed

    private void regFPAddPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regFPAddPayActionPerformed
        foodPackagesPanel.setVisible(false);
        paymentPanel.setVisible(true);
        
        addPayEventIDlbl.setText(regFPEnterEventIDlbl.getText());
        addPayCusNIClbl.setText(regFPEnterCustomerNIClbl.getText());
        addPayTotalfoodAmount.setText(regFPTotalPricelbl.getText());
    }//GEN-LAST:event_regFPAddPayActionPerformed

    private void cusFPAddPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusFPAddPayActionPerformed
        foodPackagesPanel.setVisible(false);
        paymentPanel.setVisible(true);
        String fpEid=cusFPEventID.getText();
        addPayEventIDlbl.setText(cusFPEventID.getText());
        addPayCusNIClbl.setText(cusFPCustomerNIC.getText());
        addPayTotalfoodAmount.setText(addCusTotalFPPricelbl.getText());
        
        try {
            String hallPrice="SELECT hall_price FROM event WHERE event_id='"+fpEid+"'";
            pstatement=conn.prepareStatement(hallPrice);
            pstatement.execute(); 
            addPayTotalHallAmount.setText(hallPrice);
            
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_cusFPAddPayActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        double srPrice=Double.parseDouble(txtAddDecorSRPrice.getText());
        double decorPrice=Double.parseDouble(txtAddDecorPrice.getText());
        double tot=srPrice+decorPrice;
        addDecorTotal.setText(Double.toString(tot));
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        double srPrice=Double.parseDouble(txtUpdateDecorSRPrice.getText());
        double decorPrice=Double.parseDouble(txtUpdateDecorPrice.getText());
        double tot=srPrice+decorPrice;
        updateDecorTotal.setText(Double.toString(tot));
    }//GEN-LAST:event_jButton6ActionPerformed

    private void addPayTotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPayTotActionPerformed
        double fa=Double.parseDouble(addPayTotalfoodAmount.getText());
        double da=Double.parseDouble(addPayTotalDecorAmount.getText());
        double ha=Double.parseDouble(addPayTotalHallAmount.getText());
        double amount=Double.parseDouble(txtAddPaymentAmount.getText());
        
        double ftot=fa+da+ha;
        addPayTotal.setText(Double.toString(ftot));
        double bal=ftot-amount;
        addPayBalancelbl.setText(Double.toString(bal));
        
    }//GEN-LAST:event_addPayTotActionPerformed

    private void jButton4AddCustomerAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4AddCustomerAddMouseEntered
        jButton4AddCustomerAdd.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton4AddCustomerAddMouseEntered

    private void jButton4AddCustomerAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4AddCustomerAddMouseExited
        jButton4AddCustomerAdd.setForeground(Color.black);
    }//GEN-LAST:event_jButton4AddCustomerAddMouseExited

    private void addCustAddFPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCustAddFPMouseEntered
        addCustAddFP.setForeground(Color.yellow);
    }//GEN-LAST:event_addCustAddFPMouseEntered

    private void addCustAddFPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCustAddFPMouseExited
        addCustAddFP.setForeground(Color.black);
    }//GEN-LAST:event_addCustAddFPMouseExited

    private void addCusBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusBackMouseEntered
        addCusBack.setForeground(Color.yellow);
    }//GEN-LAST:event_addCusBackMouseEntered

    private void addCusBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusBackMouseExited
        addCusBack.setForeground(Color.black);
    }//GEN-LAST:event_addCusBackMouseExited

    private void addCusNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusNextMouseEntered
        addCusNext.setForeground(Color.yellow);
    }//GEN-LAST:event_addCusNextMouseEntered

    private void addCusNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusNextMouseExited
        addCusNext.setForeground(Color.black);
    }//GEN-LAST:event_addCusNextMouseExited

    private void addCusResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusResetMouseEntered
        addCusReset.setForeground(Color.yellow);
    }//GEN-LAST:event_addCusResetMouseEntered

    private void addCusResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusResetMouseExited
        addCusReset.setForeground(Color.black);
    }//GEN-LAST:event_addCusResetMouseExited

    private void viewCusNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewCusNextMouseExited
        viewCusNext.setForeground(Color.black);
    }//GEN-LAST:event_viewCusNextMouseExited

    private void viewCusResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewCusResetMouseEntered
        viewCusReset.setForeground(Color.yellow);
    }//GEN-LAST:event_viewCusResetMouseEntered

    private void viewCusResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewCusResetMouseExited
        viewCusReset.setForeground(Color.black);
    }//GEN-LAST:event_viewCusResetMouseExited

    private void jButton12UpdateCusSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12UpdateCusSearchMouseEntered
        jButton12UpdateCusSearch.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton12UpdateCusSearchMouseEntered

    private void jButton12UpdateCusSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12UpdateCusSearchMouseExited
        jButton12UpdateCusSearch.setForeground(Color.black);
    }//GEN-LAST:event_jButton12UpdateCusSearchMouseExited

    private void jButton5UpdateCustomerUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5UpdateCustomerUpdateMouseEntered
        jButton5UpdateCustomerUpdate.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton5UpdateCustomerUpdateMouseEntered

    private void jButton5UpdateCustomerUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5UpdateCustomerUpdateMouseExited
        jButton5UpdateCustomerUpdate.setForeground(Color.black);
    }//GEN-LAST:event_jButton5UpdateCustomerUpdateMouseExited

    private void jButton4UpdateCustomerDemoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4UpdateCustomerDemoMouseEntered
        jButton4UpdateCustomerDemo.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton4UpdateCustomerDemoMouseEntered

    private void jButton4UpdateCustomerDemoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4UpdateCustomerDemoMouseExited
        jButton4UpdateCustomerDemo.setForeground(Color.black);
    }//GEN-LAST:event_jButton4UpdateCustomerDemoMouseExited

    private void updateCusBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateCusBackMouseEntered
        updateCusBack.setForeground(Color.yellow);
    }//GEN-LAST:event_updateCusBackMouseEntered

    private void updateCusBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateCusBackMouseExited
        updateCusBack.setForeground(Color.black);
    }//GEN-LAST:event_updateCusBackMouseExited

    private void updateCusNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateCusNextMouseEntered
        updateCusNext.setForeground(Color.yellow);
    }//GEN-LAST:event_updateCusNextMouseEntered

    private void updateCusNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateCusNextMouseExited
        updateCusNext.setForeground(Color.black);
    }//GEN-LAST:event_updateCusNextMouseExited

    private void UpdateCusResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateCusResetMouseEntered
        UpdateCusReset.setForeground(Color.yellow);
    }//GEN-LAST:event_UpdateCusResetMouseEntered

    private void UpdateCusResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateCusResetMouseExited
        UpdateCusReset.setForeground(Color.black);
    }//GEN-LAST:event_UpdateCusResetMouseExited

    private void selectRFPAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectRFPAddMouseEntered
        selectRFPAdd.setForeground(Color.yellow);
    }//GEN-LAST:event_selectRFPAddMouseEntered

    private void selectRFPAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectRFPAddMouseExited
        selectRFPAdd.setForeground(Color.black);
    }//GEN-LAST:event_selectRFPAddMouseExited

    private void regFPSetTotalPriceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPSetTotalPriceMouseEntered
        regFPSetTotalPrice.setForeground(Color.yellow);
    }//GEN-LAST:event_regFPSetTotalPriceMouseEntered

    private void regFPSetTotalPriceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPSetTotalPriceMouseExited
        regFPSetTotalPrice.setForeground(Color.black);
    }//GEN-LAST:event_regFPSetTotalPriceMouseExited

    private void regFPDemoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPDemoMouseEntered
        regFPDemo.setForeground(Color.yellow);
    }//GEN-LAST:event_regFPDemoMouseEntered

    private void regFPDemoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPDemoMouseExited
        regFPDemo.setForeground(Color.black);
    }//GEN-LAST:event_regFPDemoMouseExited

    private void regFPAddCusFPMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPAddCusFPMouseEntered
        regFPAddCusFP.setForeground(Color.yellow);
    }//GEN-LAST:event_regFPAddCusFPMouseEntered

    private void regFPAddCusFPMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPAddCusFPMouseExited
        regFPAddCusFP.setForeground(Color.black);
    }//GEN-LAST:event_regFPAddCusFPMouseExited

    private void regFPAddDecorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPAddDecorMouseEntered
        regFPAddDecor.setForeground(Color.yellow);
    }//GEN-LAST:event_regFPAddDecorMouseEntered

    private void regFPAddDecorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPAddDecorMouseExited
        regFPAddDecor.setForeground(Color.black);
    }//GEN-LAST:event_regFPAddDecorMouseExited

    private void regFPAddPayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPAddPayMouseEntered
        regFPAddPay.setForeground(Color.yellow);
    }//GEN-LAST:event_regFPAddPayMouseEntered

    private void regFPAddPayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPAddPayMouseExited
        regFPAddPay.setForeground(Color.black);
    }//GEN-LAST:event_regFPAddPayMouseExited

    private void regFPBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPBackMouseEntered
        regFPBack.setForeground(Color.yellow);
    }//GEN-LAST:event_regFPBackMouseEntered

    private void regFPBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPBackMouseExited
        regFPBack.setForeground(Color.black);
    }//GEN-LAST:event_regFPBackMouseExited

    private void regFPNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPNextMouseEntered
        regFPNext.setForeground(Color.yellow);
    }//GEN-LAST:event_regFPNextMouseEntered

    private void regFPNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPNextMouseExited
        regFPNext.setForeground(Color.black);
    }//GEN-LAST:event_regFPNextMouseExited

    private void regFPResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPResetMouseEntered
        regFPReset.setForeground(Color.yellow);
    }//GEN-LAST:event_regFPResetMouseEntered

    private void regFPResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_regFPResetMouseExited
        regFPReset.setForeground(Color.black);
    }//GEN-LAST:event_regFPResetMouseExited

    private void customizedFPAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customizedFPAddMouseEntered
        customizedFPAdd.setForeground(Color.yellow);
    }//GEN-LAST:event_customizedFPAddMouseEntered

    private void customizedFPAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customizedFPAddMouseExited
        customizedFPAdd.setForeground(Color.black);
    }//GEN-LAST:event_customizedFPAddMouseExited

    private void customizedFPRemoveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customizedFPRemoveMouseEntered
        customizedFPRemove.setForeground(Color.yellow);
    }//GEN-LAST:event_customizedFPRemoveMouseEntered

    private void customizedFPRemoveMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customizedFPRemoveMouseExited
        customizedFPRemove.setForeground(Color.black);
    }//GEN-LAST:event_customizedFPRemoveMouseExited

    private void cusFPOkMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cusFPOkMouseEntered
        cusFPOk.setForeground(Color.yellow);
    }//GEN-LAST:event_cusFPOkMouseEntered

    private void cusFPOkMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cusFPOkMouseExited
        cusFPOk.setForeground(Color.black);
    }//GEN-LAST:event_cusFPOkMouseExited

    private void cusFPDemoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cusFPDemoMouseEntered
        cusFPDemo.setForeground(Color.yellow);
    }//GEN-LAST:event_cusFPDemoMouseEntered

    private void cusFPDemoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cusFPDemoMouseExited
        cusFPDemo.setForeground(Color.black);
    }//GEN-LAST:event_cusFPDemoMouseExited

    private void addCusFPAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusFPAddMouseEntered
        addCusFPAdd.setForeground(Color.yellow);
    }//GEN-LAST:event_addCusFPAddMouseEntered

    private void addCusFPAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusFPAddMouseExited
        addCusFPAdd.setForeground(Color.black);
    }//GEN-LAST:event_addCusFPAddMouseExited

    private void cusFPAddDecorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cusFPAddDecorMouseEntered
        cusFPAddDecor.setForeground(Color.yellow);
    }//GEN-LAST:event_cusFPAddDecorMouseEntered

    private void cusFPAddDecorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cusFPAddDecorMouseExited
        cusFPAddDecor.setForeground(Color.black);
    }//GEN-LAST:event_cusFPAddDecorMouseExited

    private void cusFPAddPayMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cusFPAddPayMouseEntered
       cusFPAddPay.setForeground(Color.yellow);
    }//GEN-LAST:event_cusFPAddPayMouseEntered

    private void addEventAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addEventAvailabilityActionPerformed
        if(jComboAddEventHours.getSelectedItem().toString()=="" && jComboAddEventMin.getSelectedItem().toString()=="" && jComboAddEventPM.getSelectedItem().toString()==""){
             JOptionPane.showMessageDialog(null, "Select the event time");
        }
        else{
            try {
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                String eventDate=formatDate.format(jDateChooser2AddEventDate.getDate());
                String hours=jComboAddEventHours.getSelectedItem().toString();
                String min=jComboAddEventMin.getSelectedItem().toString();
                String pm=jComboAddEventPM.getSelectedItem().toString();
                String eventTime=hours+ " :"+min+" "+ pm;
                String sql="SELECT hall_name FROM event WHERE event_date like '%"+eventDate+"%' and event_time like '%"+eventTime+"%' and event_status = 'Pending' ";
                pstatement=conn.prepareStatement(sql);
                result =pstatement.executeQuery();
                
                addEventHallAvailability.setModel(DbUtils.resultSetToTableModel(result));
                
        } catch (Exception e) {
        }
        }
       
    }//GEN-LAST:event_addEventAvailabilityActionPerformed

    private void addEventHallAvailabilityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEventHallAvailabilityMouseClicked
        int row=addEventHallAvailability.getSelectedRow();
        String hallName=addEventHallAvailability.getValueAt(row, 0).toString();
        if(hallName.equals("Ballroom - max 200")){
            addEventHallPricelbl.setText("10000.00");
        }
        if(hallName.equals("Ockroom - max 250")){
            addEventHallPricelbl.setText("12000.00");
        }
        if(hallName.equals("Platinum - max 300")){
            addEventHallPricelbl.setText("16000.00");
        }
        if(hallName.equals("Rooftop - max 350")){
            addEventHallPricelbl.setText("20000.00");
        }
    }//GEN-LAST:event_addEventHallAvailabilityMouseClicked

    private void addPayTotMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPayTotMouseEntered
        addPayTot.setForeground(Color.yellow);
    }//GEN-LAST:event_addPayTotMouseEntered

    private void addPayTotMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPayTotMouseExited
        addPayTot.setForeground(Color.black);
    }//GEN-LAST:event_addPayTotMouseExited

    private void jButton4AddPaymentDemoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4AddPaymentDemoMouseEntered
        jButton4AddPaymentDemo.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton4AddPaymentDemoMouseEntered

    private void jButton4AddPaymentDemoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4AddPaymentDemoMouseExited
        jButton4AddPaymentDemo.setForeground(Color.black);
    }//GEN-LAST:event_jButton4AddPaymentDemoMouseExited

    private void jButton9AddPaymentAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9AddPaymentAddMouseExited
        jButton9AddPaymentAdd.setForeground(Color.black);
    }//GEN-LAST:event_jButton9AddPaymentAddMouseExited

    private void jButton9AddPaymentAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9AddPaymentAddMouseEntered
        jButton9AddPaymentAdd.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton9AddPaymentAddMouseEntered

    private void addPayViewBookingsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPayViewBookingsMouseEntered
        addPayViewBookings.setForeground(Color.yellow);
    }//GEN-LAST:event_addPayViewBookingsMouseEntered

    private void addPayViewBookingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPayViewBookingsMouseExited
        addPayViewBookings.setForeground(Color.black);
    }//GEN-LAST:event_addPayViewBookingsMouseExited

    private void addPayBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPayBackMouseEntered
        addPayBack.setForeground(Color.yellow);
    }//GEN-LAST:event_addPayBackMouseEntered

    private void addPayBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPayBackMouseExited
        addPayBack.setForeground(Color.black);
    }//GEN-LAST:event_addPayBackMouseExited

    private void addPayNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPayNextMouseEntered
        addPayNext.setForeground(Color.yellow);
    }//GEN-LAST:event_addPayNextMouseEntered

    private void addPayNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPayNextMouseExited
        addPayNext.setForeground(Color.black);
    }//GEN-LAST:event_addPayNextMouseExited

    private void addPaymentResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPaymentResetMouseEntered
        addPaymentReset.setForeground(Color.yellow);
    }//GEN-LAST:event_addPaymentResetMouseEntered

    private void addPaymentResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPaymentResetMouseExited
        addPaymentReset.setForeground(Color.black);
    }//GEN-LAST:event_addPaymentResetMouseExited

    private void updatePaymentSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePaymentSearchMouseEntered
        updatePaymentSearch.setForeground(Color.yellow);
    }//GEN-LAST:event_updatePaymentSearchMouseEntered

    private void updatePaymentSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePaymentSearchMouseExited
        updatePaymentSearch.setForeground(Color.black);
    }//GEN-LAST:event_updatePaymentSearchMouseExited

    private void jButton4UpdatePaymentDemoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4UpdatePaymentDemoMouseEntered
        jButton4UpdatePaymentDemo.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton4UpdatePaymentDemoMouseEntered

    private void jButton4UpdatePaymentDemoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4UpdatePaymentDemoMouseExited
        jButton4UpdatePaymentDemo.setForeground(Color.black);
    }//GEN-LAST:event_jButton4UpdatePaymentDemoMouseExited

    private void jButton11UpdatePaymentUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11UpdatePaymentUpdateMouseEntered
        jButton11UpdatePaymentUpdate.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton11UpdatePaymentUpdateMouseEntered

    private void jButton11UpdatePaymentUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11UpdatePaymentUpdateMouseExited
        jButton11UpdatePaymentUpdate.setForeground(Color.black);
    }//GEN-LAST:event_jButton11UpdatePaymentUpdateMouseExited

    private void updatePayBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePayBackMouseEntered
        updatePayBack.setForeground(Color.yellow);
    }//GEN-LAST:event_updatePayBackMouseEntered

    private void updatePayBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePayBackMouseExited
        updatePayBack.setForeground(Color.black);
    }//GEN-LAST:event_updatePayBackMouseExited

    private void updatePayNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePayNextMouseEntered
        updatePayNext.setForeground(Color.yellow);
    }//GEN-LAST:event_updatePayNextMouseEntered

    private void updatePayNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePayNextMouseExited
        updatePayNext.setForeground(Color.black);
    }//GEN-LAST:event_updatePayNextMouseExited

    private void updatePayResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePayResetMouseEntered
        updatePayReset.setForeground(Color.yellow);
    }//GEN-LAST:event_updatePayResetMouseEntered

    private void updatePayResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatePayResetMouseExited
        updatePayReset.setForeground(Color.black);
    }//GEN-LAST:event_updatePayResetMouseExited

    private void bookingSearchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingSearchMouseEntered
        bookingSearch.setForeground(Color.yellow);
    }//GEN-LAST:event_bookingSearchMouseEntered

    private void bookingSearchMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingSearchMouseExited
        bookingSearch.setForeground(Color.black);
    }//GEN-LAST:event_bookingSearchMouseExited

    private void UpdateBookingsUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateBookingsUpdateMouseEntered
        UpdateBookingsUpdate.setForeground(Color.yellow);
    }//GEN-LAST:event_UpdateBookingsUpdateMouseEntered

    private void UpdateBookingsUpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_UpdateBookingsUpdateMouseExited
        UpdateBookingsUpdate.setForeground(Color.black);
    }//GEN-LAST:event_UpdateBookingsUpdateMouseExited

    private void jButton9UpdateBookingsDemoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9UpdateBookingsDemoMouseEntered
        jButton9UpdateBookingsDemo.setForeground(Color.yellow);
    }//GEN-LAST:event_jButton9UpdateBookingsDemoMouseEntered

    private void jButton9UpdateBookingsDemoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9UpdateBookingsDemoMouseExited
        jButton9UpdateBookingsDemo.setForeground(Color.black);
    }//GEN-LAST:event_jButton9UpdateBookingsDemoMouseExited

    private void jButton1LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1LogOutActionPerformed
        int result=JOptionPane.showConfirmDialog(togglePanels,"Do you want to Log Out from the system?","Logout Confirmation",0);
        if(result==0){
            System_Login login=new System_Login();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton1LogOutActionPerformed

    private void bookingReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingReportActionPerformed
        try {
            String report="D:\\SLIIT PROFILE\\Glen Hotel\\Glen_Hotel_Management_System\\src\\glen\\event\\reportBooking.jrxml";
            Map para = new HashMap();
                SimpleDateFormat date=new SimpleDateFormat("yyy-MM-dd");
                String dateFrom=date.format(jDateChooserReportsDate1.getDate());
                String dateTo=date.format(jDateChooserReportDateTo.getDate());
                para.put("firstdate", dateFrom);
                para.put("seconddate", dateTo);
                view(para,report);
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Select the dates");
        }
    }//GEN-LAST:event_bookingReportActionPerformed

    private void genarateReportEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genarateReportEventActionPerformed
        String nic=txtReportCusNIC.getText();
        String nicval = "^[0-9]{9}[vVxX]$";
        Boolean w = nic.matches(nicval);
            if (w == false) {
                JOptionPane.showMessageDialog(null, "Invalid NIC", "invaild input", JOptionPane.ERROR_MESSAGE);
                txtAddCustomerNIC.setText("");
                txtAddCustomerNIC.setBackground(Color.red);
                
            }
            else{
                try {
                String report="D:\\SLIIT PROFILE\\Glen Hotel\\Glen_Hotel_Management_System\\src\\glen\\event\\reportEvent.jrxml";
                //String nic=txtReportCusNIC.getText();

                Map para = new HashMap();

                para.put("nic", nic);
                view(para,report);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        
    }//GEN-LAST:event_genarateReportEventActionPerformed

    private void regFPResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regFPResetActionPerformed
        regFPEnterEventIDlbl.setText("");
        regFPEnterCustomerNIClbl.setText("");
        regFPEnterEventIDlbl.setText("");
        regFPEnterEventIDlbl.setText("");
    }//GEN-LAST:event_regFPResetActionPerformed

    private void genarateReportEventMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genarateReportEventMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_genarateReportEventMousePressed

    private void genarateReportEventMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genarateReportEventMouseEntered
        genarateReportEvent.setForeground(Color.yellow);
    }//GEN-LAST:event_genarateReportEventMouseEntered

    private void genarateReportEventMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genarateReportEventMouseExited
         genarateReportEvent.setForeground(Color.black);                                                
    }//GEN-LAST:event_genarateReportEventMouseExited

    private void bookingReportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingReportMouseEntered
        bookingReport.setForeground(Color.yellow);
    }//GEN-LAST:event_bookingReportMouseEntered

    private void bookingReportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingReportMouseExited
        bookingReport.setForeground(Color.black);
    }//GEN-LAST:event_bookingReportMouseExited

    private void jButton9UpdateBookingsDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9UpdateBookingsDemoActionPerformed
        txtUpdateBookingsCusNIC.setText("927772426v");
    }//GEN-LAST:event_jButton9UpdateBookingsDemoActionPerformed

    private void updateBookingsResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBookingsResetActionPerformed
        txtUpdateBookingsCusNIC.setText("");
        bookingEventIDlbl.setText("");
        jcomBookingStatus.setSelectedItem("Pending");
    }//GEN-LAST:event_updateBookingsResetActionPerformed

    private void bookingReportsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingReportsMouseEntered
        bookingReports.setForeground(Color.yellow);
    }//GEN-LAST:event_bookingReportsMouseEntered

    private void bookingReportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingReportsMouseExited
        bookingReports.setForeground(Color.black);
    }//GEN-LAST:event_bookingReportsMouseExited

    private void updateBookingsResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateBookingsResetMouseEntered
        updateBookingsReset.setForeground(Color.yellow);
    }//GEN-LAST:event_updateBookingsResetMouseEntered

    private void updateBookingsResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateBookingsResetMouseExited
        updateBookingsReset.setForeground(Color.black);
    }//GEN-LAST:event_updateBookingsResetMouseExited

    private void bookingNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingNextMouseEntered
        bookingNext.setForeground(Color.yellow);
    }//GEN-LAST:event_bookingNextMouseEntered

    private void bookingNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingNextMouseExited
        bookingNext.setForeground(Color.black);
    }//GEN-LAST:event_bookingNextMouseExited

    private void bookingBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingBackMouseEntered
        bookingBack.setForeground(Color.yellow);
    }//GEN-LAST:event_bookingBackMouseEntered

    private void bookingBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookingBackMouseExited
        bookingBack.setForeground(Color.black);
    }//GEN-LAST:event_bookingBackMouseExited

    private void cusFPAddPayMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cusFPAddPayMouseExited
        cusFPAddPay.setForeground(Color.black);
    }//GEN-LAST:event_cusFPAddPayMouseExited

    private void addCusFPBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusFPBackMouseEntered
        addCusFPBack.setForeground(Color.yellow);
    }//GEN-LAST:event_addCusFPBackMouseEntered

    private void addCusFPBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusFPBackMouseExited
        addCusFPBack.setForeground(Color.black);
    }//GEN-LAST:event_addCusFPBackMouseExited

    private void addCusFPNextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusFPNextMouseEntered
        addCusFPNext.setForeground(Color.yellow);
    }//GEN-LAST:event_addCusFPNextMouseEntered

    private void addCusFPNextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addCusFPNextMouseExited
        addCusFPNext.setForeground(Color.black);
    }//GEN-LAST:event_addCusFPNextMouseExited

    private void cusFPResetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cusFPResetMouseEntered
        cusFPReset.setForeground(Color.yellow);
    }//GEN-LAST:event_cusFPResetMouseEntered

    private void cusFPResetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cusFPResetMouseExited
        cusFPReset.setForeground(Color.black);
    }//GEN-LAST:event_cusFPResetMouseExited

    private void jButton4UpdateDecorationDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4UpdateDecorationDemoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4UpdateDecorationDemoActionPerformed

    private void updateDecorResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDecorResetActionPerformed
        txtUpdateDecorationEventID.setText("");
        updateDecorIDlbl.setText("");
        updateDecorEventIDlb.setText("");
        jComboUpdateDecorColor.setSelectedItem("Color");
        updateDecorTotal.setText("");
        txtUpdateDecorPrice.setText("");
        jTextAreaUpdatedecorDes.setText("");
        jTextAreaUpdateDecorSR.setText("");
        txtUpdateDecorSRPrice.setText("");
    }//GEN-LAST:event_updateDecorResetActionPerformed

    private void cusFPResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusFPResetActionPerformed
        txtCusFPNoOfPlates.setText("");
        DefaultTableModel model=(DefaultTableModel)selectedCusFoodItemTable.getModel();
        while(model.getRowCount()>0){
            for(int i=0;i<model.getRowCount();i++){
                model.removeRow(i);
            }
        }
        addCusPricePerPlatelbl.setText("");
        addCusTotalFPPricelbl.setText("");
    }//GEN-LAST:event_cusFPResetActionPerformed

    private void jButton3AddCustomerDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3AddCustomerDemoActionPerformed
        txtAddCustomerName.setText("Buddhini Dias");
        txtAddCustomerAddress.setText("no128,Nugegoda");
        txtAddCustomerNIC.setText("927772426v");
        txtAddCustomerTelephoneNo.setText("0112809441");
    }//GEN-LAST:event_jButton3AddCustomerDemoActionPerformed

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
            java.util.logging.Logger.getLogger(Event_Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Event_Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Event_Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Event_Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Event_Manager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AddEventTable;
    private javax.swing.JPanel CusFoodPackagesPanel;
    private javax.swing.JPanel RegFoodPackagesPanel;
    private javax.swing.JLabel UpdateBookingsEnterCusNIC;
    private javax.swing.JButton UpdateBookingsUpdate;
    private javax.swing.JButton UpdateCusReset;
    private javax.swing.JLabel UpdatePaymentEventID;
    private javax.swing.JButton ViewEvent;
    private javax.swing.JButton addCusBack;
    private javax.swing.JButton addCusDetails;
    private javax.swing.JLabel addCusEventID;
    private javax.swing.JLabel addCusEventIDlbl;
    private javax.swing.JButton addCusFPAdd;
    private javax.swing.JButton addCusFPBack;
    private javax.swing.JLabel addCusFPEventIDlbl;
    private javax.swing.JButton addCusFPNext;
    private javax.swing.JButton addCusNext;
    private javax.swing.JLabel addCusPricePerPlatelbl;
    private javax.swing.JButton addCusReset;
    private javax.swing.JLabel addCusTotalFPPricelbl;
    private javax.swing.JButton addCustAddFP;
    private javax.swing.JPanel addCustomerPanel;
    private javax.swing.JTable addCustomerTable;
    private javax.swing.JButton addDecorAddPayment;
    private javax.swing.JButton addDecorBack;
    private javax.swing.JComboBox addDecorColor;
    private javax.swing.JLabel addDecorCusNIClbl;
    private javax.swing.JLabel addDecorEventIDlbl;
    private javax.swing.JButton addDecorNext;
    private javax.swing.JTable addDecorTable;
    private javax.swing.JLabel addDecorTotal;
    private javax.swing.JPanel addDecorationsPanel;
    private javax.swing.JButton addEventAvailability;
    private javax.swing.JTable addEventHallAvailability;
    private javax.swing.JLabel addEventHallPricelbl;
    private javax.swing.JButton addEventNext;
    private javax.swing.JPanel addEventPanel;
    private javax.swing.JButton addEventReset;
    private javax.swing.JButton addPayBack;
    private javax.swing.JLabel addPayBalancelbl;
    private javax.swing.JLabel addPayCusNIClbl;
    private javax.swing.JLabel addPayEventIDlbl;
    private javax.swing.JButton addPayNext;
    private javax.swing.JTable addPayTable;
    private javax.swing.JButton addPayTot;
    private javax.swing.JLabel addPayTotal;
    private javax.swing.JLabel addPayTotalDecorAmount;
    private javax.swing.JLabel addPayTotalFdAmount;
    private javax.swing.JLabel addPayTotalHallAmount;
    private javax.swing.JLabel addPayTotalfoodAmount;
    private javax.swing.JButton addPayViewBookings;
    private javax.swing.JButton addPaymentReset;
    private javax.swing.JPanel addPaymentsPanel;
    private javax.swing.JButton bookingBack;
    private javax.swing.JLabel bookingDatelbl;
    private javax.swing.JLabel bookingEventIDlbl;
    private javax.swing.JButton bookingNext;
    private javax.swing.JButton bookingReport;
    private javax.swing.JButton bookingReports;
    private javax.swing.JButton bookingSearch;
    private javax.swing.JPanel bookingsPanel;
    private javax.swing.JButton buttonBookings;
    private javax.swing.JButton buttonCustomer;
    private javax.swing.JButton buttonDecorations;
    private javax.swing.JButton buttonEvent;
    private javax.swing.JButton buttonFoodPackages;
    private javax.swing.JButton buttonPayments;
    private javax.swing.JButton buttonReports;
    private javax.swing.JButton buttonSettings;
    private javax.swing.JButton buttonUserLog;
    private javax.swing.JButton cusFPAddDecor;
    private javax.swing.JButton cusFPAddPay;
    private javax.swing.JLabel cusFPCustomerNIC;
    private javax.swing.JButton cusFPDemo;
    private javax.swing.JLabel cusFPEventID;
    private javax.swing.JButton cusFPOk;
    private javax.swing.JButton cusFPReset;
    private javax.swing.JPanel customerPanel;
    private javax.swing.JButton customizedFPAdd;
    private javax.swing.JButton customizedFPRemove;
    private javax.swing.JPanel decorationsPanel;
    private javax.swing.JTable eventBookingTable;
    private javax.swing.JPanel eventManagerPanel;
    private javax.swing.JPanel eventPanel;
    private javax.swing.JPanel eventSettingsPanel;
    private javax.swing.JPanel eventUserLogPanel;
    private javax.swing.JPanel foodPackagesPanel;
    private javax.swing.JLabel fpPricedecor;
    private javax.swing.JButton genarateReportEvent;
    private javax.swing.JPanel generateReportsPanel;
    private javax.swing.JLabel hallPrice;
    private javax.swing.JLabel hallPricefp;
    private javax.swing.JLabel hallPricerfp;
    private javax.swing.JLabel hallpricedecor;
    private javax.swing.JPanel headerpanel;
    private javax.swing.JButton jButton11UpdatePaymentUpdate;
    private javax.swing.JButton jButton12UpdateCusSearch;
    private javax.swing.JButton jButton1AddEvent;
    private javax.swing.JButton jButton1LogOut;
    private javax.swing.JButton jButton1ViewEventDemo;
    private javax.swing.JButton jButton3AddCustomerDemo;
    private javax.swing.JButton jButton3AddEventDemo;
    private javax.swing.JButton jButton3ViewCustomerDemo;
    private javax.swing.JButton jButton4AddCustomerAdd;
    private javax.swing.JButton jButton4AddDecorationDemo;
    private javax.swing.JButton jButton4AddPaymentDemo;
    private javax.swing.JButton jButton4EventUpdate;
    private javax.swing.JButton jButton4UpdateCustomerDemo;
    private javax.swing.JButton jButton4UpdateDecorationDemo;
    private javax.swing.JButton jButton4UpdatePaymentDemo;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton5UpdateCustomerUpdate;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7AddDecoration;
    private javax.swing.JButton jButton8AddDecorReset;
    private javax.swing.JButton jButton9AddPaymentAdd;
    private javax.swing.JButton jButton9UpdateBookingsDemo;
    private javax.swing.JButton jButton9UpdateDecoration;
    private javax.swing.JCheckBox jCheckBox1AddTableDecorations;
    private javax.swing.JCheckBox jCheckBox1UpdateTable;
    private javax.swing.JCheckBox jCheckBox2AddWallDecorations;
    private javax.swing.JCheckBox jCheckBox2UpdateWall;
    private javax.swing.JCheckBox jCheckBox3AddFlowerDecoration;
    private javax.swing.JCheckBox jCheckBox3UpdateFlower;
    private javax.swing.JCheckBox jCheckBox4AddDiscoLight;
    private javax.swing.JCheckBox jCheckBox4UpdateDisco;
    private javax.swing.JCheckBox jCheckBox5AddEntranceArch;
    private javax.swing.JCheckBox jCheckBox5UpdateArch;
    private javax.swing.JComboBox jComboAddEventHours;
    private javax.swing.JComboBox jComboAddEventMin;
    private javax.swing.JComboBox jComboAddEventPM;
    private javax.swing.JComboBox jComboBox1AddEventType;
    private javax.swing.JComboBox jComboBox2AddEventEntertainment;
    private javax.swing.JComboBox jComboPayCD;
    private javax.swing.JComboBox jComboPayMethods;
    private javax.swing.JComboBox jComboUpdateCD;
    private javax.swing.JComboBox jComboUpdateDecorColor;
    private javax.swing.JComboBox jComboUpdatePayMethod;
    private com.toedter.calendar.JDateChooser jDateChooser2AddEventDate;
    private com.toedter.calendar.JDateChooser jDateChooser2BookingDate;
    private com.toedter.calendar.JDateChooser jDateChooser2UpdatePayDate;
    private com.toedter.calendar.JDateChooser jDateChooserReportDateTo;
    private com.toedter.calendar.JDateChooser jDateChooserReportsDate1;
    private com.toedter.calendar.JDateChooser jDatePayDueDate;
    private javax.swing.JDesktopPane jDesktopPaneEvent;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel10CustomerTel;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel1AddCustomerName;
    private javax.swing.JLabel jLabel1AddDecorationColor;
    private javax.swing.JLabel jLabel1AddDecorationDescription;
    private javax.swing.JLabel jLabel1AddDecorationTypes;
    private javax.swing.JLabel jLabel1AddSpecialRequirements;
    private javax.swing.JLabel jLabel1EnterEventID;
    private javax.swing.JLabel jLabel1Entertainment;
    private javax.swing.JLabel jLabel1UpdateDecorationEventID;
    private javax.swing.JLabel jLabel1UpdateEnterCustomerNIC;
    private javax.swing.JLabel jLabel1ViewEnterCustomerNIC;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel2AddCustomerNIC;
    private javax.swing.JLabel jLabel2AddEventNoOfGuests;
    private javax.swing.JLabel jLabel2AddPaymentMethod;
    private javax.swing.JLabel jLabel2EventType;
    private javax.swing.JLabel jLabel2UpdatePaymentEventID;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel3AddEventTime;
    private javax.swing.JLabel jLabel3AddPaymentAmount;
    private javax.swing.JLabel jLabel3AddPaymentCredit;
    private javax.swing.JLabel jLabel3EventDate;
    private javax.swing.JLabel jLabel3SystemDate;
    private javax.swing.JLabel jLabel3SystemTime;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel4AddAddress;
    private javax.swing.JLabel jLabel4AddPaymentDescription;
    private javax.swing.JLabel jLabel4CustomerAddress;
    private javax.swing.JLabel jLabel4EventDuration;
    private javax.swing.JLabel jLabel4UpdateCustomerName;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel5AddContactNo;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList3;
    private javax.swing.JList jList4;
    private javax.swing.JList jList5;
    private javax.swing.JList jList6;
    private javax.swing.JList jList7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPaneCustomer;
    private javax.swing.JTabbedPane jTabbedPaneDecorations;
    private javax.swing.JTabbedPane jTabbedPaneEvent;
    private javax.swing.JTabbedPane jTabbedPaneFoodPackages;
    private javax.swing.JTabbedPane jTabbedPanePayments;
    private javax.swing.JTabbedPane jTabbedPaneReports;
    private javax.swing.JTextArea jTextAreaUpdateDecorSR;
    private javax.swing.JTextArea jTextAreaUpdatedecorDes;
    private javax.swing.JComboBox jcomBookingStatus;
    private javax.swing.JComboBox jcomRegFPSelecPack;
    private javax.swing.JPanel mainButtonPanel;
    private javax.swing.JPanel paymentPanel;
    private javax.swing.JButton regFPAddCusFP;
    private javax.swing.JButton regFPAddDecor;
    private javax.swing.JButton regFPAddPay;
    private javax.swing.JButton regFPBack;
    private javax.swing.JLabel regFPCustomerNIC;
    private javax.swing.JButton regFPDemo;
    private javax.swing.JLabel regFPEnterCustomerNIClbl;
    private javax.swing.JLabel regFPEnterEventIDlbl;
    private javax.swing.JLabel regFPEventID;
    private javax.swing.JButton regFPNext;
    private javax.swing.JButton regFPReset;
    private javax.swing.JButton regFPSetTotalPrice;
    private javax.swing.JLabel regFPTotalPricelbl;
    private javax.swing.JLabel regFpackageNolbl;
    private javax.swing.JPanel reportsPanel;
    private javax.swing.JButton selectRFPAdd;
    private javax.swing.JTable selectedCusFoodItemTable;
    private javax.swing.JPanel togglePanels;
    private javax.swing.JTextField txtAddCustomerAddress;
    private javax.swing.JTextField txtAddCustomerNIC;
    private javax.swing.JTextField txtAddCustomerName;
    private javax.swing.JTextField txtAddCustomerTelephoneNo;
    private javax.swing.JTextField txtAddDecorPrice;
    private javax.swing.JTextField txtAddDecorSRPrice;
    private javax.swing.JTextField txtAddEventDuration;
    private javax.swing.JTextField txtAddEventNoOfGuests;
    private javax.swing.JTextField txtAddPaymentAmount;
    private javax.swing.JTextArea txtAreaAddPaymentDescription;
    private javax.swing.JTextArea txtAreaDescription;
    private javax.swing.JTextArea txtAreaSpecialRequirements;
    private javax.swing.JTextArea txtAreaUpdatePaymentDescription;
    private javax.swing.JTextField txtCusFPNoOfPlates;
    private javax.swing.JTextField txtFPNoOfPlates;
    private javax.swing.JTextField txtReportCusNIC;
    private javax.swing.JTextField txtUpdateBookingsCusNIC;
    private javax.swing.JTextField txtUpdateCustomerAdd;
    private javax.swing.JTextField txtUpdateCustomerName;
    private javax.swing.JTextField txtUpdateCustomerTel;
    private javax.swing.JTextField txtUpdateDecorPrice;
    private javax.swing.JTextField txtUpdateDecorSRPrice;
    private javax.swing.JTextField txtUpdateDecorationEventID;
    private javax.swing.JTextField txtUpdateEnterCustomerNIC;
    private javax.swing.JTextField txtUpdatePayAmount;
    private javax.swing.JTextField txtUpdatePayBalance;
    private javax.swing.JTextField txtUpdatePaymentCusNIC;
    private javax.swing.JTextField txtViewCustomerEnterCustomerNIC;
    private javax.swing.JTextField txtViewEventID;
    private javax.swing.JPanel updateBookingsPanel;
    private javax.swing.JButton updateBookingsReset;
    private javax.swing.JButton updateCusBack;
    private javax.swing.JLabel updateCusEventID;
    private javax.swing.JLabel updateCusEventIDlbl;
    private javax.swing.JButton updateCusNext;
    private javax.swing.JPanel updateCustomerPanel;
    private javax.swing.JTable updateCustomerTable;
    private javax.swing.JButton updateDecorBack;
    private javax.swing.JLabel updateDecorEventIDlb;
    private javax.swing.JLabel updateDecorIDlbl;
    private javax.swing.JButton updateDecorNext;
    private javax.swing.JLabel updateDecorPricelbl;
    private javax.swing.JButton updateDecorReset;
    private javax.swing.JLabel updateDecorSRPricelbl;
    private javax.swing.JButton updateDecorSearch;
    private javax.swing.JTable updateDecorTable;
    private javax.swing.JLabel updateDecorTotal;
    private javax.swing.JPanel updateDecorationsPanel;
    private javax.swing.JButton updatePayBack;
    private javax.swing.JButton updatePayNext;
    private javax.swing.JButton updatePayReset;
    private javax.swing.JTable updatePayTable;
    private javax.swing.JLabel updatePayementDeslbl;
    private javax.swing.JLabel updatePaymentDueDatelbl;
    private javax.swing.JLabel updatePaymentEventIDlbl;
    private javax.swing.JButton updatePaymentSearch;
    private javax.swing.JPanel updatePaymentsPanel;
    private javax.swing.JButton viewCusBack;
    private javax.swing.JTable viewCusFPTable;
    private javax.swing.JButton viewCusNext;
    private javax.swing.JButton viewCusReset;
    private javax.swing.JButton viewCusSearch;
    private javax.swing.JPanel viewCustomerPanel;
    private javax.swing.JTable viewCustomerTable;
    private javax.swing.JButton viewEventBack;
    private javax.swing.JButton viewEventNext;
    private javax.swing.JPanel viewEventPanel;
    private javax.swing.JButton viewEventReset;
    private javax.swing.JTable viewEventTable;
    // End of variables declaration//GEN-END:variables

}
