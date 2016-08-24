package com.example.canvaspainting;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private ImageView colorBtn,sizeBtn,hhh;
    private SimpleDrawingView canvas;
    private final static String TAG = "Main";
    private static final int REQUEST_CODE_PERMISSION = 2;
    private static final int SELECT_PICTURE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        checkWritingPermission();
        canvas = (SimpleDrawingView) findViewById(R.id.simpleDrawingView1);
        sizeBtn = (ImageView) findViewById(R.id.sizeBtn);
        sizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popupSize = new PopupMenu(MainActivity.this, sizeBtn);

                try {
                Field[] fields = popupSize.getClass().getDeclaredFields();
                for (Field field : fields) {
                    if ("mPopup".equals(field.getName())) {
                        field.setAccessible(true);
                        Object menuPopupHelper = field.get(popupSize);
                        Class<?> classPopupHelper = Class.forName(menuPopupHelper
                                .getClass().getName());
                        Method setForceIcons = classPopupHelper.getMethod(
                                "setForceShowIcon", boolean.class);
                        setForceIcons.invoke(menuPopupHelper, true);
                        break;
                    }
                }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Inflating the Popup using xml file
                popupSize.getMenuInflater().inflate(R.menu.size_popup, popupSize.getMenu());
                popupSize.show(); //showing popup menu

                //registering popup with OnMenuItemClickListener
                popupSize.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch(item.getItemId()) {

                            case R.id.twenty:
                                canvas.drawPaint.setStrokeWidth(20);
                                return true;

                            case R.id.fourty:
                                canvas.drawPaint.setStrokeWidth(40);
                                return true;

                            case R.id.sixty:
                                canvas.drawPaint.setStrokeWidth(60);
                                return true;

                            case R.id.eighty:
                                canvas.drawPaint.setStrokeWidth(80);
                                return true;

                            case R.id.hundred:
                                canvas.drawPaint.setStrokeWidth(100);
                                return true;

                            default:
                                return true;
                        }
                    }
                });

            }
        }); //closing the setOnClickListener method

        colorBtn = (ImageView) findViewById(R.id.colorBtn);
        colorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Creating the instance of PopupMenu
            PopupMenu popupColor = new PopupMenu(MainActivity.this, colorBtn);

                try {
                    Field[] fields = popupColor.getClass().getDeclaredFields();
                    for (Field field : fields) {
                        if ("mPopup".equals(field.getName())) {
                            field.setAccessible(true);
                            Object menuPopupHelper = field.get(popupColor);
                            Class<?> classPopupHelper = Class.forName(menuPopupHelper
                                    .getClass().getName());
                            Method setForceIcons = classPopupHelper.getMethod(
                                    "setForceShowIcon", boolean.class);
                            setForceIcons.invoke(menuPopupHelper, true);
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Inflating the Popup using xml file
                popupColor.getMenuInflater().inflate(R.menu.color_popup, popupColor.getMenu());
                popupColor.show(); //showing popup menu

            //registering popup with OnMenuItemClickListener
                popupColor.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch(item.getItemId()) {

                        case R.id.black:
                            canvas.drawPaint.setColor(Color.BLACK);
                            return true;

                        case R.id.blue:
                            canvas.drawPaint.setColor(Color.parseColor("#0066FF"));
                            return true;

                        case R.id.green:
                            canvas.drawPaint.setColor(Color.parseColor("#00832B"));
                            return true;

                        case R.id.orange:
                            canvas.drawPaint.setColor(Color.parseColor("#FFA70F"));
                            return true;

                        case R.id.purple:
                            canvas.drawPaint.setColor(Color.parseColor("#5C00A3"));
                            return true;

                        case R.id.red:
                            canvas.drawPaint.setColor(Color.parseColor("#E10000"));
                            return true;

                        case R.id.white:
                            canvas.drawPaint.setColor(Color.WHITE);
                            return true;

                        case R.id.yellow:
                            canvas.drawPaint.setColor(Color.parseColor("#FFF227"));
                            return true;

                        default:
                            return true;
                    }
                }
            });

            }
        }); //closing the setOnClickListener method
    }

    public void exitApp(View view){
        AlertDialog.Builder alert =  new AlertDialog.Builder(this);
        alert.setIcon(R.drawable.alert);
        alert.setTitle("Exit Paint");
        alert.setMessage("Are you sure you want to close the Dreamy Painter?");
        alert.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){
                finish();   //Closes the application, finish is a reserved word for builder class
            }
        });
        alert.setNegativeButton("No", null);
        alert.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void clearScreen(View view){canvas.setBackground(null); canvas.path.reset(); canvas.bitmap.eraseColor(Color.WHITE); canvas.invalidate(); Toast.makeText(this, "Screen cleared!", Toast.LENGTH_SHORT).show(); }

    public void eraser(View view){ canvas.drawPaint.setStrokeWidth(110); canvas.drawPaint.setColor(Color.WHITE); }

    private static boolean canWriteToExternalStorage(Context context) {
        return ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void checkWritingPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // permission wasn't granted
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
            }
        }
    }

    public void saveImage(View view) {
        File sdCard = Environment.getExternalStorageDirectory();
        File folder = new File(sdCard.getAbsolutePath() + "/Dreamy Painter");

        boolean success = false;
        if (!folder.exists()) {
            success = folder.mkdirs();
            Log.i(TAG, " " + success);
        }

        File file = new File(folder, "drawing.png");

        if (!file.exists()) {
            try {
                success = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        FileOutputStream ostream = null;
        try {
            ostream = new FileOutputStream(file);

            Bitmap well = canvas.getBitmap();
            Bitmap save = Bitmap.createBitmap(320, 480,
                    Bitmap.Config.ARGB_8888);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            Canvas now = new Canvas(save);
            now.drawRect(new Rect(0, 0, 320, 480), paint);
            now.drawBitmap(well,
                    new Rect(0, 0, well.getWidth(), well.getHeight()),
                    new Rect(0, 0, 320, 480), null);

            if (save == null) {
            }
            save.compress(Bitmap.CompressFormat.PNG, 100, ostream);
            Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(this, "Null error",
                    Toast.LENGTH_SHORT).show();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "File error", Toast.LENGTH_SHORT).show();
        }
    }

    /* Choose an image from Gallery */
   public void openImageChooser(View view) {
       Toast.makeText(this, "Open from a File Manager Only.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                // Get the url from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // Get the path from the Uri
                    String path = getPathFromURI(selectedImageUri);
                    Log.i(TAG, "Image Path : " + path);
                   File f = new File(getPathFromURI(selectedImageUri));
                    // Set the image as background in canvas
                    Drawable d = Drawable.createFromPath(f.getAbsolutePath());
                    canvas.setBackground(d);
                }
            }
        }
    }


    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

}
