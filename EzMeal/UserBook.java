
import java.io.*;
import java.util.Scanner;

public class UserBook{
    int nu = 20;        // number of max users (for now)
    User [] users = new User[nu];

    // Layout for UserData.txt:
    // <name>/<id>/<password>/<how many recipes saved>/<how many ingredients>/<recipe>/<recipe>..../<ingredient>/<ingredient>
    //                                                                        |<-------------(varies)---------------------->|
    public UserBook(String fn)throws IOException{
        File user_file = new File(fn);
        Scanner user_scan = new Scanner(user_file);
        int counter = 0;
        while(user_scan.hasNext()){
            String user_line = user_scan.nextLine();
            String [] user_param = user_line.split("/");
            String name = user_param[0];
            String id = user_param[1];
            String pw = user_param[2]; 
            int nr = Integer.parseInt(user_param[3]);       // Number of saved recipes
            int ni = Integer.parseInt(user_param[4]);       // Number of ingredients user has
            String [] saved_recipes = new String[nr];
            String [] user_ingredients = new String[ni];
            if(nr > 0){
                for(int i = 0; i < nr; i++){
                    saved_recipes[i] = user_param[i+4];
                }
            } 
            if(ni > 0){
                for(int j = 0; j < nr; j++){
                    user_ingredients[j] = user_param[j+3+nr];
                }
            }
            users[counter] = new User(name, id, pw, saved_recipes, user_ingredients);
            counter++;
        }
        user_scan.close();
    }

    public void displayUsers(){
        for(int i = 0; i < nu; i++){
            if(users[i] == null){
                break;
            }
            System.out.println(users[i].getName());
        }
    }
    
}