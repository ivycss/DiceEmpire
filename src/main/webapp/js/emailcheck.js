$(document).ready(function() {
    $('#email').on('blur', function() {
        var email = $(this).val();
        $.ajax({
            url: 'check-email',
            type: 'POST',
            data: { email: email },
            success: function(response) {
                if (response.exists) {
                    alert('Email gia\' registrata.');
                    $('#email').val('');
                }
            },
            error: function() {
                alert('Errore durante il controllo dell\'email.');
            }
        });
    });
});