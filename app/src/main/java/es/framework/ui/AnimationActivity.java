package es.framework.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import es.framework.R;


public class AnimationActivity extends Activity {

    private ImageView imageView;
    private Animation rotateAnim;
    private Animation scaleAnim;
    private Animation translateAnim;
    private Animation alphaAnim;

    private Button brotate;
    private Button bscale;
    private Button btranslate;
    private Button balpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        imageView= (ImageView) findViewById(R.id.imageBall);
        brotate= (Button) findViewById(R.id.brotate);
        brotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateAnimation();
            }
        });

        bscale= (Button) findViewById(R.id.bscale);
        bscale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scaleAnimation();
            }
        });

        btranslate= (Button) findViewById(R.id.btranslate);
        btranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateAnimation();
            }
        });

        balpha= (Button) findViewById(R.id.balpha);
        balpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alphaAnimation();
            }
        });
    }

    private void scaleAnimation(){
        // scaleAnim= AnimationUtils.loadAnimation(this,R.anim.scale_anim);
        scaleAnim=new ScaleAnimation(
                1.0f,1.5f,
                1.0f,1.5f,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnim.setDuration(1000);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation Started..", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(),"Animation End..",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Toast.makeText(getApplicationContext(),"Animation Repeated..",Toast.LENGTH_SHORT).show();
            }
        });
        imageView.startAnimation(scaleAnim);
    }

    private void rotateAnimation(){
        rotateAnim= AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
        imageView.startAnimation(rotateAnim);
    }

    private void translateAnimation(){
        translateAnim= AnimationUtils.loadAnimation(this,R.anim.translate_anim);
        imageView.startAnimation(translateAnim);
    }
    private void alphaAnimation(){
        alphaAnim= AnimationUtils.loadAnimation(this,R.anim.alpha_anim);
        imageView.startAnimation(alphaAnim);
    }
}