package util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Jens
 */
public class Helper {

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static String format(GregorianCalendar calendar) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
        fmt.setCalendar(calendar);
        String dateFormatted = fmt.format(calendar.getTime());
        return dateFormatted;
    }
    
    public static List<LocalDate> geefDagenTussen(LocalDate start, LocalDate eind){
        List<LocalDate> tussen = new ArrayList<>();
        for (LocalDate date = start; !date.isAfter(eind); date = date.plusDays(1)) {
            tussen.add(date);
        }
        return tussen;
    }

}
