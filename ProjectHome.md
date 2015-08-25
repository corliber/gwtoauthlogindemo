
# The project is being moved to GitHub: https://github.com/muquit/gwtoauthlogindemo #
# What is it? #
  * [GWTOAuthLoginDemo](https://gwtoauthlogindemo.appspot.com/GWTOAuthLoginDemo.html) is a web application written using  [GWT](https://developers.google.com/web-toolkit/)  to demonstrate user Authorization using [OAuth](http://oauth.net/) protocol used by third party providers (Facebook, Google, Twitter etc.). This Application uses Server side flow of OAuth using the [Scribe](https://github.com/fernandezpablo85/scribe-java) library.

  * If you need to add third party login in your GWT application, I would like to think you can use this application as an example.   I am releasing the code with the hope that you will find it useful. I am also interested in code review, finding security issues etc. if there are any.

Suggestions, bug reports are always welcome. Please join the discussion group for posting questions, concerns, suggestions.

If you are a competent coder, please join the project and make it better.
# What is GWT? #
If you are not familiar with [GWT](https://developers.google.com/web-toolkit/), it is an AJAX web toolkit from Google. In short, the code is written in Java and a compiler generates optimized, portable and scalable JavaScript.  So, if you are a Java programmer, highly scalable, enterprise class web application can be developed with GWT with ease.  The best thing is, the application can be debugged and refactored with your favorite IDE tool like Eclipse.
# Demo #
The app is hosted on Google App Engine.

To try out the stable version, please visit:

~~https://gwtoauthlogindemo.appspot.com/GWTOAuthLoginDemo.html~~

Please visit the page at Github https://github.com/muquit/gwtoauthlogindemo
(Jul-06-2014)

# Source #
To browse/checkout the source please click on the Source link above.

# Why Prefer Server Side flow? #
  * More control. All the logic and processing are done in server side.
  * Secure. The request can be signed with your App Secret.
  * OAuth artifacts (e.g. Access Token)  can remain in the server side. Note: In this example, I bring them to client side explicitly via RPC to show them to you as an example.
  * No JavaScript, no popup window, no logic in client side.

# Supported Providers #
Currently the the  supported OAuth Providers are:
  * Facebook
  * Google
  * Twitter
  * Yahoo!
  * Linkedin
  * Instagram
  * Vimeo
  * github
  * flickr
  * Microsoft Live Connect
  * tumblr
  * foursquare

This application uses [Scribe](https://github.com/fernandezpablo85/scribe-java) library in Server Side, because I find the library very easy to use and extend. For example, github and Instagram are implemented by extending with very little code. Look at OAuth directory for the code.

**Login Screen**

![http://gwtoauthlogindemo.googlecode.com/svn/wiki/app-login-screen.png](http://gwtoauthlogindemo.googlecode.com/svn/wiki/app-login-screen.png)


# Eclipse setup #
Please look at the wiki.

# How to run in your own machine #
Please look at the wiki.

# Application Flow #
In this flow when I use the word provider, it means an OAuth provider e.g. Facebook, Google etc. These are the brief description of the OAuth flow this application uses.  Note: the [Scribe](https://github.com/fernandezpablo85/scribe-java) library does all the heavy lifting of talking to the OAuth Providers and getting the resources.

  * Before using OAuth, you must register your application with the OAuth Provider. When you register the application, you specify the URL of your application (It is called Site URL, Callback URL,  Redirect URL etc.).  And the provider will give you Application ID and Application Secret.  As the name indicates, the Application Secret should be kept secret.

  * When the  Login button for a provider is clicked,  an GWT RPC call is made to the  OAuthLoginService to get the Authorization URL for the provider.

  * if the Authorization URL is received, the application redirects itself to the Authorization URL of the provider.

  * The Provider verifies the Application ID and goes through the process of authenticating the user. In this step, you will see the provider will ask you to give permission to the application. If the user is authenticated, the provider redirects the application to the registered URL of the app. Note: this application does not ask access to any specific private resource, e.g. email. But it certainly can be asked if needed. In that case the provider will ask you to give access to the resource.

  * The application intercepts the redirection and uses the parameters in the URL to make a GWT RPC call to the OAuthLoginService to verify the user. The OAuthLoginService talks to the provider to obtain something called **Access Token**. If the **Access Token** is received, the service uses the Access Token  to access a protected URL of the user (for example, the public profile information). If the information is received, the user is authenticated. Note: in this step many other things happen, for example the service will verify the state (nonce) to protect against [CSRF](http://en.wikipedia.org/wiki/Cross-site_request_forgery) attack if the provider supports it. You have to look at the OAuthLoginService code for gory details.
# Login #
When the Login link is clicked and if you are already logged in with an OAuth Provider (e.g. Facebook, Twitter etc.) you will not see the usual screens from the provider. Because the provider will provide the Access Token to our server and you will be verified with that information in the background, provided the Access Token has not expired yet.
# Logout #
When the Logout link is clicked, you will be logged out of this specific application, meaning the session of the application will be invalidated and the cookies will be removed. But you will still be logged in to the OAuth Provider.  As OAuth is an authorization protocol (you get an Access Token to access certain protected resources),  there is no way to Logout in a traditional sense. You will notice, if you Logout and Login again,  your provider will give you the same Access Token if it has not expired yet.

# External libraries used #
The application includes all the jar files necessary in war/WEB-INF/lib directory.  Here are the few ones that should be mentioned specifically.

| **Name** | **Description** |
|:---------|:----------------|
| scribe-1.3.2-patched.jar | OAuth           |
| json-simple-1.1.1.jar | Parsing JSON    |
| argo-3.2.jar | Pretty printing JSON |
| gwt-log-3.2.0.jar | Client side logging |