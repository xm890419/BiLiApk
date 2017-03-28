package com.atguigu.biliapk.utlis;

/**
 * 功能: xxxx
 */

public interface ResponseListener {
    /**
     * @param progress 已经下载或上传字节数
     * @param total    总字节数
     * @param done     是否完成
     */
    void onProgress(long progress, long total, boolean done);

    void onResponse();

    void onFailure(String error);
}
