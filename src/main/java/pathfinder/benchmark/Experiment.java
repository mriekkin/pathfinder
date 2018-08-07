package pathfinder.benchmark;

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

    public int getBucket() {
        return bucket;
    }

    public String getMap() {
        return map;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSourceX() {
        return sourceX;
    }

    public int getSourceY() {
        return sourceY;
    }

    public int getDestX() {
        return destX;
    }

    public int getDestY() {
        return destY;
    }

    public double getDist() {
        return dist;
    }

}
