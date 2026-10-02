import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorPrinterTest {

  @Test
  void testPrintlnWithRedColorAndReset() {
    // Arrange: Capture the printed output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);

    ColorPrinter printer = new ColorPrinter(printStream);
    printer.setCurrentColor(ConsoleColor.RED);

    // Act: Print the message
    String message = "I speak for the trees";
    printer.println(message);


    String expectedOutput = ConsoleColor.RED + "I speak for the trees" + System.lineSeparator() + ConsoleColor.RESET;

    // Assert: Verify the printed output
    assertEquals(expectedOutput, outputStream.toString());
  }


@Test
  void testPrintWithoutResetKeepsColor() {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);

    ColorPrinter printer = new ColorPrinter(printStream, ConsoleColor.GREEN);

    // Act Print with reset = false, then print again
    printer.print("Hello ", false);
    printer.print("World");

    // Assert GREEN code should be at the start, message 1, message 2, and RESET at the very end
    String expectedOutput = ConsoleColor.GREEN + "Hello " + "World" + ConsoleColor.RESET;
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  void testMultipleColorsAndCustomConstructor() {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);

    // Test constructor with initial color BLUE
    ColorPrinter printer = new ColorPrinter(printStream, ConsoleColor.BLUE);

    // Act
    printer.print("Blue text");
    printer.setCurrentColor(ConsoleColor.YELLOW);
    printer.print("Yellow text");

    // Assert
    String expectedOutput = ConsoleColor.BLUE + "Blue text" + ConsoleColor.RESET +
                            ConsoleColor.YELLOW + "Yellow text" + ConsoleColor.RESET;
    assertEquals(expectedOutput, outputStream.toString());
  }

  @Test
  void testDefaultColorIsWhite() {
    // Arrange
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);

    ColorPrinter printer = new ColorPrinter(printStream);

    // Act
    printer.print("Default color test");

    // Assert
    String expectedOutput = ConsoleColor.WHITE + "Default color test" + ConsoleColor.RESET;
    assertEquals(expectedOutput, outputStream.toString());
  }
}