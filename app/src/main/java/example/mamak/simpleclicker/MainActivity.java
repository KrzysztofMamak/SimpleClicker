package example.mamak.simpleclicker;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    SharedPreferences mTopScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // SharedPreferences for top score
        mTopScore = getSharedPreferences("BEST", 0);
        final SharedPreferences.Editor editor = mTopScore.edit();

        TextView mTopView = (TextView) findViewById(R.id.top_text_view);
        final TextView mScoreView = (TextView) findViewById(R.id.score_text_view);

        //coordinates
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = size.x;
        final int height = size.y;

        final ItemArray itemArray;
        ImageView item1 = (ImageView) findViewById(R.id.item1);
        ImageView item2 = (ImageView) findViewById(R.id.item2);
        ImageView item3 = (ImageView) findViewById(R.id.item3);
        ImageView item4 = (ImageView) findViewById(R.id.item4);
        ImageView item5 = (ImageView) findViewById(R.id.item5);
        ImageView item6 = (ImageView) findViewById(R.id.item6);
        ImageView item7 = (ImageView) findViewById(R.id.item7);
        ImageView item8 = (ImageView) findViewById(R.id.item8);
        ImageView item9 = (ImageView) findViewById(R.id.item9);
        ImageView item10 = (ImageView) findViewById(R.id.item10);

        int[] mImageArray = new int[10];
        mImageArray[0] = R.drawable.fish1;
        mImageArray[1] = R.drawable.fish1r;
        mImageArray[2] = R.drawable.fish2;
        mImageArray[3] = R.drawable.fish2r;
        mImageArray[4] = R.drawable.fish3;
        mImageArray[5] = R.drawable.fish3r;
        mImageArray[6] = R.drawable.fish4;
        mImageArray[7] = R.drawable.fish4r;
        mImageArray[8] = R.drawable.fish5;
        mImageArray[9] = R.drawable.fish5r;

        itemArray = new ItemArray(Arrays.asList(item1, item2, item3, item4,
                                                item5, item6, item7, item8,
                                                item9, item10), mImageArray);
        itemArray.setUp();
        mTopView.setText("Top: " + mTopScore.getInt("top", 0));

        final Handler handler = new Handler();

        final Runnable r = new Runnable() {
            @Override
            public void run() {

                // Save top score if necessary
                if (itemArray.getDeaths() >= 5) {
                    if (itemArray.getPoints() > mTopScore.getInt("top", 0)) {
                        editor.putInt("top", itemArray.getPoints());
                        editor.apply();
                    }
                    finish();
                }

                mScoreView.setText("Score: " + itemArray.getPoints());

                itemArray.checkItems();
                itemArray.restart(width, height);

                handler.postDelayed(this, 30);
            }
        };

        handler.postDelayed(r, 30);
    }
}