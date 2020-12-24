package cn.simplesdk.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import cn.video94.mediasdk.SDKMediaPublisher;
import cn.video94.mediasdk.SDKSurfacePreview;
import cn.video94.mediasdk.SDKVideoGather;
import cn.video94.mediasdk.VideoGather;

public class CameraShareActivity extends AppCompatActivity implements View.OnClickListener, SDKVideoGather.CameraOperateCallback, SDKMediaPublisher.ConnectRtmpServerCb, SDKSurfacePreview.PermissionNotify, SDKMediaPublisher.SDKListener {

@Override
public void audioDataPush(byte[] audiodata) {
        publisherSDK.audioDataPush(audiodata);
        }

@Override
public void videodatapush(byte[] data) {
        publisherSDK.rtspDataPush(data);
        }

@Override
public void Nv21ToNv12(byte[] input, byte[] yuvBuffer, int mWidth, int mHeight) {
        publisherSDK.Nv21ToNv12(input, yuvBuffer, mWidth, mHeight);
        }

@Override
public void Nv12ClockWiseRotate90(byte[] yuvBuffer, int mWidth, int mHeight, byte[] rotateYuvBuffer, int[] outWidth, int[] outHeight) {
        publisherSDK.Nv12ClockWiseRotate90(yuvBuffer, mWidth, mHeight, rotateYuvBuffer, outWidth, outHeight);
        }

@Override
public void Nv21ToI420(byte[] input, byte[] yuvBuffer, int mWidth, int mHeight) {
        publisherSDK.Nv21ToI420(input, yuvBuffer, mWidth, mHeight);
        }

@Override
public void I420ClockWiseRotate90(byte[] yuvBuffer, int mWidth, int mHeight, byte[] rotateYuvBuffer, int[] outWidth, int[] outHeight) {
        publisherSDK.I420ClockWiseRotate90(yuvBuffer, mWidth, mHeight, rotateYuvBuffer, outWidth, outHeight);
        }

@Override
public void Nv21ClockWiseRotate90(byte[] yuvBuffer, int mWidth, int mHeight, byte[] rotateYuvBuffer, int[] outWidth, int[] outHeight) {
        publisherSDK.Nv21ClockWiseRotate90(yuvBuffer, mWidth, mHeight, rotateYuvBuffer, outWidth, outHeight);
        }

@Override
public void Nv21ToYV12(byte[] input, byte[] yuvBuffer, int mWidth, int mHeight) {
        publisherSDK.Nv21ToYV12(input, yuvBuffer, mWidth, mHeight);
        }

@Override
public void Yv12ClockWiseRotate90(byte[] yuvBuffer, int mWidth, int mHeight, byte[] rotateYuvBuffer, int[] outWidth, int[] outHeight) {
        publisherSDK.Yv12ClockWiseRotate90(yuvBuffer, mWidth, mHeight, rotateYuvBuffer, outWidth, outHeight);
        }

private final static String TAG = "MainActivity";

private Button btnStart;
private SurfaceView mSurfaceView;
private SurfaceHolder mSurfaceHolder;
private SDKSurfacePreview mSurfacePreview;

//多次关闭开启
private int weight;
private int height;
private int fps;
        boolean flag = true;//多次开启关闭，false时需要重新初始化音视频编码器

private SDKMediaPublisher mediaPublisher;
private boolean isStarted;
private boolean isRtmpConnected = false;
private static final String rtmpUrl = "rtmp://192.168.0.103:1935//live/livestream";
private boolean hasPermission;
private static final int TARGET_PERMISSION_REQUEST = 100;
private SimplePublisherSDK publisherSDK;
// 要申请的权限
private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO};

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_camera_share);
        publisherSDK = new SimplePublisherSDK();
        publisherSDK.setCaptureType(0);
        //publisherSDK.setRtmpUrl(url);
        publisherSDK.setPushType(0);
        //publisherSDK.setResolvtion(mCamera.getParameters().getPreviewSize().height, mCamera.getParameters().getPreviewSize().width);
        publisherSDK.startPush();
        isStarted = false;
        hasPermission = false;
        btnStart = (Button) findViewById(R.id.btn_start);
        mSurfaceView = (SurfaceView) findViewById(R.id.surface_view);
        mSurfaceView.setKeepScreenOn(true);
        // 获得SurfaceView的SurfaceHolder
        mSurfaceHolder = mSurfaceView.getHolder();
        // 设置surface不需要自己的维护缓存区
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        // 为srfaceHolder添加一个回调监听器
        mSurfacePreview = new SDKSurfacePreview(this,this);
        mSurfaceHolder.addCallback(mSurfacePreview);
        btnStart.setOnClickListener(this);

        String logPath = Environment
        .getExternalStorageDirectory()
        + "/" + "zhongjihao/rtmp.log";
        mediaPublisher = SDKMediaPublisher.newInstance(rtmpUrl,logPath);
        mediaPublisher.setRtmpConnectCb(this);
        mediaPublisher.setSDDLister(this);
        mediaPublisher.initMediaPublish();

        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        // 检查该权限是否已经获取
        for (int i = 0; i < permissions.length; i++) {
        int result = ContextCompat.checkSelfPermission(this, permissions[i]);
        // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
        if (result != PackageManager.PERMISSION_GRANTED) {
        hasPermission = false;
        break;
        } else
        hasPermission = true;
        }
        if(!hasPermission){
        // 如果没有授予权限，就去提示用户请求
        ActivityCompat.requestPermissions(this,
        permissions, TARGET_PERMISSION_REQUEST);
        }
        }
        //YuvEngineWrap.newInstance().startYuvEngine();
        }

private void codecToggle() {
        if (isStarted) {
        isStarted = false;
        if(isRtmpConnected){
        //停止编码 先要停止编码，然后停止采集
        mediaPublisher.stopEncoder();
        //停止音频采集
        mediaPublisher.stopAudioGather();
        //断开RTMP连接
        mediaPublisher.stopRtmpPublish();
        }
        } else {
        isStarted = true;
        if(flag)
        //连接Rtmp流媒体服务器
        {
        mediaPublisher.startRtmpPublish();
        flag = false;
        } else
        {
        //多次开启关闭，false时需要重新初始化音视频编码器
        mediaPublisher.startAudioGather();
        //初始化音频编码器
        mediaPublisher.initAudioEncoder();

        mediaPublisher.initVideoEncoder(weight, height, fps);//第一次preview时，获得宽高和fps
        //启动编码
        mediaPublisher.startEncoder();
        //mediaPublisher.startRtmpPublish();

        }
        }
        btnStart.setText(isStarted ? "停止" : "开始");
        }

@Override
protected void onDestroy() {
        super.onDestroy();
        if(isStarted){
        isStarted = false;
        if(isRtmpConnected){
        //停止编码 先要停止编码，然后停止采集
        mediaPublisher.stopEncoder();
        //停止音频采集
        mediaPublisher.stopAudioGather();
        //断开RTMP连接
        mediaPublisher.stopRtmpPublish();
        }
        }
        //释放编码器
        if(mediaPublisher != null)
        mediaPublisher.release();
        mediaPublisher = null;
        VideoGather.getInstance().doStopCamera();
        //YuvEngineWrap.newInstance().stopYuvEngine();
        }

@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
        finish();
        return true;
        } else {
        return super.onKeyDown(keyCode, event);
        }
        }

@Override
public void onClick(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.btn_start:
        codecToggle();
        break;
        }
        }

@Override
public void cameraHasOpened() {
        SDKVideoGather.getInstance().doStartPreview(this, mSurfaceHolder);
        }

@Override
public void cameraHasPreview(int width,int height,int fps) {
        //初始化视频编码器
        mediaPublisher.initVideoEncoder(width,height,fps);
        this.weight = width;
        this.height = height;
        this.fps = fps;
        }

@Override
public void onConnectRtmp(final int ret) {
        isRtmpConnected = ret == 0 ? false : true;
        if(ret != 0){
        //采集音频
        mediaPublisher.startAudioGather();
        //初始化音频编码器
        mediaPublisher.initAudioEncoder();
        //启动编码
        mediaPublisher.startEncoder();
        }
        runOnUiThread(new Runnable(){
@Override
public void run() {
        if(ret == 0){
        Log.e(TAG, "===zhongjihao=====Rtmp连接失败====");
        //更新UI
        Toast.makeText(CameraShareActivity.this,"RTMP流媒体服务器连接失败,请检测网络或服务器是否启动!",Toast.LENGTH_LONG).show();
        }
        }
        });
        }

@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        && (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
        && (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
        if(requestCode == TARGET_PERMISSION_REQUEST){
        btnStart.setEnabled(true);
        hasPermission = true;
        // 打开摄像头
        SDKVideoGather.getInstance().doOpenCamera(this);
        }
        }else{
        btnStart.setEnabled(false);
        hasPermission = false;
        Toast.makeText(this, getText(R.string.no_permission_tips), Toast.LENGTH_SHORT)
        .show();
        }
        }

@Override
public boolean hasPermission(){
        return  hasPermission;
        }
}
