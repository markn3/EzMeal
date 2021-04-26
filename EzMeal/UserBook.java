import java.sql.SQLException;
import java.sql.*;

public class UserBook {
    int nu;                          // Number of users
    User [] users = new User[20];    // Assuming max number of users is 20 (for now)
 
    public UserBook()throws SQLException{
        
        // Establish connection with SQL server and get data
        String s1 = "jdbc:mysql://34.72.168.150:3306/UserData?useSSL=false";
		Connection connection = DriverManager.getConnection(s1, "root", "1234qwer");
		Statement stmt = connection.createStatement();
		String sqlStatement = "SELECT * FROM users";
		ResultSet result = stmt.executeQuery(sqlStatement);

        // Parse through data and put in arrays
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
                temp_ingredients[k] = result.getString(16+k);
            }

            users[i] = new User(name, id, pw, num_recipes, num_ingredients, temp_recipes, temp_ingredients);
            i++;
        }
        nu = i;
        connection.close();
    }

    public void add_user(String name, String id, String pw)throws SQLException  {
        // establish connection
        String s1 = "jdbc:mysql://34.72.168.150:3306/UserData?useSSL=false";
		Connection connection = DriverManager.getConnection(s1, "root", "1234qwer");
		Statement stmt = connection.createStatement();

        // Build string
        String update_string = ("INSERT INTO users VALUES ('" + name + "','" + id + "','" + pw + "','" + 0 + "','" + 0 +"'");
        
        for(int i = 0; i < 35; i++){
            update_string += ", null";
        }
        update_string += ");";
        System.out.println("Update_string: " + update_string);
        
        // Execute string
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
    }

    public User valid_user(String id, String pw)throws SQLException{

        // Establish connection
        String s1 = "jdbc:mysql://34.72.168.150:3306/UserData?useSSL=false";
		Connection connection = DriverManager.getConnection(s1, "root", "1234qwer");
		Statement stmt = connection.createStatement();

        String sqlStatement = "SELECT * FROM users WHERE id = '" + id  + "' AND password = '" + pw  + "'";
		ResultSet result = stmt.executeQuery(sqlStatement);

        // If user exists
        if(result.next()){
            String usr_id = result.getString(2);    
            for(int i = 0; i < nu; i++){
                if(users[i].getId().equals(usr_id)){
                    connection.close();
                    return users[i];
                }
            }
            
        }
        else{
            System.out.println("NOT EQUAL");
        }
        connection.close();
        return null;

    }
}