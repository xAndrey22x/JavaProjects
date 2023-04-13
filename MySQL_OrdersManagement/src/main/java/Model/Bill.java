package Model;

/**
 * Bill record model
 */
public record Bill(int id, String clientName, String productName, int quantity, int price) {
}

