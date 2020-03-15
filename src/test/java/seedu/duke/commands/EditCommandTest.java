package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.data.ShoppingList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditCommandTest {

    ShoppingList items = new ShoppingList();
    Command command = new Command();

    @BeforeEach
    void setUp() {
        items.add(new Item("apple", 2.0));
        items.add(new Item("donut", 3.0));
        items.add(new Item("banana", 4.50));
    }

    @Test
    void testEdit_ItemDescriptionOnly_Success() {
        command = new EditCommand(1, "apples", null);
        command.setData(items, null);
        command.execute();
        String expectedFeedback =  String.format(EditCommand.MESSAGE_SUCCESS, items.getList().get(0).toString());
        assertEquals(expectedFeedback, command.feedbackToUser);

    }

    @Test
    void testEdit_ItemPriceOnly_Success() {
        command = new EditCommand(2, null, "3.5");
        command.setData(items, null);
        command.execute();
        String expectedFeedback1 =  String.format(EditCommand.MESSAGE_SUCCESS, items.getList().get(1).toString());
        assertEquals(expectedFeedback1, command.feedbackToUser);
    }

    @Test
    void testEdit_ItemPriceAndDescription_Success() {
        command = new EditCommand(3, "banana", "5.00");
        command.setData(items, null);
        command.execute();
        String expectedFeedback2 =  String.format(EditCommand.MESSAGE_SUCCESS, items.getList().get(2).toString());
        assertEquals(expectedFeedback2, command.feedbackToUser);
    }

    @Test
    public void testEdit_ItemToEditNotInList_exceptionThrown() {
        command = new EditCommand(0, "banana", "5.00");
        command.setData(items, null);
        command.execute();
        String expectedFeedback3 = EditCommand.MESSAGE_FAILURE_NOT_IN_LIST;
        assertEquals(expectedFeedback3, command.feedbackToUser);
    }

    @Test
    public void testEdit_ItemPriceIncorrectFormat_exceptionThrown() {
        command = new EditCommand(1, "banana", "QWERTY");
        command.setData(items, null);
        command.execute();
        String expectedFeedback3 = EditCommand.MESSAGE_FAILURE_PRICE_INCORRECT_FORMAT;
        assertEquals(expectedFeedback3, command.feedbackToUser);
    }


}