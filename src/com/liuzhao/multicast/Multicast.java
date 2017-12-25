package com.liuzhao.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Multicast {

    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(10);

        String broadcast_ip = "230.0.0.1";
        int broadcast_port = 30000;
        InetAddress broadcastAddress = InetAddress.getByName(broadcast_ip);

        MulticastSocket multicastSocket = new MulticastSocket(broadcast_port);
        multicastSocket.joinGroup(broadcastAddress);
        multicastSocket.setLoopbackMode(false);

        MulticastReadThread multicastReadThread = new MulticastReadThread(multicastSocket);
        pool.submit(multicastReadThread);

        DatagramPacket datagramPacket = new DatagramPacket(new byte[0], 0, broadcastAddress, broadcast_port);
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String temp = scanner.nextLine();
            //System.out.println("From Console : " + temp);
            byte[] bytes = temp.getBytes();

            datagramPacket.setData(bytes);

            multicastSocket.send(datagramPacket);
        }

    }
}
