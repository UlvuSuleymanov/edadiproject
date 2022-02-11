package az.edadi.back.utility;

public class SlugUtil {

    public static String createSlug(String title, Long id) {
        title=title.replaceAll("[?&%=!.,')(]","");
        title = title.trim().replaceAll(" +", "-");
        return title+"-"+id;
    }

    public static Long getId(String slug){
        String crumbs[]=slug.split("-");
        String id = crumbs[crumbs.length-1];
        return Long.valueOf(id);
    }
}
