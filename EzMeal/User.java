import java.sql.*;
import java.util.List;

// User class

public class User{
    private String name;
    private String id;
    private String pw;
    int nr;                                   // number of recipes saved
    int ni;                                   // number of ingredients user has

    String [] saved_recipes = new String [10];    // Assuming max number of saved recipes is 10
    String [] usr_ingredients = new String [25];  // Assuming max number of ingredients (for now) is 25

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
                i = Integer.toString(j+1);
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

    public void updateIngredients(List <String> l)throws SQLException{
        // Starting from i11 to i35 are the ingredients
        String s1 = "jdbc:mysql://34.72.168.150:3306/UserData?useSSL=false";
		Connection connection = DriverManager.getConnection(s1, "root", "1234qwer");
		Statement stmt = connection.createStatement();

 
        for(int i = 0; i < l.size(); i++){
            String prev = Integer.toString(11+i);
            String update_string = ("UPDATE users SET i" + prev +" = '" + l.get(i) + "' where id = '" + id +"'");
            stmt.executeUpdate(update_string);
        }

        String update_num_ing = ("UPDATE users SET num_ing = '" + l.size() + "' WHERE id = '" + id +"'");
        stmt.executeUpdate(update_num_ing);

        for(int j = 0; j < (25-l.size()) ; j++){
            // Start i16
            String prev2 = Integer.toString(11+l.size()+j);
            String null_update = ("UPDATE users SET i" + prev2 +" = null WHERE id = '" + id + "'");
            stmt.executeUpdate(null_update);
        }

        connection.close();

    }
}