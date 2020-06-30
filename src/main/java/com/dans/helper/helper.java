package com.dans.helper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;



public class helper {
	public static String postTransaction(String url, Object params) throws Exception {
		URL BASE_URL = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) BASE_URL.openConnection();
		
		
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setConnectTimeout(20000);
		con.setDoInput(true);
		con.setDoOutput(true);

		DataOutputStream res = new DataOutputStream(con.getOutputStream());
		res.writeBytes(params.toString());
		res.flush();
		res.close();

		StringBuffer strSuccess = new StringBuffer();
		BufferedReader bufferSuccess = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String inputLine;
		while ((inputLine = bufferSuccess.readLine()) != null) {
			strSuccess.append(inputLine);
		}
		bufferSuccess.close();
		
		String out =strSuccess.toString();

		return out;
	}
	
	public static String getTransaction(String url) throws Exception {
		URL BASE_URL = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) BASE_URL.openConnection();
		
		
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setConnectTimeout(20000);
		con.setDoInput(true);
		con.setDoOutput(true);

		
		StringBuffer strSuccess = new StringBuffer();
		BufferedReader bufferSuccess = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String inputLine;
		while ((inputLine = bufferSuccess.readLine()) != null) {
			strSuccess.append(inputLine);
		}
		bufferSuccess.close();
		
		String out =strSuccess.toString();

		return out;
	}
}
