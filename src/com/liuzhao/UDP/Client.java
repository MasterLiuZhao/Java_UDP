package com.liuzhao.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Client {
    public static void main(String[] args) throws IOException {
        InetSocketAddress serverSocketAddress = new InetSocketAddress("127.0.0.1", 8888);

        DatagramSocket datagramSocket = new DatagramSocket();
        //datagramSocket.connect(serverSocketAddress);

        String reply = "大哥收到我的消息了吗？";
        byte[] replyBytes = reply.getBytes();
        DatagramPacket outputDatagramPacket = new DatagramPacket(replyBytes, replyBytes.length, serverSocketAddress);
        datagramSocket.send(outputDatagramPacket);


        byte[] bytes = new byte[1024];
        DatagramPacket inputDatagramPacket = new DatagramPacket(bytes, bytes.length);
        datagramSocket.receive(inputDatagramPacket);
        //datagramSocket

        String content = new String(bytes, 0, bytes.length);
        System.out.println(content.trim());
        System.out.println(bytes == inputDatagramPacket.getData());

    }
}
