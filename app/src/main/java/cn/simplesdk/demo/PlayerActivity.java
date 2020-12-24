package cn.simplesdk.demo;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.libsdl.app.SDL;
import org.libsdl.app.SDLActivity;

public class PlayerActivity extends SDLActivity implements SurfaceHolder.Callback {
    private SimplePlayerSDK m_plaerSDK;
    private SurfaceView m_surfaceView;
    private SurfaceHolder m_holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        m_plaerSDK = new SimplePlayerSDK();
        Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        Button bntPlay = findViewById(R.id.simplayersdk);
        bntPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                        m_plaerSDK.setPlayUrl("rtmp://58.200.131.2:1935/livetv/cctv1");
                        m_plaerSDK.setPlayWnd(m_holder.getSurface());
                        //showWH();
                        int w = m_surfaceView.getWidth();
                        int h = m_surfaceView.getHeight();
                        m_plaerSDK.play();
                m_surfaceView.setVisibility(View.VISIBLE);



            }
        });
        m_surfaceView = findViewById(R.id.surface);
        m_holder =  m_surfaceView.getHolder();
        m_holder.addCallback(this);
    }
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
         m_plaerSDK.setPlayWnd(m_holder.getSurface());
        m_surfaceView.setVisibility(View.VISIBLE);
    }
    public void showWH()
    {
        Toast.makeText(this, String.valueOf(m_surfaceView.getWidth()), Toast.LENGTH_LONG).show();
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        m_plaerSDK.setPlayWnd(m_holder.getSurface());
        m_surfaceView.setVisibility(View.VISIBLE);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onPause()
    {
        super.onPause();
        m_plaerSDK.setPlayWnd(m_holder.getSurface());
        m_surfaceView.setVisibility(View.VISIBLE);
    }
}
