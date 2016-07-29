/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ChatServer {

    /**
     * The port that the server listens on.
     */
    private static final int PORT = 55555;

    /**
     * The set of all names of clients in the chat room. Maintained so that we
     * can check that new clients are not registering name already in use.
     */
    private static HashMap<String,Integer> names_rooms = new HashMap<>();
    
    /**
     * The map of all the print writers and room's ID for all the clients. This map is kept so
     * we can easily broadcast messages.
     */
    private static HashMap<PrintWriter,Integer> writers_rooms = new HashMap<>();

    /**
     * The appplication main method, which just listens on a port and spawns
     * handler threads.
     */
    public static void main(String[] args) throws Exception {
        System.out.println("The chat server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    /**
     * A handler thread class. Handlers are spawned from the listening loop and
     * are responsible for a dealing with a single client and broadcasting its
     * messages.
     */
    private static class Handler extends Thread {

        private String name;
        private int roomID;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        /**
         * Constructs a handler thread, squirreling away the socket. All the
         * interesting work is done in the run method.
         */
        public Handler(Socket socket) {
            this.socket = socket;
        }

        /**
         * Services this thread's client by repeatedly requesting a screen name
         * until a unique one has been submitted, then acknowledges the name and
         * registers the output stream for the client in a global set, then
         * repeatedly gets inputs and broadcasts them.
         */
        public void run() {
            try {

                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Request a name from this client.  Keep requesting until
                // a name is submitted that is not already used.  Note that
                // checking for the existence of a name and adding the name
                // must be done while locking the set of names.
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names_rooms) {
                        if (!names_rooms.keySet().contains(name)) {
                            names_rooms.put(name,0);
                            break;
                        }
                    }
                }
                // Now that a successful name has been chosen, add the
                // socket's print writer to the set of all writers so
                // this client can receive broadcast messages.
                out.println("NAMEACCEPTED");
          //     writers.add(out);

                while (true) {
                    out.println("SUBMITROOM");
                    try {
                        roomID = Integer.valueOf(in.readLine());
                    /*    if(roomID == 0){
                            return;
                        }*/
                        synchronized (names_rooms) {
                            for(Map.Entry<String,Integer> e : names_rooms.entrySet()){
                                if(e.getKey().equals(name)){
                                    e.setValue(roomID);
                                    break;
                                }
                            }
                            break;
                    }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }

                }
                out.println("ROOMACCEPTED");
                writers_rooms.put(out, roomID);
                
                
                // Accept messages from this client and broadcast them.
                // Ignore other clients that cannot be broadcasted to.
                while (true) {
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    for (Map.Entry<PrintWriter, Integer> writer_room : writers_rooms.entrySet()) {
                        if(roomID == writer_room.getValue())
                            writer_room.getKey().println("MESSAGE " + name + ": " + input);
                    }
                }

            } catch (IOException e) {
                System.out.println(e);
            } finally {
                // This client is going down!  Remove its name and its print
                // writer from the sets, and close its socket.
                if (name != null) {
                    names_rooms.remove(name);
                }
                if (out != null) {
                    writers_rooms.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
