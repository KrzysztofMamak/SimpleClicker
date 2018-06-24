package example.mamak.simpleclicker;

import android.view.View;
import android.widget.ImageView;

import java.util.List;

public class ItemArray {

    private List<ImageView> mItemArray;
    private int[] mImageArray;
    private int mDeaths;
    private int mPoints;
    private int mWait;
    private int mSpeed;
    private int mTime;

    public ItemArray(List<ImageView> list, int[] imageArray) {
        mItemArray = list;
        this.mImageArray = imageArray;
        mDeaths = 0;
        mPoints = 0;
        mWait = 0;
        mSpeed = 15;
        mTime = 0;
    }

    // Make items invisible and set "onClicks"
    public void setUp() {
        for (final ImageView item : mItemArray) {
            item.setVisibility(View.INVISIBLE);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.setVisibility(View.INVISIBLE);
                    mPoints++;
                }
            });
        }
    }

    // Check if the game need to change speed, check and change coordinates
    public void checkItems() {

        if (mTime == 150) {
            mSpeed++;
            mTime = 0;
        }

        for (ImageView item : mItemArray) {

            if (item.getVisibility() == View.VISIBLE) {
                if (item.getY() <= -item.getHeight()) {
                    item.setVisibility(View.INVISIBLE);
                    mDeaths++;
                } else {
                    item.setY(item.getY() - mSpeed);
                }
            }
        }
        mTime++;
    }

    // Restart item
    public void restart(int width, int height) {
        for (ImageView item : mItemArray) {
            if (mWait <= 0 && item.getVisibility() == View.INVISIBLE) {
                setProperties(item, width, height);
                mWait = (int) ((Math.random() * 8) + 7);
            }
        }
        mWait--;
    }

    // Prepare item to start
    private void setProperties(ImageView item, int width, int height) {

        item.setY(height);
        item.setVisibility(View.VISIBLE);

        int i = (int) (Math.random() * 10);
        switch (i) {
            case 0:
                item.setImageResource(mImageArray[0]);
                break;
            case 1:
                item.setImageResource(mImageArray[1]);
                break;
            case 2:
                item.setImageResource(mImageArray[2]);
                break;
            case 3:
                item.setImageResource(mImageArray[3]);
                break;
            case 4:
                item.setImageResource(mImageArray[4]);
                break;
            case 5:
                item.setImageResource(mImageArray[5]);
                break;
            case 6:
                item.setImageResource(mImageArray[6]);
                break;
            case 7:
                item.setImageResource(mImageArray[7]);
                break;
            case 8:
                item.setImageResource(mImageArray[8]);
                break;
            case 9:
                item.setImageResource(mImageArray[9]);
                break;
        }
        item.setX((float) Math.random() * (width - item.getWidth()));
    }

    public int getPoints() {
        return mPoints;
    }

    public int getDeaths() {
        return mDeaths;
    }
}
