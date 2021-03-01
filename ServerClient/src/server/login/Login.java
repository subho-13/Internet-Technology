package server.login;

import server.file.StringMapString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Login {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Map<String, String> userPass;
    List<String> newUsers = new ArrayList<>();
    String filename;

    public Login(String filename) {
        this.filename = filename;
        this.userPass = StringMapString.read(filename);
    }

    private boolean isPresent(String user) {
        boolean present = false;
        try {
            lock.readLock().lock();
            if (userPass.containsKey(user)) {
                present = true;
            }
        } finally {
            lock.readLock().unlock();
        }

        return present;
    }

    public boolean add(String user, String pass) {
        if (isPresent(user)) {
            return false;
        }

        try {
            lock.writeLock().lock();
            userPass.put(user, pass);
        } finally {
            lock.writeLock().unlock();
        }

        newUsers.add(user);

        return true;
    }

    public boolean check(String user, String pass) {
        boolean okay = false;

        try {
            lock.readLock().lock();
            if (pass.equals(userPass.get(user))) {
                okay = true;
            }
        } finally {
            lock.readLock().unlock();
        }

        return okay;
    }

    public boolean change(String user, String pass) {
        if (!isPresent(user)) {
            return false;
        }

        try {
            lock.writeLock().lock();
            userPass.replace(user, pass);
        } finally {
            lock.writeLock().unlock();
        }

        return true;
    }

    public void remove(String user) {
        try {
            lock.writeLock().lock();
            userPass.remove(user);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void save() {
        Map<String, String> newUserPass = new TreeMap<>();

        for (String user : newUsers) {
            newUserPass.put(user, userPass.get(user));
        }

        StringMapString.write(this.filename, newUserPass);
        newUsers.clear();
    }
}
