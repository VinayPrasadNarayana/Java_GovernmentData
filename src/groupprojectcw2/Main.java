/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupprojectcw2;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/*
HCI of the Main page
********************
I wanted to red colour for the lables at first but later i realised after reading up online,
how common colour blindness and impairment was, I noticed that keeping to simple and original colours was
best for the system.

I made sure that i used fewer font in the mian page so that it was kept simple and no colors were used to make sure
everyone could read the content.

I made the data table bigger with bigger fonts to ensure they were easily readible and would cause no issues when people
were trying to study the data. And also made sure all the buttons were big and the font was right so that everyone could read it and 
no color were added to them.

The layout was also a key area i made sure to spend time on simply because i read up about the gestalt 
theory/principles which suggests that people like seeing things in line and looking simple making it 
easy for ANYONE to use the program.

I also made sure that the language used in the page is the language the most people who use the computer are aware of.
and also made sure that I have used full-lengthname so that it will be easy for the user to identity the content.

I used very few colors just for the layout background and not on the text fields so that it can be easy 
for the use with various vision problems to see.

Security
********
Adding a user log adds to the security of the program because this allows all users to see what other users
have searched for so if anything happens within the program, they could always refer back to what has been 
searched and by who.

A user log where all logs in and logs out are recorded at what time would also make the system a lot secure
because that way, people would be able to see who logged in and at what time which could help tracking any issues
such as data being deleted or going missing.

This would allow admins to see who was logged in when things went wrong and ensure whoever is responsible is caught.

If a user somehow gets access to the project, they could easily run the MAIN class and get access to all of the
data set compromising all security. To prevet this i have used Ceaser Cipher to encrypt the content of the file.

We could have also used a timer to make sure the right person is using the data, and when the time runs out the 
user has to enter his password in order to use the data again. This avoids unwanted people from viewing the government data.



 */
/**
 *
 * @author vinay
 */
public class Main extends javax.swing.JFrame {

    public TableRowSorter<TableModel> rowSorter;
    String UserName;
    double Probability = 0;

    /**
     * Creates new form Main
     */
    public Main(String username) {
        initComponents();
        //Username variable for the "Logged in as: " feature
        UserName = username;
        //Sets the text in the TextField to the user who is logged in
        LogUser.setText(UserName);

        //CSVFile refers to public class below
        CSVFile DataSet = new CSVFile();
        //My Model class extends the Abstract Table Model below
        MyModel NewModel = new MyModel();
        //Sets the new model into my table called "Table"
        this.Table.setModel(NewModel);
        //Gets data from the csv which is in the quotations
        File DataFile = new File("wste_bus.csv");
        //Creates an array list for my rowsorter by reading the CSV file called "DataFile"
        ArrayList<String[]> RowSorter2 = DataSet.ReadCSVfile(DataFile);
        //Adds CSVData into the rowsorter
        NewModel.AddCSVData(RowSorter2);
        //Prints all rows and columns into compiler below
        System.out.println("Rows: " + NewModel.getRowCount());
        System.out.println("Cols: " + NewModel.getColumnCount());

        rowSorter = new TableRowSorter<>(Table.getModel());
        Table.setRowSorter(rowSorter);

    }

    public Main() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //Method for reading CSV file
    public class CSVFile {

        //Array list for each row in the CSV file
        private final ArrayList<String[]> AL = new ArrayList<>();
        private String[] RowOne;

        //Reads CSV file to fill the array
        public ArrayList<String[]> ReadCSVfile(File DataFile) {

            try {
                //Buffered reader
                BufferedReader BufferedR = new BufferedReader(new FileReader(DataFile));
                //While loop which reads each value from compiler and seperates them with a comma
                while (BufferedR.ready()) {
                    String MyString = BufferedR.readLine();
                    //Each value is split by a comma 
                    RowOne = MyString.split(",");
                    AL.add(RowOne);
                    //Prints all rows in the compiler one by one
                    System.out.println(Arrays.toString(RowOne));
                }//End of while loop

            }//end of try
            catch (Exception error) {
                String ErrorMessage = error.getMessage();
                System.out.println("File not found: " + ErrorMessage);

            }
            return AL;

        }
    }//Class end

    public class MyModel extends AbstractTableModel {

        //Puts all column names into an array 
        private final String[] columnNames;
        private ArrayList<String[]> Data = new ArrayList<String[]>();

        //My model stores all titles for each column but manually 
        //(need to find way to get these from the file directly)
        MyModel() {
            this.columnNames = new String[]{"Sector Description", "industrial sludges", "other chemical wastes"};
        }

        //Add data from the csv file into the array
        public void AddCSVData(ArrayList<String[]> DataIn) {
            this.Data = DataIn;
            this.fireTableDataChanged();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
            //Gets column name length
        }

        @Override
        public int getRowCount() {
            return Data.size();
            //Gets row size
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
            //Gets column names and stores them into "col"
        }

        @Override
        public Object getValueAt(int row, int col) {
            return Data.get(row)[col];
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        SearchBox = new javax.swing.JTextField();
        SearchBtn = new javax.swing.JButton();
        UserLOG = new javax.swing.JButton();
        LogOut = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        LogUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Barchat = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        Probability1 = new javax.swing.JTextField();
        Probability2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        ProbabilityAnswer = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 102, 102));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SearchBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBoxActionPerformed(evt);
            }
        });
        jPanel1.add(SearchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 40, 270, 40));

        SearchBtn.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        SearchBtn.setText("Search");
        SearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(SearchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(323, 39, 108, 40));

        UserLOG.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        UserLOG.setText("Users Search Log");
        UserLOG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserLOGActionPerformed(evt);
            }
        });
        jPanel1.add(UserLOG, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 39, -1, 41));

        LogOut.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        LogOut.setText("Log Out");
        LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutActionPerformed(evt);
            }
        });
        jPanel1.add(LogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 40, -1, 41));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Logged in as: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1098, 13, -1, -1));

        LogUser.setEditable(false);
        LogUser.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        LogUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogUserActionPerformed(evt);
            }
        });
        jPanel1.add(LogUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1098, 47, 156, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Click Here for Bar Chart");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(798, 15, 165, -1));

        Barchat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Barchat.setText("Bar Chat");
        Barchat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BarchatActionPerformed(evt);
            }
        });
        jPanel1.add(Barchat, new org.netbeans.lib.awtextra.AbsoluteConstraints(798, 40, -1, 41));

        Table.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title1", "Title2", "Title3", "Title4", "Title5", "Title6"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(Table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 99, 1402, 348));

        Probability1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(Probability1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 100, 30));

        Probability2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(Probability2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 490, 100, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Enter the values to caluclate the probability");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Bayes Probability");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 520, -1, -1));

        ProbabilityAnswer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(ProbabilityAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 510, 150, 40));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 64, 1430, 600));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Waste Management UI");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 13, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogUserActionPerformed
        // TODO add your handling code here:
        LogUser.setText(UserName);
    }//GEN-LAST:event_LogUserActionPerformed

    private void LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutActionPerformed
        // TODO add your handling code here:
        // go to login page idf this button is clicked
        this.dispose();
        Login LoginPage = new Login();
        LoginPage.setVisible(true);
    }//GEN-LAST:event_LogOutActionPerformed

    private void UserLOGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserLOGActionPerformed
        //UserLogs are created
        UserLogs logs = new UserLogs();
        logs.setVisible(true);
        //Inputs all user logs into the UserLog.txt file
        File inputFile = new File("src/groupprojectcw2/UserLog.txt");
        String answer = "";
        try {
            // get the userlog details from the user file
            Scanner input = new Scanner(inputFile);
            while (input.hasNextLine()) {
                answer += input.nextLine() + "\n";
            }
            String decrypt = answer;
            String decrypted = Cipherdecrypt(decrypt, 5);
            logs.UserLogDisplay.setText(decrypted);
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!");
        }

    }//GEN-LAST:event_UserLOGActionPerformed

    private void SearchBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchBoxActionPerformed

    private void SearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtnActionPerformed

        Boolean flag = true;
        //check if the useer has enter the values into the probability textfields
        if (Probability1.getText() == null || Probability2.getText() == null) {
            flag = false;
        }
        if (flag == true) {
            String Searched = SearchBox.getText();
            String UserLoggedIn = LogUser.getText();
           //filter the row according to the user search value
            if (Searched.trim().length() == 0) {
                rowSorter.setRowFilter(null);
            } else {

                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + Searched));
            }
            BufferedWriter BuffWriter1 = null;
            FileWriter FileWriter1 = null;
            try {
                // add the searched values to the user log
                File file = new File("src/groupprojectcw2/UserLog.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter1 = new FileWriter(file.getAbsoluteFile(), true);
                BuffWriter1 = new BufferedWriter(FileWriter1);
                //encrypt the user log text
                String encrypt = "User: " + UserLoggedIn + " Searched for: " + Searched;
                String encrypted = Cipherencrypt(encrypt, 5);

                BuffWriter1.write(encrypted + "\r\n");
            } catch (IOException e) {
            } finally {
                try {
                    if (BuffWriter1 != null) {
                        BuffWriter1.close();
                    }
                    if (FileWriter1 != null) {
                        FileWriter1.close();
                    }
                } catch (IOException ex) {
                }
            }
            //gets the user entered values 
            String prob1 = Probability1.getText();
            String prob2 = Probability2.getText();
            // converts it into a int from a string
            int Prob1 = Integer.valueOf(prob1);
            int Prob2 = Integer.valueOf(prob2);
            // calls the method to get the probability if the user entered values
            getProbability(Prob1, Prob2);
        } else {
            JOptionPane.showMessageDialog(null, "Please enter the probability values");
        }

    }//GEN-LAST:event_SearchBtnActionPerformed
// this method is to show the probability of the searched content
    private void BarchatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BarchatActionPerformed
        // TODO add your handling code here:
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(Probability, "Value1", "Value2");
        JFreeChart chart = ChartFactory.createBarChart("Probability Bar Chart", "Column2", "Cloumn1", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        ChartFrame frame = new ChartFrame("Probability Bar Chart", chart);
        frame.setVisible(true);
        frame.setSize(450, 350);


    }//GEN-LAST:event_BarchatActionPerformed
    public void getProbability(int value1, int value2) {
        //
        double E = 0;
        double H = 0;
        double EofH = 0;
        double HofE = 0;
        //
        double PofE;
        double PofH;
        double PofHofE;
        //

        ArrayList<Integer> Coloumn1 = new ArrayList<>();
        ArrayList<Integer> Coloumn2 = new ArrayList<>();

        for (int Number = 0; Number < Table.getRowCount(); Number++) {
            Coloumn1.add(Integer.parseInt(Table.getValueAt(Number, 1).toString()));
        }
        for (int Number = 0; Number < Table.getRowCount(); Number++) {
            Coloumn2.add(Integer.parseInt(Table.getValueAt(Number, 2).toString()));
        }
        for (int i = 0; i < Coloumn1.size(); i++) {
            if (Coloumn1.get(i) == value1) {
                E++;
                System.out.println("E: " + E);
            }
        }
        for (int i = 0; i < Coloumn2.size(); i++) {
            if (Coloumn2.get(i) == value2) {
                H++;
                System.out.println("H: " + H);
            }
        }
        for (int i = 0; i < Coloumn1.size(); i++) {
            if (Coloumn1.get(i) == value1 && Coloumn2.get(i) == value2) {
                EofH++;
                System.out.println("EofH: " + EofH);
            }
        }
        int size = Coloumn1.size();
        PofH = H / size;
        System.out.println("PofH: " + PofH);
        PofE = E / size;
        System.out.println("PofE: " + PofE);

        PofHofE = EofH * HofE / PofH;
        System.out.println("PofHofE: " + PofHofE);
        String value = String.valueOf(PofHofE);
        Probability = Double.valueOf(value);
        ProbabilityAnswer.setText(value);

    }

    public static void main(String args[]) {

        //Create and display the form
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // this method is used to encrypt the user log using ceaser cipher
    public static String Cipherencrypt(String plainText, int shift) {
        if (shift > 26) {
            shift = shift % 26;
        } else if (shift < 0) {
            shift = (shift % 26) + 26;
        }

        String cipherText = "";
        int length = plainText.length();
        for (int i = 0; i < length; i++) {
            char ch = plainText.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isLowerCase(ch)) {
                    char c = (char) (ch + shift);
                    if (c > 'z') {
                        cipherText += (char) (ch - (26 - shift));
                    } else {
                        cipherText += c;
                    }
                } else if (Character.isUpperCase(ch)) {
                    char c = (char) (ch + shift);
                    if (c > 'Z') {
                        cipherText += (char) (ch - (26 - shift));
                    } else {
                        cipherText += c;
                    }
                }
            } else {
                cipherText += ch;
            }
        }
        return cipherText;
    }

    // this method is used to decrypt the userlog file to display it in the user log window
    public static String Cipherdecrypt(String plainText, int shift) {
        if (shift > 26) {
            shift = shift % 26;
        } else if (shift < 0) {
            shift = (shift % 26) + 26;
        }

        String cipherText = "";
        int length = plainText.length();
        for (int i = 0; i < length; i++) {
            char ch = plainText.charAt(i);
            if (Character.isLetter(ch)) {
                if (Character.isLowerCase(ch)) {
                    char c = (char) (ch - shift);
                    if (c < 'a') {
                        cipherText += (char) (ch + (26 - shift));
                    } else {
                        cipherText += c;
                    }
                } else if (Character.isUpperCase(ch)) {
                    char c = (char) (ch - shift);
                    if (c < 'A') {
                        cipherText += (char) (ch + (26 - shift));
                    } else {
                        cipherText += c;
                    }
                }
            } else {
                cipherText += ch;
            }
        }
        return cipherText;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Barchat;
    private javax.swing.JButton LogOut;
    private javax.swing.JTextField LogUser;
    private javax.swing.JTextField Probability1;
    private javax.swing.JTextField Probability2;
    private javax.swing.JTextField ProbabilityAnswer;
    private javax.swing.JTextField SearchBox;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JTable Table;
    private javax.swing.JButton UserLOG;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
