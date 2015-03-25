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
    
    //ʹ��ServiceConnection������Service״̬�ı仯  
    private ServiceConnection conn = new ServiceConnection() {  
          
        @Override  
        public void onServiceDisconnected(ComponentName name) {  
            // TODO Auto-generated method stub  
            audioService = null;  
        }  
          
        @Override  
        public void onServiceConnected(ComponentName name, IBinder binder) {  
            //��������ʵ����audioService,ͨ��binder��ʵ��  
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
            //����Service��Ȼ��󶨸�Service���������ǿ�����ͬʱ���ٸ�Activity�����������Ƿ��ڲ���  
            startService(intent);  
            //bindService(intent, conn, Context.BIND_AUTO_CREATE);
            //finish();
        }else if(id == R.id.btn_end){  
            //����Service  
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
