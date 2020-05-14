package com.last.booking.network;

public class API {

    private static String baseApi = "http://120.26.184.232:7777/sys";
    public static class User
    {
        public static String login = baseApi + "/user/login?";
        public static String loginByWeibo = baseApi + "/user/loginByWeibo?";
        public static String reset = baseApi + "/user/reset?";
        public static String resgister = baseApi + "/user/register?";
    }

    public static class Office
    {
        public static String getCity = baseApi + "/office/getCity";
        public static String getCityOffices = baseApi + "/office/getCityOffices?";

    }

    public static class Business
    {
        public static String business = baseApi + "/business/business?";
    }

    public static class Mission
    {
        public static String getUsefulInfo = baseApi + "/mission/getUsefulInfo?";
        public static String add = baseApi + "/mission/add?";
    }

    public static class Sms
    {
        public static String code = baseApi + "/sms/code?";
    }
}
