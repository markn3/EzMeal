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

}