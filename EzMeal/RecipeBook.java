import java.io.*;
import java.util.Scanner;

public class RecipeBook{
    int nr;                                         

    Recipe [] recipe_list = new Recipe [20];        // assuming 30 is the max number of recipes (for now)
    // will read the recipe database and initialize
    public RecipeBook(String fn)throws IOException{             // fn = recipe data filename 
        File recipe_file = new File(fn);                        // Creates representation of file
        Scanner recipe_scan = new Scanner(recipe_file);         // Creates scanner to read through representation
        int counter = 0;                                        // Counter to append to recipe_list
        while(recipe_scan.hasNext()){                           // Loops until end of file
            String recipe_line = recipe_scan.nextLine();        // Takes the text file's next line
            String [] recipe_param = recipe_line.split("/");    // Splits the line, delimited with "/", and stores each part into array
            String name = recipe_param[0];                      // Takes the name of the recipe. Which should be first element

            String [] temp_arr = new String [20];               // Creates a temp array to take in the ingredients
            for(int i = 1; i < recipe_param.length; i++){       // Loops until end of line (end of recipe?)
                temp_arr[i-1] = recipe_param[i];                // Appends ingredients to temp array
            }
            recipe_list[counter] = new Recipe(name, temp_arr);  // Appends the recipe into the array of recipes (puts recipe into list)
            counter++;
        }
        nr = counter;                                           // ipdates the number of recipes
        recipe_scan.close();
    }

    public void displayRecipes(){                               
        for(int i = 0; i < nr; i++){
            if(recipe_list[i] == null){                            
                break;
            }
            System.out.println(recipe_list[i].getName());
        }
    }

    public void printIngredients(Scanner in){
        System.out.print("Enter the recipe name: ");
        String name= in.next();

        for(int i = 0; i < recipe_list.length; i++){
            if(recipe_list[i] == null){
                break;
            }
            if(name.equals(recipe_list[i].getName())){
                recipe_list[i].getIngredients();
                break;
            }
        }

        System.out.println();
    }

}

