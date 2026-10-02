# Truffula Notes
As part of Wave 0, please fill out notes for each of the below files. They are in the order I recommend you go through them. A few bullet points for each file is enough. You don't need to have a perfect understanding of everything, but you should work to gain an idea of how the project is structured and what you'll need to implement. Note that there are programming techniques used here that we have not covered in class! You will need to do some light research around things like enums and and `java.io.File`.

PLEASE MAKE FREQUENT COMMITS AS YOU FILL OUT THIS FILE.

## App.java
* **Role:** The main entry point of the application containing the `main` method.
* **Functionality:** Orchestrates program execution, processes initial command-line arguments, and initializes core components like options and printers.
* **Key Observations:** Acts as the controller tying together the configuration, sorting, and printing modules.

## ConsoleColor.java
* **Role:** An enum that defines ANSI escape code strings for terminal text colors (e.g., RED, GREEN, BLUE) and a `RESET` code.
* **Functionality:** Overrides `toString()` to return its internal ANSI code string, allowing it to be concatenated directly into print statements.
* **Key Observations:** A clean way to manage state and constants using Java enums rather than raw magic strings.

## ColorPrinter.java / ColorPrinterTest.java
* **Role:** A utility class that wraps a `PrintStream` and tracks a `currentColor` to output colored text.
* **Functionality:** Implements `print` and `println` methods with an optional `reset` parameter to control whether the color resets immediately or persists.
* **Key Observations:** Uses `ByteArrayOutputStream` in tests to capture and assert terminal output without polluting the actual console.

## TruffulaOptions.java / TruffulaOptionsTest.java
* **Role:** Handles parsing, validation, and storage of command-line arguments and configuration flags.
* **Functionality:** Interprets user configurations (such as paths, sorting preferences, or color options) passed into the app.
* **Key Observations:** Encapsulates user input rules so `App` and printers don't have to worry about raw argument parsing.

## TruffulaPrinter.java / TruffulaPrinterTest.java
* **Role:** The core printing/rendering engine for the application.
* **Functionality:** Iterates through files/directories and prints them using the `ColorPrinter` based on the parsed `TruffulaOptions`.
* **Key Observations:** Coordinates with file system objects using `java.io` and formats the output structure visually.

## AlphabeticalFileSorter.java
* **Role:** A helper class responsible for sorting collections or arrays of `java.io.File` objects.
* **Functionality:** Orders files and directories alphabetically by name to ensure consistent and predictable output structure.
* **Key Observations:** Relies on standard `java.io.File` methods and Java's sorting capabilities.

Very interesting stuff.