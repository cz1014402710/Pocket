package com.chenz.pocket.bean;

import com.chenz.pocket.base.BaseBean;

/**
 * description: <一句话功能简述>
 *
 * @author Chenz
 * @date 2020/6/22
 */
public class GoodsBean extends BaseBean {

    /**
     * {"code":1,
     * "msg":"数据返回成功！",
     * "data":{
     * "goodsName":"脉动维生素饮料（水蜜桃口味）600ml",
     * "barcode":"6902538005141",
     * "price":"3.80",
     * "brand":"达能",
     * "supplier":"达能(中国)食品饮料有限公司",
     * "standard":"600ml"}}
     */

    public Data data;

    public static class Data {
        public String goodsName;
        public String barcode;
        public String price;
        public String brand;
        public String supplier;
        public String standard;

        @Override
        public String toString() {
            return "Data{" +
                    "goodsName='" + goodsName + '\'' +
                    ", barcode='" + barcode + '\'' +
                    ", price='" + price + '\'' +
                    ", brand='" + brand + '\'' +
                    ", supplier='" + supplier + '\'' +
                    ", standard='" + standard + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "data=" + data +
                '}';
    }
}
