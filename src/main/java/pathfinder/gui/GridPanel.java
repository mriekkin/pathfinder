package pathfinder.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import pathfinder.logic.Graph;
import pathfinder.logic.Node;
import pathfinder.logic.Pathfinder;

public class GridPanel extends JPanel {

    private final int borderSize;
    private final int squareSize;
    private final Color borderColor;
    private final Color defaultColor;
    private final Color startColor;
    private final Color endColor;
    private final Color obstacleColor;
    private final Color visitedColor;

    private Graph graph;
    private Pathfinder pathfinder;

    public GridPanel(Graph g, Pathfinder pathfinder) {
        this.graph = g;
        this.pathfinder = pathfinder;

        this.borderSize = 2;
        this.squareSize = 20;
        this.borderColor = new Color(0x719a9a);
        this.defaultColor = new Color(0xffffff);
        this.startColor = new Color(0x00dd00);
        this.endColor = new Color(0xee4400);
        this.obstacleColor = new Color(0x808080);
        this.visitedColor = new Color(0xafeeee);

        this.setPreferredSize(new Dimension(computeWidth(), computeHeight()));
        this.setDoubleBuffered(true);
    }

    public void setPathfinder(Pathfinder pathfinder) {
        this.pathfinder = pathfinder;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //paintBorders(g);
        paintSquares(g);
    }

    private void paintBorders(Graphics g) {
        g.setColor(borderColor);

        int cellSize = getCellSize();
        int width = computeWidth();
        int height = computeHeight();

        for (int y = 0; y < graph.getHeight(); y++) {
            g.drawLine(0, y * cellSize, width, y * cellSize);
        }

        for (int x = 0; x < graph.getWidth(); x++) {
            g.drawLine(x * cellSize, 0, x * cellSize, height);
        }
    }

    private int getCellSize() {
        return borderSize + squareSize;
    }

    private int computeWidth() {
        return getCellSize() * graph.getWidth() + borderSize;
    }

    private int computeHeight() {
        return getCellSize() * graph.getHeight() + borderSize;
    }

    private void paintSquares(Graphics g) {
        for (int y = 0; y < graph.getHeight(); y++) {
            for (int x = 0; x < graph.getWidth(); x++) {
                paintNode(g, graph.getNode(x, y));
            }
        }
    }

    private void paintNode(Graphics g, Node node) {
        int cellSize = getCellSize();
        int lastRow = (graph.getHeight()-1) * cellSize + borderSize;
        int fillX = node.x() * cellSize + borderSize;
        int fillY = node.y() * cellSize + borderSize;
        fillY = lastRow - fillY;
        g.setColor(getNodeColor(node));
        g.fill3DRect(fillX, fillY, squareSize, squareSize, true);
    }

    private Color getNodeColor(Node node) {
        if (node.equals(pathfinder.getStart())) return startColor;
        if (node.equals(pathfinder.getEnd())) return endColor;
        if (!node.isWalkable()) return obstacleColor;
        if (pathfinder.getVisited(node)) return visitedColor;

        return defaultColor;
    }

}
