import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class DestinationHashGenerator {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -jar <jar-file> <rollNumber> <jsonFilePath>");
            return;
        }

        String rollNumber = args[0].toLowerCase();
        String filePath = args[1];

        try {
            String destinationValue = JsonProcessor.findDestination(filePath);
            if (destinationValue == null) {
                System.out.println("Key 'destination' not found in the JSON file.");
                return;
            }

            String randomString = RandomStringGenerator.generateRandomString(8);
            String concatenated = rollNumber + destinationValue + randomString;
            String hash = HashGenerator.generateMD5Hash(concatenated);

            System.out.println(hash + ";" + randomString);
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
