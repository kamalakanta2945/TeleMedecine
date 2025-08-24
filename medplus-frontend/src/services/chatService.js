import api from './api';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

export const getChatHistory = async (appointmentId) => {
  const response = await api.get(`/chat/${appointmentId}`);
  return response.data;
};

export const connectWebSocket = (onMessage, userId) => {
  const client = new Client({
    //brokerURL: 'ws://localhost:8081/ws',
    connectHeaders: {},
    webSocketFactory: () => new SockJS('http://localhost:8081/chat'),
    onConnect: () => {
      client.subscribe(`/user/${userId}/queue/messages`, onMessage);
    },
  });
  client.activate();
  return client;
};

export const sendMessage = (client, dto, senderId) => {
  client.publish({
    destination: '/app/send',
    body: JSON.stringify({ ...dto, senderId }),
  });
};