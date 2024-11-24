package repository;

import model.products.InventoryItem;
import model.interfaces.ItemRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileItemRepository implements ItemRepository {

    private final String filePath;

    public FileItemRepository(String filePath) {
        this.filePath = filePath;
        ensureFileExists();
    }

    private void ensureFileExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Файлът " + filePath + " беше създаден успешно.");
                }
            } catch (IOException e) {
                System.err.println("Грешка при създаването на файл: " + e.getMessage());
            }
        }
    }

    @Override
    public List<InventoryItem> loadItems() {
        List<InventoryItem> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    items.add(InventoryItem.fromString(line));
                } catch (IllegalArgumentException e) {
                    System.err.println("Грешка при четене на елемент: " + line + ". Пропускане.");
                }
            }
        } catch (IOException e) {
            System.err.println("Грешка при четене на файла: " + e.getMessage());
        }
        return items;
    }


    @Override
    public void saveItems(List<InventoryItem> items) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (InventoryItem item : items) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Грешка при запис във файла: " + e.getMessage());
        }
    }
}
