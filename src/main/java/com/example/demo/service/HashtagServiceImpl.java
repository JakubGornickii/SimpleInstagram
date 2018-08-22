package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service("hashtagService")

public class HashtagServiceImpl implements HashtagService {
    @Override
    public String check(String hashtags) {

        String[] hashs = hashtags.split(" ");
        Set<String> hashs1 = new HashSet<>();
        for (String hash : hashs) {
            if (!hash.equals("")) {
                if (hashs1.contains(hash)) {
                    return "*Błąd wykryto dwa takie same hashtagi";
                }
                hashs1.add(hash);
            }
        }
        for (String has : hashs1) {
            if (!has.startsWith("#")) {
                return "*Błąd wykryto słowo nie zaczynające się na #";

            } else if (has.startsWith("#", 1)) {
                return "*Błąd wykryto dwa ## pod rząd";

            } else if (has.length() < 2) {
                return "*Błąd wykryto sam #";

            } else if (has.length() < 4) {
                return "*Błąd hashtag musi mieć minimum 3 znaki po #";
            }

        }
        return "true";
    }

    @Override
    public List<String> toList(String hashtags) {
        String[] hashs = hashtags.split(" ");
        List<String> hashtag = new ArrayList<>();
        for (String hash : hashs) {
            if (!hash.equals("")) {
                hashtag.add(hash);}
        }
                return hashtag;


    }
}