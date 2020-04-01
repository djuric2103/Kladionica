/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluator;

import domain.Odds;

/**
 *
 * @author not-sure
 */
public class Evaluator {
    public static boolean evaluate(Odds o) throws Exception{
        return evaluate(o.getMatch().getGoalsHome(), o.getMatch().getGoalsAway(), o.getTip().getName());
    }
    
    public static boolean evaluate(int homeGoals, int awayGoals, String tip) throws Exception{
        switch(tip){
            case "1":
                return homeGoals > awayGoals;
            case "x":
                return homeGoals == awayGoals;
            case "2":
                return homeGoals < awayGoals;
            case "0-1":
                return homeGoals + awayGoals <= 1;
            case "2-3":
                return homeGoals + awayGoals >= 2 && homeGoals + awayGoals <= 3;
            case "3+":
                return homeGoals + awayGoals >= 3;
            case "5+":
                return homeGoals + awayGoals >= 5;
        }
        throw new Exception("Tip "+tip+" does not exist");
    }
}
