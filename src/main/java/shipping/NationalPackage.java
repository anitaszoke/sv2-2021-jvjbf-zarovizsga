package shipping;

public class NationalPackage implements Transportable {

    private final int weight;
    private final boolean isBreakable;

    public NationalPackage(int weight, boolean isBreakable) {
        this.weight = weight;
        this.isBreakable = isBreakable;
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
            return 2*1000;
        }
        else {
            return 1000;
        }
    }

    @Override
    public String getDestinationCountry() {
        return Transportable.super.getDestinationCountry();
    }


}
