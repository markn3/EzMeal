import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class RecipeBook {
    int nr;                                         
    Recipe [] recipe_list = new Recipe [20];        

    public RecipeBook()throws SQLException{     // Connect with database and initialize recipes
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
        nr = i;
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
        Recipe [] matched_recipes = recipe_list;
        int [] recipe_points = new int[20];
        
        for(int i = 0; i < nr; i++){
            recipe_points[i] = 0;

            int num_recipe_ingredients = recipe_list[i].getNumIngredients();    // length of recipe i;
            String [] temp_recipes = recipe_list[i].getIngredients();           // Ingredients for recipe i;

            for(int j = 0; j < ingredients.length; j++){
                for(int k = 0; k < num_recipe_ingredients; k++){
                    if(temp_recipes[k].equals(ingredients[j])){
                        recipe_points[i]++;
                    }
                }
            }

        }

        // selection sort 
        for(int l = 0; l < 20; l++){
            int greatest = l;
            for(int m = l+1; m < 20; m++){
                if(recipe_points[m] > recipe_points[greatest]){
                    greatest = m;
                }
                // Swap recipes
                // Greater points first
                Recipe temp_r = matched_recipes[greatest];
                matched_recipes[greatest] = recipe_list[l];
                matched_recipes[l] = temp_r;        

                // Swap ints
                int temp = recipe_points[greatest];
                recipe_points[greatest] = recipe_points[l];
                recipe_points[l] = temp;
            }
        }
        for(int remove = 0; remove < matched_recipes.length; remove++){
            if(recipe_points[remove] == 0){
                matched_recipes[remove] = null;
            }
        }

        for(int yes = 0; yes < matched_recipes.length; yes++){
            if(matched_recipes[yes] == null){
                break;
            }
        }
        return matched_recipes;
    }

    public List <String> getEveryIngredient(){
        List<String> all_recipe_ingredients = new ArrayList<String>();
        for(int i = 0; i < nr; i++){
            int recipe_ing_num = recipe_list[i].getNumIngredients();
            for(int j = 0; j < recipe_ing_num; j++){
                if(!(all_recipe_ingredients.contains(recipe_list[i].getIngredients()[j]))){
                    all_recipe_ingredients.add(recipe_list[i].getIngredients()[j]);
                }
            }
        }
        return all_recipe_ingredients;
    }
}
