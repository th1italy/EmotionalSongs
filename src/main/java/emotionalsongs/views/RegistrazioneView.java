package emotionalsongs.views;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Alessandro Bugno
 * @author Daniele Maccagnan
 * @author Tommaso Mariani
 * <p></p>
 * Classe che rappresenta la vista per la registrazione di un nuovo utente.
 * @version 1.0
 */
@PageTitle("Registrazione")
@Route(value = "registrazione")
public class RegistrazioneView extends VerticalLayout {

    HorizontalLayout layoutTitolo;
    Icon iconTitolo;
    H3 titoloPagina;
    FormLayout registrazione;
    TextField nome;
    TextField cognome;
    DatePicker dataNascita;
    List<Character> scelteSesso;
    ComboBox<Character> sesso;
    ComboBox<String> luogoNascita;
    TextField codFiscale;
    Button calcolaCf;
    TextField via_piazza;
    EmailField email;
    TextField username;
    PasswordField password;
    PasswordField confirmPassword;
    VerticalLayout regButtonLayout;
    Button registerButton;
    VerticalLayout pageLayout;
//    ClientES client = ClientES.getInstance();


    /**
     * Costruttore per la vista per la registrazione di un nuovo utente.
     */
    public RegistrazioneView() {
        setSpacing(false);
        setSizeFull();

        registerButton = new Button("Registrati", buttonClickEvent -> {
            try {
                registration();
            } catch (SQLException e) {
                Notification.show("Impossibile effettuare l'operazione", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        });

        calcolaCf = new Button("Calcola CF", buttonClickEvent -> calcolaCodFiscale());

        configureLayoutTitolo();
        configureLayoutRegistrazione();
        setComponent();
        configureButton();
        configurePageLayout();
        add(layoutTitolo, pageLayout);
    }

    /**
     * Metodo privato che permette la creazione del layout dell'intestazione della pagina.
     */
    private void configureLayoutTitolo() {
        layoutTitolo = new HorizontalLayout();
        layoutTitolo.setAlignItems(FlexComponent.Alignment.CENTER);
        iconTitolo = new Icon(VaadinIcon.USERS);
        iconTitolo.setColor("#006af5");
        titoloPagina = new H3("Nuovo Utente");
        layoutTitolo.add(iconTitolo, titoloPagina);
    }

    /**
     * Metodo privato che permette la creazione del layout contenente i campi che l'utente deve compilare per reistrarsi all'applicazione.
     */
    private void configureLayoutRegistrazione() {
        registrazione = new FormLayout();
        nome = new TextField("Nome");
        cognome = new TextField("Cognome");
        dataNascita = new DatePicker("Data di nascita");
        luogoNascita = new ComboBox<>("Luogo di nascita");
        scelteSesso = Arrays.asList('M', 'F');
        sesso = new ComboBox<>("Sesso", scelteSesso);
        codFiscale = new TextField("Codice Fiscale");
        via_piazza = new TextField("Via/Piazza");
        email = new EmailField("Email");
        username = new TextField("Username");
        password = new PasswordField("Password");
        confirmPassword = new PasswordField("Conferma Password");
        registrazione.add(nome, cognome, dataNascita, sesso,
                luogoNascita, via_piazza, codFiscale,
                calcolaCf, email, username,
                password, confirmPassword);
    }

    /**
     * Metodo privato che permette la creazione del layout per il pulsante per confermare la registrazione.
     */
    private void configureButton() {
        regButtonLayout = new VerticalLayout();
        regButtonLayout.setAlignItems(Alignment.CENTER);
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerButton.setIcon(VaadinIcon.USERS.create());
        registerButton.setAutofocus(true);
        registerButton.setWidth("420px");
        regButtonLayout.add(registerButton);
    }

    /**
     * Metodo privato che permette la configurazione del layout dell'intera pagina
     */
    private void configurePageLayout() {
        pageLayout = new VerticalLayout();
        pageLayout.setSizeFull();
        pageLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        pageLayout.add(registrazione, registerButton);
    }

    /**
     * Metodo privato che imposta tutti i campi necessari per la registrazione con le relative condizioni.
     */
    private void setComponent() {
        nome.setSuffixComponent(VaadinIcon.USER.create());
        cognome.setSuffixComponent(VaadinIcon.USER.create());
        codFiscale.setSuffixComponent(VaadinIcon.BARCODE.create());
        Icon CF = new Icon("C:\\Users\\danym\\Desktop\\demo\\demo\\src\\main\\resources\\META-INF\\resources\\images\\codicefiscaleicon.png");
        codFiscale.setSuffixComponent(CF);
        via_piazza.setSuffixComponent(VaadinIcon.HOME.create());
        email.setSuffixComponent(VaadinIcon.ENVELOPE.create());
        username.setSuffixComponent(VaadinIcon.USER.create());
        calcolaCf.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        nome.setRequired(true);
        nome.setPattern("^[a-zA-Z\\s]*$");
        nome.setErrorMessage("Non sono ammessi numeri e/o caratteri speciali, solo lettere e spazi");
        cognome.setRequired(true);
        cognome.setPattern("^[a-zA-Z\\s]*$");
        cognome.setErrorMessage("Non sono ammessi numeri e/o caratteri speciali, solo lettere e spazi");
        sesso.setRequired(true);
        luogoNascita.setRequired(true);
        dataNascita.setRequired(true);
        dataNascita.setMax(LocalDate.now());
        codFiscale.setMaxLength(16);
        codFiscale.setRequired(true);
        codFiscale.setPattern("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$");
        via_piazza.setRequired(true);
        email.setRequired(true);
        username.setRequired(true);
        username.setMinLength(5);
        username.setErrorMessage("Almeno 5 caratteri");
        password.setRequired(true);
        password.setMinLength(8);
        password.setErrorMessage("Almeno 8 caratteri");
        confirmPassword.setRequired(true);

    }

    /**
     * Metodo privato che viene utilizzato per il calcolo del codice fiscale.
     */
    private void calcolaCodFiscale() {
        if (!nome.getValue().equals("") && !cognome.getValue().equals("") && luogoNascita.getValue() != null && dataNascita.getValue() != null && sesso.getValue() != null) {
            String nomeCf = nome.getValue();
            String cognomeCf = cognome.getValue();
            String luogoNascitaCf = luogoNascita.getValue();
            LocalDate selectedDate = dataNascita.getValue();
            Character sessoCf = sesso.getValue();

            //CityByName cities = CityProvider.ofDefault();
           // City city = cities.findByName(luogoNascitaCf);

           /* Person person =	Person.builder()
                    .firstname(nomeCf)
                    .lastname(cognomeCf)
                    .birthDate(LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), selectedDate.getDayOfMonth()))
                    .isFemale(isFemale(sessoCf))
                    .city(city)
                    .build();
            CodiceFiscale cf = CodiceFiscale.of(person);

            codFiscale.setValue(cf.getValue());*/

        } else {
            Notification.show("Dati Mancanti", 3000, Notification.Position.MIDDLE)
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

    /**
     * Metodo privato che effettua i controlli sui dati inseriti dall'utente ed esegue le query di inserimento.
     */
    private void registration() throws SQLException {
        String nome = this.nome.getValue();
        String cognome = this.cognome.getValue();
        String user = username.getValue();
        String password = this.password.getValue();
        String confPassword = this.confirmPassword.getValue();
        String indirizzo = this.via_piazza.getValue();
        String codFiscale = this.codFiscale.getValue();
        String email = this.email.getValue();
        if (nome.isEmpty() || cognome.isEmpty() || dataNascita.isEmpty() || sesso.isEmpty() || luogoNascita.isEmpty() || via_piazza.isEmpty() || codFiscale.isEmpty() || username.isEmpty() || email.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {
            Notification.show("Dati Mancanti", 3000, Notification.Position.MIDDLE)
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
        } else {
            if (password.equals(confPassword)) {
                if(user.length() < 5 || password.length() < 8 || !codFiscale.matches("^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$") || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
                    Notification.show("Dati non validi!", 3000, Notification.Position.MIDDLE)
                            .addThemeVariants(NotificationVariant.LUMO_ERROR);
                }else {
                   /* List<String> usernames = client.getUsernames();
                    if (usernameUnivoco(usernames, user)) {
                        client.registrazione(nome, cognome, indirizzo, codFiscale, email, user, password);
                        Notification.show("Registrazione effettuata", 4000, Notification.Position.MIDDLE)
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                        UI.getCurrent().navigate(RicercaView.class);
                    } else {
                        Notification.show("Username già presente", 3000, Notification.Position.MIDDLE)
                                .addThemeVariants(NotificationVariant.LUMO_ERROR);
                    }*/
                }
            } else {
                Notification.show("Le password non coincidono", 3000, Notification.Position.MIDDLE)
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        }
    }

    /**
     * Metodo privato che controlla che l'username inserito dall'utente non sia già presente nell'applicazione.
     * @param usernames Una lista contente gli username registrati all'applicazione.
     * @param username L'username inserito dall'utente.
     * @return true se l'username non è già presente, false altriemnti
     */
    private boolean usernameUnivoco(List<String> usernames, String username) {
        for (String u : usernames) {
            if (u.equals(username))
                return false;
        }
        return true;
    }

    /**
     * Metodo privato utilizzato per controllare se l'utente è maschio o femmina.
     * Verrà utilizzato per il calcolo del codice fiscale.
     * @param sesso Il sesso indicato dall'utente
     * @return true se l'utente è femmina, false altriementi.
     */
    private boolean isFemale(Character sesso){
        return sesso == 'F';
    }

}
