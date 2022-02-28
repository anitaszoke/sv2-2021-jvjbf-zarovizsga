package shipping;

public class InternationalPackage implements Transportable {

    private final int weight;
    private final boolean isBreakable;
    private final String destinationCountry;
    private final int distance;

    public InternationalPackage(int weight, boolean isBreakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.isBreakable = isBreakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return isBreakable;
    }

    @Override
    public int calculateShippingPrice() {
        if (isBreakable) {
            return (2 * 1200) + (10 * distance);
        } else {
            return 1200 + (10 * distance);
        }
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public int getDistance() {
        return distance;
    }
}
