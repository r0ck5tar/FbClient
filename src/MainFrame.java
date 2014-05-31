import javax.swing.*;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Hakim on 31/05/14.
 */
public class MainFrame extends JFrame{
    private Annuaire annuaire;
    private Mur user;
    private JList<String> publications;
    private JList<String> amis;

    public MainFrame(Annuaire annuaire, Mur user) {
        this.annuaire = annuaire;
        this.user = user;


        pack();
        setVisible(true);
    }

    private void majListePublications() {
        publications = new JList<String>();

        List<String> listPublications = null;
        try {
            listPublications = user.getContenu();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(listPublications != null) {

            for(String msg : listPublications) {

            }
        }
    }
}
