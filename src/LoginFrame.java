import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

/**
 * Created by Hakim on 31/05/14.
 */
public class LoginFrame extends JFrame implements ActionListener {
    private Annuaire annuaire;
    private JLabel nameLabel = new JLabel("Nom");
    private JLabel passwordLabel = new JLabel("Password");
    private JTextField nameTextBox = new JTextField();
    private JTextField passwordTextBox = new JTextField();
    private JButton loginButton = new JButton("Login");
    private JButton createButton = new JButton("Creer");

    public LoginFrame(Annuaire annuaire){
        this.annuaire = annuaire;
        setLayout(new GridLayout(3, 2));

        add(nameLabel);
        add(nameTextBox);
        add(passwordLabel);
        add(passwordTextBox);
        add(loginButton);
        add(createButton);
        loginButton.addActionListener(this);
        createButton.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(loginButton)) {
            try {
                Mur user = annuaire.login(nameTextBox.getText(), passwordTextBox.getText());
                if(user != null){
                    new MainFrame(annuaire, user);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Incorrect Name/Password", "Unable to login", JOptionPane.ERROR_MESSAGE);
                }
            } catch (RemoteException e1) {
                JOptionPane.showMessageDialog(this, "Connection error", "Unable to login", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource().equals(createButton)) {
            try {
                annuaire.createUser(nameTextBox.getText(), passwordTextBox.getText());
            } catch (RemoteException e1) {
                JOptionPane.showMessageDialog(this, "Connection error", "Unable to create user", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
