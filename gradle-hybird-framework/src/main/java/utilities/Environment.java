package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources ({"file:environmentConfig/${env}.properties"})
public interface Environment extends Config{
	//Hàm getter
	@Key("App.Url")
	String appUrl();
	
	@Key("App.User")
	String appUrlname();
	
	@Key("App.Pass")
	String appPassword();
	
	@Key("DB.Host")
	String dbHostname();
	
	@Key("DB.User")
	String dbUsername();

	@Key("DB.Pass")
	String dbPassword();

}
