import java.util.Scanner;

public class Menu {

    public void loginMenu() {
        System.out.println("\n1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. Exit");
        System.out.print("Please input your choice(1~3): ");
    }

    public void mainMenu() {
        System.out.println("\n1. Search Recipes");
        System.out.println("2. Try Something New");
        System.out.println("3. Saved Recipes");
        System.out.println("4. Log out");
        System.out.print("Please input your choice(1~4): ");
    }

    public void printIngredients(RecipeBook rb, Scanner in){
        System.out.print("\nChoose a recipe to see ingredients: ");
        String name = in.nextLine();


        for(int i = 0; i < rb.recipe_list.length; i++){
            if(rb.recipe_list[i] == null){
                break;
            }
            if(name.equals(rb.recipe_list[i].getName())){
                rb.recipe_list[i].getIngredients();
                break;
            }
        }
    }
}