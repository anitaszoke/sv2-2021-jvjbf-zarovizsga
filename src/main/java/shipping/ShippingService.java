package shipping;

import java.util.*;
import java.util.stream.Collectors;

public class ShippingService {

    private List<Transportable> packages = new ArrayList<>();

    public List<Transportable> getPackages() {
        return packages;
    }

    //(Transportable package hiba)
    public void addPackage(Transportable packagee) {
        packages.add(packagee);
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean isBreakable, int weight) {
        return packages.stream()
                .filter(Transportable::isBreakable)
                .filter(t -> t.getWeight() >= weight)
                .toList();
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> result = new HashMap<>();
        String country = "";
        int count = 1;

        for (Transportable t : packages) {
            country = t.getDestinationCountry();
            if (!result.containsKey(country)) {
                result.put(country, count);

            } else if (result.containsKey(country)) {
                count++;
                result.put(country, count);
            }
        }
        return result;
    }


    public List<Transportable> sortInternationalPackagesByDistance() {
        List<Transportable> result = new ArrayList<>();
        for (Transportable t : packages) {
            if (t instanceof InternationalPackage) {
                result.add(t);
            }
        }

        return result;


    }
}

