const express = require('express');
const router = express.Router();

const fs = require('fs');
const path = require('path');
const User = require('../User');
const jsonPath = path.join(__dirname, '/../json/user.json');

// Endpoint for user authentication
router.post('/', (req, res) => {
    const file = fs.readFileSync(jsonPath);
    const jsonObject = JSON.parse(file);
    var exist = false;

    // Check if account exists
    for (var user of jsonObject) {
        if (user.username == req.body.username) {
            exist = true
        }
    }
    
    // Create account if doesn't exist
    if (!exist) {
        let data = new User(req.body.username);
        jsonObject.push(data);
        
        fs.writeFile(jsonPath, JSON.stringify(jsonObject), err => {
            if(err) {
                throw err;
            }  

        }); 
    }

    res.send('OK');
});

// Retourne le score de l'utilisateur
router.get('/:user', (req, res) => {
    const file = fs.readFileSync(jsonPath);
    const jsonObject = JSON.parse(file);
    var user = 0;

    for (var user of jsonObject) {
        if (user.username == req.params.user) {
            score = user;
        }
    }

    res.send(user);
});

// Endpoint to modify the user's score
router.put('/:user', (req, res) => {
    const file = fs.readFileSync(jsonPath);
    const jsonObject = JSON.parse(file);
    const tempObject = JSON.parse("[]");

    for (var user of jsonObject) {
        if (user.username == req.params.user) {
            user.score = req.body.score;
        }
        tempObject.push(user);
    }

    fs.writeFile(jsonPath, JSON.stringify(tempObject), err => {
        if(err) {
            throw err;
        }  

    }); 

    res.send('OK');
});

module.exports = router;