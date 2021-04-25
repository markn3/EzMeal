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
            String pic = result.getString(2);
            int num_ingredients = result.getInt(3);
            String [] ingredients = new String [15];
            int SQL_index = 4;
            int arr_index = 0;
            while(result.getString(SQL_index) != null){
                ingredients[arr_index] = result.getString(SQL_index);
                SQL_index++;
                arr_index++;
            }

            Recipe temp = new Recipe(recipe_name, pic, num_ingredients, ingredients);
            recipe_list[i] = temp;
            i++;
        }

        connection.close();
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

        stmt.executeUpdate("DELETE FROM recipes WHERE NAME = '" + recipe_name +"';");
        connection.close();
        System.out.println("Delete!");
    }

    // Returns recipe
    public Recipe [] IngredientMatchRecipes(String [] ingredients){
        Recipe [] matched_recipes = new Recipe[20];
        int [] recipe_points = new int[20];
        
        for(int i = 0; i < nr; i++){
            int points = 0;

            int num_recipe_ingredients = recipe_list[i].getNumIngredients();    // length of recipe i;
            String [] temp_recipes = recipe_list[i].getIngredients();           // Ingredients for recipe i;

            for(int j = 0; j < num_recipe_ingredients; j++){
                for(int k = 0; k <num_recipe_ingredients;k++){
                    if(temp_recipes[j].equals(ingredients[k])){
                        points++;
                    }
                }
            }
            recipe_points[i] = points;

        }

        // selection sort 
        for(int l = 0; l < 20; l++){
            int min_index = l;
            for(int m = l+1; m < 20; m++){
                if(recipe_points[m] < recipe_points[min_index]){
                    min_index = m;
                }
                // Swap recipes
                matched_recipes[min_index] = recipe_list[l];
                matched_recipes[l] = recipe_list[min_index];        

                // Swap ints
                int temp = recipe_points[min_index];
                recipe_points[min_index] = recipe_points[l];
                recipe_points[l] = temp;
            }
        }
        return matched_recipes;
    }
}
