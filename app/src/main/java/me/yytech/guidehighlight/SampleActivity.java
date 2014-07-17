package me.yytech.guidehighlight;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import me.yytech.guidehighlight.library.HighLightLayout;
import me.yytech.guidehighlight.library.Target;

public class SampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_sample, container, false);
            return rootView;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            HighLightLayout guide = new HighLightLayout(getActivity());
            View tv1 = getView().findViewById(R.id.tv1);
            View tv2 = getView().findViewById(R.id.tv2);
            Paint titlePaint = new Paint();
            titlePaint.setAntiAlias(true);
            titlePaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.text_size));
            titlePaint.setColor(Color.WHITE);
            Target target = new Target.Builder().setTitle("test323")
                    .setTitlePaint(titlePaint)
                    .setArrowXOffset(40)
                    .setView(tv1)
                    .setTitlePosition(Target.TitlePosition.above)
                    .build();
            Target target2 = new Target.Builder().setTitle("test")
                    .setTitlePaint(titlePaint)
                    .setArrowAngle(270).setTitleYOffset(20)
                    .setArrowXOffset(40)
                    .setView(tv2)
                    .setTitlePosition(Target.TitlePosition.below)
                    .build();
            guide.addTarget(target, target2);
            ((ViewGroup) getActivity().getWindow().getDecorView()).addView(guide);
        }

    }
}
