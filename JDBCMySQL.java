import java.sql.*;
import java.util.Scanner;

/**
 * Java Course 3, Module 3
 * 
 * Hands-on Assignment 3: MySQL JDBC Connectivity (Graded)
 *
 * @author Jhumel M. Bonganciso
 */
public class JDBCMySQL {
    public static void main(String[] args) {
    // Scanner open
        Scanner s = new Scanner(System.in);

        // Loop checker boolean
        boolean exit = false;
        try (
                // database 'Connection' object called 'conn'
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/mysqljdbc", "root", null);
                // Statement Construction
                Statement stmt = conn.createStatement();) {
            // SQL QUERY
            String qry = "SELECT * FROM `user_policies`";
            String case2 = "select * from user_policies where date_registered < '2012-01-01'";

            // Result Set
            ResultSet rset;
             
            // Do while loop for asking of user input
            do {
                System.out.println("\nData Retrieval");
                System.out.println("\n1) Retrieve all the user policy details");
                System.out.println("2) Retrieve user policies where registered date is less than January 1, 2012");
                System.out.println("3) Exit");
                System.out.print("Please enter 1, 2, or 3 ==>  ");
                String choice = s.nextLine();

                // Switch case for user input
                switch (choice) {
                    case "1": {
                        // SQL query execution
                        rset = stmt.executeQuery(qry);
                        ResultSetMetaData meta = rset.getMetaData();
                        String col1Name = meta.getColumnLabel(1).toUpperCase();
                        String col2Name = meta.getColumnLabel(2).toUpperCase();
                        String col3Name = meta.getColumnLabel(3).toUpperCase();
                        String col4Name = meta.getColumnLabel(4).toUpperCase();
                        System.out.println(
                                "\nAll User Policy Details\n" +col1Name +"\t"+ col2Name+"\t\t"+col3Name+"\t\t"+col4Name);

                        // While loop for result
                        // rset.next() inside the whole-loop repeatedly moves the cursor to the next row.
                        while (rset.next()) {
                            int polNum = rset.getInt("policy_no"); //Retrieve a int in the row
                            String userId = rset.getString("user_id"); //Retrieve a String in the row
                            String date_registered = rset.getString("date_registered"); //Retrieve a String in the row
                            String policy_type_id = rset.getString("policy_type_id"); //Retrieve a String in the row
                            // Printing of result
                            System.out.println("\n" + polNum + "\t\t " + userId + "\t\t " + date_registered
                                    + "\t\t     " + policy_type_id);
                        }
                        
                        break;
                    }
                    case "2": {
                        
                        System.out.println(
                                "\nUser policies where date is less than January 1, 2012");

                                // Query execution
                        rset = stmt.executeQuery(case2);

                        ResultSetMetaData meta = rset.getMetaData();
                        String col1Name = meta.getColumnLabel(1).toUpperCase();
                        String col2Name = meta.getColumnLabel(2).toUpperCase();
                        String col3Name = meta.getColumnLabel(3).toUpperCase();
                        String col4Name = meta.getColumnLabel(4).toUpperCase();
                        System.out.println(
                                "\nAll User Policy Details\n" +col1Name +"\t"+ col2Name+"\t\t"+col3Name+"\t\t"+col4Name);
                        // While loop for result
                        // rset.next() inside the whole-loop repeatedly moves the cursor to the next row.
                        while (rset.next()) {
                            int polNum = rset.getInt("policy_no"); //Retrieve a int in the row
                            String userId = rset.getString("user_id"); //Retrieve a String in the row
                            String date_registered = rset.getString("date_registered"); //Retrieve a String in the row
                            String policy_type_id = rset.getString("policy_type_id"); //Retrieve a String in the row

                            // Printing of result
                            System.out.println("\n" + polNum + "\t\t " + userId + "\t\t " + date_registered
                                    + "\t\t     " + policy_type_id);
                        }

                        System.out.println("\nEnd of result\n");
                        break;
                    }

                    // Exit
                    case "3": {

                        exit = true;

                    }
                   
                }
            } while (!exit);
        }

        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            ;
        }

        // Scanner Close
        s.close();
    }
}