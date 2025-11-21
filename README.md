# StockKeeper

StockKeeper is a simple desktop application built with Java Swing for managing inventory stock.  
This application allows users to easily add, edit, and delete items through a graphical user interface.

---

## Features

- Add items with inputs for name, quantity, and price.
- Display the list of items in a table with prices formatted in Indonesian Rupiah.
- Edit and delete items directly within the table.
- Show dummy data when the item list is empty.

---

## Technology

- Language: Java
- Build System: **Maven** (for dependency management and build)
- GUI: Swing (`JPanel`, `JFrame`, `JTable`)
- Formatting: `NumberFormat` used for Rupiah currency formatting
- Design: Lightweight MVC (model separated from UI)

---

## Project Structure

- `src/main/java/com/stockkeeper` - Java source code
- `src/main/resources` - Resources such as application icons
- `pom.xml` - Maven configuration file

---

## How to Run

### Prerequisites

- Java JDK 8 or higher installed
- Maven installed and accessible from the terminal/command prompt

### Build and Run the Application

1. **Clone** the repository or download the source code.
2. Open a terminal in the project folder.
3. Run the command to build the project:

   ```bash
   mvn clean compile
   ```

4. Run the command to run the application:

   ```bash
   mvn javafx:run@run-stockkeeper
   ```

--- 

## Usage
1. Fill the input form with item name, quantity, and price.
2. Click the Add button to add the item to the list.
3. Use the Edit and Delete buttons in the table to modify or remove items.
4. Prices are automatically formatted in Rupiah (IDR).

---

## Notes

- Data is stored only in memory while the application is running.
- Dummy data is shown when the item list is empty.
- Can be extended to save data permanently (e.g., database or file).

---

## GUI Design
![StockKeeper GUI](./src/main/resources/display.png)