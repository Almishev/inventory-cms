package model.persons;

import model.products.InventoryItem;
import utils.AESUtils;

import javax.crypto.SecretKey;
import java.io.*;
import java.util.*;

public class InventoryManager extends ItemViewer {

    private List<InventoryItem> items;

    public InventoryManager() {
        this.items = new ArrayList<>();
    }

    public void addItemToFile(InventoryItem item, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(item.getName() + ", " + item.getDescription() + ", " + item.getCategory() + ", " + item.getPrice() + ", " + item.isPerishable() + ", " + item.isBreakable() + ", " + item.getQuantity());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    public void removeItemFromFile(String itemName, String filename) {
        try {
            List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            boolean removed = false;

            while ((line = reader.readLine()) != null) {

                String itemInFile = line.split(",")[0].trim();

                if (!itemInFile.equals(itemName)) {
                    lines.add(line);
                } else {
                    removed = true;
                }
            }

            if (removed) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                for (String l : lines) {
                    writer.write(l + "\n"); // Записваме останалите редове в файла
                }
                writer.close();
                System.out.println("Item removed from file: " + itemName);
            } else {
                System.out.println("Item not found: " + itemName);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading or writing to file.");
            e.printStackTrace();
        }
    }



    public void loadItemsFromFile(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filename);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    System.out.println("Skipping empty line.");
                    continue;
                }

                try {
                    InventoryItem item = InventoryItem.fromString(line);
                    items.add(item);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid item data: " + line);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file: " + filename, e);
        }
    }


    public List<InventoryItem> getItems() {
        return items;
    }

    public static boolean validateManagerCredentials(String username, String password) throws IOException, Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader("managers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 3) {
                    String fileUsername = credentials[0].trim();
                    String encryptedPassword = credentials[1].trim();
                    String keyString = credentials[2].trim();

                    SecretKey secretKey = AESUtils.getKeyFromString(keyString);

                    String decryptedPassword = AESUtils.decrypt(encryptedPassword, secretKey);

                    if (fileUsername.equals(username) && decryptedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void addManager(String username, String password) {
        try {
            SecretKey secretKey = AESUtils.generateKey();
            String encryptedPassword = AESUtils.encrypt(password, secretKey);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("managers.txt", true))) {
                writer.write(username + "," + encryptedPassword + "," + AESUtils.keyToString(secretKey) + "\n");
                System.out.println("Manager added successfully: " + username);
            }
        } catch (Exception e) {
            System.out.println("Error adding manager: " + e.getMessage());
        }
    }


    public static void removeManager(String username) {
        File inputFile = new File("managers.txt");
        File tempFile = new File("temp_managers.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (!credentials[0].equals(username)) {
                    writer.write(line + "\n");
                } else {
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Manager not found: " + username);
            } else {
                System.out.println("Manager removed successfully: " + username);
            }
        } catch (IOException e) {
            System.out.println("Error removing manager: " + e.getMessage());
        }

        if (inputFile.delete() && tempFile.renameTo(inputFile)) {
            System.out.println("Changes saved successfully.");
        } else {
            System.out.println("Error updating managers file.");
        }
    }


    public static void updateItemPriceInFile(InventoryItem updatedItem, String filename) {
        List<String> lines = new ArrayList<>();
        boolean itemUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String itemName = line.split(",")[0].trim();

                if (itemName.equalsIgnoreCase(updatedItem.getName())) {

                    line = updatedItem.getName() + ", " + updatedItem.getDescription() + ", " +
                            updatedItem.getCategory() + ", " + updatedItem.getPrice() + ", " +
                            updatedItem.isPerishable() + ", " + updatedItem.isBreakable() + ", " + updatedItem.getQuantity();
                    itemUpdated = true;
                }

                lines.add(line);
            }

            if (itemUpdated) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine + "\n");
                    }
                }
                System.out.println("Price updated successfully in file.");
            } else {
                System.out.println("Item not found in file.");
            }
        } catch (IOException e) {
            System.out.println("Error reading or writing to file: " + e.getMessage());
        }
    }

    public static void updateItemQuantityInFile(InventoryItem updatedItem, String filename) {
        List<String> lines = new ArrayList<>();
        boolean itemUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String itemName = line.split(",")[0].trim();

                if (itemName.equalsIgnoreCase(updatedItem.getName())) {

                    line = updatedItem.getName() + ", " + updatedItem.getDescription() + ", " +
                            updatedItem.getCategory() + ", " + updatedItem.getPrice() + ", " +
                            updatedItem.isPerishable() + ", " + updatedItem.isBreakable() + ", " + updatedItem.getQuantity();
                    itemUpdated = true;
                }

                lines.add(line);
            }

            if (itemUpdated) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine + "\n");
                    }
                }
                System.out.println("Quantity updated successfully in file.");
            } else {
                System.out.println("Item not found in file.");
            }
        } catch (IOException e) {
            System.out.println("Error reading or writing to file: " + e.getMessage());
        }
    }


}
