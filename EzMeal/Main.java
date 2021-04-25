// CSCI 3326 Project    Members: Mark Navalta, Stephen Pagayon, Enrique Manrique
// Main program

import java.io.IOException;
import java.sql.SQLException;

public class Main{
    public static void main(String args[]) throws SQLException, IOException
	{
        // Initialize databases
        RecipeBook rb = new RecipeBook();
        UserBook ub = new UserBook();

        new StartMenu();
        
	}
}
