// Generated code from Butter Knife. Do not modify!
package vt.droidcon;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class Welcome$$ViewInjector {
  public static void inject(Finder finder, final vt.droidcon.Welcome target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131230807, "field 'mTimeLabel'");
    target.mTimeLabel = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131230803, "field 'mTemperatureLabel'");
    target.mTemperatureLabel = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131230810, "field 'mHumidityValue'");
    target.mHumidityValue = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131230812, "field 'mPrecipValue'");
    target.mPrecipValue = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131230813, "field 'mSummaryLabel'");
    target.mSummaryLabel = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131230805, "field 'mIconImageView'");
    target.mIconImageView = (android.widget.ImageView) view;
    view = finder.findRequiredView(source, 2131230814, "field 'prog'");
    target.prog = (android.widget.ProgressBar) view;
    view = finder.findRequiredView(source, 2131230808, "field 'mlocationlabel'");
    target.mlocationlabel = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131230816, "field 'mback'");
    target.mback = (android.widget.Button) view;
  }

  public static void reset(vt.droidcon.Welcome target) {
    target.mTimeLabel = null;
    target.mTemperatureLabel = null;
    target.mHumidityValue = null;
    target.mPrecipValue = null;
    target.mSummaryLabel = null;
    target.mIconImageView = null;
    target.prog = null;
    target.mlocationlabel = null;
    target.mback = null;
  }
}
