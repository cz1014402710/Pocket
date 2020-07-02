package com.chenz.pocket.utils.glide;

import android.graphics.Bitmap;

/**
 * description: <一句话功能简述>
 *
 * @author Chenz
 * @date 2017/11/3
 */
public interface ImageDownLoadCallBack {
    void onDownLoadSuccess(Bitmap bitmap);

    void onDownLoadFailed();
}
