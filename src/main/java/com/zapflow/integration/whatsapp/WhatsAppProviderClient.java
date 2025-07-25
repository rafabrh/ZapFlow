package com.zapflow.integration.whatsapp;

public interface WhatsAppProviderClient {
    void sendMessage(String to, String text);
}