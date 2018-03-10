package client;

import hw.HelloRemote;
import java.security.Security;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.sasl.JBossSaslProvider;

public class HelloClient {
    static {
        Security.addProvider(new JBossSaslProvider());
    }

    public static void main(String[] args) throws Exception {
        // Appel du stateless bean
        HelloRemote helloBean = lookupRemoteStatelessHello();
        System.out.println(helloBean.sayHello());
    }

      // Connexion au serveur et lookup du bean
      private static HelloRemote lookupRemoteStatelessHello() throws NamingException {
    	HelloRemote remote=null;
        try {
            Properties jndiProps = new Properties();
            jndiProps.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            InitialContext ctx = new InitialContext(jndiProps); 
            remote = (HelloRemote) ctx.lookup("ejb:/ProjetEJB/Hello!hw.HelloRemote");
        	} catch (Exception e) {
            e.printStackTrace();
        		}    	
        return remote;
    }
}

