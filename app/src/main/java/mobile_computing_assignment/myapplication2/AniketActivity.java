package mobile_computing_assignment.myapplication2;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class AniketActivity extends AppCompatActivity {

    static final int SocketServerPORT = 8080;
    RelativeLayout loginPanel;
    RelativeLayout chatPanel;
    EditText editTextUserName;
    Button buttonConnect;
    TextView chatMsg, textPort;
    String iPAddress = "192.168.0.17"; // Change the IP Address.

    EditText editTextSay;
    Button buttonSend, buttonDisconnect;

    String msgLog = "";
    ChatClientThread chatClientThread = null;
    String textUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniket);

        loginPanel = (RelativeLayout)findViewById(R.id.loginPanel);
        chatPanel = (RelativeLayout) findViewById(R.id.chatpanel);
        editTextUserName = (EditText)findViewById(R.id.username);
        textPort = (TextView)findViewById(R.id.port);

        buttonConnect = (Button)findViewById(R.id.connect);
        buttonDisconnect = (Button)findViewById(R.id.disconnect);
        chatMsg = (TextView)findViewById(R.id.chatmsg);


        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textUserName = editTextUserName.getText().toString();
                if(textUserName.equals("")){
                    Toast.makeText(AniketActivity.this, "Enter User Name", Toast.LENGTH_LONG).show();
                    return;
                }

                msgLog = "";
                chatMsg.setText(msgLog);
                loginPanel.setVisibility(View.GONE);
                chatPanel.setVisibility(View.VISIBLE);

                chatClientThread = new ChatClientThread(textUserName, iPAddress, SocketServerPORT);
                chatClientThread.start();
            }
        });
        buttonDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chatClientThread == null){
                    return;
                }
                chatClientThread.disconnect();
            }
        });

        editTextSay = (EditText)findViewById(R.id.sendmessage);
        buttonSend = (Button)findViewById(R.id.send);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextSay.getText().toString().equals("")){
                    return;
                }
                if(chatClientThread == null){
                    return;
                }
                chatClientThread.sendMsg(editTextSay.getText().toString() + "\n");
                editTextSay.setText("");
            }
        });
    }

    private class ChatClientThread extends Thread{
        String name;
        String dstAddress;
        int dstPort;

        String msgToSend = "";
        boolean goOut = false;

        ChatClientThread(String name, String address, int port){
            this.name = name;
            dstAddress = address;
            dstPort = port;
        }

        @Override
        public void run(){
            Socket socket = null;
            DataOutputStream dataOutputStream = null;
            DataInputStream dataInputStream = null;

            try{
                socket = new Socket(dstAddress,dstPort);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream.writeUTF(name);
                dataOutputStream.flush();

                while(!goOut){
                    if(dataInputStream.available() > 0){
                        msgLog += dataInputStream.readUTF();
                        AniketActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                chatMsg.setText(msgLog);
                            }
                        });
                    }

                    if(!msgToSend.equals("")){
                        dataOutputStream.writeUTF(msgToSend);
                        dataOutputStream.flush();
                        msgToSend = "";
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
                final String eString = e.toString();
                AniketActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AniketActivity.this, eString, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            finally {

                if(socket != null){
                    try{
                        socket.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if(dataOutputStream != null){
                    try{
                        dataOutputStream.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if(dataInputStream != null){
                    try{
                        dataInputStream.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }

                AniketActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loginPanel.setVisibility(View.VISIBLE);
                        chatPanel.setVisibility(View.GONE);
                    }
                });
            }
        }
        private void sendMsg(String msg){
            msgToSend = msg;
        }

        private void disconnect(){
            goOut = true;
            sendMsg(" has left the chat. \n");
        }
    }

}
