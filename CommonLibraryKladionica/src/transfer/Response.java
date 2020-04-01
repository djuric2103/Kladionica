/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;
import transfer.util.ResponseStatus;

/**
 *
 * @author not-sure
 */
public class Response implements Serializable{
    private ResponseStatus status;
    private Object error;
    private Object data;

    public Response() {
    }

    public Response(ResponseStatus status, Object error, Object data) {
        this.status = status;
        this.error = error;
        this.data = data;
    }

    

    /**
     * @return the status
     */
    public ResponseStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    /**
     * @return the error
     */
    public Object getError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(Object error) {
        this.error = error;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    
}
