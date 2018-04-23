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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SharedPreferences top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // SharedPreferences for top score
        top = getSharedPreferences("BEST", 0);
        final SharedPreferences.Editor editor = top.edit();

        TextView topView = (TextView) findViewById(R.id.topView);
        final TextView scoreView = (TextView) findViewById(R.id.scoreView);

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

        List<ImageView> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item6);
        list.add(item7);
        list.add(item8);
        list.add(item9);
        list.add(item10);

        itemArray = new ItemArray(list);
        itemArray.setUp();
        topView.setText("Top: " + top.getInt("top", 0));

        final Handler handler = new Handler();

        final Runnable r = new Runnable() {
            @Override
            public void run() {

                // Save top score if necessary
                if (itemArray.getDeaths() >= 5) {
                    if (itemArray.getPoints() > top.getInt("top", 0)) {
                        editor.putInt("top", itemArray.getPoints());
                        editor.apply();
                    }
                    finish();
                }

                scoreView.setText("Score: " + itemArray.getPoints());

                itemArray.checkItems();
                itemArray.restart(width, height);

                handler.postDelayed(this, 30);
            }
        };

        handler.postDelayed(r, 30);
    }
}
