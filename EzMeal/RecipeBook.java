import java.sql.SQLException;
import java.sql.*;

public class RecipeBook {
    int nr;                                         
    Recipe [] recipe_list = new Recipe [20];        // assuming 30 is the max number of recipes (for now)

    public RecipeBook()throws SQLException{
        String s1 = "jdbc:mysql://34.72.168.150:3306/RecipeData?useSSL=false";
		Connection connection = DriverManager.getConnection(s1, "root", "1234qwer");
		Statement stmt = connection.createStatement();
		String sqlStatement = "SELECT * FROM recipes";
		ResultSet result = stmt.executeQuery(sqlStatement);

        int i = 0;
        while(result.next()){
            String recipe_name = result.getString(1);
            int num_ingredients = result.getInt(2);
            String [] ingredients = new String [15];
            int SQL_index = 3;
            int arr_index = 0;
            while(result.getString(SQL_index) != null){
                ingredients[arr_index] = result.getString(SQL_index);
                SQL_index++;
                arr_index++;
            }

            Recipe temp = new Recipe(recipe_name,num_ingredients, ingredients);
            recipe_list[i] = temp;
            i++;
        }

        connection.close();
		System.out.println("Displaying all");
    }

    public void add_recipe(String recipe_name, int num_ing, String ... i) throws SQLException{
		String s1 = "jdbc:mysql://34.72.168.150:3306/RecipeData?useSSL=false";
		Connection connection = DriverManager.getConnection(s1, "root", "1234qwer");
		Statement stmt = connection.createStatement();
        String update_string = "INSERT INTO recipes Values('" + recipe_name +"','" + num_ing;
        if(num_ing != 0){
            for(String ing: i){
                String ing_string = ",'" + i;
                update_string += ing_string;
            }
        }

        for(int j = 15-num_ing; j >0; j++){
            String null_string = ", null";
            update_string += null_string;
        }


		stmt.executeUpdate(update_string + ");");
		connection.close();
		System.out.println("Add!");
    }

    public void delete_recipe(String recipe_name) throws SQLException{
        String s1 = "jdbc:mysql://34.72.168.150:3306/RecipeData?useSSL=false";
		Connection connection = DriverManager.getConnection(s1, "root", "1234qwer");
		Statement stmt = connection.createStatement();

        stmt.executeUpdate("DELETE FROM cars WHERE NAME = '" + recipe_name +"';");
        connection.close();
        System.out.println("Delete!");
    }
}
