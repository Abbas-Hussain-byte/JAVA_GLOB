# Bus Ticket Management System

This project is a **Java-based GUI application** for managing bus ticketing operations. It simplifies viewing buses, generating tickets, and managing bus details, providing an intuitive interface for both users and administrators. The project was developed as part of the **Java Programming Lab - GRIET Lab On Board (G-LOB)** course.

## Features

### User Features:
- View the list of available buses with details such as name, route, type (AC/Non-AC), seat availability, etc.
- Generate bus tickets by selecting start and destination stops, with validations for input correctness.
- Receive error messages for invalid inputs (e.g., same start and destination stops).

### Admin Features:
- Add new buses with details like name, route, type, seat capacity, and availability.
- Dynamically update the bus list with newly added entries.

### GUI Design:
- Developed using **Java Swing**, with components like buttons, dropdown menus, text areas, and scroll panes.
- Includes user-friendly color schemes and intuitive layouts.
- Event handling for responsive and dynamic interactions.

---

## System Requirements

### Functional Requirements:
- **User Functions**: View buses and generate tickets.
- **Admin Functions**: Manage bus details and add new buses.
- **Error Handling**: Display informative error messages for invalid operations.

### Non-Functional Requirements:
- **Usability**: Intuitive graphical user interface.
- **Performance**: Fast response to user actions.
- **Portability**: Compatible with all Java-supported platforms.

### System Constraints:
- Static data storage using `ArrayList` (no database connectivity).
- Hardcoded fare structure and static routing.

---

## Implementation Overview

The core implementation includes:
1. **Graphical User Interface (GUI)**: Built with Swing for an interactive and visually appealing layout.
2. **Bus Class**: Defines attributes such as name, route, type, seat capacity, and availability.
3. **Event Handling**: Utilizes action listeners to dynamically capture and process user/admin interactions.
4. **Admin Panel**: Allows administrators to add new buses and manage existing ones.

---

## How to Run

1. Clone this repository:
   ```bash
   git clone <repository-url>
