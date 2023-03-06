package com.jz.mall.elasticsearch.esModel;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *  将商品列表中,需要经常查到的信息属性,注入这里
 *
 * Document中 indexName表示数据库 product表示数据表  replicas 默认副本数量;shards 分片数
 */
@Document(indexName = "pms", type = "product",shards = 1,replicas = 1)
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    private Long id;
    private String productSn;
    private Long brandId;
    @Field(type = FieldType.Text)
    private String brandName;
    private Long productCategoryId;
    private String productCategoryName;
    private String pic;
    @Field(type = FieldType.Keyword)
    private String name;
    @Field(type = FieldType.Text)
    private String subTitle;
    @Field(type = FieldType.Keyword)
    private String keywords;
    private BigDecimal price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
    private List<EsProductAttributeValue> attrValueList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    public Integer getRecommandStatus() {
        return recommandStatus;
    }

    public void setRecommandStatus(Integer recommandStatus) {
        this.recommandStatus = recommandStatus;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(Integer promotionType) {
        this.promotionType = promotionType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<EsProductAttributeValue> getAttrValueList() {
        return attrValueList;
    }

    public void setAttrValueList(List<EsProductAttributeValue> attrValueList) {
        this.attrValueList = attrValueList;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}

//@Data
//@EqualsAndHashCode
//@Document(indexName = "pms")
//@Setting(shards = 1,replicas = 0)
//public class EsProduct implements Serializable {
//    private static final long serialVersionUID = 1L;
//    @Id
//    private Long id; //表示这个是每一个索引(表)里元素(数据行)的id
//
//    @Field(type = FieldType.Keyword)//所有用@Field 注解标注的 表示都是这行元素的属性,type=FieldType.Keyword 表示  不会进行分词建立索引的类型
//    private String productSn;
//
//    @Field(type = FieldType.Keyword)
//    private String brandName;
//
//    @Field(type = FieldType.Keyword)
//    private String productCategoryName;
//
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)  // Text,  会进行分词并建了索引的字符类型(经常去查的)
//    private String name;
//
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)
//    private String subTitle;
//
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)
//    private String keywords;
//
//    @Field(type =FieldType.Nested) //Nested ,嵌套对象类型
//    private List<EsProductAttributeValue> attrValueList;
//
//    private Long brandId;
//    private String pic;
//    private BigDecimal price;
//    private Integer sale;
//    private Integer newStatus;
//    private Integer recommandStatus;
//    private Integer stock;
//    private Integer promotionType;
//    private Integer sort;
//    private Long productCategoryId;
//}
