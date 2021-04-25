import java.awt.event.*;
import java.beans.Visibility;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;

public class SearchRecipes extends JFrame{ // JFrame

    JButton backButton = new JButton("Back");
    User usr;

    SearchRecipes(User usr,String query) throws SQLException{
        super(query);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        this.usr = usr;

        backButton.addActionListener(new backButton_clicked());

        // If the user presses the saved button
        if(query.equals("Saved")){
            setSize(1080, 720);	                                // Window Resolution (1024, 768 || 600, 600)


            RecipeBook temp = new RecipeBook();                     // Make instance of RecipeBook
            Recipe [] savedRecipesArr = new Recipe [usr.nr];        // Make array to save recipes to
            
            // Finds which recipes were saved and puts them into array
            for(int i = 0; i < usr.nr; i++){
                for(int j = 0; j < temp.nr; j++){
                    if(usr.saved_recipes[i].equals(temp.recipe_list[j].getName())){
                        savedRecipesArr[i] = temp.recipe_list[j];
                    }
                }
            }

            // Container to hold the gridPanel
            JPanel container = new JPanel();              
            container.setLayout(new FlowLayout());          
            container.setSize(500, 500);
            container.setBackground(Color.BLUE);

            // Panel that will have the saved recipes in a grid
            JPanel gridPanel = new JPanel();                
            gridPanel.setLayout(new GridLayout(0, 3, 5, 5));        // (row, col, space, space)
            gridPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(20, 26, 39)));
            gridPanel.setBackground(new Color(20, 26, 39));	//dark grey
    
            // Creates an array of buttons for the saved recipes
            JButton [] buttons = new JButton[savedRecipesArr.length];
            for(int i = 0; i < buttons.length; i++){
                try {
                    buttons[i] = new RecipeButton(savedRecipesArr[i]); // Custom button
                    buttons[i].addActionListener(new RecipeListener(usr, temp.recipe_list[i])); // Custom actionlistener
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                buttons[i].setPreferredSize(new Dimension(300,200)); // Set size of buttons
                gridPanel.add(buttons[i]); 
            }

    
            // Make the gridPanel scrollable
            JScrollPane scrollPane = new JScrollPane(gridPanel);
            scrollPane.setPreferredSize(new Dimension(1000,700));
            container.add(scrollPane);
            container.add(backButton);
            add(container);
            setVisible(true);

        }
        // If the user presses the ingredients button
        else if(query.equals("Ingredients")){

        }

        else{
            RecipeBook temp = new RecipeBook();
            JPanel gridPanel = new JPanel();    
            gridPanel.setLayout(new GridLayout(0, 3, 5, 5));
            gridPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(20, 26, 39)));
            gridPanel.setBackground(new Color(20, 26, 39));	//dark grey
    
            JButton [] buttons = new JButton[15];  // CHANGE THIS NUMBER LATER
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
            add(scrollPane);
        }

    }
    private class backButton_clicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
			System.out.println("Back button clicked!");
            try {
                dispose();
                new MainMenu(usr);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
		}
	}
}
