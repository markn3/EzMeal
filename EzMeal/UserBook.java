
import java.io.*;
import java.util.Scanner;

public class UserBook{
    int nu;                          // Number of users
    User [] users = new User[20];    // Assuming max number of users is 20 (for now)

    // Layout for UserData.txt:
    // <name>/<id>/<password>/<how many recipes saved>/<how many ingredients>/<recipe>/<recipe>..../<ingredient>/<ingredient>
    //                                                                        |<-------------(varies)---------------------->|

    public UserBook(String fn)throws IOException{
        File user_file = new File(fn);
        Scanner user_scan = new Scanner(user_file);
        int counter = 0;                
        while(user_scan.hasNext()){                                 // Loops until end of file
            String user_line = user_scan.nextLine();                // Takes the next line of the file
            String [] user_param = user_line.split("/");            // Splits the line taken by the symbol: '/' and puts it into an array
            String fname = user_param[0];                            // The name of the user is the first element of the array
            String id = user_param[1];                              // ID is the 2nd element
            String pw = user_param[2];                              // Password is the 3rd element
            int nr = Integer.parseInt(user_param[3]);               // Number of saved recipes
            int ni = Integer.parseInt(user_param[4]);               // Number of ingredients user has
            String [] saved_recipes = new String[nr];               // Creates an array that will be passed into the class constructor 
            String [] user_ingredients = new String[ni];            // Array for ingredients
            if(nr > 0){                                             // If user has any saved recipes then...
                for(int i = 0; i < nr; i++){                        //    fill the array with the saved recipes
                    saved_recipes[i] = user_param[i+4];
                }
            } 
            if(ni > 0){                                             //If user has any ingredients then...
                for(int j = 0; j < nr; j++){                        //   fill the array with the ingredients
                    user_ingredients[j] = user_param[j+3+nr];
                }
            }
            users[counter] = new User(fname, id, pw, nr, ni, saved_recipes, user_ingredients);   // Fill the users array with users
            counter++;                                                                          
        }
        nu = counter;                                              // updates number of users
        user_scan.close();
    }

    public void displayUsers(){
        for(int i = 0; i < nu; i++){
            if(users[i] == null){
                break;
            }
            System.out.println(users[i].getFName());
        }
    }

    public void addUser(String name, String id, String pw){
        String [] r = new String[0];
        String [] i = new String[0];
        users[nu] = new User(name, id, pw, 0, 0, r, i);
        nu++;
    }

    public void updateUserBook(String fn) throws IOException{       
        PrintWriter pw = new PrintWriter(fn);                               // Instantiate printwriter to write over UserData.txt
        for(int k = 0; k < nu; k++){                                        // loop through number of users
            int nr = users[k].getNumRec();                                  // Gets the number of saved recipes and temporarily holds it
            int ni = users[k].getNumIng();                                  // Gets the number of ingredients and temporarily holds it
            String [] saved_rec = users[k].getRecipes();                    // Temporarily holds the array of saved recipes
            String [] ingredients = users[k].getIngredients();              // Same
            pw.print(users[k].getFName() + "/" + users[k].getId() + "/" + nr + "/" + ni + "/");      // Writes onto file
            for(int r = 0; r < nr; r++){                                    // Looping through saved recipes and writing
                pw.print(saved_rec[r] + "/");   
            }
            for(int i = 0; i < ni; i++){                                    // Same same
                pw.print(ingredients[i] + "/");
            }
            pw.println();                                                   // Goes to the next line
        }
        pw.close();
    }
    
}