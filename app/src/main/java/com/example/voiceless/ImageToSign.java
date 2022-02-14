package com.example.voiceless;
 import androidx.annotation.Nullable;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.app.ActivityCompat;
 import androidx.core.content.ContextCompat;
 import android.Manifest;
 import android.content.ClipData;
 import android.content.ClipboardManager;
 import android.content.Context;
 import android.content.Intent;
 import android.content.pm.PackageManager;
 import android.graphics.Bitmap;
 import android.net.Uri;
 import android.os.Bundle;
 import android.provider.MediaStore;
 import android.util.SparseArray;
 import android.view.View;
 import android.widget.Button;
 import android.widget.ImageView;
 import android.widget.TextView;
 import android.widget.Toast;
 import com.google.android.gms.vision.Frame;
 import com.google.android.gms.vision.text.TextBlock;
 import com.google.android.gms.vision.text.TextRecognizer;
 import com.theartofdev.edmodo.cropper.CropImage;
 import com.theartofdev.edmodo.cropper.CropImageView;
 import java.io.IOException;


public class ImageToSign extends AppCompatActivity {

    private Button captureBtn, copyTextBtn, toDisplaySignBtn, retakeBtn;
    TextView textView_data;
    private static final int REQUSET_CAMERA_CODE = 100;
    Bitmap bitmap;
    ImageView displaySignfromImage;
    Compute objectImageToSign = new Compute();
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_to_sign);

//        Intent intent = getIntent();

        captureBtn = findViewById(R.id.CaptureBtnId);
        retakeBtn = findViewById(R.id.RetakeBtnId);
        copyTextBtn = findViewById(R.id.CopyTextBtnId);
        textView_data = findViewById(R.id.text_data);
        toDisplaySignBtn = findViewById(R.id.toDisplaySignId);
        displaySignfromImage = findViewById(R.id.displayImageId); //imageview

        //Manifest.permission.CAMERA => permission name
        if (ContextCompat.checkSelfPermission(ImageToSign.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ImageToSign.this, new String[]{
                    Manifest.permission.CAMERA
            }, REQUSET_CAMERA_CODE);
        }

        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(ImageToSign.this);
            }
        });

        retakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(ImageToSign.this);
            }
        });

        copyTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scanText = textView_data.getText().toString();
                copyToClipBoard(scanText);
            }
        });

        toDisplaySignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str = textView_data.getText().toString();
                objectImageToSign.detectSign(str, displaySignfromImage);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resulturi = result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resulturi);
                    getTextFromImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void getTextFromImage(Bitmap bitmap) {
        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();
        if (!recognizer.isOperational()) {
            Toast.makeText(ImageToSign.this, "ERROR OCCURRED!!", Toast.LENGTH_SHORT).show();
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < textBlockSparseArray.size(); i++) {
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
            }

            textView_data.setText(stringBuilder.toString());
            copyTextBtn.setVisibility(View.VISIBLE);
            retakeBtn.setVisibility(View.VISIBLE);
            captureBtn.setVisibility(View.GONE);
            toDisplaySignBtn.setVisibility(View.VISIBLE);
        }
    }

    private void copyToClipBoard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("COPIED DATA", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(ImageToSign.this, "COPIED TO CLIPBOARD!!", Toast.LENGTH_SHORT).show();
    }

}