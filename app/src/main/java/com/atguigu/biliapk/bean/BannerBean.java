package com.atguigu.biliapk.bean;

import java.util.List;

/**
 * Created by 熊猛 on 2017/3/22.
 */

public class BannerBean {

    /**
     * code : 0
     * message : success
     * result : [{"cover":"http://i0.hdslb.com/bfs/bangumi/790c62dc1d7752620e486480230bc01f4886cd46.jpg","cursor":0,"desc":"第二次公开直播\no(*≧▽≦)ツ┏━┓kimi不要总是搞事\n就你最跳","id":3897,"is_new":1,"link":"http://bangumi.bilibili.com/anime/5061","onDt":"2017-03-22 18:15:00","title":"梦之祭！研究室 21"},{"cover":"http://i0.hdslb.com/bfs/bangumi/31f8adbe9de4ba004b8d97aa173b8d9978c71764.jpg","cursor":0,"desc":"听说那一家子笨蛋又出来了！","id":3862,"is_new":1,"link":"http://bangumi.bilibili.com/anime/92","onDt":"2017-03-21 08:00:00","title":"每度！浦安铁筋家族"},{"cover":"http://i0.hdslb.com/bfs/bangumi/7a3a43959444ed1cf06dcc3b29a0675e22e05009.jpg","cursor":0,"desc":"迷茫的时候也要战斗\n再去看看那些自己曾经付出过努力的地方吧","id":3861,"is_new":1,"link":"http://bangumi.bilibili.com/anime/5787","onDt":"2017-03-20 18:00:00","title":"偶像选举 11"},{"cover":"http://i0.hdslb.com/bfs/bangumi/eae1bfdcf27750933341212d25b82af764fdf67f.jpg","cursor":0,"desc":"请问您今天要来点尾巴肉吗？","id":3859,"link":"http://www.bilibili.com/blackboard/activity-HJBAZ_8sg.html","onDt":"2017-03-19 18:00:00","title":"【AniKey vol.8】这里的龙不得了"},{"cover":"http://i0.hdslb.com/bfs/bangumi/f5932fed37d21a098ea20a387982d694fcbbc241.jpg","cursor":0,"desc":"曾经不愿面对的事物\n却重新变得如此的美丽","id":3823,"link":"http://www.bilibili.com/video/av5753879/","onDt":"2017-03-18 18:00:00","reply":"109","title":"【周末剧场】苍之茧"},{"cover":"http://i0.hdslb.com/bfs/bangumi/50927447dd7e485e092f3da9ee64d18986a47544.jpg","cursor":0,"desc":"嗨嗨嗨醒一醒，四月番都要来了~","id":3852,"link":"http://www.bilibili.com/video/av9218963/","onDt":"2017-03-17 19:14:00","reply":"5171","title":"2017年4月新番介绍"},{"cover":"http://i0.hdslb.com/bfs/bangumi/da7c2f394a52dc7e2f50ffdeb1d912a5b17b5f9a.jpg","cursor":0,"desc":"京阿尼信仰充值！","id":3843,"link":"http://www.bilibili.com/blackboard/activity-Hkwjcpdje.html","onDt":"2017-03-17 18:00:00","title":"【资讯档】2017年第11周"},{"cover":"http://i0.hdslb.com/bfs/bangumi/973e8102edf9a5ec5fb753d33490bb5481daf17f.jpg","cursor":0,"desc":"这一次如果不能成功\n便放弃梦想","id":3824,"link":"http://bangumi.bilibili.com/anime/5798","onDt":"2017-03-16 18:00:00","title":"One Room 10"},{"cover":"http://i0.hdslb.com/bfs/bangumi/e4f06e303884573d71cbe63cf1b6830f452a47ac.jpg","cursor":0,"desc":"老好人小森同学，今天也拒绝不了别人的求助","id":3790,"link":"http://bangumi.bilibili.com/anime/2744","onDt":"2017-03-14 08:00:00","title":"【泡面档】小森同学拒绝不了！"},{"cover":"http://i0.hdslb.com/bfs/bangumi/18a8fa03bf371ff61e1d4f93dd8ed3ccb5e66ede.jpg","cursor":1489312800944,"desc":"总之来吃个安利吧ヽ(✿ﾟ▽ﾟ)ノ\n（或许并不用安利？","id":3784,"link":"http://www.bilibili.com/blackboard/activity-SkN9Q4pqx.html","onDt":"2017-03-12 18:00:00","title":"【CV Collection vol.01】DearGirl~Stories~"}]
     */

    private int code;
    private String message;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * cover : http://i0.hdslb.com/bfs/bangumi/790c62dc1d7752620e486480230bc01f4886cd46.jpg
         * cursor : 0
         * desc : 第二次公开直播
         o(*≧▽≦)ツ┏━┓kimi不要总是搞事
         就你最跳
         * id : 3897
         * is_new : 1
         * link : http://bangumi.bilibili.com/anime/5061
         * onDt : 2017-03-22 18:15:00
         * title : 梦之祭！研究室 21
         * reply : 109
         */

        private String cover;
        private int cursor;
        private String desc;
        private int id;
        private int is_new;
        private String link;
        private String onDt;
        private String title;
        private String reply;

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getCursor() {
            return cursor;
        }

        public void setCursor(int cursor) {
            this.cursor = cursor;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIs_new() {
            return is_new;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getOnDt() {
            return onDt;
        }

        public void setOnDt(String onDt) {
            this.onDt = onDt;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }
    }
}
