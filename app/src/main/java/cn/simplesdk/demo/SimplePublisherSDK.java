package cn.simplesdk.demo;

import java.io.Serializable;

///javah.exe -classpath d:\\SDK\platforms\android-26\android.jar;.  -jni c om.publisher.ffmpeg.SimplePublisherSDK
public class SimplePublisherSDK implements Serializable {
    static {
        System.loadLibrary("avutil");
        System.loadLibrary("swresample");
        System.loadLibrary("avcodec");
        System.loadLibrary("avformat");
        System.loadLibrary("avutil");
        System.loadLibrary("postproc");
        System.loadLibrary("avutil");
        System.loadLibrary("swscale");
        System.loadLibrary("avfilter");
        System.loadLibrary("avdevice");
        System.loadLibrary("avutil");
        System.loadLibrary("swresample");
        System.loadLibrary("avcodec");
        System.loadLibrary("rtmp");
        System.loadLibrary("SimplePublisherSDK");
    }

    public native long SimplePublisherSDKgen();

    private native void SimplePublisherSDKfree(long ptr_);

    private native void setFrameRate(long ptr_, int rate);

    private native void setResolvtion(long ptr_, int width, int height);

    private native void setEncodeRate(long ptr_, int rate);

    //0：X264, 1:X265
    private native void setEncodeType(long ptr_, int type);

    private native void setHardwareEncode(long ptr_, boolean flag);

    private native void setRtmpUrl(long ptr_, String url);

    private native void startPush(long ptr_);

    private native void stopPush(long ptr_);

    private native void setIsPushing(long ptr_, boolean isPushing);

    private native boolean getIsPushing(long ptr_);

    private native String getUrl(long ptr_);

    //0:RTSP, 1:RTMP
    private native void setPushType(long ptr_, int type);

    private native void rtspDataPush(long ptr_, byte[] data);
    private native void rtmpDataPush(long ptr_, byte[] data);

    public  native int sendSpsAndPps(long ptr_,byte[] sps, int spsLen, byte[] pps, int ppsLen);

    public  native int sendVideoFrame(long ptr_,byte[] frame, int len, int timestamp);

    public  native int sendAacSpec(long ptr_,byte[] data, int len);

    public  native int sendAacData(long ptr_,byte[] data, int len, int timestamp);


    private native void setRtspPort(long ptr_, int port);
    private native void setRtspStream(long ptr_, String stream);
    private native int getPushType(long ptr_);

    //
    private native void audioDataPush(long ptr_, byte[] data);
    //0：前置摄像头 1：后置摄像头 2：屏幕
    private native void setCaptureType(long ptr_, int capturetype);


    //YUV转换操作
    private native  void Yv12ToI420(long ptr_,byte[] pYv12, byte[] pI420, int width, int height);
    private native  void I420ToYv12(long ptr_,byte[] pI420, byte[] pYv12, int width, int height);
    private native  void Nv21ToI420(long ptr_,byte[] pNv21,byte[] pI420,int width,int height);
    private native  void I420ToNv21(long ptr_,byte[] pI420,byte[] pNv21,int width,int height);
    private native  void Nv21ToYV12(long ptr_,byte[] pNv21,byte[] pYv12,int width,int height);
    private native  void YV12ToNv21(long ptr_,byte[] pYv12,byte[] pNv21,int width,int height);
    private native  void Nv21ToNv12(long ptr_,byte[] pNv21,byte[] pNv12,int width,int height);
    private native  void Nv12ToNv21(long ptr_,byte[] pNv12,byte[] pNv21,int width,int height);
    private native  void cutCommonYuv(long ptr_,int yuvType, int startX,int startY,byte[] srcYuv, int srcW,int srcH,byte[] tarYuv,int cutW, int cutH);
    private native  void getSpecYuvBuffer(long ptr_,int yuvType,byte[] dstBuf, byte[] srcYuv, int srcW, int srcH,int dirty_Y,int dirty_UV);
    private native  void yuvAddWaterMark(long ptr_,int yuvType, int startX, int startY, byte[] waterMarkData,
                                              int waterMarkW, int waterMarkH,byte[] yuvData, int yuvW, int yuvH);

    private native  void Nv21ClockWiseRotate90(long ptr_,byte[] pNv21,int srcWidth,int srcHeight,byte[] outData,int[] outWidth,int[] outHeight);
    private native  void Nv12ClockWiseRotate90(long ptr_,byte[] pNv12,int srcWidth,int srcHeight,byte[] outData,int[] outWidth,int[] outHeight);
    private native  void Nv21ClockWiseRotate180(long ptr_,byte[] pNv21,int srcWidth,int srcHeight,byte[] outData,int[] outWidth,int[] outHeight);
    private native  void Nv21ClockWiseRotate270(long ptr_,byte[] pNv21,int srcWidth,int srcHeight,byte[] outData,int[] outWidth,int[] outHeight);

    //I420(YUV420P)图像顺时针旋转90度
    private  native void I420ClockWiseRotate90(long ptr_,byte[] pI420, int srcWidth,int srcHeight,byte[] outData, int[] outWidth,int[] outHeight);
    //YV12图像顺时针旋转90度
    private  native void Yv12ClockWiseRotate90(long ptr_,byte[] pYv12, int srcWidth,int srcHeight,byte[] outData, int[] outWidth,int[] outHeight);

    public  void Yv12ToI420(byte[] pYv12, byte[] pI420, int width, int height)
    {
        Yv12ToI420(m_publisher, pYv12, pI420, width, height);
    }
    public   void I420ToYv12(byte[] pI420, byte[] pYv12, int width, int height)
    {
        I420ToYv12(m_publisher, pI420, pYv12, width, height);
    }
    public   void Nv21ToI420(byte[] pNv21,byte[] pI420,int width,int height)
    {
        Nv21ToI420(m_publisher, pNv21, pI420, width, height);
    }
    public   void I420ToNv21(byte[] pI420,byte[] pNv21,int width,int height)
    {
        I420ToNv21(m_publisher, pI420, pNv21, width, height);
    }
    public   void Nv21ToYV12(byte[] pNv21,byte[] pYv12,int width,int height)
    {
        Nv21ToYV12(m_publisher, pNv21, pYv12, width, height);
    }
    public   void YV12ToNv21(byte[] pYv12,byte[] pNv21,int width,int height)
    {
        YV12ToNv21(m_publisher, pYv12, pNv21, width, height);
    }
    public   void Nv21ToNv12(byte[] pNv21,byte[] pNv12,int width,int height)
    {
        Nv21ToNv12(m_publisher, pNv21, pNv12, width, height);
    }
    public   void Nv12ToNv21(byte[] pNv12,byte[] pNv21,int width,int height)
    {
        Nv12ToNv21(m_publisher, pNv12, pNv21, width, height);
    }
    public   void cutCommonYuv(int yuvType, int startX,int startY,byte[] srcYuv, int srcW,int srcH,byte[] tarYuv,int cutW, int cutH)
    {
        cutCommonYuv(m_publisher, yuvType, startX, startY, srcYuv, srcW, srcH, tarYuv, cutW, cutH);
    }
    public   void getSpecYuvBuffer(int yuvType,byte[] dstBuf, byte[] srcYuv, int srcW, int srcH,int dirty_Y,int dirty_UV)
    {
        getSpecYuvBuffer(m_publisher, yuvType, dstBuf, srcYuv, srcW, srcH, dirty_Y, dirty_UV);
    }
    public   void yuvAddWaterMark(int yuvType, int startX, int startY, byte[] waterMarkData,
                                         int waterMarkW, int waterMarkH,byte[] yuvData, int yuvW, int yuvH)
    {
        yuvAddWaterMark(m_publisher, yuvType, startX, startY, waterMarkData, waterMarkW, waterMarkH, yuvData, yuvW, yuvH);
    }

    public   void Nv21ClockWiseRotate90(byte[] pNv21,int srcWidth,int srcHeight,byte[] outData,int[] outWidth,int[] outHeight)
    {
        Nv21ClockWiseRotate90(m_publisher, pNv21, srcWidth, srcHeight, outData, outWidth, outHeight);
    }
    public   void Nv12ClockWiseRotate90(byte[] pNv12,int srcWidth,int srcHeight,byte[] outData,int[] outWidth,int[] outHeight)
    {
        Nv12ClockWiseRotate90(m_publisher, pNv12, srcWidth, srcHeight, outData, outWidth, outHeight);
    }
    public   void Nv21ClockWiseRotate180(byte[] pNv21,int srcWidth,int srcHeight,byte[] outData,int[] outWidth,int[] outHeight)
    {
        Nv21ClockWiseRotate180(m_publisher, pNv21, srcWidth, srcHeight, outData, outWidth, outHeight);
    }
    public   void Nv21ClockWiseRotate270(byte[] pNv21,int srcWidth,int srcHeight,byte[] outData,int[] outWidth,int[] outHeight)
    {
        Nv21ClockWiseRotate270(m_publisher, pNv21, srcWidth, srcHeight, outData, outWidth, outHeight);
    }

    //I420(YUV420P)图像顺时针旋转90度
    public   void I420ClockWiseRotate90(byte[] pI420, int srcWidth,int srcHeight,byte[] outData, int[] outWidth,int[] outHeight)
    {
        I420ClockWiseRotate90(m_publisher, pI420, srcWidth, srcHeight, outData, outWidth, outHeight);
    }
    //YV12图像顺时针旋转90度
    public   void Yv12ClockWiseRotate90(byte[] pYv12, int srcWidth,int srcHeight,byte[] outData, int[] outWidth,int[] outHeight)
    {
        Yv12ClockWiseRotate90(m_publisher, pYv12, srcWidth, srcHeight, outData, outWidth, outHeight);
    }
    private long m_publisher;
    private int timeoffset;


    public SimplePublisherSDK() {
        m_publisher = SimplePublisherSDKgen();
    }

    public  void rtspDataPush(byte[] data)
    {
        rtspDataPush(m_publisher, data);
    }
    public void audioDataPush(byte[] data)
    {
        audioDataPush(m_publisher, data);
    }

    public void rtmpDataPush(byte[] data)
    {
        rtmpDataPush(m_publisher, data);
    }

    public void sendSpsAndPps(byte[] sps, int spsLen, byte[] pps, int ppsLen, int timeoffset)
    {
        if(m_publisher != 0) {
            this.timeoffset = timeoffset;
            sendSpsAndPps(m_publisher, sps, spsLen, pps, ppsLen);
        }
    }

    public void sendVideoFrame(byte[] frame, int len, int timestamp){
        if(timestamp - this.timeoffset < 0)
            return;
        if(m_publisher != 0)
            sendVideoFrame(m_publisher, frame, len, timestamp);
    }

    public void sendAacSpec(byte[] data, int len)
    {
        if(m_publisher != 0)
            sendAacSpec(m_publisher, data, len);
    }

    public void sendAacData(byte[] data, int len, int timestamp)
    {
        if(timestamp - timeoffset < 0)
            return;
        if(m_publisher != 0)
            sendAacData(m_publisher, data, len, timestamp);
    }

    public void setRtspPort(int port)
    {
        setRtspPort(m_publisher, port);
    }
    public void setRtspStream(String stream)
    {
        setRtspStream(m_publisher, stream);
    }

    public int getPushType()
    {
        return getPushType(m_publisher);
    }

    public void setPushType(int type) {
        setPushType(m_publisher, type);
    }

    public String getUrl() {
        return getUrl(m_publisher);
    }

    public boolean getIsPushing() {
        return getIsPushing(m_publisher);
    }

    public void setIsPushing(boolean flag) {
        setIsPushing(m_publisher, flag);
    }

    public void startPush() {
        startPush(m_publisher);
    }

    public void stopPush() {
        stopPush(m_publisher);
    }

    public void setRtmpUrl(String url) {
        setRtmpUrl(m_publisher, url);
    }

    public void setHardwareEncode(boolean flag) {
        setHardwareEncode(m_publisher, flag);
    }

    public void setEncodeType(int type) {
        setEncodeType(m_publisher, type);
    }

    public void SimplePublisherSDKfree() {
        SimplePublisherSDKfree(m_publisher);
    }

    public void setFrameRate(int rate) {
        setFrameRate(m_publisher, rate);
    }

    public void setResolvtion(int width, int height)
    {
        setResolvtion(m_publisher, width, height);
    }
    public  void setEncodeRate(int rate)
    {
        setEncodeRate(m_publisher, rate);
    }
    public void setCaptureType(int captureType){
        setCaptureType(m_publisher, captureType);
    }


}
