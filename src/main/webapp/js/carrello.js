
// funzione per aggiornare il totale
function aggiornaTotale() {
    var spedizione = document.getElementById("spedizione").value;

    // verifica se il valore della spedizione è valido
    if (!isNaN(parseFloat(spedizione))) {
        var totale = parseFloat(document.getElementById("totalPrice").value);


        // aggiungi il costo della spedizione al totale
        totale += parseFloat(spedizione);

        // rende decente il totale e lo mostra oppure da errore
        document.getElementById("totale").innerHTML = "&euro; " + totale.toFixed(2);
    } else {
        console.error("Il valore della spedizione non è valido.");
    }
}

// chiama la funzione aggiornaTotale() al caricamento della pagina
window.onload = function() {
    aggiornaTotale();
};
