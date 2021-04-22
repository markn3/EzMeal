// CSCI 3326 Project    Members: Mark Navalta, Stephen Pagayon, Enrique Manrique
// Main program

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main{
    public static void main(String args[]) throws SQLException
	{
        // Initialize databases
        RecipeBook rb = new RecipeBook();
        UserBook ub = new UserBook();

		new Menu();
	}
}
