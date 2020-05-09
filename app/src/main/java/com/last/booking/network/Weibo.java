package com.last.booking.network;

public class Weibo {

    public final static String  APP_KEY = "2531526103";
    public final static String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";
    public static final String SCOPE =
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";

    public final static String APP_SECRET = "ea9d1661ae78043afffbbc9c370d244a";


    public static class API{
        public final static String SHOW = "https://api.weibo.com/2/users/show.json?";
        public final static String AUTHORIZE = "https://api.weibo.com/oauth2/authorize?" + "client_id=" + APP_KEY +
                "&redirect_uri=" +  REDIRECT_URL + "&display=mobile";
        public final static String ACCESS_TOKEN = "https://api.weibo.com/oauth2/access_token?" + "client_id=" + APP_KEY +
                "&client_secret=" + APP_SECRET + "&grant_type=authorization_code" + "&redirect_uri" + REDIRECT_URL;
    }
}
