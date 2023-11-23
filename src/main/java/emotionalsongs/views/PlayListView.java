package emotionalsongs.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import com.vaadin.flow.component.button.ButtonVariant;

@Route("playlist")
public class PlayListView extends AppLayout {

    public PlayListView() {
        H1 appName = new H1("EmotionalSongs");
        Icon logo = new Icon("images/logo.png");
        logo.setSize("40px");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        appName.getElement().getStyle().set("margin-top", "-35px"); // Aggiungi spazio superiore
        Header header = new Header(logo, appName);

        // Aggiungi il pulsante "Ricerca" con icona
        Button searchButton = new Button("Ricerca");
        searchButton.setIcon(new Icon(VaadinIcon.SEARCH));
        searchButton.addThemeVariants(ButtonVariant.LUMO_ICON); // Aggiungi il tema per l'icona
        searchButton.getStyle().set("margin-right", "10px"); // Aggiungi un margine destro
        // Aggiungi qui la logica per la ricerca
        searchButton.addClickListener(e -> UI.getCurrent().navigate("mainview"));

        // Aggiungi il pulsante Login direttamente alla navbar
        Button loginButton = new Button("Login");
        loginButton.setIcon(new Icon(VaadinIcon.USER));
        loginButton.addClickListener(event -> openLoginDialog());

        HorizontalLayout headerLayout = new HorizontalLayout(header);
        headerLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        headerLayout.setWidthFull();
        headerLayout.setPadding(true);
        headerLayout.setFlexGrow(1, header); // Espandi header per occupare tutto lo spazio disponibile
        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER); // Allinea al centro verticalmente

        HorizontalLayout menuLayout = new HorizontalLayout(searchButton, loginButton);
        menuLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        addToDrawer(new Footer());
        addToNavbar(headerLayout, menuLayout);
    }

    private void openLoginDialog() {
        Dialog loginDialog = new Dialog();
        loginDialog.setCloseOnEsc(false);
        loginDialog.setCloseOnOutsideClick(true);

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

        // Aggiungi un pulsante "Chiudi" per chiudere il dialogo
        Button closeButton = new Button("Chiudi");
        closeButton.getElement().getThemeList().add("error");
        closeButton.addClickListener(event -> loginDialog.close());

        // Crea un layout per i pulsanti del dialogo
        HorizontalLayout buttonLayout = new HorizontalLayout(confirmButton, registerButton, closeButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        // Crea un layout per il form del dialogo
        FormLayout formLayout = new FormLayout(usernameField, passwordField);
        formLayout.getStyle().set("margin", "20px");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        // Aggiungi il form e i pulsanti al dialogo
        loginDialog.add(formLayout, buttonLayout);

        loginDialog.open();
    }
}
