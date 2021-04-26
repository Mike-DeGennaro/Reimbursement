package doa;

import Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import frontcontroller.FrontController;
import org.apache.log4j.Logger;



public class Jdbc {
    //connects us to db
    public static Logger loggy = Logger.getLogger(FrontController.class);
    public static String url = "jdbc:postgresql://firstdatabase.ctxycf2tpkhe.us-east-2.rds.amazonaws.com/mydatabase";
    public static String username = "mike";
    public static String password = "p4ssw0rd";




    // searches for account to log into
    public static Account getInfo(String name, String pass) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM userss WHERE username='" + name + "' AND user_pass='" + pass + "' ");

            //gathers requited information, bundles it into an object, and sends it back to api into javascript
            if (rs.next() == true) {
                String tempName=rs.getString("username");
                String tempPass=rs.getString("user_pass");
                String tempFName=rs.getString("first_name");
                String tempLName=rs.getString("last_name");
                String tempEmail=rs.getString("email");
                int tempRole=rs.getInt("role_id");
                int tempID=rs.getInt("user_id");
                System.out.println(tempName);
                Account temp = new Account(tempName, tempPass, tempFName, tempLName, tempEmail, tempRole, tempID);
                loggy.info("JDBC getting account info");

                return temp;
            }
        } catch (SQLException throwables) {
            loggy.info("JDBC getting account info failed");
            throwables.printStackTrace();
        }
        return null;
    }

    //gets corresponding tickets to a specific account
    public static Object getTickets(Account account, int id){

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement stmt = conn.createStatement();
            String getTicket = "";
            //join on reimbursement, reimbursement status, and reimbursement type tables IF the account
            //is a mere employee account, finds tickets corresponding to that employee
            if(account.getRole()==1){
                getTicket="SELECT * FROM reimbursement r ,\n" +
                        "  reimbursement_type rt  ,\n" +
                        "  reimbursement_status rs \n" +
                        "  WHERE rt.reimb_type_id = r.reimb_type_id \n" +
                        "   AND r.reimb_status_id  = rs.reimb_status_id\n" +
                        "AND r.reimb_author_id ='" + id + "'";
            }else{
                //if account we found is an admin account, we simply get all tickets available
                getTicket = "SELECT * FROM reimbursement r , \n" +
                        "    reimbursement_type rt  ,\n" +
                        "    reimbursement_status rs \n" +
                        "    WHERE rt.reimb_type_id = r.reimb_type_id \n" +
                        "    AND r.reimb_status_id  = rs.reimb_status_id";
            }
            loggy.info("JDBC finding tickets");

            ResultSet rs = stmt.executeQuery(getTicket);

            //gathers required information and creates a new ticket object with all that info, pushes it
            //into an array to hold all the tickets and send it back to the user to display on front end
            while(rs.next() == true) {
                int tempID=rs.getInt("reimb_author_id");
                int tempStatusID=rs.getInt("reimb_status_id");
                int tempTicketID=rs.getInt("reimb_id");
                int tempAmount=rs.getInt("reimb_amount");
                String tempText=rs.getString("reimb_message");
                int tempStatus=rs.getInt("status");
                int tempType=rs.getInt("reimb_type");
                Date tempDate=rs.getDate("time_submitted");
                String tempDate2=tempDate.toString();
                String tempResDate=rs.getString("time_resolved");

                Ticket ticket = new Ticket(tempID, tempAmount, tempText, tempStatus, tempType, tempDate2, tempTicketID, tempStatusID, tempResDate);
                    account.ticketList.add(ticket);
                }
            return rs;
        } catch (SQLException throwables) {
            loggy.info("JDBC getting tickets failed");
            throwables.printStackTrace();
        }
        return null;
    }

    //adds a ticket
    public static Ticket addTicket(int id, int amount, String message, int type){
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            loggy.info("JDBC adding tickets");

            String sql="INSERT INTO reimbursement(reimb_id, reimb_amount, time_submitted, time_resolved, reimb_message, reimb_reciept, reimb_author_id, reimb_status_id, reimb_type_id) VALUES(?,?,?,?,?,?,?,?,?)";

            Random rand = new Random();
            //ids for ticket, status, and type
            int x = rand.nextInt(100000);
            int y = rand.nextInt(100000);
            int z = rand.nextInt(100000);

            addTicketStatus(y, 1);
            addTicketType(z, type);
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, x);
            statement.setInt(2, amount);
            statement.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            statement.setDate(4, null);
            statement.setString(5, message);
            statement.setString(6, null);
            statement.setInt(7, id);
            statement.setInt(8, y);
            statement.setInt(9, z);

            statement.executeUpdate();

            Ticket ticket = new Ticket(x, amount, message, 1, z, java.sql.Date.valueOf(java.time.LocalDate.now()).toString(), x, y, null);
        } catch (SQLException throwables) {
            loggy.info("JDBC adding ticket failed");
            throwables.printStackTrace();
        }
        return null;
    }

    //adds ticket type, ties together with addTicket method, adds type for that specific ticket
    public static boolean addTicketType(int id, int type){
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql="INSERT INTO reimbursement_type VALUES(?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setInt(2, type);

            statement.executeUpdate();
            loggy.info("JDBC adding ticket type");
            return true;

            //make it return result sets and create another service layer dealing with whatever returned :D
        } catch (SQLException throwables) {
            loggy.info("JDBC adding ticket type failed");
            throwables.printStackTrace();
        }
        return false;
    }

    //adds ticket status, ties together with addTicket method, adds status for that specific ticket
    public static boolean addTicketStatus(int id, int status){
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            loggy.info("JDBC adding ticket status");

            String sql="INSERT INTO reimbursement_status VALUES(?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            statement.setInt(2, status);

            statement.executeUpdate();
            return true;
            //make it return result sets and create another service layer dealing with whatever returned :D
        } catch (SQLException throwables) {
            loggy.info("JDBC addding ticket status failed");
            throwables.printStackTrace();
        }
        return false;
    }

    //updates a ticket status and time resolved
    public static void updateTicket(int id, int status, int resolvedBy){
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            loggy.info("JDBC updating ticket");
            String sql="UPDATE reimbursement_status SET status='" + status + "' WHERE reimb_status_id='" + id + "'";
            String sql2="UPDATE reimbursement SET time_resolved='" + java.sql.Date.valueOf(java.time.LocalDate.now()) + "', reimb_resolver_id='" + resolvedBy + "' WHERE reimb_status_id='" + id + "'";
            PreparedStatement statement = conn.prepareStatement(sql);
            PreparedStatement statement2 = conn.prepareStatement(sql2);

            statement.executeUpdate();
            statement2.executeUpdate();

            //make it return result sets and create another service layer dealing with whatever returned :D
        } catch (SQLException throwables) {
            loggy.info("JDBC updating ticket failed");
            throwables.printStackTrace();
        }
    }


}
