package com.example.backgroundaudiodemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class BackgroundAudioDemo extends Activity{

	private AudioService audioService;
	List<Music> musiclist = new ArrayList<Music>();
	Music music1 = new Music(1,2,"/mnt/sdcard/cunzai", "http://mp3.baidu.com/cunzai", 0, 0, 0, null);
	Music music2 = new Music(1,2,"/mnt/sdcard/lanlianhua", "http://mp3.baidu.com/lanlianhua", 0, 0, 0, null);
    
    //使用ServiceConnection来监听Service状态的变化  
    private ServiceConnection conn = new ServiceConnection() {  
          
        @Override  
        public void onServiceDisconnected(ComponentName name) {  
            // TODO Auto-generated method stub  
            audioService = null;  
        }  
          
        @Override  
        public void onServiceConnected(ComponentName name, IBinder binder) {  
            //这里我们实例化audioService,通过binder来实现  
            audioService = ((AudioService.AudioBinder)binder).getService();  
              
        }  
    };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_background_audio_demo);
		musiclist.add(music1);
		musiclist.add(music2);
	}
	
	public void onButtonStartClick(View v){
        int id = v.getId();  
        Intent intent = new Intent(); 
        Bundle bundle = new Bundle();
        bundle.putSerializable("musiclist", (Serializable) musiclist);
        intent.putExtras(bundle);
        intent.setClass(this, AudioService.class);        
        if(id == R.id.btn_start){
            //启动Service，然后绑定该Service，这样我们可以在同时销毁该Activity，看看歌曲是否还在播放  
            startService(intent);  
            //bindService(intent, conn, Context.BIND_AUTO_CREATE);
            //finish();
        }else if(id == R.id.btn_end){  
            //结束Service  
            //unbindService(conn);  
            stopService(intent); 
        }else if(id == R.id.btn_fun){  
            audioService.haveFun();  
        }  
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.background_audio_demo, menu);
		return true;
	}

	
}
