import javax.swing.JCheckBox;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Ingredients extends JFrame{
    JButton done = new JButton("Done");
    JCheckBox cheeseBox = new JCheckBox("Cheese");
    List <String> l;
    JCheckBox [] checklist;
    User usr;


    public Ingredients(User usr, List <String> l){
        super("Ingredients");
        setSize(1080,520);
        setResizable(false);
        setLayout(new FlowLayout());

        this.usr = usr;
        this.l = l;
        this.checklist = new JCheckBox [l.size()];

        done.addActionListener(new done_clicked());
        
        JPanel botPanel = new JPanel();
        botPanel.setSize(600, 220);
        botPanel.setBackground(Color.blue);

        botPanel.add(done);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(0, 5, 5, 5));
        topPanel.setSize(600, 300);

        add(cheeseBox);
        for(int i = 0; i < l.size(); i++){
            checklist[i] = new JCheckBox(l.get(i));
            topPanel.add(checklist[i]);
        }

        add(topPanel, BorderLayout.NORTH);
        add(botPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private class done_clicked implements ActionListener{
        public void actionPerformed(ActionEvent e){
            List <String> saved_ingredients = new ArrayList<String>();
            for(int i = 0; i < checklist.length; i++){
                if(checklist[i].isSelected()){
                    saved_ingredients.add(checklist[i].getText());
                }
            }
            
            try {
                usr.updateIngredients(saved_ingredients);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    public static void main(String [] args) throws SQLException{
        RecipeBook rb = new RecipeBook();
        UserBook ub = new UserBook();
        List <String> yup = rb.getEveryIngredient();
        new Ingredients(ub.users[0] , yup);
    }

}
