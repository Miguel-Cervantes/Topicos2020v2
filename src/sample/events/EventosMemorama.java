package sample.events;

import javafx.event.Event;
import javafx.event.EventHandler;

public class EventosMemorama implements EventHandler {
    @Override
    public void handle(Event event) {
        System.out.println("segundo evento en otra clase");
    }
}
