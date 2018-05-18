package pathfinder.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;

/**
 * Shows a dialog which allows the user to create a new grid.
 *
 */
public class ShowNewGridAction extends AbstractAction {

    private final UserInterface gui;
    private final JFrame parent;
    private int initRows;
    private int initCols;

    /**
     * Constructs a <code>NewGridAction</code>
     *
     * @param gui reference to a GUI object, which should be notififed of
     * changes to the graph.
     * @param parent reference to a frame. Used for positioning this dialog.
     * @param initCols initial value for the number of columns
     * @param initRows initial value for the number of rows
     */
    public ShowNewGridAction(UserInterface gui, JFrame parent, int initCols, int initRows) {
        super("New");
        putValue(SHORT_DESCRIPTION, "Create an empty grid");
        putValue(MNEMONIC_KEY, KeyEvent.VK_N);
        this.gui = gui;
        this.parent = parent;
        setInitialValues(initCols, initRows);
    }

    /**
     * Shows the new grid dialog
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new NewGridView(gui, parent, initCols, initRows);
    }

    public final void setInitialValues(int initCols, int initRows) {
        this.initRows = initRows;
        this.initCols = initCols;
    }

}
