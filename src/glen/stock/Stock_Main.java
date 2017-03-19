package glen.stock;


import glen.*;
import glen.finance.DBAccess;
import java.awt.Button;
import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import javax.swing.table.*;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import java.util.Date;
import java.awt.event.ItemListener;
import javafx.scene.control.ComboBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxUI;
/**
 *
 * @author NSA
 * IT15020086
 */

public class Stock_Main extends javax.swing.JFrame {

    //*******************************************************************VARIABLES*************************************************************
    //Database connecting variables
    Connection conn = DBconnect.ConnectDB();
    PreparedStatement pStatement = null;
    ResultSet resultSet = null;
    String sqlQuery, sqlQuery2;
    DBAccess dbaFinance = new DBAccess();
    //***********************************************************************ORDERS************************************************************
    String orderListOrderIdSearch, orderListItemIdSearch, orderListItemNameSearch, orderListCategorySearch;
    String transferStockOrderId, transferStockItemId, transferStockItemName, transferStockCategory, transferStockDepartment, transferStockOrderQty, transferStockUnits, transferStockOrderDate;     //Transfer Stock
    float stockQty, orderQty;
    static String  USER;
    //***********************************************************************ITEMS*************************************************************
    String itemListItemId, itemListItemName, itemListUnits, itemListDescription, itemListCategory, itemListReorder;
    //*********************************************************************SUPPLIERS***********************************************************
    String supplierListSupplierId, supplierListSupplierName, supplierListSupplierAddress, supplierListSupplierContact, supplierListSupplierEmail, supplierListSupplierDescription;
    //*******************************************************************ITEM CATEGORY*********************************************************
    String itemCategoryId, itemCategoryName;
    //***********************************************************************USERS*************************************************************
    String userListUserId, userListEmployeeId, userListFullName, userListUsername, userListPassword;
    public Stock_Main(String username) {
        //Setting username
        USER = username;
        initComponents();        
        this.setLocationRelativeTo(null); //Setting to display in the center of screen
        //setting all Panels hidden
        actionPanel.setVisible(true);
        stockPanel.setVisible(false);
        ordersPanel.setVisible(false);
        purchasePanel.setVisible(false);
        transactionsPanel.setVisible(false);
        itemsPanel.setVisible(false);
        suppliersPanel.setVisible(false);
        categoriesPanel.setVisible(false);
        accountPanel.setVisible(false);
        userPanel.setVisible(false);
        userlogPanel.setVisible(false);
    }   
    //**********************************************************************FUNCTIONS**********************************************************
    public void retrieveItemList(String availability, JTable table, JPanel panel){//Retrieving data from database to Item List panel
        try{
            sqlQuery = "select i.item_id as 'Item ID', i.item_name as 'Item Name', i.item_units as 'Units', i.item_description as 'Description', ic.item_category_name as 'Category', "
                    + "i.item_reorder_level as 'Re-order Level'  from item i, item_category ic where i.item_category = ic.item_category_id and i.item_availability = '"+availability+"'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            JOptionPane.showMessageDialog(panel, e, "Item list Retrieval Error!", 0);
        }
    }   
    public void retrieveStock(){
        try{
            sqlQuery = "select s.stock_id as 'Stock ID', s.item_id as 'Item ID', i.item_name as 'Item Name', ic.item_category_name as 'Category', s.stock_qty as 'Avail Qty', i.item_units as 'Units',"
                    + " i.item_reorder_level as 'Re-order' from stock s, item i, item_category ic where s.item_id = i.item_id and i.item_category = ic.item_category_id and i.item_availability = 'available'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            stockTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            JOptionPane.showMessageDialog(stockPanel, e, "Query Execution Error!", 0);
        }           
    }   
    public void retrieveSuppliers(String availability, JTable table, JPanel panel){
        //Retrieve data from database to Supplier List panel
        try{
            sqlQuery = "select supplier_id as 'Supplier ID', supplier_name as 'Supplier Name', supplier_contact as 'Contact No.', supplier_address as 'Address',"
                    + " supplier_email as 'Email', supplier_description as 'Description', DATE_FORMAT(supplier_reg_date, '%Y-%m-%d %H:%i') as 'Register Date' from"
                    + " supplier where supplier_availability = '"+availability+"'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
            //Setting column width for supplier name, email, reg date, description, address
            TableColumn column1 = table.getColumnModel().getColumn(1);
            column1.setMinWidth(130);
            TableColumn column2 = table.getColumnModel().getColumn(4);
            column2.setMinWidth(180);
            TableColumn column3 = table.getColumnModel().getColumn(6);
            column3.setMinWidth(110);
            TableColumn column4 = table.getColumnModel().getColumn(5);
            column4.setMinWidth(130);
            TableColumn column5 = table.getColumnModel().getColumn(3);
            column5.setMinWidth(100);
        }catch(Exception e){
            JOptionPane.showMessageDialog(panel, e, "Supplier list Retrieval Error!", 0);
        }
    }    
    public void retrieveItemCategories(JComboBox combobox, String text, JPanel panel){
        combobox.removeAllItems();
        combobox.addItem(text);
        try{
            sqlQuery2 = "select item_category_name as 'Category' from item_category";
            pStatement = conn.prepareStatement(sqlQuery2);
            resultSet = pStatement.executeQuery();
            //retrieving item categories available in database to the Item Category ComboBox
            while(resultSet.next()){               
                combobox.addItem(resultSet.getString("Category"));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(panel, e, "Item Categories Retrieving Error!", 0);
        } 
    }
    public void retrieveUsers(String availability, JTable table, JPanel panel){
        try{
            sqlQuery = "select system_user_id as 'User ID', employee_id as 'Employee ID', full_name as 'Full Name',  username as 'Username', password as 'Password', "
                    + " DATE_FORMAT(last_updated_date, '%Y-%m-%d') as 'Last Updated Date' from system_user where user_availability = '"+availability+"' and department = 'Stock' and access_level = 'User'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            JOptionPane.showMessageDialog(panel, e, "User Data Retrieval Error!", 0);
        }
    }
    public int retrieveLoggedUserId(JPanel panel){
        try{
            sqlQuery = "select system_user_id from system_user where username = '"+USER+"'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            if(resultSet.next()){               
                return Integer.parseInt(resultSet.getString(1));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(panel, e, "Logged User ID Retrieving Error!", 0);
        }
        return -1;
    }
    //**********************************************************************Interfaces*********************************************************    
    //Implementing StockActionPanel Navigation
    
    public void stockActionPanelNavigation(String buttonPanelClick){
        
        switch(buttonPanelClick){
        
            case "STOCK" :  
                            stockPanel.setVisible(true);
                            ordersPanel.setVisible(false);
                            purchasePanel.setVisible(false);
                            transactionsPanel.setVisible(false);
                            itemsPanel.setVisible(false);
                            suppliersPanel.setVisible(false);
                            categoriesPanel.setVisible(false);
                            accountPanel.setVisible(false);
                            userPanel.setVisible(false);
                            userlogPanel.setVisible(false);
                            break;
            
            case "ORDERS" : 
                            stockPanel.setVisible(false);
                            ordersPanel.setVisible(true);
                            purchasePanel.setVisible(false);
                            transactionsPanel.setVisible(false);
                            itemsPanel.setVisible(false);
                            suppliersPanel.setVisible(false);
                            categoriesPanel.setVisible(false);
                            accountPanel.setVisible(false);
                            userPanel.setVisible(false);
                            userlogPanel.setVisible(false);
                            break;

            case "PURCHASE" :  
                            stockPanel.setVisible(false);
                            ordersPanel.setVisible(false);
                            purchasePanel.setVisible(true);
                            transactionsPanel.setVisible(false);
                            itemsPanel.setVisible(false);
                            suppliersPanel.setVisible(false);
                            categoriesPanel.setVisible(false);
                            accountPanel.setVisible(false);
                            userPanel.setVisible(false);
                            userlogPanel.setVisible(false);
                            break;

            case "TRANSACTIONS" :  
                            stockPanel.setVisible(false);
                            ordersPanel.setVisible(false);
                            purchasePanel.setVisible(false);
                            transactionsPanel.setVisible(true);
                            itemsPanel.setVisible(false);
                            suppliersPanel.setVisible(false);
                            categoriesPanel.setVisible(false);
                            accountPanel.setVisible(false);
                            userPanel.setVisible(false);
                            userlogPanel.setVisible(false);
                            break;

            case "ITEMS" :  
                            stockPanel.setVisible(false);
                            ordersPanel.setVisible(false);
                            purchasePanel.setVisible(false);
                            transactionsPanel.setVisible(false);
                            itemsPanel.setVisible(true);
                            suppliersPanel.setVisible(false);
                            categoriesPanel.setVisible(false);
                            accountPanel.setVisible(false);
                            userPanel.setVisible(false);
                            userlogPanel.setVisible(false);
                            break;

            case "SUPPLIERS" :                             
                            stockPanel.setVisible(false);
                            ordersPanel.setVisible(false);
                            purchasePanel.setVisible(false);
                            transactionsPanel.setVisible(false);
                            itemsPanel.setVisible(false);
                            suppliersPanel.setVisible(true);
                            categoriesPanel.setVisible(false);
                            accountPanel.setVisible(false);
                            userPanel.setVisible(false);
                            userlogPanel.setVisible(false);
                            break;

            case "CATEGORIES" :                             
                            stockPanel.setVisible(false);
                            ordersPanel.setVisible(false);
                            purchasePanel.setVisible(false);
                            transactionsPanel.setVisible(false);
                            itemsPanel.setVisible(false);
                            suppliersPanel.setVisible(false);
                            categoriesPanel.setVisible(true);
                            accountPanel.setVisible(false);
                            userPanel.setVisible(false);
                            userlogPanel.setVisible(false);
                            break;

            case "ACCOUNT" :                             
                            stockPanel.setVisible(false);
                            ordersPanel.setVisible(false);
                            purchasePanel.setVisible(false);
                            transactionsPanel.setVisible(false);
                            itemsPanel.setVisible(false);
                            suppliersPanel.setVisible(false);
                            categoriesPanel.setVisible(false);
                            accountPanel.setVisible(true);
                            userPanel.setVisible(false);
                            userlogPanel.setVisible(false);
                            break;

            case "USERS" :  
                            stockPanel.setVisible(false);
                            ordersPanel.setVisible(false);
                            purchasePanel.setVisible(false);
                            transactionsPanel.setVisible(false);
                            itemsPanel.setVisible(false);
                            suppliersPanel.setVisible(false);
                            categoriesPanel.setVisible(false);
                            accountPanel.setVisible(false);
                            userPanel.setVisible(true);
                            userlogPanel.setVisible(false);
                            break;

            case "USERLOG" :                             
                            stockPanel.setVisible(false);
                            ordersPanel.setVisible(false);
                            purchasePanel.setVisible(false);
                            transactionsPanel.setVisible(false);
                            itemsPanel.setVisible(false);
                            suppliersPanel.setVisible(false);
                            categoriesPanel.setVisible(false);
                            accountPanel.setVisible(false);
                            userPanel.setVisible(false);
                            userlogPanel.setVisible(true);
                            break;                                               
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

        stockMainDesktopPane = new javax.swing.JDesktopPane();
        stockMainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        buttonPanel = new javax.swing.JPanel();
        stockButton = new javax.swing.JButton();
        ordersButton = new javax.swing.JButton();
        purchaseButton = new javax.swing.JButton();
        transactionsButton = new javax.swing.JButton();
        itemsButton = new javax.swing.JButton();
        suppliersButton = new javax.swing.JButton();
        categoriesButton = new javax.swing.JButton();
        accountButton = new javax.swing.JButton();
        usersButton = new javax.swing.JButton();
        userlogButton = new javax.swing.JButton();
        actionPanel = new javax.swing.JPanel();
        stockPanel = new javax.swing.JPanel();
        stockScrollPane = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        stockIdTextField = new javax.swing.JTextField();
        stockItemIdTextField = new javax.swing.JTextField();
        stockItemNameTextField = new javax.swing.JTextField();
        stockSearchButton = new javax.swing.JButton();
        stockRefreshButton = new javax.swing.JButton();
        stockItemCategoryComboBox = new javax.swing.JComboBox<>();
        ordersPanel = new javax.swing.JPanel();
        ordersTabbedPane = new javax.swing.JTabbedPane();
        orderListPanel = new javax.swing.JPanel();
        ordersScrollPane = new javax.swing.JScrollPane();
        ordersTable = new javax.swing.JTable();
        orderItemIdTextField = new javax.swing.JTextField();
        orderItemNameTextField = new javax.swing.JTextField();
        orderSearchButton = new javax.swing.JButton();
        orderRefreshButton = new javax.swing.JButton();
        orderIdTextField = new javax.swing.JTextField();
        orderItemDepartmentTextField = new javax.swing.JTextField();
        orderNextButton = new javax.swing.JButton();
        orderDemoButton = new javax.swing.JButton();
        orderItemCategoryComboBox = new javax.swing.JComboBox<>();
        transferStockPanel = new javax.swing.JPanel();
        transferOrderIdLabel = new javax.swing.JLabel();
        transferOrderIdTextField = new javax.swing.JTextField();
        transferItemIdLabel = new javax.swing.JLabel();
        transferItemIdTextField = new javax.swing.JTextField();
        transferItemNameLabel = new javax.swing.JLabel();
        transferItemNameTextField = new javax.swing.JTextField();
        transferItemCategoryLabel = new javax.swing.JLabel();
        transferItemCategoryTextField = new javax.swing.JTextField();
        transferDepartmentLabel = new javax.swing.JLabel();
        transferDepartmentTextField = new javax.swing.JTextField();
        transferOrderQtyLabel = new javax.swing.JLabel();
        transferOrderQtyTextField = new javax.swing.JTextField();
        transferUnitsLabel = new javax.swing.JLabel();
        transferOrderDateLabel = new javax.swing.JLabel();
        transferOrderDateTextField = new javax.swing.JTextField();
        transferAvailQtyLabel = new javax.swing.JLabel();
        transferAvailQtyTextField = new javax.swing.JTextField();
        transferOrderButton = new javax.swing.JButton();
        transferOrderListButton = new javax.swing.JButton();
        transferUnitsTextField = new javax.swing.JTextField();
        purchasePanel = new javax.swing.JPanel();
        addStockPurchaseItemCategoryLabel = new javax.swing.JLabel();
        addStockPurchaseItemCategoryComboBox = new javax.swing.JComboBox<>();
        addStockPurchaseItemNameLabel = new javax.swing.JLabel();
        addStockPurchaseItemNameComboBox = new javax.swing.JComboBox<>();
        addStockPurchaseItemIdLabel = new javax.swing.JLabel();
        addStockPurchaseItemIdComboBox = new javax.swing.JComboBox<>();
        addStockPurchaseSupplierNameLabel = new javax.swing.JLabel();
        addStockPurchaseSupplierNameComboBox = new javax.swing.JComboBox<>();
        addStockPurchaseSupplierIdComboBox = new javax.swing.JComboBox<>();
        addStockPurchaseSupplierIdLabel = new javax.swing.JLabel();
        addStockPurchaseItemUnitPriceLabel = new javax.swing.JLabel();
        addStockPurchaseItemUnitsLabel = new javax.swing.JLabel();
        addStockPurchaseItemUnitPriceTextField = new javax.swing.JTextField();
        addStockPurchaseAvailQtyLabel = new javax.swing.JLabel();
        addStockPurchaseAvailQtyTextField = new javax.swing.JTextField();
        addStockPurchaseItemQtyLabel = new javax.swing.JLabel();
        addStockPurchaseItemQtyTextField = new javax.swing.JTextField();
        addStockPurchaseTotalAmountLabel = new javax.swing.JLabel();
        addStockPurchaseTotalAmountTextField = new javax.swing.JTextField();
        stockPurchaseConfirmPurchaseButton = new javax.swing.JButton();
        addStockPurchaseItemUnitsTextField = new javax.swing.JTextField();
        stockPurchaseClearButton = new javax.swing.JButton();
        stockPurchaseCalculateTotalButton = new javax.swing.JButton();
        transactionsPanel = new javax.swing.JPanel();
        stockTransactionsTabbedPane = new javax.swing.JTabbedPane();
        stockPurchasePanel = new javax.swing.JPanel();
        stockPurchaseScrollPane = new javax.swing.JScrollPane();
        stockPurchaseTable = new javax.swing.JTable();
        stockPurchaseIdTextField = new javax.swing.JTextField();
        stockPurchaseItemIdTextField = new javax.swing.JTextField();
        stockPurchaseItemNameTextField = new javax.swing.JTextField();
        stockPurchaseSupplierIdTextField = new javax.swing.JTextField();
        stockPurchaseSearchButton = new javax.swing.JButton();
        stockPurchaseRefreshButton = new javax.swing.JButton();
        stockPurchaseSupplierNameTextField = new javax.swing.JTextField();
        stockTransferPanel = new javax.swing.JPanel();
        stockTransferScrollPane = new javax.swing.JScrollPane();
        stockTransferTable = new javax.swing.JTable();
        stockTransferIdTextField = new javax.swing.JTextField();
        stockTransferItemIdTextField = new javax.swing.JTextField();
        stockTransferItemNameTextField = new javax.swing.JTextField();
        stockTransferDepartmentTextField = new javax.swing.JTextField();
        stockTransferSearchButton = new javax.swing.JButton();
        stockTransferRefreshButton = new javax.swing.JButton();
        itemsPanel = new javax.swing.JPanel();
        itemTabbedPane = new javax.swing.JTabbedPane();
        itemListPanel = new javax.swing.JPanel();
        itemListScrollPane = new javax.swing.JScrollPane();
        itemListTable = new javax.swing.JTable();
        itemListItemIdTextField = new javax.swing.JTextField();
        itemListItemNameTextField = new javax.swing.JTextField();
        itemListSearchButton = new javax.swing.JButton();
        itemListRefreshButton = new javax.swing.JButton();
        itemListAddItemButton = new javax.swing.JButton();
        itemListRemovedItemsButton = new javax.swing.JButton();
        itemListEditItemButton = new javax.swing.JButton();
        itemListItemCategoryComboBox = new javax.swing.JComboBox<>();
        addItemPanel = new javax.swing.JPanel();
        addItemNameLabel = new javax.swing.JLabel();
        addItemNameTextField = new javax.swing.JTextField();
        addItemUnitsLabel = new javax.swing.JLabel();
        addItemDescriptionLabel = new javax.swing.JLabel();
        addItemCategoryLabel = new javax.swing.JLabel();
        addItemReorderLevelLabel = new javax.swing.JLabel();
        addItemReorderLevelTextField = new javax.swing.JTextField();
        addItemUnitsComboBox = new javax.swing.JComboBox<>();
        addItemDescriptionScrollPane = new javax.swing.JScrollPane();
        addItemDescriptionTextArea = new javax.swing.JTextArea();
        addItemCategoryComboBox = new javax.swing.JComboBox<>();
        addItemItemListButton = new javax.swing.JButton();
        addItemAddItemButton = new javax.swing.JButton();
        addItemClearButton = new javax.swing.JButton();
        addItemDemoButton = new javax.swing.JButton();
        editItemPanel = new javax.swing.JPanel();
        editItemNameLabel = new javax.swing.JLabel();
        editItemNameTextField = new javax.swing.JTextField();
        editItemUnitsLabel = new javax.swing.JLabel();
        editItemDescriptionLabel = new javax.swing.JLabel();
        editItemCategoryLabel = new javax.swing.JLabel();
        editItemReorderLevelLabel = new javax.swing.JLabel();
        editItemReorderLevelTextField = new javax.swing.JTextField();
        editItemDescriptionScrollPane = new javax.swing.JScrollPane();
        editItemDescriptionTextArea = new javax.swing.JTextArea();
        editItemItemListButton = new javax.swing.JButton();
        editItemEditItemButton = new javax.swing.JButton();
        editItemRemoveItemButton = new javax.swing.JButton();
        editItemIdLabel = new javax.swing.JLabel();
        editItemIdTextField = new javax.swing.JTextField();
        editItemCategoryTextField = new javax.swing.JTextField();
        editItemUnitsTextField = new javax.swing.JTextField();
        removedItemsPanel = new javax.swing.JPanel();
        removedItemsScrollPane = new javax.swing.JScrollPane();
        removedItemsTable = new javax.swing.JTable();
        removedItemsItemListButton = new javax.swing.JButton();
        removedItemListItemIdTextField = new javax.swing.JTextField();
        removedItemListItemNameTextField = new javax.swing.JTextField();
        removedItemListItemCategoryComboBox = new javax.swing.JComboBox<>();
        removedItemListSearchButton = new javax.swing.JButton();
        removedItemListRefreshButton = new javax.swing.JButton();
        suppliersPanel = new javax.swing.JPanel();
        supplierTabbedPane = new javax.swing.JTabbedPane();
        supplierListPanel = new javax.swing.JPanel();
        supplierListScrollPane = new javax.swing.JScrollPane();
        supplierListTable = new javax.swing.JTable();
        supplierListSupplierIdTextField = new javax.swing.JTextField();
        supplierListSupplierNameTextField = new javax.swing.JTextField();
        supplierListSupplierContactNumberTextField = new javax.swing.JTextField();
        supplierListSearchButton = new javax.swing.JButton();
        supplierListRefreshButton = new javax.swing.JButton();
        supplierListAddSupplierButton = new javax.swing.JButton();
        supplierListRemovedSuppliersButton = new javax.swing.JButton();
        supplierListEditSupplierButton = new javax.swing.JButton();
        addSupplierPanel = new javax.swing.JPanel();
        addSupplierNameLabel = new javax.swing.JLabel();
        addSupplierNameTextField = new javax.swing.JTextField();
        addSupplierAddressLabel = new javax.swing.JLabel();
        addSupplierTelephoneLabel = new javax.swing.JLabel();
        addSupplierAddressScrollPane = new javax.swing.JScrollPane();
        addSupplierAddressTextArea = new javax.swing.JTextArea();
        addSupplierSupplierListButton = new javax.swing.JButton();
        addSupplierAddSupplierButton = new javax.swing.JButton();
        addSupplierTelephoneTextField = new javax.swing.JTextField();
        addSupplierEmailLabel = new javax.swing.JLabel();
        addSupplierEmailTextField = new javax.swing.JTextField();
        addSupplierDescriptionLabel = new javax.swing.JLabel();
        addSupplierDescriptionScrollPane = new javax.swing.JScrollPane();
        addSupplierDescriptionTextArea = new javax.swing.JTextArea();
        addSupplierClearButton = new javax.swing.JButton();
        addSupplierDemoButton = new javax.swing.JButton();
        editSupplierPanel = new javax.swing.JPanel();
        editSupplierSupplierListButton = new javax.swing.JButton();
        editSupplierEditSupplierButton = new javax.swing.JButton();
        editSupplierRemoveSupplierButton = new javax.swing.JButton();
        editSupplierNameLabel = new javax.swing.JLabel();
        editSupplierNameTextField = new javax.swing.JTextField();
        editSupplierAddressLabel = new javax.swing.JLabel();
        editSupplierAddressScrollPane = new javax.swing.JScrollPane();
        editSupplierAddressTextArea = new javax.swing.JTextArea();
        editSupplierTelephoneTextField = new javax.swing.JTextField();
        editSupplierTelephoneLabel = new javax.swing.JLabel();
        editSupplierEmailTextField = new javax.swing.JTextField();
        editSupplierEmailLabel = new javax.swing.JLabel();
        editSupplierDescriptionLabel = new javax.swing.JLabel();
        editSupplierDescriptionScrollPane = new javax.swing.JScrollPane();
        editSupplierDescriptionTextArea = new javax.swing.JTextArea();
        editSupplierIdLabel = new javax.swing.JLabel();
        editSupplierIdTextField = new javax.swing.JTextField();
        removedSuppliersPanel = new javax.swing.JPanel();
        removedSuppliersScrollPane = new javax.swing.JScrollPane();
        removedSuppliersTable = new javax.swing.JTable();
        removedSuppliersSupplierIdTextField = new javax.swing.JTextField();
        removedSuppliersSupplierNameTextField = new javax.swing.JTextField();
        removedSuppliersSupplierContactNumberTextField = new javax.swing.JTextField();
        removedSuppliersSearchButton = new javax.swing.JButton();
        removedSuppliersRefreshButton = new javax.swing.JButton();
        removedSuppliersSupplierListButton = new javax.swing.JButton();
        removedSuppliersAddSupplierBackButton = new javax.swing.JButton();
        categoriesPanel = new javax.swing.JPanel();
        stockCategoriesScrollPane = new javax.swing.JScrollPane();
        stockCategoriesTable = new javax.swing.JTable();
        categoriesAddCategoryButton = new javax.swing.JButton();
        categoriesRemoveCategoryButton = new javax.swing.JButton();
        categoriesEditCategoryButton = new javax.swing.JButton();
        categoriesCategoryIdLabel = new javax.swing.JLabel();
        categoriesCategoryIdTextField = new javax.swing.JTextField();
        categoriesCategoryNameLabel = new javax.swing.JLabel();
        categoriesCategoryNameTextField = new javax.swing.JTextField();
        accountPanel = new javax.swing.JPanel();
        accountDepositLabel = new javax.swing.JLabel();
        accountBalanceLabel = new javax.swing.JLabel();
        accountDepositTextField = new javax.swing.JTextField();
        accountBalanceTextField = new javax.swing.JTextField();
        accountRequestCreditButton = new javax.swing.JButton();
        accountRequestAmountLabel = new javax.swing.JLabel();
        accountRequestAmountTextField = new javax.swing.JTextField();
        userPanel = new javax.swing.JPanel();
        userTabbedPane = new javax.swing.JTabbedPane();
        userListPanel = new javax.swing.JPanel();
        userListScrollPane = new javax.swing.JScrollPane();
        userListTable = new javax.swing.JTable();
        userListUserIdTextField = new javax.swing.JTextField();
        userListUserNameTextField = new javax.swing.JTextField();
        userListSearchButton = new javax.swing.JButton();
        userListRefreshButton = new javax.swing.JButton();
        userListAddUserButton = new javax.swing.JButton();
        userListEditUserButton = new javax.swing.JButton();
        userListRemovedUsersButton = new javax.swing.JButton();
        addUserPanel = new javax.swing.JPanel();
        addUserUsernameLabel = new javax.swing.JLabel();
        addUserUsernameTextField = new javax.swing.JTextField();
        addUserFullNameLabel = new javax.swing.JLabel();
        addUserFullNameScrollPane = new javax.swing.JScrollPane();
        addUserFullNameTextArea = new javax.swing.JTextArea();
        addUserUserListButton = new javax.swing.JButton();
        addUserAddUserButton = new javax.swing.JButton();
        addUserPasswordLabel = new javax.swing.JLabel();
        addUserPasswordTextField = new javax.swing.JTextField();
        addUserEmployeeIdLabel = new javax.swing.JLabel();
        addUserEmployeeIdComboBox = new javax.swing.JComboBox<>();
        addUserClearButton = new javax.swing.JButton();
        editUserPanel = new javax.swing.JPanel();
        editUserUserListButton = new javax.swing.JButton();
        editUserEditUserButton = new javax.swing.JButton();
        editUserRemoveUserButton = new javax.swing.JButton();
        editUserIdLabel = new javax.swing.JLabel();
        editUserIdTextField = new javax.swing.JTextField();
        editUserFullNameLabel = new javax.swing.JLabel();
        addUserFullNameScrollPane2 = new javax.swing.JScrollPane();
        editUserFullNameTextArea = new javax.swing.JTextArea();
        editUserUsernameLabel = new javax.swing.JLabel();
        editUserUsernameTextField = new javax.swing.JTextField();
        editUserPasswordLabel = new javax.swing.JLabel();
        editUserPasswordTextField = new javax.swing.JTextField();
        editEmployeeIdLabel = new javax.swing.JLabel();
        editEmployeeIdTextField = new javax.swing.JTextField();
        removedUsersPanel = new javax.swing.JPanel();
        removedUsersScrollPane = new javax.swing.JScrollPane();
        removedUsersTable = new javax.swing.JTable();
        removedUsersUserIdTextField = new javax.swing.JTextField();
        removedUsersUserNameTextField = new javax.swing.JTextField();
        removedUsersSearchButton = new javax.swing.JButton();
        removedUsersRefreshButton = new javax.swing.JButton();
        removedUsersUserListButton = new javax.swing.JButton();
        userlogPanel = new javax.swing.JPanel();
        userlogScrollPane = new javax.swing.JScrollPane();
        userlogTable = new javax.swing.JTable();
        userlogUserNameTextField = new javax.swing.JTextField();
        userlogTransactionIdTextField = new javax.swing.JTextField();
        userlogSearchButton = new javax.swing.JButton();
        userlogRefreshButton = new javax.swing.JButton();
        userlogTransactionTypeComboBox = new javax.swing.JComboBox<>();
        userlogUserIdTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stockMainPanel.setBackground(new java.awt.Color(255, 255, 255));
        stockMainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        headerPanel.setPreferredSize(new java.awt.Dimension(1007, 90));

        headerLabel.setBackground(new java.awt.Color(255, 255, 255));
        headerLabel.setIcon(new javax.swing.ImageIcon("D:\\Education\\SLIIT\\2nd year 2nd semester\\ITP\\Final Project\\Stock Management\\Stock Icons\\LogoNew.png")); // NOI18N

        logoutButton.setBackground(new java.awt.Color(255, 255, 255));
        logoutButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(42, 135, 235));
        logoutButton.setIcon(new javax.swing.ImageIcon("D:\\Education\\SLIIT\\2nd year 2nd semester\\ITP\\Final Project\\Stock Management\\Stock Icons\\StockLog.png")); // NOI18N
        logoutButton.setText("Log out");
        logoutButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        userLabel.setBackground(new java.awt.Color(255, 255, 255));
        userLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        userLabel.setForeground(new java.awt.Color(42, 135, 235));

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        stockMainPanel.add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        buttonPanel.setBackground(new java.awt.Color(255, 255, 255));
        buttonPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        buttonPanel.setMinimumSize(new java.awt.Dimension(100, 100));
        buttonPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stockButton.setBackground(new java.awt.Color(42, 135, 235));
        stockButton.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        stockButton.setForeground(new java.awt.Color(255, 255, 255));
        stockButton.setIcon(new javax.swing.ImageIcon("D:\\Education\\SLIIT\\2nd year 2nd semester\\ITP\\Final Project\\Stock Management\\Stock Icons\\StockStock.png")); // NOI18N
        stockButton.setText("STOCK");
        stockButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        stockButton.setBorderPainted(false);
        stockButton.setFocusPainted(false);
        stockButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        stockButton.setIconTextGap(6);
        stockButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                stockButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                stockButtonMouseExited(evt);
            }
        });
        stockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(stockButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, 186, 50));

        ordersButton.setBackground(new java.awt.Color(42, 135, 235));
        ordersButton.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        ordersButton.setForeground(new java.awt.Color(255, 255, 255));
        ordersButton.setIcon(new javax.swing.ImageIcon("D:\\Education\\SLIIT\\2nd year 2nd semester\\ITP\\Final Project\\Stock Management\\Stock Icons\\StockOrders.png")); // NOI18N
        ordersButton.setText("ORDERS");
        ordersButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        ordersButton.setFocusPainted(false);
        ordersButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ordersButton.setIconTextGap(6);
        ordersButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ordersButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ordersButtonMouseExited(evt);
            }
        });
        ordersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordersButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(ordersButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 69, 186, 50));

        purchaseButton.setBackground(new java.awt.Color(42, 135, 235));
        purchaseButton.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        purchaseButton.setForeground(new java.awt.Color(255, 255, 255));
        purchaseButton.setIcon(new javax.swing.ImageIcon("D:\\Education\\SLIIT\\2nd year 2nd semester\\ITP\\Final Project\\Stock Management\\Stock Icons\\StockPurchase.png")); // NOI18N
        purchaseButton.setText("PURCHASE");
        purchaseButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        purchaseButton.setFocusPainted(false);
        purchaseButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        purchaseButton.setIconTextGap(6);
        purchaseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                purchaseButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                purchaseButtonMouseExited(evt);
            }
        });
        purchaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(purchaseButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 125, 186, 50));

        transactionsButton.setBackground(new java.awt.Color(42, 135, 235));
        transactionsButton.setFont(new java.awt.Font("Segoe UI Symbol", 1, 18)); // NOI18N
        transactionsButton.setForeground(new java.awt.Color(255, 255, 255));
        transactionsButton.setIcon(new javax.swing.ImageIcon("D:\\Education\\SLIIT\\2nd year 2nd semester\\ITP\\Final Project\\Stock Management\\Stock Icons\\StockTransactions.png")); // NOI18N
        transactionsButton.setText("TRANSACTIONS");
        transactionsButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        transactionsButton.setFocusPainted(false);
        transactionsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        transactionsButton.setIconTextGap(6);
        transactionsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                transactionsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                transactionsButtonMouseExited(evt);
            }
        });
        transactionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionsButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(transactionsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 181, 186, 50));

        itemsButton.setBackground(new java.awt.Color(42, 135, 235));
        itemsButton.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        itemsButton.setForeground(new java.awt.Color(255, 255, 255));
        itemsButton.setIcon(new javax.swing.ImageIcon("D:\\Education\\SLIIT\\2nd year 2nd semester\\ITP\\Final Project\\Stock Management\\Stock Icons\\StockItems.png")); // NOI18N
        itemsButton.setText("ITEMS");
        itemsButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        itemsButton.setFocusPainted(false);
        itemsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        itemsButton.setIconTextGap(6);
        itemsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                itemsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                itemsButtonMouseExited(evt);
            }
        });
        itemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemsButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(itemsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 237, 186, 50));

        suppliersButton.setBackground(new java.awt.Color(42, 135, 235));
        suppliersButton.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        suppliersButton.setForeground(new java.awt.Color(255, 255, 255));
        suppliersButton.setIcon(new javax.swing.ImageIcon("D:\\Education\\SLIIT\\2nd year 2nd semester\\ITP\\Final Project\\Stock Management\\Stock Icons\\StockSuppliers.png")); // NOI18N
        suppliersButton.setText("SUPPLIERS");
        suppliersButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        suppliersButton.setFocusPainted(false);
        suppliersButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        suppliersButton.setIconTextGap(6);
        suppliersButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                suppliersButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                suppliersButtonMouseExited(evt);
            }
        });
        suppliersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppliersButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(suppliersButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 293, 186, 50));

        categoriesButton.setBackground(new java.awt.Color(42, 135, 235));
        categoriesButton.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        categoriesButton.setForeground(new java.awt.Color(255, 255, 255));
        categoriesButton.setIcon(new javax.swing.ImageIcon("D:\\Education\\SLIIT\\2nd year 2nd semester\\ITP\\Final Project\\Stock Management\\Stock Icons\\StockCategories.png")); // NOI18N
        categoriesButton.setText("CATEGORIES");
        categoriesButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        categoriesButton.setFocusPainted(false);
        categoriesButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        categoriesButton.setIconTextGap(6);
        categoriesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                categoriesButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                categoriesButtonMouseExited(evt);
            }
        });
        categoriesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriesButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(categoriesButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 349, 186, 50));

        accountButton.setBackground(new java.awt.Color(42, 135, 235));
        accountButton.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        accountButton.setForeground(new java.awt.Color(255, 255, 255));
        accountButton.setIcon(new javax.swing.ImageIcon("D:\\Education\\SLIIT\\2nd year 2nd semester\\ITP\\Final Project\\Stock Management\\Stock Icons\\StockAccount.png")); // NOI18N
        accountButton.setText("ACCOUNT");
        accountButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        accountButton.setFocusPainted(false);
        accountButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        accountButton.setIconTextGap(6);
        accountButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accountButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accountButtonMouseExited(evt);
            }
        });
        accountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(accountButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 405, 186, 50));

        usersButton.setBackground(new java.awt.Color(42, 135, 235));
        usersButton.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        usersButton.setForeground(new java.awt.Color(255, 255, 255));
        usersButton.setIcon(new javax.swing.ImageIcon("D:\\Education\\SLIIT\\2nd year 2nd semester\\ITP\\Final Project\\Stock Management\\Stock Icons\\StockUsers.png")); // NOI18N
        usersButton.setText("USERS");
        usersButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        usersButton.setFocusPainted(false);
        usersButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        usersButton.setIconTextGap(6);
        usersButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                usersButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                usersButtonMouseExited(evt);
            }
        });
        usersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usersButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(usersButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 461, 186, 50));

        userlogButton.setBackground(new java.awt.Color(42, 135, 235));
        userlogButton.setFont(new java.awt.Font("Segoe UI Symbol", 1, 20)); // NOI18N
        userlogButton.setForeground(new java.awt.Color(255, 255, 255));
        userlogButton.setIcon(new javax.swing.ImageIcon("D:\\Education\\SLIIT\\2nd year 2nd semester\\ITP\\Final Project\\Stock Management\\Stock Icons\\StockUserlog.png")); // NOI18N
        userlogButton.setText("USERLOG");
        userlogButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 8, 1, 1));
        userlogButton.setFocusPainted(false);
        userlogButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        userlogButton.setIconTextGap(6);
        userlogButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userlogButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                userlogButtonMouseExited(evt);
            }
        });
        userlogButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userlogButtonActionPerformed(evt);
            }
        });
        buttonPanel.add(userlogButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 517, 186, 50));

        stockMainPanel.add(buttonPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 210, 580));

        actionPanel.setBackground(new java.awt.Color(255, 255, 255));
        actionPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        actionPanel.setMaximumSize(new java.awt.Dimension(785, 580));
        actionPanel.setMinimumSize(new java.awt.Dimension(785, 580));
        actionPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        stockPanel.setBackground(new java.awt.Color(255, 255, 255));
        stockPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        stockPanel.setForeground(new java.awt.Color(42, 135, 235));
        stockPanel.setMaximumSize(new java.awt.Dimension(781, 578));
        stockPanel.setMinimumSize(new java.awt.Dimension(781, 578));
        stockPanel.setPreferredSize(new java.awt.Dimension(797, 580));

        stockScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        stockScrollPane.setBorder(null);

        stockTable.setAutoCreateRowSorter(true);
        stockTable.setForeground(new java.awt.Color(42, 135, 235));
        stockTable.setModel(new javax.swing.table.DefaultTableModel(
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
                "Stock ID", "Item ID", "Item Name", "Category", "Avail Qty", "Units", "Re-order"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stockTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        stockTable.setFocusable(false);
        stockTable.setGridColor(new java.awt.Color(42, 135, 235));
        stockTable.getTableHeader().setResizingAllowed(false);
        stockTable.getTableHeader().setReorderingAllowed(false);
        stockScrollPane.setViewportView(stockTable);
        if (stockTable.getColumnModel().getColumnCount() > 0) {
            stockTable.getColumnModel().getColumn(0).setResizable(false);
            stockTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            stockTable.getColumnModel().getColumn(1).setResizable(false);
            stockTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            stockTable.getColumnModel().getColumn(2).setResizable(false);
            stockTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            stockTable.getColumnModel().getColumn(3).setResizable(false);
            stockTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            stockTable.getColumnModel().getColumn(4).setResizable(false);
            stockTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            stockTable.getColumnModel().getColumn(5).setResizable(false);
            stockTable.getColumnModel().getColumn(6).setResizable(false);
        }

        stockIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        stockIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Stock ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        stockItemIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        stockItemIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockItemIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        stockItemNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        stockItemNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockItemNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        stockSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        stockSearchButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stockSearchButton.setForeground(new java.awt.Color(42, 135, 235));
        stockSearchButton.setText("Search");
        stockSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        stockSearchButton.setFocusPainted(false);
        stockSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockSearchButtonActionPerformed(evt);
            }
        });

        stockRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        stockRefreshButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stockRefreshButton.setForeground(new java.awt.Color(42, 135, 235));
        stockRefreshButton.setText("Refresh");
        stockRefreshButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        stockRefreshButton.setFocusPainted(false);
        stockRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockRefreshButtonActionPerformed(evt);
            }
        });

        stockItemCategoryComboBox.setForeground(new java.awt.Color(42, 135, 235));
        stockItemCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Category" }));

        javax.swing.GroupLayout stockPanelLayout = new javax.swing.GroupLayout(stockPanel);
        stockPanel.setLayout(stockPanelLayout);
        stockPanelLayout.setHorizontalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stockPanelLayout.createSequentialGroup()
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(stockPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(stockScrollPane))
                    .addGroup(stockPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(stockIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(stockItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(stockItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(stockItemCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(stockSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(stockRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addGap(16, 16, 16))
        );
        stockPanelLayout.setVerticalGroup(
            stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(stockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockItemIdTextField)
                    .addComponent(stockIdTextField)
                    .addComponent(stockItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockItemCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(stockScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        actionPanel.add(stockPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 580));

        ordersPanel.setBackground(new java.awt.Color(255, 255, 255));
        ordersPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        ordersPanel.setMaximumSize(new java.awt.Dimension(783, 587));
        ordersPanel.setMinimumSize(new java.awt.Dimension(783, 587));
        ordersPanel.setPreferredSize(new java.awt.Dimension(797, 591));
        ordersPanel.setLayout(new java.awt.BorderLayout());

        ordersTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        ordersTabbedPane.setForeground(new java.awt.Color(42, 135, 235));
        ordersTabbedPane.setName(""); // NOI18N

        orderListPanel.setBackground(new java.awt.Color(255, 255, 255));
        orderListPanel.setForeground(new java.awt.Color(42, 135, 235));

        ordersScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        ordersScrollPane.setBorder(null);

        ordersTable.setAutoCreateRowSorter(true);
        ordersTable.setForeground(new java.awt.Color(42, 135, 235));
        ordersTable.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Order ID", "Item ID", "Item Name", "Category", "Department", "Order Qty", "Units", "Order Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ordersTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ordersTable.setFocusable(false);
        ordersTable.setGridColor(new java.awt.Color(42, 135, 235));
        ordersTable.getTableHeader().setResizingAllowed(false);
        ordersTable.getTableHeader().setReorderingAllowed(false);
        ordersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordersTableMouseClicked(evt);
            }
        });
        ordersScrollPane.setViewportView(ordersTable);

        orderItemIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        orderItemIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        orderItemIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        orderItemNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        orderItemNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        orderItemNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        orderSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        orderSearchButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        orderSearchButton.setForeground(new java.awt.Color(42, 135, 235));
        orderSearchButton.setText("Search");
        orderSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        orderSearchButton.setFocusPainted(false);
        orderSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderSearchButtonActionPerformed(evt);
            }
        });

        orderRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        orderRefreshButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        orderRefreshButton.setForeground(new java.awt.Color(42, 135, 235));
        orderRefreshButton.setText("Refresh");
        orderRefreshButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        orderRefreshButton.setFocusPainted(false);
        orderRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderRefreshButtonActionPerformed(evt);
            }
        });

        orderIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        orderIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        orderIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Order ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        orderItemDepartmentTextField.setForeground(new java.awt.Color(42, 135, 235));
        orderItemDepartmentTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        orderItemDepartmentTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Department", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        orderNextButton.setBackground(new java.awt.Color(255, 255, 255));
        orderNextButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        orderNextButton.setForeground(new java.awt.Color(42, 135, 235));
        orderNextButton.setText("Next");
        orderNextButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        orderNextButton.setFocusPainted(false);
        orderNextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderNextButtonActionPerformed(evt);
            }
        });

        orderDemoButton.setBackground(new java.awt.Color(255, 255, 255));
        orderDemoButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        orderDemoButton.setForeground(new java.awt.Color(42, 135, 235));
        orderDemoButton.setText("Demo");
        orderDemoButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        orderDemoButton.setFocusPainted(false);
        orderDemoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderDemoButtonActionPerformed(evt);
            }
        });

        orderItemCategoryComboBox.setForeground(new java.awt.Color(42, 135, 235));
        orderItemCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Category" }));

        javax.swing.GroupLayout orderListPanelLayout = new javax.swing.GroupLayout(orderListPanel);
        orderListPanel.setLayout(orderListPanelLayout);
        orderListPanelLayout.setHorizontalGroup(
            orderListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(orderListPanelLayout.createSequentialGroup()
                .addGroup(orderListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(orderListPanelLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(ordersScrollPane))
                    .addGroup(orderListPanelLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(orderDemoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(orderNextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, orderListPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(orderIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(orderItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(orderItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(orderItemDepartmentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(orderItemCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(orderSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(orderRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        orderListPanelLayout.setVerticalGroup(
            orderListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderListPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(orderListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderItemDepartmentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderItemCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(ordersScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(orderListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderNextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderDemoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        ordersTabbedPane.addTab("Order List", orderListPanel);

        transferStockPanel.setBackground(new java.awt.Color(255, 255, 255));
        transferStockPanel.setForeground(new java.awt.Color(42, 135, 235));

        transferOrderIdLabel.setForeground(new java.awt.Color(42, 135, 235));
        transferOrderIdLabel.setText("Order ID");

        transferOrderIdTextField.setEditable(false);
        transferOrderIdTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        transferOrderIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        transferOrderIdTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        transferItemIdLabel.setForeground(new java.awt.Color(42, 135, 235));
        transferItemIdLabel.setText("Item ID");

        transferItemIdTextField.setEditable(false);
        transferItemIdTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        transferItemIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        transferItemIdTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        transferItemNameLabel.setForeground(new java.awt.Color(42, 135, 235));
        transferItemNameLabel.setText("Item Name");

        transferItemNameTextField.setEditable(false);
        transferItemNameTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        transferItemNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        transferItemNameTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        transferItemCategoryLabel.setForeground(new java.awt.Color(42, 135, 235));
        transferItemCategoryLabel.setText("Category");

        transferItemCategoryTextField.setEditable(false);
        transferItemCategoryTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        transferItemCategoryTextField.setForeground(new java.awt.Color(42, 135, 235));
        transferItemCategoryTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        transferDepartmentLabel.setForeground(new java.awt.Color(42, 135, 235));
        transferDepartmentLabel.setText("Department");

        transferDepartmentTextField.setEditable(false);
        transferDepartmentTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        transferDepartmentTextField.setForeground(new java.awt.Color(42, 135, 235));
        transferDepartmentTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        transferOrderQtyLabel.setForeground(new java.awt.Color(42, 135, 235));
        transferOrderQtyLabel.setText("Order Qty");

        transferOrderQtyTextField.setEditable(false);
        transferOrderQtyTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        transferOrderQtyTextField.setForeground(new java.awt.Color(42, 135, 235));
        transferOrderQtyTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        transferUnitsLabel.setForeground(new java.awt.Color(42, 135, 235));
        transferUnitsLabel.setText("Units");

        transferOrderDateLabel.setForeground(new java.awt.Color(42, 135, 235));
        transferOrderDateLabel.setText("Order Date");

        transferOrderDateTextField.setEditable(false);
        transferOrderDateTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        transferOrderDateTextField.setForeground(new java.awt.Color(42, 135, 235));
        transferOrderDateTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        transferAvailQtyLabel.setForeground(new java.awt.Color(42, 135, 235));
        transferAvailQtyLabel.setText("Avail Qty");

        transferAvailQtyTextField.setEditable(false);
        transferAvailQtyTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        transferAvailQtyTextField.setForeground(new java.awt.Color(42, 135, 235));
        transferAvailQtyTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        transferOrderButton.setBackground(new java.awt.Color(255, 255, 255));
        transferOrderButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        transferOrderButton.setForeground(new java.awt.Color(42, 135, 235));
        transferOrderButton.setText("Transfer Order");
        transferOrderButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        transferOrderButton.setFocusPainted(false);
        transferOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferOrderButtonActionPerformed(evt);
            }
        });

        transferOrderListButton.setBackground(new java.awt.Color(255, 255, 255));
        transferOrderListButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        transferOrderListButton.setForeground(new java.awt.Color(42, 135, 235));
        transferOrderListButton.setText("Order List");
        transferOrderListButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        transferOrderListButton.setFocusPainted(false);
        transferOrderListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferOrderListButtonActionPerformed(evt);
            }
        });

        transferUnitsTextField.setEditable(false);
        transferUnitsTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        transferUnitsTextField.setForeground(new java.awt.Color(42, 135, 235));
        transferUnitsTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        javax.swing.GroupLayout transferStockPanelLayout = new javax.swing.GroupLayout(transferStockPanel);
        transferStockPanel.setLayout(transferStockPanelLayout);
        transferStockPanelLayout.setHorizontalGroup(
            transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transferStockPanelLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transferStockPanelLayout.createSequentialGroup()
                        .addComponent(transferOrderQtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(transferOrderQtyTextField))
                    .addGroup(transferStockPanelLayout.createSequentialGroup()
                        .addComponent(transferDepartmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(transferDepartmentTextField))
                    .addGroup(transferStockPanelLayout.createSequentialGroup()
                        .addGroup(transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(transferStockPanelLayout.createSequentialGroup()
                                .addComponent(transferUnitsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(transferUnitsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(transferStockPanelLayout.createSequentialGroup()
                                .addComponent(transferOrderIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(transferOrderIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                            .addGroup(transferStockPanelLayout.createSequentialGroup()
                                .addComponent(transferItemIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(transferItemIdTextField))
                            .addGroup(transferStockPanelLayout.createSequentialGroup()
                                .addComponent(transferItemNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(transferItemNameTextField))
                            .addGroup(transferStockPanelLayout.createSequentialGroup()
                                .addComponent(transferItemCategoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(transferItemCategoryTextField)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(transferStockPanelLayout.createSequentialGroup()
                        .addComponent(transferOrderDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(transferOrderDateTextField)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(transferAvailQtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(transferAvailQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
            .addGroup(transferStockPanelLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(transferOrderListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(transferOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
        );
        transferStockPanelLayout.setVerticalGroup(
            transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transferStockPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferOrderIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferOrderIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferItemIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferItemNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferItemCategoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferItemCategoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferDepartmentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferDepartmentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferOrderQtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferOrderQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferAvailQtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferAvailQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferUnitsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferUnitsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferOrderDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferOrderDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(91, 91, 91)
                .addGroup(transferStockPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transferOrderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transferOrderListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        ordersTabbedPane.addTab("Transfer Stock", transferStockPanel);

        ordersPanel.add(ordersTabbedPane, java.awt.BorderLayout.CENTER);

        actionPanel.add(ordersPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 580));

        purchasePanel.setBackground(new java.awt.Color(255, 255, 255));
        purchasePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        purchasePanel.setPreferredSize(new java.awt.Dimension(790, 580));

        addStockPurchaseItemCategoryLabel.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseItemCategoryLabel.setText("Item Category");

        addStockPurchaseItemCategoryComboBox.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseItemCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Item Category" }));
        addStockPurchaseItemCategoryComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addStockPurchaseItemCategoryComboBoxItemStateChanged(evt);
            }
        });

        addStockPurchaseItemNameLabel.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseItemNameLabel.setText("Item Name");

        addStockPurchaseItemNameComboBox.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseItemNameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Item Name" }));
        addStockPurchaseItemNameComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addStockPurchaseItemNameComboBoxItemStateChanged(evt);
            }
        });

        addStockPurchaseItemIdLabel.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseItemIdLabel.setText("Item ID");

        addStockPurchaseItemIdComboBox.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseItemIdComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Item ID" }));
        addStockPurchaseItemIdComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addStockPurchaseItemIdComboBoxItemStateChanged(evt);
            }
        });

        addStockPurchaseSupplierNameLabel.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseSupplierNameLabel.setText("Supplier Name");

        addStockPurchaseSupplierNameComboBox.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseSupplierNameComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Supplier Name" }));
        addStockPurchaseSupplierNameComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addStockPurchaseSupplierNameComboBoxItemStateChanged(evt);
            }
        });

        addStockPurchaseSupplierIdComboBox.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseSupplierIdComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Supplier ID" }));
        addStockPurchaseSupplierIdComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addStockPurchaseSupplierIdComboBoxItemStateChanged(evt);
            }
        });

        addStockPurchaseSupplierIdLabel.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseSupplierIdLabel.setText("Supplier ID");

        addStockPurchaseItemUnitPriceLabel.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseItemUnitPriceLabel.setText("Unit Price (Rs)");

        addStockPurchaseItemUnitsLabel.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseItemUnitsLabel.setText("Units");

        addStockPurchaseItemUnitPriceTextField.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseItemUnitPriceTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        addStockPurchaseAvailQtyLabel.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseAvailQtyLabel.setText("Avail Qty");

        addStockPurchaseAvailQtyTextField.setEditable(false);
        addStockPurchaseAvailQtyTextField.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseAvailQtyTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        addStockPurchaseItemQtyLabel.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseItemQtyLabel.setText("Item Qty");

        addStockPurchaseItemQtyTextField.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseItemQtyTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        addStockPurchaseTotalAmountLabel.setBackground(new java.awt.Color(255, 255, 255));
        addStockPurchaseTotalAmountLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addStockPurchaseTotalAmountLabel.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseTotalAmountLabel.setText("Total Amount (Rs)  :");

        addStockPurchaseTotalAmountTextField.setEditable(false);
        addStockPurchaseTotalAmountTextField.setBackground(new java.awt.Color(255, 255, 255));
        addStockPurchaseTotalAmountTextField.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addStockPurchaseTotalAmountTextField.setForeground(new java.awt.Color(255, 153, 153));
        addStockPurchaseTotalAmountTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        addStockPurchaseTotalAmountTextField.setText("0");
        addStockPurchaseTotalAmountTextField.setBorder(null);

        stockPurchaseConfirmPurchaseButton.setBackground(new java.awt.Color(255, 255, 255));
        stockPurchaseConfirmPurchaseButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stockPurchaseConfirmPurchaseButton.setForeground(new java.awt.Color(42, 135, 235));
        stockPurchaseConfirmPurchaseButton.setText("Confirm Purchase");
        stockPurchaseConfirmPurchaseButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        stockPurchaseConfirmPurchaseButton.setFocusPainted(false);
        stockPurchaseConfirmPurchaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockPurchaseConfirmPurchaseButtonActionPerformed(evt);
            }
        });

        addStockPurchaseItemUnitsTextField.setEditable(false);
        addStockPurchaseItemUnitsTextField.setForeground(new java.awt.Color(42, 135, 235));
        addStockPurchaseItemUnitsTextField.setMargin(new java.awt.Insets(2, 10, 2, 2));

        stockPurchaseClearButton.setBackground(new java.awt.Color(255, 255, 255));
        stockPurchaseClearButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stockPurchaseClearButton.setForeground(new java.awt.Color(42, 135, 235));
        stockPurchaseClearButton.setText("Clear");
        stockPurchaseClearButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        stockPurchaseClearButton.setFocusPainted(false);
        stockPurchaseClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockPurchaseClearButtonActionPerformed(evt);
            }
        });

        stockPurchaseCalculateTotalButton.setBackground(new java.awt.Color(255, 255, 255));
        stockPurchaseCalculateTotalButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stockPurchaseCalculateTotalButton.setForeground(new java.awt.Color(42, 135, 235));
        stockPurchaseCalculateTotalButton.setText("Calculate Total");
        stockPurchaseCalculateTotalButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        stockPurchaseCalculateTotalButton.setFocusPainted(false);
        stockPurchaseCalculateTotalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockPurchaseCalculateTotalButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout purchasePanelLayout = new javax.swing.GroupLayout(purchasePanel);
        purchasePanel.setLayout(purchasePanelLayout);
        purchasePanelLayout.setHorizontalGroup(
            purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, purchasePanelLayout.createSequentialGroup()
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(purchasePanelLayout.createSequentialGroup()
                        .addContainerGap(98, Short.MAX_VALUE)
                        .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(purchasePanelLayout.createSequentialGroup()
                                    .addComponent(addStockPurchaseItemUnitPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(addStockPurchaseItemUnitPriceTextField))
                                .addGroup(purchasePanelLayout.createSequentialGroup()
                                    .addComponent(addStockPurchaseItemIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(addStockPurchaseItemIdComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(purchasePanelLayout.createSequentialGroup()
                                    .addComponent(addStockPurchaseItemNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(addStockPurchaseItemNameComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(purchasePanelLayout.createSequentialGroup()
                                    .addComponent(addStockPurchaseItemCategoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(addStockPurchaseItemCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(purchasePanelLayout.createSequentialGroup()
                                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, purchasePanelLayout.createSequentialGroup()
                                        .addComponent(addStockPurchaseTotalAmountLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(addStockPurchaseTotalAmountTextField))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, purchasePanelLayout.createSequentialGroup()
                                        .addComponent(addStockPurchaseSupplierIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(addStockPurchaseSupplierIdComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(purchasePanelLayout.createSequentialGroup()
                                        .addComponent(addStockPurchaseSupplierNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(addStockPurchaseSupplierNameComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, purchasePanelLayout.createSequentialGroup()
                                        .addComponent(addStockPurchaseItemUnitsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(addStockPurchaseItemUnitsTextField))
                                    .addGroup(purchasePanelLayout.createSequentialGroup()
                                        .addComponent(addStockPurchaseItemQtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(addStockPurchaseItemQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(purchasePanelLayout.createSequentialGroup()
                                        .addGap(40, 40, 40)
                                        .addComponent(addStockPurchaseAvailQtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(addStockPurchaseAvailQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(purchasePanelLayout.createSequentialGroup()
                                        .addGap(128, 128, 128)
                                        .addComponent(stockPurchaseCalculateTotalButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(purchasePanelLayout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(stockPurchaseClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stockPurchaseConfirmPurchaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100))
        );
        purchasePanelLayout.setVerticalGroup(
            purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchasePanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addStockPurchaseItemCategoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addStockPurchaseItemCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addStockPurchaseItemNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addStockPurchaseItemNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addStockPurchaseItemIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addStockPurchaseItemIdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addStockPurchaseItemUnitPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addStockPurchaseItemUnitPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(addStockPurchaseItemQtyLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addStockPurchaseItemQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addStockPurchaseAvailQtyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addStockPurchaseAvailQtyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addStockPurchaseItemUnitsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(addStockPurchaseItemUnitsTextField))
                .addGap(18, 18, 18)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addStockPurchaseSupplierNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addStockPurchaseSupplierNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addStockPurchaseSupplierIdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addStockPurchaseSupplierIdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addStockPurchaseTotalAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addStockPurchaseTotalAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockPurchaseCalculateTotalButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stockPurchaseConfirmPurchaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockPurchaseClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        actionPanel.add(purchasePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        transactionsPanel.setBackground(new java.awt.Color(255, 255, 255));
        transactionsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        transactionsPanel.setMaximumSize(new java.awt.Dimension(781, 578));
        transactionsPanel.setMinimumSize(new java.awt.Dimension(781, 578));
        transactionsPanel.setPreferredSize(new java.awt.Dimension(797, 591));
        transactionsPanel.setLayout(new java.awt.BorderLayout());

        stockTransactionsTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        stockTransactionsTabbedPane.setForeground(new java.awt.Color(42, 135, 235));
        stockTransactionsTabbedPane.setPreferredSize(new java.awt.Dimension(780, 587));

        stockPurchasePanel.setBackground(new java.awt.Color(255, 255, 255));
        stockPurchasePanel.setForeground(new java.awt.Color(42, 135, 235));

        stockPurchaseScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        stockPurchaseScrollPane.setBorder(null);

        stockPurchaseTable.setAutoCreateRowSorter(true);
        stockPurchaseTable.setForeground(new java.awt.Color(42, 135, 235));
        stockPurchaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Purchase ID", "Item ID", "Item Name", "Qty", "Units", "Unit Price", "Total Amount", "Supplier ID", "Supplier Name", "Purchase Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stockPurchaseTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        stockPurchaseTable.setFocusable(false);
        stockPurchaseTable.setGridColor(new java.awt.Color(42, 135, 235));
        stockPurchaseTable.getTableHeader().setResizingAllowed(false);
        stockPurchaseTable.getTableHeader().setReorderingAllowed(false);
        stockPurchaseScrollPane.setViewportView(stockPurchaseTable);
        if (stockPurchaseTable.getColumnModel().getColumnCount() > 0) {
            stockPurchaseTable.getColumnModel().getColumn(0).setResizable(false);
            stockPurchaseTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            stockPurchaseTable.getColumnModel().getColumn(1).setResizable(false);
            stockPurchaseTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            stockPurchaseTable.getColumnModel().getColumn(2).setResizable(false);
            stockPurchaseTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            stockPurchaseTable.getColumnModel().getColumn(3).setResizable(false);
            stockPurchaseTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            stockPurchaseTable.getColumnModel().getColumn(4).setResizable(false);
            stockPurchaseTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            stockPurchaseTable.getColumnModel().getColumn(5).setResizable(false);
            stockPurchaseTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            stockPurchaseTable.getColumnModel().getColumn(6).setResizable(false);
            stockPurchaseTable.getColumnModel().getColumn(6).setPreferredWidth(100);
            stockPurchaseTable.getColumnModel().getColumn(7).setResizable(false);
            stockPurchaseTable.getColumnModel().getColumn(7).setPreferredWidth(100);
            stockPurchaseTable.getColumnModel().getColumn(8).setResizable(false);
            stockPurchaseTable.getColumnModel().getColumn(8).setPreferredWidth(100);
            stockPurchaseTable.getColumnModel().getColumn(9).setResizable(false);
            stockPurchaseTable.getColumnModel().getColumn(9).setPreferredWidth(100);
        }

        stockPurchaseIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        stockPurchaseIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockPurchaseIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Purchase ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        stockPurchaseItemIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        stockPurchaseItemIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockPurchaseItemIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        stockPurchaseItemNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        stockPurchaseItemNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockPurchaseItemNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        stockPurchaseSupplierIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        stockPurchaseSupplierIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockPurchaseSupplierIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        stockPurchaseSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        stockPurchaseSearchButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stockPurchaseSearchButton.setForeground(new java.awt.Color(42, 135, 235));
        stockPurchaseSearchButton.setText("Search");
        stockPurchaseSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        stockPurchaseSearchButton.setFocusPainted(false);
        stockPurchaseSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockPurchaseSearchButtonActionPerformed(evt);
            }
        });

        stockPurchaseRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        stockPurchaseRefreshButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stockPurchaseRefreshButton.setForeground(new java.awt.Color(42, 135, 235));
        stockPurchaseRefreshButton.setText("Refresh");
        stockPurchaseRefreshButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        stockPurchaseRefreshButton.setFocusPainted(false);
        stockPurchaseRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockPurchaseRefreshButtonActionPerformed(evt);
            }
        });

        stockPurchaseSupplierNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        stockPurchaseSupplierNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockPurchaseSupplierNameTextField.setToolTipText("");
        stockPurchaseSupplierNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        javax.swing.GroupLayout stockPurchasePanelLayout = new javax.swing.GroupLayout(stockPurchasePanel);
        stockPurchasePanel.setLayout(stockPurchasePanelLayout);
        stockPurchasePanelLayout.setHorizontalGroup(
            stockPurchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockPurchasePanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(stockPurchaseIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stockPurchaseItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stockPurchaseItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stockPurchaseSupplierIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stockPurchaseSupplierNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(stockPurchaseSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(stockPurchaseRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(stockPurchasePanelLayout.createSequentialGroup()
                .addComponent(stockPurchaseScrollPane)
                .addGap(1, 1, 1))
        );
        stockPurchasePanelLayout.setVerticalGroup(
            stockPurchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockPurchasePanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(stockPurchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(stockPurchaseItemIdTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stockPurchaseItemNameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stockPurchaseSupplierIdTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stockPurchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(stockPurchaseSupplierNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stockPurchaseSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stockPurchaseRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(stockPurchaseIdTextField))
                .addGap(18, 18, 18)
                .addComponent(stockPurchaseScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        stockTransactionsTabbedPane.addTab("Stock Purchases", stockPurchasePanel);

        stockTransferPanel.setBackground(new java.awt.Color(255, 255, 255));
        stockTransferPanel.setForeground(new java.awt.Color(42, 135, 235));

        stockTransferScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        stockTransferScrollPane.setBorder(null);

        stockTransferTable.setAutoCreateRowSorter(true);
        stockTransferTable.setForeground(new java.awt.Color(42, 135, 235));
        stockTransferTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Transfer ID", "Item ID", "Item Name", "Qty", "Units", "Department", "Status", "Ordered Date", "Transfered Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stockTransferTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        stockTransferTable.setFocusable(false);
        stockTransferTable.setGridColor(new java.awt.Color(42, 135, 235));
        stockTransferTable.getTableHeader().setResizingAllowed(false);
        stockTransferTable.getTableHeader().setReorderingAllowed(false);
        stockTransferScrollPane.setViewportView(stockTransferTable);
        if (stockTransferTable.getColumnModel().getColumnCount() > 0) {
            stockTransferTable.getColumnModel().getColumn(0).setResizable(false);
            stockTransferTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            stockTransferTable.getColumnModel().getColumn(1).setResizable(false);
            stockTransferTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            stockTransferTable.getColumnModel().getColumn(2).setResizable(false);
            stockTransferTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            stockTransferTable.getColumnModel().getColumn(3).setResizable(false);
            stockTransferTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            stockTransferTable.getColumnModel().getColumn(4).setResizable(false);
            stockTransferTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            stockTransferTable.getColumnModel().getColumn(5).setResizable(false);
            stockTransferTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            stockTransferTable.getColumnModel().getColumn(6).setResizable(false);
            stockTransferTable.getColumnModel().getColumn(6).setPreferredWidth(100);
            stockTransferTable.getColumnModel().getColumn(7).setResizable(false);
            stockTransferTable.getColumnModel().getColumn(7).setPreferredWidth(200);
            stockTransferTable.getColumnModel().getColumn(8).setResizable(false);
            stockTransferTable.getColumnModel().getColumn(8).setPreferredWidth(200);
        }

        stockTransferIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        stockTransferIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockTransferIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transfer ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        stockTransferItemIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        stockTransferItemIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockTransferItemIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        stockTransferItemNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        stockTransferItemNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockTransferItemNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        stockTransferDepartmentTextField.setForeground(new java.awt.Color(42, 135, 235));
        stockTransferDepartmentTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stockTransferDepartmentTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Department", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        stockTransferSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        stockTransferSearchButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stockTransferSearchButton.setForeground(new java.awt.Color(42, 135, 235));
        stockTransferSearchButton.setText("Search");
        stockTransferSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        stockTransferSearchButton.setFocusPainted(false);
        stockTransferSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockTransferSearchButtonActionPerformed(evt);
            }
        });

        stockTransferRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        stockTransferRefreshButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        stockTransferRefreshButton.setForeground(new java.awt.Color(42, 135, 235));
        stockTransferRefreshButton.setText("Refresh");
        stockTransferRefreshButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        stockTransferRefreshButton.setFocusPainted(false);
        stockTransferRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stockTransferRefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout stockTransferPanelLayout = new javax.swing.GroupLayout(stockTransferPanel);
        stockTransferPanel.setLayout(stockTransferPanelLayout);
        stockTransferPanelLayout.setHorizontalGroup(
            stockTransferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockTransferPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(stockTransferIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stockTransferItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stockTransferItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stockTransferDepartmentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(stockTransferSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(stockTransferRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(stockTransferPanelLayout.createSequentialGroup()
                .addComponent(stockTransferScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        stockTransferPanelLayout.setVerticalGroup(
            stockTransferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockTransferPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(stockTransferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(stockTransferPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(stockTransferSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stockTransferRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(stockTransferDepartmentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockTransferItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockTransferItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stockTransferIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(stockTransferScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        stockTransactionsTabbedPane.addTab("Stock Transfers", stockTransferPanel);

        transactionsPanel.add(stockTransactionsTabbedPane, java.awt.BorderLayout.CENTER);

        actionPanel.add(transactionsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 580));

        itemsPanel.setBackground(new java.awt.Color(255, 255, 255));
        itemsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        itemsPanel.setMaximumSize(new java.awt.Dimension(783, 587));
        itemsPanel.setMinimumSize(new java.awt.Dimension(783, 587));
        itemsPanel.setPreferredSize(new java.awt.Dimension(797, 591));
        itemsPanel.setLayout(new java.awt.BorderLayout());

        itemTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        itemTabbedPane.setForeground(new java.awt.Color(42, 135, 235));
        itemTabbedPane.setFocusable(false);
        itemTabbedPane.setPreferredSize(new java.awt.Dimension(784, 580));

        itemListPanel.setBackground(new java.awt.Color(255, 255, 255));
        itemListPanel.setForeground(new java.awt.Color(42, 135, 235));
        itemListPanel.setPreferredSize(new java.awt.Dimension(777, 556));

        itemListScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        itemListScrollPane.setBorder(null);

        itemListTable.setAutoCreateRowSorter(true);
        itemListTable.setForeground(new java.awt.Color(42, 135, 235));
        itemListTable.setModel(new javax.swing.table.DefaultTableModel(
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
                "Item ID", "Item Name", "Units", "Description", "Category", "Re-order Level"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
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
        itemListTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        itemListTable.setFocusable(false);
        itemListTable.setGridColor(new java.awt.Color(42, 135, 235));
        itemListTable.getTableHeader().setResizingAllowed(false);
        itemListTable.getTableHeader().setReorderingAllowed(false);
        itemListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemListTableMouseClicked(evt);
            }
        });
        itemListScrollPane.setViewportView(itemListTable);
        if (itemListTable.getColumnModel().getColumnCount() > 0) {
            itemListTable.getColumnModel().getColumn(0).setResizable(false);
            itemListTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            itemListTable.getColumnModel().getColumn(1).setResizable(false);
            itemListTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            itemListTable.getColumnModel().getColumn(2).setResizable(false);
            itemListTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            itemListTable.getColumnModel().getColumn(3).setResizable(false);
            itemListTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            itemListTable.getColumnModel().getColumn(4).setResizable(false);
            itemListTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            itemListTable.getColumnModel().getColumn(5).setResizable(false);
            itemListTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        itemListItemIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        itemListItemIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        itemListItemIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        itemListItemNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        itemListItemNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        itemListItemNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        itemListSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        itemListSearchButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        itemListSearchButton.setForeground(new java.awt.Color(42, 135, 235));
        itemListSearchButton.setText("Search");
        itemListSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        itemListSearchButton.setFocusPainted(false);
        itemListSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListSearchButtonActionPerformed(evt);
            }
        });

        itemListRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        itemListRefreshButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        itemListRefreshButton.setForeground(new java.awt.Color(42, 135, 235));
        itemListRefreshButton.setText("Refresh");
        itemListRefreshButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        itemListRefreshButton.setFocusPainted(false);
        itemListRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListRefreshButtonActionPerformed(evt);
            }
        });

        itemListAddItemButton.setBackground(new java.awt.Color(255, 255, 255));
        itemListAddItemButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        itemListAddItemButton.setForeground(new java.awt.Color(42, 135, 235));
        itemListAddItemButton.setText("Add Item");
        itemListAddItemButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        itemListAddItemButton.setFocusPainted(false);
        itemListAddItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListAddItemButtonActionPerformed(evt);
            }
        });

        itemListRemovedItemsButton.setBackground(new java.awt.Color(255, 255, 255));
        itemListRemovedItemsButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        itemListRemovedItemsButton.setForeground(new java.awt.Color(42, 135, 235));
        itemListRemovedItemsButton.setText("Show Removed Items");
        itemListRemovedItemsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        itemListRemovedItemsButton.setFocusPainted(false);
        itemListRemovedItemsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListRemovedItemsButtonActionPerformed(evt);
            }
        });

        itemListEditItemButton.setBackground(new java.awt.Color(255, 255, 255));
        itemListEditItemButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        itemListEditItemButton.setForeground(new java.awt.Color(42, 135, 235));
        itemListEditItemButton.setText("Edit Item");
        itemListEditItemButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        itemListEditItemButton.setFocusPainted(false);
        itemListEditItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemListEditItemButtonActionPerformed(evt);
            }
        });

        itemListItemCategoryComboBox.setForeground(new java.awt.Color(42, 135, 235));
        itemListItemCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Category" }));
        itemListItemCategoryComboBox.setBorder(null);

        javax.swing.GroupLayout itemListPanelLayout = new javax.swing.GroupLayout(itemListPanel);
        itemListPanel.setLayout(itemListPanelLayout);
        itemListPanelLayout.setHorizontalGroup(
            itemListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemListPanelLayout.createSequentialGroup()
                .addGroup(itemListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(itemListPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(itemListScrollPane))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, itemListPanelLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(itemListAddItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(itemListEditItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(itemListRemovedItemsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 94, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemListPanelLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(itemListItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(itemListItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(itemListItemCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(itemListSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(itemListRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        itemListPanelLayout.setVerticalGroup(
            itemListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemListPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(itemListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(itemListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(itemListItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemListItemCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemListSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(itemListRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(itemListItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(itemListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(itemListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemListAddItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemListRemovedItemsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemListEditItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        itemTabbedPane.addTab("Item List", itemListPanel);

        addItemPanel.setBackground(new java.awt.Color(255, 255, 255));
        addItemPanel.setForeground(new java.awt.Color(42, 135, 235));
        addItemPanel.setToolTipText("");

        addItemNameLabel.setForeground(new java.awt.Color(42, 135, 235));
        addItemNameLabel.setText("Item Name");

        addItemNameTextField.setForeground(new java.awt.Color(42, 135, 235));

        addItemUnitsLabel.setForeground(new java.awt.Color(42, 135, 235));
        addItemUnitsLabel.setText("Item Units");

        addItemDescriptionLabel.setForeground(new java.awt.Color(42, 135, 235));
        addItemDescriptionLabel.setText("Item Description");

        addItemCategoryLabel.setForeground(new java.awt.Color(42, 135, 235));
        addItemCategoryLabel.setText("Item Category");

        addItemReorderLevelLabel.setForeground(new java.awt.Color(42, 135, 235));
        addItemReorderLevelLabel.setText("Re-order Level");

        addItemReorderLevelTextField.setForeground(new java.awt.Color(42, 135, 235));

        addItemUnitsComboBox.setForeground(new java.awt.Color(42, 135, 235));
        addItemUnitsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Units", "kilogram", "litre", "pound", "piece", "meter" }));

        addItemDescriptionScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        addItemDescriptionTextArea.setColumns(20);
        addItemDescriptionTextArea.setForeground(new java.awt.Color(42, 135, 235));
        addItemDescriptionTextArea.setRows(5);
        addItemDescriptionScrollPane.setViewportView(addItemDescriptionTextArea);

        addItemCategoryComboBox.setForeground(new java.awt.Color(42, 135, 235));
        addItemCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Category" }));

        addItemItemListButton.setBackground(new java.awt.Color(255, 255, 255));
        addItemItemListButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addItemItemListButton.setForeground(new java.awt.Color(42, 135, 235));
        addItemItemListButton.setText("Item List");
        addItemItemListButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        addItemItemListButton.setFocusPainted(false);
        addItemItemListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemItemListButtonActionPerformed(evt);
            }
        });

        addItemAddItemButton.setBackground(new java.awt.Color(255, 255, 255));
        addItemAddItemButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addItemAddItemButton.setForeground(new java.awt.Color(42, 135, 235));
        addItemAddItemButton.setText("Add Item");
        addItemAddItemButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        addItemAddItemButton.setFocusPainted(false);
        addItemAddItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemAddItemButtonActionPerformed(evt);
            }
        });

        addItemClearButton.setBackground(new java.awt.Color(255, 255, 255));
        addItemClearButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addItemClearButton.setForeground(new java.awt.Color(42, 135, 235));
        addItemClearButton.setText("Clear");
        addItemClearButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        addItemClearButton.setFocusPainted(false);
        addItemClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemClearButtonActionPerformed(evt);
            }
        });

        addItemDemoButton.setBackground(new java.awt.Color(255, 255, 255));
        addItemDemoButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addItemDemoButton.setForeground(new java.awt.Color(42, 135, 235));
        addItemDemoButton.setText("Demo");
        addItemDemoButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        addItemDemoButton.setFocusPainted(false);
        addItemDemoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemDemoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addItemPanelLayout = new javax.swing.GroupLayout(addItemPanel);
        addItemPanel.setLayout(addItemPanelLayout);
        addItemPanelLayout.setHorizontalGroup(
            addItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addItemPanelLayout.createSequentialGroup()
                .addGap(231, 231, 231)
                .addGroup(addItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(addItemCategoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addItemReorderLevelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addItemDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addItemUnitsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(addItemNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addItemNameTextField)
                    .addComponent(addItemUnitsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addItemDescriptionScrollPane)
                    .addComponent(addItemReorderLevelTextField)
                    .addComponent(addItemCategoryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(addItemPanelLayout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(addItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addItemPanelLayout.createSequentialGroup()
                        .addComponent(addItemDemoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(addItemPanelLayout.createSequentialGroup()
                        .addComponent(addItemItemListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addItemClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addItemAddItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))))
        );
        addItemPanelLayout.setVerticalGroup(
            addItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addItemPanelLayout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(addItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addItemNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addItemUnitsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addItemUnitsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addItemPanelLayout.createSequentialGroup()
                        .addComponent(addItemDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(addItemDescriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(addItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addItemCategoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addItemCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addItemReorderLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addItemReorderLevelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addItemDemoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(addItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addItemItemListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addItemAddItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addItemClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        itemTabbedPane.addTab("Add Item", addItemPanel);

        editItemPanel.setBackground(new java.awt.Color(255, 255, 255));
        editItemPanel.setForeground(new java.awt.Color(42, 135, 235));

        editItemNameLabel.setForeground(new java.awt.Color(42, 135, 235));
        editItemNameLabel.setText("Item Name");

        editItemNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        editItemNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        editItemUnitsLabel.setForeground(new java.awt.Color(42, 135, 235));
        editItemUnitsLabel.setText("Item Units");

        editItemDescriptionLabel.setForeground(new java.awt.Color(42, 135, 235));
        editItemDescriptionLabel.setText("Item Description");

        editItemCategoryLabel.setForeground(new java.awt.Color(42, 135, 235));
        editItemCategoryLabel.setText("Item Category");

        editItemReorderLevelLabel.setForeground(new java.awt.Color(42, 135, 235));
        editItemReorderLevelLabel.setText("Re-order Level");

        editItemReorderLevelTextField.setForeground(new java.awt.Color(42, 135, 235));
        editItemReorderLevelTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        editItemDescriptionScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        editItemDescriptionTextArea.setColumns(20);
        editItemDescriptionTextArea.setForeground(new java.awt.Color(42, 135, 235));
        editItemDescriptionTextArea.setRows(5);
        editItemDescriptionScrollPane.setViewportView(editItemDescriptionTextArea);

        editItemItemListButton.setBackground(new java.awt.Color(255, 255, 255));
        editItemItemListButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        editItemItemListButton.setForeground(new java.awt.Color(42, 135, 235));
        editItemItemListButton.setText("Item List");
        editItemItemListButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        editItemItemListButton.setFocusPainted(false);
        editItemItemListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editItemItemListButtonActionPerformed(evt);
            }
        });

        editItemEditItemButton.setBackground(new java.awt.Color(255, 255, 255));
        editItemEditItemButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        editItemEditItemButton.setForeground(new java.awt.Color(42, 135, 235));
        editItemEditItemButton.setText("Edit Item");
        editItemEditItemButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        editItemEditItemButton.setFocusPainted(false);
        editItemEditItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editItemEditItemButtonActionPerformed(evt);
            }
        });

        editItemRemoveItemButton.setBackground(new java.awt.Color(255, 255, 255));
        editItemRemoveItemButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        editItemRemoveItemButton.setForeground(new java.awt.Color(42, 135, 235));
        editItemRemoveItemButton.setText("Remove Item");
        editItemRemoveItemButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        editItemRemoveItemButton.setFocusPainted(false);
        editItemRemoveItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editItemRemoveItemButtonActionPerformed(evt);
            }
        });

        editItemIdLabel.setForeground(new java.awt.Color(42, 135, 235));
        editItemIdLabel.setText("Item ID");

        editItemIdTextField.setEditable(false);
        editItemIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        editItemIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        editItemCategoryTextField.setEditable(false);
        editItemCategoryTextField.setForeground(new java.awt.Color(42, 135, 235));
        editItemCategoryTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        editItemUnitsTextField.setEditable(false);
        editItemUnitsTextField.setForeground(new java.awt.Color(42, 135, 235));
        editItemUnitsTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout editItemPanelLayout = new javax.swing.GroupLayout(editItemPanel);
        editItemPanel.setLayout(editItemPanelLayout);
        editItemPanelLayout.setHorizontalGroup(
            editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editItemPanelLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(editItemItemListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(editItemEditItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(editItemRemoveItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editItemPanelLayout.createSequentialGroup()
                .addContainerGap(239, Short.MAX_VALUE)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, editItemPanelLayout.createSequentialGroup()
                                .addComponent(editItemUnitsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editItemUnitsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, editItemPanelLayout.createSequentialGroup()
                                .addComponent(editItemCategoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editItemCategoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, editItemPanelLayout.createSequentialGroup()
                                .addComponent(editItemReorderLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(editItemReorderLevelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editItemPanelLayout.createSequentialGroup()
                            .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(editItemNameLabel)
                                .addComponent(editItemIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(editItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(editItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(editItemPanelLayout.createSequentialGroup()
                        .addComponent(editItemDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editItemDescriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(239, 239, 239))
        );
        editItemPanelLayout.setVerticalGroup(
            editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editItemPanelLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editItemIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editItemNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editItemUnitsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editItemUnitsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editItemPanelLayout.createSequentialGroup()
                        .addComponent(editItemDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(editItemDescriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editItemPanelLayout.createSequentialGroup()
                        .addComponent(editItemCategoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editItemReorderLevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editItemReorderLevelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(editItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editItemItemListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editItemEditItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editItemRemoveItemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))
                    .addGroup(editItemPanelLayout.createSequentialGroup()
                        .addComponent(editItemCategoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        itemTabbedPane.addTab("Edit Item", editItemPanel);

        removedItemsPanel.setBackground(new java.awt.Color(255, 255, 255));
        removedItemsPanel.setForeground(new java.awt.Color(42, 135, 235));

        removedItemsScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        removedItemsScrollPane.setBorder(null);

        removedItemsTable.setAutoCreateRowSorter(true);
        removedItemsTable.setForeground(new java.awt.Color(42, 135, 235));
        removedItemsTable.setModel(new javax.swing.table.DefaultTableModel(
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
                "Item ID", "Item Name", "Units", "Description", "Category", "Re-order Level"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
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
        removedItemsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        removedItemsTable.setFocusable(false);
        removedItemsTable.setGridColor(new java.awt.Color(42, 135, 235));
        removedItemsTable.getTableHeader().setResizingAllowed(false);
        removedItemsTable.getTableHeader().setReorderingAllowed(false);
        removedItemsScrollPane.setViewportView(removedItemsTable);
        if (removedItemsTable.getColumnModel().getColumnCount() > 0) {
            removedItemsTable.getColumnModel().getColumn(0).setResizable(false);
            removedItemsTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            removedItemsTable.getColumnModel().getColumn(1).setResizable(false);
            removedItemsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            removedItemsTable.getColumnModel().getColumn(2).setResizable(false);
            removedItemsTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            removedItemsTable.getColumnModel().getColumn(3).setResizable(false);
            removedItemsTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            removedItemsTable.getColumnModel().getColumn(4).setResizable(false);
            removedItemsTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            removedItemsTable.getColumnModel().getColumn(5).setResizable(false);
            removedItemsTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        removedItemsItemListButton.setBackground(new java.awt.Color(255, 255, 255));
        removedItemsItemListButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        removedItemsItemListButton.setForeground(new java.awt.Color(42, 135, 235));
        removedItemsItemListButton.setText("Item List");
        removedItemsItemListButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        removedItemsItemListButton.setFocusPainted(false);
        removedItemsItemListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removedItemsItemListButtonActionPerformed(evt);
            }
        });

        removedItemListItemIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        removedItemListItemIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        removedItemListItemIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        removedItemListItemNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        removedItemListItemNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        removedItemListItemNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        removedItemListItemCategoryComboBox.setForeground(new java.awt.Color(42, 135, 235));
        removedItemListItemCategoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Category" }));
        removedItemListItemCategoryComboBox.setBorder(null);

        removedItemListSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        removedItemListSearchButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        removedItemListSearchButton.setForeground(new java.awt.Color(42, 135, 235));
        removedItemListSearchButton.setText("Search");
        removedItemListSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        removedItemListSearchButton.setFocusPainted(false);
        removedItemListSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removedItemListSearchButtonActionPerformed(evt);
            }
        });

        removedItemListRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        removedItemListRefreshButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        removedItemListRefreshButton.setForeground(new java.awt.Color(42, 135, 235));
        removedItemListRefreshButton.setText("Refresh");
        removedItemListRefreshButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        removedItemListRefreshButton.setFocusPainted(false);
        removedItemListRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removedItemListRefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout removedItemsPanelLayout = new javax.swing.GroupLayout(removedItemsPanel);
        removedItemsPanel.setLayout(removedItemsPanelLayout);
        removedItemsPanelLayout.setHorizontalGroup(
            removedItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(removedItemsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(removedItemsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(removedItemsPanelLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(removedItemListItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removedItemListItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removedItemListItemCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(removedItemListSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removedItemListRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, removedItemsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(removedItemsItemListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        removedItemsPanelLayout.setVerticalGroup(
            removedItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(removedItemsPanelLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(removedItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(removedItemsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(removedItemListItemNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(removedItemListItemCategoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(removedItemListSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(removedItemListRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(removedItemListItemIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(removedItemsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(removedItemsItemListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        itemTabbedPane.addTab("Removed Items", removedItemsPanel);

        itemsPanel.add(itemTabbedPane, java.awt.BorderLayout.CENTER);
        itemTabbedPane.getAccessibleContext().setAccessibleName("");

        actionPanel.add(itemsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 580));

        suppliersPanel.setBackground(new java.awt.Color(255, 255, 255));
        suppliersPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        suppliersPanel.setMaximumSize(new java.awt.Dimension(781, 578));
        suppliersPanel.setMinimumSize(new java.awt.Dimension(781, 578));
        suppliersPanel.setPreferredSize(new java.awt.Dimension(797, 591));
        suppliersPanel.setLayout(new java.awt.BorderLayout());

        supplierTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        supplierTabbedPane.setForeground(new java.awt.Color(42, 135, 235));
        supplierTabbedPane.setFocusable(false);
        supplierTabbedPane.setPreferredSize(new java.awt.Dimension(786, 587));

        supplierListPanel.setBackground(new java.awt.Color(255, 255, 255));
        supplierListPanel.setForeground(new java.awt.Color(42, 135, 235));

        supplierListScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        supplierListScrollPane.setBorder(null);

        supplierListTable.setAutoCreateRowSorter(true);
        supplierListTable.setForeground(new java.awt.Color(42, 135, 235));
        supplierListTable.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Supplier ID", "Supplier Name", "Contact No.", "Address", "Email", "Description", "Register Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        supplierListTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        supplierListTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        supplierListTable.setFocusable(false);
        supplierListTable.setGridColor(new java.awt.Color(42, 135, 235));
        supplierListTable.getTableHeader().setResizingAllowed(false);
        supplierListTable.getTableHeader().setReorderingAllowed(false);
        supplierListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplierListTableMouseClicked(evt);
            }
        });
        supplierListScrollPane.setViewportView(supplierListTable);
        if (supplierListTable.getColumnModel().getColumnCount() > 0) {
            supplierListTable.getColumnModel().getColumn(0).setResizable(false);
            supplierListTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            supplierListTable.getColumnModel().getColumn(1).setResizable(false);
            supplierListTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            supplierListTable.getColumnModel().getColumn(2).setResizable(false);
            supplierListTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            supplierListTable.getColumnModel().getColumn(3).setResizable(false);
            supplierListTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            supplierListTable.getColumnModel().getColumn(4).setResizable(false);
            supplierListTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            supplierListTable.getColumnModel().getColumn(5).setResizable(false);
            supplierListTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            supplierListTable.getColumnModel().getColumn(6).setResizable(false);
            supplierListTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        supplierListSupplierIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        supplierListSupplierIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        supplierListSupplierIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        supplierListSupplierNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        supplierListSupplierNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        supplierListSupplierNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        supplierListSupplierContactNumberTextField.setForeground(new java.awt.Color(42, 135, 235));
        supplierListSupplierContactNumberTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        supplierListSupplierContactNumberTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contact No.", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        supplierListSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        supplierListSearchButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        supplierListSearchButton.setForeground(new java.awt.Color(42, 135, 235));
        supplierListSearchButton.setText("Search");
        supplierListSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        supplierListSearchButton.setFocusPainted(false);
        supplierListSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierListSearchButtonActionPerformed(evt);
            }
        });

        supplierListRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        supplierListRefreshButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        supplierListRefreshButton.setForeground(new java.awt.Color(42, 135, 235));
        supplierListRefreshButton.setText("Refresh");
        supplierListRefreshButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        supplierListRefreshButton.setFocusPainted(false);
        supplierListRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierListRefreshButtonActionPerformed(evt);
            }
        });

        supplierListAddSupplierButton.setBackground(new java.awt.Color(255, 255, 255));
        supplierListAddSupplierButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        supplierListAddSupplierButton.setForeground(new java.awt.Color(42, 135, 235));
        supplierListAddSupplierButton.setText("Add Supplier");
        supplierListAddSupplierButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        supplierListAddSupplierButton.setFocusPainted(false);
        supplierListAddSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierListAddSupplierButtonActionPerformed(evt);
            }
        });

        supplierListRemovedSuppliersButton.setBackground(new java.awt.Color(255, 255, 255));
        supplierListRemovedSuppliersButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        supplierListRemovedSuppliersButton.setForeground(new java.awt.Color(42, 135, 235));
        supplierListRemovedSuppliersButton.setText("Show Removed Suppliers");
        supplierListRemovedSuppliersButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        supplierListRemovedSuppliersButton.setFocusPainted(false);
        supplierListRemovedSuppliersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierListRemovedSuppliersButtonActionPerformed(evt);
            }
        });

        supplierListEditSupplierButton.setBackground(new java.awt.Color(255, 255, 255));
        supplierListEditSupplierButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        supplierListEditSupplierButton.setForeground(new java.awt.Color(42, 135, 235));
        supplierListEditSupplierButton.setText("Edit Supplier");
        supplierListEditSupplierButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        supplierListEditSupplierButton.setFocusPainted(false);
        supplierListEditSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierListEditSupplierButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout supplierListPanelLayout = new javax.swing.GroupLayout(supplierListPanel);
        supplierListPanel.setLayout(supplierListPanelLayout);
        supplierListPanelLayout.setHorizontalGroup(
            supplierListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierListPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(supplierListSupplierIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(supplierListSupplierNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(supplierListSupplierContactNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(supplierListSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(supplierListRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(supplierListPanelLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(supplierListAddSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(supplierListEditSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(supplierListRemovedSuppliersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(supplierListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(supplierListScrollPane)
                .addContainerGap())
        );
        supplierListPanelLayout.setVerticalGroup(
            supplierListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierListPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(supplierListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplierListSupplierContactNumberTextField)
                    .addComponent(supplierListSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierListRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierListSupplierNameTextField)
                    .addComponent(supplierListSupplierIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(supplierListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(supplierListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplierListAddSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierListRemovedSuppliersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierListEditSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        supplierTabbedPane.addTab("Supplier List", supplierListPanel);

        addSupplierPanel.setBackground(new java.awt.Color(255, 255, 255));
        addSupplierPanel.setForeground(new java.awt.Color(42, 135, 235));

        addSupplierNameLabel.setForeground(new java.awt.Color(42, 135, 235));
        addSupplierNameLabel.setText("Supplier Name");
        addSupplierNameLabel.setToolTipText("");

        addSupplierNameTextField.setForeground(new java.awt.Color(42, 135, 235));

        addSupplierAddressLabel.setForeground(new java.awt.Color(42, 135, 235));
        addSupplierAddressLabel.setText("Supplier Address");

        addSupplierTelephoneLabel.setForeground(new java.awt.Color(42, 135, 235));
        addSupplierTelephoneLabel.setText("Telephone");

        addSupplierAddressTextArea.setColumns(20);
        addSupplierAddressTextArea.setForeground(new java.awt.Color(42, 135, 235));
        addSupplierAddressTextArea.setRows(5);
        addSupplierAddressScrollPane.setViewportView(addSupplierAddressTextArea);

        addSupplierSupplierListButton.setBackground(new java.awt.Color(255, 255, 255));
        addSupplierSupplierListButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addSupplierSupplierListButton.setForeground(new java.awt.Color(42, 135, 235));
        addSupplierSupplierListButton.setText("Supplier List");
        addSupplierSupplierListButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        addSupplierSupplierListButton.setFocusPainted(false);
        addSupplierSupplierListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierSupplierListButtonActionPerformed(evt);
            }
        });

        addSupplierAddSupplierButton.setBackground(new java.awt.Color(255, 255, 255));
        addSupplierAddSupplierButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addSupplierAddSupplierButton.setForeground(new java.awt.Color(42, 135, 235));
        addSupplierAddSupplierButton.setText("Add Supplier");
        addSupplierAddSupplierButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        addSupplierAddSupplierButton.setFocusPainted(false);
        addSupplierAddSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierAddSupplierButtonActionPerformed(evt);
            }
        });

        addSupplierTelephoneTextField.setForeground(new java.awt.Color(42, 135, 235));

        addSupplierEmailLabel.setForeground(new java.awt.Color(42, 135, 235));
        addSupplierEmailLabel.setText("Email");

        addSupplierEmailTextField.setForeground(new java.awt.Color(42, 135, 235));

        addSupplierDescriptionLabel.setForeground(new java.awt.Color(42, 135, 235));
        addSupplierDescriptionLabel.setText("Description");

        addSupplierDescriptionTextArea.setColumns(20);
        addSupplierDescriptionTextArea.setForeground(new java.awt.Color(42, 135, 235));
        addSupplierDescriptionTextArea.setRows(5);
        addSupplierDescriptionScrollPane.setViewportView(addSupplierDescriptionTextArea);

        addSupplierClearButton.setBackground(new java.awt.Color(255, 255, 255));
        addSupplierClearButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addSupplierClearButton.setForeground(new java.awt.Color(42, 135, 235));
        addSupplierClearButton.setText("Clear");
        addSupplierClearButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        addSupplierClearButton.setFocusPainted(false);
        addSupplierClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierClearButtonActionPerformed(evt);
            }
        });

        addSupplierDemoButton.setBackground(new java.awt.Color(255, 255, 255));
        addSupplierDemoButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addSupplierDemoButton.setForeground(new java.awt.Color(42, 135, 235));
        addSupplierDemoButton.setText("Demo");
        addSupplierDemoButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        addSupplierDemoButton.setFocusPainted(false);
        addSupplierDemoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierDemoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addSupplierPanelLayout = new javax.swing.GroupLayout(addSupplierPanel);
        addSupplierPanel.setLayout(addSupplierPanelLayout);
        addSupplierPanelLayout.setHorizontalGroup(
            addSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addSupplierPanelLayout.createSequentialGroup()
                .addGroup(addSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(addSupplierPanelLayout.createSequentialGroup()
                        .addGroup(addSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addSupplierPanelLayout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addGroup(addSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addSupplierEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addSupplierTelephoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addSupplierAddressLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addSupplierDescriptionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(addSupplierNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addSupplierPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addSupplierDemoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)))
                .addGroup(addSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(addSupplierNameTextField)
                    .addComponent(addSupplierDescriptionScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(addSupplierTelephoneTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addSupplierEmailTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addSupplierAddressScrollPane, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addSupplierPanelLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(addSupplierSupplierListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(addSupplierClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addSupplierAddSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        addSupplierPanelLayout.setVerticalGroup(
            addSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addSupplierPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(addSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSupplierNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSupplierNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(addSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addSupplierPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(addSupplierAddressScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(addSupplierPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(addSupplierAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(addSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSupplierTelephoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSupplierTelephoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSupplierEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSupplierEmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addSupplierDescriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addSupplierPanelLayout.createSequentialGroup()
                        .addComponent(addSupplierDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(addSupplierDemoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addGroup(addSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSupplierSupplierListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSupplierAddSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addSupplierClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );

        supplierTabbedPane.addTab("Add Supplier", addSupplierPanel);

        editSupplierPanel.setBackground(new java.awt.Color(255, 255, 255));
        editSupplierPanel.setForeground(new java.awt.Color(42, 135, 235));

        editSupplierSupplierListButton.setBackground(new java.awt.Color(255, 255, 255));
        editSupplierSupplierListButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        editSupplierSupplierListButton.setForeground(new java.awt.Color(42, 135, 235));
        editSupplierSupplierListButton.setText("Supplier List");
        editSupplierSupplierListButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        editSupplierSupplierListButton.setFocusPainted(false);
        editSupplierSupplierListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSupplierSupplierListButtonActionPerformed(evt);
            }
        });

        editSupplierEditSupplierButton.setBackground(new java.awt.Color(255, 255, 255));
        editSupplierEditSupplierButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        editSupplierEditSupplierButton.setForeground(new java.awt.Color(42, 135, 235));
        editSupplierEditSupplierButton.setText("Edit Supplier");
        editSupplierEditSupplierButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        editSupplierEditSupplierButton.setFocusPainted(false);
        editSupplierEditSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSupplierEditSupplierButtonActionPerformed(evt);
            }
        });

        editSupplierRemoveSupplierButton.setBackground(new java.awt.Color(255, 255, 255));
        editSupplierRemoveSupplierButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        editSupplierRemoveSupplierButton.setForeground(new java.awt.Color(42, 135, 235));
        editSupplierRemoveSupplierButton.setText("Remove Supplier");
        editSupplierRemoveSupplierButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        editSupplierRemoveSupplierButton.setFocusPainted(false);
        editSupplierRemoveSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSupplierRemoveSupplierButtonActionPerformed(evt);
            }
        });

        editSupplierNameLabel.setForeground(new java.awt.Color(42, 135, 235));
        editSupplierNameLabel.setText("Supplier Name");
        editSupplierNameLabel.setToolTipText("");

        editSupplierNameTextField.setForeground(new java.awt.Color(42, 135, 235));

        editSupplierAddressLabel.setForeground(new java.awt.Color(42, 135, 235));
        editSupplierAddressLabel.setText("Supplier Address");

        editSupplierAddressTextArea.setColumns(20);
        editSupplierAddressTextArea.setForeground(new java.awt.Color(42, 135, 235));
        editSupplierAddressTextArea.setRows(5);
        editSupplierAddressScrollPane.setViewportView(editSupplierAddressTextArea);

        editSupplierTelephoneTextField.setForeground(new java.awt.Color(42, 135, 235));

        editSupplierTelephoneLabel.setForeground(new java.awt.Color(42, 135, 235));
        editSupplierTelephoneLabel.setText("Telephone");

        editSupplierEmailTextField.setForeground(new java.awt.Color(42, 135, 235));

        editSupplierEmailLabel.setForeground(new java.awt.Color(42, 135, 235));
        editSupplierEmailLabel.setText("Email");

        editSupplierDescriptionLabel.setForeground(new java.awt.Color(42, 135, 235));
        editSupplierDescriptionLabel.setText("Description");

        editSupplierDescriptionTextArea.setColumns(20);
        editSupplierDescriptionTextArea.setForeground(new java.awt.Color(42, 135, 235));
        editSupplierDescriptionTextArea.setRows(5);
        editSupplierDescriptionScrollPane.setViewportView(editSupplierDescriptionTextArea);

        editSupplierIdLabel.setForeground(new java.awt.Color(42, 135, 235));
        editSupplierIdLabel.setText("Supplier ID");
        editSupplierIdLabel.setToolTipText("");

        editSupplierIdTextField.setEditable(false);
        editSupplierIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        editSupplierIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout editSupplierPanelLayout = new javax.swing.GroupLayout(editSupplierPanel);
        editSupplierPanel.setLayout(editSupplierPanelLayout);
        editSupplierPanelLayout.setHorizontalGroup(
            editSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editSupplierPanelLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(editSupplierSupplierListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(editSupplierEditSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(editSupplierRemoveSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
            .addGroup(editSupplierPanelLayout.createSequentialGroup()
                .addGap(239, 239, 239)
                .addGroup(editSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editSupplierDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editSupplierEmailLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editSupplierTelephoneLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editSupplierAddressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editSupplierNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editSupplierIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editSupplierAddressScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addComponent(editSupplierDescriptionScrollPane)
                    .addComponent(editSupplierEmailTextField)
                    .addComponent(editSupplierTelephoneTextField)
                    .addComponent(editSupplierNameTextField)
                    .addComponent(editSupplierIdTextField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editSupplierPanelLayout.setVerticalGroup(
            editSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editSupplierPanelLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(editSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editSupplierIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editSupplierIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editSupplierNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editSupplierNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editSupplierAddressScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editSupplierAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editSupplierTelephoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editSupplierTelephoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editSupplierEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editSupplierEmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editSupplierDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editSupplierDescriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(editSupplierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editSupplierSupplierListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editSupplierEditSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editSupplierRemoveSupplierButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        supplierTabbedPane.addTab("Edit Supplier", editSupplierPanel);

        removedSuppliersPanel.setBackground(new java.awt.Color(255, 255, 255));
        removedSuppliersPanel.setForeground(new java.awt.Color(42, 135, 235));

        removedSuppliersScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        removedSuppliersScrollPane.setBorder(null);

        removedSuppliersTable.setAutoCreateRowSorter(true);
        removedSuppliersTable.setForeground(new java.awt.Color(42, 135, 235));
        removedSuppliersTable.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Supplier ID", "Supplier Name", "Address", "Email", "Contact No.", "Description", "Register Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        removedSuppliersTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        removedSuppliersTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        removedSuppliersTable.setFocusable(false);
        removedSuppliersTable.setGridColor(new java.awt.Color(42, 135, 235));
        removedSuppliersTable.getTableHeader().setResizingAllowed(false);
        removedSuppliersTable.getTableHeader().setReorderingAllowed(false);
        removedSuppliersScrollPane.setViewportView(removedSuppliersTable);
        if (removedSuppliersTable.getColumnModel().getColumnCount() > 0) {
            removedSuppliersTable.getColumnModel().getColumn(0).setResizable(false);
            removedSuppliersTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            removedSuppliersTable.getColumnModel().getColumn(1).setResizable(false);
            removedSuppliersTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            removedSuppliersTable.getColumnModel().getColumn(2).setResizable(false);
            removedSuppliersTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            removedSuppliersTable.getColumnModel().getColumn(2).setHeaderValue("Address");
            removedSuppliersTable.getColumnModel().getColumn(3).setResizable(false);
            removedSuppliersTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            removedSuppliersTable.getColumnModel().getColumn(3).setHeaderValue("Email");
            removedSuppliersTable.getColumnModel().getColumn(4).setResizable(false);
            removedSuppliersTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            removedSuppliersTable.getColumnModel().getColumn(4).setHeaderValue("Contact No.");
            removedSuppliersTable.getColumnModel().getColumn(5).setResizable(false);
            removedSuppliersTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            removedSuppliersTable.getColumnModel().getColumn(5).setHeaderValue("Description");
            removedSuppliersTable.getColumnModel().getColumn(6).setResizable(false);
            removedSuppliersTable.getColumnModel().getColumn(6).setPreferredWidth(100);
            removedSuppliersTable.getColumnModel().getColumn(6).setHeaderValue("Register Date");
        }

        removedSuppliersSupplierIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        removedSuppliersSupplierIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        removedSuppliersSupplierIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        removedSuppliersSupplierNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        removedSuppliersSupplierNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        removedSuppliersSupplierNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        removedSuppliersSupplierContactNumberTextField.setForeground(new java.awt.Color(42, 135, 235));
        removedSuppliersSupplierContactNumberTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        removedSuppliersSupplierContactNumberTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Contact No.", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        removedSuppliersSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        removedSuppliersSearchButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        removedSuppliersSearchButton.setForeground(new java.awt.Color(42, 135, 235));
        removedSuppliersSearchButton.setText("Search");
        removedSuppliersSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        removedSuppliersSearchButton.setFocusPainted(false);
        removedSuppliersSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removedSuppliersSearchButtonActionPerformed(evt);
            }
        });

        removedSuppliersRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        removedSuppliersRefreshButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        removedSuppliersRefreshButton.setForeground(new java.awt.Color(42, 135, 235));
        removedSuppliersRefreshButton.setText("Refresh");
        removedSuppliersRefreshButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        removedSuppliersRefreshButton.setFocusPainted(false);
        removedSuppliersRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removedSuppliersRefreshButtonActionPerformed(evt);
            }
        });

        removedSuppliersSupplierListButton.setBackground(new java.awt.Color(255, 255, 255));
        removedSuppliersSupplierListButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        removedSuppliersSupplierListButton.setForeground(new java.awt.Color(42, 135, 235));
        removedSuppliersSupplierListButton.setText("Supplier List");
        removedSuppliersSupplierListButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        removedSuppliersSupplierListButton.setFocusPainted(false);
        removedSuppliersSupplierListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removedSuppliersSupplierListButtonActionPerformed(evt);
            }
        });

        removedSuppliersAddSupplierBackButton.setBackground(new java.awt.Color(255, 255, 255));
        removedSuppliersAddSupplierBackButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        removedSuppliersAddSupplierBackButton.setForeground(new java.awt.Color(42, 135, 235));
        removedSuppliersAddSupplierBackButton.setText("Add Supplier Back");
        removedSuppliersAddSupplierBackButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        removedSuppliersAddSupplierBackButton.setFocusPainted(false);

        javax.swing.GroupLayout removedSuppliersPanelLayout = new javax.swing.GroupLayout(removedSuppliersPanel);
        removedSuppliersPanel.setLayout(removedSuppliersPanelLayout);
        removedSuppliersPanelLayout.setHorizontalGroup(
            removedSuppliersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(removedSuppliersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(removedSuppliersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(removedSuppliersPanelLayout.createSequentialGroup()
                        .addComponent(removedSuppliersScrollPane)
                        .addGap(16, 16, 16))
                    .addGroup(removedSuppliersPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(removedSuppliersSupplierIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(removedSuppliersSupplierNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(removedSuppliersSupplierContactNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removedSuppliersSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(removedSuppliersRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))))
            .addGroup(removedSuppliersPanelLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(removedSuppliersSupplierListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(304, 304, 304)
                .addComponent(removedSuppliersAddSupplierBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
        );
        removedSuppliersPanelLayout.setVerticalGroup(
            removedSuppliersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(removedSuppliersPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(removedSuppliersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removedSuppliersSupplierContactNumberTextField)
                    .addComponent(removedSuppliersSupplierNameTextField)
                    .addComponent(removedSuppliersSupplierIdTextField)
                    .addComponent(removedSuppliersSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removedSuppliersRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(removedSuppliersScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(removedSuppliersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removedSuppliersSupplierListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removedSuppliersAddSupplierBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        supplierTabbedPane.addTab("Removed Suppliers", removedSuppliersPanel);

        suppliersPanel.add(supplierTabbedPane, java.awt.BorderLayout.CENTER);
        supplierTabbedPane.getAccessibleContext().setAccessibleName("");

        actionPanel.add(suppliersPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 580));

        categoriesPanel.setBackground(new java.awt.Color(255, 255, 255));

        stockCategoriesScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        stockCategoriesScrollPane.setBorder(null);

        stockCategoriesTable.setAutoCreateRowSorter(true);
        stockCategoriesTable.setForeground(new java.awt.Color(42, 135, 235));
        stockCategoriesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Category ID", "Category Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stockCategoriesTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        stockCategoriesTable.setFocusable(false);
        stockCategoriesTable.setGridColor(new java.awt.Color(42, 135, 235));
        stockCategoriesTable.getTableHeader().setResizingAllowed(false);
        stockCategoriesTable.getTableHeader().setReorderingAllowed(false);
        stockCategoriesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockCategoriesTableMouseClicked(evt);
            }
        });
        stockCategoriesScrollPane.setViewportView(stockCategoriesTable);
        if (stockCategoriesTable.getColumnModel().getColumnCount() > 0) {
            stockCategoriesTable.getColumnModel().getColumn(0).setResizable(false);
            stockCategoriesTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            stockCategoriesTable.getColumnModel().getColumn(1).setResizable(false);
            stockCategoriesTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        categoriesAddCategoryButton.setBackground(new java.awt.Color(255, 255, 255));
        categoriesAddCategoryButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        categoriesAddCategoryButton.setForeground(new java.awt.Color(42, 135, 235));
        categoriesAddCategoryButton.setText("Add Category");
        categoriesAddCategoryButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        categoriesAddCategoryButton.setFocusPainted(false);
        categoriesAddCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriesAddCategoryButtonActionPerformed(evt);
            }
        });

        categoriesRemoveCategoryButton.setBackground(new java.awt.Color(255, 255, 255));
        categoriesRemoveCategoryButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        categoriesRemoveCategoryButton.setForeground(new java.awt.Color(42, 135, 235));
        categoriesRemoveCategoryButton.setText("Remove Category");
        categoriesRemoveCategoryButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        categoriesRemoveCategoryButton.setFocusPainted(false);
        categoriesRemoveCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriesRemoveCategoryButtonActionPerformed(evt);
            }
        });

        categoriesEditCategoryButton.setBackground(new java.awt.Color(255, 255, 255));
        categoriesEditCategoryButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        categoriesEditCategoryButton.setForeground(new java.awt.Color(42, 135, 235));
        categoriesEditCategoryButton.setText("Edit Category");
        categoriesEditCategoryButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        categoriesEditCategoryButton.setFocusPainted(false);
        categoriesEditCategoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriesEditCategoryButtonActionPerformed(evt);
            }
        });

        categoriesCategoryIdLabel.setForeground(new java.awt.Color(42, 135, 235));
        categoriesCategoryIdLabel.setText("Category ID");

        categoriesCategoryIdTextField.setEditable(false);
        categoriesCategoryIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        categoriesCategoryIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        categoriesCategoryNameLabel.setForeground(new java.awt.Color(42, 135, 235));
        categoriesCategoryNameLabel.setText("Category Name");

        categoriesCategoryNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        categoriesCategoryNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout categoriesPanelLayout = new javax.swing.GroupLayout(categoriesPanel);
        categoriesPanel.setLayout(categoriesPanelLayout);
        categoriesPanelLayout.setHorizontalGroup(
            categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, categoriesPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(stockCategoriesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(categoriesPanelLayout.createSequentialGroup()
                        .addComponent(categoriesCategoryIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(categoriesCategoryIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(categoriesPanelLayout.createSequentialGroup()
                        .addComponent(categoriesCategoryNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoriesAddCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoriesCategoryNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoriesEditCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoriesRemoveCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(70, 70, 70))
        );
        categoriesPanelLayout.setVerticalGroup(
            categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoriesPanelLayout.createSequentialGroup()
                .addGroup(categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(categoriesPanelLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categoriesCategoryIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoriesCategoryIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categoriesCategoryNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categoriesCategoryNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addComponent(categoriesAddCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(categoriesEditCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(categoriesRemoveCategoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(categoriesPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(stockCategoriesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        actionPanel.add(categoriesPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 770, 560));

        accountPanel.setBackground(new java.awt.Color(255, 255, 255));
        accountPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        accountPanel.setMaximumSize(new java.awt.Dimension(781, 578));
        accountPanel.setMinimumSize(new java.awt.Dimension(781, 578));
        accountPanel.setName(""); // NOI18N
        accountPanel.setPreferredSize(new java.awt.Dimension(797, 591));

        accountDepositLabel.setBackground(new java.awt.Color(255, 255, 255));
        accountDepositLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        accountDepositLabel.setForeground(new java.awt.Color(42, 135, 235));
        accountDepositLabel.setText("Monthly Deposit (Rs)");

        accountBalanceLabel.setBackground(new java.awt.Color(255, 255, 255));
        accountBalanceLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        accountBalanceLabel.setForeground(new java.awt.Color(42, 135, 235));
        accountBalanceLabel.setText("Balance (Rs)");

        accountDepositTextField.setEditable(false);
        accountDepositTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        accountDepositTextField.setForeground(new java.awt.Color(42, 135, 235));
        accountDepositTextField.setMargin(new java.awt.Insets(2, 40, 2, 2));

        accountBalanceTextField.setEditable(false);
        accountBalanceTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        accountBalanceTextField.setForeground(new java.awt.Color(42, 135, 235));
        accountBalanceTextField.setMargin(new java.awt.Insets(2, 40, 2, 2));

        accountRequestCreditButton.setBackground(new java.awt.Color(255, 255, 255));
        accountRequestCreditButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        accountRequestCreditButton.setForeground(new java.awt.Color(42, 135, 235));
        accountRequestCreditButton.setText("Request Credit");
        accountRequestCreditButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        accountRequestCreditButton.setFocusPainted(false);
        accountRequestCreditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountRequestCreditButtonActionPerformed(evt);
            }
        });

        accountRequestAmountLabel.setBackground(new java.awt.Color(255, 255, 255));
        accountRequestAmountLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        accountRequestAmountLabel.setForeground(new java.awt.Color(42, 135, 235));
        accountRequestAmountLabel.setText("Request Amount (Rs)");

        accountRequestAmountTextField.setForeground(new java.awt.Color(42, 135, 235));
        accountRequestAmountTextField.setMargin(new java.awt.Insets(2, 40, 2, 2));

        javax.swing.GroupLayout accountPanelLayout = new javax.swing.GroupLayout(accountPanel);
        accountPanel.setLayout(accountPanelLayout);
        accountPanelLayout.setHorizontalGroup(
            accountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountPanelLayout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addGroup(accountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(accountDepositLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(accountRequestAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(accountBalanceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(accountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(accountRequestCreditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(accountDepositTextField)
                    .addComponent(accountBalanceTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(accountRequestAmountTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                .addContainerGap(253, Short.MAX_VALUE))
        );
        accountPanelLayout.setVerticalGroup(
            accountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountPanelLayout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addGroup(accountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accountDepositLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(accountPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(accountDepositTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(accountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accountBalanceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountBalanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(99, 99, 99)
                .addGroup(accountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accountRequestAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountRequestAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(accountRequestCreditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        actionPanel.add(accountPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 580));

        userPanel.setBackground(new java.awt.Color(255, 255, 255));
        userPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        userPanel.setPreferredSize(new java.awt.Dimension(797, 591));
        userPanel.setLayout(new java.awt.BorderLayout());

        userTabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        userTabbedPane.setForeground(new java.awt.Color(42, 135, 235));

        userListPanel.setBackground(new java.awt.Color(255, 255, 255));
        userListPanel.setForeground(new java.awt.Color(42, 135, 235));

        userListScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        userListScrollPane.setBorder(null);

        userListTable.setAutoCreateRowSorter(true);
        userListTable.setForeground(new java.awt.Color(42, 135, 235));
        userListTable.setModel(new javax.swing.table.DefaultTableModel(
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
                "User ID", "Employee ID", "Full Name", "Username", "Password", "Reg Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
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
        userListTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        userListTable.setFocusable(false);
        userListTable.setGridColor(new java.awt.Color(42, 135, 235));
        userListTable.getTableHeader().setResizingAllowed(false);
        userListTable.getTableHeader().setReorderingAllowed(false);
        userListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userListTableMouseClicked(evt);
            }
        });
        userListScrollPane.setViewportView(userListTable);
        if (userListTable.getColumnModel().getColumnCount() > 0) {
            userListTable.getColumnModel().getColumn(0).setResizable(false);
            userListTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            userListTable.getColumnModel().getColumn(1).setResizable(false);
            userListTable.getColumnModel().getColumn(2).setResizable(false);
            userListTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            userListTable.getColumnModel().getColumn(3).setResizable(false);
            userListTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            userListTable.getColumnModel().getColumn(4).setResizable(false);
            userListTable.getColumnModel().getColumn(5).setResizable(false);
            userListTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        userListUserIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        userListUserIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        userListUserIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        userListUserNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        userListUserNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        userListUserNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        userListSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        userListSearchButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        userListSearchButton.setForeground(new java.awt.Color(42, 135, 235));
        userListSearchButton.setText("Search");
        userListSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        userListSearchButton.setFocusPainted(false);
        userListSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userListSearchButtonActionPerformed(evt);
            }
        });

        userListRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        userListRefreshButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        userListRefreshButton.setForeground(new java.awt.Color(42, 135, 235));
        userListRefreshButton.setText("Refresh");
        userListRefreshButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        userListRefreshButton.setFocusPainted(false);
        userListRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userListRefreshButtonActionPerformed(evt);
            }
        });

        userListAddUserButton.setBackground(new java.awt.Color(255, 255, 255));
        userListAddUserButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        userListAddUserButton.setForeground(new java.awt.Color(42, 135, 235));
        userListAddUserButton.setText("Add User");
        userListAddUserButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        userListAddUserButton.setFocusPainted(false);
        userListAddUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userListAddUserButtonActionPerformed(evt);
            }
        });

        userListEditUserButton.setBackground(new java.awt.Color(255, 255, 255));
        userListEditUserButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        userListEditUserButton.setForeground(new java.awt.Color(42, 135, 235));
        userListEditUserButton.setText("Edit User");
        userListEditUserButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        userListEditUserButton.setFocusPainted(false);
        userListEditUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userListEditUserButtonActionPerformed(evt);
            }
        });

        userListRemovedUsersButton.setBackground(new java.awt.Color(255, 255, 255));
        userListRemovedUsersButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        userListRemovedUsersButton.setForeground(new java.awt.Color(42, 135, 235));
        userListRemovedUsersButton.setText("Show Removed Users");
        userListRemovedUsersButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        userListRemovedUsersButton.setFocusPainted(false);
        userListRemovedUsersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userListRemovedUsersButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout userListPanelLayout = new javax.swing.GroupLayout(userListPanel);
        userListPanel.setLayout(userListPanelLayout);
        userListPanelLayout.setHorizontalGroup(
            userListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userListScrollPane)
                .addContainerGap())
            .addGroup(userListPanelLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(userListAddUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(userListEditUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(userListRemovedUsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(userListPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(userListUserIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(userListUserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userListSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(userListRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );
        userListPanelLayout.setVerticalGroup(
            userListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userListPanelLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(userListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(userListRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(userListSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(userListUserIdTextField)
                        .addComponent(userListUserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(userListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(userListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userListAddUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userListEditUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userListRemovedUsersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        userTabbedPane.addTab("User List", userListPanel);

        addUserPanel.setBackground(new java.awt.Color(255, 255, 255));
        addUserPanel.setForeground(new java.awt.Color(42, 135, 235));

        addUserUsernameLabel.setForeground(new java.awt.Color(42, 135, 235));
        addUserUsernameLabel.setText("Username");

        addUserUsernameTextField.setForeground(new java.awt.Color(42, 135, 235));
        addUserUsernameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        addUserFullNameLabel.setForeground(new java.awt.Color(42, 135, 235));
        addUserFullNameLabel.setText("Full Name");

        addUserFullNameTextArea.setColumns(20);
        addUserFullNameTextArea.setForeground(new java.awt.Color(42, 135, 235));
        addUserFullNameTextArea.setRows(5);
        addUserFullNameScrollPane.setViewportView(addUserFullNameTextArea);

        addUserUserListButton.setBackground(new java.awt.Color(255, 255, 255));
        addUserUserListButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addUserUserListButton.setForeground(new java.awt.Color(42, 135, 235));
        addUserUserListButton.setText("User List");
        addUserUserListButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        addUserUserListButton.setFocusPainted(false);
        addUserUserListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserUserListButtonActionPerformed(evt);
            }
        });

        addUserAddUserButton.setBackground(new java.awt.Color(255, 255, 255));
        addUserAddUserButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addUserAddUserButton.setForeground(new java.awt.Color(42, 135, 235));
        addUserAddUserButton.setText("Add User");
        addUserAddUserButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        addUserAddUserButton.setFocusPainted(false);
        addUserAddUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserAddUserButtonActionPerformed(evt);
            }
        });

        addUserPasswordLabel.setForeground(new java.awt.Color(42, 135, 235));
        addUserPasswordLabel.setText("Password");

        addUserPasswordTextField.setForeground(new java.awt.Color(42, 135, 235));
        addUserPasswordTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        addUserEmployeeIdLabel.setForeground(new java.awt.Color(42, 135, 235));
        addUserEmployeeIdLabel.setText("Employee ID");

        addUserEmployeeIdComboBox.setForeground(new java.awt.Color(42, 135, 235));
        addUserEmployeeIdComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Employee ID", "" }));

        addUserClearButton.setBackground(new java.awt.Color(255, 255, 255));
        addUserClearButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addUserClearButton.setForeground(new java.awt.Color(42, 135, 235));
        addUserClearButton.setText("Clear");
        addUserClearButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        addUserClearButton.setFocusPainted(false);
        addUserClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addUserPanelLayout = new javax.swing.GroupLayout(addUserPanel);
        addUserPanel.setLayout(addUserPanelLayout);
        addUserPanelLayout.setHorizontalGroup(
            addUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addUserPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(addUserUserListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addUserAddUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addUserPanelLayout.createSequentialGroup()
                .addContainerGap(213, Short.MAX_VALUE)
                .addGroup(addUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addUserPanelLayout.createSequentialGroup()
                        .addComponent(addUserEmployeeIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(addUserEmployeeIdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addUserPanelLayout.createSequentialGroup()
                        .addGroup(addUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(addUserUsernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addUserFullNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addUserPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(addUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(addUserFullNameScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                .addComponent(addUserUsernameTextField)
                                .addComponent(addUserPasswordTextField))
                            .addComponent(addUserClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(207, 207, 207))
        );
        addUserPanelLayout.setVerticalGroup(
            addUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addUserPanelLayout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addGroup(addUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserEmployeeIdComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserEmployeeIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addUserFullNameScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserFullNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserUsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117)
                .addGroup(addUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addUserUserListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserAddUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addUserClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );

        userTabbedPane.addTab("Add User", addUserPanel);

        editUserPanel.setBackground(new java.awt.Color(255, 255, 255));
        editUserPanel.setForeground(new java.awt.Color(42, 135, 235));

        editUserUserListButton.setBackground(new java.awt.Color(255, 255, 255));
        editUserUserListButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        editUserUserListButton.setForeground(new java.awt.Color(42, 135, 235));
        editUserUserListButton.setText("User List");
        editUserUserListButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        editUserUserListButton.setFocusPainted(false);
        editUserUserListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUserUserListButtonActionPerformed(evt);
            }
        });

        editUserEditUserButton.setBackground(new java.awt.Color(255, 255, 255));
        editUserEditUserButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        editUserEditUserButton.setForeground(new java.awt.Color(42, 135, 235));
        editUserEditUserButton.setText("Edit User");
        editUserEditUserButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        editUserEditUserButton.setFocusPainted(false);
        editUserEditUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUserEditUserButtonActionPerformed(evt);
            }
        });

        editUserRemoveUserButton.setBackground(new java.awt.Color(255, 255, 255));
        editUserRemoveUserButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        editUserRemoveUserButton.setForeground(new java.awt.Color(42, 135, 235));
        editUserRemoveUserButton.setText("Remove User");
        editUserRemoveUserButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        editUserRemoveUserButton.setFocusPainted(false);
        editUserRemoveUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUserRemoveUserButtonActionPerformed(evt);
            }
        });

        editUserIdLabel.setForeground(new java.awt.Color(42, 135, 235));
        editUserIdLabel.setText("User ID");

        editUserIdTextField.setEditable(false);
        editUserIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        editUserIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        editUserFullNameLabel.setForeground(new java.awt.Color(42, 135, 235));
        editUserFullNameLabel.setText("Full Name");

        editUserFullNameTextArea.setColumns(20);
        editUserFullNameTextArea.setForeground(new java.awt.Color(42, 135, 235));
        editUserFullNameTextArea.setRows(5);
        addUserFullNameScrollPane2.setViewportView(editUserFullNameTextArea);

        editUserUsernameLabel.setForeground(new java.awt.Color(42, 135, 235));
        editUserUsernameLabel.setText("Username");

        editUserUsernameTextField.setForeground(new java.awt.Color(42, 135, 235));
        editUserUsernameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        editUserPasswordLabel.setForeground(new java.awt.Color(42, 135, 235));
        editUserPasswordLabel.setText("Password");

        editUserPasswordTextField.setForeground(new java.awt.Color(42, 135, 235));
        editUserPasswordTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        editEmployeeIdLabel.setForeground(new java.awt.Color(42, 135, 235));
        editEmployeeIdLabel.setText("Employee ID");

        editEmployeeIdTextField.setEditable(false);
        editEmployeeIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        editEmployeeIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout editUserPanelLayout = new javax.swing.GroupLayout(editUserPanel);
        editUserPanel.setLayout(editUserPanelLayout);
        editUserPanelLayout.setHorizontalGroup(
            editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editUserPanelLayout.createSequentialGroup()
                .addGroup(editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editUserPanelLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(editUserUserListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(editUserEditUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(editUserRemoveUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editUserPanelLayout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addGroup(editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editUserPanelLayout.createSequentialGroup()
                                .addComponent(editEmployeeIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(editEmployeeIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(editUserPanelLayout.createSequentialGroup()
                                .addComponent(editUserIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(editUserIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(editUserPanelLayout.createSequentialGroup()
                                .addGroup(editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(editUserFullNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editUserUsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editUserPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(editUserPasswordTextField)
                                        .addComponent(editUserUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(addUserFullNameScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        editUserPanelLayout.setVerticalGroup(
            editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editUserPanelLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addGroup(editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editUserIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editUserIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editEmployeeIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editEmployeeIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(editUserPanelLayout.createSequentialGroup()
                        .addComponent(editUserFullNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addGroup(editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editUserUsernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editUserUsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editUserPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editUserPasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(addUserFullNameScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addGroup(editUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editUserUserListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editUserEditUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editUserRemoveUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        userTabbedPane.addTab("Edit User", editUserPanel);

        removedUsersPanel.setBackground(new java.awt.Color(255, 255, 255));
        removedUsersPanel.setForeground(new java.awt.Color(42, 135, 235));

        removedUsersScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        removedUsersScrollPane.setBorder(null);

        removedUsersTable.setAutoCreateRowSorter(true);
        removedUsersTable.setForeground(new java.awt.Color(42, 135, 235));
        removedUsersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "User ID", "Full Name", "Username", "Reg Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        removedUsersTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        removedUsersTable.setFocusable(false);
        removedUsersTable.setGridColor(new java.awt.Color(42, 135, 235));
        removedUsersTable.getTableHeader().setResizingAllowed(false);
        removedUsersTable.getTableHeader().setReorderingAllowed(false);
        removedUsersScrollPane.setViewportView(removedUsersTable);
        if (removedUsersTable.getColumnModel().getColumnCount() > 0) {
            removedUsersTable.getColumnModel().getColumn(0).setResizable(false);
            removedUsersTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            removedUsersTable.getColumnModel().getColumn(1).setResizable(false);
            removedUsersTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            removedUsersTable.getColumnModel().getColumn(2).setResizable(false);
            removedUsersTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            removedUsersTable.getColumnModel().getColumn(3).setResizable(false);
            removedUsersTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        removedUsersUserIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        removedUsersUserIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        removedUsersUserIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        removedUsersUserNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        removedUsersUserNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        removedUsersUserNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        removedUsersSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        removedUsersSearchButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        removedUsersSearchButton.setForeground(new java.awt.Color(42, 135, 235));
        removedUsersSearchButton.setText("Search");
        removedUsersSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        removedUsersSearchButton.setFocusPainted(false);
        removedUsersSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removedUsersSearchButtonActionPerformed(evt);
            }
        });

        removedUsersRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        removedUsersRefreshButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        removedUsersRefreshButton.setForeground(new java.awt.Color(42, 135, 235));
        removedUsersRefreshButton.setText("Refresh");
        removedUsersRefreshButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        removedUsersRefreshButton.setFocusPainted(false);
        removedUsersRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removedUsersRefreshButtonActionPerformed(evt);
            }
        });

        removedUsersUserListButton.setBackground(new java.awt.Color(255, 255, 255));
        removedUsersUserListButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        removedUsersUserListButton.setForeground(new java.awt.Color(42, 135, 235));
        removedUsersUserListButton.setText("User List");
        removedUsersUserListButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        removedUsersUserListButton.setFocusPainted(false);
        removedUsersUserListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removedUsersUserListButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout removedUsersPanelLayout = new javax.swing.GroupLayout(removedUsersPanel);
        removedUsersPanel.setLayout(removedUsersPanelLayout);
        removedUsersPanelLayout.setHorizontalGroup(
            removedUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(removedUsersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(removedUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(removedUsersPanelLayout.createSequentialGroup()
                        .addComponent(removedUsersScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, removedUsersPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(removedUsersUserListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))))
            .addGroup(removedUsersPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(removedUsersUserIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removedUsersUserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(removedUsersSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(removedUsersRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        removedUsersPanelLayout.setVerticalGroup(
            removedUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(removedUsersPanelLayout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(removedUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, removedUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(removedUsersUserIdTextField)
                        .addComponent(removedUsersUserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, removedUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(removedUsersRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(removedUsersSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(removedUsersScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(removedUsersUserListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        userTabbedPane.addTab("Removed Users", removedUsersPanel);

        userPanel.add(userTabbedPane, java.awt.BorderLayout.CENTER);

        actionPanel.add(userPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 580));

        userlogPanel.setBackground(new java.awt.Color(255, 255, 255));
        userlogPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235), 2));
        userlogPanel.setForeground(new java.awt.Color(42, 135, 235));
        userlogPanel.setMaximumSize(new java.awt.Dimension(781, 578));
        userlogPanel.setMinimumSize(new java.awt.Dimension(781, 578));
        userlogPanel.setPreferredSize(new java.awt.Dimension(797, 580));

        userlogScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        userlogScrollPane.setBorder(null);

        userlogTable.setAutoCreateRowSorter(true);
        userlogTable.setForeground(new java.awt.Color(42, 135, 235));
        userlogTable.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Userlog ID", "User ID", "Username", "Transaction Type", "Transaction ID", "Transaction Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
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
        userlogTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        userlogTable.setFocusable(false);
        userlogTable.setGridColor(new java.awt.Color(42, 135, 235));
        userlogTable.getTableHeader().setResizingAllowed(false);
        userlogTable.getTableHeader().setReorderingAllowed(false);
        userlogScrollPane.setViewportView(userlogTable);
        if (userlogTable.getColumnModel().getColumnCount() > 0) {
            userlogTable.getColumnModel().getColumn(0).setResizable(false);
            userlogTable.getColumnModel().getColumn(1).setResizable(false);
            userlogTable.getColumnModel().getColumn(2).setResizable(false);
            userlogTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            userlogTable.getColumnModel().getColumn(3).setResizable(false);
            userlogTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            userlogTable.getColumnModel().getColumn(4).setResizable(false);
            userlogTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            userlogTable.getColumnModel().getColumn(5).setResizable(false);
            userlogTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        userlogUserNameTextField.setForeground(new java.awt.Color(42, 135, 235));
        userlogUserNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        userlogUserNameTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User Name", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        userlogTransactionIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        userlogTransactionIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        userlogTransactionIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transcation ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        userlogSearchButton.setBackground(new java.awt.Color(255, 255, 255));
        userlogSearchButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        userlogSearchButton.setForeground(new java.awt.Color(42, 135, 235));
        userlogSearchButton.setText("Search");
        userlogSearchButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        userlogSearchButton.setFocusPainted(false);
        userlogSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userlogSearchButtonActionPerformed(evt);
            }
        });

        userlogRefreshButton.setBackground(new java.awt.Color(255, 255, 255));
        userlogRefreshButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        userlogRefreshButton.setForeground(new java.awt.Color(42, 135, 235));
        userlogRefreshButton.setText("Refresh");
        userlogRefreshButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(42, 135, 235)));
        userlogRefreshButton.setFocusPainted(false);
        userlogRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userlogRefreshButtonActionPerformed(evt);
            }
        });

        userlogTransactionTypeComboBox.setForeground(new java.awt.Color(42, 135, 235));
        userlogTransactionTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type", "Stock Transfer", "Stock Purchase" }));

        userlogUserIdTextField.setForeground(new java.awt.Color(42, 135, 235));
        userlogUserIdTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        userlogUserIdTextField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User ID", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(42, 135, 235))); // NOI18N

        javax.swing.GroupLayout userlogPanelLayout = new javax.swing.GroupLayout(userlogPanel);
        userlogPanel.setLayout(userlogPanelLayout);
        userlogPanelLayout.setHorizontalGroup(
            userlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userlogPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(userlogScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userlogPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(userlogUserIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(userlogUserNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(userlogTransactionIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(userlogTransactionTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(userlogSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(userlogRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        userlogPanelLayout.setVerticalGroup(
            userlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userlogPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(userlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(userlogSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(userlogRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(userlogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(userlogUserNameTextField)
                        .addComponent(userlogTransactionIdTextField)
                        .addComponent(userlogTransactionTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(userlogUserIdTextField)))
                .addGap(18, 18, 18)
                .addComponent(userlogScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addContainerGap())
        );

        actionPanel.add(userlogPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 580));

        stockMainPanel.add(actionPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 110, 790, 580));

        stockMainDesktopPane.setLayer(stockMainPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout stockMainDesktopPaneLayout = new javax.swing.GroupLayout(stockMainDesktopPane);
        stockMainDesktopPane.setLayout(stockMainDesktopPaneLayout);
        stockMainDesktopPaneLayout.setHorizontalGroup(
            stockMainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stockMainDesktopPaneLayout.createSequentialGroup()
                .addComponent(stockMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        stockMainDesktopPaneLayout.setVerticalGroup(
            stockMainDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stockMainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(stockMainDesktopPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void stockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockButtonActionPerformed
        stockActionPanelNavigation(stockButton.getText());
        //Retrieving data from database
        stockTable.getSelectionModel().clearSelection();
        retrieveStock();
        //Refreshing item categories
        retrieveItemCategories(stockItemCategoryComboBox, "Select Category", stockPanel);
        //Comparing avail Qty and reorder level
        stockTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                float avail = (float) table.getModel().getValueAt(row, 4);
                int reOrder = (int) table.getModel().getValueAt(row, 6);
                //setting colors
                if (avail <= reOrder){
                    renderer.setBackground(new Color(255, 153, 153));
                    renderer.setForeground(new Color(255, 255, 255));
                }else{
                    renderer.setBackground(new Color(255, 255, 255));
                    renderer.setForeground(new Color(42,135,235));
                }
            return renderer;
            }
        });
    }//GEN-LAST:event_stockButtonActionPerformed

    private void itemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemsButtonActionPerformed
        stockActionPanelNavigation(itemsButton.getText());                      //Action Panel Navigation
        itemTabbedPane.setEnabledAt(0, true);                                   //Setting the panel in Item to redirect to Item List Panel
        itemTabbedPane.setEnabledAt(1, false);
        itemTabbedPane.setEnabledAt(2, false);
        itemTabbedPane.setEnabledAt(3, false);
        itemTabbedPane.setSelectedIndex(0);
        itemListTable.getSelectionModel().clearSelection();
        itemListItemId = null;  //for update item validation
        //Refreshing categories
        retrieveItemCategories(itemListItemCategoryComboBox, "Select Category", itemsPanel);
        //Retrieving data from database to Item List panel
        retrieveItemList("available", itemListTable, itemsPanel);         
    }//GEN-LAST:event_itemsButtonActionPerformed

    private void ordersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordersButtonActionPerformed
        stockActionPanelNavigation(ordersButton.getText());                     //Action Panel Navigation
        ordersTabbedPane.setEnabledAt(0, true);                                 //Setting the correct panel sequence
        ordersTabbedPane.setEnabledAt(1, false);
        ordersTabbedPane.setSelectedIndex(0);
        transferStockOrderId = null;                                            //Validating the next button in Order List panel
        //Retrieving data from database to Order List panel
        ordersTable.getSelectionModel().clearSelection();
        //Refreshing and retrieving item categories
        retrieveItemCategories(orderItemCategoryComboBox, "Category", ordersPanel);
        try{
            sqlQuery = "select st.stock_transfer_id as 'Order ID', i.item_id as 'Item ID', i.item_name as 'Item Name', ic.item_category_name as 'Category', st.transfer_department as 'Department', "
                    + "st.transfer_qty as 'Order Qty', i.item_units as 'Units', DATE_FORMAT(st.order_date, '%Y-%m-%d %H:%i') as 'Order Date' from stock_transfer st, stock s, item i, item_category ic where st.stock_id = s.stock_id and s.item_id = i.item_id "
                    + "and i.item_category = ic.item_category_id and st.transfer_status = 'ordered' and i.item_availability = 'available'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            ordersTable.setModel(DbUtils.resultSetToTableModel(resultSet));
            //Setting Column width for dates
            TableColumn column1 = ordersTable.getColumnModel().getColumn(7);
            column1.setMinWidth(100);
        }catch(Exception e){
            JOptionPane.showMessageDialog(orderListPanel, e, "Query Execution Error!", 0);
        }
    }//GEN-LAST:event_ordersButtonActionPerformed

    private void purchaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseButtonActionPerformed
        stockActionPanelNavigation(purchaseButton.getText());       
        //Initially retrieving data to Tables
        retrieveItemList("available", itemListTable, purchasePanel); 
        retrieveStock(); 
        retrieveSuppliers("available", supplierListTable, purchasePanel);
        //Refreshing all combobox data and textfields
        addStockPurchaseItemCategoryComboBox.removeAllItems();
        addStockPurchaseItemCategoryComboBox.addItem("Select Item Category");
        addStockPurchaseItemNameComboBox.removeAllItems();
        addStockPurchaseItemNameComboBox.addItem("Select Item Name");
        addStockPurchaseItemIdComboBox.removeAllItems();
        addStockPurchaseItemIdComboBox.addItem("Select Item ID");
        addStockPurchaseSupplierNameComboBox.removeAllItems();
        addStockPurchaseSupplierNameComboBox.addItem("Select Supplier Name");
        addStockPurchaseSupplierIdComboBox.removeAllItems();
        addStockPurchaseSupplierIdComboBox.addItem("Select Supplier ID");
        addStockPurchaseItemUnitPriceTextField.setText(null);
        addStockPurchaseItemQtyTextField.setText(null);
        addStockPurchaseAvailQtyTextField.setText(null);
        addStockPurchaseItemUnitsTextField.setText(null);
        addStockPurchaseTotalAmountTextField.setText("0");
        //Resetting colors
        addStockPurchaseItemCategoryComboBox.setBackground(new Color(255, 255, 255));
        addStockPurchaseItemNameComboBox.setBackground(new Color(255, 255, 255));
        addStockPurchaseItemIdComboBox.setBackground(new Color(255, 255, 255));
        addStockPurchaseSupplierNameComboBox.setBackground(new Color(255, 255, 255));
        addStockPurchaseSupplierIdComboBox.setBackground(new Color(255, 255, 255));
        addStockPurchaseItemUnitPriceTextField.setBackground(new Color(255, 255, 255));
        addStockPurchaseItemQtyTextField.setBackground(new Color(255, 255, 255));
        addStockPurchaseItemUnitsTextField.setBackground(new Color(255, 255, 255));
        try{
            //Refreshing item categories
            sqlQuery2 = "select item_category_name as 'Category' from item_category";
            pStatement = conn.prepareStatement(sqlQuery2);
            resultSet = pStatement.executeQuery();
            //retrieving item categories available in database to the Item Category ComboBox
            while(resultSet.next()){               
                addStockPurchaseItemCategoryComboBox.addItem(resultSet.getString("Category"));
            }
            
            //retrieve data to the combo boxes from the database                    
            sqlQuery = "select item_name as 'Select Item Name' from item where item_availability = 'available'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            //data to addStockPurchaseItemNameComboBox
            String itemName;
            while(resultSet.next()){
                itemName = resultSet.getString("Select Item Name");
                addStockPurchaseItemNameComboBox.addItem(itemName);
            }            
            
            sqlQuery = "select item_id as 'Select Item ID' from item where item_availability = 'available'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            //data to addStockPurchaseItemIdComboBox
            String itemId;
            while(resultSet.next()){
                itemId = resultSet.getString("Select Item ID");
                addStockPurchaseItemIdComboBox.addItem(itemId);
            }
            
            
            sqlQuery = "select supplier_name as 'Select Supplier Name' from supplier where supplier_availability = 'available'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            //data to addStockPurchaseSupplierNameComboBox
            String supplierName;
            while(resultSet.next()){
                supplierName = resultSet.getString("Select Supplier Name");
                addStockPurchaseSupplierNameComboBox.addItem(supplierName);
            }
            
            sqlQuery = "select supplier_id as 'Select Supplier ID' from supplier where supplier_availability = 'available'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            //data to addStockPurchaseSupplierIdComboBox
            String supplierId;
            while(resultSet.next()){
                supplierId = resultSet.getString("Select Supplier ID");
                addStockPurchaseSupplierIdComboBox.addItem(supplierId);
            }                            
        }catch(Exception e){
            JOptionPane.showMessageDialog(purchasePanel, e, "Query Execution Error!", 0);
        }        
    }//GEN-LAST:event_purchaseButtonActionPerformed

    private void transactionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactionsButtonActionPerformed
        stockActionPanelNavigation(transactionsButton.getText());
        stockPurchaseTable.getSelectionModel().clearSelection();                   //clearing seletions
        stockTransferTable.getSelectionModel().clearSelection();
        //Retrieving Stock Purchase data
        try{                                                                      
            sqlQuery = "select distinct sp.stock_purchase_id as 'Purchase ID', i.item_id as 'Item ID', i.item_name as 'Item Name', sp.purchase_qty as 'Qty', i.item_units as 'Units', "
                    + "sp.unit_price as 'Unit Price', sp.total_amount as 'Amount (Rs)', su.supplier_id as 'Supplier ID', su.supplier_name as 'Supplier Name', DATE_FORMAT(sp.stock_purchase_date, '%Y-%m-%d %H:%i') as 'Purchase Date' "
                    + "from stock_purchase sp, stock s, item i, supplier su "
                    + "where sp.stock_id = s.stock_id and sp.supplier_id = su.supplier_id and s.item_id = i.item_id order by sp.stock_purchase_id desc"; 
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            stockPurchaseTable.setModel(DbUtils.resultSetToTableModel(resultSet));
            //Setting Column width for supplier name, purchase date
            TableColumn column1 = stockPurchaseTable.getColumnModel().getColumn(8);
            column1.setMinWidth(100);
            TableColumn column2 = stockPurchaseTable.getColumnModel().getColumn(9);
            column2.setMinWidth(110);
        }catch(Exception e){
            JOptionPane.showMessageDialog(stockPurchasePanel, e, "Query Execution Error!", 0);
        } 
        //Retrieving Stock Transfer data
        try{                                                                        
            sqlQuery2 = "select st.stock_transfer_id as 'Transfer ID', i.item_id as 'Item ID', i.item_name as 'Item Name', st.transfer_qty as 'Trans Qty'"
                    + ", i.item_units as 'Units', st.transfer_department as 'Department', st.transfer_status as 'Status', DATE_FORMAT(st.order_date, '%Y-%m-%d %H:%i') as 'Order Date',"
                    + " DATE_FORMAT(st.transfer_date, '%Y-%m-%d %H:%i') as 'Transfered Date' from stock_transfer st, stock s, item i where st.stock_id = s.stock_id and s.item_id = i.item_id order by st.stock_transfer_id desc";
            pStatement = conn.prepareStatement(sqlQuery2);
            resultSet = pStatement.executeQuery();
            stockTransferTable.setModel(DbUtils.resultSetToTableModel(resultSet));
            //Setting Column width for dates
            TableColumn column1 = stockTransferTable.getColumnModel().getColumn(7);
            column1.setMinWidth(100);
            TableColumn column2 = stockTransferTable.getColumnModel().getColumn(8);
            column2.setMinWidth(100);
        }catch(Exception e){
            JOptionPane.showMessageDialog(stockTransferPanel, e, "Query Execution Error!", 0);
        }
    }//GEN-LAST:event_transactionsButtonActionPerformed

    private void suppliersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppliersButtonActionPerformed
        stockActionPanelNavigation(suppliersButton.getText());                  //Action Panel Navigation
        supplierTabbedPane.setEnabledAt(0, true);                                   //Setting the panel in Suppliers to redirect to Supplier List Panel
        supplierTabbedPane.setEnabledAt(1, false);
        supplierTabbedPane.setEnabledAt(2, false);
        supplierTabbedPane.setEnabledAt(3, false);
        supplierTabbedPane.setSelectedIndex(0);
        supplierListTable.getSelectionModel().clearSelection();
        //Setting supplierId for editing
        supplierListSupplierId = null;
        //Retrieve data from database to Supplier List panel
        retrieveSuppliers("available", supplierListTable, supplierListPanel);
    }//GEN-LAST:event_suppliersButtonActionPerformed

    private void categoriesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriesButtonActionPerformed
        stockActionPanelNavigation(categoriesButton.getText());
        itemCategoryId = null;
        itemCategoryName = null;
        categoriesCategoryIdTextField.setText(null);
        categoriesCategoryNameTextField.setText(null);
        try{
            sqlQuery = "select item_category_id as 'Category ID', item_category_name as 'Category Name' from item_category order by item_category_id";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            stockCategoriesTable.setModel(DbUtils.resultSetToTableModel(resultSet));  
        }catch(Exception e){
            JOptionPane.showMessageDialog(categoriesPanel, e, "Query Execution Error!", 0);
        }         
        
    }//GEN-LAST:event_categoriesButtonActionPerformed

    private void accountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountButtonActionPerformed
        stockActionPanelNavigation(accountButton.getText());
        accountDepositTextField.setText("0.00");
        accountBalanceTextField.setText("0.00");
        //Retrieving Account data
        try {
            sqlQuery = "select TRUNCATE(budget_amount, 2), TRUNCATE(budget_balance, 2) from fin_budget where budget_department = 'Stock'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            if(resultSet.next()){              
                accountDepositTextField.setText(resultSet.getString(1));
                accountBalanceTextField.setText(resultSet.getString(2));
            }            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(accountPanel, e, "Account Data Retrieving Error!", 0);
        }
    }//GEN-LAST:event_accountButtonActionPerformed

    private void usersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usersButtonActionPerformed
        stockActionPanelNavigation(usersButton.getText());
        userTabbedPane.setEnabledAt(0, true);                                   //Setting the panel in Users to redirect to User List Panel
        userTabbedPane.setEnabledAt(1, false);
        userTabbedPane.setEnabledAt(2, false);
        userTabbedPane.setEnabledAt(3, false);
        userTabbedPane.setSelectedIndex(0);
        userListTable.getSelectionModel().clearSelection();
        userListUserId = null;  //Setting userId for editing
        //Retrieving data from database to User List panel
        retrieveUsers("available", userListTable, itemListPanel);
    }//GEN-LAST:event_usersButtonActionPerformed

    private void userlogButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userlogButtonActionPerformed
        stockActionPanelNavigation(userlogButton.getText());
        userlogTransactionTypeComboBox.setSelectedIndex(0);
        //Retrieving userlog table data
        try{
            sqlQuery = "select su.stock_userlog_id as 'Userlog ID', su.user_id as 'User ID', u.username as 'Username', su.stock_transaction_type as 'Transaction Type', su.stock_transaction_id as 'Transaction ID',"
                    + " DATE_FORMAT(su.stock_transaction_date, '%Y-%m-%d %H:%m') as 'Transaction Date' from stock_userlog su, system_user u where su.user_id = u.system_user_id order by su.stock_transaction_date desc";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            userlogTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            JOptionPane.showMessageDialog(userListPanel, e, "Userlog data retrieval Error!", 0);
        } 
    }//GEN-LAST:event_userlogButtonActionPerformed

    private void orderNextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderNextButtonActionPerformed
        //Validate selection
        if(transferStockOrderId == null)
            JOptionPane.showMessageDialog(orderListPanel, "Please select an order from the order list to continue.", "No Order Selected!", 2);
        else{
            ordersTabbedPane.setEnabledAt(0, false);                                //Setting the correct panel sequence
            ordersTabbedPane.setEnabledAt(1, true);
            ordersTabbedPane.setSelectedIndex(1);            
            try{//Setting Available stock qty
                sqlQuery = "select stock_qty from stock where item_id = "+ transferStockItemId +"";
                pStatement = conn.prepareStatement(sqlQuery);
                resultSet = pStatement.executeQuery();                
                orderQty = Float.valueOf(transferStockOrderQty);                    //Setting order qty value
                while(resultSet.next())
                    transferAvailQtyTextField.setText(resultSet.getString(1));                   //Setting available qty for the item ordered                                              
            }catch(Exception e){
                JOptionPane.showMessageDialog(userListPanel, e, "Stock Quantity Retrieval Error!", 0);
            }            
        }        
    }//GEN-LAST:event_orderNextButtonActionPerformed

    private void transferOrderListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transferOrderListButtonActionPerformed
        
        ordersTabbedPane.setEnabledAt(0, true);                                 //Setting the correct panel sequence
        ordersTabbedPane.setEnabledAt(1, false);
        ordersTabbedPane.setSelectedIndex(0);
    }//GEN-LAST:event_transferOrderListButtonActionPerformed

    private void itemListAddItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListAddItemButtonActionPerformed
        itemTabbedPane.setEnabledAt(1, true);                                   //Setting the correct panel sequence
        itemTabbedPane.setEnabledAt(0, false);
        itemTabbedPane.setEnabledAt(2, false);
        itemTabbedPane.setEnabledAt(3, false);
        addItemClearButton.doClick();
        itemTabbedPane.setSelectedIndex(1);
        //retrieving data from database to Category comboBox
        try{
            sqlQuery = "select item_category_name as 'Select Category' from item_category";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            
            while(resultSet.next()){
                String category = resultSet.getString("Select Category");
                addItemCategoryComboBox.addItem(category);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(addItemPanel, e, "Query Execution Error!", 0);
        }  
    }//GEN-LAST:event_itemListAddItemButtonActionPerformed

    private void itemListEditItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListEditItemButtonActionPerformed
        //Resetting colors
        editItemNameTextField.setBackground(new Color(255, 255, 255));
        editItemDescriptionTextArea.setBackground(new Color(255, 255, 255));
        editItemReorderLevelTextField.setBackground(new Color(255, 255, 255));
        //Validation
        if(itemListItemId == null){
            JOptionPane.showMessageDialog(itemListPanel, "Please select an Item from the Item list to continue.", "No Item Selected!", 2);
        }else{
            itemTabbedPane.setEnabledAt(2, true);                                   //Setting the correct panel sequence
            itemTabbedPane.setEnabledAt(1, false);
            itemTabbedPane.setEnabledAt(0, false);
            itemTabbedPane.setEnabledAt(3, false);
            itemTabbedPane.setSelectedIndex(2);   
        }
    }//GEN-LAST:event_itemListEditItemButtonActionPerformed

    private void itemListRemovedItemsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListRemovedItemsButtonActionPerformed
        itemTabbedPane.setEnabledAt(3, true);                                   //Setting the correct panel sequence
        itemTabbedPane.setEnabledAt(1, false);
        itemTabbedPane.setEnabledAt(2, false);
        itemTabbedPane.setEnabledAt(0, false);
        itemTabbedPane.setSelectedIndex(3);
        removedItemsTable.getSelectionModel().clearSelection();
        //Retrieving data from database to Removed Items panel
        retrieveItemList("removed", removedItemsTable, removedItemsPanel); 
        retrieveItemCategories(removedItemListItemCategoryComboBox, "Select Category", removedItemsPanel);
    }//GEN-LAST:event_itemListRemovedItemsButtonActionPerformed

    private void addItemItemListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemItemListButtonActionPerformed
        itemsButton.doClick();
    }//GEN-LAST:event_addItemItemListButtonActionPerformed

    private void editItemItemListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editItemItemListButtonActionPerformed
        itemsButton.doClick();
    }//GEN-LAST:event_editItemItemListButtonActionPerformed

    private void removedItemsItemListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removedItemsItemListButtonActionPerformed
        itemsButton.doClick();
    }//GEN-LAST:event_removedItemsItemListButtonActionPerformed

    private void addSupplierSupplierListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierSupplierListButtonActionPerformed
        suppliersButton.doClick();
    }//GEN-LAST:event_addSupplierSupplierListButtonActionPerformed

    private void editSupplierSupplierListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSupplierSupplierListButtonActionPerformed
        suppliersButton.doClick();
    }//GEN-LAST:event_editSupplierSupplierListButtonActionPerformed

    private void removedSuppliersSupplierListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removedSuppliersSupplierListButtonActionPerformed
        suppliersButton.doClick();
    }//GEN-LAST:event_removedSuppliersSupplierListButtonActionPerformed

    private void supplierListAddSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierListAddSupplierButtonActionPerformed
        supplierTabbedPane.setEnabledAt(1, true);                                   //Setting the panel in Suppliers to redirect to Add Supplier Panel
        supplierTabbedPane.setEnabledAt(0, false);
        supplierTabbedPane.setEnabledAt(2, false);
        supplierTabbedPane.setEnabledAt(3, false);
        addSupplierClearButton.doClick();                                           //Refrshing Add Supplier panel
        supplierTabbedPane.setSelectedIndex(1); 
    }//GEN-LAST:event_supplierListAddSupplierButtonActionPerformed

    private void supplierListEditSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierListEditSupplierButtonActionPerformed
        //Resetting edit supplier data
        editSupplierNameTextField.setBackground(new Color(255, 255, 255));
        editSupplierAddressTextArea.setBackground(new Color(255, 255, 255));
        editSupplierTelephoneTextField.setBackground(new Color(255, 255, 255));
        editSupplierEmailTextField.setBackground(new Color(255, 255, 255));
        editSupplierDescriptionTextArea.setBackground(new Color(255, 255, 255));                   
        if (supplierListSupplierId == null){
            JOptionPane.showMessageDialog(supplierListPanel, "Please select a supplier from the supplier list to continue.", "No Supplier Selected!", 2);
        }else{
            supplierTabbedPane.setEnabledAt(2, true);                                   //Setting the panel in Suppliers to redirect to Edit Supplier Panel
            supplierTabbedPane.setEnabledAt(1, false);
            supplierTabbedPane.setEnabledAt(0, false);
            supplierTabbedPane.setEnabledAt(3, false);
            supplierTabbedPane.setSelectedIndex(2);
        }
    }//GEN-LAST:event_supplierListEditSupplierButtonActionPerformed

    private void supplierListRemovedSuppliersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierListRemovedSuppliersButtonActionPerformed
        supplierTabbedPane.setEnabledAt(3, true);                                   //Setting the panel in Suppliers to redirect to Removed Suppliers Panel
        supplierTabbedPane.setEnabledAt(1, false);
        supplierTabbedPane.setEnabledAt(2, false);
        supplierTabbedPane.setEnabledAt(0, false);
        supplierTabbedPane.setSelectedIndex(3);
        removedSuppliersTable.getSelectionModel().clearSelection();
        //Retrieve data from database to Removed Suppliers panel
        retrieveSuppliers("removed", removedSuppliersTable, supplierListPanel);
    }//GEN-LAST:event_supplierListRemovedSuppliersButtonActionPerformed

    private void userListAddUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userListAddUserButtonActionPerformed
        userTabbedPane.setEnabledAt(1, true);                                   
        userTabbedPane.setEnabledAt(0, false);
        userTabbedPane.setEnabledAt(2, false);
        userTabbedPane.setEnabledAt(3, false);
        userTabbedPane.setSelectedIndex(1);
        addUserClearButton.doClick();
    }//GEN-LAST:event_userListAddUserButtonActionPerformed

    private void userListEditUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userListEditUserButtonActionPerformed
        //Resetting update field colors
        editUserFullNameTextArea.setBackground(new Color(255, 255, 255));
        editUserUsernameTextField.setBackground(new Color(255, 255, 255));
        editUserPasswordTextField.setBackground(new Color(255, 255, 255));        
        if(userListUserId == null){
             JOptionPane.showMessageDialog(userListPanel, "Please select a user from the user list to continue.", "No User Selected!", 2);
        }else{
            userTabbedPane.setEnabledAt(2, true);                                   
            userTabbedPane.setEnabledAt(1, false);
            userTabbedPane.setEnabledAt(0, false);
            userTabbedPane.setEnabledAt(3, false);
            userTabbedPane.setSelectedIndex(2);
        }
    }//GEN-LAST:event_userListEditUserButtonActionPerformed

    private void userListRemovedUsersButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userListRemovedUsersButtonActionPerformed
        userTabbedPane.setEnabledAt(3, true);                                   
        userTabbedPane.setEnabledAt(1, false);
        userTabbedPane.setEnabledAt(2, false);
        userTabbedPane.setEnabledAt(0, false);
        userTabbedPane.setSelectedIndex(3);
        removedUsersTable.getSelectionModel().clearSelection();
        //Retrieving data from database to Removed Users panel
        retrieveUsers("removed", removedUsersTable, removedUsersPanel);
    }//GEN-LAST:event_userListRemovedUsersButtonActionPerformed

    private void addUserUserListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserUserListButtonActionPerformed
        usersButton.doClick();
    }//GEN-LAST:event_addUserUserListButtonActionPerformed

    private void editUserUserListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserUserListButtonActionPerformed
        usersButton.doClick();
    }//GEN-LAST:event_editUserUserListButtonActionPerformed

    private void removedUsersUserListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removedUsersUserListButtonActionPerformed
        usersButton.doClick();
    }//GEN-LAST:event_removedUsersUserListButtonActionPerformed

    private void ordersTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersTableMouseClicked
        int row = ordersTable.getSelectedRow();                                 //Getting values from the selected row
        transferStockOrderId = ordersTable.getValueAt(row, 0).toString();       
        transferStockItemId = ordersTable.getValueAt(row, 1).toString();
        transferStockItemName = ordersTable.getValueAt(row, 2).toString();
        transferStockCategory = ordersTable.getValueAt(row, 3).toString();
        transferStockDepartment = ordersTable.getValueAt(row, 4).toString();
        transferStockOrderQty = ordersTable.getValueAt(row, 5).toString();
        transferStockUnits = ordersTable.getValueAt(row, 6).toString();
        transferStockOrderDate = ordersTable.getValueAt(row, 7).toString();
        //Get each value as string and assign to variables    
        transferOrderIdTextField.setText(transferStockOrderId);
        transferItemIdTextField.setText(transferStockItemId);
        transferItemNameTextField.setText(transferStockItemName);
        transferItemCategoryTextField.setText(transferStockCategory);
        transferDepartmentTextField.setText(transferStockDepartment);
        transferOrderQtyTextField.setText(transferStockOrderQty);
        transferUnitsTextField.setText(transferStockUnits);
        transferOrderDateTextField.setText(transferStockOrderDate);              
    }//GEN-LAST:event_ordersTableMouseClicked

    private void orderRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderRefreshButtonActionPerformed
        orderIdTextField.setText(null);                                              //Resetting search fields
        orderItemIdTextField.setText(null); 
        orderItemNameTextField.setText(null); 
        orderItemCategoryComboBox.setSelectedIndex(0);
        orderItemDepartmentTextField.setText(null);         
                                                                                                                                     
        transferOrderIdTextField.setText(null);                                     // Resetting assigned values if any
        transferItemIdTextField.setText(null);
        transferItemNameTextField.setText(null);
        transferItemCategoryTextField.setText(null);
        transferDepartmentTextField.setText(null);
        transferOrderQtyTextField.setText(null);
        transferUnitsTextField.setText(null);
        transferOrderDateTextField.setText(null);    
        ordersTable.getSelectionModel().clearSelection();                           //clear row selection
        ordersButton.doClick();                                                     //refreshing database
        
    }//GEN-LAST:event_orderRefreshButtonActionPerformed

    private void stockRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockRefreshButtonActionPerformed
        stockIdTextField.setText(null);                                                 //resetting search fields
        stockItemIdTextField.setText(null);
        stockItemNameTextField.setText(null);
        stockItemCategoryComboBox.setSelectedIndex(0);
        stockTable.getSelectionModel().clearSelection();                                //clear row selection 
        stockButton.doClick();                                                          //refresh and load the page
    }//GEN-LAST:event_stockRefreshButtonActionPerformed

    private void stockPurchaseRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockPurchaseRefreshButtonActionPerformed
        stockPurchaseIdTextField.setText(null);                                         //resetting search fields
        stockPurchaseItemIdTextField.setText(null);
        stockPurchaseItemNameTextField.setText(null);
        stockPurchaseSupplierIdTextField.setText(null);
        stockPurchaseSupplierNameTextField.setText(null);
        stockPurchaseTable.getSelectionModel().clearSelection();                        //clear row selection 
        transactionsButton.doClick(); //Refreshing table data
    }//GEN-LAST:event_stockPurchaseRefreshButtonActionPerformed

    private void stockTransferRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockTransferRefreshButtonActionPerformed
        stockTransferIdTextField.setText(null);                                         //resetting search fields
        stockTransferItemIdTextField.setText(null);
        stockTransferItemNameTextField.setText(null);
        stockTransferDepartmentTextField.setText(null);    
        stockTransferTable.getSelectionModel().clearSelection();                        //clear row selection 
        transactionsButton.doClick(); //Refreshing table data
    }//GEN-LAST:event_stockTransferRefreshButtonActionPerformed

    private void addSupplierClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierClearButtonActionPerformed
        addSupplierNameTextField.setText(null);                                         //clear all fields in Add Supplier panel
        addSupplierAddressTextArea.setText(null);
        addSupplierTelephoneTextField.setText(null);
        addSupplierEmailTextField.setText(null);
        addSupplierDescriptionTextArea.setText(null);
        addSupplierNameTextField.setBackground(new Color(255, 255, 255));               //Setting background of textfields to white
        addSupplierAddressTextArea.setBackground(new Color(255, 255, 255));
        addSupplierEmailTextField.setBackground(new Color(255, 255, 255));
        addSupplierDescriptionTextArea.setBackground(new Color(255, 255, 255));
        addSupplierTelephoneTextField.setBackground(new Color(255, 255, 255));        
        
    }//GEN-LAST:event_addSupplierClearButtonActionPerformed

    private void addSupplierDemoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierDemoButtonActionPerformed
        addSupplierNameTextField.setText("Nimal Perera");                                         
        addSupplierAddressTextArea.setText("Malabe");
        addSupplierTelephoneTextField.setText("0771213411");
        addSupplierEmailTextField.setText("nimalperera@gmail.lk");
        addSupplierDescriptionTextArea.setText("Fast delivery");   
    }//GEN-LAST:event_addSupplierDemoButtonActionPerformed

    private void addSupplierAddSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierAddSupplierButtonActionPerformed
       //Variable declaration
        String supplierName, supplierAddress, supplierEmail, supplierDescription, supplierTelephone;        
        int telephone;        
        supplierName = addSupplierNameTextField.getText();
        supplierAddress = addSupplierAddressTextArea.getText();
        supplierEmail =   addSupplierEmailTextField.getText();
        supplierDescription = addSupplierDescriptionTextArea.getText();
        supplierTelephone = addSupplierTelephoneTextField.getText();        
        //validations
        if(supplierName.isEmpty())
            addSupplierNameTextField.setBackground(new Color(255, 150, 150));
        else
            addSupplierNameTextField.setBackground(new Color(255, 255, 255));
        if(supplierAddress.isEmpty())
            addSupplierAddressTextArea.setBackground(new Color(255, 150, 150));
        else
            addSupplierAddressTextArea.setBackground(new Color(255, 255, 255));
        if(supplierEmail.isEmpty())
            addSupplierEmailTextField.setBackground(new Color(255, 150, 150));
        else
            addSupplierEmailTextField.setBackground(new Color(255, 255, 255));
        if(supplierDescription.isEmpty())
            addSupplierDescriptionTextArea.setBackground(new Color(255, 150, 150));
        else
            addSupplierDescriptionTextArea.setBackground(new Color(255, 255, 255));
        if(supplierTelephone.isEmpty())
            addSupplierTelephoneTextField.setBackground(new Color(255, 150, 150));
        else{
            addSupplierTelephoneTextField.setBackground(new Color(255, 255, 255));    
        }
        if(supplierName.isEmpty() || supplierAddress.isEmpty() || supplierEmail.isEmpty() || supplierDescription.isEmpty() || supplierTelephone.isEmpty()){     
            JOptionPane.showMessageDialog(addSupplierPanel, "Please fill all the fields to continue.", "Form Incomplete!", 0);
        }else{
            int confirm = JOptionPane.showConfirmDialog(addSupplierPanel, "Are you sure you want to add a supplier?", "Add Supplier", 0, 3);        //get confirmation for adding supplier to database
            if(confirm == 0){
                try{//Checking supplier availability
                    sqlQuery = "select * where supplier_name = '"+supplierName+"'";
                    pStatement = conn.prepareStatement(sqlQuery);
                    resultSet = pStatement.executeQuery();
                    if(resultSet.isBeforeFirst()){
                        JOptionPane.showMessageDialog(addSupplierPanel, "Supplier you tried to add is already available. Please add a new supplier.", "Supplier Already Available!", 2);
                    }else{    
                        //Inserting a new supplier
                        telephone = Integer.parseInt(supplierTelephone);                                                                //Inserting validated result to the database
                        sqlQuery = "insert into supplier(supplier_name, supplier_address, supplier_contact, supplier_email, supplier_description)"
                                + " values('"+supplierName+"', '"+supplierAddress+"', '"+telephone+"', '"+supplierEmail+"', '"+supplierDescription+"')";
                        pStatement = conn.prepareStatement(sqlQuery);
                        pStatement.execute();
                        addSupplierClearButton.doClick();                                               //clear data after insertion 
                        JOptionPane.showMessageDialog(addSupplierPanel, "Supplier successfully added to the database.", "Supplier Added!", 1); 
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(addSupplierPanel, e, "Supplier data Insertion Error!", 0);
                }
            }
        }    
    }//GEN-LAST:event_addSupplierAddSupplierButtonActionPerformed

    private void addItemAddItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemAddItemButtonActionPerformed
        //Variable declaration        
        String itemName, itemUnits, itemDescription, itemCategoryName, reorderString;
        int reorderLevel, itemId = 0; 
        itemName = addItemNameTextField.getText();
        itemUnits = String.valueOf(addItemUnitsComboBox.getSelectedItem());
        itemDescription = addItemDescriptionTextArea.getText();
        itemCategoryName = addItemCategoryComboBox.getSelectedItem().toString();
        reorderString = addItemReorderLevelTextField.getText();
        int categoryId = 0;
        //Retrieving CategoryId
        try {
            sqlQuery = "select item_category_id from item_category where item_category_name = '"+itemCategoryName+"'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();            
            if(resultSet.next()){
                categoryId = Integer.parseInt(resultSet.getString(1));
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(addItemPanel, e, "Item Category Id retrieving Error!", 0);
        }       
        //validations
        if(itemName.isEmpty())
            addItemNameTextField.setBackground(new Color(255, 150, 150));
        else
            addItemNameTextField.setBackground(new Color(255, 255, 255));
        if(itemDescription.isEmpty())
            addItemDescriptionTextArea.setBackground(new Color(255, 150, 150));
        else
            addItemDescriptionTextArea.setBackground(new Color(255, 255, 255));
        if(reorderString.isEmpty())
            addItemReorderLevelTextField.setBackground(new Color(255, 150, 150));
        else
            addItemReorderLevelTextField.setBackground(new Color(255, 255, 255));
        if(addItemUnitsComboBox.getSelectedIndex() == 0)
            addItemUnitsComboBox.setBackground(new Color(255, 150, 150));
        else
            addItemUnitsComboBox.setBackground(new Color(255, 255, 255));
        if(itemCategoryName.equals("Select Category"))
            addItemCategoryComboBox.setBackground(new Color(255, 150, 150));
        else
            addItemCategoryComboBox.setBackground(new Color(255, 255, 255));
        //Displaying Error Message
        if(itemName.isEmpty() || itemDescription.isEmpty() || reorderString.isEmpty() || itemUnits.equals("Select Units") || itemCategoryName.equals("Select Category")){
            JOptionPane.showMessageDialog(addItemPanel, "Please fill all the fields to continue.", "Form Incomplete!", 0);
        }else{
            try{
                int confirm = JOptionPane.showConfirmDialog(addItemPanel, "Are you sure you want to add an item?", "Add Item", 0, 3);        //get confirmation for adding item to database
                if(confirm == 0){//Checking whether the item is already available
                    sqlQuery = "select item_id from item where item_name = '"+itemName+"' and item_description = '"+itemDescription+"' and item_availability = 'available'";
                    pStatement = conn.prepareStatement(sqlQuery);
                    resultSet = pStatement.executeQuery();
                    if(resultSet.isBeforeFirst()){
                        JOptionPane.showMessageDialog(addItemPanel, "Item you tried to add is already available. Please add a new item.", "Item Already Available!", 2);
                    }else{//Inserting into Items table                        
                        reorderLevel = Integer.parseInt(reorderString);                                                                //Inserting validated result to the database
                        sqlQuery = "insert into item(item_name, item_units, item_description, item_category, item_reorder_level) values('"+itemName+"', '"+itemUnits+"', '"+itemDescription+"' ,'"+categoryId+"' ,"+reorderLevel+")";
                        pStatement = conn.prepareStatement(sqlQuery);
                        pStatement.execute();
                        //Retrieving itemId                    
                        try {
                            sqlQuery = "select item_id from item where item_name = '" + itemName + "'";
                            pStatement = conn.prepareStatement(sqlQuery);
                            resultSet = pStatement.executeQuery();
                            if (resultSet.next()) {
                                itemId = Integer.parseInt(resultSet.getString(1));
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(addItemPanel, e, "Item ID Retrieving Error!", 0);
                        }
                        //Inserting into Stock Table
                        sqlQuery2 = "insert into stock(item_id, stock_qty) values(" + itemId + ", 0)";
                        pStatement = conn.prepareStatement(sqlQuery2);
                        pStatement.execute();
                        addItemClearButton.doClick();                                               //clear data after insertion 
                        JOptionPane.showMessageDialog(addItemPanel, "Item successfully added to the database.", "Item Added!", 1);    //print successful message
                    }
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(addItemPanel, e, "Item Add Error!", 0);
            }
        }        
    }//GEN-LAST:event_addItemAddItemButtonActionPerformed

    private void addItemClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemClearButtonActionPerformed
        //Resetting values
        addItemNameTextField.setText(null);
        addItemUnitsComboBox.setSelectedIndex(0);
        addItemDescriptionTextArea.setText(null);
        addItemCategoryComboBox.setSelectedIndex(0);
        addItemReorderLevelTextField.setText(null);       
        //Resetting color
        addItemNameTextField.setBackground(new Color(255, 255, 255));
        addItemDescriptionTextArea.setBackground(new Color(255, 255, 255));
        addItemReorderLevelTextField.setBackground(new Color(255, 255, 255));
        addItemUnitsComboBox.setBackground(new Color(255, 255, 255));
        addItemCategoryComboBox.setBackground(new Color(255, 255, 255));        
    }//GEN-LAST:event_addItemClearButtonActionPerformed

    private void addItemDemoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemDemoButtonActionPerformed
        addItemNameTextField.setText("Potato");
        addItemUnitsComboBox.setSelectedIndex(1);
        addItemDescriptionTextArea.setText("Sale");
        addItemCategoryComboBox.setSelectedIndex(3);
        addItemReorderLevelTextField.setText("300");  
    }//GEN-LAST:event_addItemDemoButtonActionPerformed

    private void orderDemoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderDemoButtonActionPerformed
        ordersTable.getSelectionModel().clearSelection();
        //Insert Data
        try{
            sqlQuery = "insert into stock_transfer(stock_id, transfer_department, transfer_qty) values('330005', 'Restaurant', '60')";
            pStatement = conn.prepareStatement(sqlQuery);
            pStatement.execute();           
        }catch(Exception e){
            JOptionPane.showMessageDialog(orderListPanel, e, "Query Execution Error!", 0);
        }        
        //Display Data
        try{
            sqlQuery = "select st.stock_transfer_id as 'Order ID', i.item_id as 'Item ID', i.item_name as 'Item Name', ic.item_category_name as 'Category', st.transfer_department as 'Department', st.transfer_qty as 'Order Qty', i.item_units as 'Units', st.order_date as 'Order Date' from stock_transfer st, stock s, item i, item_category ic where st.stock_id = s.stock_id and s.item_id = i.item_id and i.item_category = ic.item_category_id";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            ordersTable.setModel(DbUtils.resultSetToTableModel(resultSet));
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(orderListPanel, e, "Query Execution Error!", 0);
        }        
    }//GEN-LAST:event_orderDemoButtonActionPerformed

    private void transferOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transferOrderButtonActionPerformed
        stockQty = Float.parseFloat(transferAvailQtyTextField.getText());
        int confirm = JOptionPane.showConfirmDialog(transferStockPanel, "Please Confirm the Order Transfer by selecting OK", "Confirm Transfer", 2, 2);
        if(confirm == 0){
            if(stockQty >= orderQty){    //Validate Order Qty
                try{
                    float remainQty = stockQty - orderQty;
                    int stockTransferId = Integer.valueOf(transferStockOrderId);                    
                    //getting current timestamp to update transfer date
                    Date transferDate = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd h:mm");
                    String formattedDate = sdf.format(transferDate);
                    //Updating transfer stock table
                    sqlQuery = "update stock_transfer st, stock s set st.transfer_status = 'transfered', st.transfer_date = '" + formattedDate + "', s.stock_qty = "+ remainQty +" where st.stock_id = s.stock_id and st.stock_transfer_id = "+ stockTransferId;
                    pStatement = conn.prepareStatement(sqlQuery);
                    pStatement.execute();
                    //Retrieving Logged User
                    int userId = retrieveLoggedUserId(transferStockPanel);
                    //Inserting data to userlog table
                    sqlQuery = "insert into stock_userlog(user_id, stock_transaction_type, stock_transaction_id) values("+userId+", 'Stock Transfer', "+stockTransferId+")";
                    pStatement = conn.prepareStatement(sqlQuery);
                    pStatement.execute();  
                    JOptionPane.showMessageDialog(orderListPanel, "Stock successfully transfered.", "Stock Transfered!", 1);
                    ordersButton.doClick();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(orderListPanel, e, "Stock Transfer Error!", 0);
                }
            }else{
                JOptionPane.showMessageDialog(transferStockPanel, "Sorry! Order is too large. You don't have enough items in stock to transfer", "Transfer Failed!", 2);
                transferOrderListButton.doClick();          //back to Order list 
            }
        }
    }//GEN-LAST:event_transferOrderButtonActionPerformed

    private void stockSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockSearchButtonActionPerformed
        String stockId, itemId, itemName, itemCategory;
        stockId = stockIdTextField.getText();
        itemId = stockItemIdTextField.getText();
        itemName = stockItemNameTextField.getText();
        itemCategory = stockItemCategoryComboBox.getSelectedItem().toString();
        
        try{    //Without item category selection
            if(itemCategory.equals("Select Category")){
                sqlQuery = "select s.stock_id as 'Stock ID', s.item_id as 'Item ID', i.item_name as 'Item Name', "
                        + "ic.item_category_name as 'Category', s.stock_qty as 'Avail Qty', i.item_units as 'Units', "
                        + "i.item_reorder_level as 'Re-order' from stock s, item i, item_category ic where s.item_id = i.item_id "
                        + "and i.item_category = ic.item_category_id and CAST(s.stock_id as CHAR) like  '%"+stockId+"%' and CAST(i.item_id as CHAR) like '%"+ itemId +"%'"
                        + " and i.item_name like '%"+ itemName +"%'";    
            }else{//With item category selection
                sqlQuery = "select s.stock_id as 'Stock ID', s.item_id as 'Item ID', i.item_name as 'Item Name', "
                        + "ic.item_category_name as 'Category', s.stock_qty as 'Avail Qty', i.item_units as 'Units', "
                        + "i.item_reorder_level as 'Re-order' from stock s, item i, item_category ic where s.item_id = i.item_id "
                        + "and i.item_category = ic.item_category_id and CAST(s.stock_id as CHAR) like  '%"+stockId+"%' and CAST(i.item_id as CHAR) like '%"+ itemId +"%'"
                        + " and i.item_name like '%"+ itemName +"%' and ic.item_category_name like '%"+ itemCategory +"%'";
            }
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            stockTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            JOptionPane.showMessageDialog(orderListPanel, e, "Stock Transfer Error!", 0);
        }
    }//GEN-LAST:event_stockSearchButtonActionPerformed

    private void orderSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderSearchButtonActionPerformed
        //Retrieving search results from database to Order List panel
        String orderId,  itemId, itemName, category, department;
        orderId = orderIdTextField.getText();
        itemId = orderItemIdTextField.getText();
        itemName = orderItemNameTextField.getText();  
        category = orderItemCategoryComboBox.getSelectedItem().toString();
        department = orderItemDepartmentTextField.getText();       
        ordersTable.getSelectionModel().clearSelection();
        try{
            if (category.equals("Category")){
                sqlQuery = "select st.stock_transfer_id as 'Order ID', i.item_id as 'Item ID', i.item_name as 'Item Name', ic.item_category_name as 'Category', st.transfer_department as 'Department',"
                    + " st.transfer_qty as 'Order Qty', i.item_units as 'Units', DATE_FORMAT(st.order_date, '%Y-%m-%d %H:%i') as 'Order Date' from stock_transfer st, stock s, item i, item_category ic where st.stock_id = s.stock_id"
                    + " and s.item_id = i.item_id and i.item_category = ic.item_category_id and st.transfer_status = 'ordered' and CAST(st.stock_transfer_id as CHAR) like '%"+ orderId+"%' and CAST(i.item_id as CHAR) like '%"+ itemId +"%'"
                    + " and i.item_name like '%"+ itemName +"%' and st.transfer_department like '%"+ department +"%'"; 
            }
            else{
                sqlQuery = "select st.stock_transfer_id as 'Order ID', i.item_id as 'Item ID', i.item_name as 'Item Name', ic.item_category_name as 'Category', st.transfer_department as 'Department',"
                    + " st.transfer_qty as 'Order Qty', i.item_units as 'Units', DATE_FORMAT(st.order_date, '%Y-%m-%d %H:%i') as 'Order Date' from stock_transfer st, stock s, item i, item_category ic where st.stock_id = s.stock_id"
                    + " and s.item_id = i.item_id and i.item_category = ic.item_category_id and st.transfer_status = 'ordered' and CAST(st.stock_transfer_id as CHAR) like '%"+ orderId+"%' and CAST(i.item_id as CHAR) like '%"+ itemId +"%'"
                    + " and i.item_name like '%"+ itemName +"%' and ic.item_category_name like '%"+ category +"%' and st.transfer_department like '%"+ department +"%'";
            }
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            ordersTable.setModel(DbUtils.resultSetToTableModel(resultSet));
            //Setting Column width for dates
            TableColumn column1 = ordersTable.getColumnModel().getColumn(7);
            column1.setMinWidth(100);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(orderListPanel, e, "Query Execution Error!", 0);
        }
    }//GEN-LAST:event_orderSearchButtonActionPerformed

    private void itemListRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListRefreshButtonActionPerformed
        itemListItemIdTextField.setText(null);
        itemListItemNameTextField.setText(null);
        itemListItemCategoryComboBox.setSelectedIndex(0);
        itemsButton.doClick();
    }//GEN-LAST:event_itemListRefreshButtonActionPerformed

    private void itemListSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemListSearchButtonActionPerformed
        String itemId, itemName, category;
        itemId = itemListItemIdTextField.getText();
        itemName = itemListItemNameTextField.getText();
        category = String.valueOf(itemListItemCategoryComboBox.getSelectedItem());
        try{
            if(itemListItemCategoryComboBox.getSelectedIndex() == 0)                    //If category is not selected
            sqlQuery = "select i.item_id as 'Item ID', i.item_name as 'Item Name', i.item_units as 'Units', i.item_description as 'Description', ic.item_category_name as 'Category', i.item_reorder_level as 'Re-order Level'  from item i, item_category ic where i.item_category = ic.item_category_id and item_availability = 'available' and CAST(i.item_id as CHAR) like '%"+ itemId +"%' and i.item_name like '%"+ itemName +"%'";

            else                                                                        //If category is selected
            sqlQuery = "select i.item_id as 'Item ID', i.item_name as 'Item Name', i.item_units as 'Units', i.item_description as 'Description', ic.item_category_name as 'Category', i.item_reorder_level as 'Re-order Level'  from item i, item_category ic where i.item_category = ic.item_category_id and item_availability = 'available' and CAST(i.item_id as CHAR) like '%"+ itemId +"%' and i.item_name like '%"+ itemName +"%' and ic.item_category_name like '%"+ category +"%'";
            
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            itemListTable.setModel(DbUtils.resultSetToTableModel(resultSet));            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(itemListPanel, e, "Search Item Query Execution Error!", 0);
        } 
    }//GEN-LAST:event_itemListSearchButtonActionPerformed

    private void categoriesAddCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriesAddCategoryButtonActionPerformed
        //Validation
        String categoryName = categoriesCategoryNameTextField.getText();
        if(categoryName.isEmpty() && !categoryName.matches("[a-zA-Z]+")){ 
            JOptionPane.showMessageDialog(categoriesPanel, "Please enter a valid category name", "Category Adding Failed!", 0);
        }else{
            try{
                int confirm = JOptionPane.showConfirmDialog(categoriesPanel, "Are you sure you want to add Category you entered?", "Add Category!", 0, 3);
                if(confirm == 0){
                    sqlQuery = "insert into item_category(item_category_name) values('"+categoryName+"')";
                    pStatement = conn.prepareStatement(sqlQuery);
                    pStatement.execute();
                    JOptionPane.showMessageDialog(categoriesPanel, "Category successfully added to the database", "Category Added!", 1);
                    categoriesButton.doClick();
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(categoriesPanel, "Category is already available", "Category Adding Error!", 0);
            }
        }
    }//GEN-LAST:event_categoriesAddCategoryButtonActionPerformed

    private void stockPurchaseClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockPurchaseClearButtonActionPerformed
        purchaseButton.doClick();       
    }//GEN-LAST:event_stockPurchaseClearButtonActionPerformed

    private void stockPurchaseConfirmPurchaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockPurchaseConfirmPurchaseButtonActionPerformed
        //Validations
        String itemName, itemId;
        int supplierId, stockId;
        float itemQty, availQty = 0;
        double unitPrice, totalAmount;  
        if(addStockPurchaseItemCategoryComboBox.getSelectedIndex() == 0){
            addStockPurchaseItemCategoryComboBox.setBackground(new Color(255, 150, 150));
            addStockPurchaseItemNameComboBox.setBackground(new Color(255, 150, 150));
            addStockPurchaseItemIdComboBox.setBackground(new Color(255, 150, 150));
        }else{
            addStockPurchaseItemCategoryComboBox.setBackground(new Color(255, 255, 255));
            addStockPurchaseItemNameComboBox.setBackground(new Color(255, 255, 255));
            addStockPurchaseItemIdComboBox.setBackground(new Color(255, 255, 255));
        }
        if(addStockPurchaseItemUnitPriceTextField.getText().isEmpty())
            addStockPurchaseItemUnitPriceTextField.setBackground(new Color(255, 150, 150));
        else
            addStockPurchaseItemUnitPriceTextField.setBackground(new Color(255, 255, 255));
        if(addStockPurchaseItemQtyTextField.getText().isEmpty())
            addStockPurchaseItemQtyTextField.setBackground(new Color(255, 150, 150));
        else
            addStockPurchaseItemQtyTextField.setBackground(new Color(255, 255, 255));
        if(addStockPurchaseItemUnitsTextField.getText().isEmpty())
            addStockPurchaseItemUnitsTextField.setBackground(new Color(255, 150, 150));
        else
            addStockPurchaseItemUnitsTextField.setBackground(new Color(255, 255, 255));
        if(addStockPurchaseSupplierNameComboBox.getSelectedIndex() == 0){
            addStockPurchaseSupplierNameComboBox.setBackground(new Color(255, 150, 150));
            addStockPurchaseSupplierIdComboBox.setBackground(new Color(255, 150, 150));
        }else{
            addStockPurchaseSupplierNameComboBox.setBackground(new Color(255, 255, 255));
            addStockPurchaseSupplierIdComboBox.setBackground(new Color(255, 255, 255));
        }
        
        if(addStockPurchaseItemCategoryComboBox.getSelectedIndex() == 0 || addStockPurchaseItemUnitPriceTextField.getText().isEmpty() || 
           addStockPurchaseItemQtyTextField.getText().isEmpty() || addStockPurchaseItemUnitsTextField.getText().isEmpty() || addStockPurchaseSupplierNameComboBox.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(addSupplierPanel, "Please fill all the fields to continue.", "Form Incomplete!", 0);
        }else{        
            try {
                int confirm = JOptionPane.showConfirmDialog(purchasePanel, "Please select OK to confirm Stock Purchase", "Confirm Purchase!", 2, 2);
                if (confirm == 0) {
                    //retrieving data from stock purchase panel                   
                    itemName = addStockPurchaseItemNameComboBox.getSelectedItem().toString();
                    supplierId = Integer.parseInt(addStockPurchaseSupplierIdComboBox.getSelectedItem().toString());
                    itemQty = Float.parseFloat(addStockPurchaseItemQtyTextField.getText());
                    unitPrice = Double.parseDouble(addStockPurchaseItemUnitPriceTextField.getText());
                    totalAmount = itemQty * unitPrice;
                    //Getting relevant stockId
                    int selectedRow = 0;
                    String tableItemId;
                    itemId = addStockPurchaseItemIdComboBox.getSelectedItem().toString();
                    for (int r = 0; r < itemListTable.getRowCount(); r++) {
                        tableItemId = stockTable.getValueAt(r, 1).toString();
                        if (tableItemId.equals(itemId)){
                            selectedRow = r;
                            break;
                        }
                    }
                    stockId = Integer.parseInt(stockTable.getValueAt(selectedRow, 0).toString());
                    availQty = Float.parseFloat(stockTable.getValueAt(selectedRow, 4).toString());
                    //getting current timestamp to update transfer date
                    Date transferDate = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = sdf.format(transferDate);
                    
                    //Retrieving month and year for balancecheck function
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
                    String year = sdf2.format(transferDate);
                    SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
                    String month = sdf3.format(transferDate); 
                    
                    //Checking total purchase amount with available balance of stock department
                    double availableBalance = dbaFinance.balanceChecking("Stock", "Expenditure", totalAmount, month, year);
                    if(availableBalance >= totalAmount){
                        //Inserting data to fin Cashflow table
                        sqlQuery = "insert into fin_cashflow(cashflow_date, cashflow_description, cashflow_department, cashflow_method, cashflow_amount, cashflow_payment_type, cashflow_payment_status) "
                                + " values('" + formattedDate + "' , '" + itemName + "', 'Stock', 'Bank', " + totalAmount + ", 'Expenditure', 'Debit')";
                        pStatement = conn.prepareStatement(sqlQuery);
                        pStatement.execute();                    
                        //Inserting data to Stock Purchase Table
                        sqlQuery = "insert into stock_purchase(stock_id, supplier_id, purchase_qty, unit_price, total_amount) values("+stockId+", "+supplierId+", "+itemQty+", "+unitPrice+", "+totalAmount+")";
                        pStatement = conn.prepareStatement(sqlQuery);
                        pStatement.execute();                    
                        //Updating stock
                        double totalQty = availQty + itemQty;
                        sqlQuery2 = "update stock set stock_qty = " + totalQty + " where stock_id = " + stockId;
                        pStatement = conn.prepareStatement(sqlQuery2);
                        pStatement.execute();                    
                        //Retrieving Stock Purchase ID
                        sqlQuery = "select stock_purchase_id from stock_purchase where stock_id = "+stockId+" and supplier_id = "+supplierId+" and purchase_qty = "+itemQty+" and unit_price = "+unitPrice+"";
                        pStatement = conn.prepareStatement(sqlQuery);
                        resultSet = pStatement.executeQuery();
                        String stockPurchaseId = "";
                        if(resultSet.next())
                            stockPurchaseId = resultSet.getString(1);
                        //Retrieving Logged User
                        int userId = retrieveLoggedUserId(purchasePanel);                        
                        //Inserting data to userlog table
                        sqlQuery2 = "insert into stock_userlog(user_id, stock_transaction_type, stock_transaction_id) values("+userId+", 'Stock Purchase', "+stockPurchaseId+")";
                        pStatement = conn.prepareStatement(sqlQuery2);
                        pStatement.execute();
                        JOptionPane.showMessageDialog(purchasePanel, "Stock Successfully Purchased.", "Stock Purchased!", 1);
                        purchaseButton.doClick();
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(purchasePanel, e, "Stock Purchase Failed!", 0);
            }
        }
    }//GEN-LAST:event_stockPurchaseConfirmPurchaseButtonActionPerformed

    private void stockButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockButtonMouseEntered
        stockButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_stockButtonMouseEntered

    private void stockButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockButtonMouseExited
        stockButton.setBackground(new Color(42,135,235));    
    }//GEN-LAST:event_stockButtonMouseExited

    private void ordersButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersButtonMouseEntered
        ordersButton.setBackground(new Color(35, 171, 69));
    }//GEN-LAST:event_ordersButtonMouseEntered

    private void ordersButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersButtonMouseExited
       ordersButton.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_ordersButtonMouseExited

    private void purchaseButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseButtonMouseEntered
        purchaseButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_purchaseButtonMouseEntered

    private void purchaseButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_purchaseButtonMouseExited
        purchaseButton.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_purchaseButtonMouseExited

    private void transactionsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionsButtonMouseEntered
        transactionsButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_transactionsButtonMouseEntered

    private void transactionsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transactionsButtonMouseExited
        transactionsButton.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_transactionsButtonMouseExited

    private void itemsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsButtonMouseEntered
        itemsButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_itemsButtonMouseEntered

    private void itemsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemsButtonMouseExited
        itemsButton.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_itemsButtonMouseExited

    private void suppliersButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suppliersButtonMouseEntered
        suppliersButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_suppliersButtonMouseEntered

    private void suppliersButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suppliersButtonMouseExited
        suppliersButton.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_suppliersButtonMouseExited

    private void categoriesButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoriesButtonMouseEntered
        categoriesButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_categoriesButtonMouseEntered

    private void categoriesButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoriesButtonMouseExited
        categoriesButton.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_categoriesButtonMouseExited

    private void accountButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountButtonMouseEntered
        accountButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_accountButtonMouseEntered

    private void accountButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountButtonMouseExited
        accountButton.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_accountButtonMouseExited

    private void usersButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersButtonMouseEntered
        usersButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_usersButtonMouseEntered

    private void usersButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersButtonMouseExited
        usersButton.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_usersButtonMouseExited

    private void userlogButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userlogButtonMouseEntered
        userlogButton.setBackground(new Color(35,171,69));
    }//GEN-LAST:event_userlogButtonMouseEntered

    private void userlogButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userlogButtonMouseExited
        userlogButton.setBackground(new Color(42,135,235));
    }//GEN-LAST:event_userlogButtonMouseExited

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        System_Login login = new System_Login();
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void addStockPurchaseItemNameComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addStockPurchaseItemNameComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int selectedRow = 0;
            String itemName = addStockPurchaseItemNameComboBox.getSelectedItem().toString();
            String tableItemName;
            for (int r = 0; r < itemListTable.getRowCount(); r++) {
                tableItemName = itemListTable.getValueAt(r, 1).toString();
                if (tableItemName.equals(itemName)) {
                    selectedRow = r;
                    break;
                }
            }
            String itemId = itemListTable.getValueAt(selectedRow, 0).toString();
            addStockPurchaseItemIdComboBox.setSelectedItem(itemId);
            addStockPurchaseItemCategoryComboBox.setSelectedItem(itemListTable.getValueAt(selectedRow, 4).toString());
            addStockPurchaseItemUnitsTextField.setText(itemListTable.getValueAt(selectedRow, 2).toString());
            //Retrieving Avail Qty
            selectedRow = 0;
            for (int r = 0; r < stockTable.getRowCount(); r++) {                
                if (stockTable.getValueAt(r, 1).toString().equals(itemId)){
                    selectedRow = r;
                    break;
                }
            }  
            addStockPurchaseAvailQtyTextField.setText(stockTable.getValueAt(selectedRow, 4).toString());
        }
    }//GEN-LAST:event_addStockPurchaseItemNameComboBoxItemStateChanged

    private void addStockPurchaseItemIdComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addStockPurchaseItemIdComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int selectedRow = 0;
            String itemId = addStockPurchaseItemIdComboBox.getSelectedItem().toString();
            String tableItemId;
            for (int r = 0; r < itemListTable.getRowCount(); r++){
                tableItemId = itemListTable.getValueAt(r, 0).toString();
                if(tableItemId.equals(itemId)){
                    selectedRow = r;
                    break;
                }                   
            }            
            addStockPurchaseItemNameComboBox.setSelectedItem(itemListTable.getValueAt(selectedRow, 1).toString());
            addStockPurchaseItemCategoryComboBox.setSelectedItem(itemListTable.getValueAt(selectedRow, 4).toString());
            addStockPurchaseItemUnitsTextField.setText(itemListTable.getValueAt(selectedRow, 2).toString());
            //Retrieving Avail Qty
            selectedRow = 0;
            for (int r = 0; r < stockTable.getRowCount(); r++) {
                if (stockTable.getValueAt(r, 1).toString().equals(itemId)) {
                    selectedRow = r;
                    break;
                }
            }
            addStockPurchaseAvailQtyTextField.setText(stockTable.getValueAt(selectedRow, 4).toString());
        }   
    }//GEN-LAST:event_addStockPurchaseItemIdComboBoxItemStateChanged

    private void addStockPurchaseSupplierNameComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addStockPurchaseSupplierNameComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int selectedRow = 0;
            String supplierName = addStockPurchaseSupplierNameComboBox.getSelectedItem().toString();
            String tableSupplierName;
            for (int r = 0; r < supplierListTable.getRowCount(); r++) {
                tableSupplierName = supplierListTable.getValueAt(r, 1).toString();
                if (tableSupplierName.equals(supplierName)) {
                    selectedRow = r;
                    break;
                }
            }
            addStockPurchaseSupplierIdComboBox.setSelectedItem(supplierListTable.getValueAt(selectedRow, 0).toString());
        }    
    }//GEN-LAST:event_addStockPurchaseSupplierNameComboBoxItemStateChanged

    private void addStockPurchaseSupplierIdComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addStockPurchaseSupplierIdComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int selectedRow = 0;
            String supplierId = addStockPurchaseSupplierIdComboBox.getSelectedItem().toString();
            String tableSupplierId;
            for (int r = 0; r < supplierListTable.getRowCount(); r++) {
                tableSupplierId = supplierListTable.getValueAt(r, 0).toString();
                if (tableSupplierId.equals(supplierId)) {
                    selectedRow = r;
                    break;
                }
            }
            addStockPurchaseSupplierNameComboBox.setSelectedItem(supplierListTable.getValueAt(selectedRow, 1).toString());
        }       
    }//GEN-LAST:event_addStockPurchaseSupplierIdComboBoxItemStateChanged

    private void stockPurchaseCalculateTotalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockPurchaseCalculateTotalButtonActionPerformed
        try{
        double price = Double.parseDouble(addStockPurchaseItemUnitPriceTextField.getText());
        float qty = Float.parseFloat(addStockPurchaseItemQtyTextField.getText());
        double total = price * qty;
        addStockPurchaseTotalAmountTextField.setText(Double.toString(total));
        }catch(NumberFormatException e){
             JOptionPane.showMessageDialog(purchasePanel, "Please enter valid data for unit price and quantity", "Calculation Error!", 0);
        }          
    }//GEN-LAST:event_stockPurchaseCalculateTotalButtonActionPerformed

    private void stockPurchaseSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockPurchaseSearchButtonActionPerformed
        String purchaseId, itemId, itemName, supplierId, supplierName;
        purchaseId = stockPurchaseIdTextField.getText();
        itemId = stockPurchaseItemIdTextField.getText();
        itemName = stockPurchaseItemNameTextField.getText();
        supplierId = stockPurchaseSupplierIdTextField.getText();
        supplierName = stockPurchaseSupplierNameTextField.getText();
        
        try{                                   
            sqlQuery = "select distinct sp.stock_purchase_id as 'Purchase ID', i.item_id as 'Item ID', i.item_name as 'Item Name', sp.purchase_qty as 'Qty', i.item_units as 'Units', "
                    + "sp.unit_price as 'Unit Price', sp.total_amount as 'Total Amount', su.supplier_id as 'Supplier ID', su.supplier_name as 'Supplier Name', sp.stock_purchase_date as 'Purchase Date' "
                    + "from stock_purchase sp, stock s, item i, supplier su "
                    + "where sp.stock_id = s.stock_id and sp.supplier_id = su.supplier_id and s.item_id = i.item_id and CAST(sp.stock_purchase_id as CHAR) like  '%"+purchaseId+"%' and "
                    + "CAST(i.item_id as CHAR) like '%"+itemId+"%' and i.item_name like '%"+itemName+"%' and CAST(su.supplier_id as CHAR) like '%"+supplierId+"%' and su.supplier_name like '%"+supplierName+"%' order by sp.stock_purchase_id desc";            
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            stockPurchaseTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            JOptionPane.showMessageDialog(transactionsPanel, e, "Stock Purchase Search Error!", 0);
        }
    }//GEN-LAST:event_stockPurchaseSearchButtonActionPerformed

    private void stockTransferSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stockTransferSearchButtonActionPerformed
        String transferId, itemId, itemName, department, supplierName;
        transferId = stockTransferIdTextField.getText();
        itemId = stockTransferItemIdTextField.getText();
        itemName = stockTransferItemNameTextField.getText();
        department = stockTransferDepartmentTextField.getText();        
        try{                                   
            sqlQuery = "select st.stock_transfer_id as 'Transfer ID', i.item_id as 'Item ID', i.item_name as 'Item Name', st.transfer_qty as 'Trans Qty'"
                    + ", i.item_units as 'Units', st.transfer_department as 'Department', st.transfer_status as 'Status', st.order_date as 'Order Date',"
                    + " st.transfer_date as 'Transfered Date' from stock_transfer st, stock s, item i where st.stock_id = s.stock_id and s.item_id = i.item_id and CAST(st.stock_transfer_id as CHAR) like '%"+transferId+"%' and "
                    + "CAST(i.item_id as CHAR) like '%"+itemId+"%' and i.item_name like '%"+itemName+"%' and st.transfer_department like '%"+department+"%' order by st.stock_transfer_id desc";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            stockTransferTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            JOptionPane.showMessageDialog(transactionsPanel, e, "Stock Transfer Search Error!", 0);
        }
    }//GEN-LAST:event_stockTransferSearchButtonActionPerformed

    private void accountRequestCreditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountRequestCreditButtonActionPerformed
        //Requesting Budget
        String requestAmount = accountRequestAmountTextField.getText();            
        try{
            double amount = Double.parseDouble(requestAmount);
            int confirm = JOptionPane.showConfirmDialog(editItemPanel, "Are you sure you want to request credit?", "Request Credit", 0, 3);
            if(confirm == 0){
                dbaFinance.budgetRequest("Stock", amount);
                JOptionPane.showMessageDialog(accountPanel, "Credit Request succesfully sent.", "Request Sent!", 1);
                accountRequestAmountTextField.setText(null);
            }
        }catch (NumberFormatException e){
            accountRequestAmountTextField.setBackground(new Color(255, 150, 150));
            JOptionPane.showMessageDialog(accountPanel, "Please enter a valid value to request credit.", "Invalid Request Amount!", 0);
            accountRequestAmountTextField.setBackground(new Color(255, 255, 255));
            accountRequestAmountTextField.setText(null);                    
        }           
    }//GEN-LAST:event_accountRequestCreditButtonActionPerformed

    private void itemListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemListTableMouseClicked
        int row = itemListTable.getSelectedRow();                                 //Getting values from the selected row
        itemListItemId = itemListTable.getValueAt(row, 0).toString();       //Get each value as string and assign to variables    
        itemListItemName = itemListTable.getValueAt(row, 1).toString();
        itemListUnits = itemListTable.getValueAt(row, 2).toString();
        itemListDescription = itemListTable.getValueAt(row, 3).toString();
        itemListCategory = itemListTable.getValueAt(row, 4).toString();
        itemListReorder = itemListTable.getValueAt(row, 5).toString();

        editItemIdTextField.setText(itemListItemId);
        editItemNameTextField.setText(itemListItemName);        
        editItemUnitsTextField.setText(itemListUnits);        
        editItemDescriptionTextArea.setText(itemListDescription);        
        editItemCategoryTextField.setText(itemListCategory);        
        editItemReorderLevelTextField.setText(itemListReorder);
    }//GEN-LAST:event_itemListTableMouseClicked

    private void editItemEditItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editItemEditItemButtonActionPerformed
        //Validation
        String itemName = editItemNameTextField.getText();
        String itemDescription = editItemDescriptionTextArea.getText();
        String reOrderString = editItemReorderLevelTextField.getText();
        int reOrder = Integer.parseInt(reOrderString);
        if(itemName.isEmpty())
            editItemNameTextField.setBackground(new Color(255, 150, 150));
        else
            editItemNameTextField.setBackground(new Color(255, 255, 255));
        if(itemDescription.isEmpty())
            editItemDescriptionTextArea.setBackground(new Color(255, 150, 150));
        else
            editItemDescriptionTextArea.setBackground(new Color(255, 255, 255));
        if(reOrderString.isEmpty())
            editItemReorderLevelTextField.setBackground(new Color(255, 150, 150));
        else
            editItemReorderLevelTextField.setBackground(new Color(255, 255, 255));
        if (itemName.isEmpty() || itemDescription.isEmpty() || reOrderString.isEmpty()){
            JOptionPane.showMessageDialog(editItemPanel, "Please fill all the fields to continue.", "Form Incomplete!", 0);
        }else{
            int confirm = JOptionPane.showConfirmDialog(editItemPanel, "Are you sure you want to update this item?", "Update Item", 0, 3);
                if (confirm == 0){ //Checking item availability                       
                        try {//Checking item availability
                            sqlQuery = "select item_id from item where item_name = '"+itemName+"' and item_description = '"+itemDescription+"' and item_reorder_level = "+reOrder+" and item_availability = 'available'";
                            pStatement = conn.prepareStatement(sqlQuery);
                            resultSet = pStatement.executeQuery();
                            if(resultSet.isBeforeFirst()){
                                JOptionPane.showMessageDialog(editItemPanel, "Item data you tried to update is already available.", "Item Already Available!", 2);
                            }else{//Updating item data
                            sqlQuery = "update item set item_name = '"+itemName+"', item_description = '"+itemDescription+"', item_reorder_level = "+reOrder+" where item_id = "+ Integer.parseInt(itemListItemId);
                            pStatement = conn.prepareStatement(sqlQuery);
                            pStatement.execute();
                            JOptionPane.showMessageDialog(editItemPanel, "Item data Successfully Updated.", "Item Updated!", 1);
                            itemsButton.doClick();
                            }
                        }catch(SQLException e){
                            JOptionPane.showMessageDialog(editItemPanel, e, "Item data Update Error!", 0);
                        }
                }
        }        
    }//GEN-LAST:event_editItemEditItemButtonActionPerformed

    private void editItemRemoveItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editItemRemoveItemButtonActionPerformed
        int confirm = JOptionPane.showConfirmDialog(editItemPanel, "Are you sure you want to remove this item?", "Remove Item", 0, 3);
        if (confirm == 0){//Removing item data
            try {
                sqlQuery2 = "update item set item_availability = 'removed' where item_id = "+Integer.parseInt(itemListItemId);
                pStatement = conn.prepareStatement(sqlQuery2);
                pStatement.execute();
                JOptionPane.showMessageDialog(editItemPanel, "Item data Successfully Removed.", "Item Removed!", 1);
                itemsButton.doClick();
            } catch(Exception e){
                JOptionPane.showMessageDialog(editItemPanel, e, "Item data Remove Error!", 0);
            }                
        }        
    }//GEN-LAST:event_editItemRemoveItemButtonActionPerformed

    private void addStockPurchaseItemCategoryComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addStockPurchaseItemCategoryComboBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            addStockPurchaseItemNameComboBox.removeAllItems();
            addStockPurchaseItemIdComboBox.removeAllItems();
            String itemCategory = addStockPurchaseItemCategoryComboBox.getSelectedItem().toString();
            String tableItemCategory;
            for (int r = 0; r < itemListTable.getRowCount(); r++) {
                tableItemCategory = itemListTable.getValueAt(r, 4).toString();
                if (tableItemCategory.equals(itemCategory)) {
                    addStockPurchaseItemNameComboBox.addItem(itemListTable.getValueAt(r, 1).toString());
                    addStockPurchaseItemIdComboBox.addItem(itemListTable.getValueAt(r, 0).toString());
                }
            }
        } 
    }//GEN-LAST:event_addStockPurchaseItemCategoryComboBoxItemStateChanged

    private void supplierListSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierListSearchButtonActionPerformed
        String supplierId, supplierName, contactNo;
        supplierId = supplierListSupplierIdTextField.getText();
        supplierName = supplierListSupplierNameTextField.getText();
        contactNo =  supplierListSupplierContactNumberTextField.getText();
        try{            
            sqlQuery = "select supplier_id as 'Supplier ID', supplier_name as 'Supplier Name', supplier_contact as 'Contact No.', supplier_address as 'Address',"
                    + " supplier_email as 'Email', supplier_description as 'Description', DATE_FORMAT(supplier_reg_date, '%Y-%m-%d %H:%i') as 'Register Date' from"
                    + " supplier where CAST(supplier_id as CHAR) like '%"+supplierId+"%' and supplier_name like '%"+supplierName+"%'"
                    + " and CAST(supplier_contact as CHAR) like '%"+contactNo+"%' and supplier_availability = 'available'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            supplierListTable.setModel(DbUtils.resultSetToTableModel(resultSet)); 
            //Setting column width for supplier name, email, reg date, description, address
            TableColumn column1 = supplierListTable.getColumnModel().getColumn(1);
            column1.setMinWidth(130);
            TableColumn column2 = supplierListTable.getColumnModel().getColumn(4);
            column2.setMinWidth(180);
            TableColumn column3 = supplierListTable.getColumnModel().getColumn(6);
            column3.setMinWidth(110);
            TableColumn column4 = supplierListTable.getColumnModel().getColumn(5);
            column4.setMinWidth(130);
            TableColumn column5 = supplierListTable.getColumnModel().getColumn(3);
            column5.setMinWidth(100);
        }catch(Exception e){
            JOptionPane.showMessageDialog(supplierListPanel, e, "Search Supplier Error!", 0);
        } 
    }//GEN-LAST:event_supplierListSearchButtonActionPerformed

    private void supplierListRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierListRefreshButtonActionPerformed
        supplierListSupplierIdTextField.setText(null);
        supplierListSupplierNameTextField.setText(null);
        supplierListSupplierContactNumberTextField.setText(null);
        suppliersButton.doClick();
    }//GEN-LAST:event_supplierListRefreshButtonActionPerformed

    private void supplierListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierListTableMouseClicked
        int row = supplierListTable.getSelectedRow();   //Getting data from table row
        supplierListSupplierId = supplierListTable.getValueAt(row, 0).toString();
        supplierListSupplierName = supplierListTable.getValueAt(row, 1).toString();
        supplierListSupplierContact = supplierListTable.getValueAt(row, 2).toString();                
        supplierListSupplierAddress = supplierListTable.getValueAt(row, 3).toString();
        supplierListSupplierEmail = supplierListTable.getValueAt(row, 4).toString();  
        supplierListSupplierDescription = supplierListTable.getValueAt(row, 5).toString();
        //Assigning values to relevant variables for editing
        editSupplierIdTextField.setText(supplierListSupplierId);
        editSupplierNameTextField.setText(supplierListSupplierName);
        editSupplierAddressTextArea.setText(supplierListSupplierAddress);
        editSupplierTelephoneTextField.setText(supplierListSupplierContact);
        editSupplierEmailTextField.setText(supplierListSupplierEmail); 
        editSupplierDescriptionTextArea.setText(supplierListSupplierDescription);
    }//GEN-LAST:event_supplierListTableMouseClicked

    private void editSupplierEditSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSupplierEditSupplierButtonActionPerformed
        String supplierName, supplierAddress, supplierEmail, supplierDescription, supplierTelephone;  //Variable declaration      
        int telephone;
        supplierName = editSupplierNameTextField.getText();
        supplierAddress = editSupplierAddressTextArea.getText();
        supplierEmail = editSupplierEmailTextField.getText();
        supplierDescription = editSupplierDescriptionTextArea.getText();
        supplierTelephone = editSupplierTelephoneTextField.getText();
        //validations
        if(supplierName.isEmpty())
            editSupplierNameTextField.setBackground(new Color(255, 150, 150));
        else
            editSupplierNameTextField.setBackground(new Color(255, 255, 255));
        if(supplierAddress.isEmpty())
            editSupplierAddressTextArea.setBackground(new Color(255, 150, 150));
        else
            editSupplierAddressTextArea.setBackground(new Color(255, 255, 255));
        if(supplierEmail.isEmpty())
            editSupplierTelephoneTextField.setBackground(new Color(255, 150, 150));
        else
            editSupplierTelephoneTextField.setBackground(new Color(255, 255, 255));
        if(supplierDescription.isEmpty())
            editSupplierEmailTextField.setBackground(new Color(255, 150, 150));
        else
            editSupplierEmailTextField.setBackground(new Color(255, 255, 255));
        if(supplierTelephone.isEmpty())
            editSupplierDescriptionTextArea.setBackground(new Color(255, 150, 150));
        else{
            editSupplierDescriptionTextArea.setBackground(new Color(255, 255, 255));    
        }
        if(supplierName.isEmpty() || supplierAddress.isEmpty() || supplierEmail.isEmpty() || supplierDescription.isEmpty() || supplierTelephone.isEmpty()){     
            JOptionPane.showMessageDialog(editSupplierPanel, "Please fill all the fields to continue.", "Form Incomplete!", 0);
        }else{
            int confirm = JOptionPane.showConfirmDialog(editSupplierPanel, "Are you sure you want to update this supplier?", "Update Supplier", 0, 3);        //get confirmation for adding supplier to database
            if(confirm == 0){
                try{ 
                    telephone = Integer.parseInt(supplierTelephone);
                    //Checking supplier availability
                    sqlQuery = "select * from supplier where supplier_name = '"+supplierName+"' and supplier_address = '"+supplierAddress+"' and supplier_contact = "+telephone+" and "
                                + "supplier_email = '"+supplierEmail+"' and supplier_description = '"+supplierDescription+"'";
                    pStatement = conn.prepareStatement(sqlQuery);
                    resultSet = pStatement.executeQuery();
                    if(resultSet.isBeforeFirst()){
                        JOptionPane.showMessageDialog(editSupplierPanel, "Supplier data you tried to update is already available.", "Supplier Already Available!", 2);
                    }else{//Inserting validated result to the database
                        sqlQuery = "update supplier set supplier_name = '"+supplierName+"', supplier_address = '"+supplierAddress+"', supplier_contact = "+telephone+", "
                                + "supplier_email = '"+supplierEmail+"', supplier_description = '"+supplierDescription+"' where supplier_id = "+ Integer.parseInt(supplierListSupplierId);
                        pStatement = conn.prepareStatement(sqlQuery);
                        pStatement.execute();
                        JOptionPane.showMessageDialog(editSupplierPanel, "Supplier data Successfully Updated.", "Supplier Updated!", 1);    //print successful message
                        suppliersButton.doClick(); 
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(editSupplierPanel, e, "Supplier data Updation Error!", 0);
                }
            }
        }                
    }//GEN-LAST:event_editSupplierEditSupplierButtonActionPerformed

    private void editSupplierRemoveSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSupplierRemoveSupplierButtonActionPerformed
        int confirm = JOptionPane.showConfirmDialog(editSupplierPanel, "Are you sure you want to remove this supplier?", "Remove Supplier", 0, 3);
        if (confirm == 0){//Removing supplier data
            try {
                sqlQuery2 = "update supplier set supplier_availability = 'removed' where supplier_id = "+Integer.parseInt(supplierListSupplierId);
                pStatement = conn.prepareStatement(sqlQuery2);
                pStatement.execute();
                JOptionPane.showMessageDialog(editSupplierPanel, "Supplier data Successfully Removed.", "Supplier Removed!", 1);
                suppliersButton.doClick();
            } catch(Exception e){
                JOptionPane.showMessageDialog(editSupplierPanel, e, "Supplier data Removal Error!", 0);
            }                
        }         
    }//GEN-LAST:event_editSupplierRemoveSupplierButtonActionPerformed

    private void removedSuppliersSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removedSuppliersSearchButtonActionPerformed
        String supplierId, supplierName, contactNo;
        supplierId = removedSuppliersSupplierIdTextField.getText();
        supplierName = removedSuppliersSupplierNameTextField.getText();
        contactNo =  removedSuppliersSupplierContactNumberTextField.getText();
        try{            
            sqlQuery = "select supplier_id as 'Supplier ID', supplier_name as 'Supplier Name', supplier_contact as 'Contact No.', supplier_address as 'Address',"
                    + " supplier_email as 'Email', supplier_description as 'Description', DATE_FORMAT(supplier_reg_date, '%Y-%m-%d %H:%i') as 'Register Date' from"
                    + " supplier where CAST(supplier_id as CHAR) like '%"+supplierId+"%' and supplier_name like '%"+supplierName+"%'"
                    + " and CAST(supplier_contact as CHAR) like '%"+contactNo+"%' and supplier_availability = 'removed'";
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            removedSuppliersTable.setModel(DbUtils.resultSetToTableModel(resultSet)); 
            //Setting column width for supplier name, email, reg date, description, address
            TableColumn column1 = removedSuppliersTable.getColumnModel().getColumn(1);
            column1.setMinWidth(130);
            TableColumn column2 = removedSuppliersTable.getColumnModel().getColumn(4);
            column2.setMinWidth(180);
            TableColumn column3 = removedSuppliersTable.getColumnModel().getColumn(6);
            column3.setMinWidth(110);
            TableColumn column4 = removedSuppliersTable.getColumnModel().getColumn(5);
            column4.setMinWidth(130);
            TableColumn column5 = removedSuppliersTable.getColumnModel().getColumn(3);
            column5.setMinWidth(100);
        }catch(Exception e){
            JOptionPane.showMessageDialog(removedSuppliersTable, e, "Search Removed Supplier Error!", 0);
        }        
    }//GEN-LAST:event_removedSuppliersSearchButtonActionPerformed

    private void removedSuppliersRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removedSuppliersRefreshButtonActionPerformed
        removedSuppliersSupplierIdTextField.setText(null);
        removedSuppliersSupplierNameTextField.setText(null);
        removedSuppliersSupplierContactNumberTextField.setText(null);
        supplierListRemovedSuppliersButton.doClick();
    }//GEN-LAST:event_removedSuppliersRefreshButtonActionPerformed

    private void removedItemListSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removedItemListSearchButtonActionPerformed
        String itemId, itemName, category;
        itemId = removedItemListItemIdTextField.getText();
        itemName = removedItemListItemNameTextField.getText();
        category = String.valueOf(removedItemListItemCategoryComboBox.getSelectedItem());
        try{
            if(itemListItemCategoryComboBox.getSelectedIndex() == 0){                   //If category is not selected
            sqlQuery = "select i.item_id as 'Item ID', i.item_name as 'Item Name', i.item_units as 'Units', i.item_description as 'Description',"
                    + " ic.item_category_name as 'Category', i.item_reorder_level as 'Re-order Level'  from item i, item_category ic where i.item_category = ic.item_category_id "
                    + "and item_availability = 'removed' and CAST(i.item_id as CHAR) like '%"+ itemId +"%' and i.item_name like '%"+ itemName +"%'";
            }else{                                                                        //If category is selected
            sqlQuery = "select i.item_id as 'Item ID', i.item_name as 'Item Name', i.item_units as 'Units', i.item_description as 'Description', ic.item_category_name"
                    + " as 'Category', i.item_reorder_level as 'Re-order Level'  from item i, item_category ic where i.item_category = ic.item_category_id and item_availability = 'removed'"
                    + " and CAST(i.item_id as CHAR) like '%"+ itemId +"%' and i.item_name like '%"+ itemName +"%' and ic.item_category_name like '%"+ category +"%'";
            }
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            removedItemsTable.setModel(DbUtils.resultSetToTableModel(resultSet));                      
        }catch(Exception e){
            JOptionPane.showMessageDialog(removedItemsPanel, e, "Search Removed Item Query Execution Error!", 0);
        } 
    }//GEN-LAST:event_removedItemListSearchButtonActionPerformed

    private void removedItemListRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removedItemListRefreshButtonActionPerformed
        removedItemListItemIdTextField.setText(null);
        removedItemListItemNameTextField.setText(null);
        removedItemListItemCategoryComboBox.setSelectedIndex(0);
        itemListRemovedItemsButton.doClick();
    }//GEN-LAST:event_removedItemListRefreshButtonActionPerformed

    private void categoriesEditCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriesEditCategoryButtonActionPerformed
        if(itemCategoryId == null){
            JOptionPane.showMessageDialog(categoriesPanel, "Please select an Item Category to continue", "No Item Category Selected!", 2);
        }else{
            int confirm = JOptionPane.showConfirmDialog(categoriesPanel, "Are you sure you want to update this item category?", "Update Item Category", 0, 3);
            if (confirm == 0){
                try{
                    String categoryName = categoriesCategoryNameTextField.getText();
                    sqlQuery = "update item_category set item_category_name = '"+categoryName+"' where item_category_id = "+Integer.parseInt(itemCategoryId);
                    pStatement = conn.prepareStatement(sqlQuery);
                    pStatement.execute();
                    JOptionPane.showMessageDialog(categoriesPanel, "Item Category Successfully Updated.", "Item Category Updated!", 1);
                    categoriesButton.doClick();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(categoriesPanel, e, "Edit Item Category Query Execution Error!", 0);
                }
            }
        }
    }//GEN-LAST:event_categoriesEditCategoryButtonActionPerformed

    private void stockCategoriesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockCategoriesTableMouseClicked
        int row = stockCategoriesTable.getSelectedRow();
        itemCategoryId = stockCategoriesTable.getValueAt(row, 0).toString();
        itemCategoryName = stockCategoriesTable.getValueAt(row, 1).toString();
        categoriesCategoryIdTextField.setText(itemCategoryId);
        categoriesCategoryNameTextField.setText(itemCategoryName);
    }//GEN-LAST:event_stockCategoriesTableMouseClicked

    private void categoriesRemoveCategoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriesRemoveCategoryButtonActionPerformed
        if(itemCategoryId == null){
            JOptionPane.showMessageDialog(categoriesPanel, "Please select an Item Category to continue", "No Item Category Selected!", 2);
        }else{
            int confirm = JOptionPane.showConfirmDialog(categoriesPanel, "Are you sure you want to delete this item category? All Item data will be lost if you press 'Yes'", "Remove Item Category", 0, 3);
            if (confirm == 0){
                try{           
                    sqlQuery = "delete from item_category where item_category_id = "+Integer.parseInt(itemCategoryId);
                    pStatement = conn.prepareStatement(sqlQuery);
                    pStatement.execute();
                    JOptionPane.showMessageDialog(categoriesPanel, "Item Category Successfully Deleted.", "Item Category Deleted!", 1);
                    categoriesButton.doClick();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(categoriesPanel, e, "Delete Item Category Query Execution Error!", 0);
                }
            }
        }
    }//GEN-LAST:event_categoriesRemoveCategoryButtonActionPerformed

    private void userlogSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userlogSearchButtonActionPerformed
        String userId, username, transactionId, transactionType;
        userId = userlogUserIdTextField.getText();
        username = userlogUserNameTextField.getText();
        transactionId = userlogTransactionIdTextField.getText();
        transactionType = userlogTransactionTypeComboBox.getSelectedItem().toString();       
        try{    //Without transaction type selection
            if(transactionType.equals("Select Type")){
            sqlQuery = "select su.stock_userlog_id as 'Userlog ID', su.user_id as 'User ID', u.username as 'Username', su.stock_transaction_type as 'Transaction Type', su.stock_transaction_id as 'Transaction ID',"
                    + " DATE_FORMAT(su.stock_transaction_date, '%Y-%m-%d %H:%m') as 'Transaction Date' from stock_userlog su, system_user u where su.user_id = u.system_user_id and "
                    + "CAST(su.user_id as CHAR) like '%"+userId+"%' and u.username like '%"+username+"%' and CAST(su.stock_transaction_id as CHAR) like '%"+transactionId+"%' order by su.stock_transaction_date desc";  
            }else{//With transaction type selection
            sqlQuery = "select su.stock_userlog_id as 'Userlog ID', su.user_id as 'User ID', u.username as 'Username', su.stock_transaction_type as 'Transaction Type', su.stock_transaction_id as 'Transaction ID',"
                    + " DATE_FORMAT(su.stock_transaction_date, '%Y-%m-%d %H:%m') as 'Transaction Date' from stock_userlog su, system_user u where su.user_id = u.system_user_id and "
                    + "CAST(su.user_id as CHAR) like '%"+userId+"%' and u.username like '%"+username+"%' and CAST(su.stock_transaction_id as CHAR) like '%"+transactionId+"%' and "
                    + "su.stock_transaction_type like '%"+transactionType+"%' order by su.stock_transaction_date desc";               
            }
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            userlogTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            JOptionPane.showMessageDialog(userlogPanel, e, "Userlog data Retrieval Error!", 0);
        }
    }//GEN-LAST:event_userlogSearchButtonActionPerformed

    private void userlogRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userlogRefreshButtonActionPerformed
        userlogUserIdTextField.setText(null);
        userlogUserNameTextField.setText(null);
        userlogTransactionIdTextField.setText(null);
        userlogButton.doClick();
    }//GEN-LAST:event_userlogRefreshButtonActionPerformed

    private void userListSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userListSearchButtonActionPerformed
        String userId, username;
        userId = userListUserIdTextField.getText();
        username = userListUserNameTextField.getText();
        try{    
            sqlQuery = "select system_user_id as 'User ID', employee_id as 'Employee ID', full_name as 'Full Name', username as 'Username', password as 'Password', "
                    + " DATE_FORMAT(last_updated_date, '%Y-%m-%d') as 'Last Updated Date' from system_user where user_availability = 'available' and department = 'Stock' and "
                    + "CAST(system_user_id as CHAR) like '%"+userId+"%' and username like '%"+username+"%' and access_level = 'User'"; 
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            userListTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            JOptionPane.showMessageDialog(userListPanel, e, "User data Retrieval Error!", 0);
        }
    }//GEN-LAST:event_userListSearchButtonActionPerformed

    private void userListRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userListRefreshButtonActionPerformed
        userListUserIdTextField.setText(null);
        userListUserNameTextField.setText(null);
        usersButton.doClick();
    }//GEN-LAST:event_userListRefreshButtonActionPerformed

    private void addUserAddUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserAddUserButtonActionPerformed
        String employeeId, fullName, username, password, accessLevel;
        employeeId = addUserEmployeeIdComboBox.getSelectedItem().toString();
        fullName = addUserFullNameTextArea.getText();
        username = addUserUsernameTextField.getText();
        password = addUserPasswordTextField.getText();
        //validations
        if(employeeId.equals("Select Employee ID"))
            addUserEmployeeIdComboBox.setBackground(new Color(255, 150, 150));
        else
            addUserEmployeeIdComboBox.setBackground(new Color(255, 255, 255));
        if(fullName.isEmpty())
            addUserFullNameTextArea.setBackground(new Color(255, 150, 150));
        else
            addUserFullNameTextArea.setBackground(new Color(255, 255, 255));
        if(username.isEmpty())
            addUserUsernameTextField.setBackground(new Color(255, 150, 150));
        else
            addUserUsernameTextField.setBackground(new Color(255, 255, 255));
        if(password.isEmpty())
            addUserPasswordTextField.setBackground(new Color(255, 150, 150));
        else
            addUserPasswordTextField.setBackground(new Color(255, 255, 255));
        //Displaying Error Message
        if(employeeId.equals("Select Employee ID") || fullName.isEmpty() || username.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(addUserPanel, "Please fill all the fields to continue.", "Form Incomplete!", 0);
        }else{
            try{
                int confirm = JOptionPane.showConfirmDialog(addUserPanel, "Are you sure you want to add an user?", "Add User", 0, 3);        //get confirmation for adding item to database
                if(confirm == 0){//Checking whether the user is already available
                    sqlQuery = "select employee_id from system_user where employee_id = "+Integer.parseInt(employeeId)+" and user_availability = 'available'";
                    pStatement = conn.prepareStatement(sqlQuery);
                    resultSet = pStatement.executeQuery();
                    if(resultSet.isBeforeFirst()){
                        JOptionPane.showMessageDialog(addUserPanel, "User data you added is already available. Please add a new user.", "User Already Available!", 2);
                    }else{//Inserting (Users only) into System User table                                                                                        
                        sqlQuery = "insert into system_user(full_name, username, password, access_level, department, employee_id) "
                                + "values('"+fullName+"', '"+username+"', '"+password+"' ,'User', 'Stock', "+Integer.parseInt(employeeId)+")";
                        pStatement = conn.prepareStatement(sqlQuery);
                        pStatement.execute();
                        addItemClearButton.doClick();                                               //clear data after insertion 
                        JOptionPane.showMessageDialog(addItemPanel, "Item successfully added to the database.", "Item Added!", 1);    //print successful message
                    }
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(addItemPanel, e, "Item Add Error!", 0);
            }
        }
    }//GEN-LAST:event_addUserAddUserButtonActionPerformed

    private void addUserClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserClearButtonActionPerformed
        //Resetting values
        addUserEmployeeIdComboBox.setSelectedIndex(0);
        addUserFullNameTextArea.setText(null);
        addUserUsernameTextField.setText(null); 
        addUserPasswordTextField.setText(null);             
        //Resetting color
        addUserEmployeeIdComboBox.setBackground(new Color(255, 255, 255));
        addUserFullNameTextArea.setBackground(new Color(255, 255, 255));
        addUserUsernameTextField.setBackground(new Color(255, 255, 255));
        addUserPasswordTextField.setBackground(new Color(255, 255, 255));          
    }//GEN-LAST:event_addUserClearButtonActionPerformed

    private void userListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userListTableMouseClicked
        int row = userListTable.getSelectedRow();   //Getting data from table row
        userListUserId = userListTable.getValueAt(row, 0).toString();
        userListEmployeeId = userListTable.getValueAt(row, 1).toString();
        userListFullName = userListTable.getValueAt(row, 2).toString();                
        userListUsername = userListTable.getValueAt(row, 3).toString();
        userListPassword = userListTable.getValueAt(row, 4).toString(); 
        //Assigning values to relevant variables for editing
        editUserIdTextField.setText(userListUserId);
        editEmployeeIdTextField.setText(userListEmployeeId);
        editUserFullNameTextArea.setText(userListFullName);
        editUserUsernameTextField.setText(userListUsername);
        editUserPasswordTextField.setText(userListPassword); 
    }//GEN-LAST:event_userListTableMouseClicked

    private void editUserEditUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserEditUserButtonActionPerformed
        String fullName, username, password;  //Variable declaration      
        fullName = editUserFullNameTextArea.getText();
        username = editUserUsernameTextField.getText();
        password = editUserPasswordTextField.getText();
        //validations
        if(fullName.isEmpty())
            editUserFullNameTextArea.setBackground(new Color(255, 150, 150));
        else
            editUserFullNameTextArea.setBackground(new Color(255, 255, 255));
        if(username.isEmpty())
            editUserUsernameTextField.setBackground(new Color(255, 150, 150));
        else
            editUserUsernameTextField.setBackground(new Color(255, 255, 255));
        if(password.isEmpty())
            editUserPasswordTextField.setBackground(new Color(255, 150, 150));
        else
            editUserPasswordTextField.setBackground(new Color(255, 255, 255));
        if(fullName.isEmpty() || username.isEmpty() || password.isEmpty()){     
            JOptionPane.showMessageDialog(editUserPanel, "Please fill all the fields to continue.", "Form Incomplete!", 0);
        }else{
            int confirm = JOptionPane.showConfirmDialog(editUserPanel, "Are you sure you want to update this user?", "Update User", 0, 3);        //get confirmation for adding supplier to database
            if(confirm == 0){
                try{ 
                    //Checking user availability
                    sqlQuery = "select * from system_user where full_name = '"+fullName+"' and username = '"+username+"'";
                    pStatement = conn.prepareStatement(sqlQuery);
                    resultSet = pStatement.executeQuery();
                    if(resultSet.isBeforeFirst()){
                        JOptionPane.showMessageDialog(editUserPanel, "User data you tried to update is already available.", "User Already Available!", 2);
                    }else{//Inserting validated result to the database
                        sqlQuery = "update system_user set full_name = '"+fullName+"', username = '"+username+"', password = '"+password+"' "
                                   + " where system_user_id = "+Integer.parseInt(userListUserId)+" and employee_id = "+Integer.parseInt(userListEmployeeId);
                        pStatement = conn.prepareStatement(sqlQuery);
                        pStatement.execute();
                        JOptionPane.showMessageDialog(editUserPanel, "User data Successfully Updated.", "User Updated!", 1);    //print successful message
                        usersButton.doClick(); 
                    }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(editUserPanel, e, "User data Updation Error!", 0);
                }
            }
        }
    }//GEN-LAST:event_editUserEditUserButtonActionPerformed

    private void editUserRemoveUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserRemoveUserButtonActionPerformed
        int confirm = JOptionPane.showConfirmDialog(editUserPanel, "Are you sure you want to remove this user?", "Remove User", 0, 3);
        if (confirm == 0){//Removing user data
            try {
                sqlQuery2 = "update system_user set user_availability = 'removed' where system_user_id = "+Integer.parseInt(userListUserId)+" and employee_id = "+Integer.parseInt(userListEmployeeId);
                pStatement = conn.prepareStatement(sqlQuery2);
                pStatement.execute();
                JOptionPane.showMessageDialog(editUserPanel, "User data Successfully Removed.", "User Removed!", 1);
                usersButton.doClick();
            } catch(Exception e){
                JOptionPane.showMessageDialog(editUserPanel, e, "User data Removal Error!", 0);
            }                
        }  
    }//GEN-LAST:event_editUserRemoveUserButtonActionPerformed

    private void removedUsersSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removedUsersSearchButtonActionPerformed
        String userId, username;
        userId = removedUsersUserIdTextField.getText();
        username = removedUsersUserNameTextField.getText();
        try{    
            sqlQuery = "select system_user_id as 'User ID', employee_id as 'Employee ID', full_name as 'Full Name', username as 'Username', password as 'Password', "
                    + " DATE_FORMAT(last_updated_date, '%Y-%m-%d') as 'Last Updated Date' from system_user where user_availability = 'removed' and department = 'Stock' and "
                    + "CAST(system_user_id as CHAR) like '%"+userId+"%' and username like '%"+username+"%' and access_level = 'User'"; 
            pStatement = conn.prepareStatement(sqlQuery);
            resultSet = pStatement.executeQuery();
            removedUsersTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch(Exception e){
            JOptionPane.showMessageDialog(removedUsersPanel, e, "Removed User data Retrieval Error!", 0);
        }
    }//GEN-LAST:event_removedUsersSearchButtonActionPerformed

    private void removedUsersRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removedUsersRefreshButtonActionPerformed
        removedUsersUserIdTextField.setText(null);
        removedUsersUserNameTextField.setText(null);
        userListRemovedUsersButton.doClick();
    }//GEN-LAST:event_removedUsersRefreshButtonActionPerformed
            
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Metal look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Metal (introduced in Java SE 6) is not available, stay with the default look and feel.
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
            java.util.logging.Logger.getLogger(Stock_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stock_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stock_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stock_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stock_Main(USER).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountBalanceLabel;
    private javax.swing.JTextField accountBalanceTextField;
    private javax.swing.JButton accountButton;
    private javax.swing.JLabel accountDepositLabel;
    private javax.swing.JTextField accountDepositTextField;
    private javax.swing.JPanel accountPanel;
    private javax.swing.JLabel accountRequestAmountLabel;
    private javax.swing.JTextField accountRequestAmountTextField;
    private javax.swing.JButton accountRequestCreditButton;
    private javax.swing.JPanel actionPanel;
    private javax.swing.JButton addItemAddItemButton;
    private javax.swing.JComboBox<String> addItemCategoryComboBox;
    private javax.swing.JLabel addItemCategoryLabel;
    private javax.swing.JButton addItemClearButton;
    private javax.swing.JButton addItemDemoButton;
    private javax.swing.JLabel addItemDescriptionLabel;
    private javax.swing.JScrollPane addItemDescriptionScrollPane;
    private javax.swing.JTextArea addItemDescriptionTextArea;
    private javax.swing.JButton addItemItemListButton;
    private javax.swing.JLabel addItemNameLabel;
    private javax.swing.JTextField addItemNameTextField;
    private javax.swing.JPanel addItemPanel;
    private javax.swing.JLabel addItemReorderLevelLabel;
    private javax.swing.JTextField addItemReorderLevelTextField;
    private javax.swing.JComboBox<String> addItemUnitsComboBox;
    private javax.swing.JLabel addItemUnitsLabel;
    private javax.swing.JLabel addStockPurchaseAvailQtyLabel;
    private javax.swing.JTextField addStockPurchaseAvailQtyTextField;
    private javax.swing.JComboBox<String> addStockPurchaseItemCategoryComboBox;
    private javax.swing.JLabel addStockPurchaseItemCategoryLabel;
    private javax.swing.JComboBox<String> addStockPurchaseItemIdComboBox;
    private javax.swing.JLabel addStockPurchaseItemIdLabel;
    private javax.swing.JComboBox<String> addStockPurchaseItemNameComboBox;
    private javax.swing.JLabel addStockPurchaseItemNameLabel;
    private javax.swing.JLabel addStockPurchaseItemQtyLabel;
    private javax.swing.JTextField addStockPurchaseItemQtyTextField;
    private javax.swing.JLabel addStockPurchaseItemUnitPriceLabel;
    private javax.swing.JTextField addStockPurchaseItemUnitPriceTextField;
    private javax.swing.JLabel addStockPurchaseItemUnitsLabel;
    private javax.swing.JTextField addStockPurchaseItemUnitsTextField;
    private javax.swing.JComboBox<String> addStockPurchaseSupplierIdComboBox;
    private javax.swing.JLabel addStockPurchaseSupplierIdLabel;
    private javax.swing.JComboBox<String> addStockPurchaseSupplierNameComboBox;
    private javax.swing.JLabel addStockPurchaseSupplierNameLabel;
    private javax.swing.JLabel addStockPurchaseTotalAmountLabel;
    private javax.swing.JTextField addStockPurchaseTotalAmountTextField;
    private javax.swing.JButton addSupplierAddSupplierButton;
    private javax.swing.JLabel addSupplierAddressLabel;
    private javax.swing.JScrollPane addSupplierAddressScrollPane;
    private javax.swing.JTextArea addSupplierAddressTextArea;
    private javax.swing.JButton addSupplierClearButton;
    private javax.swing.JButton addSupplierDemoButton;
    private javax.swing.JLabel addSupplierDescriptionLabel;
    private javax.swing.JScrollPane addSupplierDescriptionScrollPane;
    private javax.swing.JTextArea addSupplierDescriptionTextArea;
    private javax.swing.JLabel addSupplierEmailLabel;
    private javax.swing.JTextField addSupplierEmailTextField;
    private javax.swing.JLabel addSupplierNameLabel;
    private javax.swing.JTextField addSupplierNameTextField;
    private javax.swing.JPanel addSupplierPanel;
    private javax.swing.JButton addSupplierSupplierListButton;
    private javax.swing.JLabel addSupplierTelephoneLabel;
    private javax.swing.JTextField addSupplierTelephoneTextField;
    private javax.swing.JButton addUserAddUserButton;
    private javax.swing.JButton addUserClearButton;
    private javax.swing.JComboBox<String> addUserEmployeeIdComboBox;
    private javax.swing.JLabel addUserEmployeeIdLabel;
    private javax.swing.JLabel addUserFullNameLabel;
    private javax.swing.JScrollPane addUserFullNameScrollPane;
    private javax.swing.JScrollPane addUserFullNameScrollPane2;
    private javax.swing.JTextArea addUserFullNameTextArea;
    private javax.swing.JPanel addUserPanel;
    private javax.swing.JLabel addUserPasswordLabel;
    private javax.swing.JTextField addUserPasswordTextField;
    private javax.swing.JButton addUserUserListButton;
    private javax.swing.JLabel addUserUsernameLabel;
    private javax.swing.JTextField addUserUsernameTextField;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton categoriesAddCategoryButton;
    private javax.swing.JButton categoriesButton;
    private javax.swing.JLabel categoriesCategoryIdLabel;
    private javax.swing.JTextField categoriesCategoryIdTextField;
    private javax.swing.JLabel categoriesCategoryNameLabel;
    private javax.swing.JTextField categoriesCategoryNameTextField;
    private javax.swing.JButton categoriesEditCategoryButton;
    private javax.swing.JPanel categoriesPanel;
    private javax.swing.JButton categoriesRemoveCategoryButton;
    private javax.swing.JLabel editEmployeeIdLabel;
    private javax.swing.JTextField editEmployeeIdTextField;
    private javax.swing.JLabel editItemCategoryLabel;
    private javax.swing.JTextField editItemCategoryTextField;
    private javax.swing.JLabel editItemDescriptionLabel;
    private javax.swing.JScrollPane editItemDescriptionScrollPane;
    private javax.swing.JTextArea editItemDescriptionTextArea;
    private javax.swing.JButton editItemEditItemButton;
    private javax.swing.JLabel editItemIdLabel;
    private javax.swing.JTextField editItemIdTextField;
    private javax.swing.JButton editItemItemListButton;
    private javax.swing.JLabel editItemNameLabel;
    private javax.swing.JTextField editItemNameTextField;
    private javax.swing.JPanel editItemPanel;
    private javax.swing.JButton editItemRemoveItemButton;
    private javax.swing.JLabel editItemReorderLevelLabel;
    private javax.swing.JTextField editItemReorderLevelTextField;
    private javax.swing.JLabel editItemUnitsLabel;
    private javax.swing.JTextField editItemUnitsTextField;
    private javax.swing.JLabel editSupplierAddressLabel;
    private javax.swing.JScrollPane editSupplierAddressScrollPane;
    private javax.swing.JTextArea editSupplierAddressTextArea;
    private javax.swing.JLabel editSupplierDescriptionLabel;
    private javax.swing.JScrollPane editSupplierDescriptionScrollPane;
    private javax.swing.JTextArea editSupplierDescriptionTextArea;
    private javax.swing.JButton editSupplierEditSupplierButton;
    private javax.swing.JLabel editSupplierEmailLabel;
    private javax.swing.JTextField editSupplierEmailTextField;
    private javax.swing.JLabel editSupplierIdLabel;
    private javax.swing.JTextField editSupplierIdTextField;
    private javax.swing.JLabel editSupplierNameLabel;
    private javax.swing.JTextField editSupplierNameTextField;
    private javax.swing.JPanel editSupplierPanel;
    private javax.swing.JButton editSupplierRemoveSupplierButton;
    private javax.swing.JButton editSupplierSupplierListButton;
    private javax.swing.JLabel editSupplierTelephoneLabel;
    private javax.swing.JTextField editSupplierTelephoneTextField;
    private javax.swing.JButton editUserEditUserButton;
    private javax.swing.JLabel editUserFullNameLabel;
    private javax.swing.JTextArea editUserFullNameTextArea;
    private javax.swing.JLabel editUserIdLabel;
    private javax.swing.JTextField editUserIdTextField;
    private javax.swing.JPanel editUserPanel;
    private javax.swing.JLabel editUserPasswordLabel;
    private javax.swing.JTextField editUserPasswordTextField;
    private javax.swing.JButton editUserRemoveUserButton;
    private javax.swing.JButton editUserUserListButton;
    private javax.swing.JLabel editUserUsernameLabel;
    private javax.swing.JTextField editUserUsernameTextField;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JButton itemListAddItemButton;
    private javax.swing.JButton itemListEditItemButton;
    private javax.swing.JComboBox<String> itemListItemCategoryComboBox;
    private javax.swing.JTextField itemListItemIdTextField;
    private javax.swing.JTextField itemListItemNameTextField;
    private javax.swing.JPanel itemListPanel;
    private javax.swing.JButton itemListRefreshButton;
    private javax.swing.JButton itemListRemovedItemsButton;
    private javax.swing.JScrollPane itemListScrollPane;
    private javax.swing.JButton itemListSearchButton;
    private javax.swing.JTable itemListTable;
    private javax.swing.JTabbedPane itemTabbedPane;
    private javax.swing.JButton itemsButton;
    private javax.swing.JPanel itemsPanel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton orderDemoButton;
    private javax.swing.JTextField orderIdTextField;
    private javax.swing.JComboBox<String> orderItemCategoryComboBox;
    private javax.swing.JTextField orderItemDepartmentTextField;
    private javax.swing.JTextField orderItemIdTextField;
    private javax.swing.JTextField orderItemNameTextField;
    private javax.swing.JPanel orderListPanel;
    private javax.swing.JButton orderNextButton;
    private javax.swing.JButton orderRefreshButton;
    private javax.swing.JButton orderSearchButton;
    private javax.swing.JButton ordersButton;
    private javax.swing.JPanel ordersPanel;
    private javax.swing.JScrollPane ordersScrollPane;
    private javax.swing.JTabbedPane ordersTabbedPane;
    private javax.swing.JTable ordersTable;
    private javax.swing.JButton purchaseButton;
    private javax.swing.JPanel purchasePanel;
    private javax.swing.JComboBox<String> removedItemListItemCategoryComboBox;
    private javax.swing.JTextField removedItemListItemIdTextField;
    private javax.swing.JTextField removedItemListItemNameTextField;
    private javax.swing.JButton removedItemListRefreshButton;
    private javax.swing.JButton removedItemListSearchButton;
    private javax.swing.JButton removedItemsItemListButton;
    private javax.swing.JPanel removedItemsPanel;
    private javax.swing.JScrollPane removedItemsScrollPane;
    private javax.swing.JTable removedItemsTable;
    private javax.swing.JButton removedSuppliersAddSupplierBackButton;
    private javax.swing.JPanel removedSuppliersPanel;
    private javax.swing.JButton removedSuppliersRefreshButton;
    private javax.swing.JScrollPane removedSuppliersScrollPane;
    private javax.swing.JButton removedSuppliersSearchButton;
    private javax.swing.JTextField removedSuppliersSupplierContactNumberTextField;
    private javax.swing.JTextField removedSuppliersSupplierIdTextField;
    private javax.swing.JButton removedSuppliersSupplierListButton;
    private javax.swing.JTextField removedSuppliersSupplierNameTextField;
    private javax.swing.JTable removedSuppliersTable;
    private javax.swing.JPanel removedUsersPanel;
    private javax.swing.JButton removedUsersRefreshButton;
    private javax.swing.JScrollPane removedUsersScrollPane;
    private javax.swing.JButton removedUsersSearchButton;
    private javax.swing.JTable removedUsersTable;
    private javax.swing.JTextField removedUsersUserIdTextField;
    private javax.swing.JButton removedUsersUserListButton;
    private javax.swing.JTextField removedUsersUserNameTextField;
    private javax.swing.JButton stockButton;
    private javax.swing.JScrollPane stockCategoriesScrollPane;
    private javax.swing.JTable stockCategoriesTable;
    private javax.swing.JTextField stockIdTextField;
    private javax.swing.JComboBox<String> stockItemCategoryComboBox;
    private javax.swing.JTextField stockItemIdTextField;
    private javax.swing.JTextField stockItemNameTextField;
    private javax.swing.JDesktopPane stockMainDesktopPane;
    private javax.swing.JPanel stockMainPanel;
    private javax.swing.JPanel stockPanel;
    private javax.swing.JButton stockPurchaseCalculateTotalButton;
    private javax.swing.JButton stockPurchaseClearButton;
    private javax.swing.JButton stockPurchaseConfirmPurchaseButton;
    private javax.swing.JTextField stockPurchaseIdTextField;
    private javax.swing.JTextField stockPurchaseItemIdTextField;
    private javax.swing.JTextField stockPurchaseItemNameTextField;
    private javax.swing.JPanel stockPurchasePanel;
    private javax.swing.JButton stockPurchaseRefreshButton;
    private javax.swing.JScrollPane stockPurchaseScrollPane;
    private javax.swing.JButton stockPurchaseSearchButton;
    private javax.swing.JTextField stockPurchaseSupplierIdTextField;
    private javax.swing.JTextField stockPurchaseSupplierNameTextField;
    private javax.swing.JTable stockPurchaseTable;
    private javax.swing.JButton stockRefreshButton;
    private javax.swing.JScrollPane stockScrollPane;
    private javax.swing.JButton stockSearchButton;
    private javax.swing.JTable stockTable;
    private javax.swing.JTabbedPane stockTransactionsTabbedPane;
    private javax.swing.JTextField stockTransferDepartmentTextField;
    private javax.swing.JTextField stockTransferIdTextField;
    private javax.swing.JTextField stockTransferItemIdTextField;
    private javax.swing.JTextField stockTransferItemNameTextField;
    private javax.swing.JPanel stockTransferPanel;
    private javax.swing.JButton stockTransferRefreshButton;
    private javax.swing.JScrollPane stockTransferScrollPane;
    private javax.swing.JButton stockTransferSearchButton;
    private javax.swing.JTable stockTransferTable;
    private javax.swing.JButton supplierListAddSupplierButton;
    private javax.swing.JButton supplierListEditSupplierButton;
    private javax.swing.JPanel supplierListPanel;
    private javax.swing.JButton supplierListRefreshButton;
    private javax.swing.JButton supplierListRemovedSuppliersButton;
    private javax.swing.JScrollPane supplierListScrollPane;
    private javax.swing.JButton supplierListSearchButton;
    private javax.swing.JTextField supplierListSupplierContactNumberTextField;
    private javax.swing.JTextField supplierListSupplierIdTextField;
    private javax.swing.JTextField supplierListSupplierNameTextField;
    private javax.swing.JTable supplierListTable;
    private javax.swing.JTabbedPane supplierTabbedPane;
    private javax.swing.JButton suppliersButton;
    private javax.swing.JPanel suppliersPanel;
    private javax.swing.JButton transactionsButton;
    private javax.swing.JPanel transactionsPanel;
    private javax.swing.JLabel transferAvailQtyLabel;
    private javax.swing.JTextField transferAvailQtyTextField;
    private javax.swing.JLabel transferDepartmentLabel;
    private javax.swing.JTextField transferDepartmentTextField;
    private javax.swing.JLabel transferItemCategoryLabel;
    private javax.swing.JTextField transferItemCategoryTextField;
    private javax.swing.JLabel transferItemIdLabel;
    private javax.swing.JTextField transferItemIdTextField;
    private javax.swing.JLabel transferItemNameLabel;
    private javax.swing.JTextField transferItemNameTextField;
    private javax.swing.JButton transferOrderButton;
    private javax.swing.JLabel transferOrderDateLabel;
    private javax.swing.JTextField transferOrderDateTextField;
    private javax.swing.JLabel transferOrderIdLabel;
    private javax.swing.JTextField transferOrderIdTextField;
    private javax.swing.JButton transferOrderListButton;
    private javax.swing.JLabel transferOrderQtyLabel;
    private javax.swing.JTextField transferOrderQtyTextField;
    private javax.swing.JPanel transferStockPanel;
    private javax.swing.JLabel transferUnitsLabel;
    private javax.swing.JTextField transferUnitsTextField;
    private javax.swing.JLabel userLabel;
    private javax.swing.JButton userListAddUserButton;
    private javax.swing.JButton userListEditUserButton;
    private javax.swing.JPanel userListPanel;
    private javax.swing.JButton userListRefreshButton;
    private javax.swing.JButton userListRemovedUsersButton;
    private javax.swing.JScrollPane userListScrollPane;
    private javax.swing.JButton userListSearchButton;
    private javax.swing.JTable userListTable;
    private javax.swing.JTextField userListUserIdTextField;
    private javax.swing.JTextField userListUserNameTextField;
    private javax.swing.JPanel userPanel;
    private javax.swing.JTabbedPane userTabbedPane;
    private javax.swing.JButton userlogButton;
    private javax.swing.JPanel userlogPanel;
    private javax.swing.JButton userlogRefreshButton;
    private javax.swing.JScrollPane userlogScrollPane;
    private javax.swing.JButton userlogSearchButton;
    private javax.swing.JTable userlogTable;
    private javax.swing.JTextField userlogTransactionIdTextField;
    private javax.swing.JComboBox<String> userlogTransactionTypeComboBox;
    private javax.swing.JTextField userlogUserIdTextField;
    private javax.swing.JTextField userlogUserNameTextField;
    private javax.swing.JButton usersButton;
    // End of variables declaration//GEN-END:variables
}
