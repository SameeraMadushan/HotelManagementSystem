//GLEN RESTAURANT MANAGEMENT SYSTEM 

package glen.restaurant;

import com.toedter.calendar.JDateChooser;
import glen.DBconnect;
import glen.System_Login;
import java.awt.*;
import java.awt.event.KeyEvent;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;
import java.util.ArrayList;
import java.util.List;

public class Restaurant_Manager extends javax.swing.JFrame
{
    Connection conn = DBconnect.ConnectDB(); 
    PreparedStatement pre = null;
    ResultSet rs = null;     
    JButton[] menubuttons ;
    
    Calendar now = Calendar.getInstance();
    int month = now.get(Calendar.MONTH); 
    int date = now.get(Calendar.DATE); 
    int year = now.get(Calendar.YEAR);
    String Date = date + " - " + month + " - " + year;             
                
    public void date()
    {
        Thread clock = new Thread(){
        public void run()
        {
            for(;;)
            {
                Calendar now = Calendar.getInstance();
                int second=now.get(Calendar.SECOND);
                int min=now.get(Calendar.MINUTE);
                int hr=now.get(Calendar.HOUR_OF_DAY);
                String t = String.format("%02d:%02d:%02d", hr, min, second);
                timelabel.setText(t);                
                try 
                {
                    sleep(1000);
                } 
                catch (InterruptedException ex) 
                {
                    System.out.println(ex);
                }
                        
            }
        }
    };
    clock.start();
               
    }
    
        
    public Restaurant_Manager()
    {
        initComponents();
        this.setLocationRelativeTo(null); //Setting to display in the center of screen
        //setting the welcome screen for a logged in user
        panelWelcome.setVisible(true);
        panelRestaurantOrder.setVisible(false);
        panelCustomer.setVisible(false);
        panelPayment.setVisible(false);
        panelFoodMenu.setVisible(false);
        panelOtherOrders.setVisible(false);
        panelEmployee.setVisible(false);
        panelStockOrder.setVisible(false);
        panelMaintenance.setVisible(false);
        panelReports.setVisible(false); 
        panelUsers.setVisible(false);
        date();
        day.setText(Date);
        
        //setting colors
        comboBoxAddOrderSelectMenu.setBackground(new Color(255, 255, 255));
        menubuttons = new JButton[]{buttonRestaurantOrder,buttonCustomer,buttonFoodMenu,buttonOtherOrders,buttonEmployee,buttonStockOrder,buttonMaintenance,buttonReports,buttonUsers};
      /*  textFieldAddCustomerNIC.setDocument(new JTextFieldLimit(10));
        textFieldUpdateOrderOrderID.setDocument(new JTextFieldLimit(6));
        textFieldRemoveOrderOrderID.setDocument(new JTextFieldLimit(6));
        textFieldUpdateOrderOrderID.setDocument(new JTextFieldLimit(6));
        textFieldSerachOrderOrderID.setDocument(new JTextFieldLimit(6));
        textFieldSerachOrderOrderID1.setDocument(new JTextFieldLimit(10));
        textFieldSerachOrderOrderID3.setDocument(new JTextFieldLimit(1));
        textFieldSearchCustomerCustomerID.setDocument(new JTextFieldLimit(10));*/
    }       
    
    //variables decalaration
    String orderdetails[] = new String [20];
    String orderdescription = "";
    String customerdetails[] = new String [3];
    JComboBox comboBox;
    List<String> data = new ArrayList<String>();
    int clicked1,clicked2,clicked3,clicked4,clicked5,clicked6,clicked7,clicked8,clicked9 = 0; 
    int niccount=0;
    boolean cusA = false; //customer not in db
    boolean ovalidity = false; //order
    boolean mvalidity = false; //menu
    boolean cvalidity = false; //nic
    boolean dvalidity = false; //date
    boolean svalidity = false; //status

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        mainDesktopPane = new javax.swing.JDesktopPane();
        menuPanel = new javax.swing.JPanel();
        buttonRestaurantOrder = new javax.swing.JButton();
        buttonCustomer = new javax.swing.JButton();
        buttonEmployee = new javax.swing.JButton();
        buttonFoodMenu = new javax.swing.JButton();
        buttonStockOrder = new javax.swing.JButton();
        buttonUsers = new javax.swing.JButton();
        buttonMaintenance = new javax.swing.JButton();
        buttonOtherOrders = new javax.swing.JButton();
        buttonReports = new javax.swing.JButton();
        headerPanel = new javax.swing.JPanel();
        buttonLogOut = new javax.swing.JButton();
        day = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        timelabel = new javax.swing.JLabel();
        contentPanel = new javax.swing.JPanel();
        panelWelcome = new javax.swing.JPanel();
        labelWelcome = new javax.swing.JLabel();
        labelWelcomeMessage = new javax.swing.JLabel();
        panelRestaurantOrder = new javax.swing.JPanel();
        panelRestaurantContent = new javax.swing.JPanel();
        buttonRestaurantContentAddOrder = new javax.swing.JButton();
        buttonRestaurantContentUpdateOrder = new javax.swing.JButton();
        buttonRestaurantContentRemoveOrder = new javax.swing.JButton();
        buttonRestaurantContentSearchOrder = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        tabbedPaneRestaurantOrder = new javax.swing.JTabbedPane();
        panelAddOrder = new javax.swing.JPanel();
        comboBoxAddOrderSelectMenu = new javax.swing.JComboBox<>();
        labelAddOrderMenuID = new javax.swing.JLabel();
        labelAddOrderOrderDescription = new javax.swing.JLabel();
        buttonAddOrderDemo = new javax.swing.JButton();
        buttonAddOrderNext = new javax.swing.JButton();
        buttonAddOrderReset = new javax.swing.JButton();
        scrollPaneAddOrderItems = new javax.swing.JScrollPane();
        listAddOrderItems1 = new javax.swing.JList<>();
        buttonAddOrderAdd = new javax.swing.JButton();
        labelAddOrderOrderDescription1 = new javax.swing.JLabel();
        buttonAddOrderRemove = new javax.swing.JButton();
        buttonAddOrderNext1 = new javax.swing.JButton();
        orderScrollPane = new javax.swing.JScrollPane();
        tableOrderitems = new javax.swing.JTable();
        panelUpdateOrder = new javax.swing.JPanel();
        labelUpdateOrderMenuID = new javax.swing.JLabel();
        comboBoxUpdateOrderSelectMenu = new javax.swing.JComboBox<>();
        labelUpdateOrderOrderID = new javax.swing.JLabel();
        textFieldUpdateOrderOrderID = new javax.swing.JTextField();
        buttonUpdateOrderDemo = new javax.swing.JButton();
        labelUpdateOrderOrderDescription = new javax.swing.JLabel();
        buttonUpdateOrderUpdateOrder = new javax.swing.JButton();
        buttonUpdateOrderHome = new javax.swing.JButton();
        buttonUpdateOrderReset = new javax.swing.JButton();
        scrollPaneAddOrderItems1 = new javax.swing.JScrollPane();
        listAddOrderItems2 = new javax.swing.JList<>();
        scrollPaneUpdateOrder = new javax.swing.JScrollPane();
        tableUpdateOrder = new javax.swing.JTable();
        labelUpdateOrderOrderDescription1 = new javax.swing.JLabel();
        updateAdd = new javax.swing.JButton();
        updateRemove = new javax.swing.JButton();
        panelRemoveOrder = new javax.swing.JPanel();
        buttonRemoveOrderRemoveOrder = new javax.swing.JButton();
        buttonRemoveOrderHome = new javax.swing.JButton();
        buttonRemoveOrderDemo = new javax.swing.JButton();
        textFieldRemoveOrderOrderID = new javax.swing.JTextField();
        labelRemoveOrderOrderID = new javax.swing.JLabel();
        buttonRemoveOrderReset = new javax.swing.JButton();
        buttonRemoveOrderSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRemoveOrderResultsTable = new javax.swing.JTable();
        panelSearchOrder = new javax.swing.JPanel();
        labelSearchOrderOrderID = new javax.swing.JLabel();
        textFieldSerachOrderOrderID = new javax.swing.JTextField();
        buttonSearchOrderDemo = new javax.swing.JButton();
        scrollPaneSearchOrderResultsTable = new javax.swing.JScrollPane();
        tableSearchOrderTable = new javax.swing.JTable();
        buttonSearchOrderHome = new javax.swing.JButton();
        buttonSearchOrderPrint = new javax.swing.JButton();
        buttonSearchOrderReset = new javax.swing.JButton();
        buttonSearchOrderSearch = new javax.swing.JButton();
        comboBoxUpdateOrderSelectMenu1 = new javax.swing.JComboBox<>();
        labelUpdateOrderMenuID1 = new javax.swing.JLabel();
        labelSearchOrderOrderID1 = new javax.swing.JLabel();
        textFieldSerachOrderOrderID1 = new javax.swing.JTextField();
        labelSearchOrderOrderID2 = new javax.swing.JLabel();
        labelSearchOrderOrderID3 = new javax.swing.JLabel();
        textFieldSerachOrderOrderID3 = new javax.swing.JTextField();
        orderDate = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        panelCustomer = new javax.swing.JPanel();
        panelCustomerContent = new javax.swing.JPanel();
        buttonCustomerContentAddCustomer = new javax.swing.JButton();
        buttonCustomerContentSearchCustomer = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        buttonCustomerContentAddCustomer1 = new javax.swing.JButton();
        tabbedPaneCustomer = new javax.swing.JTabbedPane();
        panelAddCustomer = new javax.swing.JPanel();
        labelAddCustomerName = new javax.swing.JLabel();
        textFieldAddCustomerName = new javax.swing.JTextField();
        buttonAddCustomerDemo = new javax.swing.JButton();
        buttonAddCustomerReset = new javax.swing.JButton();
        labelAddCustomerTelephone = new javax.swing.JLabel();
        labelAddCustomerEmail = new javax.swing.JLabel();
        textFieldAddCustomerEmail = new javax.swing.JTextField();
        countryCode = new javax.swing.JTextField();
        buttonAddCustomerNext = new javax.swing.JButton();
        buttonAddCustomerHome = new javax.swing.JButton();
        buttonAddCustomerBack = new javax.swing.JButton();
        labelAddCustomerName1 = new javax.swing.JLabel();
        textFieldAddCustomerNIC = new javax.swing.JTextField();
        textFieldAddCustomerTelephone2 = new javax.swing.JTextField();
        textFieldAddCustomerTelephone1 = new javax.swing.JTextField();
        textFieldAddCustomerTelephone3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        customerStatus = new javax.swing.JLabel();
        panelSearchCustomer = new javax.swing.JPanel();
        labelSearchCustomerCustomerID = new javax.swing.JLabel();
        textFieldSearchCustomerCustomerID = new javax.swing.JTextField();
        buttonSearchCustomerReset = new javax.swing.JButton();
        buttonSearchCustomerDemo = new javax.swing.JButton();
        scrollPaneSearchCustomerResultsTable = new javax.swing.JScrollPane();
        tableSearchCustomerResultsTable = new javax.swing.JTable();
        buttonSearchCustomerSearch = new javax.swing.JButton();
        buttonSearchCustomerHome = new javax.swing.JButton();
        panelUpdateCustomer = new javax.swing.JPanel();
        labelSearchCustomerCustomerID1 = new javax.swing.JLabel();
        textFieldSearchCustomerCustomerID1 = new javax.swing.JTextField();
        buttonSearchCustomerSearch1 = new javax.swing.JButton();
        buttonSearchCustomerReset1 = new javax.swing.JButton();
        buttonSearchCustomerDemo1 = new javax.swing.JButton();
        scrollPaneSearchCustomerResultsTable1 = new javax.swing.JScrollPane();
        tableSearchCustomerResultsTable1 = new javax.swing.JTable();
        buttonSearchCustomerHome1 = new javax.swing.JButton();
        buttonUpdateCustomer = new javax.swing.JButton();
        panelPayment = new javax.swing.JPanel();
        buttonPaymentHome = new javax.swing.JButton();
        buttonPaymentDone = new javax.swing.JButton();
        labelPaymentPaymentMethod = new javax.swing.JLabel();
        comboBoxPaymentPaymentType = new javax.swing.JComboBox<>();
        labelPaymentAmount = new javax.swing.JLabel();
        textFieldPaymentAmount = new javax.swing.JTextField();
        scrollPanePaymentOrderDescription = new javax.swing.JScrollPane();
        textAreaPaymentOrderDescription = new javax.swing.JTextArea();
        labelPaymentPaymentDescription = new javax.swing.JLabel();
        buttonPaymentPrintBill = new javax.swing.JButton();
        buttonPaymentBack = new javax.swing.JButton();
        labelPaymentAmount1 = new javax.swing.JLabel();
        labelPaymentAmount2 = new javax.swing.JLabel();
        textFieldPaymentPaidAmount = new javax.swing.JTextField();
        textFieldPaymentBalance = new javax.swing.JTextField();
        labelMenuID = new javax.swing.JLabel();
        labelPaymentPaymentDescription1 = new javax.swing.JLabel();
        panelFoodMenu = new javax.swing.JPanel();
        panelFoodMenuContent = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        buttonRestaurantContentAddOrder1 = new javax.swing.JButton();
        buttonRestaurantContentUpdateOrder1 = new javax.swing.JButton();
        buttonRestaurantContentRemoveOrder1 = new javax.swing.JButton();
        buttonRestaurantContentSearchOrder1 = new javax.swing.JButton();
        tabbedPaneFoodMenu = new javax.swing.JTabbedPane();
        panelAddMenu = new javax.swing.JPanel();
        buttonFoodMenuReset = new javax.swing.JButton();
        labelFoodMenuMenuPrice = new javax.swing.JLabel();
        textFieldFoodMenuMenuPrice = new javax.swing.JTextField();
        lablFoodMenuMenuDescription = new javax.swing.JLabel();
        buttonFoodMenuHome = new javax.swing.JButton();
        buttonFoodMenuAddMenu = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        foodItems = new javax.swing.JTable();
        buttonFoodMenuHome1 = new javax.swing.JButton();
        buttonFoodMenuHome2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        panelUpdateMenu = new javax.swing.JPanel();
        lablFoodMenuMenuDescription1 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        buttonFoodMenuHome3 = new javax.swing.JButton();
        buttonFoodMenuHome4 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        foodItems1 = new javax.swing.JTable();
        buttonFoodMenuReset1 = new javax.swing.JButton();
        buttonFoodMenuHome5 = new javax.swing.JButton();
        buttonFoodMenuAddMenu1 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        textFieldFoodMenuMenuPrice1 = new javax.swing.JTextField();
        labelFoodMenuMenuPrice1 = new javax.swing.JLabel();
        labelFoodMenuMenuPrice2 = new javax.swing.JLabel();
        textFieldFoodMenuID = new javax.swing.JTextField();
        buttonFoodMenuReset2 = new javax.swing.JButton();
        panelRemoveMenu = new javax.swing.JPanel();
        buttonRemoveMenuReset = new javax.swing.JButton();
        buttonRemoveMenuDemo = new javax.swing.JButton();
        buttonRemoveMenuHome = new javax.swing.JButton();
        labelRemoveMenuMenuID = new javax.swing.JLabel();
        textFieldRemoveMenuMenuID = new javax.swing.JTextField();
        scrollPaneRemoveMenuResultsTable = new javax.swing.JScrollPane();
        tableRemoveMenuResultsTable = new javax.swing.JTable();
        buttonRemoveMenuSearch = new javax.swing.JButton();
        buttonRemoveMenuRemoveMenu = new javax.swing.JButton();
        panelViewMenu = new javax.swing.JPanel();
        textFieldViewMenuMenuID = new javax.swing.JTextField();
        labelViewMenuMenuID = new javax.swing.JLabel();
        buttonViewMenuReset = new javax.swing.JButton();
        buttonViewMenuDemo = new javax.swing.JButton();
        buttonViewMenuSearch = new javax.swing.JButton();
        buttonViewMenuHome = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        textFieldViewMenuMenuID1 = new javax.swing.JTextField();
        labelViewMenuMenuID1 = new javax.swing.JLabel();
        labelViewMenuMenuID2 = new javax.swing.JLabel();
        textFieldViewMenuMenuID2 = new javax.swing.JTextField();
        panelOtherOrders = new javax.swing.JPanel();
        tabbedPaneOtherOrders = new javax.swing.JTabbedPane();
        panelRoomOrders = new javax.swing.JPanel();
        scrollPaneRoomOrders = new javax.swing.JScrollPane();
        roomOrders = new javax.swing.JTable();
        buttonAddCustomerHome1 = new javax.swing.JButton();
        buttonAddCustomerNext1 = new javax.swing.JButton();
        buttonAddCustomerDemo1 = new javax.swing.JButton();
        buttonAddCustomerReset1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        roomOrderID = new javax.swing.JTextField();
        buttonAddCustomerNext2 = new javax.swing.JButton();
        buttonAddCustomerNext4 = new javax.swing.JButton();
        buttonAddCustomerNext3 = new javax.swing.JButton();
        panelEventOrders = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        buttonAddCustomerReset2 = new javax.swing.JButton();
        buttonAddCustomerDemo2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        eventOrders = new javax.swing.JTable();
        buttonAddCustomerNext6 = new javax.swing.JButton();
        buttonAddCustomerNext7 = new javax.swing.JButton();
        buttonAddCustomerHome3 = new javax.swing.JButton();
        buttonAddCustomerHome4 = new javax.swing.JButton();
        buttonAddCustomerNext5 = new javax.swing.JButton();
        roomOrderID1 = new javax.swing.JTextField();
        panelEmployee = new javax.swing.JPanel();
        panelEmployeDetails = new javax.swing.JPanel();
        buttonEmployeeReset = new javax.swing.JButton();
        buttonEmployeeDemo = new javax.swing.JButton();
        buttonEmployeeNext = new javax.swing.JButton();
        labelEmployeeID = new javax.swing.JLabel();
        textFieldEmployeeID = new javax.swing.JTextField();
        scrollPaneEmployeeResultsTable = new javax.swing.JScrollPane();
        tableEmployeeResultsTable = new javax.swing.JTable();
        buttonEmployeeSearchSingle = new javax.swing.JButton();
        buttonEmployeePrint = new javax.swing.JButton();
        panelStockOrder = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        panelMaintenance = new javax.swing.JPanel();
        accountDepositLabel = new javax.swing.JLabel();
        accountDepositTextField = new javax.swing.JTextField();
        accountBalanceLabel = new javax.swing.JLabel();
        accountBalanceTextField = new javax.swing.JTextField();
        accountRequestAmountLabel = new javax.swing.JLabel();
        accountRequestAmountTextField = new javax.swing.JTextField();
        accountRequestCreditButton = new javax.swing.JButton();
        panelReports = new javax.swing.JPanel();
        labelParameter = new javax.swing.JLabel();
        buttonDemoReports = new javax.swing.JButton();
        buttonResetReports = new javax.swing.JButton();
        textFieldParameter = new javax.swing.JTextField();
        buttonPrintReport = new javax.swing.JButton();
        buttonGenerateReport = new javax.swing.JButton();
        buttonHomeReport = new javax.swing.JButton();
        panelUsers = new javax.swing.JPanel();
        labelUserName = new javax.swing.JLabel();
        textFieldEmployeeID1 = new javax.swing.JTextField();
        buttonEmployeeSearchAll1 = new javax.swing.JButton();
        scrollPaneEmployeeResultsTable1 = new javax.swing.JScrollPane();
        tableEmployeeResultsTable1 = new javax.swing.JTable();
        buttonEmployeeNext1 = new javax.swing.JButton();
        buttonEmployeeDemo1 = new javax.swing.JButton();
        buttonEmployeeReset1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 700));
        setResizable(false);

        mainDesktopPane.setBackground(new java.awt.Color(255, 255, 255));
        mainDesktopPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        menuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        menuPanel.setLayout(null);

        buttonRestaurantOrder.setBackground(new java.awt.Color(51, 153, 255));
        buttonRestaurantOrder.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonRestaurantOrder.setForeground(new java.awt.Color(255, 255, 255));
        buttonRestaurantOrder.setText("RESTAURANT ORDER");
        buttonRestaurantOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonRestaurantOrderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonRestaurantOrderMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonRestaurantOrderMouseClicked(evt);
            }
        });
        buttonRestaurantOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRestaurantOrderActionPerformed(evt);
            }
        });
        menuPanel.add(buttonRestaurantOrder);
        buttonRestaurantOrder.setBounds(10, 20, 170, 50);

        buttonCustomer.setBackground(new java.awt.Color(51, 153, 255));
        buttonCustomer.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonCustomer.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustomer.setText("CUSTOMER");
        buttonCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonCustomerMouseClicked(evt);
            }
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
        menuPanel.add(buttonCustomer);
        buttonCustomer.setBounds(10, 80, 170, 50);

        buttonEmployee.setBackground(new java.awt.Color(51, 153, 255));
        buttonEmployee.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonEmployee.setForeground(new java.awt.Color(255, 255, 255));
        buttonEmployee.setText("EMPLOYEE");
        buttonEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonEmployeeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonEmployeeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonEmployeeMouseExited(evt);
            }
        });
        buttonEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEmployeeActionPerformed(evt);
            }
        });
        menuPanel.add(buttonEmployee);
        buttonEmployee.setBounds(10, 260, 170, 50);

        buttonFoodMenu.setBackground(new java.awt.Color(51, 153, 255));
        buttonFoodMenu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonFoodMenu.setForeground(new java.awt.Color(255, 255, 255));
        buttonFoodMenu.setText("MENU");
        buttonFoodMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonFoodMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonFoodMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonFoodMenuMouseExited(evt);
            }
        });
        buttonFoodMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFoodMenuActionPerformed(evt);
            }
        });
        menuPanel.add(buttonFoodMenu);
        buttonFoodMenu.setBounds(10, 140, 170, 50);

        buttonStockOrder.setBackground(new java.awt.Color(51, 153, 255));
        buttonStockOrder.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonStockOrder.setForeground(new java.awt.Color(255, 255, 255));
        buttonStockOrder.setText("STOCK ORDER");
        buttonStockOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonStockOrderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonStockOrderMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonStockOrderMouseExited(evt);
            }
        });
        buttonStockOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStockOrderActionPerformed(evt);
            }
        });
        menuPanel.add(buttonStockOrder);
        buttonStockOrder.setBounds(10, 320, 170, 50);

        buttonUsers.setBackground(new java.awt.Color(51, 153, 255));
        buttonUsers.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonUsers.setForeground(new java.awt.Color(255, 255, 255));
        buttonUsers.setText("USERS");
        buttonUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonUsersMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonUsersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonUsersMouseExited(evt);
            }
        });
        buttonUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUsersActionPerformed(evt);
            }
        });
        menuPanel.add(buttonUsers);
        buttonUsers.setBounds(10, 500, 170, 50);

        buttonMaintenance.setBackground(new java.awt.Color(51, 153, 255));
        buttonMaintenance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonMaintenance.setForeground(new java.awt.Color(255, 255, 255));
        buttonMaintenance.setText("MAINTENANCE");
        buttonMaintenance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonMaintenanceMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonMaintenanceMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonMaintenanceMouseExited(evt);
            }
        });
        buttonMaintenance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMaintenanceActionPerformed(evt);
            }
        });
        menuPanel.add(buttonMaintenance);
        buttonMaintenance.setBounds(10, 380, 170, 50);

        buttonOtherOrders.setBackground(new java.awt.Color(51, 153, 255));
        buttonOtherOrders.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonOtherOrders.setForeground(new java.awt.Color(255, 255, 255));
        buttonOtherOrders.setText("OTHER ORDERS");
        buttonOtherOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonOtherOrdersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonOtherOrdersMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonOtherOrdersMouseClicked(evt);
            }
        });
        buttonOtherOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOtherOrdersActionPerformed(evt);
            }
        });
        menuPanel.add(buttonOtherOrders);
        buttonOtherOrders.setBounds(10, 200, 170, 50);

        buttonReports.setBackground(new java.awt.Color(51, 153, 255));
        buttonReports.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonReports.setForeground(new java.awt.Color(255, 255, 255));
        buttonReports.setText("REPORTS");
        buttonReports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonReportsMouseClicked(evt);
            }
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
        menuPanel.add(buttonReports);
        buttonReports.setBounds(10, 440, 170, 50);

        mainDesktopPane.add(menuPanel);
        menuPanel.setBounds(10, 120, 190, 570);

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        headerPanel.setMaximumSize(new java.awt.Dimension(1000, 100));
        headerPanel.setMinimumSize(new java.awt.Dimension(1000, 100));
        headerPanel.setPreferredSize(new java.awt.Dimension(1000, 100));

        buttonLogOut.setBackground(new java.awt.Color(255, 255, 255));
        buttonLogOut.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonLogOut.setForeground(new java.awt.Color(51, 153, 255));
        buttonLogOut.setText("Log Out");
        buttonLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogOutActionPerformed(evt);
            }
        });

        day.setBackground(new java.awt.Color(255, 255, 255));
        day.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        day.setForeground(new java.awt.Color(51, 153, 255));
        day.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day.setText("date");

        timelabel.setBackground(new java.awt.Color(255, 255, 255));
        timelabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        timelabel.setForeground(new java.awt.Color(51, 153, 255));
        timelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timelabel.setText("time");

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(day, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timelabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                        .addComponent(buttonLogOut)
                        .addGap(39, 39, 39)))
                .addGap(18, 18, 18))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(headerPanelLayout.createSequentialGroup()
                        .addComponent(day)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timelabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        mainDesktopPane.add(headerPanel);
        headerPanel.setBounds(10, 10, 1000, 100);

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));

        panelWelcome.setBackground(new java.awt.Color(255, 255, 255));
        panelWelcome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        panelWelcome.setPreferredSize(new java.awt.Dimension(800, 570));

        labelWelcome.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelWelcome.setText("WELCOME!");

        labelWelcomeMessage.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelWelcomeMessage.setText("GLEN RESTAURANT MANAGEMENT SYSTEM");

        javax.swing.GroupLayout panelWelcomeLayout = new javax.swing.GroupLayout(panelWelcome);
        panelWelcome.setLayout(panelWelcomeLayout);
        panelWelcomeLayout.setHorizontalGroup(
            panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWelcomeLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelWelcomeMessage))
                .addContainerGap(166, Short.MAX_VALUE))
        );
        panelWelcomeLayout.setVerticalGroup(
            panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelWelcomeLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(labelWelcomeMessage)
                .addGap(97, 97, 97)
                .addComponent(labelWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(319, Short.MAX_VALUE))
        );

        panelRestaurantOrder.setBackground(new java.awt.Color(255, 255, 255));
        panelRestaurantOrder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        panelRestaurantOrder.setPreferredSize(new java.awt.Dimension(800, 570));
        panelRestaurantOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelRestaurantOrderMouseEntered(evt);
            }
        });

        panelRestaurantContent.setBackground(new java.awt.Color(255, 255, 255));
        panelRestaurantContent.setPreferredSize(new java.awt.Dimension(800, 570));

        buttonRestaurantContentAddOrder.setBackground(new java.awt.Color(51, 153, 255));
        buttonRestaurantContentAddOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonRestaurantContentAddOrder.setForeground(new java.awt.Color(255, 255, 255));
        buttonRestaurantContentAddOrder.setText("Add Order");
        buttonRestaurantContentAddOrder.setName(""); // NOI18N
        buttonRestaurantContentAddOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRestaurantContentAddOrderActionPerformed(evt);
            }
        });

        buttonRestaurantContentUpdateOrder.setBackground(new java.awt.Color(51, 153, 255));
        buttonRestaurantContentUpdateOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonRestaurantContentUpdateOrder.setForeground(new java.awt.Color(255, 255, 255));
        buttonRestaurantContentUpdateOrder.setText("Update Order");
        buttonRestaurantContentUpdateOrder.setName(""); // NOI18N
        buttonRestaurantContentUpdateOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRestaurantContentUpdateOrderActionPerformed(evt);
            }
        });

        buttonRestaurantContentRemoveOrder.setBackground(new java.awt.Color(51, 153, 255));
        buttonRestaurantContentRemoveOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonRestaurantContentRemoveOrder.setForeground(new java.awt.Color(255, 255, 255));
        buttonRestaurantContentRemoveOrder.setText("Remove Order");
        buttonRestaurantContentRemoveOrder.setName(""); // NOI18N
        buttonRestaurantContentRemoveOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRestaurantContentRemoveOrderActionPerformed(evt);
            }
        });

        buttonRestaurantContentSearchOrder.setBackground(new java.awt.Color(51, 153, 255));
        buttonRestaurantContentSearchOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonRestaurantContentSearchOrder.setForeground(new java.awt.Color(255, 255, 255));
        buttonRestaurantContentSearchOrder.setText("Search Order");
        buttonRestaurantContentSearchOrder.setName(""); // NOI18N
        buttonRestaurantContentSearchOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRestaurantContentSearchOrderActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 51));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("RESTAURANT ORDER MAIN");

        javax.swing.GroupLayout panelRestaurantContentLayout = new javax.swing.GroupLayout(panelRestaurantContent);
        panelRestaurantContent.setLayout(panelRestaurantContentLayout);
        panelRestaurantContentLayout.setHorizontalGroup(
            panelRestaurantContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRestaurantContentLayout.createSequentialGroup()
                .addGroup(panelRestaurantContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRestaurantContentLayout.createSequentialGroup()
                        .addGap(277, 277, 277)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRestaurantContentLayout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addGroup(panelRestaurantContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonRestaurantContentRemoveOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonRestaurantContentSearchOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonRestaurantContentUpdateOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonRestaurantContentAddOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(273, Short.MAX_VALUE))
        );
        panelRestaurantContentLayout.setVerticalGroup(
            panelRestaurantContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRestaurantContentLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel9)
                .addGap(59, 59, 59)
                .addComponent(buttonRestaurantContentAddOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(buttonRestaurantContentUpdateOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(buttonRestaurantContentRemoveOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonRestaurantContentSearchOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );

        tabbedPaneRestaurantOrder.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPaneRestaurantOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabbedPaneRestaurantOrder.setPreferredSize(new java.awt.Dimension(798, 580));

        panelAddOrder.setBackground(new java.awt.Color(255, 255, 255));
        panelAddOrder.setMaximumSize(new java.awt.Dimension(796, 549));
        panelAddOrder.setMinimumSize(new java.awt.Dimension(796, 549));
        panelAddOrder.setPreferredSize(new java.awt.Dimension(796, 549));
        panelAddOrder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboBoxAddOrderSelectMenu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboBoxAddOrderSelectMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select Menu--", "Menu R001", "Menu R002", "Menu R003", "Menu R004", " ", " " }));
        comboBoxAddOrderSelectMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxAddOrderSelectMenuActionPerformed(evt);
            }
        });
        panelAddOrder.add(comboBoxAddOrderSelectMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(278, 35, 121, -1));

        labelAddOrderMenuID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAddOrderMenuID.setText("Menu ID");
        panelAddOrder.add(labelAddOrderMenuID, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 35, 47, 20));

        labelAddOrderOrderDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAddOrderOrderDescription.setText("Order Items List");
        panelAddOrder.add(labelAddOrderOrderDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 101, -1, -1));

        buttonAddOrderDemo.setBackground(new java.awt.Color(51, 153, 255));
        buttonAddOrderDemo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonAddOrderDemo.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddOrderDemo.setText("DEMO");
        buttonAddOrderDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddOrderDemoActionPerformed(evt);
            }
        });
        panelAddOrder.add(buttonAddOrderDemo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 35, -1, 37));

        buttonAddOrderNext.setBackground(new java.awt.Color(51, 153, 255));
        buttonAddOrderNext.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonAddOrderNext.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddOrderNext.setText("Next");
        buttonAddOrderNext.setName(""); // NOI18N
        buttonAddOrderNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddOrderNextActionPerformed(evt);
            }
        });
        panelAddOrder.add(buttonAddOrderNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 493, 95, 33));

        buttonAddOrderReset.setBackground(new java.awt.Color(51, 153, 255));
        buttonAddOrderReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonAddOrderReset.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddOrderReset.setText("RESET");
        buttonAddOrderReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddOrderResetActionPerformed(evt);
            }
        });
        panelAddOrder.add(buttonAddOrderReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 35, -1, 37));

        listAddOrderItems1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listAddOrderItems1MouseClicked(evt);
            }
        });
        scrollPaneAddOrderItems.setViewportView(listAddOrderItems1);

        panelAddOrder.add(scrollPaneAddOrderItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 255, 345));

        buttonAddOrderAdd.setBackground(new java.awt.Color(255, 255, 255));
        buttonAddOrderAdd.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        buttonAddOrderAdd.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddOrderAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        buttonAddOrderAdd.setEnabled(false);
        buttonAddOrderAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddOrderAddActionPerformed(evt);
            }
        });
        panelAddOrder.add(buttonAddOrderAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 50, 40));

        labelAddOrderOrderDescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAddOrderOrderDescription1.setText("Menu Items List");
        panelAddOrder.add(labelAddOrderOrderDescription1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, -1, -1));

        buttonAddOrderRemove.setBackground(new java.awt.Color(255, 255, 255));
        buttonAddOrderRemove.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 12)); // NOI18N
        buttonAddOrderRemove.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddOrderRemove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        buttonAddOrderRemove.setEnabled(false);
        buttonAddOrderRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddOrderRemoveActionPerformed(evt);
            }
        });
        panelAddOrder.add(buttonAddOrderRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 50, 40));

        buttonAddOrderNext1.setBackground(new java.awt.Color(51, 153, 255));
        buttonAddOrderNext1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonAddOrderNext1.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddOrderNext1.setText("Home");
        buttonAddOrderNext1.setName(""); // NOI18N
        buttonAddOrderNext1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddOrderNext1ActionPerformed(evt);
            }
        });
        panelAddOrder.add(buttonAddOrderNext1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 493, 95, 33));

        tableOrderitems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ){public boolean isCellEditable(int row, int column){return false;}});
        tableOrderitems.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tableOrderitemsComponentShown(evt);
            }
        });
        orderScrollPane.setViewportView(tableOrderitems);

        panelAddOrder.add(orderScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 134, 392, 341));

        tabbedPaneRestaurantOrder.addTab("Add Order", panelAddOrder);

        panelUpdateOrder.setBackground(new java.awt.Color(255, 255, 255));
        panelUpdateOrder.setMaximumSize(new java.awt.Dimension(796, 549));
        panelUpdateOrder.setMinimumSize(new java.awt.Dimension(796, 549));
        panelUpdateOrder.setPreferredSize(new java.awt.Dimension(796, 549));
        panelUpdateOrder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelUpdateOrderMenuID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelUpdateOrderMenuID.setText("Menu ID");
        labelUpdateOrderMenuID.setName(""); // NOI18N
        panelUpdateOrder.add(labelUpdateOrderMenuID, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, -1));

        comboBoxUpdateOrderSelectMenu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboBoxUpdateOrderSelectMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select Menu--", "Menu R001", "Menu R002", "Menu R003", "Menu R004", " ", " " }));
        comboBoxUpdateOrderSelectMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUpdateOrderSelectMenuActionPerformed(evt);
            }
        });
        panelUpdateOrder.add(comboBoxUpdateOrderSelectMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 33, 121, -1));

        labelUpdateOrderOrderID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelUpdateOrderOrderID.setText("Order ID");
        panelUpdateOrder.add(labelUpdateOrderOrderID, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 35, -1, 20));

        textFieldUpdateOrderOrderID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldUpdateOrderOrderID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldUpdateOrderOrderID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldUpdateOrderOrderID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldUpdateOrderOrderIDActionPerformed(evt);
            }
        });
        textFieldUpdateOrderOrderID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldUpdateOrderOrderIDKeyReleased(evt);
            }
        });
        panelUpdateOrder.add(textFieldUpdateOrderOrderID, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 33, 110, 25));

        buttonUpdateOrderDemo.setBackground(new java.awt.Color(51, 153, 255));
        buttonUpdateOrderDemo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonUpdateOrderDemo.setForeground(new java.awt.Color(255, 255, 255));
        buttonUpdateOrderDemo.setText("DEMO");
        buttonUpdateOrderDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateOrderDemoActionPerformed(evt);
            }
        });
        panelUpdateOrder.add(buttonUpdateOrderDemo, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 35, -1, 37));

        labelUpdateOrderOrderDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelUpdateOrderOrderDescription.setText("Menu Items List");
        panelUpdateOrder.add(labelUpdateOrderOrderDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 90, -1, -1));

        buttonUpdateOrderUpdateOrder.setBackground(new java.awt.Color(51, 153, 255));
        buttonUpdateOrderUpdateOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonUpdateOrderUpdateOrder.setForeground(new java.awt.Color(255, 255, 255));
        buttonUpdateOrderUpdateOrder.setText("Update Order");
        buttonUpdateOrderUpdateOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateOrderUpdateOrderActionPerformed(evt);
            }
        });
        panelUpdateOrder.add(buttonUpdateOrderUpdateOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 488, -1, 33));

        buttonUpdateOrderHome.setBackground(new java.awt.Color(51, 153, 255));
        buttonUpdateOrderHome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonUpdateOrderHome.setForeground(new java.awt.Color(255, 255, 255));
        buttonUpdateOrderHome.setText("Home");
        buttonUpdateOrderHome.setName(""); // NOI18N
        buttonUpdateOrderHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateOrderHomeActionPerformed(evt);
            }
        });
        panelUpdateOrder.add(buttonUpdateOrderHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 488, 95, 33));

        buttonUpdateOrderReset.setBackground(new java.awt.Color(51, 153, 255));
        buttonUpdateOrderReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonUpdateOrderReset.setForeground(new java.awt.Color(255, 255, 255));
        buttonUpdateOrderReset.setText("RESET");
        buttonUpdateOrderReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateOrderResetActionPerformed(evt);
            }
        });
        panelUpdateOrder.add(buttonUpdateOrderReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(613, 35, -1, 37));

        listAddOrderItems2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listAddOrderItems2MouseClicked(evt);
            }
        });
        scrollPaneAddOrderItems1.setViewportView(listAddOrderItems2);

        panelUpdateOrder.add(scrollPaneAddOrderItems1, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 123, -1, 347));

        tableUpdateOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {

            }
        ));
        scrollPaneUpdateOrder.setViewportView(tableUpdateOrder);

        panelUpdateOrder.add(scrollPaneUpdateOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 122, 388, 348));

        labelUpdateOrderOrderDescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelUpdateOrderOrderDescription1.setText("Order Description");
        panelUpdateOrder.add(labelUpdateOrderOrderDescription1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 89, -1, -1));

        updateAdd.setBackground(new java.awt.Color(255, 255, 255));
        updateAdd.setForeground(new java.awt.Color(255, 255, 255));
        updateAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        updateAdd.setEnabled(false);
        updateAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAddActionPerformed(evt);
            }
        });
        panelUpdateOrder.add(updateAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 206, 53, 40));

        updateRemove.setBackground(new java.awt.Color(255, 255, 255));
        updateRemove.setForeground(new java.awt.Color(255, 255, 255));
        updateRemove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        updateRemove.setEnabled(false);
        updateRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRemoveActionPerformed(evt);
            }
        });
        panelUpdateOrder.add(updateRemove, new org.netbeans.lib.awtextra.AbsoluteConstraints(436, 343, 53, 40));

        tabbedPaneRestaurantOrder.addTab("Update Order", panelUpdateOrder);

        panelRemoveOrder.setBackground(new java.awt.Color(255, 255, 255));
        panelRemoveOrder.setMaximumSize(new java.awt.Dimension(796, 549));
        panelRemoveOrder.setMinimumSize(new java.awt.Dimension(796, 549));
        panelRemoveOrder.setPreferredSize(new java.awt.Dimension(796, 549));

        buttonRemoveOrderRemoveOrder.setBackground(new java.awt.Color(51, 153, 255));
        buttonRemoveOrderRemoveOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonRemoveOrderRemoveOrder.setForeground(new java.awt.Color(255, 255, 255));
        buttonRemoveOrderRemoveOrder.setText("Remove Order");
        buttonRemoveOrderRemoveOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveOrderRemoveOrderActionPerformed(evt);
            }
        });

        buttonRemoveOrderHome.setBackground(new java.awt.Color(51, 153, 255));
        buttonRemoveOrderHome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonRemoveOrderHome.setForeground(new java.awt.Color(255, 255, 255));
        buttonRemoveOrderHome.setText("Home");
        buttonRemoveOrderHome.setName(""); // NOI18N
        buttonRemoveOrderHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveOrderHomeActionPerformed(evt);
            }
        });

        buttonRemoveOrderDemo.setBackground(new java.awt.Color(51, 153, 255));
        buttonRemoveOrderDemo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonRemoveOrderDemo.setForeground(new java.awt.Color(255, 255, 255));
        buttonRemoveOrderDemo.setText("DEMO");
        buttonRemoveOrderDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveOrderDemoActionPerformed(evt);
            }
        });

        textFieldRemoveOrderOrderID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldRemoveOrderOrderID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldRemoveOrderOrderID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldRemoveOrderOrderID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldRemoveOrderOrderIDActionPerformed(evt);
            }
        });
        textFieldRemoveOrderOrderID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldRemoveOrderOrderIDKeyReleased(evt);
            }
        });

        labelRemoveOrderOrderID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelRemoveOrderOrderID.setText("Order ID");

        buttonRemoveOrderReset.setBackground(new java.awt.Color(51, 153, 255));
        buttonRemoveOrderReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonRemoveOrderReset.setForeground(new java.awt.Color(255, 255, 255));
        buttonRemoveOrderReset.setText("RESET");
        buttonRemoveOrderReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveOrderResetActionPerformed(evt);
            }
        });

        buttonRemoveOrderSearch.setBackground(new java.awt.Color(51, 153, 255));
        buttonRemoveOrderSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonRemoveOrderSearch.setForeground(new java.awt.Color(255, 255, 255));
        buttonRemoveOrderSearch.setText("Search");
        buttonRemoveOrderSearch.setName(""); // NOI18N
        buttonRemoveOrderSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRemoveOrderSearchActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        tableRemoveOrderResultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableRemoveOrderResultsTable.setEnabled(false);
        tableRemoveOrderResultsTable.setGridColor(new java.awt.Color(51, 153, 255));
        jScrollPane1.setViewportView(tableRemoveOrderResultsTable);

        javax.swing.GroupLayout panelRemoveOrderLayout = new javax.swing.GroupLayout(panelRemoveOrder);
        panelRemoveOrder.setLayout(panelRemoveOrderLayout);
        panelRemoveOrderLayout.setHorizontalGroup(
            panelRemoveOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRemoveOrderLayout.createSequentialGroup()
                .addGroup(panelRemoveOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRemoveOrderLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(buttonRemoveOrderSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelRemoveOrderLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelRemoveOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelRemoveOrderLayout.createSequentialGroup()
                                .addComponent(labelRemoveOrderOrderID)
                                .addGap(47, 47, 47)
                                .addComponent(textFieldRemoveOrderOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(378, 378, 378)
                                .addComponent(buttonRemoveOrderReset)
                                .addGap(18, 18, 18)
                                .addComponent(buttonRemoveOrderDemo))
                            .addGroup(panelRemoveOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRemoveOrderLayout.createSequentialGroup()
                                    .addComponent(buttonRemoveOrderHome, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonRemoveOrderRemoveOrder))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(31, 31, 31))
        );
        panelRemoveOrderLayout.setVerticalGroup(
            panelRemoveOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRemoveOrderLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelRemoveOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRemoveOrderLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(labelRemoveOrderOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textFieldRemoveOrderOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRemoveOrderReset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRemoveOrderDemo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buttonRemoveOrderSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(panelRemoveOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRemoveOrderRemoveOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRemoveOrderHome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(210, 210, 210))
        );

        tabbedPaneRestaurantOrder.addTab("Remove Order", panelRemoveOrder);

        panelSearchOrder.setBackground(new java.awt.Color(255, 255, 255));
        panelSearchOrder.setMaximumSize(new java.awt.Dimension(796, 549));
        panelSearchOrder.setMinimumSize(new java.awt.Dimension(796, 549));

        labelSearchOrderOrderID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelSearchOrderOrderID.setText("Order ID");

        textFieldSerachOrderOrderID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldSerachOrderOrderID.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldSerachOrderOrderID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldSerachOrderOrderID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSerachOrderOrderIDActionPerformed(evt);
            }
        });
        textFieldSerachOrderOrderID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldSerachOrderOrderIDKeyReleased(evt);
            }
        });

        buttonSearchOrderDemo.setBackground(new java.awt.Color(51, 153, 255));
        buttonSearchOrderDemo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonSearchOrderDemo.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchOrderDemo.setText("DEMO");
        buttonSearchOrderDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchOrderDemoActionPerformed(evt);
            }
        });

        scrollPaneSearchOrderResultsTable.setBackground(new java.awt.Color(255, 255, 255));
        scrollPaneSearchOrderResultsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        tableSearchOrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableSearchOrderTable.setGridColor(new java.awt.Color(51, 153, 255));
        scrollPaneSearchOrderResultsTable.setViewportView(tableSearchOrderTable);

        buttonSearchOrderHome.setBackground(new java.awt.Color(51, 153, 255));
        buttonSearchOrderHome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonSearchOrderHome.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchOrderHome.setText("Home");
        buttonSearchOrderHome.setName(""); // NOI18N
        buttonSearchOrderHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchOrderHomeActionPerformed(evt);
            }
        });

        buttonSearchOrderPrint.setBackground(new java.awt.Color(51, 153, 255));
        buttonSearchOrderPrint.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonSearchOrderPrint.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchOrderPrint.setText("Print");
        buttonSearchOrderPrint.setName(""); // NOI18N
        buttonSearchOrderPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchOrderPrintActionPerformed(evt);
            }
        });

        buttonSearchOrderReset.setBackground(new java.awt.Color(51, 153, 255));
        buttonSearchOrderReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonSearchOrderReset.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchOrderReset.setText("RESET");
        buttonSearchOrderReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchOrderResetActionPerformed(evt);
            }
        });

        buttonSearchOrderSearch.setBackground(new java.awt.Color(51, 153, 255));
        buttonSearchOrderSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonSearchOrderSearch.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchOrderSearch.setText("Search");
        buttonSearchOrderSearch.setName(""); // NOI18N
        buttonSearchOrderSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchOrderSearchActionPerformed(evt);
            }
        });

        comboBoxUpdateOrderSelectMenu1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comboBoxUpdateOrderSelectMenu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select Menu--", "Menu R001 - 550000", "Menu R002 - 550001", "Menu R003 - 550002", "Menu R004 - 550003", " ", " " }));
        comboBoxUpdateOrderSelectMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxUpdateOrderSelectMenu1ActionPerformed(evt);
            }
        });

        labelUpdateOrderMenuID1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelUpdateOrderMenuID1.setText("Menu ID");
        labelUpdateOrderMenuID1.setName(""); // NOI18N

        labelSearchOrderOrderID1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelSearchOrderOrderID1.setText("Customer NIC");

        textFieldSerachOrderOrderID1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldSerachOrderOrderID1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldSerachOrderOrderID1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldSerachOrderOrderID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSerachOrderOrderID1ActionPerformed(evt);
            }
        });
        textFieldSerachOrderOrderID1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldSerachOrderOrderID1KeyReleased(evt);
            }
        });

        labelSearchOrderOrderID2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelSearchOrderOrderID2.setText("Order Date");

        labelSearchOrderOrderID3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelSearchOrderOrderID3.setText("Order Status");

        textFieldSerachOrderOrderID3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldSerachOrderOrderID3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldSerachOrderOrderID3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldSerachOrderOrderID3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSerachOrderOrderID3ActionPerformed(evt);
            }
        });
        textFieldSerachOrderOrderID3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldSerachOrderOrderID3KeyReleased(evt);
            }
        });

        orderDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderDateMouseClicked(evt);
            }
        });

        jLabel15.setText("1 - Available");

        jLabel16.setText("0 - Not Available");

        javax.swing.GroupLayout panelSearchOrderLayout = new javax.swing.GroupLayout(panelSearchOrder);
        panelSearchOrder.setLayout(panelSearchOrderLayout);
        panelSearchOrderLayout.setHorizontalGroup(
            panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchOrderLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchOrderLayout.createSequentialGroup()
                        .addComponent(buttonSearchOrderPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSearchOrderHome, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addComponent(scrollPaneSearchOrderResultsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 734, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(panelSearchOrderLayout.createSequentialGroup()
                .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchOrderLayout.createSequentialGroup()
                        .addGap(610, 610, 610)
                        .addComponent(buttonSearchOrderReset)
                        .addGap(21, 21, 21)
                        .addComponent(buttonSearchOrderDemo))
                    .addGroup(panelSearchOrderLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonSearchOrderSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelSearchOrderLayout.createSequentialGroup()
                                .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldSerachOrderOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelSearchOrderOrderID))
                                .addGap(30, 30, 30)
                                .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelUpdateOrderMenuID1)
                                    .addComponent(comboBoxUpdateOrderSelectMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(30, 30, 30)
                        .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSearchOrderOrderID1)
                            .addComponent(textFieldSerachOrderOrderID1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSearchOrderLayout.createSequentialGroup()
                                .addComponent(labelSearchOrderOrderID2)
                                .addGap(78, 78, 78)
                                .addComponent(labelSearchOrderOrderID3))
                            .addGroup(panelSearchOrderLayout.createSequentialGroup()
                                .addComponent(orderDate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldSerachOrderOrderID3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 31, Short.MAX_VALUE))
        );
        panelSearchOrderLayout.setVerticalGroup(
            panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchOrderLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSearchOrderReset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearchOrderDemo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSearchOrderOrderID1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelSearchOrderOrderID2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelUpdateOrderMenuID1)
                        .addComponent(labelSearchOrderOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelSearchOrderOrderID3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(orderDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textFieldSerachOrderOrderID)
                    .addComponent(textFieldSerachOrderOrderID1)
                    .addComponent(textFieldSerachOrderOrderID3)
                    .addComponent(comboBoxUpdateOrderSelectMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSearchOrderSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(45, 45, 45)
                .addComponent(scrollPaneSearchOrderResultsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addGroup(panelSearchOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSearchOrderHome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearchOrderPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );

        tabbedPaneRestaurantOrder.addTab("Search Order", panelSearchOrder);

        javax.swing.GroupLayout panelRestaurantOrderLayout = new javax.swing.GroupLayout(panelRestaurantOrder);
        panelRestaurantOrder.setLayout(panelRestaurantOrderLayout);
        panelRestaurantOrderLayout.setHorizontalGroup(
            panelRestaurantOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRestaurantContent, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(tabbedPaneRestaurantOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelRestaurantOrderLayout.setVerticalGroup(
            panelRestaurantOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRestaurantOrderLayout.createSequentialGroup()
                .addComponent(tabbedPaneRestaurantOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(panelRestaurantContent, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
        );

        panelCustomer.setBackground(new java.awt.Color(255, 255, 255));
        panelCustomer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        panelCustomer.setPreferredSize(new java.awt.Dimension(800, 570));
        panelCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelCustomerMouseEntered(evt);
            }
        });

        panelCustomerContent.setBackground(new java.awt.Color(255, 255, 255));
        panelCustomerContent.setPreferredSize(new java.awt.Dimension(800, 570));

        buttonCustomerContentAddCustomer.setBackground(new java.awt.Color(51, 153, 255));
        buttonCustomerContentAddCustomer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonCustomerContentAddCustomer.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustomerContentAddCustomer.setText("Add Customer");
        buttonCustomerContentAddCustomer.setEnabled(false);
        buttonCustomerContentAddCustomer.setName(""); // NOI18N
        buttonCustomerContentAddCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustomerContentAddCustomerActionPerformed(evt);
            }
        });

        buttonCustomerContentSearchCustomer.setBackground(new java.awt.Color(51, 153, 255));
        buttonCustomerContentSearchCustomer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonCustomerContentSearchCustomer.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustomerContentSearchCustomer.setText("Search Customer");
        buttonCustomerContentSearchCustomer.setName(""); // NOI18N
        buttonCustomerContentSearchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustomerContentSearchCustomerActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("CUSTOMER MAIN");

        buttonCustomerContentAddCustomer1.setBackground(new java.awt.Color(51, 153, 255));
        buttonCustomerContentAddCustomer1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonCustomerContentAddCustomer1.setForeground(new java.awt.Color(255, 255, 255));
        buttonCustomerContentAddCustomer1.setText("Update Customer");
        buttonCustomerContentAddCustomer1.setName(""); // NOI18N
        buttonCustomerContentAddCustomer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCustomerContentAddCustomer1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCustomerContentLayout = new javax.swing.GroupLayout(panelCustomerContent);
        panelCustomerContent.setLayout(panelCustomerContentLayout);
        panelCustomerContentLayout.setHorizontalGroup(
            panelCustomerContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerContentLayout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addGroup(panelCustomerContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCustomerContentLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(panelCustomerContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonCustomerContentAddCustomer1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelCustomerContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(buttonCustomerContentSearchCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonCustomerContentAddCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(270, Short.MAX_VALUE))
        );
        panelCustomerContentLayout.setVerticalGroup(
            panelCustomerContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCustomerContentLayout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(81, 81, 81)
                .addComponent(buttonCustomerContentAddCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(buttonCustomerContentSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(buttonCustomerContentAddCustomer1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
        );

        tabbedPaneCustomer.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustomer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tabbedPaneCustomer.setPreferredSize(new java.awt.Dimension(798, 580));

        panelAddCustomer.setBackground(new java.awt.Color(255, 255, 255));

        labelAddCustomerName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAddCustomerName.setText("NIC");

        textFieldAddCustomerName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldAddCustomerName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldAddCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldAddCustomerNameActionPerformed(evt);
            }
        });
        textFieldAddCustomerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldAddCustomerNameKeyPressed(evt);
            }
        });

        buttonAddCustomerDemo.setBackground(new java.awt.Color(51, 153, 255));
        buttonAddCustomerDemo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonAddCustomerDemo.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddCustomerDemo.setText("DEMO");
        buttonAddCustomerDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddCustomerDemoActionPerformed(evt);
            }
        });

        buttonAddCustomerReset.setBackground(new java.awt.Color(51, 153, 255));
        buttonAddCustomerReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonAddCustomerReset.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddCustomerReset.setText("RESET");
        buttonAddCustomerReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddCustomerResetActionPerformed(evt);
            }
        });

        labelAddCustomerTelephone.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAddCustomerTelephone.setText("Telephone");

        labelAddCustomerEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAddCustomerEmail.setText("Email");

        textFieldAddCustomerEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldAddCustomerEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldAddCustomerEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldAddCustomerEmailActionPerformed(evt);
            }
        });

        countryCode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        countryCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        countryCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        countryCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                countryCodeActionPerformed(evt);
            }
        });
        countryCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                countryCodeKeyReleased(evt);
            }
        });

        buttonAddCustomerNext.setBackground(new java.awt.Color(51, 153, 255));
        buttonAddCustomerNext.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonAddCustomerNext.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddCustomerNext.setText("Next");
        buttonAddCustomerNext.setName(""); // NOI18N
        buttonAddCustomerNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddCustomerNextActionPerformed(evt);
            }
        });

        buttonAddCustomerHome.setBackground(new java.awt.Color(51, 153, 255));
        buttonAddCustomerHome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonAddCustomerHome.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddCustomerHome.setText("Home");
        buttonAddCustomerHome.setName(""); // NOI18N
        buttonAddCustomerHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddCustomerHomeActionPerformed(evt);
            }
        });

        buttonAddCustomerBack.setBackground(new java.awt.Color(51, 153, 255));
        buttonAddCustomerBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonAddCustomerBack.setForeground(new java.awt.Color(255, 255, 255));
        buttonAddCustomerBack.setText("Back");
        buttonAddCustomerBack.setName(""); // NOI18N
        buttonAddCustomerBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddCustomerBackActionPerformed(evt);
            }
        });

        labelAddCustomerName1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelAddCustomerName1.setText("Name");

        textFieldAddCustomerNIC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldAddCustomerNIC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldAddCustomerNIC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldAddCustomerNICActionPerformed(evt);
            }
        });
        textFieldAddCustomerNIC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldAddCustomerNICKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldAddCustomerNICKeyReleased(evt);
            }
        });

        textFieldAddCustomerTelephone2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldAddCustomerTelephone2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldAddCustomerTelephone2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldAddCustomerTelephone2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldAddCustomerTelephone2ActionPerformed(evt);
            }
        });
        textFieldAddCustomerTelephone2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldAddCustomerTelephone2KeyReleased(evt);
            }
        });

        textFieldAddCustomerTelephone1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldAddCustomerTelephone1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldAddCustomerTelephone1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldAddCustomerTelephone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldAddCustomerTelephone1ActionPerformed(evt);
            }
        });
        textFieldAddCustomerTelephone1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldAddCustomerTelephone1KeyReleased(evt);
            }
        });

        textFieldAddCustomerTelephone3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldAddCustomerTelephone3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textFieldAddCustomerTelephone3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldAddCustomerTelephone3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldAddCustomerTelephone3ActionPerformed(evt);
            }
        });
        textFieldAddCustomerTelephone3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldAddCustomerTelephone3KeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("-");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("-");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("-");

        customerStatus.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        customerStatus.setForeground(new java.awt.Color(0, 153, 0));
        customerStatus.setText("DB availability");

        javax.swing.GroupLayout panelAddCustomerLayout = new javax.swing.GroupLayout(panelAddCustomer);
        panelAddCustomer.setLayout(panelAddCustomerLayout);
        panelAddCustomerLayout.setHorizontalGroup(
            panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddCustomerLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAddCustomerTelephone)
                    .addComponent(labelAddCustomerEmail)
                    .addComponent(labelAddCustomerName)
                    .addComponent(buttonAddCustomerBack, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAddCustomerName1))
                .addGroup(panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddCustomerLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAddCustomerLayout.createSequentialGroup()
                                .addGroup(panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldAddCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldAddCustomerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelAddCustomerLayout.createSequentialGroup()
                                        .addComponent(countryCode, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textFieldAddCustomerTelephone1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textFieldAddCustomerTelephone2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textFieldAddCustomerTelephone3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textFieldAddCustomerNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(240, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAddCustomerLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelAddCustomerLayout.createSequentialGroup()
                                        .addComponent(buttonAddCustomerReset)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buttonAddCustomerDemo))
                                    .addGroup(panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(customerStatus)
                                        .addComponent(buttonAddCustomerNext, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(31, 31, 31))))
                    .addGroup(panelAddCustomerLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(buttonAddCustomerHome, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelAddCustomerLayout.setVerticalGroup(
            panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddCustomerLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddCustomerReset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAddCustomerDemo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAddCustomerName)
                    .addComponent(textFieldAddCustomerNIC, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelAddCustomerName1)
                    .addComponent(textFieldAddCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAddCustomerTelephone)
                    .addComponent(countryCode, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldAddCustomerTelephone2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldAddCustomerTelephone1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldAddCustomerTelephone3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(34, 34, 34)
                .addGroup(panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldAddCustomerEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAddCustomerEmail))
                .addGap(48, 48, 48)
                .addComponent(customerStatus)
                .addGap(18, 18, 18)
                .addGroup(panelAddCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddCustomerHome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAddCustomerBack, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAddCustomerNext, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        tabbedPaneCustomer.addTab("Add Customer", panelAddCustomer);

        panelSearchCustomer.setBackground(new java.awt.Color(255, 255, 255));

        labelSearchCustomerCustomerID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelSearchCustomerCustomerID.setText("Customer NIC");

        textFieldSearchCustomerCustomerID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldSearchCustomerCustomerID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldSearchCustomerCustomerID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchCustomerCustomerIDActionPerformed(evt);
            }
        });
        textFieldSearchCustomerCustomerID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldSearchCustomerCustomerIDKeyReleased(evt);
            }
        });

        buttonSearchCustomerReset.setBackground(new java.awt.Color(51, 153, 255));
        buttonSearchCustomerReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonSearchCustomerReset.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchCustomerReset.setText("RESET");
        buttonSearchCustomerReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomerResetActionPerformed(evt);
            }
        });

        buttonSearchCustomerDemo.setBackground(new java.awt.Color(51, 153, 255));
        buttonSearchCustomerDemo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonSearchCustomerDemo.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchCustomerDemo.setText("DEMO");
        buttonSearchCustomerDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomerDemoActionPerformed(evt);
            }
        });

        scrollPaneSearchCustomerResultsTable.setBackground(new java.awt.Color(255, 255, 255));
        scrollPaneSearchCustomerResultsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        tableSearchCustomerResultsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableSearchCustomerResultsTable.setEnabled(false);
        tableSearchCustomerResultsTable.setGridColor(new java.awt.Color(51, 153, 255));
        scrollPaneSearchCustomerResultsTable.setViewportView(tableSearchCustomerResultsTable);

        buttonSearchCustomerSearch.setBackground(new java.awt.Color(51, 153, 255));
        buttonSearchCustomerSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonSearchCustomerSearch.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchCustomerSearch.setText("Search");
        buttonSearchCustomerSearch.setName(""); // NOI18N
        buttonSearchCustomerSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomerSearchActionPerformed(evt);
            }
        });

        buttonSearchCustomerHome.setBackground(new java.awt.Color(51, 153, 255));
        buttonSearchCustomerHome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonSearchCustomerHome.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchCustomerHome.setText("Home");
        buttonSearchCustomerHome.setName(""); // NOI18N
        buttonSearchCustomerHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomerHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSearchCustomerLayout = new javax.swing.GroupLayout(panelSearchCustomer);
        panelSearchCustomer.setLayout(panelSearchCustomerLayout);
        panelSearchCustomerLayout.setHorizontalGroup(
            panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                        .addComponent(buttonSearchCustomerSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchCustomerLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonSearchCustomerHome, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollPaneSearchCustomerResultsTable, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSearchCustomerLayout.createSequentialGroup()
                        .addComponent(labelSearchCustomerCustomerID)
                        .addGap(111, 111, 111)
                        .addComponent(textFieldSearchCustomerCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonSearchCustomerReset)
                        .addGap(18, 18, 18)
                        .addComponent(buttonSearchCustomerDemo)))
                .addGap(31, 31, 31))
        );
        panelSearchCustomerLayout.setVerticalGroup(
            panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelSearchCustomerCustomerID)
                            .addComponent(textFieldSearchCustomerCustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelSearchCustomerLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panelSearchCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonSearchCustomerReset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonSearchCustomerDemo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42)
                .addComponent(buttonSearchCustomerSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(scrollPaneSearchCustomerResultsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(buttonSearchCustomerHome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        tabbedPaneCustomer.addTab("Search Customer", panelSearchCustomer);

        panelUpdateCustomer.setBackground(new java.awt.Color(255, 255, 255));

        labelSearchCustomerCustomerID1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        labelSearchCustomerCustomerID1.setText("Customer NIC");

        textFieldSearchCustomerCustomerID1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        textFieldSearchCustomerCustomerID1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        textFieldSearchCustomerCustomerID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchCustomerCustomerID1ActionPerformed(evt);
            }
        });
        textFieldSearchCustomerCustomerID1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textFieldSearchCustomerCustomerID1KeyReleased(evt);
            }
        });

        buttonSearchCustomerSearch1.setBackground(new java.awt.Color(51, 153, 255));
        buttonSearchCustomerSearch1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        buttonSearchCustomerSearch1.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchCustomerSearch1.setText("Search");
        buttonSearchCustomerSearch1.setName(""); // NOI18N
        buttonSearchCustomerSearch1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomerSearch1ActionPerformed(evt);
            }
        });

        buttonSearchCustomerReset1.setBackground(new java.awt.Color(51, 153, 255));
        buttonSearchCustomerReset1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonSearchCustomerReset1.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchCustomerReset1.setText("RESET");
        buttonSearchCustomerReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomerReset1ActionPerformed(evt);
            }
        });

        buttonSearchCustomerDemo1.setBackground(new java.awt.Color(51, 153, 255));
        buttonSearchCustomerDemo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonSearchCustomerDemo1.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchCustomerDemo1.setText("DEMO");
        buttonSearchCustomerDemo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchCustomerDemo1ActionPerformed(evt);
            }
        });

        scrollPaneSearchCustomerResultsTable1.setBackground(new java.awt.Color(255, 255, 255));
        scrollPaneSearchCustomerResultsTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        tableSearchCustomerResultsTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }

        )

    );
    tableSearchCustomerResultsTable1.setGridColor(new java.awt.Color(51, 153, 255));
    scrollPaneSearchCustomerResultsTable1.setViewportView(tableSearchCustomerResultsTable1);

    buttonSearchCustomerHome1.setBackground(new java.awt.Color(51, 153, 255));
    buttonSearchCustomerHome1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonSearchCustomerHome1.setForeground(new java.awt.Color(255, 255, 255));
    buttonSearchCustomerHome1.setText("Home");
    buttonSearchCustomerHome1.setName(""); // NOI18N
    buttonSearchCustomerHome1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonSearchCustomerHome1ActionPerformed(evt);
        }
    });

    buttonUpdateCustomer.setBackground(new java.awt.Color(51, 153, 255));
    buttonUpdateCustomer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonUpdateCustomer.setForeground(new java.awt.Color(255, 255, 255));
    buttonUpdateCustomer.setText("Update");
    buttonUpdateCustomer.setName(""); // NOI18N
    buttonUpdateCustomer.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonUpdateCustomerActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panelUpdateCustomerLayout = new javax.swing.GroupLayout(panelUpdateCustomer);
    panelUpdateCustomer.setLayout(panelUpdateCustomerLayout);
    panelUpdateCustomerLayout.setHorizontalGroup(
        panelUpdateCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelUpdateCustomerLayout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addGroup(panelUpdateCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelUpdateCustomerLayout.createSequentialGroup()
                    .addComponent(buttonSearchCustomerSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUpdateCustomerLayout.createSequentialGroup()
                    .addGroup(panelUpdateCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panelUpdateCustomerLayout.createSequentialGroup()
                            .addComponent(buttonSearchCustomerHome1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonUpdateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPaneSearchCustomerResultsTable1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                        .addGroup(panelUpdateCustomerLayout.createSequentialGroup()
                            .addComponent(labelSearchCustomerCustomerID1)
                            .addGap(111, 111, 111)
                            .addComponent(textFieldSearchCustomerCustomerID1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonSearchCustomerReset1)
                            .addGap(18, 18, 18)
                            .addComponent(buttonSearchCustomerDemo1)))
                    .addGap(31, 31, 31))))
    );
    panelUpdateCustomerLayout.setVerticalGroup(
        panelUpdateCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelUpdateCustomerLayout.createSequentialGroup()
            .addGroup(panelUpdateCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelUpdateCustomerLayout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addGroup(panelUpdateCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelSearchCustomerCustomerID1)
                        .addComponent(textFieldSearchCustomerCustomerID1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelUpdateCustomerLayout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addGroup(panelUpdateCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonSearchCustomerReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonSearchCustomerDemo1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGap(42, 42, 42)
            .addComponent(buttonSearchCustomerSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(41, 41, 41)
            .addComponent(scrollPaneSearchCustomerResultsTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(35, 35, 35)
            .addGroup(panelUpdateCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonUpdateCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonSearchCustomerHome1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(173, Short.MAX_VALUE))
    );

    tabbedPaneCustomer.addTab("Update Customer", panelUpdateCustomer);

    panelPayment.setBackground(new java.awt.Color(255, 255, 255));
    panelPayment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    panelPayment.setName(""); // NOI18N
    panelPayment.setPreferredSize(new java.awt.Dimension(800, 570));

    buttonPaymentHome.setBackground(new java.awt.Color(51, 153, 255));
    buttonPaymentHome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonPaymentHome.setForeground(new java.awt.Color(255, 255, 255));
    buttonPaymentHome.setText("Home");
    buttonPaymentHome.setName(""); // NOI18N
    buttonPaymentHome.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonPaymentHomeActionPerformed(evt);
        }
    });

    buttonPaymentDone.setBackground(new java.awt.Color(51, 153, 255));
    buttonPaymentDone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonPaymentDone.setForeground(new java.awt.Color(255, 255, 255));
    buttonPaymentDone.setText("Done");
    buttonPaymentDone.setName(""); // NOI18N
    buttonPaymentDone.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            buttonPaymentDoneMouseEntered(evt);
        }
    });
    buttonPaymentDone.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonPaymentDoneActionPerformed(evt);
        }
    });

    labelPaymentPaymentMethod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelPaymentPaymentMethod.setText("Payment method");

    comboBoxPaymentPaymentType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    comboBoxPaymentPaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Credit Card" }));
    comboBoxPaymentPaymentType.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            comboBoxPaymentPaymentTypeActionPerformed(evt);
        }
    });

    labelPaymentAmount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelPaymentAmount.setText("Amount");

    textFieldPaymentAmount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldPaymentAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    textFieldPaymentAmount.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseExited(java.awt.event.MouseEvent evt) {
            textFieldPaymentAmountMouseExited(evt);
        }
    });
    textFieldPaymentAmount.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            textFieldPaymentAmountActionPerformed(evt);
        }
    });

    textAreaPaymentOrderDescription.setColumns(20);
    textAreaPaymentOrderDescription.setRows(5);
    textAreaPaymentOrderDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    textAreaPaymentOrderDescription.setEnabled(false);
    scrollPanePaymentOrderDescription.setViewportView(textAreaPaymentOrderDescription);

    labelPaymentPaymentDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelPaymentPaymentDescription.setText("Order Description");

    buttonPaymentPrintBill.setBackground(new java.awt.Color(51, 153, 255));
    buttonPaymentPrintBill.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonPaymentPrintBill.setForeground(new java.awt.Color(255, 255, 255));
    buttonPaymentPrintBill.setText("Print bill");
    buttonPaymentPrintBill.setEnabled(false);
    buttonPaymentPrintBill.setName(""); // NOI18N
    buttonPaymentPrintBill.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonPaymentPrintBillActionPerformed(evt);
        }
    });

    buttonPaymentBack.setBackground(new java.awt.Color(51, 153, 255));
    buttonPaymentBack.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonPaymentBack.setForeground(new java.awt.Color(255, 255, 255));
    buttonPaymentBack.setText("Back");
    buttonPaymentBack.setName(""); // NOI18N
    buttonPaymentBack.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonPaymentBackActionPerformed(evt);
        }
    });

    labelPaymentAmount1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelPaymentAmount1.setText("Paid Amount");

    labelPaymentAmount2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelPaymentAmount2.setText("Balance");

    textFieldPaymentPaidAmount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldPaymentPaidAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    textFieldPaymentPaidAmount.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            textFieldPaymentPaidAmountMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            textFieldPaymentPaidAmountMouseExited(evt);
        }
    });
    textFieldPaymentPaidAmount.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            textFieldPaymentPaidAmountActionPerformed(evt);
        }
    });
    textFieldPaymentPaidAmount.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            textFieldPaymentPaidAmountKeyPressed(evt);
        }
    });

    textFieldPaymentBalance.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldPaymentBalance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    textFieldPaymentBalance.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            textFieldPaymentBalanceMouseEntered(evt);
        }
    });

    labelMenuID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelMenuID.setText("menuID");

    labelPaymentPaymentDescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelPaymentPaymentDescription1.setText("Menu ID");

    javax.swing.GroupLayout panelPaymentLayout = new javax.swing.GroupLayout(panelPayment);
    panelPayment.setLayout(panelPaymentLayout);
    panelPaymentLayout.setHorizontalGroup(
        panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelPaymentLayout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(panelPaymentLayout.createSequentialGroup()
                    .addComponent(labelPaymentPaymentMethod)
                    .addGap(113, 113, 113)
                    .addComponent(comboBoxPaymentPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelPaymentLayout.createSequentialGroup()
                    .addComponent(labelPaymentAmount)
                    .addGap(164, 164, 164)
                    .addComponent(textFieldPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelPaymentLayout.createSequentialGroup()
                    .addComponent(labelPaymentAmount1)
                    .addGap(138, 138, 138)
                    .addComponent(textFieldPaymentPaidAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelPaymentLayout.createSequentialGroup()
                    .addComponent(labelPaymentAmount2)
                    .addGap(167, 167, 167)
                    .addComponent(textFieldPaymentBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelPaymentLayout.createSequentialGroup()
                    .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelPaymentPaymentDescription)
                        .addComponent(labelPaymentPaymentDescription1))
                    .addGap(113, 113, 113)
                    .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelMenuID)
                        .addComponent(scrollPanePaymentOrderDescription)))
                .addGroup(panelPaymentLayout.createSequentialGroup()
                    .addComponent(buttonPaymentPrintBill)
                    .addGap(231, 231, 231)
                    .addComponent(buttonPaymentBack, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(buttonPaymentHome, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(buttonPaymentDone, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(128, Short.MAX_VALUE))
    );
    panelPaymentLayout.setVerticalGroup(
        panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelPaymentLayout.createSequentialGroup()
            .addGap(26, 26, 26)
            .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelMenuID)
                .addComponent(labelPaymentPaymentDescription1))
            .addGap(16, 16, 16)
            .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPanePaymentOrderDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelPaymentPaymentDescription))
            .addGap(33, 33, 33)
            .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPaymentLayout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(labelPaymentPaymentMethod))
                .addComponent(comboBoxPaymentPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(34, 34, 34)
            .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPaymentLayout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(labelPaymentAmount))
                .addComponent(textFieldPaymentAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(29, 29, 29)
            .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPaymentLayout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(labelPaymentAmount1))
                .addComponent(textFieldPaymentPaidAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(30, 30, 30)
            .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelPaymentLayout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(labelPaymentAmount2))
                .addComponent(textFieldPaymentBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(35, 35, 35)
            .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(buttonPaymentPrintBill, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelPaymentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonPaymentHome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonPaymentBack, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonPaymentDone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
    );

    javax.swing.GroupLayout panelCustomerLayout = new javax.swing.GroupLayout(panelCustomer);
    panelCustomer.setLayout(panelCustomerLayout);
    panelCustomerLayout.setHorizontalGroup(
        panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
        .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addComponent(tabbedPaneCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE)))
        .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(panelCustomerContent, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(panelPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE)))
    );
    panelCustomerLayout.setVerticalGroup(
        panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
        .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addComponent(tabbedPaneCustomer, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                .addContainerGap()))
        .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelCustomerContent, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(panelPayment, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addContainerGap()))
    );

    panelFoodMenu.setBackground(new java.awt.Color(255, 255, 255));
    panelFoodMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    panelFoodMenu.setName(""); // NOI18N
    panelFoodMenu.setPreferredSize(new java.awt.Dimension(800, 570));

    panelFoodMenuContent.setBackground(new java.awt.Color(255, 255, 255));
    panelFoodMenuContent.setPreferredSize(new java.awt.Dimension(800, 570));

    jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel17.setForeground(new java.awt.Color(0, 0, 51));
    jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel17.setText("FOOD MENU MAIN");

    buttonRestaurantContentAddOrder1.setBackground(new java.awt.Color(51, 153, 255));
    buttonRestaurantContentAddOrder1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonRestaurantContentAddOrder1.setForeground(new java.awt.Color(255, 255, 255));
    buttonRestaurantContentAddOrder1.setText("Add Menu");
    buttonRestaurantContentAddOrder1.setName(""); // NOI18N
    buttonRestaurantContentAddOrder1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonRestaurantContentAddOrder1ActionPerformed(evt);
        }
    });

    buttonRestaurantContentUpdateOrder1.setBackground(new java.awt.Color(51, 153, 255));
    buttonRestaurantContentUpdateOrder1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonRestaurantContentUpdateOrder1.setForeground(new java.awt.Color(255, 255, 255));
    buttonRestaurantContentUpdateOrder1.setText("Update Menu");
    buttonRestaurantContentUpdateOrder1.setName(""); // NOI18N
    buttonRestaurantContentUpdateOrder1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonRestaurantContentUpdateOrder1ActionPerformed(evt);
        }
    });

    buttonRestaurantContentRemoveOrder1.setBackground(new java.awt.Color(51, 153, 255));
    buttonRestaurantContentRemoveOrder1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonRestaurantContentRemoveOrder1.setForeground(new java.awt.Color(255, 255, 255));
    buttonRestaurantContentRemoveOrder1.setText("Remove Menu");
    buttonRestaurantContentRemoveOrder1.setName(""); // NOI18N
    buttonRestaurantContentRemoveOrder1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonRestaurantContentRemoveOrder1ActionPerformed(evt);
        }
    });

    buttonRestaurantContentSearchOrder1.setBackground(new java.awt.Color(51, 153, 255));
    buttonRestaurantContentSearchOrder1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonRestaurantContentSearchOrder1.setForeground(new java.awt.Color(255, 255, 255));
    buttonRestaurantContentSearchOrder1.setText("View Menu");
    buttonRestaurantContentSearchOrder1.setName(""); // NOI18N
    buttonRestaurantContentSearchOrder1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonRestaurantContentSearchOrder1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panelFoodMenuContentLayout = new javax.swing.GroupLayout(panelFoodMenuContent);
    panelFoodMenuContent.setLayout(panelFoodMenuContentLayout);
    panelFoodMenuContentLayout.setHorizontalGroup(
        panelFoodMenuContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelFoodMenuContentLayout.createSequentialGroup()
            .addGroup(panelFoodMenuContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelFoodMenuContentLayout.createSequentialGroup()
                    .addGap(326, 326, 326)
                    .addGroup(panelFoodMenuContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buttonRestaurantContentRemoveOrder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRestaurantContentSearchOrder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRestaurantContentUpdateOrder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRestaurantContentAddOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelFoodMenuContentLayout.createSequentialGroup()
                    .addGap(261, 261, 261)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(289, Short.MAX_VALUE))
    );
    panelFoodMenuContentLayout.setVerticalGroup(
        panelFoodMenuContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelFoodMenuContentLayout.createSequentialGroup()
            .addGap(51, 51, 51)
            .addComponent(jLabel17)
            .addGap(56, 56, 56)
            .addComponent(buttonRestaurantContentAddOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(70, 70, 70)
            .addComponent(buttonRestaurantContentUpdateOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(72, 72, 72)
            .addComponent(buttonRestaurantContentRemoveOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
            .addComponent(buttonRestaurantContentSearchOrder1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(88, 88, 88))
    );

    tabbedPaneFoodMenu.setBackground(new java.awt.Color(255, 255, 255));
    tabbedPaneFoodMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    tabbedPaneFoodMenu.setPreferredSize(new java.awt.Dimension(798, 580));

    panelAddMenu.setBackground(new java.awt.Color(255, 255, 255));
    panelAddMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    buttonFoodMenuReset.setBackground(new java.awt.Color(51, 153, 255));
    buttonFoodMenuReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonFoodMenuReset.setForeground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuReset.setText("RESET");
    buttonFoodMenuReset.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonFoodMenuResetActionPerformed(evt);
        }
    });
    panelAddMenu.add(buttonFoodMenuReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, -1, 37));

    labelFoodMenuMenuPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelFoodMenuMenuPrice.setText("Menu price");
    panelAddMenu.add(labelFoodMenuMenuPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, -1, -1));

    textFieldFoodMenuMenuPrice.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldFoodMenuMenuPrice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    textFieldFoodMenuMenuPrice.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            textFieldFoodMenuMenuPriceActionPerformed(evt);
        }
    });
    panelAddMenu.add(textFieldFoodMenuMenuPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 88, 25));

    lablFoodMenuMenuDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    lablFoodMenuMenuDescription.setText("Menu description");
    panelAddMenu.add(lablFoodMenuMenuDescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

    buttonFoodMenuHome.setBackground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuHome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonFoodMenuHome.setForeground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuHome.setName(""); // NOI18N
    buttonFoodMenuHome.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonFoodMenuHomeActionPerformed(evt);
        }
    });
    panelAddMenu.add(buttonFoodMenuHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 60, 40));

    buttonFoodMenuAddMenu.setBackground(new java.awt.Color(51, 153, 255));
    buttonFoodMenuAddMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonFoodMenuAddMenu.setForeground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuAddMenu.setText("Add Menu");
    buttonFoodMenuAddMenu.setName(""); // NOI18N
    buttonFoodMenuAddMenu.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonFoodMenuAddMenuActionPerformed(evt);
        }
    });
    panelAddMenu.add(buttonFoodMenuAddMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 450, 95, 33));

    jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

    foodItems.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }
    ));
    foodItems.setFocusable(false);
    foodItems.setGridColor(new java.awt.Color(51, 153, 255));
    jScrollPane5.setViewportView(foodItems);

    panelAddMenu.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 310, 340));

    buttonFoodMenuHome1.setBackground(new java.awt.Color(51, 153, 255));
    buttonFoodMenuHome1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonFoodMenuHome1.setForeground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuHome1.setText("Home");
    buttonFoodMenuHome1.setName(""); // NOI18N
    buttonFoodMenuHome1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonFoodMenuHome1ActionPerformed(evt);
        }
    });
    panelAddMenu.add(buttonFoodMenuHome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 450, 95, 33));

    buttonFoodMenuHome2.setBackground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuHome2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonFoodMenuHome2.setForeground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuHome2.setName(""); // NOI18N
    buttonFoodMenuHome2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonFoodMenuHome2ActionPerformed(evt);
        }
    });
    panelAddMenu.add(buttonFoodMenuHome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 60, 40));

    jScrollPane4.setViewportView(jList2);

    panelAddMenu.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 310, 340));

    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane6.setViewportView(jTextArea1);

    panelAddMenu.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, -1, 30));

    tabbedPaneFoodMenu.addTab("Add Menu", panelAddMenu);

    panelUpdateMenu.setBackground(new java.awt.Color(255, 255, 255));
    panelUpdateMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    lablFoodMenuMenuDescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    lablFoodMenuMenuDescription1.setText("Menu description");
    panelUpdateMenu.add(lablFoodMenuMenuDescription1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, -1, -1));

    jScrollPane7.setViewportView(jList3);

    panelUpdateMenu.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 310, 340));

    buttonFoodMenuHome3.setBackground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuHome3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonFoodMenuHome3.setForeground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuHome3.setName(""); // NOI18N
    buttonFoodMenuHome3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonFoodMenuHome3ActionPerformed(evt);
        }
    });
    panelUpdateMenu.add(buttonFoodMenuHome3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 60, 40));

    buttonFoodMenuHome4.setBackground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuHome4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonFoodMenuHome4.setForeground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuHome4.setName(""); // NOI18N
    buttonFoodMenuHome4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonFoodMenuHome4ActionPerformed(evt);
        }
    });
    panelUpdateMenu.add(buttonFoodMenuHome4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 60, 40));

    jScrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

    foodItems1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }
    ));
    foodItems1.setFocusable(false);
    foodItems1.setGridColor(new java.awt.Color(51, 153, 255));
    jScrollPane8.setViewportView(foodItems1);

    panelUpdateMenu.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 310, 340));

    buttonFoodMenuReset1.setBackground(new java.awt.Color(51, 153, 255));
    buttonFoodMenuReset1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonFoodMenuReset1.setForeground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuReset1.setText("RESET");
    buttonFoodMenuReset1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonFoodMenuReset1ActionPerformed(evt);
        }
    });
    panelUpdateMenu.add(buttonFoodMenuReset1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, -1, 37));

    buttonFoodMenuHome5.setBackground(new java.awt.Color(51, 153, 255));
    buttonFoodMenuHome5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonFoodMenuHome5.setForeground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuHome5.setText("Home");
    buttonFoodMenuHome5.setName(""); // NOI18N
    buttonFoodMenuHome5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonFoodMenuHome5ActionPerformed(evt);
        }
    });
    panelUpdateMenu.add(buttonFoodMenuHome5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 450, 95, 33));

    buttonFoodMenuAddMenu1.setBackground(new java.awt.Color(51, 153, 255));
    buttonFoodMenuAddMenu1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonFoodMenuAddMenu1.setForeground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuAddMenu1.setText("Update Menu");
    buttonFoodMenuAddMenu1.setName(""); // NOI18N
    buttonFoodMenuAddMenu1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonFoodMenuAddMenu1ActionPerformed(evt);
        }
    });
    panelUpdateMenu.add(buttonFoodMenuAddMenu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(645, 450, 120, 33));

    jTextArea2.setColumns(20);
    jTextArea2.setRows(5);
    jScrollPane9.setViewportView(jTextArea2);

    panelUpdateMenu.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 450, -1, 30));

    textFieldFoodMenuMenuPrice1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldFoodMenuMenuPrice1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    textFieldFoodMenuMenuPrice1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            textFieldFoodMenuMenuPrice1ActionPerformed(evt);
        }
    });
    panelUpdateMenu.add(textFieldFoodMenuMenuPrice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 88, 25));

    labelFoodMenuMenuPrice1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelFoodMenuMenuPrice1.setText("Menu price");
    panelUpdateMenu.add(labelFoodMenuMenuPrice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, -1, -1));

    labelFoodMenuMenuPrice2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelFoodMenuMenuPrice2.setText("Menu ID");
    panelUpdateMenu.add(labelFoodMenuMenuPrice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

    textFieldFoodMenuID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldFoodMenuID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    textFieldFoodMenuID.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            textFieldFoodMenuIDActionPerformed(evt);
        }
    });
    textFieldFoodMenuID.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            textFieldFoodMenuIDKeyReleased(evt);
        }
    });
    panelUpdateMenu.add(textFieldFoodMenuID, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 88, 25));

    buttonFoodMenuReset2.setBackground(new java.awt.Color(51, 153, 255));
    buttonFoodMenuReset2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonFoodMenuReset2.setForeground(new java.awt.Color(255, 255, 255));
    buttonFoodMenuReset2.setText("DEMO");
    buttonFoodMenuReset2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonFoodMenuReset2ActionPerformed(evt);
        }
    });
    panelUpdateMenu.add(buttonFoodMenuReset2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, -1, 37));

    tabbedPaneFoodMenu.addTab("Update Menu", panelUpdateMenu);

    panelRemoveMenu.setBackground(new java.awt.Color(255, 255, 255));

    buttonRemoveMenuReset.setBackground(new java.awt.Color(51, 153, 255));
    buttonRemoveMenuReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonRemoveMenuReset.setForeground(new java.awt.Color(255, 255, 255));
    buttonRemoveMenuReset.setText("RESET");
    buttonRemoveMenuReset.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonRemoveMenuResetActionPerformed(evt);
        }
    });

    buttonRemoveMenuDemo.setBackground(new java.awt.Color(51, 153, 255));
    buttonRemoveMenuDemo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonRemoveMenuDemo.setForeground(new java.awt.Color(255, 255, 255));
    buttonRemoveMenuDemo.setText("DEMO");
    buttonRemoveMenuDemo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonRemoveMenuDemoActionPerformed(evt);
        }
    });

    buttonRemoveMenuHome.setBackground(new java.awt.Color(51, 153, 255));
    buttonRemoveMenuHome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonRemoveMenuHome.setForeground(new java.awt.Color(255, 255, 255));
    buttonRemoveMenuHome.setText("Home");
    buttonRemoveMenuHome.setName(""); // NOI18N
    buttonRemoveMenuHome.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonRemoveMenuHomeActionPerformed(evt);
        }
    });

    labelRemoveMenuMenuID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelRemoveMenuMenuID.setText("Menu ID");

    textFieldRemoveMenuMenuID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldRemoveMenuMenuID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    textFieldRemoveMenuMenuID.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            textFieldRemoveMenuMenuIDMouseClicked(evt);
        }
    });
    textFieldRemoveMenuMenuID.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            textFieldRemoveMenuMenuIDActionPerformed(evt);
        }
    });

    scrollPaneRemoveMenuResultsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

    tableRemoveMenuResultsTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ));
    tableRemoveMenuResultsTable.setGridColor(new java.awt.Color(51, 153, 255));
    scrollPaneRemoveMenuResultsTable.setViewportView(tableRemoveMenuResultsTable);

    buttonRemoveMenuSearch.setBackground(new java.awt.Color(51, 153, 255));
    buttonRemoveMenuSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonRemoveMenuSearch.setForeground(new java.awt.Color(255, 255, 255));
    buttonRemoveMenuSearch.setText("Search");
    buttonRemoveMenuSearch.setName(""); // NOI18N
    buttonRemoveMenuSearch.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonRemoveMenuSearchActionPerformed(evt);
        }
    });

    buttonRemoveMenuRemoveMenu.setBackground(new java.awt.Color(51, 153, 255));
    buttonRemoveMenuRemoveMenu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonRemoveMenuRemoveMenu.setForeground(new java.awt.Color(255, 255, 255));
    buttonRemoveMenuRemoveMenu.setText("Remove Menu");
    buttonRemoveMenuRemoveMenu.setName(""); // NOI18N
    buttonRemoveMenuRemoveMenu.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonRemoveMenuRemoveMenuActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panelRemoveMenuLayout = new javax.swing.GroupLayout(panelRemoveMenu);
    panelRemoveMenu.setLayout(panelRemoveMenuLayout);
    panelRemoveMenuLayout.setHorizontalGroup(
        panelRemoveMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelRemoveMenuLayout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addGroup(panelRemoveMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRemoveMenuLayout.createSequentialGroup()
                    .addComponent(buttonRemoveMenuSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(panelRemoveMenuLayout.createSequentialGroup()
                    .addComponent(labelRemoveMenuMenuID)
                    .addGap(47, 47, 47)
                    .addComponent(textFieldRemoveMenuMenuID, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRemoveMenuLayout.createSequentialGroup()
                    .addGroup(panelRemoveMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(scrollPaneRemoveMenuResultsTable, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelRemoveMenuLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 566, Short.MAX_VALUE)
                            .addComponent(buttonRemoveMenuReset)
                            .addGap(31, 31, 31)
                            .addComponent(buttonRemoveMenuDemo))
                        .addGroup(panelRemoveMenuLayout.createSequentialGroup()
                            .addComponent(buttonRemoveMenuHome, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonRemoveMenuRemoveMenu)))
                    .addGap(31, 31, 31))))
    );
    panelRemoveMenuLayout.setVerticalGroup(
        panelRemoveMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelRemoveMenuLayout.createSequentialGroup()
            .addGap(35, 35, 35)
            .addGroup(panelRemoveMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonRemoveMenuDemo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonRemoveMenuReset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(3, 3, 3)
            .addGroup(panelRemoveMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelRemoveMenuMenuID)
                .addComponent(textFieldRemoveMenuMenuID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(28, 28, 28)
            .addComponent(buttonRemoveMenuSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(32, 32, 32)
            .addComponent(scrollPaneRemoveMenuResultsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(37, 37, 37)
            .addGroup(panelRemoveMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonRemoveMenuRemoveMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonRemoveMenuHome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(174, Short.MAX_VALUE))
    );

    tabbedPaneFoodMenu.addTab("Remove Menu", panelRemoveMenu);

    panelViewMenu.setBackground(new java.awt.Color(255, 255, 255));

    textFieldViewMenuMenuID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldViewMenuMenuID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    textFieldViewMenuMenuID.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            textFieldViewMenuMenuIDMouseClicked(evt);
        }
    });
    textFieldViewMenuMenuID.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            textFieldViewMenuMenuIDActionPerformed(evt);
        }
    });

    labelViewMenuMenuID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelViewMenuMenuID.setText("Menu ID");

    buttonViewMenuReset.setBackground(new java.awt.Color(51, 153, 255));
    buttonViewMenuReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonViewMenuReset.setForeground(new java.awt.Color(255, 255, 255));
    buttonViewMenuReset.setText("RESET");
    buttonViewMenuReset.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonViewMenuResetActionPerformed(evt);
        }
    });

    buttonViewMenuDemo.setBackground(new java.awt.Color(51, 153, 255));
    buttonViewMenuDemo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonViewMenuDemo.setForeground(new java.awt.Color(255, 255, 255));
    buttonViewMenuDemo.setText("DEMO");
    buttonViewMenuDemo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonViewMenuDemoActionPerformed(evt);
        }
    });

    buttonViewMenuSearch.setBackground(new java.awt.Color(51, 153, 255));
    buttonViewMenuSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonViewMenuSearch.setForeground(new java.awt.Color(255, 255, 255));
    buttonViewMenuSearch.setText("Search");
    buttonViewMenuSearch.setName(""); // NOI18N
    buttonViewMenuSearch.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonViewMenuSearchActionPerformed(evt);
        }
    });

    buttonViewMenuHome.setBackground(new java.awt.Color(51, 153, 255));
    buttonViewMenuHome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonViewMenuHome.setForeground(new java.awt.Color(255, 255, 255));
    buttonViewMenuHome.setText("Home");
    buttonViewMenuHome.setName(""); // NOI18N
    buttonViewMenuHome.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonViewMenuHomeActionPerformed(evt);
        }
    });

    jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
    jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

    jList1.setEnabled(false);
    jScrollPane2.setViewportView(jList1);

    textFieldViewMenuMenuID1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldViewMenuMenuID1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    textFieldViewMenuMenuID1.setEnabled(false);
    textFieldViewMenuMenuID1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            textFieldViewMenuMenuID1ActionPerformed(evt);
        }
    });

    labelViewMenuMenuID1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelViewMenuMenuID1.setText("Menu Status");

    labelViewMenuMenuID2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelViewMenuMenuID2.setText("Menu Price");

    textFieldViewMenuMenuID2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldViewMenuMenuID2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    textFieldViewMenuMenuID2.setEnabled(false);
    textFieldViewMenuMenuID2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            textFieldViewMenuMenuID2ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panelViewMenuLayout = new javax.swing.GroupLayout(panelViewMenu);
    panelViewMenu.setLayout(panelViewMenuLayout);
    panelViewMenuLayout.setHorizontalGroup(
        panelViewMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelViewMenuLayout.createSequentialGroup()
            .addGroup(panelViewMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelViewMenuLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(labelViewMenuMenuID)
                    .addGap(38, 38, 38)
                    .addComponent(textFieldViewMenuMenuID, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(42, 42, 42)
                    .addComponent(buttonViewMenuSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelViewMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelViewMenuLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(panelViewMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(buttonViewMenuHome, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelViewMenuLayout.createSequentialGroup()
                                .addGroup(panelViewMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelViewMenuMenuID2)
                                    .addComponent(labelViewMenuMenuID1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                                .addGroup(panelViewMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldViewMenuMenuID1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textFieldViewMenuMenuID2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelViewMenuLayout.createSequentialGroup()
                        .addGap(547, 547, 547)
                        .addComponent(buttonViewMenuReset)
                        .addGap(36, 36, 36)
                        .addComponent(buttonViewMenuDemo))))
            .addContainerGap(61, Short.MAX_VALUE))
    );
    panelViewMenuLayout.setVerticalGroup(
        panelViewMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelViewMenuLayout.createSequentialGroup()
            .addGap(35, 35, 35)
            .addGroup(panelViewMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonViewMenuDemo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonViewMenuReset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(1, 1, 1)
            .addGroup(panelViewMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelViewMenuLayout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(labelViewMenuMenuID))
                .addGroup(panelViewMenuLayout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(textFieldViewMenuMenuID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(buttonViewMenuSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelViewMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(panelViewMenuLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelViewMenuLayout.createSequentialGroup()
                    .addGap(52, 52, 52)
                    .addGroup(panelViewMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textFieldViewMenuMenuID1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelViewMenuMenuID1))
                    .addGap(47, 47, 47)
                    .addGroup(panelViewMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textFieldViewMenuMenuID2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelViewMenuMenuID2))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonViewMenuHome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(36, 36, 36))
    );

    tabbedPaneFoodMenu.addTab("View Menu", panelViewMenu);

    javax.swing.GroupLayout panelFoodMenuLayout = new javax.swing.GroupLayout(panelFoodMenu);
    panelFoodMenu.setLayout(panelFoodMenuLayout);
    panelFoodMenuLayout.setHorizontalGroup(
        panelFoodMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
        .addGroup(panelFoodMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFoodMenuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tabbedPaneFoodMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(panelFoodMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFoodMenuLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(panelFoodMenuContent, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    );
    panelFoodMenuLayout.setVerticalGroup(
        panelFoodMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 0, Short.MAX_VALUE)
        .addGroup(panelFoodMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFoodMenuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tabbedPaneFoodMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(panelFoodMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFoodMenuLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelFoodMenuContent, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    );

    panelOtherOrders.setBackground(new java.awt.Color(255, 255, 255));
    panelOtherOrders.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    panelOtherOrders.setOpaque(false);
    panelOtherOrders.setPreferredSize(new java.awt.Dimension(800, 570));

    tabbedPaneOtherOrders.setBackground(new java.awt.Color(255, 255, 255));
    tabbedPaneOtherOrders.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    tabbedPaneOtherOrders.setPreferredSize(new java.awt.Dimension(798, 580));

    panelRoomOrders.setBackground(new java.awt.Color(255, 255, 255));

    scrollPaneRoomOrders.setBackground(new java.awt.Color(255, 255, 255));
    scrollPaneRoomOrders.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

    roomOrders.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }
    ));
    roomOrders.setGridColor(new java.awt.Color(51, 153, 255));
    scrollPaneRoomOrders.setViewportView(roomOrders);

    buttonAddCustomerHome1.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerHome1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonAddCustomerHome1.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerHome1.setText("See All");
    buttonAddCustomerHome1.setName(""); // NOI18N
    buttonAddCustomerHome1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerHome1ActionPerformed(evt);
        }
    });

    buttonAddCustomerNext1.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerNext1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonAddCustomerNext1.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerNext1.setText("Serach");
    buttonAddCustomerNext1.setName(""); // NOI18N
    buttonAddCustomerNext1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerNext1ActionPerformed(evt);
        }
    });

    buttonAddCustomerDemo1.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerDemo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonAddCustomerDemo1.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerDemo1.setText("DEMO");
    buttonAddCustomerDemo1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerDemo1ActionPerformed(evt);
        }
    });

    buttonAddCustomerReset1.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerReset1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonAddCustomerReset1.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerReset1.setText("RESET");
    buttonAddCustomerReset1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerReset1ActionPerformed(evt);
        }
    });

    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel1.setText("Room Order ID");

    roomOrderID.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    roomOrderID.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            roomOrderIDActionPerformed(evt);
        }
    });

    buttonAddCustomerNext2.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerNext2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonAddCustomerNext2.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerNext2.setText("Print");
    buttonAddCustomerNext2.setFocusTraversalPolicyProvider(true);
    buttonAddCustomerNext2.setName(""); // NOI18N
    buttonAddCustomerNext2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerNext2ActionPerformed(evt);
        }
    });

    buttonAddCustomerNext4.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerNext4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonAddCustomerNext4.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerNext4.setText("Home");
    buttonAddCustomerNext4.setName(""); // NOI18N
    buttonAddCustomerNext4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerNext4ActionPerformed(evt);
        }
    });

    buttonAddCustomerNext3.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerNext3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonAddCustomerNext3.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerNext3.setText("Update Status");
    buttonAddCustomerNext3.setName(""); // NOI18N
    buttonAddCustomerNext3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerNext3ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panelRoomOrdersLayout = new javax.swing.GroupLayout(panelRoomOrders);
    panelRoomOrders.setLayout(panelRoomOrdersLayout);
    panelRoomOrdersLayout.setHorizontalGroup(
        panelRoomOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelRoomOrdersLayout.createSequentialGroup()
            .addGroup(panelRoomOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRoomOrdersLayout.createSequentialGroup()
                    .addGap(554, 554, 554)
                    .addComponent(buttonAddCustomerNext4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(buttonAddCustomerNext2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelRoomOrdersLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addGroup(panelRoomOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelRoomOrdersLayout.createSequentialGroup()
                            .addComponent(buttonAddCustomerHome1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(457, 457, 457)
                            .addComponent(buttonAddCustomerReset1)
                            .addGap(30, 30, 30)
                            .addComponent(buttonAddCustomerDemo1))
                        .addComponent(buttonAddCustomerNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelRoomOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelRoomOrdersLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(roomOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonAddCustomerNext3))
                            .addComponent(scrollPaneRoomOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGap(31, 31, 31))
    );
    panelRoomOrdersLayout.setVerticalGroup(
        panelRoomOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelRoomOrdersLayout.createSequentialGroup()
            .addGap(35, 35, 35)
            .addGroup(panelRoomOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(buttonAddCustomerHome1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelRoomOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddCustomerDemo1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAddCustomerReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(30, 30, 30)
            .addComponent(scrollPaneRoomOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(19, 19, 19)
            .addGroup(panelRoomOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(roomOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1)
                .addComponent(buttonAddCustomerNext3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(18, 18, 18)
            .addComponent(buttonAddCustomerNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(23, 23, 23)
            .addGroup(panelRoomOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(buttonAddCustomerNext4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonAddCustomerNext2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
    );

    tabbedPaneOtherOrders.addTab("Room Orders", panelRoomOrders);

    panelEventOrders.setBackground(new java.awt.Color(255, 255, 255));

    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel2.setText("Event Order ID");

    buttonAddCustomerReset2.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerReset2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonAddCustomerReset2.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerReset2.setText("RESET");
    buttonAddCustomerReset2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerReset2ActionPerformed(evt);
        }
    });

    buttonAddCustomerDemo2.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerDemo2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonAddCustomerDemo2.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerDemo2.setText("DEMO");
    buttonAddCustomerDemo2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerDemo2ActionPerformed(evt);
        }
    });

    jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

    eventOrders.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null},
            {null, null, null, null}
        },
        new String [] {
            "Title 1", "Title 2", "Title 3", "Title 4"
        }
    ));
    eventOrders.setGridColor(new java.awt.Color(51, 153, 255));
    jScrollPane3.setViewportView(eventOrders);

    buttonAddCustomerNext6.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerNext6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonAddCustomerNext6.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerNext6.setText("Home");
    buttonAddCustomerNext6.setName(""); // NOI18N
    buttonAddCustomerNext6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerNext6ActionPerformed(evt);
        }
    });

    buttonAddCustomerNext7.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerNext7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonAddCustomerNext7.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerNext7.setText("Print");
    buttonAddCustomerNext7.setName(""); // NOI18N
    buttonAddCustomerNext7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerNext7ActionPerformed(evt);
        }
    });

    buttonAddCustomerHome3.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerHome3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonAddCustomerHome3.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerHome3.setText("Search");
    buttonAddCustomerHome3.setName(""); // NOI18N
    buttonAddCustomerHome3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerHome3ActionPerformed(evt);
        }
    });

    buttonAddCustomerHome4.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerHome4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonAddCustomerHome4.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerHome4.setText("See All");
    buttonAddCustomerHome4.setName(""); // NOI18N
    buttonAddCustomerHome4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerHome4ActionPerformed(evt);
        }
    });

    buttonAddCustomerNext5.setBackground(new java.awt.Color(51, 153, 255));
    buttonAddCustomerNext5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonAddCustomerNext5.setForeground(new java.awt.Color(255, 255, 255));
    buttonAddCustomerNext5.setText("Update Status");
    buttonAddCustomerNext5.setName(""); // NOI18N
    buttonAddCustomerNext5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonAddCustomerNext5ActionPerformed(evt);
        }
    });

    roomOrderID1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    roomOrderID1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            roomOrderID1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panelEventOrdersLayout = new javax.swing.GroupLayout(panelEventOrders);
    panelEventOrders.setLayout(panelEventOrdersLayout);
    panelEventOrdersLayout.setHorizontalGroup(
        panelEventOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelEventOrdersLayout.createSequentialGroup()
            .addGap(31, 31, 31)
            .addGroup(panelEventOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEventOrdersLayout.createSequentialGroup()
                    .addComponent(buttonAddCustomerHome4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAddCustomerReset2)
                    .addGap(30, 30, 30)
                    .addComponent(buttonAddCustomerDemo2))
                .addGroup(panelEventOrdersLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(buttonAddCustomerNext6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(buttonAddCustomerNext7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelEventOrdersLayout.createSequentialGroup()
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(roomOrderID1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAddCustomerNext5))
                .addGroup(panelEventOrdersLayout.createSequentialGroup()
                    .addComponent(buttonAddCustomerHome3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGap(31, 31, 31))
    );
    panelEventOrdersLayout.setVerticalGroup(
        panelEventOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelEventOrdersLayout.createSequentialGroup()
            .addGap(35, 35, 35)
            .addGroup(panelEventOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonAddCustomerReset2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonAddCustomerDemo2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonAddCustomerHome4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(30, 30, 30)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(19, 19, 19)
            .addGroup(panelEventOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonAddCustomerNext5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(roomOrderID1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2))
            .addGap(18, 18, 18)
            .addComponent(buttonAddCustomerHome3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(23, 23, 23)
            .addGroup(panelEventOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonAddCustomerNext7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonAddCustomerNext6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(33, 33, 33))
    );

    tabbedPaneOtherOrders.addTab("Event Orders", panelEventOrders);

    javax.swing.GroupLayout panelOtherOrdersLayout = new javax.swing.GroupLayout(panelOtherOrders);
    panelOtherOrders.setLayout(panelOtherOrdersLayout);
    panelOtherOrdersLayout.setHorizontalGroup(
        panelOtherOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelOtherOrdersLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(tabbedPaneOtherOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );
    panelOtherOrdersLayout.setVerticalGroup(
        panelOtherOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelOtherOrdersLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(tabbedPaneOtherOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
    );

    panelEmployee.setBackground(new java.awt.Color(255, 255, 255));
    panelEmployee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    panelEmployee.setPreferredSize(new java.awt.Dimension(800, 570));

    panelEmployeDetails.setBackground(new java.awt.Color(255, 255, 255));

    buttonEmployeeReset.setBackground(new java.awt.Color(51, 153, 255));
    buttonEmployeeReset.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonEmployeeReset.setForeground(new java.awt.Color(255, 255, 255));
    buttonEmployeeReset.setText("RESET");
    buttonEmployeeReset.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonEmployeeResetActionPerformed(evt);
        }
    });

    buttonEmployeeDemo.setBackground(new java.awt.Color(51, 153, 255));
    buttonEmployeeDemo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonEmployeeDemo.setForeground(new java.awt.Color(255, 255, 255));
    buttonEmployeeDemo.setText("DEMO");
    buttonEmployeeDemo.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonEmployeeDemoActionPerformed(evt);
        }
    });

    buttonEmployeeNext.setBackground(new java.awt.Color(51, 153, 255));
    buttonEmployeeNext.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonEmployeeNext.setForeground(new java.awt.Color(255, 255, 255));
    buttonEmployeeNext.setText("Home");
    buttonEmployeeNext.setName(""); // NOI18N
    buttonEmployeeNext.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonEmployeeNextActionPerformed(evt);
        }
    });

    labelEmployeeID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelEmployeeID.setText("Employee ID");

    textFieldEmployeeID.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldEmployeeID.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            textFieldEmployeeIDMouseClicked(evt);
        }
    });
    textFieldEmployeeID.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            textFieldEmployeeIDActionPerformed(evt);
        }
    });
    textFieldEmployeeID.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            textFieldEmployeeIDKeyPressed(evt);
        }
        public void keyReleased(java.awt.event.KeyEvent evt) {
            textFieldEmployeeIDKeyReleased(evt);
        }
    });

    scrollPaneEmployeeResultsTable.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)));

    tableEmployeeResultsTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ));
    tableEmployeeResultsTable.setEnabled(false);
    tableEmployeeResultsTable.setGridColor(new java.awt.Color(0, 153, 255));
    scrollPaneEmployeeResultsTable.setViewportView(tableEmployeeResultsTable);

    buttonEmployeeSearchSingle.setBackground(new java.awt.Color(51, 153, 255));
    buttonEmployeeSearchSingle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonEmployeeSearchSingle.setForeground(new java.awt.Color(255, 255, 255));
    buttonEmployeeSearchSingle.setText("Search Single");
    buttonEmployeeSearchSingle.setName(""); // NOI18N
    buttonEmployeeSearchSingle.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonEmployeeSearchSingleActionPerformed(evt);
        }
    });

    buttonEmployeePrint.setBackground(new java.awt.Color(51, 153, 255));
    buttonEmployeePrint.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonEmployeePrint.setForeground(new java.awt.Color(255, 255, 255));
    buttonEmployeePrint.setText("Print");
    buttonEmployeePrint.setEnabled(false);
    buttonEmployeePrint.setName(""); // NOI18N
    buttonEmployeePrint.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonEmployeePrintActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panelEmployeDetailsLayout = new javax.swing.GroupLayout(panelEmployeDetails);
    panelEmployeDetails.setLayout(panelEmployeDetailsLayout);
    panelEmployeDetailsLayout.setHorizontalGroup(
        panelEmployeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmployeDetailsLayout.createSequentialGroup()
            .addContainerGap(568, Short.MAX_VALUE)
            .addGroup(panelEmployeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(buttonEmployeeNext, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelEmployeDetailsLayout.createSequentialGroup()
                    .addComponent(buttonEmployeeReset)
                    .addGap(26, 26, 26)
                    .addComponent(buttonEmployeeDemo)))
            .addGap(24, 24, 24))
        .addGroup(panelEmployeDetailsLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(labelEmployeeID)
            .addGap(45, 45, 45)
            .addComponent(textFieldEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGroup(panelEmployeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmployeDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmployeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonEmployeePrint, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonEmployeeSearchSingle)
                    .addComponent(scrollPaneEmployeeResultsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE)))
    );
    panelEmployeDetailsLayout.setVerticalGroup(
        panelEmployeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelEmployeDetailsLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(panelEmployeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonEmployeeReset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonEmployeeDemo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(2, 2, 2)
            .addGroup(panelEmployeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelEmployeeID)
                .addComponent(textFieldEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 420, Short.MAX_VALUE)
            .addComponent(buttonEmployeeNext, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
        .addGroup(panelEmployeDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmployeDetailsLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(buttonEmployeeSearchSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(scrollPaneEmployeeResultsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(buttonEmployeePrint, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
    );

    javax.swing.GroupLayout panelEmployeeLayout = new javax.swing.GroupLayout(panelEmployee);
    panelEmployee.setLayout(panelEmployeeLayout);
    panelEmployeeLayout.setHorizontalGroup(
        panelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 798, Short.MAX_VALUE)
        .addGroup(panelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmployeeLayout.createSequentialGroup()
                .addGap(0, 23, Short.MAX_VALUE)
                .addComponent(panelEmployeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE)))
    );
    panelEmployeeLayout.setVerticalGroup(
        panelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 568, Short.MAX_VALUE)
        .addGroup(panelEmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmployeeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelEmployeDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
    );

    panelStockOrder.setBackground(new java.awt.Color(255, 255, 255));
    panelStockOrder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    panelStockOrder.setName(""); // NOI18N
    panelStockOrder.setPreferredSize(new java.awt.Dimension(800, 570));
    panelStockOrder.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel3.setText("Item ID");
    panelStockOrder.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 120, 30));

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select ID--" }));
    panelStockOrder.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 215, 30));

    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel4.setText("Item Name");
    panelStockOrder.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 120, 30));

    jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select Name--" }));
    panelStockOrder.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 215, 30));

    jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel5.setText("Item Category");
    panelStockOrder.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 120, 30));

    jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select Category--" }));
    jComboBox3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox3ActionPerformed(evt);
        }
    });
    panelStockOrder.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 215, 30));

    jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel6.setText("Department");
    panelStockOrder.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 120, 30));

    jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel7.setText("Order Qty");
    panelStockOrder.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 120, 30));

    jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    jLabel8.setText("Units");
    panelStockOrder.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 120, 30));

    jTextField3.setEditable(false);
    jTextField3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextField3ActionPerformed(evt);
        }
    });
    panelStockOrder.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 215, 30));

    jTextField4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jTextField4ActionPerformed(evt);
        }
    });
    jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyReleased(java.awt.event.KeyEvent evt) {
            jTextField4KeyReleased(evt);
        }
    });
    panelStockOrder.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 215, 30));

    jTextField5.setEditable(false);
    panelStockOrder.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 215, 30));

    jButton1.setBackground(new java.awt.Color(42, 135, 235));
    jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButton1.setForeground(new java.awt.Color(255, 255, 255));
    jButton1.setText("Clear");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });
    panelStockOrder.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 430, 94, 43));

    jButton2.setBackground(new java.awt.Color(42, 135, 235));
    jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jButton2.setForeground(new java.awt.Color(255, 255, 255));
    jButton2.setText("Request Order");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });
    panelStockOrder.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 430, 140, 43));

    jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
    jLabel18.setForeground(new java.awt.Color(51, 204, 0));
    jLabel18.setText("Availability");
    panelStockOrder.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, -1, -1));

    panelMaintenance.setBackground(new java.awt.Color(255, 255, 255));
    panelMaintenance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    panelMaintenance.setPreferredSize(new java.awt.Dimension(800, 570));

    accountDepositLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    accountDepositLabel.setText("Monthly Deposit");

    accountDepositTextField.setEditable(false);

    accountBalanceLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    accountBalanceLabel.setText("Balance");

    accountBalanceTextField.setEditable(false);

    accountRequestAmountLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    accountRequestAmountLabel.setText("Request Amount (Rs)");

    accountRequestAmountTextField.addKeyListener(new java.awt.event.KeyAdapter() {
        public void keyPressed(java.awt.event.KeyEvent evt) {
            accountRequestAmountTextFieldKeyPressed(evt);
        }
    });

    accountRequestCreditButton.setBackground(new java.awt.Color(42, 135, 235));
    accountRequestCreditButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    accountRequestCreditButton.setForeground(new java.awt.Color(255, 255, 255));
    accountRequestCreditButton.setText("Request Credit");
    accountRequestCreditButton.setBorder(null);
    accountRequestCreditButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            accountRequestCreditButtonActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panelMaintenanceLayout = new javax.swing.GroupLayout(panelMaintenance);
    panelMaintenance.setLayout(panelMaintenanceLayout);
    panelMaintenanceLayout.setHorizontalGroup(
        panelMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelMaintenanceLayout.createSequentialGroup()
            .addGap(182, 182, 182)
            .addGroup(panelMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(accountBalanceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(accountDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(accountRequestAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addGroup(panelMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(accountRequestCreditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(accountDepositTextField)
                .addComponent(accountBalanceTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addComponent(accountRequestAmountTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
            .addContainerGap(258, Short.MAX_VALUE))
    );
    panelMaintenanceLayout.setVerticalGroup(
        panelMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelMaintenanceLayout.createSequentialGroup()
            .addContainerGap(147, Short.MAX_VALUE)
            .addGroup(panelMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(accountDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelMaintenanceLayout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(accountDepositTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(33, 33, 33)
            .addGroup(panelMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(accountBalanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(accountBalanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(99, 99, 99)
            .addGroup(panelMaintenanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(accountRequestAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(accountRequestAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(27, 27, 27)
            .addComponent(accountRequestCreditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(106, 106, 106))
    );

    panelReports.setBackground(new java.awt.Color(255, 255, 255));
    panelReports.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
    panelReports.setPreferredSize(new java.awt.Dimension(800, 570));

    labelParameter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelParameter.setText("Parameter");

    buttonDemoReports.setBackground(new java.awt.Color(51, 153, 255));
    buttonDemoReports.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonDemoReports.setForeground(new java.awt.Color(255, 255, 255));
    buttonDemoReports.setText("DEMO");
    buttonDemoReports.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonDemoReportsActionPerformed(evt);
        }
    });

    buttonResetReports.setBackground(new java.awt.Color(51, 153, 255));
    buttonResetReports.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonResetReports.setForeground(new java.awt.Color(255, 255, 255));
    buttonResetReports.setText("RESET");
    buttonResetReports.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonResetReportsActionPerformed(evt);
        }
    });

    textFieldParameter.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldParameter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

    buttonPrintReport.setBackground(new java.awt.Color(51, 153, 255));
    buttonPrintReport.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonPrintReport.setForeground(new java.awt.Color(255, 255, 255));
    buttonPrintReport.setText("Print");
    buttonPrintReport.setName(""); // NOI18N
    buttonPrintReport.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonPrintReportActionPerformed(evt);
        }
    });

    buttonGenerateReport.setBackground(new java.awt.Color(51, 153, 255));
    buttonGenerateReport.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonGenerateReport.setForeground(new java.awt.Color(255, 255, 255));
    buttonGenerateReport.setText("Generate Report");
    buttonGenerateReport.setName(""); // NOI18N
    buttonGenerateReport.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonGenerateReportActionPerformed(evt);
        }
    });

    buttonHomeReport.setBackground(new java.awt.Color(51, 153, 255));
    buttonHomeReport.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonHomeReport.setForeground(new java.awt.Color(255, 255, 255));
    buttonHomeReport.setText("Home");
    buttonHomeReport.setName(""); // NOI18N
    buttonHomeReport.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonHomeReportActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panelReportsLayout = new javax.swing.GroupLayout(panelReports);
    panelReports.setLayout(panelReportsLayout);
    panelReportsLayout.setHorizontalGroup(
        panelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelReportsLayout.createSequentialGroup()
            .addGap(33, 33, 33)
            .addGroup(panelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelReportsLayout.createSequentialGroup()
                    .addGroup(panelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelReportsLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 590, Short.MAX_VALUE)
                            .addComponent(buttonResetReports)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(buttonDemoReports))
                        .addGroup(panelReportsLayout.createSequentialGroup()
                            .addComponent(buttonPrintReport, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonHomeReport, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(31, 31, 31))
                .addGroup(panelReportsLayout.createSequentialGroup()
                    .addGroup(panelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelReportsLayout.createSequentialGroup()
                            .addComponent(labelParameter)
                            .addGap(144, 144, 144)
                            .addComponent(textFieldParameter, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(buttonGenerateReport))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
    );
    panelReportsLayout.setVerticalGroup(
        panelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelReportsLayout.createSequentialGroup()
            .addGap(35, 35, 35)
            .addGroup(panelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonDemoReports, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonResetReports, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(panelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(textFieldParameter, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(labelParameter))
            .addGap(26, 26, 26)
            .addComponent(buttonGenerateReport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
            .addGroup(panelReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(buttonPrintReport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonHomeReport, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(52, 52, 52))
    );

    panelUsers.setBackground(new java.awt.Color(255, 255, 255));
    panelUsers.setPreferredSize(new java.awt.Dimension(800, 570));

    labelUserName.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    labelUserName.setText("Username");

    textFieldEmployeeID1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    textFieldEmployeeID1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            textFieldEmployeeID1ActionPerformed(evt);
        }
    });

    buttonEmployeeSearchAll1.setBackground(new java.awt.Color(51, 153, 255));
    buttonEmployeeSearchAll1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonEmployeeSearchAll1.setForeground(new java.awt.Color(255, 255, 255));
    buttonEmployeeSearchAll1.setText("Search");
    buttonEmployeeSearchAll1.setName(""); // NOI18N
    buttonEmployeeSearchAll1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonEmployeeSearchAll1ActionPerformed(evt);
        }
    });

    scrollPaneEmployeeResultsTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

    tableEmployeeResultsTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
    ));
    tableEmployeeResultsTable1.setGridColor(new java.awt.Color(51, 153, 255));
    scrollPaneEmployeeResultsTable1.setViewportView(tableEmployeeResultsTable1);

    buttonEmployeeNext1.setBackground(new java.awt.Color(51, 153, 255));
    buttonEmployeeNext1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    buttonEmployeeNext1.setForeground(new java.awt.Color(255, 255, 255));
    buttonEmployeeNext1.setText("Home");
    buttonEmployeeNext1.setName(""); // NOI18N
    buttonEmployeeNext1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonEmployeeNext1ActionPerformed(evt);
        }
    });

    buttonEmployeeDemo1.setBackground(new java.awt.Color(51, 153, 255));
    buttonEmployeeDemo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonEmployeeDemo1.setForeground(new java.awt.Color(255, 255, 255));
    buttonEmployeeDemo1.setText("DEMO");
    buttonEmployeeDemo1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonEmployeeDemo1ActionPerformed(evt);
        }
    });

    buttonEmployeeReset1.setBackground(new java.awt.Color(51, 153, 255));
    buttonEmployeeReset1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
    buttonEmployeeReset1.setForeground(new java.awt.Color(255, 255, 255));
    buttonEmployeeReset1.setText("RESET");
    buttonEmployeeReset1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            buttonEmployeeReset1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout panelUsersLayout = new javax.swing.GroupLayout(panelUsers);
    panelUsers.setLayout(panelUsersLayout);
    panelUsersLayout.setHorizontalGroup(
        panelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(panelUsersLayout.createSequentialGroup()
            .addGap(32, 32, 32)
            .addGroup(panelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelUsersLayout.createSequentialGroup()
                    .addComponent(labelUserName)
                    .addGap(33, 33, 33)
                    .addComponent(textFieldEmployeeID1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEmployeeReset1)
                    .addGap(18, 18, 18)
                    .addComponent(buttonEmployeeDemo1))
                .addComponent(scrollPaneEmployeeResultsTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonEmployeeNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(buttonEmployeeSearchAll1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(49, Short.MAX_VALUE))
    );
    panelUsersLayout.setVerticalGroup(
        panelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUsersLayout.createSequentialGroup()
            .addGroup(panelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelUsersLayout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addGroup(panelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textFieldEmployeeID1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelUserName))
                    .addGap(30, 30, 30)
                    .addComponent(buttonEmployeeSearchAll1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(panelUsersLayout.createSequentialGroup()
                    .addGap(0, 50, Short.MAX_VALUE)
                    .addGroup(panelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonEmployeeDemo1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonEmployeeReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(110, 110, 110)))
            .addComponent(scrollPaneEmployeeResultsTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(buttonEmployeeNext1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(53, 53, 53))
    );

    javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
    contentPanel.setLayout(contentPanelLayout);
    contentPanelLayout.setHorizontalGroup(
        contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(panelRestaurantOrder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelFoodMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelOtherOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelStockOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelMaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelReports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
    );
    contentPanelLayout.setVerticalGroup(
        contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(panelRestaurantOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelFoodMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelOtherOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelStockOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelMaintenance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelReports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
    );

    mainDesktopPane.add(contentPanel);
    contentPanel.setBounds(210, 120, 800, 570);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(mainDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(0, 0, 0)
            .addComponent(mainDesktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //method to change button colors when shifting to panel related to it
    public void changeButtonColors(String clicked)
    {
        if(clicked.equals("clicked1")) //restaurant order is clicked
        {
            for(int i=0;i<9;i++)
            {
                if(i!=0)
                {                    
                    menubuttons[i].setBackground(new Color(51,153,255));
                }                
            }
        }    
    
        if(clicked.equals("clicked2")) //customer is clicked
        {
            for(int i=0;i<9;i++)
            {
                if(i!=1)
                {                    
                    menubuttons[i].setBackground(new Color(51,153,255));
                }                
            }            
        }
        
        if(clicked.equals("clicked3")) //food menu is clicked
        {
            for(int i=0;i<9;i++)
            {
                if(i!=2)
                {                    
                    menubuttons[i].setBackground(new Color(51,153,255));
                }                
            }            
        }
        
        if(clicked.equals("clicked4")) //other orders is clicked
        {
            for(int i=0;i<9;i++)
            {
                if(i!=3)
                {                    
                    menubuttons[i].setBackground(new Color(51,153,255));
                }                
            }            
        }
        
        if(clicked.equals("clicked5")) //employee is clicked
        {
            for(int i=0;i<9;i++)
            {
                if(i!=4)
                {                    
                    menubuttons[i].setBackground(new Color(51,153,255));
                }                
            }            
        }
        
        if(clicked.equals("clicked6")) //stock order is clicked
        {
            for(int i=0;i<9;i++)
            {
                if(i!=5)
                {                    
                    menubuttons[i].setBackground(new Color(51,153,255));
                }                
            }            
        }
        
        if(clicked.equals("clicked7")) //maintenance is clicked
        {
            for(int i=0;i<9;i++)
            {
                if(i!=6)
                {                    
                    menubuttons[i].setBackground(new Color(51,153,255));
                }                
            }            
        }
        
        if(clicked.equals("clicked8")) //reports is clicked
        {
            for(int i=0;i<9;i++)
            {
                if(i!=7)
                {                    
                    menubuttons[i].setBackground(new Color(51,153,255));
                }                
            }            
        }
        
        if(clicked.equals("clicked9")) //users is clicked
        {
            for(int i=0;i<9;i++)
            {
                if(i!=8)
                {                    
                    menubuttons[i].setBackground(new Color(51,153,255));
                }                
            }            
        }            
    }     
    
    //method to calculate the restaurant order payment amount
    public String calculate()
    {
        String fooditem = "";
        String type = "";
        String qty = "";        
        String text="";
        String prices = "";
        double total = 0.0;
        StringTokenizer tk1 ;
        int count = 0;
        String itemprice1 = "",itemprice2 = ""; 
        
        for(int i = 0 ;i<data.size();i++)
        {
            text = data.get(i);
            System.out.println("text is : "+text);
            tk1 = new StringTokenizer(text,"-");
            
            while(tk1.hasMoreTokens())
            {
                count++;
                
                if(count==1)
                {
                    fooditem=tk1.nextToken().trim();
                } 
                if(count==2)
                {
                    type=tk1.nextToken();
                } 
                if(count==3)
                {
                    qty=tk1.nextToken();
                    String sql = "SELECT food_price FROM `food_item` WHERE food_name LIKE '%"+fooditem+"%'";
                    System.out.println(sql);            
                
                    try 
                    {
                        pre = conn.prepareStatement(sql);
                        rs = pre.executeQuery();
                    
                        while(rs.next())
                        {
                              prices = rs.getString("food_price");   
                              System.out.println("PRI : "+prices);
                        }                  
                    } 
                    catch (Exception ex) 
                    {
                        System.out.println(ex);
                    }
           
                    itemprice1 = prices.substring(5,11);
                    System.out.println("P1 :"+itemprice1);

                    itemprice2 = prices.substring(17);
                    System.out.println("P2 :"+itemprice2);
                
                    if(type.equals("Full"))
                    {
                        total = total + (Integer.parseInt(qty))*(Double.parseDouble(itemprice1)); 
                    }    
                    if(type.equals("Half"))
                    {
                        total = total + (Integer.parseInt(qty))*(Double.parseDouble(itemprice2)); 
                    } 
                    
                } 
            }            
        }
        
    String x = Double.toString(total);        
    return x;
    
    } 

    //validation methods
    public boolean isNICValid(String nic)
    {
        String r = "^\\d{9}[V|v|x|X]$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(r);
        java.util.regex.Matcher m = pattern.matcher(nic);
        return m.matches();      
    }
    
    public boolean isPhoneNumberValid(String phoneNumber)
    {
        String phone = "\\+(9[976]\\d|8[987530]\\d|6[987]\\d|5[90]\\d|42\\d|3[875]\\d|2[98654321]\\d|9[8543210]|8[6421]|6[6543210]|5[87654321]|4[987654310]|3[9643210]|2[70]|7|1)\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*\\d\\W*(\\d{1,2})$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(phone);
        java.util.regex.Matcher m = pattern.matcher(phoneNumber);
        return m.matches();      
    }
    
    public boolean isValidEmailAddress(String email) 
    {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }    
    
    private void buttonRestaurantOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRestaurantOrderActionPerformed
         panelshift(buttonRestaurantOrder.getText());           
         buttonUpdateOrderReset.doClick();
         buttonAddOrderReset.doClick();
         buttonRemoveOrderReset.doClick();
         buttonSearchOrderReset.doClick();
         
    }//GEN-LAST:event_buttonRestaurantOrderActionPerformed
   
    private void buttonFoodMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodMenuActionPerformed
            panelshift(buttonFoodMenu.getText());
            jScrollPane6.setVisible(false);
            jTextArea1.setVisible(false);
            jScrollPane9.setVisible(false);
            jTextArea2.setVisible(false);
            buttonRemoveMenuReset.doClick();
            buttonViewMenuReset.doClick();
            buttonFoodMenuReset.doClick();
            
    }//GEN-LAST:event_buttonFoodMenuActionPerformed

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
            int result=JOptionPane.showConfirmDialog(contentPanel,"Do you want to Log Out from the system?","Logout Confirmation",0);
        if(result==0){
            System_Login login=new System_Login();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_buttonLogOutActionPerformed

    private void buttonEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeeActionPerformed
            panelshift(buttonEmployee.getText());
            buttonEmployeeReset.doClick();
    }//GEN-LAST:event_buttonEmployeeActionPerformed
            
    private void buttonCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustomerActionPerformed
            panelshift(buttonCustomer.getText());    
            buttonCustomerContentAddCustomer.setEnabled(false);            
    }//GEN-LAST:event_buttonCustomerActionPerformed

    private void buttonStockOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStockOrderActionPerformed
            panelshift(buttonStockOrder.getText());
            jLabel18.setVisible(false);
            jButton1.doClick();
            //jTextField5.setText(null);
            jTextField5.setText("");
            
            
        try{
            //retrieve data to the combo boxes from the database
            String sql = "select item_category_name as 'Select Item Category' from item_category";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            //data to addStockPurchaseItemCategoryComboBox
            String itemCategory;
            while(rs.next()){
                itemCategory = rs.getString("Select Item Category");
                jComboBox3.addItem(itemCategory);
            }
            
            sql = "select item_name as 'Select Item Name' from item where item_availability = 'available'";
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            //data to addStockPurchaseItemCategoryComboBox
            String itemName;
            while(rs.next()){
                itemName = rs.getString("Select Item Name");
                jComboBox2.addItem(itemName);
            }  
            
            sql = "select item_id as 'Select Item ID' from item where item_availability = 'available'";
            pre= conn.prepareStatement(sql);
            rs = pre.executeQuery();
            //data to addStockPurchaseItemCategoryComboBox
            String itemId;
            while(rs.next()){
                itemId = rs.getString("Select Item ID");
                jComboBox1.addItem(itemId);
            }
            
            jTextField3.setText("Restaurant");
            
        }
        catch(Exception e)
        {
            
        }        
            
    }//GEN-LAST:event_buttonStockOrderActionPerformed

    private void buttonAddOrderDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddOrderDemoActionPerformed
            comboBoxAddOrderSelectMenu.setSelectedIndex(1);                  
    }//GEN-LAST:event_buttonAddOrderDemoActionPerformed

    //method to set the created Jtable "Type" column in Add Order
    public void setUpTypeColumn(JTable table,TableColumn typeColumn)
    {
        String types[] = {"Full","Half"};
        comboBox = new JComboBox<String>(types);
        comboBox.setBackground(new Color(255, 255, 255));         
        tableOrderitems.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(comboBox));        
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getDefaultRenderer(Integer.class);
        renderer.setHorizontalAlignment(SwingConstants.CENTER);  
    }
    
    private void comboBoxAddOrderSelectMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxAddOrderSelectMenuActionPerformed

    if(comboBoxAddOrderSelectMenu.getSelectedIndex()>0)
    {
        //loading the orderitems table
        tableOrderitems = new javax.swing.JTable();
        tableOrderitems.setModel(new javax.swing.table.DefaultTableModel            
        (
            new Object [][] { }, new String [] { "Item", "Type","Quantity" }
        )
        {
            Class[] type = new Class [] 
            {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean []
            {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) 
            {
                return type [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) 
            {
                return canEdit [columnIndex];
            }
        }
                
        );        
         
        buttonAddOrderAdd.setEnabled(true);
        buttonAddOrderRemove.setEnabled(true);
        orderScrollPane.setViewportView(tableOrderitems);          
        orderScrollPane.getViewport().setBackground(Color.white); 
        tableOrderitems.setOpaque(false);
        tableOrderitems.setGridColor(new Color(42, 135, 235));       
        tableOrderitems.getColumnModel().getColumn(0).setResizable(false);
        tableOrderitems.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableOrderitems.getColumnModel().getColumn(1).setResizable(false);
        tableOrderitems.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableOrderitems.getColumnModel().getColumn(2).setResizable(false);
        tableOrderitems.getColumnModel().getColumn(2).setPreferredWidth(100);
        
        //setting up the combobox in type column
        setUpTypeColumn(tableOrderitems,tableOrderitems.getColumnModel().getColumn(1));        
                  
        //adding the data from database to the list box
        int selectedindex = comboBoxAddOrderSelectMenu.getSelectedIndex();                      
        int selectedmenuID = selectedMenu(selectedindex);  
        
        if(selectedmenuID>0)
        { 
            comboBoxAddOrderSelectMenu.setBackground(new Color(255, 255, 255));            
        } 
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) listAddOrderItems1.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);  
        String orderdes = ""; //compile error prevention
        //showing menu items on list         
        String sql = "SELECT menu_des FROM menu WHERE menu_id = '" + selectedmenuID + "'";
            
        try 
        {
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            
            DefaultListModel model = new DefaultListModel();
            listAddOrderItems1.setModel(model);
                   
            while(rs.next())
            {
               orderdes = rs.getString("menu_des");
               StringTokenizer tk1 = new StringTokenizer(orderdes,",");               
             
               while(tk1.hasMoreTokens())
               {
                    model.addElement(tk1.nextToken()+"\n");       
               }
            }                
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
        }
    }
    
    }//GEN-LAST:event_comboBoxAddOrderSelectMenuActionPerformed

    private void comboBoxUpdateOrderSelectMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUpdateOrderSelectMenuActionPerformed
          // menuselection
          menuselection++;
          System.out.println("MENU SLECTION "+menuselection);
          if(menuselection>1)
          {
              
              DefaultTableModel model = (DefaultTableModel)tableUpdateOrder.getModel();
                while(model.getRowCount() > 0)
                {
                    for (int i = 0;i< model.getRowCount(); i++)
                    {
                        model.removeRow(i)  ;  
                    }   
                }
          }    
           
            int selm = selectedMenu(comboBoxUpdateOrderSelectMenu.getSelectedIndex());
            
            updateAdd.setEnabled(true);
            updateRemove.setEnabled(true);  
                DefaultListCellRenderer renderer2 = (DefaultListCellRenderer) listAddOrderItems2.getCellRenderer();
                renderer2.setHorizontalAlignment(SwingConstants.CENTER); 
           
            //showing menu items on list         
            String sql = "SELECT menu_des FROM menu WHERE menu_id = '" + selm + "'";
            try 
            {
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
                
                DefaultListModel model2 = new DefaultListModel();
                listAddOrderItems2.setModel(model2);
                
                
                while(rs.next())
                {
                    String menu = rs.getString("menu_des");
                    StringTokenizer tk2 = new StringTokenizer(menu,",");               
                    while(tk2.hasMoreTokens())
                    {
                        model2.addElement(tk2.nextToken()+"\n");       
                    }
                }                
            } 
            catch (SQLException ex) 
            {
                    System.out.println(ex);
            }
                 
    }//GEN-LAST:event_comboBoxUpdateOrderSelectMenuActionPerformed
       
    private void textFieldUpdateOrderOrderIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldUpdateOrderOrderIDActionPerformed

    }//GEN-LAST:event_textFieldUpdateOrderOrderIDActionPerformed

    private void buttonUpdateOrderDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateOrderDemoActionPerformed
        //setting demo values to Update Order
        textFieldUpdateOrderOrderID.setText("510000");
        comboBoxUpdateOrderSelectMenu.setSelectedIndex(1);      
    }//GEN-LAST:event_buttonUpdateOrderDemoActionPerformed
    
    public boolean updateordervalidations()
    {
        String orderid = textFieldUpdateOrderOrderID.getText();
        //validating the orderid       
        if(isInteger(orderid))
        {
            //checking whether the orderid is empty is done inside the isInteger method         
            int menutype = comboBoxUpdateOrderSelectMenu.getSelectedIndex();       
            
            //checking whether a menu is selected
            if(menutype==0)
            {
                
                JOptionPane.showMessageDialog(null,"Menu ID not provided!");       
            }  
            else //if menu ID is provided
            { 
                return true;             
            }    
        } 
        else //if orderid is not an integer
        {
            //JOptionPane.showMessageDialog(panelup, e, "Query Execution Error!", 0);
            JOptionPane.showMessageDialog(null,"Invalid Order ID"); 
            return false;
        }    
        
        return false;            
    }       

    boolean updateorder = false;
    private void buttonUpdateOrderUpdateOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateOrderUpdateOrderActionPerformed

    if(textFieldUpdateOrderOrderID.getText().equals(""))
    {
         JOptionPane.showMessageDialog(null,"Invalid Data", "Warning",JOptionPane.WARNING_MESSAGE); 
    }    
    else
    {
    
    int n = JOptionPane.showConfirmDialog(null,"Are you sure?","Confirm Update",JOptionPane.YES_NO_OPTION);
            
    if(n ==0)
    {   
        updateorder = true;
        selectedMenu(comboBoxUpdateOrderSelectMenu.getSelectedIndex());
        int selectedindex = comboBoxUpdateOrderSelectMenu.getSelectedIndex();
        int selectedmenuID = selectedMenu(selectedindex);        
        boolean flag = true;
        
        //SAVING ORDER DATA
        try
        {
            for (int count = 0; count < tableUpdateOrder.getRowCount(); count++)
            {   
                String item = tableUpdateOrder.getValueAt(count, 0).toString();
                String type = tableUpdateOrder.getValueAt(count, 1).toString();
                String qty = tableUpdateOrder.getValueAt(count, 2).toString();
               
                if((qtyValidation(qty)) && (flag==true))
                {
                    data.add(item+"-"+type+"-"+qty+"-");
                }
                else 
                {
                    if(flag==false)
                        JOptionPane.showMessageDialog(null,"Invalid Quantity", "Warning",JOptionPane.WARNING_MESSAGE);                  
                }
            }
            
            orderdetails[0] = Integer.toString(selectedmenuID);
            System.out.println(data); 
            System.out.println("DATA : "+data.get(0));           
           
       //redirecting to Add Payment from update order
       
       labelMenuID.setText(Integer.toString(selectedmenuID)); 
       for(int i = 0 ;i<data.size();i++)
       textAreaPaymentOrderDescription.append(data.get(i)+"\n");
       
        Double a = Double.parseDouble(calculate());
        textFieldPaymentAmount.setText(String.format("%.2f", a)); //calculates the amount and displays  
        orderdetails[4] = textFieldPaymentAmount.getText();        
        
        panelWelcome.setVisible(false);
        panelRestaurantOrder.setVisible(false);
        panelCustomer.setVisible(true);  
        tabbedPaneCustomer.setVisible(false);
        panelCustomerContent.setVisible(false);
        panelPayment.setVisible(true);        
        panelFoodMenu.setVisible(false);
        panelOtherOrders.setVisible(false);
        panelStockOrder.setVisible(false);
        panelMaintenance.setVisible(false);
        panelReports.setVisible(false);
        panelUsers.setVisible(false);
        comboBoxUpdateOrderSelectMenu.setSelectedIndex(0);
        textFieldUpdateOrderOrderID.setText("");
        buttonRestaurantOrder.setBackground(new Color(51,153,255));
        buttonPaymentBack.setEnabled(false); 
        
        }
        catch(Exception e)
        {
                buttonUpdateOrderUpdateOrder.setEnabled(false);
                JOptionPane.showMessageDialog(null,"Invalid Data","Error",JOptionPane.ERROR_MESSAGE);               
        }
        
    } 
    }
        
    }//GEN-LAST:event_buttonUpdateOrderUpdateOrderActionPerformed

    private void buttonRemoveOrderRemoveOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveOrderRemoveOrderActionPerformed

       int n = JOptionPane.showConfirmDialog(null,"Are you sure?","Confirm Update",JOptionPane.YES_NO_OPTION);
       
        if(n==0)
        {
                       
            String sql1 = "UPDATE `restaurant_order` SET order_status = '0' WHERE restaurant_order_id = '" + textFieldRemoveOrderOrderID.getText() + "'";
            System.out.println(sql1);            
            
            try
            {
                    pre = conn.prepareStatement(sql1);
                    pre.execute();                    
                    JOptionPane.showMessageDialog(null,"Order Status Updated","Success",JOptionPane.INFORMATION_MESSAGE);
                    buttonRemoveOrderReset.doClick();
                     buttonAddOrderNext1.doClick();
            } 
            catch (SQLException ex)
            {
                    System.out.println(ex);
            }
              
       }    
      
    }//GEN-LAST:event_buttonRemoveOrderRemoveOrderActionPerformed

    private void buttonRestaurantOrderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonRestaurantOrderMouseEntered
       if(clicked1==0)
       buttonRestaurantOrder.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonRestaurantOrderMouseEntered

    private void buttonUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUsersActionPerformed
        panelshift(buttonUsers.getText());
    }//GEN-LAST:event_buttonUsersActionPerformed

    private void buttonMaintenanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMaintenanceActionPerformed
        panelshift(buttonMaintenance.getText());
       accountRequestAmountTextField.setText("");
                
            String sql = "SELECT budget_amount, budget_balance FROM fin_budget WHERE budget_department = 'Restaurant'";
            System.out.println(sql);
            try 
            {
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
                while(rs.next())
                {
                    accountDepositTextField.setText(rs.getString("budget_amount"));
                    accountBalanceTextField.setText(rs.getString("budget_balance"));
                }
                
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Error Retrieving Data!","Error",JOptionPane.ERROR_MESSAGE);                
            }        
                
        
        
    }//GEN-LAST:event_buttonMaintenanceActionPerformed

    private void buttonOtherOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOtherOrdersActionPerformed
        buttonAddCustomerReset1.doClick();
        panelshift(buttonOtherOrders.getText());
        
    }//GEN-LAST:event_buttonOtherOrdersActionPerformed

    private void buttonUpdateOrderHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateOrderHomeActionPerformed
         buttonAddOrderNext1.doClick();
         
    }//GEN-LAST:event_buttonUpdateOrderHomeActionPerformed

    private void buttonCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCustomerMouseEntered
        if(clicked2==0)buttonCustomer.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonCustomerMouseEntered

    private void buttonCustomerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCustomerMouseExited
        if(clicked2==0)buttonCustomer.setBackground(new Color(51,153,255));        
    }//GEN-LAST:event_buttonCustomerMouseExited

    private void buttonEmployeeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEmployeeMouseEntered
        if(clicked5==0)buttonEmployee.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonEmployeeMouseEntered

    private void buttonEmployeeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEmployeeMouseExited
        if(clicked5==0)buttonEmployee.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_buttonEmployeeMouseExited

    private void buttonFoodMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonFoodMenuMouseEntered
        if(clicked3==0)buttonFoodMenu.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonFoodMenuMouseEntered

    private void buttonFoodMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonFoodMenuMouseExited
        if(clicked3==0)buttonFoodMenu.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_buttonFoodMenuMouseExited

    private void buttonStockOrderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonStockOrderMouseEntered
        if(clicked6==0)buttonStockOrder.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonStockOrderMouseEntered

    private void buttonStockOrderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonStockOrderMouseExited
        if(clicked6==0)buttonStockOrder.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_buttonStockOrderMouseExited

    private void buttonUsersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUsersMouseEntered
        buttonUsers.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonUsersMouseEntered

    private void buttonUsersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUsersMouseExited
        buttonUsers.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_buttonUsersMouseExited

    private void buttonMaintenanceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMaintenanceMouseEntered
        if(clicked7==0)buttonMaintenance.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonMaintenanceMouseEntered

    private void buttonMaintenanceMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMaintenanceMouseExited
        if(clicked7==0)buttonMaintenance.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_buttonMaintenanceMouseExited

    private void buttonOtherOrdersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonOtherOrdersMouseEntered
        if(clicked4==0)buttonOtherOrders.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonOtherOrdersMouseEntered

    private void buttonOtherOrdersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonOtherOrdersMouseExited
        if (clicked4==0)
        buttonOtherOrders.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_buttonOtherOrdersMouseExited

    private void buttonRemoveOrderHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveOrderHomeActionPerformed
         //panelshift(buttonRemoveOrderHome.getText());
         buttonAddOrderNext1.doClick();
    }//GEN-LAST:event_buttonRemoveOrderHomeActionPerformed

    private void textFieldSerachOrderOrderIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSerachOrderOrderIDActionPerformed
       
    }//GEN-LAST:event_textFieldSerachOrderOrderIDActionPerformed

    private void buttonSearchOrderDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchOrderDemoActionPerformed
       textFieldSerachOrderOrderID1.setText("958351518V"); 
    }//GEN-LAST:event_buttonSearchOrderDemoActionPerformed

    private void buttonSearchOrderHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchOrderHomeActionPerformed
       buttonAddOrderNext1.doClick();
      buttonSearchOrderReset.doClick();
    }//GEN-LAST:event_buttonSearchOrderHomeActionPerformed

    private void buttonSearchOrderPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchOrderPrintActionPerformed
        
        MessageFormat header = new  MessageFormat("Order Details Report");
        MessageFormat footer = new  MessageFormat("page{0,number,integer}");
        
        try
        {
            tableSearchOrderTable.print(JTable.PrintMode.NORMAL, header, footer);            
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }//GEN-LAST:event_buttonSearchOrderPrintActionPerformed

    private void buttonRemoveOrderDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveOrderDemoActionPerformed
        textFieldRemoveOrderOrderID.setText("510017");
    }//GEN-LAST:event_buttonRemoveOrderDemoActionPerformed

    private void textFieldRemoveOrderOrderIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldRemoveOrderOrderIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldRemoveOrderOrderIDActionPerformed

    private void buttonAddOrderResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddOrderResetActionPerformed
        
        //resetting the Add Order tab
        try
        {
            DefaultListModel listModel = (DefaultListModel) listAddOrderItems1.getModel();
            listModel.removeAllElements();
        }
        catch(Exception e)
        {}
        
        comboBoxAddOrderSelectMenu.setSelectedIndex(0); 
        buttonAddOrderAdd.setEnabled(false);
        buttonAddOrderRemove.setEnabled(false);
        comboBoxAddOrderSelectMenu.setBackground(Color.white);
        int selectedindex = comboBoxAddOrderSelectMenu.getSelectedIndex();
        int selectedmenuID = selectedMenu(selectedindex); 
          
        comboBoxAddOrderSelectMenu.setBackground(new Color(255, 255, 255)); 
        
        tableOrderitems = new javax.swing.JTable();

        tableOrderitems.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
        ){public boolean isCellEditable(int row, int column){return false;}});

        tableOrderitems.addComponentListener(new java.awt.event.ComponentAdapter() {
        public void componentShown(java.awt.event.ComponentEvent evt) {
        tableOrderitemsComponentShown(evt);
        }
        });

        orderScrollPane.setViewportView(tableOrderitems);
    }//GEN-LAST:event_buttonAddOrderResetActionPerformed

    private void buttonUpdateOrderResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateOrderResetActionPerformed
        //resetting the Update Order tab
        textFieldUpdateOrderOrderID.setText("");
        comboBoxUpdateOrderSelectMenu.setSelectedIndex(0);
        try
        {
            DefaultListModel listModel = (DefaultListModel) listAddOrderItems2.getModel();
            listModel.removeAllElements();
        }
        catch(Exception e)
        {}
        updateAdd.setEnabled(false);
        updateRemove.setEnabled(false);
        textFieldUpdateOrderOrderID.setBackground(Color.white);
        oidcount = 0;
        updateA = true;//available in DB
        menuselection = 0;
       
        tableUpdateOrder = new javax.swing.JTable();

        tableUpdateOrder.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
        },
        new String [] {

        }
        ));

        scrollPaneUpdateOrder.setViewportView(tableUpdateOrder);
        
    }//GEN-LAST:event_buttonUpdateOrderResetActionPerformed

    private void buttonRemoveOrderResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveOrderResetActionPerformed
        //resetting the Remove Order tab
        textFieldRemoveOrderOrderID.setText("");      
        
        tableRemoveOrderResultsTable = new javax.swing.JTable();
        tableRemoveOrderResultsTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
        }
        ) {
        Class[] types = new Class [] {
        java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean [] {
        false, false, false, false, false
        };

        public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
        }
        });

        tableRemoveOrderResultsTable.setEnabled(false);
        tableRemoveOrderResultsTable.setGridColor(new java.awt.Color(51, 153, 255));
        jScrollPane1.setViewportView(tableRemoveOrderResultsTable);

    }//GEN-LAST:event_buttonRemoveOrderResetActionPerformed

    private void buttonSearchOrderResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchOrderResetActionPerformed
       
        textFieldSerachOrderOrderID.setText("");
        comboBoxUpdateOrderSelectMenu1.setSelectedIndex(0);
        textFieldSerachOrderOrderID1.setText("");
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setCalendar(null);
        
        textFieldSerachOrderOrderID3.setText("");         
        tableSearchOrderTable = new javax.swing.JTable();
        comboBoxUpdateOrderSelectMenu1.setBackground(Color.white);

        tableSearchOrderTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
        ));

        tableSearchOrderTable.setGridColor(new java.awt.Color(51, 153, 255));
        scrollPaneSearchOrderResultsTable.setViewportView(tableSearchOrderTable);

    }//GEN-LAST:event_buttonSearchOrderResetActionPerformed
 
    public String[] getDetails() //to pass the selected details to the other outside references 
    {
        String []array = new String[20];        
        array[0] = Integer.toString(comboBoxAddOrderSelectMenu.getSelectedIndex());//data1
        //array[2] = textAreaAddOrderOrderDescription.getText();//data2
        array[3] = textFieldAddCustomerName.getText();//data3
        array[4] = countryCode.getText();//data4
        array[5] = textFieldAddCustomerEmail.getText();//data5      
        System.out.println(array);
        return array;
    }
    
    private void buttonAddOrderNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddOrderNextActionPerformed
        
        selectedMenu(comboBoxAddOrderSelectMenu.getSelectedIndex());
        int selectedindex = comboBoxAddOrderSelectMenu.getSelectedIndex();
        int selectedmenuID = selectedMenu(selectedindex);        
        boolean flag = true;
        
        //SAVING ORDER DATA
        try
        {
            for (int count = 0; count < tableOrderitems.getRowCount(); count++)
            {   
                String item = tableOrderitems.getValueAt(count, 0).toString();
                String type = tableOrderitems.getValueAt(count, 1).toString();
                String qty = tableOrderitems.getValueAt(count, 2).toString();
                
                if((qtyValidation(qty)) && (flag==true))
                {
                    data.add(item+"-"+type+"-"+qty+"-");
                }
                else 
                {
                    if(flag==false)
                    JOptionPane.showMessageDialog(null,"Invalid Quantity", "Warning",JOptionPane.WARNING_MESSAGE);                  
                }
            }
            
            orderdetails[0] = Integer.toString(selectedmenuID);
            System.out.println(data); 
            System.out.println("DATA : "+data.get(0));           
           
            //redirecting          
            customerpanelshift();
            tabbedPaneCustomer.setSelectedIndex(0);    
            tabbedPaneCustomer.setEnabledAt(0, true);
            customerStatus.setVisible(false);
            
        }
        catch(Exception e)
        {
                buttonAddOrderNext.setEnabled(false);
                JOptionPane.showMessageDialog(null,"Invalid Data","Error",JOptionPane.ERROR_MESSAGE);               
        }
        
    }//GEN-LAST:event_buttonAddOrderNextActionPerformed
            
    //int searched = 0;
    private void buttonSearchOrderSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchOrderSearchActionPerformed

        //performing search
        String orderid = textFieldSerachOrderOrderID.getText();        
        selectedMenu(comboBoxUpdateOrderSelectMenu1.getSelectedIndex());
        int selectedindex = comboBoxUpdateOrderSelectMenu1.getSelectedIndex();
        int selectedmenuID = selectedMenu(selectedindex);    
        String menuid = Integer.toString(selectedmenuID);        
        String nic = textFieldSerachOrderOrderID1.getText();
        String status = textFieldSerachOrderOrderID3.getText();
        
        //search by Order ID
        if((ovalidity))
        {
            String sql = "SELECT o.restaurant_order_id AS 'Order ID',o.restaurant_customer_nic AS 'Customer NIC',o.order_menu_id AS 'Order Menu ID',o.order_des AS 'Order Description',o.order_date AS 'Order Date' FROM restaurant_order o,restaurant_payment p WHERE o.restaurant_order_id = p.restaurant_payment_order_id AND o.restaurant_order_id = '"+orderid+"'";
            System.out.println(sql);
            try 
            {
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
                tableSearchOrderTable.setModel(DbUtils.resultSetToTableModel(rs));
                //searched++;
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Invalid Data!","Error",JOptionPane.ERROR_MESSAGE);
                
            }
        
        } 
        String datex = "";
        //order date validation
        try
        {
            String s = ((JTextField)orderDate.getDateEditor().getUiComponent()).getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            datex = sdf.format(orderDate.getDate());
        
        if(s.equals(""))
        {
            dvalidity = false;
        }
        else
        {
            dvalidity = true;
        }
        }
        catch(Exception e){}  
        
            if((dvalidity)||(cvalidity)||(mvalidity)||(svalidity))
            {
                                              
           String sql = "SELECT o.restaurant_order_id AS 'Order ID',o.restaurant_customer_nic AS 'Customer NIC',o.order_menu_id AS 'Order Menu ID',o.order_des AS 'Order Description',o.order_date AS 'Order Date',p.restaurant_payment_id AS 'Payment ID' FROM restaurant_order o,restaurant_payment p WHERE o.restaurant_order_id = p.restaurant_payment_order_id AND CAST(o.restaurant_order_id as CHAR) LIKE '%"+orderid+"%' AND CAST(o.restaurant_customer_nic as CHAR) LIKE '%"+nic+"%'"
                   + "AND CAST(o.order_menu_id as CHAR) LIKE '%"+menuid+"%' AND o.order_status LIKE '%"+status+"%' AND o.order_date LIKE '%"+datex+"%'";
            
           System.out.println(sql);
           try
           {
               pre = conn.prepareStatement(sql);
               rs= pre.executeQuery();
           }
           catch (SQLException ex)
           {
               System.out.println(ex);
           }
		
                tableSearchOrderTable.setModel(DbUtils.resultSetToTableModel(rs)); //loading the query results 
            } 
      
    }//GEN-LAST:event_buttonSearchOrderSearchActionPerformed

    private void buttonRemoveOrderSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveOrderSearchActionPerformed
        
        int count = 0;
        if(isInteger(textFieldRemoveOrderOrderID.getText()))
        {
            String sql = "SELECT restaurant_order_id AS 'Order ID',restaurant_customer_nic AS 'Customer NIC',order_menu_id AS 'Order Menu ID',order_des AS 'Order Description',order_date AS 'Order Date' FROM restaurant_order WHERE restaurant_order_id = '" + textFieldRemoveOrderOrderID.getText() + "'";
            
            try 
            {
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
                tableRemoveOrderResultsTable.setModel(DbUtils.resultSetToTableModel(rs));
                //if ((!rs.next()))
                //{
                  //  JOptionPane.showMessageDialog(null,"No Such Record!","Error",JOptionPane.ERROR_MESSAGE);                       
               // }
                //else
                //{
                   // while(rs.next())
                
                //}  
                
                //if(rs.next()==true)
                
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Invalid Data!","Error",JOptionPane.ERROR_MESSAGE);                
            }
           
        }
        else
        {
            textFieldRemoveOrderOrderID.setBackground(new Color(255, 150, 150));
            JOptionPane.showMessageDialog(null,"Invalid Data!","Error",JOptionPane.ERROR_MESSAGE);             
        }    
           
    }//GEN-LAST:event_buttonRemoveOrderSearchActionPerformed

    private void buttonPaymentHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPaymentHomeActionPerformed
         panelshift(buttonSearchCustomerHome.getText());
         buttonRestaurantOrder.setBackground(new Color(51,153,255));         
    }//GEN-LAST:event_buttonPaymentHomeActionPerformed

    private void buttonPaymentDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPaymentDoneActionPerformed

        if(updateorder == false) //new order

        {   
            selectedMenu(comboBoxAddOrderSelectMenu.getSelectedIndex());
            int selectedindex = comboBoxAddOrderSelectMenu.getSelectedIndex();
            int selectedmenuID = selectedMenu(selectedindex);    
            String menuid = Integer.toString(selectedmenuID);
            String order = textAreaPaymentOrderDescription.getText();
            int status = 1;
            String type ="";
            String amt = textFieldPaymentAmount.getText();
            int cashOrCredit = comboBoxPaymentPaymentType.getSelectedIndex();
            String cashflowm = "";
            if(cashOrCredit==0)
            {
                type = "Cash";
                cashflowm = "Money";
            }
            else 
            {
                type = "Credit Card";
                cashflowm = "Bank";
            }             
                       
            //adding data to database
            String day = year + "-" + month + "-" + date ;
            String sql1 = "INSERT INTO restaurant_customer VALUES ('"+orderdetails[5]+"','"+orderdetails[1]+"','"+orderdetails[2]+"','"+orderdetails[3]+"')";
            String sql2 = "INSERT INTO restaurant_order (restaurant_customer_nic,order_menu_id,order_des,order_date,order_status) VALUES ('"+orderdetails[5]+"','"+menuid+"','"+order+"','"+day+"','"+status+"')";
            String sql3 = "SELECT order_menu_id FROM restaurant_order WHERE restaurant_customer_nic  = '"+orderdetails[5]+"'";
            String sql4 = "SELECT order_date FROM restaurant_order WHERE restaurant_customer_nic  = '"+orderdetails[5]+"'";
            String sql5 = "SELECT order_des FROM restaurant_order WHERE restaurant_customer_nic  = '"+orderdetails[5]+"'";
            String sql0 = "INSERT INTO fin_cashflow (cashflow_date,cashflow_description,cashflow_department,cashflow_method,cashflow_amount,cashflow_payment_type,cashflow_payment_status,cashflow_approval) VALUES ('"+day+"','Bill Payment','Restaurant','"+cashflowm+"','"+amt+"','Income','Credit','Not Approved')";
            
            String omi = "",od = "",odes = "";
            int id= 0;
            try 
                {
                    System.out.println("CUS "+cusA);
                    if(cusA==false) //customer not in DB already
                    {pre = conn.prepareStatement(sql1);
                    pre.execute();}
                    pre = conn.prepareStatement(sql2);
                    pre.execute();
                            
                    pre = conn.prepareStatement(sql3);
                    rs = pre.executeQuery();                    
                    while(rs.next())
                    omi = rs.getString("order_menu_id");
                    System.out.println(omi);
                    pre = conn.prepareStatement(sql4);
                    rs = pre.executeQuery();
                    while(rs.next())
                    od = rs.getString("order_date");
                    System.out.println(od);
                    pre = conn.prepareStatement(sql5);
                    rs = pre.executeQuery();
                    while(rs.next())
                    odes = rs.getString("order_des"); 
                    System.out.println(odes);
                    
                    String sql6 = "SELECT * FROM restaurant_order WHERE restaurant_customer_nic  = '"+orderdetails[5]+"' AND order_menu_id = '"+omi+"' AND order_date = '"+od+"' AND order_des = '"+odes+"'";
                    pre = conn.prepareStatement(sql6);                   
                    rs = pre.executeQuery();
                    while(rs.next())
                    {
                        id = rs.getInt("restaurant_order_id");
                        System.out.println(id);                   
                    }
                    
                    String sql7 = "INSERT INTO restaurant_payment (restaurant_payment_cashier_id,restaurant_payment_customer_nic,restaurant_payment_order_id,restaurant_payment_amount,restaurant_payment_credit_or_cash) VALUES ('210014','"+orderdetails[5]+"','"+id+"','"+amt+"','"+type+"')";
                    pre = conn.prepareStatement(sql7);
                    pre.execute();
                    pre = conn.prepareStatement(sql0);
                    pre.execute();                    
                    
                    JOptionPane.showMessageDialog(null,"Order Added","Success",JOptionPane.INFORMATION_MESSAGE);
                
                //go to welcome screen
                panelWelcome.setVisible(true);
                panelRestaurantOrder.setVisible(false);
                panelCustomer.setVisible(false);
                panelPayment.setVisible(false);
                panelFoodMenu.setVisible(false);
                panelOtherOrders.setVisible(false);
                panelEmployee.setVisible(false);
                panelStockOrder.setVisible(false);
                panelMaintenance.setVisible(false);
                panelReports.setVisible(false);
                panelUsers.setVisible(false);
                
                buttonRestaurantOrder.setBackground(new Color(51,153,255));
                
                //clear all previous details
                textAreaPaymentOrderDescription.setText("");
                comboBoxPaymentPaymentType.setSelectedIndex(0);
                textFieldPaymentAmount.setText("");
                textFieldPaymentPaidAmount.setText("");
                textFieldPaymentBalance.setText("");
                labelMenuID.setText("");
                
                textFieldAddCustomerNIC.setText("");
                textFieldAddCustomerName.setText("");
                countryCode.setText("");
                textFieldAddCustomerTelephone1.setText("");
                textFieldAddCustomerTelephone2.setText("");
                textFieldAddCustomerTelephone3.setText("");
                textFieldAddCustomerEmail.setText("");
                customerStatus.setVisible(false);
               
                comboBoxAddOrderSelectMenu.setSelectedIndex(0);
                listAddOrderItems1.removeAll();
                DefaultTableModel model = (DefaultTableModel)tableOrderitems.getModel();
                while(model.getRowCount() > 0)
                {
                    for (int i = 0;i< model.getRowCount(); i++)
                    {
                        model.removeRow(i)  ;  
                    }   
                }
                
                } 
                catch (SQLException ex)
                {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null,"Error Adding Order!","Error",JOptionPane.ERROR_MESSAGE);
                }
        }
        
         //updates
         if(updateorder == true) 
         {
            String newmenu = labelMenuID.getText();
            String order = textAreaPaymentOrderDescription.getText();
            //int status = 1;
            String type ="";
            String amt = textFieldPaymentAmount.getText();
            int cashOrCredit = comboBoxPaymentPaymentType.getSelectedIndex();
            if(cashOrCredit==1)
            {type = "Cash";}
            else 
            {type = "Credit Card";}  
            
            //update records
            String sql8 = "UPDATE restaurant_order SET order_des = '"+order+"',order_menu_id = '"+newmenu+"'";
            String sql9 = "UPDATE restaurant_payment SET restaurant_payment_amount = '"+amt+"',restaurant_payment_credit_or_cash = '"+type+"'";
            try
            {
                pre = conn.prepareStatement(sql8);
                pre.execute();  
                pre = conn.prepareStatement(sql9);
                pre.execute(); 
                JOptionPane.showMessageDialog(null,"Order Updated","Success",JOptionPane.INFORMATION_MESSAGE);
                
                //go to welcome screen
                panelWelcome.setVisible(true);
                panelRestaurantOrder.setVisible(false);
                panelCustomer.setVisible(false);
                panelPayment.setVisible(false);
                panelFoodMenu.setVisible(false);
                panelOtherOrders.setVisible(false);
                panelEmployee.setVisible(false);
                panelStockOrder.setVisible(false);
                panelMaintenance.setVisible(false);
                panelReports.setVisible(false);
                panelUsers.setVisible(false);
                
                buttonRestaurantOrder.setBackground(new Color(51,153,255));
                
                //clear all previous details
                textAreaPaymentOrderDescription.setText("");
                comboBoxPaymentPaymentType.setSelectedIndex(0);
                textFieldPaymentAmount.setText("");
                textFieldPaymentPaidAmount.setText("");
                textFieldPaymentBalance.setText("");
                labelMenuID.setText("");
                buttonUpdateOrderReset.doClick();                
                
            }
            catch(Exception e)
            {
                System.out.println(e);
                JOptionPane.showMessageDialog(null,"Error Updating Order!","Error",JOptionPane.ERROR_MESSAGE);
            } 
         
         }
        
    }//GEN-LAST:event_buttonPaymentDoneActionPerformed

    private void buttonFoodMenuResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodMenuResetActionPerformed
        
        
        String sql = "SELECT food_id AS 'Food ID', food_name AS 'Food Name', food_price AS 'Food Price' FROM food_item WHERE food_item_status = '1'";
            
        try 
        {
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            foodItems.setModel(DbUtils.resultSetToTableModel(rs));
           
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"Error Retrieving Data!","Error",JOptionPane.ERROR_MESSAGE);                
        }
        
        try
        {
            DefaultListModel listModel = (DefaultListModel) jList2.getModel();
            listModel.removeAllElements();
        }
        catch(Exception e)
        {}
    }//GEN-LAST:event_buttonFoodMenuResetActionPerformed

    private void buttonDemoReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDemoReportsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonDemoReportsActionPerformed

    private void buttonResetReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetReportsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonResetReportsActionPerformed

    private void buttonPrintReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintReportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonPrintReportActionPerformed

    private void buttonGenerateReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerateReportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonGenerateReportActionPerformed

    private void buttonHomeReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonHomeReportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonHomeReportActionPerformed

    private void buttonRemoveMenuResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveMenuResetActionPerformed
        textFieldRemoveMenuMenuID.setText("");
        textFieldRemoveMenuMenuID.setBackground(Color.white);
        tableRemoveMenuResultsTable = new javax.swing.JTable();

        tableRemoveMenuResultsTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
        
        },
        new String [] {
      
        }
        ));

    scrollPaneRemoveMenuResultsTable.setViewportView(tableRemoveMenuResultsTable);

    }//GEN-LAST:event_buttonRemoveMenuResetActionPerformed

    private void buttonRemoveMenuDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveMenuDemoActionPerformed
       buttonRemoveMenuReset.doClick();
       textFieldRemoveMenuMenuID.setBackground(Color.white);
       textFieldRemoveMenuMenuID.setText("550002");
       
    }//GEN-LAST:event_buttonRemoveMenuDemoActionPerformed

    private void comboBoxPaymentPaymentTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxPaymentPaymentTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxPaymentPaymentTypeActionPerformed

    private void textFieldPaymentAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldPaymentAmountActionPerformed
        
    }//GEN-LAST:event_textFieldPaymentAmountActionPerformed

    private void buttonPaymentPrintBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPaymentPrintBillActionPerformed
       
    }//GEN-LAST:event_buttonPaymentPrintBillActionPerformed

    private void textFieldFoodMenuMenuPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldFoodMenuMenuPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldFoodMenuMenuPriceActionPerformed

    private void buttonFoodMenuHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodMenuHomeActionPerformed
        int index = jList2.getSelectedIndex();
        try
        {
            DefaultListModel model = (DefaultListModel) jList2.getModel();
             model.remove(index);
        }
       catch(Exception e)
       {}
    }//GEN-LAST:event_buttonFoodMenuHomeActionPerformed

    private void buttonFoodMenuAddMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodMenuAddMenuActionPerformed
        String price = textFieldFoodMenuMenuPrice.getText();
        List<String> datac = new ArrayList<String>();
        String menudes = "";
    try{    
        
        if(isDoubleValid(price) && (((textFieldFoodMenuMenuPrice.getText()).length())==6))
        {
            
            ListModel model = jList2.getModel();
            for(int i=0; i < model.getSize(); i++)
            {
             Object o =  model.getElementAt(i); 
             datac.add(o+"");
            }
            System.out.println(datac);
            
           for(int i = 0 ;i<datac.size();i++)
           jTextArea1.append(datac.get(i)); 
           //menudes.concat(datac.get(i)+",");
           menudes = jTextArea1.getText();
           
           String sql = "INSERT INTO menu (menu_des,menu_price,menu_status) VALUES ('"+menudes+"','"+price+"','1')";
        
           try 
            {
                pre = conn.prepareStatement(sql);
                pre.execute();
                JOptionPane.showMessageDialog(null,"Menu Added!","Success",JOptionPane.INFORMATION_MESSAGE);                
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Menu Addition Failed!","Error",JOptionPane.ERROR_MESSAGE);                
            }
          
        } 
       
    }
    catch(Exception e)
    {
        textFieldFoodMenuMenuPrice.setBackground(new Color(255, 150, 150));
        JOptionPane.showMessageDialog(null,"Invalid Data!","Error",JOptionPane.ERROR_MESSAGE);  
    }
    }//GEN-LAST:event_buttonFoodMenuAddMenuActionPerformed

    private void buttonRemoveMenuHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveMenuHomeActionPerformed
        buttonRemoveMenuReset.doClick();
        //redirecting to to welcome
        panelWelcome.setVisible(true);
        panelRestaurantOrder.setVisible(false);
        panelCustomer.setVisible(false);                            
        panelPayment.setVisible(false);
        panelFoodMenu.setVisible(false);
        panelOtherOrders.setVisible(false);
        panelStockOrder.setVisible(false);
        panelMaintenance.setVisible(false);
        panelReports.setVisible(false);
        panelUsers.setVisible(false);      
        buttonFoodMenu.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_buttonRemoveMenuHomeActionPerformed

    private void textFieldRemoveMenuMenuIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldRemoveMenuMenuIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldRemoveMenuMenuIDActionPerformed

    private void buttonRemoveMenuSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveMenuSearchActionPerformed
        
        String id = textFieldRemoveMenuMenuID.getText();
        if(isInteger(id))
        {
            String sql = "SELECT menu_des AS 'Menu Description', menu_price AS 'Menu Price', menu_status AS 'Menu Status' FROM menu WHERE menu_id = '"+id+"'";
        
            try
            { 
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
                 
                tableRemoveMenuResultsTable.setModel(DbUtils.resultSetToTableModel(rs));  
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"Error Retrieving Data!","Error",JOptionPane.ERROR_MESSAGE);            
            }            
            
        }
        else
        {
           textFieldRemoveMenuMenuID.setBackground(new Color(255, 150, 150));
           JOptionPane.showMessageDialog(null,"Invalid Menu ID!","Error",JOptionPane.ERROR_MESSAGE);        
        }
    }//GEN-LAST:event_buttonRemoveMenuSearchActionPerformed

    private void buttonRemoveMenuRemoveMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRemoveMenuRemoveMenuActionPerformed
    
    String id = textFieldRemoveMenuMenuID.getText();
    
    if(id.equals(""))
    {
         JOptionPane.showMessageDialog(null,"Invalid Data", "Warning",JOptionPane.WARNING_MESSAGE); 
    }    
    else if(isInteger(id))
    {
    
    int n = JOptionPane.showConfirmDialog(null,"Are you sure?","Confirm Remove",JOptionPane.YES_NO_OPTION);
            
    if(n ==0)
    { 
        String sql = "UPDATE menu SET menu_status = '0' WHERE menu_id = '"+id+"'";
        try
        {
            pre = conn.prepareStatement(sql);
            pre.execute();
            JOptionPane.showMessageDialog(null,"Success","Success",JOptionPane.INFORMATION_MESSAGE); 
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Data Retrieval Error!","Error",JOptionPane.ERROR_MESSAGE);  
        }    
        
        
    }
    else
    {
        textFieldRemoveMenuMenuID.setBackground(new Color(255, 150, 150));
        JOptionPane.showMessageDialog(null,"Update Cancelled","Warning",JOptionPane.WARNING_MESSAGE);  
    
    }    
    
    
    
    }
    }//GEN-LAST:event_buttonRemoveMenuRemoveMenuActionPerformed

    private void buttonViewMenuResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewMenuResetActionPerformed
        textFieldViewMenuMenuID.setText("");
        textFieldViewMenuMenuID.setBackground(Color.white);
        textFieldViewMenuMenuID1.setText("");
        textFieldViewMenuMenuID2.setText("");      
        try
        {
            DefaultListModel listModel = (DefaultListModel) jList1.getModel();
            listModel.removeAllElements();
        }
        catch(Exception e)
        {}
    }//GEN-LAST:event_buttonViewMenuResetActionPerformed

    private void buttonViewMenuDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewMenuDemoActionPerformed
       buttonViewMenuReset.doClick();
        textFieldViewMenuMenuID.setText("550002");
    }//GEN-LAST:event_buttonViewMenuDemoActionPerformed

    private void buttonViewMenuSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewMenuSearchActionPerformed
       String id = textFieldViewMenuMenuID.getText();
      
       //if valid
       if(isInteger(id))  
       {
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) jList1.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);  
        String orderdes = ""; //compile error prevention
        String x = "";
        //showing menu items on list         
        String sql = "SELECT menu_des, menu_price, menu_status FROM menu WHERE menu_id = '" + id + "'";
        
        System.out.println(sql);
        try 
        {
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            
            DefaultListModel model = new DefaultListModel();
            jList1.setModel(model);
                   
            while(rs.next())
            {
               orderdes = rs.getString("menu_des");
               StringTokenizer tk1 = new StringTokenizer(orderdes,",");               
             
               while(tk1.hasMoreTokens())
               {
                    model.addElement(tk1.nextToken()+"\n");       
               }
               
            x = rs.getString("menu_price");
            System.out.println(x);
            textFieldViewMenuMenuID2.setText(x);           
            
            String st = "";
            if(rs.getString("menu_status").equals("1")){st = "Available";}
            else{ st = "Not Available";}
            textFieldViewMenuMenuID1.setText(st);
               
            }
            
            
            
        }            
        catch (Exception ex) 
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"Error Retrieving Data!","Error",JOptionPane.ERROR_MESSAGE); 
        }
           
           
           
       } 
       else
       {
           textFieldViewMenuMenuID.setBackground(new Color(255, 150, 150));
           JOptionPane.showMessageDialog(null,"Invalid Menu ID!","Error",JOptionPane.ERROR_MESSAGE);
       }    
          
    }//GEN-LAST:event_buttonViewMenuSearchActionPerformed

    private void buttonViewMenuHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewMenuHomeActionPerformed
        buttonViewMenuReset.doClick();
        //redirecting to to welcome
        panelWelcome.setVisible(true);
        panelRestaurantOrder.setVisible(false);
        panelCustomer.setVisible(false);                            
        panelPayment.setVisible(false);
        panelFoodMenu.setVisible(false);
        panelOtherOrders.setVisible(false);
        panelStockOrder.setVisible(false);
        panelMaintenance.setVisible(false);
        panelReports.setVisible(false);
        panelUsers.setVisible(false);      
        buttonFoodMenu.setBackground(new Color(51,153,255));
        
    }//GEN-LAST:event_buttonViewMenuHomeActionPerformed

    private void buttonEmployeeResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeeResetActionPerformed
        
        textFieldEmployeeID.setText("");
        textFieldEmployeeID.setBackground(Color.white);
        
        String sql = "SELECT * FROM hr_employee WHERE employee_department = 'Restaurant'";  
        try
        {
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();            
            tableEmployeeResultsTable.setModel(DbUtils.resultSetToTableModel(rs));            
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"Error Retrieving Data!","Error",JOptionPane.ERROR_MESSAGE);            
        }
        
    }//GEN-LAST:event_buttonEmployeeResetActionPerformed

    private void buttonEmployeeDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeeDemoActionPerformed
            textFieldEmployeeID.setText("210012");       
    }//GEN-LAST:event_buttonEmployeeDemoActionPerformed

    private void buttonEmployeeNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeeNextActionPerformed
        panelshift(buttonRemoveOrderHome.getText());      
    }//GEN-LAST:event_buttonEmployeeNextActionPerformed

    private void textFieldEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldEmployeeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldEmployeeIDActionPerformed

    private void buttonEmployeeSearchSingleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeeSearchSingleActionPerformed
       
        String eid =textFieldEmployeeID.getText();
        int len = eid.length();
        
        String sql = "SELECT * FROM hr_employee WHERE employee_id = '"+eid+"' AND employee_department = 'Restaurant'";  
            try 
            {
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
                tableEmployeeResultsTable.setModel(DbUtils.resultSetToTableModel(rs));
            } 
            catch(SQLException ex) 
            {
                System.out.println(ex);
            }
        
        
    }//GEN-LAST:event_buttonEmployeeSearchSingleActionPerformed

    private void buttonEmployeePrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeePrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonEmployeePrintActionPerformed

    private void buttonAddCustomerHome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerHome1ActionPerformed
        roomOrderID.setText("");    
        String sql = "SELECT * FROM room_roomserviceorders";
        System.out.println(sql);
        try 
        {
            pre = conn.prepareStatement(sql);
            rs= pre.executeQuery();
            roomOrders.setModel(DbUtils.resultSetToTableModel(rs)); //loading the query results */
        } 
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
	                      
        
    }//GEN-LAST:event_buttonAddCustomerHome1ActionPerformed

    private void buttonAddCustomerNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerNext1ActionPerformed
       String rmsid = roomOrderID.getText();
        //validating the id       
        if(isInteger(rmsid))
        {       
            //checking for the empty field is done inside isInteger method
           
                String sql = "SELECT * FROM room_roomserviceorders WHERE room_roomserviceorders_rsid = '" + rmsid + "'";
                System.out.println(sql);
                try {
                        pre = conn.prepareStatement(sql);
                        rs= pre.executeQuery();
                        roomOrders.setModel(DbUtils.resultSetToTableModel(rs)); //loading the query results */
            
                } catch (SQLException ex)
                {
                       System.out.println(ex);
                }      
        } 
        else //id orderid is not an integer
        {
            roomOrderID.setBackground(new Color(255, 150, 150));
            JOptionPane.showMessageDialog(null,"Invalid ID!","Error",JOptionPane.ERROR_MESSAGE);
        }    
    }//GEN-LAST:event_buttonAddCustomerNext1ActionPerformed

    private void buttonAddCustomerReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerReset1ActionPerformed
        roomOrderID.setText("");
        roomOrderID.setBackground(Color.white);
        Calendar now = Calendar.getInstance();
        int second=now.get(Calendar.SECOND);
        int min=now.get(Calendar.MINUTE);
        int hr=now.get(Calendar.HOUR_OF_DAY);
        String t = String.format("%02d:%02d:%02d", hr, min, second);
        
        String sql = "SELECT * FROM room_roomserviceorders WHERE room_roomserviceorders_date = '"+t+"' AND room_restaurant_status = '1'";  
        try
        {
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            
            roomOrders.setModel(DbUtils.resultSetToTableModel(rs));            
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"Error Retrieving Data!","Error",JOptionPane.ERROR_MESSAGE);            
        }
    }//GEN-LAST:event_buttonAddCustomerReset1ActionPerformed

    private void buttonAddCustomerNext2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerNext2ActionPerformed
        
        MessageFormat header = new  MessageFormat("Room Order Details Report");
        MessageFormat footer = new  MessageFormat("page{0,number,integer}");
        
        try
        {
           roomOrders.print(JTable.PrintMode.NORMAL, header, footer);
            
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
        
    }//GEN-LAST:event_buttonAddCustomerNext2ActionPerformed

    private void buttonAddCustomerNext4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerNext4ActionPerformed
        panelshift(buttonRemoveOrderHome.getText());
    }//GEN-LAST:event_buttonAddCustomerNext4ActionPerformed

    private void buttonAddCustomerReset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerReset2ActionPerformed
        roomOrderID1.setText("");        
        roomOrderID1.setBackground(Color.white);
              
        String sql = "SELECT * FROM event_order WHERE order_status = '1'";  
        try
        {
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            
            eventOrders.setModel(DbUtils.resultSetToTableModel(rs));            
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"Error Retrieving Data!","Error",JOptionPane.ERROR_MESSAGE);            
        }
    }//GEN-LAST:event_buttonAddCustomerReset2ActionPerformed

    private void buttonAddCustomerNext6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerNext6ActionPerformed
        panelshift(buttonRemoveOrderHome.getText());
    }//GEN-LAST:event_buttonAddCustomerNext6ActionPerformed

    private void buttonAddCustomerNext7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerNext7ActionPerformed
        MessageFormat header = new  MessageFormat("Event Order Details Report");
        MessageFormat footer = new  MessageFormat("page{0,number,integer}");
        
        try
        {
           roomOrders.print(JTable.PrintMode.NORMAL, header, footer);
            
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }//GEN-LAST:event_buttonAddCustomerNext7ActionPerformed

    private void buttonAddCustomerHome3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerHome3ActionPerformed
       
        String evid = roomOrderID1.getText();
        //validating the id       
        if(isInteger(evid))
        {       
                String sql = "SELECT * FROM event_order WHERE order_event_id = '" + evid + "'";
                System.out.println(sql);
                try {
                        pre = conn.prepareStatement(sql);
                        rs= pre.executeQuery();
                        eventOrders.setModel(DbUtils.resultSetToTableModel(rs)); //loading the query results */
            
                } catch (SQLException ex)
                {
                       System.out.println(ex);
                }
		
                
                               
        } 
        else //id orderid is not an integer
        {
            roomOrderID1.setBackground(new Color(255, 150, 150));
            JOptionPane.showMessageDialog(null,"Invalid ID!","Error",JOptionPane.ERROR_MESSAGE);
        }  
        
    }//GEN-LAST:event_buttonAddCustomerHome3ActionPerformed

    private void buttonPaymentBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPaymentBackActionPerformed
        //redirecting back to Add Customer from Payment
        panelWelcome.setVisible(false);
        panelRestaurantOrder.setVisible(false);
        panelCustomer.setVisible(true);
        panelCustomerContent.setVisible(false);
        tabbedPaneCustomer.setSelectedIndex(0);    
        tabbedPaneCustomer.setEnabledAt(0, true);
        tabbedPaneCustomer.setEnabledAt(1, false);
        tabbedPaneCustomer.setEnabledAt(2, false);        
        panelPayment.setVisible(false);
        panelFoodMenu.setVisible(false);
        panelOtherOrders.setVisible(false);
        panelStockOrder.setVisible(false);
        panelMaintenance.setVisible(false);
        panelReports.setVisible(false);
        panelUsers.setVisible(false);
        
        //keep the filled data
        //fill the details of the Cistomer back
        //HAVE A SEPERATE METHOD FOR STORING THE PASSED VALUES 

    }//GEN-LAST:event_buttonPaymentBackActionPerformed

    
    public void restaurantpanelshift()
    {
        panelshift(buttonRestaurantOrder.getText());
        panelRestaurantContent.setVisible(false);
        tabbedPaneRestaurantOrder.setVisible(true);  
        tabbedPaneRestaurantOrder.setEnabledAt(0, false);
        tabbedPaneRestaurantOrder.setEnabledAt(1, false);
        tabbedPaneRestaurantOrder.setEnabledAt(2, false);
        tabbedPaneRestaurantOrder.setEnabledAt(3, false);       
    }    
     
    public void foodmenupanelshift()
    {
        panelshift(buttonFoodMenu.getText());
        panelFoodMenuContent.setVisible(false);
        tabbedPaneFoodMenu.setVisible(true);  
       
    
    
    
    }

    
    private void buttonRestaurantContentAddOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRestaurantContentAddOrderActionPerformed
        restaurantpanelshift();
        tabbedPaneRestaurantOrder.setSelectedIndex(0);    
        tabbedPaneRestaurantOrder.setEnabledAt(0, true);   
    }//GEN-LAST:event_buttonRestaurantContentAddOrderActionPerformed

    private void buttonRestaurantContentUpdateOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRestaurantContentUpdateOrderActionPerformed
        restaurantpanelshift();
        tabbedPaneRestaurantOrder.setSelectedIndex(1);
        tabbedPaneRestaurantOrder.setEnabledAt(1, true); 
        comboBoxUpdateOrderSelectMenu.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_buttonRestaurantContentUpdateOrderActionPerformed

    private void buttonRestaurantContentRemoveOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRestaurantContentRemoveOrderActionPerformed
        restaurantpanelshift();
        tabbedPaneRestaurantOrder.setSelectedIndex(2);
        tabbedPaneRestaurantOrder.setEnabledAt(2, true);        
    }//GEN-LAST:event_buttonRestaurantContentRemoveOrderActionPerformed

    private void buttonRestaurantContentSearchOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRestaurantContentSearchOrderActionPerformed
        
        restaurantpanelshift();
        tabbedPaneRestaurantOrder.setSelectedIndex(3);
        tabbedPaneRestaurantOrder.setEnabledAt(3, true);
        comboBoxUpdateOrderSelectMenu1.setBackground(Color.white);
    }//GEN-LAST:event_buttonRestaurantContentSearchOrderActionPerformed

     public void customerpanelshift()
    {
        panelshift(buttonCustomer.getText());
        panelCustomerContent.setVisible(false);
        tabbedPaneCustomer.setVisible(true);  
        tabbedPaneCustomer.setEnabledAt(0, false);
        tabbedPaneCustomer.setEnabledAt(1, false);
        tabbedPaneCustomer.setEnabledAt(2, false);
    }   
        
    private void buttonCustomerContentAddCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustomerContentAddCustomerActionPerformed
        customerpanelshift();
        tabbedPaneCustomer.setSelectedIndex(0);    
        tabbedPaneCustomer.setEnabledAt(0, true); 
    }//GEN-LAST:event_buttonCustomerContentAddCustomerActionPerformed

    private void buttonCustomerContentSearchCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustomerContentSearchCustomerActionPerformed
        customerpanelshift();
        tabbedPaneCustomer.setSelectedIndex(1);    
        tabbedPaneCustomer.setEnabledAt(1, true);  
    }//GEN-LAST:event_buttonCustomerContentSearchCustomerActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jTextField4.setText("");
        jTextField5.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed
       
    private void buttonAddOrderAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddOrderAddActionPerformed

     
        if(flaglist == true)
        { 
            String item = listAddOrderItems1.getSelectedValue();
            DefaultTableModel model = (DefaultTableModel)tableOrderitems.getModel();       
            model.addRow(new Object[]{item,"Choose Type"});
        }   
        
    }//GEN-LAST:event_buttonAddOrderAddActionPerformed
           
    private void buttonAddOrderRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddOrderRemoveActionPerformed
        
        //removing items from the order items list
        try
        {
            int row = tableOrderitems.getSelectedRow();        
            String toberemoved = tableOrderitems.getValueAt(row, 0).toString();
            DefaultTableModel model = (DefaultTableModel)tableOrderitems.getModel();
            model.removeRow(row);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Row not selected!","Error",JOptionPane.ERROR_MESSAGE);        
        }
        
    }//GEN-LAST:event_buttonAddOrderRemoveActionPerformed

    boolean flaglist = false; //selected one from list or not
    private void listAddOrderItems1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listAddOrderItems1MouseClicked
        
        selectedMenu(comboBoxAddOrderSelectMenu.getSelectedIndex());
        int selectedindex = comboBoxAddOrderSelectMenu.getSelectedIndex();
        int selectedmenuID = selectedMenu(selectedindex);     
        if(selectedmenuID==0)
        { 
            comboBoxAddOrderSelectMenu.setBackground(new Color(255, 150, 150));
            JOptionPane.showMessageDialog(null,"Menu ID not provided!","Error",JOptionPane.ERROR_MESSAGE);
        } 
         if(selectedmenuID>0)
        { 
            comboBoxAddOrderSelectMenu.setBackground(new Color(255, 255, 255));
            flaglist = true;            
        } 
    }//GEN-LAST:event_listAddOrderItems1MouseClicked

    private void listAddOrderItems2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listAddOrderItems2MouseClicked
        //selectedMenu(comboBoxUpdateOrderSelectMenu.getSelectedIndex());
        boolean flag = updateordervalidations();
    }//GEN-LAST:event_listAddOrderItems2MouseClicked

    StringBuffer s1 = new StringBuffer(""); 
    private void buttonAddOrderNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddOrderNext1ActionPerformed
       
        //redirecting to to welcome
        panelWelcome.setVisible(true);
        panelRestaurantOrder.setVisible(false);
        panelCustomer.setVisible(false);                            
        panelPayment.setVisible(false);
        panelFoodMenu.setVisible(false);
        panelOtherOrders.setVisible(false);
        panelStockOrder.setVisible(false);
        panelMaintenance.setVisible(false);
        panelReports.setVisible(false);
        panelUsers.setVisible(false);
        comboBoxAddOrderSelectMenu.setSelectedIndex(0);        
        buttonRestaurantOrder.setBackground(new Color(51,153,255));
        comboBoxAddOrderSelectMenu.setBackground(new Color(255, 255, 255)); 
        buttonUpdateOrderReset.doClick();
        buttonAddOrderReset.doClick();
        buttonRemoveOrderReset.doClick();
        buttonSearchOrderReset.doClick();
        
    }//GEN-LAST:event_buttonAddOrderNext1ActionPerformed
    String removedetails[] = new String[5];
    private void buttonReportsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonReportsMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonReportsMouseEntered

    private void buttonReportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonReportsMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonReportsMouseExited

    private void buttonReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReportsActionPerformed
       panelshift(buttonReports.getText());                                             
    }//GEN-LAST:event_buttonReportsActionPerformed

    private void textFieldPaymentAmountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldPaymentAmountMouseExited

    }//GEN-LAST:event_textFieldPaymentAmountMouseExited

    private void tableOrderitemsComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tableOrderitemsComponentShown
        comboBox.setSelectedIndex(1); 
    }//GEN-LAST:event_tableOrderitemsComponentShown

    private void textFieldEmployeeID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldEmployeeID1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldEmployeeID1ActionPerformed

    private void buttonEmployeeSearchAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeeSearchAll1ActionPerformed

        String un = textFieldEmployeeID1.getText(); 
        tableEmployeeResultsTable1 = new javax.swing.JTable();
        tableEmployeeResultsTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

         },
        new String [] {

        }
        ));

        tableEmployeeResultsTable1.setGridColor(new java.awt.Color(51, 153, 255));
        scrollPaneEmployeeResultsTable1.setViewportView(tableEmployeeResultsTable1);

              
              
                String sql = "SELECT system_user_id AS 'User ID', employee_id AS 'Employee ID', full_name AS 'Full Name', username as 'Username',password as 'Password',last_updated_date AS 'Last Updated Date', user_availability AS 'User Availability' FROM system_user WHERE department = 'Restaurant' AND username = '"+un+"'";
                System.out.println(sql);
                try {
                        pre = conn.prepareStatement(sql);
                        rs= pre.executeQuery();
                        tableEmployeeResultsTable1.setModel(DbUtils.resultSetToTableModel(rs)); //loading the query results */
                                                   
                } 
                catch (SQLException ex)
                {
                       System.out.println(ex);
                       JOptionPane.showMessageDialog(null,"Data Retrieval Erroor!","Error",JOptionPane.ERROR_MESSAGE);
                }	
                     
        
    }//GEN-LAST:event_buttonEmployeeSearchAll1ActionPerformed

    private void buttonEmployeeNext1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeeNext1ActionPerformed
               
        //redirecting to to welcome
        panelWelcome.setVisible(true);
        panelRestaurantOrder.setVisible(false);
        panelCustomer.setVisible(false);                            
        panelPayment.setVisible(false);
        panelFoodMenu.setVisible(false);
        panelOtherOrders.setVisible(false);
        panelStockOrder.setVisible(false);
        panelMaintenance.setVisible(false);
        panelReports.setVisible(false);
        panelUsers.setVisible(false);
        buttonEmployeeReset1.doClick();     
        buttonUsers.setBackground(new Color(51,153,255));
        
        
    }//GEN-LAST:event_buttonEmployeeNext1ActionPerformed

    private void buttonEmployeeDemo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeeDemo1ActionPerformed
       buttonEmployeeReset1.doClick();
        textFieldEmployeeID1.setText("Uthpala");
       
    }//GEN-LAST:event_buttonEmployeeDemo1ActionPerformed

    private void buttonEmployeeReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEmployeeReset1ActionPerformed
       
    tableEmployeeResultsTable1 = new javax.swing.JTable();

    tableEmployeeResultsTable1.setModel(new javax.swing.table.DefaultTableModel(
    new Object [][] {

    },
    new String [] {

    }
    ));

    tableEmployeeResultsTable1.setGridColor(new java.awt.Color(51, 153, 255));
    scrollPaneEmployeeResultsTable1.setViewportView(tableEmployeeResultsTable1);
    textFieldEmployeeID1.setText("");


    }//GEN-LAST:event_buttonEmployeeReset1ActionPerformed

    private void buttonPaymentDoneMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonPaymentDoneMouseEntered
        
    }//GEN-LAST:event_buttonPaymentDoneMouseEntered

    private void textFieldPaymentPaidAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldPaymentPaidAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldPaymentPaidAmountActionPerformed

    private void textFieldPaymentPaidAmountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldPaymentPaidAmountMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldPaymentPaidAmountMouseEntered

    private void textFieldPaymentBalanceMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldPaymentBalanceMouseEntered
       
    }//GEN-LAST:event_textFieldPaymentBalanceMouseEntered

    private void textFieldPaymentPaidAmountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldPaymentPaidAmountMouseExited
       
    }//GEN-LAST:event_textFieldPaymentPaidAmountMouseExited

    private void buttonRestaurantOrderMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonRestaurantOrderMouseExited
        
        if(clicked1==0)buttonRestaurantOrder.setBackground(new Color(51,153,255));
    }//GEN-LAST:event_buttonRestaurantOrderMouseExited

    private void buttonRestaurantOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonRestaurantOrderMouseClicked
        clicked1++;
        buttonRestaurantOrder.setBackground(new Color(0,102,204));
        changeButtonColors("clicked1");
        clicked2=0; clicked3=0; clicked4=0; clicked5=0; clicked6=0; clicked7=0;clicked8=0;clicked9=0;
       
    }//GEN-LAST:event_buttonRestaurantOrderMouseClicked

    private void panelRestaurantOrderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRestaurantOrderMouseEntered
        //buttonRestaurantOrder.setBackground(new Color(0,102,204));
    }//GEN-LAST:event_panelRestaurantOrderMouseEntered

    private void panelCustomerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelCustomerMouseEntered
        //buttonRestaurantOrder.setBackground(new Color(0,102,204));
    }//GEN-LAST:event_panelCustomerMouseEntered

    private void buttonCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCustomerMouseClicked
        clicked2++;
        buttonCustomer.setBackground(new Color(0,102,204));
        changeButtonColors("clicked2");
        clicked1=0; clicked3=0; clicked4=0; clicked5=0; clicked6=0; clicked7=0;clicked8=0;clicked9=0;       
    }//GEN-LAST:event_buttonCustomerMouseClicked

    private void buttonFoodMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonFoodMenuMouseClicked
        clicked3++;
        buttonFoodMenu.setBackground(new Color(0,102,204));
        changeButtonColors("clicked3");
        clicked1=0; clicked2=0; clicked4=0; clicked5=0; clicked6=0; clicked7=0;clicked8=0;clicked9=0;
    }//GEN-LAST:event_buttonFoodMenuMouseClicked

    private void buttonOtherOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonOtherOrdersMouseClicked
       clicked4++;
        buttonOtherOrders.setBackground(new Color(0,102,204));
        changeButtonColors("clicked4");
        clicked1=0; clicked2=0; clicked3=0; clicked5=0; clicked6=0; clicked7=0;clicked8=0;clicked9=0;
    }//GEN-LAST:event_buttonOtherOrdersMouseClicked

    private void buttonEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEmployeeMouseClicked
        clicked5++;
        buttonEmployee.setBackground(new Color(0,102,204));
        changeButtonColors("clicked5");
        clicked1=0; clicked2=0; clicked3=0; clicked4=0; clicked6=0; clicked7=0;clicked8=0;clicked9=0;
    }//GEN-LAST:event_buttonEmployeeMouseClicked

    private void buttonStockOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonStockOrderMouseClicked
        clicked6++;
        buttonStockOrder.setBackground(new Color(0,102,204));
        changeButtonColors("clicked6");
        clicked1=0; clicked2=0; clicked3=0; clicked4=0; clicked5=0; clicked7=0;clicked8=0;clicked9=0;
    }//GEN-LAST:event_buttonStockOrderMouseClicked

    private void buttonMaintenanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonMaintenanceMouseClicked
        clicked7++;
        buttonMaintenance.setBackground(new Color(0,102,204));
        changeButtonColors("clicked7");
        clicked1=0; clicked2=0; clicked3=0; clicked4=0; clicked6=0; clicked5=0;clicked8=0;clicked9=0;
    }//GEN-LAST:event_buttonMaintenanceMouseClicked

    private void buttonReportsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonReportsMouseClicked
         clicked8++;
        buttonReports.setBackground(new Color(0,102,204));
        changeButtonColors("clicked8");
        clicked1=0; clicked2=0; clicked3=0; clicked4=0; clicked6=0; clicked7=0;clicked8=0;clicked9=0;
    }//GEN-LAST:event_buttonReportsMouseClicked

    private void buttonUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonUsersMouseClicked
         clicked9++;
        buttonReports.setBackground(new Color(0,102,204));
        changeButtonColors("clicked9");
        clicked1=0; clicked2=0; clicked3=0; clicked4=0; clicked6=0; clicked7=0;clicked8=0;clicked9=0;
    }//GEN-LAST:event_buttonUsersMouseClicked

    public boolean isDoubleValid(String x)
    {
        String r = "\\d{1,5}";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(r);
        java.util.regex.Matcher m = pattern.matcher(x);
        boolean t = m.matches();
        Double v = Double.parseDouble(x);
        if ((v<0) && (t=false))
        {
            return false;
        }
                
        return true;        
    }
        
    private void textFieldPaymentPaidAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldPaymentPaidAmountKeyPressed

        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
       {
            Double amnt = Double.parseDouble(orderdetails[4]);            
            Double paid = Double.parseDouble(textFieldPaymentPaidAmount.getText());
           
           if(isDoubleValid(textFieldPaymentPaidAmount.getText()) && (paid>amnt))
           {
                try              
                {
                  
                  textFieldPaymentBalance.setText(String.format("%.2f",(paid-amnt)));
                }
                catch(Exception f)
                {
                  System.out.println(f);
                }
           }
           else
           { 
               textFieldPaymentPaidAmount.setBackground(new Color(255, 150, 150));
               JOptionPane.showMessageDialog(null,"Invalid Amount","Error",JOptionPane.ERROR_MESSAGE);
           }
       }
       
    }//GEN-LAST:event_textFieldPaymentPaidAmountKeyPressed

    int t1count=0,t2count=0,t3count=0,t4count=0;
    int oidcount = 0;
    boolean updateA = true;//available in DB
    int menuselection = 0;
    int enterpressed= 0;
    
    private void textFieldUpdateOrderOrderIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldUpdateOrderOrderIDKeyReleased
    
    oidcount++;
    int runtime = 0;
    String menu="";
    String des = "";
    StringTokenizer tk1 ;
    int tokennum=0;
    String token1="",token2="",token3="";
    int selm = 0;
    
    //if(enterpressed>1)
    //{buttonUpdateOrderReset.doClick();}
    //check order id validity
    if(isInteger(textFieldUpdateOrderOrderID.getText()))
    {      
        if((oidcount==6 ) || (evt.getKeyCode()==KeyEvent.VK_ENTER)|| (evt.getKeyCode()==KeyEvent.VK_TAB))
        {
            enterpressed++;
            
            //loading the orderitems table
            tableUpdateOrder = new javax.swing.JTable();
            tableUpdateOrder.setModel(new javax.swing.table.DefaultTableModel            
            (
                new Object [][] { }, new String [] { "Item", "Type","Quantity" }
            )
            {
            Class[] type = new Class [] 
            {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean []
            {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) 
            {
                return type [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) 
            {
                return canEdit [columnIndex];
            }
        }
                
        );    
         
        scrollPaneUpdateOrder.setViewportView(tableUpdateOrder);          
        scrollPaneUpdateOrder.getViewport().setBackground(Color.white); 
        tableUpdateOrder.setOpaque(false);
        tableUpdateOrder.setGridColor(new Color(42, 135, 235));       
        tableUpdateOrder.getColumnModel().getColumn(0).setResizable(false);
        tableUpdateOrder.getColumnModel().getColumn(0).setPreferredWidth(150);
        tableUpdateOrder.getColumnModel().getColumn(1).setResizable(false);
        tableUpdateOrder.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableUpdateOrder.getColumnModel().getColumn(2).setResizable(false);
        tableUpdateOrder.getColumnModel().getColumn(2).setPreferredWidth(100);
        
        //setting up the combobox in type column          
        
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) listAddOrderItems2.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);  
        DefaultListModel model2 = new DefaultListModel();
        listAddOrderItems2.setModel(model2); 
        
        TableColumn col = tableUpdateOrder.getColumnModel().getColumn(1);
        String types[] = {"Full","Half"};
        comboBox = new JComboBox<String>(types);
        col.setCellEditor(new DefaultCellEditor(comboBox));   
        comboBox.setBackground(Color.white);
        
        DefaultTableCellRenderer rendererx = (DefaultTableCellRenderer) tableUpdateOrder.getDefaultRenderer(Integer.class);
        rendererx.setHorizontalAlignment(SwingConstants.CENTER);  
        
        String orderdes = ""; //compile error prevention
        //showing already available order description 
        String sql2 = "SELECT * FROM restaurant_order WHERE restaurant_order_id = '"+textFieldUpdateOrderOrderID.getText()+"'";
        try 
        {
            DefaultTableModel model = (DefaultTableModel)tableUpdateOrder.getModel();
            pre = conn.prepareStatement(sql2);
            rs = pre.executeQuery();
            
            while(rs.next())
            { 
               runtime++;
               des = rs.getString("order_des");
               tk1 = new StringTokenizer(des,"-");
                
               while(tk1.hasMoreTokens())
               {
                    tokennum++; 
                    if(tokennum==1)token1= tk1.nextToken();
                    if(tokennum==2)token2= tk1.nextToken();
                    if(tokennum==3)token3= tk1.nextToken();
                    if(tokennum==4)//one item is over
                    {
                         
                        model.addRow(new Object[]{token1,token2,token3});
                        tokennum = 0;
                    }    
                    
                }
                
                 textFieldUpdateOrderOrderID.setBackground(new Color(255, 255, 255));
                 menu = rs.getString("order_menu_id");
                 if(menu.equals("550000"))
                 comboBoxUpdateOrderSelectMenu.setSelectedIndex(1);
                 if(menu.equals("550001"))
                 comboBoxUpdateOrderSelectMenu.setSelectedIndex(2);
                 if(menu.equals("550002"))
                 comboBoxUpdateOrderSelectMenu.setSelectedIndex(3);
                 if(menu.equals("550003"))
                 comboBoxUpdateOrderSelectMenu.setSelectedIndex(4);
                 
                 updateAdd.setEnabled(true);
                 updateRemove.setEnabled(true);
                   //showing  menu item list
            selm = selectedMenu(comboBoxUpdateOrderSelectMenu.getSelectedIndex());
            //showing menu items on list         
            String sql = "SELECT menu_des FROM menu WHERE menu_id = '" + selm + "'";
            try 
            {
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
            
                while(rs.next())
                {
                    orderdes = rs.getString("menu_des");
                    StringTokenizer tk2 = new StringTokenizer(orderdes,",");               
                    while(tk2.hasMoreTokens())
                    {
                        model2.addElement(tk2.nextToken()+"\n");       
                    }
                }                
            } 
            catch (SQLException ex) 
            {
                    System.out.println(ex);
            }
            }
            if((runtime==0)&& (rs.next()==false))
            {
                  textFieldUpdateOrderOrderID.setBackground(new Color(255, 150, 150));
                  JOptionPane.showMessageDialog(null,"No such record!","Error",JOptionPane.ERROR_MESSAGE);
                  comboBoxUpdateOrderSelectMenu.setSelectedIndex(0);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);  
            JOptionPane.showMessageDialog(null,"Invalid Data!","Error",JOptionPane.ERROR_MESSAGE);
            textFieldUpdateOrderOrderID.setBackground(new Color(255, 150, 150));
        }
        
          
       }       
                
    }//GEN-LAST:event_textFieldUpdateOrderOrderIDKeyReleased
    }
    private void updateAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAddActionPerformed
        
      //if( == true)
       // { 
        String item = listAddOrderItems2.getSelectedValue();
        DefaultTableModel model = (DefaultTableModel)tableUpdateOrder.getModel();       
        model.addRow(new Object[]{item,"Choose Type"});
       // }  
        
    }//GEN-LAST:event_updateAddActionPerformed

    private void updateRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateRemoveActionPerformed
        
        try
        {
            int row = tableUpdateOrder.getSelectedRow();        
            String toberemoved = tableUpdateOrder.getValueAt(row, 0).toString();
            DefaultTableModel model = (DefaultTableModel)tableUpdateOrder.getModel();
            model.removeRow(row);        
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Row not selected!","Error",JOptionPane.ERROR_MESSAGE);        
        }
       
    }//GEN-LAST:event_updateRemoveActionPerformed

    private void textFieldRemoveOrderOrderIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldRemoveOrderOrderIDKeyReleased

        if((evt.getKeyCode()==KeyEvent.VK_ENTER)|| (evt.getKeyCode()==KeyEvent.VK_TAB))
        {
            buttonRemoveOrderSearch.doClick();        
        }
                 
    }//GEN-LAST:event_textFieldRemoveOrderOrderIDKeyReleased

    private void comboBoxUpdateOrderSelectMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxUpdateOrderSelectMenu1ActionPerformed
       
        if(comboBoxUpdateOrderSelectMenu1.getSelectedIndex()==0)
        {
            mvalidity = false;
            comboBoxUpdateOrderSelectMenu1.setBackground(new Color(255, 150, 150));
        }
        else
        {
            mvalidity = true; comboBoxUpdateOrderSelectMenu1.setBackground(new Color(255, 255, 255));
        }    
        
        
    }//GEN-LAST:event_comboBoxUpdateOrderSelectMenu1ActionPerformed

    private void textFieldSerachOrderOrderID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSerachOrderOrderID1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSerachOrderOrderID1ActionPerformed

    private void textFieldSerachOrderOrderID3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSerachOrderOrderID3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSerachOrderOrderID3ActionPerformed

    private void textFieldSerachOrderOrderIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSerachOrderOrderIDKeyReleased

        String orderid = textFieldSerachOrderOrderID.getText();
        int len = orderid.length();
        
            //validate
            if((isInteger(orderid)) && (len==6))
            {       
                ovalidity = true;
                if((evt.getKeyCode()==KeyEvent.VK_ENTER)|| (evt.getKeyCode()==KeyEvent.VK_TAB))
                {
                    textFieldSerachOrderOrderID.setBackground(new Color(255, 255, 255));
                    buttonSearchOrderSearch.doClick();
                }
                
            }
            else if((isInteger(orderid)==false) && (len==6))
            {
                ovalidity = false;   
                textFieldSerachOrderOrderID.setBackground(new Color(255, 150, 150));                
            }  
               
    }//GEN-LAST:event_textFieldSerachOrderOrderIDKeyReleased

    private void textFieldSerachOrderOrderID1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSerachOrderOrderID1KeyReleased
        
        if(isNICValid(textFieldSerachOrderOrderID1.getText()))
        {       
                cvalidity = true;
                if((evt.getKeyCode()==KeyEvent.VK_ENTER)|| (evt.getKeyCode()==KeyEvent.VK_TAB))
                {
                    textFieldSerachOrderOrderID1.setBackground(new Color(255, 255, 255));
                    buttonSearchOrderSearch.doClick();
                }    
               
        }
        else
        {       cvalidity = false;   
                textFieldSerachOrderOrderID1.setBackground(new Color(255, 150, 150));                
        }  

    }//GEN-LAST:event_textFieldSerachOrderOrderID1KeyReleased

    private void orderDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderDateMouseClicked
        
    }//GEN-LAST:event_orderDateMouseClicked

    private void textFieldSerachOrderOrderID3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSerachOrderOrderID3KeyReleased
       
        String x = textFieldSerachOrderOrderID3.getText();
        if(x.equals("1") || x.equals("0"))
        {
            svalidity = true;
        }
        else
        {
             svalidity = false;
             textFieldSerachOrderOrderID3.setBackground(new Color(255, 150, 150)); 
        }
    }//GEN-LAST:event_textFieldSerachOrderOrderID3KeyReleased

    private void buttonSearchCustomerHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerHomeActionPerformed
        panelshift(buttonAddCustomerHome.getText());
          //redirecting to to welcome
        panelWelcome.setVisible(true);
        panelRestaurantOrder.setVisible(false);
        panelCustomer.setVisible(false);                            
        panelPayment.setVisible(false);
        panelFoodMenu.setVisible(false);
        panelOtherOrders.setVisible(false);
        panelStockOrder.setVisible(false);
        panelMaintenance.setVisible(false);
        panelReports.setVisible(false);
        panelUsers.setVisible(false);                
        buttonCustomer.setBackground(new Color(51,153,255));        
        buttonAddCustomerReset.doClick();
        buttonSearchCustomerReset.doClick();
        buttonSearchCustomerReset1.doClick();
    }//GEN-LAST:event_buttonSearchCustomerHomeActionPerformed

    private void buttonSearchCustomerSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerSearchActionPerformed

        String cusnic = textFieldSearchCustomerCustomerID.getText();
        //validating the cusnic
        if(isNICValid(cusnic))
        {
            String sql = "SELECT restaurant_customer_nic AS 'Customer NIC', restaurant_customer_name AS 'Customer Name', restaurant_customer_telephone AS 'Customer Telephone', restaurant_customer_email AS 'Customer Email' FROM restaurant_customer WHERE restaurant_customer_nic = '" + cusnic + "'";
            System.out.println(sql);
            try 
            {
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
            }

            tableSearchCustomerResultsTable.setModel(DbUtils.resultSetToTableModel(rs)); //loading the query results
        }
        else 
        {
            JOptionPane.showMessageDialog(null,"Invalid NIC!","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonSearchCustomerSearchActionPerformed

    private void buttonSearchCustomerDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerDemoActionPerformed
        textFieldSearchCustomerCustomerID.setText("926531266V");
    }//GEN-LAST:event_buttonSearchCustomerDemoActionPerformed

    private void buttonSearchCustomerResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerResetActionPerformed

        textFieldSearchCustomerCustomerID.setBackground(new Color(255, 255, 255));
        textFieldSearchCustomerCustomerID.setText("");
        
        tableSearchCustomerResultsTable = new javax.swing.JTable();
        tableSearchCustomerResultsTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
        ));

        scrollPaneSearchCustomerResultsTable.setViewportView(tableSearchCustomerResultsTable);

    }//GEN-LAST:event_buttonSearchCustomerResetActionPerformed

    private void textFieldSearchCustomerCustomerIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchCustomerCustomerIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchCustomerCustomerIDActionPerformed

    private void textFieldAddCustomerTelephone3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldAddCustomerTelephone3KeyReleased
        t4count++;
        if(t4count==3)
        {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.getFocusOwner().transferFocus();

        }
    }//GEN-LAST:event_textFieldAddCustomerTelephone3KeyReleased

    private void textFieldAddCustomerTelephone3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldAddCustomerTelephone3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldAddCustomerTelephone3ActionPerformed

    private void textFieldAddCustomerTelephone1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldAddCustomerTelephone1KeyReleased
        t2count++;
        if(t2count==3)
        {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.getFocusOwner().transferFocus();

        }
    }//GEN-LAST:event_textFieldAddCustomerTelephone1KeyReleased

    private void textFieldAddCustomerTelephone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldAddCustomerTelephone1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldAddCustomerTelephone1ActionPerformed

    private void textFieldAddCustomerTelephone2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldAddCustomerTelephone2KeyReleased
        t3count++;
        if(t3count==3)
        {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.getFocusOwner().transferFocus();

        }
    }//GEN-LAST:event_textFieldAddCustomerTelephone2KeyReleased

    private void textFieldAddCustomerTelephone2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldAddCustomerTelephone2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldAddCustomerTelephone2ActionPerformed

    private void textFieldAddCustomerNICKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldAddCustomerNICKeyReleased
        niccount++;
        if(niccount==10)
        {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.getFocusOwner().transferFocus();

            //checking whether the customer is already in database
            //checking nic validity
            if(isNICValid(textFieldAddCustomerNIC.getText()))
            {
                StringTokenizer tk1;
                int count = 0;
                //if nic is valid
                //check for db availability
                String sql = "SELECT * FROM restaurant_customer WHERE restaurant_customer_nic = '"+textFieldAddCustomerNIC.getText()+"'";
                try
                {
                    pre = conn.prepareStatement(sql);
                    rs = pre.executeQuery();

                    while(rs.next())
                    {
                        //if is, complete the other textboxes with his/her details
                        textFieldAddCustomerName.setText(rs.getString("restaurant_customer_name"));
                        textFieldAddCustomerEmail.setText(rs.getString("restaurant_customer_email"));
                        String tp = rs.getString("restaurant_customer_telephone");

                        tk1 = new StringTokenizer(tp,"-");

                        while(tk1.hasMoreTokens())
                        {
                            count++;
                            if(count==1)
                            countryCode.setText(tk1.nextToken());
                            if(count==2)
                            textFieldAddCustomerTelephone1.setText(tk1.nextToken());
                            if(count==3)
                            textFieldAddCustomerTelephone2.setText(tk1.nextToken());
                            if(count==4)
                            textFieldAddCustomerTelephone3.setText(tk1.nextToken());
                        }
                        customerStatus.setVisible(true);
                        customerStatus.setText("Already in DB");
                        cusA = true;
                    }

                }
                catch (Exception ex)
                {
                    System.out.println(ex);
                }

            }

        }

    }//GEN-LAST:event_textFieldAddCustomerNICKeyReleased

    private void textFieldAddCustomerNICKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldAddCustomerNICKeyPressed

        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.getFocusOwner().transferFocus();
        }

    }//GEN-LAST:event_textFieldAddCustomerNICKeyPressed

    private void textFieldAddCustomerNICActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldAddCustomerNICActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldAddCustomerNICActionPerformed

    private void buttonAddCustomerBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerBackActionPerformed
        //redirecting back to Add Order from Add Customer
        restaurantpanelshift();
        tabbedPaneRestaurantOrder.setSelectedIndex(0);
        tabbedPaneRestaurantOrder.setEnabledAt(0, true);
    }//GEN-LAST:event_buttonAddCustomerBackActionPerformed

    private void buttonAddCustomerHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerHomeActionPerformed
        panelshift(buttonAddCustomerHome.getText());
          //redirecting to to welcome
        panelWelcome.setVisible(true);
        panelRestaurantOrder.setVisible(false);
        panelCustomer.setVisible(false);                            
        panelPayment.setVisible(false);
        panelFoodMenu.setVisible(false);
        panelOtherOrders.setVisible(false);
        panelStockOrder.setVisible(false);
        panelMaintenance.setVisible(false);
        panelReports.setVisible(false);
        panelUsers.setVisible(false);                
        buttonCustomer.setBackground(new Color(51,153,255));        
        buttonAddCustomerReset.doClick();
        buttonSearchCustomerReset.doClick();
        buttonSearchCustomerReset1.doClick();
    }//GEN-LAST:event_buttonAddCustomerHomeActionPerformed

    private void buttonAddCustomerNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerNextActionPerformed

        //String phone =  countryCode.getText()+textFieldAddCustomerTelephone1.getText()+textFieldAddCustomerTelephone2.getText()+textFieldAddCustomerTelephone3.getText();
        String phone =  countryCode.getText() + "-" + textFieldAddCustomerTelephone1.getText() +
        "-" + textFieldAddCustomerTelephone2.getText() +"-" + textFieldAddCustomerTelephone3.getText();
        boolean x = isPhoneNumberValid(phone);
        System.out.println("isNumber? "+x);
        boolean y =isValidEmailAddress(textFieldAddCustomerEmail.getText());
        System.out.println("isEmail? "+y);
        boolean z =isNICValid(textFieldAddCustomerNIC.getText());
        System.out.println("isNIC? "+z);

        if(y==false)
        textFieldAddCustomerEmail.setBackground(new Color(255, 150, 150));
        if(x==false)
        countryCode.setBackground(new Color(255, 150, 150));
        textFieldAddCustomerTelephone1.setBackground(new Color(255, 150, 150));
        textFieldAddCustomerTelephone2.setBackground(new Color(255, 150, 150));
        textFieldAddCustomerTelephone3.setBackground(new Color(255, 150, 150));
        if(z==false)
        textFieldAddCustomerNIC.setBackground(new Color(255, 150, 150));

        if(textFieldAddCustomerName.getText().equals(""))
        textFieldAddCustomerName.setBackground(new Color(255, 150, 150));

        if((x==true)&&(y==true)&&(z==true))
        {
            //redirecting to Add Payment from Add Customer
            panelWelcome.setVisible(false);
            panelRestaurantOrder.setVisible(false);
            panelCustomer.setVisible(true);
            tabbedPaneCustomer.setVisible(false);
            panelCustomerContent.setVisible(false);
            panelPayment.setVisible(true);
            panelFoodMenu.setVisible(false);
            panelOtherOrders.setVisible(false);
            panelStockOrder.setVisible(false);
            panelMaintenance.setVisible(false);
            panelReports.setVisible(false);
            panelUsers.setVisible(false);

            //SAVING CURRENT DATA
            orderdetails[1] = textFieldAddCustomerName.getText();
            orderdetails[2] = countryCode.getText() + "-" + textFieldAddCustomerTelephone1.getText() +
            "-" + textFieldAddCustomerTelephone2.getText() +"-" + textFieldAddCustomerTelephone3.getText();
            orderdetails[3] = textFieldAddCustomerEmail.getText();
            orderdetails[5] = textFieldAddCustomerNIC.getText();

            //payment panel order description show
            textAreaPaymentOrderDescription.setEnabled(false);
            labelMenuID.setText(orderdetails[0]);
            for(int i = 0 ;i<data.size();i++)
            textAreaPaymentOrderDescription.append(data.get(i)+"\n");
            Double a = Double.parseDouble(calculate());
            textFieldPaymentAmount.setText(String.format("%.2f", a)); //calculates the amount and displays
            orderdetails[4] = textFieldPaymentAmount.getText();

        }
    }//GEN-LAST:event_buttonAddCustomerNextActionPerformed

    private void countryCodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_countryCodeKeyReleased

        t1count++;
        if(t1count==4)
        {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.getFocusOwner().transferFocus();
        }

    }//GEN-LAST:event_countryCodeKeyReleased

    private void countryCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_countryCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_countryCodeActionPerformed

    private void textFieldAddCustomerEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldAddCustomerEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldAddCustomerEmailActionPerformed

    private void buttonAddCustomerResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerResetActionPerformed
        textFieldAddCustomerNIC.setText("");
        textFieldAddCustomerName.setText("");
        countryCode.setText("");
        textFieldAddCustomerEmail.setText("");
    }//GEN-LAST:event_buttonAddCustomerResetActionPerformed

    private void buttonAddCustomerDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerDemoActionPerformed
        textFieldAddCustomerNIC.setText("938944547V");
        textFieldAddCustomerName.setText("Saman Jayathunga");
        countryCode.setText("+91");
        textFieldAddCustomerEmail.setText("saman@yahoo.com");
    }//GEN-LAST:event_buttonAddCustomerDemoActionPerformed

    private void textFieldAddCustomerNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldAddCustomerNameKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.getFocusOwner().transferFocus();

        }
    }//GEN-LAST:event_textFieldAddCustomerNameKeyPressed

    private void textFieldAddCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldAddCustomerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldAddCustomerNameActionPerformed

    private void textFieldSearchCustomerCustomerID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchCustomerCustomerID1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchCustomerCustomerID1ActionPerformed

    private void buttonSearchCustomerSearch1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerSearch1ActionPerformed

        
        String cusnic = textFieldSearchCustomerCustomerID1.getText();
        //validating the cusnic
        if(isNICValid(cusnic))
        {
            String sql = "SELECT restaurant_customer_name AS 'Customer Name', restaurant_customer_telephone AS 'Customer Telephone', restaurant_customer_email AS 'Customer Email' FROM restaurant_customer WHERE restaurant_customer_nic = '" + cusnic + "'";
            System.out.println(sql);
            try 
            {
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
            }

            tableSearchCustomerResultsTable1.setModel(DbUtils.resultSetToTableModel(rs)); //loading the query results
        }
        else 
        {
            
            tableSearchCustomerResultsTable1 = new javax.swing.JTable();
            tableSearchCustomerResultsTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
            )
            );

        tableSearchCustomerResultsTable1.setGridColor(new java.awt.Color(51, 153, 255));
        scrollPaneSearchCustomerResultsTable1.setViewportView(tableSearchCustomerResultsTable1);

        JOptionPane.showMessageDialog(null,"Invalid NIC!","Error",JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_buttonSearchCustomerSearch1ActionPerformed

    private void buttonSearchCustomerReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerReset1ActionPerformed
    
        textFieldSearchCustomerCustomerID1.setText("");       
        tableSearchCustomerResultsTable1 = new javax.swing.JTable();

        tableSearchCustomerResultsTable1.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {

        }
        ));

        scrollPaneSearchCustomerResultsTable1.setViewportView(tableSearchCustomerResultsTable1);
    }//GEN-LAST:event_buttonSearchCustomerReset1ActionPerformed

    private void buttonSearchCustomerDemo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerDemo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchCustomerDemo1ActionPerformed

    private void buttonSearchCustomerHome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchCustomerHome1ActionPerformed
            panelshift(buttonAddCustomerHome.getText()); 
    }//GEN-LAST:event_buttonSearchCustomerHome1ActionPerformed

    private void buttonCustomerContentAddCustomer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCustomerContentAddCustomer1ActionPerformed
        customerpanelshift();
        tabbedPaneCustomer.setSelectedIndex(2);    
        tabbedPaneCustomer.setEnabledAt(2, true);
    }//GEN-LAST:event_buttonCustomerContentAddCustomer1ActionPerformed

    int chlen = 0 ;
    private void textFieldSearchCustomerCustomerIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSearchCustomerCustomerIDKeyReleased
       
        String nic = textFieldSearchCustomerCustomerID.getText();
        chlen = nic.length();
        
        if(isNICValid(nic) && (chlen==10))
        {
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)|| (evt.getKeyCode()==KeyEvent.VK_TAB))
            {
                textFieldSearchCustomerCustomerID.setBackground(new Color(255, 255, 255));
                buttonSearchCustomerSearch.doClick();
            }    
        }
        else if(isNICValid(nic)==false && (chlen==10))
        {
            textFieldSearchCustomerCustomerID.setBackground(new Color(255, 150, 150));
        }
        else if((evt.getKeyCode()==KeyEvent.VK_ENTER)|| (evt.getKeyCode()==KeyEvent.VK_TAB))
        {
                textFieldSearchCustomerCustomerID.setBackground(new Color(255, 150, 150));
                //buttonSearchCustomerSearch.doClick();
                JOptionPane.showMessageDialog(null,"Invalid NIC!","Error",JOptionPane.ERROR_MESSAGE);
        }  
        
    }//GEN-LAST:event_textFieldSearchCustomerCustomerIDKeyReleased

    private void textFieldSearchCustomerCustomerID1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldSearchCustomerCustomerID1KeyReleased
        String nic = textFieldSearchCustomerCustomerID1.getText();
        chlen = nic.length();
        
        if(isNICValid(nic) && (chlen==10))
        {
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)|| (evt.getKeyCode()==KeyEvent.VK_TAB))
            {
                textFieldSearchCustomerCustomerID1.setBackground(new Color(255, 255, 255));
                buttonSearchCustomerSearch1.doClick();
            }    
        }
        else if(isNICValid(nic)==false && (chlen==10))
        {
            textFieldSearchCustomerCustomerID1.setBackground(new Color(255, 150, 150));
        }        
        else if((evt.getKeyCode()==KeyEvent.VK_ENTER)|| (evt.getKeyCode()==KeyEvent.VK_TAB))
        {
                textFieldSearchCustomerCustomerID1.setBackground(new Color(255, 150, 150));
                //buttonSearchCustomerSearch.doClick();
                JOptionPane.showMessageDialog(null,"Invalid NIC!","Error",JOptionPane.ERROR_MESSAGE);
        }  
    }//GEN-LAST:event_textFieldSearchCustomerCustomerID1KeyReleased

    private void textFieldEmployeeIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldEmployeeIDKeyReleased
 
        String eid =textFieldEmployeeID.getText();
        int len = eid.length();
        
        String sql = "SELECT * FROM hr_employee WHERE employee_id = '"+eid+"' AND employee_department = 'Restaurant'";  
        try 
            {
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
                tableEmployeeResultsTable.setModel(DbUtils.resultSetToTableModel(rs));
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
            }
                
        if(isNICValid(eid)==false && (len==6))
        {
            textFieldEmployeeID.setBackground(new Color(255, 150, 150));
        
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)|| (evt.getKeyCode()==KeyEvent.VK_TAB))
            {
                textFieldEmployeeID.setBackground(new Color(255, 150, 150));
                //buttonSearchCustomerSearch.doClick();
                JOptionPane.showMessageDialog(null,"Invalid Employee ID!","Error",JOptionPane.ERROR_MESSAGE);
            }  
        }
       
    }//GEN-LAST:event_textFieldEmployeeIDKeyReleased

    private void textFieldEmployeeIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldEmployeeIDKeyPressed
                 
    }//GEN-LAST:event_textFieldEmployeeIDKeyPressed

    private void textFieldEmployeeIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldEmployeeIDMouseClicked
        buttonEmployeeReset.doClick();
    }//GEN-LAST:event_textFieldEmployeeIDMouseClicked

    private void buttonUpdateCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateCustomerActionPerformed
    
     String nic = textFieldSearchCustomerCustomerID1.getText();   
    
     chlen = nic.length();
        
    if(isNICValid(nic) && (chlen==10))
    {
       int r = tableSearchCustomerResultsTable1.getSelectedRow(); 
       String name = tableSearchCustomerResultsTable1.getValueAt(r,0).toString();
       String tp = tableSearchCustomerResultsTable1.getValueAt(r,1).toString();
       String mail =tableSearchCustomerResultsTable1.getValueAt(r,2).toString();
        
       int b = JOptionPane.showConfirmDialog(null,"Are you sure?","Confirm Update",JOptionPane.YES_NO_OPTION);
            
        if(b ==0)
        {   
            String sql = "UPDATE restaurant_customer SET restaurant_customer_name = '"+name+"', restaurant_customer_telephone = '"+tp+"', restaurant_customer_email = '"+mail+"' WHERE restaurant_customer_nic  = '"+nic+"'";  
            try 
            {
                pre = conn.prepareStatement(sql);
                pre.execute();
                JOptionPane.showMessageDialog(null,"Customer Information Updated","Success",JOptionPane.INFORMATION_MESSAGE);
                
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Invalid Data", "Warning",JOptionPane.WARNING_MESSAGE);                 
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Update Cancelled!","Success",JOptionPane.INFORMATION_MESSAGE);
        }
              
    }    
    else
    {
        JOptionPane.showMessageDialog(null,"Invalid Data", "Warning",JOptionPane.WARNING_MESSAGE); 
    }   
   
    }//GEN-LAST:event_buttonUpdateCustomerActionPerformed

    private void buttonFoodMenuHome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodMenuHome1ActionPerformed
       buttonFoodMenuReset.doClick();
       buttonFoodMenu.doClick();
    }//GEN-LAST:event_buttonFoodMenuHome1ActionPerformed

    DefaultListModel modelz = new DefaultListModel();
    int count = 0;
    private void buttonFoodMenuHome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodMenuHome2ActionPerformed
        
        int row = foodItems.getSelectedRow();        
        String foodname = foodItems.getValueAt(row, 1).toString();
        if(count==0)
        {
            jList2.setModel(modelz);
        }
        
        modelz.addElement(foodname+"\n");       
        count++; 
        
    }//GEN-LAST:event_buttonFoodMenuHome2ActionPerformed

    private void buttonRestaurantContentAddOrder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRestaurantContentAddOrder1ActionPerformed
        foodmenupanelshift();            
        tabbedPaneFoodMenu.setEnabledAt(0, true);        
        tabbedPaneFoodMenu.setEnabledAt(1, false);
        tabbedPaneFoodMenu.setEnabledAt(2, false);
        tabbedPaneFoodMenu.setEnabledAt(3, false); 
        tabbedPaneFoodMenu.setSelectedIndex(0); 
        buttonFoodMenuReset.doClick();
        /*try{tabbedPaneRestaurantOrder.setSelectedComponent(panelAddMenu);}
        catch(Exception e){}*/
    }//GEN-LAST:event_buttonRestaurantContentAddOrder1ActionPerformed

    private void buttonRestaurantContentUpdateOrder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRestaurantContentUpdateOrder1ActionPerformed
        foodmenupanelshift();
        tabbedPaneFoodMenu.setEnabledAt(1, true);           
        tabbedPaneFoodMenu.setEnabledAt(0, false);  
        tabbedPaneFoodMenu.setEnabledAt(2, false);
        tabbedPaneFoodMenu.setEnabledAt(3, false);
        tabbedPaneFoodMenu.setSelectedIndex(1); 
        
        buttonFoodMenuReset1.doClick();
         /*try{tabbedPaneRestaurantOrder.setSelectedComponent(panelUpdateMenu);}
        catch(Exception e){}*/
        
    }//GEN-LAST:event_buttonRestaurantContentUpdateOrder1ActionPerformed

    private void buttonRestaurantContentRemoveOrder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRestaurantContentRemoveOrder1ActionPerformed
        foodmenupanelshift();    
        tabbedPaneFoodMenu.setEnabledAt(2, true);           
        tabbedPaneFoodMenu.setEnabledAt(0, false);
        tabbedPaneFoodMenu.setEnabledAt(1, false);
        tabbedPaneFoodMenu.setEnabledAt(3, false);
        tabbedPaneFoodMenu.setSelectedIndex(2); 
    }//GEN-LAST:event_buttonRestaurantContentRemoveOrder1ActionPerformed

    private void buttonRestaurantContentSearchOrder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRestaurantContentSearchOrder1ActionPerformed
        foodmenupanelshift();         
        tabbedPaneFoodMenu.setSelectedIndex(3);  
        tabbedPaneFoodMenu.setEnabledAt(0, false);
        tabbedPaneFoodMenu.setEnabledAt(1, false);
        tabbedPaneFoodMenu.setEnabledAt(2, false); 
        tabbedPaneFoodMenu.setEnabledAt(3, true);
    }//GEN-LAST:event_buttonRestaurantContentSearchOrder1ActionPerformed

    private void roomOrderIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomOrderIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomOrderIDActionPerformed

    private void buttonAddCustomerNext3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerNext3ActionPerformed

        String s = roomOrderID.getText();
        if(s.equals(""))//no id
        {
        
        String sql = "UPDATE room_roomserviceorders SET room_restaurant_status = 1'";  
        try
        {
            pre = conn.prepareStatement(sql);
            pre.execute();        
            JOptionPane.showMessageDialog(null,"Success","Success",JOptionPane.INFORMATION_MESSAGE); 
            
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"Error Updating Status!","Error",JOptionPane.ERROR_MESSAGE);            
        }
        }
        else //id given
        {
            int row = roomOrders.getSelectedRow();        
            String rsid = roomOrders.getValueAt(row, 0).toString();
            
            String sql = "UPDATE room_roomserviceorders SET room_restaurant_status = '1' WHERE room_roomserviceorders_rsid = '"+rsid+"'";  
            try
            {
                pre = conn.prepareStatement(sql);
                pre.execute();        
                JOptionPane.showMessageDialog(null,"Success","Success",JOptionPane.INFORMATION_MESSAGE); 
            
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Error Updating Status!","Error",JOptionPane.ERROR_MESSAGE);            
            }
                       
        }    
        
    }//GEN-LAST:event_buttonAddCustomerNext3ActionPerformed

    private void buttonAddCustomerHome4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerHome4ActionPerformed
        roomOrderID1.setText("");   
        String sql = "SELECT * FROM event_order";
        System.out.println(sql);
        try 
        {
            pre = conn.prepareStatement(sql);
            rs= pre.executeQuery();
            eventOrders.setModel(DbUtils.resultSetToTableModel(rs)); //loading the query results */
        } 
        catch (SQLException ex)
        {
            System.out.println(ex);
        }
		
        
    }//GEN-LAST:event_buttonAddCustomerHome4ActionPerformed

    private void buttonAddCustomerNext5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerNext5ActionPerformed
       
        
        if(roomOrderID1.getText().equals(""))
        {
        String sql = "UPDATE event_order SET order_status = '1'";  
        try
        {
            pre = conn.prepareStatement(sql);
            pre.execute();        
            JOptionPane.showMessageDialog(null,"Success","Success",JOptionPane.INFORMATION_MESSAGE); 
            
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"Error Updating Status!","Error",JOptionPane.ERROR_MESSAGE);            
        }
        }
        else
        {
            int row = eventOrders.getSelectedRow();                
            String eoid = eventOrders.getValueAt(row, 0).toString();
            
            String sql = "UPDATE event_order SET order_status = '1' WHERE order_event_id = '"+eoid+"'";  
            try
            {
                pre = conn.prepareStatement(sql);
                pre.execute();        
                JOptionPane.showMessageDialog(null,"Success","Success",JOptionPane.INFORMATION_MESSAGE); 
            
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Error Updating Status!","Error",JOptionPane.ERROR_MESSAGE);            
            }
        }
               
    }//GEN-LAST:event_buttonAddCustomerNext5ActionPerformed

    private void roomOrderID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomOrderID1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomOrderID1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        if((evt.getKeyCode()==KeyEvent.VK_ENTER)|| (evt.getKeyCode()==KeyEvent.VK_TAB))
        {
            int x = Integer.parseInt(jTextField4.getText());
            if(isInteger(jTextField4.getText()))
            {
                String level;
                String sql = "select item_reorder_level as 'level' from item where item_availability = 'available'";
                try
                {
                pre= conn.prepareStatement(sql);
                rs = pre.executeQuery();
                
                while(rs.next())
                {
                level = rs.getString("level");
                if((Integer.parseInt(level)) < x)
                {
                    jLabel18.setVisible(true);
                    jLabel18.setForeground(Color.red);
                    jLabel18.setText("Not Available");
                    jButton2.setEnabled(false);
                }
                else
                {
                    jTextField4.setBackground(Color.white);
                     jLabel18.setVisible(true);
                     jLabel18.setForeground(new Color(51,204,0));
                    jLabel18.setText("Available");
                    jButton2.setEnabled(true);
                }    

                }
                }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,"No Such Record!","Error",JOptionPane.ERROR_MESSAGE);
            }
            }    
            else
            {
                jTextField4.setBackground(new Color(255, 150, 150));

            }
        }    
    }//GEN-LAST:event_jTextField4KeyReleased

    private void textFieldViewMenuMenuID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldViewMenuMenuID1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldViewMenuMenuID1ActionPerformed

    private void textFieldViewMenuMenuID2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldViewMenuMenuID2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldViewMenuMenuID2ActionPerformed

    private void textFieldViewMenuMenuIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldViewMenuMenuIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldViewMenuMenuIDActionPerformed

    private void textFieldViewMenuMenuIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldViewMenuMenuIDMouseClicked
        buttonViewMenuReset.doClick();
    }//GEN-LAST:event_textFieldViewMenuMenuIDMouseClicked

    private void textFieldRemoveMenuMenuIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textFieldRemoveMenuMenuIDMouseClicked
        buttonRemoveMenuReset.doClick();
    }//GEN-LAST:event_textFieldRemoveMenuMenuIDMouseClicked
    //int countl = 0;
    private void buttonFoodMenuHome3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodMenuHome3ActionPerformed
        int row = foodItems1.getSelectedRow();        
        String foodname = foodItems1.getValueAt(row, 1).toString();
        DefaultListModel listModel = (DefaultListModel) jList3.getModel();
        JList list = new JList(listModel);
        
        listModel.addElement(foodname+"\n");       
        //countl++; 
    }//GEN-LAST:event_buttonFoodMenuHome3ActionPerformed

    private void buttonFoodMenuHome4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodMenuHome4ActionPerformed
         int index = jList3.getSelectedIndex();
        try
        {
            DefaultListModel model = (DefaultListModel) jList3.getModel();
             model.remove(index);
        }
       catch(Exception e)
       {}
    }//GEN-LAST:event_buttonFoodMenuHome4ActionPerformed

    private void buttonFoodMenuReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodMenuReset1ActionPerformed
        
        String sql = "SELECT food_id AS 'Food ID', food_name AS 'Food Name', food_price AS 'Food Price' FROM food_item WHERE food_item_status = '1'";
            
        try 
        {
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            foodItems1.setModel(DbUtils.resultSetToTableModel(rs));               
        } 
        catch (SQLException ex) 
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"Error Retrieving Data!","Error",JOptionPane.ERROR_MESSAGE);                
        }
         try
        {
            DefaultListModel listModel = (DefaultListModel) jList3.getModel();
            listModel.removeAllElements();
        }
        catch(Exception e)
        {}
    }//GEN-LAST:event_buttonFoodMenuReset1ActionPerformed

    private void buttonFoodMenuHome5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodMenuHome5ActionPerformed
         buttonFoodMenuReset1.doClick();
       buttonFoodMenu.doClick();
    }//GEN-LAST:event_buttonFoodMenuHome5ActionPerformed

    private void buttonFoodMenuAddMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodMenuAddMenu1ActionPerformed
        String price = textFieldFoodMenuMenuPrice1.getText();
        List<String> datac = new ArrayList<String>();
        String menudes = "";
    try{    
        
        if(isDoubleValid(price) && (((textFieldFoodMenuMenuPrice1.getText()).length())==6))
        {
            
            ListModel model = jList3.getModel();
            for(int i=0; i < model.getSize(); i++)
            {
             Object o =  model.getElementAt(i); 
             datac.add(o+"");
            }
            System.out.println(datac);
            
           for(int i = 0 ;i<datac.size();i++)
           jTextArea2.append(datac.get(i)); 
           //menudes.concat(datac.get(i)+",");
           menudes = jTextArea2.getText();
           
           String sql = "INSERT INTO menu (menu_des,menu_price,menu_status) VALUES ('"+menudes+"','"+price+"','1')";
        
           try 
            {
                pre = conn.prepareStatement(sql);
                pre.execute();
                JOptionPane.showMessageDialog(null,"Menu Added!","Success",JOptionPane.INFORMATION_MESSAGE);                
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Menu Addition Failed!","Error",JOptionPane.ERROR_MESSAGE);                
            }
          
        } 
       
    }
    catch(Exception e)
    {
        textFieldFoodMenuMenuPrice1.setBackground(new Color(255, 150, 150));
        JOptionPane.showMessageDialog(null,"Invalid Data!","Error",JOptionPane.ERROR_MESSAGE);  
    }
    }//GEN-LAST:event_buttonFoodMenuAddMenu1ActionPerformed

    private void textFieldFoodMenuMenuPrice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldFoodMenuMenuPrice1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldFoodMenuMenuPrice1ActionPerformed

    private void textFieldFoodMenuIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldFoodMenuIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldFoodMenuIDActionPerformed

    //int menuidc = 0;
    String orderdesn = ""; //compile error prevention
    private void textFieldFoodMenuIDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldFoodMenuIDKeyReleased
        
        //menuidc++;
        String id = textFieldFoodMenuID.getText();
        if(isInteger(id) && (id.length()==6))
        {
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)|| (evt.getKeyCode()==KeyEvent.VK_TAB))
            {
            DefaultListCellRenderer renderer = (DefaultListCellRenderer) jList3.getCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);  
            
            //showing menu items on list         
            String sql = "SELECT menu_des, menu_price FROM menu WHERE menu_id = '" + id + "'";
            
            try 
            {
            pre = conn.prepareStatement(sql);
            rs = pre.executeQuery();
            
          //  if(rs.next()
            DefaultListModel model = new DefaultListModel();
            jList3.setModel(model);
                   
            while(rs.next())
            {
               orderdesn = rs.getString("menu_des");
               StringTokenizer tk1 = new StringTokenizer(orderdesn,",");               
             
               while(tk1.hasMoreTokens())
               {
                    model.addElement(tk1.nextToken()+"\n");       
               }
               textFieldFoodMenuMenuPrice1.setText(rs.getString("menu_price"));
            }
            
            
            
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Error Retrieving Data!","Error",JOptionPane.ERROR_MESSAGE); 
            }
            
            
            }
        }
        else if((isInteger(id)==false) && (id.length()==6))
        {
            textFieldFoodMenuID.setBackground(new Color(255, 150, 150));
            JOptionPane.showMessageDialog(null,"Invalid Menu ID!","Error",JOptionPane.ERROR_MESSAGE); 

        }   
       
    }//GEN-LAST:event_textFieldFoodMenuIDKeyReleased

    private void buttonFoodMenuReset2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFoodMenuReset2ActionPerformed
       buttonFoodMenuReset1.doClick();
       textFieldFoodMenuID.setText("550001");
       textFieldFoodMenuID.setBackground(Color.white);
       
    }//GEN-LAST:event_buttonFoodMenuReset2ActionPerformed

    private void accountRequestAmountTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountRequestAmountTextFieldKeyPressed
        
    }//GEN-LAST:event_accountRequestAmountTextFieldKeyPressed

    private void accountRequestCreditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountRequestCreditButtonActionPerformed

    
        String req = accountRequestAmountTextField.getText();
        String balance = "";
        
            String sql = "SELECT budget_balance FROM fin_budget WHERE budget_department = 'Restaurant'";
            System.out.println(sql);
            try 
            {
                pre = conn.prepareStatement(sql);
                rs = pre.executeQuery();
                while(rs.next())
                {
                    balance = (rs.getString("budget_balance"));
                }             
                
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Error Retrieving Data!","Error",JOptionPane.ERROR_MESSAGE);                
            } 
            
                
        Double bal = Double.parseDouble(balance);
        Double reqa = Double.parseDouble(req);
        
        if(isDoubleValid(req) && (bal>reqa))   
        {    
            
            String sql2 = "INSERT INTO fin_budget_request (request_budget_id,budget_request_department,budget_request_amount) VALUES ('170003','Restaurant','"+accountRequestAmountTextField.getText()+"')";
            System.out.println(sql);
            try 
            {
                pre = conn.prepareStatement(sql2);
                pre.execute();
                accountRequestAmountTextField.setBackground(Color.white);
                JOptionPane.showMessageDialog(null,"Success!","Success",JOptionPane.INFORMATION_MESSAGE);
                accountRequestAmountTextField.setText("");
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,"Error Entering Data!","Error",JOptionPane.ERROR_MESSAGE);                
            } 
        } 
        else if(isDoubleValid(req)!=false && (bal<reqa))
        {
            accountRequestAmountTextField.setBackground(new Color(255, 150, 150));  
            
        }
        else
        {
            accountRequestAmountTextField.setBackground(Color.white);
        }    
        
        
    }//GEN-LAST:event_accountRequestCreditButtonActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        
            String sql = "select item_units as 'Units' from item where item_availability = 'available'";
            try
            {
            pre= conn.prepareStatement(sql);
            rs = pre.executeQuery();
            //data to addStockPurchaseItemCategoryComboBox
            String units;
            while(rs.next()){
                units = rs.getString("Units");
                jTextField5.setText(units);
            }
            }
            catch(Exception e){}
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void buttonAddCustomerDemo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerDemo2ActionPerformed
        //  2.setText("");
         DefaultTableModel model = (DefaultTableModel)eventOrders.getModel();
        while(model.getRowCount() > 0)
        {
            for (int i = 0;i< model.getRowCount(); i++)
            {model.removeRow(i)  ;
            }
        }
    }//GEN-LAST:event_buttonAddCustomerDemo2ActionPerformed

    private void buttonAddCustomerDemo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCustomerDemo1ActionPerformed
        roomOrderID.setText("750001");
         DefaultTableModel model = (DefaultTableModel)roomOrders.getModel();
        while(model.getRowCount() > 0)
        {
            for (int i = 0;i< model.getRowCount(); i++)
            {model.removeRow(i)  ;
            }
        }
    }//GEN-LAST:event_buttonAddCustomerDemo1ActionPerformed
    
    public static void main(String args[])
    {
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
            java.util.logging.Logger.getLogger(Restaurant_Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Restaurant_Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Restaurant_Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Restaurant_Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run()
            {
                new Restaurant_Manager().setVisible(true);                
            }
        });
    }
    
    //method to check a given value is a valid integer or not
   public static boolean isInteger(String s)
    {
        String r = "\\d{1,6}";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(r);
        java.util.regex.Matcher m = pattern.matcher(s);
        return m.matches();        
    }
    
   //validating the quantity
   public static boolean qtyValidation(String s)
   {
        String r = "\\d{1,3}";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(r);
        java.util.regex.Matcher m = pattern.matcher(s);
        return m.matches();    
   }   
    //shifting panels when menu buttons are clicked
    public void panelshift(String buttonText)
    {
        switch (buttonText) 
        {
                case "RESTAURANT ORDER":
                panelWelcome.setVisible(false);
                panelRestaurantOrder.setVisible(true);
                panelRestaurantContent.setVisible(true);
                tabbedPaneRestaurantOrder.setVisible(false);             
                panelCustomer.setVisible(false);
                panelPayment.setVisible(false);
                panelFoodMenu.setVisible(false);
                panelOtherOrders.setVisible(false);
                panelEmployee.setVisible(false);
                panelStockOrder.setVisible(false);
                panelMaintenance.setVisible(false);
                panelReports.setVisible(false);
                panelUsers.setVisible(false);                
                break;        
                
                case "CUSTOMER":
                panelWelcome.setVisible(false);
                panelRestaurantOrder.setVisible(false);
                panelCustomer.setVisible(true);
                panelCustomerContent.setVisible(true);
                tabbedPaneCustomer.setVisible(false); 
                panelPayment.setVisible(false);
                panelFoodMenu.setVisible(false);
                panelOtherOrders.setVisible(false);
                panelEmployee.setVisible(false);
                panelStockOrder.setVisible(false);
                panelMaintenance.setVisible(false);
                panelReports.setVisible(false); 
                panelUsers.setVisible(false);
                break;
                
                case "USERS":
                panelWelcome.setVisible(false);
                panelRestaurantOrder.setVisible(false);
                panelCustomer.setVisible(false);
                panelPayment.setVisible(false);
                panelFoodMenu.setVisible(false);
                panelOtherOrders.setVisible(false);
                panelEmployee.setVisible(false);
                panelStockOrder.setVisible(false);
                panelMaintenance.setVisible(false);
                panelReports.setVisible(false);
                panelUsers.setVisible(true);
                break;  
                
                case "MENU":
                panelWelcome.setVisible(false);
                panelRestaurantOrder.setVisible(false);
                panelCustomer.setVisible(false);
                panelPayment.setVisible(false);
                panelFoodMenu.setVisible(true);
                panelFoodMenuContent.setVisible(true);
                tabbedPaneFoodMenu.setVisible(false);                
                panelOtherOrders.setVisible(false);
                panelEmployee.setVisible(false);
                panelStockOrder.setVisible(false);
                panelMaintenance.setVisible(false);
                panelReports.setVisible(false);
                panelUsers.setVisible(false);
                break;  
                
                case "OTHER ORDERS":
                panelWelcome.setVisible(false);
                panelRestaurantOrder.setVisible(false);
                panelCustomer.setVisible(false);
                panelPayment.setVisible(false);
                panelFoodMenu.setVisible(false);
                panelOtherOrders.setVisible(true);
                panelEmployee.setVisible(false);
                panelStockOrder.setVisible(false);
                panelMaintenance.setVisible(false);
                panelReports.setVisible(false);
                panelUsers.setVisible(false);
                break;  
                
                case "STOCK ORDER":
                panelWelcome.setVisible(false);
                panelRestaurantOrder.setVisible(false);
                panelCustomer.setVisible(false);
                panelPayment.setVisible(false);
                panelFoodMenu.setVisible(false);
                panelOtherOrders.setVisible(false);
                panelEmployee.setVisible(false);
                panelStockOrder.setVisible(true);
                panelMaintenance.setVisible(false);
                panelReports.setVisible(false);
                panelUsers.setVisible(false);
                break;  
                
                case "MAINTENANCE":
                panelWelcome.setVisible(false);
                panelRestaurantOrder.setVisible(false);
                panelCustomer.setVisible(false);
                panelPayment.setVisible(false);
                panelFoodMenu.setVisible(false);
                panelOtherOrders.setVisible(false);
                panelEmployee.setVisible(false);
                panelStockOrder.setVisible(false);
                panelMaintenance.setVisible(true);
                panelReports.setVisible(false);
                panelUsers.setVisible(false);
                break;  
                
                case "REPORTS":
                panelWelcome.setVisible(false);
                panelRestaurantOrder.setVisible(false);
                panelCustomer.setVisible(false);
                panelPayment.setVisible(false);
                panelFoodMenu.setVisible(false);
                panelOtherOrders.setVisible(false);
                panelEmployee.setVisible(false);
                panelStockOrder.setVisible(false);
                panelMaintenance.setVisible(false);
                panelReports.setVisible(true);
                panelUsers.setVisible(false);
                break;  
                
                case "EMPLOYEE":
                panelWelcome.setVisible(false);
                panelRestaurantOrder.setVisible(false);
                panelCustomer.setVisible(false);
                panelPayment.setVisible(false);
                panelFoodMenu.setVisible(false);
                panelOtherOrders.setVisible(false);
                panelEmployee.setVisible(true);
                panelEmployeDetails.setVisible(true);
                panelStockOrder.setVisible(false);
                panelMaintenance.setVisible(false);
                panelReports.setVisible(false);
                panelUsers.setVisible(false);
                break;  
                
                case "Home":
                panelWelcome.setVisible(true);
                panelRestaurantOrder.setVisible(false);
                panelCustomer.setVisible(false);
                panelPayment.setVisible(false);
                panelFoodMenu.setVisible(false);
                panelOtherOrders.setVisible(false);
                panelEmployee.setVisible(false);
                panelStockOrder.setVisible(false);
                panelMaintenance.setVisible(false);
                panelReports.setVisible(false);
                panelUsers.setVisible(false);
                break;  
                
                default: 
                break;
        }
    } 
    
    //checking the menu ID related to provided index
    public int selectedMenu(int index)
    {
                int menuID = 0;
                System.out.println(index);
                
                if(index == 1)
                { menuID = 550000;}
                if(index == 2)
                { menuID = 550001;}
                if(index == 3)
                { menuID = 550002;}
                if(index == 4)
                { menuID = 550003;}
                
                return menuID;    
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountBalanceLabel;
    private javax.swing.JTextField accountBalanceTextField;
    private javax.swing.JLabel accountDepositLabel;
    private javax.swing.JTextField accountDepositTextField;
    private javax.swing.JLabel accountRequestAmountLabel;
    private javax.swing.JTextField accountRequestAmountTextField;
    private javax.swing.JButton accountRequestCreditButton;
    private javax.swing.JButton buttonAddCustomerBack;
    private javax.swing.JButton buttonAddCustomerDemo;
    private javax.swing.JButton buttonAddCustomerDemo1;
    private javax.swing.JButton buttonAddCustomerDemo2;
    private javax.swing.JButton buttonAddCustomerHome;
    private javax.swing.JButton buttonAddCustomerHome1;
    private javax.swing.JButton buttonAddCustomerHome3;
    private javax.swing.JButton buttonAddCustomerHome4;
    private javax.swing.JButton buttonAddCustomerNext;
    private javax.swing.JButton buttonAddCustomerNext1;
    private javax.swing.JButton buttonAddCustomerNext2;
    private javax.swing.JButton buttonAddCustomerNext3;
    private javax.swing.JButton buttonAddCustomerNext4;
    private javax.swing.JButton buttonAddCustomerNext5;
    private javax.swing.JButton buttonAddCustomerNext6;
    private javax.swing.JButton buttonAddCustomerNext7;
    private javax.swing.JButton buttonAddCustomerReset;
    private javax.swing.JButton buttonAddCustomerReset1;
    private javax.swing.JButton buttonAddCustomerReset2;
    private javax.swing.JButton buttonAddOrderAdd;
    private javax.swing.JButton buttonAddOrderDemo;
    private javax.swing.JButton buttonAddOrderNext;
    private javax.swing.JButton buttonAddOrderNext1;
    private javax.swing.JButton buttonAddOrderRemove;
    private javax.swing.JButton buttonAddOrderReset;
    private javax.swing.JButton buttonCustomer;
    private javax.swing.JButton buttonCustomerContentAddCustomer;
    private javax.swing.JButton buttonCustomerContentAddCustomer1;
    private javax.swing.JButton buttonCustomerContentSearchCustomer;
    private javax.swing.JButton buttonDemoReports;
    private javax.swing.JButton buttonEmployee;
    private javax.swing.JButton buttonEmployeeDemo;
    private javax.swing.JButton buttonEmployeeDemo1;
    private javax.swing.JButton buttonEmployeeNext;
    private javax.swing.JButton buttonEmployeeNext1;
    private javax.swing.JButton buttonEmployeePrint;
    private javax.swing.JButton buttonEmployeeReset;
    private javax.swing.JButton buttonEmployeeReset1;
    private javax.swing.JButton buttonEmployeeSearchAll1;
    private javax.swing.JButton buttonEmployeeSearchSingle;
    private javax.swing.JButton buttonFoodMenu;
    private javax.swing.JButton buttonFoodMenuAddMenu;
    private javax.swing.JButton buttonFoodMenuAddMenu1;
    private javax.swing.JButton buttonFoodMenuHome;
    private javax.swing.JButton buttonFoodMenuHome1;
    private javax.swing.JButton buttonFoodMenuHome2;
    private javax.swing.JButton buttonFoodMenuHome3;
    private javax.swing.JButton buttonFoodMenuHome4;
    private javax.swing.JButton buttonFoodMenuHome5;
    private javax.swing.JButton buttonFoodMenuReset;
    private javax.swing.JButton buttonFoodMenuReset1;
    private javax.swing.JButton buttonFoodMenuReset2;
    private javax.swing.JButton buttonGenerateReport;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton buttonHomeReport;
    private javax.swing.JButton buttonLogOut;
    private javax.swing.JButton buttonMaintenance;
    private javax.swing.JButton buttonOtherOrders;
    private javax.swing.JButton buttonPaymentBack;
    private javax.swing.JButton buttonPaymentDone;
    private javax.swing.JButton buttonPaymentHome;
    private javax.swing.JButton buttonPaymentPrintBill;
    private javax.swing.JButton buttonPrintReport;
    private javax.swing.JButton buttonRemoveMenuDemo;
    private javax.swing.JButton buttonRemoveMenuHome;
    private javax.swing.JButton buttonRemoveMenuRemoveMenu;
    private javax.swing.JButton buttonRemoveMenuReset;
    private javax.swing.JButton buttonRemoveMenuSearch;
    private javax.swing.JButton buttonRemoveOrderDemo;
    private javax.swing.JButton buttonRemoveOrderHome;
    private javax.swing.JButton buttonRemoveOrderRemoveOrder;
    private javax.swing.JButton buttonRemoveOrderReset;
    private javax.swing.JButton buttonRemoveOrderSearch;
    private javax.swing.JButton buttonReports;
    private javax.swing.JButton buttonResetReports;
    private javax.swing.JButton buttonRestaurantContentAddOrder;
    private javax.swing.JButton buttonRestaurantContentAddOrder1;
    private javax.swing.JButton buttonRestaurantContentRemoveOrder;
    private javax.swing.JButton buttonRestaurantContentRemoveOrder1;
    private javax.swing.JButton buttonRestaurantContentSearchOrder;
    private javax.swing.JButton buttonRestaurantContentSearchOrder1;
    private javax.swing.JButton buttonRestaurantContentUpdateOrder;
    private javax.swing.JButton buttonRestaurantContentUpdateOrder1;
    private javax.swing.JButton buttonRestaurantOrder;
    private javax.swing.JButton buttonSearchCustomerDemo;
    private javax.swing.JButton buttonSearchCustomerDemo1;
    private javax.swing.JButton buttonSearchCustomerHome;
    private javax.swing.JButton buttonSearchCustomerHome1;
    private javax.swing.JButton buttonSearchCustomerReset;
    private javax.swing.JButton buttonSearchCustomerReset1;
    private javax.swing.JButton buttonSearchCustomerSearch;
    private javax.swing.JButton buttonSearchCustomerSearch1;
    private javax.swing.JButton buttonSearchOrderDemo;
    private javax.swing.JButton buttonSearchOrderHome;
    private javax.swing.JButton buttonSearchOrderPrint;
    private javax.swing.JButton buttonSearchOrderReset;
    private javax.swing.JButton buttonSearchOrderSearch;
    private javax.swing.JButton buttonStockOrder;
    private javax.swing.JButton buttonUpdateCustomer;
    private javax.swing.JButton buttonUpdateOrderDemo;
    private javax.swing.JButton buttonUpdateOrderHome;
    private javax.swing.JButton buttonUpdateOrderReset;
    private javax.swing.JButton buttonUpdateOrderUpdateOrder;
    private javax.swing.JButton buttonUsers;
    private javax.swing.JButton buttonViewMenuDemo;
    private javax.swing.JButton buttonViewMenuHome;
    private javax.swing.JButton buttonViewMenuReset;
    private javax.swing.JButton buttonViewMenuSearch;
    private javax.swing.JComboBox<String> comboBoxAddOrderSelectMenu;
    private javax.swing.JComboBox<String> comboBoxPaymentPaymentType;
    private javax.swing.JComboBox<String> comboBoxUpdateOrderSelectMenu;
    private javax.swing.JComboBox<String> comboBoxUpdateOrderSelectMenu1;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JTextField countryCode;
    private javax.swing.JLabel customerStatus;
    private javax.swing.JLabel day;
    private javax.swing.JTable eventOrders;
    private javax.swing.JTable foodItems;
    private javax.swing.JTable foodItems1;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JLabel labelAddCustomerEmail;
    private javax.swing.JLabel labelAddCustomerName;
    private javax.swing.JLabel labelAddCustomerName1;
    private javax.swing.JLabel labelAddCustomerTelephone;
    private javax.swing.JLabel labelAddOrderMenuID;
    private javax.swing.JLabel labelAddOrderOrderDescription;
    private javax.swing.JLabel labelAddOrderOrderDescription1;
    private javax.swing.JLabel labelEmployeeID;
    private javax.swing.JLabel labelFoodMenuMenuPrice;
    private javax.swing.JLabel labelFoodMenuMenuPrice1;
    private javax.swing.JLabel labelFoodMenuMenuPrice2;
    private javax.swing.JLabel labelMenuID;
    private javax.swing.JLabel labelParameter;
    private javax.swing.JLabel labelPaymentAmount;
    private javax.swing.JLabel labelPaymentAmount1;
    private javax.swing.JLabel labelPaymentAmount2;
    private javax.swing.JLabel labelPaymentPaymentDescription;
    private javax.swing.JLabel labelPaymentPaymentDescription1;
    private javax.swing.JLabel labelPaymentPaymentMethod;
    private javax.swing.JLabel labelRemoveMenuMenuID;
    private javax.swing.JLabel labelRemoveOrderOrderID;
    private javax.swing.JLabel labelSearchCustomerCustomerID;
    private javax.swing.JLabel labelSearchCustomerCustomerID1;
    private javax.swing.JLabel labelSearchOrderOrderID;
    private javax.swing.JLabel labelSearchOrderOrderID1;
    private javax.swing.JLabel labelSearchOrderOrderID2;
    private javax.swing.JLabel labelSearchOrderOrderID3;
    private javax.swing.JLabel labelUpdateOrderMenuID;
    private javax.swing.JLabel labelUpdateOrderMenuID1;
    private javax.swing.JLabel labelUpdateOrderOrderDescription;
    private javax.swing.JLabel labelUpdateOrderOrderDescription1;
    private javax.swing.JLabel labelUpdateOrderOrderID;
    private javax.swing.JLabel labelUserName;
    private javax.swing.JLabel labelViewMenuMenuID;
    private javax.swing.JLabel labelViewMenuMenuID1;
    private javax.swing.JLabel labelViewMenuMenuID2;
    private javax.swing.JLabel labelWelcome;
    private javax.swing.JLabel labelWelcomeMessage;
    private javax.swing.JLabel lablFoodMenuMenuDescription;
    private javax.swing.JLabel lablFoodMenuMenuDescription1;
    private javax.swing.JList<String> listAddOrderItems1;
    private javax.swing.JList<String> listAddOrderItems2;
    private javax.swing.JDesktopPane mainDesktopPane;
    private javax.swing.JPanel menuPanel;
    private com.toedter.calendar.JDateChooser orderDate;
    private javax.swing.JScrollPane orderScrollPane;
    private javax.swing.JPanel panelAddCustomer;
    private javax.swing.JPanel panelAddMenu;
    private javax.swing.JPanel panelAddOrder;
    private javax.swing.JPanel panelCustomer;
    private javax.swing.JPanel panelCustomerContent;
    private javax.swing.JPanel panelEmployeDetails;
    private javax.swing.JPanel panelEmployee;
    private javax.swing.JPanel panelEventOrders;
    private javax.swing.JPanel panelFoodMenu;
    private javax.swing.JPanel panelFoodMenuContent;
    private javax.swing.JPanel panelMaintenance;
    private javax.swing.JPanel panelOtherOrders;
    private javax.swing.JPanel panelPayment;
    private javax.swing.JPanel panelRemoveMenu;
    private javax.swing.JPanel panelRemoveOrder;
    private javax.swing.JPanel panelReports;
    private javax.swing.JPanel panelRestaurantContent;
    private javax.swing.JPanel panelRestaurantOrder;
    private javax.swing.JPanel panelRoomOrders;
    private javax.swing.JPanel panelSearchCustomer;
    private javax.swing.JPanel panelSearchOrder;
    private javax.swing.JPanel panelStockOrder;
    private javax.swing.JPanel panelUpdateCustomer;
    private javax.swing.JPanel panelUpdateMenu;
    private javax.swing.JPanel panelUpdateOrder;
    private javax.swing.JPanel panelUsers;
    private javax.swing.JPanel panelViewMenu;
    private javax.swing.JPanel panelWelcome;
    private javax.swing.JTextField roomOrderID;
    private javax.swing.JTextField roomOrderID1;
    private javax.swing.JTable roomOrders;
    private javax.swing.JScrollPane scrollPaneAddOrderItems;
    private javax.swing.JScrollPane scrollPaneAddOrderItems1;
    private javax.swing.JScrollPane scrollPaneEmployeeResultsTable;
    private javax.swing.JScrollPane scrollPaneEmployeeResultsTable1;
    private javax.swing.JScrollPane scrollPanePaymentOrderDescription;
    private javax.swing.JScrollPane scrollPaneRemoveMenuResultsTable;
    private javax.swing.JScrollPane scrollPaneRoomOrders;
    private javax.swing.JScrollPane scrollPaneSearchCustomerResultsTable;
    private javax.swing.JScrollPane scrollPaneSearchCustomerResultsTable1;
    private javax.swing.JScrollPane scrollPaneSearchOrderResultsTable;
    private javax.swing.JScrollPane scrollPaneUpdateOrder;
    private javax.swing.JTabbedPane tabbedPaneCustomer;
    private javax.swing.JTabbedPane tabbedPaneFoodMenu;
    private javax.swing.JTabbedPane tabbedPaneOtherOrders;
    private javax.swing.JTabbedPane tabbedPaneRestaurantOrder;
    private javax.swing.JTable tableEmployeeResultsTable;
    private javax.swing.JTable tableEmployeeResultsTable1;
    private javax.swing.JTable tableOrderitems;
    private javax.swing.JTable tableRemoveMenuResultsTable;
    private javax.swing.JTable tableRemoveOrderResultsTable;
    private javax.swing.JTable tableSearchCustomerResultsTable;
    private javax.swing.JTable tableSearchCustomerResultsTable1;
    private javax.swing.JTable tableSearchOrderTable;
    private javax.swing.JTable tableUpdateOrder;
    private javax.swing.JTextArea textAreaPaymentOrderDescription;
    private javax.swing.JTextField textFieldAddCustomerEmail;
    private javax.swing.JTextField textFieldAddCustomerNIC;
    private javax.swing.JTextField textFieldAddCustomerName;
    private javax.swing.JTextField textFieldAddCustomerTelephone1;
    private javax.swing.JTextField textFieldAddCustomerTelephone2;
    private javax.swing.JTextField textFieldAddCustomerTelephone3;
    private javax.swing.JTextField textFieldEmployeeID;
    private javax.swing.JTextField textFieldEmployeeID1;
    private javax.swing.JTextField textFieldFoodMenuID;
    private javax.swing.JTextField textFieldFoodMenuMenuPrice;
    private javax.swing.JTextField textFieldFoodMenuMenuPrice1;
    private javax.swing.JTextField textFieldParameter;
    private javax.swing.JTextField textFieldPaymentAmount;
    private javax.swing.JTextField textFieldPaymentBalance;
    private javax.swing.JTextField textFieldPaymentPaidAmount;
    private javax.swing.JTextField textFieldRemoveMenuMenuID;
    private javax.swing.JTextField textFieldRemoveOrderOrderID;
    private javax.swing.JTextField textFieldSearchCustomerCustomerID;
    private javax.swing.JTextField textFieldSearchCustomerCustomerID1;
    private javax.swing.JTextField textFieldSerachOrderOrderID;
    private javax.swing.JTextField textFieldSerachOrderOrderID1;
    private javax.swing.JTextField textFieldSerachOrderOrderID3;
    private javax.swing.JTextField textFieldUpdateOrderOrderID;
    private javax.swing.JTextField textFieldViewMenuMenuID;
    private javax.swing.JTextField textFieldViewMenuMenuID1;
    private javax.swing.JTextField textFieldViewMenuMenuID2;
    private javax.swing.JLabel timelabel;
    private javax.swing.JButton updateAdd;
    private javax.swing.JButton updateRemove;
    // End of variables declaration//GEN-END:variables
}

