package com.haoxuan.worknote.activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.Toast;
import com.haoxuan.worknote.R;

public class ArticleDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private EditText content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        initView();
        initData();

    }

    private void initView()
    {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        content= (EditText) findViewById(R.id.content);
    }

    private void initData()
    {
        mSocketTask.execute();
    }


    @Override
    public void onError(String message) {
        super.onError(message);
        Toast.makeText(ArticleDetailActivity.this, getString(R.string.server_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(String result) {
        super.onSuccess(result);
        content.setText(result);

    }

//    private class MyTask extends AsyncTask<String,Integer,String>
//    {
//
//
//        @Override
//        protected String doInBackground(String[] params) {
//            String text=null;
//            try {
//                Socket socket=new Socket("192.168.1.105",8080);
//                BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                text=reader.readLine();
//                socket.close();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return text;
//        }
//
//        @Override
//        protected void onPostExecute(String o) {
//            super.onPostExecute(o);
//            Toast.makeText(ArticleDetailActivity.this,o, Toast.LENGTH_SHORT).show();
//        }
//    }
}
