# PackingList

A simple console-based Java application for creating trip packing lists. Practice Java basics by organizing trips and checking off items as you pack.

## Features

- Create trips with a name, destination, and departure date
- View all trips
- Add items to a trip's packing list
- Mark items as packed
- View packing progress
- Remove items from a list
- Basic input validation

## Concepts Used

- Classes and objects
- `ArrayList`
- Methods
- Loops and `switch`
- `if` statements
- Encapsulation with fields and getters/setters
- `Scanner` for user input
- Exception handling with `try/catch`

## Requirements

- Java 17 or later

## How to Run

1. Download or clone this repository.
2. Open a terminal in the project folder.
3. Compile the Java file:

   ```bash
   javac src/PackingListApp.java
   ```

4. Run the application:

   ```bash
   java -cp src PackingListApp
   ```

## Example

```text
=================================
       PACKING LIST PLANNER
=================================

--- MAIN MENU ---
1. Create a trip
2. View trips
3. Add item to a trip
4. Mark item as packed
5. View packing list
6. Remove item from a trip
0. Exit
```

## Note

This beginner project stores information in memory while the program is running. Trips and items reset when you exit the application.

## Future Improvements

- Save trips to a file
- Add packing categories such as Clothing, Toiletries, and Documents
- Allow users to edit trip details
