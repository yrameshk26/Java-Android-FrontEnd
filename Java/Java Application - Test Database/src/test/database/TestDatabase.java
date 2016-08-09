
package test.database;
  
    // Use the JDBC driver  
    import java.sql.*;  

public class TestDatabase {
// Connect to your database.  
            // Replace server name, username, and password with your credentials  
    public static void main(String[] args) {
       String connectionString =  
                    "jdbc:sqlserver://testingazure.database.windows.net:1433;"  
                    + "database=Testing;"  
                    + "user=testingazure@testingazure;"  
                    + "password=abc123@@@;"  
                    + "encrypt=true;"  
                    + "trustServerCertificate=false;"  
                    + "hostNameInCertificate=*.database.windows.net;"  
                    + "loginTimeout=30;"; 
              
                // Declare the JDBC objects.  
                Connection connection = null; 
                Statement statement = null;   
                ResultSet resultSet = null; 
                PreparedStatement prepsInsertProduct = null;
                              
                try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String insertSql = "INSERT INTO StockItems (ItemName,ItemCategory,ItemPrice,ItemWeight,Distributor,ItemManDate,ItemExpiryDate,UserName) VALUES ('Cookies','Dry Foods','2.75$','700g','Oreo Dist.','2016-02-10','2017-04-27','Ramesh')";  
                    prepsInsertProduct = connection.prepareStatement(  
                        insertSql,  
                        Statement.RETURN_GENERATED_KEYS);  
                    prepsInsertProduct.execute(); 
                    resultSet = prepsInsertProduct.getGeneratedKeys();
                      
                    while (resultSet.next())   
                    {  
                        
                        System.out.println("Generated: " + resultSet.getString(1));  
                    }  
                }  
                catch (Exception e) {  
                    e.printStackTrace();  
                }  
                finally {  
                    // Close the connections after the data has been handled.  
                    if (prepsInsertProduct != null) try { prepsInsertProduct.close(); } catch(Exception e) {}  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
                }  
            }  
    
}
