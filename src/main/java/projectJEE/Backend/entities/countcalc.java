package projectJEE.Backend.entities;

public class countcalc {
    private long count;
    private Location location;

    public countcalc(long count, Location location) {
        this.count = count;
        this.location = location;
    }

    @Override
    public String toString() {
        return "countcalc{" +
                "count=" + count +
                ", location=" + location +
                '}';
    }

    public long getCount() {
        return count;
    }

    public Location getLocation() {
        return location;
    }
}
