package com.gfa.orientationexampleexam.demo.services;

import com.gfa.orientationexampleexam.demo.models.URLItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class URLService {

    String message;
    URLItem item = new URLItem();
    List<URLItem> urlList = new ArrayList<>();

    public boolean isAlreadyStored (URLItem item){
        return urlList.stream().filter(u -> u.getId() == item.getId()).count() > 0;
    }

    public void setMessage(States state, URLItem item){
        if (state == States.EMPTY){
            message = null;
        } else if (state == States.FREE){
            message = "Your url is aliased to " + item.getAlias()
                    + " and your secret code is "
                    + item.getCode();
        } else {
            message = "Your alias is already in use!";
        }
    }

    public boolean hasMessage(){
        return message != null;
    }

    public String getMessage(){
        return message;
    }

    public void storeItem(URLItem item){
        item = new URLItem();
        urlList.add(item);
    }

    public URLItem getItem() {
        return item;
    }

    public void setItem(URLItem item) {
        this.item = item;
    }
}
