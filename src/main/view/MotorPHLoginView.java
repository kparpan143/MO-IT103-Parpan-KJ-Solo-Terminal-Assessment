package main.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import main.MotorPH;

public class MotorPHLoginView {
    // Constant Variables
    private static final String EMPLOYEE_LOGIN_CSV = System.getProperty("user.dir") + "/src/main/resources/employee_login.csv";
    
    // Variables needed for the Add Employee View
    private BufferedReader loginReader = null;
    private JLabel lblHeader, lblUsername, lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton loginButton, signupButton;
    
	public MotorPHLoginView () {
		
		// Set Frame
		JFrame frame = new JFrame();
        frame.setSize(400, 250);
        frame.setTitle("MotorPH Employee App");
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        // Set Panel 1
        // Create labels and textfields
        lblHeader = new JLabel("MotorPH Employee Login");
        lblHeader.setFont(new Font("Calibri", Font.BOLD, 20));

        lblUsername = new JLabel("Enter Username:");
        txtUsername = new JTextField(20);
        
        lblPassword = new JLabel("EnterPassword:");
        txtPassword = new JPasswordField(20);
        
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");
        
        // Define the panel to hold the components  
        JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
       
        // Add the components to the frame
        panel.add(lblHeader);
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(loginButton);
        panel.add(signupButton);
        
        // Put constraint on components       
        // Set label and textfield position: top and bottom
        layout.putConstraint(SpringLayout.NORTH, lblHeader, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblHeader, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        
        layout.putConstraint(SpringLayout.NORTH, lblUsername, 20, SpringLayout.SOUTH, lblHeader);
        layout.putConstraint(SpringLayout.WEST, lblUsername, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, txtUsername, 20, SpringLayout.SOUTH, lblHeader);
        layout.putConstraint(SpringLayout.EAST, txtUsername, -20, SpringLayout.EAST, panel);

        layout.putConstraint(SpringLayout.NORTH, lblPassword, 10, SpringLayout.SOUTH, lblUsername);
        layout.putConstraint(SpringLayout.WEST, lblPassword, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, txtPassword, 6, SpringLayout.SOUTH, txtUsername);
        layout.putConstraint(SpringLayout.EAST, txtPassword, -20, SpringLayout.EAST, panel);
        
        // Set button position
        layout.putConstraint(SpringLayout.NORTH, loginButton, 30, SpringLayout.SOUTH, lblPassword);
        layout.putConstraint(SpringLayout.WEST, loginButton, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, signupButton, 30, SpringLayout.SOUTH, txtPassword);
        layout.putConstraint(SpringLayout.EAST, signupButton, -20, SpringLayout.EAST, panel);


        //Add panel to frame
        frame.add(panel);
        frame.setVisible(true);

        // Add an ActionListener to the button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		resetData();
        		readCSVFiles();
    			
        		String loginRow = "";
        		
    			try {
	        		String username = txtUsername.getText();
	        		String password = txtPassword.getText();
	        		boolean isLoginValid = false;

	                while ((loginRow = loginReader.readLine()) != null) {
	                    String[] splitLogin = loginRow.split(",");
	                    
	                    if (splitLogin[0].equals(username) && splitLogin[1].equals(password)) {
	                    	isLoginValid = true;
	                    }
	                }
	                
	                if (isLoginValid) {
                		JOptionPane.showMessageDialog(null, "Logged in successfully.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                        new EmployeeListView();
                		frame.dispose();
	                }
	                else {
	            		JOptionPane.showMessageDialog(null, "Invalid username or password.", "Login Failed!", JOptionPane.ERROR_MESSAGE);	
	                }
            		
    		    } catch (Exception e1) {
                    Logger.getLogger(MotorPH.class.getName()).log(Level.SEVERE, null, e1);
            		JOptionPane.showMessageDialog(null, "Invalid Input.", "ERROR!", JOptionPane.ERROR_MESSAGE);
    		    }
            }
        });
        

        // Add an ActionListener to the button
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MotorPHSignUpView();
        		frame.dispose();
            }
        });
	}
    // Method used for reseting data used in the system
    private void resetData () {
        loginReader = null;
    }

    // Method used for reading CSV files
    private void readCSVFiles() {
        try {
            loginReader = new BufferedReader(new FileReader(EMPLOYEE_LOGIN_CSV));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MotorPH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
