package apps.punksta.autofittext;

import android.support.annotation.ArrayRes;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by punksta on 28.03.16.
 */
public class AutoFitUtil {
    private static final Comparator<String> lengthComparator = new Comparator<String>() {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.length() - rhs.length();
        }
    };

    /**
     * Find string which width is optimal to the TextView and set it as text
     * @param textView can be not measured yet
     * @param stringArray id of string array, can be unsorted
     */
    public static void setOptimalWidthText(final TextView textView, @ArrayRes int stringArray) {
        setOptimalWidthText(textView, textView.getContext().getResources().getStringArray(stringArray));
    }

    /**
     * Finds string which width is optimal to the TextView and set it as text
     * @param textView can be not measured yet
     * @param strings can be unsorted
     */
    public static void setOptimalWidthText(final TextView textView, @NonNull @Size(min = 1) final String ... strings) {
        if (strings.length == 0) {
            textView.setText("");
        } else if (strings.length == 1) {
            textView.setText(strings[0]);
        } else {
            textView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    int measuredWidth = textView.getMeasuredWidth();
                    setOptimalWidthText(textView, measuredWidth, strings);
                    textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            });
        }
    }

    /**
     * Finds string which width is optimal to the TextView and set it as text.
     * @param textView can be not measured yet
     * @param width value to compare with strings width
     * @param strings can be unsorted
     */
    private static void setOptimalWidthText(final TextView textView, int width, final String ... strings) {
        textView.setText(getOptimalWidthString(textView, width, strings));
    }


    /**
     * Find string which width is optimal to the TextView.
     * @param textView can be not measured yet
     * @param width value to compare with strings width
     * @param strings can be unsorted
     * @return optimal width string from width
     */
    public static String getOptimalWidthString(final TextView textView, int width, final String ... strings) {
        List<String> collection = Arrays.asList(strings);
        if (!isSorted(collection, lengthComparator))
            Collections.sort(collection, lengthComparator);
        Iterator<String> i = collection.iterator();
        String result = i.next();
        String current;
        while (i.hasNext() && getTextSize(textView, current = i.next()) < width) {
            result = current;
        }
        return result;
    }

    /**
     * Return the width of the testText.
     * @return the width of testText placed in the textView
     */
    private static float getTextSize(TextView textView, String testText) {
        return textView.getPaint().measureText(testText);
    }

    private static <T extends Comparable> boolean isSorted(Iterable<T> iterable, Comparator<T> comparator) {
        T previous = null;
        for (T t: iterable) {
            if (previous != null && comparator.compare(previous, t) < 0)
                return false;
            previous = t;
        }
        return true;
    }
}
