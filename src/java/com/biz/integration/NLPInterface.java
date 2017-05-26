/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.biz.integration;

import com.biz.model.SkillFlowContext;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import org.json.JSONObject;

/**
 *
 * @author Akhilesh yadav
 */
public class NLPInterface {
    private static ResourceBundle bundle = null;
    public String resolveBotRequest (SkillFlowContext skillFlowContext){
		 StringBuilder result=null;
		 try {
            bundle = ResourceBundle.getBundle("config");
//            System.out.println(bundle.getString("api_ai_ip"));
            URL url = new URL(bundle.getString("nlp_ip"));
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/json");
            http.setDoInput(true);
            http.setDoOutput(true);
            JSONObject obj = new JSONObject();
            obj.put("question", skillFlowContext.getQueryParameter());
            System.out.println(obj.toString());
            //Send request
            DataOutputStream wr = new DataOutputStream(
                    http.getOutputStream());
            wr.writeBytes(obj.toString());
            wr.flush();
            wr.close();
            int statusCode = http.getResponseCode();
            System.out.println("code=======" + statusCode);
            String newLine = System.getProperty("line.separator");
            BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream()));
            result = new StringBuilder();
            String line;
            boolean flag = false;
            while ((line = reader.readLine()) != null) {
                result.append(flag ? newLine : "").append(line);
                flag = true;
            }
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("error  " + e.getMessage());
        }
                 return result.toString();
	}
}
