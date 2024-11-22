import controller.InventoryController;
import repository.FileItemRepository;
import service.InventoryService;

public class Main {


    public static void main(String[] args) {

        String filePath = "inventory_data.txt";

        FileItemRepository itemRepository = new FileItemRepository(filePath);
        InventoryService inventoryService = new InventoryService(itemRepository);
        InventoryController controller = new InventoryController(inventoryService);

        controller.start();
    }
}