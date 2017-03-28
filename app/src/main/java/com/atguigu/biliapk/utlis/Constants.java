package com.atguigu.biliapk.utlis;

/**
 * Created by 熊猛 on 2017/3/21.
 */

public class Constants {
    //直播
    public static String LIVE_URL = "http://live.bilibili.com/AppNewIndex/common?_device=android&appkey=\n" +
            "1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&scale=\n" +
            "hdpi&ts=1490013188000&sign=92541a11ed62841120e786e637b9db3b\n";

    public static String LIVE_BANNER_IMAGE  = "http://i0.hdslb.com/bfs/live/2807719e244e45714a3e08548b1c889815eaa1f6.png";

    //推荐
    public static String RECOMMEND_URL ="http://app.bilibili.com/x/" +
            "feed/index?appkey=1d8b6e7d45233436&build=501000&idx=1490013261&mobi_app=" +
            "android&network=wifi&platform=android&pull=true&style=2&ts=1490015599000&sign=" +
            "af4edc66aef7e443c98c28de2b660aa4";
    //追番
    public static String THEM_URL ="http://bangumi.bilibili.com/api/app_index_page_v4?build=3940&device=phone&mobi_app=iphone&platform=ios";
    //banner
    public static String BANNER_URL ="http://bangumi.bilibili.com/api/bangumi_recommend?access_key=f5bd4e793b82fba5aaf5b91fb549910a&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3470&cursor=0&device=phone&mobi_app=iphone&pagesize=10&platform=ios&sign=56329a5709c401d4d7c0237f64f7943f&ts=1469613558";
    //分区动画
    public static String ANIM_URL = "http://app.bilibili.com/x/v2/show/region?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&platform=android&ts=1490014674000&sign=93edb7634f38498a60e5c3ad0b8b0974";
    //发现tag
    public static String TAG_URL = "http://app.bilibili.com/x/v2/search/hot?appkey=1d8b6e7d45233436" +
            "&build=501000&limit=50&mobi_app=android&platform=android&ts=1490014710000&sign=e5ddf" +
            "94fa9a0d6876cb85756c37c4adc";
    //话题中心跳转接口
    public static String TOPIC_URL = "http://api.bilibili.com/topic/getlist?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&page=1&pageSize=20&platform=android&ts=1490015740000&sign=be68382cdc99c168ef87f2fa423dd280";
    //原创排行榜
    public static String ORIGINAL_URL = "http://app.bilibili.com/x/v2/rank?appkey=1d8b6e7d45233436&build=501000&mobi_app=android&order=all&platform=android&pn=1&ps=20&ts=1490015891000&sign=8e7dfaa1c2fb779943430b46e734b422";
    //搜索数据
    public static String SEARCH_URL = "http://app.bilibili.com/x/v2/search?appkey=1d8b6e7d45233436&build=501000&duration=0&keyword=%E6%9E%81%E4%B9%90%E5%87%80%E5%9C%9F&mobi_app=android&platform=android&pn=1&ps=20";
}
