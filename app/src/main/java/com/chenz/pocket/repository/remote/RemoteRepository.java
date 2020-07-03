package com.chenz.pocket.repository.remote;

import com.chenz.pocket.bean.GoodsBean;
import com.chenz.pocket.repository.remote.api.NetWorks;

import retrofit2.Call;


/**
 * description: <一句话功能简述>
 *
 * @author Chenz
 * @date 2020/7/3
 */
public class RemoteRepository {

    private final static RemoteRepository S_REMOTE_REPOSITORY = new RemoteRepository();

    private RemoteRepository() {
    }

    public static RemoteRepository getInstance() {
        return S_REMOTE_REPOSITORY;
    }


    public Call<GoodsBean> getGoodsDetail(String barcode) {
       return NetWorks.getInstance().getAPIService().getGoodsDetail(barcode,"sj2wyjpijjvusagh","bC9DYkdvWkFjNnFtT0pvUTR3SjltQT09");
    }

    public Call<GoodsBean> getGoodsDetailOld(String barcode) {
        return NetWorks.getInstance().getAPIService().getGoodsDetailOld(barcode);
    }

}
