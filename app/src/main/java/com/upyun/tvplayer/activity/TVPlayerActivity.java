package com.upyun.tvplayer.activity;

import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chnvideo.smp.sdk.SmoothStreamingTestMediaDrmCallback;
import com.chnvideo.smp.sdk.SmpFrameLayout;
import com.chnvideo.smp.sdk.WidevineTestMediaDrmCallback;
import com.chnvideo.smp.sdk.engine.DashRendererBuilder;
import com.chnvideo.smp.sdk.engine.ExtractorRendererBuilder;
import com.chnvideo.smp.sdk.engine.HlsRendererBuilder;
import com.chnvideo.smp.sdk.engine.SmoothStreamingRendererBuilder;
import com.chnvideo.smp.sdk.engine.Smp;
import com.chnvideo.smp.sdk.engine.SmpEngine;
import com.upyun.tvplayer.R;
import com.upyun.tvplayer.adapter.CategoryPagerAdapter;
import com.upyun.tvplayer.adapter.ProgramPagerAdapter;
import com.upyun.tvplayer.model.Category;
import com.upyun.tvplayer.model.Channel;
import com.upyun.tvplayer.model.Program;
import com.upyun.tvplayer.model.ProgramList;
import com.upyun.tvplayer.ui.InfoView;
import com.upyun.tvplayer.ui.QuitAlertDialog;
import com.upyun.tvplayer.util.MyApplication;
import com.viewpagerindicator.TitlePageIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;


public class TVPlayerActivity extends BaseActivity implements SmpEngine.Listener {
    private static final String TAG = "TVPlayerActivity";
    private DrawerLayout mDrawer;
    private View mMenuChannel;
    private View mMenuProgram;
    private TitlePageIndicator mTitleCategory;
    private TitlePageIndicator mTitleProgram;
    private ViewPager mPagerCategory;
    private ViewPager mPageProgram;
    private CategoryPagerAdapter mAdapterCategory;
    private ProgramPagerAdapter mAdapterProgram;
    private List<Category> mCategories;

//    private String playAddress = "http://live.dltv.cn:81/live5/live5_video.m3u8";
    private String playAddress = "http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8";

    public static final int TYPE_DASH = 0;
    public static final int TYPE_SS = 1;
    public static final int TYPE_HLS = 2;
    public static final int TYPE_OTHER = 3;

    private final int contentType = TYPE_HLS;
    private final String contentId = "httptsbravoplayer";
    private Uri contentUri = null;

    private SmpFrameLayout videoFrame = null;
    private View shutterView = null;
    private SurfaceView surfaceView;
    private Smp smp = new Smp();
    private boolean isMenuShow;
    private List<Program> mPrograms;
    private InfoView mInfoView;


    @Override
    protected void initVariables() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //盒子性能太差 Intent传递数据可能丢失
//        Intent intent = getIntent();
//        mCategories = (List<Category>) intent.getSerializableExtra("categories");
//        mPrograms = (List<Program>) intent.getSerializableExtra("programs");

        mCategories = ((MyApplication) getApplication()).getCategories();
        mPrograms = ((MyApplication) getApplication()).getPrograms();
        Log.e(TAG, mCategories + ":::" + mPrograms);

        if (MyApplication.channel != null) {
//            playAddress = MyApplication.channel.getInputAndOutput().get(0).getOutputUrl();
        }
    }

    @Subscribe
    public void onEvent(Channel channel) {
        //TODO 切换频道
        MyApplication.channel = channel;
//        stopPlay();
//        startPlay(channel.getInputAndOutput().get(0).getOutputUrl());
//        SharedPreferencesUtils.saveChannelId(this,channel.getId());
//        SharedPreferencesUtils.saveURL(this, channel.getInputAndOutput().get(0).getSourceUrl());
        showInfo();
        Log.e(TAG, channel.toString());
    }

    @Subscribe
    public void onEvent(ProgramList programList) {
        //TODO 切换节目单
        MyApplication.programList = programList;
        showInfo();
        Log.e(TAG, programList.toString());
    }

    @Override
    protected void loadDate() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_tvplayer);
        videoFrame = (SmpFrameLayout) findViewById(R.id.video_frame);
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        shutterView = findViewById(R.id.shutter);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mMenuChannel = findViewById(R.id.view_channel);
        mMenuProgram = findViewById(R.id.view_program);
        mTitleCategory = (TitlePageIndicator) findViewById(R.id.title_channel);
        mTitleProgram = (TitlePageIndicator) findViewById(R.id.title_item);
        mPagerCategory = (ViewPager) findViewById(R.id.pager_channel);
        mPageProgram = (ViewPager) findViewById(R.id.pager_item);
        mInfoView = (InfoView) findViewById(R.id.info);

        mAdapterCategory = new CategoryPagerAdapter(getSupportFragmentManager(), mCategories);
        mAdapterProgram = new ProgramPagerAdapter(getSupportFragmentManager(), mPrograms);
        mPagerCategory.setAdapter(mAdapterCategory);
        mPageProgram.setAdapter(mAdapterProgram);
        mTitleCategory.setViewPager(mPagerCategory);
        mTitleProgram.setViewPager(mPageProgram);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (!isMenuShow) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    mDrawer.openDrawer(mMenuChannel);
                    mMenuChannel.requestFocus();
                    isMenuShow = true;
                    return true;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    mDrawer.openDrawer(mMenuProgram);
                    mMenuProgram.requestFocus();
                    isMenuShow = true;
                    return true;
                case KeyEvent.KEYCODE_DPAD_UP:
                    //TODO
                    return true;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    //TODO
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onBackPressed() {
        if (isMenuShow) {
            mDrawer.closeDrawer(mMenuProgram);
            mDrawer.closeDrawer(mMenuChannel);
            isMenuShow = false;
        } else {
            QuitAlertDialog.show(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startPlay(playAddress);
        showInfo();
    }

    private void showInfo() {
        if (MyApplication.channel != null && MyApplication.programList != null) {
            mInfoView.setTitleText(MyApplication.channel.getId() + "   " + MyApplication.channel.getDisplayName());
            mInfoView.setItemText(MyApplication.programList.getProgramName());
            mInfoView.setTargetText(MyApplication.channel.getStatus() + MyApplication.channel.getSwitchVideoStatus());
        }
        mInfoView.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopPlay();
    }

    private void startPlay(String playAddress) {
        contentUri = Uri.parse(playAddress);
        try {
            smp.init(getRendererBuilder(), TVPlayerActivity.this, surfaceView.getHolder());
            smp.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        shutterView.setVisibility(View.VISIBLE);
    }

    private void stopPlay() {
        try {
            smp.release();
        } catch (Smp.SmpException e) {
            e.printStackTrace();
        }
        shutterView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            smp.release();
        } catch (Smp.SmpException e) {
            e.printStackTrace();
        }
    }

    //Internal methods
    private SmpEngine.RendererBuilder getRendererBuilder() {
        String userAgent = "PlayerDemo";
        switch (contentType) {
            case TYPE_SS:
                return new SmoothStreamingRendererBuilder(this, userAgent, contentUri.toString(),
                        new SmoothStreamingTestMediaDrmCallback());
            case TYPE_DASH:
                return new DashRendererBuilder(this, userAgent, contentUri.toString(),
                        new WidevineTestMediaDrmCallback(contentId));
            case TYPE_HLS:
                return new HlsRendererBuilder(this, userAgent, contentUri.toString());
            case TYPE_OTHER:
                return new ExtractorRendererBuilder(this, userAgent, contentUri);
            default:
                throw new IllegalStateException("Unsupported type: " + contentType);
        }
    }

    @Override
    public void onStateChanged(boolean playWhenReady, int playbackState) {

        String text = "playWhenReady=" + playWhenReady + ", playbackState=";
        switch (playbackState) {
            case Smp.STATE_BUFFERING:
                text += "buffering";
                shutterView.setVisibility(View.VISIBLE);
                break;
            case Smp.STATE_ENDED:
                text += "ended";
                shutterView.setVisibility(View.INVISIBLE);
                break;
            case Smp.STATE_IDLE:
                text += "idle";
                shutterView.setVisibility(View.VISIBLE);
                break;
            case Smp.STATE_PREPARING:
                text += "preparing";
                shutterView.setVisibility(View.VISIBLE);
                break;
            case Smp.STATE_READY:
                text += "ready";
                shutterView.setVisibility(View.INVISIBLE);
                break;
            default:
                text += "unknown";
                break;
        }
        Log.d(TAG, text);
    }

    @Override
    public void onError(Exception e) {
        Log.d(TAG, "error:" + e);
    }

    @Override
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees,
                                   float pixelWidthAspectRatio) {
        shutterView.setVisibility(View.GONE);
    }
}
