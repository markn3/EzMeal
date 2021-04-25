import java.awt.event.*;
import java.io.IOException;

public class RecipeListener implements ActionListener{
    Recipe recipe_button;
    User usr;

    public RecipeListener(User usr, Recipe b){
        this.recipe_button = b;
        this.usr = usr;
    }
    public void actionPerformed(ActionEvent e){
        System.out.println(recipe_button.getName() + " Pressed!");
        try {
            new ShowRecipe(usr, recipe_button);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
