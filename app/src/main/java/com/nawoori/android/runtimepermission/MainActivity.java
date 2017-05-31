package com.nawoori.android.runtimepermission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    WebView WebView;
    Button previos, next, go;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView = (WebView) findViewById(R.id.WebView);
        previos = (Button) findViewById(R.id.previos);
        next = (Button) findViewById(R.id.next);
        go = (Button) findViewById(R.id.go);

        previos.setOnClickListener(this);
        next.setOnClickListener(this);
        go.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.url);

        //-------기본적인 WebView 세팅 ------------------------
        //script 사용설정 (필수) - 페이지내의 javascript가 동작하도록 한다
        WebView.getSettings().setJavaScriptEnabled(true);
        //1.웹뷰 클라이언트를 지정...(안하면 내장 웹브라우저가 팝업된다.)
        WebView.setWebViewClient(new WebViewClient());
        //2.둘다 세팅할 것 : 프로토콜에 따라 클라이언트가 선택되는 것으로 파악됨...
        WebView.setWebViewClient(new WebViewClient());
        //*클라이언트가 지정되지 않으면 내장 웹앱이 실행된다.
        //----------------------------------------------------------

        loadUrl("naver.com");
    }
        private void loadUrl(String url){
        //문자열의 앞에 프로토콜인 http 문자열이 없다면 붙혀준다
        if(!url.startsWith("http")){
            url = "http://" + url;
        }
        //url호출하기
        WebView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.previos : // 뒤로가기
                if(WebView.canGoBack()) {
                    WebView.goBack();
                }
                break;
            case R.id.next : //앞으로가기
                if(WebView.canGoForward()){
                    WebView.goForward();
                }
                break;
            case R.id.go : //url이동
                String url = editText.getText().toString();
                if(!"".equals(url)) { // 공백이 아닐경우 처리
                    //문자열이 url 패턴일때만
                    if (url.matches("^(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$")) {
                        loadUrl(url);
                    }else{
                        Toast.makeText(this, "url이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }
}
