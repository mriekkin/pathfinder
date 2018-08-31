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
import javax.swing.JRootPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import pathfinder.logic.CurrentGraph;
import pathfinder.logic.Graph;
import pathfinder.logic.Pair;

/**
 * A dialog which allows the user to create a new empty grid.
 * <p>
 * The user can create a new empty grid with specified dimensions, which
 * replaces the current grid. An empty grid provides a clean plate for creating
 * new maps. An empty grid consists of walkable nodes and has no obstacles. The
 * source and destination nodes are placed at default locations.
 * <p>
 * The layout of this dialog consists of a panel for the grid dimensions (the
 * content panel), and a panel for the OK and Cancel buttons (the buttons row).
 * The content panel has a tabular layout with one row for the number of columns
 * and one row for the number of rows. Both have labels and are controlled with
 * spinner controls.
 */
public class NewGridDialog {

    private static final String TITLE = "New grid";
    private static final int MIN_NUM_COLS_ROWS = 2;
    private static final int MAX_NUM_COLS_ROWS = 512;

    private JDialog dialog;
    private JSpinner numRows;
    private JSpinner numCols;
    private JButton ok;
    private final CurrentGraph current;

    /**
     * Constructs a <code>NewGridView</code>. The dimensions of the current
     * graph are used as default values. That is, the values of the controls are
     * matched to the dimensions of the current graph.
     *
     * @param current the current graph used by this application
     */
    public NewGridDialog(CurrentGraph current) {
        this.current = current;
    }

    /**
     * Returns the number of rows. Returns the value chosen on the spinner
     * control.
     *
     * @return the chosen number of rows
     */
    public int getNumRows() {
        return (int) numRows.getValue();
    }

    /**
     * Returns the number of columns. Returns the value chosen on the spinner
     * control.
     *
     * @return the chosen number of columns
     */
    public int getNumCols() {
        return (int) numCols.getValue();
    }

    /**
     * Constructs and displays this dialog.
     *
     * @param owner reference to a frame. Used for positioning this dialog.
     */
    public void show(JFrame owner) {
        buildGui(owner);
    }

    private void buildGui(JFrame owner) {
        dialog = new JDialog(owner, TITLE, true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addComponents(dialog.getContentPane());
        setDefaultButton(dialog.getRootPane());

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
        addGridDimensions(content);

        pane.add(content, BorderLayout.CENTER);
    }

    private void addGridDimensions(JPanel content) {
        JLabel numColsLabel = new JLabel("Columns");
        JLabel numRowsLabel = new JLabel("Rows");
        int initCols = clamp(current.getCols(), MIN_NUM_COLS_ROWS, MAX_NUM_COLS_ROWS);
        int initRows = clamp(current.getRows(), MIN_NUM_COLS_ROWS, MAX_NUM_COLS_ROWS);
        numCols = new JSpinner(new SpinnerNumberModel(initCols, MIN_NUM_COLS_ROWS, MAX_NUM_COLS_ROWS, 1));
        numRows = new JSpinner(new SpinnerNumberModel(initRows, MIN_NUM_COLS_ROWS, MAX_NUM_COLS_ROWS, 1));

        content.add(numColsLabel, getConstraints(0, 0));
        content.add(numRowsLabel, getConstraints(0, 1));
        content.add(numCols, getConstraints(1, 0));
        content.add(numRows, getConstraints(1, 1));
    }

    private int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    private void addButtonsRow(final Container pane) {
        JPanel buttons = new JPanel();
        addOkButton(buttons);
        addCancelButton(buttons);
        pane.add(buttons, BorderLayout.AFTER_LAST_LINE);
    }

    private void addOkButton(JPanel buttons) {
        ok = new JButton("OK");
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
        Graph graph = CreateNewGrid.create(cols, rows);

        current.setGraph(graph);
    }

    private void close() {
        dialog.dispose();
    }

    private void setDefaultButton(JRootPane rootPane) {
        rootPane.setDefaultButton(ok);
    }

    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.insets = new Insets(2, 2, 2, 2);

        return c;
    }

    /**
     * Creates an empty graph, and places the source and destination nodes to
     * default locations. It is the responsibility of this class to encode
     * unified default positions for the source and destination nodes.
     */
    private static class CreateNewGrid {

        /**
         * Creates an empty graph, and places the source and destination nodes
         * to default locations.
         *
         * @param cols number of columns
         * @param rows number of rows
         * @return an empty graph created as specified
         */
        public static Graph create(int cols, int rows) {
            Pair dimensions = new Pair(cols, rows);
            Pair source = new Pair(0, 0);
            Pair dest = new Pair(cols - 1, rows - 1);

            return new Graph(dimensions, source, dest);
        }

    }

}
