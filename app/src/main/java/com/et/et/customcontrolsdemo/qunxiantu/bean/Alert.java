package com.et.et.customcontrolsdemo.qunxiantu.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/4/12 0012.
 */
public class Alert {

    /**
     * abnormal :
     * city_code : 101280601
     * detail : 6小时内本地将可能有暴雨发生，或者强降水将可能持续
     * holiday :
     * level : 黄色
     * pub_time : 1460443200000
     * title : 深圳气象台12日14时发布暴雨黄色预警!
     * type : 暴雨
     */
    public class AlertBean {
        private String abnormal;
        private String city_code;
        private String detail;
        private String holiday;
        private String level;
        private long pub_time;
        private String title;
        private String type;

        public String getAbnormal() {
            return abnormal;
        }

        public void setAbnormal(String abnormal) {
            this.abnormal = abnormal;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getHoliday() {
            return holiday;
        }

        public void setHoliday(String holiday) {
            this.holiday = holiday;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public long getPub_time() {
            return pub_time;
        }

        public void setPub_time(long pub_time) {
            this.pub_time = pub_time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
