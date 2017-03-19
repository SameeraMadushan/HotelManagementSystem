package glen.transport;

import com.sun.glass.events.KeyEvent;
import glen.DBconnect;
import glen.System_Login;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.Calendar;
import java.util.logging.Level;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class Transport_Manager extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Transport_Manager() {
        initComponents();

        conn = DBconnect.ConnectDB();
        this.setLocationRelativeTo(null); //Setting to display in the center of screen
        
        tableup_pac();//package
        tableloadcus();//customer
        tableloadcus_se();
        tableloadcus_up();//customer
        tableup_pac1();//package
        tablese_pac();//package
        tableup_pac();//package
        tableview();//view
        tableviewupdate();//view
        tableviewsearch();//view
        tablenewu();//new
        tablenewv();//new
        tableoffersu();
        tableoffersv();
        tabledv();//driver
        tabledu();

        panalmenu.setVisible(true);
        panalcontent.setVisible(true);
        panalhead.setVisible(true);
        PACKAGE.setVisible(false);
        CUSTOMER.setVisible(false);
        PAYMENT.setVisible(false);
        VIEWBOOKIN.setVisible(false);
        REPORTS1.setVisible(false);
        NEW.setVisible(false);
        OFFERS.setVisible(false);
        HOME.setVisible(true);
        DRIVER.setVisible(false);
        GUIDE.setVisible(false);
        mix.setVisible(true);
        mix.setText("<< HOME Page >>");
        homel.setVisible(true);
        date();

        Calendar now = Calendar.getInstance();

        int second = now.get(Calendar.SECOND);
        int min = now.get(Calendar.MINUTE);
        int hr = now.get(Calendar.HOUR);
        String t = (hr + ":" + min + ":" + second);
        time.setText(t);
        // date----------------------------------------------------------
        int month = now.get(Calendar.MONTH);
        int date = now.get(Calendar.DATE);
        int year = now.get(Calendar.YEAR);
        String Date = date + " - " + month + " - " + year;
        date1.setText(Date);

    }

    public void tableup_pac() {
        try {
            String sql = " SELECT *  FROM trans_package";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jTable11.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tableup_pac1() {
        try {
            String sql = " SELECT *  FROM trans_package";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            pac_view.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tableloadcus() {
        try {
            String sql = "select * from trans_customer";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tableloadcus_up() {
        try {
            String sql = "select * from trans_customer";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tableloadcus_se() {
        try {
            String sql = "select * from trans_customer";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable22.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tablese_pac() {
        try {
            String sql = " SELECT *  FROM trans_package";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            sea_pac.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tableview() {
        try {
            String sql = "select * from trans_book";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable8.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tableviewupdate() {
        try {
            String sql = "select * from trans_book";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable18.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tableviewsearch() {
        try {
            String sql = "select * from trans_book";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable19.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tablenewv() {
        try {
            String sql = " SELECT * FROM trans_customize ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable21.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tablenewu() {
        try {
            String sql = " SELECT * FROM trans_customize ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable20.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tableoffersv() {
        try {
            String sql = " SELECT * FROM trans_offers ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable6.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tableoffersu() {
        try {
            String sql = " SELECT * FROM trans_offers ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tabledv() {
        try {
            String sql = " SELECT * FROM trans_driver ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable23.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void tabledu() {
        try {
            String sql = " SELECT * FROM trans_driver ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable25.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    void date() {

        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar now = Calendar.getInstance();
                    int second = now.get(Calendar.SECOND);
                    int min = now.get(Calendar.MINUTE);
                    int hr = now.get(Calendar.HOUR);
                    String t = (hr + ":" + min + ":" + second);
                    time.setText(t);
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        java.util.logging.Logger.getLogger(Transport_Manager.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        };
        clock.start();
    }

    public void panelshift(String buttonName) {
        switch (buttonName) {

            case "HOME":
                HOME.setVisible(true);
                PACKAGE.setVisible(false);
                CUSTOMER.setVisible(false);
                PAYMENT.setVisible(false);
                VIEWBOOKIN.setVisible(false);
                REPORTS1.setVisible(false);
                NEW.setVisible(false);
                OFFERS.setVisible(false);
                DRIVER.setVisible(false);
                GUIDE.setVisible(false);
                mix.setVisible(true);
                mix.setText("<< HOME Page >>");
                break;

            case "PACKAGES":
                HOME.setVisible(false);
                PACKAGE.setVisible(true);
                CUSTOMER.setVisible(false);
                PAYMENT.setVisible(false);
                VIEWBOOKIN.setVisible(false);
                REPORTS1.setVisible(false);
                NEW.setVisible(false);
                OFFERS.setVisible(false);
                DRIVER.setVisible(false);
                GUIDE.setVisible(false);
                mix.setVisible(true);
                mix.setText("<< PACKAGES Page >>");
                break;

            case "CUSTOMER":
                PACKAGE.setVisible(false);
                CUSTOMER.setVisible(true);
                PAYMENT.setVisible(false);
                VIEWBOOKIN.setVisible(false);
                NEW.setVisible(false);
                REPORTS1.setVisible(false);
                OFFERS.setVisible(false);
                HOME.setVisible(false);
                DRIVER.setVisible(false);
                GUIDE.setVisible(false);
                mix.setVisible(true);
                mix.setText("<< CUSTOMER Page >>");
                break;

            case "PAYMENT":
                PACKAGE.setVisible(false);
                CUSTOMER.setVisible(false);
                PAYMENT.setVisible(true);
                VIEWBOOKIN.setVisible(false);
                NEW.setVisible(false);
                REPORTS1.setVisible(false);
                OFFERS.setVisible(false);
                HOME.setVisible(false);
                DRIVER.setVisible(false);
                GUIDE.setVisible(false);
                mix.setVisible(true);
                mix.setText("<< PAYMENT Page >>");
                break;

            case "VIEW BOOKINGS":
                PACKAGE.setVisible(false);
                CUSTOMER.setVisible(false);
                PAYMENT.setVisible(false);
                VIEWBOOKIN.setVisible(true);
                NEW.setVisible(false);
                REPORTS1.setVisible(false);
                OFFERS.setVisible(false);
                HOME.setVisible(false);
                DRIVER.setVisible(false);
                GUIDE.setVisible(false);
                mix.setVisible(true);
                mix.setText("<< VIEW BOOKINGS Page >>");
                break;

            case "REPORTS":
                PACKAGE.setVisible(false);
                CUSTOMER.setVisible(false);
                PAYMENT.setVisible(false);
                VIEWBOOKIN.setVisible(false);
                REPORTS1.setVisible(true);
                NEW.setVisible(false);
                OFFERS.setVisible(false);
                HOME.setVisible(false);
                DRIVER.setVisible(false);
                GUIDE.setVisible(false);
                mix.setVisible(true);
                mix.setText("<< REPORTS Page >>");
                break;

            case "CREATE NEW":
                PACKAGE.setVisible(false);
                CUSTOMER.setVisible(false);
                PAYMENT.setVisible(false);
                VIEWBOOKIN.setVisible(false);
                REPORTS1.setVisible(false);
                NEW.setVisible(true);
                OFFERS.setVisible(false);
                HOME.setVisible(false);
                DRIVER.setVisible(false);
                GUIDE.setVisible(false);
                mix.setVisible(true);
                mix.setText("<< CREATE NEW Page >>");
                break;

            case "OFFERS":
                PACKAGE.setVisible(false);
                CUSTOMER.setVisible(false);
                PAYMENT.setVisible(false);
                VIEWBOOKIN.setVisible(false);
                REPORTS1.setVisible(false);
                NEW.setVisible(false);
                OFFERS.setVisible(true);
                HOME.setVisible(false);
                DRIVER.setVisible(false);
                GUIDE.setVisible(false);
                mix.setVisible(true);
                mix.setText("<< OFFERS Page >>");
                break;

            case "DRIVER":
                PACKAGE.setVisible(false);
                CUSTOMER.setVisible(false);
                PAYMENT.setVisible(false);
                VIEWBOOKIN.setVisible(false);
                REPORTS1.setVisible(false);
                NEW.setVisible(false);
                OFFERS.setVisible(false);
                HOME.setVisible(false);
                DRIVER.setVisible(true);
                GUIDE.setVisible(false);
                mix.setVisible(true);
                mix.setText("<< DRIVER Page >>");
                break;

            case "GUIDE":
                PACKAGE.setVisible(false);
                CUSTOMER.setVisible(false);
                PAYMENT.setVisible(false);
                VIEWBOOKIN.setVisible(false);
                REPORTS1.setVisible(false);
                NEW.setVisible(false);
                OFFERS.setVisible(false);
                HOME.setVisible(false);
                DRIVER.setVisible(false);
                GUIDE.setVisible(true);
                mix.setVisible(true);
                mix.setText("<< GUIDE Page >>");
                break;

            default:
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel11 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        panalmenu = new javax.swing.JPanel();
        home = new javax.swing.JButton();
        pakageB = new javax.swing.JButton();
        customer = new javax.swing.JButton();
        payment = new javax.swing.JButton();
        viewBook = new javax.swing.JButton();
        createNew = new javax.swing.JButton();
        reports = new javax.swing.JButton();
        offers1 = new javax.swing.JButton();
        driver = new javax.swing.JButton();
        guide = new javax.swing.JButton();
        panalhead = new javax.swing.JPanel();
        homel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        mix = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        date1 = new javax.swing.JLabel();
        panalcontent = new javax.swing.JPanel();
        HOME = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        CUSTOMER = new javax.swing.JPanel();
        tabcustomer = new javax.swing.JTabbedPane();
        addcus = new javax.swing.JPanel();
        namecus = new javax.swing.JLabel();
        niccus = new javax.swing.JLabel();
        addresscus = new javax.swing.JLabel();
        telephonecus = new javax.swing.JLabel();
        namecust = new javax.swing.JTextField();
        emailt = new javax.swing.JTextField();
        telephonecust = new javax.swing.JTextField();
        addresscust = new javax.swing.JTextField();
        addcusb = new javax.swing.JButton();
        resetcusb = new javax.swing.JButton();
        niccust1 = new javax.swing.JTextField();
        emaill = new javax.swing.JLabel();
        demo = new javax.swing.JButton();
        viewcus = new javax.swing.JPanel();
        idvcus = new javax.swing.JLabel();
        namevcus = new javax.swing.JLabel();
        nicvcus = new javax.swing.JLabel();
        addressvcus = new javax.swing.JLabel();
        telephonevcus = new javax.swing.JLabel();
        name_vcust = new javax.swing.JTextField();
        id_vcust = new javax.swing.JTextField();
        nic_vcut = new javax.swing.JTextField();
        telephone_vcust = new javax.swing.JTextField();
        address_vcust = new javax.swing.JTextField();
        table_vcus = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        emailvcus1 = new javax.swing.JLabel();
        email_vcust1 = new javax.swing.JTextField();
        searchcus = new javax.swing.JPanel();
        jComboBox3 = new javax.swing.JComboBox<>();
        table_ucust1 = new javax.swing.JScrollPane();
        jTable22 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        va2cuse = new javax.swing.JTextField();
        va1cuse = new javax.swing.JTextField();
        updatecus = new javax.swing.JPanel();
        id_ucus = new javax.swing.JLabel();
        name_ucus = new javax.swing.JLabel();
        address_ucus = new javax.swing.JLabel();
        nic_ucus = new javax.swing.JLabel();
        telephone_ucus = new javax.swing.JLabel();
        name_ucust = new javax.swing.JTextField();
        id_ucust = new javax.swing.JTextField();
        nic_ucust = new javax.swing.JTextField();
        telephone_ucust = new javax.swing.JTextField();
        adderss_ucust = new javax.swing.JTextField();
        table_ucust = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        update_ucust = new javax.swing.JButton();
        rest_ucust = new javax.swing.JButton();
        email_ucust = new javax.swing.JTextField();
        nic_ucus1 = new javax.swing.JLabel();
        delete_cus = new javax.swing.JButton();
        PACKAGE = new javax.swing.JPanel();
        tabpackage = new javax.swing.JTabbedPane();
        view = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        id_upact1 = new javax.swing.JTextField();
        name_upac1 = new javax.swing.JLabel();
        name_upact1 = new javax.swing.JTextField();
        destiantion_upac1 = new javax.swing.JLabel();
        destination_upact1 = new javax.swing.JTextField();
        activity_upac1 = new javax.swing.JLabel();
        activity_upact1 = new javax.swing.JTextField();
        day_upac1 = new javax.swing.JLabel();
        day_upact1 = new javax.swing.JTextField();
        fee_uapc1 = new javax.swing.JLabel();
        fee_upact1 = new javax.swing.JTextField();
        table_upac1 = new javax.swing.JScrollPane();
        pac_view = new javax.swing.JTable();
        search = new javax.swing.JPanel();
        day_upac2 = new javax.swing.JLabel();
        val2pac = new javax.swing.JTextField();
        val1pac = new javax.swing.JTextField();
        fee_uapc2 = new javax.swing.JLabel();
        table_upac2 = new javax.swing.JScrollPane();
        sea_pac = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        bookpacage = new javax.swing.JPanel();
        satus_pact = new javax.swing.JTextField();
        date_pacd = new com.toedter.calendar.JDateChooser();
        customerid_pac = new javax.swing.JLabel();
        date_pac = new javax.swing.JLabel();
        status_pac = new javax.swing.JLabel();
        customerid_pact = new javax.swing.JTextField();
        no_pac = new javax.swing.JLabel();
        name_pac = new javax.swing.JLabel();
        book_pacb = new javax.swing.JButton();
        name_pact = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        no_pact = new javax.swing.JTextField();
        add_pack = new javax.swing.JPanel();
        name_apac = new javax.swing.JLabel();
        name_apact = new javax.swing.JTextField();
        destination_apac = new javax.swing.JLabel();
        destination_apact = new javax.swing.JTextField();
        activity_apac = new javax.swing.JLabel();
        activity_apact = new javax.swing.JTextField();
        fee_apact = new javax.swing.JTextField();
        fee_apac = new javax.swing.JLabel();
        day_apact = new javax.swing.JTextField();
        day_apcc = new javax.swing.JLabel();
        add_apacd = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        uppackage = new javax.swing.JPanel();
        name_upac = new javax.swing.JLabel();
        name_upact = new javax.swing.JTextField();
        destination_upact = new javax.swing.JTextField();
        destiantion_upac = new javax.swing.JLabel();
        activity_upac = new javax.swing.JLabel();
        activity_upact = new javax.swing.JTextField();
        day_upac = new javax.swing.JLabel();
        day_upact = new javax.swing.JTextField();
        fee_upact = new javax.swing.JTextField();
        fee_uapc = new javax.swing.JLabel();
        table_upac = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        update_upacb = new javax.swing.JButton();
        delete_upab = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        id_upact = new javax.swing.JTextField();
        PAYMENT = new javax.swing.JPanel();
        tabpayment = new javax.swing.JTabbedPane();
        addpay = new javax.swing.JPanel();
        date_pay = new javax.swing.JLabel();
        balance_pay = new javax.swing.JLabel();
        customer_pay = new javax.swing.JLabel();
        methord_pay = new javax.swing.JLabel();
        customerid_payt = new javax.swing.JTextField();
        methord_payt = new javax.swing.JTextField();
        paid_payt = new javax.swing.JTextField();
        total_payt = new javax.swing.JTextField();
        add_payb = new javax.swing.JButton();
        reset_payb = new javax.swing.JButton();
        date_paydate = new com.toedter.calendar.JDateChooser();
        total_pay = new javax.swing.JLabel();
        balance_payt = new javax.swing.JTextField();
        paid_pay = new javax.swing.JLabel();
        viewpay = new javax.swing.JPanel();
        search_vpayb = new javax.swing.JButton();
        id_vpay = new javax.swing.JLabel();
        total_vpay = new javax.swing.JLabel();
        paid_vpay = new javax.swing.JLabel();
        customer_vpay = new javax.swing.JLabel();
        methord_vpay = new javax.swing.JLabel();
        total_vpayt = new javax.swing.JTextField();
        id_vpayt = new javax.swing.JTextField();
        paid_vpayt = new javax.swing.JTextField();
        methord_vpayt = new javax.swing.JTextField();
        customer_vpayt = new javax.swing.JTextField();
        table_vpay = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        date_vpay = new javax.swing.JLabel();
        date_vpayt = new javax.swing.JTextField();
        balance_vpay = new javax.swing.JLabel();
        balance_vpayt = new javax.swing.JTextField();
        updatepay = new javax.swing.JPanel();
        total_upay = new javax.swing.JLabel();
        customerid_upay = new javax.swing.JLabel();
        upadete_upay = new javax.swing.JButton();
        methord_upay = new javax.swing.JLabel();
        id_upay = new javax.swing.JLabel();
        pay_upay = new javax.swing.JLabel();
        balance_upayt = new javax.swing.JTextField();
        id_upayt = new javax.swing.JTextField();
        total_upayt = new javax.swing.JTextField();
        methord_upayt = new javax.swing.JTextField();
        date_upayt = new javax.swing.JTextField();
        table_upay = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        date_upay = new javax.swing.JLabel();
        paid_upayt = new javax.swing.JTextField();
        balance_upay = new javax.swing.JLabel();
        customerid_upayt = new javax.swing.JTextField();
        reset_upayb = new javax.swing.JButton();
        VIEWBOOKIN = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        packagename_viewt = new javax.swing.JTextField();
        packagename_view = new javax.swing.JLabel();
        customerid_view = new javax.swing.JLabel();
        customerid_viewt = new javax.swing.JTextField();
        no_viewt = new javax.swing.JTextField();
        no_view = new javax.swing.JLabel();
        date_viewt = new javax.swing.JTextField();
        date_view = new javax.swing.JLabel();
        table_view = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        customerid_view1 = new javax.swing.JLabel();
        id_view = new javax.swing.JTextField();
        status_view = new javax.swing.JTextField();
        date_view1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        val2sea = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        val1sea = new javax.swing.JTextField();
        table_view2 = new javax.swing.JScrollPane();
        jTable19 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        packagename_viewt1 = new javax.swing.JTextField();
        packagename_view1 = new javax.swing.JLabel();
        customerid_view2 = new javax.swing.JLabel();
        customerid_viewt1 = new javax.swing.JTextField();
        no_viewt1 = new javax.swing.JTextField();
        no_view1 = new javax.swing.JLabel();
        date_view2 = new javax.swing.JLabel();
        table_view1 = new javax.swing.JScrollPane();
        jTable18 = new javax.swing.JTable();
        search_viewb1 = new javax.swing.JButton();
        customerid_view3 = new javax.swing.JLabel();
        id_view1 = new javax.swing.JTextField();
        status_view1 = new javax.swing.JTextField();
        date_view3 = new javax.swing.JLabel();
        date_pacd1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        search_viewb2 = new javax.swing.JButton();
        REPORTS1 = new javax.swing.JPanel();
        NEW = new javax.swing.JPanel();
        tabnew15 = new javax.swing.JTabbedPane();
        addnew15 = new javax.swing.JPanel();
        place1_anew16 = new javax.swing.JLabel();
        place1_anewt16 = new javax.swing.JTextField();
        customerid_anew16 = new javax.swing.JLabel();
        guide_anew16 = new javax.swing.JLabel();
        driver_anewt16 = new javax.swing.JTextField();
        guide_anewt16 = new javax.swing.JTextField();
        customerid_anewt16 = new javax.swing.JTextField();
        driver_anew16 = new javax.swing.JLabel();
        vechical_anewt16 = new javax.swing.JTextField();
        vehical_anew16 = new javax.swing.JLabel();
        nofday_anew16 = new javax.swing.JLabel();
        pass_anewt16 = new javax.swing.JTextField();
        fee_anew16 = new javax.swing.JLabel();
        fee_anewt16 = new javax.swing.JTextField();
        date_anew16 = new javax.swing.JLabel();
        date_anewt16 = new com.toedter.calendar.JDateChooser();
        add_anewb16 = new javax.swing.JButton();
        demonew = new javax.swing.JButton();
        demonew1 = new javax.swing.JButton();
        dis_anewt17 = new javax.swing.JTextField();
        fee_anew17 = new javax.swing.JLabel();
        days_anewt17 = new javax.swing.JTextField();
        fee_anew18 = new javax.swing.JLabel();
        viewnew4 = new javax.swing.JPanel();
        jLabel147 = new javax.swing.JLabel();
        vehicle_newvt = new javax.swing.JTextField();
        jLabel148 = new javax.swing.JLabel();
        plase_newvt = new javax.swing.JTextField();
        jLabel149 = new javax.swing.JLabel();
        noof_newvt = new javax.swing.JTextField();
        jLabel150 = new javax.swing.JLabel();
        fee_newvt = new javax.swing.JTextField();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        driver_newvt = new javax.swing.JTextField();
        cid_newvt = new javax.swing.JTextField();
        guide_newvt = new javax.swing.JTextField();
        jLabel153 = new javax.swing.JLabel();
        pass_newvt = new javax.swing.JTextField();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable21 = new javax.swing.JTable();
        date_newvt = new javax.swing.JTextField();
        dis_newvt = new javax.swing.JTextField();
        jLabel156 = new javax.swing.JLabel();
        id_newvt = new javax.swing.JTextField();
        jLabel157 = new javax.swing.JLabel();
        updatenew4 = new javax.swing.JPanel();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable20 = new javax.swing.JTable();
        plase_newvt1 = new javax.swing.JTextField();
        jLabel140 = new javax.swing.JLabel();
        fee_newvt1 = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        cid_newvt1 = new javax.swing.JTextField();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        noof_newvt1 = new javax.swing.JTextField();
        jLabel146 = new javax.swing.JLabel();
        id_newvt1 = new javax.swing.JTextField();
        pass_newvt1 = new javax.swing.JTextField();
        vehicle_newvt1 = new javax.swing.JTextField();
        date_newvt1 = new javax.swing.JTextField();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        driver_newvt1 = new javax.swing.JTextField();
        guide_newvt1 = new javax.swing.JTextField();
        dis_newvt1 = new javax.swing.JTextField();
        jButton39 = new javax.swing.JButton();
        OFFERS = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        ViewO = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        id_ov = new javax.swing.JTextField();
        date_ov = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        pac_ov = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        name_ov = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        duration_ov1 = new javax.swing.JLabel();
        duration_ov = new javax.swing.JTextField();
        discount_ov1 = new javax.swing.JLabel();
        discount_ov = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        dis_ov = new javax.swing.JTextField();
        AddO = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        discount_oa = new javax.swing.JTextField();
        duration_oa = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        date_oa = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        name_oa = new javax.swing.JTextField();
        pac_oa = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        dis_oa = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        updateO = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        discount_ou = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        duration_ou = new javax.swing.JTextField();
        date_ou = new javax.swing.JTextField();
        id_ou = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        pac_ou = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        name_ou = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jLabel94 = new javax.swing.JLabel();
        dis_ou = new javax.swing.JTextField();
        DRIVER = new javax.swing.JPanel();
        tabdriver = new javax.swing.JTabbedPane();
        addcus1 = new javax.swing.JPanel();
        namecus1 = new javax.swing.JLabel();
        niccus1 = new javax.swing.JLabel();
        addresscus1 = new javax.swing.JLabel();
        telephonecus1 = new javax.swing.JLabel();
        namecust1 = new javax.swing.JTextField();
        licence_da = new javax.swing.JTextField();
        telephonecust1 = new javax.swing.JTextField();
        addresscust1 = new javax.swing.JTextField();
        addcusb1 = new javax.swing.JButton();
        resetcusb1 = new javax.swing.JButton();
        niccust2 = new javax.swing.JTextField();
        emaill1 = new javax.swing.JLabel();
        demo1 = new javax.swing.JButton();
        vehicle_da = new javax.swing.JTextField();
        emaill3 = new javax.swing.JLabel();
        viewcus1 = new javax.swing.JPanel();
        idvcus1 = new javax.swing.JLabel();
        namevcus1 = new javax.swing.JLabel();
        nicvcus1 = new javax.swing.JLabel();
        addressvcus1 = new javax.swing.JLabel();
        telephonevcus1 = new javax.swing.JLabel();
        name_vcust1 = new javax.swing.JTextField();
        id_vcust1 = new javax.swing.JTextField();
        nic_vcut1 = new javax.swing.JTextField();
        telephone_vcust1 = new javax.swing.JTextField();
        address_vcust1 = new javax.swing.JTextField();
        table_vcus1 = new javax.swing.JScrollPane();
        jTable23 = new javax.swing.JTable();
        bookingid_vcus1 = new javax.swing.JLabel();
        vechile_vcust1 = new javax.swing.JTextField();
        emailvcus2 = new javax.swing.JLabel();
        licen_vcust2 = new javax.swing.JTextField();
        updatecus1 = new javax.swing.JPanel();
        id_ucus1 = new javax.swing.JLabel();
        name_ucus1 = new javax.swing.JLabel();
        address_ucus1 = new javax.swing.JLabel();
        nic_ucus2 = new javax.swing.JLabel();
        telephone_ucus1 = new javax.swing.JLabel();
        name_ucust1 = new javax.swing.JTextField();
        id_ucust1 = new javax.swing.JTextField();
        nic_ucust1 = new javax.swing.JTextField();
        telephone_ucust1 = new javax.swing.JTextField();
        adderss_ucust1 = new javax.swing.JTextField();
        table_ucust3 = new javax.swing.JScrollPane();
        jTable25 = new javax.swing.JTable();
        update_ucust1 = new javax.swing.JButton();
        rest_ucust1 = new javax.swing.JButton();
        email_ucust1 = new javax.swing.JTextField();
        nic_ucus3 = new javax.swing.JLabel();
        delete_cus1 = new javax.swing.JButton();
        licence_ucust1 = new javax.swing.JTextField();
        name_ucus3 = new javax.swing.JLabel();
        GUIDE = new javax.swing.JPanel();
        tabcustomer2 = new javax.swing.JTabbedPane();
        addcus2 = new javax.swing.JPanel();
        namecus2 = new javax.swing.JLabel();
        niccus2 = new javax.swing.JLabel();
        addresscus2 = new javax.swing.JLabel();
        telephonecus2 = new javax.swing.JLabel();
        namecust2 = new javax.swing.JTextField();
        emailt2 = new javax.swing.JTextField();
        telephonecust2 = new javax.swing.JTextField();
        addresscust2 = new javax.swing.JTextField();
        addcusb2 = new javax.swing.JButton();
        resetcusb2 = new javax.swing.JButton();
        niccust3 = new javax.swing.JTextField();
        emaill2 = new javax.swing.JLabel();
        demo2 = new javax.swing.JButton();
        viewcus2 = new javax.swing.JPanel();
        idvcus2 = new javax.swing.JLabel();
        namevcus2 = new javax.swing.JLabel();
        nicvcus2 = new javax.swing.JLabel();
        addressvcus2 = new javax.swing.JLabel();
        telephonevcus2 = new javax.swing.JLabel();
        name_vcust2 = new javax.swing.JTextField();
        id_vcust2 = new javax.swing.JTextField();
        nic_vcut2 = new javax.swing.JTextField();
        telephone_vcust2 = new javax.swing.JTextField();
        address_vcust2 = new javax.swing.JTextField();
        table_vcus2 = new javax.swing.JScrollPane();
        jTable26 = new javax.swing.JTable();
        bookingid_vcus2 = new javax.swing.JLabel();
        bookingid_vcust2 = new javax.swing.JTextField();
        emailvcus3 = new javax.swing.JLabel();
        email_vcust3 = new javax.swing.JTextField();
        updatecus2 = new javax.swing.JPanel();
        id_ucus2 = new javax.swing.JLabel();
        name_ucus2 = new javax.swing.JLabel();
        address_ucus2 = new javax.swing.JLabel();
        nic_ucus4 = new javax.swing.JLabel();
        telephone_ucus2 = new javax.swing.JLabel();
        name_ucust2 = new javax.swing.JTextField();
        id_ucust2 = new javax.swing.JTextField();
        nic_ucust2 = new javax.swing.JTextField();
        telephone_ucust2 = new javax.swing.JTextField();
        adderss_ucust2 = new javax.swing.JTextField();
        table_ucust5 = new javax.swing.JScrollPane();
        jTable28 = new javax.swing.JTable();
        update_ucust2 = new javax.swing.JButton();
        rest_ucust2 = new javax.swing.JButton();
        email_ucust2 = new javax.swing.JTextField();
        nic_ucus5 = new javax.swing.JLabel();
        delete_cus2 = new javax.swing.JButton();
        VEHICAL = new javax.swing.JPanel();
        tabnew3 = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane1.setMaximumSize(new java.awt.Dimension(1024, 700));
        jDesktopPane1.setMinimumSize(new java.awt.Dimension(1024, 700));

        panalmenu.setBackground(new java.awt.Color(255, 255, 255));
        panalmenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));
        panalmenu.setLayout(null);

        home.setBackground(new java.awt.Color(51, 153, 255));
        home.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        home.setForeground(new java.awt.Color(255, 255, 255));
        home.setText("HOME");
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeMouseExited(evt);
            }
        });
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });
        panalmenu.add(home);
        home.setBounds(10, 20, 160, 40);

        pakageB.setBackground(new java.awt.Color(51, 153, 255));
        pakageB.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pakageB.setForeground(new java.awt.Color(255, 255, 255));
        pakageB.setText("PACKAGES");
        pakageB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pakageBMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pakageBMouseExited(evt);
            }
        });
        pakageB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pakageBActionPerformed(evt);
            }
        });
        panalmenu.add(pakageB);
        pakageB.setBounds(10, 120, 160, 40);

        customer.setBackground(new java.awt.Color(51, 153, 255));
        customer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customer.setForeground(new java.awt.Color(255, 255, 255));
        customer.setText("CUSTOMER");
        customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                customerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                customerMouseExited(evt);
            }
        });
        customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerActionPerformed(evt);
            }
        });
        panalmenu.add(customer);
        customer.setBounds(10, 70, 160, 40);

        payment.setBackground(new java.awt.Color(51, 153, 255));
        payment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        payment.setForeground(new java.awt.Color(255, 255, 255));
        payment.setText("PAYMENT");
        payment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                paymentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                paymentMouseExited(evt);
            }
        });
        payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentActionPerformed(evt);
            }
        });
        panalmenu.add(payment);
        payment.setBounds(10, 370, 160, 40);

        viewBook.setBackground(new java.awt.Color(51, 153, 255));
        viewBook.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        viewBook.setForeground(new java.awt.Color(255, 255, 255));
        viewBook.setText("VIEW BOOKINGS");
        viewBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewBookMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewBookMouseExited(evt);
            }
        });
        viewBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBookActionPerformed(evt);
            }
        });
        panalmenu.add(viewBook);
        viewBook.setBounds(10, 170, 160, 40);

        createNew.setBackground(new java.awt.Color(51, 153, 255));
        createNew.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        createNew.setForeground(new java.awt.Color(255, 255, 255));
        createNew.setText("CREATE NEW");
        createNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createNewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createNewMouseExited(evt);
            }
        });
        createNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewActionPerformed(evt);
            }
        });
        panalmenu.add(createNew);
        createNew.setBounds(10, 220, 160, 40);

        reports.setBackground(new java.awt.Color(51, 153, 255));
        reports.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        reports.setForeground(new java.awt.Color(255, 255, 255));
        reports.setText("REPORTS");
        reports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reportsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reportsMouseExited(evt);
            }
        });
        reports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsActionPerformed(evt);
            }
        });
        panalmenu.add(reports);
        reports.setBounds(10, 470, 160, 40);

        offers1.setBackground(new java.awt.Color(51, 153, 255));
        offers1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        offers1.setForeground(new java.awt.Color(255, 255, 255));
        offers1.setText("OFFERS");
        offers1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                offers1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                offers1MouseExited(evt);
            }
        });
        offers1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                offers1ActionPerformed(evt);
            }
        });
        panalmenu.add(offers1);
        offers1.setBounds(10, 270, 160, 40);

        driver.setBackground(new java.awt.Color(51, 153, 255));
        driver.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        driver.setForeground(new java.awt.Color(255, 255, 255));
        driver.setText("DRIVER");
        driver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                driverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                driverMouseExited(evt);
            }
        });
        driver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                driverActionPerformed(evt);
            }
        });
        panalmenu.add(driver);
        driver.setBounds(10, 420, 160, 40);

        guide.setBackground(new java.awt.Color(51, 153, 255));
        guide.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        guide.setForeground(new java.awt.Color(255, 255, 255));
        guide.setText("GUIDE");
        guide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                guideMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                guideMouseExited(evt);
            }
        });
        guide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guideActionPerformed(evt);
            }
        });
        panalmenu.add(guide);
        guide.setBounds(10, 320, 160, 40);

        jDesktopPane1.add(panalmenu);
        panalmenu.setBounds(10, 120, 178, 570);

        panalhead.setBackground(new java.awt.Color(255, 255, 255));
        panalhead.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 2));

        homel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        homel.setForeground(new java.awt.Color(0, 204, 0));
        homel.setText("Transport Department");

        mix.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        mix.setForeground(new java.awt.Color(51, 153, 255));
        mix.setText("Transport Management");

        logout.setBackground(new java.awt.Color(255, 255, 255));
        logout.setForeground(new java.awt.Color(51, 153, 255));
        logout.setText("Log Out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        jLabel17.setText("Date:");

        jLabel18.setText("Time:");

        time.setText("d");

        date1.setText("d");

        javax.swing.GroupLayout panalheadLayout = new javax.swing.GroupLayout(panalhead);
        panalhead.setLayout(panalheadLayout);
        panalheadLayout.setHorizontalGroup(
            panalheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panalheadLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addGroup(panalheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panalheadLayout.createSequentialGroup()
                        .addComponent(homel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mix, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panalheadLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88)
                .addComponent(logout)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panalheadLayout.setVerticalGroup(
            panalheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panalheadLayout.createSequentialGroup()
                .addGroup(panalheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panalheadLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(panalheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panalheadLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(logout))))
                    .addGroup(panalheadLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(panalheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panalheadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(homel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mix, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(7, 7, 7))
        );

        jDesktopPane1.add(panalhead);
        panalhead.setBounds(10, 10, 1010, 100);

        panalcontent.setBackground(new java.awt.Color(255, 255, 255));
        panalcontent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        panalcontent.setMaximumSize(new java.awt.Dimension(1200, 1200));
        panalcontent.setMinimumSize(new java.awt.Dimension(8, 5));
        panalcontent.setName(""); // NOI18N
        panalcontent.setPreferredSize(new java.awt.Dimension(810, 570));
        panalcontent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HOME.setBackground(new java.awt.Color(255, 255, 255));
        HOME.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        HOME.setPreferredSize(new java.awt.Dimension(810, 570));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 204));
        jLabel4.setText("TRANSPORT DEPARTMENT");

        javax.swing.GroupLayout HOMELayout = new javax.swing.GroupLayout(HOME);
        HOME.setLayout(HOMELayout);
        HOMELayout.setHorizontalGroup(
            HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HOMELayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        HOMELayout.setVerticalGroup(
            HOMELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HOMELayout.createSequentialGroup()
                .addContainerGap(229, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(223, 223, 223))
        );

        panalcontent.add(HOME, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        CUSTOMER.setBackground(new java.awt.Color(255, 255, 255));
        CUSTOMER.setMaximumSize(new java.awt.Dimension(1200, 1200));
        CUSTOMER.setPreferredSize(new java.awt.Dimension(810, 570));
        CUSTOMER.setLayout(new java.awt.BorderLayout());

        tabcustomer.setBackground(new java.awt.Color(51, 153, 255));
        tabcustomer.setForeground(new java.awt.Color(255, 255, 255));
        tabcustomer.setMaximumSize(new java.awt.Dimension(1200, 1200));
        tabcustomer.setPreferredSize(new java.awt.Dimension(810, 570));

        addcus.setBackground(new java.awt.Color(255, 255, 255));
        addcus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        addcus.setMaximumSize(new java.awt.Dimension(1200, 1200));
        addcus.setPreferredSize(new java.awt.Dimension(810, 570));

        namecus.setText("Name");

        niccus.setText("NIC");

        addresscus.setText("Address");

        telephonecus.setText("Telephone");

        namecust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namecustActionPerformed(evt);
            }
        });

        telephonecust.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telephonecustKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telephonecustKeyTyped(evt);
            }
        });

        addresscust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addresscustActionPerformed(evt);
            }
        });

        addcusb.setBackground(new java.awt.Color(51, 153, 255));
        addcusb.setForeground(new java.awt.Color(255, 255, 255));
        addcusb.setText("Add");
        addcusb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addcusbActionPerformed(evt);
            }
        });

        resetcusb.setBackground(new java.awt.Color(51, 153, 255));
        resetcusb.setForeground(new java.awt.Color(255, 255, 255));
        resetcusb.setText("Reset");
        resetcusb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetcusbActionPerformed(evt);
            }
        });

        emaill.setText("Email");

        demo.setBackground(new java.awt.Color(51, 153, 255));
        demo.setForeground(new java.awt.Color(255, 255, 255));
        demo.setText("DEMO");
        demo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addcusLayout = new javax.swing.GroupLayout(addcus);
        addcus.setLayout(addcusLayout);
        addcusLayout.setHorizontalGroup(
            addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addcusLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(namecus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(niccus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(emaill, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namecust, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(niccust1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(119, 119, 119)
                .addGroup(addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addresscus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telephonecus, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addresscust, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telephonecust, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(120, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addcusLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(demo)
                .addGap(18, 18, 18)
                .addComponent(resetcusb)
                .addGap(18, 18, 18)
                .addComponent(addcusb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );
        addcusLayout.setVerticalGroup(
            addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addcusLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addresscust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addresscus)
                    .addGroup(addcusLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namecus)
                            .addComponent(namecust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addcusLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(telephonecus)
                                    .addComponent(telephonecust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(addcusLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(niccust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(niccus))))))
                .addGap(59, 59, 59)
                .addGroup(addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emaill))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                .addGroup(addcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetcusb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addcusb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(demo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tabcustomer.addTab("Add New", addcus);

        viewcus.setBackground(new java.awt.Color(255, 255, 255));
        viewcus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        viewcus.setMaximumSize(new java.awt.Dimension(1200, 1200));
        viewcus.setPreferredSize(new java.awt.Dimension(810, 570));

        idvcus.setText("ID");

        namevcus.setText("Name");

        nicvcus.setText("NIC");

        addressvcus.setText("Address");

        telephonevcus.setText("Telephone");

        name_vcust.setEditable(false);
        name_vcust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_vcustActionPerformed(evt);
            }
        });

        id_vcust.setEditable(false);

        nic_vcut.setEditable(false);

        telephone_vcust.setEditable(false);

        address_vcust.setEditable(false);
        address_vcust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                address_vcustActionPerformed(evt);
            }
        });

        jTable4.setForeground(new java.awt.Color(51, 153, 255));
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "NIC", "Address", "Telephone", "Booking ID"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        table_vcus.setViewportView(jTable4);

        emailvcus1.setText("Email");

        email_vcust1.setEditable(false);

        javax.swing.GroupLayout viewcusLayout = new javax.swing.GroupLayout(viewcus);
        viewcus.setLayout(viewcusLayout);
        viewcusLayout.setHorizontalGroup(
            viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewcusLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(table_vcus, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(viewcusLayout.createSequentialGroup()
                        .addGroup(viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(viewcusLayout.createSequentialGroup()
                                    .addComponent(nicvcus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewcusLayout.createSequentialGroup()
                                    .addComponent(namevcus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(47, 47, 47)))
                            .addGroup(viewcusLayout.createSequentialGroup()
                                .addComponent(emailvcus1)
                                .addGap(32, 32, 32)))
                        .addGroup(viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(email_vcust1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_vcust, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nic_vcut, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(addressvcus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(telephonevcus, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                            .addComponent(idvcus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(address_vcust, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(telephone_vcust, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_vcust, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        viewcusLayout.setVerticalGroup(
            viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewcusLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(viewcusLayout.createSequentialGroup()
                        .addGroup(viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewcusLayout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(telephone_vcust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(address_vcust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(viewcusLayout.createSequentialGroup()
                                .addComponent(addressvcus)
                                .addGap(54, 54, 54)
                                .addComponent(telephonevcus)))
                        .addGap(75, 75, 75))
                    .addGroup(viewcusLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name_vcust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namevcus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email_vcust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailvcus1))
                        .addGap(44, 44, 44)
                        .addGroup(viewcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nicvcus)
                            .addComponent(nic_vcut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_vcust, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idvcus))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(table_vcus, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        tabcustomer.addTab("View Details", viewcus);

        searchcus.setBackground(new java.awt.Color(255, 255, 255));

        jComboBox3.setBackground(new java.awt.Color(51, 153, 255));
        jComboBox3.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One For Advance Search", "ID", "Address", "NIC", "Telephone", "email" }));

        jTable22.setForeground(new java.awt.Color(51, 153, 255));
        jTable22.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "ID", "Name", "NIC", "Address", "Telephone"
            }
        ));
        jTable22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable22MouseClicked(evt);
            }
        });
        table_ucust1.setViewportView(jTable22);

        jLabel11.setText("Value 1");

        jLabel12.setText("Value 2");

        va2cuse.setEditable(false);
        va2cuse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                va2cuseActionPerformed(evt);
            }
        });
        va2cuse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                va2cuseKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                va2cuseKeyTyped(evt);
            }
        });

        va1cuse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                va1cuseActionPerformed(evt);
            }
        });
        va1cuse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                va1cuseKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout searchcusLayout = new javax.swing.GroupLayout(searchcus);
        searchcus.setLayout(searchcusLayout);
        searchcusLayout.setHorizontalGroup(
            searchcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchcusLayout.createSequentialGroup()
                .addGroup(searchcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchcusLayout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchcusLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(table_ucust1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchcusLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(va1cuse, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(va2cuse, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        searchcusLayout.setVerticalGroup(
            searchcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchcusLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(searchcusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(va2cuse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(va1cuse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(table_ucust1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        tabcustomer.addTab("Search", searchcus);

        updatecus.setBackground(new java.awt.Color(255, 255, 255));
        updatecus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        updatecus.setMaximumSize(new java.awt.Dimension(1200, 1200));
        updatecus.setPreferredSize(new java.awt.Dimension(810, 570));
        updatecus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        id_ucus.setText("ID");
        updatecus.add(id_ucus, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 42, 35, -1));

        name_ucus.setText("Name");
        updatecus.add(name_ucus, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 35, -1));

        address_ucus.setText("Address");
        updatecus.add(address_ucus, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 66, -1));

        nic_ucus.setText("NIC");
        updatecus.add(nic_ucus, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 143, 35, -1));

        telephone_ucus.setText("Telephone");
        updatecus.add(telephone_ucus, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 66, -1));

        name_ucust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_ucustActionPerformed(evt);
            }
        });
        updatecus.add(name_ucust, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 194, -1));

        id_ucust.setEditable(false);
        updatecus.add(id_ucust, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 194, -1));
        updatecus.add(nic_ucust, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 194, -1));
        updatecus.add(telephone_ucust, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 194, -1));

        adderss_ucust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adderss_ucustActionPerformed(evt);
            }
        });
        updatecus.add(adderss_ucust, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 39, 194, -1));

        jTable5.setForeground(new java.awt.Color(51, 153, 255));
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "ID", "Name", "NIC", "Address", "Telephone"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        table_ucust.setViewportView(jTable5);

        updatecus.add(table_ucust, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 191, 729, 270));

        update_ucust.setBackground(new java.awt.Color(51, 153, 255));
        update_ucust.setForeground(new java.awt.Color(255, 255, 255));
        update_ucust.setText("Update");
        update_ucust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_ucustActionPerformed(evt);
            }
        });
        updatecus.add(update_ucust, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 470, -1, 40));

        rest_ucust.setBackground(new java.awt.Color(51, 153, 255));
        rest_ucust.setForeground(new java.awt.Color(255, 255, 255));
        rest_ucust.setText("Reset");
        rest_ucust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rest_ucustActionPerformed(evt);
            }
        });
        updatecus.add(rest_ucust, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 470, -1, 40));
        updatecus.add(email_ucust, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 194, -1));

        nic_ucus1.setText("Email");
        updatecus.add(nic_ucus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 35, -1));

        delete_cus.setBackground(new java.awt.Color(51, 153, 255));
        delete_cus.setForeground(new java.awt.Color(255, 255, 255));
        delete_cus.setText("Delete");
        delete_cus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_cusActionPerformed(evt);
            }
        });
        updatecus.add(delete_cus, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 470, -1, 40));

        tabcustomer.addTab("Update Info", updatecus);

        CUSTOMER.add(tabcustomer, java.awt.BorderLayout.CENTER);

        panalcontent.add(CUSTOMER, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        PACKAGE.setBackground(new java.awt.Color(255, 255, 255));
        PACKAGE.setPreferredSize(new java.awt.Dimension(810, 570));
        PACKAGE.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabpackage.setBackground(new java.awt.Color(51, 153, 255));
        tabpackage.setForeground(new java.awt.Color(255, 255, 255));
        tabpackage.setPreferredSize(new java.awt.Dimension(810, 570));

        view.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("ID");

        id_upact1.setEditable(false);
        id_upact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_upact1ActionPerformed(evt);
            }
        });

        name_upac1.setText("Name");

        name_upact1.setEditable(false);
        name_upact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_upact1ActionPerformed(evt);
            }
        });

        destiantion_upac1.setText("Destination");

        destination_upact1.setEditable(false);
        destination_upact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destination_upact1ActionPerformed(evt);
            }
        });

        activity_upac1.setText("Activity");

        activity_upact1.setEditable(false);
        activity_upact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activity_upact1ActionPerformed(evt);
            }
        });

        day_upac1.setText("Days");

        day_upact1.setEditable(false);
        day_upact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                day_upact1ActionPerformed(evt);
            }
        });

        fee_uapc1.setText("Fee");

        fee_upact1.setEditable(false);
        fee_upact1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fee_upact1ActionPerformed(evt);
            }
        });

        pac_view.setForeground(new java.awt.Color(51, 153, 255));
        pac_view.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "Package ID", "Name", "destination", "Activity", "fee", "days"
            }
        ));
        pac_view.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pac_viewMouseClicked(evt);
            }
        });
        table_upac1.setViewportView(pac_view);

        javax.swing.GroupLayout viewLayout = new javax.swing.GroupLayout(view);
        view.setLayout(viewLayout);
        viewLayout.setHorizontalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(table_upac1)
                    .addGroup(viewLayout.createSequentialGroup()
                        .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(name_upac1)
                            .addComponent(activity_upac1)
                            .addComponent(destiantion_upac1))
                        .addGap(51, 51, 51)
                        .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(activity_upact1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(viewLayout.createSequentialGroup()
                                .addComponent(destination_upact1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(166, 166, 166)
                                .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(day_upac1)
                                    .addComponent(fee_uapc1))
                                .addGap(58, 58, 58)
                                .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fee_upact1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(day_upact1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(id_upact1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(name_upact1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))
                        .addGap(0, 146, Short.MAX_VALUE)))
                .addContainerGap())
        );
        viewLayout.setVerticalGroup(
            viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(id_upact1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_upac1)
                    .addComponent(name_upact1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(fee_uapc1)
                            .addComponent(fee_upact1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(day_upac1)
                            .addComponent(day_upact1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(viewLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(destination_upact1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(destiantion_upac1))
                        .addGap(37, 37, 37)
                        .addGroup(viewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(activity_upac1)
                            .addComponent(activity_upact1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(table_upac1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addGap(68, 68, 68))
        );

        tabpackage.addTab("View", view);

        search.setBackground(new java.awt.Color(255, 255, 255));

        day_upac2.setText("Value 2");

        val2pac.setEditable(false);
        val2pac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                val2pacActionPerformed(evt);
            }
        });
        val2pac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                val2pacKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                val2pacKeyTyped(evt);
            }
        });

        val1pac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                val1pacActionPerformed(evt);
            }
        });
        val1pac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                val1pacKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                val1pacKeyTyped(evt);
            }
        });

        fee_uapc2.setText("Value 1");

        sea_pac.setForeground(new java.awt.Color(51, 153, 255));
        sea_pac.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "Package ID", "Name", "destination", "Activity", "fee", "days"
            }
        ));
        sea_pac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sea_pacMouseClicked(evt);
            }
        });
        table_upac2.setViewportView(sea_pac);

        jComboBox1.setBackground(new java.awt.Color(51, 153, 255));
        jComboBox1.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One For Advance Search", "ID", "Destination", "Activity", "Fee", "Days", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchLayout = new javax.swing.GroupLayout(search);
        search.setLayout(searchLayout);
        searchLayout.setHorizontalGroup(
            searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchLayout.createSequentialGroup()
                .addGroup(searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchLayout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(fee_uapc2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(val1pac, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(day_upac2)
                        .addGap(28, 28, 28)
                        .addComponent(val2pac, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchLayout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(187, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(table_upac2, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        searchLayout.setVerticalGroup(
            searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addGroup(searchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(val1pac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fee_uapc2)
                    .addComponent(day_upac2)
                    .addComponent(val2pac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addComponent(table_upac2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        tabpackage.addTab("Search", search);

        bookpacage.setBackground(new java.awt.Color(255, 255, 255));
        bookpacage.setPreferredSize(new java.awt.Dimension(810, 570));

        customerid_pac.setText("Customer ID");

        date_pac.setText("Date");

        status_pac.setText("Status");

        customerid_pact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerid_pactActionPerformed(evt);
            }
        });
        customerid_pact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                customerid_pactKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                customerid_pactKeyTyped(evt);
            }
        });

        no_pac.setText("No. Of Passengers");

        name_pac.setText("Package Name");

        book_pacb.setBackground(new java.awt.Color(51, 153, 255));
        book_pacb.setForeground(new java.awt.Color(255, 255, 255));
        book_pacb.setText("Book");
        book_pacb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                book_pacbActionPerformed(evt);
            }
        });

        name_pact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_pactActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(51, 153, 255));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 153, 255));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("DEMO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        no_pact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                no_pactActionPerformed(evt);
            }
        });
        no_pact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                no_pactKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout bookpacageLayout = new javax.swing.GroupLayout(bookpacage);
        bookpacage.setLayout(bookpacageLayout);
        bookpacageLayout.setHorizontalGroup(
            bookpacageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookpacageLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(bookpacageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(name_pac)
                    .addComponent(no_pac)
                    .addComponent(status_pac))
                .addGap(25, 25, 25)
                .addGroup(bookpacageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(satus_pact, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(name_pact)
                    .addComponent(no_pact))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addGroup(bookpacageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerid_pac)
                    .addComponent(date_pac))
                .addGap(18, 18, 18)
                .addGroup(bookpacageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(customerid_pact)
                    .addComponent(date_pacd, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                .addGap(80, 80, 80))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookpacageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(book_pacb)
                .addGap(86, 86, 86))
        );
        bookpacageLayout.setVerticalGroup(
            bookpacageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookpacageLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(bookpacageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bookpacageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(name_pac)
                        .addComponent(date_pac)
                        .addComponent(name_pact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(date_pacd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(bookpacageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(no_pac)
                    .addComponent(customerid_pac)
                    .addComponent(customerid_pact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(no_pact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(bookpacageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status_pac)
                    .addComponent(satus_pact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                .addGroup(bookpacageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(book_pacb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        tabpackage.addTab("Book", bookpacage);

        add_pack.setBackground(new java.awt.Color(255, 255, 255));

        name_apac.setText("Name");

        name_apact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_apactActionPerformed(evt);
            }
        });

        destination_apac.setText("Destination");

        destination_apact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destination_apactActionPerformed(evt);
            }
        });

        activity_apac.setText("Activity");

        activity_apact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activity_apactActionPerformed(evt);
            }
        });

        fee_apact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fee_apactActionPerformed(evt);
            }
        });
        fee_apact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fee_apactKeyTyped(evt);
            }
        });

        fee_apac.setText("Fee");

        day_apact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                day_apactActionPerformed(evt);
            }
        });
        day_apact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                day_apactKeyTyped(evt);
            }
        });

        day_apcc.setText("Days");

        add_apacd.setBackground(new java.awt.Color(51, 153, 255));
        add_apacd.setForeground(new java.awt.Color(255, 255, 255));
        add_apacd.setText("Add");
        add_apacd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_apacdActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(51, 153, 255));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("DEMO");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout add_packLayout = new javax.swing.GroupLayout(add_pack);
        add_pack.setLayout(add_packLayout);
        add_packLayout.setHorizontalGroup(
            add_packLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_packLayout.createSequentialGroup()
                .addGroup(add_packLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_packLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(add_packLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(add_packLayout.createSequentialGroup()
                                .addComponent(activity_apac)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(activity_apact, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(add_packLayout.createSequentialGroup()
                                .addComponent(destination_apac)
                                .addGap(18, 18, 18)
                                .addComponent(destination_apact, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(add_packLayout.createSequentialGroup()
                                .addComponent(name_apac)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(name_apact, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(144, 144, 144)
                        .addGroup(add_packLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(day_apcc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fee_apac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(add_packLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fee_apact, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(day_apact, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_packLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addGap(31, 31, 31)
                        .addComponent(jButton1)
                        .addGap(28, 28, 28)))
                .addComponent(add_apacd)
                .addGap(89, 89, 89))
        );
        add_packLayout.setVerticalGroup(
            add_packLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_packLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(add_packLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_apac)
                    .addComponent(name_apact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fee_apac)
                    .addComponent(fee_apact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(add_packLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destination_apac)
                    .addComponent(destination_apact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(day_apcc)
                    .addComponent(day_apact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(add_packLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activity_apac)
                    .addComponent(activity_apact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 273, Short.MAX_VALUE)
                .addGroup(add_packLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_apacd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        tabpackage.addTab("add", add_pack);

        uppackage.setBackground(new java.awt.Color(255, 255, 255));

        name_upac.setText("Name");

        name_upact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_upactActionPerformed(evt);
            }
        });

        destination_upact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destination_upactActionPerformed(evt);
            }
        });

        destiantion_upac.setText("Destination");

        activity_upac.setText("Activity");

        activity_upact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activity_upactActionPerformed(evt);
            }
        });

        day_upac.setText("Days");

        day_upact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                day_upactActionPerformed(evt);
            }
        });
        day_upact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                day_upactKeyTyped(evt);
            }
        });

        fee_upact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fee_upactActionPerformed(evt);
            }
        });
        fee_upact.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fee_upactKeyTyped(evt);
            }
        });

        fee_uapc.setText("Fee");

        jTable11.setForeground(new java.awt.Color(51, 153, 255));
        jTable11.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "Package ID", "Name", "destination", "Activity", "fee", "days"
            }
        ));
        jTable11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable11MouseClicked(evt);
            }
        });
        table_upac.setViewportView(jTable11);

        update_upacb.setBackground(new java.awt.Color(51, 153, 255));
        update_upacb.setForeground(new java.awt.Color(255, 255, 255));
        update_upacb.setText("Update");
        update_upacb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_upacbActionPerformed(evt);
            }
        });

        delete_upab.setBackground(new java.awt.Color(51, 153, 255));
        delete_upab.setForeground(new java.awt.Color(255, 255, 255));
        delete_upab.setText("delete");
        delete_upab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_upabActionPerformed(evt);
            }
        });

        jLabel1.setText("ID");

        id_upact.setEditable(false);
        id_upact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                id_upactActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout uppackageLayout = new javax.swing.GroupLayout(uppackage);
        uppackage.setLayout(uppackageLayout);
        uppackageLayout.setHorizontalGroup(
            uppackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(uppackageLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(61, 61, 61)
                .addComponent(id_upact, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uppackageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(uppackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uppackageLayout.createSequentialGroup()
                        .addComponent(delete_upab)
                        .addGap(18, 18, 18)
                        .addComponent(update_upacb)
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uppackageLayout.createSequentialGroup()
                        .addComponent(fee_uapc)
                        .addGap(390, 390, 390))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uppackageLayout.createSequentialGroup()
                        .addComponent(table_upac, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(uppackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(uppackageLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addGroup(uppackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(uppackageLayout.createSequentialGroup()
                            .addComponent(activity_upac)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(activity_upact, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(uppackageLayout.createSequentialGroup()
                            .addComponent(destiantion_upac)
                            .addGap(18, 18, 18)
                            .addComponent(destination_upact, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(uppackageLayout.createSequentialGroup()
                            .addComponent(name_upac)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(name_upact, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(144, 144, 144)
                    .addComponent(day_upac)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(uppackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fee_upact, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(day_upact, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(199, 199, 199)))
        );
        uppackageLayout.setVerticalGroup(
            uppackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, uppackageLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(uppackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_upact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(43, 43, 43)
                .addComponent(fee_uapc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(table_upac, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(uppackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update_upacb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_upab, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
            .addGroup(uppackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(uppackageLayout.createSequentialGroup()
                    .addGap(79, 79, 79)
                    .addGroup(uppackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(name_upac)
                        .addComponent(name_upact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fee_upact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(34, 34, 34)
                    .addGroup(uppackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(destiantion_upac)
                        .addComponent(destination_upact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(day_upac)
                        .addComponent(day_upact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(43, 43, 43)
                    .addGroup(uppackageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(activity_upac)
                        .addComponent(activity_upact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(326, Short.MAX_VALUE)))
        );

        tabpackage.addTab("update", uppackage);

        PACKAGE.add(tabpackage, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, 808, -1));

        panalcontent.add(PACKAGE, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        PAYMENT.setBackground(new java.awt.Color(255, 255, 255));
        PAYMENT.setPreferredSize(new java.awt.Dimension(810, 570));
        PAYMENT.setLayout(new java.awt.BorderLayout());

        tabpayment.setBackground(new java.awt.Color(51, 153, 255));
        tabpayment.setForeground(new java.awt.Color(255, 255, 255));
        tabpayment.setPreferredSize(new java.awt.Dimension(810, 570));

        addpay.setBackground(new java.awt.Color(255, 255, 255));
        addpay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        addpay.setPreferredSize(new java.awt.Dimension(810, 570));

        date_pay.setText("Date");

        balance_pay.setText("Balance");

        customer_pay.setText("Customer ID");

        methord_pay.setText("Methord");

        customerid_payt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerid_paytActionPerformed(evt);
            }
        });

        total_payt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_paytActionPerformed(evt);
            }
        });

        add_payb.setBackground(new java.awt.Color(51, 153, 255));
        add_payb.setForeground(new java.awt.Color(255, 255, 255));
        add_payb.setText("Add");
        add_payb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_paybActionPerformed(evt);
            }
        });

        reset_payb.setBackground(new java.awt.Color(51, 153, 255));
        reset_payb.setForeground(new java.awt.Color(255, 255, 255));
        reset_payb.setText("Reset");

        total_pay.setText("Total");

        balance_payt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balance_paytActionPerformed(evt);
            }
        });

        paid_pay.setText("Paid");

        javax.swing.GroupLayout addpayLayout = new javax.swing.GroupLayout(addpay);
        addpay.setLayout(addpayLayout);
        addpayLayout.setHorizontalGroup(
            addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addpayLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addpayLayout.createSequentialGroup()
                        .addComponent(customer_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(customerid_payt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addpayLayout.createSequentialGroup()
                        .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addpayLayout.createSequentialGroup()
                                .addComponent(date_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(addpayLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(methord_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(date_paydate, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(methord_payt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(111, 111, 111)))
                .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addpayLayout.createSequentialGroup()
                        .addComponent(total_pay, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addGroup(addpayLayout.createSequentialGroup()
                        .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(paid_pay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(balance_pay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(total_payt, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(paid_payt, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addComponent(balance_payt))
                .addGap(99, 99, 99))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addpayLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reset_payb)
                .addGap(42, 42, 42)
                .addComponent(add_payb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );
        addpayLayout.setVerticalGroup(
            addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addpayLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addpayLayout.createSequentialGroup()
                        .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(total_payt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total_pay))
                        .addGap(45, 45, 45)
                        .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paid_payt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paid_pay)))
                    .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addpayLayout.createSequentialGroup()
                            .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(customerid_payt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(customer_pay))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date_paydate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, addpayLayout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(date_pay))))
                .addGap(58, 58, 58)
                .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balance_payt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(balance_pay)
                    .addComponent(methord_pay)
                    .addComponent(methord_payt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                .addGroup(addpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset_payb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_payb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95))
        );

        tabpayment.addTab("Add New", addpay);

        viewpay.setBackground(new java.awt.Color(255, 255, 255));
        viewpay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        viewpay.setPreferredSize(new java.awt.Dimension(810, 570));

        search_vpayb.setText("Search");
        search_vpayb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_vpaybActionPerformed(evt);
            }
        });

        id_vpay.setText("ID");

        total_vpay.setText("Total");

        paid_vpay.setText("Paid");

        customer_vpay.setText("Customer ID");

        methord_vpay.setText("Methord");

        total_vpayt.setEditable(false);
        total_vpayt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_vpaytActionPerformed(evt);
            }
        });

        id_vpayt.setEditable(false);

        paid_vpayt.setEditable(false);

        methord_vpayt.setEditable(false);

        customer_vpayt.setEditable(false);
        customer_vpayt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customer_vpaytActionPerformed(evt);
            }
        });

        jTable9.setForeground(new java.awt.Color(51, 153, 255));
        jTable9.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Payment ID", "Total", "Paid", "Balance", "Customer Id", "Methord", "Date"
            }
        ));
        table_vpay.setViewportView(jTable9);

        date_vpay.setText("Date");

        date_vpayt.setEditable(false);
        date_vpayt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date_vpaytActionPerformed(evt);
            }
        });

        balance_vpay.setText("Balance");

        balance_vpayt.setEditable(false);

        javax.swing.GroupLayout viewpayLayout = new javax.swing.GroupLayout(viewpay);
        viewpay.setLayout(viewpayLayout);
        viewpayLayout.setHorizontalGroup(
            viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewpayLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewpayLayout.createSequentialGroup()
                        .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewpayLayout.createSequentialGroup()
                                .addComponent(id_vpay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(customer_vpay, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewpayLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(date_vpay, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(methord_vpay, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(47, 47, 47)
                        .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(date_vpayt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customer_vpayt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120))
                    .addGroup(viewpayLayout.createSequentialGroup()
                        .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(table_vpay, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(viewpayLayout.createSequentialGroup()
                                .addComponent(balance_vpay, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(balance_vpayt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(44, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewpayLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search_vpayb)
                .addGap(142, 142, 142))
            .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(viewpayLayout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(viewpayLayout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(id_vpayt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(viewpayLayout.createSequentialGroup()
                            .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(total_vpay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(paid_vpay, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(total_vpayt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(paid_vpayt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(203, 203, 203)
                    .addComponent(methord_vpayt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(125, Short.MAX_VALUE)))
        );
        viewpayLayout.setVerticalGroup(
            viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewpayLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customer_vpay)
                    .addComponent(id_vpay)
                    .addComponent(customer_vpayt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(methord_vpay)
                .addGap(50, 50, 50)
                .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date_vpay)
                    .addComponent(date_vpayt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balance_vpay)
                    .addComponent(balance_vpayt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(table_vpay, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94)
                .addComponent(search_vpayb)
                .addGap(54, 54, 54))
            .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(viewpayLayout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, viewpayLayout.createSequentialGroup()
                            .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(viewpayLayout.createSequentialGroup()
                                    .addGap(65, 65, 65)
                                    .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(total_vpay)
                                        .addComponent(total_vpayt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(id_vpayt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(52, 52, 52)
                            .addGroup(viewpayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(paid_vpay)
                                .addComponent(paid_vpayt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, viewpayLayout.createSequentialGroup()
                            .addGap(65, 65, 65)
                            .addComponent(methord_vpayt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(473, Short.MAX_VALUE)))
        );

        tabpayment.addTab("View Details", viewpay);

        updatepay.setBackground(new java.awt.Color(255, 255, 255));
        updatepay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        updatepay.setPreferredSize(new java.awt.Dimension(810, 570));

        total_upay.setText("Total");

        customerid_upay.setText("Customer ID");

        upadete_upay.setBackground(new java.awt.Color(51, 153, 255));
        upadete_upay.setForeground(new java.awt.Color(255, 255, 255));
        upadete_upay.setText("Update");
        upadete_upay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upadete_upayActionPerformed(evt);
            }
        });

        methord_upay.setText("Methord");

        id_upay.setText("ID");

        pay_upay.setText("Paid");

        balance_upayt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                balance_upaytActionPerformed(evt);
            }
        });

        id_upayt.setEditable(false);

        date_upayt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                date_upaytActionPerformed(evt);
            }
        });

        jTable12.setForeground(new java.awt.Color(51, 153, 255));
        jTable12.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Payment ID", "Total", "Paid", "Balance", "Customer Id", "Methord", "Date"
            }
        ));
        table_upay.setViewportView(jTable12);

        date_upay.setText("Date");

        paid_upayt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paid_upaytActionPerformed(evt);
            }
        });

        balance_upay.setText("Balance");

        reset_upayb.setBackground(new java.awt.Color(51, 153, 255));
        reset_upayb.setForeground(new java.awt.Color(255, 255, 255));
        reset_upayb.setText("Reset");
        reset_upayb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_upaybActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updatepayLayout = new javax.swing.GroupLayout(updatepay);
        updatepay.setLayout(updatepayLayout);
        updatepayLayout.setHorizontalGroup(
            updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatepayLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updatepayLayout.createSequentialGroup()
                        .addComponent(table_upay, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addGroup(updatepayLayout.createSequentialGroup()
                        .addGroup(updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updatepayLayout.createSequentialGroup()
                                .addComponent(balance_upay, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(balance_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatepayLayout.createSequentialGroup()
                                .addGroup(updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(id_upay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(total_upay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pay_upay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(paid_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(total_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(id_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(91, 193, Short.MAX_VALUE)
                                .addGroup(updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(customerid_upay, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(methord_upay)
                                    .addComponent(date_upay, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(29, 29, 29)
                        .addGroup(updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(methord_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customerid_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatepayLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(reset_upayb)
                .addGap(37, 37, 37)
                .addComponent(upadete_upay)
                .addGap(87, 87, 87))
        );
        updatepayLayout.setVerticalGroup(
            updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatepayLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_upay)
                    .addComponent(id_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerid_upay)
                    .addComponent(customerid_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(total_upay)
                    .addGroup(updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(total_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(methord_upay)
                        .addComponent(methord_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pay_upay)
                    .addComponent(paid_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_upay)
                    .addComponent(date_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(balance_upay)
                    .addComponent(balance_upayt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(table_upay, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(updatepayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reset_upayb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(upadete_upay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tabpayment.addTab("Update Info", updatepay);

        PAYMENT.add(tabpayment, java.awt.BorderLayout.CENTER);

        panalcontent.add(PAYMENT, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        VIEWBOOKIN.setBackground(new java.awt.Color(255, 255, 255));
        VIEWBOOKIN.setForeground(new java.awt.Color(255, 255, 255));
        VIEWBOOKIN.setMaximumSize(new java.awt.Dimension(1200, 1200));
        VIEWBOOKIN.setPreferredSize(new java.awt.Dimension(810, 570));

        jTabbedPane1.setBackground(new java.awt.Color(51, 153, 255));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(810, 570));

        packagename_viewt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packagename_viewtActionPerformed(evt);
            }
        });

        packagename_view.setText("Package name");

        customerid_view.setText("Customer ID");

        no_view.setText("No.Of Passengers");

        date_view.setText("Date");

        jTable8.setForeground(new java.awt.Color(51, 153, 255));
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "Package ID", "Package Name", "Passengers", "Status", "Date", "Customer ID"
            }
        ));
        jTable8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable8MouseClicked(evt);
            }
        });
        table_view.setViewportView(jTable8);

        customerid_view1.setText("Status");

        date_view1.setText("ID");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerid_view1)
                    .addComponent(customerid_view)
                    .addComponent(packagename_view))
                .addGap(61, 61, 61)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(status_view, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerid_viewt, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(packagename_viewt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(no_view, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(no_viewt, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 56, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(date_view)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(date_viewt, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(date_view1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(id_view, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(table_view, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(64, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(no_view)
                    .addComponent(no_viewt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(packagename_viewt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(packagename_view))
                .addGap(55, 55, 55)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(customerid_viewt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(customerid_view))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(date_view)
                        .addComponent(date_viewt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status_view, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerid_view1)
                    .addComponent(date_view1)
                    .addComponent(id_view, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(389, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap(261, Short.MAX_VALUE)
                    .addComponent(table_view, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(121, 121, 121)))
        );

        jTabbedPane1.addTab("View", jPanel4);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(810, 570));

        jComboBox2.setBackground(new java.awt.Color(51, 153, 255));
        jComboBox2.setForeground(new java.awt.Color(255, 255, 255));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select One For Advance Search", "ID", "Customer ID", "No. of passengers", "Date", " " }));

        jLabel5.setText("Value 1");

        val2sea.setEditable(false);
        val2sea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                val2seaActionPerformed(evt);
            }
        });
        val2sea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                val2seaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                val2seaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                val2seaKeyTyped(evt);
            }
        });

        jLabel8.setText("Value 2");

        val1sea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                val1seaActionPerformed(evt);
            }
        });
        val1sea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                val1seaKeyReleased(evt);
            }
        });

        jTable19.setForeground(new java.awt.Color(51, 153, 255));
        jTable19.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "Package ID", "Package Name", "Passengers", "Status", "Date", "Customer ID"
            }
        ));
        jTable19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable19MouseClicked(evt);
            }
        });
        table_view2.setViewportView(jTable19);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(val1sea, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(val2sea, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(202, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(63, 63, 63)
                    .addComponent(table_view2, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(29, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(val2sea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(val1sea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(434, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(177, 177, 177)
                    .addComponent(table_view2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(205, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Search", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(810, 570));

        packagename_viewt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packagename_viewt1ActionPerformed(evt);
            }
        });
        packagename_viewt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                packagename_viewt1KeyReleased(evt);
            }
        });

        packagename_view1.setText("Package name");

        customerid_view2.setText("Customer ID");

        customerid_viewt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                customerid_viewt1KeyTyped(evt);
            }
        });

        no_viewt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                no_viewt1KeyTyped(evt);
            }
        });

        no_view1.setText("No.Of Passengers");

        date_view2.setText("Date");

        jTable18.setForeground(new java.awt.Color(51, 153, 255));
        jTable18.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "Package ID", "Package Name", "Passengers", "Status", "Date", "Customer ID"
            }
        ));
        jTable18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable18MouseClicked(evt);
            }
        });
        table_view1.setViewportView(jTable18);

        search_viewb1.setBackground(new java.awt.Color(51, 153, 255));
        search_viewb1.setForeground(new java.awt.Color(255, 255, 255));
        search_viewb1.setText("update");
        search_viewb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_viewb1ActionPerformed(evt);
            }
        });

        customerid_view3.setText("Status");

        id_view1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                id_view1KeyTyped(evt);
            }
        });

        date_view3.setText("ID");

        jButton4.setBackground(new java.awt.Color(51, 153, 255));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Reset");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        search_viewb2.setBackground(new java.awt.Color(51, 153, 255));
        search_viewb2.setForeground(new java.awt.Color(255, 255, 255));
        search_viewb2.setText("Delete");
        search_viewb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_viewb2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(search_viewb2)
                .addGap(21, 21, 21)
                .addComponent(jButton4)
                .addGap(26, 26, 26)
                .addComponent(search_viewb1)
                .addGap(206, 206, 206))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(packagename_view1)
                            .addComponent(customerid_view2)
                            .addComponent(customerid_view3))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status_view1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customerid_viewt1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(packagename_viewt1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(126, 126, 126)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(date_view2)
                            .addComponent(date_view3)
                            .addComponent(no_view1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(no_viewt1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_view1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date_pacd1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(table_view1, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(no_viewt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(no_view1)
                    .addComponent(packagename_view1)
                    .addComponent(packagename_viewt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerid_view2)
                    .addComponent(date_view2)
                    .addComponent(customerid_viewt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_pacd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerid_view3)
                    .addComponent(date_view3)
                    .addComponent(id_view1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status_view1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(table_view1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(search_viewb1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search_viewb2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37))
        );

        jTabbedPane1.addTab("Update", jPanel3);

        javax.swing.GroupLayout VIEWBOOKINLayout = new javax.swing.GroupLayout(VIEWBOOKIN);
        VIEWBOOKIN.setLayout(VIEWBOOKINLayout);
        VIEWBOOKINLayout.setHorizontalGroup(
            VIEWBOOKINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        VIEWBOOKINLayout.setVerticalGroup(
            VIEWBOOKINLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panalcontent.add(VIEWBOOKIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        REPORTS1.setBackground(new java.awt.Color(255, 255, 255));
        REPORTS1.setPreferredSize(new java.awt.Dimension(810, 570));

        javax.swing.GroupLayout REPORTS1Layout = new javax.swing.GroupLayout(REPORTS1);
        REPORTS1.setLayout(REPORTS1Layout);
        REPORTS1Layout.setHorizontalGroup(
            REPORTS1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        REPORTS1Layout.setVerticalGroup(
            REPORTS1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panalcontent.add(REPORTS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 1, -1, -1));

        NEW.setBackground(new java.awt.Color(255, 255, 255));
        NEW.setPreferredSize(new java.awt.Dimension(810, 570));

        tabnew15.setBackground(new java.awt.Color(51, 153, 255));
        tabnew15.setForeground(new java.awt.Color(255, 255, 255));
        tabnew15.setPreferredSize(new java.awt.Dimension(810, 570));

        addnew15.setBackground(new java.awt.Color(255, 255, 255));
        addnew15.setMaximumSize(new java.awt.Dimension(1200, 1200));
        addnew15.setPreferredSize(new java.awt.Dimension(810, 570));

        place1_anew16.setText("places");

        place1_anewt16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                place1_anewt16place1_anewtActionPerformed(evt);
            }
        });

        customerid_anew16.setText("Customer ID");

        guide_anew16.setText("Guide");

        customerid_anewt16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                customerid_anewt16KeyTyped(evt);
            }
        });

        driver_anew16.setText("Driver");

        vehical_anew16.setText("Vehical No.");

        nofday_anew16.setText(" Passengers");

        pass_anewt16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pass_anewt16KeyTyped(evt);
            }
        });

        fee_anew16.setText("fee");

        fee_anewt16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fee_anewt16KeyTyped(evt);
            }
        });

        date_anew16.setText("Date");

        add_anewb16.setBackground(new java.awt.Color(51, 153, 255));
        add_anewb16.setForeground(new java.awt.Color(255, 255, 255));
        add_anewb16.setText("Add");
        add_anewb16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_anewb16add_anewbActionPerformed(evt);
            }
        });

        demonew.setBackground(new java.awt.Color(51, 153, 255));
        demonew.setForeground(new java.awt.Color(255, 255, 255));
        demonew.setText("DEMO");
        demonew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demonewActionPerformed(evt);
            }
        });

        demonew1.setBackground(new java.awt.Color(51, 153, 255));
        demonew1.setForeground(new java.awt.Color(255, 255, 255));
        demonew1.setText("Reset");
        demonew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demonew1ActionPerformed(evt);
            }
        });

        fee_anew17.setText("Description");

        days_anewt17.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                days_anewt17KeyTyped(evt);
            }
        });

        fee_anew18.setText("No.of Days");

        javax.swing.GroupLayout addnew15Layout = new javax.swing.GroupLayout(addnew15);
        addnew15.setLayout(addnew15Layout);
        addnew15Layout.setHorizontalGroup(
            addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addnew15Layout.createSequentialGroup()
                .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addnew15Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nofday_anew16)
                            .addComponent(fee_anew17)
                            .addComponent(date_anew16)
                            .addComponent(place1_anew16)))
                    .addGroup(addnew15Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(customerid_anew16)))
                .addGap(10, 10, 10)
                .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addnew15Layout.createSequentialGroup()
                        .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(place1_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pass_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customerid_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addnew15Layout.createSequentialGroup()
                                .addGap(181, 181, 181)
                                .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(guide_anew16)
                                    .addComponent(driver_anew16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(driver_anewt16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(guide_anewt16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(addnew15Layout.createSequentialGroup()
                                .addGap(173, 173, 173)
                                .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(addnew15Layout.createSequentialGroup()
                                        .addComponent(fee_anew18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(days_anewt17, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(addnew15Layout.createSequentialGroup()
                                        .addComponent(vehical_anew16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(vechical_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(addnew15Layout.createSequentialGroup()
                        .addComponent(dis_anewt17, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)
                        .addComponent(fee_anew16)
                        .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(addnew15Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(demonew)
                                .addGap(18, 18, 18)
                                .addComponent(add_anewb16))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addnew15Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fee_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(demonew1)
                .addContainerGap(123, Short.MAX_VALUE))
        );
        addnew15Layout.setVerticalGroup(
            addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addnew15Layout.createSequentialGroup()
                .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addnew15Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(fee_anew18)
                                .addComponent(days_anewt17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(customerid_anew16)
                                .addComponent(customerid_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(addnew15Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(place1_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(place1_anew16)
                            .addComponent(vehical_anew16)
                            .addComponent(vechical_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nofday_anew16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pass_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(guide_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(guide_anew16)))
                .addGap(48, 48, 48)
                .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(date_anew16)
                    .addComponent(date_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(driver_anew16)
                        .addComponent(driver_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53)
                .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fee_anew17)
                    .addComponent(dis_anewt17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fee_anew16)
                    .addComponent(fee_anewt16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addGroup(addnew15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_anewb16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(demonew, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(demonew1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );

        tabnew15.addTab("Add New", addnew15);
        addnew15.getAccessibleContext().setAccessibleName("");

        viewnew4.setBackground(new java.awt.Color(255, 255, 255));
        viewnew4.setPreferredSize(new java.awt.Dimension(810, 570));

        jLabel147.setText("Vehical No.");

        vehicle_newvt.setEditable(false);

        jLabel148.setText(" No. of Days");

        plase_newvt.setEditable(false);
        plase_newvt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plase_newvtActionPerformed(evt);
            }
        });

        jLabel149.setText("fee");

        noof_newvt.setEditable(false);

        jLabel150.setText("Date");

        fee_newvt.setEditable(false);

        jLabel151.setText("Passengers");

        jLabel152.setText("Guide ");

        driver_newvt.setEditable(false);

        cid_newvt.setEditable(false);

        guide_newvt.setEditable(false);

        jLabel153.setText("places");

        pass_newvt.setEditable(false);

        jLabel154.setText("Driver");

        jLabel155.setText("Customer ID");

        jTable21.setForeground(new java.awt.Color(51, 153, 255));
        jTable21.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null}
            },
            new String [] {
                "Package ID", "Date", "Customer ID", "places"
            }
        ));
        jTable21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable21MouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(jTable21);

        date_newvt.setEditable(false);

        dis_newvt.setEditable(false);

        jLabel156.setText("Description");

        id_newvt.setEditable(false);

        jLabel157.setText("ID");

        javax.swing.GroupLayout viewnew4Layout = new javax.swing.GroupLayout(viewnew4);
        viewnew4.setLayout(viewnew4Layout);
        viewnew4Layout.setHorizontalGroup(
            viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewnew4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(viewnew4Layout.createSequentialGroup()
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(viewnew4Layout.createSequentialGroup()
                                .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel155)
                                    .addComponent(jLabel153)
                                    .addComponent(jLabel148)
                                    .addComponent(jLabel150)
                                    .addComponent(jLabel149))
                                .addGap(19, 19, 19)
                                .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(noof_newvt, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                    .addComponent(cid_newvt, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                    .addComponent(plase_newvt, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                    .addComponent(date_newvt)
                                    .addComponent(fee_newvt)))
                            .addGroup(viewnew4Layout.createSequentialGroup()
                                .addComponent(jLabel157)
                                .addGap(69, 69, 69)
                                .addComponent(id_newvt, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
                        .addGap(214, 214, 214)
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel156)
                            .addComponent(jLabel151)
                            .addComponent(jLabel147)
                            .addComponent(jLabel152)
                            .addComponent(jLabel154))
                        .addGap(41, 41, 41)
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(driver_newvt, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(guide_newvt, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(vehicle_newvt, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(pass_newvt, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(dis_newvt))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        viewnew4Layout.setVerticalGroup(
            viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewnew4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_newvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel157))
                .addGap(24, 24, 24)
                .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewnew4Layout.createSequentialGroup()
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel151)
                            .addComponent(pass_newvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vehicle_newvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel147))
                        .addGap(30, 30, 30)
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guide_newvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel152))
                        .addGap(30, 30, 30)
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(driver_newvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel154))
                        .addGap(30, 30, 30)
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dis_newvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel156)))
                    .addGroup(viewnew4Layout.createSequentialGroup()
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel155)
                            .addComponent(cid_newvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel153)
                            .addComponent(plase_newvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel148)
                            .addComponent(noof_newvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel150)
                            .addComponent(date_newvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(viewnew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel149)
                            .addComponent(fee_newvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabnew15.addTab("View Details", viewnew4);

        updatenew4.setBackground(new java.awt.Color(255, 255, 255));
        updatenew4.setPreferredSize(new java.awt.Dimension(810, 570));

        jButton37.setBackground(new java.awt.Color(51, 153, 255));
        jButton37.setForeground(new java.awt.Color(255, 255, 255));
        jButton37.setText("Delete");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton38.setBackground(new java.awt.Color(51, 153, 255));
        jButton38.setForeground(new java.awt.Color(255, 255, 255));
        jButton38.setText("Reset");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jLabel138.setText(" No. of Days");

        jLabel139.setText("fee");

        jTable20.setForeground(new java.awt.Color(51, 153, 255));
        jTable20.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null}
            },
            new String [] {
                "Package ID", "Date", "Customer ID", "places"
            }
        ));
        jTable20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable20MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable20);

        plase_newvt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plase_newvt1ActionPerformed(evt);
            }
        });

        jLabel140.setText("Date");

        jLabel141.setText("Driver");

        jLabel142.setText("vehical No.");

        jLabel143.setText("ID");

        cid_newvt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cid_newvt1ActionPerformed(evt);
            }
        });

        jLabel144.setText("Places");

        jLabel145.setText("Guide");

        jLabel146.setText("Customer ID");

        id_newvt1.setEditable(false);

        jLabel158.setText("Passengers");

        jLabel159.setText("Discripyion");

        jButton39.setBackground(new java.awt.Color(51, 153, 255));
        jButton39.setForeground(new java.awt.Color(255, 255, 255));
        jButton39.setText("Update");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updatenew4Layout = new javax.swing.GroupLayout(updatenew4);
        updatenew4.setLayout(updatenew4Layout);
        updatenew4Layout.setHorizontalGroup(
            updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatenew4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updatenew4Layout.createSequentialGroup()
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(updatenew4Layout.createSequentialGroup()
                        .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updatenew4Layout.createSequentialGroup()
                                .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(updatenew4Layout.createSequentialGroup()
                                        .addComponent(jLabel138)
                                        .addGap(33, 33, 33)
                                        .addComponent(noof_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel140)
                                    .addGroup(updatenew4Layout.createSequentialGroup()
                                        .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(updatenew4Layout.createSequentialGroup()
                                                .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel143)
                                                    .addComponent(jLabel139))
                                                .addGap(69, 69, 69))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatenew4Layout.createSequentialGroup()
                                                .addComponent(jLabel146)
                                                .addGap(31, 31, 31)))
                                        .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(id_newvt1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                            .addComponent(fee_newvt1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                            .addComponent(cid_newvt1)
                                            .addComponent(date_newvt1)))
                                    .addGroup(updatenew4Layout.createSequentialGroup()
                                        .addComponent(jLabel144)
                                        .addGap(61, 61, 61)
                                        .addComponent(plase_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(203, 203, 203))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatenew4Layout.createSequentialGroup()
                                .addComponent(jButton37)
                                .addGap(1, 1, 1)))
                        .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updatenew4Layout.createSequentialGroup()
                                .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel145)
                                    .addComponent(jLabel141)
                                    .addComponent(jLabel159))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(updatenew4Layout.createSequentialGroup()
                                .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(updatenew4Layout.createSequentialGroup()
                                        .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel158)
                                            .addComponent(jLabel142))
                                        .addGap(43, 43, 43)
                                        .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(vehicle_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(pass_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(driver_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(guide_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dis_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(updatenew4Layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(jButton38)
                                        .addGap(50, 50, 50)
                                        .addComponent(jButton39)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        updatenew4Layout.setVerticalGroup(
            updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatenew4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel143))
                .addGap(21, 21, 21)
                .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cid_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel158)
                    .addComponent(pass_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plase_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel142)
                    .addComponent(vehicle_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel144))
                .addGap(31, 31, 31)
                .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel138)
                    .addComponent(noof_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel145)
                    .addComponent(guide_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel140)
                    .addComponent(date_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel141)
                    .addComponent(driver_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fee_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel139)
                    .addComponent(jLabel159)
                    .addComponent(dis_newvt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(updatenew4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );

        tabnew15.addTab("Update Info", updatenew4);

        javax.swing.GroupLayout NEWLayout = new javax.swing.GroupLayout(NEW);
        NEW.setLayout(NEWLayout);
        NEWLayout.setHorizontalGroup(
            NEWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabnew15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        NEWLayout.setVerticalGroup(
            NEWLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabnew15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panalcontent.add(NEW, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        OFFERS.setBackground(new java.awt.Color(255, 255, 255));
        OFFERS.setPreferredSize(new java.awt.Dimension(810, 570));

        jTabbedPane4.setBackground(new java.awt.Color(51, 153, 255));
        jTabbedPane4.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane4.setPreferredSize(new java.awt.Dimension(810, 570));

        ViewO.setBackground(new java.awt.Color(255, 255, 255));
        ViewO.setPreferredSize(new java.awt.Dimension(810, 570));

        jLabel3.setText("ID");

        id_ov.setEditable(false);

        date_ov.setEditable(false);

        jLabel6.setText("Name");

        pac_ov.setEditable(false);

        jLabel7.setText("Package ID");

        name_ov.setEditable(false);

        jLabel24.setText("Date");

        duration_ov1.setText("Duration");

        duration_ov.setEditable(false);

        discount_ov1.setText("Discount");

        discount_ov.setEditable(false);

        jTable6.setForeground(new java.awt.Color(51, 153, 255));
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Package ID", "Date", "Duration", "Discount"
            }
        ));
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jLabel9.setText("Description");

        dis_ov.setEditable(false);

        javax.swing.GroupLayout ViewOLayout = new javax.swing.GroupLayout(ViewO);
        ViewO.setLayout(ViewOLayout);
        ViewOLayout.setHorizontalGroup(
            ViewOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ViewOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ViewOLayout.createSequentialGroup()
                        .addGroup(ViewOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ViewOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(ViewOLayout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(40, 40, 40)
                                    .addComponent(name_ov, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(ViewOLayout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(id_ov, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(ViewOLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(pac_ov, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                        .addGroup(ViewOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewOLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(date_ov, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewOLayout.createSequentialGroup()
                                .addComponent(duration_ov1)
                                .addGap(50, 50, 50)
                                .addComponent(duration_ov, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ViewOLayout.createSequentialGroup()
                                .addComponent(discount_ov1)
                                .addGap(50, 50, 50)
                                .addComponent(discount_ov, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(ViewOLayout.createSequentialGroup()
                .addGap(237, 237, 237)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(dis_ov, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ViewOLayout.setVerticalGroup(
            ViewOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ViewOLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(ViewOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(id_ov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_ov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(41, 41, 41)
                .addGroup(ViewOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(name_ov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(duration_ov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(duration_ov1))
                .addGap(46, 46, 46)
                .addGroup(ViewOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(pac_ov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discount_ov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discount_ov1))
                .addGap(30, 30, 30)
                .addGroup(ViewOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(dis_ov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        jTabbedPane4.addTab("view", ViewO);

        AddO.setBackground(new java.awt.Color(255, 255, 255));
        AddO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        AddO.setPreferredSize(new java.awt.Dimension(810, 570));

        jLabel60.setText("Discount");

        jLabel61.setText("Description");

        jButton20.setBackground(new java.awt.Color(51, 153, 255));
        jButton20.setForeground(new java.awt.Color(255, 255, 255));
        jButton20.setText("Add");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        pac_oa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pac_oaKeyTyped(evt);
            }
        });

        jLabel62.setText("Name");

        jLabel63.setText("Package ID");

        jLabel64.setText("Duration");

        jLabel65.setText("Date");

        jButton22.setBackground(new java.awt.Color(51, 153, 255));
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setText("Reset");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(51, 153, 255));
        jButton21.setForeground(new java.awt.Color(255, 255, 255));
        jButton21.setText("DEMO");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddOLayout = new javax.swing.GroupLayout(AddO);
        AddO.setLayout(AddOLayout);
        AddOLayout.setHorizontalGroup(
            AddOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddOLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(AddOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddOLayout.createSequentialGroup()
                        .addGroup(AddOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61)
                            .addComponent(jLabel62))
                        .addGap(45, 45, 45)
                        .addGroup(AddOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dis_oa, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_oa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddOLayout.createSequentialGroup()
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pac_oa, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addGroup(AddOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddOLayout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(date_oa, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddOLayout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addGap(50, 50, 50)
                        .addComponent(duration_oa, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddOLayout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addGap(50, 50, 50)
                        .addComponent(discount_oa, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(62, 62, 62))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddOLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton21)
                .addGap(36, 36, 36)
                .addComponent(jButton20)
                .addGap(32, 32, 32)
                .addComponent(jButton22)
                .addGap(46, 46, 46))
        );
        AddOLayout.setVerticalGroup(
            AddOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddOLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(AddOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(name_oa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_oa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65)
                    .addComponent(jLabel62))
                .addGap(41, 41, 41)
                .addGroup(AddOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dis_oa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(duration_oa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64)
                    .addComponent(jLabel61))
                .addGap(46, 46, 46)
                .addGroup(AddOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63)
                    .addComponent(pac_oa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discount_oa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                .addGroup(AddOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );

        jTabbedPane4.addTab("add", AddO);

        updateO.setBackground(new java.awt.Color(255, 255, 255));
        updateO.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        updateO.setPreferredSize(new java.awt.Dimension(810, 570));

        jLabel66.setText("Discount");

        jLabel67.setText("ID");

        id_ou.setEditable(false);

        jTable7.setForeground(new java.awt.Color(51, 153, 255));
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Package ID", "Date", "Duration", "Discount"
            }
        ));
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable7);

        jLabel68.setText("Name");

        jLabel91.setText("Package ID");

        jLabel92.setText("Duration");

        jLabel93.setText("Datel");

        jButton26.setBackground(new java.awt.Color(51, 153, 255));
        jButton26.setForeground(new java.awt.Color(255, 255, 255));
        jButton26.setText("Update");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setBackground(new java.awt.Color(51, 153, 255));
        jButton27.setForeground(new java.awt.Color(255, 255, 255));
        jButton27.setText("Reset");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setBackground(new java.awt.Color(51, 153, 255));
        jButton28.setForeground(new java.awt.Color(255, 255, 255));
        jButton28.setText("Delete");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel94.setText("Description");

        javax.swing.GroupLayout updateOLayout = new javax.swing.GroupLayout(updateO);
        updateO.setLayout(updateOLayout);
        updateOLayout.setHorizontalGroup(
            updateOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateOLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(updateOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateOLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(updateOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(updateOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(updateOLayout.createSequentialGroup()
                                    .addComponent(jLabel68)
                                    .addGap(40, 40, 40)
                                    .addComponent(name_ou, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(updateOLayout.createSequentialGroup()
                                    .addComponent(jLabel67)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(id_ou, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(updateOLayout.createSequentialGroup()
                                .addComponent(jLabel91)
                                .addGap(18, 18, 18)
                                .addComponent(pac_ou, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                        .addGroup(updateOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateOLayout.createSequentialGroup()
                                .addComponent(jLabel93)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(date_ou, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateOLayout.createSequentialGroup()
                                .addComponent(jLabel92)
                                .addGap(50, 50, 50)
                                .addComponent(duration_ou, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateOLayout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addGap(50, 50, 50)
                                .addComponent(discount_ou, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(42, 42, 42))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateOLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton28)
                .addGap(29, 29, 29)
                .addComponent(jButton26)
                .addGap(28, 28, 28)
                .addComponent(jButton27)
                .addGap(68, 68, 68))
            .addGroup(updateOLayout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(jLabel94)
                .addGap(18, 18, 18)
                .addComponent(dis_ou, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        updateOLayout.setVerticalGroup(
            updateOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateOLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(updateOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(id_ou, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_ou, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93))
                .addGap(41, 41, 41)
                .addGroup(updateOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(name_ou, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(duration_ou, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel92))
                .addGap(46, 46, 46)
                .addGroup(updateOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(pac_ou, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discount_ou, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66))
                .addGap(34, 34, 34)
                .addGroup(updateOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(dis_ou, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(updateOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jTabbedPane4.addTab("update", updateO);

        javax.swing.GroupLayout OFFERSLayout = new javax.swing.GroupLayout(OFFERS);
        OFFERS.setLayout(OFFERSLayout);
        OFFERSLayout.setHorizontalGroup(
            OFFERSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        OFFERSLayout.setVerticalGroup(
            OFFERSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OFFERSLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panalcontent.add(OFFERS, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, -1, -1));

        DRIVER.setBackground(new java.awt.Color(255, 255, 255));
        DRIVER.setPreferredSize(new java.awt.Dimension(810, 570));
        DRIVER.setLayout(new java.awt.BorderLayout());

        tabdriver.setBackground(new java.awt.Color(51, 153, 255));
        tabdriver.setForeground(new java.awt.Color(255, 255, 255));
        tabdriver.setMaximumSize(new java.awt.Dimension(1200, 1200));
        tabdriver.setPreferredSize(new java.awt.Dimension(810, 570));

        addcus1.setBackground(new java.awt.Color(255, 255, 255));
        addcus1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        addcus1.setMaximumSize(new java.awt.Dimension(1200, 1200));
        addcus1.setPreferredSize(new java.awt.Dimension(810, 570));

        namecus1.setText("Name");

        niccus1.setText("NIC");

        addresscus1.setText("Address");

        telephonecus1.setText("Telephone");

        namecust1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namecust1ActionPerformed(evt);
            }
        });

        telephonecust1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telephonecust1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telephonecust1KeyTyped(evt);
            }
        });

        addresscust1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addresscust1ActionPerformed(evt);
            }
        });

        addcusb1.setBackground(new java.awt.Color(51, 153, 255));
        addcusb1.setForeground(new java.awt.Color(255, 255, 255));
        addcusb1.setText("Add");
        addcusb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addcusb1ActionPerformed(evt);
            }
        });

        resetcusb1.setBackground(new java.awt.Color(51, 153, 255));
        resetcusb1.setForeground(new java.awt.Color(255, 255, 255));
        resetcusb1.setText("Reset");
        resetcusb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetcusb1ActionPerformed(evt);
            }
        });

        emaill1.setText("Licence");

        demo1.setBackground(new java.awt.Color(51, 153, 255));
        demo1.setForeground(new java.awt.Color(255, 255, 255));
        demo1.setText("DEMO");
        demo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demo1ActionPerformed(evt);
            }
        });

        emaill3.setText("Allocate Vehicle");

        javax.swing.GroupLayout addcus1Layout = new javax.swing.GroupLayout(addcus1);
        addcus1.setLayout(addcus1Layout);
        addcus1Layout.setHorizontalGroup(
            addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addcus1Layout.createSequentialGroup()
                .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addcus1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namecus1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(niccus1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addcus1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(emaill1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namecust1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(licence_da, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(niccust2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(119, 119, 119)
                .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addresscus1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telephonecus1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(emaill3, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addcus1Layout.createSequentialGroup()
                        .addComponent(demo1)
                        .addGap(18, 18, 18)
                        .addComponent(resetcusb1)
                        .addGap(29, 29, 29)
                        .addComponent(addcusb1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addcus1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(telephonecust1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vehicle_da, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addresscust1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        addcus1Layout.setVerticalGroup(
            addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addcus1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addresscus1)
                        .addComponent(addresscust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addcus1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namecus1)
                            .addComponent(namecust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addcus1Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(telephonecus1)
                                    .addComponent(telephonecust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(addcus1Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(niccust2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(niccus1))))))
                .addGap(59, 59, 59)
                .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(licence_da, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emaill1)
                    .addComponent(vehicle_da, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emaill3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addGroup(addcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetcusb1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addcusb1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(demo1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106))
        );

        tabdriver.addTab("Add New", addcus1);

        viewcus1.setBackground(new java.awt.Color(255, 255, 255));
        viewcus1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        viewcus1.setMaximumSize(new java.awt.Dimension(1200, 1200));
        viewcus1.setPreferredSize(new java.awt.Dimension(810, 570));

        idvcus1.setText("ID");

        namevcus1.setText("Name");

        nicvcus1.setText("NIC");

        addressvcus1.setText("Address");

        telephonevcus1.setText("Telephone");

        name_vcust1.setEditable(false);
        name_vcust1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_vcust1ActionPerformed(evt);
            }
        });

        id_vcust1.setEditable(false);

        nic_vcut1.setEditable(false);

        telephone_vcust1.setEditable(false);

        address_vcust1.setEditable(false);
        address_vcust1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                address_vcust1ActionPerformed(evt);
            }
        });

        jTable23.setForeground(new java.awt.Color(51, 153, 255));
        jTable23.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "NIC", "Address", "Telephone", "Booking ID"
            }
        ));
        jTable23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable23MouseClicked(evt);
            }
        });
        table_vcus1.setViewportView(jTable23);

        bookingid_vcus1.setText("Allocate Vechile");

        vechile_vcust1.setEditable(false);

        emailvcus2.setText("Licence");

        licen_vcust2.setEditable(false);

        javax.swing.GroupLayout viewcus1Layout = new javax.swing.GroupLayout(viewcus1);
        viewcus1.setLayout(viewcus1Layout);
        viewcus1Layout.setHorizontalGroup(
            viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewcus1Layout.createSequentialGroup()
                .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewcus1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(table_vcus1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(viewcus1Layout.createSequentialGroup()
                                .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(viewcus1Layout.createSequentialGroup()
                                            .addComponent(nicvcus1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewcus1Layout.createSequentialGroup()
                                            .addComponent(namevcus1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(47, 47, 47)))
                                    .addGroup(viewcus1Layout.createSequentialGroup()
                                        .addComponent(emailvcus2)
                                        .addGap(32, 32, 32)))
                                .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(licen_vcust2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(name_vcust1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nic_vcut1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bookingid_vcus1)
                                    .addComponent(telephonevcus1)
                                    .addComponent(addressvcus1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(address_vcust1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(telephone_vcust1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vechile_vcust1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(viewcus1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(idvcus1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(id_vcust1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        viewcus1Layout.setVerticalGroup(
            viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewcus1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_vcust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idvcus1))
                .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(viewcus1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewcus1Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(telephone_vcust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(telephonevcus1)))
                            .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(address_vcust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(addressvcus1)))
                        .addGap(52, 52, 52)
                        .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bookingid_vcus1)
                            .addComponent(vechile_vcust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addGroup(viewcus1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name_vcust1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namevcus1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(licen_vcust2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailvcus2))
                        .addGap(44, 44, 44)
                        .addGroup(viewcus1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nicvcus1)
                            .addComponent(nic_vcut1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(table_vcus1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        tabdriver.addTab("View Details", viewcus1);

        updatecus1.setBackground(new java.awt.Color(255, 255, 255));
        updatecus1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        updatecus1.setMaximumSize(new java.awt.Dimension(1200, 1200));
        updatecus1.setPreferredSize(new java.awt.Dimension(810, 570));
        updatecus1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        id_ucus1.setText("ID");
        updatecus1.add(id_ucus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 35, -1));

        name_ucus1.setText("Name");
        updatecus1.add(name_ucus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 35, -1));

        address_ucus1.setText("Address");
        updatecus1.add(address_ucus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 66, -1));

        nic_ucus2.setText("NIC");
        updatecus1.add(nic_ucus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 35, -1));

        telephone_ucus1.setText("Telephone");
        updatecus1.add(telephone_ucus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 66, -1));

        name_ucust1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_ucust1ActionPerformed(evt);
            }
        });
        updatecus1.add(name_ucust1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 194, -1));

        id_ucust1.setEditable(false);
        updatecus1.add(id_ucust1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 194, -1));
        updatecus1.add(nic_ucust1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 194, -1));
        updatecus1.add(telephone_ucust1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 194, -1));

        adderss_ucust1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adderss_ucust1ActionPerformed(evt);
            }
        });
        updatecus1.add(adderss_ucust1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 194, -1));

        jTable25.setForeground(new java.awt.Color(51, 153, 255));
        jTable25.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "ID", "Name", "NIC", "Address", "Telephone"
            }
        ));
        jTable25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable25MouseClicked(evt);
            }
        });
        table_ucust3.setViewportView(jTable25);

        updatecus1.add(table_ucust3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 729, 190));

        update_ucust1.setBackground(new java.awt.Color(51, 153, 255));
        update_ucust1.setForeground(new java.awt.Color(255, 255, 255));
        update_ucust1.setText("Update");
        update_ucust1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_ucust1ActionPerformed(evt);
            }
        });
        updatecus1.add(update_ucust1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 470, -1, 40));

        rest_ucust1.setBackground(new java.awt.Color(51, 153, 255));
        rest_ucust1.setForeground(new java.awt.Color(255, 255, 255));
        rest_ucust1.setText("Reset");
        rest_ucust1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rest_ucust1ActionPerformed(evt);
            }
        });
        updatecus1.add(rest_ucust1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 470, -1, 40));
        updatecus1.add(email_ucust1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 194, -1));

        nic_ucus3.setText("Vehicle");
        updatecus1.add(nic_ucus3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 50, -1));

        delete_cus1.setBackground(new java.awt.Color(51, 153, 255));
        delete_cus1.setForeground(new java.awt.Color(255, 255, 255));
        delete_cus1.setText("Delete");
        delete_cus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_cus1ActionPerformed(evt);
            }
        });
        updatecus1.add(delete_cus1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 470, -1, 40));

        licence_ucust1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                licence_ucust1ActionPerformed(evt);
            }
        });
        updatecus1.add(licence_ucust1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 194, -1));

        name_ucus3.setText("Licence");
        updatecus1.add(name_ucus3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 35, -1));

        tabdriver.addTab("Update Info", updatecus1);

        DRIVER.add(tabdriver, java.awt.BorderLayout.CENTER);

        panalcontent.add(DRIVER, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 810, 570));

        GUIDE.setBackground(new java.awt.Color(255, 255, 255));
        GUIDE.setEnabled(false);
        GUIDE.setPreferredSize(new java.awt.Dimension(810, 570));
        GUIDE.setLayout(new java.awt.BorderLayout());

        tabcustomer2.setBackground(new java.awt.Color(51, 153, 255));
        tabcustomer2.setForeground(new java.awt.Color(255, 255, 255));
        tabcustomer2.setMaximumSize(new java.awt.Dimension(1200, 1200));
        tabcustomer2.setPreferredSize(new java.awt.Dimension(810, 570));

        addcus2.setBackground(new java.awt.Color(255, 255, 255));
        addcus2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        addcus2.setMaximumSize(new java.awt.Dimension(1200, 1200));
        addcus2.setPreferredSize(new java.awt.Dimension(810, 570));

        namecus2.setText("Name");

        niccus2.setText("NIC");

        addresscus2.setText("Address");

        telephonecus2.setText("Telephone");

        namecust2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namecust2ActionPerformed(evt);
            }
        });

        telephonecust2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                telephonecust2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                telephonecust2KeyTyped(evt);
            }
        });

        addresscust2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addresscust2ActionPerformed(evt);
            }
        });

        addcusb2.setBackground(new java.awt.Color(51, 153, 255));
        addcusb2.setForeground(new java.awt.Color(255, 255, 255));
        addcusb2.setText("Add");
        addcusb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addcusb2ActionPerformed(evt);
            }
        });

        resetcusb2.setBackground(new java.awt.Color(51, 153, 255));
        resetcusb2.setForeground(new java.awt.Color(255, 255, 255));
        resetcusb2.setText("Reset");
        resetcusb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetcusb2ActionPerformed(evt);
            }
        });

        emaill2.setText("Email");

        demo2.setBackground(new java.awt.Color(51, 153, 255));
        demo2.setForeground(new java.awt.Color(255, 255, 255));
        demo2.setText("DEMO");
        demo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                demo2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addcus2Layout = new javax.swing.GroupLayout(addcus2);
        addcus2.setLayout(addcus2Layout);
        addcus2Layout.setHorizontalGroup(
            addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addcus2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(namecus2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(niccus2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(emaill2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namecust2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailt2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(niccust3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110)
                .addGroup(addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addresscus2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(telephonecus2, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addcus2Layout.createSequentialGroup()
                        .addComponent(demo2)
                        .addGap(18, 18, 18)
                        .addComponent(resetcusb2)
                        .addGap(29, 29, 29)
                        .addComponent(addcusb2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addresscust2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telephonecust2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        addcus2Layout.setVerticalGroup(
            addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addcus2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addresscust2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addresscus2)
                    .addGroup(addcus2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namecus2)
                            .addComponent(namecust2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addcus2Layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(telephonecus2)
                                    .addComponent(telephonecust2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(addcus2Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addGroup(addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(niccust3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(niccus2))))))
                .addGap(59, 59, 59)
                .addGroup(addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emaill2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addGroup(addcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetcusb2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addcusb2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(demo2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84))
        );

        tabcustomer2.addTab("Add New", addcus2);

        viewcus2.setBackground(new java.awt.Color(255, 255, 255));
        viewcus2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        viewcus2.setMaximumSize(new java.awt.Dimension(1200, 1200));
        viewcus2.setPreferredSize(new java.awt.Dimension(810, 570));

        idvcus2.setText("ID");

        namevcus2.setText("Name");

        nicvcus2.setText("NIC");

        addressvcus2.setText("Address");

        telephonevcus2.setText("Telephone");

        name_vcust2.setEditable(false);
        name_vcust2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_vcust2ActionPerformed(evt);
            }
        });

        id_vcust2.setEditable(false);

        nic_vcut2.setEditable(false);

        telephone_vcust2.setEditable(false);

        address_vcust2.setEditable(false);
        address_vcust2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                address_vcust2ActionPerformed(evt);
            }
        });

        jTable26.setForeground(new java.awt.Color(51, 153, 255));
        jTable26.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "NIC", "Address", "Telephone", "Booking ID"
            }
        ));
        jTable26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable26MouseClicked(evt);
            }
        });
        table_vcus2.setViewportView(jTable26);

        bookingid_vcus2.setText("Booking ID");

        bookingid_vcust2.setEditable(false);

        emailvcus3.setText("Email");

        email_vcust3.setEditable(false);

        javax.swing.GroupLayout viewcus2Layout = new javax.swing.GroupLayout(viewcus2);
        viewcus2.setLayout(viewcus2Layout);
        viewcus2Layout.setHorizontalGroup(
            viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewcus2Layout.createSequentialGroup()
                .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewcus2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(table_vcus2, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(viewcus2Layout.createSequentialGroup()
                                .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(viewcus2Layout.createSequentialGroup()
                                            .addComponent(nicvcus2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewcus2Layout.createSequentialGroup()
                                            .addComponent(namevcus2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(47, 47, 47)))
                                    .addGroup(viewcus2Layout.createSequentialGroup()
                                        .addComponent(emailvcus3)
                                        .addGap(32, 32, 32)))
                                .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(email_vcust3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(name_vcust2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nic_vcut2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bookingid_vcus2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(addressvcus2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(telephonevcus2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(address_vcust2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(telephone_vcust2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookingid_vcust2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(viewcus2Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(idvcus2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(id_vcust2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        viewcus2Layout.setVerticalGroup(
            viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewcus2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id_vcust2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idvcus2))
                .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(viewcus2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewcus2Layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(telephone_vcust2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(address_vcust2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(viewcus2Layout.createSequentialGroup()
                                .addComponent(addressvcus2)
                                .addGap(54, 54, 54)
                                .addComponent(telephonevcus2)))
                        .addGap(52, 52, 52)
                        .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bookingid_vcus2)
                            .addComponent(bookingid_vcust2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addGroup(viewcus2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name_vcust2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namevcus2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(email_vcust3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailvcus3))
                        .addGap(44, 44, 44)
                        .addGroup(viewcus2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nicvcus2)
                            .addComponent(nic_vcut2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(table_vcus2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );

        tabcustomer2.addTab("View Details", viewcus2);

        updatecus2.setBackground(new java.awt.Color(255, 255, 255));
        updatecus2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255)));
        updatecus2.setMaximumSize(new java.awt.Dimension(1200, 1200));
        updatecus2.setPreferredSize(new java.awt.Dimension(810, 570));
        updatecus2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        id_ucus2.setText("ID");
        updatecus2.add(id_ucus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 42, 35, -1));

        name_ucus2.setText("Name");
        updatecus2.add(name_ucus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 35, -1));

        address_ucus2.setText("Address");
        updatecus2.add(address_ucus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 42, 66, -1));

        nic_ucus4.setText("NIC");
        updatecus2.add(nic_ucus4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 143, 35, -1));

        telephone_ucus2.setText("Telephone");
        updatecus2.add(telephone_ucus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 66, -1));

        name_ucust2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_ucust2ActionPerformed(evt);
            }
        });
        updatecus2.add(name_ucust2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 194, -1));

        id_ucust2.setEditable(false);
        updatecus2.add(id_ucust2, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 39, 194, -1));
        updatecus2.add(nic_ucust2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 140, 194, -1));
        updatecus2.add(telephone_ucust2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 194, -1));

        adderss_ucust2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adderss_ucust2ActionPerformed(evt);
            }
        });
        updatecus2.add(adderss_ucust2, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 39, 194, -1));

        jTable28.setForeground(new java.awt.Color(51, 153, 255));
        jTable28.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "ID", "Name", "NIC", "Address", "Telephone"
            }
        ));
        jTable28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable28MouseClicked(evt);
            }
        });
        table_ucust5.setViewportView(jTable28);

        updatecus2.add(table_ucust5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 191, 729, 210));

        update_ucust2.setBackground(new java.awt.Color(51, 153, 255));
        update_ucust2.setForeground(new java.awt.Color(255, 255, 255));
        update_ucust2.setText("Update");
        update_ucust2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_ucust2ActionPerformed(evt);
            }
        });
        updatecus2.add(update_ucust2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 470, -1, 40));

        rest_ucust2.setBackground(new java.awt.Color(51, 153, 255));
        rest_ucust2.setForeground(new java.awt.Color(255, 255, 255));
        rest_ucust2.setText("Reset");
        rest_ucust2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rest_ucust2ActionPerformed(evt);
            }
        });
        updatecus2.add(rest_ucust2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 470, -1, 40));
        updatecus2.add(email_ucust2, new org.netbeans.lib.awtextra.AbsoluteConstraints(476, 140, 194, -1));

        nic_ucus5.setText("Email");
        updatecus2.add(nic_ucus5, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 143, 35, -1));

        delete_cus2.setBackground(new java.awt.Color(51, 153, 255));
        delete_cus2.setForeground(new java.awt.Color(255, 255, 255));
        delete_cus2.setText("Delete");
        delete_cus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_cus2ActionPerformed(evt);
            }
        });
        updatecus2.add(delete_cus2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, -1, 40));

        tabcustomer2.addTab("Update Info", updatecus2);

        GUIDE.add(tabcustomer2, java.awt.BorderLayout.CENTER);

        panalcontent.add(GUIDE, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 810, 570));

        VEHICAL.setBackground(new java.awt.Color(255, 255, 255));
        VEHICAL.setPreferredSize(new java.awt.Dimension(810, 570));

        tabnew3.setBackground(new java.awt.Color(255, 255, 255));
        tabnew3.setPreferredSize(new java.awt.Dimension(810, 570));

        javax.swing.GroupLayout VEHICALLayout = new javax.swing.GroupLayout(VEHICAL);
        VEHICAL.setLayout(VEHICALLayout);
        VEHICALLayout.setHorizontalGroup(
            VEHICALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        VEHICALLayout.setVerticalGroup(
            VEHICALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panalcontent.add(VEHICAL, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 810, 570));

        jDesktopPane1.add(panalcontent);
        panalcontent.setBounds(200, 121, 810, 570);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1032, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pakageBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pakageBActionPerformed
        panelshift(pakageB.getText());

    }//GEN-LAST:event_pakageBActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        panelshift(home.getText());
    }//GEN-LAST:event_homeActionPerformed

    private void paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentActionPerformed

        panelshift(payment.getText());
    }//GEN-LAST:event_paymentActionPerformed

    private void customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerActionPerformed

        panelshift(customer.getText());
    }//GEN-LAST:event_customerActionPerformed

    private void viewBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBookActionPerformed
        panelshift(viewBook.getText());
    }//GEN-LAST:event_viewBookActionPerformed

    private void reportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsActionPerformed
        panelshift(reports.getText());
    }//GEN-LAST:event_reportsActionPerformed

    private void createNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewActionPerformed
        panelshift(createNew.getText());
    }//GEN-LAST:event_createNewActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        name_ou.setText(" ");
        dis_ou.setText(" ");
        pac_ou.setText(" ");
        date_ou.setText(" ");
        duration_ou.setText(" ");
        discount_ou.setText(" ");
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        name_oa.setText(" ");
        dis_oa.setText(" ");
        pac_oa.setText(" ");
        date_oa.setText(" ");
        duration_oa.setText(" ");
        discount_oa.setText(" ");
    }//GEN-LAST:event_jButton22ActionPerformed

    private void packagename_viewtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packagename_viewtActionPerformed

    }//GEN-LAST:event_packagename_viewtActionPerformed

    private void reset_upaybActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_upaybActionPerformed

    }//GEN-LAST:event_reset_upaybActionPerformed

    private void paid_upaytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paid_upaytActionPerformed

    }//GEN-LAST:event_paid_upaytActionPerformed

    private void date_upaytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date_upaytActionPerformed

    }//GEN-LAST:event_date_upaytActionPerformed

    private void balance_upaytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balance_upaytActionPerformed

    }//GEN-LAST:event_balance_upaytActionPerformed

    private void upadete_upayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upadete_upayActionPerformed

    }//GEN-LAST:event_upadete_upayActionPerformed

    private void date_vpaytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_date_vpaytActionPerformed

    }//GEN-LAST:event_date_vpaytActionPerformed

    private void customer_vpaytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customer_vpaytActionPerformed

    }//GEN-LAST:event_customer_vpaytActionPerformed

    private void total_vpaytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_vpaytActionPerformed

    }//GEN-LAST:event_total_vpaytActionPerformed

    private void search_vpaybActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_vpaybActionPerformed

    }//GEN-LAST:event_search_vpaybActionPerformed

    private void balance_paytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_balance_paytActionPerformed

    }//GEN-LAST:event_balance_paytActionPerformed

    private void total_paytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_paytActionPerformed


    }//GEN-LAST:event_total_paytActionPerformed

    private void customerid_paytActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerid_paytActionPerformed

    }//GEN-LAST:event_customerid_paytActionPerformed

    private void adderss_ucustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adderss_ucustActionPerformed

    }//GEN-LAST:event_adderss_ucustActionPerformed

    private void name_ucustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_ucustActionPerformed

    }//GEN-LAST:event_name_ucustActionPerformed

    private void address_vcustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_address_vcustActionPerformed

    }//GEN-LAST:event_address_vcustActionPerformed

    private void name_vcustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_vcustActionPerformed

    }//GEN-LAST:event_name_vcustActionPerformed

    private void addresscustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addresscustActionPerformed

    }//GEN-LAST:event_addresscustActionPerformed

    private void namecustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namecustActionPerformed

    }//GEN-LAST:event_namecustActionPerformed

    private void activity_apactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activity_apactActionPerformed

    }//GEN-LAST:event_activity_apactActionPerformed

    private void destination_apactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destination_apactActionPerformed

    }//GEN-LAST:event_destination_apactActionPerformed

    private void name_apactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_apactActionPerformed


    }//GEN-LAST:event_name_apactActionPerformed

    private void customerid_pactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerid_pactActionPerformed

    }//GEN-LAST:event_customerid_pactActionPerformed

    private void fee_apactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fee_apactActionPerformed

    }//GEN-LAST:event_fee_apactActionPerformed

    private void day_apactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_day_apactActionPerformed

    }//GEN-LAST:event_day_apactActionPerformed

    private void name_upactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_upactActionPerformed

    }//GEN-LAST:event_name_upactActionPerformed

    private void destination_upactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destination_upactActionPerformed

    }//GEN-LAST:event_destination_upactActionPerformed

    private void activity_upactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activity_upactActionPerformed

    }//GEN-LAST:event_activity_upactActionPerformed

    private void fee_upactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fee_upactActionPerformed

    }//GEN-LAST:event_fee_upactActionPerformed

    private void day_upactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_day_upactActionPerformed

    }//GEN-LAST:event_day_upactActionPerformed

    private void update_upacbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_upacbActionPerformed

        int x = JOptionPane.showConfirmDialog(null, "Do you realy want to update");
        if (x == 0) {
            String id = id_upact.getText();
            String name = name_upact.getText();
            String destination = destination_upact.getText();
            String activity = activity_upact.getText();
            String fee = fee_upact.getText();
            String day = day_upact.getText();
            if (name.isEmpty()) {
                name_upact.setBackground(new Color(255, 150, 150));
            } else {
                name_upact.setBackground(new Color(255, 255, 255));
            }
            if (destination.isEmpty()) {
                destination_upact.setBackground(new Color(255, 150, 150));
            } else {
                destination_upact.setBackground(new Color(255, 255, 255));
            }
            if (activity.isEmpty()) {
                activity_upact.setBackground(new Color(255, 150, 150));
            } else {
                activity_upact.setBackground(new Color(255, 255, 255));
            }
            if (fee.isEmpty()) {
                fee_upact.setBackground(new Color(255, 150, 150));
            } else {
                fee_upact.setBackground(new Color(255, 255, 255));
            }
            if (day.isEmpty()) {
                day_upact.setBackground(new Color(255, 150, 150));
            } else {
                day_upact.setBackground(new Color(255, 255, 255));
            }

            if (name.isEmpty() || destination.isEmpty() || day.isEmpty() || fee.isEmpty() || activity.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields to continue.", "Form Incomplete!", 0);
                day_upact.setBackground(new Color(255, 255, 255));
                fee_upact.setBackground(new Color(255, 255, 255));
                activity_upact.setBackground(new Color(255, 255, 255));
                destination_upact.setBackground(new Color(255, 255, 255));
                name_upact.setBackground(new Color(255, 255, 255));
            } else {

                String q = "update trans_package set name='" + name + "', destination='" + destination + "',activity='" + activity + "',fee='" + fee + "',days='" + day + "' where idpackage='" + id + "' ";

                try {
                    pst = conn.prepareStatement(q);
                    pst.execute();
                    tableup_pac();
                    tableup_pac1();
                    tablese_pac();
                    JOptionPane.showMessageDialog(null, "saved");

                    name_upact.setText(" ");
                    destination_upact.setText(" ");
                    activity_upact.setText(" ");
                    fee_upact.setText(" ");
                    day_upact.setText(" ");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }
        }

    }//GEN-LAST:event_update_upacbActionPerformed

    private void add_apacdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_apacdActionPerformed

        String name = name_apact.getText();
        String destination = destination_apact.getText();
        String activity = activity_apact.getText();
        String fee = fee_apact.getText();
        String day = day_apact.getText();

        if (name.isEmpty()) {
            name_apact.setBackground(new Color(255, 150, 150));
        } else {
            name_apact.setBackground(new Color(255, 255, 255));
        }
        if (destination.isEmpty()) {
            destination_apact.setBackground(new Color(255, 150, 150));
        } else {
            destination_apact.setBackground(new Color(255, 255, 255));
        }
        if (activity.isEmpty()) {
            activity_apact.setBackground(new Color(255, 150, 150));
        } else {
            activity_apact.setBackground(new Color(255, 255, 255));
        }
        if (fee.isEmpty()) {
            fee_apact.setBackground(new Color(255, 150, 150));
        } else {
            fee_apact.setBackground(new Color(255, 255, 255));
        }
        if (day.isEmpty()) {
            day_apact.setBackground(new Color(255, 150, 150));
        } else {
            day_apact.setBackground(new Color(255, 255, 255));
        }

        if (name.isEmpty() || destination.isEmpty() || day.isEmpty() || fee.isEmpty() || activity.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields to continue.", "Form Incomplete!", 0);
            day_apact.setBackground(new Color(255, 255, 255));
            fee_apact.setBackground(new Color(255, 255, 255));
            activity_apact.setBackground(new Color(255, 255, 255));
            destination_apact.setBackground(new Color(255, 255, 255));
            name_apact.setBackground(new Color(255, 255, 255));
        } else {
            try {

                String q = "INSERT INTO trans_package (name,destination,activity,fee,days) values('" + name + "', '" + destination + "','" + activity + "','" + fee + "','" + day + "' )";
                pst = conn.prepareStatement(q);
                pst.execute();
                tablese_pac();
                tableup_pac();
                tableup_pac1();
                JOptionPane.showMessageDialog(null, "saved");

                name_apact.setText(" ");
                destination_apact.setText(" ");
                activity_apact.setText(" ");
                fee_apact.setText(" ");
                day_apact.setText(" ");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_add_apacdActionPerformed

    private void name_pactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_pactActionPerformed

    }//GEN-LAST:event_name_pactActionPerformed

    private void book_pacbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_book_pacbActionPerformed

        String nameb = name_pact.getText();
        String nob = no_pact.getText();
        String statusb = satus_pact.getText();
        String cusidb = customerid_pact.getText();

        if (nameb.isEmpty()) {
            name_pact.setBackground(new Color(255, 150, 150));
        } else {
            name_pact.setBackground(new Color(255, 255, 255));
        }

        if (nob.isEmpty()) {
            no_pact.setBackground(new Color(255, 150, 150));
        } else {
            no_pact.setBackground(new Color(255, 255, 255));
        }

        if (statusb.isEmpty()) {
            satus_pact.setBackground(new Color(255, 150, 150));
        } else {
            satus_pact.setBackground(new Color(255, 255, 255));
        }

        if (cusidb.isEmpty()) {
            customerid_pact.setBackground(new Color(255, 150, 150));
        } else {
            customerid_pact.setBackground(new Color(255, 255, 255));
        }

        if (nameb.isEmpty() || statusb.isEmpty() || cusidb.isEmpty() || nob.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields to continue.", "Form Incomplete!", 0);
            name_pact.setBackground(new Color(255, 255, 255));
            customerid_pact.setBackground(new Color(255, 255, 255));
            satus_pact.setBackground(new Color(255, 255, 255));
            no_pact.setBackground(new Color(255, 255, 255));

        } else {

            String dateb = date_pacd.getDate().toString();
            String q = "INSERT INTO trans_book (pac_name,no_of_pass,status,date,cusid) values('" + nameb + "', '" + nob + "','" + statusb + "','" + dateb + "','" + cusidb + "' )";
            try {

                pst = conn.prepareStatement(q);
                pst.execute();
                tableview();
                tableviewupdate();
                tableviewsearch();
                JOptionPane.showMessageDialog(null, "saved");
                name_pact.setText(" ");
                no_pact.setText(" ");
                satus_pact.setText(" ");
                date_pacd.setCalendar(null);
                customerid_pact.setText(" ");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_book_pacbActionPerformed

    private void addcusbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addcusbActionPerformed

        String namecus = namecust.getText();
        String address = addresscust.getText();
        String nic = niccust1.getText();
        String telephone = telephonecust.getText();
        String email = emailt.getText();

        if (namecus.isEmpty()) {
            namecust.setBackground(new Color(255, 150, 150));
        } else {
            namecust.setBackground(new Color(255, 255, 255));
        }

        if (address.isEmpty()) {
            addresscust.setBackground(new Color(255, 150, 150));
        } else {
            addresscust.setBackground(new Color(255, 255, 255));
        }

        if (nic.isEmpty()) {
            niccust1.setBackground(new Color(255, 150, 150));
        } else {
            niccust1.setBackground(new Color(255, 255, 255));
        }
        if (telephone.isEmpty()) {
            telephonecust.setBackground(new Color(255, 150, 150));
        } else {
            telephonecust.setBackground(new Color(255, 255, 255));

        }
        if (email.isEmpty()) {
            emailt.setBackground(new Color(255, 150, 150));
        } else {
            emailt.setBackground(new Color(255, 255, 255));

        }

        if (namecus.isEmpty() || address.isEmpty() || nic.isEmpty() || telephone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(CUSTOMER, "Please fill all the fields to continue.", "Form Incomplete!", 0);
            namecust.setBackground(new Color(255, 255, 255));
            emailt.setBackground(new Color(255, 255, 255));
            telephonecust.setBackground(new Color(255, 255, 255));
            niccust1.setBackground(new Color(255, 255, 255));
            addresscust.setBackground(new Color(255, 255, 255));
            namecust.setBackground(new Color(255, 255, 255));

        } else {

            String m = emailt.getText();
            String emailreg = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            Boolean b = m.matches(emailreg);

            if (b == false) {

                JOptionPane.showMessageDialog(null, "Invalid email", "invaild input", JOptionPane.ERROR_MESSAGE);
                emailt.setText("");
            } else {

                String p = telephonecust.getText();
                String tel = "^\\+?[0-9.  ()-]{10}$";
                Boolean q = p.matches(tel);
                if (q == false) {
                    JOptionPane.showMessageDialog(null, "Invalid telephone", "invaild input", JOptionPane.ERROR_MESSAGE);
                    telephonecust.setText("");

                } else {
                    String ni = niccust1.getText();
                    String nicval = "^[0-9]{9}[vVxX]$";
                    Boolean w = ni.matches(nicval);
                    if (w == false) {
                        JOptionPane.showMessageDialog(null, "Invalid NIC", "invaild input", JOptionPane.ERROR_MESSAGE);
                        niccust1.setText("");

                    } else {
                        int x = JOptionPane.showConfirmDialog(null, "Do you realy want to ADD");
                        if (x == 0) {
                            try {
                                String asd = "INSERT INTO trans_customer (name,address,nic,telephone,email) values('" + namecus + "', '" + address + "','" + nic + "','" + telephone + "','" + email + "' )";

                                pst = conn.prepareStatement(asd);
                                pst.execute();
                                tableloadcus();//customer
                                tableloadcus_up();
                                tableloadcus_se();
                                JOptionPane.showMessageDialog(null, "saved");

                                namecust.setText(" ");
                                addresscust.setText(" ");
                                emailt.setText(" ");
                                telephonecust.setText(" ");
                                niccust1.setText(" ");

                            } catch (Exception e) {

                                JOptionPane.showMessageDialog(null, e);
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_addcusbActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        name_apact.setText(" ");
        destination_apact.setText(" ");
        activity_apact.setText(" ");
        fee_apact.setText(" ");
        day_apact.setText(" ");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void delete_upabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_upabActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you realy want to DELETE");
        if (x == 0) {
            String id = id_upact.getText();
            String sql = "Delete from trans_package where idpackage='" + id + "' ";

            try {
                pst = conn.prepareStatement(sql);
                pst.execute();
                tableup_pac();
                JOptionPane.showMessageDialog(null, " sucessfull");
                name_upact.setText(" ");
                destination_upact.setText(" ");
                activity_upact.setText(" ");
                fee_upact.setText(" ");
                day_upact.setText(" ");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }//GEN-LAST:event_delete_upabActionPerformed
    }
    private void jTable11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable11MouseClicked

        int r = jTable11.getSelectedRow();

        String id = jTable11.getValueAt(r, 0).toString();
        String name = jTable11.getValueAt(r, 1).toString();
        String des = jTable11.getValueAt(r, 2).toString();
        String ac = jTable11.getValueAt(r, 3).toString();
        String fee = jTable11.getValueAt(r, 4).toString();
        String day = jTable11.getValueAt(r, 5).toString();

        id_upact.setText(id);
        name_upact.setText(name);
        destination_upact.setText(des);
        activity_upact.setText(ac);
        fee_upact.setText(fee);
        day_upact.setText(day);
    }//GEN-LAST:event_jTable11MouseClicked

    private void resetcusbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetcusbActionPerformed
        namecust.setText(" ");
        addresscust.setText(" ");
        emailt.setText(" ");
        telephonecust.setText(" ");
        niccust1.setText(" ");
    }//GEN-LAST:event_resetcusbActionPerformed

    private void add_paybActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_paybActionPerformed

    }//GEN-LAST:event_add_paybActionPerformed

    private void offers1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_offers1ActionPerformed
        panelshift(offers1.getText());
    }//GEN-LAST:event_offers1ActionPerformed

    private void driverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_driverActionPerformed
        panelshift(driver.getText());
    }//GEN-LAST:event_driverActionPerformed

    private void guideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guideActionPerformed
        panelshift(guide.getText());
    }//GEN-LAST:event_guideActionPerformed

    private void id_upact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_upact1ActionPerformed

    }//GEN-LAST:event_id_upact1ActionPerformed

    private void name_upact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_upact1ActionPerformed

    }//GEN-LAST:event_name_upact1ActionPerformed

    private void destination_upact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destination_upact1ActionPerformed

    }//GEN-LAST:event_destination_upact1ActionPerformed

    private void activity_upact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activity_upact1ActionPerformed

    }//GEN-LAST:event_activity_upact1ActionPerformed

    private void day_upact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_day_upact1ActionPerformed

    }//GEN-LAST:event_day_upact1ActionPerformed

    private void fee_upact1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fee_upact1ActionPerformed

    }//GEN-LAST:event_fee_upact1ActionPerformed

    private void pac_viewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pac_viewMouseClicked
        int r = pac_view.getSelectedRow();

        String id = pac_view.getValueAt(r, 0).toString();
        String name = pac_view.getValueAt(r, 1).toString();
        String des = pac_view.getValueAt(r, 2).toString();
        String ac = pac_view.getValueAt(r, 3).toString();
        String fee = pac_view.getValueAt(r, 4).toString();
        String day = pac_view.getValueAt(r, 5).toString();

        id_upact1.setText(id);
        name_upact1.setText(name);
        destination_upact1.setText(des);
        activity_upact1.setText(ac);
        fee_upact1.setText(fee);
        day_upact1.setText(day);
    }//GEN-LAST:event_pac_viewMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        name_pact.setText(" ");
        no_pact.setText(" ");
        satus_pact.setText(" ");
        date_pacd.setCalendar(null);
        customerid_pact.setText(" ");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void val2pacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_val2pacActionPerformed

    }//GEN-LAST:event_val2pacActionPerformed

    private void val1pacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_val1pacActionPerformed


    }//GEN-LAST:event_val1pacActionPerformed

    private void sea_pacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sea_pacMouseClicked

    }//GEN-LAST:event_sea_pacMouseClicked

    private void val1pacKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_val1pacKeyReleased
        tablese_pac();
        String val1 = val1pac.getText();
        String tmp = jComboBox1.getSelectedItem().toString();

        if (tmp == "Select One For Advance Search") {
            try {
                val2pac.setEditable(false);
                String sql = "Select * from trans_package where name LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                sea_pac.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

            }
        } else if (tmp == "ID") {
            try {
                val2pac.setEditable(true);
                String sql = "Select * from trans_package where idpackage LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                sea_pac.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        } else if (tmp == "Destination") {
            try {
                val2pac.setEditable(false);
                String sql = "Select * from trans_package where destination LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                sea_pac.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        } else if (tmp == "Activity") {
            try {
                val2pac.setEditable(false);
                String sql = "Select * from trans_package where activity LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                sea_pac.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        } else if (tmp == "Fee") {
            try {
                val2pac.setEditable(true);
                String sql = "Select * from trans_package where fee LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                sea_pac.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        } else if (tmp == "Days") {
            try {
                val2pac.setEditable(true);
                String sql = "Select * from trans_package where days LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                sea_pac.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_val1pacKeyReleased

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void val2pacKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_val2pacKeyReleased
        tablese_pac();
        String val1 = val1pac.getText();
        String val2 = val2pac.getText();
        String tmp = jComboBox1.getSelectedItem().toString();

        if (tmp == "ID") {
            try {

                String sql = "Select * from trans_package where idpackage between '" + val1 + "' and '" + val2 + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                sea_pac.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

            }
        } else if (tmp == "Fee") {
            try {

                String sql = "Select * from trans_package where fee between '" + val1 + "' and '" + val2 + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                sea_pac.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        } else if (tmp == "Days") {
            try {

                String sql = "Select * from trans_package where days between '" + val1 + "' and '" + val2 + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                sea_pac.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_val2pacKeyReleased

    private void fee_apactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fee_apactKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_fee_apactKeyTyped

    private void day_apactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_day_apactKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_day_apactKeyTyped

    private void id_upactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_id_upactActionPerformed

    }//GEN-LAST:event_id_upactActionPerformed

    private void jTable8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseClicked
        int r = jTable8.getSelectedRow();

        String id = jTable8.getValueAt(r, 0).toString();
        String name = jTable8.getValueAt(r, 1).toString();
        String num = jTable8.getValueAt(r, 2).toString();
        String cusid = jTable8.getValueAt(r, 5).toString();
        String status = jTable8.getValueAt(r, 3).toString();
        String date = jTable8.getValueAt(r, 4).toString();

        id_view.setText(id);
        packagename_viewt.setText(name);
        customerid_viewt.setText(cusid);
        status_view.setText(status);
        no_viewt.setText(num);
        date_viewt.setText(date);
    }//GEN-LAST:event_jTable8MouseClicked

    private void packagename_viewt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packagename_viewt1ActionPerformed

    }//GEN-LAST:event_packagename_viewt1ActionPerformed

    private void jTable18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable18MouseClicked
        int r = jTable18.getSelectedRow();

        String id = jTable18.getValueAt(r, 0).toString();
        String name = jTable18.getValueAt(r, 1).toString();
        String num = jTable18.getValueAt(r, 2).toString();
        String cusid = jTable18.getValueAt(r, 5).toString();
        String status = jTable18.getValueAt(r, 3).toString();
        String date = jTable18.getValueAt(r, 4).toString();

        id_view1.setText(id);
        packagename_viewt1.setText(name);
        customerid_viewt1.setText(cusid);
        status_view1.setText(status);
        no_viewt1.setText(num);
        date_pacd1.setText(date);
    }//GEN-LAST:event_jTable18MouseClicked

    private void search_viewb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_viewb1ActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you realy want to update");
        if (x == 0) {
            String name = packagename_viewt1.getText();
            String cusid = customerid_viewt1.getText();
            String status = status_view1.getText();
            String no = no_viewt1.getText();
            String date = date_pacd1.getText();
            String id = id_view1.getText();

            String q = "update trans_book set pac_name='" + name + "', No_of_pass='" + no + "',date='" + date + "',status='" + status + "',No_of_pass='" + no + "', cusid='" + cusid + "' where idbook='" + id + "' ";

            try {
                pst = conn.prepareStatement(q);
                pst.execute();
                tableview();
                tableviewupdate();
                tableviewsearch();
                JOptionPane.showMessageDialog(null, "saved");
                id_view1.setText("");
                packagename_viewt1.setText("");
                customerid_viewt1.setText("");
                status_view1.setText("");
                no_viewt1.setText("");
                date_pacd1.setText("");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }//GEN-LAST:event_search_viewb1ActionPerformed
    }
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        id_view1.setText("");
        packagename_viewt1.setText("");
        customerid_viewt1.setText("");
        status_view1.setText("");
        no_viewt1.setText("");
        date_pacd1.setText("");

    }//GEN-LAST:event_jButton4ActionPerformed

    private void customerid_viewt1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customerid_viewt1KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_customerid_viewt1KeyTyped

    private void no_viewt1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_viewt1KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_no_viewt1KeyTyped

    private void id_view1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_id_view1KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_id_view1KeyTyped

    private void packagename_viewt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_packagename_viewt1KeyReleased

    }//GEN-LAST:event_packagename_viewt1KeyReleased

    private void val2seaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_val2seaActionPerformed

    }//GEN-LAST:event_val2seaActionPerformed

    private void val1seaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_val1seaActionPerformed

    }//GEN-LAST:event_val1seaActionPerformed

    private void jTable19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable19MouseClicked

    }//GEN-LAST:event_jTable19MouseClicked

    private void val1seaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_val1seaKeyReleased
        tableviewsearch();
        String val1 = val1sea.getText();
        String tmp = jComboBox2.getSelectedItem().toString();

        if (tmp == "Select One For Advance Search") {
            try {
                val2sea.setEditable(false);
                String sql = "Select * from trans_book where pac_name LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable19.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

            }
        } else if (tmp == "ID") {
            try {
                val2sea.setEditable(true);

                String sql = "Select * from trans_book where idbook LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable19.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        } else if (tmp == "Customer ID") {
            try {
                val2sea.setEditable(true);
                String sql = "Select * from trans_book where cusid LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable19.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        } else if (tmp == "No. of passengers") {
            try {
                val2sea.setEditable(true);
                String sql = "Select * from trans_book where No_of_pass LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable19.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        } else if (tmp == "Date") {
            try {
                val2sea.setEditable(false);
                String sql = "Select * from trans_book where date LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable19.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_val1seaKeyReleased

    private void val2seaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_val2seaKeyReleased
        tableviewsearch();
        String val1 = val1sea.getText();
        String val2 = val2sea.getText();
        String tmp = jComboBox2.getSelectedItem().toString();

        if (tmp == "ID") {
            try {

                String sql = "Select * from trans_book where idbook between '" + val1 + "' and '" + val2 + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable19.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        } else if (tmp == "Customer ID") {
            try {

                String sql = "Select * from trans_book where cusid between '" + val1 + "' and '" + val2 + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable19.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        } else if (tmp == "No. of passengers") {
            try {

                String sql = "Select * from trans_book where No_of_pass between '" + val1 + "' and '" + val2 + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable19.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_val2seaKeyReleased

    private void val2seaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_val2seaKeyPressed
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_val2seaKeyPressed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        int r = jTable4.getSelectedRow();

        String id = jTable4.getValueAt(r, 0).toString();
        String name = jTable4.getValueAt(r, 1).toString();
        String nic = jTable4.getValueAt(r, 2).toString();
        String add = jTable4.getValueAt(r, 3).toString();
        String tel = jTable4.getValueAt(r, 4).toString();
        String ema = jTable4.getValueAt(r, 5).toString();
        //String bookid = jTable4.getValueAt(r, 6).toString();

        id_vcust.setText(id);
        name_vcust.setText(name);
        nic_vcut.setText(nic);
        address_vcust.setText(add);
        telephone_vcust.setText(tel);
        // bookingid_vcust.setText(bookid);
        email_vcust1.setText(ema);
    }//GEN-LAST:event_jTable4MouseClicked

    private void place1_anewt16place1_anewtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_place1_anewt16place1_anewtActionPerformed

    }//GEN-LAST:event_place1_anewt16place1_anewtActionPerformed

    private void add_anewb16add_anewbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_anewb16add_anewbActionPerformed
        String cid = customerid_anewt16.getText();
        String plase = place1_anewt16.getText();
        String noof = days_anewt17.getText();
        String vehicle = vechical_anewt16.getText();
        String guide = guide_anewt16.getText();
        String driver = driver_anewt16.getText();
        String fre = fee_anewt16.getText();
        String dis = dis_anewt17.getText();
        String pass = pass_anewt16.getText();

        if (cid.isEmpty()) {
            customerid_anewt16.setBackground(new Color(255, 150, 150));
        } else {
            customerid_anewt16.setBackground(new Color(255, 255, 255));
        }

        if (plase.isEmpty()) {
            place1_anewt16.setBackground(new Color(255, 150, 150));
        } else {
            place1_anewt16.setBackground(new Color(255, 255, 255));
        }

        if (noof.isEmpty()) {
            pass_anewt16.setBackground(new Color(255, 150, 150));
        } else {
            pass_anewt16.setBackground(new Color(255, 255, 255));
        }

        if (guide.isEmpty()) {
            guide_anewt16.setBackground(new Color(255, 150, 150));
        } else {
            guide_anewt16.setBackground(new Color(255, 255, 255));
        }

        if (driver.isEmpty()) {
            driver_anewt16.setBackground(new Color(255, 150, 150));
        } else {
            driver_anewt16.setBackground(new Color(255, 255, 255));
        }

        if (fre.isEmpty()) {
            fee_anewt16.setBackground(new Color(255, 150, 150));
        } else {
            fee_anewt16.setBackground(new Color(255, 255, 255));
        }

        if (vehicle.isEmpty()) {
            vechical_anewt16.setBackground(new Color(255, 150, 150));
        } else {
            vechical_anewt16.setBackground(new Color(255, 255, 255));
        }

        if (noof.isEmpty()) {
            days_anewt17.setBackground(new Color(255, 150, 150));
        } else {
            days_anewt17.setBackground(new Color(255, 255, 255));
        }

        if (dis.isEmpty()) {
            dis_anewt17.setBackground(new Color(255, 150, 150));
        } else {
            dis_anewt17.setBackground(new Color(255, 255, 255));
        }

        if (pass.isEmpty()) {
            pass_anewt16.setBackground(new Color(255, 150, 150));
        } else {
            pass_anewt16.setBackground(new Color(255, 255, 255));
        }

        if (fre.isEmpty() || driver.isEmpty() || guide.isEmpty() || noof.isEmpty() || plase.isEmpty() || cid.isEmpty() || vehicle.isEmpty() || pass.isEmpty() || dis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields to continue.", "Form Incomplete!", 0);
            vechical_anewt16.setBackground(new Color(255, 255, 255));
            fee_anewt16.setBackground(new Color(255, 255, 255));
            driver_anewt16.setBackground(new Color(255, 255, 255));

            pass_anewt16.setBackground(new Color(255, 255, 255));
            place1_anewt16.setBackground(new Color(255, 255, 255));
            customerid_anewt16.setBackground(new Color(255, 255, 255));
            dis_anewt17.setBackground(new Color(255, 255, 255));
            days_anewt17.setBackground(new Color(255, 255, 255));
            guide_anewt16.setBackground(new Color(255, 255, 255));

        } else {
            String date = date_anewt16.getDate().toString();
            String q = "INSERT INTO trans_customize (customized_description,fee_custom,place1,days_custom,date_custom,vehicleno_custom,driver_custom,guide_custom,place2,place3) values( '" + dis + "','" + fre + "','" + plase + "','" + noof + "','" + date + "','" + vehicle + "','" + driver + "','" + guide + "','" + pass + "','" + cid + "' )";
            try {
                pst = conn.prepareStatement(q);
                pst.execute();
                tablenewu();
                tablenewv();
                JOptionPane.showMessageDialog(null, "saved");

                customerid_anewt16.setText(" ");
                place1_anewt16.setText(" ");
                pass_anewt16.setText(" ");
                guide_anewt16.setText(" ");
                vechical_anewt16.setText(" ");
                guide_anewt16.setText(" ");
                driver_anewt16.setText(" ");
                fee_anewt16.setText(" ");
                date_anewt16.setCalendar(null);
                dis_anewt17.setText(" ");
                days_anewt17.setText(" ");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_add_anewb16add_anewbActionPerformed

    private void plase_newvt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plase_newvt1ActionPerformed

    }//GEN-LAST:event_plase_newvt1ActionPerformed

    private void plase_newvtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plase_newvtActionPerformed

    }//GEN-LAST:event_plase_newvtActionPerformed

    private void customerid_pactKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customerid_pactKeyReleased

    }//GEN-LAST:event_customerid_pactKeyReleased

    private void customerid_pactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customerid_pactKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_customerid_pactKeyTyped

    private void fee_upactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fee_upactKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_fee_upactKeyTyped

    private void day_upactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_day_upactKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_day_upactKeyTyped

    private void telephonecustKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telephonecustKeyReleased

    }//GEN-LAST:event_telephonecustKeyReleased

    private void telephonecustKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telephonecustKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_telephonecustKeyTyped

    private void demoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demoActionPerformed
        namecust.setText("Chethana");
        addresscust.setText("col07, Srilanka ");
        emailt.setText("chethana@gmail.com");
        telephonecust.setText("0123456789");
        niccust1.setText("952341561V");
    }//GEN-LAST:event_demoActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        int r = jTable5.getSelectedRow();

        String id = jTable5.getValueAt(r, 0).toString();
        String name = jTable5.getValueAt(r, 1).toString();
        String add = jTable5.getValueAt(r, 2).toString();
        String nic = jTable5.getValueAt(r, 3).toString();
        String tel = jTable5.getValueAt(r, 4).toString();
        String ema = jTable5.getValueAt(r, 5).toString();

        id_ucust.setText(id);
        name_ucust.setText(name);
        nic_ucust.setText(nic);
        adderss_ucust.setText(add);
        telephone_ucust.setText(tel);
        email_ucust.setText(ema);
    }//GEN-LAST:event_jTable5MouseClicked

    private void update_ucustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_ucustActionPerformed

        String id1 = id_ucust.getText();
        String name1 = name_ucust.getText();
        String nic = nic_ucust.getText();
        String add = adderss_ucust.getText();
        String tel = telephone_ucust.getText();
        String email = email_ucust.getText();

        if (name1.isEmpty()) {
            name_ucust.setBackground(new Color(255, 150, 150));
        } else {
            name_ucust.setBackground(new Color(255, 255, 255));
        }

        if (add.isEmpty()) {
            adderss_ucust.setBackground(new Color(255, 150, 150));
        } else {
            adderss_ucust.setBackground(new Color(255, 255, 255));
        }

        if (nic.isEmpty()) {
            nic_ucust.setBackground(new Color(255, 150, 150));
        } else {
            nic_ucust.setBackground(new Color(255, 255, 255));
        }
        if (tel.isEmpty()) {
            telephone_ucust.setBackground(new Color(255, 150, 150));
        } else {
            telephone_ucust.setBackground(new Color(255, 255, 255));

        }
        if (email.isEmpty()) {
            email_ucust.setBackground(new Color(255, 150, 150));
        } else {
            email_ucust.setBackground(new Color(255, 255, 255));

        }

        if (name1.isEmpty() || add.isEmpty() || nic.isEmpty() || tel.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(CUSTOMER, "Please fill all the fields to continue.", "Form Incomplete!", 0);
            name_ucust.setBackground(new Color(255, 255, 255));
            email_ucust.setBackground(new Color(255, 255, 255));
            telephone_ucust.setBackground(new Color(255, 255, 255));
            nic_ucust.setBackground(new Color(255, 255, 255));
            adderss_ucust.setBackground(new Color(255, 255, 255));

        } else {

            String m = email_ucust.getText();
            String emailreg = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            Boolean b = m.matches(emailreg);

            if (b == false) {

                JOptionPane.showMessageDialog(null, "Invalid email", "invaild input", JOptionPane.ERROR_MESSAGE);
                email_ucust.setText("");
            } else {

                String p = telephone_ucust.getText();
                String tele = "^\\+?[0-9.  ()-]{10}$";
                Boolean q = p.matches(tele);
                if (q == false) {
                    JOptionPane.showMessageDialog(null, "Invalid telephone", "invaild input", JOptionPane.ERROR_MESSAGE);
                    telephone_ucust.setText("");

                } else {
                    String ni = nic_ucust.getText();
                    String nicval = "^[0-9]{9}[vVxX]$";
                    Boolean w = ni.matches(nicval);
                    if (w == false) {
                        JOptionPane.showMessageDialog(null, "Invalid NIC", "invaild input", JOptionPane.ERROR_MESSAGE);
                        nic_ucust.setText("");

                    } else {
                        int x = JOptionPane.showConfirmDialog(null, "Do you realy want to update");
                        if (x == 0) {

                            String qw = "update trans_customer set name='" + name1 + "', address='" + add + "',nic='" + nic + "',telephone='" + tel + "',email='" + email + "' where idcustomer='" + id1 + "' ";

                            try {
                                pst = conn.prepareStatement(qw);
                                pst.execute();
                                tableloadcus();//customer
                                tableloadcus_up();
                                tableloadcus_se();
                                JOptionPane.showMessageDialog(null, "saved");

                                id_ucust.setText(" ");
                                name_ucust.setText(" ");
                                nic_ucust.setText(" ");
                                adderss_ucust.setText(" ");
                                telephone_ucust.setText(" ");
                                email_ucust.setText(" ");

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, e);
                            }

                        }
                    }
                }
            }
        }

    }//GEN-LAST:event_update_ucustActionPerformed

    private void rest_ucustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rest_ucustActionPerformed
        id_ucust.setText(" ");
        name_ucust.setText(" ");
        nic_ucust.setText(" ");
        adderss_ucust.setText(" ");
        telephone_ucust.setText(" ");
        email_ucust.setText(" ");
    }//GEN-LAST:event_rest_ucustActionPerformed

    private void delete_cusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_cusActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you realy want to DELETE");
        if (x == 0) {
            String id = id_ucust.getText();
            String sql = "Delete from trans_customer where idcustomer='" + id + "' ";
            try {
                pst = conn.prepareStatement(sql);
                pst.execute();
                tableloadcus();//customer
                tableloadcus_up();
                tableloadcus_se();

                id_ucust.setText(" ");
                name_ucust.setText(" ");
                nic_ucust.setText(" ");
                adderss_ucust.setText(" ");
                telephone_ucust.setText(" ");
                email_ucust.setText(" ");
                JOptionPane.showMessageDialog(null, "saved");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_delete_cusActionPerformed

    private void jTable22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable22MouseClicked

    }//GEN-LAST:event_jTable22MouseClicked

    private void va2cuseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_va2cuseActionPerformed

    }//GEN-LAST:event_va2cuseActionPerformed

    private void va1cuseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_va1cuseActionPerformed

    }//GEN-LAST:event_va1cuseActionPerformed

    private void va1cuseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_va1cuseKeyReleased
        tableloadcus_se();
        String val1 = va1cuse.getText();
        String tmp = jComboBox3.getSelectedItem().toString();

        if (tmp == "Select One For Advance Search") {
            try {
                va2cuse.setEditable(false);
                String sql = "Select * from trans_customer where name LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable22.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

            }
        } else if (tmp == "ID") {
            try {
                va2cuse.setEditable(true);
                String sql = "Select * from trans_customer where idcustomer LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable22.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

            }

        } else if (tmp == "Address") {
            try {
                va2cuse.setEditable(false);

                String sql = "Select * from trans_customer where address LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable22.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

            }
        } else if (tmp == "NIC") {
            try {
                va2cuse.setEditable(false);
                String sql = "Select * from trans_customer where nic LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable22.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

            }
        } else if (tmp == "Telephone") {
            try {
                va2cuse.setEditable(false);
                String sql = "Select * from trans_customer where tel LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable22.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

            }
        } else if (tmp == "email") {
            try {
                va2cuse.setEditable(false);
                String sql = "Select * from trans_customer where email LIKE '%" + val1 + "%'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable22.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_va1cuseKeyReleased

    private void va2cuseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_va2cuseKeyReleased
        tableloadcus_se();
        String val1 = va1cuse.getText();
        String val2 = va2cuse.getText();
        String tmp = jComboBox3.getSelectedItem().toString();

        if (tmp == "ID") {
            try {
                va2cuse.setEditable(true);
                String sql = "Select * from trans_customer where idcustomer between '" + val1 + "' and '" + val2 + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable22.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

            }
        }
    }//GEN-LAST:event_va2cuseKeyReleased

    private void va2cuseKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_va2cuseKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_va2cuseKeyTyped

    private void homeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseEntered
        home.setBackground(new Color(0, 204, 51));
    }//GEN-LAST:event_homeMouseEntered

    private void customerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerMouseEntered
        customer.setBackground(new Color(0, 204, 51));
    }//GEN-LAST:event_customerMouseEntered

    private void pakageBMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pakageBMouseEntered
        pakageB.setBackground(new Color(0, 204, 51));
    }//GEN-LAST:event_pakageBMouseEntered

    private void viewBookMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewBookMouseEntered
        viewBook.setBackground(new Color(0, 204, 51));
    }//GEN-LAST:event_viewBookMouseEntered

    private void createNewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createNewMouseEntered
        createNew.setBackground(new Color(0, 204, 51));
    }//GEN-LAST:event_createNewMouseEntered

    private void offers1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_offers1MouseEntered
        offers1.setBackground(new Color(0, 204, 51));
    }//GEN-LAST:event_offers1MouseEntered

    private void guideMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guideMouseEntered
        guide.setBackground(new Color(0, 204, 51));
    }//GEN-LAST:event_guideMouseEntered

    private void paymentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentMouseEntered
        payment.setBackground(new Color(0, 204, 51));
    }//GEN-LAST:event_paymentMouseEntered

    private void driverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driverMouseEntered
        driver.setBackground(new Color(0, 204, 51));
    }//GEN-LAST:event_driverMouseEntered

    private void reportsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsMouseEntered
        reports.setBackground(new Color(0, 204, 51));
    }//GEN-LAST:event_reportsMouseEntered

    private void homeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseExited
        home.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_homeMouseExited

    private void customerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerMouseExited
        customer.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_customerMouseExited

    private void pakageBMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pakageBMouseExited
        pakageB.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_pakageBMouseExited

    private void viewBookMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewBookMouseExited
        viewBook.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_viewBookMouseExited

    private void createNewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createNewMouseExited
        createNew.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_createNewMouseExited

    private void offers1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_offers1MouseExited
        offers1.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_offers1MouseExited

    private void guideMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guideMouseExited
        guide.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_guideMouseExited

    private void paymentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paymentMouseExited
        payment.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_paymentMouseExited

    private void driverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driverMouseExited
        driver.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_driverMouseExited

    private void reportsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportsMouseExited
        reports.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_reportsMouseExited

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked

    }//GEN-LAST:event_homeMouseClicked

    private void namecust1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namecust1ActionPerformed

    }//GEN-LAST:event_namecust1ActionPerformed

    private void telephonecust1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telephonecust1KeyReleased

    }//GEN-LAST:event_telephonecust1KeyReleased

    private void telephonecust1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telephonecust1KeyTyped

    }//GEN-LAST:event_telephonecust1KeyTyped

    private void addresscust1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addresscust1ActionPerformed

    }//GEN-LAST:event_addresscust1ActionPerformed

    private void addcusb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addcusb1ActionPerformed
        String namecus = namecust1.getText();
        String address = addresscust1.getText();
        String nic = niccust2.getText();
        String telephone = telephonecust1.getText();
        String licence = licence_da.getText();
        String vehicle = vehicle_da.getText();

        if (namecus.isEmpty()) {
            namecust1.setBackground(new Color(255, 150, 150));
        } else {
            namecust1.setBackground(new Color(255, 255, 255));
        }

        if (address.isEmpty()) {
            addresscust1.setBackground(new Color(255, 150, 150));
        } else {
            addresscust1.setBackground(new Color(255, 255, 255));
        }

        if (nic.isEmpty()) {
            niccust2.setBackground(new Color(255, 150, 150));
        } else {
            niccust2.setBackground(new Color(255, 255, 255));
        }
        if (telephone.isEmpty()) {
            telephonecust1.setBackground(new Color(255, 150, 150));
        } else {
            telephonecust1.setBackground(new Color(255, 255, 255));

        }
        if (licence.isEmpty()) {
            licence_da.setBackground(new Color(255, 150, 150));
        } else {
            licence_da.setBackground(new Color(255, 255, 255));

        }
        if (vehicle.isEmpty()) {
            vehicle_da.setBackground(new Color(255, 150, 150));
        } else {
            vehicle_da.setBackground(new Color(255, 255, 255));

        }

        if (namecus.isEmpty() || address.isEmpty() || nic.isEmpty() || telephone.isEmpty() || licence.isEmpty() || vehicle.isEmpty()) {
            JOptionPane.showMessageDialog(CUSTOMER, "Please fill all the fields to continue.", "Form Incomplete!", 0);
            namecust1.setBackground(new Color(255, 255, 255));
            licence_da.setBackground(new Color(255, 255, 255));
            telephonecust1.setBackground(new Color(255, 255, 255));
            niccust2.setBackground(new Color(255, 255, 255));
            addresscust1.setBackground(new Color(255, 255, 255));
            vehicle_da.setBackground(new Color(255, 255, 255));

        } else {

            String p = telephonecust1.getText();
            String tel = "^\\+?[0-9.  ()-]{10}$";
            Boolean q = p.matches(tel);
            if (q == false) {
                JOptionPane.showMessageDialog(null, "Invalid telephone", "invaild input", JOptionPane.ERROR_MESSAGE);
                telephonecust1.setText("");

            } else {
                String ni = niccust2.getText();
                String nicval = "^[0-9]{9}[vVxX]$";
                Boolean w = ni.matches(nicval);
                if (w == false) {
                    JOptionPane.showMessageDialog(null, "Invalid NIC", "invaild input", JOptionPane.ERROR_MESSAGE);
                    niccust2.setText("");

                } else {
                    int x = JOptionPane.showConfirmDialog(null, "Do you realy want to ADD");
                    if (x == 0) {
                        try {
                            String asd = "INSERT INTO trans_driver (driver_name,driver_address,driver_nic,driver_experience,driver_license,allocate_vehicle_no) values('" + namecus + "', '" + address + "','" + nic + "','" + telephone + "','" + licence + "','" + vehicle + "' )";

                            pst = conn.prepareStatement(asd);
                            pst.execute();
                            tabledv();//driver
                            tabledu();

                            JOptionPane.showMessageDialog(null, "saved");

                            namecust1.setText(" ");
                            addresscust1.setText(" ");
                            licence_da.setText(" ");
                            telephonecust1.setText(" ");
                            niccust2.setText(" ");
                            vehicle_da.setText(" ");

                        } catch (Exception e) {

                            JOptionPane.showMessageDialog(null, e);
                        }
                    }
                }
            }

        }
    }//GEN-LAST:event_addcusb1ActionPerformed

    private void resetcusb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetcusb1ActionPerformed
        namecust1.setText(" ");
        addresscust1.setText(" ");
        licence_da.setText(" ");
        telephonecust1.setText(" ");
        niccust2.setText(" ");
        vehicle_da.setText(" ");
    }//GEN-LAST:event_resetcusb1ActionPerformed

    private void demo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demo1ActionPerformed
        namecust1.setText("Malinga Anuradha ");
        addresscust1.setText("45,Piliyandala ");
        licence_da.setText("012345as ");
        telephonecust1.setText("0123456789");
        niccust2.setText("012345678V");
        vehicle_da.setText("BBJ6237");
    }//GEN-LAST:event_demo1ActionPerformed

    private void name_vcust1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_vcust1ActionPerformed

    }//GEN-LAST:event_name_vcust1ActionPerformed

    private void address_vcust1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_address_vcust1ActionPerformed

    }//GEN-LAST:event_address_vcust1ActionPerformed

    private void jTable23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable23MouseClicked
        int r = jTable23.getSelectedRow();

        String id = jTable23.getValueAt(r, 0).toString();
        String name = jTable23.getValueAt(r, 4).toString();
        String nic = jTable23.getValueAt(r, 1).toString();
        String add = jTable23.getValueAt(r, 2).toString();
        String tel = jTable23.getValueAt(r, 3).toString();
        String lic = jTable23.getValueAt(r, 5).toString();
        String vehi = jTable23.getValueAt(r, 6).toString();

        id_vcust1.setText(id);
        name_vcust1.setText(name);
        nic_vcut1.setText(nic);
        address_vcust1.setText(add);
        telephone_vcust1.setText(tel);
        vechile_vcust1.setText(vehi);
        licen_vcust2.setText(lic);
    }//GEN-LAST:event_jTable23MouseClicked

    private void name_ucust1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_ucust1ActionPerformed

    }//GEN-LAST:event_name_ucust1ActionPerformed

    private void adderss_ucust1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adderss_ucust1ActionPerformed

    }//GEN-LAST:event_adderss_ucust1ActionPerformed

    private void jTable25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable25MouseClicked
        int r = jTable25.getSelectedRow();

        String id = jTable25.getValueAt(r, 0).toString();
        String name = jTable25.getValueAt(r, 4).toString();
        String nic = jTable25.getValueAt(r, 1).toString();
        String add = jTable25.getValueAt(r, 2).toString();
        String tel = jTable25.getValueAt(r, 3).toString();
        String lic = jTable25.getValueAt(r, 5).toString();
        String vehi = jTable25.getValueAt(r, 6).toString();

        id_ucust1.setText(id);
        name_ucust1.setText(name);
        nic_ucust1.setText(nic);
        adderss_ucust1.setText(add);
        telephone_ucust1.setText(tel);
        email_ucust1.setText(vehi);
        licence_ucust1.setText(lic);
    }//GEN-LAST:event_jTable25MouseClicked

    private void update_ucust1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_ucust1ActionPerformed

    }//GEN-LAST:event_update_ucust1ActionPerformed

    private void rest_ucust1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rest_ucust1ActionPerformed
        id_ucust1.setText(" ");
        name_ucust1.setText(" ");
        nic_ucust1.setText(" ");
        adderss_ucust1.setText(" ");
        telephone_ucust1.setText(" ");
        email_ucust1.setText(" ");
        licence_ucust1.setText(" ");
    }//GEN-LAST:event_rest_ucust1ActionPerformed

    private void delete_cus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_cus1ActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you realy want to DELETE");
        if (x == 0) {
            String id = id_ucust1.getText();
            String sql = "Delete from trans_driver where iddriver='" + id + "' ";
            try {
                pst = conn.prepareStatement(sql);
                pst.execute();
                tabledv();//driver
                tabledu();

                id_ucust1.setText(" ");
                name_ucust1.setText(" ");
                nic_ucust1.setText(" ");
                adderss_ucust1.setText(" ");
                telephone_ucust1.setText(" ");
                email_ucust1.setText(" ");
                licence_ucust1.setText(" ");
                JOptionPane.showMessageDialog(null, "saved");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_delete_cus1ActionPerformed

    private void namecust2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namecust2ActionPerformed

    }//GEN-LAST:event_namecust2ActionPerformed

    private void telephonecust2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telephonecust2KeyReleased

    }//GEN-LAST:event_telephonecust2KeyReleased

    private void telephonecust2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_telephonecust2KeyTyped

    }//GEN-LAST:event_telephonecust2KeyTyped

    private void addresscust2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addresscust2ActionPerformed

    }//GEN-LAST:event_addresscust2ActionPerformed

    private void addcusb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addcusb2ActionPerformed

    }//GEN-LAST:event_addcusb2ActionPerformed

    private void resetcusb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetcusb2ActionPerformed

    }//GEN-LAST:event_resetcusb2ActionPerformed

    private void demo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demo2ActionPerformed

    }//GEN-LAST:event_demo2ActionPerformed

    private void name_vcust2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_vcust2ActionPerformed

    }//GEN-LAST:event_name_vcust2ActionPerformed

    private void address_vcust2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_address_vcust2ActionPerformed

    }//GEN-LAST:event_address_vcust2ActionPerformed

    private void jTable26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable26MouseClicked

    }//GEN-LAST:event_jTable26MouseClicked

    private void name_ucust2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_ucust2ActionPerformed

    }//GEN-LAST:event_name_ucust2ActionPerformed

    private void adderss_ucust2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adderss_ucust2ActionPerformed

    }//GEN-LAST:event_adderss_ucust2ActionPerformed

    private void jTable28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable28MouseClicked

    }//GEN-LAST:event_jTable28MouseClicked

    private void update_ucust2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_ucust2ActionPerformed

    }//GEN-LAST:event_update_ucust2ActionPerformed

    private void rest_ucust2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rest_ucust2ActionPerformed

    }//GEN-LAST:event_rest_ucust2ActionPerformed

    private void delete_cus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_cus2ActionPerformed

    }//GEN-LAST:event_delete_cus2ActionPerformed

    private void val2pacKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_val2pacKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_val2pacKeyTyped

    private void val1pacKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_val1pacKeyTyped

    }//GEN-LAST:event_val1pacKeyTyped

    private void val2seaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_val2seaKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_val2seaKeyTyped

    private void demonew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demonew1ActionPerformed

        customerid_anewt16.setText(" ");
        place1_anewt16.setText(" ");
        pass_anewt16.setText(" ");
        guide_anewt16.setText(" ");
        vechical_anewt16.setText(" ");
        days_anewt17.setText(" ");
        driver_anewt16.setText(" ");
        fee_anewt16.setText(" ");
        date_anewt16.setCalendar(null);
        dis_anewt17.setText(" ");
    }//GEN-LAST:event_demonew1ActionPerformed

    private void demonewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_demonewActionPerformed
        customerid_anewt16.setText("26 ");
        place1_anewt16.setText("Anuradhapura, Mathara ");
        pass_anewt16.setText("8");
        guide_anewt16.setText("none");
        vechical_anewt16.setText("own");
        days_anewt17.setText("8");
        driver_anewt16.setText("003");
        fee_anewt16.setText("20000");
        dis_anewt17.setText("BBQ");
        // TODO add your handling code here:
    }//GEN-LAST:event_demonewActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        name_pact.setText("Kalutara Adventure ");
        no_pact.setText("5");
        satus_pact.setText("none");
        customerid_pact.setText("24");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        name_apact.setText("kalutara Adventure ");
        destination_apact.setText("Kalutara");
        activity_apact.setText("Camping");
        fee_apact.setText("5000");
        day_apact.setText("3");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void customerid_anewt16KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customerid_anewt16KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_customerid_anewt16KeyTyped

    private void pass_anewt16KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pass_anewt16KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_pass_anewt16KeyTyped

    private void days_anewt17KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_days_anewt17KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_days_anewt17KeyTyped

    private void fee_anewt16KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fee_anewt16KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_fee_anewt16KeyTyped

    private void no_pactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_no_pactActionPerformed

    }//GEN-LAST:event_no_pactActionPerformed

    private void no_pactKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_pactKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_no_pactKeyTyped

    private void jTable21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable21MouseClicked
        int r = jTable21.getSelectedRow();

        String id = jTable21.getValueAt(r, 0).toString();
        String dis = jTable21.getValueAt(r, 1).toString();
        String cid = jTable21.getValueAt(r, 5).toString();
        String plase = jTable21.getValueAt(r, 3).toString();
        String noof = jTable21.getValueAt(r, 6).toString();
        String date = jTable21.getValueAt(r, 7).toString();
        String fee = jTable21.getValueAt(r, 2).toString();
        String pass = jTable21.getValueAt(r, 4).toString();
        String vehicle = jTable21.getValueAt(r, 8).toString();
        String guide = jTable21.getValueAt(r, 10).toString();
        String driver = jTable21.getValueAt(r, 9).toString();

        id_newvt.setText(id);
        cid_newvt.setText(cid);
        dis_newvt.setText(dis);
        plase_newvt.setText(plase);
        noof_newvt.setText(noof);
        date_newvt.setText(date);
        fee_newvt.setText(fee);
        pass_newvt.setText(pass);
        vehicle_newvt.setText(vehicle);
        guide_newvt.setText(guide);
        driver_newvt.setText(driver);

    }//GEN-LAST:event_jTable21MouseClicked

    private void jTable20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable20MouseClicked
        int r = jTable20.getSelectedRow();

        String id = jTable20.getValueAt(r, 0).toString();
        String dis = jTable20.getValueAt(r, 1).toString();
        String cid = jTable20.getValueAt(r, 5).toString();
        String plase = jTable20.getValueAt(r, 3).toString();
        String noof = jTable20.getValueAt(r, 6).toString();
        String date = jTable20.getValueAt(r, 7).toString();
        String fee = jTable20.getValueAt(r, 2).toString();
        String pass = jTable20.getValueAt(r, 4).toString();
        String vehicle = jTable20.getValueAt(r, 8).toString();
        String guide = jTable20.getValueAt(r, 10).toString();
        String driver = jTable20.getValueAt(r, 9).toString();

        id_newvt1.setText(id);
        cid_newvt1.setText(cid);
        dis_newvt1.setText(dis);
        plase_newvt1.setText(plase);
        noof_newvt1.setText(noof);
        date_newvt1.setText(date);
        fee_newvt1.setText(fee);
        pass_newvt1.setText(pass);
        vehicle_newvt1.setText(vehicle);
        guide_newvt1.setText(guide);
        driver_newvt1.setText(driver);
    }//GEN-LAST:event_jTable20MouseClicked

    private void cid_newvt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cid_newvt1ActionPerformed

    }//GEN-LAST:event_cid_newvt1ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you realy want to DELETE");
        if (x == 0) {
            String id = id_newvt1.getText();
            String sql = "Delete from trans_customize where idcustom='" + id + "' ";

            try {
                pst = conn.prepareStatement(sql);
                pst.execute();
                tablenewu();
                tablenewv();
                JOptionPane.showMessageDialog(null, " sucessfull");
                id_newvt1.setText(" ");
                plase_newvt1.setText(" ");
                pass_newvt1.setText(" ");
                guide_newvt1.setText(" ");
                vehicle_newvt1.setText(" ");
                noof_newvt1.setText(" ");
                driver_newvt1.setText(" ");
                fee_newvt1.setText(" ");
                date_newvt1.setText(" ");;
                dis_newvt1.setText(" ");
                cid_newvt1.setText(" ");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }

    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed

    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        id_newvt1.setText(" ");
        plase_newvt1.setText(" ");
        pass_newvt1.setText(" ");
        guide_newvt1.setText(" ");
        vehicle_newvt1.setText(" ");
        noof_newvt1.setText(" ");
        driver_newvt1.setText(" ");
        fee_newvt1.setText(" ");
        date_newvt1.setText(" ");;
        dis_newvt1.setText(" ");
        cid_newvt1.setText(" ");
    }//GEN-LAST:event_jButton38ActionPerformed

    private void search_viewb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_viewb2ActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you realy want to DELETE");
        if (x == 0) {
            String id = id_view1.getText();
            String sql = "Delete from trans_book where idbook='" + id + "' ";

            try {
                pst = conn.prepareStatement(sql);
                pst.execute();
                tableview();
                tableviewupdate();
                tableviewsearch();
                JOptionPane.showMessageDialog(null, " sucessfull");
                id_view1.setText("");
                packagename_viewt1.setText("");
                customerid_viewt1.setText("");
                status_view1.setText("");
                no_viewt1.setText("");
                date_pacd1.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_search_viewb2ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        name_oa.setText("Maxa offer");
        dis_oa.setText("least two packages ");
        pac_oa.setText("20");
        date_oa.setText("2016/9/18");
        duration_oa.setText("1 month");
        discount_oa.setText("20%");
    }//GEN-LAST:event_jButton21ActionPerformed

    private void pac_oaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pac_oaKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACKSPACE) || (c == KeyEvent.VK_DELETE))) {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_pac_oaKeyTyped

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        String name = name_oa.getText();
        String dis = dis_oa.getText();
        String pac = pac_oa.getText();
        String date = date_oa.getText();
        String duration = duration_oa.getText();
        String discount = discount_oa.getText();
        if (name.isEmpty()) {
            name_oa.setBackground(new Color(255, 150, 150));
        } else {
            name_oa.setBackground(new Color(255, 255, 255));
        }
        if (dis.isEmpty()) {
            dis_oa.setBackground(new Color(255, 150, 150));
        } else {
            dis_oa.setBackground(new Color(255, 255, 255));
        }
        if (pac.isEmpty()) {
            pac_oa.setBackground(new Color(255, 150, 150));
        } else {
            pac_oa.setBackground(new Color(255, 255, 255));
        }
        if (date.isEmpty()) {
            date_oa.setBackground(new Color(255, 150, 150));
        } else {
            date_oa.setBackground(new Color(255, 255, 255));
        }
        if (duration.isEmpty()) {
            duration_oa.setBackground(new Color(255, 150, 150));
        } else {
            duration_oa.setBackground(new Color(255, 255, 255));
        }
        if (discount.isEmpty()) {
            discount_oa.setBackground(new Color(255, 150, 150));
        } else {
            discount_oa.setBackground(new Color(255, 255, 255));
        }

        if (name.isEmpty() || dis.isEmpty() || duration.isEmpty() || date.isEmpty() || pac.isEmpty() || discount.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields to continue.", "Form Incomplete!", 0);
            discount_oa.setBackground(new Color(255, 255, 255));
            duration_oa.setBackground(new Color(255, 255, 255));
            date_oa.setBackground(new Color(255, 255, 255));
            pac_oa.setBackground(new Color(255, 255, 255));
            dis_oa.setBackground(new Color(255, 255, 255));
            name_oa.setBackground(new Color(255, 255, 255));
        } else {
            try {

                String q = "INSERT INTO trans_offers (name_of,discrip_of,id_package,date_of,duration_of,discount_of) values('" + name + "', '" + dis + "','" + pac + "','" + date + "','" + duration + "','" + discount + "' )";
                pst = conn.prepareStatement(q);
                pst.execute();
                tableoffersu();
                tableoffersv();
                JOptionPane.showMessageDialog(null, "saved");

                name_oa.setText(" ");
                dis_oa.setText(" ");
                pac_oa.setText(" ");
                date_oa.setText(" ");
                duration_oa.setText(" ");
                discount_oa.setText(" ");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        int r = jTable6.getSelectedRow();

        String id = jTable6.getValueAt(r, 0).toString();
        String name = jTable6.getValueAt(r, 1).toString();
        String pac = jTable6.getValueAt(r, 6).toString();
        String dis = jTable6.getValueAt(r, 5).toString();
        String date = jTable6.getValueAt(r, 2).toString();
        String duration = jTable6.getValueAt(r, 3).toString();
        String discount = jTable6.getValueAt(r, 4).toString();

        id_ov.setText(id);
        name_ov.setText(name);
        dis_ov.setText(dis);
        pac_ov.setText(pac);
        date_ov.setText(date);
        duration_ov.setText(duration);
        discount_ov.setText(discount);
    }//GEN-LAST:event_jTable6MouseClicked

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you realy want to DELETE");
        if (x == 0) {
            String id = id_ou.getText();
            String sql = "Delete from trans_offers where idoffers='" + id + "' ";

            try {
                pst = conn.prepareStatement(sql);
                pst.execute();
                tableoffersu();
                tableoffersv();
                JOptionPane.showMessageDialog(null, " sucessfull");

                id_ou.setText(" ");
                name_ou.setText(" ");
                dis_ou.setText(" ");
                pac_ou.setText(" ");
                date_ou.setText(" ");
                duration_ou.setText(" ");
                discount_ou.setText(" ");

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you realy want to update");
        if (x == 0) {

            String id = id_ou.getText();
            String name = name_ou.getText();
            String dis = dis_ou.getText();
            String pac = pac_ou.getText();
            String date = date_ou.getText();
            String duration = duration_ou.getText();
            String discount = discount_ou.getText();
            if (name.isEmpty()) {
                name_ou.setBackground(new Color(255, 150, 150));
            } else {
                name_ou.setBackground(new Color(255, 255, 255));
            }
            if (dis.isEmpty()) {
                dis_ou.setBackground(new Color(255, 150, 150));
            } else {
                dis_ou.setBackground(new Color(255, 255, 255));
            }
            if (pac.isEmpty()) {
                pac_ou.setBackground(new Color(255, 150, 150));
            } else {
                pac_ou.setBackground(new Color(255, 255, 255));
            }
            if (date.isEmpty()) {
                date_ou.setBackground(new Color(255, 150, 150));
            } else {
                date_ou.setBackground(new Color(255, 255, 255));
            }
            if (duration.isEmpty()) {
                duration_ou.setBackground(new Color(255, 150, 150));
            } else {
                duration_ou.setBackground(new Color(255, 255, 255));
            }
            if (discount.isEmpty()) {
                discount_ou.setBackground(new Color(255, 150, 150));
            } else {
                discount_ou.setBackground(new Color(255, 255, 255));
            }

            if (name.isEmpty() || dis.isEmpty() || duration.isEmpty() || date.isEmpty() || pac.isEmpty() || discount.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields to continue.", "Form Incomplete!", 0);
                discount_ou.setBackground(new Color(255, 255, 255));
                duration_ou.setBackground(new Color(255, 255, 255));
                date_ou.setBackground(new Color(255, 255, 255));
                pac_ou.setBackground(new Color(255, 255, 255));
                dis_ou.setBackground(new Color(255, 255, 255));
                name_ou.setBackground(new Color(255, 255, 255));
            } else {

                String q = "update trans_offers set name_of='" + name + "', date_of='" + date + "',duration_of='" + duration + "',discount_of='" + discount + "',discrip_of='" + dis + "',id_package='" + pac + "' where idoffers='" + id + "' ";

                try {
                    pst = conn.prepareStatement(q);
                    pst.execute();
                    tableoffersu();
                    tableoffersv();
                    JOptionPane.showMessageDialog(null, "saved");

                    id_ou.setText(" ");
                    name_ou.setText(" ");
                    dis_ou.setText(" ");
                    pac_ou.setText(" ");
                    date_ou.setText(" ");
                    duration_ou.setText(" ");
                    discount_ou.setText(" ");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

            }
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        int r = jTable7.getSelectedRow();

        String id = jTable7.getValueAt(r, 0).toString();
        String name = jTable7.getValueAt(r, 1).toString();
        String pac = jTable7.getValueAt(r, 6).toString();
        String dis = jTable7.getValueAt(r, 5).toString();
        String date = jTable7.getValueAt(r, 2).toString();
        String duration = jTable7.getValueAt(r, 3).toString();
        String discount = jTable7.getValueAt(r, 4).toString();

        id_ou.setText(id);
        name_ou.setText(name);
        dis_ou.setText(dis);
        pac_ou.setText(pac);
        date_ou.setText(date);
        duration_ou.setText(duration);
        discount_ou.setText(discount);
    }//GEN-LAST:event_jTable7MouseClicked

    private void licence_ucust1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_licence_ucust1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_licence_ucust1ActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        int result=JOptionPane.showConfirmDialog(AddO,"Do you want to Log Out from the system?","Logout Confirmation",0);
        if(result==0){
            System_Login login=new System_Login();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_logoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transport_Manager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddO;
    private javax.swing.JPanel CUSTOMER;
    private javax.swing.JPanel DRIVER;
    private javax.swing.JPanel GUIDE;
    private javax.swing.JPanel HOME;
    private javax.swing.JPanel NEW;
    private javax.swing.JPanel OFFERS;
    private javax.swing.JPanel PACKAGE;
    private javax.swing.JPanel PAYMENT;
    private javax.swing.JPanel REPORTS1;
    private javax.swing.JPanel VEHICAL;
    private javax.swing.JPanel VIEWBOOKIN;
    private javax.swing.JPanel ViewO;
    private javax.swing.JLabel activity_apac;
    private javax.swing.JTextField activity_apact;
    private javax.swing.JLabel activity_upac;
    private javax.swing.JLabel activity_upac1;
    private javax.swing.JTextField activity_upact;
    private javax.swing.JTextField activity_upact1;
    private javax.swing.JButton add_anewb16;
    private javax.swing.JButton add_apacd;
    private javax.swing.JPanel add_pack;
    private javax.swing.JButton add_payb;
    private javax.swing.JPanel addcus;
    private javax.swing.JPanel addcus1;
    private javax.swing.JPanel addcus2;
    private javax.swing.JButton addcusb;
    private javax.swing.JButton addcusb1;
    private javax.swing.JButton addcusb2;
    private javax.swing.JTextField adderss_ucust;
    private javax.swing.JTextField adderss_ucust1;
    private javax.swing.JTextField adderss_ucust2;
    private javax.swing.JPanel addnew15;
    private javax.swing.JPanel addpay;
    private javax.swing.JLabel address_ucus;
    private javax.swing.JLabel address_ucus1;
    private javax.swing.JLabel address_ucus2;
    private javax.swing.JTextField address_vcust;
    private javax.swing.JTextField address_vcust1;
    private javax.swing.JTextField address_vcust2;
    private javax.swing.JLabel addresscus;
    private javax.swing.JLabel addresscus1;
    private javax.swing.JLabel addresscus2;
    private javax.swing.JTextField addresscust;
    private javax.swing.JTextField addresscust1;
    private javax.swing.JTextField addresscust2;
    private javax.swing.JLabel addressvcus;
    private javax.swing.JLabel addressvcus1;
    private javax.swing.JLabel addressvcus2;
    private javax.swing.JLabel balance_pay;
    private javax.swing.JTextField balance_payt;
    private javax.swing.JLabel balance_upay;
    private javax.swing.JTextField balance_upayt;
    private javax.swing.JLabel balance_vpay;
    private javax.swing.JTextField balance_vpayt;
    private javax.swing.JButton book_pacb;
    private javax.swing.JLabel bookingid_vcus1;
    private javax.swing.JLabel bookingid_vcus2;
    private javax.swing.JTextField bookingid_vcust2;
    private javax.swing.JPanel bookpacage;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cid_newvt;
    private javax.swing.JTextField cid_newvt1;
    private javax.swing.JButton createNew;
    private javax.swing.JButton customer;
    private javax.swing.JLabel customer_pay;
    private javax.swing.JLabel customer_vpay;
    private javax.swing.JTextField customer_vpayt;
    private javax.swing.JLabel customerid_anew16;
    private javax.swing.JTextField customerid_anewt16;
    private javax.swing.JLabel customerid_pac;
    private javax.swing.JTextField customerid_pact;
    private javax.swing.JTextField customerid_payt;
    private javax.swing.JLabel customerid_upay;
    private javax.swing.JTextField customerid_upayt;
    private javax.swing.JLabel customerid_view;
    private javax.swing.JLabel customerid_view1;
    private javax.swing.JLabel customerid_view2;
    private javax.swing.JLabel customerid_view3;
    private javax.swing.JTextField customerid_viewt;
    private javax.swing.JTextField customerid_viewt1;
    private javax.swing.JLabel date1;
    private javax.swing.JLabel date_anew16;
    private com.toedter.calendar.JDateChooser date_anewt16;
    private javax.swing.JTextField date_newvt;
    private javax.swing.JTextField date_newvt1;
    private javax.swing.JTextField date_oa;
    private javax.swing.JTextField date_ou;
    private javax.swing.JTextField date_ov;
    private javax.swing.JLabel date_pac;
    private com.toedter.calendar.JDateChooser date_pacd;
    private javax.swing.JTextField date_pacd1;
    private javax.swing.JLabel date_pay;
    private com.toedter.calendar.JDateChooser date_paydate;
    private javax.swing.JLabel date_upay;
    private javax.swing.JTextField date_upayt;
    private javax.swing.JLabel date_view;
    private javax.swing.JLabel date_view1;
    private javax.swing.JLabel date_view2;
    private javax.swing.JLabel date_view3;
    private javax.swing.JTextField date_viewt;
    private javax.swing.JLabel date_vpay;
    private javax.swing.JTextField date_vpayt;
    private javax.swing.JTextField day_apact;
    private javax.swing.JLabel day_apcc;
    private javax.swing.JLabel day_upac;
    private javax.swing.JLabel day_upac1;
    private javax.swing.JLabel day_upac2;
    private javax.swing.JTextField day_upact;
    private javax.swing.JTextField day_upact1;
    private javax.swing.JTextField days_anewt17;
    private javax.swing.JButton delete_cus;
    private javax.swing.JButton delete_cus1;
    private javax.swing.JButton delete_cus2;
    private javax.swing.JButton delete_upab;
    private javax.swing.JButton demo;
    private javax.swing.JButton demo1;
    private javax.swing.JButton demo2;
    private javax.swing.JButton demonew;
    private javax.swing.JButton demonew1;
    private javax.swing.JLabel destiantion_upac;
    private javax.swing.JLabel destiantion_upac1;
    private javax.swing.JLabel destination_apac;
    private javax.swing.JTextField destination_apact;
    private javax.swing.JTextField destination_upact;
    private javax.swing.JTextField destination_upact1;
    private javax.swing.JTextField dis_anewt17;
    private javax.swing.JTextField dis_newvt;
    private javax.swing.JTextField dis_newvt1;
    private javax.swing.JTextField dis_oa;
    private javax.swing.JTextField dis_ou;
    private javax.swing.JTextField dis_ov;
    private javax.swing.JTextField discount_oa;
    private javax.swing.JTextField discount_ou;
    private javax.swing.JTextField discount_ov;
    private javax.swing.JLabel discount_ov1;
    private javax.swing.JButton driver;
    private javax.swing.JLabel driver_anew16;
    private javax.swing.JTextField driver_anewt16;
    private javax.swing.JTextField driver_newvt;
    private javax.swing.JTextField driver_newvt1;
    private javax.swing.JTextField duration_oa;
    private javax.swing.JTextField duration_ou;
    private javax.swing.JTextField duration_ov;
    private javax.swing.JLabel duration_ov1;
    private javax.swing.JTextField email_ucust;
    private javax.swing.JTextField email_ucust1;
    private javax.swing.JTextField email_ucust2;
    private javax.swing.JTextField email_vcust1;
    private javax.swing.JTextField email_vcust3;
    private javax.swing.JLabel emaill;
    private javax.swing.JLabel emaill1;
    private javax.swing.JLabel emaill2;
    private javax.swing.JLabel emaill3;
    private javax.swing.JTextField emailt;
    private javax.swing.JTextField emailt2;
    private javax.swing.JLabel emailvcus1;
    private javax.swing.JLabel emailvcus2;
    private javax.swing.JLabel emailvcus3;
    private javax.swing.JLabel fee_anew16;
    private javax.swing.JLabel fee_anew17;
    private javax.swing.JLabel fee_anew18;
    private javax.swing.JTextField fee_anewt16;
    private javax.swing.JLabel fee_apac;
    private javax.swing.JTextField fee_apact;
    private javax.swing.JTextField fee_newvt;
    private javax.swing.JTextField fee_newvt1;
    private javax.swing.JLabel fee_uapc;
    private javax.swing.JLabel fee_uapc1;
    private javax.swing.JLabel fee_uapc2;
    private javax.swing.JTextField fee_upact;
    private javax.swing.JTextField fee_upact1;
    private javax.swing.JButton guide;
    private javax.swing.JLabel guide_anew16;
    private javax.swing.JTextField guide_anewt16;
    private javax.swing.JTextField guide_newvt;
    private javax.swing.JTextField guide_newvt1;
    private javax.swing.JButton home;
    private javax.swing.JLabel homel;
    private javax.swing.JTextField id_newvt;
    private javax.swing.JTextField id_newvt1;
    private javax.swing.JTextField id_ou;
    private javax.swing.JTextField id_ov;
    private javax.swing.JLabel id_ucus;
    private javax.swing.JLabel id_ucus1;
    private javax.swing.JLabel id_ucus2;
    private javax.swing.JTextField id_ucust;
    private javax.swing.JTextField id_ucust1;
    private javax.swing.JTextField id_ucust2;
    private javax.swing.JTextField id_upact;
    private javax.swing.JTextField id_upact1;
    private javax.swing.JLabel id_upay;
    private javax.swing.JTextField id_upayt;
    private javax.swing.JTextField id_vcust;
    private javax.swing.JTextField id_vcust1;
    private javax.swing.JTextField id_vcust2;
    private javax.swing.JTextField id_view;
    private javax.swing.JTextField id_view1;
    private javax.swing.JLabel id_vpay;
    private javax.swing.JTextField id_vpayt;
    private javax.swing.JLabel idvcus;
    private javax.swing.JLabel idvcus1;
    private javax.swing.JLabel idvcus2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable18;
    private javax.swing.JTable jTable19;
    private javax.swing.JTable jTable20;
    private javax.swing.JTable jTable21;
    private javax.swing.JTable jTable22;
    private javax.swing.JTable jTable23;
    private javax.swing.JTable jTable25;
    private javax.swing.JTable jTable26;
    private javax.swing.JTable jTable28;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField licen_vcust2;
    private javax.swing.JTextField licence_da;
    private javax.swing.JTextField licence_ucust1;
    private javax.swing.JButton logout;
    private javax.swing.JLabel methord_pay;
    private javax.swing.JTextField methord_payt;
    private javax.swing.JLabel methord_upay;
    private javax.swing.JTextField methord_upayt;
    private javax.swing.JLabel methord_vpay;
    private javax.swing.JTextField methord_vpayt;
    private javax.swing.JLabel mix;
    private javax.swing.JLabel name_apac;
    private javax.swing.JTextField name_apact;
    private javax.swing.JTextField name_oa;
    private javax.swing.JTextField name_ou;
    private javax.swing.JTextField name_ov;
    private javax.swing.JLabel name_pac;
    private javax.swing.JTextField name_pact;
    private javax.swing.JLabel name_ucus;
    private javax.swing.JLabel name_ucus1;
    private javax.swing.JLabel name_ucus2;
    private javax.swing.JLabel name_ucus3;
    private javax.swing.JTextField name_ucust;
    private javax.swing.JTextField name_ucust1;
    private javax.swing.JTextField name_ucust2;
    private javax.swing.JLabel name_upac;
    private javax.swing.JLabel name_upac1;
    private javax.swing.JTextField name_upact;
    private javax.swing.JTextField name_upact1;
    private javax.swing.JTextField name_vcust;
    private javax.swing.JTextField name_vcust1;
    private javax.swing.JTextField name_vcust2;
    private javax.swing.JLabel namecus;
    private javax.swing.JLabel namecus1;
    private javax.swing.JLabel namecus2;
    private javax.swing.JTextField namecust;
    private javax.swing.JTextField namecust1;
    private javax.swing.JTextField namecust2;
    private javax.swing.JLabel namevcus;
    private javax.swing.JLabel namevcus1;
    private javax.swing.JLabel namevcus2;
    private javax.swing.JLabel nic_ucus;
    private javax.swing.JLabel nic_ucus1;
    private javax.swing.JLabel nic_ucus2;
    private javax.swing.JLabel nic_ucus3;
    private javax.swing.JLabel nic_ucus4;
    private javax.swing.JLabel nic_ucus5;
    private javax.swing.JTextField nic_ucust;
    private javax.swing.JTextField nic_ucust1;
    private javax.swing.JTextField nic_ucust2;
    private javax.swing.JTextField nic_vcut;
    private javax.swing.JTextField nic_vcut1;
    private javax.swing.JTextField nic_vcut2;
    private javax.swing.JLabel niccus;
    private javax.swing.JLabel niccus1;
    private javax.swing.JLabel niccus2;
    private javax.swing.JTextField niccust1;
    private javax.swing.JTextField niccust2;
    private javax.swing.JTextField niccust3;
    private javax.swing.JLabel nicvcus;
    private javax.swing.JLabel nicvcus1;
    private javax.swing.JLabel nicvcus2;
    private javax.swing.JLabel no_pac;
    private javax.swing.JTextField no_pact;
    private javax.swing.JLabel no_view;
    private javax.swing.JLabel no_view1;
    private javax.swing.JTextField no_viewt;
    private javax.swing.JTextField no_viewt1;
    private javax.swing.JLabel nofday_anew16;
    private javax.swing.JTextField noof_newvt;
    private javax.swing.JTextField noof_newvt1;
    private javax.swing.JButton offers1;
    private javax.swing.JTextField pac_oa;
    private javax.swing.JTextField pac_ou;
    private javax.swing.JTextField pac_ov;
    private javax.swing.JTable pac_view;
    private javax.swing.JLabel packagename_view;
    private javax.swing.JLabel packagename_view1;
    private javax.swing.JTextField packagename_viewt;
    private javax.swing.JTextField packagename_viewt1;
    private javax.swing.JLabel paid_pay;
    private javax.swing.JTextField paid_payt;
    private javax.swing.JTextField paid_upayt;
    private javax.swing.JLabel paid_vpay;
    private javax.swing.JTextField paid_vpayt;
    private javax.swing.JButton pakageB;
    private javax.swing.JPanel panalcontent;
    private javax.swing.JPanel panalhead;
    private javax.swing.JPanel panalmenu;
    private javax.swing.JTextField pass_anewt16;
    private javax.swing.JTextField pass_newvt;
    private javax.swing.JTextField pass_newvt1;
    private javax.swing.JLabel pay_upay;
    private javax.swing.JButton payment;
    private javax.swing.JLabel place1_anew16;
    private javax.swing.JTextField place1_anewt16;
    private javax.swing.JTextField plase_newvt;
    private javax.swing.JTextField plase_newvt1;
    private javax.swing.JButton reports;
    private javax.swing.JButton reset_payb;
    private javax.swing.JButton reset_upayb;
    private javax.swing.JButton resetcusb;
    private javax.swing.JButton resetcusb1;
    private javax.swing.JButton resetcusb2;
    private javax.swing.JButton rest_ucust;
    private javax.swing.JButton rest_ucust1;
    private javax.swing.JButton rest_ucust2;
    private javax.swing.JTextField satus_pact;
    private javax.swing.JTable sea_pac;
    private javax.swing.JPanel search;
    private javax.swing.JButton search_viewb1;
    private javax.swing.JButton search_viewb2;
    private javax.swing.JButton search_vpayb;
    private javax.swing.JPanel searchcus;
    private javax.swing.JLabel status_pac;
    private javax.swing.JTextField status_view;
    private javax.swing.JTextField status_view1;
    private javax.swing.JTabbedPane tabcustomer;
    private javax.swing.JTabbedPane tabcustomer2;
    private javax.swing.JTabbedPane tabdriver;
    private javax.swing.JScrollPane table_ucust;
    private javax.swing.JScrollPane table_ucust1;
    private javax.swing.JScrollPane table_ucust3;
    private javax.swing.JScrollPane table_ucust5;
    private javax.swing.JScrollPane table_upac;
    private javax.swing.JScrollPane table_upac1;
    private javax.swing.JScrollPane table_upac2;
    private javax.swing.JScrollPane table_upay;
    private javax.swing.JScrollPane table_vcus;
    private javax.swing.JScrollPane table_vcus1;
    private javax.swing.JScrollPane table_vcus2;
    private javax.swing.JScrollPane table_view;
    private javax.swing.JScrollPane table_view1;
    private javax.swing.JScrollPane table_view2;
    private javax.swing.JScrollPane table_vpay;
    private javax.swing.JTabbedPane tabnew15;
    private javax.swing.JTabbedPane tabnew3;
    private javax.swing.JTabbedPane tabpackage;
    private javax.swing.JTabbedPane tabpayment;
    private javax.swing.JLabel telephone_ucus;
    private javax.swing.JLabel telephone_ucus1;
    private javax.swing.JLabel telephone_ucus2;
    private javax.swing.JTextField telephone_ucust;
    private javax.swing.JTextField telephone_ucust1;
    private javax.swing.JTextField telephone_ucust2;
    private javax.swing.JTextField telephone_vcust;
    private javax.swing.JTextField telephone_vcust1;
    private javax.swing.JTextField telephone_vcust2;
    private javax.swing.JLabel telephonecus;
    private javax.swing.JLabel telephonecus1;
    private javax.swing.JLabel telephonecus2;
    private javax.swing.JTextField telephonecust;
    private javax.swing.JTextField telephonecust1;
    private javax.swing.JTextField telephonecust2;
    private javax.swing.JLabel telephonevcus;
    private javax.swing.JLabel telephonevcus1;
    private javax.swing.JLabel telephonevcus2;
    private javax.swing.JLabel time;
    private javax.swing.JLabel total_pay;
    private javax.swing.JTextField total_payt;
    private javax.swing.JLabel total_upay;
    private javax.swing.JTextField total_upayt;
    private javax.swing.JLabel total_vpay;
    private javax.swing.JTextField total_vpayt;
    private javax.swing.JButton upadete_upay;
    private javax.swing.JPanel updateO;
    private javax.swing.JButton update_ucust;
    private javax.swing.JButton update_ucust1;
    private javax.swing.JButton update_ucust2;
    private javax.swing.JButton update_upacb;
    private javax.swing.JPanel updatecus;
    private javax.swing.JPanel updatecus1;
    private javax.swing.JPanel updatecus2;
    private javax.swing.JPanel updatenew4;
    private javax.swing.JPanel updatepay;
    private javax.swing.JPanel uppackage;
    private javax.swing.JTextField va1cuse;
    private javax.swing.JTextField va2cuse;
    private javax.swing.JTextField val1pac;
    private javax.swing.JTextField val1sea;
    private javax.swing.JTextField val2pac;
    private javax.swing.JTextField val2sea;
    private javax.swing.JTextField vechical_anewt16;
    private javax.swing.JTextField vechile_vcust1;
    private javax.swing.JLabel vehical_anew16;
    private javax.swing.JTextField vehicle_da;
    private javax.swing.JTextField vehicle_newvt;
    private javax.swing.JTextField vehicle_newvt1;
    private javax.swing.JPanel view;
    private javax.swing.JButton viewBook;
    private javax.swing.JPanel viewcus;
    private javax.swing.JPanel viewcus1;
    private javax.swing.JPanel viewcus2;
    private javax.swing.JPanel viewnew4;
    private javax.swing.JPanel viewpay;
    // End of variables declaration//GEN-END:variables

}
