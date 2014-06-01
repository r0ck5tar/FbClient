import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Hakim on 31/05/14.
 */
public class ClientFb {
    private Annuaire annuaire;

    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.getRegistry(1099);

        ClientFb client = new ClientFb();
        try {
            client.annuaire = (Annuaire) registry.lookup("Annuaire");
        } catch (NotBoundException e) {
            System.err.println("Annuaire unavailable");
            client.annuaire = null;
        }

        //new LoginFrame(client.annuaire);


        if(client.annuaire != null) {
            /*
            client.annuaire.createUser("Hakim", "1234");

            Invitation hakim = client.annuaire.findUser("Hakim");

            System.out.println(hakim.quiEsTu());

            Mur murHakim = client.annuaire.login("Hakim", "1234");

            if(murHakim != null) {
                System.out.println("login success");
            }
            else{
                System.out.println("login fail");
            }
            */

            client.annuaire.createUser("Hakim", "1234");
            client.annuaire.createUser("Clément", "1234");

            Invitation hakim = client.annuaire.findUser("Hakim");
            Invitation clement = client.annuaire.findUser("Clément");

            Utilisateur userHakim = client.annuaire.login("Hakim", "1234");
            Utilisateur userClement = client.annuaire.login("Clément", "1234");

            clement.invite(hakim);  //hakim invites clément

            System.out.println("InvitationsEnAttente,  Clément: " + userClement.getInvitationsEnAttente().size());
            System.out.println("DemandeAmiEnAttente,  Hakim: " + userHakim.getDemandeAmiEnAttente().size());

            hakim.accept(userClement.getMur());

            //clement.accept(murHakim); //clément accepts

            System.out.println("amis de Hakim: " + userHakim.getMur().getListeAmis().size());
            System.out.println("amis de Clément: " + userClement.getMur().getListeAmis().size());

            System.out.println("InvitationsEnAttente,  Clément: " + userClement.getInvitationsEnAttente().size());
            System.out.println("DemandeAmiEnAttente,  Hakim: " + userHakim.getDemandeAmiEnAttente().size());

        }

    }
}
