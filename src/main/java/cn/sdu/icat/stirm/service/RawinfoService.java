package cn.sdu.icat.stirm.service;

/**
 * Created by J on  17-10-27.
 */

public interface RawinfoService {

    String findRealName(String objectId);

    String findRawText(String objectId);

    void addRealName(String realName,String objectId);

    void insertRealName(String realName,String objectId);

    void addRawText(String rawText,String objectId);

    String getRawinfo(String objectId);

    void addRawinfo(String objectId,String raw);
}
