//package com.example.pc1.store;
//
//import android.app.Activity;
//import android.content.res.AssetManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.viro.core.ARAnchor;
//import com.viro.core.ARNode;
//import com.viro.core.ARScene;
//import com.viro.core.Object3D;
//import com.viro.core.OmniLight;
//import com.viro.core.Portal;
//import com.viro.core.PortalScene;
//import com.viro.core.Texture;
//import com.viro.core.ViroView;
//import com.viro.core.ViroViewARCore;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.ref.WeakReference;
//import java.util.Vector;
//
//public class ViroActivity extends Activity {
//    private static final String TAG = ViroActivity.class.getSimpleName();
//
//    private ViroView mViroView;
//
//    private ARScene mScene;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mViroView = new ViroViewARCore(this, new ViroViewARCore.StartupListener() {
//            @Override
//            public void onSuccess() {
//                displayScene();
//            }
//
//            @Override
//            public void onFailure(ViroViewARCore.StartupError error, String errorMessage) {
//                Log.e(TAG, "Error initializing AR [" + errorMessage + "]");
//            }
//        });
//
//        setContentView(mViroView);
//    }
//
//
//    private void displayScene() {
//        mScene = new ARScene();
//        mScene.setListener(new ARSceneListener(this, mViroView));
//
//        //add light
//        OmniLight light = new OmniLight();
//        light.setColor(Color.WHITE);
//        light.setPosition(new com.viro.core.Vector(0, 1,-4));
//        mScene.getRootNode().addLight(light);
//
//        //load ship window model
//        Object3D shipDoorModel = new Object3D();
//        shipDoorModel.loadModel(Uri.parse("file:///android_asset/portal_ship.vrx"), Object3D.Type.FBX, null);
//
//        //create a portal
//        Portal portal = new Portal();
//        portal.addChildNode(shipDoorModel);
//        portal.setScale(new com.viro.core.Vector(0.5, 0.5, 0.5));
//
//        //add a beach world
//        PortalScene portalScene = new PortalScene();
//        portalScene.setPosition(new com.viro.core.Vector(0, 0, -5));
//        portalScene.setPassable(true);
//        portalScene.setPortalEntrance(portal);
//        Bitmap beachBackground = getBitmapFromAssets("back.jpg");          //load from assets
//        Texture beachTexture = new Texture(beachBackground, Texture.Format.RGBA8, true, false);
//        portalScene.setBackgroundTexture(beachTexture);
//
//        mScene.getRootNode().addChildNode(portalScene);
//
//        //set scene in ViroView
//        mViroView.setScene(mScene);
//
//        View.inflate(this, R.layout.viro_initializing_ar, ((ViewGroup) mViroView));
//    }
//
//
//    private Bitmap getBitmapFromAssets(String filePath) {
//        AssetManager assetManager = getAssets();
//
//        InputStream istr;
//        Bitmap bitmap = null;
//        try {
//            istr = assetManager.open(filePath);
//            bitmap = BitmapFactory.decodeStream(istr);
//        } catch (IOException e) {
//            // handle exception
//        }
//
//        return bitmap;
//    }
//
//
//    private static class ARSceneListener implements ARScene.Listener {
//        private WeakReference<Activity> mCurrentActivityWeak;
//        private boolean mInitialized;
//
//        public ARSceneListener(Activity activity, View rootView) {
//            mCurrentActivityWeak = new WeakReference<Activity>(activity);
//            mInitialized = false;
//        }
//
//        @Override
//        public void onTrackingUpdated(ARScene.TrackingState trackingState,
//                                      ARScene.TrackingStateReason trackingStateReason) {
//            if (!mInitialized && trackingState == ARScene.TrackingState.NORMAL) {
//                Activity activity = mCurrentActivityWeak.get();
//                if (activity == null) {
//                    return;
//                }
//
//                TextView initText = (TextView) activity.findViewById(R.id.initText);
//                initText.setText("AR is initialized");
//                mInitialized = true;
//            }
//        }
//
//        @Override
//        public void onTrackingInitialized() {
//            // This method is deprecated.
//        }
//
//        @Override
//        public void onAmbientLightUpdate(float v, float v1) {
//
//        }
//
//        @Override
//        public void onAnchorFound(ARAnchor arAnchor, ARNode arNode) {
//
//        }
//
//        @Override
//        public void onAnchorRemoved(ARAnchor arAnchor, ARNode arNode) {
//
//        }
//
//        @Override
//        public void onAnchorUpdated(ARAnchor arAnchor, ARNode arNode) {
//
//        }
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mViroView.onActivityStarted(this);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mViroView.onActivityResumed(this);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mViroView.onActivityPaused(this);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mViroView.onActivityStopped(this);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mViroView.onActivityDestroyed(this);
//    }
//}
//
