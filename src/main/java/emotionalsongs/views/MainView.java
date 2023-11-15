package emotionalsongs.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("")
public class MainView extends AppLayout {

    public MainView() {
        H1 appName = new H1("EmotionalSongs");
        Icon logo = new Icon("images/codicefiscale.png");
        logo.setSize("40px");
        logo.setSize("40px");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(logo, appName);


        addToDrawer(header,  new Footer());


        Button loginButton = new Button("Login");
        loginButton.setIcon(new Icon(VaadinIcon.USER));
        loginButton.getElement().getStyle().set("margin-left", "auto"); // Sposta a destra

        loginButton.addClickListener(event -> openLoginDialog());

        addToNavbar(loginButton);
    }

    private void openLoginDialog() {
        Dialog loginDialog = new Dialog();
        loginDialog.setCloseOnEsc(false);
        loginDialog.setCloseOnOutsideClick(false);

        // Aggiungi i campi di testo per username e password
        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");

        // Aggiungi un pulsante per confermare il login
        Button confirmButton = new Button("Login");
        confirmButton.getElement().getThemeList().add("primary");

        // Aggiungi un'azione quando si preme il pulsante di conferma
        confirmButton.addClickListener(event -> {
            // Qui puoi aggiungere la logica di autenticazione
            // e chiudere la finestra di dialogo se l'autenticazione ha successo
            loginDialog.close();
        });

        // Aggiungi un link per la registrazione
        Button registerButton = new Button("Nuovo? Registrati");
        registerButton.addClickListener(event -> {
            UI.getCurrent().navigate(RegistrazioneView.class);
            loginDialog.close();
            // Potresti chiudere anche la finestra di login prima di aprire quella di registrazione
        });

        loginDialog.add(usernameField, passwordField, confirmButton, registerButton);
        loginDialog.open();
    }
}
