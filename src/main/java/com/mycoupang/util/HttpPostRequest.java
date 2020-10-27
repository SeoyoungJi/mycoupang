package com.mycoupang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HttpPostRequest {
	
	public static String httpPost (String pURL, HashMap < String, String > map) {
			
			String myResult = "";
	
	        try {
	            //   URL 설정하고 접속하기 
	            URL url = new URL(pURL); // URL 설정 
	
	            HttpURLConnection http = (HttpURLConnection) url.openConnection(); // 접속 
	            System.setProperty ( "http.agent", "Mozilla / 4.76");
	            
	            //-------------------------- 
	            //   전송 모드 설정 - 기본적인 설정 
	            //-------------------------- 
	            http.setDefaultUseCaches(false);
	            http.setDoInput(true); // 서버에서 읽기 모드 지정 
	            http.setDoOutput(true); // 서버로 쓰기 모드 지정  
	            http.setRequestMethod("POST"); // 전송 방식은 POST
	
	
	            //--------------------------
	            // 헤더 세팅
	            //--------------------------
	            // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다 
	            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
	
	
	            //-------------------------- 
	            //   서버로 값 전송 
	            //-------------------------- 
	            StringBuffer buffer = new StringBuffer();
	
	            //HashMap으로 전달받은 파라미터가 null이 아닌경우 버퍼에 넣어준다
	            if (map != null) {
	
	                Set key = map.keySet();
	
	                for (Iterator iterator = key.iterator(); iterator.hasNext();) {
	                    String keyName = (String) iterator.next();
	                    String valueName = map.get(keyName);
	                    buffer.append(keyName).append("=").append(valueName);
	                }
	            }
	
	            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
	            PrintWriter writer = new PrintWriter(outStream);
	            writer.write(buffer.toString());
	            writer.flush();
	
	
	            //--------------------------
	            //   Response Code
	            //--------------------------
	        //    http.getResponseCode();
	
	
	            //-------------------------- 
	            //   서버에서 전송받기 
	            //-------------------------- 
	           
	
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return myResult;
	        
	    }		
}
