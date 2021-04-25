import java.awt.event.*;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class ShowRecipe extends JFrame{

    ShowRecipe(Recipe r) throws IOException{
        super(r.getName());
        setSize(1080, 720);	// Window Resolution (1024, 768 || 600, 600)
        setResizable(false);	// Not Resize
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

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
    
}
