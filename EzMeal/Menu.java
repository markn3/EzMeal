
import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;
public class Menu
{
	JFrame menu1 = new JFrame("1st Window");
    JFrame login_menu = new JFrame("Login Window");
    JFrame recipe_main = new JFrame("Recipe Menu");
	JFrame search_menu = new JFrame("Search Recipes");

	private JButton login_button , signup_button, exit_button;
	private JButton login_button2;
	private JButton search_button, saved_button, signOut_button;

    private JLabel idLabel, pwLabel;
    private JTextField idField, pwField;

	
	public Menu() throws SQLException
	{
        // ################### Main menu #######################################
        //#region
        menu1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu1.setSize(500, 500);
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
        login_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login_menu.setSize(500, 500);
        login_menu.setLayout(null);

        idLabel = new JLabel("ID:");
		idLabel.setSize(100, 30);
		idLabel.setLocation(50, 100);
		login_menu.add(idLabel);
		
		idField = new JTextField(10);
		idField.setSize(100, 30);
		idField.setLocation(100, 100);
		login_menu.add(idField);
		
		pwLabel = new JLabel("Password:");
		pwLabel.setSize(100, 30);
		pwLabel.setLocation(50, 200);
		login_menu.add(pwLabel);
		
		pwField = new JTextField(10);
		pwField.setSize(100, 30);
		pwField.setLocation(100, 200);
		login_menu.add(pwField);

        login_button2 = new JButton("Login");
		login_button2.setSize(100, 30);
		login_button2.setLocation(50, 300);
		login_button2.addActionListener(new login_button2Clicked());
		login_menu.add(login_button2);
        //#endregion

        // ################### Main Recipe Menu #####################################
        //#region
        recipe_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        recipe_main.setSize(500, 500);
        recipe_main.setLayout(null);

        search_button = new JButton("Search Recipes");
		search_button.setSize(100, 30);
		search_button.setLocation(200, 50);
		search_button.addActionListener(new search_buttonClicked());
		recipe_main.add(search_button);

		
		saved_button = new JButton("Saved Recipes");
		saved_button.setSize(100, 30);
		saved_button.setLocation(200, 200);
		saved_button.addActionListener(new saved_buttonClicked());
		recipe_main.add(saved_button);

		
		signOut_button = new JButton("Sign out");
		signOut_button.setSize(100, 30);
		signOut_button.setLocation(200, 350);
		signOut_button.addActionListener(new signOut_buttonClicked());
		recipe_main.add(signOut_button);
        //#endregion


		// ################### Search Recipes ##############################
        search_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        search_menu.setSize(500, 500);
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
			System.out.println("signup_button button clicked!");
		}
	}
	private class exit_buttonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
    //#endregion
    
    // --------------------- Signin Menu Buttons ---------------------------------
    //#region
    private class login_button2Clicked implements ActionListener
	{
        // TODO: Check user database for sign in. Use if or else
        // Also, make pop-up window for failed login. Another JFrame
		public void actionPerformed(ActionEvent e)
		{
            login_menu.dispose();
            recipe_main.setVisible(true);

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
