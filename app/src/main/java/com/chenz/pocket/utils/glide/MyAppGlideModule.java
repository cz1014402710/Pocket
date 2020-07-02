package com.chenz.pocket.utils.glide;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;

/**
 * description: <Glide配置>
 *
 * @author Chenz
 * @date 2017/11/1
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {

    private String TAG = getClass().getSimpleName();

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        Log.d(TAG, "--- Glide Module init begin ---");

        int diskSize = 1024 * 1024 * 1024; // unit:byte 1Gb
        // 定义缓存大小和位置 默认：ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR
        Environment.isExternalStorageEmulated();
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskSize));  //内存中
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, diskSize)); //sd卡中
        // 默认内存和图片池大小
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize(); // 默认内存大小
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize(); // 默认图片池大小
        builder.setMemoryCache(new LruResourceCache(defaultMemoryCacheSize)); // 该两句无需设置，是默认的
        builder.setBitmapPool(new LruBitmapPool(defaultBitmapPoolSize));
        Log.d(TAG, "--- Glide Module init end ---");
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
    }

    // Disable manifest parsing to avoid adding similar modules twice.
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
