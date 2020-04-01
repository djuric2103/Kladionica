/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer.util;

/**
 *
 * @author not-sure
 */
public interface Operation {
    public static final int LOGIN = 1;
    public static final int GET_ALL_TEAMS = 2;
    public static final int SAVE_MATCH = 3;
    public static final int GET_MATCH_FOR_SCORE_INSERT = 4;
    public static final int SAVE_SCORE = 5;
    public static final int GET_ALL_TIPS = 6;
    public static final int GET_ODDS_FOR_NEW_TICKET = 7;
    public static final int SAVE_TICKET = 8;
    public static final int GET_TICKET = 9;
    public static final int DELETE_TICKET = 10;
    public static final int PAYOFF_TICKET = 11;
    public static final int GET_BUSSINESS_RESULTS = 12;
    public static final int OPERATION_SHUTDOWN = 13;
}
