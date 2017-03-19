
package glen.finance;

import glen.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class DBAccess {
    
    Connection conn=DBconnect.ConnectDB();
    PreparedStatement pstatement=null;
    ResultSet Results =null;
    
    String id="";
    String colName="";
    String tableName="";
    double totalAmount;
    double currentBalance;
    double totalExpense;
    double latestBalence;
    
    //-------------------------------------------------------------------------------------------------
    public ResultSet finincometableload(){
        try{
            String Sql="select cashflow_id as 'Clashflow ID', cashflow_date as Date, cashflow_description as Description,cashflow_department as Department, cashflow_method as Method, cashflow_amount as Amount, cashflow_Payment_status as Status, cashflow_approval as Approval from fin_cashflow where cashflow_Payment_type='Income' and cashflow_approval='not approved' order by cashflow_Id desc";
            pstatement=conn.prepareStatement(Sql);
            Results=pstatement.executeQuery();
            
            return Results;// return result set to insert results to income GUI table
           }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return Results;
    }       //income table load
    
    public ResultSet finincometableload1(){
        try{
            String Sql="select cashflow_id as 'Clashflow ID',income_id as 'Income ID', cashflow_date as Date, cashflow_description as Description,cashflow_department as Department, cashflow_method as Method, cashflow_amount as Amount, income_Payment_status as Status from fin_cashflow, fin_income where cashflow_id=income_cashflow_id order by cashflow_Id desc";
            pstatement=conn.prepareStatement(Sql);
            Results=pstatement.executeQuery();
            // return result set to insert results to income GUI table
            return Results;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return Results;
    }       //income table load
    
    public ResultSet finexpensetableaoad(){
        try{
            String Sql="select cashflow_id as 'Clashflow ID', cashflow_date as Date, cashflow_description as Description,cashflow_department as Department, cashflow_method as Method, cashflow_amount as Amount, cashflow_Payment_status as Status, cashflow_approval as Approval from fin_cashflow where cashflow_Payment_type='Expenditure' and cashflow_approval='not approved' order by cashflow_Id desc";
            pstatement=conn.prepareStatement(Sql);
            Results=pstatement.executeQuery();
            return Results;
            }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return Results;
    }       //expense table load
    
    public ResultSet finexpensetableaoad1(){
        try{
            String Sql="select cashflow_id as 'Clashflow ID',expense_id as 'Expenditure ID', cashflow_date as Date, cashflow_description as Description,cashflow_department as Department, cashflow_method as Method, cashflow_amount as Amount, expense_Payment_status as Status from fin_cashflow, fin_expenditure where cashflow_id=expense_cashflow_id order by cashflow_Id desc";
            pstatement=conn.prepareStatement(Sql);
            Results=pstatement.executeQuery();
            return Results;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return Results;
    }       //expense table load
    
    public ResultSet finasseststableload(){
        try{
            String Sql="select cashflow_id as 'Clashflow ID', cashflow_date as Date, cashflow_description as Description,cashflow_department as Department, cashflow_method as Method, cashflow_amount as Amount, cashflow_Payment_status as Status, cashflow_approval as Aprroval from fin_cashflow where cashflow_Payment_type='Assests'  and cashflow_approval='not approved' order by cashflow_Id desc";
            pstatement=conn.prepareStatement(Sql);
            Results=pstatement.executeQuery();
            return Results;// inserting table results to Assests GUI table
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return Results;
    }       //assests table load
    
    public ResultSet finasseststableload1(){
        try{
            String Sql="select cashflow_id as 'Clashflow ID',assests_id as 'Assests ID', cashflow_date as Date, cashflow_description as Description,cashflow_department as Department, cashflow_method as Method, cashflow_amount as Amount, assests_Payment_status as Status from fin_cashflow, fin_assests where cashflow_id=assests_cashflow_id order by cashflow_Id desc";
            pstatement=conn.prepareStatement(Sql);
            Results=pstatement.executeQuery();
            return Results;// inserting table results to Assests GUI table
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return Results;
    }       //assests table load
    
    public ResultSet finliabilitytableload(){
         try{
            String Sql="select cashflow_id as 'Clashflow ID', cashflow_date as Date, cashflow_description as Description, cashflow_method as Method, cashflow_amount as Amount, cashflow_Payment_status as Status, cashflow_approval as Aprroval from fin_cashflow where cashflow_Payment_type='Liability'  and cashflow_approval='not approved' order by cashflow_Id desc";
            pstatement=conn.prepareStatement(Sql);
            Results=pstatement.executeQuery();
            return Results;      // inserting table results to liability GUI table
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
      return Results;
    }       //liability table load
    
    public ResultSet finliabilitytableload1(){
         try{
            String Sql="select cashflow_id as 'Clashflow ID',liability_id as 'Liability ID', cashflow_date as Date, cashflow_description as Description, cashflow_method as Method, cashflow_amount as Amount, liability_Payment_status as Status from fin_cashflow, fin_Liability where cashflow_id=liability_cashflow_id order by cashflow_Id desc";
            pstatement=conn.prepareStatement(Sql);
            Results=pstatement.executeQuery();
            return Results;     // inserting table results to liability GUI table
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
      return Results;
    }       //liability table load
    
    public ResultSet fininvestmenttableload(){
        try{
            String Sql="select cashflow_id as 'Clashflow ID', cashflow_date as Date, cashflow_description as Description, cashflow_method as Method, cashflow_amount as Amount, cashflow_Payment_status as Status, cashflow_approval as Aprroval from fin_cashflow where cashflow_Payment_type='Investment'  and cashflow_approval='not approved' order by cashflow_Id desc";
            pstatement=conn.prepareStatement(Sql);
            Results=pstatement.executeQuery();
            return Results;      // inserting table results to investment GUI table
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return Results;
    }       //investment table load
    
    public ResultSet fininvestmenttableload1(){
        try{
            String Sql="select cashflow_id as 'Clashflow ID',investment_id as 'Investment ID', cashflow_date as Date, cashflow_description as Description, cashflow_method as Method, cashflow_amount as Amount, investment_Payment_status as Status from fin_cashflow, fin_investment where cashflow_id=investment_cashflow_id order by cashflow_Id desc";
            pstatement=conn.prepareStatement(Sql);
            Results=pstatement.executeQuery();
            return Results;     // inserting table results to investment GUI table
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        return Results;
    }       //investment table load
    
    public ResultSet fincashflowtableload(){
         try{
            String Sql="select cashflow_id as 'Clashflow ID', cashflow_date as Date, cashflow_description as Description,cashflow_department as Department, cashflow_method as Method, cashflow_amount as Amount, cashflow_Payment_status as Status,cashflow_Payment_type as Type, cashflow_approval as Approval from fin_cashflow order by cashflow_Id desc";
            pstatement=conn.prepareStatement(Sql);
            Results=pstatement.executeQuery();
            return Results;     // inserting table results to cashflow GUI table
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
      return Results;
    }       //cashflow table load
    
    public ResultSet fincashflowtableload1(){
         try{
            String Sql="select cashflow_id as 'Clashflow ID', cashflow_date as Date, cashflow_description as Description,cashflow_department as Department, cashflow_method as Method, cashflow_amount as Amount, cashflow_Payment_status as Status,cashflow_Payment_type as Type, cashflow_approval as Approval from fin_cashflow order by cashflow_Id desc";
            pstatement=conn.prepareStatement(Sql);
            Results=pstatement.executeQuery();
            return Results;      // inserting table results to cashflow GUI table
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
      return Results;
    }       //cashflow table load
    
    public ResultSet finbudgettableload() {
        try{
            String sql= "select budget_request_id as 'Request ID', request_budget_id as 'Budget ID', budget_request_date as Date, budget_request_department as Department, budget_request_amount as Amount, budget_request_status as Status from fin_budget_request order by budget_request_Id desc";
            pstatement=conn.prepareStatement(sql);
            Results=pstatement.executeQuery();
            return Results;      // inserting table results to budget GUI table
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
      return Results;
    }       //budget table load

    public ResultSet finbudgettableload1() {
        try{
            String sql= "select budget_id as 'Budget ID', DATE_FORMAT(budget_date, '%Y-%m-%d') as Date,budget_description as Description, budget_department as Department, budget_amount as Amount, budget_Payment_status as Status, budget_balance as Balance from fin_budget";
            pstatement=conn.prepareStatement(sql);
            Results=pstatement.executeQuery();
            return Results;      // inserting table results to budget GUI table
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
      return Results;
    }       //budget table load
    
    public ResultSet finuserlogtableload() {
        try{
            String sql= "select userlog_id as 'Userlog ID', userlog_date as Date, system_user_id as 'User ID', userlog_username as Username, userlog_action_type as 'Action Type', userlog_action as Action, userlog_action_id as 'Action ID' from fin_userlog order by userlog_id desc";
            pstatement=conn.prepareStatement(sql);
            Results=pstatement.executeQuery();
            return Results;      // inserting table results to userlog GUI table
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
      return Results;
    }       //userlog table load
    
    public ResultSet finusertableload(String availability){
        try{
            String sql = "select system_user_id as 'User ID', employee_id as 'Employee ID', full_name as 'Full Name',  username as 'Username', password as 'Password', "
                    + " DATE_FORMAT(last_updated_date, '%Y-%m-%d') as 'Last Updated Date' from system_user where user_availability = '"+availability+"' and department = 'Finance' and access_level not like 'Admin'";
            pstatement = conn.prepareStatement(sql);
            Results = pstatement.executeQuery();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(new Finance_Main("").togglePanels, e, "User Data Retrieval Error!", 0);
        }
        return Results;
    }
    //-------------------------------------------------------------------------------------------------
    
    public boolean addIncome(String year, String month, String day, String description, String department, String paymentMethod, double amount, String cashflowType, String paymentStatus, String cashflowApproval, int cashflowId, String incomeStatus)
    {
        boolean status=false;
        try{                                                                //first inserting record to cash flow
                String sql = "insert into fin_cashflow(cashflow_date, cashflow_description, cashflow_department, cashflow_method, cashflow_amount, cashflow_Payment_type, cashflow_Payment_status, cashflow_approval) values('" + year + "/" + month + "/" + day + "', '" + description + "', '" + department + "', '" + paymentMethod + "' , '" + amount + "' , '" + cashflowType + "' , '" + paymentStatus + "' , '" + cashflowApproval + "' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
                                                                                //then get cashflow id from cashflow table
                sql = "select cashflow_id from fin_cashflow order by cashflow_id desc limit 1";
                pstatement = conn.prepareStatement(sql);
                Results = pstatement.executeQuery();
                while(Results.next())
                    cashflowId= Results.getInt(1);
                                                                                //finally add income id and cashflow id to income table
                sql = "insert into fin_income(income_cashflow_id, income_payment_status) values('" + cashflowId + "' , '" + incomeStatus + "' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
                
                status = true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        return status;
    }
    
    public boolean addExpense(String year, String month, String day, String description, String department, String paymentMethod, double amount, String cashflowType, String paymentStatus, String cashflowApproval, int cashflowId, String expenditureStatus)
    {
        boolean status=false;
        try{                                                                //first inserting record to cash flow
                String sql = "insert into fin_cashflow(cashflow_date, cashflow_description, cashflow_department, cashflow_method, cashflow_amount, cashflow_Payment_type, cashflow_Payment_status, cashflow_approval) values('" + year + "/" + month + "/" + day + "', '" + description + "', '" + department + "', '" + paymentMethod + "' , '" + amount + "' , '" + cashflowType + "' , '" + paymentStatus + "' , '" + cashflowApproval + "' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
                                                                                //then get cashflow id from cashflow table
                sql = "select cashflow_id from fin_cashflow order by cashflow_id desc limit 1";
                pstatement = conn.prepareStatement(sql);
                Results = pstatement.executeQuery();
                while(Results.next())
                    cashflowId= Results.getInt(1);
                                                                                //finally add expenditure id and cashflow id to expenditure table
                sql = "insert into fin_expenditure(expense_cashflow_id, expense_payment_status) values('" + cashflowId + "' , '" + expenditureStatus + "' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
                
                status = true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        return status;
    }
    
    public boolean addCashflow(String year, String month, String day, String description, String department, String paymentMethod, double amount, String cashflowType, String paymentStatus, String cashflowApproval, int cashflowId)
    {
        boolean status=false;
        if(cashflowType.equals("Liability")||cashflowType.equals("Investment"))
            department="";
        
        try{                                                                //inserting record to cash flow
            String sql = "insert into fin_cashflow(cashflow_date, cashflow_description, cashflow_department, cashflow_method, cashflow_amount, cashflow_Payment_type, cashflow_Payment_status, cashflow_approval) values('" + year + "/" + month + "/" + day + "', '" + description + "', '" + department + "', '" + paymentMethod + "' , '" + amount + "' , '" + cashflowType + "' , '" + paymentStatus + "' , '" + cashflowApproval + "' )";
            pstatement = conn.prepareStatement(sql);
            pstatement.execute();
            status = true;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return status;
    }
    
    public boolean addAssests(String year, String month, String day, String description, String department, String paymentMethod, double amount, String cashflowType, String paymentStatus, String cashflowApproval, int cashflowId, String assestsStatus)
    {
        boolean status=false;
        try{                                                                //first inserting record to cash flow
                String sql = "insert into fin_cashflow(cashflow_date, cashflow_description, cashflow_department, cashflow_method, cashflow_amount, cashflow_Payment_type, cashflow_Payment_status, cashflow_approval) values('" + year + "/" + month + "/" + day + "', '" + description + "', '" + department + "', '" + paymentMethod + "' , '" + amount + "' , '" + cashflowType + "' , '" + paymentStatus + "' , '" + cashflowApproval + "' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
                                                                                //then get cashflow id from cashflow table
                sql = "select cashflow_id from fin_cashflow order by cashflow_id desc limit 1";
                pstatement = conn.prepareStatement(sql);
                Results = pstatement.executeQuery();
                while(Results.next())
                    cashflowId= Results.getInt(1);
                                                                                //finally add expenditure id and cashflow id to expenditure table
                sql = "insert into fin_assests(assests_cashflow_id, assests_payment_status) values('" + cashflowId + "' , '" + assestsStatus + "' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
                
                status = true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        return status;
    }
    
    public boolean addLiability(String year, String month, String day, String description, String department, String paymentMethod, double amount, String cashflowType, String paymentStatus, String cashflowApproval, int cashflowId, String liabilityStatus)
    {
        boolean status=false;
        try{                                                                //first inserting record to cash flow
                String sql = "insert into fin_cashflow(cashflow_date, cashflow_description, cashflow_department, cashflow_method, cashflow_amount, cashflow_Payment_type, cashflow_Payment_status, cashflow_approval) values('" + year + "/" + month + "/" + day + "', '" + description + "', '" + department + "', '" + paymentMethod + "' , '" + amount + "' , '" + cashflowType + "' , '" + paymentStatus + "' , '" + cashflowApproval + "' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
                                                                                //then get cashflow id from cashflow table
                sql = "select cashflow_id from fin_cashflow order by cashflow_id desc limit 1";
                pstatement = conn.prepareStatement(sql);
                Results = pstatement.executeQuery();
                while(Results.next())
                    cashflowId= Results.getInt(1);
                                                                                //finally add liability id and cashflow id to liability table
                sql = "insert into fin_liability(liability_cashflow_id, liability_payment_status) values('" + cashflowId + "' , '" + liabilityStatus + "' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
                
                status = true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        return status;
    }
    
    public boolean addInvestment(String year, String month, String day, String description, String department, String paymentMethod, double amount, String cashflowType, String paymentStatus, String cashflowApproval, int cashflowId, String investmentStatus)
    {
        boolean status=false;
        try{                                                                //first inserting record to cash flow
                String sql = "insert into fin_cashflow(cashflow_date, cashflow_description, cashflow_department, cashflow_method, cashflow_amount, cashflow_Payment_type, cashflow_Payment_status, cashflow_approval) values('" + year + "/" + month + "/" + day + "', '" + description + "', '" + department + "', '" + paymentMethod + "' , '" + amount + "' , '" + cashflowType + "' , '" + paymentStatus + "' , '" + cashflowApproval + "' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
                                                                                //then get cashflow id from cashflow table
                sql = "select cashflow_id from fin_cashflow order by cashflow_id desc limit 1";
                pstatement = conn.prepareStatement(sql);
                Results = pstatement.executeQuery();
                while(Results.next())
                    cashflowId= Results.getInt(1);
                                                                                //finally add Investment id and cashflow id to Investment table
                sql = "insert into fin_investment(investment_cashflow_id, investment_payment_status) values('" + cashflowId + "' , '" + investmentStatus + "' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
                
                status = true;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        return status;
    }
    
    public void adduserlogs(String username, String actionType, String action, int actionId){
        int userId=0;
        try{
            String sql="select system_user_id from system_user where username='"+username+"'";
            pstatement=conn.prepareStatement(sql);
            Results=pstatement.executeQuery();
            
            if(Results.next())
                userId=Results.getInt("system_user_id");
            
            sql="insert into fin_userlog (system_user_id, userlog_username, userlog_action_type, userlog_action, userlog_action_id) values("+userId+",'"+username+"','"+actionType+"','"+action+"',"+actionId+")";
            pstatement=conn.prepareStatement(sql);
            pstatement.execute();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public ResultSet financeDBSearch(String searchBy, String searchValue, String Catagory) {
        
        
        switch (Catagory) {
            case "income":
                id="income_id";
                colName="Income ID";
                tableName="income";
                Catagory=Catagory+"_";
                if(searchBy.equals("id"))
                    searchBy="income_"+searchBy;
                else if(searchBy.equals("amount"))
                    searchBy="cashflow_"+searchBy;
                break;
            case "expense":
                id="expense_id";
                colName="Expense ID";
                tableName="expenditure";
                Catagory=Catagory+"_";
                if(searchBy.equals("id"))
                    searchBy="expense_"+searchBy;
                else if(searchBy.equals("amount"))
                    searchBy="cashflow_"+searchBy;
                break;
            case "assests":
                id="assests_id";
                colName="Assests ID";
                tableName="assests";
                Catagory=Catagory+"_";
                if(searchBy.equals("id"))
                    searchBy="assests_"+searchBy;
                else if(searchBy.equals("amount"))
                    searchBy="cashflow_"+searchBy;
                break;
            case "liability":
                id="liability_id";
                colName="Liability ID";
                tableName="liability";
                Catagory=Catagory+"_";
                if(searchBy.equals("id"))
                    searchBy="liability_"+searchBy;
                else if(searchBy.equals("amount"))
                    searchBy="cashflow_"+searchBy;
                break;
            case "investment":
                id="investment_id";
                colName="Investment ID";
                tableName="investment";
                Catagory=Catagory+"_";
                if(searchBy.equals("id"))
                    searchBy="investment_"+searchBy;
                else if(searchBy.equals("amount"))
                    searchBy="cashflow_"+searchBy;
                break;
            default:
                break;
        }
        
            try{
                String sql= "select distinct cashflow_id as 'Cashflow ID', "+id+" as '"+colName+"', cashflow_date as Date, cashflow_description as Description,cashflow_department as Department, cashflow_method as Method, cashflow_amount as Amount, "+Catagory+"Payment_status as Status from fin_cashflow, fin_"+tableName+" where cashflow_id="+Catagory+"cashflow_id and  CAST("+searchBy+" as CHAR) LIKE '%"+searchValue+"%'";;
                pstatement=conn.prepareStatement(sql);
                Results=pstatement.executeQuery();
               
                return Results;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(new Finance_Main("").togglePanels,"Please select Correct Search Field!","Search Error",0);
            }
        return Results;
    }

    public ResultSet cashflowDBSearch(String searchBy, String searchValue) {
        try{
                String sql= "select distinct cashflow_id as 'Cashflow ID', cashflow_date as Date, cashflow_description as Description,cashflow_department as Department, cashflow_method as Method, cashflow_amount as Amount,cashflow_payment_type as Type, cashflow_Payment_status as Status, cashflow_approval as Approval from fin_cashflow where  CAST(cashflow_"+searchBy+" as CHAR) LIKE '%"+searchValue+"%'";
                pstatement=conn.prepareStatement(sql);
                Results=pstatement.executeQuery();
               
                return Results;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(new Finance_Main("").togglePanels,"Please select Correct Search Field!","Search Error",0);
            }
        return Results;
    }
    
    public ResultSet budgetDBSearch(String searchBy, String searchValue) {
        try{
                String sql= "select budget_request_id as 'Request ID', request_budget_id as 'Budget ID', budget_request_date as Date, budget_request_department as Department, budget_request_amount as Amount, budget_request_status as Status from fin_budget_request where CAST(budget_request_"+searchBy+" as CHAR) LIKE '%"+searchValue+"%'";
                pstatement=conn.prepareStatement(sql);
                Results=pstatement.executeQuery();
               
                return Results;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(new Finance_Main("").togglePanels,"Please select Correct Search Field!","Search Error",0);
            }
        return Results;
    }
    
    public ResultSet userlogSearch(String userlogId, String actionId, String actionType, String day, String month, String year) {
        try{
                String sql= "select userlog_id as 'Userlog ID', userlog_date as Date, userlog_username as Username, userlog_action_type as 'Action Type', userlog_action as Action, userlog_action_id as 'Action ID' from fin_userlog where CAST(userlog_id as CHAR) LIKE '%"+userlogId+"%' and userlog_action_type LIKE '%"+actionType+"%' and CAST(userlog_action_id as CHAR) LIKE '%"+actionId+"%' or userlog_date like '"+year+"-"+month+"-"+day+"%'";
                pstatement=conn.prepareStatement(sql);
                Results=pstatement.executeQuery();
               
                return Results;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(new Finance_Main("").togglePanels,"Please select Correct Search Field!","Search Error",0);
            }
        return Results;
    }
   
    //----------------------------------------------------------------------------------------------------------------------------------- 
  
    public boolean financeUpdate( int cashflowId,int localId, String year, String month, String day, String description, String department, String paymentMethod, String paymentStatus, String localStatus,String tableName,String colName ){
        boolean status=false;
        try{
            if(localStatus.equals("Credit"))
                paymentStatus="Debit";
            else
                paymentStatus="Credit";

           String sql="update fin_cashflow set cashflow_date='"+year+"-"+month+"-"+day+"', cashflow_description='"+description+"', cashflow_department='"+department+"', cashflow_method='"+paymentMethod+"',cashflow_payment_status='"+paymentStatus+"' where cashflow_id="+cashflowId;
           pstatement = conn.prepareStatement(sql);
           pstatement.execute();
           sql="update fin_"+tableName+" set "+colName+"_payment_status='"+localStatus+"' where "+colName+"_id="+localId;
           pstatement = conn.prepareStatement(sql);
           pstatement.execute();
           status=true;
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(new Finance_Main("").togglePanels,"Enter Correct Values!","Input Error",0);
       }
       return status;
    }
    
    public boolean financeCashflowUpdate( int cashflowId, String year, String month, String day, String description, String department, String paymentMethod, String paymentStatus, String localStatus,String type, String tableName,String colName ){
        boolean status=false;
        try{
            if(paymentStatus.equals("Credit"))
                localStatus="Debit";
            else
                localStatus="Credit";

           String sql="update fin_cashflow, fin_"+tableName+" set cashflow_date='"+year+"-"+month+"-"+day+"', cashflow_description='"+description+"', cashflow_department='"+department+"', cashflow_method='"+paymentMethod+"', cashflow_payment_status='"+paymentStatus+"', cashflow_payment_type='"+type+"', "+colName+"_payment_status='"+localStatus+"' where cashflow_id="+colName+"_cashflow_id and cashflow_id="+cashflowId;
           pstatement = conn.prepareStatement(sql);
           pstatement.execute();
           status=true;
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(new Finance_Main("").togglePanels,"Enter Correct Values!","Input Error",0);
       }
       return status;
    }
    
    public boolean budgetUpdate(int budgetId, String year, String month, String day, String description, String department, double amount, String paymentStatus, double balance){
        boolean status=false;
        try{
            double amountDifference;
            double balanceDifference;
            String date="";
            String budgetStatus="";
            String sql="select * from fin_budget where budget_id="+budgetId;
            pstatement = conn.prepareStatement(sql);
            Results=pstatement.executeQuery();
            if(Results.next()){
                totalAmount=Results.getDouble(5);
                currentBalance=Results.getDouble(7);
                date=Results.getString(2);
                date=date.substring(0, 10);
                budgetStatus=Results.getString(6);
            }
            amountDifference= amount-totalAmount;
            balanceDifference= balance-currentBalance;
            
           if(currentBalance<balance && amountDifference==0 && day.equals("01")){
                sql="insert into fin_cashflow(cashflow_date, cashflow_description, cashflow_department, cashflow_method, cashflow_amount, cashflow_Payment_type, cashflow_Payment_status, cashflow_approval) values(CURDATE(), 'Update monthly budget', '" + department + "', 'Bank' , '" + balanceDifference + "' , 'Budget' , 'Debit' , 'Approved' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
            }
            else if(currentBalance>balance && amountDifference==0 && day.equals("01")){
                JOptionPane.showMessageDialog(new Finance_Main("").togglePanels,"You can't Decrease Balance without Decreasing Total Amount!","Update Error",0);
                return status;
            }
            else if(currentBalance>balance && totalAmount>amount && day.equals("01")){
                balanceDifference= -1*(balanceDifference);
                sql="insert into fin_cashflow(cashflow_date, cashflow_description, cashflow_department, cashflow_method, cashflow_amount, cashflow_Payment_type, cashflow_Payment_status, cashflow_approval) values(CURDATE(), 'Update monthly budget', '" + department + "', 'Bank' , '" + balanceDifference + "' , 'Budget' , 'Credit' , 'Approved' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
            }
            else if(currentBalance<balance && totalAmount<amount && day.equals("01")){
                sql="insert into fin_cashflow(cashflow_date, cashflow_description, cashflow_department, cashflow_method, cashflow_amount, cashflow_Payment_type, cashflow_Payment_status, cashflow_approval) values(CURDATE(), 'Update monthly budget', '" + department + "', 'Bank' , '" + balanceDifference + "' , 'Budget' , 'Debit' , 'Approved' )";
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
            }
            
            if(!day.equals("01")&&currentBalance!=balance){
                JOptionPane.showMessageDialog(new Finance_Main("").togglePanels,"You can't update Balance in the middle of the month!","Update Error",0);
                return status;
            }
            else if((currentBalance==balance) && (totalAmount==amount) && (date.equals(""+year+"-"+month+"-"+day+"")) && (budgetStatus.equals(paymentStatus))){
                JOptionPane.showMessageDialog(new Finance_Main("").togglePanels,"No Changes Detected!","Update Failed",0);
                return status;
            }
            else{
                sql="update fin_budget set budget_date='"+year+"-"+month+"-"+day+"', budget_description='"+description+"', budget_department='"+department+"', budget_amount="+amount+", budget_payment_status='"+paymentStatus+"', budget_balance="+balance+" where budget_id= "+budgetId;
                pstatement = conn.prepareStatement(sql);
                pstatement.execute();
            }
           status=true;
       }
       catch(Exception e){
           JOptionPane.showMessageDialog(new Finance_Main("").togglePanels,"Enter Correct Values!","Input Error",0);
       }
       return status;
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public double balanceChecking(String department, String payType, double paymentAmount, String month, String year){
        try{
            String sql="select * from fin_budget where budget_department='"+department+"'";
            pstatement=conn.prepareStatement(sql);
            Results=pstatement.executeQuery();
            
            if(Results.next()){
                totalAmount=Results.getDouble(5);
            }
            
            sql="select sum(cashflow_amount) from fin_cashflow where cashflow_department='"+department+"' and cashflow_payment_type='"+payType+"' and cashflow_date BETWEEN '"+year+"-"+month+"-01' and '"+year+"-"+month+"-31'";
            pstatement=conn.prepareStatement(sql);
            Results=pstatement.executeQuery();
            
            if(Results.next()){
                totalExpense=Results.getDouble(1);
            }
            currentBalance= totalAmount - totalExpense;
            latestBalence= currentBalance-paymentAmount;
            if(paymentAmount>currentBalance){
                JOptionPane.showMessageDialog(null,"Your Credit Limit Exceeded! Please Request for Additional Budget!","Payment can't proceed!",0);
            }
            else{
                try{
                    sql="update fin_budget set budget_balance="+latestBalence+" where budget_department='"+department+"'";
                    pstatement=conn.prepareStatement(sql);
                    pstatement.execute();
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
            }
        }
        catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
        }
        return currentBalance;
    }
    
    public void budgetRequest(String department,double amount){
        try{
            int id = 0;
            String sql= "select * from fin_budget where budget_department='"+department+"'";
            pstatement=conn.prepareStatement(sql);
            Results=pstatement.executeQuery();
            if(Results.next())
               id=Results.getInt(1);
            sql="insert into fin_budget_request(request_budget_id, budget_request_department, budget_request_amount) values("+id+",'"+department+"',"+amount+")";
            pstatement=conn.prepareStatement(sql);
            pstatement.execute();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void acceptRequesst(int budgetRequestId, int budgetId, double amount){
        try{
            double budgetBalance=0;
            double budgetAmount=0;
            String department="";
            String sql="update fin_budget_request set budget_request_status='Accepted' where budget_request_id="+budgetRequestId;
            pstatement=conn.prepareStatement(sql);
            pstatement.execute();
            sql= "select * from fin_budget where budget_id="+budgetId;
            pstatement=conn.prepareStatement(sql);
            Results=pstatement.executeQuery();
                if(Results.next()){
                    budgetBalance=Results.getDouble(7);
                    budgetAmount=Results.getDouble(5);
                    department=Results.getString(4);
                }
                   budgetAmount=budgetAmount+amount;
                   budgetBalance=budgetBalance+amount;
            sql="update fin_budget set budget_balance="+budgetBalance+", budget_amount="+budgetAmount+" where budget_id="+budgetId;
            pstatement=conn.prepareStatement(sql);
            pstatement.execute();
            
            sql="insert into fin_cashflow(cashflow_date, cashflow_description, cashflow_department, cashflow_method, cashflow_amount, cashflow_Payment_type, cashflow_Payment_status, cashflow_approval) values(CURDATE(), 'Add additional budget', '" + department + "', 'Bank' , '" + amount + "' , 'Budget' , 'Credit' , 'Approved' )";
            pstatement=conn.prepareStatement(sql);
            pstatement.execute();
        }
        catch(Exception e){
            JOptionPane.showConfirmDialog(new Finance_Main("").togglePanels,e);
        }
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    
    public int getlastid(String tablename, String colname){
        int id=0;
        try{
            String sql="select "+colname+" from "+tablename+" order by "+colname+" desc limit 1";
            pstatement=conn.prepareStatement(sql);
            Results=pstatement.executeQuery();
            
            if(Results.next())
               id= Results.getInt(1);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(new Finance_Main("").togglePanels,e);
        }
        return id;
    }
}
