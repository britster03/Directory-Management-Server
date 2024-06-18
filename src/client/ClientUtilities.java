package client;

/**
 * ClientUtilities provides utility functions to support client-side operations
 * such as input validation and message formatting.
 */
public class ClientUtilities {

    /**
     * Validates the command input by the user.
     * @param command The command entered by the user.
     * @return true if the command is valid, false otherwise.
     */
    public static boolean validateCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            return false;
        }
        // Split command and validate parts
        String[] parts = command.split(" ");
        String operation = parts[0].toLowerCase();

        // Check if the operation is valid and has required number of arguments
        switch (operation) {
            case "create":
            case "delete":
                return parts.length == 2; // These commands should have exactly one argument
            case "move":
            case "rename":
                return parts.length == 3; // These commands should have exactly two arguments
            default:
                return false;
        }
    }

    /**
     * Formats the command to ensure it is properly structured before sending to the server.
     * This could involve trimming unnecessary spaces, converting to lower case, etc.
     * @param command The command to format.
     * @return A formatted command string.
     */
    public static String formatCommand(String command) {
        return command.trim().replaceAll("\\s+", " ").toLowerCase();
    }

    /**
     * Parses the server's response for display in the client GUI.
     * @param response The response from the server.
     * @return A user-friendly string representation of the response.
     */
    public static String parseResponse(String response) {
        // Here you could add logic to format or parse the response based on certain criteria or errors.
        return response;
    }
}
