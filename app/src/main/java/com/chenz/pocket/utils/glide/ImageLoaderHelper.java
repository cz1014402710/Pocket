package com.chenz.pocket.utils.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.chenz.pocket.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * Created by chenz on 2017/3/30.
 */
public class ImageLoaderHelper {

    /**
     * 图片显示选项-默认
     */
    public final static int DISPLAY_OPTION_DEFAULT = 0;
    /**
     * 图片显示选项-圆角
     */
    public final static int DISPLAY_OPTION_CORNER = 1;
    /**
     * 图片显示选项-圆形
     */
    public final static int DISPLAY_OPTION_CIRCLE = 2;

    private static ImageLoaderHelper instance;

    public static ImageLoaderHelper getInstance() {
        if (instance == null) {
            instance = new ImageLoaderHelper();
        }
        return instance;
    }

    private ImageLoaderHelper() {
    }

    /**
     * 显示不同类型图片
     *
     * @param context
     * @param url
     * @param displayView
     * @param displayOption use{@link ImageLoaderHelper }
     */
    public void display(Context context, Object url, ImageView displayView, int displayOption) {
        if (displayOption == DISPLAY_OPTION_DEFAULT) {
            displayImage(context, url, displayView);
            return;
        }
        if (displayOption == DISPLAY_OPTION_CORNER) {
            displayCornerImage(context, url, displayView, 10);
            return;
        }
        if (displayOption == DISPLAY_OPTION_CIRCLE) {
            displayCircleImage(context, url, displayView);
            return;
        }
    }

    /**
     * 默认
     *
     * @param context
     * @param url
     * @param displayView
     */
    public void displayImage(Context context, Object url, ImageView displayView) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_base_image_loading)
                .error(R.drawable.ic_base_image_loading)
                .into(displayView);
    }

    /**
     * 圆角
     *
     * @param context
     * @param url
     * @param displayView
     * @param radius      角度
     */
    public void displayCornerImage(Context context, Object url, ImageView displayView, int radius) {
        displayCornerImage(context, url, displayView, radius, RoundedCornersTransformation.CornerType.ALL);
    }

    /**
     * 圆角
     *
     * @param context
     * @param url
     * @param displayView
     * @param radius      角度
     * @param cornerType  use{@link RoundedCornersTransformation.CornerType}
     */
    public void displayCornerImage(Context context, Object url, ImageView displayView, int radius, RoundedCornersTransformation.CornerType cornerType) {

        MultiTransformation multi = new MultiTransformation(new CenterCrop(),
                new RoundedCornersTransformation(radius, 0, cornerType));

        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.ic_base_image_loading)
                .error(R.drawable.ic_base_image_loading)
                .apply(bitmapTransform(multi))
                .into(displayView);
    }

    //圆
    public void displayCircleImage(Context context, Object url, ImageView displayView) {

        MultiTransformation multi = new MultiTransformation(new CenterCrop(),
                new CircleCrop());

        GlideApp.with(context)
                .load(url)
                .placeholder(R.drawable.ic_base_image_loading)
                .error(R.drawable.ic_base_image_loading)
                .apply(bitmapTransform(multi))
                .into(displayView);
    }
}
