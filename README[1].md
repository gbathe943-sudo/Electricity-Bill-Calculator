# Electricity Bill Calculator ⚡

A beginner-friendly **Java Swing desktop application** that calculates an electricity bill from the customer's details and units consumed.

## Features

- Enter customer name, customer ID, and electricity units.
- Calculate the bill automatically using a slab-based sample tariff.
- Display a formatted bill receipt.
- Validate empty, negative, and non-numeric inputs.
- Reset the form.
- **No database required** — all calculations happen in memory.

## Technology

- Java
- Java Swing (GUI)
- AWT event handling

## Sample Educational Tariff

> These rates are only for this academic demonstration. They are **not official electricity-board rates**.

| Units | Rate |
|---|---:|
| First 100 units | ₹3 per unit |
| Next 100 units (101–200) | ₹5 per unit |
| Above 200 units | ₹7 per unit |
| Fixed charge | ₹50 |

### Example

For 150 units:

- First 100 × ₹3 = ₹300
- Next 50 × ₹5 = ₹250
- Fixed charge = ₹50
- **Total = ₹600**

## Requirements

- JDK 15 or later (the source uses Java text blocks and `String.formatted`).
- VS Code, IntelliJ IDEA, Eclipse, or any Java IDE.

## How to Run

Open a terminal in this project folder.

### 1. Compile

```bash
javac ElectricityBillCalculator.java
```

### 2. Run

```bash
java ElectricityBillCalculator
```

A desktop window will open. Enter the customer information and click **Calculate Bill**.

## Project Structure

```text
Electricity-Bill-Calculator/
├── ElectricityBillCalculator.java
├── README.md
└── .gitignore
```

## Concepts Demonstrated

- Classes and objects
- Methods
- Conditional statements
- Exception handling
- GUI programming with Java Swing
- Event handling
- Input validation

## Academic Note

This is a mini project created for learning and demonstration. It does not connect to an electricity provider and does not store customer information.
