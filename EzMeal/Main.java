// CSCI 3326 Project    Members: Mark Navalta, Stephen Pagayon, Enrique Manrique
// Main program

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Initialzie recipe database into local string array at beginning
// Same with user database

public class App{
    public static void main(String args[]) throws SQLException
	{
        RecipeBook rb = new RecipeBook();
        UserBook ub = new UserBook();
        
		new MM();
	}
}
