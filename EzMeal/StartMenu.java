
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;
public class StartMenu
{
	UserBook ub = new UserBook();
	JFrame menu1 = new JFrame("Start");
    JFrame login_menu = new JFrame("Login Window");
    JFrame recipe_main = new JFrame("Recipe Menu");
	JFrame search_menu = new JFrame("Search Recipes");

	private JButton login_button , signup_button, exit_button;
	private JButton login_button2, back_button;

    private JLabel idLabel, pwLabel;
    private JTextField idField, pwField;
	
	public StartMenu() throws SQLException, IOException
	{
        // ################### Main menu #######################################
        //#region
        menu1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu1.setSize(500, 550);
		menu1.setBackground(new Color(20, 26, 39));
        menu1.setLayout(new FlowLayout());
	
		JLabel pic = new JLabel();
		pic.setSize(500,450);
		BufferedImage img = null;
        img = ImageIO.read(new File("ez-meal.jpg"));
        Image dimg = img.getScaledInstance(pic.getWidth(), pic.getHeight(),
        Image.SCALE_SMOOTH);  
        pic.setIcon(new ImageIcon(dimg));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setSize(500, 100);
		buttonPanel.setBackground(new Color(37, 42, 52));

		login_button = new JButton("Login");
		login_button.addActionListener(new login_buttonClicked());
		buttonPanel.add(login_button);

		
		signup_button = new JButton("Sign up");
		signup_button.addActionListener(new signup_buttonClicked());
		buttonPanel.add(signup_button);

		
		exit_button = new JButton("Exit");
		exit_button.addActionListener(new exit_buttonClicked());
		buttonPanel.add(exit_button);

		menu1.add(pic, BorderLayout.NORTH);
		menu1.add(buttonPanel, BorderLayout.SOUTH);
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
		public void actionPerformed(ActionEvent e)
		{
            login_menu.dispose();
			menu1.setVisible(true);
		}
    }
    //#endregion
}
