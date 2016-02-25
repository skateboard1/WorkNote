package network;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.haoxuan.worknote.constant.K;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by skateboard on 16-2-5.
 */
public class SocketTask extends AsyncTask<String, Integer, String> {

    private String host;

    private int port;

    private OnSocketRequestListener mListener;

    private String method;

    private String title;

    private String content;

    private BufferedReader reader;

    private BufferedWriter writer;

    public SocketTask(String method, String title, String host, int port) {

        this.method = method;

        this.host = host;

        this.port = port;

        this.title = title;

    }

    public SocketTask(String method, String host, int port) {
        this(method, K.SOCKET_DEFAULT, host, port);
    }

    public SocketTask(String host, int port) {
        this(Method.SGET, host, port);
    }

    public SocketTask() {
        this(K.DEST_HOST, K.DEFAULT_PORT);

    }

    public void sendRequest(String method, String title) {
        this.method = method;
        this.title = title;
        if(getStatus()!=Status.RUNNING) {
            this.execute();
        }
    }

    public void sendRequest(String method, String title, String content) {
        this.method = method;
        this.title = title;
        this.content = content;
        if(getStatus()!=Status.RUNNING) {
            this.execute();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;
        if (Method.SGET.equals(method)) {
            result = getData();
        } else if (Method.SPOST.equals(method)) {
            result = postData();
            if (K.POST_RESULT_FAILURE.equals(result)) {
                result = K.CONNECT_SERVER_ERROR;
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (mListener != null) {
            if (method.equals(Method.SPOST)) {
                if( K.SOCKET_RESULT_OK.equals(s)) {
                    mListener.onSuccess(s);
                }
                else
                {
                    mListener.onError(s);
                }
            } else if(method.equals(Method.SGET)){
                if(K.CONNECT_SERVER_ERROR.equals(s))
                {
                    mListener.onError(s);
                }
                else {
                    mListener.onSuccess(s);
                }
            }
        }
    }

    private String postData() {
        String result = null;
        String answer=null;
        try {
            Socket socket = new Socket(host, port);
            socket.setSoTimeout(5000);
            sendRequest(socket, Method.SPOST, title, content);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            answer = reader.readLine();
            if (K.SOCKET_RESULT_OK.equals(result)) {
                result = reader.readLine();

            }
            else
            {
                result=answer;
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    private void sendRequest(Socket socket, String method, String title, String content) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(method + "\n");
            writer.write(title + "\n");
            if (!TextUtils.isEmpty(content)) {
                writer.write(content+"\n");
            }
            writer.flush();
//            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getData() {
        StringBuilder builder = null;
        String answer=null;
        String line=null;
        String result=null;
        try {
            Socket socket = new Socket(host, port);
            socket.setSoTimeout(5000);
            sendRequest(socket, Method.SGET, title, null);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            builder = new StringBuilder();
            answer = reader.readLine();
            if (K.SOCKET_RESULT_OK.equals(answer)) {
                line = reader.readLine();
                while (line != null) {
                    builder.append(line).append("\n");
                    line = reader.readLine();
                }
            }
            else
            {
                result=answer;
            }
            writer.close();
            reader.close();
            socket.close();
        }
        catch(SocketTimeoutException e)
        {
            e.printStackTrace();
            result=K.READ_DATA_TIMEOUT;
        }
        catch (IOException e) {
//            e.printStackTrace();
            result=K.CONNECT_SERVER_ERROR;
        }
        finally {
            if (builder != null) {
                result=builder.toString();
                return result;
            } else {
                return result;
            }
        }

    }


    public interface OnSocketRequestListener {
        void onError(String message);

        void onSuccess(String result);
    }

    public void setOnSocketRequestListener(OnSocketRequestListener socketRequestListener) {
        this.mListener = socketRequestListener;
    }

    public static class Method {
        public static final String SPOST = "socket_post";

        public static final String SGET = "socket_get";
    }
}
