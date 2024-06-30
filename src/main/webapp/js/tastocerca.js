$(document).ready(function() {
    $("#search-input").on("keyup", function() {
        var query = $(this).val();
        if (query.length > 1) {
            $.ajax({
                url: "SearchServlet",
                method: "GET",
                data: { query: query },
                success: function(data) {
                    $("#search-results").html(data);
                }
            });
        } else {
            $("#search-results").empty();
        }
    });

    // Gestisci il click sui risultati della ricerca
    $(document).on("click", ".search-result", function() {
        var prodottoId = $(this).data("id");
        window.location.href = "dettagli.jsp?id=" + prodottoId; // Reindirizza alla pagina dettagli.jsp con l'ID del prodotto
    });
});