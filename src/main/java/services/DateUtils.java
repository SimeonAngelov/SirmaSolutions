package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static services.FileParser.YYYY_MM_DD;


public class DateUtils {


    public static long calculateDatesOverlap(String startDate1String, String endDate1String,
                                              String startDate2String, String endDate2String) throws ParseException {
        SimpleDateFormat myFormat = new SimpleDateFormat(YYYY_MM_DD);

        Date startDate1 = myFormat.parse(startDate1String);
        Date endDate1 = myFormat.parse(endDate1String);
        Date startDate2 = myFormat.parse(startDate2String);
        Date endDate2 = myFormat.parse(endDate2String);
        long end1 = endDate1.getTime();
        long end2 = endDate2.getTime();

        long overlap = Math.max(-1, Math.min(end1, end2) - Math.max(startDate1.getTime(), startDate2.getTime())) + 1;

        return TimeUnit.DAYS.convert(overlap, TimeUnit.MILLISECONDS) ;

    }
}
