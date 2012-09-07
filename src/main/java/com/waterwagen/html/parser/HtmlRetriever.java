package com.waterwagen.html.parser;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class HtmlRetriever
{
	public static String retrieve(String url) throws ClientProtocolException, IOException
	{
        HttpClient httpclient = new DefaultHttpClient();
        try
        {
            HttpGet httpget = new HttpGet("http://www.cnn.com/");
            // Create a response handler
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            return httpclient.execute(httpget, responseHandler);
        } 
        finally 
        {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpclient.getConnectionManager().shutdown();
        }
	}
}
