package com.example.user.secondfootballapp.user.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.user.adapter.RVEditClubMembersAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CAMERA;

public class UserEditClub extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(UserEditClub.class);
    ImageButton buttonLogo;
    Bitmap myBitmap;
    Uri picUri;
    private ArrayList permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();

    private final static int ALL_PERMISSIONS_RESULT = 107;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView recyclerView;
        ImageButton buttonSave;
        ImageButton buttonClose;
        Toolbar toolbar;
        final EditText editTitle;
        final EditText editDesc;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_edit_club);
        try {
            Intent intent = getIntent();
            String title = intent.getStringExtra("NEWSTITLE");
            toolbar = (Toolbar) findViewById(R.id.toolbarUserCommandInfo);
            setSupportActionBar(toolbar);
            buttonClose = (ImageButton) findViewById(R.id.userEditClubClose);
            buttonSave = (ImageButton) findViewById(R.id.userEditClubSave);
            buttonLogo = (ImageButton) findViewById(R.id.userEditClubLogo);
            editTitle = (EditText) findViewById(R.id.userEditClubTitle);
            editDesc = (EditText) findViewById(R.id.userEditClubDesc);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEditClub);
            RVEditClubMembersAdapter adapter = new RVEditClubMembersAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            editTitle.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
            editTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus){
                        editTitle.getBackground().clearColorFilter();
                    }
                    else {
                        editTitle.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
                    }
                }
            });
            editDesc.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
            editDesc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus){
                        editDesc.getBackground().clearColorFilter();
                    }
                    else {
                        editDesc.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
                    }
                }
            });
            buttonClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            buttonLogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(getPickImageChooserIntent(), 200);
                }
            });
            permissions.add(CAMERA);
            permissionsToRequest = findUnAskedPermissions(permissions);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


                if (permissionsToRequest.size() > 0)
                    requestPermissions((String[]) permissionsToRequest.toArray(new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
            }
        } catch (Exception e) {
            log.error("ERROR: ", e);
        }
    }




    public Intent getPickImageChooserIntent() {

        // Determine Uri of camera image to save.
        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // collect all camera intents
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                log.info("INFO: outputFileUri != null");
            }
            else{
                log.error("ERROR: outputFileUri == null");
            }
            allIntents.add(intent);
        }

        // collect all gallery intents
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        // the main intent is the last in the list (fucking android) so pickup the useless one
        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals("com.android.documentsui.DocumentsActivity")) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        // Create a chooser from the main intent
        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");

        // Add all other intents
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }

    /**
     * Get URI to image received from capture by camera.
     */
    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bitmap bitmap;
        if (resultCode == Activity.RESULT_OK) {
//            picUri = getPickImageResultUri(data);
            if (getPickImageResultUri(data) != null) {
                picUri = getPickImageResultUri(data);
                try {
                    myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
//                    myBitmap = rotateImageIfRequired(myBitmap, picUri);
                    myBitmap = getResizedBitmap(myBitmap, 500);

//                    CircleImageView croppedImageView = (CircleImageView) findViewById(R.id.img_profile);
//                    croppedImageView.setImageBitmap(myBitmap);
//                    buttonPhoto.setImageBitmap(myBitmap);
                    Glide.with(this)
                            .load(myBitmap)
                            .apply(new RequestOptions()
                                    .circleCropTransform()
                                    .format(DecodeFormat.PREFER_ARGB_8888)
                                    .priority(Priority.HIGH))
//                            .load(picUri)
                            .into(buttonLogo);
                } catch (IOException e) {e.printStackTrace(); }
            } else {
                log.info("INFO: CAMERA");
                if (picUri==null){
                    log.info("INFO: picUri==null");
                }
                if (data != null && data.getExtras() != null){
//                    bitmap = (Bitmap) data.getExtras().get("data");
                    log.info("INFO: data != null");

                    bitmap = (Bitmap) data.getExtras().get("data");
                    myBitmap = bitmap;
                    myBitmap = getResizedBitmap(myBitmap, 500);
                    Glide.with(this)
                            .load(myBitmap)
                            .apply(new RequestOptions()
                                    .circleCropTransform()
                                    .format(DecodeFormat.PREFER_ARGB_8888)
                                    .priority(Priority.HIGH))
//                        .load(picUri)
                            .into(buttonLogo);
                }
//                else {
//                    try {
//                        myBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
////                    myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_con);
//                }

//                if(data.getData()!=null){
//                    bitmap = (Bitmap)data.getExtras().get("data");
//                    myBitmap = bitmap;
//                }else{
//                    try {
//                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
//                        myBitmap = bitmap;
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }


//                CircleImageView croppedImageView = (CircleImageView) findViewById(R.id.img_profile);
//                if (croppedImageView != null) {
//                    croppedImageView.setImageBitmap(myBitmap);
//                }


//                buttonPhoto.setImageBitmap(myBitmap);

            }

        }

    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
//        if (data != null) {
//            String action = data.getAction();
//            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
//        }
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            isCamera = (data != null && data.getClipData() != null) ? false : true;
        } else {
            if (data != null) {
                String action = data.getAction();
                isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
            }
        }
//        if (data != null && data.getData() != null) {
//            String action = data.getAction();
//            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
//        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();


//        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        log.info("INFO: onSaveInstanceState");
        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("pic_uri", picUri);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        log.info("INFO: onRestoreInstanceState");
        // get the file url
        picUri = savedInstanceState.getParcelable("pic_uri");

    }

    private ArrayList<String> findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList<String> result = new ArrayList<String>();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (canMakeSmores()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        //for android 6
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
//            if (checkSelfPermission(Manifest.permission.CAMERA)
//                    != PackageManager.PERMISSION_GRANTED) {
//
//                requestPermissions(new String[]{Manifest.permission.CAMERA , Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                        200);
//            }
//        }

        return true;
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private boolean canMakeSmores() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case ALL_PERMISSIONS_RESULT:
                for (Object perms : permissionsToRequest) {
                    if (hasPermission(perms.toString())) {

                    } else {

                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale((String) permissionsRejected.get(0))) {
                            showMessageOKCancel("These permissions are mandatory for the application. Please allow access.",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                                                //Log.d("API123", "permisionrejected " + permissionsRejected.size());

                                                requestPermissions((String[]) permissionsRejected.toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }

                }

                break;
        }

    }
}
