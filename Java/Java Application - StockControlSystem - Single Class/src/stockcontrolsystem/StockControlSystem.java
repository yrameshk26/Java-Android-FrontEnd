/*
 * Trying to make a Stock Control System Desktop Application
*/

package stockcontrolsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;  

public class StockControlSystem extends javax.swing.JFrame  {
    static String AddStock = "Add A New Item To The Stock";
    static String DisplayStock = "Search and Display an Item";
    static String DeleteStock = "Remove an Item From The Stock";
    static String ModifyStock = "Edit an Item Details";
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
                PreparedStatement prepsDeleteProduct = null;
                PreparedStatement prepsUpdateProduct = null;
    
    public void addComponentToPane(Container pane) {
        JTabbedPane tabbedPane = new JTabbedPane();
 
        JPanel addStkPanel = new JPanel() {
            public Dimension getPreferredSize() {
                return new Dimension(880, 400);
            }
        };
        addStkPanel.setLayout(new BoxLayout(addStkPanel, BoxLayout.PAGE_AXIS));
        addStkPanel.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        
        JPanel addStkPanelTitle = new JPanel();
        JPanel addStkPanelget = new JPanel(new GridLayout(9,2,15,15));
        JPanel addStkPanelButton = new JPanel();
        
        addStkPanel.add(addStkPanelTitle);
        addStkPanel.add(addStkPanelget);
        addStkPanel.add(addStkPanelButton);
        
       JLabel ItmNo = new JLabel("Item No:");
       JLabel ItmNme = new JLabel("Item Name:");
       JLabel ItmCat = new JLabel("Item Category:");
       JLabel ItmPrc = new JLabel("Item Price:");
       JLabel ItmWgt = new JLabel("Item Weight:");
       JLabel Dstrbtr = new JLabel("Distributor:");
       JLabel MnftcrngDte = new JLabel("Item Manufacturing Date:");
       JLabel ExprDte = new JLabel("Item Expiry Date:");
       JLabel Usr = new JLabel("User:");
       final JTextField ItmNoTxt = new JTextField(20);
       final JTextField ItmNmeTxt = new JTextField(20);
       final JTextField ItmCatTxt = new JTextField(20);
       final JTextField ItmPrcTxt = new JTextField(20);
       final JTextField ItmWgtTxt = new JTextField(20);
       final JTextField DstrbtrTxt = new JTextField(20);
       final JTextField MnfctrTxt = new JTextField(20);
       final JTextField ExpryDteTxt = new JTextField(20);
       final JTextField UsrTxt = new JTextField(20);
       JButton Submit = new JButton("Submit");
       JLabel AddTitle = new JLabel("<HTML><U>Please Enter The Item Details Below</U></HTML>");
       

       Font font = ItmNo.getFont();
       
       AddTitle.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 20f));
       ItmNo.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 15f));
       ItmNoTxt.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 14f));
       ItmNme.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 15f));
       ItmNmeTxt.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 14f));
       ItmCat.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 15f));
       ItmCatTxt.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 14f));
       ItmPrc.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 15f));
       ItmPrcTxt.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 14f));
       ItmWgt.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 15f));
       ItmWgtTxt.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 14f));
       Dstrbtr.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 15f));
       DstrbtrTxt.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 14f));
       MnftcrngDte.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 15f));
       MnfctrTxt.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 14f));
       ExprDte.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 15f));
       ExpryDteTxt.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 14f));
       Usr.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 15f));
       UsrTxt.setFont(font.deriveFont(Font.BOLD|Font.ITALIC, 14f));
       
        addStkPanelTitle.add(AddTitle);
        addStkPanelget.add(ItmNo);
        addStkPanelget.add(ItmNoTxt);
        addStkPanelget.add(ItmNme);
        addStkPanelget.add(ItmNmeTxt);
        addStkPanelget.add(ItmCat);
        addStkPanelget.add(ItmCatTxt);
        addStkPanelget.add(ItmPrc);
        addStkPanelget.add(ItmPrcTxt);
        addStkPanelget.add(ItmWgt);
        addStkPanelget.add(ItmWgtTxt);
        addStkPanelget.add(Dstrbtr);
        addStkPanelget.add(DstrbtrTxt);
        addStkPanelget.add(MnftcrngDte);
        addStkPanelget.add(MnfctrTxt);
        addStkPanelget.add(ExprDte);
        addStkPanelget.add(ExpryDteTxt);
        addStkPanelget.add(Usr);
        addStkPanelget.add(UsrTxt);
        addStkPanelButton.add(Submit);   
        
        addStkPanel.addMouseMotionListener(new MouseMotionListener()
        {
            public void mouseMoved(MouseEvent e) {
               try {  
                    connection = DriverManager.getConnection(connectionString);  
      
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT TOP 1 ItemID FROM StockItems ORDER BY ItemID DESC";
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
 
                    while (resultSet.next())   
                    {  
                        ItmNoTxt.setText(Integer.toString(Integer.parseInt(resultSet.getString(1))+1));  
                        ItmNoTxt.setEnabled(false);
                    }  
                }  
                catch (Exception ex) {  
                    ex.printStackTrace();  
                }  
                finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception ex) {}  
                    if (statement != null) try { statement.close(); } catch(Exception ex) {}  
                    if (connection != null) try { connection.close(); } catch(Exception ex) {}  
                }
            }
            public void mouseDragged(MouseEvent a)
            {}
        });
                
        
        
        Submit.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String insertSql = "INSERT INTO StockItems (ItemID,ItemName,ItemCategory,ItemPrice,ItemWeight,Distributor,ItemManDate,ItemExpiryDate,UserName) VALUES ('" + ItmNoTxt.getText() + "','" + ItmNmeTxt.getText() + "','" + ItmCatTxt.getText() + "','" + ItmPrcTxt.getText() + "','" + ItmWgtTxt.getText() + "','" + DstrbtrTxt.getText() + "','" + MnfctrTxt.getText() + "','" + ExpryDteTxt.getText() + "','" + UsrTxt.getText() + "')";  
                    prepsInsertProduct = connection.prepareStatement(  
                        insertSql,  
                        Statement.RETURN_GENERATED_KEYS);  
                    prepsInsertProduct.execute(); 
                    resultSet = prepsInsertProduct.getGeneratedKeys();
                      
                    while (resultSet.next())   
                    {  
                        JOptionPane.showMessageDialog(null,"Inserted Successfully!"); 
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
            
            
            try {  
                    connection = DriverManager.getConnection(connectionString);  
      
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT TOP 1 ItemID FROM StockItems ORDER BY ItemID DESC";
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
 
                    while (resultSet.next())   
                    {  
                        ItmNoTxt.setText(Integer.toString(Integer.parseInt(resultSet.getString(1))+1));  
                        ItmNoTxt.setEnabled(false);
                    }  
                }  
                catch (Exception e) {  
                    e.printStackTrace();  
                }  
                finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
                }
            
       ItmNmeTxt.setText("");
       ItmCatTxt.setText("");
       ItmPrcTxt.setText("");
       ItmWgtTxt.setText("");
       DstrbtrTxt.setText("");
       MnfctrTxt.setText("");
       ExpryDteTxt.setText("");
       UsrTxt.setText("");
            
        }
        });
       
        
        JPanel dsplyStkPanel = new JPanel();
        dsplyStkPanel.setLayout(new BoxLayout(dsplyStkPanel, BoxLayout.PAGE_AXIS));
        
        JPanel displayTitlePanel = new JPanel();
        JPanel displayGetPanel = new JPanel(new GridLayout(5,3,15,15));
        JPanel displayShowPanel = new JPanel(new BorderLayout());
        
        dsplyStkPanel.add(displayTitlePanel);
        dsplyStkPanel.add(displayGetPanel);
        dsplyStkPanel.add(displayShowPanel);
        
        final DefaultListModel ItemNoList = new DefaultListModel();

        
        JButton NoSearch = new JButton("Search");
        JButton NmeSearch = new JButton("Search");
        JButton CatSearch = new JButton("Search");
        JButton DistSearch = new JButton("Search");
        JLabel DispTitle = new JLabel("Search And Display The Item");
        JLabel searchNo = new JLabel("Search By Item Number:");
        JLabel searchNme = new JLabel("Search By Item Name:");
        JLabel searchCat = new JLabel("Search By Item Category:");
        JLabel searchDist = new JLabel("Search By Item Distributor:");
        final JTextField SearchNoEnter = new JTextField(20);
        final JTextField SearchNmeEnter = new JTextField(20);
        final JTextField SearchCatEnter = new JTextField(20);
        final JTextField SearchDistEnter = new JTextField(20);
        JList SearchList = new JList(ItemNoList);
        JScrollPane Searchpane = new JScrollPane(SearchList);
        DispTitle.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 20f));
        searchNo.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 15f));
        searchNme.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 15f));
        searchCat.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 15f));
        searchDist.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 15f));
        
        displayTitlePanel.add(DispTitle);
        displayGetPanel.add(searchNo);
        displayGetPanel.add(SearchNoEnter);
        displayGetPanel.add(NoSearch);
        displayGetPanel.add(searchNme);
        displayGetPanel.add(SearchNmeEnter);
        displayGetPanel.add(NmeSearch);
        displayGetPanel.add(searchCat);
        displayGetPanel.add(SearchCatEnter);
        displayGetPanel.add(CatSearch);
        displayGetPanel.add(searchDist);
        displayGetPanel.add(SearchDistEnter);
        displayGetPanel.add(DistSearch);
        displayShowPanel.add(Searchpane);
        
        dsplyStkPanel.addMouseListener(new MouseListener()
        {
            public void mouseClicked(MouseEvent ev) {
               try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems";  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    ItemNoList.clear();
                    ItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        ItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    }  
            } 
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            }
            public void mouseExit(MouseEvent a){}
            
            public void mousePressed(MouseEvent e) {}
            
            public void mouseReleased(MouseEvent e) {}

            public void mouseEntered(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}
        });
        
        
        NoSearch.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems WHERE ItemID = "+SearchNoEnter.getText();  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    ItemNoList.clear();
                    ItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        ItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    }  
            } 
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            SearchNoEnter.setText("");
            SearchNmeEnter.setText("");
            SearchCatEnter.setText("");
            SearchDistEnter.setText("");
        }
        });
        
        
        NmeSearch.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems WHERE ItemName LIKE'%"+SearchNmeEnter.getText()+"%'";  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    ItemNoList.clear();
                    ItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        ItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    }  
            }              
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            SearchNoEnter.setText("");
            SearchNmeEnter.setText("");
            SearchCatEnter.setText("");
            SearchDistEnter.setText("");
            
        }
        });
        
        CatSearch.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems WHERE ItemCategory LIKE'%"+SearchCatEnter.getText()+"%'";  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    ItemNoList.clear();
                    ItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        ItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    } 
            }              
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            SearchNoEnter.setText("");
            SearchNmeEnter.setText("");
            SearchCatEnter.setText("");
            SearchDistEnter.setText("");
            
        }
        });
        
        DistSearch.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems WHERE Distributor LIKE'%"+SearchDistEnter.getText()+"%'";  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    ItemNoList.clear();
                    ItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        ItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    }  
            }              
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            SearchNoEnter.setText("");
            SearchNmeEnter.setText("");
            SearchCatEnter.setText("");
            SearchDistEnter.setText("");
            
        }
        });
        
        JPanel dltStkPanel = new JPanel();
        
        dltStkPanel.setLayout(new BoxLayout(dltStkPanel, BoxLayout.PAGE_AXIS));
        
        JPanel dltTitlePanel = new JPanel();
        JPanel dltGetPanel = new JPanel(new GridLayout(5,3,15,15));
        JPanel dltShowPanel = new JPanel(new BorderLayout());
        JPanel dltBtnPanel = new JPanel();
        
        dltStkPanel.add(dltTitlePanel);
        dltStkPanel.add(dltGetPanel);
        dltStkPanel.add(dltShowPanel);
        dltStkPanel.add(dltBtnPanel);
        
        final DefaultListModel DelItemNoList = new DefaultListModel();

        
        JButton DelNoSearch = new JButton("Search");
        JButton DelNmeSearch = new JButton("Search");
        JButton DelCatSearch = new JButton("Search");
        JButton DelDistSearch = new JButton("Search");
        JLabel DelTitle = new JLabel("Search And Delete The Item On The List");
        JLabel DelsearchNo = new JLabel("Search By Item Number:");
        JLabel DelsearchNme = new JLabel("Search By Item Name:");
        JLabel DelsearchCat = new JLabel("Search By Item Category:");
        JLabel DelsearchDist = new JLabel("Search By Item Distributor:");
        final JTextField DelSearchNoEnter = new JTextField(20);
        final JTextField DelSearchNmeEnter = new JTextField(20);
        final JTextField DelSearchCatEnter = new JTextField(20);
        final JTextField DelSearchDistEnter = new JTextField(20);
        final JList DelSearchList = new JList(DelItemNoList);
        JScrollPane DelSearchpane = new JScrollPane(DelSearchList);
        JButton DeleteRecord = new JButton("Delete Record");
        DelTitle.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 20f));
        DelsearchNo.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 15f));
        DelsearchNme.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 15f));
        DelsearchCat.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 15f));
        DelsearchDist.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 15f));
        
        dltTitlePanel.add(DelTitle);
        dltGetPanel.add(DelsearchNo);
        dltGetPanel.add(DelSearchNoEnter);
        dltGetPanel.add(DelNoSearch);
        dltGetPanel.add(DelsearchNme);
        dltGetPanel.add(DelSearchNmeEnter);
        dltGetPanel.add(DelNmeSearch);
        dltGetPanel.add(DelsearchCat);
        dltGetPanel.add(DelSearchCatEnter);
        dltGetPanel.add(DelCatSearch);
        dltGetPanel.add(DelsearchDist);
        dltGetPanel.add(DelSearchDistEnter);
        dltGetPanel.add(DelDistSearch);
        dltShowPanel.add(DelSearchpane);
        dltBtnPanel.add(DeleteRecord);
        
        DelNoSearch.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems WHERE ItemID ="+DelSearchNoEnter.getText();  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    DelItemNoList.clear();
                    DelItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        DelItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    }  
            } 
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            DelSearchNoEnter.setText("");
            DelSearchNmeEnter.setText("");
            DelSearchCatEnter.setText("");
            DelSearchDistEnter.setText("");
        }
        });
        
        
        DelNmeSearch.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems WHERE ItemName LIKE'%"+DelSearchNmeEnter.getText()+"%'";  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    DelItemNoList.clear();
                    DelItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        DelItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    }  
            }              
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            DelSearchNoEnter.setText("");
            DelSearchNmeEnter.setText("");
            DelSearchCatEnter.setText("");
            DelSearchDistEnter.setText("");
            
        }
        });
        
        DelCatSearch.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems WHERE ItemCategory LIKE'%"+DelSearchCatEnter.getText()+"%'";  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    DelItemNoList.clear();
                    DelItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        DelItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    } 
            }              
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            DelSearchNoEnter.setText("");
            DelSearchNmeEnter.setText("");
            DelSearchCatEnter.setText("");
            DelSearchDistEnter.setText("");
            
        }
        });
        
        DelDistSearch.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems WHERE Distributor LIKE'%"+DelSearchDistEnter.getText()+"%'";  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    DelItemNoList.clear();
                    DelItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        DelItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    }  
            }              
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            DelSearchNoEnter.setText("");
            DelSearchNmeEnter.setText("");
            DelSearchCatEnter.setText("");
            DelSearchDistEnter.setText("");
            
        }
        });
        
        DeleteRecord.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    
                    String Selected = (String) DelSearchList.getSelectedValue();
                    
                    String DeleteSql = "DELETE FROM StockItems Where ItemID =" + Selected.substring(0, 6);  
                    prepsDeleteProduct = connection.prepareStatement(  
                        DeleteSql,  
                        Statement.RETURN_GENERATED_KEYS);  
                    prepsDeleteProduct.execute();  
                    resultSet = prepsDeleteProduct.getGeneratedKeys();  
                    while (resultSet.next()) {  
                        JOptionPane.showMessageDialog(null,"Deleted The Record " + Selected.substring(0, 6) +" Successfully.");  
                    } 
                    DelItemNoList.clear();
                }  
                catch (Exception e) {  
                    e.printStackTrace();  
                }  
                finally {  
                    // Close the connections after the data has been handled.  
                    if (prepsDeleteProduct != null) try { prepsDeleteProduct.close(); } catch(Exception e) {}  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
                } 
            DelSearchNoEnter.setText("");
            DelSearchNmeEnter.setText("");
            DelSearchCatEnter.setText("");
            DelSearchDistEnter.setText("");
            
        }
        });
        
        JPanel mdfyStkPanel = new JPanel();
        
        mdfyStkPanel.setLayout(new BoxLayout(mdfyStkPanel, BoxLayout.PAGE_AXIS));
        
        JPanel mdfyTitlePanel = new JPanel();
        JPanel mdfyGetPanel = new JPanel(new GridLayout(5,3,15,15));
        JPanel mdfyShowPanel = new JPanel(new BorderLayout());
        JPanel mdfyBtnPanel = new JPanel();
        
        mdfyStkPanel.add(mdfyTitlePanel);
        mdfyStkPanel.add(mdfyGetPanel);
        mdfyStkPanel.add(mdfyShowPanel);
        mdfyStkPanel.add(mdfyBtnPanel);
        
        final DefaultListModel mdfyItemNoList = new DefaultListModel();

        
        JButton mdfyNoSearch = new JButton("Search");
        JButton mdfyNmeSearch = new JButton("Search");
        JButton mdfyCatSearch = new JButton("Search");
        JButton mdfyDistSearch = new JButton("Search");
        JLabel mdfyTitle = new JLabel("Search And Edit The Item On The List");
        JLabel mdfysearchNo = new JLabel("Search By Item Number:");
        JLabel mdfysearchNme = new JLabel("Search By Item Name:");
        JLabel mdfysearchCat = new JLabel("Search By Item Category:");
        JLabel mdfysearchDist = new JLabel("Search By Item Distributor:");
        final JTextField mdfySearchNoEnter = new JTextField(20);
        final JTextField mdfySearchNmeEnter = new JTextField(20);
        final JTextField mdfySearchCatEnter = new JTextField(20);
        final JTextField mdfySearchDistEnter = new JTextField(20);
        final JList mdfySearchList = new JList(mdfyItemNoList);
        JScrollPane mdfySearchpane = new JScrollPane(mdfySearchList);
        JButton updateRecord = new JButton("Update Record");
        mdfyTitle.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 20f));
        mdfysearchNo.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 15f));
        mdfysearchNme.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 15f));
        mdfysearchCat.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 15f));
        mdfysearchDist.setFont(font.deriveFont(Font.BOLD|Font.PLAIN, 15f));
        
        mdfyTitlePanel.add(mdfyTitle);
        mdfyGetPanel.add(mdfysearchNo);
        mdfyGetPanel.add(mdfySearchNoEnter);
        mdfyGetPanel.add(mdfyNoSearch);
        mdfyGetPanel.add(mdfysearchNme);
        mdfyGetPanel.add(mdfySearchNmeEnter);
        mdfyGetPanel.add(mdfyNmeSearch);
        mdfyGetPanel.add(mdfysearchCat);
        mdfyGetPanel.add(mdfySearchCatEnter);
        mdfyGetPanel.add(mdfyCatSearch);
        mdfyGetPanel.add(mdfysearchDist);
        mdfyGetPanel.add(mdfySearchDistEnter);
        mdfyGetPanel.add(mdfyDistSearch);
        mdfyShowPanel.add(mdfySearchpane);
        mdfyBtnPanel.add(updateRecord);
        
        mdfyNoSearch.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems WHERE ItemID ="+mdfySearchNoEnter.getText();  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    mdfyItemNoList.clear();
                    mdfyItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        mdfyItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    }  
            } 
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            mdfySearchNoEnter.setText("");
            mdfySearchNmeEnter.setText("");
            mdfySearchCatEnter.setText("");
            mdfySearchDistEnter.setText("");
        }
        });
        
        
        mdfyNmeSearch.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems WHERE ItemName LIKE'%"+mdfySearchNmeEnter.getText()+"%'";  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    mdfyItemNoList.clear();
                    mdfyItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        mdfyItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    }  
            }              
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            mdfySearchNoEnter.setText("");
            mdfySearchNmeEnter.setText("");
            mdfySearchCatEnter.setText("");
            mdfySearchDistEnter.setText("");
            
        }
        });
        
        mdfyCatSearch.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems WHERE ItemCategory LIKE'%"+DelSearchCatEnter.getText()+"%'";  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    mdfyItemNoList.clear();
                    mdfyItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        mdfyItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    } 
            }              
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            mdfySearchNoEnter.setText("");
            mdfySearchNmeEnter.setText("");
            mdfySearchCatEnter.setText("");
            mdfySearchDistEnter.setText("");
            
        }
        });
        
        mdfyDistSearch.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
            try {  
                    connection = DriverManager.getConnection(connectionString);  
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "SELECT ItemID,ItemName,ItemCategory,Distributor,ItemExpiryDate FROM StockItems WHERE Distributor LIKE'%"+mdfySearchDistEnter.getText()+"%'";  
                    statement = connection.createStatement();  
                    resultSet = statement.executeQuery(selectSql);  
                    mdfyItemNoList.clear();
                    mdfyItemNoList.addElement("ItemID             Item Name           Item Category           Distributor                  Expiry Date"); 
                    while (resultSet.next())   
                    {  
                        mdfyItemNoList.addElement(resultSet.getString(1)+"          "+resultSet.getString(2)+"                "+resultSet.getString(3)+"                "+resultSet.getString(4)+"           "+resultSet.getString(5)); 
                    }  
            }              
            catch (Exception e) {  
                    e.printStackTrace();  
            }  
            finally {  
                    // Close the connections after the data has been handled.  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
            }
            mdfySearchNoEnter.setText("");
            mdfySearchNmeEnter.setText("");
            mdfySearchCatEnter.setText("");
            mdfySearchDistEnter.setText("");
            
        }
        });
        
        updateRecord.addActionListener(new ActionListener()
        {
        @Override
        public void actionPerformed(ActionEvent a)
        {
                    String Selected = (String) mdfySearchList.getSelectedValue(); 
            try {  
                connection = DriverManager.getConnection(connectionString); 
                statement = connection.createStatement();  
                    
                    String itemNameSelectSql = "SELECT ItemName FROM StockItems WHERE ItemID = " + Selected.substring(0, 6);
                    resultSet = statement.executeQuery(itemNameSelectSql);  
                    JTextField itemNameGet = new JTextField();
                    while (resultSet.next()) {itemNameGet.setText(resultSet.getString(1));}
                    
                    String itemCatSelectSql = "SELECT ItemCategory FROM StockItems WHERE ItemID = " + Selected.substring(0, 6);
                    resultSet = statement.executeQuery(itemCatSelectSql); 
                    JTextField itemCatGet = new JTextField();
                    while (resultSet.next()) {itemCatGet.setText(resultSet.getString(1));}
                    
                    String itemPriceSelectSql = "SELECT ItemPrice FROM StockItems WHERE ItemID = " + Selected.substring(0, 6);
                    resultSet = statement.executeQuery(itemPriceSelectSql);  
                    JTextField itemPriceGet = new JTextField();
                    while (resultSet.next()) {itemPriceGet.setText(resultSet.getString(1));}
                    
                    String itemWeightSelectSql = "SELECT ItemWeight FROM StockItems WHERE ItemID = " + Selected.substring(0, 6);
                    resultSet = statement.executeQuery(itemWeightSelectSql); 
                    JTextField itemWeightGet = new JTextField();
                    while (resultSet.next()) {itemWeightGet.setText(resultSet.getString(1));}
                    
                    String distributorSelectSql = "SELECT Distributor FROM StockItems WHERE ItemID = " + Selected.substring(0, 6);
                    resultSet = statement.executeQuery(distributorSelectSql);  
                    JTextField distributorGet = new JTextField();
                    while (resultSet.next()) {distributorGet.setText(resultSet.getString(1));}
                    
                    String itemManDateSelectSql = "SELECT ItemManDate FROM StockItems WHERE ItemID = " + Selected.substring(0, 6);
                    resultSet = statement.executeQuery(itemManDateSelectSql); 
                    JTextField itemManDateGet = new JTextField();
                    while (resultSet.next()) {itemManDateGet.setText(resultSet.getString(1));}
                    
                    String itemExpDateSelectSql = "SELECT ItemExpiryDate FROM StockItems WHERE ItemID = " + Selected.substring(0, 6);
                    resultSet = statement.executeQuery(itemExpDateSelectSql);  
                    JTextField itemExpDateGet = new JTextField();
                    while (resultSet.next()) {itemExpDateGet.setText(resultSet.getString(1));}
                    
                    String userSelectSql = "SELECT UserName FROM StockItems WHERE ItemID = " + Selected.substring(0, 6);
                    resultSet = statement.executeQuery(userSelectSql); 
                    JTextField userGet = new JTextField();
                    while (resultSet.next()) {userGet.setText(resultSet.getString(1));}
                    
                    JPanel panel = new JPanel(new GridLayout(0, 1));
                    panel.add(new JLabel("Item Name:"));
                    panel.add(itemNameGet);
                    panel.add(new JLabel("Item Category:"));
                    panel.add(itemCatGet);
                    panel.add(new JLabel("Item Price:"));
                    panel.add(itemPriceGet);
                    panel.add(new JLabel("Item Weight:"));
                    panel.add(itemWeightGet);
                    panel.add(new JLabel("Distributor:"));
                    panel.add(distributorGet);
                    panel.add(new JLabel("Item Manufacturing Date:"));
                    panel.add(itemManDateGet);
                    panel.add(new JLabel("Item Expiry Date:"));
                    panel.add(itemExpDateGet);
                    panel.add(new JLabel("User:"));
                    panel.add(userGet);
                    int result = JOptionPane.showConfirmDialog(null, panel, "Update Item",JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    
                    if (result == JOptionPane.OK_OPTION) {
                        String UpdateSql = "UPDATE StockItems SET ItemName = '" + itemNameGet.getText() + "' ,ItemCategory = '" + itemCatGet.getText() + "' ,ItemPrice = '" + itemPriceGet.getText() + "' ,ItemWeight = '" + itemWeightGet.getText() + "' ,Distributor = '" + distributorGet.getText() + "' ,ItemManDate = '" + itemManDateGet.getText() + "' ,ItemExpiryDate = '" + itemExpDateGet.getText() + "' ,UserName = '" + userGet.getText() + "' WHERE ItemID =" + Selected.substring(0, 6);  
                        prepsUpdateProduct = connection.prepareStatement(  
                            UpdateSql,  
                            Statement.RETURN_GENERATED_KEYS);  
                        prepsUpdateProduct.execute();  
                        resultSet = prepsUpdateProduct.getGeneratedKeys();  
                        while (resultSet.next()) {  
                            JOptionPane.showMessageDialog(null,"Updated The Record " + Selected.substring(0, 6) +" Successfully.");  
                        } 
                        mdfyItemNoList.clear();
                    }
                    
                }
                catch (Exception e) {  
                    e.printStackTrace();  
                }  
                finally {  
                    // Close the connections after the data has been handled.  
                    if (prepsUpdateProduct != null) try { prepsUpdateProduct.close(); } catch(Exception e) {}  
                    if (resultSet != null) try { resultSet.close(); } catch(Exception e) {}  
                    if (statement != null) try { statement.close(); } catch(Exception e) {}  
                    if (connection != null) try { connection.close(); } catch(Exception e) {}  
                } 
            
            mdfySearchNoEnter.setText("");
            mdfySearchNmeEnter.setText("");
            mdfySearchCatEnter.setText("");
            mdfySearchDistEnter.setText("");
            
        }
        });
        
        
        tabbedPane.setFont( new Font( "Dialog", Font.BOLD, 14 ) );
        tabbedPane.addTab(AddStock, addStkPanel);
        tabbedPane.addTab(DisplayStock, dsplyStkPanel);
        tabbedPane.addTab(DeleteStock, dltStkPanel);
        tabbedPane.addTab(ModifyStock, mdfyStkPanel);
 
        pane.add(tabbedPane, BorderLayout.CENTER);
    }
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Stock Control System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        StockControlSystem StockControl = new StockControlSystem();
        StockControl.addComponentToPane(frame.getContentPane());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    
    public static void main(String[] args) {
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
                createAndShowGUI();
            
    }
}
   