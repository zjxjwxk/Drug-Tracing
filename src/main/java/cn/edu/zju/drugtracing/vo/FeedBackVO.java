package cn.edu.zju.drugtracing.vo;

import lombok.Data;

/**
 * @author Xinkang Wu
 * @date 2020/8/21 6:53 下午
 */
@Data
public class FeedBackVO {

    String packageID;
    String information;
    Integer age;
    Integer gender;
    String time;

    public FeedBackVO(String packageID, String information, Integer age, Integer gender, String time) {
        this.packageID = packageID;
        this.information = information;
        this.age = age;
        this.gender = gender;
        this.time = time;
    }
}
