package cn.simplesdk.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import java.io.Serializable;

import cn.video94.mediasdk.ScreenService;

public class ScreenShareActivity extends AppCompatActivity implements ScreenService.SDKListener, Serializable {
    public static final int PERMISSION_CODE = 1;
    private static final int VIDEO_WIDTH = 480;
    private static final int VIDEO_HEIGHT = 640;
    private SimplePublisherSDK publisherSDK;
    boolean screenflag = true;
    @Override
    public void audioDataPush(byte[] audiodata) {
        publisherSDK.audioDataPush(audiodata);
    }

    @Override
    public void sendAacSpec(byte[] audiodata, int len) {
        publisherSDK.sendAacSpec(audiodata, len);
    }

    @Override
    public void sendAacData(byte[] audiodata, int len, int timestamp) {
        publisherSDK.sendAacData(audiodata, len, timestamp);
    }

    @Override
    public void rtspDataPush(byte[] frame) {
        publisherSDK.rtspDataPush(frame);
    }

    @Override
    public void rtmpDataPush(byte[] frame) {
        publisherSDK.rtmpDataPush(frame);
    }

    @Override
    public void sendSpsAndPps(byte[] sps, int spslen, byte[] pps, int ppslen, int timestamp) {
        publisherSDK.sendSpsAndPps(sps, spslen, pps, ppslen, timestamp);
    }

    @Override
    public void sendVideoFrame(byte[] nalu, int len, int nTimeStamp) {
        publisherSDK.sendVideoFrame(nalu, len, nTimeStamp);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_share);
        final Button btnScrren = (Button)findViewById(R.id.screen);
        publisherSDK = new SimplePublisherSDK();

        btnScrren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(screenflag) {
                    publisherSDK.setPushType(0);
                    publisherSDK.setResolvtion(VIDEO_WIDTH, VIDEO_HEIGHT);
                    publisherSDK.startPush();
                    startRtspService();
                    btnScrren.setText("暂停");
                }else
                {
                    stopRtspService();
                    btnScrren.setText("开始");
                }
                screenflag = !screenflag;
            }
        });
    }

    private void startRtspService() {
        MediaProjectionManager manager =
                (MediaProjectionManager) this.getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        Intent intent = manager.createScreenCaptureIntent();
        startActivityForResult(intent, PERMISSION_CODE);
    }

    private void stopRtspService()
    {
        Intent serviceIntent = new Intent(this, cn.video94.mediasdk.ScreenService.class);
        stopService(serviceIntent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        handleRecordScreenRequest(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleRecordScreenRequest(int requestCode, int resultCode, Intent data) {
        if (requestCode != PERMISSION_CODE) return;
        if (resultCode != Activity.RESULT_OK) return;

        // start background service
        Intent serviceIntent = new Intent(this, ScreenService.class);
        serviceIntent.putExtra("resultCode", resultCode);
        serviceIntent.putExtra("data", data);
        Bundle bundle = new Bundle();
        bundle.putSerializable("SDKListener", this);
        serviceIntent.putExtra("sdk", bundle);
        serviceIntent.putExtra("isRtsp", true);
        startService(serviceIntent);
    }

}
