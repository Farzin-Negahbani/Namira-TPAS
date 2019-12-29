package Classes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Farzin.negahbani on 5/26/2016.
 */
public class Game {

    public static boolean foul ;
    public static boolean offside ;
    public static boolean goldengoal;
    public static boolean penalty ;
    public static boolean syncmode ;
    public static Integer extrahalftime ;
    public static Integer halftime ;
    public static Integer stamina ;
    public static String  cupname ;
    public static String  mode ;
    public static ArrayList<String> teamList ;
    public static Integer matchNumber ;
    public static boolean monitor ;
    public static Integer totalMatches;


    public static void set_Game_Settings(int totalMatches,boolean monitor, boolean syncmode, String mode , String cupname, Integer stamina, Integer matchNumber, boolean foul, boolean offside, boolean goldengoal,  boolean penalty, Integer extrahalftime, Integer halftime, ArrayList<String> teamList){
        Game.foul          = foul;
        Game.offside       = offside;
        Game.goldengoal    = goldengoal;
        Game.penalty       = penalty;
        Game.extrahalftime = extrahalftime;
        Game.halftime      = halftime;
        Game.stamina       = stamina;
        Game.cupname       = cupname;
        Game.mode          = mode;
        Game.syncmode      = syncmode;
        Game.teamList      = teamList;
        Game.matchNumber   = matchNumber;
        Game.monitor       = monitor;
        Game.totalMatches  = totalMatches;

    }


    public static JSONObject toJSON(){
        
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("mode", Game.mode);
        jsonObject.put("match", Game.matchNumber);
        jsonObject.put("use_offside", Game.offside);
        jsonObject.put("foul", Game.foul);
        jsonObject.put("monitor", Game.monitor);
        jsonObject.put("golden_goal", Game.goldengoal);
        jsonObject.put("penalty_shoot_outs", Game.penalty);
        jsonObject.put("stamina_capacity", Game.stamina);
        jsonObject.put("half_time", Game.halftime);
        jsonObject.put("extra_half_time", Game.extrahalftime);
        jsonObject.put("synch_mode", Game.syncmode);

        if(Game.mode == "friendly" || Game.mode == "league"){
            JSONArray arr = new JSONArray();
            arr.addAll(Game.teamList);

            jsonObject.put("Teams",arr);
        }
//        else if(Game.mode == "league"){
//
//        }


        return jsonObject;
    }



}
