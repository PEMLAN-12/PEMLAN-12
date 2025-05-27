import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Customer {
    File f = new File("src");
    private final String user;
    private final String password;
    private int ln =0;

    public Customer(String userName, String password) {
        this.user = userName;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void logicLogin(String usr, String pwd, AppFront loginWindow) {
    try {
        RandomAccessFile raf = new RandomAccessFile(f + "logins.txt", "r");
        String line;
        boolean found = false;

        while ((line = raf.readLine()) != null) {
            if (line.startsWith("Username:")) {
                String forUser = line.length() >= 9 ? line.substring(9).trim() : "";
                String passLine = raf.readLine(); // baca baris berikutnya (Password)
                if (passLine != null && passLine.startsWith("Password:")) {
                    String forPassword = passLine.length() >= 9 ? passLine.substring(9).trim() : "";
                    if (usr.equals(forUser) && pwd.equals(forPassword)) {
                        found = true;
                        PemesananTiketBioskopMini app = new PemesananTiketBioskopMini(loginWindow);
                        app.setVisible(true);
                        loginWindow.dispose();
                        break; // berhenti karena login sukses
                    }
                }
            }
        }

        raf.close();

        if (!found) {
            JOptionPane.showMessageDialog(null, "Wrong user/password", "Error", JOptionPane.ERROR_MESSAGE);
        }

    } catch (FileNotFoundException ex) {
        Logger.getLogger(AppFront.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(AppFront.class.getName()).log(Level.SEVERE, null, ex);
    }
}


    public void countLines() {
            
        try {
            RandomAccessFile raf = new RandomAccessFile(f + "logins.txt", "r");
            while (raf.readLine() != null) {
                ln++;
            }
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AppFront.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AppFront.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readFile() {
        File file = new File(f + "logins.txt");

        if (file.exists()) {
            System.out.println("File exists.");
        } else {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("Failed to created file.");
                }
            } catch (IOException | SecurityException e) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void createFolder() {
        if (!f.exists()){
            f.mkdirs();
        }
    }

    public void addDataCustomer(String usr, String pwd) {
        try {
            RandomAccessFile raf = new RandomAccessFile(f+"logins.txt", "rw");
            for(int i = 0;i<ln;i++){
                raf.readLine();
            }
            raf.seek(raf.length());

            if (raf.length() > 0){
                raf.writeBytes(System.lineSeparator());
            }
            raf.writeBytes("Username:" + usr + "\r\n");
            raf.writeBytes("Password:" + pwd);
            raf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
