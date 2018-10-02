package com.example.user.secondfootballapp.user.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.secondfootballapp.PersonalActivity;
import com.ycuwq.datepicker.date.DatePicker;

import com.example.user.secondfootballapp.R;
import com.example.user.secondfootballapp.tournament.activity.TournamentTimeTableFragment;
import com.ycuwq.datepicker.date.DatePickerDialogFragment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.Manifest.permission.CAMERA;

public class UserInfo extends AppCompatActivity {
    Logger log = LoggerFactory.getLogger(UserInfo.class);
//    public static Button buttonDOB;
    public static TextView buttonDOB;
    ImageButton buttonPhoto;
    ImageButton buttonClose;
    ImageButton buttonSave;
    TextView textStatus;
    EditText textName;
    EditText textSurname;
    EditText textPatronymic;
    EditText textLogin;
    TextView textDOB;
    Bitmap myBitmap;
    Uri picUri;
    private ArrayList permissionsToRequest;
    private ArrayList permissionsRejected = new ArrayList();
    private ArrayList permissions = new ArrayList();

    private final static int ALL_PERMISSIONS_RESULT = 107;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        final Logger log = LoggerFactory.getLogger(TournamentTimeTableFragment.class);
//        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
//        final DatePickerDialogFragment datePickerDialogFragment;
//        final DatePicker datePickerDialogFragment;
//        buttonDOB = (Button) findViewById(R.id.userInfoDOB);
        textStatus = (TextView) findViewById(R.id.userStatus);
        textName = (EditText) findViewById(R.id.userInfoName);
        textName.clearFocus();
        textSurname = (EditText) findViewById(R.id.userInfoSurname);
        textPatronymic = (EditText) findViewById(R.id.userInfoPatronymic);
        textLogin = (EditText) findViewById(R.id.userInfoLogin);
        buttonPhoto = (ImageButton) findViewById(R.id.userInfoPhoto);
        buttonDOB = (TextView) findViewById(R.id.userInfoDOB);
        buttonClose = (ImageButton) findViewById(R.id.userProfileClose);
        buttonSave = (ImageButton) findViewById(R.id.userProfileSave);
//        datePickerDialogFragment = new DatePickerDialogFragment();
//        datePickerDialogFragment = new DatePicker();
//        datePickerDialogFragment.setOnDateChooseListener(new DatePickerDialogFragment.OnDateChooseListener() {
//            @Override
//            public void onDateChoose(int year, int month, int day) {
//                log.info("INFO: chosen: ", year, " " , month, " ", day);
//            }
//        });
//        buttonDOB.setPaintFlags(buttonDOB.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textName.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
        textName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    textName.getBackground().clearColorFilter();
                }
                else {
                    textName.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
                }
            }
        });
        textSurname.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
        textSurname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    textSurname.getBackground().clearColorFilter();
                }
                else {
                    textSurname.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
                }
            }
        });

        textPatronymic.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
        textPatronymic.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    textPatronymic.getBackground().clearColorFilter();
                }
                else {
                    textPatronymic.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
                }
            }
        });
        textLogin.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
        textLogin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    textLogin.getBackground().clearColorFilter();
                }
                else {
                    textLogin.getBackground().setColorFilter(getResources().getColor(R.color.colorLightGray), PorterDuff.Mode.SRC_IN);
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
                //post data
                String name;
                String surname;
                String patronymic;
                String login;
                String status;
                String[] str;
                String DOB;
                int yearDOB;
                int monthDOB;
                int dayDOB;
                Image photo;
                HashMap<String, Integer> months = new HashMap<String, Integer>();
                months.put("янв.", 1);
                months.put("февр.", 2);
                months.put("марта", 3);
                months.put("апр.", 4);
                months.put("мая", 5);
                months.put("июня", 6);
                months.put("июля", 7);
                months.put("авг.", 8);
                months.put("сент.", 9);
                months.put("окт.", 10);
                months.put("нояб.", 11);
                months.put("дек.", 12);
                try{
                    name = textName.getText().toString();
                    surname = textSurname.getText().toString();
                    patronymic = textPatronymic.getText().toString();
                    login = textLogin.getText().toString();
                    status = (String) textStatus.getText();
                    DOB = (String) buttonDOB.getText();
                    str = DOB.split(" ");
                    yearDOB = Integer.parseInt(str[2]);
                    monthDOB = months.get(str[1]);
                    dayDOB = Integer.parseInt(str[0]);
                }catch (Exception e){}

                finish();
            }
        });
        buttonPhoto.setOnClickListener(new View.OnClickListener() {
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


        buttonDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                datePickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");
//                DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
//                DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
//                com.example.user.secondfootballapp.user.activity.DatePicker datePicker1 = new com.example.user.secondfootballapp.user.activity.DatePicker();
                buttonDOB.setTextColor(getResources().getColor(R.color.colorBottomNavigationUnChecked));
                com.example.user.secondfootballapp.user.activity.DatePicker datePicker1 = new com.example.user.secondfootballapp.user.activity.DatePicker();
                datePicker1.show(getSupportFragmentManager(), "DatePickerDialogFragment");
//                datePicker.show(getSupportFragmentManager(), "DatePickerDialogFragment");
//                datePickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");
            }
        });
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
                            .into(buttonPhoto);
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
                            .into(buttonPhoto);
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
