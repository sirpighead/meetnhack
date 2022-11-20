package com.csgame.api.csgameapi.persistence;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.csgame.api.csgameapi.model.VolunteerUser;

@Component
public class VolunteerUserFileDAO implements VolunteerUserDAO {
    Map<String, VolunteerUser> users;

    private ObjectMapper objectMapper;

    private static int nextID;
    private String filename;

    public VolunteerUserFileDAO(@Value("${users.file}") String filename, ObjectMapper objectMapper) throws IOException {
        this.filename = filename;
        this.objectMapper = objectMapper;

        load();
    }

    private VolunteerUser[] getUsersArray() {
        ArrayList<VolunteerUser> userArray = new ArrayList<>();

        for (VolunteerUser u : users.values())
            userArray.add(u);

        VolunteerUser[] array = (VolunteerUser[]) userArray.toArray();

        return array;
    }

    public VolunteerUser getUser(String UID) {
        synchronized (users) {
            for (VolunteerUser u : users.values())
                if (u.getUID().equals(UID))
                    return u;

            return null;
        }
    }

    public VolunteerUser[] getUsers() {
        synchronized (users) {
            return getUsersArray();
        }
    }

    private boolean save() throws IOException {
        VolunteerUser[] userArray = getUsersArray();

        objectMapper.writeValue(new File(filename), userArray);
        return true;
    }

    private boolean load() throws IOException {
        users = new TreeMap<>();
        nextID = 0;

        VolunteerUser[] userArray = objectMapper.readValue(new File(filename), VolunteerUser[].class);

        for (VolunteerUser user : userArray)
            if (user.getUID().substring(0, 1).equals("V"))
                users.put(user.getUsername(), user);

        return true;
    }

    private synchronized static int nextID() {
        int id = nextID;
        nextID += 1;
        return id;
    }
    
    public VolunteerUser createUser(VolunteerUser u) throws IOException {
        synchronized(users) {
            VolunteerUser user = new VolunteerUser("V" + nextID(), u.getUsername(), u.getPassword(),
                                        u.getName(), u.getCurrentPoints(), u.getLevel(),
                                        u.getClaimedDiscounts(), u.getEventsJoined());
            users.put(user.getUsername(), user);
            save();
            return user;
        }
    }

    public VolunteerUser login(String username, String password) {
        for (VolunteerUser u : users.values()) {
            if (u.getUsername() == username && u.getPassword() == password)
                return u;
        }

        return null;
    }

    public VolunteerUser updateUser(VolunteerUser user) {
        return user;
    }
}
