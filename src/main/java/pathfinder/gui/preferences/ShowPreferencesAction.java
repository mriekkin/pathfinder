package pathfinder.gui.preferences;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;
import pathfinder.gui.UserInterface;

/**
 * Constructs the preferences dialog, and displays it to the user.
 *
 */
public class ShowPreferencesAction extends AbstractAction {
    
    UserInterface gui;
    JFrame owner;
    JDialog dialog;
    PreferencesView prefs;

    /**
     * Constructs a <code>ShowPreferencesAction</code>
     *
     * @param gui reference to the GUI object
     * @param owner reference to a frame. Used for positioning this dialog.
     * @param prefs
     */
    public ShowPreferencesAction(UserInterface gui, JFrame owner, PreferencesView prefs) {
        super("Settings");
        putValue(SHORT_DESCRIPTION, "Show application settings");
        putValue(MNEMONIC_KEY, KeyEvent.VK_P);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        this.gui = gui;
        this.owner = owner;
        this.prefs = prefs;
    }

    /**
     * Shows the preferences dialog
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        prefs.show(owner);
    }

}
