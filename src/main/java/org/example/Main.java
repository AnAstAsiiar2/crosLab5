package org.example;

import org.example.data.PostManager;
import org.example.ui.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        PostManager collection = new PostManager();
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow(collection);
            mainWindow.setVisible(true);
        });
    }
}