
public class Recipe {
    private String name;
    private String country;
    private String mealTime;
    private int time;
    private int servings;
    
    // list of ingredients needed so probably string array.
    String [] ingredients = new String[20];

    // constructor
    public Recipe(String n, String [] i) {
        this.name = n;
        this.ingredients = i;
    }

    // setter and getter functions

    public void setName(String n){
        this.name = n;
    }

    public String getName(){
        return name;
    }

    public void getIngredients(){
    System.out.println("\nIngredients for " + name +": ");
    for(int i = 0; i < 20; i++){
        if(ingredients[i] == null){
            break;
        }
        System.out.println(ingredients[i]);
    }    }

}
