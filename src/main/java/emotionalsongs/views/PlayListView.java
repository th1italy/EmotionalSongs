package emotionalsongs.views;
//
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
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
import com.vaadin.flow.component.button.ButtonVariant;

@Route("playlist")
public class PlayListView extends AppLayout {

    public PlayListView() {
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

        // Aggiungi il pulsante "Ricerca" con icona

        Button searchButton = new Button("Ricerca");
        searchButton.setIcon(new Icon(VaadinIcon.SEARCH));
        searchButton.addThemeVariants(ButtonVariant.LUMO_ICON); // Aggiungi il tema per l'icona
        searchButton.getStyle().set("margin-right", "10px"); // Aggiungi un margine destro
        // Aggiungi qui la logica per la ricerca
        searchButton.addClickListener(e -> UI.getCurrent().navigate(""));

        // Aggiungi il pulsante Login direttamente alla navbar
        Button loginButton = new Button("Login");
        loginButton.setIcon(new Icon(VaadinIcon.USER));
        loginButton.addClickListener(event -> openLoginDialog());

        // Aggiungere i bottoni al layout dell'header
        HorizontalLayout menuLayout = new HorizontalLayout(searchButton, loginButton);
        menuLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        // Creare la disposizione dell'header
        addToNavbar(headerLayout, menuLayout);


        if(isLogged()==false) {
            // Aggiungere la scritta a sinistra
            H3 labelText = new H3("DEVI ESSERE LOGGATO");
            labelText.getStyle().set("font-weight", "bold");
            labelText.getStyle().set("color", "black");

            VerticalLayout contentLayout = new VerticalLayout(labelText);
            contentLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.START);
            setContent(contentLayout);
         } else {
            H3 PlaylistText = new H3("LE MIE PLAYLIST");
            PlaylistText.getStyle().set("font-weight", "bold");
            PlaylistText.getStyle().set("color", "black");
            Button aggiungiPlaylist = new Button("Aggiungi Playlist");
            aggiungiPlaylist.addClickListener(event -> dialogCreatePlaylist());
            aggiungiPlaylist.setIcon(new Icon(VaadinIcon.PLUS));
            aggiungiPlaylist.addThemeVariants(ButtonVariant.LUMO_ICON);
            aggiungiPlaylist.getStyle().set("margin-right", "10px");

            HorizontalLayout searchLayout = new HorizontalLayout(aggiungiPlaylist);
            searchLayout.setAlignItems(FlexComponent.Alignment.CENTER);
            searchLayout.setWidthFull();
            searchLayout.getStyle().set("padding", "20px"); // Aggiungi spazio al layout

            VerticalLayout contentLayout = new VerticalLayout(PlaylistText, searchLayout);
            contentLayout.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.START);
            setContent(contentLayout);

        }


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

    private boolean isLogged() {
        return true;
    }

    private void dialogCreatePlaylist() {
        H1 title = new H1("Aggiungi Playlist \uD83C\uDFB6");
        Dialog dialog = new Dialog();
        TextField playlistName = new TextField("Titolo Playlist");
        playlistName.setWidthFull();
        playlistName.setRequired(true);

        // Aggiungi il pulsante "Aggiungi Playlist" con icona
        Button addPlaylistButton = new Button("Aggiungi Playlist");
        addPlaylistButton.setIcon(new Icon(VaadinIcon.PLUS));
        addPlaylistButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS); // Aggiungi il tema per l'icona
        addPlaylistButton.getStyle().set("margin-right", "10px"); // Aggiungi un margine destro
        // Aggiungi qui la logica per aggiungere una nuova playlist
        addPlaylistButton.addClickListener(e -> {
            // Aggiungi qui la logica per aggiungere una nuova playlist
            // ...
            dialog.close(); // Chiudi il dialogo dopo aver aggiunto la playlist
        });

        Button closeButton = new Button("Chiudi", buttonClickEvent -> dialog.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        closeButton.setIcon(VaadinIcon.CLOSE_CIRCLE.create());
        dialog.setCloseOnEsc(true);
        dialog.open();

        VerticalLayout newPlaylistForm = new VerticalLayout(title, playlistName, addPlaylistButton, closeButton);
        newPlaylistForm.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        newPlaylistForm.setAlignItems(FlexComponent.Alignment.CENTER);

        dialog.add(newPlaylistForm);
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


