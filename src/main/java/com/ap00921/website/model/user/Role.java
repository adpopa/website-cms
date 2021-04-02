/**
 * Role.java
 */
package com.ap00921.website.model.user;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alex Daniel Popa
 *
 */
@Document(collection = "roles") 
public class Role {

	@Id
	@Getter
	private String roleId;
	
	@Getter
	@Setter
	private String roleType;

	/**
	 * @param roleId
	 */
	public Role(String roleType) {
		this.roleId = new ObjectId().toHexString();
		this.roleType = roleType;
	}
	
}
