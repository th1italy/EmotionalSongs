package emotionalsongs.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//
@PageTitle("Registrazione")
@Route(value = "registrazione", layout = MainView.class)
public class RegistrazioneView extends VerticalLayout {

    private Div wrapperDiv;
    private FormLayout registrazione;
    private TextField nome;
    private TextField cognome;
    private DatePicker dataNascita;
    private ComboBox<Character> sesso;
    private ComboBox<String> luogoNascita;
    private TextField codFiscale;
    private Button calcolaCf;
    private TextField via_piazza;
    private EmailField email;
    private TextField username;
    private PasswordField password;
    private PasswordField confirmPassword;
    private Button registerButton;

    public RegistrazioneView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);
        setMargin(false);
        setAlignItems(Alignment.CENTER);

        wrapperDiv = new Div();
        wrapperDiv.getStyle().set("margin-right", "10px")
                .set("max-width", "800px")
                .set("width", "100%")
                .set("margin-left", "10px");
        configureLayoutRegistrazione();
        setComponent();
        configureButton();
        configurePageLayout();
        wrapperDiv.add(registrazione);
        add(wrapperDiv, registerButton);
    }

    private void configureLayoutRegistrazione() {
        registrazione = new FormLayout();
        registrazione.setWidth("100%");
        registrazione.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("25em", 2),
                new FormLayout.ResponsiveStep("32em", 3));

        nome = new TextField("Nome", "Inserisci il tuo nome");
        nome.setSuffixComponent(VaadinIcon.USER.create());
        nome.getStyle().set("color", "black");

        cognome = new TextField("Cognome", "Inserisci il tuo cognome");
        cognome.setSuffixComponent(VaadinIcon.USER.create());
        cognome.getStyle().set("color", "black");

        dataNascita = new DatePicker("Data di nascita");

        List<Character> scelteSesso = Arrays.asList('M', 'F');
        sesso = new ComboBox<>("Sesso", scelteSesso);
        sesso.setPrefixComponent(VaadinIcon.MALE.create());

        luogoNascita = new ComboBox<>("Luogo di nascita");
        luogoNascita.setPrefixComponent(new Icon(VaadinIcon.MAP_MARKER));

        codFiscale = new TextField("Codice Fiscale", "Inserisci il tuo codice fiscale");
        codFiscale.setSuffixComponent(VaadinIcon.BARCODE.create());
        codFiscale.getStyle().set("color", "black");

        via_piazza = new TextField("Via/Piazza", "Inserisci il tuo indirizzo");
        via_piazza.setSuffixComponent(VaadinIcon.HOME.create());
        via_piazza.getStyle().set("color", "black");

        email = new EmailField("Email", "Inserisci il tuo indirizzo email");
        email.setSuffixComponent(VaadinIcon.ENVELOPE.create());
        email.getStyle().set("color", "black");

        username = new TextField("Username", "Scegli un username");
        username.setSuffixComponent(VaadinIcon.USER.create());
        username.getStyle().set("color", "black");

        password = new PasswordField("Password", "Scegli una password");
        password.setSuffixComponent(VaadinIcon.LOCK.create());
        password.getStyle().set("color", "black");

        confirmPassword = new PasswordField("Conferma Password", "Conferma la password");
        confirmPassword.setSuffixComponent(VaadinIcon.LOCK.create());
        confirmPassword.getStyle().set("color", "black");

        calcolaCf = new Button("Codice Fiscale", e -> calcolaCodFiscale());
        calcolaCf.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_CONTRAST);
        calcolaCf.setSuffixComponent(VaadinIcon.CALC_BOOK.create());

        try {
            String comuni = "META-INF/resources/data/Comuni.txt";
            InputStream input = this.getClass().getClassLoader().getResourceAsStream(comuni);

            if (input != null) {
                BufferedReader breader = new BufferedReader(new InputStreamReader(input));

                List<String> comunii = new ArrayList<>();
                String line;

                while ((line = breader.readLine()) != null) {
                    comunii.add(line);
                }

                luogoNascita.setItems(comunii);
            } else {
                throw new RuntimeException("Impossibile trovare il file Comuni.txt");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        registrazione.add(nome, cognome, dataNascita, sesso,
                luogoNascita, via_piazza, codFiscale,
                calcolaCf, email, username,
                password, confirmPassword);
    }

    private void configureButton() {
        registerButton = new Button("Registrati", e -> {
            try {
                registration();
            } catch (SQLException ex) {
                Notification.show("Impossibile effettuare l'operazione", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_CONTRAST);
        registerButton.setIcon(VaadinIcon.CHECK.create());
        registerButton.setAutofocus(true);
        registerButton.setWidth("200px");
    }

    private void configurePageLayout() {
        // Il metodo configurePageLayout() è stato rimosso
    }

    private void setComponent() {
        // Il metodo setComponent() è stato rimosso
    }

    private void calcolaCodFiscale() {
        // Il metodo calcolaCodFiscale() è stato rimosso
    }

    private void registration() throws SQLException {
        // Il metodo registration() è stato rimosso
    }
}
