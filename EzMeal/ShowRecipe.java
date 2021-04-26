import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class ShowRecipe extends JFrame{
    JButton saveButton = new JButton("Save recipe");
    JButton deleteButton = new JButton("Delete recipe from saved");
    User usr;
    Recipe r;

    ShowRecipe(User usr, Recipe r) throws IOException{
        super(r.getName());
        setSize(1080, 720);	// Window Resolution (1024, 768 || 600, 600)
        setResizable(false);	// Not Resize
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        this.usr = usr;
        this.r = r;

        boolean saved_recipe = false;
        for(int i = 0; i < usr.nr; i++){
            if(usr.saved_recipes[i].equals(r.getName())){
                saved_recipe = true;
                
            }
        }
        if(saved_recipe == true){  //  add delete button
            deleteButton.addActionListener(new deleteButton_clicked());
            add(deleteButton);
        }
        else{   // add save button
            saveButton.addActionListener(new saveButton_clicked());
            add(saveButton);
        }

        JList<String> ing = new JList<String>(r.getIngredients());

        JPanel container = new JPanel();
        container.setBackground(Color.RED);

        JPanel leftP = new JPanel();
        leftP.setLayout(new BoxLayout(leftP, BoxLayout.Y_AXIS));

        JLabel leftLabel = new JLabel("Ingredients:");
        leftP.add(leftLabel);
        leftP.add(ing);
        
        JLabel pic = new JLabel();
        pic.setSize(500,350);
        
        BufferedImage img = null;
        img = ImageIO.read(new File(r.getPic()));
        Image dimg = img.getScaledInstance(pic.getWidth(), pic.getHeight(),
        Image.SCALE_SMOOTH);  
        pic.setIcon(new ImageIcon(dimg));
        add(leftP, BorderLayout.WEST);
        add(pic, BorderLayout.NORTH);
        setVisible(true);
    }
    
    private class saveButton_clicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
			System.out.println("Save button clicked!");

            try {
                usr.saveRecipe(r.getName());
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

		}
	}

    private class deleteButton_clicked implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{			
			System.out.println("Delete button clicked!");

            try {
                usr.deleteRecipe(r.getName());
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

		}
	}
}
