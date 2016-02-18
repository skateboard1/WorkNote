package network;

import android.os.AsyncTask;

import com.haoxuan.worknote.constant.K;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by skateboard on 16-2-5.
 */
public class SocketTask extends AsyncTask<String, Integer, String> {

    private String host;

    private int port;

    private OnSocketRequestListener mListener;

    private String method;

    private String title;

    private static final String CONNECT_SERVER_ERROR = "connect_server_error";

    public SocketTask(String method, String title, String host, int port) {

        this.method = method;

        this.host = host;

        this.port = port;

        this.title = title;
    }

    public SocketTask(String method, String host, int port) {
        this(method, K.SOCKET_DEFAULT, host, port);
    }


    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected String doInBackground(String... params) {

        if (Method.SGET.equals(method)) {
            return getData();
        } else if (Method.SPOST.equals(method)) {
            postData(params[0]);
            return params[0];
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (mListener != null) {
            if (CONNECT_SERVER_ERROR.equals(s)) {
                mListener.onError(s);
            } else {
                mListener.onSuccess(s);
            }
        }
    }

    private void postData(String text) {
        try {
            Socket socket = new Socket(host, port);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(K.POST_TITLE + "\n");
            writer.write(text);
            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void postRequest(Socket socket, String title) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(title+"\n");
            writer.flush();
//            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getData() {
        StringBuilder builder = null;
        try {
            Socket socket = new Socket(host, port);
            postRequest(socket, title);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            builder = new StringBuilder();
            String text = reader.readLine();
            while (text != null) {
                builder.append(text).append("\n");
                text = reader.readLine();
            }
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            return CONNECT_SERVER_ERROR;
        }
        if (builder != null) {
            return builder.toString();
        } else {
            return null;
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
