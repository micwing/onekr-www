package onekr.biz.user.realm;

import onekr.biz.model.User;
import onekr.biz.model.UserPassword;
import onekr.biz.user.intf.UserLoginBiz;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Shiro框架自定义Realm
 * @author Administrator
 *
 */
@Service
public class UserAuthorizingRealm extends AuthorizingRealm {
	//自动装配的dao需要在shiro.xml中显式配置
	@Autowired
	private UserLoginBiz userLoginBiz;
	
	/*
	 * 授权 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		User user = (User) getAvailablePrincipal(principals);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//			info.setRoles(userLoginBiz.findUserRoleStr(user));
//			info.setStringPermissions(userLoginBiz.findUserRolePermissionsStr(user));
			return info;
		}
		return null;
	}

	/*
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;   
        String input = token.getUsername();
    	if (!StringUtils.isEmpty(input) ) {
    		User user = userLoginBiz.findUserByName(input);
    		UserPassword userpassword = userLoginBiz.findUserPasswordByName(input);
    		if (user != null) {    			
    			return new SimpleAuthenticationInfo(user, userpassword.getPassword(), getName());
    		}
    	}
		return null;
	}

}
