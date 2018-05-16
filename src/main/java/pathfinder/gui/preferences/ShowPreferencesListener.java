package pathfinder.gui.preferences;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import pathfinder.gui.UserInterface;
import pathfinder.logic.Graph;

/**
 * Constructs the preferences dialog, and displays it to the user.
 *
 */
public class ShowPreferencesListener implements ActionListener {

    UserInterface gui;
    JFrame parent;
    JDialog dialog;
    GridPreferencesEditor prefs;
    JButton ok;
    JButton cancel;

    /**
     * Constructs a <code>ShowPreferencesListener</code>
     *
     * @param gui reference to the GUI object
     * @param parent reference to the frame. Used for positioning the settings
     * dialog.
     */
    public ShowPreferencesListener(UserInterface gui, JFrame parent, GridPreferencesEditor prefs) {
        this.gui = gui;
        this.parent = parent;
        this.prefs = prefs;
    }

    /**
     * Shows the preferences dialog
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        dialog = new JDialog(parent, "Preferences", true);
        //dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponents(dialog.getContentPane());

        dialog.pack();
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    private void addComponents(final Container pane) {
        addContent(pane);
        addButtonsPanel(pane);
    }

    private void addContent(final Container pane) {
        pane.add(prefs.getUI(), BorderLayout.CENTER);
    }

    private void addButtonsPanel(final Container pane) {
        ok = new JButton("OK");
        cancel = new JButton("Cancel");

        addActionListeners();

        JPanel buttons = new JPanel();
        buttons.add(ok);
        buttons.add(cancel);

        pane.add(buttons, BorderLayout.AFTER_LAST_LINE);
    }

    private void addActionListeners() {
        ok.addActionListener((e) -> {
            prefs.savePreferences();
            apply();
            dialog.setVisible(false);
        });

        cancel.addActionListener((e) -> {
            dialog.setVisible(false);
        });
    }

    private void apply() {
        int cols = prefs.getNumCols();
        int rows = prefs.getNumRows();
        Graph g = CreateEmptyGrid.create(cols, rows);

        gui.setGraph(g);
    }

}
