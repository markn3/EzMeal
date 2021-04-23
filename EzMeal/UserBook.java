import java.sql.SQLException;
import java.sql.*;

public class UserBook {
    int nu;                          // Number of users
    User [] users = new User[20];    // Assuming max number of users is 20 (for now)
 
    public UserBook()throws SQLException{
        String s1 = "jdbc:mysql://34.72.168.150:3306/UserData?useSSL=false";
		Connection connection = DriverManager.getConnection(s1, "root", "1234qwer");
		Statement stmt = connection.createStatement();
		String sqlStatement = "SELECT * FROM users";
		ResultSet result = stmt.executeQuery(sqlStatement);

        int i = 0;
        while(result.next()){
            String name = result.getString(1);
            String id = result.getString(2);
            String pw = result.getString(3);
            int num_recipes = result.getInt(4);
            int num_ingredients = result.getInt(5);

            String [] temp_recipes = new String[num_recipes];
            String [] temp_ingredients = new String[num_ingredients];

            for(int j = 0; j < num_recipes; j++){
                temp_recipes[j] = result.getString(j+6);
            }

            for(int k = 0; k < num_ingredients; k++){
                temp_ingredients[k] = result.getString(7+num_recipes);
            }

            users[i] = new User(name, id, pw, num_recipes, num_ingredients, temp_recipes, temp_ingredients);
            i++;
        }
    }

    public void add_user(String name, String id, String pw)throws SQLException  {
        String s1 = "jdbc:mysql://34.72.168.150:3306/UserData?useSSL=false";
		Connection connection = DriverManager.getConnection(s1, "root", "1234qwer");
		Statement stmt = connection.createStatement();
        String update_string = ("INSERT INTO users VALUES ('" + name + "','" + id + "','" + pw + "','" + 0 + "','" + 0 +"'");
        for(int i = 0; i < 15; i++){
            update_string += ", null";
        }
        update_string += ");";
        System.out.println("Update_string: " + update_string);
        
        
		stmt.executeUpdate(update_string);
		connection.close();
		System.out.println("Add!");
    }

    public void delete_user(String id, String pw)throws SQLException{
        String s1 = "jdbc:mysql://34.72.168.150:3306/UserData?useSSL=false";
		Connection connection = DriverManager.getConnection(s1, "root", "1234qwer");
		Statement stmt = connection.createStatement();

        stmt.executeUpdate("DELETE FROM users WHERE pw = '" + pw +"';");
        connection.close();
        System.out.println("Delete!");
        /* Create authentication for account deletion
		String sqlStatement = "SELECT PASSWORD FROM recipes WHERE ID = '" + id +"'";
		ResultSet result = stmt.executeQuery(sqlStatement);
        if(result.getString() != null){

        }
        */
    }
}