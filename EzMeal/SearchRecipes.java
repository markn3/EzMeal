import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;

public class SearchRecipes extends JPanel{

    SearchRecipes(User usr,String query) throws SQLException{
        if(query.equals("Saved")){
            RecipeBook temp = new RecipeBook();
            Recipe [] savedRecipesArr = new Recipe [usr.nr];
            
            // Finds which recipes were saved and puts them into array
            for(int i = 0; i < usr.nr; i++){
                for(int j = 0; j < temp.nr; j++){
                    if(usr.saved_recipes[j] == temp.recipe_list[j].getName()){
                        savedRecipesArr[i] = temp.recipe_list[j];
                    }
                }
            }

            //
            JPanel gridPanel = new JPanel();    
            gridPanel.setLayout(new GridLayout(0, 3, 5, 5));
            gridPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(20, 26, 39)));
            gridPanel.setBackground(new Color(20, 26, 39));	//dark grey
    
            JButton [] buttons = new JButton[savedRecipesArr.length];
            for(int i = 0; i < buttons.length; i++){
                try {
                    buttons[i] = new RecipeButton(savedRecipesArr[i]);
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
}
