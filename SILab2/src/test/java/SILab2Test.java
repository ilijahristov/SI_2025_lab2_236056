import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {

    // Помошна функција за создавање на Item
    private Item createItem(String name, int quantity, int price, double discount) {
        return new Item(name, quantity, price, discount);
    }

    @Test
    public void testStatementCoverage() {
        // Тестирај ако allItems е null -> фрла RuntimeException
        Exception exception = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(null, "1234567812345678");
        });
        assertEquals("allItems list can't be null!", exception.getMessage());

        // Тестирај item со null име -> фрла RuntimeException
        List<Item> items = new ArrayList<>();
        items.add(createItem(null, 1, 10, 0));
        Exception exception2 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, "1234567812345678");
        });
        assertEquals("Invalid item!", exception2.getMessage());

        // Тестирај невалиден број картичка (length != 16)
        items.clear();
        items.add(createItem("Apple", 1, 10, 0));
        Exception exception3 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, "123");
        });
        assertEquals("Invalid card number!", exception3.getMessage());

        // Тестирај невалиден картичка број (не-цифра)
        Exception exception4 = assertThrows(RuntimeException.class, () -> {
            SILab2.checkCart(items, "12345678abc45678z");
        });
        assertEquals("Invalid character in card number!", exception4.getMessage());

        // Тестирај пресметка со валиден предмет и картичка
        items.clear();
        items.add(createItem("Banana", 2, 50, 0));
        double total = SILab2.checkCart(items, "1234567812345678");
        assertEquals(100, total, 0.001);
    }

    @Test
    public void testMultipleConditionCoverage() {
        // Тестирај сите комбинации од условот:
        // if (price > 300 || discount > 0 || quantity > 10)

        List<Item> items = new ArrayList<>();
        String card = "1234567812345678";

        // 1) price <=300, discount = 0, quantity <=10 (некој предмет што не активира услов)
        items.clear();
        items.add(createItem("Item1", 10, 300, 0));
        double result1 = SILab2.checkCart(items, card);
        assertEquals(3000, result1, 0.001);  // 300 * 10

        // 2) price > 300, discount=0, quantity <=10 (треба да се одземе 30)
        items.clear();
        items.add(createItem("Item2", 1, 301, 0));
        double result2 = SILab2.checkCart(items, card);
        assertEquals(301 - 30, result2, 0.001);

        // 3) price <=300, discount>0, quantity <=10 (треба да се одземе 30 + дискаунт)
        items.clear();
        items.add(createItem("Item3", 2, 100, 0.1)); // 10% discount
        double result3 = SILab2.checkCart(items, card);
        assertEquals((100 * 2 * 0.9) - 30, result3, 0.001);

        // 4) price <=300, discount=0, quantity > 10 (треба да се одземе 30)
        items.clear();
        items.add(createItem("Item4", 11, 50, 0));
        double result4 = SILab2.checkCart(items, card);
        assertEquals((50 * 11) - 30, result4, 0.001);

        // 5) price > 300, discount > 0, quantity > 10 (тројно true, сепак само -30)
        items.clear();
        items.add(createItem("Item5", 20, 500, 0.2));
        double result5 = SILab2.checkCart(items, card);
        assertEquals((500 * 20 * 0.8) - 30, result5, 0.001);
    }
}