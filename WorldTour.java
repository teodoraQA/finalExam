import java.util.Scanner;

public class WorldTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder stops = new StringBuilder();

        stops.append(scanner.nextLine());
        String command = scanner.nextLine();

        while (!command.equals("Travel")) {
            String[] inputCommand = command.split(":");
            String commandType = inputCommand[0];
            switch (commandType) {
                case "Add Stop":
                    addStop(stops, inputCommand);
                    break;
                case "Remove Stop":
                    removeStop(stops, inputCommand);
                    break;
                case "Switch":
                    switchStops(stops, inputCommand);
                    break;
            }

            System.out.println(stops);
            command = scanner.nextLine();
        }

        System.out.println("Ready for world tour! Planned stops: " + stops);
    }

    private static void switchStops(StringBuilder stops, String[] inputCommand) {
        String oldString = inputCommand[1];
        String newString = inputCommand[2];
        String getStops = stops.toString();

        if (getStops.contains(oldString)) {
            String stopsSwitch = getStops.replace(oldString, newString);
            stops.setLength(0);
            stops.append(stopsSwitch);
        }
    }

    private static void removeStop(StringBuilder stops, String[] inputCommand) {
        int startIndex = Integer.parseInt(inputCommand[1]);
        int endIndex = Integer.parseInt(inputCommand[2]);
        if (startIndex < stops.length() && endIndex < stops.length()) {
            stops.replace(startIndex, endIndex + 1, "");
        }
    }

    private static void addStop(StringBuilder stops, String[] inputCommand) {
        int index = Integer.parseInt(inputCommand[1]);
        String string = inputCommand[2];
        if (stops.length() > index) {
            stops.insert(index, string);
        }
    }
}
