
# Before running #
Before running the app in your own machine, you must do the following:
  * [Setup Eclipse](EclipseSetup.md)
  * Come up with a fictitious domain name e.g. _oauthtest.com_.
  * Determine the URL of your application.
  * Use the domain and URL to register the application with providers (facebook, google, twitter etc.)
  * update hosts file to point the domain name to 127.0.0.1
    * In Linux/Unix/Mac, edit /etc/hosts file, add the following:
```
127.0.0.1 oauthtest.com
```
    * In windows, update `c:\Windows\System32\drivers\etc\hosts` file (as Administrator).
    * verify, `ping oauthtest.com`
```
PING oauthtest.com (127.0.0.1): 56 data bytes
64 bytes from 127.0.0.1: icmp_seq=0 ttl=64 time=0.045 ms
64 bytes from 127.0.0.1: icmp_seq=1 ttl=64 time=0.080 ms
...

```
Note: some providers support 127.0.0.1 in the URL, some don't. So it's a good idea just to use a domain.
  * Update source with registered URL, App ID and App Secret.
# Register Application #
Here is a screenshot of an application registration window with Facebook:

![http://gwtoauthlogindemo.googlecode.com/svn/wiki/facebook-app-register.png](http://gwtoauthlogindemo.googlecode.com/svn/wiki/facebook-app-register.png)

After successful authentication, Facebook will redirect the app to the URL specified in **Site URL**.

Register the URL with other OAuth providers in a similar way. Please Google to find the registration URL.

# Update source #
  * Modify `src/com/example/GWTOAuthLoginDemo/client/util/ClientUtils.java` and change APP\_CALLBACK\_URL to your URL. e.g.
```
    private static final String APP_CALLBACK_URL   = "https://oauthtest.com:8888/GWTOAuthLoginDemo.html";
```
  * Modify `src/com/example/GWTOAuthLoginDemo/server/util/ServerUtils.java` and change app id and app secret for the providers for your app. e.g.
```
    public static final String FACEBOOK_APP_ID      = "534691036564570";
    public static final String FACEBOOK_APP_SECRET  = "60c0fecf6aa2042b00104fb65ff0f13d";
```

You have to do the same for each of the OAuth providers after registering the app.
# Run #
The application can not be run in GWT dev mode, because the code server part of the URL can not be registered with the providers and GWT will crap out when redirection happens in a different URL. Therefore, the app **must be compiled** and run. The easy way is to use the ant task `compile-and-run`.

  * Before running ant tasks, you must set the environment variable **GWT\_DIR** to the directory where GWT SDK is installed. Example, in Linux/Unix/Mac:
```
GWT_DIR=/usr/local/gwt-2.5.0; export GWT_DIR
```
  * To load the Ant build.xml,  In Eclipse, Click  _Window->Show View->Ant_. Then Right click, Select `Add Buldfiles...`.  Select `build.xml` and click OK. Open the ant tasks and run `cmpile-and-run`.

![http://gwtoauthlogindemo.googlecode.com/svn/wiki/ant-tasks.png](http://gwtoauthlogindemo.googlecode.com/svn/wiki/ant-tasks.png)

  * Start Firefox or chrome and enter the following URL in the URL bar:
```
http://oauthtest.com:8888/GWTOAuthLoginDemo.html   
```