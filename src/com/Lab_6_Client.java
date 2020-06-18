package com;

import com.support.UserDragClient;

/**
 * Основной класс программы клиентского приложения
 * @author Маслов Алексей и Третьяков Артур
 * @version 1.0
 */
public class Lab_6_Client
{
    public static void main (String[] args)
    {
        UserDragClient userDragonClient = new UserDragClient();
        userDragonClient.process();
    }
}

