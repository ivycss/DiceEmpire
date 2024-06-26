<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    function showAlert() {
        alert("Prodotto aggiunto al carrello con successo!");
    }
</script>

<c:if test="${not empty sessionScope.aggiuntoCarrello}">
    <script type="text/javascript">
        showAlert();
    </script>
    <c:remove var="aggiuntoCarrello" scope="session"/>
</c:if>
