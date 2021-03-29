import java.io.*;
import java.util.Scanner;

public class RecipeBook{
    int nr = 30;                                    // number of recipes (for now)

    Recipe [] recipe_list = new Recipe [nr];        // array that will hold recipes

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

}

