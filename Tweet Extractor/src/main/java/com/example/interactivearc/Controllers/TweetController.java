package com.example.interactivearc.Controllers;

import com.example.interactivearc.Entities.tweet;
import com.example.interactivearc.Repositories.TweetRepository;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


@Controller
@RequestMapping("/")
public class TweetController {

    String next_token="";

    @RequestMapping(value = "/addtweet")
    public void search() throws URISyntaxException {
        JSONObject meta = new JSONObject();
        try {
            boolean flag = false, condition=false;
            int count=1;
            String searchString = "from:the_hindu OR from:IndianExpress OR from:IndiaToday OR from:EconomicTimes OR " +
                    "from:TOIIndiaNews OR from:livemint OR from:ians_india OR from:ani_digital OR from:TimesNow OR " +
                    "from:ndtv"; // You can change the twitter handles as per your need
            String bearerToken = ""; //PLACE YOUR TWITTER API KEY HERE
            String searchResponse = null;
            HttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build()).build();
            URIBuilder uriBuilder = new URIBuilder("https://api.twitter.com/2/tweets/search/recent");
            ArrayList<NameValuePair> queryParameters;
            queryParameters = new ArrayList<>();
            queryParameters.add(new BasicNameValuePair("query", searchString));
            uriBuilder.addParameters(queryParameters);
            uriBuilder.addParameter("tweet.fields", "attachments,author_id,created_at,public_metrics,source");
            uriBuilder.addParameter("max_results","100");
            do {
                if(flag && count==2) {
                    uriBuilder.addParameter("next_token", next_token);
                    System.out.println(next_token);
                }
                else if(flag && count>2)
                    uriBuilder.setParameter("next_token",next_token);
//                uriBuilder.setParameter("pagination_token",meta.get("next_token").toString());
//                String pagination_query = "pagination_token"
//                URIBuilder temp = uriBuilder;
                HttpGet httpGet = new HttpGet(uriBuilder.build());
                httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
                httpGet.setHeader("Content-Type", "application/json");

                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    searchResponse = EntityUtils.toString(entity, "UTF-8");
                }
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(searchResponse);
                JSONArray array = (JSONArray) json.get("data");
                meta = (JSONObject) json.get("meta");
                if(meta!=null){
                    if (meta.get("next_token") != null) {
                        next_token=meta.get("next_token").toString();
                        System.out.println("Next Token -> "+next_token);
                        condition = true;
                    }
                    else
                        condition = false;
                        //                        System.out.println(meta.get("next_token").toString());
                }
//            System.out.println("__________________________");
//            System.out.println("Unparsed Data" + "\n" + searchResponse);
//            System.out.println("__________________________");
                if (array != null) {
                    JSONObject obj = null;
                    JSONObject publicMetrics = null;
                    for (int i = 0; i < array.size(); i++) {
                        obj = (JSONObject) array.get(i);
                        String id = obj.get("id").toString();
                        String author_id = obj.get("author_id").toString();
                        String link = "https://twitter.com/" + author_id + "/status/" + id;
                        String tweetBody = obj.get("text").toString();
                        publicMetrics = (JSONObject) obj.get("public_metrics");
                        String retweetCount = publicMetrics.get("retweet_count").toString();
                        String replyCount = publicMetrics.get("reply_count").toString();
                        String likeCount = publicMetrics.get("like_count").toString();
                        String quoteCount = publicMetrics.get("quote_count").toString();
                        String tweetedAt = obj.get("created_at").toString();
//                    System.out.println("Tweet #" + i + "  Tweet Id:"+id+"  Author Id:"+author_id+"\nTweet Link:  "+link
//                            +"\n"+tweetBody);
                        addTweet(author_id, id, link, tweetBody,retweetCount, replyCount, likeCount, quoteCount,
                                tweetedAt);
//                    System.out.println("__________________________");
                    }
                }
                flag=true;
                count++;
            }while(condition && count<=10);

        System.out.println("Finished Successfully");

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    TweetRepository tweetRepo;

        public boolean addTweet(String author_id, String tweet_id, String link, String tweetBody,String retweetCount,
                                String replyCount,String likeCount,String quoteCount,String tweetedAt){
        tweet t = new tweet();
        t.setAuthor_id(author_id);
        t.setTweet_id(tweet_id);
        t.setLink(link);
        t.setTweetBody(tweetBody);
        t.setRetweetCount(retweetCount);
        t.setReplyCount(replyCount);
        t.setLikeCount(likeCount);
        t.setQuoteCount(quoteCount);
        t.setTweetedAt(tweetedAt);
//        t.printAll();
//        System.out.println(t.getId());
        tweetRepo.save(t);
        return true;
    }

    @GetMapping(path="/alltweets")
    public @ResponseBody
    Iterable<tweet> getAllUsers() {
        return tweetRepo.findAll();
    }
}
