import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class BusTicketSystem {

    private static final ArrayList<Bus> buses = new ArrayList<>();

    public static void main(String[] args) {
        // Initial buses
        buses.add(new Bus("Bus 101", "A-B-C", "AC", 40, true));
        buses.add(new Bus("Bus 102", "B-C-D-E", "Non-AC", 50, true));

        JFrame frame = new JFrame("Bus Ticket Management");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Center the interface on the screen
        frame.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(new Color(173, 216, 230)); // Light blue background

        JTextArea outputArea = new JTextArea(15, 50);
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(245, 245, 245)); // Light gray background
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JComboBox<String> startStop = new JComboBox<>(new String[]{"A", "B", "C", "D", "E"});
        JComboBox<String> destStop = new JComboBox<>(new String[]{"A", "B", "C", "D", "E"});

        JButton generateTicket = new JButton("Generate Ticket");
        JButton viewBuses = new JButton("View Buses");
        JButton adminPanel = new JButton("Admin Panel");

        // Style buttons
        generateTicket.setBackground(new Color(135, 206, 250)); // Sky blue
        generateTicket.setForeground(Color.BLACK);
        viewBuses.setBackground(new Color(144, 238, 144)); // Light green
        viewBuses.setForeground(Color.BLACK);
        adminPanel.setBackground(new Color(255, 182, 193)); // Light pink
        adminPanel.setForeground(Color.BLACK);

        topPanel.add(new JLabel("From:"));
        topPanel.add(startStop);
        topPanel.add(new JLabel("To:"));
        topPanel.add(destStop);
        topPanel.add(generateTicket);
        topPanel.add(viewBuses);
        topPanel.add(adminPanel);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Event handling for "View Buses"
        viewBuses.addActionListener(e -> {
            outputArea.setForeground(Color.BLUE); // Set text color
            outputArea.setText(getBusList());
        });

        // Event handling for "Generate Ticket"
        generateTicket.addActionListener(e -> {
            String start = (String) startStop.getSelectedItem();
            String dest = (String) destStop.getSelectedItem();
            if (start.equals(dest)) {
                outputArea.setForeground(Color.RED); // Error message in red
                outputArea.setText("Error: Starting and destination stops cannot be the same.");
            } else {
                if (buses.isEmpty()) {
                    outputArea.setForeground(Color.RED);
                    outputArea.setText("No buses available to generate tickets.");
                } else {
                    outputArea.setForeground(Color.BLACK); // Normal text color
                    outputArea.setText(generateTicket(start, dest, buses.get(0))); // Use the first bus for simplicity
                }
            }
        });

        // Event handling for "Admin Panel"
        adminPanel.addActionListener(e -> showAdminPanel(outputArea));

        frame.setVisible(true);
    }

    // Method to generate the list of buses
    private static String getBusList() {
        if (buses.isEmpty()) {
            return "No buses available.";
        }
        StringBuilder result = new StringBuilder("------ Available Buses ------\n");
        for (Bus bus : buses) {
            result.append(bus).append("\n");
        }
        result.append("-----------------------------");
        return result.toString();
    }

    // Method to generate a formatted ticket
    private static String generateTicket(String start, String dest, Bus bus) {
        return String.format("""
                ************************************
                *         BUS TICKET               *
                ************************************
                *   Bus Name: %s
                *   Bus Type: %s
                *   From: %s
                *   To: %s
                *   Fare: ₹20
                ************************************
                """, bus.name, bus.type, start, dest);
    }

    // Method to display the admin panel for adding buses
    private static void showAdminPanel(JTextArea outputArea) {
        JFrame adminFrame = new JFrame("Admin Panel");
        adminFrame.setSize(400, 300);
        adminFrame.setLayout(new FlowLayout());
        adminFrame.getContentPane().setBackground(new Color(255, 239, 213)); // Light cream background

        // Center the admin panel on the screen
        adminFrame.setLocationRelativeTo(null);

        JTextField busName = new JTextField(10);
        JTextField route = new JTextField(10);
        JTextField type = new JTextField(10);
        JTextField seats = new JTextField(5);
        JTextField available = new JTextField(5);
        JButton addBus = new JButton("Add Bus");

        // Style "Add Bus" button
        addBus.setBackground(new Color(173, 255, 47)); // Green-yellow
        addBus.setForeground(Color.BLACK);

        adminFrame.add(new JLabel("Name:"));
        adminFrame.add(busName);
        adminFrame.add(new JLabel("Route:"));
        adminFrame.add(route);
        adminFrame.add(new JLabel("Type:"));
        adminFrame.add(type);
        adminFrame.add(new JLabel("Seats:"));
        adminFrame.add(seats);
        adminFrame.add(new JLabel("Available:"));
        adminFrame.add(available);
        adminFrame.add(addBus);

        addBus.addActionListener(e -> {
            try {
                String name = busName.getText().trim();
                String routeStr = route.getText().trim();
                String typeStr = type.getText().trim();
                int seatCount = Integer.parseInt(seats.getText().trim());
                boolean isAvailable = Boolean.parseBoolean(available.getText().trim());

                if (name.isEmpty() || routeStr.isEmpty() || typeStr.isEmpty()) {
                    throw new IllegalArgumentException("Fields cannot be empty.");
                }

                buses.add(new Bus(name, routeStr, typeStr, seatCount, isAvailable));
                outputArea.setForeground(new Color(34, 139, 34)); // Success message in green
                outputArea.setText("Bus added successfully!\n" + name + " -> " + routeStr);
                adminFrame.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(adminFrame, "Invalid input. Please check your entries.");
            }
        });

        adminFrame.setVisible(true);
    }

    // Bus class to store bus details
    static class Bus {
        String name, route, type;
        int seats;
        boolean available;

        Bus(String name, String route, String type, int seats, boolean available) {
            this.name = name;
            this.route = route;
            this.type = type;
            this.seats = seats;
            this.available = available;
        }

        @Override
        public String toString() {
            return String.format("%s (%s) - Route: %s, Seats: %d, Available: %s",
                    name, type, route, seats, available ? "Yes" : "No");
        }
    }
}