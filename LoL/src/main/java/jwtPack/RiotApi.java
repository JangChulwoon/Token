package jwtPack;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class RiotApi {
	// 각자 환경이 달라 api key 를 따로 받거나 획일화 해야함.
	public long judge(String id) {
		StringBuffer buffer = new StringBuffer();
		String trans;
		URL url;
		long idnum = 0;
		// https://kr.api.pvp.net/api/lol/kr/v1.4/summoner/by-name/lusiue?api_key=25dd02d8-5b30-48d9-8e1a-1e06efd62a17
		try {
			buffer.append("https://kr.api.pvp.net/api/lol/kr/v1.4/summoner/by-name/");
			trans = URLEncoder.encode(id, "UTF-8");
			buffer.append(trans);
			buffer.append("?api_key=25dd02d8-5b30-48d9-8e1a-1e06efd62a17");
			url = new URL(new String(buffer));
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			JSONObject object = (JSONObject) JSONValue.parseWithException(isr);
			JSONObject obj = (JSONObject) object.get(id);
			idnum = (Long) obj.get("id");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idnum;
	}

	public String pagename(long id) {
		StringBuffer buffer = new StringBuffer();
		URL url;
		String p_name = null;
		try {
			buffer.append("https://kr.api.pvp.net/api/lol/kr/v1.4/summoner/");
			buffer.append(id);
			buffer.append("/runes?api_key=25dd02d8-5b30-48d9-8e1a-1e06efd62a17");
			url = new URL(new String(buffer));
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
			JSONObject object = (JSONObject) JSONValue.parseWithException(isr);
			JSONObject obj = (JSONObject) object.get("" + id);
			JSONArray page = (JSONArray) obj.get("pages");
			JSONObject run = (JSONObject) page.get(0);
			p_name = (String) run.get("name");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p_name;
	}

}
