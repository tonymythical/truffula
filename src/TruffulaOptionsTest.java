import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TruffulaOptionsTest {

  @Test
  void testValidDirectoryIsSet(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange: Prepare the arguments with the temp directory
    File directory = new File(tempDir, "subfolder");
    directory.mkdir();
    String directoryPath = directory.getAbsolutePath();
    String[] args = {"-nc", "-h", directoryPath};

    // Act: Create TruffulaOptions instance
    TruffulaOptions options = new TruffulaOptions(args);

    // Assert: Check that the root directory is set correctly
    assertEquals(directory.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertTrue(options.isShowHidden());
    assertFalse(options.isUseColor());
  }

  @Test
  void testDefaultFlagsWhenOnlyPathIsProvided(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange
    String[] args = {tempDir.getAbsolutePath()};

    // Act
    TruffulaOptions options = new TruffulaOptions(args);

    // Assert
    assertEquals(tempDir.getAbsolutePath(), options.getRoot().getAbsolutePath());
    assertFalse(options.isShowHidden()); // defaults to false
    assertTrue(options.isUseColor());    // defaults to true
  }

  @Test
  void testFlagOrderDoesNotMatter(@TempDir File tempDir) throws FileNotFoundException {
    // Arrange
    String[] args = {"-h", "-nc", tempDir.getAbsolutePath()};

    // Act
    TruffulaOptions options = new TruffulaOptions(args);

    // Assert
    assertTrue(options.isShowHidden());
    assertFalse(options.isUseColor());
  }

  @Test
  void testUnknownFlagThrowsIllegalArgumentException(@TempDir File tempDir) {
    // Arrange
    String[] args = {"-unknown", tempDir.getAbsolutePath()};

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> {
      new TruffulaOptions(args);
    });
  }

  @Test
  void testMissingPathThrowsIllegalArgumentException() {
    // Arrange
    String[] args = {"-h", "-nc"};

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> {
      new TruffulaOptions(args);
    });
  }

  @Test
  void testNonExistentDirectoryThrowsFileNotFoundException() {
    // Arrange
    String[] args = {"/non/existent/directory/path/12345"};

    // Act & Assert
    assertThrows(FileNotFoundException.class, () -> {
      new TruffulaOptions(args);
    });
  }

  @Test
  void testPathPointingToFileThrowsFileNotFoundException(@TempDir File tempDir) throws IOException {
    // Arrange: Create a file instead of a directory
    File tempFile = new File(tempDir, "test.txt");
    tempFile.createNewFile();
    String[] args = {tempFile.getAbsolutePath()};

    // Act & Assert
    assertThrows(FileNotFoundException.class, () -> {
      new TruffulaOptions(args);
    });
  }
}
