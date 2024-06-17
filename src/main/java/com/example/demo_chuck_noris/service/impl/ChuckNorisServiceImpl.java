package com.example.demo_chuck_noris.service.impl;


import com.example.demo_chuck_noris.entity.Joke;
import com.example.demo_chuck_noris.service.ChuckNorisService;
import com.example.demo_chuck_noris.service.dto.JokeDto;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Service
public class ChuckNorisServiceImpl implements ChuckNorisService {

    public JokeDto getJoke() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Joke mainJoke = new Joke( "A man woke in Intensive Care with severe injuries and no memory of what happened. An investigation determined he was hit by either a freight train or by Chuck Norris.", LocalDateTime.now());
        JSONObject object = null;
        JokeDto jokeDto;
        try {
            URL url = new URL("https://api.chucknorris.io/jokes/random");
            Scanner scanner = new Scanner((InputStream) url.getContent());
            String result = "";
            while (scanner.hasNext()) {
                result += scanner.nextLine();
            }
            object = new JSONObject(result);
        } catch (IOException e) {

        }
        if (object != null) {
           jokeDto = new JokeDto(object.getString("value"), LocalDateTime.parse(object.getString("created_at").substring(0, 19), formatter));

        } else {
            jokeDto = new JokeDto(mainJoke.getJoke(), mainJoke.getCreatedAt());
        }
        return jokeDto;
    }

}
