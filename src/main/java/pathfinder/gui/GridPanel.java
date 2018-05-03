package pathfinder.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Pathfinder;

public class GridPanel extends JPanel {

    private final int borderSize = 2;
    private final int squareSize = 20;
    private final Color borderColor = new Color(0x719a9a);
    private final Color defaultColor = new Color(0xffffff);
    private final Color startColor = new Color(0x00dd00);
    private final Color endColor = new Color(0xee4400);
    private final Color obstacleColor = new Color(0x808080);
    private final Color visitedColor = new Color(0xafeeee);

    private Graph g;
    private Pathfinder pathfinder;

    public GridPanel(Graph g, Pathfinder pathfinder) {
        this.g = g;
        this.pathfinder = pathfinder;

        this.setPreferredSize(new Dimension(computeWidth(), computeHeight()));
        this.setDoubleBuffered(true);
    }

    public void setPathfinder(Pathfinder pathfinder) {
        this.pathfinder = pathfinder;
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        //paintBorders(gr);
        paintSquares(gr);
    }

    private void paintBorders(Graphics gr) {
        gr.setColor(borderColor);

        int cellSize = getCellSize();
        int width = computeWidth();
        int height = computeHeight();

        for (int y = 0; y < g.getRows(); y++) {
            gr.drawLine(0, y * cellSize, width, y * cellSize);
        }

        for (int x = 0; x < g.getCols(); x++) {
            gr.drawLine(x * cellSize, 0, x * cellSize, height);
        }
    }

    private int getCellSize() {
        return borderSize + squareSize;
    }

    private int computeWidth() {
        return getCellSize() * g.getCols() + borderSize;
    }

    private int computeHeight() {
        return getCellSize() * g.getRows() + borderSize;
    }

    private void paintSquares(Graphics gr) {
        for (int y = 0; y < g.getRows(); y++) {
            for (int x = 0; x < g.getCols(); x++) {
                paintNode(gr, g.getNode(x, y));
            }
        }
    }

    private void paintNode(Graphics gr, Node node) {
        int cellSize = getCellSize();
        int lastRow = (g.getRows()-1) * cellSize + borderSize;
        int fillX = node.x() * cellSize + borderSize;
        int fillY = node.y() * cellSize + borderSize;
        fillY = lastRow - fillY;
        gr.setColor(getNodeColor(node));
        gr.fill3DRect(fillX, fillY, squareSize, squareSize, true);
    }

    private Color getNodeColor(Node node) {
        if (node.equals(g.getStart())) return startColor;
        if (node.equals(g.getEnd())) return endColor;
        if (!node.isWalkable()) return obstacleColor;
        if (pathfinder.getVisited(node)) return visitedColor;

        return defaultColor;
    }

    protected Node getNode(int pointX, int pointY) {
        int x = pointX / getCellSize();
        int y = pointY / getCellSize();
        y = g.getRows() - 1 - y;

        if (x < 0 || x >= g.getCols()) return null;
        if (y < 0 || y >= g.getRows()) return null;

        return g.getNode(x, y);
    }

}
