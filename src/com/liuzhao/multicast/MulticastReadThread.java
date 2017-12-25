package com.liuzhao.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class MulticastReadThread implements Runnable {
    private MulticastSocket multicastSocket;

    public MulticastReadThread(MulticastSocket multicastSocket) {
        this.multicastSocket = multicastSocket;
    }

    @Override
    public void run() {
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);

        try {
            while(true){
                multicastSocket.receive(datagramPacket);

                Charset charset = Charset.forName("UTF-8");

                String content = charset.decode(ByteBuffer.wrap(bytes)).toString().trim();
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }

}
