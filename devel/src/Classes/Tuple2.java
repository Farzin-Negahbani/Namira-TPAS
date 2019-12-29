package Classes;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Farzin.negahbani on 3/12/2017.
 */
public class Tuple2 {
    private final Integer position;
    private final Integer played;
    private final Integer won;
    private final Integer drawn;
    private final Integer lost;
    private final Integer points;
    private final Integer GF;
    private final Integer GA;
    private final Integer GD;
    private final String Team;

    public Tuple2(Integer position, String teamName,Integer played, Integer won, Integer drawn, Integer lost, Integer points, Integer gf, Integer ga, Integer gd) {
        this.position   = position;
        this.Team       = new String(teamName);
        this.played     = played;
        this.won        = won;
        this.drawn      = drawn;
        this.lost       = lost;
        this.points     = points;
        this.GF         = gf;
        this.GA         = ga;
        this.GD         = gd;
    }


    public String getTeam(){
        return this.Team;
    }

    public Integer getPosition(){
        return this.position;
    }


    public Integer getPoints(){
        return this.points;
    }

    public Integer getLost(){
        return this.lost;
    }

    public Integer getDrawn(){
        return this.drawn;
    }

    public Integer getWon(){
        return this.won;
    }

    public Integer getPlayed(){
        return this.played;
    }
    public Integer getGF(){
        return this.GF;
    }

    public Integer getGA(){
        return this.GA;
    }
    public Integer getGD(){
        return this.GD;
    }


}
