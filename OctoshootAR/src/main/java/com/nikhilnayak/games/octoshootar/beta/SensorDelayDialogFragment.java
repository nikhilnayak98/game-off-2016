/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016. Nikhil Nayak <nikhilnayak98@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.nikhilnayak.games.octoshootar.beta;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.ArrayList;

import com.nikhilnayak.games.octoshootar.R;

public class SensorDelayDialogFragment extends DialogFragment {

    /**
     * Default Constructor.
     * <p/>
     * lint [ValidFragment]
     * http://developer.android.com/reference/android/app/Fragment.html#Fragment()
     * Every fragment must have an empty constructor, so it can be instantiated when restoring its activity's state.
     */
    public SensorDelayDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Sensor Delay");

        final ArrayList<String> sensorDelayString = new ArrayList<String>();
        sensorDelayString.add("Delay Fastest");
        sensorDelayString.add("Delay Game");
        sensorDelayString.add("Delay Ui");
        sensorDelayString.add("Delay Normal");

        final ArrayList<Integer> sensorDelayInteger = new ArrayList<Integer>();
        sensorDelayInteger.add(SensorManager.SENSOR_DELAY_FASTEST);
        sensorDelayInteger.add(SensorManager.SENSOR_DELAY_GAME);
        sensorDelayInteger.add(SensorManager.SENSOR_DELAY_UI);
        sensorDelayInteger.add(SensorManager.SENSOR_DELAY_NORMAL);

        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences(BetaUtils.KEY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

        int currentSensorDelayIndex = sensorDelayInteger.indexOf(sharedPreferences.getInt(BetaUtils.KEY_SENSOR_DELAY, SensorManager.SENSOR_DELAY_GAME));

        builder.setSingleChoiceItems(sensorDelayString.toArray(new String[]{}), currentSensorDelayIndex, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sharedPreferencesEditor.putInt(BetaUtils.KEY_SENSOR_DELAY, sensorDelayInteger.get(which));
            }
        });

        builder.setPositiveButton(R.string.craft_dialog_fragment_ok_response, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sharedPreferencesEditor.commit();
            }
        });

        return builder.create();
    }
}
