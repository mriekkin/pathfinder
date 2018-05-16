package pathfinder.gui.preferences;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.prefs.Preferences;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GridPreferencesEditor implements PreferencesEditor {

    private static final String TITLE = "Grid Settings";

    // Preference keys
    private static final String NUM_ROWS = "num_rows";
    private static final String NUM_COLS = "num_cols";
    private static final String CELL_SIZE = "cell_size";

    // Default values
    private static final int NUM_ROWS_DEFAULT = 15;
    private static final int NUM_COLS_DEFAULT = 30;
    private static final int CELL_SIZE_DEFAULT = 20;

    private final Preferences prefs;
    private JSpinner numRows;
    private JSpinner numCols;
    private JSpinner cellSize;

    public GridPreferencesEditor() {
        this.prefs = Preferences.userNodeForPackage(GridPreferencesEditor.class);
    }

    @Override
    public JComponent getUI() {
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());

        addGridSize(content);
        addCellSize(content);

        matchGuiToStoredPreferences();

        return content;
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public void savePreferences() {
        prefs.putInt(NUM_ROWS, (int) numRows.getValue());
        prefs.putInt(NUM_COLS, (int) numCols.getValue());
        prefs.putInt(CELL_SIZE, (int) cellSize.getValue());
    }

    @Override
    public void matchGuiToDefaultPreferences() {

    }

    private void matchGuiToStoredPreferences() {
        numRows.setValue(getNumRows());
        numCols.setValue(getNumCols());
        cellSize.setValue(getCellSize());
    }
    
    public int getNumRows() {
        return prefs.getInt(NUM_ROWS, NUM_ROWS_DEFAULT);
    }

    public int getNumCols() {
        return prefs.getInt(NUM_COLS, NUM_COLS_DEFAULT);
    }

    public int getCellSize() {
        return prefs.getInt(CELL_SIZE, CELL_SIZE_DEFAULT);
    }
    
    private void addGridSize(JPanel content) {
        JLabel numColsLabel = new JLabel("Columns");
        JLabel numRowsLabel = new JLabel("Rows");
        numCols = new JSpinner(new SpinnerNumberModel(getNumCols(), 10, 512, 1));
        numRows = new JSpinner(new SpinnerNumberModel(getNumRows(), 10, 512, 1));

        content.add(numColsLabel, getConstraints(0, 0));
        content.add(numRowsLabel, getConstraints(0, 1));
        content.add(numCols, getConstraints(1, 0));
        content.add(numRows, getConstraints(1, 1));

    }

    private void addCellSize(JPanel content) {
        JLabel cellSizeLabel = new JLabel("Cell size");
        cellSize = new JSpinner(new SpinnerNumberModel(getCellSize(), 1, 30, 1));

        content.add(cellSizeLabel, getConstraints(0, 2));
        content.add(cellSize, getConstraints(1, 2));
    }

    private GridBagConstraints getConstraints(int x, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.insets = new Insets(2, 2, 2, 2);

        return c;
    }

}
