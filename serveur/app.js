const express = require('express');
const logger = require('morgan');
const userRouter = require('./routes/user');
const repereRouter = require('./routes/reperes');

const app = express();

app.use(logger('dev'));
app.use(express.json());

app.use('/user', userRouter);
app.use('/repere', repereRouter);

module.exports = app;