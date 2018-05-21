package pathfinder.logic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CurrentGraph {

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(propertyName, listener);
    }

    private Graph g;

    public CurrentGraph(Graph g) {
        this.g = g;
    }

    public Graph getGraph() {
        return g;
    }

    public void setGraph(Graph g) {
        Graph oldValue = this.g;
        this.g = g;
        pcs.firePropertyChange("graph", oldValue, g);
    }

    public Node getStart() {
        return g.getStart();
    }

    public Node getEnd() {
        return g.getEnd();
    }

    public void setStart(Node start) {
        Node oldValue = g.getStart();
        g.setStart(start);
        pcs.firePropertyChange("start", oldValue, start);
    }

    public void setEnd(Node end) {
        Node oldValue = g.getEnd();
        g.setEnd(end);
        pcs.firePropertyChange("end", oldValue, end);
    }

    public int getCols() {
        return g.getCols();
    }

    public int getRows() {
        return g.getRows();
    }

}
