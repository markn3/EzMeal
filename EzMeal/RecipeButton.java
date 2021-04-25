import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class RecipeButton extends JButton {
    JPanel container = new JPanel();
    JLabel text = new JLabel();
    JLabel pic_label = new JLabel();

    RecipeButton(Recipe rp) throws IOException {
        pic_label.setSize(300,200);
        text.setText(rp.getName());
        text.setSize(300,200);
        text.setBackground(Color.GREEN);

        BufferedImage img = null;
        img = ImageIO.read(new File(rp.getPic()));
        Image dimg = img.getScaledInstance(pic_label.getWidth(), pic_label.getHeight(),
        Image.SCALE_SMOOTH);  
        pic_label.setIcon(new ImageIcon(dimg));

        container.setLayout(new BoxLayout(container,BoxLayout.Y_AXIS));
        container.add(text);
        container.add(Box.createRigidArea(new Dimension(0,5)));
        container.add(pic_label);

        add(container);
    }
}
