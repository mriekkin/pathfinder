package pathfinder.logic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the graph which is currently being edited in this application.
 * This is the graph of current interest to the user. This is an example of a
 * model suitable for applications which edit only one item at a time.
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
public class CurrentGraph {

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

    private Graph g;

    /**
     * Constructs a <code>CurrentGraph</code> with the specified graph.
     *
     * @param g the graph to be registered as the current graph
     */
    public CurrentGraph(Graph g) {
        this.g = g;
    }

    /**
     * Returns the current graph.
     *
     * @return the current graph
     * @see #setGraph(Graph)
     */
    public Graph getGraph() {
        return g;
    }

    /**
     * Registers the specified graph as the current graph. Notifies registered
     * observers of the new graph.
     *
     * @param g the graph to be registered as the current graph
     */
    public void setGraph(Graph g) {
        Graph oldValue = this.g;
        this.g = g;
        pcs.firePropertyChange("graph", oldValue, g);
    }

    /**
     * Returns the node labeled as a source node for the current graph.
     *
     * @return the node labeled as a source node for the current graph
     * @see #setSource(Node)
     */
    public Node getSource() {
        return g.getSource();
    }

    /**
     * Returns the node labeled as a destination node for the current graph.
     *
     * @return the node labeled as a destination node for this graph
     * @see #setDest(Node)
     */
    public Node getDest() {
        return g.getDest();
    }

    /**
     * Labels the specified node as the new source node for the current graph.
     * Each graph has one node labeled as <code>source</code> and one node
     * labeled as <code>dest</code>. These are used by pathfinders as the source
     * and destination nodes.
     *
     * @param source the node to be labeled as the new source node
     */
    public void setSource(Node source) {
        Node oldValue = g.getSource();
        g.setSource(source);
        pcs.firePropertyChange("source", oldValue, source);
    }

    /**
     * Labels the specified node as the new destination node for the current
     * graph. Each graph has one node labeled as <code>source</code> and one
     * node labeled as <code>dest</code>. These are used by pathfinders as the
     * source and destination nodes.
     *
     * @param dest the node to be labeled as the new destination node
     */
    public void setDest(Node dest) {
        Node oldValue = g.getDest();
        g.setDest(dest);
        pcs.firePropertyChange("dest", oldValue, dest);
    }

    /**
     * Returns the number of columns in the current graph. Each grid has a
     * specific number of rows and columns.
     *
     * @return the number of columns in this grid
     */
    public int getCols() {
        return g.getCols();
    }

    /**
     * Returns the number of rows in the current graph. Each grid has a specific
     * number of rows and columns.
     *
     * @return the number of rows in this grid
     */
    public int getRows() {
        return g.getRows();
    }

}
