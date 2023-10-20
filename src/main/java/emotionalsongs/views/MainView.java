package emotionalsongs.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        setJustifyContentMode(JustifyContentMode.END);
        setPadding(true);
        getStyle().set("background-color", "#C2C2D6");

        // Crea un'icona personalizzata dall'immagine
        Image customIcon = new Image("images/logo.png", "logo");
        customIcon.setHeight("100px");
        customIcon.setWidth("100px");

        // Crea un testo leggermente più grande utilizzando il componente Span
        Span text = new Span("EmotionalSongs");
        text.getElement().getStyle().set("font-size", "1.2em");

        // Crea un layout orizzontale per l'icona e il testo
        HorizontalLayout headerLayout = new HorizontalLayout(customIcon, text);
        headerLayout.setAlignItems(Alignment.CENTER);

        // Crea un pulsante "Login" con un'icona dalla libreria Vaadin
        Button loginButton = new Button("Login");
        loginButton.setIcon(new Icon(VaadinIcon.USER));

        // Aggiungi gli elementi al layout
        add(headerLayout, loginButton);


        // Allinea il pulsante "Login" in alto a destra
        setHorizontalComponentAlignment(Alignment.END, loginButton);
    }
}
