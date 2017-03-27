package com.atguigu.biliapk.utlis;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.biliapk.bean.RecommendBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 熊猛 on 2017/2/27.
 */

public class CartStorage {
    public static final String JSON_CART = "jsonCart";
    public static CartStorage instance;
    private final Context mContext;
    //SparseArray替代HashMap
    private SparseArray<RecommendBean.DataBean> sparseArrays;

    private CartStorage(Context context) {
        this.mContext = context;
        sparseArrays = new SparseArray<>();
        //从本地获取数据
        listToSparseArray();
    }

    private void listToSparseArray() {
        List<RecommendBean.DataBean> goodsBeanList = getAllData();
        //循环起来，把数据转存到sparseArray
        for (int i = 0;i<goodsBeanList.size();i++){
            RecommendBean.DataBean goodsBean = goodsBeanList.get(i);
            sparseArrays.put(goodsBean.getTid(),goodsBean);
        }
    }
    //得到所有的数据
    public List<RecommendBean.DataBean> getAllData() {
        return getLocalData();
    }
    //得到本地缓存的数据
    private List<RecommendBean.DataBean> getLocalData() {
        List<RecommendBean.DataBean> goodsBeens = new ArrayList<>();
        //从本地获取数据 从sp中
        String jsonCart = CacheUtils.getString(mContext, JSON_CART);//保持的json数据
        if(!TextUtils.isEmpty(jsonCart)) {
            //把它转化成列表
            goodsBeens = new Gson().fromJson(jsonCart,new TypeToken<List<RecommendBean.DataBean>>(){}.getType());
        }
        //把json数据解析成List数据
        return goodsBeens;
    }


    /**
     * 懒汉式
     * @param context
     * @return
     */
    public static CartStorage getInstance(Context context){
        if(instance == null) {
            synchronized (CartStorage.class){
                if(instance == null) {
                    instance = new CartStorage(context);
                }
            }
        }
        return instance;
    }
    //添加数据
    public void addData(RecommendBean.DataBean goodsBean){
        //1.数据添加到sparseArray
        RecommendBean.DataBean tempGoodsBean = sparseArrays.get(goodsBean.getTid());
        //已经保存过
        if(tempGoodsBean != null) {
            //tempGoodsBean.setNumber(tempGoodsBean.getNumber()+ goodsBean.getNumber());
            tempGoodsBean.setNumber(goodsBean.getNumber());
        }else {
            //没有添加过
            tempGoodsBean = goodsBean;
        }
        //添加到集合中
        sparseArrays.put(tempGoodsBean.getTid(),tempGoodsBean);
        //2.保持到本地
        saveLocal();
    }

    //删除数据
    public void deleteData(RecommendBean.DataBean goodsBean){
        //1.删除数据
        sparseArrays.delete(goodsBean.getTid());
        //2.保持到本地
        saveLocal();
    }

    //修改数据
    public void updateData(RecommendBean.DataBean goodsBean){
        //1.修改数据
        sparseArrays.put(goodsBean.getTid(),goodsBean);
        //2.保持到本地
        saveLocal();
    }

    //保持到本地
    private void saveLocal() {
        //1.把sparseArray转成List
        List<RecommendBean.DataBean> been = sparseArrayToList();
        //2.使用Gson把List转json的String类型数据
        String savejson = new Gson().toJson(been);
        //3.使用CacheUtils缓存数据
        CacheUtils.setString(mContext,JSON_CART,savejson);
    }
    // 把sparseArray转成List
    private List<RecommendBean.DataBean> sparseArrayToList() {
        //列表数据
        List<RecommendBean.DataBean> beanLists = new ArrayList<>();
        for (int i =0;i<sparseArrays.size();i++){
            RecommendBean.DataBean goodsBean = sparseArrays.valueAt(i);
            beanLists.add(goodsBean);
        }
        return beanLists;
    }
    //是否在购物车中存在
    public RecommendBean.DataBean findDete(int product_id) {
        return sparseArrays.get(product_id);
    }
}
