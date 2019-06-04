package com.gfa.oeexam.orientation.services;

import com.gfa.oeexam.orientation.models.UrlAlias;
import com.gfa.oeexam.orientation.repositories.URLRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class URLService {

    private UrlAlias itemToDisplayed = new UrlAlias();
    private URLRepository repo;
    private boolean displayMessage;
    private String message;


    public URLService(URLRepository repo){
        this.repo = repo;
    }


    public void setMessageFor(UrlAlias item) {
        this.message = item == null ? "Your alias is already in use!" : getMessageForItem(item);
    }

    public String getMessage() {
        return !displayMessage ? "" : message;
    }

    public boolean isAlreadyStored(String alias){
        Optional<UrlAlias> itemWithTheSameAlias = repo.getItemByAlias(alias);
        return  itemWithTheSameAlias.isPresent();
    }

    public boolean isAlreadyStored(){
        return isAlreadyStored(itemToDisplayed.getAlias());
    }

    public void save (UrlAlias itemToSave){
        itemToSave.generateSecretCode();
        repo.save(itemToSave);
    }

    public UrlAlias getItemToDisplayed() {
        return itemToDisplayed;
    }

    public void setItemToDisplayed(UrlAlias itemToDisplayed) {
        this.itemToDisplayed = itemToDisplayed;
    }

    public boolean displayMessage() {
        return displayMessage;
    }

    public void shallDisplayMessage(boolean displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getMessageForItem(UrlAlias alias) {
        return  "Your url is aliased to " + alias.getAlias()
                + " and your secret code is " + alias.getSecretCode();
    }
}