import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationMapper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputMap = scanner.nextLine();
        List<String> destinations = new ArrayList<>();
        int travelPoints = 0;

        String regex = "[=\\/][A-Z][A-Za-z]{2,}[=\\/]";
        Pattern countryFinder = Pattern.compile(regex);
        Matcher countryMatch = countryFinder.matcher(inputMap);

        while (countryMatch.find()) {
            String country = countryMatch.group();
            if (country.charAt(0) == country.charAt(country.length() - 1)) {
                int destinationPoints = country.length() - 2;
                String getCountry = country.substring(1, country.length() -1);
                destinations.add(getCountry);
                travelPoints += destinationPoints;
            }
        }
        System.out.println("Destinations: " + String.join(", ", destinations));
        System.out.println("Travel Points: " + travelPoints);
    }
}
