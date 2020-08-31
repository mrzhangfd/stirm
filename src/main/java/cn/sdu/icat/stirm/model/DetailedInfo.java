package cn.sdu.icat.stirm.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;


/**
 *
 * @author J
 * @date 17-10-23
 */

@Data
@XStreamAlias("detailedinfo")
public class DetailedInfo {

    @XStreamAlias("location")
    private String location;
    @XStreamAlias("description")
    private String description;
    @XStreamAlias("result")
    private String result;

    public DetailedInfo() {
    }

    public DetailedInfo(String location, String description, String result) {
        this.location = location;
        this.description = description;
        this.result = result;
    }

    @Override
    public String toString() {
        String stringBuilder = location +
                " " +
                description +
                " " +
                result +
                " ";
        return stringBuilder;
    }


}
