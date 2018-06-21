package pathfinder.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Path;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import pathfinder.io.GraphReader;
import pathfinder.logic.CurrentGraph;
import pathfinder.logic.Graph;

/**
 * An action which shows the open file dialog, and loads the selected file.
 * <p>
 * The user can load a saved file. If the operation is successful, the loaded
 * file will replace the current grid. The file contains the graph, and the
 * positions of the source and destination nodes. Hence, loading a file will
 * restore the graph as it was when it was saved.
 *
 * @see GraphReader
 */
public class OpenFileAction extends AbstractAction {

    private final JFrame owner;
    private final CurrentGraph current;
    private final JFileChooser fileChooser;

    /**
     * Constructs an <code>OpenFileAction</code>
     *
     * @param owner reference to a frame. Used for positioning the file chooser.
     * @param current the current graph used by this application
     */
    public OpenFileAction(JFrame owner, CurrentGraph current) {
        super("Open");
        putValue(SHORT_DESCRIPTION, "Open a file");
        putValue(MNEMONIC_KEY, KeyEvent.VK_O);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        this.owner = owner;
        this.current = current;
        this.fileChooser = new JFileChooser();
    }

    /**
     * Shows the open file dialog, and loads the selected file
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showOpenDialog(owner);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;
        }

        Path path = fileChooser.getSelectedFile().toPath();
        openFile(path);
    }

    private void openFile(Path path) {
        try {
            Graph graph = GraphReader.readFile(path);
            current.setGraph(graph);
        } catch (IOException e) {
            showErrorMessage(e);
        }
    }

    private void showErrorMessage(IOException e) {
        String title = "Open file error";
        String msg = "Cannot open the file:\n\n" + e.toString();

        JOptionPane.showMessageDialog(owner, msg, title, JOptionPane.ERROR_MESSAGE);
    }

}
