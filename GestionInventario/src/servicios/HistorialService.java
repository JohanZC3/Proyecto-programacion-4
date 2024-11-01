package servicios;

import java.util.ArrayList;
import java.util.Optional;

import classes.Historial;
import repositorios.HistorialRepository;

public class HistorialService {
    public int obtenerMaxIdHistorial() {
        new HistorialRepository();
        ArrayList<Historial> historiales = HistorialRepository.obtenerHistoriales();
        Optional<Historial> maxIdHistorial = historiales.stream()
            .max((h1, h2) -> Integer.compare(h1.getId(), h2.getId()));

        int maxId = maxIdHistorial.isPresent() ? maxIdHistorial.get().getId() : -1;
        System.out.println("Max id: " + maxId);
        maxId++;
        return maxId;
    }
}
