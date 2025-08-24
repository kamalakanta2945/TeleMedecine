import React from 'react';
import { Paper, Typography } from '@mui/material';

const MessageBubble = ({ message, isSender }) => {
  return (
    <Paper
      elevation={1}
      className={`p-2 mb-2 max-w-xs ${isSender ? 'bg-blue-500 text-white self-end' : 'bg-gray-200 self-start'}`}
    >
      <Typography>{message.content}</Typography>
      <Typography variant="caption">{message.timestamp}</Typography>
    </Paper>
  );
};

export default MessageBubble;