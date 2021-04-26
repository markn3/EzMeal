import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;

public class SignUpMenu{
    JFrame menu = new JFrame("Sign up");    
    private JTextField nameField = new JTextField();               
    private JTextField idField = new JTextField();
    private JTextField passwordField = new JTextField();
    private JTextField confirmationField = new JTextField();

    UserBook temp = new UserBook();                                // Creates a instance of UserBook to update database


    public SignUpMenu() throws SQLException{

        menu.setSize(600, 400);	// Window Resolution (1024, 768 || 600, 600)
        menu.setResizable(false);	// Not Resize
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLayout(new FlowLayout());
        
        JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setSize(150, 30);
		nameLabel.setLocation(50, 100);
        nameLabel.setForeground(Color.WHITE);
		menu.add(nameLabel);

        nameField.setSize(100, 30);
		nameField.setLocation(200, 100);
		menu.add(nameField);
        
        JLabel idLabel = new JLabel("ID: "); 
        idLabel.setSize(150, 30);
		idLabel.setLocation(50, 150);
        idLabel.setForeground(Color.WHITE);
		menu.add(idLabel);
        
        idField.setSize(100, 30);
		idField.setLocation(200, 150);
		menu.add(idField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setSize(150, 30);
		passwordLabel.setLocation(50, 200);
        passwordLabel.setForeground(Color.WHITE);
		menu.add(passwordLabel);
        
        passwordField.setSize(100, 30);
		passwordField.setLocation(200, 200);
		menu.add(passwordField);

        JLabel confrimationLabel = new JLabel("Re-Enter Password: ");
        confrimationLabel.setSize(150, 30);
		confrimationLabel.setLocation(50, 250);
        confrimationLabel.setForeground(Color.WHITE);
		menu.add(confrimationLabel);

        confirmationField.setSize(100, 30);
		confirmationField.setLocation(200, 250);
		menu.add(confirmationField);
        
        

        JLabel title1 = new JLabel("Fill out fields below	");


        JButton done_button = new JButton("Done");
        JButton back_button = new JButton("Back");

        // Create action listeners for buttons to register clicks
        done_button.addActionListener(new done_buttonClicked());
        back_button.addActionListener(new back_buttonClicked());
                
        Container mainContainer = menu.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        mainContainer.setBackground(new Color(20, 26, 39));
        menu.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(44, 49, 63)));
        
        // Panel Top
        
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(44, 49, 63)));	// light grey
        topPanel.setBackground(new Color(37, 42, 52));
        topPanel.setLayout(new FlowLayout(5));
        mainContainer.add(topPanel, BorderLayout.NORTH);

        topPanel.add(title1);
        title1.setForeground(Color.WHITE);
        
        // Panel bottom
        JPanel botPanel = new JPanel();
        botPanel.setLayout(new FlowLayout(3));
        
        botPanel.add(done_button);
        botPanel.add(back_button);
        botPanel.setBackground(new Color(37, 42, 52));
        botPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(44, 49, 63)));
        mainContainer.add(botPanel, BorderLayout.SOUTH);
        menu.setVisible(true);
    }

    
    public class done_buttonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
			System.out.println("Search button clicked!");
            if(nameField.getText() != ""){                
                try {
                    temp.add_user(nameField.getText(), idField.getText(), passwordField.getText());
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                System.out.println("Done");
                
            }
            else{
                // TODO: make pop up for incorrect
                //       or maybe have a label visible and the disappear

            }
            
		}
	}

    public class back_buttonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
            menu.dispose();
            try {
                UserBook temp = new UserBook();
                new StartMenu();
            } catch (SQLException | IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }            
		}
	}
}
