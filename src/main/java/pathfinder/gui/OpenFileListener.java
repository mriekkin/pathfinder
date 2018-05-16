package pathfinder.gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Path;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import pathfinder.io.GraphReader;
import pathfinder.logic.Graph;

/**
 * Shows the open file dialog, and reads the selected file
 * 
 */
public class OpenFileListener implements ActionListener {

    private final UserInterface gui;
    private final Component parent;
    private final JFileChooser fileChooser;

    /**
     * Constructs an OpenFileListener
     * 
     * @param gui reference to the GUI object
     * @param parent reference to the frame. Used for positioning the file chooser.
     */
    public OpenFileListener(UserInterface gui, Component parent) {
        this.gui = gui;
        this.parent = parent;
        this.fileChooser = new JFileChooser();
    }

    /**
     * Shows the open file dialog, and reads the selected file
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showOpenDialog(parent);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;
        }

        Path path = fileChooser.getSelectedFile().toPath();
        openFile(path);
    }

    private void openFile(Path path) {
        try {
            Graph g = GraphReader.readFile(path);
            gui.setGraph(g);
        } catch (IOException e) {
            showErrorMessage(e);
        }
    }

    private void showErrorMessage(IOException e) {
        String title = "Open file error";
        String msg = "Cannot open the file:\n\n" + e.toString();

        JOptionPane.showMessageDialog(parent, msg, title, JOptionPane.ERROR_MESSAGE);
    }

}
