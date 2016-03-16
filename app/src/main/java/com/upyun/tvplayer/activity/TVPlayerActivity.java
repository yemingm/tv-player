package com.upyun.tvplayer.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.view.ViewPager;
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
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.upyun.tvplayer.R;
import com.upyun.tvplayer.adapter.CategoryPagerAdapter;
import com.upyun.tvplayer.adapter.ProgramPagerAdapter;
import com.upyun.tvplayer.model.Category;
import com.upyun.tvplayer.ui.QuitAlertDialog;
import com.viewpagerindicator.TitlePageIndicator;

import java.util.List;


public class TVPlayerActivity extends BaseActivity implements SmpEngine.Listener {
    private static final String TAG = "TVPlayerActivity";
    private SlidingMenu mMenu;
    private TitlePageIndicator mTitleCategory;
    private TitlePageIndicator mTitleProgram;
    private ViewPager mPagerCategory;
    private ViewPager mPageProgram;
    private CategoryPagerAdapter mAdapterCategory;
    private ProgramPagerAdapter mAdapterProgram;
    private List<Category> mCategories;

    private static final int SHOW_CONT = 1001;
    private static final int SHOW_MENU = 1002;
    private static final int SHOW_INFO = 1003;
    private int state = SHOW_CONT;

    private String playAddress = "http://vevoplaylist-live.hls.adaptive.level3.net/vevo/ch1/appleman.m3u8";
//    private String playAddress = "rtmp://124.207.19.118:1935/live/jctx";
//    private String playAddress = "http://live.dltv.cn:81/live5/live5_video.m3u8";

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

    @Override
    protected void initVariables() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Intent intent = getIntent();
        mCategories = (List<Category>) intent.getSerializableExtra("categories");
        Log.e(TAG, mCategories.toString());
    }

    @Override
    protected void loadDate() {
//        CategoryAPI categoryAPI = new CategoryAPI();
//        categoryAPI.getCategory(new UIListener<Category[]>() {
//            @Override
//            public void onSuccessed(Category[] result) {
//                mAdapterCategory.setmCategorys(result);
//                mAdapterCategory.notifyDataSetChanged();
//                Log.e(TAG, Arrays.toString(result));
//            }
//            @Override
//            public void onfailed(Exception e) {
//                Log.e(TAG, "获取频道分类失败");
//            }
//
//        });
//
//        ChannelAPI channelAPI = new ChannelAPI();
//        channelAPI.getChannels(new UIListener<ChannelList>() {
//            @Override
//            public void onSuccessed(ChannelList result) {
//                Log.e(TAG, result.toString());
//                Toast.makeText(TVPlayerActivity.this, result.toString(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onfailed(Exception e) {
//                Log.e(TAG, "获取频道失败");
//                Toast.makeText(TVPlayerActivity.this, e.toString(), Toast.LENGTH_LONG).show();
//            }
//        }, 100);
//
//        ProgramAPI programAPI = new ProgramAPI();
//        programAPI.getProgram(new UIListener<Program>() {
//            @Override
//            public void onSuccessed(Program result) {
//                Log.e(TAG, result.toString());
//                Toast.makeText(TVPlayerActivity.this, result.toString(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onfailed(Exception e) {
//                Log.e(TAG, "获取频道失败");
//                Toast.makeText(TVPlayerActivity.this, e.toString(), Toast.LENGTH_LONG).show();
//            }
//        }, ProgramAPI.Week.next_week, ProgramAPI.WeekDay.Thu,100);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_tvplayer);
        videoFrame = (SmpFrameLayout) findViewById(R.id.video_frame);
        surfaceView = (SurfaceView) findViewById(R.id.surface_view);
        shutterView = findViewById(R.id.shutter);

        mMenu = new SlidingMenu(this);
        mMenu.setMode(SlidingMenu.LEFT_RIGHT);
        mMenu.setMenu(R.layout.view_menu_channel);
        mMenu.setSecondaryMenu(R.layout.view_menu_item);
        mTitleCategory = (TitlePageIndicator) mMenu.getMenu().findViewById(R.id.title_channel);
        mTitleProgram = (TitlePageIndicator) mMenu.getSecondaryMenu().findViewById(R.id.title_item);
        mPagerCategory = (ViewPager) mMenu.getMenu().findViewById(R.id.pager_channel);
        mPageProgram = (ViewPager) mMenu.getSecondaryMenu().findViewById(R.id.pager_item);

        mAdapterCategory = new CategoryPagerAdapter(getSupportFragmentManager(), mCategories, this);
        mAdapterProgram = new ProgramPagerAdapter(getSupportFragmentManager());
        mPagerCategory.setAdapter(mAdapterCategory);
        mPageProgram.setAdapter(mAdapterProgram);
        mTitleCategory.setViewPager(mPagerCategory);
        mTitleProgram.setViewPager(mPageProgram);
        mTitleCategory.setTextColor(R.color.black);
        mTitleProgram.setTextColor(R.color.black);
        mMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        mMenu.setFadeDegree(0.35f);
        mMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
//        Log.e(TAG, event.getKeyCode() + "" + event);
        if (!mMenu.isMenuShowing()) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    mMenu.showMenu();
                    mMenu.getMenu().requestFocus();
                    state = SHOW_MENU;
                    return true;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    mMenu.showSecondaryMenu();
                    mMenu.getSecondaryMenu().requestFocus();
                    state = SHOW_MENU;
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onBackPressed() {
        if (mMenu.isMenuShowing()) {
            mMenu.showContent();
            return;
        } else {
            QuitAlertDialog.show(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        contentUri = Uri.parse(playAddress);
        try {
            smp.init(getRendererBuilder(), TVPlayerActivity.this, surfaceView.getHolder());
            smp.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            smp.release();
        } catch (Smp.SmpException e) {
            e.printStackTrace();
        }
        shutterView.setVisibility(View.VISIBLE);
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
                shutterView.setVisibility(View.INVISIBLE);
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
        videoFrame.setAspectRatio(
                height == 0 ? 1 : (width * pixelWidthAspectRatio) / height);
        Log.d(TAG, "width == " + width + ", height == " + height);
    }
}
