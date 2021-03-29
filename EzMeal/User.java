
// User class

public class User{
    private String name;
    private String id;
    private String pw;
    int nr = 20;            // number of recipes saved
    int ni = 20;            // number of user has ingredients

    String [] saved_recipes = new String [nr];
    String [] usr_ingredients = new String [ni];

    public User(String n, String i, String p, String [] r, String [] ing){
        this.name = n;
        this.id = i;
        this.pw = p;
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
    
}