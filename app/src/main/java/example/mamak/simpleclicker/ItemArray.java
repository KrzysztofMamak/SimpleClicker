package example.mamak.simpleclicker;

import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ItemArray {

    private List<ImageView> itemArray = new ArrayList<>();
    private int deaths;
    private int points;
    private int wait;
    private int speed;
    private int time;

    public ItemArray(List<ImageView> list) {
        itemArray = list;
        deaths = 0;
        points = 0;
        wait = 0;
        speed = 15;
        time = 0;
    }

    // Make items invisible and set "onClicks"
    public void setUp() {
        for (final ImageView item : itemArray) {
            item.setVisibility(View.INVISIBLE);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.setVisibility(View.INVISIBLE);
                    points++;
                }
            });
        }
    }

    // Check if the game need to change speed, check and change coordinates
    public void checkItems() {

        if (time == 150) {
            speed++;
            time = 0;
        }

        for (ImageView item : itemArray) {

            if (item.getVisibility() == View.VISIBLE) {
                if (item.getY() <= -item.getHeight()) {
                    item.setVisibility(View.INVISIBLE);
                    deaths++;
                } else {
                    item.setY(item.getY() - speed);
                }
            }
        }
        time++;
    }

    // Restart item
    public void restart(int width, int height) {
        for (ImageView item : itemArray) {
            if (wait <= 0 && item.getVisibility() == View.INVISIBLE) {
                setProperties(item, width, height);
                wait = (int) ((Math.random() * 8) + 7);
            }
        }
        wait--;
    }

    // Prepare item to start
    private void setProperties(ImageView item, int width, int height) {

        item.setY(height);
        item.setVisibility(View.VISIBLE);

        int i = (int) (Math.random() * 10);
        switch (i) {
            case 0:
                item.setImageResource(R.drawable.fish1);
                break;
            case 1:
                item.setImageResource(R.drawable.fish1r);
                break;
            case 2:
                item.setImageResource(R.drawable.fish2);
                break;
            case 3:
                item.setImageResource(R.drawable.fish2r);
                break;
            case 4:
                item.setImageResource(R.drawable.fish3);
                break;
            case 5:
                item.setImageResource(R.drawable.fish3r);
                break;
            case 6:
                item.setImageResource(R.drawable.fish4);
                break;
            case 7:
                item.setImageResource(R.drawable.fish4r);
                break;
            case 8:
                item.setImageResource(R.drawable.fish5);
                break;
            case 9:
                item.setImageResource(R.drawable.fish5r);
                break;
        }
        item.setX((float) Math.random() * (width - item.getWidth()));
    }

    public int getPoints() {
        return points;
    }

    public int getDeaths() {
        return deaths;
    }
}
