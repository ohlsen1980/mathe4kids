package ohlsen.kindermathe;

import android.util.Log;

/**
 * Created by schoeneo on 24.08.2017.
 */

public class ApplicationManager {

    private static ApplicationManager instance = null;

    private String playerName = "";

    //for debugging purposes
    public static Boolean DEBUG = true;


    private ApplicationManager() {

    }

    /**
     * Singleton implementation of ApplicationManager
     * @return Instance of ApplicationManager
     */
    public static ApplicationManager getInstance() {
        if(instance == null) {
            instance = new ApplicationManager();
        }
        return instance;
    }

    /**
     * Gets actual player name
     * @return string player name
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Sets the player name
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        if(ApplicationManager.DEBUG) {
            Log.d(this.getClass().toString(), "player name set to: " + this.playerName);
        }
        this.playerName = playerName;
    }
}
