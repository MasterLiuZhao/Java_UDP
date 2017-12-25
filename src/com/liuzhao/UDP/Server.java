package com.liuzhao.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws IOException {
        //InetSocketAddress serverSocketAddress = new InetSocketAddress("127.0.0.1", 8888);
        DatagramSocket datagramSocket = new DatagramSocket(8888);


        byte[] bytes = new byte[1024];
        DatagramPacket inputDatagramPacket = new DatagramPacket(bytes, bytes.length);

        datagramSocket.receive(inputDatagramPacket);

        String content = new String(bytes, 0, bytes.length);
        System.out.println(content.trim());
        System.out.println(bytes == inputDatagramPacket.getData());

        String reply = "我收到了你的'" + content + "'信息。";
        byte[] replyBytes = reply.getBytes();
        DatagramPacket outputDatagramPacket = new DatagramPacket(replyBytes, replyBytes.length, inputDatagramPacket.getSocketAddress());

        datagramSocket.send(outputDatagramPacket);
    }

}
