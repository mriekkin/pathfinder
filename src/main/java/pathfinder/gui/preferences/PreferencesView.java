package pathfinder.gui.preferences;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import pathfinder.gui.UserInterface;

public class PreferencesView {

    private static final String TITLE = "Settings";

    private UserInterface gui;
    private JDialog dialog;
    private JSpinner cellSizeSpin;
    private int cellSizeValue;

    public PreferencesView(UserInterface gui, int cellSize) {
        this.gui = gui;
        this.cellSizeValue = cellSize;
    }

    public int getCellSize() {
        return cellSizeValue;
    }

    public void show(JFrame owner) {
        buildGui(owner);
    }

    private void buildGui(JFrame owner) {
        dialog = new JDialog(owner, TITLE, true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addComponents(dialog.getContentPane());

        dialog.pack();
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    private void addComponents(final Container pane) {
        addContent(pane);
        addButtonsRow(pane);
    }

    private void addContent(final Container pane) {
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        addCellSize(content);

        pane.add(content, BorderLayout.CENTER);
    }

    private void addCellSize(JPanel content) {
        JLabel cellSizeLabel = new JLabel("Cell size");
        cellSizeSpin = new JSpinner(new SpinnerNumberModel(getCellSize(), 1, 30, 1));

        content.add(cellSizeLabel, getConstraints(0, 2));
        content.add(cellSizeSpin, getConstraints(1, 2));
    }

    private void addButtonsRow(final Container pane) {
        JPanel buttons = new JPanel();
        addOkButton(buttons);
        addCancelButton(buttons);
        pane.add(buttons, BorderLayout.AFTER_LAST_LINE);
    }

    private void addOkButton(JPanel buttons) {
        JButton ok = new JButton("OK");
        ok.addActionListener((e) -> {
            okAction();
            close();
        });

        buttons.add(ok);
    }

    private void addCancelButton(JPanel buttons) {
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener((e) -> {
            close();
        });

        buttons.add(cancel);
    }

    private void okAction() {
        cellSizeValue = (int) cellSizeSpin.getValue();

        gui.resize();
    }

    private void close() {
        dialog.dispose();
    }

    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.insets = new Insets(2, 2, 2, 2);

        return c;
    }

}
