package az.edadi.back.utility;

import java.util.regex.Pattern;

public class SlugUtil {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-[ÜüÖöŞşÇçIıƏəĞğ ]]");

    public static String createSlug(String title, Long id) {
        String slug = NONLATIN.matcher(title).replaceAll("");
        slug = slug.trim().replaceAll(" +", "-");
        slug = slug + "-" + id;
        return slug;
    }

    public static Long getId(String slug) {
        String crumbs[] = slug.split("-");
        String id = crumbs[crumbs.length - 1];
        return Long.valueOf(id);
    }
}
