const crypto = require('crypto');

class Repere {
    constructor (latitude, longitude, texte) {
        this.id = crypto.randomUUID()
        this.latitude = latitude;
        this.longitude = longitude;
        this.texte = texte;
    }
}

module.exports = Repere;