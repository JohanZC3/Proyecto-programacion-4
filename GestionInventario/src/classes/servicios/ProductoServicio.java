package classes.servicios;

import java.time.LocalDate;

public class ProductoServicio {
    public boolean validacionInformacion(int id, String productName, int productCategory, int amoundint,
            Double price, int idProveedor) {
        if (id <= 0) {
            return false;
        }
        if (productName == null || productName.isEmpty()) {
            return false;
        }
        if (productCategory <= 0) {
            return false;
        }
        if (amoundint <= 0) {
            return false;
        }
        if (price <= 0) {
            return false;
        }
        if (idProveedor <= 0) {
            return false;
        }
        return true;
    }

    public boolean validarFecha(int year, int month, int day) {
        if (year < 1 || month < 1 || month > 12 || day < 1) {
            return false;
        }

        int[] daysInMonth = { 31, (isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (day > daysInMonth[month - 1]) {
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate inputDate = LocalDate.of(year, month, day);
        return inputDate.isAfter(currentDate);
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
