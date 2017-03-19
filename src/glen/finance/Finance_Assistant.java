package glen.finance;

import glen.DBconnect;
import glen.System_Login;
import static glen.finance.Finance_Accounant.USER;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import net.proteanit.sql.DbUtils;
import javax.swing.*;
import javax.swing.table.TableColumn;

public class Finance_Assistant extends javax.swing.JFrame {
    //variable for connect database
    Connection conn=DBconnect.ConnectDB();
    PreparedStatement pstatement=null;
    ResultSet Results =null;
    DBAccess data=new DBAccess();
    
    
    //variable initialization
    String description ="";             String paymentMethod="";
    String department="";               String cashflowType="";
    String day="";                      String month="";
    String year="";                     String paymentStatus="";
    String cashflowApproval="";         String amountString="";
    String incomeStatus="";             String expenditureStatus="";
    
    String searchBy="";                 String searchValue="";
    String colName="";                  String paymentType=""; 
    String actionType="";               
    static String USER;
    
    int commonId;                       String userlogActionId;
    double amount;                      int cashflowId=0;
    double budgetBalance;               
    int incomeId;                       int expenditureId;
    
    public Finance_Assistant(String username) {
        
        USER=username;
        initComponents();
        this.setLocationRelativeTo(null); //Setting to display in the center of screen
        clock();
        data.adduserlogs(USER, "System Login", "Login",00000);
        
        welcomeuser.setText(USER);
        togglePanels.setVisible(true);
        incomePanel.setVisible(false);
        incomeUpdatePanel.setVisible(false);
        expenditurePanel.setVisible(false);
        expenditurePanel1.setVisible(false);
        cashflowPanel.setVisible(false);
        cashflowPanel1.setVisible(false);
    }
//----------------------------------------------------------finance functions----------------------------------------------------------------
    public void financepanelshift(String buttonName){
        switch (buttonName) {
            case "INCOME":
                incomePanel.setVisible(true);
                incomeUpdatePanel.setVisible(false);
                expenditurePanel.setVisible(false);
                expenditurePanel1.setVisible(false);
                cashflowPanel.setVisible(false);
                cashflowPanel1.setVisible(false);
                jLabel14.setVisible(false);
                break;
            case "IN. UPDATE":
                incomePanel.setVisible(false);
                incomeUpdatePanel.setVisible(true);
                expenditurePanel.setVisible(false);
                expenditurePanel1.setVisible(false);
                cashflowPanel.setVisible(false);
                cashflowPanel1.setVisible(false);
                jLabel14.setVisible(false);
                break;    
            case "EXPENDITURE":
                incomePanel.setVisible(false);
                incomeUpdatePanel.setVisible(false);
                expenditurePanel.setVisible(true);
                expenditurePanel1.setVisible(false);
                cashflowPanel.setVisible(false);
                cashflowPanel1.setVisible(false);
                jLabel14.setVisible(false);
                break;
            case "EX. UPDATE":
                incomePanel.setVisible(false);
                incomeUpdatePanel.setVisible(false);
                expenditurePanel.setVisible(false);
                expenditurePanel1.setVisible(true);
                cashflowPanel.setVisible(false);
                cashflowPanel1.setVisible(false);
                jLabel14.setVisible(false);
                break;
            case "CASHFLOW":
                incomePanel.setVisible(false);
                incomeUpdatePanel.setVisible(false);
                expenditurePanel.setVisible(false);
                expenditurePanel1.setVisible(false);
                cashflowPanel.setVisible(true);
                cashflowPanel1.setVisible(false);
                jLabel14.setVisible(false);
                break;
            case "CA. UPDATE":
                incomePanel.setVisible(false);
                incomeUpdatePanel.setVisible(false);
                expenditurePanel.setVisible(false);
                expenditurePanel.setVisible(false);
                cashflowPanel.setVisible(false);
                cashflowPanel1.setVisible(true);
                jLabel14.setVisible(false);
                break;


            default:
                break;
        }
    }            //panel shifting
    public void financeincometableload(){
        jTableIncome.setModel(DbUtils.resultSetToTableModel(data.finincometableload())); // inserting table results to income GUI table
        jTableIncome3.setModel(DbUtils.resultSetToTableModel(data.finincometableload1())); // inserting table results to income GUI table
    }                         //table load
    public void financeexpensetableaoad(){
        jTableAddExpense.setModel(DbUtils.resultSetToTableModel(data.finexpensetableaoad()));      // inserting table results to expenditure GUI table
        jTableAddExpense1.setModel(DbUtils.resultSetToTableModel(data.finexpensetableaoad1()));      // inserting table results to expenditure GUI table
    }                        //table load
    public void financecashflowtableload(){
            jTableCashflow.setModel(DbUtils.resultSetToTableModel(data.fincashflowtableload()));      // inserting table results to income GUI table
            jTableCashflow1.setModel(DbUtils.resultSetToTableModel(data.fincashflowtableload1()));      // inserting table results to income GUI table
            TableColumn col=jTableCashflow.getColumnModel().getColumn(2);
            col.setMinWidth(150);
            TableColumn col2=jTableCashflow1.getColumnModel().getColumn(2);
            col2.setMinWidth(150);
    }                       //table load
    public void accept(int cashflowid, String type, String tableName, String idCol, String statusCol ){
        try{
            String Sql = "select * from fin_cashflow where cashflow_Payment_type='"+type+"' and cashflow_approval='not approved' and cashflow_id="+cashflowid;
            pstatement = conn.prepareStatement(Sql);
            Results = pstatement.executeQuery();
            
            while(Results.next()){
                cashflowid= Results.getInt(1);
                paymentStatus= Results.getString(8);
                                                                            //---------------------------------------------------
                if("Debit".equals(paymentStatus))
                       paymentStatus="Credit";                                              //Accounts law
                else    
                        paymentStatus="Debit";                              //----------------------------------------------------        

                Sql="insert into "+tableName+"("+idCol+","+statusCol+") values('"+cashflowid+"','"+paymentStatus+"')";
                pstatement= conn.prepareStatement(Sql);
                pstatement.execute();
            }
            Sql="update fin_cashflow set cashflow_approval='approved' where cashflow_Payment_type='"+type+"' and cashflow_approval='not approved' and cashflow_id="+cashflowId;
            pstatement= conn.prepareStatement(Sql);
            pstatement.execute();
            financeincometableload(); financeexpensetableaoad(); 
            if(cashflowid<110000) 
                JOptionPane.showMessageDialog(togglePanels,"No New Records Found!","Alert!",1);
            else{
                JOptionPane.showMessageDialog(togglePanels,"Successfully Added to the "+type+ "Table!","Success Message",1);
                cashflowid=0;    
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(togglePanels,e,"Exception Handled!",0);
        }
    }  //catogarize cashflows
    public void financeSearch(String catagory){
        Finance_Search search=new Finance_Search();
        UIManager ui=new UIManager();
        ui.put("OptionPane.background",Color.white);
        ui.put("Panel.background", Color.white);
        int r=JOptionPane.showConfirmDialog(togglePanels, search,"Please Enter Searching Values",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
        if(r== JOptionPane.OK_OPTION){
             searchBy = search.txtSearchBy.getSelectedItem().toString().toLowerCase();
             searchValue = search.txtSearchValue.getText();
             if(catagory.equals("cashflow")){
                Results=data.cashflowDBSearch(searchBy,searchValue);
                jTableCashflow1.setModel(DbUtils.resultSetToTableModel(Results));
             }
             else{    
                    Results= data.financeDBSearch(searchBy,searchValue,catagory);
                    switch(catagory){
                        case "income":
                            jTableIncome3.setModel(DbUtils.resultSetToTableModel(Results));
                            break;
                        case "expense":
                            jTableAddExpense1.setModel(DbUtils.resultSetToTableModel(Results));
                            break;
                        default:
                            break;
                    }
                }
            try{ 
                if(!Results.last())
                    JOptionPane.showMessageDialog(togglePanels,"No Records were Found!","Search Empty",1);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDesktopPaneFinance = new javax.swing.JDesktopPane();
        financeManagerPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        welcomeuser = new javax.swing.JLabel();
        welcomeuser1 = new javax.swing.JLabel();
        Time = new javax.swing.JLabel();
        jLabelheaderimage = new javax.swing.JLabel();
        mainButtonPanel = new javax.swing.JPanel();
        buttonIncome = new javax.swing.JButton();
        buttonIncome1 = new javax.swing.JButton();
        buttonExpenditure = new javax.swing.JButton();
        buttonExpenditure1 = new javax.swing.JButton();
        buttonCashflow = new javax.swing.JButton();
        buttonCashflow1 = new javax.swing.JButton();
        jButtonlogout = new javax.swing.JButton();
        togglePanels = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        incomePanel = new javax.swing.JPanel();
        jTabbedPaneIncome = new javax.swing.JTabbedPane();
        IncomePanel = new javax.swing.JPanel();
        lableincomedescription = new javax.swing.JLabel();
        txtIncomeDescription = new javax.swing.JTextField();
        lableincomedepartment = new javax.swing.JLabel();
        txtIncomeDepartment = new javax.swing.JComboBox<>();
        txtIncomeDebitCredit = new javax.swing.JComboBox<>();
        lableincomedebitcredit = new javax.swing.JLabel();
        lableincomedate = new javax.swing.JLabel();
        txtdIncomeAmount = new javax.swing.JTextField();
        lableIncomePaymentType = new javax.swing.JLabel();
        lableIncomeMethod = new javax.swing.JLabel();
        txtIncomeMethod = new javax.swing.JComboBox<>();
        lableincomeamount = new javax.swing.JLabel();
        txtIncomePaymentType = new javax.swing.JComboBox<>();
        txtIncomeday = new javax.swing.JComboBox<>();
        txtIncomeMonth = new javax.swing.JComboBox<>();
        txtIncomeYear = new javax.swing.JComboBox<>();
        jScrollPaneIncome = new javax.swing.JScrollPane();
        jTableIncome = new javax.swing.JTable();
        jButtonIncome = new javax.swing.JButton();
        jButtonResetIncome = new javax.swing.JButton();
        jButtonDemoIncome = new javax.swing.JButton();
        jButtonAcceptAllIncome = new javax.swing.JButton();
        incomeUpdatePanel = new javax.swing.JPanel();
        jTabbedPaneIncome1 = new javax.swing.JTabbedPane();
        IncomePanel3 = new javax.swing.JPanel();
        lableincomeid2 = new javax.swing.JLabel();
        lableincomedescription3 = new javax.swing.JLabel();
        txtIncomeId2 = new javax.swing.JTextField();
        txtIncomeDescription3 = new javax.swing.JTextField();
        lableincomedepartment3 = new javax.swing.JLabel();
        txtIncomeDepartment3 = new javax.swing.JComboBox<>();
        txtIncomeDebitCredit3 = new javax.swing.JComboBox<>();
        lableincomedebitcredit3 = new javax.swing.JLabel();
        lableincomedate3 = new javax.swing.JLabel();
        txtdIncomeAmount3 = new javax.swing.JTextField();
        lableIncomePaymentType3 = new javax.swing.JLabel();
        lableIncomeMethod3 = new javax.swing.JLabel();
        txtIncomeMethod3 = new javax.swing.JComboBox<>();
        lableincomeamount3 = new javax.swing.JLabel();
        txtIncomePaymentType3 = new javax.swing.JComboBox<>();
        txtIncomeday3 = new javax.swing.JComboBox<>();
        txtIncomeMonth3 = new javax.swing.JComboBox<>();
        txtIncomeYear3 = new javax.swing.JComboBox<>();
        jScrollPaneIncome3 = new javax.swing.JScrollPane();
        jTableIncome3 = new javax.swing.JTable();
        jButtonResetIncome3 = new javax.swing.JButton();
        jButtonUpdateIncome2 = new javax.swing.JButton();
        jButtonFindIncome2 = new javax.swing.JButton();
        expenditurePanel = new javax.swing.JPanel();
        jTabbedPaneExpenditure = new javax.swing.JTabbedPane();
        ExpenditurePanel = new javax.swing.JPanel();
        lableExpensedescription = new javax.swing.JLabel();
        txtExpenseDescription = new javax.swing.JTextField();
        lableExpensedepartment = new javax.swing.JLabel();
        txtExpenseDepartment = new javax.swing.JComboBox<>();
        lableExpenseMethod = new javax.swing.JLabel();
        txtExpenseMethod = new javax.swing.JComboBox<>();
        txtExpensePaymentType = new javax.swing.JComboBox<>();
        lableExpensePaymentType = new javax.swing.JLabel();
        txtExpenseDebitCredit = new javax.swing.JComboBox<>();
        lableExpensedebitcredit = new javax.swing.JLabel();
        lableExpensedate = new javax.swing.JLabel();
        txtExpenseAmount = new javax.swing.JTextField();
        lableExpenseamount = new javax.swing.JLabel();
        txtExpenseday = new javax.swing.JComboBox<>();
        txtExpenseMonth = new javax.swing.JComboBox<>();
        txtExpenseYear = new javax.swing.JComboBox<>();
        jScrollPaneExpense = new javax.swing.JScrollPane();
        jTableAddExpense = new javax.swing.JTable();
        jButtonExpense = new javax.swing.JButton();
        jButtonResetExpense = new javax.swing.JButton();
        jButtonDemoExpense = new javax.swing.JButton();
        jButtonAcceptAllExpenditure = new javax.swing.JButton();
        expenditurePanel1 = new javax.swing.JPanel();
        jTabbedPaneExpenditure1 = new javax.swing.JTabbedPane();
        ExpenditurePanel1 = new javax.swing.JPanel();
        lableExpenseId1 = new javax.swing.JLabel();
        txtExpenseId1 = new javax.swing.JTextField();
        lableExpensedescription1 = new javax.swing.JLabel();
        txtExpenseDescription1 = new javax.swing.JTextField();
        lableExpensedepartment1 = new javax.swing.JLabel();
        txtExpenseDepartment1 = new javax.swing.JComboBox<>();
        lableExpenseMethod1 = new javax.swing.JLabel();
        txtExpenseMethod1 = new javax.swing.JComboBox<>();
        txtExpensePaymentType1 = new javax.swing.JComboBox<>();
        lableExpensePaymentType1 = new javax.swing.JLabel();
        txtExpenseDebitCredit1 = new javax.swing.JComboBox<>();
        lableExpensedebitcredit1 = new javax.swing.JLabel();
        lableExpensedate1 = new javax.swing.JLabel();
        txtExpenseAmount1 = new javax.swing.JTextField();
        lableExpenseamount1 = new javax.swing.JLabel();
        txtExpenseday1 = new javax.swing.JComboBox<>();
        txtExpenseMonth1 = new javax.swing.JComboBox<>();
        txtExpenseYear1 = new javax.swing.JComboBox<>();
        jScrollPaneExpense1 = new javax.swing.JScrollPane();
        jTableAddExpense1 = new javax.swing.JTable();
        jButtonResetExpense1 = new javax.swing.JButton();
        jButtonUpdateExpense1 = new javax.swing.JButton();
        jButtonFindExpense1 = new javax.swing.JButton();
        cashflowPanel = new javax.swing.JPanel();
        jTabbedPaneCashflow = new javax.swing.JTabbedPane();
        CashflowPanel = new javax.swing.JPanel();
        lableCashflowdescription = new javax.swing.JLabel();
        txtCashflowDescription = new javax.swing.JTextField();
        lableCashflowMethod = new javax.swing.JLabel();
        txtCashflowMethod = new javax.swing.JComboBox<>();
        txtCashflowDebitCredit = new javax.swing.JComboBox<>();
        lableCashflowdebitcredit = new javax.swing.JLabel();
        lableCashflowdate = new javax.swing.JLabel();
        txtCashflowAmount = new javax.swing.JTextField();
        lableCashflowamount = new javax.swing.JLabel();
        txtCashflowday = new javax.swing.JComboBox<>();
        txtCashflowMonth = new javax.swing.JComboBox<>();
        txtCashflowYear = new javax.swing.JComboBox<>();
        jScrollPaneCashflow = new javax.swing.JScrollPane();
        jTableCashflow = new javax.swing.JTable();
        jButtonAddCashflow = new javax.swing.JButton();
        jButtonResetCashflow = new javax.swing.JButton();
        jButtonDemoCashflow = new javax.swing.JButton();
        lableCashflowDepartment = new javax.swing.JLabel();
        txtCashflowDepartment = new javax.swing.JComboBox<>();
        txtCashflowPaymentType = new javax.swing.JComboBox<>();
        lableCashflowPaymentType = new javax.swing.JLabel();
        cashflowPanel1 = new javax.swing.JPanel();
        jTabbedPaneCashflow1 = new javax.swing.JTabbedPane();
        CashflowPanel1 = new javax.swing.JPanel();
        lableCashflowid1 = new javax.swing.JLabel();
        lableCashflowdescription1 = new javax.swing.JLabel();
        txtCashflowId1 = new javax.swing.JTextField();
        txtCashflowDescription1 = new javax.swing.JTextField();
        lableCashflowMethod1 = new javax.swing.JLabel();
        txtCashflowMethod1 = new javax.swing.JComboBox<>();
        txtCashflowDebitCredit1 = new javax.swing.JComboBox<>();
        lableCashflowdebitcredit1 = new javax.swing.JLabel();
        lableCashflowdate1 = new javax.swing.JLabel();
        txtCashflowAmount1 = new javax.swing.JTextField();
        lableCashflowamount1 = new javax.swing.JLabel();
        txtCashflowday1 = new javax.swing.JComboBox<>();
        txtCashflowMonth1 = new javax.swing.JComboBox<>();
        txtCashflowYear1 = new javax.swing.JComboBox<>();
        jScrollPaneCashflow1 = new javax.swing.JScrollPane();
        jTableCashflow1 = new javax.swing.JTable();
        jButtonResetCashflow1 = new javax.swing.JButton();
        lableCashflowDepartment1 = new javax.swing.JLabel();
        txtCashflowDepartment1 = new javax.swing.JComboBox<>();
        txtCashflowPaymentType1 = new javax.swing.JComboBox<>();
        lableCashflowPaymentType1 = new javax.swing.JLabel();
        jButtonUpdateCashflow1 = new javax.swing.JButton();
        jButtonViewCashflow1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Glen Hotel Management System");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setSize(new java.awt.Dimension(1020, 700));

        jDesktopPaneFinance.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPaneFinance.setForeground(new java.awt.Color(255, 255, 255));
        jDesktopPaneFinance.setPreferredSize(new java.awt.Dimension(1024, 690));
        jDesktopPaneFinance.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        financeManagerPanel.setBackground(new java.awt.Color(255, 255, 255));
        financeManagerPanel.setPreferredSize(new java.awt.Dimension(1024, 690));
        financeManagerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));
        headerPanel.setLayout(null);

        welcomeuser.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        welcomeuser.setForeground(new java.awt.Color(0, 153, 255));
        welcomeuser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeuser.setText("Name");
        headerPanel.add(welcomeuser);
        welcomeuser.setBounds(820, 40, 170, 30);

        welcomeuser1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        welcomeuser1.setForeground(new java.awt.Color(0, 153, 255));
        welcomeuser1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeuser1.setText("WELCOME ");
        headerPanel.add(welcomeuser1);
        welcomeuser1.setBounds(820, 10, 170, 30);

        Time.setFont(new java.awt.Font("Electrofied", 0, 12)); // NOI18N
        Time.setForeground(new java.awt.Color(51, 153, 255));
        Time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Time.setText("Time");
        headerPanel.add(Time);
        Time.setBounds(790, 100, 230, 20);

        jLabelheaderimage.setIcon(new javax.swing.ImageIcon("D:\\SLIIT PROFILE\\Glen Hotel\\Glen_Hotel_Management_System\\src\\glen\\img\\finance\\headerback1.png")); // NOI18N
        jLabelheaderimage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        jLabelheaderimage.setPreferredSize(new java.awt.Dimension(1020, 130));
        headerPanel.add(jLabelheaderimage);
        jLabelheaderimage.setBounds(2, 2, 1020, 126);

        financeManagerPanel.add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1024, 130));

        mainButtonPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainButtonPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        mainButtonPanel.setPreferredSize(new java.awt.Dimension(187, 552));

        buttonIncome.setBackground(new java.awt.Color(42, 135, 235));
        buttonIncome.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonIncome.setForeground(new java.awt.Color(255, 255, 255));
        buttonIncome.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Income.png")); // NOI18N
        buttonIncome.setText("INCOME");
        buttonIncome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonIncome.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonIncome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonIncomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonIncomeMouseExited(evt);
            }
        });
        buttonIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIncomeActionPerformed(evt);
            }
        });

        buttonIncome1.setBackground(new java.awt.Color(42, 135, 235));
        buttonIncome1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonIncome1.setForeground(new java.awt.Color(255, 255, 255));
        buttonIncome1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Income.png")); // NOI18N
        buttonIncome1.setText("IN. UPDATE");
        buttonIncome1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonIncome1.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonIncome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonIncome1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonIncome1MouseExited(evt);
            }
        });
        buttonIncome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIncome1ActionPerformed(evt);
            }
        });

        buttonExpenditure.setBackground(new java.awt.Color(42, 135, 235));
        buttonExpenditure.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonExpenditure.setForeground(new java.awt.Color(255, 255, 255));
        buttonExpenditure.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Expense.png")); // NOI18N
        buttonExpenditure.setText("EXPENDITURE");
        buttonExpenditure.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonExpenditure.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonExpenditure.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonExpenditureMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonExpenditureMouseExited(evt);
            }
        });
        buttonExpenditure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExpenditureActionPerformed(evt);
            }
        });

        buttonExpenditure1.setBackground(new java.awt.Color(42, 135, 235));
        buttonExpenditure1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonExpenditure1.setForeground(new java.awt.Color(255, 255, 255));
        buttonExpenditure1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Expense.png")); // NOI18N
        buttonExpenditure1.setText("EX. UPDATE");
        buttonExpenditure1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonExpenditure1.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonExpenditure1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonExpenditure1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonExpenditure1MouseExited(evt);
            }
        });
        buttonExpenditure1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExpenditure1ActionPerformed(evt);
            }
        });

        buttonCashflow.setBackground(new java.awt.Color(42, 135, 235));
        buttonCashflow.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonCashflow.setForeground(new java.awt.Color(255, 255, 255));
        buttonCashflow.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Cashflow.png")); // NOI18N
        buttonCashflow.setText("CASHFLOW");
        buttonCashflow.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonCashflow.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonCashflow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonCashflowMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonCashflowMouseExited(evt);
            }
        });
        buttonCashflow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCashflowActionPerformed(evt);
            }
        });

        buttonCashflow1.setBackground(new java.awt.Color(42, 135, 235));
        buttonCashflow1.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonCashflow1.setForeground(new java.awt.Color(255, 255, 255));
        buttonCashflow1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Cashflow.png")); // NOI18N
        buttonCashflow1.setText("CA. UPDATE");
        buttonCashflow1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonCashflow1.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonCashflow1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonCashflow1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonCashflow1MouseExited(evt);
            }
        });
        buttonCashflow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCashflow1ActionPerformed(evt);
            }
        });

        jButtonlogout.setBackground(new java.awt.Color(42, 135, 235));
        jButtonlogout.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        jButtonlogout.setForeground(new java.awt.Color(255, 255, 255));
        jButtonlogout.setIcon(new javax.swing.ImageIcon("D:\\SLIIT PROFILE\\Glen Hotel\\Glen_Hotel_Management_System\\src\\glen\\img\\finance\\logoutAssistant.png")); // NOI18N
        jButtonlogout.setText("LOGOUT");
        jButtonlogout.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonlogout.setMargin(new java.awt.Insets(2, 10, 2, 14));
        jButtonlogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButtonlogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonlogoutMouseExited(evt);
            }
        });
        jButtonlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonlogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainButtonPanelLayout = new javax.swing.GroupLayout(mainButtonPanel);
        mainButtonPanel.setLayout(mainButtonPanelLayout);
        mainButtonPanelLayout.setHorizontalGroup(
            mainButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonIncome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonExpenditure, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(buttonCashflow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonlogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonIncome1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonExpenditure1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(buttonCashflow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainButtonPanelLayout.setVerticalGroup(
            mainButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonExpenditure, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonExpenditure1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCashflow, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCashflow1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(jButtonlogout, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        financeManagerPanel.add(mainButtonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 135, -1, -1));

        togglePanels.setBackground(new java.awt.Color(255, 255, 255));
        togglePanels.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        togglePanels.setPreferredSize(new java.awt.Dimension(832, 552));

        jLabel14.setFont(new java.awt.Font("Trajan Pro 3", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 102, 255));
        jLabel14.setText("WELCOME TO FINANCE");

        incomePanel.setBackground(new java.awt.Color(255, 255, 255));
        incomePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        incomePanel.setPreferredSize(new java.awt.Dimension(830, 548));

        jTabbedPaneIncome.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneIncome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPaneIncome.setPreferredSize(new java.awt.Dimension(830, 548));

        IncomePanel.setBackground(new java.awt.Color(255, 255, 255));
        IncomePanel.setPreferredSize(new java.awt.Dimension(825, 519));

        lableincomedescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomedescription.setText("Description");

        txtIncomeDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableincomedepartment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomedepartment.setText("Department");

        txtIncomeDepartment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Finance", "Stock", "Room", "Restaurant", "HR", "Transport", "Event" }));
        txtIncomeDepartment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeDepartment.setBackground(new Color(255,255,255));

        txtIncomeDebitCredit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeDebitCredit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Debit", "Credit" }));
        txtIncomeDebitCredit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeDebitCredit.setBackground(new Color(255,255,255));

        lableincomedebitcredit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomedebitcredit.setText("Debit / Credit");

        lableincomedate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomedate.setText("Date");

        txtdIncomeAmount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtdIncomeAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableIncomePaymentType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableIncomePaymentType.setText("Cashflow Type");

        lableIncomeMethod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableIncomeMethod.setText("Payment Method");

        txtIncomeMethod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Money", "Bank" }));
        txtIncomeMethod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeMethod.setBackground(new Color(255,255,255));

        lableincomeamount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomeamount.setText("Amount");

        txtIncomePaymentType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomePaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Income", "Expenditure", "Budget", "Investment", "Assest", "Liability" }));
        txtIncomePaymentType.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomePaymentType.setEnabled(false);
        txtIncomePaymentType.setBackground(new Color(255,255,255));

        txtIncomeday.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeday.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtIncomeday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeday.setBackground(new Color(255,255,255));

        txtIncomeMonth.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtIncomeMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeMonth.setBackground(new Color(255,255,255));

        txtIncomeYear.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtIncomeYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeYear.setBackground(new Color(255,255,255));

        jScrollPaneIncome.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneIncome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneIncome.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneIncome.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableIncome.setAutoCreateRowSorter(true);
        jTableIncome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableIncome.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableIncome.setModel(new javax.swing.table.DefaultTableModel(
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
                "Income ID", "Date", "Description", "Department", "Amount", "Debit / Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableIncome.setGridColor(new java.awt.Color(102, 153, 255));
        jTableIncome.setRowHeight(20);
        jTableIncome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableIncomeMouseClicked(evt);
            }
        });
        jScrollPaneIncome.setViewportView(jTableIncome);

        jButtonIncome.setBackground(new java.awt.Color(255, 255, 255));
        jButtonIncome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonIncome.setForeground(new java.awt.Color(51, 153, 255));
        jButtonIncome.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Addbutton.png")); // NOI18N
        jButtonIncome.setText("Add");
        jButtonIncome.setFocusable(false);
        jButtonIncome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonIncome.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonIncome.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIncomeActionPerformed(evt);
            }
        });

        jButtonResetIncome.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetIncome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetIncome.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetIncome.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetIncome.setText("Reset");
        jButtonResetIncome.setFocusable(false);
        jButtonResetIncome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetIncome.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetIncome.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetIncomeActionPerformed(evt);
            }
        });

        jButtonDemoIncome.setBackground(new java.awt.Color(255, 255, 255));
        jButtonDemoIncome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonDemoIncome.setForeground(new java.awt.Color(51, 153, 255));
        jButtonDemoIncome.setText("Demo");
        jButtonDemoIncome.setFocusable(false);
        jButtonDemoIncome.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonDemoIncome.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonDemoIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDemoIncomeActionPerformed(evt);
            }
        });

        jButtonAcceptAllIncome.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAcceptAllIncome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAcceptAllIncome.setForeground(new java.awt.Color(102, 153, 255));
        jButtonAcceptAllIncome.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\acceptbutton.png")); // NOI18N
        jButtonAcceptAllIncome.setText("   Accept");
        jButtonAcceptAllIncome.setFocusable(false);
        jButtonAcceptAllIncome.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAcceptAllIncome.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonAcceptAllIncome.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonAcceptAllIncome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcceptAllIncomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout IncomePanelLayout = new javax.swing.GroupLayout(IncomePanel);
        IncomePanel.setLayout(IncomePanelLayout);
        IncomePanelLayout.setHorizontalGroup(
            IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IncomePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneIncome)
                    .addGroup(IncomePanelLayout.createSequentialGroup()
                        .addComponent(jButtonDemoIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonResetIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAcceptAllIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(IncomePanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(IncomePanelLayout.createSequentialGroup()
                        .addComponent(lableIncomePaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtIncomePaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(IncomePanelLayout.createSequentialGroup()
                            .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lableincomedescription, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lableincomedepartment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IncomePanelLayout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addComponent(txtIncomeDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IncomePanelLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtIncomeDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(IncomePanelLayout.createSequentialGroup()
                            .addComponent(lableIncomeMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtIncomeMethod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(IncomePanelLayout.createSequentialGroup()
                        .addComponent(lableincomeamount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(txtdIncomeAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(IncomePanelLayout.createSequentialGroup()
                        .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableincomedate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableincomedebitcredit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIncomeDebitCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(IncomePanelLayout.createSequentialGroup()
                                .addComponent(txtIncomeday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIncomeMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIncomeYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(52, 52, 52))
        );
        IncomePanelLayout.setVerticalGroup(
            IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IncomePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdIncomeAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableincomeamount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIncomeDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableincomedescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableincomedate)
                    .addComponent(txtIncomeday, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIncomeMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIncomeYear, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableIncomeMethod)
                    .addComponent(txtIncomeMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableincomedebitcredit)
                    .addComponent(txtIncomeDebitCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableincomedepartment)
                    .addComponent(txtIncomeDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableIncomePaymentType)
                    .addComponent(txtIncomePaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPaneIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(IncomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonResetIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDemoIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAcceptAllIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneIncome.addTab("Add Income", IncomePanel);

        javax.swing.GroupLayout incomePanelLayout = new javax.swing.GroupLayout(incomePanel);
        incomePanel.setLayout(incomePanelLayout);
        incomePanelLayout.setHorizontalGroup(
            incomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneIncome, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        incomePanelLayout.setVerticalGroup(
            incomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneIncome, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
        );

        incomeUpdatePanel.setBackground(new java.awt.Color(255, 255, 255));
        incomeUpdatePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        incomeUpdatePanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jTabbedPaneIncome1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneIncome1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPaneIncome1.setPreferredSize(new java.awt.Dimension(830, 548));

        IncomePanel3.setBackground(new java.awt.Color(255, 255, 255));
        IncomePanel3.setPreferredSize(new java.awt.Dimension(825, 519));

        lableincomeid2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomeid2.setText("Income ID");

        lableincomedescription3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomedescription3.setText("Description");

        txtIncomeId2.setEditable(false);
        txtIncomeId2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeId2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        txtIncomeDescription3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeDescription3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableincomedepartment3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomedepartment3.setText("Department");

        txtIncomeDepartment3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeDepartment3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Finance", "Stock", "Room", "Restaurant", "HR", "Transport", "Event" }));
        txtIncomeDepartment3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeDepartment3.setBackground(new Color(255,255,255));

        txtIncomeDebitCredit3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeDebitCredit3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Debit", "Credit" }));
        txtIncomeDebitCredit3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeDebitCredit3.setBackground(new Color(255,255,255));

        lableincomedebitcredit3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomedebitcredit3.setText("Debit / Credit");

        lableincomedate3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomedate3.setText("Date");

        txtdIncomeAmount3.setEditable(false);
        txtdIncomeAmount3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtdIncomeAmount3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableIncomePaymentType3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableIncomePaymentType3.setText("Cashflow Type");

        lableIncomeMethod3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableIncomeMethod3.setText("Payment Method");

        txtIncomeMethod3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeMethod3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Money", "Bank" }));
        txtIncomeMethod3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeMethod3.setBackground(new Color(255,255,255));

        lableincomeamount3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomeamount3.setText("Amount");

        txtIncomePaymentType3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomePaymentType3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Income", "Expenditure", "Budget", "Investment", "Assest", "Liability" }));
        txtIncomePaymentType3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomePaymentType3.setEnabled(false);
        txtIncomePaymentType3.setBackground(new Color(255,255,255));

        txtIncomeday3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeday3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtIncomeday3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeday3.setBackground(new Color(255,255,255));

        txtIncomeMonth3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeMonth3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtIncomeMonth3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeMonth3.setBackground(new Color(255,255,255));

        txtIncomeYear3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeYear3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtIncomeYear3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeYear3.setBackground(new Color(255,255,255));

        jScrollPaneIncome3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneIncome3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneIncome3.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneIncome3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableIncome3.setAutoCreateRowSorter(true);
        jTableIncome3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableIncome3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableIncome3.setModel(new javax.swing.table.DefaultTableModel(
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
                "Income ID", "Date", "Description", "Department", "Amount", "Debit / Credit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableIncome3.setGridColor(new java.awt.Color(102, 153, 255));
        jTableIncome3.setRowHeight(20);
        jTableIncome3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableIncome3MouseClicked(evt);
            }
        });
        jScrollPaneIncome3.setViewportView(jTableIncome3);

        jButtonResetIncome3.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetIncome3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetIncome3.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetIncome3.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetIncome3.setText("Reset");
        jButtonResetIncome3.setFocusable(false);
        jButtonResetIncome3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetIncome3.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetIncome3.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetIncome3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetIncome3ActionPerformed(evt);
            }
        });

        jButtonUpdateIncome2.setBackground(new java.awt.Color(255, 255, 255));
        jButtonUpdateIncome2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonUpdateIncome2.setForeground(new java.awt.Color(51, 153, 255));
        jButtonUpdateIncome2.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Updatebutton.png")); // NOI18N
        jButtonUpdateIncome2.setText("Update");
        jButtonUpdateIncome2.setFocusable(false);
        jButtonUpdateIncome2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonUpdateIncome2.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonUpdateIncome2.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonUpdateIncome2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateIncome2ActionPerformed(evt);
            }
        });

        jButtonFindIncome2.setBackground(new java.awt.Color(255, 255, 255));
        jButtonFindIncome2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonFindIncome2.setForeground(new java.awt.Color(51, 153, 255));
        jButtonFindIncome2.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Findbutton.png")); // NOI18N
        jButtonFindIncome2.setText("Find");
        jButtonFindIncome2.setFocusable(false);
        jButtonFindIncome2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonFindIncome2.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonFindIncome2.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonFindIncome2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindIncome2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout IncomePanel3Layout = new javax.swing.GroupLayout(IncomePanel3);
        IncomePanel3.setLayout(IncomePanel3Layout);
        IncomePanel3Layout.setHorizontalGroup(
            IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IncomePanel3Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IncomePanel3Layout.createSequentialGroup()
                            .addComponent(lableincomeid2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(txtIncomeId2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(IncomePanel3Layout.createSequentialGroup()
                            .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lableincomedescription3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lableincomedepartment3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(IncomePanel3Layout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addComponent(txtIncomeDescription3, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IncomePanel3Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtIncomeDepartment3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(IncomePanel3Layout.createSequentialGroup()
                        .addComponent(lableIncomeMethod3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIncomeMethod3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(IncomePanel3Layout.createSequentialGroup()
                            .addComponent(lableincomeamount3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(txtdIncomeAmount3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(IncomePanel3Layout.createSequentialGroup()
                            .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lableincomedate3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lableincomedebitcredit3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)
                            .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIncomeDebitCredit3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(IncomePanel3Layout.createSequentialGroup()
                                    .addComponent(txtIncomeday3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIncomeMonth3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIncomeYear3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtIncomePaymentType3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lableIncomePaymentType3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
            .addGroup(IncomePanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneIncome3)
                    .addGroup(IncomePanel3Layout.createSequentialGroup()
                        .addComponent(jButtonResetIncome3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonFindIncome2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUpdateIncome2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        IncomePanel3Layout.setVerticalGroup(
            IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IncomePanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIncomeId2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableincomeid2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdIncomeAmount3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableincomeamount3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIncomeDescription3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableincomedescription3)
                    .addComponent(lableincomedate3)
                    .addComponent(txtIncomeday3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIncomeMonth3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIncomeYear3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableincomedebitcredit3)
                    .addComponent(txtIncomeDebitCredit3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableIncomeMethod3)
                    .addComponent(txtIncomeMethod3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableincomedepartment3)
                    .addComponent(txtIncomeDepartment3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableIncomePaymentType3)
                    .addComponent(txtIncomePaymentType3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneIncome3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(IncomePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonResetIncome3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFindIncome2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdateIncome2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPaneIncome1.addTab("Search & Update", IncomePanel3);

        javax.swing.GroupLayout incomeUpdatePanelLayout = new javax.swing.GroupLayout(incomeUpdatePanel);
        incomeUpdatePanel.setLayout(incomeUpdatePanelLayout);
        incomeUpdatePanelLayout.setHorizontalGroup(
            incomeUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        incomeUpdatePanelLayout.setVerticalGroup(
            incomeUpdatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        expenditurePanel.setBackground(new java.awt.Color(255, 255, 255));
        expenditurePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        expenditurePanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jTabbedPaneExpenditure.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneExpenditure.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPaneExpenditure.setPreferredSize(new java.awt.Dimension(830, 548));

        ExpenditurePanel.setBackground(new java.awt.Color(255, 255, 255));
        ExpenditurePanel.setPreferredSize(new java.awt.Dimension(825, 519));

        lableExpensedescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpensedescription.setText("Description");

        txtExpenseDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableExpensedepartment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpensedepartment.setText("Department");

        txtExpenseDepartment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Finance", "Stock", "Room", "Restaurant", "HR", "Transport", "Event" }));
        txtExpenseDepartment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpenseDepartment.setBackground(new Color(255,255,255));

        lableExpenseMethod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpenseMethod.setText("Payment Method");

        txtExpenseMethod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Money", "Bank" }));
        txtExpenseMethod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpenseMethod.setBackground(new Color(255,255,255));

        txtExpensePaymentType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpensePaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expenditure", "Income", "Budget", "Investment", "Assest", "Liability" }));
        txtExpensePaymentType.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpensePaymentType.setEnabled(false);
        txtExpensePaymentType.setBackground(new Color(255,255,255));

        lableExpensePaymentType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpensePaymentType.setText("Cashflow Type");

        txtExpenseDebitCredit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseDebitCredit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit", "Debit" }));
        txtExpenseDebitCredit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpenseDebitCredit.setBackground(new Color(255,255,255));

        lableExpensedebitcredit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpensedebitcredit.setText("Debit / Credit");

        lableExpensedate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpensedate.setText("Date");

        txtExpenseAmount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableExpenseamount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpenseamount.setText("Amount");

        txtExpenseday.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseday.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtExpenseday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpenseday.setBackground(new Color(255,255,255));

        txtExpenseMonth.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtExpenseMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpenseMonth.setBackground(new Color(255,255,255));

        txtExpenseYear.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtExpenseYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpenseYear.setBackground(new Color(255,255,255));

        jScrollPaneExpense.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneExpense.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneExpense.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneExpense.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableAddExpense.setAutoCreateRowSorter(true);
        jTableAddExpense.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        jTableAddExpense.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableAddExpense.setModel(new javax.swing.table.DefaultTableModel(
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
                "Expense ID", "Date", "Description", "Department", "Amount", "Debit / Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAddExpense.setGridColor(new java.awt.Color(102, 153, 255));
        jTableAddExpense.setRowHeight(20);
        jTableAddExpense.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAddExpenseMouseClicked(evt);
            }
        });
        jScrollPaneExpense.setViewportView(jTableAddExpense);

        jButtonExpense.setBackground(new java.awt.Color(255, 255, 255));
        jButtonExpense.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonExpense.setForeground(new java.awt.Color(51, 153, 255));
        jButtonExpense.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Addbutton.png")); // NOI18N
        jButtonExpense.setText("Add");
        jButtonExpense.setFocusable(false);
        jButtonExpense.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonExpense.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonExpense.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExpenseActionPerformed(evt);
            }
        });

        jButtonResetExpense.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetExpense.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetExpense.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetExpense.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetExpense.setText("Reset");
        jButtonResetExpense.setFocusable(false);
        jButtonResetExpense.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetExpense.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetExpense.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetExpenseActionPerformed(evt);
            }
        });

        jButtonDemoExpense.setBackground(new java.awt.Color(255, 255, 255));
        jButtonDemoExpense.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonDemoExpense.setForeground(new java.awt.Color(51, 153, 255));
        jButtonDemoExpense.setText("Demo");
        jButtonDemoExpense.setFocusable(false);
        jButtonDemoExpense.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonDemoExpense.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonDemoExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDemoExpenseActionPerformed(evt);
            }
        });

        jButtonAcceptAllExpenditure.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAcceptAllExpenditure.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAcceptAllExpenditure.setForeground(new java.awt.Color(102, 153, 255));
        jButtonAcceptAllExpenditure.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\acceptbutton.png")); // NOI18N
        jButtonAcceptAllExpenditure.setText("   Accept");
        jButtonAcceptAllExpenditure.setFocusable(false);
        jButtonAcceptAllExpenditure.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAcceptAllExpenditure.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonAcceptAllExpenditure.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonAcceptAllExpenditure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcceptAllExpenditureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ExpenditurePanelLayout = new javax.swing.GroupLayout(ExpenditurePanel);
        ExpenditurePanel.setLayout(ExpenditurePanelLayout);
        ExpenditurePanelLayout.setHorizontalGroup(
            ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExpenditurePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneExpense)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ExpenditurePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonDemoExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonResetExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAcceptAllExpenditure, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(ExpenditurePanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ExpenditurePanelLayout.createSequentialGroup()
                        .addComponent(lableExpensePaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtExpensePaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ExpenditurePanelLayout.createSequentialGroup()
                        .addGroup(ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableExpensedescription, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableExpensedepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtExpenseDescription)
                            .addComponent(txtExpenseDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ExpenditurePanelLayout.createSequentialGroup()
                        .addComponent(lableExpenseMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtExpenseMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ExpenditurePanelLayout.createSequentialGroup()
                        .addComponent(lableExpenseamount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(txtExpenseAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ExpenditurePanelLayout.createSequentialGroup()
                        .addGroup(ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableExpensedate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableExpensedebitcredit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtExpenseDebitCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ExpenditurePanelLayout.createSequentialGroup()
                                .addComponent(txtExpenseday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtExpenseMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtExpenseYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(52, 52, 52))
        );
        ExpenditurePanelLayout.setVerticalGroup(
            ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExpenditurePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtExpenseAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableExpenseamount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpenseDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableExpensedescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableExpensedate)
                    .addComponent(txtExpenseday, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpenseMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpenseYear, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableExpenseMethod)
                    .addComponent(txtExpenseMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableExpensedebitcredit)
                    .addComponent(txtExpenseDebitCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableExpensedepartment)
                    .addComponent(txtExpenseDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableExpensePaymentType)
                    .addComponent(txtExpensePaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPaneExpense, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(ExpenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonResetExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDemoExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAcceptAllExpenditure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPaneExpenditure.addTab("Add Expenditure", ExpenditurePanel);

        javax.swing.GroupLayout expenditurePanelLayout = new javax.swing.GroupLayout(expenditurePanel);
        expenditurePanel.setLayout(expenditurePanelLayout);
        expenditurePanelLayout.setHorizontalGroup(
            expenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneExpenditure, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        expenditurePanelLayout.setVerticalGroup(
            expenditurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneExpenditure, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        expenditurePanel1.setBackground(new java.awt.Color(255, 255, 255));
        expenditurePanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        expenditurePanel1.setPreferredSize(new java.awt.Dimension(830, 550));

        jTabbedPaneExpenditure1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneExpenditure1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPaneExpenditure1.setPreferredSize(new java.awt.Dimension(830, 548));

        ExpenditurePanel1.setBackground(new java.awt.Color(255, 255, 255));
        ExpenditurePanel1.setPreferredSize(new java.awt.Dimension(825, 519));

        lableExpenseId1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpenseId1.setText("Expenditure ID");

        txtExpenseId1.setEditable(false);
        txtExpenseId1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseId1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableExpensedescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpensedescription1.setText("Description");

        txtExpenseDescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseDescription1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableExpensedepartment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpensedepartment1.setText("Department");

        txtExpenseDepartment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseDepartment1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Finance", "Stock", "Room", "Restaurant", "HR", "Transport", "Event" }));
        txtExpenseDepartment1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpenseDepartment1.setBackground(new Color(255,255,255));

        lableExpenseMethod1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpenseMethod1.setText("Payment Method");

        txtExpenseMethod1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseMethod1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Money", "Bank" }));
        txtExpenseMethod1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpenseMethod1.setBackground(new Color(255,255,255));

        txtExpensePaymentType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpensePaymentType1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expenditure", "Income", "Budget", "Investment", "Assest", "Liability" }));
        txtExpensePaymentType1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpensePaymentType1.setEnabled(false);
        txtExpensePaymentType1.setBackground(new Color(255,255,255));

        lableExpensePaymentType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpensePaymentType1.setText("Cashflow Type");

        txtExpenseDebitCredit1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseDebitCredit1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit", "Debit" }));
        txtExpenseDebitCredit1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpenseDebitCredit1.setBackground(new Color(255,255,255));

        lableExpensedebitcredit1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpensedebitcredit1.setText("Debit / Credit");

        lableExpensedate1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpensedate1.setText("Date");

        txtExpenseAmount1.setEditable(false);
        txtExpenseAmount1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseAmount1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableExpenseamount1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableExpenseamount1.setText("Amount");

        txtExpenseday1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseday1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtExpenseday1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpenseday1.setBackground(new Color(255,255,255));

        txtExpenseMonth1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtExpenseMonth1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpenseMonth1.setBackground(new Color(255,255,255));

        txtExpenseYear1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtExpenseYear1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtExpenseYear1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtExpenseYear1.setBackground(new Color(255,255,255));

        jScrollPaneExpense1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneExpense1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneExpense1.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneExpense1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableAddExpense1.setAutoCreateRowSorter(true);
        jTableAddExpense1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableAddExpense1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableAddExpense1.setModel(new javax.swing.table.DefaultTableModel(
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
                "Expense ID", "Date", "Description", "Department", "Amount", "Debit / Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAddExpense1.setGridColor(new java.awt.Color(102, 153, 255));
        jTableAddExpense1.setRowHeight(20);
        jTableAddExpense1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAddExpense1MouseClicked(evt);
            }
        });
        jScrollPaneExpense1.setViewportView(jTableAddExpense1);

        jButtonResetExpense1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetExpense1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetExpense1.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetExpense1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetExpense1.setText("Reset");
        jButtonResetExpense1.setFocusable(false);
        jButtonResetExpense1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetExpense1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetExpense1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetExpense1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetExpense1ActionPerformed(evt);
            }
        });

        jButtonUpdateExpense1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonUpdateExpense1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonUpdateExpense1.setForeground(new java.awt.Color(51, 153, 255));
        jButtonUpdateExpense1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Updatebutton.png")); // NOI18N
        jButtonUpdateExpense1.setText("Update");
        jButtonUpdateExpense1.setFocusable(false);
        jButtonUpdateExpense1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonUpdateExpense1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonUpdateExpense1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonUpdateExpense1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateExpense1ActionPerformed(evt);
            }
        });

        jButtonFindExpense1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonFindExpense1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonFindExpense1.setForeground(new java.awt.Color(51, 153, 255));
        jButtonFindExpense1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Findbutton.png")); // NOI18N
        jButtonFindExpense1.setText("Find");
        jButtonFindExpense1.setFocusable(false);
        jButtonFindExpense1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonFindExpense1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonFindExpense1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonFindExpense1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindExpense1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ExpenditurePanel1Layout = new javax.swing.GroupLayout(ExpenditurePanel1);
        ExpenditurePanel1.setLayout(ExpenditurePanel1Layout);
        ExpenditurePanel1Layout.setHorizontalGroup(
            ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExpenditurePanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(ExpenditurePanel1Layout.createSequentialGroup()
                            .addComponent(lableExpenseId1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtExpenseId1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(ExpenditurePanel1Layout.createSequentialGroup()
                            .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lableExpensedescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lableExpensedepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)
                            .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtExpenseDescription1)
                                .addComponent(txtExpenseDepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(ExpenditurePanel1Layout.createSequentialGroup()
                        .addComponent(lableExpenseMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtExpenseMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ExpenditurePanel1Layout.createSequentialGroup()
                        .addComponent(lableExpenseamount1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(txtExpenseAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ExpenditurePanel1Layout.createSequentialGroup()
                        .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableExpensedate1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableExpensedebitcredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtExpenseDebitCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ExpenditurePanel1Layout.createSequentialGroup()
                                .addComponent(txtExpenseday1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtExpenseMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtExpenseYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ExpenditurePanel1Layout.createSequentialGroup()
                        .addComponent(lableExpensePaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtExpensePaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
            .addGroup(ExpenditurePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneExpense1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ExpenditurePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonResetExpense1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonFindExpense1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonUpdateExpense1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        ExpenditurePanel1Layout.setVerticalGroup(
            ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExpenditurePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtExpenseId1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableExpenseId1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpenseAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableExpenseamount1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtExpenseDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableExpensedescription1)
                    .addComponent(lableExpensedate1)
                    .addComponent(txtExpenseday1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpenseMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExpenseYear1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableExpenseMethod1)
                        .addComponent(txtExpenseMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableExpensedebitcredit1)
                        .addComponent(txtExpenseDebitCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13)
                .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableExpensePaymentType1)
                        .addComponent(txtExpensePaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableExpensedepartment1)
                        .addComponent(txtExpenseDepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneExpense1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(ExpenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonResetExpense1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFindExpense1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdateExpense1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPaneExpenditure1.addTab("Search & Update", ExpenditurePanel1);

        javax.swing.GroupLayout expenditurePanel1Layout = new javax.swing.GroupLayout(expenditurePanel1);
        expenditurePanel1.setLayout(expenditurePanel1Layout);
        expenditurePanel1Layout.setHorizontalGroup(
            expenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneExpenditure1, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        expenditurePanel1Layout.setVerticalGroup(
            expenditurePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneExpenditure1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        cashflowPanel.setBackground(new java.awt.Color(255, 255, 255));
        cashflowPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        cashflowPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jTabbedPaneCashflow.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneCashflow.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPaneCashflow.setPreferredSize(new java.awt.Dimension(830, 548));

        CashflowPanel.setBackground(new java.awt.Color(255, 255, 255));
        CashflowPanel.setPreferredSize(new java.awt.Dimension(825, 519));

        lableCashflowdescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowdescription.setText("Description");

        txtCashflowDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableCashflowMethod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowMethod.setText("Payment Method");

        txtCashflowMethod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Money", "Bank" }));
        txtCashflowMethod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowMethod.setBackground(new Color(255,255,255));

        txtCashflowDebitCredit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowDebitCredit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit", "Debit" }));
        txtCashflowDebitCredit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowDebitCredit.setBackground(new Color(255,255,255));

        lableCashflowdebitcredit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowdebitcredit.setText("Debit / Credit");

        lableCashflowdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowdate.setText("Date");

        txtCashflowAmount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableCashflowamount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowamount.setText("Amount");

        txtCashflowday.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowday.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtCashflowday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowday.setBackground(new Color(255,255,255));

        txtCashflowMonth.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtCashflowMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowMonth.setBackground(new Color(255,255,255));

        txtCashflowYear.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtCashflowYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowYear.setBackground(new Color(255,255,255));

        jScrollPaneCashflow.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneCashflow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneCashflow.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneCashflow.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableCashflow.setAutoCreateRowSorter(true);
        jTableCashflow.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableCashflow.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableCashflow.setModel(new javax.swing.table.DefaultTableModel(
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
                "Cashflow ID", "Date", "Description", "Bank / Money", "Amount", "Debit / Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCashflow.setGridColor(new java.awt.Color(102, 153, 255));
        jTableCashflow.setRowHeight(20);
        jScrollPaneCashflow.setViewportView(jTableCashflow);

        jButtonAddCashflow.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAddCashflow.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAddCashflow.setForeground(new java.awt.Color(51, 153, 255));
        jButtonAddCashflow.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Addbutton.png")); // NOI18N
        jButtonAddCashflow.setText("Add");
        jButtonAddCashflow.setFocusable(false);
        jButtonAddCashflow.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAddCashflow.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonAddCashflow.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonAddCashflow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddCashflowActionPerformed(evt);
            }
        });

        jButtonResetCashflow.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetCashflow.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetCashflow.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetCashflow.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetCashflow.setText("Reset");
        jButtonResetCashflow.setFocusable(false);
        jButtonResetCashflow.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetCashflow.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetCashflow.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetCashflow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetCashflowActionPerformed(evt);
            }
        });

        jButtonDemoCashflow.setBackground(new java.awt.Color(255, 255, 255));
        jButtonDemoCashflow.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonDemoCashflow.setForeground(new java.awt.Color(51, 153, 255));
        jButtonDemoCashflow.setText("Demo");
        jButtonDemoCashflow.setFocusable(false);
        jButtonDemoCashflow.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonDemoCashflow.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonDemoCashflow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDemoCashflowActionPerformed(evt);
            }
        });

        lableCashflowDepartment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowDepartment.setText("Department");

        txtCashflowDepartment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Finance", "Event", "Room", "Stock", "HR", "Transport", "Restaurant" }));
        txtCashflowDepartment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowDepartment.setBackground(new Color(255,255,255));

        txtCashflowPaymentType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowPaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Income", "Expenditure" }));
        txtCashflowPaymentType.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowPaymentType.setBackground(new Color(255,255,255));

        lableCashflowPaymentType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowPaymentType.setText("Cashflow Type");

        javax.swing.GroupLayout CashflowPanelLayout = new javax.swing.GroupLayout(CashflowPanel);
        CashflowPanel.setLayout(CashflowPanelLayout);
        CashflowPanelLayout.setHorizontalGroup(
            CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CashflowPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneCashflow)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CashflowPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonDemoCashflow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonResetCashflow, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAddCashflow, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(CashflowPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CashflowPanelLayout.createSequentialGroup()
                        .addComponent(lableCashflowPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtCashflowPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CashflowPanelLayout.createSequentialGroup()
                        .addComponent(lableCashflowDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtCashflowDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CashflowPanelLayout.createSequentialGroup()
                        .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableCashflowdescription, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableCashflowMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCashflowDescription)
                            .addComponent(txtCashflowMethod, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CashflowPanelLayout.createSequentialGroup()
                        .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableCashflowamount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableCashflowdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CashflowPanelLayout.createSequentialGroup()
                                .addComponent(txtCashflowday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCashflowMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCashflowYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCashflowAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(CashflowPanelLayout.createSequentialGroup()
                        .addComponent(lableCashflowdebitcredit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtCashflowDebitCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );
        CashflowPanelLayout.setVerticalGroup(
            CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CashflowPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCashflowDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lableCashflowdescription))
                    .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCashflowAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lableCashflowamount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableCashflowMethod)
                        .addComponent(txtCashflowMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableCashflowdate)
                        .addComponent(txtCashflowday, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCashflowMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCashflowYear, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableCashflowDepartment)
                        .addComponent(txtCashflowDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableCashflowdebitcredit)
                        .addComponent(txtCashflowDebitCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableCashflowPaymentType)
                    .addComponent(txtCashflowPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPaneCashflow, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(CashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddCashflow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonResetCashflow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDemoCashflow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPaneCashflow.addTab("Add Cashflow", CashflowPanel);

        javax.swing.GroupLayout cashflowPanelLayout = new javax.swing.GroupLayout(cashflowPanel);
        cashflowPanel.setLayout(cashflowPanelLayout);
        cashflowPanelLayout.setHorizontalGroup(
            cashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneCashflow, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        cashflowPanelLayout.setVerticalGroup(
            cashflowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneCashflow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        cashflowPanel1.setBackground(new java.awt.Color(255, 255, 255));
        cashflowPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        cashflowPanel1.setPreferredSize(new java.awt.Dimension(830, 550));

        jTabbedPaneCashflow1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneCashflow1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPaneCashflow1.setPreferredSize(new java.awt.Dimension(830, 548));

        CashflowPanel1.setBackground(new java.awt.Color(255, 255, 255));
        CashflowPanel1.setPreferredSize(new java.awt.Dimension(825, 519));

        lableCashflowid1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowid1.setText("Cash Flow ID");

        lableCashflowdescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowdescription1.setText("Description");

        txtCashflowId1.setEditable(false);
        txtCashflowId1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowId1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        txtCashflowDescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowDescription1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableCashflowMethod1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowMethod1.setText("Payment Method");

        txtCashflowMethod1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowMethod1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Money", "Bank" }));
        txtCashflowMethod1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowMethod1.setBackground(new Color(255,255,255));

        txtCashflowDebitCredit1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowDebitCredit1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit", "Debit" }));
        txtCashflowDebitCredit1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowDebitCredit1.setBackground(new Color(255,255,255));

        lableCashflowdebitcredit1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowdebitcredit1.setText("Debit / Credit");

        lableCashflowdate1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowdate1.setText("Date");

        txtCashflowAmount1.setEditable(false);
        txtCashflowAmount1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowAmount1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableCashflowamount1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowamount1.setText("Amount");

        txtCashflowday1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowday1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtCashflowday1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowday1.setBackground(new Color(255,255,255));

        txtCashflowMonth1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtCashflowMonth1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowMonth1.setBackground(new Color(255,255,255));

        txtCashflowYear1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowYear1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtCashflowYear1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowYear1.setBackground(new Color(255,255,255));

        jScrollPaneCashflow1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneCashflow1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneCashflow1.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneCashflow1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableCashflow1.setAutoCreateRowSorter(true);
        jTableCashflow1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableCashflow1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableCashflow1.setModel(new javax.swing.table.DefaultTableModel(
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
                "Cashflow ID", "Date", "Description", "Bank / Money", "Amount", "Debit / Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCashflow1.setGridColor(new java.awt.Color(102, 153, 255));
        jTableCashflow1.setRowHeight(20);
        jTableCashflow1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCashflow1MouseClicked(evt);
            }
        });
        jScrollPaneCashflow1.setViewportView(jTableCashflow1);

        jButtonResetCashflow1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetCashflow1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetCashflow1.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetCashflow1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetCashflow1.setText("Reset");
        jButtonResetCashflow1.setFocusable(false);
        jButtonResetCashflow1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetCashflow1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetCashflow1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetCashflow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetCashflow1ActionPerformed(evt);
            }
        });

        lableCashflowDepartment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowDepartment1.setText("Department");

        txtCashflowDepartment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowDepartment1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Finance", "Event", "Room", "Stock", "HR", "Transport", "Restaurant" }));
        txtCashflowDepartment1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowDepartment1.setBackground(new Color(255,255,255));

        txtCashflowPaymentType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCashflowPaymentType1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Income", "Expenditure" }));
        txtCashflowPaymentType1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtCashflowPaymentType1.setEnabled(false);
        txtCashflowPaymentType1.setBackground(new Color(255,255,255));

        lableCashflowPaymentType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableCashflowPaymentType1.setText("Cashflow Type");

        jButtonUpdateCashflow1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonUpdateCashflow1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonUpdateCashflow1.setForeground(new java.awt.Color(51, 153, 255));
        jButtonUpdateCashflow1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Updatebutton.png")); // NOI18N
        jButtonUpdateCashflow1.setText("Update");
        jButtonUpdateCashflow1.setFocusable(false);
        jButtonUpdateCashflow1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonUpdateCashflow1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonUpdateCashflow1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonUpdateCashflow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateCashflow1ActionPerformed(evt);
            }
        });

        jButtonViewCashflow1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonViewCashflow1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonViewCashflow1.setForeground(new java.awt.Color(51, 153, 255));
        jButtonViewCashflow1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Findbutton.png")); // NOI18N
        jButtonViewCashflow1.setText("Find");
        jButtonViewCashflow1.setFocusable(false);
        jButtonViewCashflow1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonViewCashflow1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonViewCashflow1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonViewCashflow1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewCashflow1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CashflowPanel1Layout = new javax.swing.GroupLayout(CashflowPanel1);
        CashflowPanel1.setLayout(CashflowPanel1Layout);
        CashflowPanel1Layout.setHorizontalGroup(
            CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CashflowPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneCashflow1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CashflowPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonResetCashflow1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonViewCashflow1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonUpdateCashflow1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(CashflowPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CashflowPanel1Layout.createSequentialGroup()
                        .addComponent(lableCashflowDepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtCashflowDepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CashflowPanel1Layout.createSequentialGroup()
                            .addComponent(lableCashflowid1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtCashflowId1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(CashflowPanel1Layout.createSequentialGroup()
                            .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lableCashflowdescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lableCashflowMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtCashflowDescription1)
                                .addComponent(txtCashflowMethod1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CashflowPanel1Layout.createSequentialGroup()
                        .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableCashflowamount1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableCashflowdate1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CashflowPanel1Layout.createSequentialGroup()
                                .addComponent(txtCashflowday1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCashflowMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCashflowYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCashflowAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(CashflowPanel1Layout.createSequentialGroup()
                        .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CashflowPanel1Layout.createSequentialGroup()
                                .addComponent(lableCashflowdebitcredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CashflowPanel1Layout.createSequentialGroup()
                                .addComponent(lableCashflowPaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)))
                        .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCashflowPaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCashflowDebitCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(61, 61, 61))
        );
        CashflowPanel1Layout.setVerticalGroup(
            CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CashflowPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCashflowId1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableCashflowid1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCashflowAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableCashflowamount1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCashflowDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableCashflowdescription1)
                    .addComponent(lableCashflowdate1)
                    .addComponent(txtCashflowday1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCashflowMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCashflowYear1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableCashflowMethod1)
                    .addComponent(txtCashflowMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableCashflowdebitcredit1)
                    .addComponent(txtCashflowDebitCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableCashflowPaymentType1)
                        .addComponent(txtCashflowPaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableCashflowDepartment1)
                        .addComponent(txtCashflowDepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPaneCashflow1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(CashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonResetCashflow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdateCashflow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonViewCashflow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPaneCashflow1.addTab("Search & Update", CashflowPanel1);

        javax.swing.GroupLayout cashflowPanel1Layout = new javax.swing.GroupLayout(cashflowPanel1);
        cashflowPanel1.setLayout(cashflowPanel1Layout);
        cashflowPanel1Layout.setHorizontalGroup(
            cashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneCashflow1, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        cashflowPanel1Layout.setVerticalGroup(
            cashflowPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneCashflow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout togglePanelsLayout = new javax.swing.GroupLayout(togglePanels);
        togglePanels.setLayout(togglePanelsLayout);
        togglePanelsLayout.setHorizontalGroup(
            togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(expenditurePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(incomeUpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(incomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(cashflowPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(cashflowPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(expenditurePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        togglePanelsLayout.setVerticalGroup(
            togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(expenditurePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(incomeUpdatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(incomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(cashflowPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(cashflowPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(togglePanelsLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(expenditurePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        financeManagerPanel.add(togglePanels, new org.netbeans.lib.awtextra.AbsoluteConstraints(192, 135, -1, -1));

        jDesktopPaneFinance.add(financeManagerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPaneFinance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPaneFinance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncomeActionPerformed
        financepanelshift(buttonIncome.getText());
        financeincometableload();
        jButtonResetIncome.doClick(); jButtonResetIncome3.doClick();
    }//GEN-LAST:event_buttonIncomeActionPerformed

    private void buttonExpenditureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExpenditureActionPerformed
        financepanelshift(buttonExpenditure.getText());
        financeexpensetableaoad();  
        jButtonResetExpense.doClick(); jButtonResetExpense1.doClick();
    }//GEN-LAST:event_buttonExpenditureActionPerformed

    private void buttonCashflowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCashflowActionPerformed
        financepanelshift(buttonCashflow.getText());
        financecashflowtableload(); 
        jButtonResetCashflow.doClick(); jButtonResetCashflow1.doClick();
    }//GEN-LAST:event_buttonCashflowActionPerformed
    public void clock(){
        Thread clock = new Thread(){
            public void run(){
                try{
                    for(;;){
                    Calendar cal=new GregorianCalendar();
                    Time.setText(cal.get(Calendar.HOUR)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND)+" / "+cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DATE));
                    sleep(1000);
                    }
                }catch(Exception e){ 
                    
                }
            }
        };
        clock.start();
    }
    private void jButtonlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonlogoutActionPerformed
        int result=JOptionPane.showConfirmDialog(financeManagerPanel,"Do you want to Log Out from the system?","Logout Confirmation",0);
        if(result==0){
            data.adduserlogs(USER, "System Logout", "Logout",0);
            System_Login login=new System_Login();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonlogoutActionPerformed

    private void buttonIncomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncomeMouseEntered
        buttonIncome.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonIncomeMouseEntered

    private void buttonIncomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncomeMouseExited
        buttonIncome.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonIncomeMouseExited

    private void buttonExpenditureMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExpenditureMouseEntered
        buttonExpenditure.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonExpenditureMouseEntered

    private void buttonExpenditureMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExpenditureMouseExited
        buttonExpenditure.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonExpenditureMouseExited

    private void buttonCashflowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCashflowMouseEntered
        buttonCashflow.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonCashflowMouseEntered

    private void buttonCashflowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCashflowMouseExited
        buttonCashflow.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonCashflowMouseExited

    private void jButtonResetIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetIncomeActionPerformed
        txtIncomeDescription.setText("");
        txtdIncomeAmount.setText("");
        txtIncomeDepartment.setSelectedItem("Finance");
        txtIncomeMethod.setSelectedItem("Money");
        txtIncomeDebitCredit.setSelectedItem("Debit");
        txtIncomeday.setSelectedItem("Day");
        txtIncomeMonth.setSelectedItem("Month");
        txtIncomeYear.setSelectedItem("Year");
    }//GEN-LAST:event_jButtonResetIncomeActionPerformed

    private void jButtonResetExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetExpenseActionPerformed
        txtExpenseDescription.setText("");
        txtExpenseAmount.setText("");
        txtExpenseDepartment.setSelectedItem("Finance");
        txtExpenseMethod.setSelectedItem("Money");
        txtExpenseDebitCredit.setSelectedItem("Credit");
        txtExpenseday.setSelectedItem("Day");
        txtExpenseMonth.setSelectedItem("Month");
        txtExpenseYear.setSelectedItem("Year");
    }//GEN-LAST:event_jButtonResetExpenseActionPerformed

    private void jButtonResetCashflowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetCashflowActionPerformed
        txtCashflowDescription.setText("");
        txtCashflowAmount.setText("");
        txtCashflowDepartment.setSelectedItem("Finance");
        txtCashflowMethod.setSelectedItem("Money");
        txtCashflowDebitCredit.setSelectedItem("Credit");
        txtCashflowday.setSelectedItem("Day");
        txtCashflowMonth.setSelectedItem("Month");
        txtCashflowYear.setSelectedItem("Year");
        txtCashflowPaymentType.setSelectedItem("Income");
    }//GEN-LAST:event_jButtonResetCashflowActionPerformed

    private void jButtonDemoIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDemoIncomeActionPerformed
        txtIncomeDescription.setText("Food Item");
        txtdIncomeAmount.setText("1000");
        txtIncomeDepartment.setSelectedItem("Restaurent");
        txtIncomeMethod.setSelectedItem("Money");
        txtIncomeDebitCredit.setSelectedItem("Debit");
        txtIncomeday.setSelectedItem("24");
        txtIncomeMonth.setSelectedItem("08");
        txtIncomeYear.setSelectedItem("2016");
    }//GEN-LAST:event_jButtonDemoIncomeActionPerformed

    private void jButtonDemoExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDemoExpenseActionPerformed
        txtExpenseDescription.setText("Decoration charges");
        txtExpenseAmount.setText("15000");
        txtExpenseDepartment.setSelectedItem("Event");
        txtExpenseMethod.setSelectedItem("Bank");
        txtExpenseDebitCredit.setSelectedItem("Credit");
        txtExpenseday.setSelectedItem("24");
        txtExpenseMonth.setSelectedItem("08");
        txtExpenseYear.setSelectedItem("2016");
    }//GEN-LAST:event_jButtonDemoExpenseActionPerformed

    private void jButtonDemoCashflowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDemoCashflowActionPerformed
        txtCashflowDescription.setText("Electricity Bill");
        txtCashflowAmount.setText("12000");
        txtCashflowDepartment.setSelectedItem("Restaurent");
        txtCashflowMethod.setSelectedItem("Bank");
        txtCashflowDebitCredit.setSelectedItem("Debit");
        txtCashflowday.setSelectedItem("24");
        txtCashflowMonth.setSelectedItem("08");
        txtCashflowYear.setSelectedItem("2016");
        txtCashflowPaymentType.setSelectedItem("Expenditure");
    }//GEN-LAST:event_jButtonDemoCashflowActionPerformed

    private void jButtonIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIncomeActionPerformed
        try{
            description=txtIncomeDescription.getText();                             //getting values from interface
            paymentMethod=txtIncomeMethod.getSelectedItem().toString();
            department=txtIncomeDepartment.getSelectedItem().toString();
            cashflowType=txtIncomePaymentType.getSelectedItem().toString();
            amountString=txtdIncomeAmount.getText();
            day=txtIncomeday.getSelectedItem().toString();
            month=txtIncomeMonth.getSelectedItem().toString();
            year=txtIncomeYear.getSelectedItem().toString();
            incomeStatus=txtIncomeDebitCredit.getSelectedItem().toString();
            cashflowApproval="approved";
            //---------------------------------------------------------------------------------------------------------------------------
            if(description.isEmpty())
                txtIncomeDescription.setBackground(new Color(255,150,150));         //validations of text fields
            else
                txtIncomeDescription.setBackground(new Color(255,255,255));
            if(day.equals("Day"))
                txtIncomeday.setBackground(new Color(255,150,150));
            else
                txtIncomeday.setBackground(new Color(255,255,255));
            if(month.equals("Month"))
                txtIncomeMonth.setBackground(new Color(255,150,150));
            else
                txtIncomeMonth.setBackground(new Color(255,255,255));
            if(year.equals("Year"))
                txtIncomeYear.setBackground(new Color(255,150,150));
            else
                txtIncomeYear.setBackground(new Color(255,255,255));
            if(amountString.isEmpty())
                txtdIncomeAmount.setBackground(new Color(255,150,150));
            else{
                amount=Double.parseDouble(amountString);
                txtdIncomeAmount.setBackground(new Color(255,255,255));
            }
            if(amountString.isEmpty()||description.isEmpty()||day.equals("Day")||month.equals("Month")||year.equals("Year")){
                JOptionPane.showMessageDialog(IncomePanel,"Please fill all the fields!","Form Incomplete!",0);
            }
            if(!amountString.isEmpty()&&!description.isEmpty()&&!day.equals("Day")&&!month.equals("Month")&&!year.equals("Year")){
                //---------------------------------------------------------------------------------------------------------------------------
                if(incomeStatus.equals("Debit"))                                    // this is according to the low of accounts
                    paymentStatus="Credit";
                else
                    paymentStatus="Debit";
                //---------------------------------------------------------------------------------------------------------------------------
                int r=JOptionPane.showConfirmDialog(IncomePanel, "Are You Sure You Want To Continue?", "Confirm Add Entry!",0,2);
                if(r==0){
                    if(data.addIncome( year,  month,  day,  description,  department,  paymentMethod,  amount,  cashflowType,  paymentStatus,  cashflowApproval,  cashflowId,  incomeStatus)){
                        data.adduserlogs(USER, "Income", "Add", data.getlastid("fin_income","income_id"));
                        jButtonResetIncome.doClick();
                        JOptionPane.showMessageDialog(IncomePanel,"Successfully Added To The Database!","Success Message!",1);
                        financeincometableload();
                    }
                    else
                        JOptionPane.showMessageDialog(IncomePanel,"Please Check Data again!","Error Message!",0);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(IncomePanel,"Please Check Data again!","Error Message!",0);
        }
    }//GEN-LAST:event_jButtonIncomeActionPerformed

    private void jButtonExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExpenseActionPerformed
        try{
            description=txtExpenseDescription.getText();                             //getting values from interface
            paymentMethod=txtExpenseMethod.getSelectedItem().toString();
            department=txtExpenseDepartment.getSelectedItem().toString();
            cashflowType=txtExpensePaymentType.getSelectedItem().toString();
            amountString=txtExpenseAmount.getText();
            day=txtExpenseday.getSelectedItem().toString();
            month=txtExpenseMonth.getSelectedItem().toString();
            year=txtExpenseYear.getSelectedItem().toString();
            expenditureStatus=txtExpenseDebitCredit.getSelectedItem().toString();
            cashflowApproval="approved";
            //---------------------------------------------------------------------------------------------------------------------------

            if(description.isEmpty())
                txtExpenseDescription.setBackground(new Color(255,150,150));         //validations of text fields
            else
                txtExpenseDescription.setBackground(new Color(255,255,255));
            if(day.equals("Day"))
                txtExpenseday.setBackground(new Color(255,150,150));
            else
                txtExpenseday.setBackground(new Color(255,255,255));
            if(month.equals("Month"))
                txtExpenseMonth.setBackground(new Color(255,150,150));
            else
                txtExpenseMonth.setBackground(new Color(255,255,255));
            if(year.equals("Year"))
                txtExpenseYear.setBackground(new Color(255,150,150));
            else
                txtExpenseYear.setBackground(new Color(255,255,255));
            if(amountString.isEmpty())
                txtExpenseAmount.setBackground(new Color(255,150,150));
            else{
                amount=Double.parseDouble(amountString);
                txtExpenseAmount.setBackground(new Color(255,255,255));
            }
            if(amountString.isEmpty()||description.isEmpty()||day.equals("Day")||month.equals("Month")||year.equals("Year")){
                JOptionPane.showMessageDialog(expenditurePanel,"Please fill all the fields!","Form Incomplete!",0);
            }
            if(!amountString.isEmpty()&&!description.isEmpty()&&!day.equals("Day")&&!month.equals("Month")&&!year.equals("Year")){
                //---------------------------------------------------------------------------------------------------------------------------
                if(expenditureStatus.equals("Credit"))                              // this is according to the low of accounts
                    paymentStatus="Debit";
                else
                    paymentStatus="Credit";
                //---------------------------------------------------------------------------------------------------------------------------
                budgetBalance=data.balanceChecking(department,cashflowType,amount, day, month);
                if(budgetBalance>=amount){
                    int r=JOptionPane.showConfirmDialog(IncomePanel, "Are You Sure You Want To Continue?", "Confirm Add Entry!",0,2);
                    if(r==0){
                        if(data.addExpense(year,  month,  day,  description,  department,  paymentMethod,  amount,  cashflowType,  paymentStatus,  cashflowApproval,  cashflowId,  expenditureStatus)){
                            data.adduserlogs(USER, "Expenditure", "Add", data.getlastid("fin_expenditure","expense_id"));
                            jButtonResetExpense.doClick();
                            JOptionPane.showMessageDialog(expenditurePanel,"Successfully Added To The Database!","Success Message!",1);
                            financeexpensetableaoad();
                        }
                        else
                            JOptionPane.showMessageDialog(expenditurePanel,"Please Check Data again!","Error Message!",0);
                    }
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(expenditurePanel,"Please Check Data again!","Error Message!",0);
        }
    }//GEN-LAST:event_jButtonExpenseActionPerformed

    private void jButtonAddCashflowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddCashflowActionPerformed
        try{
            description=txtCashflowDescription.getText();                             //getting values from interface
            paymentMethod=txtCashflowMethod.getSelectedItem().toString();
            department=txtCashflowDepartment.getSelectedItem().toString();
            cashflowType=txtCashflowPaymentType.getSelectedItem().toString();
            amountString=txtCashflowAmount.getText();
            day=txtCashflowday.getSelectedItem().toString();
            month=txtCashflowMonth.getSelectedItem().toString();
            year=txtCashflowYear.getSelectedItem().toString();
            paymentStatus=txtCashflowDebitCredit.getSelectedItem().toString();
            cashflowApproval="not approved";
            //---------------------------------------------------------------------------------------------------------------------------

            if(description.isEmpty())
                txtCashflowDescription.setBackground(new Color(255,150,150));         //validations of text fields
            else
                txtCashflowDescription.setBackground(new Color(255,255,255));
            if(day.equals("Day"))
                txtCashflowday.setBackground(new Color(255,150,150));
            else
                txtCashflowday.setBackground(new Color(255,255,255));
            if(month.equals("Month"))
                txtCashflowMonth.setBackground(new Color(255,150,150));
            else
                txtCashflowMonth.setBackground(new Color(255,255,255));
            if(year.equals("Year"))
                txtCashflowYear.setBackground(new Color(255,150,150));
            else
                txtCashflowYear.setBackground(new Color(255,255,255));
            if(amountString.isEmpty())
                txtCashflowAmount.setBackground(new Color(255,150,150));
            else{
                amount=Double.parseDouble(amountString);
                txtCashflowAmount.setBackground(new Color(255,255,255));
            }
            if(amountString.isEmpty()||description.isEmpty()||day.equals("Day")||month.equals("Month")||year.equals("Year")){
                JOptionPane.showMessageDialog(IncomePanel,"Please fill all the fields!","Form Incomplete!",0);
            }
            if(!amountString.isEmpty()&&!description.isEmpty()&&!day.equals("Day")&&!month.equals("Month")&&!year.equals("Year")){
                int r=JOptionPane.showConfirmDialog(IncomePanel, "Are You Sure You Want To Continue?", "Confirm Add Entry!",0,2);
                if(r==0){
                    if(cashflowType.equals("Expenditure")){
                        budgetBalance=data.balanceChecking(department,cashflowType,amount, month, year);
                        if(budgetBalance>=amount){
                            if(data.addCashflow(year,  month,  day,  description,  department,  paymentMethod,  amount,  cashflowType,  paymentStatus,  cashflowApproval,  cashflowId)){
                                data.adduserlogs(USER, "Cashflow", "Add", data.getlastid("fin_cashflow", "cashflow_id"));
                                jButtonResetCashflow.doClick();
                                JOptionPane.showMessageDialog(cashflowPanel,"Successfully Added To The Database!","Success Message!",1);
                                financecashflowtableload();
                            }
                            else
                                JOptionPane.showMessageDialog(cashflowPanel,"Please Check Data again!","Error Message!",0);
                        }
                    }
                    else{
                        if(data.addCashflow(year,  month,  day,  description,  department,  paymentMethod,  amount,  cashflowType,  paymentStatus,  cashflowApproval,  cashflowId)){
                                data.adduserlogs(USER, "Cashflow", "Add", data.getlastid("fin_cashflow", "cashflow_id"));
                                jButtonResetCashflow.doClick();
                                JOptionPane.showMessageDialog(cashflowPanel,"Successfully Added To The Database!","Success Message!",1);
                                financecashflowtableload();
                            }
                            else
                                JOptionPane.showMessageDialog(cashflowPanel,"Please Check Data again!","Error Message!",0);
                    }
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(cashflowPanel,"Please Check Data again!","Error Message!",0);
        }
    }//GEN-LAST:event_jButtonAddCashflowActionPerformed

    private void jButtonAcceptAllIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcceptAllIncomeActionPerformed
        accept(cashflowId,"Income", "fin_income", "income_cashflow_id", "income_payment_status"); cashflowId=0;
        data.adduserlogs(USER, "Income", "Accept", data.getlastid("fin_income","income_id"));
    }//GEN-LAST:event_jButtonAcceptAllIncomeActionPerformed

    private void jButtonAcceptAllExpenditureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcceptAllExpenditureActionPerformed
        accept(cashflowId,"Expenditure", "fin_expenditure", "expense_cashflow_id", "expense_payment_status"); cashflowId=0;
        data.adduserlogs(USER, "Expenditure", "Accept", data.getlastid("fin_expenditure","expense_id"));        
    }//GEN-LAST:event_jButtonAcceptAllExpenditureActionPerformed

    private void jTableIncomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableIncomeMouseClicked
        int row= jTableIncome.getSelectedRow(); 
        cashflowId=Integer.parseInt(jTableIncome.getValueAt(row, 0).toString());
        day= jTableIncome.getValueAt(row, 1).toString().substring(8, 10);          txtIncomeday.setSelectedItem(day);
        month=jTableIncome.getValueAt(row, 1).toString().substring(5, 7);          txtIncomeMonth.setSelectedItem(month);
        year=jTableIncome.getValueAt(row, 1).toString().substring(0, 4);           txtIncomeYear.setSelectedItem(year);
        description=jTableIncome.getValueAt(row,2).toString();                     txtIncomeDescription.setText(description);
        department= jTableIncome.getValueAt(row, 3).toString();                    txtIncomeDepartment.setSelectedItem(department);
        paymentMethod= jTableIncome.getValueAt(row, 4).toString();                 txtIncomeMethod.setSelectedItem(paymentMethod);
        amount= Double.parseDouble(jTableIncome.getValueAt(row, 5).toString());    txtdIncomeAmount.setText(Double.toString(amount));
        incomeStatus= jTableIncome.getValueAt(row, 6).toString();                  txtIncomeDebitCredit.setSelectedItem(incomeStatus);
    }//GEN-LAST:event_jTableIncomeMouseClicked

    private void jTableAddExpenseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAddExpenseMouseClicked
        int row= jTableAddExpense.getSelectedRow(); 
        cashflowId=Integer.parseInt(jTableAddExpense.getValueAt(row, 0).toString());
        day= jTableAddExpense.getValueAt(row, 1).toString().substring(8, 10);          txtExpenseday.setSelectedItem(day);
        month=jTableAddExpense.getValueAt(row, 1).toString().substring(5, 7);          txtExpenseMonth.setSelectedItem(month);
        year=jTableAddExpense.getValueAt(row, 1).toString().substring(0, 4);           txtExpenseYear.setSelectedItem(year);
        description=jTableAddExpense.getValueAt(row,2).toString();                     txtExpenseDescription.setText(description);
        department= jTableAddExpense.getValueAt(row, 3).toString();                    txtExpenseDepartment.setSelectedItem(department);
        paymentMethod= jTableAddExpense.getValueAt(row, 4).toString();                 txtExpenseMethod.setSelectedItem(paymentMethod);
        amount= Double.parseDouble(jTableAddExpense.getValueAt(row, 5).toString());    txtExpenseAmount.setText(Double.toString(amount));
        expenditureStatus= jTableAddExpense.getValueAt(row, 6).toString();             txtExpenseDebitCredit.setSelectedItem(expenditureStatus);
    }//GEN-LAST:event_jTableAddExpenseMouseClicked

    private void jButtonlogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonlogoutMouseEntered
        jButtonlogout.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_jButtonlogoutMouseEntered

    private void jButtonlogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonlogoutMouseExited
        jButtonlogout.setBackground(new Color(42,135,235));

    }//GEN-LAST:event_jButtonlogoutMouseExited

    private void jTableIncome3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableIncome3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableIncome3MouseClicked

    private void jButtonResetIncome3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetIncome3ActionPerformed
        txtIncomeId2.setText("");
        txtIncomeDescription3.setText("");
        txtdIncomeAmount3.setText("");
        txtIncomeDepartment3.setSelectedItem("Finance");
        txtIncomeMethod3.setSelectedItem("Money");
        txtIncomeDebitCredit3.setSelectedItem("Debit");
        txtIncomeday3.setSelectedItem("Day");
        txtIncomeMonth3.setSelectedItem("Month");
        txtIncomeYear3.setSelectedItem("Year");
    }//GEN-LAST:event_jButtonResetIncome3ActionPerformed

    private void jButtonUpdateIncome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateIncome2ActionPerformed
        try{
            incomeId=Integer.parseInt(txtIncomeId2.getText());
            amount=Double.parseDouble(txtdIncomeAmount3.getText());
            description=txtIncomeDescription3.getText();
            incomeStatus=txtIncomeDebitCredit3.getSelectedItem().toString();
            department=txtIncomeDepartment3.getSelectedItem().toString();
            paymentMethod=txtIncomeMethod3.getSelectedItem().toString();
            year=txtIncomeYear3.getSelectedItem().toString();
            month=txtIncomeMonth3.getSelectedItem().toString();
            day=txtIncomeday3.getSelectedItem().toString();
            int r=JOptionPane.showConfirmDialog(IncomePanel, "Are You Sure You Want To Continue?", "Confirm Update Entry!",0,2);
            if(r==0){
                if(data.financeUpdate(cashflowId, incomeId, year, month, day, description, department, paymentMethod, paymentStatus, incomeStatus,"income","income")){
                    JOptionPane.showMessageDialog(IncomePanel, "Succesfully Updated!","Success Message",1);
                    data.adduserlogs(USER, "Income", "Update", incomeId);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(togglePanels,"Enter Correct Values!","Input Error",0);
            }
        financeincometableload();
    }//GEN-LAST:event_jButtonUpdateIncome2ActionPerformed

    private void jButtonFindIncome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindIncome2ActionPerformed
        financeSearch("income");
    }//GEN-LAST:event_jButtonFindIncome2ActionPerformed

    private void buttonIncome1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonIncome1MouseEntered

    private void buttonIncome1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonIncome1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonIncome1MouseExited

    private void buttonIncome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncome1ActionPerformed
        financepanelshift(buttonIncome1.getText());
        financeincometableload();
        jButtonResetIncome.doClick(); jButtonResetIncome3.doClick();
    }//GEN-LAST:event_buttonIncome1ActionPerformed

    private void buttonExpenditure1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExpenditure1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonExpenditure1MouseEntered

    private void buttonExpenditure1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExpenditure1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonExpenditure1MouseExited

    private void buttonExpenditure1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExpenditure1ActionPerformed
         financepanelshift(buttonExpenditure1.getText());
        financeexpensetableaoad();  
        jButtonResetExpense.doClick(); jButtonResetExpense1.doClick();
    }//GEN-LAST:event_buttonExpenditure1ActionPerformed

    private void buttonCashflow1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCashflow1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCashflow1MouseEntered

    private void buttonCashflow1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonCashflow1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonCashflow1MouseExited

    private void buttonCashflow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCashflow1ActionPerformed
        financepanelshift(buttonCashflow1.getText());
        financecashflowtableload(); 
        jButtonResetCashflow.doClick(); jButtonResetCashflow1.doClick();       // TODO add your handling code here:
    }//GEN-LAST:event_buttonCashflow1ActionPerformed

    private void jTableAddExpense1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAddExpense1MouseClicked
        int row= jTableAddExpense1.getSelectedRow();
        cashflowId=Integer.parseInt(jTableAddExpense1.getValueAt(row, 0).toString());
        expenditureId=Integer.parseInt(jTableAddExpense1.getValueAt(row, 1).toString());txtExpenseId1.setText(Integer.toString(expenditureId));
        day= jTableAddExpense1.getValueAt(row, 2).toString().substring(8, 10);          txtExpenseday1.setSelectedItem(day);
        month=jTableAddExpense1.getValueAt(row, 2).toString().substring(5, 7);          txtExpenseMonth1.setSelectedItem(month);
        year=jTableAddExpense1.getValueAt(row, 2).toString().substring(0, 4);           txtExpenseYear1.setSelectedItem(year);
        description=jTableAddExpense1.getValueAt(row,3).toString();                     txtExpenseDescription1.setText(description);
        department= jTableAddExpense1.getValueAt(row, 4).toString();                    txtExpenseDepartment1.setSelectedItem(department);
        paymentMethod= jTableAddExpense1.getValueAt(row, 5).toString();                 txtExpenseMethod1.setSelectedItem(paymentMethod);
        amount= Double.parseDouble(jTableAddExpense1.getValueAt(row, 6).toString());    txtExpenseAmount1.setText(Double.toString(amount));
        expenditureStatus= jTableAddExpense1.getValueAt(row, 7).toString();             txtExpenseDebitCredit1.setSelectedItem(expenditureStatus);
    }//GEN-LAST:event_jTableAddExpense1MouseClicked

    private void jButtonResetExpense1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetExpense1ActionPerformed
        txtExpenseId1.setText("");
        txtExpenseDescription1.setText("");
        txtExpenseAmount1.setText("");
        txtExpenseDepartment1.setSelectedItem("Finance");
        txtExpenseMethod1.setSelectedItem("Money");
        txtExpenseDebitCredit1.setSelectedItem("Credit");
        txtExpenseday1.setSelectedItem("Day");
        txtExpenseMonth1.setSelectedItem("Month");
        txtExpenseYear1.setSelectedItem("Year");
    }//GEN-LAST:event_jButtonResetExpense1ActionPerformed

    private void jButtonUpdateExpense1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateExpense1ActionPerformed
        try{
            amount=Double.parseDouble(txtExpenseAmount1.getText());
            description=txtExpenseDescription1.getText();
            incomeStatus=txtExpenseDebitCredit1.getSelectedItem().toString();
            department=txtExpenseDepartment1.getSelectedItem().toString();
            paymentMethod=txtExpenseMethod1.getSelectedItem().toString();
            year=txtExpenseYear1.getSelectedItem().toString();
            month=txtExpenseMonth1.getSelectedItem().toString();
            day=txtExpenseday1.getSelectedItem().toString();
            int r=JOptionPane.showConfirmDialog(IncomePanel, "Are You Sure You Want To Continue?", "Confirm Update Entry!",0,2);
            if(r==0){
                if(data.financeUpdate(cashflowId, expenditureId, year, month, day, description, department, paymentMethod, paymentStatus, expenditureStatus,"expenditure","expense")){
                    JOptionPane.showMessageDialog(IncomePanel, "Succesfully Updated!","Success Message",1);
                    data.adduserlogs(USER, "Expenditure", "Update", expenditureId);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(togglePanels,"Enter Correct Values!","Input Error",0);
        }
        financeexpensetableaoad();
    }//GEN-LAST:event_jButtonUpdateExpense1ActionPerformed

    private void jButtonFindExpense1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindExpense1ActionPerformed
        financeSearch("expense");
    }//GEN-LAST:event_jButtonFindExpense1ActionPerformed

    private void jTableCashflow1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCashflow1MouseClicked
        int row= jTableCashflow1.getSelectedRow();
        cashflowId=Integer.parseInt(jTableCashflow1.getValueAt(row, 0).toString());   txtCashflowId1.setText(Integer.toString(cashflowId));
        day= jTableCashflow1.getValueAt(row, 1).toString().substring(8, 10);          txtCashflowday1.setSelectedItem(day);
        month=jTableCashflow1.getValueAt(row, 1).toString().substring(5, 7);          txtCashflowMonth1.setSelectedItem(month);
        year=jTableCashflow1.getValueAt(row, 1).toString().substring(0, 4);           txtCashflowYear1.setSelectedItem(year);
        description=jTableCashflow1.getValueAt(row,2).toString();                     txtCashflowDescription1.setText(description);
        department= jTableCashflow1.getValueAt(row, 3).toString();                    txtCashflowDepartment1.setSelectedItem(department);
        paymentMethod= jTableCashflow1.getValueAt(row, 4).toString();                 txtCashflowMethod1.setSelectedItem(paymentMethod);
        amount= Double.parseDouble(jTableCashflow1.getValueAt(row, 5).toString());    txtCashflowAmount1.setText(Double.toString(amount));
        paymentStatus= jTableCashflow1.getValueAt(row, 6).toString();                 txtCashflowDebitCredit1.setSelectedItem(paymentStatus);
        cashflowType= jTableCashflow1.getValueAt(row, 7).toString();                  txtCashflowPaymentType1.setSelectedItem(cashflowType);
    }//GEN-LAST:event_jTableCashflow1MouseClicked

    private void jButtonResetCashflow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetCashflow1ActionPerformed
        txtCashflowId1.setText("");
        txtCashflowDescription1.setText("");
        txtCashflowAmount1.setText("");
        txtCashflowDepartment1.setSelectedItem("Finance");
        txtCashflowMethod1.setSelectedItem("Money");
        txtCashflowDebitCredit1.setSelectedItem("Credit");
        txtCashflowday1.setSelectedItem("Day");
        txtCashflowMonth1.setSelectedItem("Month");
        txtCashflowYear1.setSelectedItem("Year");
        txtCashflowPaymentType1.setSelectedItem("Income");
    }//GEN-LAST:event_jButtonResetCashflow1ActionPerformed

    private void jButtonUpdateCashflow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateCashflow1ActionPerformed
        try{
            amount=Double.parseDouble(txtCashflowAmount1.getText());
            description=txtCashflowDescription1.getText();
            paymentStatus=txtCashflowDebitCredit1.getSelectedItem().toString();
            department=txtCashflowDepartment1.getSelectedItem().toString();
            paymentMethod=txtCashflowMethod1.getSelectedItem().toString();
            year=txtCashflowYear1.getSelectedItem().toString();
            month=txtCashflowMonth1.getSelectedItem().toString();
            day=txtCashflowday1.getSelectedItem().toString();
            cashflowType= txtCashflowPaymentType1.getSelectedItem().toString();
            colName=cashflowType;
            if(cashflowType.equals("Expenditure"))
            colName="expense";
            int r=JOptionPane.showConfirmDialog(IncomePanel, "Are You Sure You Want To Continue?", "Confirm Update Entry!",0,2);
            if(r==0){
                if(data.financeCashflowUpdate(cashflowId, year, month, day, description, department, paymentMethod, paymentStatus, incomeStatus, cashflowType, cashflowType.toLowerCase(), colName)){
                    JOptionPane.showMessageDialog(togglePanels, "Succesfully Updated!","Success Message",1);
                    data.adduserlogs(USER, "Cashflow", "Update",cashflowId);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(togglePanels,"Enter Correct Values!","Input Error",0);
        }
        financecashflowtableload();
    }//GEN-LAST:event_jButtonUpdateCashflow1ActionPerformed

    private void jButtonViewCashflow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewCashflow1ActionPerformed
        financeSearch("cashflow");
    }//GEN-LAST:event_jButtonViewCashflow1ActionPerformed

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
            java.util.logging.Logger.getLogger(Finance_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Finance_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Finance_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Finance_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Finance_Main(USER).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JPanel CashflowPanel;
    javax.swing.JPanel CashflowPanel1;
    javax.swing.JPanel ExpenditurePanel;
    javax.swing.JPanel ExpenditurePanel1;
    javax.swing.JPanel IncomePanel;
    javax.swing.JPanel IncomePanel3;
    javax.swing.JLabel Time;
    javax.swing.JButton buttonCashflow;
    javax.swing.JButton buttonCashflow1;
    javax.swing.JButton buttonExpenditure;
    javax.swing.JButton buttonExpenditure1;
    javax.swing.JButton buttonIncome;
    javax.swing.JButton buttonIncome1;
    javax.swing.JPanel cashflowPanel;
    javax.swing.JPanel cashflowPanel1;
    javax.swing.JPanel expenditurePanel;
    javax.swing.JPanel expenditurePanel1;
    javax.swing.JPanel financeManagerPanel;
    javax.swing.JPanel headerPanel;
    javax.swing.JPanel incomePanel;
    javax.swing.JPanel incomeUpdatePanel;
    javax.swing.JButton jButtonAcceptAllExpenditure;
    javax.swing.JButton jButtonAcceptAllIncome;
    javax.swing.JButton jButtonAddCashflow;
    javax.swing.JButton jButtonDemoCashflow;
    javax.swing.JButton jButtonDemoExpense;
    javax.swing.JButton jButtonDemoIncome;
    javax.swing.JButton jButtonExpense;
    javax.swing.JButton jButtonFindExpense1;
    javax.swing.JButton jButtonFindIncome2;
    javax.swing.JButton jButtonIncome;
    javax.swing.JButton jButtonResetCashflow;
    javax.swing.JButton jButtonResetCashflow1;
    javax.swing.JButton jButtonResetExpense;
    javax.swing.JButton jButtonResetExpense1;
    javax.swing.JButton jButtonResetIncome;
    javax.swing.JButton jButtonResetIncome3;
    javax.swing.JButton jButtonUpdateCashflow1;
    javax.swing.JButton jButtonUpdateExpense1;
    javax.swing.JButton jButtonUpdateIncome2;
    javax.swing.JButton jButtonViewCashflow1;
    javax.swing.JButton jButtonlogout;
    javax.swing.JDesktopPane jDesktopPaneFinance;
    javax.swing.JLabel jLabel14;
    javax.swing.JLabel jLabelheaderimage;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPaneCashflow;
    javax.swing.JScrollPane jScrollPaneCashflow1;
    javax.swing.JScrollPane jScrollPaneExpense;
    javax.swing.JScrollPane jScrollPaneExpense1;
    javax.swing.JScrollPane jScrollPaneIncome;
    javax.swing.JScrollPane jScrollPaneIncome3;
    javax.swing.JTabbedPane jTabbedPaneCashflow;
    javax.swing.JTabbedPane jTabbedPaneCashflow1;
    javax.swing.JTabbedPane jTabbedPaneExpenditure;
    javax.swing.JTabbedPane jTabbedPaneExpenditure1;
    javax.swing.JTabbedPane jTabbedPaneIncome;
    javax.swing.JTabbedPane jTabbedPaneIncome1;
    javax.swing.JTable jTable1;
    javax.swing.JTable jTableAddExpense;
    javax.swing.JTable jTableAddExpense1;
    javax.swing.JTable jTableCashflow;
    javax.swing.JTable jTableCashflow1;
    javax.swing.JTable jTableIncome;
    javax.swing.JTable jTableIncome3;
    javax.swing.JLabel lableCashflowDepartment;
    javax.swing.JLabel lableCashflowDepartment1;
    javax.swing.JLabel lableCashflowMethod;
    javax.swing.JLabel lableCashflowMethod1;
    javax.swing.JLabel lableCashflowPaymentType;
    javax.swing.JLabel lableCashflowPaymentType1;
    javax.swing.JLabel lableCashflowamount;
    javax.swing.JLabel lableCashflowamount1;
    javax.swing.JLabel lableCashflowdate;
    javax.swing.JLabel lableCashflowdate1;
    javax.swing.JLabel lableCashflowdebitcredit;
    javax.swing.JLabel lableCashflowdebitcredit1;
    javax.swing.JLabel lableCashflowdescription;
    javax.swing.JLabel lableCashflowdescription1;
    javax.swing.JLabel lableCashflowid1;
    javax.swing.JLabel lableExpenseId1;
    javax.swing.JLabel lableExpenseMethod;
    javax.swing.JLabel lableExpenseMethod1;
    javax.swing.JLabel lableExpensePaymentType;
    javax.swing.JLabel lableExpensePaymentType1;
    javax.swing.JLabel lableExpenseamount;
    javax.swing.JLabel lableExpenseamount1;
    javax.swing.JLabel lableExpensedate;
    javax.swing.JLabel lableExpensedate1;
    javax.swing.JLabel lableExpensedebitcredit;
    javax.swing.JLabel lableExpensedebitcredit1;
    javax.swing.JLabel lableExpensedepartment;
    javax.swing.JLabel lableExpensedepartment1;
    javax.swing.JLabel lableExpensedescription;
    javax.swing.JLabel lableExpensedescription1;
    javax.swing.JLabel lableIncomeMethod;
    javax.swing.JLabel lableIncomeMethod3;
    javax.swing.JLabel lableIncomePaymentType;
    javax.swing.JLabel lableIncomePaymentType3;
    javax.swing.JLabel lableincomeamount;
    javax.swing.JLabel lableincomeamount3;
    javax.swing.JLabel lableincomedate;
    javax.swing.JLabel lableincomedate3;
    javax.swing.JLabel lableincomedebitcredit;
    javax.swing.JLabel lableincomedebitcredit3;
    javax.swing.JLabel lableincomedepartment;
    javax.swing.JLabel lableincomedepartment3;
    javax.swing.JLabel lableincomedescription;
    javax.swing.JLabel lableincomedescription3;
    javax.swing.JLabel lableincomeid2;
    javax.swing.JPanel mainButtonPanel;
    javax.swing.JPanel togglePanels;
    javax.swing.JTextField txtCashflowAmount;
    javax.swing.JTextField txtCashflowAmount1;
    javax.swing.JComboBox<String> txtCashflowDebitCredit;
    javax.swing.JComboBox<String> txtCashflowDebitCredit1;
    javax.swing.JComboBox<String> txtCashflowDepartment;
    javax.swing.JComboBox<String> txtCashflowDepartment1;
    javax.swing.JTextField txtCashflowDescription;
    javax.swing.JTextField txtCashflowDescription1;
    javax.swing.JTextField txtCashflowId1;
    javax.swing.JComboBox<String> txtCashflowMethod;
    javax.swing.JComboBox<String> txtCashflowMethod1;
    javax.swing.JComboBox<String> txtCashflowMonth;
    javax.swing.JComboBox<String> txtCashflowMonth1;
    javax.swing.JComboBox<String> txtCashflowPaymentType;
    javax.swing.JComboBox<String> txtCashflowPaymentType1;
    javax.swing.JComboBox<String> txtCashflowYear;
    javax.swing.JComboBox<String> txtCashflowYear1;
    javax.swing.JComboBox<String> txtCashflowday;
    javax.swing.JComboBox<String> txtCashflowday1;
    javax.swing.JTextField txtExpenseAmount;
    javax.swing.JTextField txtExpenseAmount1;
    javax.swing.JComboBox<String> txtExpenseDebitCredit;
    javax.swing.JComboBox<String> txtExpenseDebitCredit1;
    javax.swing.JComboBox<String> txtExpenseDepartment;
    javax.swing.JComboBox<String> txtExpenseDepartment1;
    javax.swing.JTextField txtExpenseDescription;
    javax.swing.JTextField txtExpenseDescription1;
    javax.swing.JTextField txtExpenseId1;
    javax.swing.JComboBox<String> txtExpenseMethod;
    javax.swing.JComboBox<String> txtExpenseMethod1;
    javax.swing.JComboBox<String> txtExpenseMonth;
    javax.swing.JComboBox<String> txtExpenseMonth1;
    javax.swing.JComboBox<String> txtExpensePaymentType;
    javax.swing.JComboBox<String> txtExpensePaymentType1;
    javax.swing.JComboBox<String> txtExpenseYear;
    javax.swing.JComboBox<String> txtExpenseYear1;
    javax.swing.JComboBox<String> txtExpenseday;
    javax.swing.JComboBox<String> txtExpenseday1;
    javax.swing.JComboBox<String> txtIncomeDebitCredit;
    javax.swing.JComboBox<String> txtIncomeDebitCredit3;
    javax.swing.JComboBox<String> txtIncomeDepartment;
    javax.swing.JComboBox<String> txtIncomeDepartment3;
    javax.swing.JTextField txtIncomeDescription;
    javax.swing.JTextField txtIncomeDescription3;
    javax.swing.JTextField txtIncomeId2;
    javax.swing.JComboBox<String> txtIncomeMethod;
    javax.swing.JComboBox<String> txtIncomeMethod3;
    javax.swing.JComboBox<String> txtIncomeMonth;
    javax.swing.JComboBox<String> txtIncomeMonth3;
    javax.swing.JComboBox<String> txtIncomePaymentType;
    javax.swing.JComboBox<String> txtIncomePaymentType3;
    javax.swing.JComboBox<String> txtIncomeYear;
    javax.swing.JComboBox<String> txtIncomeYear3;
    javax.swing.JComboBox<String> txtIncomeday;
    javax.swing.JComboBox<String> txtIncomeday3;
    javax.swing.JTextField txtdIncomeAmount;
    javax.swing.JTextField txtdIncomeAmount3;
    javax.swing.JLabel welcomeuser;
    javax.swing.JLabel welcomeuser1;
    // End of variables declaration//GEN-END:variables
}