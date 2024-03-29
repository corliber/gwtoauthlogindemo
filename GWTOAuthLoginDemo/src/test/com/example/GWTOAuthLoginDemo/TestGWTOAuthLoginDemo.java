package test.com.example.GWTOAuthLoginDemo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.example.GWTOAuthLoginDemo.client.model.SocialUser;
import com.example.GWTOAuthLoginDemo.client.util.ClientUtils;
import com.google.gson.Gson;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TestGWTOAuthLoginDemo
{
    private final static Log logger=LogFactory.getLog(TestGWTOAuthLoginDemo.class);
    
    @Before
    public void init() throws Exception
    {
        BasicConfigurator.configure();
        logger.info("info");
        logger.debug("debug");
        logger.error("error");
    }
    
    private static Map<String,String> parseQueryString(String qs)
    {
        String[] ps = qs.split("&");
        Map<String,String> map = new HashMap<String,String>();
        
        for (String p: ps )
        {
            String k = p.split("=")[0];
            String v = p.split("=")[1];
            map.put(k,v);
        }
        return map;
    }
    private String getQueryStringValue(String qs,String name)
    {
        Map<String,String> map = parseQueryString(qs);
        return map.get(name);
        
    }
    @Test
    public void testParseQuerystring()
    {
        final String qs = "oauth_token=A%3D1x1guZLgszvoOvDDCPQv5IqVYCnXel6NbQD_kKDremCtN66sgW5HbMPE.pTerRvJK4ogR1x1kmvqgU1fMylh_npk3KiHoWPfFLRTd3burE8WJaLVwqoMGljIw_FNXd.ITMjBsWoTRMkB5.sLRWT93GC3cMIOiob66Gonmu2KjnsU8v4V_qu5cKRyVQkAvY96K5n7igfRgX7A_enqUwmSe0tBlAbrOquWn5fv60z16iMqSeMB46E9sUaaf4Lag1GRPdzuIZMzC1pcnOfkQMglf1f7ozPJNSVO7On7dKn5UQ3kUnDMpkiT4_9WJSTIkJyYt_H1u4IegB2U8XdfWcZSbxWuBGDB3N0Ov2tY67ZKai.8lLE7uq_oRyqSEm3TtdCLkJEf73._MfRGYK1oWwxN7Nxs_VEcPq5OGd_TH49xeA6.mkv8jOrX.6hk67zuZAkmt2jRbLeB5N0u3bF5FKNMYJs6qtn_HtvGPQRBJMUJxftA49mmCKWENrybwIb8JZWE8cQnt63f.Nf_vlCwaamTOvX6cEFtcOGSb4gQrMtSb3J_opa28MycJIqsLSn045_eT7AyQHWlDuwN1CNjCGFYevlJ.qkatETNfAQ4ZjDrU1SxZVE5bxRedRmSSAuXr.DF49HpmYa4RFa3bbbn2V1DYolSHoqPrbCpxda1cYkCBHTtOyAJxIk970kxQKu_bbBtCLq4FizQ_X8j9Equ3g9kLrAFHS94eQ--&oauth_token_secret=0914f988577ec39419b887957ea815ce6a7bad4c&oauth_expires_in=3600&oauth_session_handle=AKOZulCqpibrGHWn_Ka7mY_W1T6LsxP.eQS01FB.LNCQvF62NA--&oauth_authorization_expires_in=793071275&xoauth_yahoo_guid=AWTCXFJYJJH77BHW4HFOUMS5SA";

        Map<String,String> map = parseQueryString(qs);
        Set<String> keys = map.keySet();
        for (String k: keys)
        {
            System.out.println(k + "=" + map.get(k));
        }
        String guid = getQueryStringValue(qs,"oauth_token_secret");
        System.out.println("GUID: " + guid);
    }
    
    @Test
    public void testRepalceGUID()
    {
        String url = ClientUtils.getProctedResourceUrl(4);
        System.out.println(url);
        String guid="uZLgszvoOvDDCPQv5Iq";
        url = url.replace("<GUID>",guid);
        System.out.println(url);
        
    }
    
    @Test
    public void testParseYahooProfileJson()
    {
        String json = 
            "{"
                + "\"profile\": {"
                + "\"guid\": \"ECUFIYO7BLY5FOAPEQDC3Y\","
                + "\"birthYear\": 1969,"
                + "\"displayAge\": 43,"
                + "\"gender\": \"M\","
                + "\"nickname\": \"jdoe\""
                + "}"
                + "}";
        Gson gson = new Gson();
        try
        {
            System.out.println(json);
            logger.error(gson);
            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(json);
            JSONObject jsonObject = (JSONObject) obj;
            json = (String) jsonObject.get("profile").toString();
            logger.info("JSON: " + json);
            SocialUser user = gson.fromJson(json,SocialUser.class);
            logger.info("guid: " + user.getGuid());
            logger.info("nickname: " + user.getNickname());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testInstagramProtectedResourceUrl()
    {
        String url = ClientUtils.getProctedResourceUrl(ClientUtils.INSTAGRAM);
        logger.info("url: " + url);
        String userId = "1234";
        String accessToken = "29329329difjldjfdjldldf";
        String formattedUrl = String.format(url,userId,accessToken);
        logger.info("formatted: " + formattedUrl);
        
    }
    
    @Test
    public void testInstagramTokenExtraction()
    {
        String accessTokenJson = 
            "{"
                + "\"access_token\":\"263534762.8e52bda.5a03053395cc4d8ebb47a3ac43f45da2\","
                + "\"user\":"
                + "{"
                + "\"id\":\"263534762\""
                + "\"username\":\"mmqx1219\","
                + "\"full_name\":\"mmqx2219\""
                + "}"
             + "}";
        
        logger.info(accessTokenJson);
        JSONParser jsonParser = new JSONParser();
        try
        {
            Object obj = jsonParser.parse(accessTokenJson);
            JSONObject jsonObj = (JSONObject) obj;
            String accessToken = (String) jsonObj.get("access_token");
            logger.info("Access Token: " + accessToken);
            jsonObj = (JSONObject) jsonObj.get("user");
            String userName = (String) jsonObj.get("username");
            logger.info("Usrname: " + userName);
            String userId = (String) jsonObj.get("id");
            logger.info("userId: " + userId);
            String fullName = (String) jsonObj.get("full_name");
            logger.info("Full name: " + fullName);
            
        } catch (ParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
