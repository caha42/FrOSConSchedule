package de.froscon.schedule;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class FrosconSchedule extends Activity {

	private WebView myWebView;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://programm.froscon.org/mobil/");
        
        //webview being your WebView object reference. 
        myWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY); 
        
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Check if the key event was the BACK key and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the BACK key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_options_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.refresh:
        	this.onCreate(null);
            return true;
        case R.id.info:
        	Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.froscon.org"));
        	startActivity(viewIntent); 
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}