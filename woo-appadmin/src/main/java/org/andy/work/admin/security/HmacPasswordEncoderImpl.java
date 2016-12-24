package org.andy.work.admin.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class HmacPasswordEncoderImpl implements PasswordEncoder {

	@Override
	public String encodePassword(String rawPass, Object salt) {
		String pw=DigestUtils.md5Hex(rawPass);
		String pw1=DigestUtils.md5Hex(pw);
		return DigestUtils.md5Hex(pw1);
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		boolean flag = encPass.equals(this.encodePassword(rawPass, salt));
		return flag;
	}
	
}
