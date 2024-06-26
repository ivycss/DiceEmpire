
// Funzione per aggiornare il totale
function aggiornaTotale() {
    var spedizione = document.getElementById("spedizione").value;

    // Verifica se il valore della spedizione è valido
    if (!isNaN(parseFloat(spedizione))) {
        var totale = parseFloat(document.getElementById("totalPrice").value);


        // Aggiungi il costo della spedizione al totale
        totale += parseFloat(spedizione);

        // Formatta e visualizza il nuovo totale
        document.getElementById("totale").innerHTML = "&euro; " + totale.toFixed(2);
    } else {
        console.error("Il valore della spedizione non è valido.");
    }
}

// Chiama la funzione aggiornaTotale() al caricamento della pagina
window.onload = function() {
    aggiornaTotale();
};
