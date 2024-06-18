package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static String processCommand(String command) {
        String[] parts = command.split(" ");
        try {
            switch (parts[0].toLowerCase()) {
                case "create":
                    return createDirectory(parts[1]);
                case "delete":
                    return deleteDirectory(parts[1]);
                case "move":
                    return moveDirectory(parts[1], parts[2]);
                case "rename":
                    return renameDirectory(parts[1], parts[2]);
                default:
                    return "Unknown command";
            }
        } catch (Exception e) {
            return "Error processing command: " + e.getMessage();
        }
    }

    private static String createDirectory(String dirPath) throws Exception {
        Path path = Paths.get(dirPath);
        Files.createDirectories(path);
        return "Directory created: " + dirPath;
    }

    private static String deleteDirectory(String dirPath) throws Exception {
        Path path = Paths.get(dirPath);
        Files.delete(path);
        return "Directory deleted: " + dirPath;
    }

    private static String moveDirectory(String sourcePath, String destPath) throws Exception {
        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destPath);
        Files.move(source, destination);
        return "Directory moved from " + sourcePath + " to " + destPath;
    }

    private static String renameDirectory(String oldPath, String newPath) throws Exception {
        Path oldDir = Paths.get(oldPath);
        Path newDir = Paths.get(newPath);
        Files.move(oldDir, newDir);
        return "Directory renamed from " + oldPath + " to " + newPath;
    }
}
