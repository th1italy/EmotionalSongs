package emotionalsongs.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends AppLayout {

    public MainView() {
        // Creare il titolo dell'app
        H1 appName = new H1("EmotionalSongs");

        // Caricare e impostare il logo
        Icon logo = new Icon("frontend/images/logo.png");  // Utilizza il percorso corretto

        // Creare un'intestazione (header)
        Header header = new Header(logo, appName);

        // Aggiungere il logo e il nome dell'app all'header
        HorizontalLayout headerLayout = new HorizontalLayout(logo, appName);
        headerLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        headerLayout.setWidthFull();
        headerLayout.setPadding(true);
        headerLayout.setFlexGrow(1, header);
        headerLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        // Aggiungere il pulsante "Playlist" con icona
        Button playlistButton = new Button("Playlist");
        playlistButton.setIcon(new Icon(VaadinIcon.MUSIC));
        playlistButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        playlistButton.getStyle().set("margin-right", "10px");
        playlistButton.addClickListener(e -> UI.getCurrent().navigate("playlist"));

        // Aggiungere il pulsante Login direttamente alla navbar
        Button loginButton = new Button("Login");
        loginButton.setIcon(new Icon(VaadinIcon.USER));
        loginButton.addClickListener(event -> openLoginDialog());

        // Aggiungere i bottoni al layout dell'header
        HorizontalLayout menuLayout = new HorizontalLayout(playlistButton, loginButton);
        menuLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        // Creare la disposizione dell'header
        addToNavbar(headerLayout, menuLayout);

        // Aggiungere la barra di ricerca sotto l'header e a destra del footer
        TextField titolo = new TextField();
        titolo.setPlaceholder("Titolo");
        TextField anno = new TextField();
        anno.setPlaceholder("Anno");
        TextField autore = new TextField();
        autore.setPlaceholder("Autore");
        Button cerca = new Button("Cerca");
        cerca.setIcon(new Icon(VaadinIcon.SEARCH));
        cerca.addThemeVariants(ButtonVariant.LUMO_ICON);

        HorizontalLayout searchLayout = new HorizontalLayout(titolo, anno, autore, cerca);
        searchLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        searchLayout.setWidthFull();
        searchLayout.getStyle().set("padding", "20px"); // Aggiungi spazio al layout

        // Aggiungere la scritta a sinistra
        H3 labelText = new H3("Ricerca per Titolo, Anno, Autore:");
        labelText.getStyle().set("font-weight", "bold");
        labelText.getStyle().set("color", "black");

        VerticalLayout contentLayout = new VerticalLayout(labelText, searchLayout);
        contentLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.START);
        setContent(contentLayout);

        // Aggiungere il footer
        Footer footer = new Footer();
        addToDrawer(footer);

        // Aggiungere l'autore al footer
        VerticalLayout footerContent = new VerticalLayout();
        footerContent.setWidthFull();
        footerContent.getStyle().set("text-align", "center");

        // Aggiungere il testo degli autori
        H3 autoreLabel = new H3("Autori:");
        autoreLabel.getStyle().set("font-weight", "bold");
        autoreLabel.getStyle().set("color", "black");

        // Aggiungere gli autori
        H3 autore1 = new H3("Daniele Maccagnan");
        H3 autore2 = new H3("Alessandro Bugno");
        H3 autore3 = new H3("Tommaso Mariani");

        // Aggiungere gli autori al layout
        footerContent.add(autoreLabel, autore1, autore2, autore3);

        // Aggiungere il layout degli autori al footer
        footer.getElement().appendChild(footerContent.getElement());
        // Aggiungere il footer
        addToDrawer(new Footer());

        // ... (restante codice)
    }



    private void openLoginDialog() {
        // Creare il dialogo di login
        Dialog loginDialog = new Dialog();
        loginDialog.setCloseOnEsc(false);
        loginDialog.setCloseOnOutsideClick(true);

        // Aggiungere i campi di testo per username e password
        TextField usernameField = new TextField("Username");
        PasswordField passwordField = new PasswordField("Password");

        // Aggiungere un pulsante per confermare il login
        Button confirmButton = new Button("Login");
        confirmButton.getElement().getThemeList().add("primary");

        // Aggiungere un'azione quando si preme il pulsante di conferma
        confirmButton.addClickListener(event -> {
            // Qui puoi aggiungere la logica di autenticazione
            // e chiudere la finestra di dialogo se l'autenticazione ha successo
            loginDialog.close();
        });

        // Aggiungere un link per la registrazione
        Button registerButton = new Button("Nuovo? Registrati");
        registerButton.addClickListener(event -> {
            UI.getCurrent().navigate(RegistrazioneView.class);
            loginDialog.close();
        });

        // Aggiungere un pulsante "Chiudi" per chiudere il dialogo
        Button closeButton = new Button("Chiudi");
        closeButton.getElement().getThemeList().add("error");
        closeButton.addClickListener(event -> loginDialog.close());

        // Creare un layout per i pulsanti del dialogo
        HorizontalLayout buttonLayout = new HorizontalLayout(confirmButton, registerButton, closeButton);
        buttonLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        // Creare un layout per il form del dialogo
        FormLayout formLayout = new FormLayout(usernameField, passwordField);
        formLayout.getStyle().set("margin", "20px");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("600px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        // Aggiungere il form e i pulsanti al dialogo
        loginDialog.add(formLayout, buttonLayout);

        // Aprire il dialogo di login
        loginDialog.open();
    }
}
