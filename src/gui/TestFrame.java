package gui;
import java.awt.*;
import javax.swing.*;

public class TestFrame extends JFrame {
    public TestFrame() {
        super("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        Container contentPane = getContentPane();

        contentPane.add(new JButton("Uno"), BorderLayout.CENTER);
        contentPane.add(new JButton("Due"), BorderLayout.SOUTH);

        JButton b3 = new JButton("Tre");
        b3.setBounds(10, 100, 100, 60);

        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.add(b3, JLayeredPane.PALETTE_LAYER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                TestFrame f = new TestFrame();
                f.setVisible(true);
            }
        });
    }
}