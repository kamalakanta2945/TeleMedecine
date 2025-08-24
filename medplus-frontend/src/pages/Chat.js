import React, { useState, useEffect, useContext } from 'react';
import { useParams } from 'react-router-dom';
import { Container, Typography, TextField, Button, Stack } from '@mui/material';
import MessageBubble from '../components/MessageBubble';
import { getChatHistory, sendMessage, connectWebSocket } from '../services/chatService';
import { AuthContext } from '../contexts/AuthContext';

const Chat = () => {
  const { appointmentId } = useParams();
  const { user } = useContext(AuthContext);
  const [messages, setMessages] = useState([]);
  const [content, setContent] = useState('');
  const [stompClient, setStompClient] = useState(null);

  useEffect(() => {
    const fetchHistory = async () => {
      const history = await getChatHistory(appointmentId);
      setMessages(history);
    };
    fetchHistory();

    const client = connectWebSocket((msg) => {
      setMessages(prev => [...prev, JSON.parse(msg.body)]);
    }, user.id);
    setStompClient(client);

    return () => client.deactivate();
  }, [appointmentId, user.id]);

  const handleSend = () => {
    if (stompClient && content) {
      sendMessage(stompClient, {
        appointmentId,
        receiverId: 'receiverId_here',  // TODO: Fetch receiverId from appointment
        content,
      }, user.id);
      setContent('');
    }
  };

  return (
    <Container className="mt-8">
      <Typography variant="h4" className="mb-4">Chat for Appointment {appointmentId}</Typography>
      <Stack spacing={2} className="h-96 overflow-y-auto">
        {messages.map(msg => (
          <MessageBubble key={msg.id} message={msg} isSender={msg.senderId === user.id} />
        ))}
      </Stack>
      <div className="flex mt-4">
        <TextField fullWidth value={content} onChange={(e) => setContent(e.target.value)} />
        <Button variant="contained" onClick={handleSend} className="ml-2">Send</Button>
      </div>
    </Container>
  );
};

export default Chat;