package edu.byuh.cis.cs203.hellocs203.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import edu.byuh.cis.cs203.hellocs203.R;

public class PrefActivity extends PreferenceActivity {

    public static final String MUSIC_PREF = "MUSIC_PREF";
    public static final String MISSILE_PREF = "MISSILE_PREF";
    public static final String DEPTH_PREF = "DEPTH_PREF";
    public static final String NUM_PREF = "NUM_PREF";
    public static final String SPEED_PREF = "SPEED_PREF";
    public static final String DIR_PREF = "DIR_PREF";


    /**
     * override onCreate
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(this);

        CheckBoxPreference music = new CheckBoxPreference(this);
        music.setTitle(R.string.sound_effect_title);
        music.setSummaryOn(R.string.sound_yesyes);
        music.setSummaryOff(R.string.sound_nono);
        music.setChecked(true);
        music.setKey(MUSIC_PREF);
        screen.addPreference(music);

        CheckBoxPreference missile = new CheckBoxPreference(this);
        missile.setTitle(R.string.missile_title);
        missile.setSummaryOn(R.string.missile_many);
        missile.setSummaryOff(R.string.missile_normal);
        missile.setChecked(true);
        missile.setKey(MISSILE_PREF);
        screen.addPreference(missile);

        CheckBoxPreference depth = new CheckBoxPreference(this);
        depth.setTitle(R.string.depth_charge_title);
        depth.setSummaryOn(R.string.depth_charge_many);
        depth.setSummaryOff(R.string.depth_charge_normal);
        depth.setChecked(true);
        depth.setKey(DEPTH_PREF);
        screen.addPreference(depth);

        ListPreference level = new ListPreference(this);
        level.setTitle(R.string.num_of_en);
        level.setSummary(R.string.level_choice);
        level.setKey(NUM_PREF);
        String[] levellabels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        level.setEntries(levellabels);
        level.setEntryValues(levellabels);
        screen.addPreference(level);

        ListPreference speed = new ListPreference(this);
        speed.setTitle(R.string.speed_title);
        speed.setSummary(R.string.choose_speed);
        speed.setKey(SPEED_PREF);
        String[] speedlabels = {getResources().getString(R.string.fast), getResources().getString(R.string.medium), getResources().getString(R.string.slow)};
        String[] speedvalues = {"20", "5", "0"};
        speed.setEntries(speedlabels);
        speed.setEntryValues(speedvalues);
        screen.addPreference(speed);

        ListPreference direction = new ListPreference(this);
        direction.setTitle(R.string.enemy_dir);
        direction.setSummary(R.string.choose_the_dir);
        direction.setKey(DIR_PREF);
        String[] dirlabels = {getResources().getString(R.string.l_to_r),getResources().getString(R.string.r_to_l), getResources().getString(R.string.both)};
        direction.setEntries(dirlabels);
        direction.setEntryValues(dirlabels);
        screen.addPreference(direction);

        setPreferenceScreen(screen);
    }

    /**
     * Music Preference
     * @param c
     * @return boolean
     */
    public static boolean getMusicPref(Context c) {
        return PreferenceManager.
                getDefaultSharedPreferences(c).getBoolean(MUSIC_PREF, true);
    }

    /**
     * Missile Preference
     * @param c
     * @return boolean
     */
    public static boolean getMissilePref(Context c) {
        return PreferenceManager.
                getDefaultSharedPreferences(c).getBoolean(MISSILE_PREF, true);
    }

    /**
     * Depth Charge Preference
     * @param c
     * @return boolean
     */
    public static boolean getDepthPref(Context c) {
        return PreferenceManager.
                getDefaultSharedPreferences(c).getBoolean(DEPTH_PREF, true);
    }

    /**
     * Level Preference
     * @param c
     * @return int
     */
    public static int getLevelPref(Context c) {
        String seth = PreferenceManager.
                getDefaultSharedPreferences(c).getString(NUM_PREF, "10");
        return Integer.parseInt(seth);
    }

    /**
     * Speed Preference
     * @param c
     * @return int
     */
    public static int getSpeedPref(Context c) {
        String seth = PreferenceManager.
                getDefaultSharedPreferences(c).getString(SPEED_PREF, "5");
        return Integer.parseInt(seth);
    }

    /**
     * Direction Preference
     * @param c
     * @return String
     */
    public static String getDirPref(Context c) {
        String seth = PreferenceManager.
                getDefaultSharedPreferences(c).getString(DIR_PREF, "both");
        return seth;
    }


}
