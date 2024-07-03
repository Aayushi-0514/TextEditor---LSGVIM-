import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
    // Text area
    JTextArea textArea;

    // Frame
    JFrame frame;

    // Constructor
    public TextEditor() {
        // Create a frame
        frame = new JFrame("Simple Text Editor");

        // Text component
        textArea = new JTextArea();

        // Create a menubar
        JMenuBar menuBar = new JMenuBar();

        // Create a menu for file
        JMenu fileMenu = new JMenu("File");

        // Create menu items
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        // Add action listeners
        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);

        // Add menu items to file menu
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);

        // Add file menu to menu bar
        menuBar.add(fileMenu);

        // Set menu bar to frame
        frame.setJMenuBar(menuBar);

        // Add text area to frame
        frame.add(textArea);

        // Set frame size
        frame.setSize(500, 500);

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the frame visible
        frame.setVisible(true);
    }

    // If a button is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "New" -> textArea.setText("");
            case "Open" -> {
                JFileChooser fileChooser = new JFileChooser();
                int response = fileChooser.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        textArea.read(reader, null);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error opening file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            case "Save" -> {
                JFileChooser fileChooser = new JFileChooser();
                int response = fileChooser.showSaveDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        textArea.write(writer);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error saving file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            case "Exit" -> System.exit(0);
        }
    }

    // Main class
    public static void main(String[] args) {
        new TextEditor();
    }
}
