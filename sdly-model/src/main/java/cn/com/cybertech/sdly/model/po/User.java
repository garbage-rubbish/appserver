package cn.com.cybertech.sdly.model.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @desc 用户PO

 * @author zhumaer
 * @since 6/15/2017 2:48 PM
 */
@ApiModel("用户PO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User  {

	private static final long serialVersionUID = -7491215402569546437L;

	@ApiModelProperty(value = "用户主键")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "SELECT REPLACE(UUID(),'-','')")
	private String id;

	@ApiModelProperty(value = "昵称")
	private String nickname;

	@ApiModelProperty(value = "性别")
	private String gender;

	@ApiModelProperty(value = "头像")
	private String avatar;

	@ApiModelProperty(value = "状态")
	private String type;

	@ApiModelProperty(value = "账号状态")
	private String status;

	@ApiModelProperty(value = "创建时间")
	@Column(name = "create_Time")
	private Date createTime;

	@ApiModelProperty(value = "更新时间")
	@Column(name = "update_Time")
	private Date updateTime;

}
