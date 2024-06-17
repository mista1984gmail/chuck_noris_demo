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
        Joke mainJoke = new Joke( "Sticks and stones MAY break your bones, but with Chuck Norris it's a sure thing.", LocalDateTime.now());
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
