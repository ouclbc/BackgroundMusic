
package com.example.backgroundaudiodemo;

import java.io.Serializable;

public class Music implements Serializable {

    private static final long serialVersionUID = 1L;

    private int mPlayOrder;

    private int mMusicId;

    private String mMusicName;

    /**
     * CDN url
     */
    private String mMusicUrl;

    /**
     * file size, unit is byte
     */
    private int mMusicFileSize;

    private int mOffset = 0;

    /**
     * play time, unit is ms
     */
    private long mDuration;

    /**
     * MD5 HASH
     */
    private String mChecksum;

    public int getPlayOrder() {
        return mPlayOrder;
    }

    public void setPlayOrder(int playOrder) {
        mPlayOrder = playOrder;
    }

    public int getMusicId() {
        return mMusicId;
    }

    public void setMusicId(int musicId) {
        mMusicId = musicId;
    }

    public String getMusicName() {
        return mMusicName;
    }

    public void setMusicName(String musicName) {
        mMusicName = musicName;
    }

    public String getMusicUrl() {
        return mMusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        mMusicUrl = musicUrl;
    }

    public int getMusicFileSize() {
        return mMusicFileSize;
    }

    public void setMusicFileSize(int musicFileSize) {
        mMusicFileSize = musicFileSize;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public String getChecksum() {
        return mChecksum;
    }

    public void setChecksum(String checksum) {
        mChecksum = checksum;
    }

    public Music(int playOrder, int musicID, String musicName, String musicUrl, int fileSzie,
            int offset, long duration, String checkSum) {
        mPlayOrder = playOrder;
        mMusicId = musicID;
        mMusicName = musicName;
        mMusicUrl = musicUrl;
        mMusicFileSize = fileSzie;
        mOffset = offset;
        mDuration = duration;
        mChecksum = checkSum;
    }

    public Music() {
    }
}
