package pathfinder.gui.grid;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.pathfinders.Pathfinder;

/**
 * A panel which displays the grid. This panel is the central view for the user
 * and the most important element of the user interface. It displays the graph
 * including walkable nodes and obstacles. The source and destination nodes are
 * painted with separate colors. After a pathfinding operation it also displays
 * the results of the chosen algorithm.
 */
public class GridPanel extends JPanel {

    private static final Color borderColor = Color.BLACK;
    private static final Color defaultColor = new Color(0xffffff);
    private static final Color sourceColor = new Color(0x00dd00);
    private static final Color destColor = new Color(0xee4400);
    private static final Color obstacleColor = new Color(0x808080);
    private static final Color visitedColor = new Color(0xafeeee);
    private static final Color pathColor = Color.YELLOW;
    private static final BasicStroke pathStroke = new BasicStroke(2.5f);

    private int borderSize;
    private int cellSize;
    private BasicStroke borderStroke;

    private Graph g;
    private Pathfinder pathfinder;

    /**
     * Constructs a <code>GridPanel</code> with the specified graph, pathfinder
     * and cell size.
     * 
     * @param g the current graph
     * @param pathfinder the current pathfinder
     * @param cellSize the size of a single cell on screen in pixels
     */
    public GridPanel(Graph g, Pathfinder pathfinder, int cellSize) {
        reset(g, pathfinder, cellSize);
    }

    /**
     * Resizes this panel to fit the new graph and cell size.
     * 
     * @param g the current graph
     * @param pathfinder the current pathfinder
     * @param cellSize the size of a single cell on screen in pixels
     */
    public void reset(Graph g, Pathfinder pathfinder, int cellSize) {
        this.g = g;
        this.pathfinder = pathfinder;
        initSizes(cellSize);

        this.setPreferredSize(new Dimension(computeWidth(), computeHeight()));
        this.setDoubleBuffered(true);
    }

    private void initSizes(int cellSize) {
        if (cellSize < 0) throw new IllegalArgumentException("Negative cell size: " + cellSize);
        this.cellSize = cellSize;
        this.borderSize = getBorderSize(cellSize);
        this.borderStroke = new BasicStroke(borderSize);
    }

    private int getBorderSize(int squareSize) {
        if (squareSize == 1) return 0;
        if (squareSize < 5) return 1;
        return 1;
    }

    /**
     * Updates the pathfinder used by this grid panel.
     * 
     * @param pathfinder the pathfinder to be registered as the current pathfinder
     */
    public void setPathfinder(Pathfinder pathfinder) {
        this.pathfinder = pathfinder;
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        Graphics2D g2 = (Graphics2D) gr;
        paintSquares(g2);
        if (borderSize > 0)
            paintBorders(g2);
        paintPath(g2);
    }

    private void paintBorders(Graphics2D g2) {
        g2.setColor(borderColor);
        g2.setStroke(borderStroke);

        int cellSize = getTotalSize();
        int width = computeWidth();
        int height = computeHeight();

        for (int y = 0; y <= g.getRows(); y++) {
            g2.drawLine(0, y * cellSize, width, y * cellSize);
        }

        for (int x = 0; x <= g.getCols(); x++) {
            g2.drawLine(x * cellSize, 0, x * cellSize, height);
        }
    }

    private int getTotalSize() {
        return borderSize + cellSize;
    }

    private int computeWidth() {
        return getTotalSize() * g.getCols() + borderSize;
    }

    private int computeHeight() {
        return getTotalSize() * g.getRows() + borderSize;
    }

    private void paintSquares(Graphics2D g2) {
        for (int y = 0; y < g.getRows(); y++) {
            for (int x = 0; x < g.getCols(); x++) {
                paintNode(g2, g.getNode(x, y));
            }
        }
    }

    private void paintNode(Graphics2D g2, Node node) {
        int totalSize = getTotalSize();
        int lastRow = (g.getRows()-1) * totalSize + borderSize;
        int fillX = node.x() * totalSize + borderSize;
        int fillY = node.y() * totalSize + borderSize;
        fillY = lastRow - fillY;
        g2.setColor(getNodeColor(node));
        g2.fillRect(fillX, fillY, cellSize, cellSize);
    }

    private Color getNodeColor(Node node) {
        if (node.equals(g.getSource())) return sourceColor;
        if (node.equals(g.getDest())) return destColor;
        if (!node.isWalkable()) return obstacleColor;
        if (pathfinder.getVisited(node)) return visitedColor;

        return defaultColor;
    }

    private void paintPath(Graphics2D g2) {
        List<Node> path = pathfinder.getPath();
        if (path.isEmpty()) return;

        int[] xPoints = new int[path.size()];
        int[] yPoints = new int[path.size()];
        final int totalSize = getTotalSize();
        final int lastRow = g.getRows() * totalSize;
        int i = 0;
        for (Node node : path) {
            xPoints[i] = node.x() * totalSize + borderSize + cellSize/2;
            yPoints[i] = node.y() * totalSize + borderSize + cellSize/2;
            yPoints[i] = lastRow - yPoints[i];
            i++;
        }

        PaintPathPolyline(g2, xPoints, yPoints, path);
    }

    private void PaintPathPolyline(Graphics2D g2, int[] xPoints, int[] yPoints, List<Node> path) {
        g2.setColor(pathColor);
        g2.setStroke(pathStroke);
        g2.drawPolyline(xPoints, yPoints, path.size());
    }

    /**
     * Returns the node located at the specified position, if any. This method
     * can be used to convert positions on screen into node objects. The
     * coordinates of each position are specified relative to this panel.
     * 
     * @param pointX x-coordinate of the point relative to this panel
     * @param pointY y-coordinate of the point relative to this panel
     * @return the node at the specified position. Returns <code>null</code> if
     * the specified point is outside the boundaries of this grid.
     */
    protected Node getNode(int pointX, int pointY) {
        int x = pointX / getTotalSize();
        int y = pointY / getTotalSize();
        y = g.getRows() - 1 - y;

        if (x < 0 || x >= g.getCols()) return null;
        if (y < 0 || y >= g.getRows()) return null;

        return g.getNode(x, y);
    }

}
