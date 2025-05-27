
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Order {
    private final AppFront appFront;
    private final PemesananTiketBioskopMini app;

    public Order(AppFront appFront, PemesananTiketBioskopMini app) {
        this.app = app;
        this.appFront = appFront;
    }

    public boolean isUserLoggedIn() {
        return appFront != null;
    }

    public String getUserName() {
        if (appFront == null || appFront.getUserName().isEmpty()) {
            return "Tidak terindentifikasi";
        } else {
            return appFront.getUserName();
        }
    }

    public boolean isValidSelection(String filmName, String jamTayang) {
        return !(filmName.equals("---Select Option---")) && !(jamTayang.equals("--Select Jadwal---"));
    }

    public String getSelectedSeats(JPanel seatPanel) {
        StringBuilder seat = new StringBuilder();
        for (Component component : seatPanel.getComponents()) {
            if (component instanceof JPanel panel) {
                for (Component button : panel.getComponents()) {
                    if (button instanceof JButton && button.getBackground().equals(Color.red)) {
                        seat.append(button.getName()).append(" ");
                    }
                }
            }
        }
        return seat.toString().trim();
    }

    public void OrderAction(Component parent, String filmName, String jamTayang, JPanel seatPanel) {
        if (!isUserLoggedIn()) {
            JOptionPane.showMessageDialog(parent, "Silahkan login terlebih dahulu", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isValidSelection(filmName, jamTayang)) {
            JOptionPane.showMessageDialog(parent, "Silahkan pilih film dan jam tayang", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            Invoice invoice = new Invoice(appFront, app, this);
            invoice.setVisible(true);
            app.dispose();
        }
    }
}

