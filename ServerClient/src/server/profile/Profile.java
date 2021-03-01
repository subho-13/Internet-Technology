package server.profile;

import server.file.StringMapString;
import server.profile.role.CreateRole;
import server.profile.role.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Profile {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Map<String, Role> userRole = new TreeMap<>();
    List<String> newUsers = new ArrayList<>();
    String filename;

    public Profile(String filename) {
        this.filename = filename;
        Map<String, String> tmpMap = StringMapString.read(filename);

        for (Map.Entry<String, String> entry : tmpMap.entrySet()) {
            Role tmpRole = CreateRole.forRole(entry.getValue());
            userRole.put(entry.getKey(), tmpRole);
        }
    }

    private boolean isPresent(String user) {
        boolean present = false;

        try {
            lock.readLock().lock();
            if (userRole.containsKey(user)) {
                present = true;
            }
        } finally {
            lock.readLock().unlock();
        }

        return present;
    }

    public boolean add(String user, String rolename) {
        if (isPresent(user)) {
            return false;
        }

        Role role = CreateRole.forRole(rolename);
        if(role == null) {
            return false;
        }

        try {
            lock.writeLock().lock();
            userRole.put(user, role);
        } finally {
            lock.writeLock().unlock();
        }

        newUsers.add(user);
        return true;
    }

    public Role get(String user) {
        Role tmpRole;

        try {
            lock.readLock().lock();
            tmpRole = userRole.get(user);
        } finally {
            lock.readLock().unlock();
        }

        return tmpRole;
    }

    public boolean update(String user, String role) {
        if (!isPresent(user)) {
            return false;
        }

        try {
            lock.writeLock().lock();
            userRole.replace(user, CreateRole.forRole(role));
        } finally {
            lock.writeLock().unlock();
        }

        return true;
    }

    public void remove(String user) {
        try {
            lock.writeLock().lock();
            userRole.remove(user);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void save() {
        Map<String, String> newUserRole = new TreeMap<>();

        for (String user : newUsers) {
            newUserRole.put(user, userRole.get(user).getIdentity());
        }

        StringMapString.write(this.filename, newUserRole);
        newUsers.clear();
    }
}
