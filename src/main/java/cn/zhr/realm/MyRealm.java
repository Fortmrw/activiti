package cn.zhr.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.zhr.dao.UserDao;
import cn.zhr.entity.User;

public class MyRealm extends AuthorizingRealm {

	@Resource
	private UserDao userDao;

	/**
	 * 授予角色权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// 获取登录用户名
		// String username =(String) arg0.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(null);
		authorizationInfo.setStringPermissions(null);
		return null;
	}

	/**
	 * 验证登录
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName = (String) token.getPrincipal();
		User user = userDao.selectByName(userName);
		if (user != null) {
			AuthenticationInfo info = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), "realmName");
			return info;
		} else {
			return null;
		}
	}

}
