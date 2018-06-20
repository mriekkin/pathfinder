package pathfinder.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

/**
 * An action which displays the preferences editor.
 * 
 * @see PreferencesEditor
 */
public class ShowPreferencesAction extends AbstractAction {

    private final JFrame owner;
    private final PreferencesEditor prefs;

    /**
     * Constructs a <code>ShowPreferencesAction</code>.
     *
     * @param owner reference to a frame. Used for positioning this dialog.
     * @param prefs the preferences editor used by this application
     */
    public ShowPreferencesAction(JFrame owner, PreferencesEditor prefs) {
        super("Preferences");
        putValue(SHORT_DESCRIPTION, "Show application settings");
        putValue(MNEMONIC_KEY, KeyEvent.VK_P);
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        this.owner = owner;
        this.prefs = prefs;
    }

    /**
     * Shows the preferences editor dialog
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        prefs.show(owner);
    }

}
