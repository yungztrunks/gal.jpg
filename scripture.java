import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Scripture extends JPanel {

    private BufferedImage image;

    public Scripture() throws IOException {
        image = ImageIO.read(new File("gal.jpg"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int mountX = 300;
        int mountY = 300;

        BufferedImage scaled = new BufferedImage(mountX, mountY, BufferedImage.TYPE_INT_RGB);
        Graphics2D sg = scaled.createGraphics();
        sg.drawImage(image, 0, 0, mountX, mountY, null);
        sg.dispose();

        double w = getWidth() / (double) mountX;
        double h = getHeight() / (double) mountY;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.WHITE);

        for (int x = 0; x < mountX; x++) {
            for (int y = 0; y < mountY; y++) {

                int rgb = scaled.getRGB(x, y);

                int r = (rgb >> 16) & 0xFF;
                int gCol = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;

                // Approximate Processing brightness
                double brightness = (r + gCol + b) / 3.0;

                if (brightness > 83) {
                    double conf = brightness / 255.0;

                    int drawX = (int) (x * w);
                    int drawY = (int) (y * h);
                    int drawW = (int) (w * conf);
                    int drawH = (int) h;

                    g2.fillOval(drawX, drawY, drawW, drawH);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("gal.jpg");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);

        frame.add(new Scripture());

        frame.setVisible(true);
    }
}