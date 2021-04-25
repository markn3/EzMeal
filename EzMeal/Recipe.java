
public class Recipe {
    private String name;
    private int num_ingredients;
    private String pic;
    /*
    private String country;
    private String mealTime;
    private int time;
    private int servings;
    */
    
    // list of ingredients needed so probably string array.
    String [] ingredients = new String[15];

    // constructor
    public Recipe(String n, String pic, int n_i, String [] i) {
        this.name = n;
        this.pic = pic;
        this.num_ingredients = n_i;
        this.ingredients = i;

    }

    // setter and getter functions

    public void setName(String n){
        this.name = n;
    }

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

    /*

        System.out.println("\nIngredients for " + name +": ");
        for(int i = 0; i < 20; i++){
            if(ingredients[i] == null){
                break;
            }
            System.out.println(ingredients[i]);
        }  
    */


}