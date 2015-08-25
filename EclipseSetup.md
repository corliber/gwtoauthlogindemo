
These instructions are for Mac. I think it's similar on other platforms. Please let me know if there is anything substantially different.

# Setup Eclipse #
  * Install Eclipse http://www.eclipse.org/. I use Eclipse 3.7 (Indigo) Classic.

  * Install GWT SDK and Eclipse plugin from https://developers.google.com/web-toolkit/download. I use GWT 2.5.

  * Install Subversion plugin (I use the pure Java version):
    * Click _Help->Install New Software..->_
    * Select `Indigo - http://download.eclipse.org/releases/indigo` from Work With Selection box
    * Expand Collaboration Item
    * Select Subversion items. Follow the prompts. Restart Eclipse
    * Select SVN Kit 1.3.5
    * Click Finish. Follow the prompts. Restart Eclipse.
# Import Project #
  * Click _Window->Open Perspective->Other.._

  * Select `SVN Repository Exploring`. Click OK

  * Right click on `SVN Repositories` pane and select _New->Repository Location.._ In the URL text field, enter `https://gwtoauthlogindemo.googlecode.com/svn/trunk/GWTOAuthLoginDemo`

  * if you will be working as a developer, Enter your google email and password in `User:` and `Password:` fields respectively and click on `Save authentication..` checkbox if you want.

**Note**: this is not your google password, it is the password specific to code.google.com. You can find it by visiting https://code.google.com/hosting/settings. This authentication information is used during checking in code.

  * Click Finish.

  * Right click on the  URL and select `Find/CheckOut As...`

  * Click on radio button  `Check out as a project with the name specified:`  and enter `GWTOAuthLoginDemo` in the text filed below. Click Finish.

  * Switch back to Java perspective _Window->Open Perspective->Java_.

You should see `GWTOAuthLoginDemo` in Package Explorer. If you see anything red, open Problems view form _Window->Show View->Problems_ and see if you can figure out the issue.