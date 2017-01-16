package pl.akademiakodu.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebClient());
//        webView.setWebViewClient(new WebViewClient());
//        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(this, "Android"); //to przed załadowaniem strony internetowej
//        webView.loadUrl("http://javastart.pl");
        webView.loadUrl("http://projekt.techloft.pl/akademiakodu");
//        webView.zoomIn();//przybliżenie zawartości strony
//        webView.goBack();//powracanie do poprzedniej strony (historia)
//        webView.goForward();//przejście do następnej strony z historii
//        webView.canGoForward();//prawda/fałsz czy następna strona w historii jest dostępna
//        webView.canGoBack();//prawda/fałsz czy historia jest dostępna

    }

    @JavascriptInterface
    public void showToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void changeText(String text){
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        v.vibrate(2000);
    }

    private class MyWebClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(!url.equals("http://google.pl")){
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://facebook.pl"));
                startActivity(i);
                return true;
            }
            return false;
        }
    }

}
