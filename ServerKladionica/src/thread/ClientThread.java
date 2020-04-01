/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.Controller;
import domain.Has;
import domain.Match;
import domain.Odds;
import domain.Ticket;
import domain.Tip;
import domain.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.Request;
import transfer.Response;
import transfer.util.Operation;
import transfer.util.ResponseStatus;
import domain.GeneralEntity;

/**
 *
 * @author not-sure
 */
public class ClientThread extends Thread {

    private Socket socket;

    public ClientThread() {
    }

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Response response = handleRequest();
                sendResponse(response);
                //System.out.println("sent");
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Client leave the session");
            Controller.getInsance().removeClient(this);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Response handleRequest() throws IOException, ClassNotFoundException {
        ObjectInputStream inSocket = new ObjectInputStream(socket.getInputStream());
        Request request = (Request) inSocket.readObject();

        int operation = request.getOperation();
        Response response = new Response();

        switch (operation) {
            case Operation.LOGIN:
                User userRequest = (User) request.getData();

                 {
                    try {
                        User user = Controller.getInsance().login(userRequest);
                        response.setData(user);
                        response.setStatus(ResponseStatus.SUCCESS);
                    } catch (Exception ex) {
                        Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                        response.setStatus(ResponseStatus.ERROR);
                        response.setError(ex);
                    }
                }

                break;
            case Operation.GET_ALL_TEAMS: {
                try {
                    List<GeneralEntity> list = Controller.getInsance().getAllTeams();
                    response.setData(list);
                    response.setStatus(ResponseStatus.SUCCESS);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
            }

            break;
            case Operation.SAVE_MATCH: {
                Object[] arr = (Object[])request.getData();
                Match match = (Match) arr[0];
                List<Odds> list = (List<Odds>)arr[1];
                try {
                    int id = Controller.getInsance().saveMatch(match, list);
                    match.setId(id);
                    response.setData(match);
                    response.setStatus(ResponseStatus.SUCCESS);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
            }
            break;
            case Operation.GET_MATCH_FOR_SCORE_INSERT: {
                int id = (int) request.getData();
                try {
                    Match match = Controller.getInsance().getMatchForScoreInsert(id);
                    response.setData(match);
                    response.setStatus(ResponseStatus.SUCCESS);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
            }
            break;
            case Operation.SAVE_SCORE: {
                Match match = (Match) request.getData();
                try {
                    Controller.getInsance().saveScore(match);
                    response.setStatus(ResponseStatus.SUCCESS);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
            }
            break;
            case Operation.GET_ALL_TIPS: {
                try {
                    List<GeneralEntity> list = Controller.getInsance().getAllTips();
                    response.setData(list);
                    response.setStatus(ResponseStatus.SUCCESS);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
            }
            break;
            case Operation.GET_ODDS_FOR_NEW_TICKET: {
                try {
                    int id = (int) (((Object[]) request.getData())[0]);
                    Tip tip = (Tip) (((Object[]) request.getData())[1]);
                    Odds odds = (Odds) Controller.getInsance().getOddsForNewTicket(id, tip);
                    response.setData(odds);
                    response.setStatus(ResponseStatus.SUCCESS);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
            }
            break;
            case Operation.SAVE_TICKET: {
                Object[] arr = (Object[])request.getData();
                Ticket ticket = (Ticket) arr[0];
                List<Has> list = (List<Has>)arr[1];
                try {
                    int ticketID = Controller.getInsance().saveTicket(ticket, list);
                    ticket.setId(ticketID);
                    response.setData(ticket);
                    response.setStatus(ResponseStatus.SUCCESS);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
            }
            break;
            case Operation.GET_TICKET: {
                int ticketID = (int) request.getData();

                try {
                    Object[] array = Controller.getInsance().getTicket(ticketID);
                    response.setData(array);
                    response.setStatus(ResponseStatus.SUCCESS);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
            }
            break;
            case Operation.DELETE_TICKET: {
                int ticketID = (int) request.getData();
                try {
                    Controller.getInsance().deleteTicket(ticketID);
                    response.setStatus(ResponseStatus.SUCCESS);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
            }
            break;
            case Operation.PAYOFF_TICKET: {
                int ticketID = (int) request.getData();

                try {
                    Ticket ticket = Controller.getInsance().payoffTicket(ticketID);
                    response.setData(ticket);
                    response.setStatus(ResponseStatus.SUCCESS);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }

            }
            break;
            case Operation.GET_BUSSINESS_RESULTS: {
                Object[] dates = (Object[]) request.getData();

                try {
                    Object[] info = Controller.getInsance().getBussinessResults((Date) dates[0], (Date) dates[1]);
                    response.setData(info);
                    response.setStatus(ResponseStatus.SUCCESS);
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(ResponseStatus.ERROR);
                    response.setError(ex);
                }
            }
            break;
        }

        return response;
    }

    private void sendResponse(Response response) throws IOException {
        ObjectOutputStream outSocket = new ObjectOutputStream(socket.getOutputStream());
        outSocket.writeObject(response);
    }

    public void sendShutDownMessage() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(new Request(Operation.OPERATION_SHUTDOWN, null));
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
