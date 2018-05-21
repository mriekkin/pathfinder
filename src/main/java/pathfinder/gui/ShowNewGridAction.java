package pathfinder.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import pathfinder.logic.CurrentGraph;

/**
 * Shows a dialog which allows the user to create a new grid.
 *
 */
public class ShowNewGridAction extends AbstractAction {

    private final JFrame owner;
    private final CurrentGraph current;

    /**
     * Constructs a <code>NewGridAction</code>
     *
     * @param owner reference to a frame. Used for positioning this dialog.
     * @param current
     */
    public ShowNewGridAction(JFrame owner, CurrentGraph current) {
        super("New");
        putValue(SHORT_DESCRIPTION, "Create an empty grid");
        putValue(MNEMONIC_KEY, KeyEvent.VK_N);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        this.owner = owner;
        this.current = current;
    }

    /**
     * Shows the new grid dialog
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new NewGridView(owner, current);
    }

}
