package pathfinder.benchmark;

/**
 * Represents a single experiment (one shortest path problem). Each experiment
 * represents one shortest path problem. Each experiment refers to a map, and
 * defines the coordinates of one source node and one destination node. In
 * addition, the experiment should define the expected length of the shortest
 * path for diagnostic purposes.
 */
public class Experiment {

    private final int bucket;
    private final String map;
    private final int sizeX;
    private final int sizeY;
    private final int sourceX;
    private final int sourceY;
    private final int destX;
    private final int destY;
    private final double dist;

    /**
     * Constructs an <code>Experiment</code> object with the specified
     * properties.
     *
     * @param bucket the bucket (=floor(dist/4))
     * @param map the filename of the map
     * @param sizeX width of the map
     * @param sizeY height of the map
     * @param sourceX x-coordinate of the source node
     * @param sourceY y-coordinate of the source node
     * @param destX x-coordinate of the destination node
     * @param destY y-coordinate of the destination node
     * @param dist expected length of the shortest path
     */
    public Experiment(int bucket, String map, int sizeX, int sizeY,
            int sourceX, int sourceY, int destX, int destY, double dist) {
        this.bucket = bucket;
        this.map = map;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.destX = destX;
        this.destY = destY;
        this.dist = dist;
    }

    /**
     * Returns the bucket.
     *
     * @return the bucket (=floor(dist/4))
     */
    public int getBucket() {
        return bucket;
    }

    /**
     * Returns the filename of the map.
     *
     * @return the filename of the map
     */
    public String getMap() {
        return map;
    }

    /**
     * Returns the width of the map.
     *
     * @return the width of the map
     */
    public int getSizeX() {
        return sizeX;
    }

    /**
     * Returns the height of the map.
     *
     * @return the height of the map
     */
    public int getSizeY() {
        return sizeY;
    }

    /**
     * Returns the x-coordinate of the source node.
     *
     * @return the x-coordinate of the source node
     */
    public int getSourceX() {
        return sourceX;
    }

    /**
     * Returns the y-coordinate of the source node.
     *
     * @return the y-coordinate of the source node
     */
    public int getSourceY() {
        return sourceY;
    }

    /**
     * Returns the x-coordinate of the destination node.
     *
     * @return the x-coordinate of the destination node
     */
    public int getDestX() {
        return destX;
    }

    /**
     * Returns the y-coordinate of the destination node.
     *
     * @return the y-coordinate of the destination node
     */
    public int getDestY() {
        return destY;
    }

    /**
     * Returns the expected length of the shortest path.
     *
     * @return the expected length of the shortest path
     */
    public double getDist() {
        return dist;
    }

}
