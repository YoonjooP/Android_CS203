package edu.byuh.cis.cs203.hellocs203.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class PrefActivity extends PreferenceActivity {

    public static final String MUSIC_PREF = "MUSIC_PREF";
    public static final String MISSILE_PREF = "MISSILE_PREF";
    public static final String DEPTH_PREF = "DEPTH_PREF";
    public static final String NUM_PREF = "NUM_PREF";
    public static final String SPEED_PREF = "SPEED_PREF";
    public static final String DIR_PREF = "DIR_PREF";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(this);

        CheckBoxPreference music = new CheckBoxPreference(this);
        music.setTitle("sound effect");
        music.setSummaryOn("Sound will play");
        music.setSummaryOff("Sound will not play");
        music.setChecked(true);
        music.setKey(MUSIC_PREF);
        screen.addPreference(music);

        CheckBoxPreference missile = new CheckBoxPreference(this);
        missile.setTitle("Missile option");
        missile.setSummaryOn("Rapid-fire missiles!");
        missile.setSummaryOff("Normal Missile");
        missile.setChecked(true);
        missile.setKey(MISSILE_PREF);
        screen.addPreference(missile);

        CheckBoxPreference depth = new CheckBoxPreference(this);
        depth.setTitle("Depth charge option");
        depth.setSummaryOn("Rapid-fire depth charges!");
        depth.setSummaryOff("Normal depth charges");
        depth.setChecked(true);
        depth.setKey(DEPTH_PREF);
        screen.addPreference(depth);

        ListPreference level = new ListPreference(this);
        level.setTitle("Number of enemies");
        level.setSummary("Choose the Level");
        level.setKey(NUM_PREF);
        String[] levellabels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        level.setEntries(levellabels);
        level.setEntryValues(levellabels);
        screen.addPreference(level);

        ListPreference speed = new ListPreference(this);
        speed.setTitle("Speed option");
        speed.setSummary("Choose the speed");
        speed.setKey(SPEED_PREF);
        String[] speedlabels = {"Fast", "Medium", "Slow"};
        String[] speedvalues = {"50", "10", "5"};
        speed.setEntries(speedlabels);
        speed.setEntryValues(speedvalues);
        screen.addPreference(speed);

        ListPreference direction = new ListPreference(this);
        direction.setTitle("Enemy Direction");
        direction.setSummary("Choose the direction");
        direction.setKey(DIR_PREF);
        String[] dirlabels = {"left-to-right", "right-to-left", "both"};
        direction.setEntries(dirlabels);
        direction.setEntryValues(dirlabels);
        screen.addPreference(direction);

        setPreferenceScreen(screen);
    }
}
