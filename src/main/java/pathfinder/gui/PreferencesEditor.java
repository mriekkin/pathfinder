package pathfinder.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * A dialog which allows the editing of a set of application preferences.
 * Currently the set of preferences consists of the cell size and corner-cutting
 * options.
 * <p>
 * The cell size option controls the size of a single cell on screen in pixels.
 * A larger cell size, naturally, allows one to see more detail. A smaller cell
 * size allows one to view much larger grids without the need for scrolling. The
 * default cell size is 20 pixels for each side of a square. The maximum is 30
 * pixels, and the minimum just 1 pixel. The minimum (1 pixel per cell) is
 * intented for viewing very large grids, such as the provided grids which
 * consist of 512x512 nodes.
 * <p>
 * The corner-cutting option controls whether it's possible to cross corners. In
 * other words, if corner-cutting is allowed, the shortest path can take
 * diagonal shortcuts around corners.
 * <p>
 * The current values of the options can be retrieved by calling
 * <code>getCellSize</code> and <code>isCornerCuttingAllowed</code>. These
 * values can be retrieved at any time, regardless of whether this dialog is
 * visible or not. This dialog maintains two sets of values: the values of the
 * controls, and the saved values of the options. Hence, the values of the
 * options will be saved only when (and if) the user presses OK. This dialog can
 * be made visible by calling <code>show</code>, and will be closed when the
 * user presses either OK or Cancel.
 * <p>
 * The layout of this dialog consists of a panel for the option controls (the
 * content panel), and a panel for the OK and Cancel buttons (the buttons row).
 * The content panel has a tabular layout with one row per option. The cell size
 * option has a label, and a spinner control for adjusting the cell size.
 * <p>
 * This class follows the observer design pattern. The observer pattern is
 * implemented with the <code>PropertyChangeListener</code> interface. Since
 * this class is an <i>observable</i> it sends notifications to registered
 * observers when a property of the class changes. To this end this class keeps
 * a reference to a <code>PropertyChangeSupport</code> instance.
 *
 * @see <a href="http://www.baeldung.com/java-observer-pattern">The Observer
 * Pattern in Java</a>
 */
public class PreferencesEditor {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /**
     * Adds a <code>PropertyChangeListener</code> for the specified property.
     *
     * @param propertyName the name of the property to listen on
     * @param listener the <code>PropertyChangeListener</code> to be added
     */
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    /**
     * Removes a <code>PropertyChangeListener</code> for the specified property.
     *
     * @param propertyName the name of the property that was listened on
     * @param listener the <code>PropertyChangeListener</code> to be removed
     */
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(propertyName, listener);
    }

    private static final String TITLE = "Preferences";
    private static final int MIN_CELL_SIZE = 1;
    private static final int MAX_CELL_SIZE = 30;

    private JDialog dialog;
    private JSpinner cellSizeSpin;
    private JCheckBox cornerCuttingCb;
    private JButton ok;
    private int cellSizeValue;
    private boolean cornerCuttingValue;

    /**
     * Constructs a <code>PreferencesEditor</code>, with the specified initial
     * values for the cell size and corner-cutting options.
     *
     * @param cellSize initial value for the cell size option
     * @param cornerCutting initial value for the corner-cutting option
     */
    public PreferencesEditor(int cellSize, boolean cornerCutting) {
        this.cellSizeValue = cellSize;
        this.cornerCuttingValue = cornerCutting;
    }

    /**
     * Returns the current value of the cell size option.
     *
     * @return the current value of the cell size option
     */
    public int getCellSize() {
        return cellSizeValue;
    }

    /**
     * Returns true if corner-cutting is allowed.
     *
     * @return true if corner-cutting is allowed
     */
    public boolean isCornerCuttingAllowed() {
        return cornerCuttingValue;
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
        addCellSize(content);
        addCornerCutting(content);

        pane.add(content, BorderLayout.CENTER);
    }

    private void addCellSize(JPanel content) {
        JLabel cellSizeLabel = new JLabel("Cell size");
        cellSizeSpin = new JSpinner(new SpinnerNumberModel(getCellSize(), MIN_CELL_SIZE, MAX_CELL_SIZE, 1));

        content.add(cellSizeLabel, getConstraints(0, 0, 1, 1));
        content.add(cellSizeSpin, getConstraints(1, 0, 1, 1));
    }

    private void addCornerCutting(JPanel content) {
        cornerCuttingCb = new JCheckBox("Corner-cutting", isCornerCuttingAllowed());

        content.add(cornerCuttingCb, getConstraints(0, 1, 2, 1));
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
        saveCellSize();
        saveCornerCutting();
    }

    private void saveCellSize() {
        int oldValue = cellSizeValue;
        cellSizeValue = (int) cellSizeSpin.getValue();

        pcs.firePropertyChange("cellSize", oldValue, cellSizeValue);
    }

    private void saveCornerCutting() {
        boolean oldValue = cornerCuttingValue;
        cornerCuttingValue = cornerCuttingCb.isSelected();

        pcs.firePropertyChange("corner-cutting", oldValue, cornerCuttingValue);
    }

    private void close() {
        dialog.dispose();
    }

    private void setDefaultButton(JRootPane rootPane) {
        rootPane.setDefaultButton(ok);
    }

    private GridBagConstraints getConstraints(int x, int y, int w, int h) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = w;
        c.gridheight = h;
        c.insets = new Insets(2, 2, 2, 2);

        return c;
    }

}
