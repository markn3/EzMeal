
public class Recipe {
    private String name;
    private int num_ingredients;
    private String pic;

    // string list of ingredients.
    String [] ingredients = new String[15];

    // constructor
    public Recipe(String n, String pic, int n_i, String [] i) {
        this.name = n;
        this.pic = pic;
        this.num_ingredients = n_i;
        this.ingredients = i;

    }

    //getter functions
    public int getNumIngredients(){
        return num_ingredients;
    }

    public String getName(){
        return name;
    }

    public String getPic(){
        return pic;
    }
    public String[] getIngredients(){ 
        return ingredients;
    }

}