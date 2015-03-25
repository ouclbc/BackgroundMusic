package com.example.backgroundaudiodemo;
import java.io.IOException;
import java.util.List;

import android.app.Service;  
import android.content.Intent;  
import android.media.MediaPlayer;  
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Binder;  
import android.os.IBinder;  
import android.util.Log;
import android.widget.MediaController.MediaPlayerControl;  
/** 
 * Ϊ�˿���ʹ���ں�̨�������֣�������ҪService 
 * Service���������ں�̨���һЩ����Ҫ���û������Ķ��� 
 * @author Administrator 
 * 
 */  
public class AudioService extends Service implements MediaPlayer.OnCompletionListener{  
      
    private static final String TAG = AudioService.class.getSimpleName();
	MediaPlayer mPlayer;  
      
    private final IBinder binder = new AudioBinder();  
    @Override  
    public IBinder onBind(Intent arg0) {  
        // TODO Auto-generated method stub  
        return binder;  
    }  
    /** 
     * ��Audio�������ʱ�򴥷��ö��� 
     */  
    @Override  
    public void onCompletion(MediaPlayer player) {  
        // TODO Auto-generated method stub  
        stopSelf();//�����ˣ������Service  
    }  
      
    //������������Ҫʵ����MediaPlayer����  
    public void onCreate(){  
        super.onCreate();
        Log.d(TAG, "onCreate");
        //���Ǵ�raw�ļ����л�ȡһ��Ӧ���Դ���mp3�ļ�  
        mPlayer = MediaPlayer.create(this, R.raw.cunzai);
        mPlayer.setOnCompletionListener(this);
        mPlayer.setOnErrorListener(new OnErrorListener(){

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				Log.d(TAG, "Audio error: what = " + what + " extra="
						+ extra);
				return false;
			}
        	
        });
    }  
    
    public void start(Uri uri){
    	try {
			mPlayer.setDataSource(uri.toString());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	mPlayer.start();
    }
    public void stop(){
    	if(mPlayer.isPlaying()){  
            mPlayer.stop();  
        }  
        mPlayer.release();
    }
    /** 
     * �÷�����SDK2.0�ſ�ʼ�еģ����ԭ����onStart���� 
     */  
    public int onStartCommand(Intent intent, int flags, int startId){
    	List<Music> resultList = (List<Music>)intent.getSerializableExtra("musiclist");
    	if(resultList!=null){
    		for (Music music:resultList){
        		Log.d(TAG, music.getMusicName());
        	}
    	}
        //Uri musicuri = Uri.parse(resultList.get(mIndex).toString());
        if(!mPlayer.isPlaying()){  
            mPlayer.start();  
        }  
        return START_STICKY;  
    }  
      
    public void onDestroy(){  
        //super.onDestroy();
    	Log.d(TAG, "onDestroy");
        if(mPlayer.isPlaying()){  
            mPlayer.stop();  
        }  
        mPlayer.release();  
    }  
      
    //Ϊ�˺�Activity������������Ҫ����һ��Binder����  
    class AudioBinder extends Binder{  
          
        //����Service����  
        AudioService getService(){  
            return AudioService.this;  
        }  
    }  
      
    //���˲��Ž���  
    public void haveFun(){  
        if(mPlayer.isPlaying() && mPlayer.getCurrentPosition()>2500){  
            mPlayer.seekTo(mPlayer.getCurrentPosition()-2500);  
        }  
    }  
}  