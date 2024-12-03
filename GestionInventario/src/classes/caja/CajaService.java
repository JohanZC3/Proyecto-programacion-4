package classes.caja;

import classes.backSerialId.SerialId;
import classes.backSerialId.SerialIdRepository;

public class CajaService {
    public static int loadCajaId() {
        SerialId serialId = SerialIdRepository.obtenerSerialIdPorId(1);
        int lastId = serialId.getLastidCaja();
        lastId++;
        return lastId;
    }

    public static void actualizarIds() {
        SerialId serialId = SerialIdRepository.obtenerSerialIdPorId(1);
        serialId.setLastidCaja(serialId.getLastidCaja() + 1);
        SerialIdRepository.modificarSerialId(serialId);
    }

}
