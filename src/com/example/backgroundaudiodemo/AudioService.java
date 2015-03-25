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
 * 为了可以使得在后台播放音乐，我们需要Service 
 * Service就是用来在后台完成一些不需要和用户交互的动作 
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
     * 当Audio播放完的时候触发该动作 
     */  
    @Override  
    public void onCompletion(MediaPlayer player) {  
        // TODO Auto-generated method stub  
        stopSelf();//结束了，则结束Service  
    }  
      
    //在这里我们需要实例化MediaPlayer对象  
    public void onCreate(){  
        super.onCreate();
        Log.d(TAG, "onCreate");
        //我们从raw文件夹中获取一个应用自带的mp3文件  
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
     * 该方法在SDK2.0才开始有的，替代原来的onStart方法 
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
      
    //为了和Activity交互，我们需要定义一个Binder对象  
    class AudioBinder extends Binder{  
          
        //返回Service对象  
        AudioService getService(){  
            return AudioService.this;  
        }  
    }  
      
    //后退播放进度  
    public void haveFun(){  
        if(mPlayer.isPlaying() && mPlayer.getCurrentPosition()>2500){  
            mPlayer.seekTo(mPlayer.getCurrentPosition()-2500);  
        }  
    }  
}  