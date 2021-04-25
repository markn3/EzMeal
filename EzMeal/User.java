import java.sql.*;

// User class

public class User{
    private String name;
    private String id;
    private String pw;
    int nr;            // number of recipes saved
    int ni;            // number of ingredients user has

    String [] saved_recipes = new String [20];    // Assuming max number of saved recipes is 20
    String [] usr_ingredients = new String [20];  // Assuming max number of ingredients (for now) is 20

    public User(String n, String i, String p, int num_rec, int num_ing, String [] r, String [] ing){
        this.name = n;
        this.id = i;
        this.pw = p;
        this.nr = num_rec;
        this.ni = num_ing;
        this.saved_recipes = r;
        this.usr_ingredients = ing;
    }


    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public String getPw(){
        return pw;
    }
    
    public int getNumRec(){
        return nr;
    }

    public int getNumIng(){
        return ni;
    }

    public String [] getRecipes(){
        return saved_recipes;
    }

    public String [] getIngredients(){
        return usr_ingredients;
    }

    public void saveRecipe(String recipe) throws SQLException{
        String s1 = "jdbc:mysql://34.72.168.150:3306/UserData?useSSL=false";
		Connection connection = DriverManager.getConnection(s1, "root", "1234qwer");
		Statement stmt = connection.createStatement();

        saved_recipes[nr-1] = recipe;
        nr++;
        String i = Integer.toString(nr);
        String update_string = ("UPDATE users SET i"+ i +" = '" +recipe + "' where id = '" + id + "'");
        String update_num_recipes = ("UPDATE users SET num_rec = '" + nr + "' where id = '" + id + "'");
        stmt.executeUpdate(update_string);
        stmt.executeUpdate(update_num_recipes);
		connection.close();

		System.out.println("Add!");
    }

    public void deleteRecipe(String recipe)throws SQLException{
        String s1 = "jdbc:mysql://34.72.168.150:3306/UserData?useSSL=false";
		Connection connection = DriverManager.getConnection(s1, "root", "1234qwer");
		Statement stmt = connection.createStatement();

        String i = "a";
        for(int j = 0; j < nr; j++){
            if(saved_recipes[j].equals(recipe)){
                i = Integer.toString(j);
            }
        }

        String last_recipe = Integer.toString(nr);

        String update_string = ("UPDATE users SET i"+ i +" = '" + saved_recipes[nr-1] + "' where id = '" + id +"'");
        String setNull = ("UPDATE users SET i"+ last_recipe +" = null where id = '" + id + "'");
        String update_num_recipes = ("UPDATE users SET num_rec = '" + (nr-1) + "' where id = '" + id + "'");
        System.out.println(update_string);

        stmt.executeUpdate(update_string);
        stmt.executeUpdate(setNull);
        stmt.executeUpdate(update_num_recipes);
        connection.close();
        System.out.println("Delete!");
    }

}