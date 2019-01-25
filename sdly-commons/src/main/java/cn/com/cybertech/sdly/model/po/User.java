package cn.com.cybertech.sdly.model.po;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@ApiModel
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User  {

	private static final long serialVersionUID = -7491215402569546437L;

	private Integer id;
	private String username;
	private String password;
	private String phone;
	private String idCard;
	private String depart;
	private List<String> roles;
	private Date lastChangePwdTime;



}
