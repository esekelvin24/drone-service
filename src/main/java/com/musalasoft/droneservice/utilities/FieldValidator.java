package com.musalasoft.droneservice.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidator {

    public static boolean validateDronSn(String sn)
    {
        if (sn.length() > 100)
        {
            return false;
        }
        return true;
    }

    public static boolean validateMedicationName(String medicationName)
    {
        String regex = "^[a-zA-Z0-9 _-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(medicationName);

        return matcher.matches();
    }

    public static boolean validateMedicationCode(String medicationCode)
    {
        String regex = "^[A-Z0-9 _-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(medicationCode);

        return matcher.matches();
    }
}
