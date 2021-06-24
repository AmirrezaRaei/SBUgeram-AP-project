package Server;

import Model.Post;
import Model.Profile;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class Database {
    public static String File_PREFIX = "C:\\Users\\amirr\\Desktop\\src\\database";
    public static final String ProfileFile = File_PREFIX + "ProfileDatabase.txt";
    public static final String PostFile = File_PREFIX + "PostDatabase.txt";

    private static Database ourInstance = new Database();

    public static Database getInstance() {
        return ourInstance;
    }

    private Database() {
    }

    public synchronized void initializeServer() {
        // for profiles
        try {
            FileInputStream fin = new FileInputStream(Database.ProfileFile);
            ObjectInputStream inFromFile = new ObjectInputStream(fin);
            Server.profiles = new ConcurrentHashMap<>((ConcurrentHashMap<String, Profile>) inFromFile.readObject());
            inFromFile.close();
            fin.close();
        } catch (EOFException | StreamCorruptedException e) {
            Server.profiles = new ConcurrentHashMap<>();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // for Posts
        try {
            FileInputStream fin = new FileInputStream(Database.PostFile);
            ObjectInputStream inFromFile = new ObjectInputStream(fin);
            Server.posts = new ConcurrentSkipListSet<>((ConcurrentSkipListSet<Post>) inFromFile.readObject());
            inFromFile.close();
            fin.close();
        } catch (EOFException | StreamCorruptedException e) {
            Server.posts = new ConcurrentSkipListSet<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * it is synchronized to prevent multi thread problems
     * it save latest change of profiles and Post to file
     **/

    public synchronized void updateDataBase() {
        try {
            FileOutputStream fout = new FileOutputStream(ProfileFile);
            ObjectOutputStream objToFile = new ObjectOutputStream(fout);
            objToFile.writeObject(Server.profiles); //writing profiles
            objToFile.close();
            fout.close();

            fout = new FileOutputStream(PostFile);
            objToFile = new ObjectOutputStream(fout);
            objToFile.writeObject(Server.posts); // writing mails
            objToFile.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
