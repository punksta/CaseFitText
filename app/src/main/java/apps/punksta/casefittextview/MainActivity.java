package apps.punksta.casefittextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import apps.punksta.autofittext.AutoFitUtil;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String titles[] = new String[] {
                "Неземной красоты",
                "Прераснейщая из прекрасных",
                "Несравненная"
        };
        AutoFitUtil.setOptimalWidthText((TextView) findViewById(R.id.text1), titles);
        AutoFitUtil.setOptimalWidthText((TextView) findViewById(R.id.text2), titles);
        AutoFitUtil.setOptimalWidthText((TextView) findViewById(R.id.text3), titles);

        AutoFitUtil.setOptimalWidthText((TextView) findViewById(R.id.text4), R.array.example);
        AutoFitUtil.setOptimalWidthText((TextView) findViewById(R.id.text5), R.array.example);

    }
}
