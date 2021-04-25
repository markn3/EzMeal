import java.awt.event.*;
import java.io.IOException;

public class RecipeListener implements ActionListener{
    Recipe recipe_button;

    public RecipeListener(Recipe b){
        this.recipe_button = b;
    }
    public void actionPerformed(ActionEvent e){
        System.out.println(recipe_button.getName() + " Pressed!");
        try {
            new ShowRecipe(recipe_button);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
