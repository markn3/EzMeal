import java.util.Scanner;

public class Menu {

    public void loginMenu() {
        System.out.println("\n1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. Exit");
        System.out.print("Please input your choice(1~3): "); 
    }

    public boolean login(UserBook ub, Scanner in){
        boolean success = false;
        System.out.print("Enter your id: " );
        String id = in.next();

        System.out.print("Enter your password: ");
        String pw = in.next();

        int user_index = 0;
        for(int i = 0; i < ub.nu; i++){
            if(ub.users[i] == null){
                break;
            }

            if((ub.users[i].getId().equals(id)) && (ub.users[i].getPw().equals(pw))){
                success = true;
                user_index = i;
            } 
        }

        if(success){
            System.out.println("Welcome " + ub.users[user_index].getFName() + "!");
        }
        return success;
    }
    public void signUp(UserBook ub, Scanner in){
        System.out.println("\nSign up:\n");
        System.out.print("Enter first name: ");
        String fname = in.next();

        System.out.print("\nEnter ID:");
        String id = in.next();

        System.out.print("\nEnter password: ");
        String pw = in.next();

        boolean valid_pw = false;
        while(!valid_pw){
            System.out.print("\nRe-enter password: ");
            String pw_2 = in.next();
            if(pw_2.equals(pw)){
                valid_pw = true;
            }
        }

        ub.addUser(fname, id, pw);
    }
    
    public void mainMenu(){
        System.out.println("\n1. Search Recipes");
        System.out.println("2. See ingredients");
        System.out.println("3. Saved Recipes");
        System.out.println("4. Log out");
        System.out.print("Please input your choice(1~4): ");
    }

    public void savedRecipes(UserBook ub, Scanner in){
        // TODO: match recipe string name with database recipe and output
        System.out.print("Enter ID: ");
        String id = in.next();
        for(int i = 0; i < ub.nu; i++){
            if(id.equals(ub.users[i].getId())){
                String [] temp = ub.users[i].getRecipes();
                System.out.println("Saved recipes: ");
                for(int j = 0; j < temp.length; j++){
                    System.out.println(temp[j]);
                }
                break;
            }
        }

    }
}