package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


/**
 * Class DateUtils
 *
 * @author Shahzeb Iqbal
 * @date 6/19/2023
 */
@SuppressWarnings({"all"})
public class DateUtils {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";                                   // Example: 2023-06-14
    public static final String MM_DD_YYYY = "MM/dd/yyyy";                                   // Example: 06/14/2023
    public static final String DD_MM_YYYY = "dd-MM-yyyy";                                   // Example: 14-06-2023
    public static final String YYYY_MM_DD_SLASH = "yyyy/MM/dd";                             // Example: 2023/06/14
    public static final String MM_DD_YYYY_DASH = "MM-dd-yyyy";                              // Example: 06-14-2023
    public static final String DD_MM_YYYY_SLASH = "dd/MM/yyyy";                             // Example: 14/06/2023
    public static final String MMM_DD_YYYY = "MMM dd, yyyy";                                // Example: Jun 14, 2023
    public static final String DD_MMM_YYYY = "dd MMM yyyy";                                 // Example: 14 Jun 2023
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";                 // Example: 2023-06-14 12:34:56
    public static final String DD_MMM_YY = "dd MMM yy";                                     // Example: 14 Jun 23
    public static final String DD_MMMM_YYYY = "dd MMMM yyyy";                               // Example: 14 June 2023
    public static final String EEE_MMM_DD_YYYY = "EEE, MMM dd, yyyy";                       // Example: Wed, Jun 14, 2023
    public static final String EEEE_MMMM_DD_YYYY = "EEEE, MMMM dd, yyyy";                   // Example: Wednesday, June 14, 2023
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";                       // Example: 2023-06-14 12:34
    public static final String HH_MM_SS = "HH:mm:ss";                                       // Example: 12:34:56
    public static final String HH_MM_SS_SSS = "HH:mm:ss.SSS";                               // Example: 12:34:56.789
    public static final String MMM_YY = "MMM yy";                                           // Example: Jun 23
    public static final String MM_YYYY = "MM yyyy";                                         // Example: 06 2023
    public static final String MMMM_YYYY = "MMMM yyyy";                                     // Example: June 2023
    public static final String MMMM_DD_YYYY_HH_MM_SS = "MMMM dd, yyyy HH:mm:ss";            // Example: June 14, 2023 12:34:56
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy/MM/dd HH:mm:ss.SSS";         // Example: 2023/06/14 18:30:45.123
    public static final String DD_MM_YYYY_HH_MM_A = "dd/MM/yyyy hh:mm a";                   // Example: 14/06/2023 06:30 PM
    public static final String DD_MMM_YYYY_HH_MM_SS_A = "dd MMM yyyy hh:mm:ss a";           // Example: 14 Jun 2023 06:30:45 PM
    public static final String MM_DD_YYYY_HH_MM_SS_SSS = "MM/dd/yyyy HH:mm:ss.SSS";         // Example: 06/14/2023 18:30:45.123
    public static final String MMMM_DD_YYYY_HH_MM_A = "MMMM dd, yyyy hh:mm a";              // Example: June 14, 2023 06:30 PM
    public static final String EEE_DD_MMM_YYYY_HH_MM_SS_A = "EEE, dd MMM yyyy hh:mm:ss a";  // Example: Fri, 14 Jun 2023 06:30:45 PM
    public static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";                 // Example: 14/06/2023 18:30:45
    public static final String DD_MM_YYYY_HH_MM_SS_DASH = "dd-MM-yyyy HH:mm:ss";                 // Example: 14-06-2023 18:30:45
    public static final String HH_MM_A = "hh:mm a";                                         // Example: 06:30 PM
    public static final String HH_MM_SS_A = "hh:mm:ss a";                                   // Example: 06:30:45 PM
    public static final String MMM_yyyy = "MMM-yyyy";
    public static final String MMM_DD_YYYY_HH_MM_A = "MMM dd, yyyy hh:mm a";                // Example: Jun 14, 2023 06:30 PM

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
    public static final String dateFormate = "MM/dd/yyyy";
    public static final SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);

    public static String dateToString(Date date) {
        if (date != null) {
            return sdf.format(date);
        }
        return null;
    }

    public static Date stringToDate(String date) {
        Date parsedDate = null;
        if (date != null && !date.equals("")) {
            try {
                parsedDate = sdf.parse(date);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Exception : ", e);
            }
        }
        return parsedDate;
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String formatDate(LocalDate localDate, String format) {
        // Create a DateTimeFormatter with the specified format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        // Format the LocalDate and return as a String
        return localDate.format(formatter);
    }

    public static Date convertStringToDate(String dateString, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertDateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date convertTimestampToDate(long timestamp) {
        return new Date(timestamp * 1000); // Convert seconds to milliseconds
    }

    public static long convertDateToTimestamp(Date date) {
        return date.getTime() / 1000; // Convert milliseconds to seconds
    }

    public static Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static Date convertZonedDateTimeToDate(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }

    public static ZonedDateTime convertDateToZonedDateTime(Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static boolean isBetween(Date date, int days) {
        long currentTime = System.currentTimeMillis();
        long startDateMillis = currentTime - (days * 24 * 60 * 60 * 1000); // Calculate start date based on days

        Date startDate = new Date(startDateMillis);
        Date endDate = new Date(currentTime);

        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }

    /**
     * String contains only numbers boolean.
     *
     * @param text the text
     * @return the boolean
     * @deprecated this method is aleady available in StringUtils class.
     */
    @Deprecated
    public static Boolean stringContainsOnlyNumbers(String text) {
        return text.matches("[0-9]+");
    }

    /**
     * Days between two dates long.
     *
     * @param firstDate  the first date
     * @param secondDate the second date
     * @return the long
     */
    public static Long daysBetweenTwoDates(Date firstDate, Date secondDate) {
        long difference = (firstDate.getTime() - secondDate.getTime()) / 86400000;
        return Math.abs(difference);
    }

    /**
     * Days between two sql dates long.
     *
     * @param firstDate  the first date
     * @param secondDate the second date
     * @return the long
     */
    public static Long daysBetweenTwoSqlDates(java.sql.Date firstDate, java.sql.Date secondDate) {
        long difference = (firstDate.getTime() - secondDate.getTime()) / 86400000;
        return Math.abs(difference);
    }

    /**
     * Parse date date.
     *
     * @param date the date
     * @return the date
     * @throws Exception the exception
     * @deprecated code is already available in DateUtils
     */
    @Deprecated
    public static Date parseDate(String date) throws Exception {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }

    /**
     * Dash percent of value double.
     *
     * @param percentage the percentage
     * @param value      the value
     * @return the double
     * @throws Exception the exception
     */
    public static Double dashPercentOfValue(Double percentage, Double value) throws Exception {
        if (percentage != null && value != null) {
            return (value / 100) * percentage;
        }
        return 0.0;
    }

    /**
     * Change date to string string.
     *
     * @param date the date
     * @return the string
     * @deprecated Code is repeated its available in DateUtils
     */
    @Deprecated
    public static String changeDateToString(Date date) {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        if (date != null) {
            return sdf.format(date);
        }
        return null;
    }

    /**
     * Gets current date.
     *
     * @param date the date
     * @return the current date
     */
    public static Date getCurrentDate(Date date) {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        if (date != null) {
            return changeDateStringToDate(sdf.format(date));
        }
        return null;
    }

    /**
     * Change date string to date.
     *
     * @param date the date
     * @return the date
     */
    public static Date changeDateStringToDate(String date) {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date parsedDate = null;
        if (date != null) {
            try {
                parsedDate = sdf.parse(date);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Exception:" + e);
            }
        }
        return parsedDate;
    }

    /**
     * Change date string to sql date java . sql . date.
     *
     * @param date the date
     * @return the java . sql . date
     * @deprecated Code is repeated its available in DateUtils
     */
    @Deprecated
    public static java.sql.Date changeDateStringToSqlDate(String date) {
        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return new java.sql.Date(sdf.parse(date).getTime());

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:" + e);
        }
        return null;
    }

    /**
     * Gets current timestamp.
     *
     * @return the current timestamp
     */
    public static Timestamp getCurrentTimestamp() {
        try {
            return new Timestamp(Calendar.getInstance().getTimeInMillis());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:" + e);
        }
        return null;
    }

    /**
     * Gets current times.
     *
     * @return the String
     */
    public static String getCurrentTime() {
        Timestamp currentTimestamp = getCurrentTimestamp();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(currentTimestamp);
    }

    /**
     * Gets current timestamp.
     *
     * @param minutes the minutes
     * @return the current timestamp
     */
    public static Timestamp getCurrentTimestamp(String minutes) {
        Timestamp currentTimestamp = null;
        try {
            currentTimestamp = new Timestamp(Calendar.getInstance().getTime().getTime() + (long) Integer.parseInt(minutes) * 60 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:" + e);
        }
        return currentTimestamp;
    }

    /**
     * Change timestamp to string string.
     *
     * @param timestamp the timestamp
     * @return the string
     * @deprecated implementation is already available in DateUtils
     */
    @Deprecated
    public static String changeTimestampToString(Timestamp timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        return sdf.format(timestamp);
    }

    /**
     * Change timestamp string to timestamp timestamp.
     *
     * @param timestamp the timestamp
     * @return the timestamp
     * @deprecated implementation is already available in DateUtils
     */
    @Deprecated
    public static Timestamp changeTimestampStringToTimestamp(String timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        try {
            return new Timestamp(dateFormat.parse(timestamp).getTime());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception:" + e);
        }
        return null;

    }


    /**
     * Calculate age double.
     *
     * @param birthDate   the birth date
     * @param currentDate the current date
     * @return the double
     */
    public static Double calculateAge(Date birthDate, Date currentDate) {
        int years = 0;
        int months = 0;
        int days = 0;
        //create calendar object for birth day
        Calendar birthDay = Calendar.getInstance();
        birthDay.setTimeInMillis(birthDate.getTime());
        //create calendar object for current day
        /*long currentTime = System.currentTimeMillis();*/
        Calendar now = Calendar.getInstance();
        /*now.setTimeInMillis(currentDate.get);*/
        now.setTime(currentDate);
        //Get difference between years
        years = now.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        int currMonth = now.get(Calendar.MONTH) + 1;
        int birthMonth = birthDay.get(Calendar.MONTH) + 1;
        //Get difference between months
        months = currMonth - birthMonth;
        //if month difference is in negative then reduce years by one and calculate the number of months.
        if (months < 0) {
            years--;
            months = 12 - birthMonth + currMonth;
            if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
                months--;
            }
        } else if (months == 0 && now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            years--;
            months = 11;
        }
        //Calculate the days
        if (now.get(Calendar.DATE) > birthDay.get(Calendar.DATE)) {
            days = now.get(Calendar.DATE) - birthDay.get(Calendar.DATE);
        } else if (now.get(Calendar.DATE) < birthDay.get(Calendar.DATE)) {
            int today = now.get(Calendar.DAY_OF_MONTH);
            now.add(Calendar.MONTH, -1);
            days = now.getActualMaximum(Calendar.DAY_OF_MONTH) - birthDay.get(Calendar.DAY_OF_MONTH) + today;
        } else {
            days = 0;
            if (months == 12) {
                years++;
                months = 0;
            }
        }
        //Create new Age object
        /*return new Age(days, months, years);*/
        return Double.parseDouble(years + "");
    }

    /**
     * Add days java . sql . date.
     *
     * @param date the date
     * @param days the days
     * @return the java . sql . date
     */
    public static synchronized java.sql.Date addDays(java.sql.Date date, Long days) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        sqlDate.setTime(sqlDate.getTime() + days * 1000 * 60 * 60 * 24);
        return sqlDate;

    }

    /**
     * Check between boolean.
     *
     * @param dateToCheck the date to check
     * @param startDate   the start date
     * @param endDate     the end date
     * @return the boolean
     */
    public static boolean checkBetween(Date dateToCheck, Date startDate, Date endDate) {

        return dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <= 0;
    }

    /**
     * Gets current timestamp with extended seconds.
     *
     * @return the current timestamp with extended seconds
     */
    public static Timestamp getCurrentTimestampWithExtendedSeconds() {
        int sec = 90;
        long retryDate = System.currentTimeMillis();
        Timestamp original = new Timestamp(retryDate);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(original.getTime());
        cal.add(Calendar.SECOND, sec);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * Gets difference between date.
     *
     * @param firstLoginTime    the first login time
     * @param currentServerTime the current server time
     * @return the difference between date
     */
    public static Integer getDifferenceBetweenDate(Timestamp firstLoginTime, Timestamp currentServerTime) {
        long milliseconds = currentServerTime.getTime() - firstLoginTime.getTime();
        int seconds = (int) milliseconds / 1000;
        return seconds;
    }

    /**
     * Gets year from timestamp.
     *
     * @param timestamp the timestamp
     * @return the year from timestamp
     */
    public static String getYearFromTimestamp(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        int year1 = calendar.get(Calendar.YEAR);
        String year = String.valueOf(year1);
        return year;
    }

    /**
     * Convert string date to timestamp timestamp.
     *
     * @param date the date
     * @return the timestamp
     * @throws ParseException the parse exception
     */
    public static Timestamp convertStringDateToTimestamp(String date) throws ParseException {
        SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = datetime.parse(date);
        return new Timestamp(parse.getTime());
    }

    /**
     * Double to string string.
     *
     * @param d the d
     * @return the string
     */
    public static String doubleToString(Double d) {
        if (d == null) {
            return null;
        }
        if (d.isNaN() || d.isInfinite()) {
            return d.toString();
        }

        // Pre Java 8, a value of 0 would yield "0.0" below
        if (d.doubleValue() == 0) {
            return "0";
        }
        return new BigDecimal(d.toString()).stripTrailingZeros().toPlainString();
    }


    /**
     * Date to string.
     * @deprecated repeated code
     * @param date the date
     * @return the string
     */


    /*
     * String to date.
     * @deprecated no need to repeat the
     * @param date the date
     * @return the date
     */


    /**
     * String to double double.
     *
     * @param doubleValue the double value
     * @return the double
     * @deprecated available in String configs
     */
    @Deprecated
    public static Double stringToDouble(String doubleValue) {
        if (StringUtils.isNumeric(doubleValue)) {
            return Double.parseDouble(doubleValue);
        }
        return 0.0;
    }

    /**
     * Is double boolean.
     *
     * @param str the str
     * @return the boolean
     * @deprecated we can have this check from StringUtils class
     */
    @Deprecated
    static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            logger.error("Exception : ", e);
            return false;
        }
    }

    /**
     * String to date.
     *
     * @param date   the date
     * @param format the format
     * @return the date
     */
    public static Date stringToDate(String date, String format) {
        if (!StringUtils.isEmpty(date)) {
            try {
                return new SimpleDateFormat(format).parse(date);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Exception : ", e);
            }
        }
        return null;
    }

    /**
     * Date to string.
     *
     * @param date   the date
     * @param format the format
     * @return the string
     */
    public static String dateToString(Date date, String format) {
        if (date != null) {
            return new SimpleDateFormat(format).format(date);
        }
        return null;
    }

    /**
     * Converts a LocalDate to a Date representing the start of the day (00:00:00).
     *
     * @param localDate the LocalDate to be converted
     * @return a Date representing the start of the given LocalDate, or null if the input is null
     */
    public static Date convertToStartOfDay(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Converts a LocalDate to a Date representing the end of the day (23:59:59).
     *
     * @param localDate the LocalDate to be converted
     * @return a Date representing the end of the given LocalDate, or null if the input is null
     */
    public static Date convertToEndOfDay(LocalDate localDate) {
        return localDate == null ? null : Date.from(localDate.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Converts a Date to a Date representing the start of the day (00:00:00).
     *
     * @param date the Date to be converted
     * @return a Date representing the start of the given Date, or null if the input is null
     * @since 17-01-2025
     */
    public static Date convertToStartOfDayFromDate(Date date) {
        if (date == null) {
            return null;
        }
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Converts a Date to a Date representing the end of the day (23:59:59).
     *
     * @param date the Date to be converted
     * @return a Date representing the end of the given Date, or null if the input is null
     * @since 17-01-2025
     */
    public static Date convertToEndOfDayFromDate(Date date) {
        if (date == null) {
            return null;
        }

        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return Date.from(localDate.atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * Calculates the expiry date by adding 3 months to the current date.
     *
     * @return the expiry date as a {@link Date} object, which is 3 months from the current date.
     * @since 21-10-2024
     */
    public static Date getPasswordExpiryDate(int expInMonth) {
        // Get the current date
        Date currentDate = new Date();
        // Create a calendar instance and set it to the current date
        Calendar calendar = Calendar.getInstance();
        Calendar.getInstance().setTime(currentDate);
        // Add 3 months to the current date
        calendar.add(Calendar.MONTH, expInMonth);
        // Return the expiry date
        return calendar.getTime();
    }
}
