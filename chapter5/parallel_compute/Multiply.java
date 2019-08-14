package chapter5.parallel_compute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by 13 on 2017/5/9.
 */
public class Multiply implements Runnable {
    public static BlockingDeque<Msg> blockingDeque = new LinkedBlockingDeque<Msg>();

    @Override
    public void run() {

        try {
            ServerSocket s = new ServerSocket(8888,2);
            Socket socket = s.accept();
            BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //由Socket对象得到输出流，并构造PrintWriter对象
            PrintWriter os =new PrintWriter(socket.getOutputStream());



        } catch (IOException e) {


        }
        while (true) {
            Msg msg = null;
            try {
                msg = blockingDeque.take();
                msg.i = msg.j * msg.i;
                Div.blockingDeque.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
