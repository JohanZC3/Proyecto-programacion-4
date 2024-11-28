package classes.backHistorial;

import classes.backSerialId.SerialId;
import classes.backSerialId.SerialIdRepository;

public class HistorialService {

    public static int loadHistorialId() {
        SerialId serialId = SerialIdRepository.obtenerSerialIdPorId(1);
        int lastId = serialId.getLastidHistorial();
        lastId++;
        return lastId;
    }

    public static void actualizarIds() {
        SerialId serialId = SerialIdRepository.obtenerSerialIdPorId(1);
        serialId.setLastidHistorial(serialId.getLastidHistorial() + 1);
        SerialIdRepository.modificarSerialId(serialId);
    }
}
