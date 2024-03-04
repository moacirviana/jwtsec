package br.jus.tream.jwtsec.security;

import org.springframework.stereotype.Service;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ActiveDirectoryService {
    
    @Value("${ad.domain}")
    private String AD_DOMAIN;
	
	@Value("${ad.url}")
    private String AD_URL;

    @SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public boolean loginActiveDirectory(String username, String password) {
    	//return true;
    
		LdapContext ctx = null;
		String userName = String.format(AD_DOMAIN, username);
        Hashtable authEnv = new Hashtable(11);
		try {
			if (!password.equals("")) {
				Hashtable environment = new Hashtable();
				environment.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
				environment.put(Context.PROVIDER_URL, AD_URL);
				environment.put(Context.SECURITY_AUTHENTICATION, "simple");
				environment.put(Context.SECURITY_PRINCIPAL, userName);
				environment.put(Context.SECURITY_CREDENTIALS, password);
				ctx = new InitialLdapContext(environment, null);
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

}
