
import domein.BeheerderController;
import domein.ProductController;
import domein.Reservatie;
import domein.ReservatieController;
import gui.LoginSchermController;
import java.util.GregorianCalendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartUp extends Application {

    private ProductController pc;
    private ReservatieController rc;
    
    @Override
    public void start(Stage stage) {
        maakdata();
        Scene scene = new Scene(new LoginSchermController(new BeheerderController() , pc , rc));
        stage.setScene(scene);
        stage.setTitle("Didactische leermiddelen :  Login");
        // The stage will not get smaller than its preferred (initial) size.
        stage.setOnShown((WindowEvent t) -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String... args) {
               
        Application.launch(StartUp.class, args);
    }
    public void maakdata()
    {
    
     pc = new ProductController();
        rc = new ReservatieController();
        
         GregorianCalendar startDatum1 = new GregorianCalendar(2016, 3, 6, 8, 0, 0);
        GregorianCalendar eindDatum1 = new GregorianCalendar(2016, 3, 10, 17, 0, 0);

        GregorianCalendar startDatum2 = new GregorianCalendar(2016, 3, 13, 8, 0, 0);
        GregorianCalendar eindDatum2 = new GregorianCalendar(2016, 3, 17, 17, 0, 0);

        GregorianCalendar startDatum3 = new GregorianCalendar(2016, 3, 13, 8, 0, 0);
        GregorianCalendar eindDatum3 = new GregorianCalendar(2016, 5, 17, 17, 0, 0);

        GregorianCalendar startDatum4 = new GregorianCalendar(2016, 4, 9, 8, 0, 0);
        GregorianCalendar eindDatum4 = new GregorianCalendar(2016, 4, 13, 17, 0, 0);

        String gebruiker1 = "student1@hogent.be";
        String gebruiker2 = "student2@hogent.be";

        rc.addReservatie(new Reservatie(startDatum1.toZonedDateTime().toLocalDate(), eindDatum1.toZonedDateTime().toLocalDate(), gebruiker1, pc.getProductById(1), 5, 2, 3));
        rc.addReservatie(new Reservatie(startDatum1.toZonedDateTime().toLocalDate(), eindDatum1.toZonedDateTime().toLocalDate(), gebruiker2, pc.getProductById(2), 6, 0, 6));
        rc.addReservatie(new Reservatie(startDatum2.toZonedDateTime().toLocalDate(), eindDatum2.toZonedDateTime().toLocalDate(), gebruiker1, pc.getProductById(3), 12, 12, 0));
        rc.addReservatie(new Reservatie(startDatum2.toZonedDateTime().toLocalDate(), eindDatum2.toZonedDateTime().toLocalDate(), gebruiker1, pc.getProductById(4), 10, 5, 4));
        rc.addReservatie(new Reservatie(startDatum3.toZonedDateTime().toLocalDate(), eindDatum3.toZonedDateTime().toLocalDate(), gebruiker2, pc.getProductById(1), 5, 0, 0));
        rc.addReservatie(new Reservatie(startDatum3.toZonedDateTime().toLocalDate(), eindDatum3.toZonedDateTime().toLocalDate(), gebruiker2, pc.getProductById(1), 5, 5, 0));
        rc.addReservatie(new Reservatie(startDatum4.toZonedDateTime().toLocalDate(), eindDatum4.toZonedDateTime().toLocalDate(), gebruiker2, pc.getProductById(1), 5, 0, 0));

        
        
    
    
    }
}
