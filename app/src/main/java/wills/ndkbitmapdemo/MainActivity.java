package wills.ndkbitmapdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {


  private Button btnJava;
  private Button btnNDK;
  private ImageView ivBallonJava;
  private ImageView ivBallonNDK;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    btnJava = findViewById(R.id.btn_java);
    btnNDK = findViewById(R.id.btn_ndk);
    ivBallonJava = findViewById(R.id.iv_balloon_java);
    ivBallonNDK = findViewById(R.id.iv_balloon_ndk);
    btnJava.setOnClickListener(this);
    btnNDK.setOnClickListener(this);
  }


  @Override
  public void onClick(View v) {
    int id = v.getId();
    if (id == R.id.btn_java) {
      ivBallonJava.setImageBitmap(ConvertGrayImg(R.drawable.balloon));
    } else if (id == R.id.btn_ndk) {
      ivBallonNDK.setImageBitmap(convertGrayImgByC(R.drawable.balloon));
    }
  }

  public Bitmap ConvertGrayImg(int resId) {
    Bitmap img1 = ((BitmapDrawable) getResources().getDrawable(resId)).getBitmap();

    int w = img1.getWidth();
    int h = img1.getHeight();
    int[] pix = new int[w * h];
    img1.getPixels(pix, 0, w, 0, 0, w, h);
    int alpha = 0xFF << 24;
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        int color = pix[w * i + j];
        int red = ((color & 0x00FF0000) >> 16);
        int green = ((color & 0x0000ff00) >> 8);
        int blue = ((color & 0x000000ff));
        color = (red + green + blue) / 3;
        color = alpha | (color << 16) | (color << 8) | color;
        pix[w * i + j] = color;
      }
    }
    Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
    result.setPixels(pix, 0, w, 0, 0, w, h);
    return result;
  }

  public Bitmap convertGrayImgByC(int res) {
    Bitmap img1 = ((BitmapDrawable) getResources().getDrawable(res)).getBitmap();
    int w = img1.getWidth();
    int h = img1.getHeight();
    int[] pix = new int[w * h];
    img1.getPixels(pix, 0, w, 0, 0, w, h);
    int[] resultInt = LibFuns.ImgToGray(pix, w, h);
    Bitmap resultImg = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);
    resultImg.setPixels(resultInt, 0, w, 0, 0, w, h);
    return resultImg;
  }
}
