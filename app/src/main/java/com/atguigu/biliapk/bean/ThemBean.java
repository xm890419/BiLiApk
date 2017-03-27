package com.atguigu.biliapk.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 熊猛 on 2017/3/22.
 */

public class ThemBean {

    /**
     * code : 0
     * message : success
     * result : {"ad":{"body":[],"head":[{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/102d534a0d843d16a476d67d5cbfc01d32b062a6.jpg","link":"http://bangumi.bilibili.com/anime/5795","pub_time":"2017-03-21 22:30:00","title":"ACCA"},{"desc":".","id":0,"img":"http://i0.hdslb.com/bfs/bangumi/150986f04c4135793981c60e3c212655d9bed321.jpg","link":"http://bangumi.bilibili.com/anime/5809","pub_time":"2017-03-22 03:30:00","title":"黑白来看守所"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/87ce44fc1c2897c4b849f6ca1a0323877c6bbfb7.jpg","link":"http://bangumi.bilibili.com/anime/5796","pub_time":"2017-03-22 01:35:00","title":"兽娘动物园"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/1ab9b2aaf7c4c6c9b0d62dfb6330353223cb4e66.jpg","link":"http://bangumi.bilibili.com/anime/5797","pub_time":"2017-03-22 01:00:00","title":"hand shakers"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/32c2d7f952cede66b2c7083924b1c6130fd26530.jpg","link":"http://bangumi.bilibili.com/anime/1299","pub_time":"2017-03-20 15:17:00","title":"彩云国物语"}]},"previous":{"list":[{"cover":"http://i0.hdslb.com/bfs/bangumi/2673ac643b48eb5bda64c960a2ca850fbebb839d.jpg","favourites":"1673902","is_finish":1,"last_time":1482262210,"newest_ep_index":"11","pub_time":1475607600,"season_id":5550,"season_status":2,"title":"夏目友人帐 伍","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b75c55d209d156c8631f5ceb21e5c52c834dbb60.jpg","favourites":"1331913","is_finish":0,"last_time":1483196409,"newest_ep_index":"1","pub_time":1483196400,"season_id":5747,"season_status":2,"title":"Fate/Grand Order \u2010First Order\u2010","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b3633d2e5cafa0d048f4beef63618c92cfac4c4c.jpg","favourites":"786253","is_finish":1,"last_time":1482465609,"newest_ep_index":"12","pub_time":1475812800,"season_id":5534,"season_status":2,"title":"我太受欢迎了该怎么办","watching_count":0}],"season":4,"year":2016},"serializing":[{"cover":"http://i0.hdslb.com/bfs/bangumi/4d06e660b8da9cb5335552f4ebde89bbcb2e9d4f.jpg","favourites":"1403165","is_finish":0,"is_started":1,"last_time":1490178302,"newest_ep_index":"49","pub_time":1459872000,"season_id":3462,"season_status":2,"title":"双星之阴阳师","watching_count":4949},{"cover":"http://i0.hdslb.com/bfs/bangumi/0e6bce5d018796dda7782aa5c97bfdd14691348a.jpg","favourites":"149659","is_finish":0,"is_started":1,"last_time":1490161452,"newest_ep_index":"蒯越篇","pub_time":1412006400,"season_id":2647,"season_status":2,"title":"口水三国","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/a910b6d56c06a1c4525ed1df30464fcf1dae0bf5.jpg","favourites":"244481","is_finish":0,"is_started":1,"last_time":1490155322,"newest_ep_index":"16","pub_time":1481083200,"season_id":5748,"season_status":2,"title":"画江湖之杯莫停","watching_count":1430},{"cover":"http://i0.hdslb.com/bfs/bangumi/0ab129325044ff4acbf808a3e38b71a1e0ea5b57.jpg","favourites":"479267","is_finish":0,"is_started":1,"last_time":1490124603,"newest_ep_index":"25","pub_time":1483471800,"season_id":5809,"season_status":2,"title":"黑白来看守所 第二季","watching_count":2485},{"cover":"http://i0.hdslb.com/bfs/bangumi/2d91d23d676d7f739ad531d31ef7205d742fbfc8.jpg","favourites":"117656","is_finish":0,"is_started":1,"last_time":1490117700,"newest_ep_index":"11","pub_time":1484069700,"season_id":5796,"season_status":2,"title":"兽娘动物园","watching_count":1465},{"cover":"http://i0.hdslb.com/bfs/bangumi/948878ec7fc44c3e824d6204b4de91abfb0dce17.jpg","favourites":"790243","is_finish":0,"is_started":1,"last_time":1490115600,"newest_ep_index":"11","pub_time":1484067600,"season_id":5797,"season_status":2,"title":"Hand Shakers","watching_count":1300}]}
     */

    private int code;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * ad : {"body":[],"head":[{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/102d534a0d843d16a476d67d5cbfc01d32b062a6.jpg","link":"http://bangumi.bilibili.com/anime/5795","pub_time":"2017-03-21 22:30:00","title":"ACCA","desc":"."},{"desc":".","id":0,"img":"http://i0.hdslb.com/bfs/bangumi/150986f04c4135793981c60e3c212655d9bed321.jpg","link":"http://bangumi.bilibili.com/anime/5809","pub_time":"2017-03-22 03:30:00","title":"黑白来看守所"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/87ce44fc1c2897c4b849f6ca1a0323877c6bbfb7.jpg","link":"http://bangumi.bilibili.com/anime/5796","pub_time":"2017-03-22 01:35:00","title":"兽娘动物园"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/1ab9b2aaf7c4c6c9b0d62dfb6330353223cb4e66.jpg","link":"http://bangumi.bilibili.com/anime/5797","pub_time":"2017-03-22 01:00:00","title":"hand shakers"},{"id":0,"img":"http://i0.hdslb.com/bfs/bangumi/32c2d7f952cede66b2c7083924b1c6130fd26530.jpg","link":"http://bangumi.bilibili.com/anime/1299","pub_time":"2017-03-20 15:17:00","title":"彩云国物语"}]}
         * previous : {"list":[{"cover":"http://i0.hdslb.com/bfs/bangumi/2673ac643b48eb5bda64c960a2ca850fbebb839d.jpg","favourites":"1673902","is_finish":1,"last_time":1482262210,"newest_ep_index":"11","pub_time":1475607600,"season_id":5550,"season_status":2,"title":"夏目友人帐 伍","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b75c55d209d156c8631f5ceb21e5c52c834dbb60.jpg","favourites":"1331913","is_finish":0,"last_time":1483196409,"newest_ep_index":"1","pub_time":1483196400,"season_id":5747,"season_status":2,"title":"Fate/Grand Order \u2010First Order\u2010","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b3633d2e5cafa0d048f4beef63618c92cfac4c4c.jpg","favourites":"786253","is_finish":1,"last_time":1482465609,"newest_ep_index":"12","pub_time":1475812800,"season_id":5534,"season_status":2,"title":"我太受欢迎了该怎么办","watching_count":0}],"season":4,"year":2016}
         * serializing : [{"cover":"http://i0.hdslb.com/bfs/bangumi/4d06e660b8da9cb5335552f4ebde89bbcb2e9d4f.jpg","favourites":"1403165","is_finish":0,"is_started":1,"last_time":1490178302,"newest_ep_index":"49","pub_time":1459872000,"season_id":3462,"season_status":2,"title":"双星之阴阳师","watching_count":4949},{"cover":"http://i0.hdslb.com/bfs/bangumi/0e6bce5d018796dda7782aa5c97bfdd14691348a.jpg","favourites":"149659","is_finish":0,"is_started":1,"last_time":1490161452,"newest_ep_index":"蒯越篇","pub_time":1412006400,"season_id":2647,"season_status":2,"title":"口水三国","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/a910b6d56c06a1c4525ed1df30464fcf1dae0bf5.jpg","favourites":"244481","is_finish":0,"is_started":1,"last_time":1490155322,"newest_ep_index":"16","pub_time":1481083200,"season_id":5748,"season_status":2,"title":"画江湖之杯莫停","watching_count":1430},{"cover":"http://i0.hdslb.com/bfs/bangumi/0ab129325044ff4acbf808a3e38b71a1e0ea5b57.jpg","favourites":"479267","is_finish":0,"is_started":1,"last_time":1490124603,"newest_ep_index":"25","pub_time":1483471800,"season_id":5809,"season_status":2,"title":"黑白来看守所 第二季","watching_count":2485},{"cover":"http://i0.hdslb.com/bfs/bangumi/2d91d23d676d7f739ad531d31ef7205d742fbfc8.jpg","favourites":"117656","is_finish":0,"is_started":1,"last_time":1490117700,"newest_ep_index":"11","pub_time":1484069700,"season_id":5796,"season_status":2,"title":"兽娘动物园","watching_count":1465},{"cover":"http://i0.hdslb.com/bfs/bangumi/948878ec7fc44c3e824d6204b4de91abfb0dce17.jpg","favourites":"790243","is_finish":0,"is_started":1,"last_time":1490115600,"newest_ep_index":"11","pub_time":1484067600,"season_id":5797,"season_status":2,"title":"Hand Shakers","watching_count":1300}]
         */

        private AdBean ad;
        private PreviousBean previous;
        private List<SerializingBean> serializing;

        public AdBean getAd() {
            return ad;
        }

        public void setAd(AdBean ad) {
            this.ad = ad;
        }

        public PreviousBean getPrevious() {
            return previous;
        }

        public void setPrevious(PreviousBean previous) {
            this.previous = previous;
        }

        public List<SerializingBean> getSerializing() {
            return serializing;
        }

        public void setSerializing(List<SerializingBean> serializing) {
            this.serializing = serializing;
        }

        public static class AdBean {
            private List<?> body;
            private List<HeadBean> head;

            public List<?> getBody() {
                return body;
            }

            public void setBody(List<?> body) {
                this.body = body;
            }

            public List<HeadBean> getHead() {
                return head;
            }

            public void setHead(List<HeadBean> head) {
                this.head = head;
            }

            public static class HeadBean {
                /**
                 * id : 0
                 * img : http://i0.hdslb.com/bfs/bangumi/102d534a0d843d16a476d67d5cbfc01d32b062a6.jpg
                 * link : http://bangumi.bilibili.com/anime/5795
                 * pub_time : 2017-03-21 22:30:00
                 * title : ACCA
                 * desc : .
                 */

                private int id;
                private String img;
                private String link;
                private String pub_time;
                private String title;
                private String desc;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getPub_time() {
                    return pub_time;
                }

                public void setPub_time(String pub_time) {
                    this.pub_time = pub_time;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }
            }
        }

        public static class PreviousBean {
            /**
             * list : [{"cover":"http://i0.hdslb.com/bfs/bangumi/2673ac643b48eb5bda64c960a2ca850fbebb839d.jpg","favourites":"1673902","is_finish":1,"last_time":1482262210,"newest_ep_index":"11","pub_time":1475607600,"season_id":5550,"season_status":2,"title":"夏目友人帐 伍","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b75c55d209d156c8631f5ceb21e5c52c834dbb60.jpg","favourites":"1331913","is_finish":0,"last_time":1483196409,"newest_ep_index":"1","pub_time":1483196400,"season_id":5747,"season_status":2,"title":"Fate/Grand Order \u2010First Order\u2010","watching_count":0},{"cover":"http://i0.hdslb.com/bfs/bangumi/b3633d2e5cafa0d048f4beef63618c92cfac4c4c.jpg","favourites":"786253","is_finish":1,"last_time":1482465609,"newest_ep_index":"12","pub_time":1475812800,"season_id":5534,"season_status":2,"title":"我太受欢迎了该怎么办","watching_count":0}]
             * season : 4
             * year : 2016
             */

            private int season;
            private int year;
            private List<ListBean> list;

            public int getSeason() {
                return season;
            }

            public void setSeason(int season) {
                this.season = season;
            }

            public int getYear() {
                return year;
            }

            public void setYear(int year) {
                this.year = year;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * cover : http://i0.hdslb.com/bfs/bangumi/2673ac643b48eb5bda64c960a2ca850fbebb839d.jpg
                 * favourites : 1673902
                 * is_finish : 1
                 * last_time : 1482262210
                 * newest_ep_index : 11
                 * pub_time : 1475607600
                 * season_id : 5550
                 * season_status : 2
                 * title : 夏目友人帐 伍
                 * watching_count : 0
                 */

                private String cover;
                private String favourites;
                private int is_finish;
                private int last_time;
                private String newest_ep_index;
                private int pub_time;
                private int season_id;
                private int season_status;
                private String title;
                private int watching_count;

                public String getCover() {
                    return cover;
                }

                public void setCover(String cover) {
                    this.cover = cover;
                }

                public String getFavourites() {
                    return favourites;
                }

                public void setFavourites(String favourites) {
                    this.favourites = favourites;
                }

                public int getIs_finish() {
                    return is_finish;
                }

                public void setIs_finish(int is_finish) {
                    this.is_finish = is_finish;
                }

                public int getLast_time() {
                    return last_time;
                }

                public void setLast_time(int last_time) {
                    this.last_time = last_time;
                }

                public String getNewest_ep_index() {
                    return newest_ep_index;
                }

                public void setNewest_ep_index(String newest_ep_index) {
                    this.newest_ep_index = newest_ep_index;
                }

                public int getPub_time() {
                    return pub_time;
                }

                public void setPub_time(int pub_time) {
                    this.pub_time = pub_time;
                }

                public int getSeason_id() {
                    return season_id;
                }

                public void setSeason_id(int season_id) {
                    this.season_id = season_id;
                }

                public int getSeason_status() {
                    return season_status;
                }

                public void setSeason_status(int season_status) {
                    this.season_status = season_status;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getWatching_count() {
                    return watching_count;
                }

                public void setWatching_count(int watching_count) {
                    this.watching_count = watching_count;
                }
            }
        }

        public static class SerializingBean implements Serializable{
            /**
             * cover : http://i0.hdslb.com/bfs/bangumi/4d06e660b8da9cb5335552f4ebde89bbcb2e9d4f.jpg
             * favourites : 1403165
             * is_finish : 0
             * is_started : 1
             * last_time : 1490178302
             * newest_ep_index : 49
             * pub_time : 1459872000
             * season_id : 3462
             * season_status : 2
             * title : 双星之阴阳师
             * watching_count : 4949
             */

            private String cover;
            private String favourites;
            private int is_finish;
            private int is_started;
            private int last_time;
            private String newest_ep_index;
            private int pub_time;
            private int season_id;
            private int season_status;
            private String title;
            private int watching_count;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public String getFavourites() {
                return favourites;
            }

            public void setFavourites(String favourites) {
                this.favourites = favourites;
            }

            public int getIs_finish() {
                return is_finish;
            }

            public void setIs_finish(int is_finish) {
                this.is_finish = is_finish;
            }

            public int getIs_started() {
                return is_started;
            }

            public void setIs_started(int is_started) {
                this.is_started = is_started;
            }

            public int getLast_time() {
                return last_time;
            }

            public void setLast_time(int last_time) {
                this.last_time = last_time;
            }

            public String getNewest_ep_index() {
                return newest_ep_index;
            }

            public void setNewest_ep_index(String newest_ep_index) {
                this.newest_ep_index = newest_ep_index;
            }

            public int getPub_time() {
                return pub_time;
            }

            public void setPub_time(int pub_time) {
                this.pub_time = pub_time;
            }

            public int getSeason_id() {
                return season_id;
            }

            public void setSeason_id(int season_id) {
                this.season_id = season_id;
            }

            public int getSeason_status() {
                return season_status;
            }

            public void setSeason_status(int season_status) {
                this.season_status = season_status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getWatching_count() {
                return watching_count;
            }

            public void setWatching_count(int watching_count) {
                this.watching_count = watching_count;
            }
        }
    }
}
