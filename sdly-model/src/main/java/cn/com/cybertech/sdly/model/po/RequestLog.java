package cn.com.cybertech.sdly.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Date;

/**
 * 请求日志持久类
 * Created by huangkd on 2019/1/20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "td_request_log")
public class RequestLog extends BasePo<String> {

    private static final long serialVersionUID = 3853724368241818325L;

    public RequestLog(Date createTime, Date updateTime, String className, String methodName, String ip, String params, String reqUrl, String desc, String result, long spendTime) {
        setCreateTime(createTime);
        setUpdateTime(updateTime);
        this.className = className;
        this.methodName = methodName;
        this.ip = ip;
        this.params = params;
        this.reqUrl = reqUrl;
        this.desc = desc;
        this.result = result;
        this.spendTime = spendTime;
    }

    @Id
    @Column(name = "id")
    //@SequenceGenerator(name = "requestLog" ,sequenceName = "SEQ_TD_REQUEST_LOG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select SEQ_TD_REQUEST_LOG_ID.nextVal from dual")
    private String id;
    /**
     * 目标类名
     */
    @Column(name="className")
    private String className;

    /**
     * 目标方法名
     */
    @Column(name="methodName")
    private String methodName;

    /**
     * 请求者ip
     */
    @Column(name="ip")
    private String ip;

    /**
     * 请求参数
     */
    @Column(name="params")
    private String params;

    /**
     * 请求url
     */
    @Column(name="reqUrl")
    private String reqUrl;


    /**
     * log描述
     */
    @Column(name="description")
    private String desc;

    /**
     * 结果返回
     */
    @Column(name="result")
    private String result;


    /**
     * 执行花费时间
     */
    @Column(name="spendTime")
    private long spendTime;


}
