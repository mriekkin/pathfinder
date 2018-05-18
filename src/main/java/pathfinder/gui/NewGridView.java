package pathfinder.gui;

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
import pathfinder.logic.Graph;

public class NewGridView {

    private static final String TITLE = "New grid";

    private JDialog dialog;
    private JSpinner numRows;
    private JSpinner numCols;
    private final int initRows;
    private final int initCols;
    private final UserInterface gui;

    /**
     * Constructs a <code>NewGridView</code>. Constructs and displays a dialog
     * which allows the user to create a new grid.
     *
     * @param gui reference to a GUI object, which should be notififed of
     * changes to the graph.
     * @param owner reference to a frame. Used for positioning this dialog.
     * @param initCols initial value for the number of columns
     * @param initRows initial value for the number of rows
     */
    public NewGridView(UserInterface gui, JFrame owner, int initCols, int initRows) {
        this.gui = gui;
        this.initRows = initRows;
        this.initCols = initCols;
        buildGui(owner);
    }

    public int getNumRows() {
        return (int) numRows.getValue();
    }

    public int getNumCols() {
        return (int) numCols.getValue();
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
        addGridSize(content);

        pane.add(content, BorderLayout.CENTER);
    }

    private void addGridSize(JPanel content) {
        JLabel numColsLabel = new JLabel("Columns");
        JLabel numRowsLabel = new JLabel("Rows");
        numCols = new JSpinner(new SpinnerNumberModel(initCols, 10, 512, 1));
        numRows = new JSpinner(new SpinnerNumberModel(initRows, 10, 512, 1));

        content.add(numColsLabel, getConstraints(0, 0));
        content.add(numRowsLabel, getConstraints(0, 1));
        content.add(numCols, getConstraints(1, 0));
        content.add(numRows, getConstraints(1, 1));
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
        int cols = getNumCols();
        int rows = getNumRows();
        Graph g = CreateNewGrid.create(cols, rows);

        gui.setGraph(g);
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
