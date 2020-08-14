package com.example.weathertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//My code import 
import java.io.InputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView edittext;
    private TextView tem;
    private TextView tem1;
    private TextView tem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //定义函数
        edittext = (TextView) findViewById(R.id.editText1);
        Button button = (Button) findViewById(R.id.button1);
        final TextView temp = findViewById(R.id.tem);
        final TextView temp1 = findViewById(R.id.tem1);
        final TextView temp2 = findViewById(R.id.tem2);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String my_string = edittext.getText().toString();
                if (TextUtils.isEmpty(my_string)) {
                    Toast.makeText(MainActivity.this, "没有数据输入", Toast.LENGTH_LONG).show();
                } else {
                    //使用api获取数据并提取数据

                    //创建URL对象

                    if(my_string =="北京"){
                        String areacode ="101010100";
                    }
                    
                    URL url = new URL("http://gfapi.mlogcn.com/weather/v001/now?areacode="+areacode+"&key=sB9vQLkyT8uigkr5FYfw8pHriiIb2OxP");
	
                    
		
                    //创建输入流
                    //		URLConnection conn = url.openConnection();
                    //		InputStream is = conn.getInputStream();
		            InputStream is = url.openStream();
    
		            //OutputStream os = new FileOutputStream("D:\\information.txt");
		
		            byte[] buffer = new byte[2048];
		
		            int length = 0;
		
		             while(-1!=(length = is.read(buffer,0,buffer.length))){
			            String strRead = new String(buffer);
			
			            System.out.println(strRead);
			
			            Pattern pattern1 = Pattern.compile("name\":\"([\\u4E00-\\u9FA5]+)\",");
			            Pattern pattern2 = Pattern.compile("text\":\"([\\u4E00-\\u9FA5]+)\",");
			            Pattern pattern3 = Pattern.compile("temp\":([0-9]+.[0-9]+)");
			
		    	        Matcher m = pattern1.matcher(strRead);
	              		if (m.find()) {
	          		    System.out.println(m.group(1));
	    		        }
			
			            m = pattern2.matcher(strRead);
			            if (m.find()) {
				            System.out.println(m.group(1));
				        }
			
			            m = pattern3.matcher(strRead);
			            if (m.find()) {
			            	System.out.println(m.group(1));
			            }
		
			
		            }		
		            is.close();
                }
            }
        });

    }


}
