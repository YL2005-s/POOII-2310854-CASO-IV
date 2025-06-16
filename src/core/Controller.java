package core;

import javax.swing.*;
import java.awt.*;

public abstract class Controller {
    protected static final JFrame mainFrame = new JFrame("Estación Meteorológica - Patrón Observer");
    private static final JPanel viewsViewer = new JPanel(new CardLayout());

    static {
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1000, 800);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(new BorderLayout());

        mainFrame.add(viewsViewer, BorderLayout.CENTER);
    }

    public abstract void run();

    public static void addView(String viewName, Component view) {
        viewsViewer.add(view, viewName);
    }

    public static void loadView(String viewName) {
        CardLayout cl = (CardLayout) viewsViewer.getLayout();
        cl.show(viewsViewer, viewName);
    }
}
