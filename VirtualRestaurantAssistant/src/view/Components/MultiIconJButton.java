package view.Components;

import javax.swing.*;
import java.awt.*;

public class MultiIconJButton extends JButton {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icon[] icons;
    
    public MultiIconJButton(String text, Icon[] icons) {
        super(text);
        this.icons = icons;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int totalIconWidth = 0;
        for (Icon icon : icons) {
            totalIconWidth += icon.getIconWidth() + 5; // add 5 for padding between icons
        }
        int x = getWidth() - totalIconWidth; // start drawing icons from the right
        int y = (getHeight() - icons[0].getIconHeight()) / 2; // center the icons vertically
        for (Icon icon : icons) {
            icon.paintIcon(this, g, x, y); // draw the icon
            x += icon.getIconWidth() + 5; // increment the x position for the next icon
        }
    }

    
    @Override
    public Dimension getPreferredSize() {
        Dimension superSize = super.getPreferredSize();
        int width = superSize.width + 5; // add some padding for the icons
        int height = Math.max(superSize.height, getMaxIconHeight()) + 5; // add some padding for the icons
        return new Dimension(width, height);
    }
    
    private int getMaxIconHeight() {
        int maxHeight = 0;
        for (Icon icon : icons) {
            maxHeight = Math.max(maxHeight, icon.getIconHeight());
        }
        return maxHeight;
    }
}