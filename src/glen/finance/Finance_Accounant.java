package glen.finance;

import glen.DBconnect;
import glen.System_Login;
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

public class Finance_Accounant extends javax.swing.JFrame {
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
    String assestsStatus="";            
    String liabilityStatus="";          String budgetRequestStatus="";
    String searchBy="";                 String searchValue="";
    String colName="";                  String paymentType=""; 
    String actionType="";               String budgetApproval="";
    static String USER;
    String userListUserId, userListEmployeeId, userListFullName, userListUsername, userListPassword;
    
    int commonId;                       String userlogActionId;
    double amount;                      int cashflowId=0;
    double budgetBalance;               int requestId;
    int incomeId;                       int expenditureId;
    int assestsId;                      
    int liabilityId;                    int budgetId;
    int budgetRequestId;                double budgetAmount;
    String userlogId;
    
    public Finance_Accounant(String username) {
        
        USER=username;
        initComponents();
        this.setLocationRelativeTo(null); //Setting to display in the center of screen
        clock();
        data.adduserlogs(USER, "System Login", "Login",00000);
        
        welcomeuser.setText(USER);
        togglePanels.setVisible(true);
        incomePanel.setVisible(false);
        expenditurePanel.setVisible(false);
        cashflowPanel.setVisible(false);
        assestsPanel.setVisible(false);
        liabilitiesPanel.setVisible(false);
        budgetPanel.setVisible(false);
        reportsPanel.setVisible(false);
        financeUserlogsPanel.setVisible(false);
    }
//----------------------------------------------------------finance functions----------------------------------------------------------------
    public void financepanelshift(String buttonName){
        switch (buttonName) {
            case "INCOME":
                incomePanel.setVisible(true);
                expenditurePanel.setVisible(false);
                cashflowPanel.setVisible(false);
                assestsPanel.setVisible(false);
                liabilitiesPanel.setVisible(false);
                budgetPanel.setVisible(false);
                reportsPanel.setVisible(false);
                financeUserlogsPanel.setVisible(false);
                break;
            case "EXPENDITURE":
                incomePanel.setVisible(false);
                expenditurePanel.setVisible(true);
                cashflowPanel.setVisible(false);
                assestsPanel.setVisible(false);
                liabilitiesPanel.setVisible(false);
                budgetPanel.setVisible(false);
                reportsPanel.setVisible(false);
                financeUserlogsPanel.setVisible(false);
                break;
            case "CASHFLOW":
                incomePanel.setVisible(false);
                expenditurePanel.setVisible(false);
                cashflowPanel.setVisible(true);
                assestsPanel.setVisible(false);
                liabilitiesPanel.setVisible(false);
                budgetPanel.setVisible(false);
                reportsPanel.setVisible(false);
                financeUserlogsPanel.setVisible(false);
                break;
            case "ASSESTS":
                incomePanel.setVisible(false);
                expenditurePanel.setVisible(false);
                cashflowPanel.setVisible(false);
                assestsPanel.setVisible(true);
                liabilitiesPanel.setVisible(false);
                budgetPanel.setVisible(false);
                reportsPanel.setVisible(false);
                financeUserlogsPanel.setVisible(false);
                break;
            case "LIABILITIES":
                incomePanel.setVisible(false);
                expenditurePanel.setVisible(false);
                cashflowPanel.setVisible(false);
                assestsPanel.setVisible(false);
                liabilitiesPanel.setVisible(true);
                budgetPanel.setVisible(false);
                reportsPanel.setVisible(false);
                financeUserlogsPanel.setVisible(false);
                break;
            case "BUDGETS":
                incomePanel.setVisible(false);
                expenditurePanel.setVisible(false);
                cashflowPanel.setVisible(false);
                assestsPanel.setVisible(false);
                liabilitiesPanel.setVisible(false);
                budgetPanel.setVisible(true);
                reportsPanel.setVisible(false);
                financeUserlogsPanel.setVisible(false);
                break;
            case "REPORTS":
                incomePanel.setVisible(false);
                expenditurePanel.setVisible(false);
                cashflowPanel.setVisible(false);
                assestsPanel.setVisible(false);
                liabilitiesPanel.setVisible(false);
                budgetPanel.setVisible(false);
                reportsPanel.setVisible(true);
                financeUserlogsPanel.setVisible(false);
                break;
            case "USER LOGS":
                incomePanel.setVisible(false);
                expenditurePanel.setVisible(false);
                cashflowPanel.setVisible(false);
                assestsPanel.setVisible(false);
                liabilitiesPanel.setVisible(false);
                budgetPanel.setVisible(false);
                reportsPanel.setVisible(false);
                financeUserlogsPanel.setVisible(true);
                break;

            default:
                break;
        }
    }            //panel shifting
    public void financeincometableload(){
        jTableIncome.setModel(DbUtils.resultSetToTableModel(data.finincometableload())); // inserting table results to income GUI table
        jTableIncome1.setModel(DbUtils.resultSetToTableModel(data.finincometableload1())); // inserting table results to income GUI table
    }                         //table load
    public void financeexpensetableaoad(){
        jTableAddExpense.setModel(DbUtils.resultSetToTableModel(data.finexpensetableaoad()));      // inserting table results to expenditure GUI table
        jTableAddExpense1.setModel(DbUtils.resultSetToTableModel(data.finexpensetableaoad1()));      // inserting table results to expenditure GUI table
    }                        //table load
    public void financeasseststableload(){
            jTableAssests.setModel(DbUtils.resultSetToTableModel(data.finasseststableload()));// inserting table results to Assests GUI table
            jTableAssests1.setModel(DbUtils.resultSetToTableModel(data.finasseststableload1()));// inserting table results to Assests GUI table
    }                        //table load
    public void financeliabilitytableload(){
            jTableLiability.setModel(DbUtils.resultSetToTableModel(data.finliabilitytableload()));      // inserting table results to liability GUI table
            jTableLiability1.setModel(DbUtils.resultSetToTableModel(data.finliabilitytableload1()));      // inserting table results to liability GUI table
    }                      //table load
    public void financecashflowtableload(){
            jTableCashflow.setModel(DbUtils.resultSetToTableModel(data.fincashflowtableload()));      // inserting table results to income GUI table
            jTableCashflow1.setModel(DbUtils.resultSetToTableModel(data.fincashflowtableload1()));      // inserting table results to income GUI table
            TableColumn col=jTableCashflow.getColumnModel().getColumn(2);
            col.setMinWidth(150);
            TableColumn col2=jTableCashflow1.getColumnModel().getColumn(2);
            col2.setMinWidth(150);
    }                       //table load
    public void financebudgettableload(){
            jTableBudgetRequest.setModel(DbUtils.resultSetToTableModel(data.finbudgettableload()));     // inserting table results to income GUI table
    }
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
            financeincometableload(); financeexpensetableaoad(); financeasseststableload(); financeliabilitytableload(); 
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
             else if(catagory.equals("budget")){
                Results=data.budgetDBSearch(searchBy,searchValue);
                jTableBudgetRequest.setModel(DbUtils.resultSetToTableModel(Results));
             }
             else{    
                    Results= data.financeDBSearch(searchBy,searchValue,catagory);
                    switch(catagory){
                        case "income":
                            jTableIncome1.setModel(DbUtils.resultSetToTableModel(Results));
                            break;
                        case "expense":
                            jTableAddExpense1.setModel(DbUtils.resultSetToTableModel(Results));
                            break;
                        case"assests":
                            jTableAssests1.setModel(DbUtils.resultSetToTableModel(Results));
                            break;
                        case"liability":
                            jTableLiability1.setModel(DbUtils.resultSetToTableModel(Results));
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
        jButtonlogout = new javax.swing.JButton();
        welcomeuser = new javax.swing.JLabel();
        Time = new javax.swing.JLabel();
        jLabelheaderimage = new javax.swing.JLabel();
        mainButtonPanel = new javax.swing.JPanel();
        buttonIncome = new javax.swing.JButton();
        buttonExpenditure = new javax.swing.JButton();
        buttonCashflow = new javax.swing.JButton();
        buttonAssests = new javax.swing.JButton();
        buttonLiabilities = new javax.swing.JButton();
        buttonBudgets = new javax.swing.JButton();
        buttonReports = new javax.swing.JButton();
        buttonfinanceUserlogs = new javax.swing.JButton();
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
        IncomePanel1 = new javax.swing.JPanel();
        lableincomeid1 = new javax.swing.JLabel();
        lableincomedescription1 = new javax.swing.JLabel();
        txtIncomeId1 = new javax.swing.JTextField();
        txtIncomeDescription1 = new javax.swing.JTextField();
        lableincomedepartment1 = new javax.swing.JLabel();
        txtIncomeDepartment1 = new javax.swing.JComboBox<>();
        txtIncomeDebitCredit1 = new javax.swing.JComboBox<>();
        lableincomedebitcredit1 = new javax.swing.JLabel();
        lableincomedate1 = new javax.swing.JLabel();
        txtdIncomeAmount1 = new javax.swing.JTextField();
        lableIncomePaymentType1 = new javax.swing.JLabel();
        lableIncomeMethod1 = new javax.swing.JLabel();
        txtIncomeMethod1 = new javax.swing.JComboBox<>();
        lableincomeamount1 = new javax.swing.JLabel();
        txtIncomePaymentType1 = new javax.swing.JComboBox<>();
        txtIncomeday1 = new javax.swing.JComboBox<>();
        txtIncomeMonth1 = new javax.swing.JComboBox<>();
        txtIncomeYear1 = new javax.swing.JComboBox<>();
        jScrollPaneIncome1 = new javax.swing.JScrollPane();
        jTableIncome1 = new javax.swing.JTable();
        jButtonResetIncome1 = new javax.swing.JButton();
        jButtonUpdateIncome1 = new javax.swing.JButton();
        jButtonFindIncome1 = new javax.swing.JButton();
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
        assestsPanel = new javax.swing.JPanel();
        jTabbedPaneAssests = new javax.swing.JTabbedPane();
        AssestsPanel = new javax.swing.JPanel();
        lableAssestsdescription = new javax.swing.JLabel();
        txtAssestsDescription = new javax.swing.JTextField();
        lableAssestsMethod = new javax.swing.JLabel();
        txtAssestsMethod = new javax.swing.JComboBox<>();
        lableAssestsDepartment = new javax.swing.JLabel();
        txtAssestsDepartment = new javax.swing.JComboBox<>();
        txtAssestsPaymentType = new javax.swing.JComboBox<>();
        lableAssestsPaymentType = new javax.swing.JLabel();
        txtAssestsDebitCredit = new javax.swing.JComboBox<>();
        lableAssestsdebitcredit = new javax.swing.JLabel();
        lableAssestsdate = new javax.swing.JLabel();
        txtAssestsAmount = new javax.swing.JTextField();
        lableAssestsamount = new javax.swing.JLabel();
        txtAssestsday = new javax.swing.JComboBox<>();
        txtAssestsMonth = new javax.swing.JComboBox<>();
        txtAssestsYear = new javax.swing.JComboBox<>();
        jScrollPaneAssests = new javax.swing.JScrollPane();
        jTableAssests = new javax.swing.JTable();
        jButtonAddAssests = new javax.swing.JButton();
        jButtonResetAssests = new javax.swing.JButton();
        jButtonDemoAssests = new javax.swing.JButton();
        jButtonAcceptAllAssests = new javax.swing.JButton();
        AssestsPanel1 = new javax.swing.JPanel();
        lableAssestsId1 = new javax.swing.JLabel();
        lableAssestsdescription1 = new javax.swing.JLabel();
        txtAssestsId1 = new javax.swing.JTextField();
        txtAssestsDescription1 = new javax.swing.JTextField();
        lableAssestsMethod1 = new javax.swing.JLabel();
        txtAssestsMethod1 = new javax.swing.JComboBox<>();
        lableAssestsDepartment1 = new javax.swing.JLabel();
        txtAssestsDepartment1 = new javax.swing.JComboBox<>();
        txtAssestsPaymentType1 = new javax.swing.JComboBox<>();
        lableAssestsPaymentType1 = new javax.swing.JLabel();
        txtAssestsDebitCredit1 = new javax.swing.JComboBox<>();
        lableAssestsdebitcredit1 = new javax.swing.JLabel();
        lableAssestsdate1 = new javax.swing.JLabel();
        txtAssestsAmount1 = new javax.swing.JTextField();
        lableAssestsamount1 = new javax.swing.JLabel();
        txtAssestsday1 = new javax.swing.JComboBox<>();
        txtAssestsMonth1 = new javax.swing.JComboBox<>();
        txtAssestsYear1 = new javax.swing.JComboBox<>();
        jScrollPaneAssests1 = new javax.swing.JScrollPane();
        jTableAssests1 = new javax.swing.JTable();
        jButtonResetAssests1 = new javax.swing.JButton();
        jButtonUpdateAssests1 = new javax.swing.JButton();
        jButtonSearchAssests1 = new javax.swing.JButton();
        liabilitiesPanel = new javax.swing.JPanel();
        jTabbedPaneLiabilities = new javax.swing.JTabbedPane();
        LiabilityPanel = new javax.swing.JPanel();
        lableLiabilitydescription = new javax.swing.JLabel();
        txtLiabilityDescription = new javax.swing.JTextField();
        lableLiabilityMethod = new javax.swing.JLabel();
        txtLiabilityMethod = new javax.swing.JComboBox<>();
        txtLiabilityPaymentType = new javax.swing.JComboBox<>();
        lableLiabilityPaymentType = new javax.swing.JLabel();
        txtLiabilityDebitCredit = new javax.swing.JComboBox<>();
        lableLiabilitydebitcredit = new javax.swing.JLabel();
        lableLiabilitydate = new javax.swing.JLabel();
        txtLiabilityAmount = new javax.swing.JTextField();
        lableLiabilityamount = new javax.swing.JLabel();
        txtLiabilityday = new javax.swing.JComboBox<>();
        txtLiabilityMonth = new javax.swing.JComboBox<>();
        txtLiabilityYear = new javax.swing.JComboBox<>();
        jScrollPaneLiability = new javax.swing.JScrollPane();
        jTableLiability = new javax.swing.JTable();
        jButtonAddLiability = new javax.swing.JButton();
        jButtonResetLiability = new javax.swing.JButton();
        jButtonDemoLiability = new javax.swing.JButton();
        jButtonAcceptAllinvestment1 = new javax.swing.JButton();
        LiabilityPanel1 = new javax.swing.JPanel();
        lableLiabilityId1 = new javax.swing.JLabel();
        lableLiabilitydescription1 = new javax.swing.JLabel();
        txtLiabilityId1 = new javax.swing.JTextField();
        txtLiabilityDescription1 = new javax.swing.JTextField();
        lableLiabilityMethod1 = new javax.swing.JLabel();
        txtLiabilityMethod1 = new javax.swing.JComboBox<>();
        txtLiabilityPaymentType1 = new javax.swing.JComboBox<>();
        lableLiabilityPaymentType1 = new javax.swing.JLabel();
        txtLiabilityDebitCredit1 = new javax.swing.JComboBox<>();
        lableLiabilitydebitcredit1 = new javax.swing.JLabel();
        lableLiabilitydate1 = new javax.swing.JLabel();
        txtLiabilityAmount1 = new javax.swing.JTextField();
        lableLiabilityamount1 = new javax.swing.JLabel();
        txtLiabilityday1 = new javax.swing.JComboBox<>();
        txtLiabilityMonth1 = new javax.swing.JComboBox<>();
        txtLiabilityYear1 = new javax.swing.JComboBox<>();
        jScrollPaneLiability1 = new javax.swing.JScrollPane();
        jTableLiability1 = new javax.swing.JTable();
        jButtonResetLiability1 = new javax.swing.JButton();
        jButtonUpdateLiability1 = new javax.swing.JButton();
        jButtonSearchLiability1 = new javax.swing.JButton();
        budgetPanel = new javax.swing.JPanel();
        jTabbedPaneBudget = new javax.swing.JTabbedPane();
        BudgetRequestPanel = new javax.swing.JPanel();
        lableBudgetRequestId = new javax.swing.JLabel();
        txtBudgetRequestId = new javax.swing.JTextField();
        lableBudgetRequestDepartment = new javax.swing.JLabel();
        lableBudgetRequestdate = new javax.swing.JLabel();
        txtBudgetRequestAmount = new javax.swing.JTextField();
        lableBudgetRequestamount = new javax.swing.JLabel();
        txtBudgetRequestday = new javax.swing.JComboBox<>();
        txtBudgetRequestMonth = new javax.swing.JComboBox<>();
        txtBudgetRequestYear = new javax.swing.JComboBox<>();
        jScrollPaneBudgetRequest = new javax.swing.JScrollPane();
        jTableBudgetRequest = new javax.swing.JTable();
        jButtonAcceptBudgetRequest = new javax.swing.JButton();
        jButtonResetBudgetRequest = new javax.swing.JButton();
        jButtonCheckBudgetRequest = new javax.swing.JButton();
        jButtonSearchBudgetRequest = new javax.swing.JButton();
        lableBudgetRequestDepartment1 = new javax.swing.JLabel();
        txtBudgetRequestDepartment = new javax.swing.JTextField();
        txtBudgetRequestApproval = new javax.swing.JTextField();
        lableBudgetRequestId1 = new javax.swing.JLabel();
        txtBudgetRequestId1 = new javax.swing.JTextField();
        reportsPanel = new javax.swing.JPanel();
        jPanelReportCashflowStatement = new javax.swing.JPanel();
        jButtonGenCashflowStatement = new javax.swing.JButton();
        jDateChooserGenCashflowStatementto = new com.toedter.calendar.JDateChooser();
        jDateChooserGenCashflowStatementfrom = new com.toedter.calendar.JDateChooser();
        jComboBoxSelectDeptCashflowStatement = new javax.swing.JComboBox<>();
        jPanelReportBnkStatement = new javax.swing.JPanel();
        jButtonGenBnkStatement = new javax.swing.JButton();
        jDateChooserGenBnkStatementto = new com.toedter.calendar.JDateChooser();
        jDateChooserGenBnkStatementfrom = new com.toedter.calendar.JDateChooser();
        financeUserlogsPanel = new javax.swing.JPanel();
        jTabbedPaneUserlog = new javax.swing.JTabbedPane();
        UserlogPanel = new javax.swing.JPanel();
        lableUserlogId = new javax.swing.JLabel();
        txtUserlogId = new javax.swing.JTextField();
        lableUserlogActionId = new javax.swing.JLabel();
        lableUserlogDate = new javax.swing.JLabel();
        lableUserlogActionType = new javax.swing.JLabel();
        txtUserlogday = new javax.swing.JComboBox<>();
        txtUserlogmonth = new javax.swing.JComboBox<>();
        txtyear = new javax.swing.JComboBox<>();
        jScrollPaneUserlog = new javax.swing.JScrollPane();
        jTableBudgetRequest7 = new javax.swing.JTable();
        jButtonResetUserlog = new javax.swing.JButton();
        jButtonSearchUserlog = new javax.swing.JButton();
        txtUserlogActionId = new javax.swing.JTextField();
        txtUserlogActionType = new javax.swing.JComboBox<>();

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

        jButtonlogout.setBackground(new java.awt.Color(255, 255, 255));
        jButtonlogout.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButtonlogout.setForeground(new java.awt.Color(51, 153, 255));
        jButtonlogout.setIcon(new javax.swing.ImageIcon("D:\\SLIIT PROFILE\\Glen Hotel\\Glen_Hotel_Management_System\\src\\glen\\img\\finance\\FinanceLog.png")); // NOI18N
        jButtonlogout.setText("Log out");
        jButtonlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonlogoutActionPerformed(evt);
            }
        });
        headerPanel.add(jButtonlogout);
        jButtonlogout.setBounds(910, 10, 100, 30);

        welcomeuser.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        welcomeuser.setForeground(new java.awt.Color(0, 153, 255));
        welcomeuser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        welcomeuser.setText("Name");
        headerPanel.add(welcomeuser);
        welcomeuser.setBounds(910, 50, 100, 20);

        Time.setFont(new java.awt.Font("Electrofied", 0, 12)); // NOI18N
        Time.setForeground(new java.awt.Color(51, 153, 255));
        Time.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Time.setText("Time");
        headerPanel.add(Time);
        Time.setBounds(780, 100, 230, 20);

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

        buttonAssests.setBackground(new java.awt.Color(42, 135, 235));
        buttonAssests.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonAssests.setForeground(new java.awt.Color(255, 255, 255));
        buttonAssests.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Assests.png")); // NOI18N
        buttonAssests.setText("ASSESTS");
        buttonAssests.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonAssests.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonAssests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonAssestsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonAssestsMouseExited(evt);
            }
        });
        buttonAssests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAssestsActionPerformed(evt);
            }
        });

        buttonLiabilities.setBackground(new java.awt.Color(42, 135, 235));
        buttonLiabilities.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonLiabilities.setForeground(new java.awt.Color(255, 255, 255));
        buttonLiabilities.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Liability.png")); // NOI18N
        buttonLiabilities.setText("LIABILITIES");
        buttonLiabilities.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonLiabilities.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonLiabilities.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonLiabilitiesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonLiabilitiesMouseExited(evt);
            }
        });
        buttonLiabilities.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLiabilitiesActionPerformed(evt);
            }
        });

        buttonBudgets.setBackground(new java.awt.Color(42, 135, 235));
        buttonBudgets.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonBudgets.setForeground(new java.awt.Color(255, 255, 255));
        buttonBudgets.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Budget.png")); // NOI18N
        buttonBudgets.setText("BUDGETS");
        buttonBudgets.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonBudgets.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonBudgets.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonBudgetsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonBudgetsMouseExited(evt);
            }
        });
        buttonBudgets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBudgetsActionPerformed(evt);
            }
        });

        buttonReports.setBackground(new java.awt.Color(42, 135, 235));
        buttonReports.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonReports.setForeground(new java.awt.Color(255, 255, 255));
        buttonReports.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Reports.png")); // NOI18N
        buttonReports.setText("REPORTS");
        buttonReports.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonReports.setMargin(new java.awt.Insets(2, 4, 2, 0));
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

        buttonfinanceUserlogs.setBackground(new java.awt.Color(42, 135, 235));
        buttonfinanceUserlogs.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 18)); // NOI18N
        buttonfinanceUserlogs.setForeground(new java.awt.Color(255, 255, 255));
        buttonfinanceUserlogs.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Userlogs.png")); // NOI18N
        buttonfinanceUserlogs.setText("USER LOGS");
        buttonfinanceUserlogs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        buttonfinanceUserlogs.setMargin(new java.awt.Insets(2, 4, 2, 0));
        buttonfinanceUserlogs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonfinanceUserlogsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonfinanceUserlogsMouseExited(evt);
            }
        });
        buttonfinanceUserlogs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonfinanceUserlogsActionPerformed(evt);
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
                    .addComponent(buttonAssests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonLiabilities, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonBudgets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonfinanceUserlogs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainButtonPanelLayout.setVerticalGroup(
            mainButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonIncome, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonExpenditure, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCashflow, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAssests, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonLiabilities, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonBudgets, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(buttonReports, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonfinanceUserlogs, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        incomePanel.setPreferredSize(new java.awt.Dimension(830, 550));

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
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPaneIncome.addTab("Add Income", IncomePanel);

        IncomePanel1.setBackground(new java.awt.Color(255, 255, 255));
        IncomePanel1.setPreferredSize(new java.awt.Dimension(825, 519));

        lableincomeid1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomeid1.setText("Income ID");

        lableincomedescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomedescription1.setText("Description");

        txtIncomeId1.setEditable(false);
        txtIncomeId1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeId1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        txtIncomeDescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeDescription1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableincomedepartment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomedepartment1.setText("Department");

        txtIncomeDepartment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeDepartment1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Finance", "Stock", "Room", "Restaurant", "HR", "Transport", "Event" }));
        txtIncomeDepartment1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeDepartment1.setBackground(new Color(255,255,255));

        txtIncomeDebitCredit1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeDebitCredit1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Debit", "Credit" }));
        txtIncomeDebitCredit1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeDebitCredit1.setBackground(new Color(255,255,255));

        lableincomedebitcredit1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomedebitcredit1.setText("Debit / Credit");

        lableincomedate1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomedate1.setText("Date");

        txtdIncomeAmount1.setEditable(false);
        txtdIncomeAmount1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtdIncomeAmount1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableIncomePaymentType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableIncomePaymentType1.setText("Cashflow Type");

        lableIncomeMethod1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableIncomeMethod1.setText("Payment Method");

        txtIncomeMethod1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeMethod1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Money", "Bank" }));
        txtIncomeMethod1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeMethod1.setBackground(new Color(255,255,255));

        lableincomeamount1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableincomeamount1.setText("Amount");

        txtIncomePaymentType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomePaymentType1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Income", "Expenditure", "Budget", "Investment", "Assest", "Liability" }));
        txtIncomePaymentType1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomePaymentType1.setEnabled(false);
        txtIncomePaymentType1.setBackground(new Color(255,255,255));

        txtIncomeday1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeday1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtIncomeday1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeday1.setBackground(new Color(255,255,255));

        txtIncomeMonth1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtIncomeMonth1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeMonth1.setBackground(new Color(255,255,255));

        txtIncomeYear1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtIncomeYear1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtIncomeYear1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtIncomeYear1.setBackground(new Color(255,255,255));

        jScrollPaneIncome1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneIncome1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneIncome1.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneIncome1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableIncome1.setAutoCreateRowSorter(true);
        jTableIncome1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableIncome1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableIncome1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableIncome1.setGridColor(new java.awt.Color(102, 153, 255));
        jTableIncome1.setRowHeight(20);
        jTableIncome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableIncome1MouseClicked(evt);
            }
        });
        jScrollPaneIncome1.setViewportView(jTableIncome1);

        jButtonResetIncome1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetIncome1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetIncome1.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetIncome1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetIncome1.setText("Reset");
        jButtonResetIncome1.setFocusable(false);
        jButtonResetIncome1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetIncome1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetIncome1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetIncome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetIncome1ActionPerformed(evt);
            }
        });

        jButtonUpdateIncome1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonUpdateIncome1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonUpdateIncome1.setForeground(new java.awt.Color(51, 153, 255));
        jButtonUpdateIncome1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Updatebutton.png")); // NOI18N
        jButtonUpdateIncome1.setText("Update");
        jButtonUpdateIncome1.setFocusable(false);
        jButtonUpdateIncome1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonUpdateIncome1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonUpdateIncome1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonUpdateIncome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateIncome1ActionPerformed(evt);
            }
        });

        jButtonFindIncome1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonFindIncome1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonFindIncome1.setForeground(new java.awt.Color(51, 153, 255));
        jButtonFindIncome1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Findbutton.png")); // NOI18N
        jButtonFindIncome1.setText("Find");
        jButtonFindIncome1.setFocusable(false);
        jButtonFindIncome1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonFindIncome1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonFindIncome1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonFindIncome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindIncome1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout IncomePanel1Layout = new javax.swing.GroupLayout(IncomePanel1);
        IncomePanel1.setLayout(IncomePanel1Layout);
        IncomePanel1Layout.setHorizontalGroup(
            IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IncomePanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IncomePanel1Layout.createSequentialGroup()
                            .addComponent(lableincomeid1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(txtIncomeId1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(IncomePanel1Layout.createSequentialGroup()
                            .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lableincomedescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lableincomedepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(IncomePanel1Layout.createSequentialGroup()
                                    .addGap(40, 40, 40)
                                    .addComponent(txtIncomeDescription1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, IncomePanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtIncomeDepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(IncomePanel1Layout.createSequentialGroup()
                        .addComponent(lableIncomeMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtIncomeMethod1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(IncomePanel1Layout.createSequentialGroup()
                            .addComponent(lableincomeamount1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(60, 60, 60)
                            .addComponent(txtdIncomeAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(IncomePanel1Layout.createSequentialGroup()
                            .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lableincomedate1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lableincomedebitcredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)
                            .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIncomeDebitCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(IncomePanel1Layout.createSequentialGroup()
                                    .addComponent(txtIncomeday1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIncomeMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtIncomeYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtIncomePaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lableIncomePaymentType1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
            .addGroup(IncomePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneIncome1)
                    .addGroup(IncomePanel1Layout.createSequentialGroup()
                        .addComponent(jButtonResetIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonFindIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUpdateIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        IncomePanel1Layout.setVerticalGroup(
            IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(IncomePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIncomeId1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableincomeid1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdIncomeAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableincomeamount1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIncomeDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableincomedescription1)
                    .addComponent(lableincomedate1)
                    .addComponent(txtIncomeday1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIncomeMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIncomeYear1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableincomedebitcredit1)
                    .addComponent(txtIncomeDebitCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableIncomeMethod1)
                    .addComponent(txtIncomeMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableincomedepartment1)
                    .addComponent(txtIncomeDepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableIncomePaymentType1)
                    .addComponent(txtIncomePaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(IncomePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonResetIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFindIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdateIncome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPaneIncome.addTab("Search & Update", IncomePanel1);

        javax.swing.GroupLayout incomePanelLayout = new javax.swing.GroupLayout(incomePanel);
        incomePanel.setLayout(incomePanelLayout);
        incomePanelLayout.setHorizontalGroup(
            incomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneIncome, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        incomePanelLayout.setVerticalGroup(
            incomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneIncome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jTabbedPaneExpenditure.addTab("Search & Update", ExpenditurePanel1);

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
        txtCashflowPaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Income", "Expenditure", "Assests", "Liability" }));
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
        txtCashflowPaymentType1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Income", "Expenditure", "Assests", "Liability" }));
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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

        jTabbedPaneCashflow.addTab("Search & Update", CashflowPanel1);

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

        assestsPanel.setBackground(new java.awt.Color(255, 255, 255));
        assestsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        assestsPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jTabbedPaneAssests.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneAssests.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPaneAssests.setPreferredSize(new java.awt.Dimension(830, 548));

        AssestsPanel.setBackground(new java.awt.Color(255, 255, 255));
        AssestsPanel.setPreferredSize(new java.awt.Dimension(825, 519));

        lableAssestsdescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsdescription.setText("Description");

        txtAssestsDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableAssestsMethod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsMethod.setText("Payment Method");

        txtAssestsMethod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Money", "Bank" }));
        txtAssestsMethod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsMethod.setBackground(new Color(255,255,255));

        lableAssestsDepartment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsDepartment.setText("Department");

        txtAssestsDepartment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Finance", "Event", "Room", "Stock", "HR", "Transport", "Restaurant" }));
        txtAssestsDepartment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsDepartment.setBackground(new Color(255,255,255));

        txtAssestsPaymentType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsPaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Assest", "Income", "Expenditure", "Budget", "Investment", "Liability" }));
        txtAssestsPaymentType.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsPaymentType.setEnabled(false);
        txtAssestsPaymentType.setBackground(new Color(255,255,255));

        lableAssestsPaymentType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsPaymentType.setText("Cashflow Type");

        txtAssestsDebitCredit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsDebitCredit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit", "Debit" }));
        txtAssestsDebitCredit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsDebitCredit.setBackground(new Color(255,255,255));

        lableAssestsdebitcredit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsdebitcredit.setText("Debit / Credit");

        lableAssestsdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsdate.setText("Date");

        txtAssestsAmount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableAssestsamount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsamount.setText("Amount");

        txtAssestsday.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsday.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtAssestsday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsday.setBackground(new Color(255,255,255));

        txtAssestsMonth.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtAssestsMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsMonth.setBackground(new Color(255,255,255));

        txtAssestsYear.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtAssestsYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsYear.setBackground(new Color(255,255,255));

        jScrollPaneAssests.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneAssests.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneAssests.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneAssests.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableAssests.setAutoCreateRowSorter(true);
        jTableAssests.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableAssests.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableAssests.setModel(new javax.swing.table.DefaultTableModel(
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
                "Assests ID", "Date", "Description", "Bank / Money", "Amount", "Debit / Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAssests.setGridColor(new java.awt.Color(102, 153, 255));
        jTableAssests.setRowHeight(20);
        jTableAssests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAssestsMouseClicked(evt);
            }
        });
        jScrollPaneAssests.setViewportView(jTableAssests);

        jButtonAddAssests.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAddAssests.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAddAssests.setForeground(new java.awt.Color(51, 153, 255));
        jButtonAddAssests.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Addbutton.png")); // NOI18N
        jButtonAddAssests.setText("Add");
        jButtonAddAssests.setFocusable(false);
        jButtonAddAssests.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAddAssests.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonAddAssests.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonAddAssests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddAssestsActionPerformed(evt);
            }
        });

        jButtonResetAssests.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetAssests.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetAssests.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetAssests.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetAssests.setText("Reset");
        jButtonResetAssests.setFocusable(false);
        jButtonResetAssests.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetAssests.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetAssests.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetAssests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetAssestsActionPerformed(evt);
            }
        });

        jButtonDemoAssests.setBackground(new java.awt.Color(255, 255, 255));
        jButtonDemoAssests.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonDemoAssests.setForeground(new java.awt.Color(51, 153, 255));
        jButtonDemoAssests.setText("Demo");
        jButtonDemoAssests.setFocusable(false);
        jButtonDemoAssests.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonDemoAssests.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonDemoAssests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDemoAssestsActionPerformed(evt);
            }
        });

        jButtonAcceptAllAssests.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAcceptAllAssests.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAcceptAllAssests.setForeground(new java.awt.Color(102, 153, 255));
        jButtonAcceptAllAssests.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\acceptbutton.png")); // NOI18N
        jButtonAcceptAllAssests.setText("   Accept");
        jButtonAcceptAllAssests.setFocusable(false);
        jButtonAcceptAllAssests.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAcceptAllAssests.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonAcceptAllAssests.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonAcceptAllAssests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcceptAllAssestsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AssestsPanelLayout = new javax.swing.GroupLayout(AssestsPanel);
        AssestsPanel.setLayout(AssestsPanelLayout);
        AssestsPanelLayout.setHorizontalGroup(
            AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssestsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneAssests)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AssestsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonDemoAssests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonResetAssests, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAcceptAllAssests, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonAddAssests, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(AssestsPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AssestsPanelLayout.createSequentialGroup()
                        .addComponent(lableAssestsPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtAssestsPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AssestsPanelLayout.createSequentialGroup()
                        .addComponent(lableAssestsDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtAssestsDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AssestsPanelLayout.createSequentialGroup()
                        .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableAssestsdescription, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableAssestsMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAssestsDescription)
                            .addComponent(txtAssestsMethod, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AssestsPanelLayout.createSequentialGroup()
                        .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableAssestsamount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableAssestsdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AssestsPanelLayout.createSequentialGroup()
                                .addComponent(txtAssestsday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAssestsMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAssestsYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtAssestsAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AssestsPanelLayout.createSequentialGroup()
                        .addComponent(lableAssestsdebitcredit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtAssestsDebitCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );
        AssestsPanelLayout.setVerticalGroup(
            AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssestsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAssestsDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lableAssestsdescription))
                    .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAssestsAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lableAssestsamount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableAssestsMethod)
                        .addComponent(txtAssestsMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableAssestsdate)
                        .addComponent(txtAssestsday, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAssestsMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAssestsYear, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableAssestsDepartment)
                        .addComponent(txtAssestsDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableAssestsdebitcredit)
                        .addComponent(txtAssestsDebitCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableAssestsPaymentType)
                    .addComponent(txtAssestsPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPaneAssests, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(AssestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddAssests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonResetAssests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDemoAssests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAcceptAllAssests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPaneAssests.addTab("Add Assests", AssestsPanel);

        AssestsPanel1.setBackground(new java.awt.Color(255, 255, 255));
        AssestsPanel1.setPreferredSize(new java.awt.Dimension(825, 519));

        lableAssestsId1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsId1.setText("Assests ID");

        lableAssestsdescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsdescription1.setText("Description");

        txtAssestsId1.setEditable(false);
        txtAssestsId1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsId1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        txtAssestsDescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsDescription1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableAssestsMethod1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsMethod1.setText("Payment Method");

        txtAssestsMethod1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsMethod1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Money", "Bank" }));
        txtAssestsMethod1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsMethod1.setBackground(new Color(255,255,255));

        lableAssestsDepartment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsDepartment1.setText("Department");

        txtAssestsDepartment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsDepartment1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Finance", "Event", "Room", "Stock", "HR", "Transport", "Restaurant" }));
        txtAssestsDepartment1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsDepartment1.setBackground(new Color(255,255,255));

        txtAssestsPaymentType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsPaymentType1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Assest", "Income", "Expenditure", "Budget", "Investment", "Liability" }));
        txtAssestsPaymentType1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsPaymentType1.setEnabled(false);
        txtAssestsPaymentType1.setBackground(new Color(255,255,255));

        lableAssestsPaymentType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsPaymentType1.setText("Cashflow Type");

        txtAssestsDebitCredit1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsDebitCredit1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit", "Debit" }));
        txtAssestsDebitCredit1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsDebitCredit1.setBackground(new Color(255,255,255));

        lableAssestsdebitcredit1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsdebitcredit1.setText("Debit / Credit");

        lableAssestsdate1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsdate1.setText("Date");

        txtAssestsAmount1.setEditable(false);
        txtAssestsAmount1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsAmount1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableAssestsamount1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableAssestsamount1.setText("Amount");

        txtAssestsday1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsday1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtAssestsday1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsday1.setBackground(new Color(255,255,255));

        txtAssestsMonth1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtAssestsMonth1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsMonth1.setBackground(new Color(255,255,255));

        txtAssestsYear1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAssestsYear1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtAssestsYear1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtAssestsYear1.setBackground(new Color(255,255,255));

        jScrollPaneAssests1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneAssests1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneAssests1.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneAssests1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableAssests1.setAutoCreateRowSorter(true);
        jTableAssests1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableAssests1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableAssests1.setModel(new javax.swing.table.DefaultTableModel(
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
                "Assests ID", "Date", "Description", "Bank / Money", "Amount", "Debit / Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAssests1.setGridColor(new java.awt.Color(102, 153, 255));
        jTableAssests1.setRowHeight(20);
        jTableAssests1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAssests1MouseClicked(evt);
            }
        });
        jScrollPaneAssests1.setViewportView(jTableAssests1);

        jButtonResetAssests1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetAssests1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetAssests1.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetAssests1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetAssests1.setText("Reset");
        jButtonResetAssests1.setFocusable(false);
        jButtonResetAssests1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetAssests1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetAssests1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetAssests1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetAssests1ActionPerformed(evt);
            }
        });

        jButtonUpdateAssests1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonUpdateAssests1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonUpdateAssests1.setForeground(new java.awt.Color(51, 153, 255));
        jButtonUpdateAssests1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Updatebutton.png")); // NOI18N
        jButtonUpdateAssests1.setText("Update");
        jButtonUpdateAssests1.setFocusable(false);
        jButtonUpdateAssests1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonUpdateAssests1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonUpdateAssests1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonUpdateAssests1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateAssests1ActionPerformed(evt);
            }
        });

        jButtonSearchAssests1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonSearchAssests1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonSearchAssests1.setForeground(new java.awt.Color(51, 153, 255));
        jButtonSearchAssests1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Findbutton.png")); // NOI18N
        jButtonSearchAssests1.setText("Find");
        jButtonSearchAssests1.setFocusable(false);
        jButtonSearchAssests1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonSearchAssests1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonSearchAssests1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonSearchAssests1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchAssests1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AssestsPanel1Layout = new javax.swing.GroupLayout(AssestsPanel1);
        AssestsPanel1.setLayout(AssestsPanel1Layout);
        AssestsPanel1Layout.setHorizontalGroup(
            AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssestsPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneAssests1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AssestsPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonResetAssests1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSearchAssests1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonUpdateAssests1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(AssestsPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AssestsPanel1Layout.createSequentialGroup()
                        .addComponent(lableAssestsDepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtAssestsDepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AssestsPanel1Layout.createSequentialGroup()
                            .addComponent(lableAssestsId1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtAssestsId1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(AssestsPanel1Layout.createSequentialGroup()
                            .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lableAssestsdescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lableAssestsMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtAssestsDescription1)
                                .addComponent(txtAssestsMethod1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AssestsPanel1Layout.createSequentialGroup()
                        .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableAssestsamount1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableAssestsdate1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AssestsPanel1Layout.createSequentialGroup()
                                .addComponent(txtAssestsday1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAssestsMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAssestsYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtAssestsAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(AssestsPanel1Layout.createSequentialGroup()
                        .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AssestsPanel1Layout.createSequentialGroup()
                                .addComponent(lableAssestsdebitcredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AssestsPanel1Layout.createSequentialGroup()
                                .addComponent(lableAssestsPaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)))
                        .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAssestsPaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAssestsDebitCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(61, 61, 61))
        );
        AssestsPanel1Layout.setVerticalGroup(
            AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AssestsPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAssestsId1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableAssestsId1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAssestsAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableAssestsamount1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAssestsDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableAssestsdescription1)
                    .addComponent(lableAssestsdate1)
                    .addComponent(txtAssestsday1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAssestsMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAssestsYear1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableAssestsMethod1)
                    .addComponent(txtAssestsMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableAssestsdebitcredit1)
                    .addComponent(txtAssestsDebitCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableAssestsPaymentType1)
                        .addComponent(txtAssestsPaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableAssestsDepartment1)
                        .addComponent(txtAssestsDepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addComponent(jScrollPaneAssests1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(AssestsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonResetAssests1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdateAssests1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearchAssests1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPaneAssests.addTab("Search & Update", AssestsPanel1);

        javax.swing.GroupLayout assestsPanelLayout = new javax.swing.GroupLayout(assestsPanel);
        assestsPanel.setLayout(assestsPanelLayout);
        assestsPanelLayout.setHorizontalGroup(
            assestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneAssests, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        assestsPanelLayout.setVerticalGroup(
            assestsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneAssests, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        liabilitiesPanel.setBackground(new java.awt.Color(255, 255, 255));
        liabilitiesPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        liabilitiesPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jTabbedPaneLiabilities.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneLiabilities.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPaneLiabilities.setPreferredSize(new java.awt.Dimension(830, 548));

        LiabilityPanel.setBackground(new java.awt.Color(255, 255, 255));
        LiabilityPanel.setPreferredSize(new java.awt.Dimension(825, 519));

        lableLiabilitydescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilitydescription.setText("Description");

        txtLiabilityDescription.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityDescription.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableLiabilityMethod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilityMethod.setText("Payment Method");

        txtLiabilityMethod.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Money", "Bank" }));
        txtLiabilityMethod.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtLiabilityMethod.setBackground(new Color(255,255,255));

        txtLiabilityPaymentType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityPaymentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Liability", "Assest", "Income", "Expenditure", "Budget", "Investment" }));
        txtLiabilityPaymentType.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtLiabilityPaymentType.setEnabled(false);
        txtLiabilityPaymentType.setBackground(new Color(255,255,255));

        lableLiabilityPaymentType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilityPaymentType.setText("Cashflow Type");

        txtLiabilityDebitCredit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityDebitCredit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Debit", "Credit" }));
        txtLiabilityDebitCredit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtLiabilityDebitCredit.setBackground(new Color(255,255,255));

        lableLiabilitydebitcredit.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilitydebitcredit.setText("Debit / Credit");

        lableLiabilitydate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilitydate.setText("Date");

        txtLiabilityAmount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableLiabilityamount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilityamount.setText("Amount");

        txtLiabilityday.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityday.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtLiabilityday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtLiabilityday.setBackground(new Color(255,255,255));

        txtLiabilityMonth.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtLiabilityMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtLiabilityMonth.setBackground(new Color(255,255,255));

        txtLiabilityYear.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtLiabilityYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtLiabilityYear.setBackground(new Color(255,255,255));

        jScrollPaneLiability.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneLiability.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneLiability.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneLiability.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableLiability.setAutoCreateRowSorter(true);
        jTableLiability.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableLiability.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableLiability.setModel(new javax.swing.table.DefaultTableModel(
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
                "Liability ID", "Date", "Description", "Bank / Money", "Amount", "Debit / Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLiability.setGridColor(new java.awt.Color(102, 153, 255));
        jTableLiability.setRowHeight(20);
        jTableLiability.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLiabilityMouseClicked(evt);
            }
        });
        jScrollPaneLiability.setViewportView(jTableLiability);

        jButtonAddLiability.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAddLiability.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAddLiability.setForeground(new java.awt.Color(51, 153, 255));
        jButtonAddLiability.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Addbutton.png")); // NOI18N
        jButtonAddLiability.setText("Add");
        jButtonAddLiability.setFocusable(false);
        jButtonAddLiability.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAddLiability.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonAddLiability.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonAddLiability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddLiabilityActionPerformed(evt);
            }
        });

        jButtonResetLiability.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetLiability.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetLiability.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetLiability.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetLiability.setText("Reset");
        jButtonResetLiability.setFocusable(false);
        jButtonResetLiability.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetLiability.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetLiability.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetLiability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetLiabilityActionPerformed(evt);
            }
        });

        jButtonDemoLiability.setBackground(new java.awt.Color(255, 255, 255));
        jButtonDemoLiability.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonDemoLiability.setForeground(new java.awt.Color(51, 153, 255));
        jButtonDemoLiability.setText("Demo");
        jButtonDemoLiability.setFocusable(false);
        jButtonDemoLiability.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonDemoLiability.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonDemoLiability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDemoLiabilityActionPerformed(evt);
            }
        });

        jButtonAcceptAllinvestment1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAcceptAllinvestment1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAcceptAllinvestment1.setForeground(new java.awt.Color(102, 153, 255));
        jButtonAcceptAllinvestment1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\acceptbutton.png")); // NOI18N
        jButtonAcceptAllinvestment1.setText("   Accept");
        jButtonAcceptAllinvestment1.setFocusable(false);
        jButtonAcceptAllinvestment1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAcceptAllinvestment1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonAcceptAllinvestment1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonAcceptAllinvestment1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcceptAllinvestment1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LiabilityPanelLayout = new javax.swing.GroupLayout(LiabilityPanel);
        LiabilityPanel.setLayout(LiabilityPanelLayout);
        LiabilityPanelLayout.setHorizontalGroup(
            LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LiabilityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneLiability)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LiabilityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonDemoLiability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonResetLiability, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAcceptAllinvestment1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonAddLiability, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(LiabilityPanelLayout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LiabilityPanelLayout.createSequentialGroup()
                        .addComponent(lableLiabilityPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtLiabilityPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LiabilityPanelLayout.createSequentialGroup()
                        .addGroup(LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableLiabilitydescription, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableLiabilityMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLiabilityDescription)
                            .addComponent(txtLiabilityMethod, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LiabilityPanelLayout.createSequentialGroup()
                        .addGroup(LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableLiabilityamount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableLiabilitydate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LiabilityPanelLayout.createSequentialGroup()
                                .addComponent(txtLiabilityday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLiabilityMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLiabilityYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtLiabilityAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(LiabilityPanelLayout.createSequentialGroup()
                        .addComponent(lableLiabilitydebitcredit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtLiabilityDebitCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );
        LiabilityPanelLayout.setVerticalGroup(
            LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LiabilityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLiabilityAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableLiabilityamount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLiabilityDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableLiabilitydescription))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableLiabilitydate)
                    .addComponent(txtLiabilityday, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLiabilityMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLiabilityYear, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableLiabilityMethod)
                    .addComponent(txtLiabilityMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableLiabilityPaymentType)
                        .addComponent(txtLiabilityPaymentType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lableLiabilitydebitcredit)
                        .addComponent(txtLiabilityDebitCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56)
                .addComponent(jScrollPaneLiability, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(LiabilityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAddLiability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonResetLiability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDemoLiability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAcceptAllinvestment1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPaneLiabilities.addTab("Add Liability", LiabilityPanel);

        LiabilityPanel1.setBackground(new java.awt.Color(255, 255, 255));
        LiabilityPanel1.setPreferredSize(new java.awt.Dimension(825, 519));

        lableLiabilityId1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilityId1.setText("Liability ID");

        lableLiabilitydescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilitydescription1.setText("Description");

        txtLiabilityId1.setEditable(false);
        txtLiabilityId1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityId1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        txtLiabilityDescription1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityDescription1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableLiabilityMethod1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilityMethod1.setText("Payment Method");

        txtLiabilityMethod1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityMethod1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Money", "Bank" }));
        txtLiabilityMethod1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtLiabilityMethod1.setBackground(new Color(255,255,255));

        txtLiabilityPaymentType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityPaymentType1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Liability", "Assest", "Income", "Expenditure", "Budget", "Investment" }));
        txtLiabilityPaymentType1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtLiabilityPaymentType1.setEnabled(false);
        txtLiabilityPaymentType1.setBackground(new Color(255,255,255));

        lableLiabilityPaymentType1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilityPaymentType1.setText("Cashflow Type");

        txtLiabilityDebitCredit1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityDebitCredit1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Debit", "Credit" }));
        txtLiabilityDebitCredit1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtLiabilityDebitCredit1.setBackground(new Color(255,255,255));

        lableLiabilitydebitcredit1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilitydebitcredit1.setText("Debit / Credit");

        lableLiabilitydate1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilitydate1.setText("Date");

        txtLiabilityAmount1.setEditable(false);
        txtLiabilityAmount1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityAmount1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableLiabilityamount1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableLiabilityamount1.setText("Amount");

        txtLiabilityday1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityday1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtLiabilityday1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtLiabilityday1.setBackground(new Color(255,255,255));

        txtLiabilityMonth1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtLiabilityMonth1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtLiabilityMonth1.setBackground(new Color(255,255,255));

        txtLiabilityYear1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLiabilityYear1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtLiabilityYear1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtLiabilityYear1.setBackground(new Color(255,255,255));

        jScrollPaneLiability1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneLiability1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneLiability1.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneLiability1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableLiability1.setAutoCreateRowSorter(true);
        jTableLiability1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableLiability1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableLiability1.setModel(new javax.swing.table.DefaultTableModel(
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
                "Liability ID", "Date", "Description", "Bank / Money", "Amount", "Debit / Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLiability1.setGridColor(new java.awt.Color(102, 153, 255));
        jTableLiability1.setRowHeight(20);
        jTableLiability1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableLiability1MouseClicked(evt);
            }
        });
        jScrollPaneLiability1.setViewportView(jTableLiability1);

        jButtonResetLiability1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetLiability1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetLiability1.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetLiability1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetLiability1.setText("Reset");
        jButtonResetLiability1.setFocusable(false);
        jButtonResetLiability1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetLiability1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetLiability1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetLiability1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetLiability1ActionPerformed(evt);
            }
        });

        jButtonUpdateLiability1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonUpdateLiability1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonUpdateLiability1.setForeground(new java.awt.Color(51, 153, 255));
        jButtonUpdateLiability1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Updatebutton.png")); // NOI18N
        jButtonUpdateLiability1.setText("Update");
        jButtonUpdateLiability1.setFocusable(false);
        jButtonUpdateLiability1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonUpdateLiability1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonUpdateLiability1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonUpdateLiability1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateLiability1ActionPerformed(evt);
            }
        });

        jButtonSearchLiability1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonSearchLiability1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonSearchLiability1.setForeground(new java.awt.Color(51, 153, 255));
        jButtonSearchLiability1.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Findbutton.png")); // NOI18N
        jButtonSearchLiability1.setText("Find");
        jButtonSearchLiability1.setFocusable(false);
        jButtonSearchLiability1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonSearchLiability1.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonSearchLiability1.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonSearchLiability1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchLiability1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LiabilityPanel1Layout = new javax.swing.GroupLayout(LiabilityPanel1);
        LiabilityPanel1.setLayout(LiabilityPanel1Layout);
        LiabilityPanel1Layout.setHorizontalGroup(
            LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LiabilityPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneLiability1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LiabilityPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonResetLiability1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSearchLiability1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonUpdateLiability1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(LiabilityPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(LiabilityPanel1Layout.createSequentialGroup()
                        .addComponent(lableLiabilityPaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(txtLiabilityPaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(LiabilityPanel1Layout.createSequentialGroup()
                            .addComponent(lableLiabilityId1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtLiabilityId1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(LiabilityPanel1Layout.createSequentialGroup()
                            .addGroup(LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lableLiabilitydescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lableLiabilityMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtLiabilityDescription1)
                                .addComponent(txtLiabilityMethod1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LiabilityPanel1Layout.createSequentialGroup()
                        .addGroup(LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableLiabilityamount1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableLiabilitydate1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(LiabilityPanel1Layout.createSequentialGroup()
                                .addComponent(txtLiabilityday1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLiabilityMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLiabilityYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtLiabilityAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(LiabilityPanel1Layout.createSequentialGroup()
                        .addComponent(lableLiabilitydebitcredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtLiabilityDebitCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );
        LiabilityPanel1Layout.setVerticalGroup(
            LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LiabilityPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLiabilityId1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableLiabilityId1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLiabilityAmount1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableLiabilityamount1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLiabilityDescription1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableLiabilitydescription1)
                    .addComponent(lableLiabilitydate1)
                    .addComponent(txtLiabilityday1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLiabilityMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLiabilityYear1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableLiabilityMethod1)
                    .addComponent(txtLiabilityMethod1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableLiabilitydebitcredit1)
                    .addComponent(txtLiabilityDebitCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableLiabilityPaymentType1)
                    .addComponent(txtLiabilityPaymentType1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPaneLiability1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(LiabilityPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonResetLiability1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdateLiability1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearchLiability1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPaneLiabilities.addTab("Search & Update", LiabilityPanel1);

        javax.swing.GroupLayout liabilitiesPanelLayout = new javax.swing.GroupLayout(liabilitiesPanel);
        liabilitiesPanel.setLayout(liabilitiesPanelLayout);
        liabilitiesPanelLayout.setHorizontalGroup(
            liabilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneLiabilities, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        liabilitiesPanelLayout.setVerticalGroup(
            liabilitiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneLiabilities, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        budgetPanel.setBackground(new java.awt.Color(255, 255, 255));
        budgetPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        budgetPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jTabbedPaneBudget.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneBudget.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPaneBudget.setPreferredSize(new java.awt.Dimension(830, 548));

        BudgetRequestPanel.setBackground(new java.awt.Color(255, 255, 255));
        BudgetRequestPanel.setPreferredSize(new java.awt.Dimension(825, 519));

        lableBudgetRequestId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableBudgetRequestId.setText("Request ID");

        txtBudgetRequestId.setEditable(false);
        txtBudgetRequestId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBudgetRequestId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableBudgetRequestDepartment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableBudgetRequestDepartment.setText("Department");

        lableBudgetRequestdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableBudgetRequestdate.setText("Date");

        txtBudgetRequestAmount.setEditable(false);
        txtBudgetRequestAmount.setBackground(new java.awt.Color(255, 255, 255));
        txtBudgetRequestAmount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBudgetRequestAmount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableBudgetRequestamount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableBudgetRequestamount.setText("Amount");

        txtBudgetRequestday.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBudgetRequestday.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtBudgetRequestday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtBudgetRequestday.setBackground(new Color(255,255,255));

        txtBudgetRequestMonth.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBudgetRequestMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtBudgetRequestMonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtBudgetRequestMonth.setBackground(new Color(255,255,255));

        txtBudgetRequestYear.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBudgetRequestYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtBudgetRequestYear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtBudgetRequestYear.setBackground(new Color(255,255,255));

        jScrollPaneBudgetRequest.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneBudgetRequest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneBudgetRequest.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneBudgetRequest.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableBudgetRequest.setAutoCreateRowSorter(true);
        jTableBudgetRequest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableBudgetRequest.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableBudgetRequest.setModel(new javax.swing.table.DefaultTableModel(
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
                "Request ID", "Date", "Description", "Bank / Money", "Amount", "Debit / Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBudgetRequest.setGridColor(new java.awt.Color(102, 153, 255));
        jTableBudgetRequest.setRowHeight(20);
        jTableBudgetRequest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBudgetRequestMouseClicked(evt);
            }
        });
        jScrollPaneBudgetRequest.setViewportView(jTableBudgetRequest);

        jButtonAcceptBudgetRequest.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAcceptBudgetRequest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonAcceptBudgetRequest.setForeground(new java.awt.Color(102, 153, 255));
        jButtonAcceptBudgetRequest.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\acceptbutton.png")); // NOI18N
        jButtonAcceptBudgetRequest.setText("Accept");
        jButtonAcceptBudgetRequest.setFocusable(false);
        jButtonAcceptBudgetRequest.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAcceptBudgetRequest.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonAcceptBudgetRequest.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonAcceptBudgetRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcceptBudgetRequestActionPerformed(evt);
            }
        });

        jButtonResetBudgetRequest.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetBudgetRequest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetBudgetRequest.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetBudgetRequest.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetBudgetRequest.setText("Reset");
        jButtonResetBudgetRequest.setFocusable(false);
        jButtonResetBudgetRequest.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetBudgetRequest.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetBudgetRequest.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetBudgetRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetBudgetRequestActionPerformed(evt);
            }
        });

        jButtonCheckBudgetRequest.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCheckBudgetRequest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonCheckBudgetRequest.setForeground(new java.awt.Color(102, 153, 255));
        jButtonCheckBudgetRequest.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Checkbutton.png")); // NOI18N
        jButtonCheckBudgetRequest.setText("Reject");
        jButtonCheckBudgetRequest.setFocusable(false);
        jButtonCheckBudgetRequest.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonCheckBudgetRequest.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonCheckBudgetRequest.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonCheckBudgetRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckBudgetRequestActionPerformed(evt);
            }
        });

        jButtonSearchBudgetRequest.setBackground(new java.awt.Color(255, 255, 255));
        jButtonSearchBudgetRequest.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonSearchBudgetRequest.setForeground(new java.awt.Color(51, 153, 255));
        jButtonSearchBudgetRequest.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Findbutton.png")); // NOI18N
        jButtonSearchBudgetRequest.setText("Find");
        jButtonSearchBudgetRequest.setFocusable(false);
        jButtonSearchBudgetRequest.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonSearchBudgetRequest.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonSearchBudgetRequest.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonSearchBudgetRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchBudgetRequestActionPerformed(evt);
            }
        });

        lableBudgetRequestDepartment1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableBudgetRequestDepartment1.setText("Approval");

        txtBudgetRequestDepartment.setEditable(false);
        txtBudgetRequestDepartment.setBackground(new java.awt.Color(255, 255, 255));
        txtBudgetRequestDepartment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBudgetRequestDepartment.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        txtBudgetRequestApproval.setEditable(false);
        txtBudgetRequestApproval.setBackground(new java.awt.Color(255, 255, 255));
        txtBudgetRequestApproval.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBudgetRequestApproval.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableBudgetRequestId1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableBudgetRequestId1.setText("Budget ID");

        txtBudgetRequestId1.setEditable(false);
        txtBudgetRequestId1.setBackground(new java.awt.Color(255, 255, 255));
        txtBudgetRequestId1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtBudgetRequestId1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        javax.swing.GroupLayout BudgetRequestPanelLayout = new javax.swing.GroupLayout(BudgetRequestPanel);
        BudgetRequestPanel.setLayout(BudgetRequestPanelLayout);
        BudgetRequestPanelLayout.setHorizontalGroup(
            BudgetRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BudgetRequestPanelLayout.createSequentialGroup()
                .addGroup(BudgetRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BudgetRequestPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(BudgetRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BudgetRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(BudgetRequestPanelLayout.createSequentialGroup()
                                    .addComponent(lableBudgetRequestId, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtBudgetRequestId, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BudgetRequestPanelLayout.createSequentialGroup()
                                    .addComponent(lableBudgetRequestDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(40, 40, 40)
                                    .addComponent(txtBudgetRequestDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(BudgetRequestPanelLayout.createSequentialGroup()
                                .addComponent(lableBudgetRequestId1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtBudgetRequestId1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addGroup(BudgetRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableBudgetRequestamount, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableBudgetRequestdate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableBudgetRequestDepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(BudgetRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBudgetRequestApproval, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BudgetRequestPanelLayout.createSequentialGroup()
                                .addComponent(txtBudgetRequestday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBudgetRequestMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBudgetRequestYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtBudgetRequestAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61))
                    .addGroup(BudgetRequestPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(BudgetRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneBudgetRequest)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BudgetRequestPanelLayout.createSequentialGroup()
                                .addComponent(jButtonResetBudgetRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSearchBudgetRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonCheckBudgetRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonAcceptBudgetRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        BudgetRequestPanelLayout.setVerticalGroup(
            BudgetRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BudgetRequestPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(BudgetRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBudgetRequestId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableBudgetRequestId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBudgetRequestAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableBudgetRequestamount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(BudgetRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableBudgetRequestdate)
                    .addComponent(txtBudgetRequestday, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBudgetRequestMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBudgetRequestYear, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBudgetRequestId1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableBudgetRequestId1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(BudgetRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableBudgetRequestDepartment1)
                    .addComponent(txtBudgetRequestApproval, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableBudgetRequestDepartment)
                    .addComponent(txtBudgetRequestDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneBudgetRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(BudgetRequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAcceptBudgetRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonResetBudgetRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCheckBudgetRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearchBudgetRequest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPaneBudget.addTab("Budget Requests", BudgetRequestPanel);

        javax.swing.GroupLayout budgetPanelLayout = new javax.swing.GroupLayout(budgetPanel);
        budgetPanel.setLayout(budgetPanelLayout);
        budgetPanelLayout.setHorizontalGroup(
            budgetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneBudget, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        budgetPanelLayout.setVerticalGroup(
            budgetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneBudget, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        reportsPanel.setBackground(new java.awt.Color(255, 255, 255));
        reportsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        reportsPanel.setPreferredSize(new java.awt.Dimension(830, 550));
        reportsPanel.setLayout(null);

        jPanelReportCashflowStatement.setBackground(new java.awt.Color(255, 255, 255));
        jPanelReportCashflowStatement.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)), "Cashflow Statement", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(51, 153, 255))); // NOI18N

        jButtonGenCashflowStatement.setBackground(new java.awt.Color(51, 153, 255));
        jButtonGenCashflowStatement.setForeground(new java.awt.Color(255, 255, 255));
        jButtonGenCashflowStatement.setText("Generate");
        jButtonGenCashflowStatement.setFocusable(false);
        jButtonGenCashflowStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenCashflowStatementActionPerformed(evt);
            }
        });

        jDateChooserGenCashflowStatementto.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooserGenCashflowStatementto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        jDateChooserGenCashflowStatementfrom.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooserGenCashflowStatementfrom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));

        jComboBoxSelectDeptCashflowStatement.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Department", "All", "Finance", "Stock", "Transport", "Event", "HR", "Room", "Restaurent" }));
        jComboBoxSelectDeptCashflowStatement.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jComboBoxSelectDeptCashflowStatement.setBackground(new Color(255,255,255));

        javax.swing.GroupLayout jPanelReportCashflowStatementLayout = new javax.swing.GroupLayout(jPanelReportCashflowStatement);
        jPanelReportCashflowStatement.setLayout(jPanelReportCashflowStatementLayout);
        jPanelReportCashflowStatementLayout.setHorizontalGroup(
            jPanelReportCashflowStatementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReportCashflowStatementLayout.createSequentialGroup()
                .addGroup(jPanelReportCashflowStatementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelReportCashflowStatementLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(jPanelReportCashflowStatementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooserGenCashflowStatementto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserGenCashflowStatementfrom, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxSelectDeptCashflowStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelReportCashflowStatementLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jButtonGenCashflowStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanelReportCashflowStatementLayout.setVerticalGroup(
            jPanelReportCashflowStatementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelReportCashflowStatementLayout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(jComboBoxSelectDeptCashflowStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jDateChooserGenCashflowStatementfrom, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jDateChooserGenCashflowStatementto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(jButtonGenCashflowStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );

        reportsPanel.add(jPanelReportCashflowStatement);
        jPanelReportCashflowStatement.setBounds(11, 12, 406, 520);

        jPanelReportBnkStatement.setBackground(new java.awt.Color(255, 255, 255));
        jPanelReportBnkStatement.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)), "Bank Statement", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(51, 153, 255))); // NOI18N

        jButtonGenBnkStatement.setBackground(new java.awt.Color(51, 153, 255));
        jButtonGenBnkStatement.setForeground(new java.awt.Color(255, 255, 255));
        jButtonGenBnkStatement.setText("Generate");
        jButtonGenBnkStatement.setFocusable(false);
        jButtonGenBnkStatement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenBnkStatementActionPerformed(evt);
            }
        });

        jDateChooserGenBnkStatementto.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooserGenBnkStatementto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        jDateChooserGenBnkStatementfrom.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooserGenBnkStatementfrom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        javax.swing.GroupLayout jPanelReportBnkStatementLayout = new javax.swing.GroupLayout(jPanelReportBnkStatement);
        jPanelReportBnkStatement.setLayout(jPanelReportBnkStatementLayout);
        jPanelReportBnkStatementLayout.setHorizontalGroup(
            jPanelReportBnkStatementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReportBnkStatementLayout.createSequentialGroup()
                .addGroup(jPanelReportBnkStatementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelReportBnkStatementLayout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(jPanelReportBnkStatementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooserGenBnkStatementto, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserGenBnkStatementfrom, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelReportBnkStatementLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jButtonGenBnkStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanelReportBnkStatementLayout.setVerticalGroup(
            jPanelReportBnkStatementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelReportBnkStatementLayout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(jDateChooserGenBnkStatementfrom, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jDateChooserGenBnkStatementto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124)
                .addComponent(jButtonGenBnkStatement, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );

        reportsPanel.add(jPanelReportBnkStatement);
        jPanelReportBnkStatement.setBounds(427, 12, 392, 520);

        financeUserlogsPanel.setBackground(new java.awt.Color(255, 255, 255));
        financeUserlogsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        financeUserlogsPanel.setPreferredSize(new java.awt.Dimension(830, 550));

        jTabbedPaneUserlog.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneUserlog.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPaneUserlog.setPreferredSize(new java.awt.Dimension(830, 548));

        UserlogPanel.setBackground(new java.awt.Color(255, 255, 255));
        UserlogPanel.setPreferredSize(new java.awt.Dimension(825, 519));

        lableUserlogId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableUserlogId.setText("Userlog ID");

        txtUserlogId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUserlogId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        lableUserlogActionId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableUserlogActionId.setText("Action ID");

        lableUserlogDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableUserlogDate.setText("Date");

        lableUserlogActionType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lableUserlogActionType.setText("Action Type");

        txtUserlogday.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUserlogday.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        txtUserlogday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtBudgetRequestday.setBackground(new Color(255,255,255));

        txtUserlogmonth.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUserlogmonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        txtUserlogmonth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtBudgetRequestMonth.setBackground(new Color(255,255,255));

        txtyear.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtyear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Year", "2016", "2017", "2018", "2019", "2020", " " }));
        txtyear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtBudgetRequestYear.setBackground(new Color(255,255,255));

        jScrollPaneUserlog.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPaneUserlog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jScrollPaneUserlog.setForeground(new java.awt.Color(102, 153, 255));
        jScrollPaneUserlog.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTableBudgetRequest7.setAutoCreateRowSorter(true);
        jTableBudgetRequest7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        jTableBudgetRequest7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTableBudgetRequest7.setModel(new javax.swing.table.DefaultTableModel(
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
                "Request ID", "Date", "Description", "Bank / Money", "Amount", "Debit / Credit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableBudgetRequest7.setGridColor(new java.awt.Color(102, 153, 255));
        jTableBudgetRequest7.setRowHeight(20);
        jScrollPaneUserlog.setViewportView(jTableBudgetRequest7);

        jButtonResetUserlog.setBackground(new java.awt.Color(255, 255, 255));
        jButtonResetUserlog.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonResetUserlog.setForeground(new java.awt.Color(102, 153, 255));
        jButtonResetUserlog.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Resetbutton.png")); // NOI18N
        jButtonResetUserlog.setText("Reset");
        jButtonResetUserlog.setFocusable(false);
        jButtonResetUserlog.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonResetUserlog.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonResetUserlog.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonResetUserlog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetUserlogActionPerformed(evt);
            }
        });

        jButtonSearchUserlog.setBackground(new java.awt.Color(255, 255, 255));
        jButtonSearchUserlog.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonSearchUserlog.setForeground(new java.awt.Color(51, 153, 255));
        jButtonSearchUserlog.setIcon(new javax.swing.ImageIcon("D:\\test01\\src\\glen\\img\\Findbutton.png")); // NOI18N
        jButtonSearchUserlog.setText("Find");
        jButtonSearchUserlog.setFocusable(false);
        jButtonSearchUserlog.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonSearchUserlog.setMargin(new java.awt.Insets(2, 5, 2, 14));
        jButtonSearchUserlog.setPreferredSize(new java.awt.Dimension(73, 30));
        jButtonSearchUserlog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchUserlogActionPerformed(evt);
            }
        });

        txtUserlogActionId.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUserlogActionId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));

        txtUserlogActionType.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUserlogActionType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select one", "Income", "Expenditure", "Cashflow", "Assests", "Liability", "Investment", "Budget", "Budget Request", "System Login", "System Logout" }));
        txtUserlogActionType.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 153, 255)));
        txtBudgetRequestYear.setBackground(new Color(255,255,255));

        javax.swing.GroupLayout UserlogPanelLayout = new javax.swing.GroupLayout(UserlogPanel);
        UserlogPanel.setLayout(UserlogPanelLayout);
        UserlogPanelLayout.setHorizontalGroup(
            UserlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserlogPanelLayout.createSequentialGroup()
                .addGroup(UserlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(UserlogPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(UserlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(UserlogPanelLayout.createSequentialGroup()
                                .addComponent(lableUserlogId, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtUserlogId, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(UserlogPanelLayout.createSequentialGroup()
                                .addComponent(lableUserlogActionId, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtUserlogActionId, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addGroup(UserlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lableUserlogDate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lableUserlogActionType, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(UserlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(UserlogPanelLayout.createSequentialGroup()
                                .addComponent(txtUserlogday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUserlogmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtUserlogActionType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(78, 78, 78))
                    .addGroup(UserlogPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(UserlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPaneUserlog)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserlogPanelLayout.createSequentialGroup()
                                .addComponent(jButtonResetUserlog, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSearchUserlog, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        UserlogPanelLayout.setVerticalGroup(
            UserlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserlogPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(UserlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserlogId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableUserlogId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableUserlogActionType, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserlogActionType, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(UserlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableUserlogDate)
                    .addComponent(txtUserlogday, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUserlogmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtyear, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lableUserlogActionId)
                    .addComponent(txtUserlogActionId, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneUserlog, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(UserlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonResetUserlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearchUserlog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPaneUserlog.addTab("Userlog Records", UserlogPanel);

        javax.swing.GroupLayout financeUserlogsPanelLayout = new javax.swing.GroupLayout(financeUserlogsPanel);
        financeUserlogsPanel.setLayout(financeUserlogsPanelLayout);
        financeUserlogsPanelLayout.setHorizontalGroup(
            financeUserlogsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneUserlog, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
        );
        financeUserlogsPanelLayout.setVerticalGroup(
            financeUserlogsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneUserlog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout togglePanelsLayout = new javax.swing.GroupLayout(togglePanels);
        togglePanels.setLayout(togglePanelsLayout);
        togglePanelsLayout.setHorizontalGroup(
            togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(togglePanelsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(incomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(togglePanelsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(budgetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(financeUserlogsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(assestsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(reportsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(liabilitiesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cashflowPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(expenditurePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(togglePanelsLayout.createSequentialGroup()
                    .addGap(277, 277, 277)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(190, Short.MAX_VALUE)))
        );
        togglePanelsLayout.setVerticalGroup(
            togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(togglePanelsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(incomePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(togglePanelsLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(budgetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(financeUserlogsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(assestsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(reportsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(liabilitiesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cashflowPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(expenditurePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(togglePanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(togglePanelsLayout.createSequentialGroup()
                    .addGap(256, 256, 256)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(246, Short.MAX_VALUE)))
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

    private void buttonReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReportsActionPerformed
        financepanelshift(buttonReports.getText());
    }//GEN-LAST:event_buttonReportsActionPerformed

    private void buttonfinanceUserlogsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonfinanceUserlogsActionPerformed
        financepanelshift(buttonfinanceUserlogs.getText());
        jTableBudgetRequest7.setModel(DbUtils.resultSetToTableModel(data.finuserlogtableload()));
        TableColumn col=jTableBudgetRequest7.getColumnModel().getColumn(1);
        col.setMinWidth(130);
    }//GEN-LAST:event_buttonfinanceUserlogsActionPerformed

    private void buttonIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIncomeActionPerformed
        financepanelshift(buttonIncome.getText());
        financeincometableload();
        jButtonResetIncome.doClick(); jButtonResetIncome1.doClick();
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

    private void buttonAssestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAssestsActionPerformed
        financepanelshift(buttonAssests.getText());
        financeasseststableload();  
        jButtonResetAssests.doClick(); jButtonResetAssests1.doClick();
    }//GEN-LAST:event_buttonAssestsActionPerformed

    private void buttonLiabilitiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLiabilitiesActionPerformed
       financepanelshift(buttonLiabilities.getText());
       financeliabilitytableload(); 
       jButtonResetLiability.doClick(); jButtonResetLiability1.doClick();
    }//GEN-LAST:event_buttonLiabilitiesActionPerformed

    private void buttonBudgetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBudgetsActionPerformed
        financepanelshift(buttonBudgets.getText()); 
        jButtonResetBudgetRequest.doClick();    
        financebudgettableload();
    }//GEN-LAST:event_buttonBudgetsActionPerformed
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

    private void buttonAssestsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAssestsMouseEntered
        buttonAssests.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonAssestsMouseEntered

    private void buttonAssestsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAssestsMouseExited
        buttonAssests.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonAssestsMouseExited

    private void buttonLiabilitiesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLiabilitiesMouseEntered
        buttonLiabilities.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonLiabilitiesMouseEntered

    private void buttonLiabilitiesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonLiabilitiesMouseExited
        buttonLiabilities.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonLiabilitiesMouseExited

    private void buttonBudgetsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBudgetsMouseEntered
        buttonBudgets.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonBudgetsMouseEntered

    private void buttonBudgetsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonBudgetsMouseExited
        buttonBudgets.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonBudgetsMouseExited

    private void buttonReportsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonReportsMouseEntered
        buttonReports.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonReportsMouseEntered

    private void buttonReportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonReportsMouseExited
        buttonReports.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonReportsMouseExited

    private void buttonfinanceUserlogsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonfinanceUserlogsMouseEntered
        buttonfinanceUserlogs.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_buttonfinanceUserlogsMouseEntered

    private void buttonfinanceUserlogsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonfinanceUserlogsMouseExited
        buttonfinanceUserlogs.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_buttonfinanceUserlogsMouseExited

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

    private void jButtonResetAssestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetAssestsActionPerformed
        txtAssestsDescription.setText("");
        txtAssestsAmount.setText("");
        txtAssestsDepartment.setSelectedItem("Finance");
        txtAssestsMethod.setSelectedItem("Money");
        txtAssestsDebitCredit.setSelectedItem("Credit");
        txtAssestsday.setSelectedItem("Day");
        txtAssestsMonth.setSelectedItem("Month");
        txtAssestsYear.setSelectedItem("Year");
    }//GEN-LAST:event_jButtonResetAssestsActionPerformed

    private void jButtonResetLiabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetLiabilityActionPerformed
        txtLiabilityDescription.setText("");
        txtLiabilityAmount.setText("");
        txtLiabilityMethod.setSelectedItem("Money");
        txtLiabilityDebitCredit.setSelectedItem("Debit");
        txtLiabilityday.setSelectedItem("Day");
        txtLiabilityMonth.setSelectedItem("Month");
        txtLiabilityYear.setSelectedItem("Year");
    }//GEN-LAST:event_jButtonResetLiabilityActionPerformed

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

    private void jButtonDemoAssestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDemoAssestsActionPerformed
        txtAssestsDescription.setText("Office Cupboard");
        txtAssestsAmount.setText("30000");
        txtAssestsDepartment.setSelectedItem("Stock");
        txtAssestsMethod.setSelectedItem("Bank");
        txtAssestsDebitCredit.setSelectedItem("Credit");
        txtAssestsday.setSelectedItem("25");
        txtAssestsMonth.setSelectedItem("08");
        txtAssestsYear.setSelectedItem("2016");
    }//GEN-LAST:event_jButtonDemoAssestsActionPerformed

    private void jButtonDemoLiabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDemoLiabilityActionPerformed
        txtLiabilityDescription.setText("Bank Loan");
        txtLiabilityAmount.setText("500000");
        txtLiabilityMethod.setSelectedItem("Bank");
        txtLiabilityDebitCredit.setSelectedItem("Debit");
        txtLiabilityday.setSelectedItem("24");
        txtLiabilityMonth.setSelectedItem("08");
        txtLiabilityYear.setSelectedItem("2016");
    }//GEN-LAST:event_jButtonDemoLiabilityActionPerformed

    private void jButtonResetIncome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetIncome1ActionPerformed
        txtIncomeId1.setText("");
        txtIncomeDescription1.setText("");
        txtdIncomeAmount1.setText("");
        txtIncomeDepartment1.setSelectedItem("Finance");
        txtIncomeMethod1.setSelectedItem("Money");
        txtIncomeDebitCredit1.setSelectedItem("Debit");
        txtIncomeday1.setSelectedItem("Day");
        txtIncomeMonth1.setSelectedItem("Month");
        txtIncomeYear1.setSelectedItem("Year");
    }//GEN-LAST:event_jButtonResetIncome1ActionPerformed

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

    private void jButtonResetAssests1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetAssests1ActionPerformed
        txtAssestsId1.setText("");
        txtAssestsDescription1.setText("");
        txtAssestsAmount1.setText("");
        txtAssestsDepartment1.setSelectedItem("Finance");
        txtAssestsMethod1.setSelectedItem("Money");
        txtAssestsDebitCredit1.setSelectedItem("Credit");
        txtAssestsday1.setSelectedItem("Day");
        txtAssestsMonth1.setSelectedItem("Month");
        txtAssestsYear1.setSelectedItem("Year");
    }//GEN-LAST:event_jButtonResetAssests1ActionPerformed

    private void jButtonResetLiability1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetLiability1ActionPerformed
        txtLiabilityId1.setText("");
        txtLiabilityDescription1.setText("");
        txtLiabilityAmount1.setText("");
        txtLiabilityMethod1.setSelectedItem("Money");
        txtLiabilityDebitCredit1.setSelectedItem("Debit");
        txtLiabilityday1.setSelectedItem("Day");
        txtLiabilityMonth1.setSelectedItem("Month");
        txtLiabilityYear1.setSelectedItem("Year");
    }//GEN-LAST:event_jButtonResetLiability1ActionPerformed

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

    private void jButtonAddAssestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddAssestsActionPerformed
        try{
            description=txtAssestsDescription.getText();                             //getting values from interface
            paymentMethod=txtAssestsMethod.getSelectedItem().toString();
            department=txtAssestsDepartment.getSelectedItem().toString();
            cashflowType=txtAssestsPaymentType.getSelectedItem().toString();
            amountString=txtAssestsAmount.getText();
            day=txtAssestsday.getSelectedItem().toString();
            month=txtAssestsMonth.getSelectedItem().toString();
            year=txtAssestsYear.getSelectedItem().toString();
            assestsStatus=txtAssestsDebitCredit.getSelectedItem().toString();
            cashflowApproval="approved";
            //---------------------------------------------------------------------------------------------------------------------------

            if(description.isEmpty())
                txtAssestsDescription.setBackground(new Color(255,150,150));         //validations of text fields
            else
                txtAssestsDescription.setBackground(new Color(255,255,255));
            if(day.equals("Day"))
                txtAssestsday.setBackground(new Color(255,150,150));
            else
                txtAssestsday.setBackground(new Color(255,255,255));
            if(month.equals("Month"))
                txtAssestsMonth.setBackground(new Color(255,150,150));
            else
                txtAssestsMonth.setBackground(new Color(255,255,255));
            if(year.equals("Year"))
                txtAssestsYear.setBackground(new Color(255,150,150));
            else
                txtAssestsYear.setBackground(new Color(255,255,255));
            if(amountString.isEmpty())
                txtAssestsAmount.setBackground(new Color(255,150,150));
            else{
                amount=Double.parseDouble(amountString);
                txtAssestsAmount.setBackground(new Color(255,255,255));
            }
            if(amountString.isEmpty()||description.isEmpty()||day.equals("Day")||month.equals("Month")||year.equals("Year")){
                JOptionPane.showMessageDialog(IncomePanel,"Please fill all the fields!","Form Incomplete!",0);
            }
            if(!amountString.isEmpty()&&!description.isEmpty()&&!day.equals("Day")&&!month.equals("Month")&&!year.equals("Year")){
                //---------------------------------------------------------------------------------------------------------------------------
                if(assestsStatus.equals("Credit"))                              // this is according to the low of accounts
                    paymentStatus="Debit";
                else
                    paymentStatus="Credit";
                //---------------------------------------------------------------------------------------------------------------------------
                int r=JOptionPane.showConfirmDialog(IncomePanel, "Are You Sure You Want To Continue?", "Confirm Add Entry!",0,2);
                if(r==0){
                    if(data.addAssests(year,  month,  day,  description,  department,  paymentMethod,  amount,  cashflowType,  paymentStatus,  cashflowApproval,  cashflowId, assestsStatus)){
                        data.adduserlogs(USER, "Assests", "Add",data.getlastid("fin_assests", "assests_id"));
                        jButtonResetAssests.doClick();
                        JOptionPane.showMessageDialog(assestsPanel,"Successfully Added To The Database!","Success Message!",1);
                        financeasseststableload();
                    }
                    else
                        JOptionPane.showMessageDialog(assestsPanel,"Please Check Data again!","Error Message!",0);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(assestsPanel,"Please Check Data again!","Error Message!",0);
        }
    }//GEN-LAST:event_jButtonAddAssestsActionPerformed

    private void jButtonAddLiabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddLiabilityActionPerformed
        try{
            description=txtLiabilityDescription.getText();                             //getting values from interface
            paymentMethod=txtLiabilityMethod.getSelectedItem().toString();
            cashflowType=txtLiabilityPaymentType.getSelectedItem().toString();
            amountString=txtLiabilityAmount.getText();
            day=txtLiabilityday.getSelectedItem().toString();
            month=txtLiabilityMonth.getSelectedItem().toString();
            year=txtLiabilityYear.getSelectedItem().toString();
            liabilityStatus=txtLiabilityDebitCredit.getSelectedItem().toString();
            cashflowApproval="approved";
            //---------------------------------------------------------------------------------------------------------------------------

            if(description.isEmpty())
                txtLiabilityDescription.setBackground(new Color(255,150,150));         //validations of text fields
            else
                txtLiabilityDescription.setBackground(new Color(255,255,255));
            if(day.equals("Day"))
                txtLiabilityday.setBackground(new Color(255,150,150));
            else
                txtLiabilityday.setBackground(new Color(255,255,255));
            if(month.equals("Month"))
                txtLiabilityMonth.setBackground(new Color(255,150,150));
            else
                txtLiabilityMonth.setBackground(new Color(255,255,255));
            if(year.equals("Year"))
                txtLiabilityYear.setBackground(new Color(255,150,150));
            else
                txtLiabilityYear.setBackground(new Color(255,255,255));
            if(amountString.isEmpty())
                txtLiabilityAmount.setBackground(new Color(255,150,150));
            else{
                amount=Double.parseDouble(amountString);
                txtLiabilityAmount.setBackground(new Color(255,255,255));
            }
            if(amountString.isEmpty()||description.isEmpty()||day.equals("Day")||month.equals("Month")||year.equals("Year")){
                JOptionPane.showMessageDialog(IncomePanel,"Please fill all the fields!","Form Incomplete!",0);
            }
            if(!amountString.isEmpty()&&!description.isEmpty()&&!day.equals("Day")&&!month.equals("Month")&&!year.equals("Year")){
                //---------------------------------------------------------------------------------------------------------------------------
                if(liabilityStatus.equals("Debit"))                                    // this is according to the low of accounts
                    paymentStatus="Credit";
                else
                    paymentStatus="Debit";
                //---------------------------------------------------------------------------------------------------------------------------
                int r=JOptionPane.showConfirmDialog(IncomePanel, "Are You Sure You Want To Continue?", "Confirm Add Entry!",0,2);
                if(r==0){
                    if(data.addLiability( year,  month,  day,  description,  department,  paymentMethod,  amount,  cashflowType,  paymentStatus,  cashflowApproval,  cashflowId,  liabilityStatus)){
                        data.adduserlogs(USER, "Liability", "Add",data.getlastid("fin_liability", "liability_id"));
                        jButtonResetLiability.doClick();
                        JOptionPane.showMessageDialog(LiabilityPanel,"Successfully Added To The Database!","Success Message!",1);
                        financeliabilitytableload();
                    }
                    else
                        JOptionPane.showMessageDialog(LiabilityPanel,"Please Check Data again!","Error Message!",0);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(LiabilityPanel,"Please Check Data again!","Error Message!",0);
        }
    }//GEN-LAST:event_jButtonAddLiabilityActionPerformed

    private void jButtonUpdateIncome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateIncome1ActionPerformed
        try{
            incomeId=Integer.parseInt(txtIncomeId1.getText());
            amount=Double.parseDouble(txtdIncomeAmount1.getText());
            description=txtIncomeDescription1.getText();
            incomeStatus=txtIncomeDebitCredit1.getSelectedItem().toString();
            department=txtIncomeDepartment1.getSelectedItem().toString();
            paymentMethod=txtIncomeMethod1.getSelectedItem().toString();
            year=txtIncomeYear1.getSelectedItem().toString();
            month=txtIncomeMonth1.getSelectedItem().toString();
            day=txtIncomeday1.getSelectedItem().toString();
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
    }//GEN-LAST:event_jButtonUpdateIncome1ActionPerformed

    private void jButtonFindIncome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindIncome1ActionPerformed
        financeSearch("income");
    }//GEN-LAST:event_jButtonFindIncome1ActionPerformed

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

    private void jButtonUpdateAssests1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateAssests1ActionPerformed
        try{
            amount=Double.parseDouble(txtAssestsAmount1.getText());
            description=txtAssestsDescription1.getText();
            incomeStatus=txtAssestsDebitCredit1.getSelectedItem().toString();
            department=txtAssestsDepartment1.getSelectedItem().toString();
            paymentMethod=txtAssestsMethod1.getSelectedItem().toString();
            year=txtAssestsYear1.getSelectedItem().toString();
            month=txtAssestsMonth1.getSelectedItem().toString();
            day=txtAssestsday1.getSelectedItem().toString();
            int r=JOptionPane.showConfirmDialog(IncomePanel, "Are You Sure You Want To Continue?", "Confirm Update Entry!",0,2);
            if(r==0){
                if(data.financeUpdate(cashflowId, assestsId, year, month, day, description, department, paymentMethod, paymentStatus, assestsStatus,"assests","assests")){
                    JOptionPane.showMessageDialog(IncomePanel, "Succesfully Updated!","Success Message",1);
                    data.adduserlogs(USER, "Assests", "Update",assestsId);
                }
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(togglePanels,"Enter Correct Values!","Input Error",0);
        }
        financeasseststableload();
    }//GEN-LAST:event_jButtonUpdateAssests1ActionPerformed

    private void jButtonSearchAssests1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchAssests1ActionPerformed
        financeSearch("assests");
    }//GEN-LAST:event_jButtonSearchAssests1ActionPerformed

    private void jButtonUpdateLiability1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateLiability1ActionPerformed
         try{
            amount=Double.parseDouble(txtLiabilityAmount1.getText());
            description=txtLiabilityDescription1.getText();
            incomeStatus=txtLiabilityDebitCredit1.getSelectedItem().toString();
            department="";
            paymentMethod=txtLiabilityMethod1.getSelectedItem().toString();
            year=txtLiabilityYear1.getSelectedItem().toString();
            month=txtLiabilityMonth1.getSelectedItem().toString();
            day=txtLiabilityday1.getSelectedItem().toString();
            int r=JOptionPane.showConfirmDialog(IncomePanel, "Are You Sure You Want To Continue?", "Confirm Update Entry!",0,2);
            if(r==0){
                if(data.financeUpdate(cashflowId, liabilityId, year, month, day, description, department, paymentMethod, paymentStatus, liabilityStatus,"liability","liability")){
                    JOptionPane.showMessageDialog(togglePanels, "Succesfully Updated!","Success Message",1);
                    data.adduserlogs(USER, "Liability", "Update",liabilityId);
                }
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(togglePanels,"Enter Correct Values!","Input Error",0);
        }
        financeliabilitytableload();
    }//GEN-LAST:event_jButtonUpdateLiability1ActionPerformed

    private void jButtonSearchLiability1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchLiability1ActionPerformed
        financeSearch("liability");
    }//GEN-LAST:event_jButtonSearchLiability1ActionPerformed

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

    private void jButtonAcceptAllIncomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcceptAllIncomeActionPerformed
        accept(cashflowId,"Income", "fin_income", "income_cashflow_id", "income_payment_status"); cashflowId=0;
        data.adduserlogs(USER, "Income", "Accept", data.getlastid("fin_income","income_id"));
    }//GEN-LAST:event_jButtonAcceptAllIncomeActionPerformed

    private void jButtonAcceptAllinvestment1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcceptAllinvestment1ActionPerformed
        accept(cashflowId,"Liability", "fin_liability", "liability_cashflow_id", "liability_payment_status"); cashflowId=0;
        data.adduserlogs(USER, "Liability", "Accept", data.getlastid("fin_liability","liability_id"));
    }//GEN-LAST:event_jButtonAcceptAllinvestment1ActionPerformed

    private void jButtonAcceptAllExpenditureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcceptAllExpenditureActionPerformed
        accept(cashflowId,"Expenditure", "fin_expenditure", "expense_cashflow_id", "expense_payment_status"); cashflowId=0;
        data.adduserlogs(USER, "Expenditure", "Accept", data.getlastid("fin_expenditure","expense_id"));        
    }//GEN-LAST:event_jButtonAcceptAllExpenditureActionPerformed

    private void jButtonAcceptAllAssestsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcceptAllAssestsActionPerformed
        accept(cashflowId,"Assests", "fin_assests", "assests_cashflow_id", "assests_payment_status"); cashflowId=0;
        data.adduserlogs(USER, "Assests", "Accept", data.getlastid("fin_assests","assests_id")); 
    }//GEN-LAST:event_jButtonAcceptAllAssestsActionPerformed

    private void jTableIncome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableIncome1MouseClicked
        int row= jTableIncome1.getSelectedRow(); 
        cashflowId=Integer.parseInt(jTableIncome1.getValueAt(row, 0).toString());
        incomeId=Integer.parseInt(jTableIncome1.getValueAt(row, 1).toString());     txtIncomeId1.setText(Integer.toString(incomeId));
        day= jTableIncome1.getValueAt(row, 2).toString().substring(8, 10);          txtIncomeday1.setSelectedItem(day);
        month=jTableIncome1.getValueAt(row, 2).toString().substring(5, 7);          txtIncomeMonth1.setSelectedItem(month);
        year=jTableIncome1.getValueAt(row, 2).toString().substring(0, 4);           txtIncomeYear1.setSelectedItem(year);
        description=jTableIncome1.getValueAt(row,3).toString();                     txtIncomeDescription1.setText(description);
        department= jTableIncome1.getValueAt(row, 4).toString();                    txtIncomeDepartment1.setSelectedItem(department);
        paymentMethod= jTableIncome1.getValueAt(row, 5).toString();                 txtIncomeMethod1.setSelectedItem(paymentMethod);
        amount= Double.parseDouble(jTableIncome1.getValueAt(row, 6).toString());    txtdIncomeAmount1.setText(Double.toString(amount));
        incomeStatus= jTableIncome1.getValueAt(row, 7).toString();                  txtIncomeDebitCredit1.setSelectedItem(incomeStatus);
    }//GEN-LAST:event_jTableIncome1MouseClicked

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

    private void jTableAssests1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAssests1MouseClicked
        int row= jTableAssests1.getSelectedRow(); 
        cashflowId=Integer.parseInt(jTableAssests1.getValueAt(row, 0).toString());
        assestsId=Integer.parseInt(jTableAssests1.getValueAt(row, 1).toString());    txtAssestsId1.setText(Integer.toString(assestsId));
        day= jTableAssests1.getValueAt(row, 2).toString().substring(8, 10);          txtAssestsday1.setSelectedItem(day);
        month=jTableAssests1.getValueAt(row, 2).toString().substring(5, 7);          txtAssestsMonth1.setSelectedItem(month);
        year=jTableAssests1.getValueAt(row, 2).toString().substring(0, 4);           txtAssestsYear1.setSelectedItem(year);
        description=jTableAssests1.getValueAt(row,3).toString();                     txtAssestsDescription1.setText(description);
        department= jTableAssests1.getValueAt(row, 4).toString();                    txtAssestsDepartment1.setSelectedItem(department);
        paymentMethod= jTableAssests1.getValueAt(row, 5).toString();                 txtAssestsMethod1.setSelectedItem(paymentMethod);
        amount= Double.parseDouble(jTableAssests1.getValueAt(row, 6).toString());    txtAssestsAmount1.setText(Double.toString(amount));
        assestsStatus= jTableAssests1.getValueAt(row, 7).toString();                 txtAssestsDebitCredit1.setSelectedItem(assestsStatus);
    }//GEN-LAST:event_jTableAssests1MouseClicked

    private void jTableLiability1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLiability1MouseClicked
        int row= jTableLiability1.getSelectedRow(); 
        cashflowId=Integer.parseInt(jTableLiability1.getValueAt(row, 0).toString());
        liabilityId=Integer.parseInt(jTableLiability1.getValueAt(row, 1).toString());  txtLiabilityId1.setText(Integer.toString(liabilityId));
        day= jTableLiability1.getValueAt(row, 2).toString().substring(8, 10);          txtLiabilityday1.setSelectedItem(day);
        month=jTableLiability1.getValueAt(row, 2).toString().substring(5, 7);          txtLiabilityMonth1.setSelectedItem(month);
        year=jTableLiability1.getValueAt(row, 2).toString().substring(0, 4);           txtLiabilityYear1.setSelectedItem(year);
        description=jTableLiability1.getValueAt(row,3).toString();                     txtLiabilityDescription1.setText(description);
        paymentMethod= jTableLiability1.getValueAt(row, 4).toString();                 txtLiabilityMethod1.setSelectedItem(paymentMethod);
        amount= Double.parseDouble(jTableLiability1.getValueAt(row, 5).toString());    txtLiabilityAmount1.setText(Double.toString(amount));
        liabilityStatus= jTableLiability1.getValueAt(row, 6).toString();               txtLiabilityDebitCredit1.setSelectedItem(liabilityStatus);
    }//GEN-LAST:event_jTableLiability1MouseClicked

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

    private void jButtonResetUserlogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetUserlogActionPerformed
        txtUserlogActionId.setText("");
        txtUserlogActionType.setSelectedItem("Select one");
        txtUserlogId.setText("");
        txtUserlogday.setSelectedItem("Day");
        txtUserlogmonth.setSelectedItem("Month");
        txtyear.setSelectedItem("Year");
        jTableBudgetRequest7.setModel(DbUtils.resultSetToTableModel(data.finuserlogtableload()));
        TableColumn col=jTableBudgetRequest7.getColumnModel().getColumn(1);
        col.setMinWidth(130);
    }//GEN-LAST:event_jButtonResetUserlogActionPerformed

    private void jButtonSearchUserlogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchUserlogActionPerformed
        
            actionType=txtUserlogActionType.getSelectedItem().toString();
            day=txtUserlogday.getSelectedItem().toString();
            month=txtUserlogmonth.getSelectedItem().toString();
            year=txtyear.getSelectedItem().toString();
            userlogActionId=txtUserlogActionId.getText();
            userlogId=txtUserlogId.getText();
                if(day.equals("Day"))
                    day="";
                if(month.equals("Month"))
                    month="";
                if(year.equals("Year"))
                    year="";
                if(actionType.equals("Select one"))
                    actionType="";
            if("".equals(actionType) && "".equals(day) && "".equals(month) && "".equals(year) && "".equals(userlogId) && "".equals(userlogActionId))
                JOptionPane.showMessageDialog(UserlogPanel, "Please Enter Atleast One Search Key!", "Input Empty!", 0);
                
            else{
                try{ 
                    Results=data.userlogSearch(userlogId,userlogActionId,actionType,day,month,year);
                    jTableBudgetRequest7.setModel(DbUtils.resultSetToTableModel(Results));
                    TableColumn col=jTableBudgetRequest7.getColumnModel().getColumn(1);
                    col.setMinWidth(130);
                    if(!Results.last())
                        JOptionPane.showMessageDialog(togglePanels,"No Records were Found!","Search Empty",1);
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
            }
    }//GEN-LAST:event_jButtonSearchUserlogActionPerformed

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

    private void jTableAssestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAssestsMouseClicked
        int row= jTableAssests.getSelectedRow(); 
        cashflowId=Integer.parseInt(jTableAssests.getValueAt(row, 0).toString());
        day= jTableAssests.getValueAt(row, 1).toString().substring(8, 10);          txtAssestsday.setSelectedItem(day);
        month=jTableAssests.getValueAt(row, 1).toString().substring(5, 7);          txtAssestsMonth.setSelectedItem(month);
        year=jTableAssests.getValueAt(row, 1).toString().substring(0, 4);           txtAssestsYear.setSelectedItem(year);
        description=jTableAssests.getValueAt(row,2).toString();                     txtAssestsDescription.setText(description);
        department= jTableAssests.getValueAt(row, 3).toString();                    txtAssestsDepartment.setSelectedItem(department);
        paymentMethod= jTableAssests.getValueAt(row, 4).toString();                 txtAssestsMethod.setSelectedItem(paymentMethod);
        amount= Double.parseDouble(jTableAssests.getValueAt(row, 5).toString());    txtAssestsAmount.setText(Double.toString(amount));
        assestsStatus= jTableAssests.getValueAt(row, 6).toString();                 txtAssestsDebitCredit.setSelectedItem(assestsStatus);
    }//GEN-LAST:event_jTableAssestsMouseClicked

    private void jTableLiabilityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableLiabilityMouseClicked
        int row= jTableLiability.getSelectedRow(); 
        cashflowId=Integer.parseInt(jTableLiability.getValueAt(row, 0).toString());
        day= jTableLiability.getValueAt(row, 1).toString().substring(8, 10);          txtLiabilityday.setSelectedItem(day);
        month=jTableLiability.getValueAt(row, 1).toString().substring(5, 7);          txtLiabilityMonth.setSelectedItem(month);
        year=jTableLiability.getValueAt(row, 1).toString().substring(0, 4);           txtLiabilityYear.setSelectedItem(year);
        description=jTableLiability.getValueAt(row,2).toString();                     txtLiabilityDescription.setText(description);
        paymentMethod= jTableLiability.getValueAt(row, 3).toString();                 txtLiabilityMethod.setSelectedItem(paymentMethod);
        amount= Double.parseDouble(jTableLiability.getValueAt(row, 4).toString());    txtLiabilityAmount.setText(Double.toString(amount));
        liabilityStatus= jTableLiability.getValueAt(row, 5).toString();               txtLiabilityDebitCredit.setSelectedItem(liabilityStatus);
    }//GEN-LAST:event_jTableLiabilityMouseClicked

    private void jButtonGenBnkStatementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenBnkStatementActionPerformed
            try{
                Report_Generation r = new Report_Generation();
                String reportPath = "D:\\SLIIT PROFILE\\Glen Hotel\\Glen_Hotel_Management_System\\src\\glen\\finance\\Bank_Statement.jrxml";
                Map para = new HashMap();
                SimpleDateFormat date=new SimpleDateFormat("yyy-MM-dd");
                String dateFrom=date.format(jDateChooserGenBnkStatementfrom.getDate());
                String dateTo=date.format(jDateChooserGenBnkStatementto.getDate());
                para.put("firstdate", dateFrom);
                para.put("seconddate", dateTo);
                r.view(para,reportPath);
            }catch(Exception e){
                JOptionPane.showMessageDialog(reportsPanel, "Please Select Date to Generate Report!","Empty Date Fields!",0);
            }
        data.adduserlogs(USER, "Report", "Bank Statement", 0);

    }//GEN-LAST:event_jButtonGenBnkStatementActionPerformed

    private void jButtonGenCashflowStatementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenCashflowStatementActionPerformed
        try{
            department=jComboBoxSelectDeptCashflowStatement.getSelectedItem().toString();
            if(department.equals("All")){
                Report_Generation r = new Report_Generation();
                String reportPath = "D:\\SLIIT PROFILE\\Glen Hotel\\Glen_Hotel_Management_System\\src\\glen\\finance\\Cashflow_Statement.jrxml";
                Map para = new HashMap();
                SimpleDateFormat date=new SimpleDateFormat("yyy-MM-dd");
                String dateFrom=date.format(jDateChooserGenCashflowStatementfrom.getDate());
                String dateTo=date.format(jDateChooserGenCashflowStatementto.getDate());
                para.put("firstdate", dateFrom);
                para.put("seconddate", dateTo);
                r.view(para,reportPath);
            }
            else if(department.equals("Select Department"))
                JOptionPane.showMessageDialog(reportsPanel, "Please Select Department to Generate Report!","Empty Date Fields!",0);
            else{
                Report_Generation r = new Report_Generation();
                String reportPath = "D:\\SLIIT PROFILE\\Glen Hotel\\Glen_Hotel_Management_System\\src\\glen\\finance\\Cashflow_Statement_Department.jrxml";
                Map para = new HashMap();
                SimpleDateFormat date=new SimpleDateFormat("yyy-MM-dd");
                String dateFrom=date.format(jDateChooserGenCashflowStatementfrom.getDate());
                String dateTo=date.format(jDateChooserGenCashflowStatementto.getDate());
                para.put("firstdate", dateFrom);
                para.put("seconddate", dateTo);
                para.put("department", department);
                r.view(para,reportPath);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(reportsPanel, "Please Select Date to Generate Report!","Empty Date Fields!",0);
        }
        data.adduserlogs(USER, "Report", "Cashflow Report", 0);
    }//GEN-LAST:event_jButtonGenCashflowStatementActionPerformed

    private void jButtonSearchBudgetRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchBudgetRequestActionPerformed
        financeSearch("budget");
    }//GEN-LAST:event_jButtonSearchBudgetRequestActionPerformed

    private void jButtonCheckBudgetRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckBudgetRequestActionPerformed
        try{
            budgetRequestId= Integer.parseInt(txtBudgetRequestId.getText());
            amount=Double.parseDouble(txtBudgetRequestAmount.getText());
            try{
                String sql="select * from fin_budget_request where budget_request_id="+budgetRequestId;
                pstatement=conn.prepareStatement(sql);
                Results=pstatement.executeQuery();
                if(Results.next())
                budgetApproval=Results.getString(6);
                if(budgetApproval.equals("Accepted")){
                    JOptionPane.showMessageDialog(togglePanels,"You cannot Reject after Accept! ","Action Prohibited!",0);
                }
                else if(budgetApproval.equals("Pending")){
                    int r=JOptionPane.showConfirmDialog(togglePanels,"Are you sure you want to Reject? ","Reject Request!",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
                    if(r== JOptionPane.OK_OPTION){
                        sql="update fin_budget_request set budget_request_status='Rejected' where budget_request_id="+budgetRequestId;
                        pstatement=conn.prepareStatement(sql);
                        pstatement.execute();
                        data.adduserlogs(USER, "Budget Request", "Reject",budgetRequestId);
                    }
                }
                else
                JOptionPane.showMessageDialog(togglePanels,"This Entry Already Rejected! ","Invalid Action!",0);
            }
            catch(Exception e){
                JOptionPane.showConfirmDialog(togglePanels,e);
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(togglePanels,"Select Record to accept or reject!","Invalid Selection",0);
        }
        financebudgettableload();
    }//GEN-LAST:event_jButtonCheckBudgetRequestActionPerformed

    private void jButtonResetBudgetRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetBudgetRequestActionPerformed
        txtBudgetRequestId.setText("");
        txtBudgetRequestAmount.setText("");
        txtBudgetRequestDepartment.setText("Finance");
        txtBudgetRequestday.setSelectedItem("Day");
        txtBudgetRequestMonth.setSelectedItem("Month");
        txtBudgetRequestYear.setSelectedItem("Year");
        txtBudgetRequestApproval.setText("");
    }//GEN-LAST:event_jButtonResetBudgetRequestActionPerformed

    private void jButtonAcceptBudgetRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcceptBudgetRequestActionPerformed
        try{
            budgetRequestId= Integer.parseInt(txtBudgetRequestId.getText());
            amount=Double.parseDouble(txtBudgetRequestAmount.getText());
            budgetApproval=txtBudgetRequestApproval.getText();
            if(!budgetApproval.equals("Accepted")){
                int r=JOptionPane.showConfirmDialog(togglePanels,"Are you sure you want to confirm? ","Confirm Request!",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if(r== JOptionPane.OK_OPTION){
                    data.acceptRequesst(budgetRequestId, budgetId, amount);
                    data.adduserlogs(USER, "Budget Request", "Accept",budgetRequestId);
                }
            }
            else
            JOptionPane.showMessageDialog(togglePanels,"Selected Entry Already Accepted!","Invalid Selection",0);
        }catch(Exception e){
            JOptionPane.showMessageDialog(togglePanels,"Select Record to accept or reject!","Invalid Selection",0);
        }
        financebudgettableload();
    }//GEN-LAST:event_jButtonAcceptBudgetRequestActionPerformed

    private void jTableBudgetRequestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBudgetRequestMouseClicked
        int row= jTableBudgetRequest.getSelectedRow();
        budgetId=Integer.parseInt(jTableBudgetRequest.getValueAt(row, 1).toString());   txtBudgetRequestId1.setText(Integer.toString(budgetId));
        requestId=Integer.parseInt(jTableBudgetRequest.getValueAt(row, 0).toString());  txtBudgetRequestId.setText(Integer.toString(requestId));
        department=jTableBudgetRequest.getValueAt(row, 3).toString();                   txtBudgetRequestDepartment.setText(department);
        amount=Double.parseDouble(jTableBudgetRequest.getValueAt(row, 4).toString());   txtBudgetRequestAmount.setText(Double.toString(amount));
        day= jTableBudgetRequest.getValueAt(row, 2).toString().substring(8, 10);        txtBudgetRequestday.setSelectedItem(day);
        month=jTableBudgetRequest.getValueAt(row, 2).toString().substring(5, 7);        txtBudgetRequestMonth.setSelectedItem(month);
        year=jTableBudgetRequest.getValueAt(row, 2).toString().substring(0, 4);         txtBudgetRequestYear.setSelectedItem(year);
        budgetApproval=jTableBudgetRequest.getValueAt(row, 5).toString();               txtBudgetRequestApproval.setText(budgetApproval);
    }//GEN-LAST:event_jTableBudgetRequestMouseClicked

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
    javax.swing.JPanel AssestsPanel;
    javax.swing.JPanel AssestsPanel1;
    javax.swing.JPanel BudgetRequestPanel;
    javax.swing.JPanel CashflowPanel;
    javax.swing.JPanel CashflowPanel1;
    javax.swing.JPanel ExpenditurePanel;
    javax.swing.JPanel ExpenditurePanel1;
    javax.swing.JPanel IncomePanel;
    javax.swing.JPanel IncomePanel1;
    javax.swing.JPanel LiabilityPanel;
    javax.swing.JPanel LiabilityPanel1;
    javax.swing.JLabel Time;
    javax.swing.JPanel UserlogPanel;
    javax.swing.JPanel assestsPanel;
    javax.swing.JPanel budgetPanel;
    javax.swing.JButton buttonAssests;
    javax.swing.JButton buttonBudgets;
    javax.swing.JButton buttonCashflow;
    javax.swing.JButton buttonExpenditure;
    javax.swing.JButton buttonIncome;
    javax.swing.JButton buttonLiabilities;
    javax.swing.JButton buttonReports;
    javax.swing.JButton buttonfinanceUserlogs;
    javax.swing.JPanel cashflowPanel;
    javax.swing.JPanel expenditurePanel;
    javax.swing.JPanel financeManagerPanel;
    javax.swing.JPanel financeUserlogsPanel;
    javax.swing.JPanel headerPanel;
    javax.swing.JPanel incomePanel;
    javax.swing.JButton jButtonAcceptAllAssests;
    javax.swing.JButton jButtonAcceptAllExpenditure;
    javax.swing.JButton jButtonAcceptAllIncome;
    javax.swing.JButton jButtonAcceptAllinvestment1;
    javax.swing.JButton jButtonAcceptBudgetRequest;
    javax.swing.JButton jButtonAddAssests;
    javax.swing.JButton jButtonAddCashflow;
    javax.swing.JButton jButtonAddLiability;
    javax.swing.JButton jButtonCheckBudgetRequest;
    javax.swing.JButton jButtonDemoAssests;
    javax.swing.JButton jButtonDemoCashflow;
    javax.swing.JButton jButtonDemoExpense;
    javax.swing.JButton jButtonDemoIncome;
    javax.swing.JButton jButtonDemoLiability;
    javax.swing.JButton jButtonExpense;
    javax.swing.JButton jButtonFindExpense1;
    javax.swing.JButton jButtonFindIncome1;
    javax.swing.JButton jButtonGenBnkStatement;
    javax.swing.JButton jButtonGenCashflowStatement;
    javax.swing.JButton jButtonIncome;
    javax.swing.JButton jButtonResetAssests;
    javax.swing.JButton jButtonResetAssests1;
    javax.swing.JButton jButtonResetBudgetRequest;
    javax.swing.JButton jButtonResetCashflow;
    javax.swing.JButton jButtonResetCashflow1;
    javax.swing.JButton jButtonResetExpense;
    javax.swing.JButton jButtonResetExpense1;
    javax.swing.JButton jButtonResetIncome;
    javax.swing.JButton jButtonResetIncome1;
    javax.swing.JButton jButtonResetLiability;
    javax.swing.JButton jButtonResetLiability1;
    javax.swing.JButton jButtonResetUserlog;
    javax.swing.JButton jButtonSearchAssests1;
    javax.swing.JButton jButtonSearchBudgetRequest;
    javax.swing.JButton jButtonSearchLiability1;
    javax.swing.JButton jButtonSearchUserlog;
    javax.swing.JButton jButtonUpdateAssests1;
    javax.swing.JButton jButtonUpdateCashflow1;
    javax.swing.JButton jButtonUpdateExpense1;
    javax.swing.JButton jButtonUpdateIncome1;
    javax.swing.JButton jButtonUpdateLiability1;
    javax.swing.JButton jButtonViewCashflow1;
    javax.swing.JButton jButtonlogout;
    javax.swing.JComboBox<String> jComboBoxSelectDeptCashflowStatement;
    com.toedter.calendar.JDateChooser jDateChooserGenBnkStatementfrom;
    com.toedter.calendar.JDateChooser jDateChooserGenBnkStatementto;
    com.toedter.calendar.JDateChooser jDateChooserGenCashflowStatementfrom;
    com.toedter.calendar.JDateChooser jDateChooserGenCashflowStatementto;
    javax.swing.JDesktopPane jDesktopPaneFinance;
    javax.swing.JLabel jLabel14;
    javax.swing.JLabel jLabelheaderimage;
    javax.swing.JPanel jPanelReportBnkStatement;
    javax.swing.JPanel jPanelReportCashflowStatement;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPaneAssests;
    javax.swing.JScrollPane jScrollPaneAssests1;
    javax.swing.JScrollPane jScrollPaneBudgetRequest;
    javax.swing.JScrollPane jScrollPaneCashflow;
    javax.swing.JScrollPane jScrollPaneCashflow1;
    javax.swing.JScrollPane jScrollPaneExpense;
    javax.swing.JScrollPane jScrollPaneExpense1;
    javax.swing.JScrollPane jScrollPaneIncome;
    javax.swing.JScrollPane jScrollPaneIncome1;
    javax.swing.JScrollPane jScrollPaneLiability;
    javax.swing.JScrollPane jScrollPaneLiability1;
    javax.swing.JScrollPane jScrollPaneUserlog;
    javax.swing.JTabbedPane jTabbedPaneAssests;
    javax.swing.JTabbedPane jTabbedPaneBudget;
    javax.swing.JTabbedPane jTabbedPaneCashflow;
    javax.swing.JTabbedPane jTabbedPaneExpenditure;
    javax.swing.JTabbedPane jTabbedPaneIncome;
    javax.swing.JTabbedPane jTabbedPaneLiabilities;
    javax.swing.JTabbedPane jTabbedPaneUserlog;
    javax.swing.JTable jTable1;
    javax.swing.JTable jTableAddExpense;
    javax.swing.JTable jTableAddExpense1;
    javax.swing.JTable jTableAssests;
    javax.swing.JTable jTableAssests1;
    javax.swing.JTable jTableBudgetRequest;
    javax.swing.JTable jTableBudgetRequest7;
    javax.swing.JTable jTableCashflow;
    javax.swing.JTable jTableCashflow1;
    javax.swing.JTable jTableIncome;
    javax.swing.JTable jTableIncome1;
    javax.swing.JTable jTableLiability;
    javax.swing.JTable jTableLiability1;
    javax.swing.JLabel lableAssestsDepartment;
    javax.swing.JLabel lableAssestsDepartment1;
    javax.swing.JLabel lableAssestsId1;
    javax.swing.JLabel lableAssestsMethod;
    javax.swing.JLabel lableAssestsMethod1;
    javax.swing.JLabel lableAssestsPaymentType;
    javax.swing.JLabel lableAssestsPaymentType1;
    javax.swing.JLabel lableAssestsamount;
    javax.swing.JLabel lableAssestsamount1;
    javax.swing.JLabel lableAssestsdate;
    javax.swing.JLabel lableAssestsdate1;
    javax.swing.JLabel lableAssestsdebitcredit;
    javax.swing.JLabel lableAssestsdebitcredit1;
    javax.swing.JLabel lableAssestsdescription;
    javax.swing.JLabel lableAssestsdescription1;
    javax.swing.JLabel lableBudgetRequestDepartment;
    javax.swing.JLabel lableBudgetRequestDepartment1;
    javax.swing.JLabel lableBudgetRequestId;
    javax.swing.JLabel lableBudgetRequestId1;
    javax.swing.JLabel lableBudgetRequestamount;
    javax.swing.JLabel lableBudgetRequestdate;
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
    javax.swing.JLabel lableIncomeMethod1;
    javax.swing.JLabel lableIncomePaymentType;
    javax.swing.JLabel lableIncomePaymentType1;
    javax.swing.JLabel lableLiabilityId1;
    javax.swing.JLabel lableLiabilityMethod;
    javax.swing.JLabel lableLiabilityMethod1;
    javax.swing.JLabel lableLiabilityPaymentType;
    javax.swing.JLabel lableLiabilityPaymentType1;
    javax.swing.JLabel lableLiabilityamount;
    javax.swing.JLabel lableLiabilityamount1;
    javax.swing.JLabel lableLiabilitydate;
    javax.swing.JLabel lableLiabilitydate1;
    javax.swing.JLabel lableLiabilitydebitcredit;
    javax.swing.JLabel lableLiabilitydebitcredit1;
    javax.swing.JLabel lableLiabilitydescription;
    javax.swing.JLabel lableLiabilitydescription1;
    javax.swing.JLabel lableUserlogActionId;
    javax.swing.JLabel lableUserlogActionType;
    javax.swing.JLabel lableUserlogDate;
    javax.swing.JLabel lableUserlogId;
    javax.swing.JLabel lableincomeamount;
    javax.swing.JLabel lableincomeamount1;
    javax.swing.JLabel lableincomedate;
    javax.swing.JLabel lableincomedate1;
    javax.swing.JLabel lableincomedebitcredit;
    javax.swing.JLabel lableincomedebitcredit1;
    javax.swing.JLabel lableincomedepartment;
    javax.swing.JLabel lableincomedepartment1;
    javax.swing.JLabel lableincomedescription;
    javax.swing.JLabel lableincomedescription1;
    javax.swing.JLabel lableincomeid1;
    javax.swing.JPanel liabilitiesPanel;
    javax.swing.JPanel mainButtonPanel;
    javax.swing.JPanel reportsPanel;
    javax.swing.JPanel togglePanels;
    javax.swing.JTextField txtAssestsAmount;
    javax.swing.JTextField txtAssestsAmount1;
    javax.swing.JComboBox<String> txtAssestsDebitCredit;
    javax.swing.JComboBox<String> txtAssestsDebitCredit1;
    javax.swing.JComboBox<String> txtAssestsDepartment;
    javax.swing.JComboBox<String> txtAssestsDepartment1;
    javax.swing.JTextField txtAssestsDescription;
    javax.swing.JTextField txtAssestsDescription1;
    javax.swing.JTextField txtAssestsId1;
    javax.swing.JComboBox<String> txtAssestsMethod;
    javax.swing.JComboBox<String> txtAssestsMethod1;
    javax.swing.JComboBox<String> txtAssestsMonth;
    javax.swing.JComboBox<String> txtAssestsMonth1;
    javax.swing.JComboBox<String> txtAssestsPaymentType;
    javax.swing.JComboBox<String> txtAssestsPaymentType1;
    javax.swing.JComboBox<String> txtAssestsYear;
    javax.swing.JComboBox<String> txtAssestsYear1;
    javax.swing.JComboBox<String> txtAssestsday;
    javax.swing.JComboBox<String> txtAssestsday1;
    javax.swing.JTextField txtBudgetRequestAmount;
    javax.swing.JTextField txtBudgetRequestApproval;
    javax.swing.JTextField txtBudgetRequestDepartment;
    javax.swing.JTextField txtBudgetRequestId;
    javax.swing.JTextField txtBudgetRequestId1;
    javax.swing.JComboBox<String> txtBudgetRequestMonth;
    javax.swing.JComboBox<String> txtBudgetRequestYear;
    javax.swing.JComboBox<String> txtBudgetRequestday;
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
    javax.swing.JComboBox<String> txtIncomeDebitCredit1;
    javax.swing.JComboBox<String> txtIncomeDepartment;
    javax.swing.JComboBox<String> txtIncomeDepartment1;
    javax.swing.JTextField txtIncomeDescription;
    javax.swing.JTextField txtIncomeDescription1;
    javax.swing.JTextField txtIncomeId1;
    javax.swing.JComboBox<String> txtIncomeMethod;
    javax.swing.JComboBox<String> txtIncomeMethod1;
    javax.swing.JComboBox<String> txtIncomeMonth;
    javax.swing.JComboBox<String> txtIncomeMonth1;
    javax.swing.JComboBox<String> txtIncomePaymentType;
    javax.swing.JComboBox<String> txtIncomePaymentType1;
    javax.swing.JComboBox<String> txtIncomeYear;
    javax.swing.JComboBox<String> txtIncomeYear1;
    javax.swing.JComboBox<String> txtIncomeday;
    javax.swing.JComboBox<String> txtIncomeday1;
    javax.swing.JTextField txtLiabilityAmount;
    javax.swing.JTextField txtLiabilityAmount1;
    javax.swing.JComboBox<String> txtLiabilityDebitCredit;
    javax.swing.JComboBox<String> txtLiabilityDebitCredit1;
    javax.swing.JTextField txtLiabilityDescription;
    javax.swing.JTextField txtLiabilityDescription1;
    javax.swing.JTextField txtLiabilityId1;
    javax.swing.JComboBox<String> txtLiabilityMethod;
    javax.swing.JComboBox<String> txtLiabilityMethod1;
    javax.swing.JComboBox<String> txtLiabilityMonth;
    javax.swing.JComboBox<String> txtLiabilityMonth1;
    javax.swing.JComboBox<String> txtLiabilityPaymentType;
    javax.swing.JComboBox<String> txtLiabilityPaymentType1;
    javax.swing.JComboBox<String> txtLiabilityYear;
    javax.swing.JComboBox<String> txtLiabilityYear1;
    javax.swing.JComboBox<String> txtLiabilityday;
    javax.swing.JComboBox<String> txtLiabilityday1;
    javax.swing.JTextField txtUserlogActionId;
    javax.swing.JComboBox<String> txtUserlogActionType;
    javax.swing.JTextField txtUserlogId;
    javax.swing.JComboBox<String> txtUserlogday;
    javax.swing.JComboBox<String> txtUserlogmonth;
    javax.swing.JTextField txtdIncomeAmount;
    javax.swing.JTextField txtdIncomeAmount1;
    javax.swing.JComboBox<String> txtyear;
    javax.swing.JLabel welcomeuser;
    // End of variables declaration//GEN-END:variables
}