package com.last.booking.network;

public class API {

    //private static String baseApi = "localhost:7777/sys";
    private static String baseApi = "http://120.26.184.232:7777/sys";
    public static class User
    {
        public static String login = baseApi + "/user/login?";
        public static String loginByWeibo = baseApi + "/user/loginByWeibo?";
        public static String reset = baseApi + "/user/reset?";
        public static String register = baseApi + "/user/register?";
        public static String userInfo = baseApi + "/user/userInfo?";
    }

    public static class Office
    {
        public static String getCity = baseApi + "/office/getCity";
        public static String getCityOffices = baseApi + "/office/getCityOffices?";

    }

    public static class Business
    {
        public static String business = baseApi + "/business/business?";
        public static String getAllBusiness = baseApi + "/business/getAllBusiness";
    }

    public static class Mission
    {
        public static String getUsefulInfo = baseApi + "/mission/getUsefulInfo?";
        public static String add = baseApi + "/mission/add?";
        public static String getUserMission = baseApi + "/mission/getUserMission?";
        public static String cancel = baseApi + "/mission/cancel?";
        public static String getNotice = baseApi + "/mission/getNotice?";
    }

    public static class Sms
    {
        public static String code = baseApi + "/sms/code?";
    }

    public static class File
    {
        public static String upload = baseApi + "/file/upload?";
    }
}
