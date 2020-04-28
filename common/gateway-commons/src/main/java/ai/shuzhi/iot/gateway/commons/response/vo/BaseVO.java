package ai.shuzhi.iot.gateway.commons.response.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 
 * @author alanyuan
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseVO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = -2075602322284672096L;

	/**
	 * 主键
	 */
	private String id;

}
