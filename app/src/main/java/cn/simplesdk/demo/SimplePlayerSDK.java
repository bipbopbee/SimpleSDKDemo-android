package cn.simplesdk.demo;

import android.view.Surface;

public class SimplePlayerSDK {
    static {
        System.loadLibrary("SimplePlayerSDK");
    }
    private long m_playerPtr;

    private native long SimplePlayerSDKgen();
    private native void SimplePlayerSDKfree(long ptr);
    private native void setPlayUrl(long ptr_, String url);
    private native void setPlayWnd(long ptr_, Surface wnd);
    private native void setIsPlaying(long ptr_, boolean playing);
    private native String getPlayUrl(long ptr_);
    //HWND getPlayWnd();
    private native boolean getIsPlaying(long ptr_);
    private native void play(long ptr_);
    private native void stop(long ptr_);

    public SimplePlayerSDK()
    {
        m_playerPtr = SimplePlayerSDKgen();
    }
    public  void SimplePlayerSDKFree()
    {
        SimplePlayerSDKfree(m_playerPtr);
    }
    public void setPlayUrl(String url)
    {
        setPlayUrl(m_playerPtr, url);
    }
    public void setPlayWnd(Surface wnd)
    {
        setPlayWnd(m_playerPtr, wnd);
    }
    public  void setIsPlaying(boolean isPlaying)
    {
        setIsPlaying(m_playerPtr, isPlaying);
    }

    public String getPlayUrl()
    {
        return getPlayUrl(m_playerPtr);
    }

    public  boolean getIsPlaying()
    {
        return getIsPlaying(m_playerPtr);
    }

    public void play()
    {
        play(m_playerPtr);
    }
    public void stop()
    {
        stop(m_playerPtr);
    }

}
