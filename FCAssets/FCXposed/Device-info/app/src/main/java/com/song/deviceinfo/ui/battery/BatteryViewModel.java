package com.song.deviceinfo.ui.battery;

import android.content.Context;

import com.song.deviceinfo.info.BatteryInfo;
import com.song.deviceinfo.ui.base.NormalViewModel;

import java.util.List;

import androidx.core.util.Pair;

/**
 * Created by chensongsong on 2020/5/27.
 */
public class BatteryViewModel extends NormalViewModel {

    public List<Pair<String, String>> getBatteryInfo(Context context) {
        return BatteryInfo.getBatteryInfo(context);
    }
}