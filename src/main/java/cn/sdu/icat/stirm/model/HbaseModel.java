package cn.sdu.icat.stirm.model;

import lombok.Data;

/**
 * Hbase数据模型
 * refs：http://c.biancheng.net/view/6517.html
 *
 * @author icatzfd
 * Created on 2020/4/26 23:21.
 */
@Data
public class HbaseModel {
    /**
     * 行
     */
    private String row;

    /**
     * 列族 Column Family
     */
    private String familyName;

    /**
     * 列标识 Column Qualifier
     */
    private String qualifier;

    /**
     * Cell 的值
     */
    private String value;

    public HbaseModel() {
    }

    public HbaseModel(String row, String familyName, String qualifier, String value) {
        this.row = row;
        this.familyName = familyName;
        this.qualifier = qualifier;
        this.value = value;
    }
}
