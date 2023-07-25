package az.edadi.back.utility;

import java.util.Date;
import java.util.Map;

public class DateUtil {

    @Deprecated
    public static Map<Long, String> duratisons = Map.of(
            31536000L, "year",
            2592000L, "month",
            604800L, "week",
            86400L, "day",
            3600L, "hour",
            60L, "minute",
            1L, "second"
    );

    private static String timeNames[] = {"year", "month", "week", "day", "hour", "minute", "second"};
    private static Long durations[] = {31556926L, 2629743L, 604800L, 86400L, 3600L, 60L, 1L,};

    public static String getHowLongAgoString(Date date) {
        long seconds = (new Date().getTime() - date.getTime()) / 1000;
        String timeName = "";
        int times = 0;
        for (int i = 0; i < durations.length; i++) {
            times = (int) (seconds / durations[i]);
            if (times > 0) {
                timeName = timeNames[i];
                break;
            }
        }
        if (times > 1)
            timeName = timeName + "s";

        String finalDateString = String.valueOf(times) + " " + Translator.getTranslation(timeName) + " " + Translator.getTranslation("ago");
        return finalDateString;
    }
}
