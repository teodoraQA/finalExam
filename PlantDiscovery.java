import java.util.*;

public class PlantDiscovery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> plants = new HashMap<>();
        Map<String, List<Double>> plantsRatings = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("<->");
            String plant = input[0];
            int rarity = Integer.parseInt(input[1]);
            plants.put(plant, rarity);
            plantsRatings.putIfAbsent(plant, new ArrayList<>());
        }

        String command = scanner.nextLine();
        while (!command.equals("Exhibition")) {
            String[] splitInput = command.split(": ");
            String action = splitInput[0];
            String[] data = splitInput[1].split(" - ");
            String plant = data[0];

            if (!plants.containsKey(plant)) {
                System.out.println("error");
                command = scanner.nextLine();
                continue;
            }

            switch (action) {
                case "Rate":
                    double rate = Double.parseDouble(data[1]);
                    plantsRatings.get(plant).add(rate);
                    break;
                case "Update":
                    int newRarity = Integer.parseInt(data[1]);
                    plants.put(plant, newRarity);
                    break;
                case "Reset":
                    plantsRatings.get(plant).clear();
                    break;
                default:
                    System.out.println("error");
            }
            command = scanner.nextLine();
        }
        System.out.println("Plants for the exhibition:");
        plants.entrySet().stream()
                .sorted((p1, p2) -> {
                    int res = p2.getValue().compareTo(p1.getValue());
                    if (res == 0) {
                        double av1 = getAverageRating(p1, plantsRatings);
                        double av2 = getAverageRating(p2, plantsRatings);
                        res = Double.compare(av2, av1);
                    }
                    return res;
                })
                .forEach(p -> {
                    double averageRating = getAverageRating(p, plantsRatings);
                    System.out.println(String.format("- %s; Rarity: %d; Rating: %.2f",
                            p.getKey(), p.getValue(), averageRating));
                });

    }

    private static double getAverageRating(Map.Entry<String, Integer> plant, Map<String, List<Double>> plantsRatings) {
        double sumRatings = 0;
        for (double r : plantsRatings.get(plant.getKey())) {
            sumRatings += r;
        }
        double averageRate = 0.0;
        if (sumRatings != 0) {
            averageRate = sumRatings / plantsRatings.get(plant.getKey()).size();
        }
        return averageRate;
    }

}
