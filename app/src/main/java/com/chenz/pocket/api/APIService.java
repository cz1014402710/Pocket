package com.chenz.pocket.api;

import com.chenz.pocket.bean.GoodsBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * method  请求方法，不区分大小写
 * path    路径
 * hasBody 是否有请求体
 *
 * @HTTP(method = "get", path = "blog/{id}", hasBody = false)
 * Call<ResponseBody> getBlog(@Path("id") int id);
 * <p>
 * 使用 @Body 注解，指定一个对象作为 request body 。
 * @POST("users/new") Call<User> createUser(@Body User user);
 * <p>
 * 表单提交，如登录
 * @FormUrlEncoded
 * @POST("v1/login") Call<ResponseBody> userLogin(@Field("phone") String phone, @Field("password") String password);
 * <p>
 * 链接 http://baseurl/blog/id
 * 请求 URL 可以替换模块来动态改变，替换模块是 {}包含的字母数字字符串，替换的参数必须使用 @Path 注解的相同字符串。
 * @GET("blog/{id}") Call<ResponseBody> getBlog(@Path("id") int id);
 * <p>
 * 链接 http://baseurl/blog/id?sort=ShortStr
 * @GET("blog/{id}") Call<ResponseBody> getBlog(@Path("id") int id, @Query("sort") String sort);
 * <p>
 * http://baseurl/blog/id?param1=Param1&param2=Param2...
 * @GET("blog/{id}") Call<ResponseBody> getBlog(@Path("id") int id, @QueryMap Map<String, String> options);
 * Created by chenz on 2017/3/30.
 */
public interface APIService {

    //    public static final String API_BASE_URL = "https://api.douban.com/v2/";
    String API_BASE_URL = "https://www.mxnzp.com/api/";
//    public static final String API_BASE_URL = "https://api.github.com/search/";


    @GET("barcode/goods/details")
    Call<GoodsBean> getGoodsDetail(@Query("barcode") String paramString);

}
