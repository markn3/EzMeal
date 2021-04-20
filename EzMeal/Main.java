// CSCI 3326 Project    Members: Mark Navalta, Stephen Pagayon, Enrique Manrique
// Main program

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws IOException{
        Scanner in = new Scanner(System.in);
        Menu menu = new Menu();
        RecipeBook rb = new RecipeBook("RecipeData.txt");
        UserBook ub = new UserBook("UserData.txt");

        while(true){
            menu.loginMenu();
            int ans = in.nextInt(); 
            if(ans == 1){
                // Sign in
                System.out.println("Logging in....");
                boolean valid = menu.login(ub, in);
                if(valid){
                    while(true){
                        menu.mainMenu();
                        int ans_2 = in.nextInt();
                        if(ans_2 == 1){
                            System.out.println("\nRecipes: ");
                            rb.displayRecipes();

                        }
                        else if(ans_2 == 2){
                            rb.displayRecipes();
                            rb.printIngredients(in);
                        }
                        else if(ans_2 == 3){
                            // See saved recipes
                            menu.savedRecipes(ub, in);
                        }
                        else if(ans_2 == 4){
                            break;
                        }
                        else{
                            System.out.println("Invalid input");
                        }
                    }
                }
                else{
                    System.out.println("Incorrect id/password");
                }
            }
            else if(ans == 2){
                // Sign up 
                menu.signUp(ub, in);
            }
            else if(ans == 3){
                System.out.println("\nBye!");
                break;
            }
            else{
                System.out.println("\nInvalid input");
            }
        }
        in.close();
        ub.updateUserBook("UserData.txt");
    }   
}
