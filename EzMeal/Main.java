// CSCI 3326 Project    Members:
// Main program

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws IOException{
        Scanner in = new Scanner(System.in);                    
        Scanner m_in = new Scanner(System.in);                  // Separate scanner for menu
        Menu menu = new Menu();
        RecipeBook rb = new RecipeBook("RecipeData.txt");

        while (true) {
            menu.mainMenu();
            int ans = in.nextInt();
            
            if (ans == 1) {
                System.out.println("\nRecipes: ");
                rb.displayRecipes();
                menu.printIngredients(rb, m_in);
            }
            else if (ans == 2) {
                System.out.println("Yee haw");
                break;
            }
            else if (ans == 3) {
                System.out.println("Yee haw");
                break;
            } 
            else if(ans == 4){
                System.out.println("\nBye!");
                break;
            }else {
                System.out.println("Invalid input");
                break;
            }

        }

        in.close();
    }
}
