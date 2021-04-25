
import javax.swing.*;

import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;
public class StartMenu
{
	UserBook ub = new UserBook();
	JFrame menu1 = new JFrame("1st Window");
    JFrame login_menu = new JFrame("Login Window");
    JFrame recipe_main = new JFrame("Recipe Menu");
	JFrame search_menu = new JFrame("Search Recipes");
	

	private JButton login_button , signup_button, exit_button;
	private JButton login_button2, back_button;
	private JButton search_button, saved_button, signOut_button;

    private JLabel idLabel, pwLabel;
    private JTextField idField, pwField;



	
	public StartMenu() throws SQLException
	{
        // ################### Main menu #######################################
        //#region
        menu1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu1.setSize(600, 600);
        menu1.setLayout(null);

		login_button = new JButton("Login");
		login_button.setSize(100, 30);
		login_button.setLocation(200, 50);
		login_button.addActionListener(new login_buttonClicked());
		menu1.add(login_button);

		
		signup_button = new JButton("Sign up");
		signup_button.setSize(100, 30);
		signup_button.setLocation(200, 200);
		signup_button.addActionListener(new signup_buttonClicked());
		menu1.add(signup_button);

		
		exit_button = new JButton("Exit");
		exit_button.setSize(100, 30);
		exit_button.setLocation(200, 350);
		exit_button.addActionListener(new exit_buttonClicked());
		menu1.add(exit_button);
        menu1.setVisible(true);
        //#endregion

        // ################### Login Menu ######################################
        //#region
        login_menu.setSize(600, 400);	// Window Resolution (1024, 768 || 600, 600)
        login_menu.setResizable(false);	// Not Resize
        login_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login_menu.setLayout(new FlowLayout());

		idLabel = new JLabel("ID:");
        idLabel.setSize(150, 30);
		idLabel.setLocation(50, 150);
        idLabel.setForeground(Color.WHITE);
		login_menu.add(idLabel);
		
		idField = new JTextField(10);
        idField.setSize(100, 30);
		idField.setLocation(200, 150);
		login_menu.add(idField);
		
		pwLabel = new JLabel("Password:");
        pwLabel.setSize(150, 30);
		pwLabel.setLocation(50, 200);
        pwLabel.setForeground(Color.WHITE);
		login_menu.add(pwLabel);
		
		pwField = new JTextField(10);
        pwField.setSize(100, 30);
		pwField.setLocation(200, 200);
		login_menu.add(pwField);
				
        login_button2 = new JButton("Login");
		login_button2.addActionListener(new login_button2Clicked());

		back_button = new JButton("Back");
		back_button.addActionListener(new back_buttonClicked());

		login_button2.setSize(100, 30);
		login_button2.setLocation(50, 300);
		login_menu.add(login_button2);

		Container mainContainer = login_menu.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        mainContainer.setBackground(new Color(20, 26, 39));
        login_menu.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(44, 49, 63)));
        
        // Panel Top
        
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(44, 49, 63)));	// light grey
        topPanel.setBackground(new Color(37, 42, 52));
        topPanel.setLayout(new FlowLayout(5));
        mainContainer.add(topPanel, BorderLayout.NORTH);
        
        // Panel bottom
        JPanel botPanel = new JPanel();
        botPanel.setLayout(new FlowLayout(3));

        botPanel.setBackground(new Color(37, 42, 52));
        botPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(44, 49, 63)));
        mainContainer.add(botPanel, BorderLayout.SOUTH);

		botPanel.add(login_button2);
		botPanel.add(back_button);
        //#endregion

		// ################### Search Recipes ##############################
        search_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        search_menu.setSize(600, 600);
        search_menu.setLayout(null);

		JPanel card1 = new JPanel();
		card1.setBackground(Color.BLUE);
		card1.setBounds(0, 0, 250, 250);
		search_menu.add(card1);

	}

    // ---------------------- Main Menu Buttons ----------------------------------
    //#region
	private class login_buttonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
			System.out.println("login_button button clicked!");
            menu1.dispose();
			login_menu.setVisible(true);
		}
	}
	
    private class signup_buttonClicked implements ActionListener
	{
        // TODO: signup and then register to database
		public void actionPerformed(ActionEvent e)
		{
			menu1.dispose();
            try {
                new SignUpMenu();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
		}
	}
	private class exit_buttonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			menu1.dispose();
		}
	}
    //#endregion
    
    // --------------------- Signin Menu Buttons ---------------------------------
    //#region
    private class login_button2Clicked implements ActionListener
	{
        // TODO: make pop-up window for failed login. Maybe another J frame. 
		// Also, maybe a greeting after a successful login
		public void actionPerformed(ActionEvent e)
		{
			try {

				User valid = ub.valid_user(idField.getText(), pwField.getText());
				if(valid != null){
					login_menu.dispose();
					System.out.println("Opening main menu");
					new MainMenu(valid);
				}
				else{
					System.out.println("Incorrect credentials");
				}
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}


		}
    }

	private class back_buttonClicked implements ActionListener
	{
        // TODO: Check user database for sign in. Use if or else
        // Also, make pop-up window for failed login. Another JFrame
		public void actionPerformed(ActionEvent e)
		{
            login_menu.dispose();
			menu1.setVisible(true);
		}
    }
    //#endregion
    
    // ###############  Recipe Main Menu Buttons ################
    //#region
    private class search_buttonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
			System.out.println("search button clicked");
            recipe_main.dispose();
			search_menu.setVisible(true);
		}
	}

    private class saved_buttonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
			System.out.println("saved button clicked!");
            menu1.dispose();
			login_menu.setVisible(true);
		}
	}

    private class signOut_buttonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
			System.out.println("signout button clicked!");
            menu1.dispose();
			login_menu.setVisible(true);
		}
	}
    //#endregion
}
