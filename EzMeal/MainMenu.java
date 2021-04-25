import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;

public class MainMenu extends JFrame {
    JLabel title1 = new JLabel("Good evening	");
    JButton rec_button = new JButton("Recommendations");
    JButton ing_button = new JButton("Ingredients");
    JButton social_button = new JButton("Social");
    JButton fil1_button = new JButton("Filter1");
    JButton fil2_button = new JButton("Filter2");
    JButton fil3_button = new JButton("Filter3");
    JButton fil4_button = new JButton("Filter4");
    JButton home_button = new JButton("Home");
    JButton search_button = new JButton("Search");
    JButton saved_button = new JButton("Saved");
    JButton hist_button = new JButton("History");
    JButton signOut_button = new JButton("Sign Out");

    MainMenu() throws SQLException {
        super("Ez-Meal");	// Window Name
        setSize(1080, 720);	// Window Resolution (1024, 768 || 600, 600)
        setResizable(false);	// Not Resize
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        

        

        // Create action listeners for buttons to register clicks
        search_button.addActionListener(new search_buttonClicked());
        signOut_button.addActionListener(new signout_buttonClicked());


        
        
        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        mainContainer.setBackground(new Color(20, 26, 39));	// dark grey
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(44, 49, 63)));
        
        // Panel Top
        
        JPanel topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, new Color(44, 49, 63)));	// light grey
        topPanel.setBackground(new Color(20, 26, 39));	// dark grey
        topPanel.setLayout(new FlowLayout(5));
        mainContainer.add(topPanel, BorderLayout.NORTH);
        
        topPanel.add(title1);
        title1.setForeground(Color.WHITE);
        
        topPanel.add(rec_button);
        topPanel.add(ing_button);
        topPanel.add(social_button);
        
        // Panel Mid
        JPanel midPanel = new JPanel();
        midPanel.setLayout(new GridLayout(4, 1, 5, 5));
        midPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(20, 26, 39)));	// open to change
        midPanel.setBackground(new Color(20, 26, 39));

        // Recipe grid
        //#region
        RecipeBook temp = new RecipeBook();

        JPanel recipeContainer = new JPanel();
        JPanel gridPanel = new JPanel();    
        gridPanel.setLayout(new GridLayout(0, 3, 5, 5));
        gridPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(20, 26, 39)));
        gridPanel.setBackground(new Color(20, 26, 39));	//dark grey

        JButton [] buttons = new JButton[12];
        for(int i = 0; i < buttons.length; i++){
            try {
                buttons[i] = new RecipeButton(temp.recipe_list[0]);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            buttons[i].setPreferredSize(new Dimension(300,200));
            gridPanel.add(buttons[i]);
        }

        JScrollPane scrollPane = new JScrollPane(gridPanel);
        scrollPane.setPreferredSize(new Dimension(500,300));
        ////#endregion
        

        midPanel.add(fil1_button);
        midPanel.add(fil2_button);
        midPanel.add(fil3_button);
        midPanel.add(fil4_button);
        
        //JLabel label = new JLabel("Center Box", SwingConstants.CENTER);
        //label.setOpaque(true);
        //label.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(20, 26, 39)));
    
        mainContainer.add(scrollPane, BorderLayout.CENTER);
        //mainContainer.add(label);
        mainContainer.add(midPanel, BorderLayout.WEST);
        
        // Panel bottom
        JPanel botPanel = new JPanel();
        botPanel.setLayout(new FlowLayout(3));
        
        botPanel.add(home_button);
        botPanel.add(search_button);
        botPanel.add(saved_button);
        botPanel.add(hist_button);
        botPanel.add(signOut_button);
        botPanel.setBackground(new Color(37, 42, 52));
        botPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(44, 49, 63)));
        mainContainer.add(botPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private class search_buttonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
			System.out.println("Search button clicked!");
            try {
                new StartMenu();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
		}
	}

    private class signout_buttonClicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
			System.out.println("Search button clicked!");
            try {
                dispose();
                new StartMenu();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
		}
	}

}
