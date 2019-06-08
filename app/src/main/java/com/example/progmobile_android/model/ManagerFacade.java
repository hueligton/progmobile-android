package com.example.progmobile_android.model;

import android.content.Context;

import com.example.progmobile_android.model.entity.Card;
import com.example.progmobile_android.model.entity.Event;
import com.example.progmobile_android.model.entity.Pair;
import com.example.progmobile_android.model.dao.EventDAO;
import com.example.progmobile_android.model.dao.PurchaseDAO;
import com.example.progmobile_android.model.dao.UserDAO;
import com.example.progmobile_android.model.util.ServerCallback;

import java.util.List;

public class ManagerFacade {
    private EventDAO eventDAO;
    private UserDAO userDAO;
    private PurchaseDAO purchaseDAO;
    private static ManagerFacade managerFacade;

    private ManagerFacade(Context context) {
        this.eventDAO = new EventDAO(context);
        this.userDAO = new UserDAO(context);
        this.purchaseDAO = new PurchaseDAO(context);
    }

    public static ManagerFacade getInstance(Context context) {
        if (managerFacade == null) {
            managerFacade = new ManagerFacade(context);
        }
        return managerFacade;
    }

    /***
     * Login de usuário
     * @param serverCallback interface para retorno da chamada HTTP
     * @param login login do usuário
     * @param password senha do usuário
     * @return Retorna Object via CallBack. Necessário cast para UserToken.
     */
    public void login(String login, String password, ServerCallback serverCallback) {
        userDAO.login(login, password, serverCallback);
    }

    /***
     * Retorna usuário logado no sistema
     * @param serverCallback interface para retorno da chamada HTTP
     * @return Retorna Object via CallBack. Necessário cast para UserToken.
     */
    public void getUser(ServerCallback serverCallback) {
        userDAO.getUser(serverCallback);
    }

    /***
     * Logout de usuário
     * @param serverCallback interface para retorno da chamada HTTP
     * @return Sem retorno
     */
    public void logout(ServerCallback serverCallback) {
        userDAO.logout(serverCallback);
    }

    /***
     * Criação de usuário
     * @param serverCallback interface para retorno da chamada HTTP
     * @param login login do usuário
     * @param name nome do usuário
     * @param password senha do usuário
     * @param email email do usuário
     * @return Retorna Object via CallBack. Necessário cast para User.
     */
    public void createUser(String login, String name, String password, String email, ServerCallback serverCallback) {
        userDAO.createUser(login, name, password, email, serverCallback);
    }

    /***
     * Lista de eventos
     * @param serverCallback interface para retorno da chamada HTTP
     * @return Retorna Object via CallBack. Necessário cast para List<Event>.
     */
    public void getListEvents(ServerCallback serverCallback) {
        eventDAO.getListEvents(serverCallback);
    }

    /***
     * Lista de eventos filtrada por nome
     * @param name nome do evento
     * @return Retorna List<Event> filtrado.
     */
    public List<Event> searchEventByName(String name) {
        return eventDAO.searchEventByName(name);
    }

    /***
     * Evento selecionado pelo seu ID
     * @param serverCallback interface para retorno da chamada HTTP
     * @param eventId id do evento
     * @return Retorna Object via CallBack. Necessário cast para Event.
     */
    public void getEvent(int eventId, ServerCallback serverCallback) {
        eventDAO.getEvent(eventId, serverCallback);
    }

    /***
     * Lista de compras de um usuário
     * @param serverCallback interface para retorno da chamada HTTP
     * @param userId id do usuário
     * @param token token do usuário
     * @return Retorna Object via CallBack. Necessário cast para List<Purchase>.
     */
    public void getListPurchases(int userId, String token, ServerCallback serverCallback) {
        purchaseDAO.getListPurchases(userId, token, serverCallback);
    }

    /***
     * Salvar compra
     * @param serverCallback interface para retorno da chamada HTTP
     * @param userId id do usuário
     * @param token token do usuário
     * @param eventId id do evento
     * @param list lista tipo do ingresso e quantidade selecionados pelo usuário
     * @return Retorna Object via CallBack. Necessário cast para Purchase.
     */
    public void setPurchase(int userId, String token, Card card, int eventId, List<Pair> list, ServerCallback serverCallback) {
        purchaseDAO.setPurchase(userId, token, card, eventId, list, serverCallback);
    }

    /***
     * Compra de um usuário pelo ID da compra
     * @param serverCallback interface para retorno da chamada HTTP
     * @param userId id do usuário
     * @param token token do usuário
     * @param purchaseId id da compra
     * @return Retorna Object via CallBack. Necessário cast para Purchase.
     */
    public void getPurchase(int userId, String token, int purchaseId, ServerCallback serverCallback) {
        purchaseDAO.getPurchase(userId, token, purchaseId, serverCallback);
    }
}
