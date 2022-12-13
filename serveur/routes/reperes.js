const express = require('express');
const router = express.Router();

const fs = require('fs');
const path = require('path');
const Texte = require('../Texte');
const Reperes = require('../Reperes');
const jsonPath = path.join(__dirname, '/../json/reperes.json');

// Endpoint to get the list of landmarks
router.get('/', (req, res) => {
    const file = fs.readFileSync(jsonPath);
    const jsonObject = JSON.parse(file);
    res.send(jsonObject);
});

// Créer un point
router.post('/', (req, res) => {
    const file = fs.readFileSync(jsonPath);
    const jsonObject = JSON.parse(file);
    
    let texte = new Texte(req.body.texte, req.body.reponse);
    let repere = new Reperes(req.body.latitude, req.body.longitude, texte);
    jsonObject.push(repere);
        
    fs.writeFile(jsonPath, JSON.stringify(jsonObject), err => {
        if(err) {
            throw err;
        }  
    }); 

    res.send('OK');
});

// Récupère le texte d'un point
router.get('/:id', (req, res) => {
    const file = fs.readFileSync(jsonPath);
    const jsonObject = JSON.parse(file);
    var texte = null;

    for (var point of jsonObject) {
        if (point.id == req.params.id) {
            texte = point.texte;
        }
    }

    res.send(texte);
});

// Envoie la réponse d'un point
router.post('/:id', (req, res) => {
    const file = fs.readFileSync(jsonPath);
    const jsonObject = JSON.parse(file);
    var result = false

    for (var point of jsonObject) {
        if (point.id == req.params.id && point.texte.reponse == req.body.reponse) {
            result = true;
        }
    }

    res.send(result);
});

module.exports = router;